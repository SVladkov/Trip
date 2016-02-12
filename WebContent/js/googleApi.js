var latitude,
	longitude,
	map,
	nameOfCity;

function initMap() {
	
  var myLatLng = {lat: 40.363, lng: 24.044};

  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 5,
    center: myLatLng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });
  
  google.maps.event.addListener(map, 'click', function(event) {
	    latitude = event.latLng.lat();
	    longitude = event.latLng.lng();
	});

  /*var marker = new google.maps.Marker({
    position: myLatLng,
    map: map,
    title: 'Hello World!'
  });*/
  
  
}

function geocodeLatLng(geocoder, map, infowindow, value) {
	var input = value;
	var res = "";
	var latlngStr = input.split(',', 2);
	var latlng = {lat: parseFloat(latlngStr[0]), lng: parseFloat(latlngStr[1])};
	geocoder.geocode({'location': latlng}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			if (results[1]) {
	        //map.setZoom(11);
				var marker = new google.maps.Marker({
					position: latlng,
					map: map
				});
				infowindow.setContent(results[1].formatted_address);
				res = results[1].address_components[0].long_name + " ";
				console.log(res);
				nameOfCity = res;
				//console.log(results[1].address_components[0][long_name]);
				infowindow.open(map, marker);
			} else {
				window.alert('No results found');
			}
		} else {
			window.alert('Geocoder failed due to: ' + status);
		}
	});
}