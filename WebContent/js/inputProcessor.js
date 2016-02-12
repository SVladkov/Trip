var indexOfSegment = 0;

function markButtons() {
	markingButtons = document.getElementsByClassName("mark");
	for(var i=0; i<markingButtons.length; i++) {
		markingButtons[i].addEventListener('click', mark, false);
	}
}

function start() {
	addUserButton = document.getElementById("addTrip");
	addUserButton.addEventListener("click", addTrip, false);
	
	addSegmentButton = document.getElementById("addSegment");
	addSegmentButton.addEventListener("click", addSegmentFields, false);
	
	markButtons();
}

var httpRequest;

function addTrip() {
	'use strict';
	var resourceUrl = 'http://localhost:8080/SimpleREST/rest/hello/trip',
		//startPoint = document.getElementById("startPoint").value,
		//endPoint = document.getElementById("endPoint").value;
	
	arrayResult = [];
	for(var i=0; i<=indexOfSegment; i++) {
		var startElement = document.getElementById("startPoint_" + i),
			startCoords = startElement.name,
			startName = startElement.value,
			endElement = document.getElementById("endPoint_" + i),
			endCoords = endElement.name,
			endName = endElement.value;
		
		var jsonElement = {
			startCoordinates: startCoords,
			startName: startName,
			endCoordinates: endCoords,
			endName: endName
		}
		console.log(jsonElement)
		arrayResult.push(jsonElement);
	}
	
	var result = {
		array: arrayResult
	}
	
	console.log(result);
	
	httpRequest = new XMLHttpRequest();

	httpRequest.open("POST", resourceUrl);
	httpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	httpRequest.send(JSON.stringify(result));
}

function addSegmentFields() {
	prevEndPoint = document.getElementById("endPoint_" + indexOfSegment);
	
	indexOfSegment++;
	var newSegment = document.createElement('div');
	newSegment.setAttribute("id", "segment_" + indexOfSegment);
	newSegment.innerHTML = "<label>Start point: </label>" +
			"<input id=\"startPoint_" + indexOfSegment + "\" name=\"" + prevEndPoint.name + "\" type=\"text\" value=\"" + prevEndPoint.value + "\"/>" +
			"<button class=\"mark\">Mark</button>" +
			"<br/>" +
			"<label>Start date: </label>" +
			"<input id=\"startDate_" + indexOfSegment + "\" name=\"startDate\" type=\"date\" />" +
			"<br/>" +		
			"<label>End point: </label>" +
			"<input id=\"endPoint_" + indexOfSegment + "\" name=\"end\" type=\"text\"/>" +
			"<button class=\"mark\">Mark</button>" +
			"<br/>" +
			"<label>End date: </label>" +
			"<input id=\"endDate_" + indexOfSegment + "\" name=\"endDate\" type=\"date\" />" +
			"<br/>" + 
			"<br/>";
	this.parentNode.insertBefore(newSegment, this);
	
	markButtons();
}

function mark() {
	var element = this.previousSibling;
	var result = latitude + "," + longitude;
	element.name = result;
	
	var geocoder = new google.maps.Geocoder;
	var infowindow = new google.maps.InfoWindow;
	
	geocodeLatLng(geocoder, map, infowindow, result);
	
	setTimeout(function(){
		element.value = nameOfCity;
	},500);
}

function index(id) {
	var res = id.split("_");
	return res[1];
}