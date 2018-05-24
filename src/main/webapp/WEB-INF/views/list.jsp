<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    $(document).ready(function(){
        
        $('.modal').modal();
        $(".waves-effect").click(function(){
            $("#lat").val(lat);
            $("#lng").val(lng);
            $("#placeName").val(placeName);
        position = {lat:lat, lng: lng};
        });        
        
        $('#register').click(function(e){
            $.ajax({
                url:'/list',
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    "Accept" : "application/json"
                },
                //contentType: 'application/json',
                dataType:'text',
                data: JSON.stringify({
                    lat: $('#lat').val(),
                    lng: $("#lng").val(),
                    title : $("#title").val(),
                    content : $("#content").val(),
                    placeName : $("#placeName").val()
                }),
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

#pac-input {
	background-color: #fff;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	margin-left: 12px;
	padding: 0 11px 0 13px;
	text-overflow: ellipsis;
	width: 300px;
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

<%@ include file="layout/nav.jsp" %>

	<input id="pac-input" class="controls" type="text"
		placeholder="Search Box">
	<div id="map"></div>
	<script>
    var lat="";
    var lng="";
    var placeName="";
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
              lat = place.geometry.location.lat();
              lng = place.geometry.location.lng();
              placeName = place.name;
              
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
                      lng: lng
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
	

	<a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>

	<!-- Modal Structure -->
	<form id="frm">
		<div id="modal1" class="modal">
			<div class="modal-content">
				lat : <input type="text" name="lat" id="lat" value=""> lng :
				<input type="text" name="lng" id="lng" value=""> 장소 : <input
					type="text" name="placeName" id="placeName" value=""> 제목 :
				<input type="text" name="title" id="title" value=""> 내용 : <input
					type="text" name="content" id="content" value="">
			</div>
			<div class="modal-footer">
				<button type="button" id="register">등록</button>
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

	<form id="updatefrm">
		<div id="modal2" class="modal">
			<div class="modal-content">
				lat : <input type="text" name="lat" id="uplat" value="" readonly>
				lng : <input type="text" name="lng" id="uplng" value="" readonly>
				장소 : <input type="text" name="placeName" id="upplaceName" value="">
				제목 : <input type="text" name="title" id="uptitle" value=""> 내용
				: <input type="text" name="content" id="upcontent" value="">

			</div>
			<div class="modal-footer">
				<button type="button" id="register">update</button>
			</div>
		</div>
	</form>
	
	</main>
</body>
</html>