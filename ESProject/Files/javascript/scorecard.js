let currentWeek;

function xmlStart() {
	if (window.XMLHttpRequest) {
		xml = new XMLHttpRequest();
	}
	//code for IE6, IE5
	else {
		xml = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function constructDataPoints(items) {
	var itemPoints = [];
	for (var i = 0; i < items.length; i++) {
		itemPoints.push({
			x: (i + 1)
			, y: items[i]
		});
	}
	return itemPoints;
}

function constructData(items) {
	var dataPoints = [];
	for (var i = 0; i < items.length; i++) {
		dataPoints.push({
			type: "line"
			, dataPoints: constructDataPoints(items[i].weeklyValues)
			, name: items[i].description
			, showInLegend: true
		});
	}
	return dataPoints;
}

function constructGraphs(rows) {
	var gr = document.getElementById("Graphs");
	for (var i = 0; i < rows.length; i++) {
		var itemsData = rows[i].doc.proc_items;
		var weekval = constructData(itemsData);
		var div = document.createElement("chartContainer");
		$(div).css({
			"width": "50%"
			, "height": "200px"
			, "display": "inline-block"
		});
		gr.appendChild(div);
		var chart = new CanvasJS.Chart(div, {
			theme: "theme2"
			, title: {
				text: rows[i].doc.proc_name
			}
			, animationEnabled: true
			, axisX: {
				valueFormatString: "week #"
				, interval: 1
			, }
			, axisY: {
				includeZero: false
			}
			, data: weekval
		});
		chart.render();
	}
}

function removeScore() {
	node = document.getElementById("Buttons");
	while (node.firstChild != null) {
		node.removeChild(node.firstChild);
	}
	node = document.getElementById("Graphs");
	while (node.firstChild != null) {
		node.removeChild(node.firstChild);
	}
	node = document.getElementById("Table");
	while (node.firstChild != null) {
		node.removeChild(node.firstChild);
	}
}

function getScorecard(callback){
	xmlStart();
	xml.onreadystatechange = function () {
		if (xml.readyState == 4 && xml.status == 200) {
			JsonObject = JSON.parse(xml.responseText)
			var scorecard = JsonObject.data.scorecard;

			return callback(scorecard);
		}
		else if (xml.readyState == 4) {
			JsonObject = JSON.parse(xml.responseText);
			window.alert("getScorecard: " + JsonObject.message);
		}
	}
	xml.open("GET", "http://127.0.0.1:5984/dashboard/processes", true);
	xml.send();
}


function getData(scorecard){
	myArray = [];

	for (var i = 0; i < scorecard.weeks[currentWeek-1].perguntas.length; i++) {

		myObject = {};
		myObject.type = "line";
		myObject.name =  "Question:  " + (i+1);
		myObject.showInLegend =  "true";
		myObject.dataPoints = getDatapoint(scorecard, i);
		myArray.push(myObject);

	}
	return myArray;
}

function getDatapoint(scorecard, index){
	let myArray = [];
	for (var i = 0; i < currentWeek; i++) {
		if(scorecard.weeks[i].perguntas[index] !== null){
			var sum = 0;
			for( var j = 0; j < scorecard.weeks[i].perguntas[index].valores.length; j++ ){
			    sum += scorecard.weeks[i].perguntas[index].valores[j];
			}
			var avg = sum/scorecard.weeks[i].perguntas[index].valores.length;
			myArray.push({
				x: i+1,
				y: avg
			});
		}
	}
	return myArray;
}

function addQuestion(topicQuestion, question){
	var table = JsonObject;
	//POR A NULL NAS RESTANTES WEEKS
	for (var i = 0; i < currentWeek-1; i++) {
		table.data.scorecard.weeks[i].perguntas.push(null);
	}

	table.data.scorecard.weeks[currentWeek-1].perguntas.push({
		topic: topicQuestion,
		texto: question,
		valores: [0,0,0,0,0,0,0]
	});

	$.ajax({
		type: "PUT",
		url: "http://127.0.0.1:5984/dashboard/processes",
		contentType: "application/json",
		data: JSON.stringify(table),
		dataType: "json",
		success: function() {
			editScorecard();
		}
	});
}


function showTextBox(topicQuestion){
	var question = prompt('Please enter your question');
	if (question != null && question != "") {
		addQuestion(topicQuestion,question);
	}
}


function removeQuestion(i, contador){
	var table = JsonObject;
	var isAnswered = false;

	if(contador !== 1) {

		table.data.scorecard.weeks[currentWeek-1].perguntas[i] = null;

		for (var j = 0; j < currentWeek-1; j++) {
			if(table.data.scorecard.weeks[j].perguntas[i] !== null){
				isAnswered = true;
			}
		}
		if(!isAnswered) {
			for (var l = 0; l < currentWeek; l++) {
				table.data.scorecard.weeks[l].perguntas.splice(i, 1);
			}
		}

		$.ajax({
	        type: "PUT",
	        url: "http://127.0.0.1:5984/dashboard/processes",
	        contentType: "application/json",
	        data: JSON.stringify(table),
	        dataType: "json",
	        success: function() {
	            editScorecard();
	        }
	    });
	} else {
		alert("YOU'VE NEED AT LEAST ONE QUESTION PER TOPIC");
	}

}


function editScorecard() {
	if(isPermitted(["Scrum Master"])){
		var weeklyscore = document.getElementById("weeklyscore");
		weeklyscore.style.display = "block";
	    document.getElementById("rolesmenu").style.display = "none";
	    document.getElementById("scorecardgraph").style.display = "none";

		weeklyscore.innerHTML = "";

		let contadorProcess = 0;
		let contadorPeople = 0;
		let contadorTecnologies = 0;

	    getScorecard(function (scorecard){
	        var aux = document.createElement("div");
	        var editscorecardmenustring = "<div class='col-xs-12 scorecard'><h1>Weekly Scorecard</h1><h2 class='topic'>People</h2><a href='javascript:showTextBox(" + '"People"' + ")' class='plus-or-minus'>+</a><br><h2 class='topic'>Process</h2><a href='javascript:showTextBox(" + '"Process"' + ")' class='plus-or-minus'>+</a><br><h2 class='topic'>Technology</h2><a href='javascript:showTextBox(" + '"Tecnologies"' + ")' class='plus-or-minus'>+</a><br></div>";
	        aux.innerHTML = editscorecardmenustring;
	        var editscorecardmenu = aux.firstChild;

			for (var i = 0; i < scorecard.weeks[currentWeek-1].perguntas.length; i++) {
	            if(scorecard.weeks[currentWeek-1].perguntas[i] !== null){
					if (scorecard.weeks[currentWeek-1].perguntas[i].topic === "Process"){
						contadorProcess++;
					} else if (scorecard.weeks[currentWeek-1].perguntas[i].topic === "People"){
						contadorPeople++;
					} else {
						contadorTecnologies++;
					}
				}
			}

	        for (var i = 0; i < scorecard.weeks[currentWeek-1].perguntas.length; i++) {
	            if(scorecard.weeks[currentWeek-1].perguntas[i] !== null){
	                var question = document.createElement("h4");
					question.setAttribute("class","question");
	                question.innerHTML = scorecard.weeks[currentWeek-1].perguntas[i].texto;
	                if (scorecard.weeks[currentWeek-1].perguntas[i].topic === "Process"){

						var minusString = "<a href='javascript:removeQuestion(" + i +"," + contadorProcess + ")' class='plus-or-minus'>-</a>";
						var line = document.createElement("br");
						aux.innerHTML = minusString;
						var minus = aux.firstChild;
						editscorecardmenu.insertBefore(line, editscorecardmenu.getElementsByClassName("topic")[2]);
						editscorecardmenu.insertBefore(minus,line);
						editscorecardmenu.insertBefore(question,minus);


					} else if (scorecard.weeks[currentWeek-1].perguntas[i].topic === "People") {

						var minusString = "<a href='javascript:removeQuestion(" + i +"," + contadorPeople + ")'  class='plus-or-minus'>-</a>";
						var line = document.createElement("br");
						aux.innerHTML = minusString;
						var minus = aux.firstChild;
						editscorecardmenu.insertBefore(line, editscorecardmenu.getElementsByClassName("topic")[1]);
						editscorecardmenu.insertBefore(minus,line);
						editscorecardmenu.insertBefore(question,minus);

					} else {

						var minusString = "<a href='javascript:removeQuestion(" + i + "," + contadorTecnologies + ")' class='plus-or-minus'>-</a>";
						var line = document.createElement("br");
						aux.innerHTML = minusString;
						var minus = aux.firstChild;
						editscorecardmenu.appendChild(line);
						editscorecardmenu.insertBefore(minus,line);
						editscorecardmenu.insertBefore(question,minus);
					}
	            }
	        }

			weeklyscore.appendChild(editscorecardmenu);




	    });
	} else {
		window.alert("This role can't change scorecard questions");
	}
}

function startScorecardGraph() {
    document.getElementById("weeklyscore").style.display = "none";
    document.getElementById("rolesmenu").style.display = "none";
    document.getElementById("scorecardgraph").style.display = "block";
	removeScore();

	var aux = document.createElement("div");
	var changebuttonstring = "<button onclick='editScorecard()' class='btn btn-primary editScore' type='button' data-toggle='dropdown'>Editar</button>";
	aux.innerHTML = changebuttonstring;
	changebutton = aux.firstChild;
	document.getElementById("Buttons").appendChild(changebutton);

	getScorecard(function (scorecard){
		let currentWeekinSeconds = Math.floor(new Date() / 1000);
		let initialTimeinSeconds = scorecard.initialDate;

		currentWeek = 1 + Math.floor((currentWeekinSeconds - initialTimeinSeconds)/(60*60*24*7));

		var div = document.createElement("div");
		div.setAttribute("id", "chartContainer1");
		div.setAttribute("style", "height: 300px;display:center;");
		document.getElementById("Graphs").appendChild(div);
		var chart = new CanvasJS.Chart("chartContainer1", {
			theme: "theme2"
			, title: {
				text: "ScoreCard"
				, fontFamily: "Catamaran"
				, fontColor: "#109877"

			}
			, animationEnabled: true
			, axisX: {
				valueFormatString: "week #"
				, interval: 1
			, }
			, axisY: {
				includeZero: false
			}
			, data: getData(scorecard)
		});

		chart.render();
		var tablediv = document.getElementById("Table");
		var table = document.createElement("table");
		var p = document.createElement("p");


		p.innerHTML = "Week#" + currentWeek;
		p.style.color = "#109877";
		p.style.fontSize="20px";
		table.setAttribute("id", "scorecardTable");
		table.setAttribute("class", "table table-bordered");
		p.style.marginTop = "2.5%";

		for (var i = 0; i < scorecard.weeks[currentWeek-1].perguntas.length; i++) {
			if(scorecard.weeks[currentWeek-1].perguntas[i] !== null){
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				cell1.innerHTML = "Question " + (i+1);
				cell2.innerHTML = scorecard.weeks[currentWeek-1].perguntas[i].texto + "<form class='questionOptions' action=''> <input checked class='scoreOptions' type='radio' name='score' value='1'>1 <input class='scoreOptions' type='radio' name='score' value='2'>2<input class='scoreOptions' type='radio' name='score' value='3'>3<input class='scoreOptions' type='radio' name='score' value='4'>4<input class='scoreOptions' type='radio' name='score' value='5'>5</form>";

			}
		}
		tablediv.appendChild(p);
		tablediv.appendChild(table);
		var aux = document.createElement("div");
		var changebuttonstring = "<button onclick='updateScorecard()' class='btn btn-primary scorecardSubmit' type='submit' data-toggle='dropdown'>Submit</button>";
		aux.innerHTML = changebuttonstring;
		changebutton = aux.firstChild;
		document.getElementById("Buttons").appendChild(changebutton);

	});

}

function updateScorecard (){

	var table = JsonObject;
	let myArray = [];
	var scores = document.getElementsByClassName('questionOptions');

	for (var i = 0; i < scores.length; i++) {
		for (var j = 0; j < 5; j++) {
			if (scores[i][j].checked){
				myArray.push(j+1);
				break;
			}
		}
	}
	let contador = 0;

	for (var i = 0; i < table.data.scorecard.weeks[currentWeek-1].perguntas.length; i++) {
		if(table.data.scorecard.weeks[currentWeek-1].perguntas[i] !== null){
			table.data.scorecard.weeks[currentWeek-1].perguntas[i].valores[user_index] = myArray[contador];
			contador++;
		}
	}

	$.ajax({
        type: "PUT",
        url: "http://127.0.0.1:5984/dashboard/processes",
        contentType: "application/json",
        data: JSON.stringify(table),
        dataType: "json",
        success: function() {
            startScorecardGraph();
        }
    });

}
