<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChK7nSU2T3oBzB1zR2hjUqsRJIRDwFkv0&libraries=places&callback=initAutocomplete" async defer></script>

<style>


#map {
	width: 100%;
	height: 400px;
	padding-right:15px;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

.controls {
	margin-top: 10px;
	border: 1px solid transparent;
	border-radius: 2px 0 0 2px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	height: 32px;
	outline: none;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input{
	background-color: #fff;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	margin-left: 12px;
	padding: 0 11px 0 13px;
	text-overflow: ellipsis;
	width: 300px;
	margin-top: 0.5em;
}

#btnRegister{
	margin-top: 0.75em;
    z-index: 1000;
    left: 440px;
    position: absolute;
}
#pac-input:focus {
	border-color: #4d90fe;
}

.pac-container {
	font-family: Roboto;
}

#type-selector {
	color: #fff;
	background-color: #4d90fe;
	padding: 5px 11px 0px 11px;
}

#type-selector label {
	font-family: Roboto;
	font-size: 13px;
	font-weight: 300;
}

#target {
	width: 345px;
}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        
        $('.modal').modal();
        $(".waves-effect").click(function(){
            $("#lat").val(lat);
            $("#lng").val(lng);
            $("#placeName").val(placeName);
            $("#mapId").val(mapId);
        	position = {lat:lat, lng: lng};
        });        
        
        $('#register').click(function(e){
			
        	if($('#placeName').val()===""){
                alert('장소를 기입해주세요!');
                return false;
            }else if($('#title').val()==""){
	            alert('제목을 기입해주세요!');
	            return false;
            }else if($('#content').val()==""){
  	            alert('내용을 기입해주세요!');
  	            return false;
            }
			var formData = new FormData($("#frm")[0]);
        	
            $.ajax({
                url:'/register',
            	type: 'POST',
                processData : false,
                contentType : false,
                data: formData,
                success: function(data){
                    console.log('register');
                },
                error: function(error){
                    console.log(error);
                }
            });
        });
      });
         
    </script>


<%@ include file="layout/nav.jsp" %>

	<input id="pac-input" class="controls" type="text" placeholder="Search Box">
	<a class="waves-effect waves-light btn modal-trigger" id="btnRegister" href="#modal1">등록</a>
		
	<div id="map"></div>
	<script>
    var lat="";
    var lng="";
    var placeName="";
    var mapId="";
    
    var position = {lat: -33.8688, lng: 151.2195};
      function initAutocomplete() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 37.579617000000, lng: 126.977041000000},
          zoom: 13,
          mapTypeId: 'roadmap'
        });
    var marker = new google.maps.Marker({
          position: position,
          map: map,
          title: 'Click to zoom'
        });
        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });
        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
          var places = searchBox.getPlaces();
          if (places.length == 0) {
            return;
          }

          // Clear out the old markers.
          markers.forEach(function(marker) {
            marker.setMap(null);
          });
          markers = [];
          // For each place, get the icon, name and location.
          var bounds = new google.maps.LatLngBounds();
          places.forEach(function(place) {
              console.log("id = " + place.id);
              console.log("name = " + place.name);
              console.log("position(lat@) = " + place.geometry.location.lat());
              console.log("position(lng) = " + place.geometry.location.lng());
              mapId = place.id;
              lat = place.geometry.location.lat();
              lng = place.geometry.location.lng();
              placeName = place.name;
              console.log("mapId######## = " + mapId);
              $.ajax({
                  url:'/listAjax',
                  method: 'POST',
                  headers: {
                      "Content-Type": "application/json",
                      "Accept" : "application/json"
                  },
                  //contentType: 'application/json',
                  dataType:'text',
                  data: JSON.stringify({
                      lat: lat,
                      lng: lng,
                      mapId : mapId,
                  }),
                  success: function(data){
                	  var obj = JSON.parse(data);
                	  $("#tbody *").remove();
                	  //var str = document.getElementById("tbody");
                	  for (var i=0; i<obj.length; i++){
                		  $('#tbody').append(
                                  $('<tr>')
                                      .append($('<td>').append(obj[i].title).append($('</td>')))
                                      .append($('<td>').append(obj[i].content).append($('</td>')))
                                      .append($('<td>').append(obj[i].memberId).append($('</td>')))
                              );
 					  } 
                  },
                  error: function(error){
                      console.log(error);
                  }
              });
              
              //var loc = typeof(place.geometry.location);
              console.log("location type = " + JSON.stringify(place.geometry.location));
            var marker = new google.maps.Marker({
          position: {lat :place.geometry.location.lat(), lng : place.geometry.location.lng()},
          map: map,
          title: 'Click to zoom'
        });
            if (place.geometry.viewport) {
              // Only geocodes have viewport.
              bounds.union(place.geometry.viewport);
            } else {
              bounds.extend(place.geometry.location);
            }
          });
          map.fitBounds(bounds);
        });
      }
    </script>


	<!-- Modal Structure -->
	<form id="frm" enctype="multipart/form-data">
		<div id="modal1" class="modal">
			<div class="modal-content"> 
				장소 : <input type="text" name="placeName" id="placeName" required> 
				제목 : <input type="text" name="title" id="title" required> 
				내용 : <input type="text" name="content" id="content">
				파일 : <input type="file" name="file" id="file">
					<input type="hidden" name="mapId" id="mapId">
					<input type="hidden" name="lat" id="lat">
					<input type="hidden" name="lng" id="lng">
					<!--  <input type="file" name="fileName" id="fileName"> -->
				
			</div>
			
			<div class="modal-footer">
				<button type="button" id="register" class="modal-close btn-flat">등록</button>
				<a href="#!" class="modal-close waves-effect waves-red btn-flat">닫기</a>
			</div>
		</div>
	</form>

	<div class="container">
		<div class="row">
			<table class="highlight centered">
				<thead>
					<tr>
						<th>제목</th>
						<th>내용</th>
						<th>작성자</th>
					</tr>
				</thead>

				<tbody id="tbody">
				<c:forEach items="${list}" var="data">
					<tr>
						<td><a class="modal-trigger" href="#modal2">${data.title}</a></td>
						<td>${data.content}</td>
						<td>${data.memberId}</td>
					</tr>
					
				</c:forEach>
				</tbody>
			</table>


		</div>
	</div>
