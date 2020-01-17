

function xmlStart() {
	if (window.XMLHttpRequest) {
		xml = new XMLHttpRequest();
	}
	//code for IE6, IE5
	else {
		xml = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function getMembers() {
	xmlStart();
	xml.onreadystatechange = function () {
		if (xml.readyState == 4 && xml.status == 200) {
			JsonObject = JSON.parse(xml.responseText);
			//console.log(JsonObject);
			criaMembros();
		}
		else if (xml.readyState == 4) {
			JsonObject = JSON.parse(xml.responseText);
			window.alert("getMembers: " + JsonObject.message);
		}
	}
	xml.open("GET", host + "/projects/" + project_id + "/members", true);
	xml.setRequestHeader('PRIVATE-TOKEN', private_token);
	xml.send();
}

function criaMembros() {
	for (var i = 0; i < JsonObject.length; i++) {
		var peop = document.createElement("div");
		var li = document.createElement("li");
		var btn = document.createElement("button");
		var img = document.createElement("img");
		var txt = document.createElement("p");
		var txt2 = document.createElement("p");
		var btn2 = document.createElement("button");
		txt.innerHTML = JsonObject[i].name;
		$(txt).css({
			"margin": "0 0 0"
			, "padding-top": "5px"
			, "top": "100px"
			, "max-height": "40px"
			, "color": "#109877"
			, "left": "-15px"
			, "position": "absolute"
			, "overflow": "hidden"
		});
		img.src = JsonObject[i].avatar_url;
		$(img).css({
			"width": "100px"
			, "height": "100px"
			, "left": "5px"
			, "top": "-15px"
			, "border-radius":"50px"
			, "margin-top": "20px"
			, "position": "absolute"
		});
		btn.appendChild(img);
		btn.appendChild(txt);
		$(btn).css({
			"width": "120px"
			, "height": "140px"
			, "background-color": "white"
			, "margin-top": "45px"
			, "border-style": "none"
			, "outline": "0"
			, "margin": "0"
			, "position": "relative"
		});
		txt2.innerHTML = "Contribuições";
		$(txt2).css({
			"-moz-transform": "rotate(-90deg)"
			, "-ms-transform": "rotate(-90deg)"
			, "-o-transform": "rotate(-90deg)"
			, "-webkit-transform": "rotate(-90deg)"
			, "margin-top": "90px"
		});
		btn2.appendChild(txt2);
		$(btn2).css({
			"width": "20px"
			, "height": "140px"
			, "border-radius": "50px"
			, "position": "relative"
			, "border-style": "none"
			, "outline": "0"
			, "background-color": "#109877"
			, "top": "-21px"
		});
		li.appendChild(btn);
		li.appendChild(btn2);
		$(li).css({
			"display": "inline-table"
			, "margin": "15px"
			, "position": "relative"
		});
		document.getElementById("list").appendChild(li);
	}
}
