function start() {
	addUserButton = document.getElementById("addUser");
	addUserButton.addEventListener("click", registerUser, false);
	
	addUserButton = document.getElementById("loginButton");
	addUserButton.addEventListener("click", login, false);
}

var httpRequest;

function registerUser() {
	var resourceURL = 'http://localhost:8080/SimpleREST/rest/trips/register';
	
	var email = document.getElementById("email").value,
		password = document.getElementById("password").value;

	
	var jsonElement = {
		email: email,
		password: password
	}
	
	httpRequest = new XMLHttpRequest();

	httpRequest.open("POST", resourceURL);
	httpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	httpRequest.send(JSON.stringify(jsonElement));
	
	setTimeout(function(){
		window.location = httpRequest.responseText;
	},500);
}

function login() {
	var resourceURL = 'http://localhost:8080/SimpleREST/rest/trips/login';
	
	var email = document.getElementById("email").value,
	password = document.getElementById("password").value;

	
	var jsonElement = {
		email: email,
		password: password
	}
	
	httpRequest = new XMLHttpRequest();
	
	httpRequest.open("POST", resourceURL);
	httpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	httpRequest.send(JSON.stringify(jsonElement));
	
	setTimeout(function(){
		window.location = httpRequest.responseText;
	},500);
}