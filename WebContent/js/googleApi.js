var latitude,
	longitude,
	map,
	nameOfCity,
	distance;

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
}

function geocodeLatLng(geocoder, map, infowindow, value) {
	var input = value;
	var res = "";
	var latlngStr = input.split(',', 2);
	var latlng = {lat: parseFloat(latlngStr[0]), lng: parseFloat(latlngStr[1])};
	geocoder.geocode({'location': latlng}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			if (results[1]) {
				var marker = new google.maps.Marker({
					position: latlng,
					map: map
				});
				infowindow.setContent(results[1].formatted_address);
				res = results[1].address_components[0].long_name + " ";
				console.log(res);
				nameOfCity = res;
				infowindow.open(map, marker);
			} else {
				window.alert('No results found');
			}
		} else {
			window.alert('Geocoder failed due to: ' + status);
		}
	});
}

function getDistance(start, end) {
	var startAsArray = start.split(','),
		endAsArray = end.split(',');
	var origin = new google.maps.LatLng(startAsArray[0], startAsArray[1]);
	var destination = new google.maps.LatLng(endAsArray[0], endAsArray[1]);
	
	var service = new google.maps.DistanceMatrixService();
	service.getDistanceMatrix(
	{
		origins: [origin],
		destinations: [destination],
		travelMode: google.maps.TravelMode.DRIVING,
	    unitSystem: google.maps.UnitSystem.METRIC,
	    avoidHighways: false,
	    avoidTolls: false,
	}, callback);
}

function callback(response, status) {
	if (status == google.maps.DistanceMatrixStatus.OK) {
		var origins = response.originAddresses;
		var destinations = response.destinationAddresses;
		
		for (var i = 0; i < origins.length; i++) {
			var results = response.rows[i].elements;
			
			console.log(results);
			
  			for (var j = 0; j < results.length; j++) {
	  			var element = results[j];
    			distance = element.distance.text;
    			console.log(distance);
    			var duration = element.duration.text;
    			var from = origins[i];
    			var to = destinations[j];
    		}
		}
	}
}

	
