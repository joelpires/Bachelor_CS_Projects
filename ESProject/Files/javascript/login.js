var host = "https://git.dei.uc.pt/api/v3";
var username;
var pass;
var private_token;
var name;
var avatar_url;
var user_url;
var xml;

function xmlStart() {
	if (window.XMLHttpRequest) {
		xml = new XMLHttpRequest();
	}
	//code for IE6, IE5
	else {
		xml = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function alreadyLogged(){
	if (sessionStorage.length != 0) {
		location.replace("../html/project.html");
	}
}

function sessionStart() {
	xmlStart();
	var JsonObject;
	xml.onreadystatechange = function () {
		if (xml.readyState == 4 && xml.status == 201) {
			JsonObject = JSON.parse(xml.responseText);
			private_token = JsonObject.private_token;
			name = JsonObject.name;
			avatar_url = JsonObject.avatar_url;
			setStorage();
			//window.location.href = "project.html";
		}
		else if (xml.readyState == 4) {
			JsonObject = JSON.parse(xml.responseText);
			window.alert("sessionStart: " + JsonObject.message);
		}
	}
	username = document.getElementById("uname").value;
	pass = document.getElementById("psw").value;
	xml.open("POST", host + "/session?login=" + encodeURIComponent(username) + "&password=" + encodeURIComponent(pass), true);
	xml.send();
}


function setStorage() {
	sessionStorage.setItem("user_url", user_url);
	sessionStorage.setItem("username", username);
	sessionStorage.setItem("name", name);
	sessionStorage.setItem("avatar_url", avatar_url);
	sessionStorage.setItem("private_token", private_token);
    sessionStorage.setItem("project_id", 816);
    sessionStorage.setItem("project_name", "gitDashboard");
    setRoleOnSession("undefined");
}
