
function xhttpStart() {
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    }
    //code for IE6, IE5
    else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function getCommits() {
    xhttpStart();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            JsonObject = JSON.parse(xhttp.responseText);
            createCommits();
        }
        else if (xhttp.readyState == 4) {
            JsonObject = JSON.parse(xhttp.responseText);
            window.alert("getProjects: " + JsonObject.message);
        }
    }
    xhttp.open("GET", host + "/projects/" + project_id + "/repository/commits?private_token=" + private_token ,true);
    xhttp.setRequestHeader('PRIVATE-TOKEN', private_token);
    xhttp.send();
}

function createCommits() {
    for (var i = 0; i < JsonObject.length; i++) {
        var proj = document.createElement("div");
        var li = document.createElement("li");
        var btn = document.createElement("button");
        var txt = document.createElement("p");
        var description = document.createElement("p");
        var last_act = document.createElement("p");


        txt.innerHTML = "FILENAME" + " | " +"\"" + JsonObject[i].title + "\"";
        description.innerHTML =JsonObject[i].author_name+" | "+ convertISO8601toDate(JsonObject[i].created_at);


        proj.appendChild(txt);
        proj.appendChild(description);

        btn.appendChild(proj);
        btn.appendChild(last_act);

        li.appendChild(btn);
        document.getElementById("list").appendChild(li);

        $(last_act).addClass("last_act");
        $(description).addClass("description");
        $(proj).addClass("proj");
        $(btn).addClass("btn3");
        $(txt).addClass("txt");
    }
}

function convertISO8601toDate(dtstr) {
    dtstr = dtstr.replace(/\D/g, " ");
    dtstr = dtstr.replace(/\s+$/, "");
    var dtcomps = dtstr.split(" ");
    if (dtcomps.length < 3) return "invalid date";
    if (dtcomps.length < 4) {
        dtcomps[3] = 0;
        dtcomps[4] = 0;
        dtcomps[5] = 0;
    }
    dtcomps[1]--;
    var convdt = new
    Date(Date.UTC(dtcomps[0], dtcomps[1], dtcomps[2], dtcomps[3], dtcomps[4], dtcomps[5]));
    return convdt.toUTCString();
}
