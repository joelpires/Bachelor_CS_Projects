var username;
var name;
var avatar_url;
var user_url;
var alerts = "Alertas User";
var project_id;
var project_name;
var host = "https://git.dei.uc.pt/api/v3";
var xml;
var JsonObject;
var private_token;
var user_index;
//var user_role;

function startContent() {
	startTime();
	getSessionStorage();
}

function startTime() {
	var today = new Date();
	var weekday = new Array(7);
	weekday[0] = "Domingo";
	weekday[1] = "Segunda-Feira";
	weekday[2] = "Terça-Feira";
	weekday[3] = "Quarta-Feira";
	weekday[4] = "Quinta-Feira";
	weekday[5] = "Sexta-Feira";
	weekday[6] = "Sábado";
	var monthN = new Array(12);
	monthN[0] = "Jan";
	monthN[1] = "Fev";
	monthN[2] = "Mar";
	monthN[3] = "Abr";
	monthN[4] = "Mai";
	monthN[5] = "Jun";
	monthN[6] = "Jul";
	monthN[7] = "Ago";
	monthN[8] = "Set";
	monthN[9] = "Out";
	monthN[10] = "Nov";
	monthN[11] = "Dez";
	var dayW = weekday[today.getDay()];
	var day = today.getDate();
	var month = monthN[today.getMonth()];
	var year = today.getFullYear();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	m = checkTime(m);
	s = checkTime(s);
	document.getElementById("date").innerHTML = dayW + ", " + day + " " + month + " " + year + " | " + h + ":" + m + ":" + s;
	var t = setTimeout(startTime, 500);
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i
	}; // add zero in front of numbers < 10
	return i;
}

function getSessionStorage() {
	if (sessionStorage.length != 0) {
        username = sessionStorage.getItem("username");
		name = sessionStorage.getItem("name");
		avatar_url = sessionStorage.getItem("avatar_url");
		user_url = sessionStorage.getItem("user_url");
		project_name = sessionStorage.getItem("project_name");
		project_id = sessionStorage.getItem("project_id");
		private_token = sessionStorage.getItem("private_token")
        user_index = sessionStorage.getItem("user_index");
        //user_role = sessionStorage.getItem("user_role")
	}
    else{
        location.replace("../html/login.html");
    }
	document.getElementById("avatar").src = avatar_url;
	document.getElementById("name").innerHTML = name;
	document.getElementById("project").innerHTML = project_name;
	document.getElementById("alertsH").innerHTML = alerts;
}

function logOut() {
	sessionStorage.clear();
	window.location.href = "login.html";
}
