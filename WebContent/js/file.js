function start() {
	addUserButton = document.getElementById("addUser");
	addUserButton.addEventListener("click", addUser, false);
}

var httpRequest;

function addUser() {
	'use strict';
	var resourceUrl = 'http://localhost:8080/SimpleREST/rest/hello/register',
		username = document.getElementById("username").value,
		password = document.getElementById("password").value;
	
	httpRequest = new XMLHttpRequest();
	console.log(httpRequest);

	httpRequest.open("POST", resourceUrl);
	httpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	var brat = httpRequest.send(JSON.stringify({username:username, password:password}));
	
	setTimeout(function(){
		window.location = httpRequest.responseText;
	},500);
}