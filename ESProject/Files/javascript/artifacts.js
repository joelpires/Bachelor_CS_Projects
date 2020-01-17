var current_path = new Array();
var pathArray = new Array();
var nameFolder = "undefined";
var path = "undefined";
var xml2;
var branch = "master";
var trees;
var backControl;
var viewControl=0;

function createBranchesButton(){
	document.getElementById("master").addEventListener("click", buttonHandler);
	document.getElementById("bckBtn").addEventListener("click", bckBtnHandler);
	document.getElementById("Lista").addEventListener("click", viewBtnHandler);
	document.getElementById("Icones").addEventListener("click", viewBtnHandler);
	xmlStart2();
	xml2.open("GET", host + "/projects/" + project_id + "/repository/branches", true);
	xml2.setRequestHeader('PRIVATE-TOKEN', private_token)
	xml2.send();
	xml2.onreadystatechange = function () {
		if (xml2.readyState == 4 && xml2.status == 200) {
			JsonObject = JSON.parse(xml2.responseText);
			for(var i=0;i<JsonObject.length;i++){
				if(JsonObject[i].name!="master"){
					var li = document.getElementById("master").parentNode.cloneNode(true);
					li.firstChild.id = JsonObject[i].name;
					li.firstChild.innerHTML = JsonObject[i].name;
					document.getElementById("branches").appendChild(li);
					document.getElementById(JsonObject[i].name).addEventListener("click", buttonHandler);
				}
				else{
					document.getElementById("master").addEventListener("click",buttonHandler);
				}			
			}
		}
		else if (xml2.readyState == 4) {
			JsonObject = JSON.parse(xml2.responseText);
			window.alert("createBranchesButton: " + JsonObject.message);
		}
	}
}

function xmlStart() {
	if (window.XMLHttpRequest) {
		xml = new XMLHttpRequest();
	}
	//code for IE6, IE5
	else {
		xml = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function xmlStart2() {
	if (window.XMLHttpRequest) {
		xml2 = new XMLHttpRequest();
	}
	//code for IE6, IE5
	else {
		xml2 = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
function buttonHandler(){
	var bot = event.target.id;
	branch = bot;
	var cenas = document.getElementById("divArtifacts").childNodes[3].innerHTML="";
	current_path = new Array();
	pathArray = new Array();
	nameFolder = "undefined";
	path = "undefined";
	document.getElementById("pathFiles").firstChild.innerHTML="/";
	getRepositoryTree();
}
function bckBtnHandler(){
	if(current_path.length<=1){
		current_path.pop();
		nameFolder=current_path[current_path.length-1];
		path = arrToPath2(current_path);
		removeButtons(path,backControl);
		getRepositoryTree();
		document.getElementById("pathFiles").firstChild.innerHTML="/";
	}
	else if(current_path.length>1){
		current_path.pop();
		nameFolder=current_path[current_path.length-1];
		path = arrToPath2(current_path);
		removeButtons(path,backControl);
		getRepositoryTree();
		createPath(path);
	}
}
function viewBtnHandler(){
	var bot = event.target.id;
	switch(bot){
		case "Icones":
			viewControl=1;
			update();
			break;
		case "Lista":
			viewControl=0;
			update();
			break;
		default:
			break;
	}

}



function getRepositoryTree() {
	xmlStart();
	if (path == "undefined") {
		xml.open("GET", host + "/projects/" + project_id + "/repository/tree?ref_name="+branch, true);
		xml.setRequestHeader('PRIVATE-TOKEN', private_token);
	}
	else {
		xml.open("GET", host + "/projects/" + project_id + "/repository/tree?path=" + path+";ref_name="+branch, true);
		xml.setRequestHeader('PRIVATE-TOKEN', private_token);
	}
	xml.send();
	xml.onreadystatechange = function () {
		if (xml.readyState == 4 && xml.status == 200) {
			JsonObject = JSON.parse(xml.responseText);
			update();
		}
		else if (xml.readyState == 4) {
			JsonObject = JSON.parse(xml.responseText);
			window.alert("getRepositoryTree: " + JsonObject.message);
		}
	}
}

function createFiles() {
	var ul = document.createElement("ul");
	var li1 = document.createElement("li");
	for (var i = 0; i < JsonObject.length; i++) {
		var li = document.createElement("li");
		var btn = document.createElement("button");
		var img = document.createElement("img");
		var txt = document.createElement("p");
		var txt2 = document.createElement("p");
		if (JsonObject[i].name.length > 19) {
			txt.innerHTML = JsonObject[i].name.substring(0, 16) + "...";
		}
		else txt.innerHTML = JsonObject[i].name;
		txt.value = JsonObject[i].name;
		if (path == "undefined") txt2.value = JsonObject[i].type + "-" + i + "-" + JsonObject[i].name;
		else txt2.value = JsonObject[i].type + "-" + i + "-" + path + "/" + JsonObject[i].name;
		if (JsonObject[i].type == "tree"){
			img.src = "../imgs/pasta.png";

		} 
		else img.src = "../imgs/iconficheiro.png";
		$(img).css({
			"width": "30px"
			, "height": "30px"
			, "position": "absolute"
			, "left": "0"
			, "top": "0"
		});
		$(txt).css({
			"width": "140px"
			, "text-align": "left"
			, "position": "absolute"
			, "left": "30px"
			, "top": "6px"
		});
		btn.appendChild(img);
		btn.appendChild(img);
		btn.appendChild(txt);
		btn.appendChild(txt2);
		$(btn).css({
			"width": "170px"
			, "height": "30px"
			, "background-color": "#109877"
			, "border-radius": "50px"
			, "padding-top": "15px"
			, "border-style": "none"
			, "position": "relative"
			, "display": "inline"
			, "outline": "0"
		});
		if(JsonObject[i].type=="tree"){
			btn.onclick = function () {
				$(this).parent().siblings().find("button").css({
					"background-color": "#0d7c61"
				});
				$(this).css({
					"background-color": "#109877"
				});
				if ($(this).find("p").eq(1).val().split("-")[2] != arrToPath(current_path)) {
					var teste;
					nameFolder = $(this).find("p").eq(0).val();
					path = $(this).find("p").eq(1).val().split("-")[2];
					current_path.push(nameFolder);
					removeButtons(path, $(this).find("p").eq(1).val().split("-")[1]);
					getRepositoryTree();
					createPath(path);
				}
			};	
		}
		else{
			btn.onclick = function () {
				$(this).parent().siblings().find("button").css({
					"background-color": "#0d7c61"
				});
				$(this).css({
					"background-color": "#109877"
				});
				if ($(this).find("p").eq(1).val().split("-")[2] != arrToPath(current_path)) {
					var teste;
					nameFolder = $(this).find("p").eq(0).val();
					path = $(this).find("p").eq(1).val().split("-")[2];
					current_path.push(nameFolder);
					removeButtons(path, $(this).find("p").eq(1).val().split("-")[1]);
					createPath(path);
				}
			};	
		} 
		li.appendChild(btn);
		$(li).css({
			"display": "list-item"
			, "text-align": "left"
		});
		ul.appendChild(li);
	}
	$(ul).css({
		"list-style-type": "none"
		, "cursor": "pointer"
		, "padding": "0"
	});
	li1.appendChild(ul);
	$(li1).css({
		"padding": "0"
		, "margin": "0"
		, "overflow": "auto"
		, "display": "inherit"
		, "padding-top":"40px"
	});
	var cenas = document.getElementById("divArtifacts");
	cenas.replaceChild(li1,cenas.childNodes[3]);
}

function createFiles2() {
	var ul = document.createElement("ul");
	var li1 = document.createElement("li");
	for (var i = 0; i < JsonObject.length; i++) {
		var li = document.createElement("li");
		var btn = document.createElement("button");
		var img = document.createElement("img");
		var txt = document.createElement("p");
		var txt2 = document.createElement("p");
		if (JsonObject[i].name.length > 19) {
			txt.innerHTML = JsonObject[i].name.substring(0, 16) + "...";
		}
		else txt.innerHTML = JsonObject[i].name;
		txt.value = JsonObject[i].name;
		if (path == "undefined") txt2.value = JsonObject[i].type + "-" + i + "-" + JsonObject[i].name;
		else txt2.value = JsonObject[i].type + "-" + i + "-" + path + "/" + JsonObject[i].name;
		if (JsonObject[i].type == "tree"){
			img.src = "../imgs/pasta.png";

		} 
		else img.src = "../imgs/iconficheiro.png";
		$(img).css({
			"width": "60px"
			, "height": "60px"
			, "position": "relative"
			, "left": "0"
			, "top": "0"
		});
		$(txt).css({
			"width": "140px"
			, "text-align": "center"
			, "position": "relative"
			,"color":"#0d7c61"
			, "left": "-25px"
			, "top": "-5px"
		});
		btn.appendChild(img);
		btn.appendChild(img);
		btn.appendChild(txt);
		btn.appendChild(txt2);
		$(btn).css({
			"width": "100px"
			, "height": "100px"
			, "padding-top": "15px"
			, "border-style": "none"
			, "position": "relative"
			,"background-color":"white"
			, "display": "inline"
			, "outline": "0"
			, "top": "20px"
		});
		if(JsonObject[i].type=="tree"){
			btn.onclick = function () {
				$(this).parent().siblings().find("button").css({
					//"background-color": "#0d7c61"
				});
				$(this).css({
					//"background-color": "#109877"
				});
				if ($(this).find("p").eq(1).val().split("-")[2] != arrToPath(current_path)) {
					var teste;
					nameFolder = $(this).find("p").eq(0).val();
					path = $(this).find("p").eq(1).val().split("-")[2];
					current_path.push(nameFolder);
					removeButtons(path, $(this).find("p").eq(1).val().split("-")[1]);
					getRepositoryTree();
					createPath(path);
				}
			};	
		}
		else{
			btn.onclick = function () {
				$(this).parent().siblings().find("button").css({
					//"background-color": "#0d7c61"
				});
				$(this).css({
					//"background-color": "#109877"
				});
				if ($(this).find("p").eq(1).val().split("-")[2] != arrToPath(current_path)) {
					var teste;
					nameFolder = $(this).find("p").eq(0).val();
					path = $(this).find("p").eq(1).val().split("-")[2];
					current_path.push(nameFolder);
					removeButtons(path, $(this).find("p").eq(1).val().split("-")[1]);
					createPath(path);
				}
			};	
		} 
		li.appendChild(btn);
		$(li).css({
			"display": "inline"
			, "text-align": "left"
		});
		ul.appendChild(li);
	}
	$(ul).css({
		"list-style-type": "none"
		, "cursor": "pointer"
		, "padding": "0"
	});
	li1.appendChild(ul);
	$(li1).css({
		"padding": "0"
		, "margin": "0"
		, "overflow": "auto"
		, "display": "inline"
	});
	var cenas = document.getElementById("divArtifacts");
	cenas.replaceChild(li1,cenas.childNodes[3]);
}

function createPath(path) {
	var listaPath = document.getElementById("pathFiles");
	while (listaPath.firstChild) {
		listaPath.removeChild(listaPath.firstChild);
	}
	var li1 = document.createElement("li");
	li1.innerHTML = "/"+path;
	listaPath.appendChild(li1);
	for (var i = 0; i < current_path.length; i++) {
		var li = document.createElement("li");
		var btn = document.createElement("button");
		var txt = document.createElement("p");
		for(var i = 0;i<current_path.length-1;i++){
		}
		if (current_path[i].length > 19) {
			txt.innerHTML = current_path[i].substring(0, 16) + ".../";
		}
		else txt.innerHTML = current_path[i] + "/";
		txt.value = current_path[i] + "/";
		$(txt).css({
			"margin": "0"
			, "padding": "0"
		});
		btn.appendChild(txt);
		$(btn).css({
			"margin": "0"
			, "padding": "0"
			, "background-color": "white"
			, "border-style": "none"
			, "position": "relative"
			, "display": "inherit"
			, "outline": "0"
		});
		li.appendChild(btn);
		listaPath.appendChild(li);
	}
}

function createPath(path) {
	var listaPath = document.getElementById("pathFiles");
	while (listaPath.firstChild) {
		listaPath.removeChild(listaPath.firstChild);
	}
	var li1 = document.createElement("li");
	li1.innerHTML = "/"+path;
	listaPath.appendChild(li1);
	for (var i = 0; i < current_path.length; i++) {
		var li = document.createElement("li");
		var btn = document.createElement("button");
		var txt = document.createElement("p");
		for(var i = 0;i<current_path.length-1;i++){
		}
		if (current_path[i].length > 19) {
			txt.innerHTML = current_path[i].substring(0, 16) + ".../";
		}
		else txt.innerHTML = current_path[i] + "/";
		txt.value = current_path[i] + "/";
		$(txt).css({
			"margin": "0"
			, "padding": "0"
		});
		btn.appendChild(txt);
		$(btn).css({
			"margin": "0"
			, "padding": "0"
			, "background-color": "white"
			, "border-style": "none"
			, "position": "relative"
			, "display": "inline"
			, "outline": "0"
		});
		li.appendChild(btn);
		listaPath.appendChild(li);
	}
}

function getFileInfo(path, file) {
	xmlStart();
	if (path == "undefined") xml.open("GET", host + "/projects/" + project_id + "/repository/files?file_path=" + file + "&ref=" + ref + "&private_token=" + private_token, true);
	else xml.open("GET", host + "/projects/" + project_id + "/repository/files?file_path=" + path + "/" + file + "&ref=" + branch + "&private_token=" + private_token, true);
	xml.send();
	var JsonObjecti;
	xml.onreadystatechange = function () {
		if (xml.readyState == 4 && xml.status == 200) {
			JsonObjecti = JSON.parse(xml.responseText)
			document.getElementsByClassName("stylename")[0].innerHTML = JsonObjecti.file_name;
			document.getElementsByClassName("stylepath")[0].innerHTML = JsonObjecti.file_path;
			document.getElementsByClassName("stylesize")[0].innerHTML = JsonObjecti.size;
		}
	}
}

function update() {
	console.log(viewControl);
	switch(viewControl){
		case 0:
			createFiles();
			break;
		case 1:
			createFiles2();
			break;
		default:
			break;
	}
}

function removeButtons(clicked, i) {
	pathArray = clicked.split("/");
	var node = document.getElementById("listFiles");
	while (current_path.length != pathArray.length && node!=null) {
		current_path.pop();
		node.removeChild(node.lastElementChild);
	}
	current_path = pathArray;
}

function arrToPath(array) {
	var string = "";
	for (var i = 1; i < array.length - 1; i++) {
		string += array[i] + "/";
	}
	string += array[i];
	return string;
}

function arrToPath2(array) {
	var string = "";
	for (var i = 0; i < array.length - 1; i++) {
		string += array[i] + "/";
	}
	string += array[i];
	return string;
}
