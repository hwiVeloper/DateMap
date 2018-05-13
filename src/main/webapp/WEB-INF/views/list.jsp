<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
 <!-- Compiled and minified CSS -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Places Searchbox</title>
    <script>
    $(document).ready(function(){
    	
        $('.modal').modal();
        $(".waves-effect").click(function(){
        	$("#lat").val(lat);
        	$("#lng").val(lng);
        	$("#placeName").val(placeName);
        });        
        
        $('#register').click(function(e){
            $.ajax({
                url:'/register',
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
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 50%;
        width: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
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
  </head>
  <body>
    <input id="pac-input" class="controls" type="text" placeholder="Search Box">
    <div id="map"></div>
    <script>
	var lat="";
	var lng="";
	var placeName="";
      function initAutocomplete() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -33.8688, lng: 151.2195},
          zoom: 13,
          mapTypeId: 'roadmap'
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
       	    //var loc = typeof(place.geometry.location);
       	    console.log("location type = " + JSON.stringify(place.geometry.location));
			
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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChK7nSU2T3oBzB1zR2hjUqsRJIRDwFkv0&libraries=places&callback=initAutocomplete"
         async defer></script>
  
   <a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>

	<!-- Modal Structure -->
	<form id="frm">
		<div id="modal1" class="modal">
			<div class="modal-content">
				lat : <input type="text" name="lat" id="lat" value="">
				lng : <input type="text" name="lng" id="lng" value="">
				장소 : <input type="text" name="placeName" id="placeName" value="">
				제목 :  <input type="text" name="title" id="title" value="">
				내용 : <input type="text" name="content" id="content" value="">
			</div>
			<div class="modal-footer">
				<button type="button" id="register">등록</button>
			</div>
		</div>
	</form>
	<div>
	<c:forEach items="${list}" var="data">
		${data.content}
	</c:forEach>
		
	</div>
</body>
</html>