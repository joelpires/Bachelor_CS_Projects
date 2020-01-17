function getRoles(callback) {
    xmlStart();
    xml.onreadystatechange = function () {
		if (xml.readyState == 4 && xml.status == 200) {
			JsonObject = JSON.parse(xml.responseText)
            var roles = JsonObject.data;
            return callback(roles);
		}
		else if (xml.readyState == 4) {
			JsonObject = JSON.parse(xml.responseText);
			window.alert("getRoles: " + JsonObject.message);
		}
	}
    xml.open("GET", "http://127.0.0.1:5984/dashboard/people", true);
    //xml.open("GET", host + "/projects/" + project_id + "/repository/files?file_path=roles.json&ref=new_integration", true);
    //xml.setRequestHeader('PRIVATE-TOKEN', private_token);
    xml.send();
}

function setRoleOnSession(member){
      getRoles(function(roles){
        var i=0;
        for(var key in roles){
            if(username == key){
                sessionStorage.setItem("user_role",roles[key].roles);
                sessionStorage.setItem("user_index",i);
                console.log(sessionStorage.getItem("user_role"));
            }
            i++;
        }
        if (member == "undefined"){
            window.location.href = "project.html"; 
        }
        else{ 
            showMemberRoles(member);
        }
      });
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

function isPermitted(role){
    for(var i=0; i<role.length;i++){
       if(sessionStorage.getItem("user_role").includes(role[i])){
            return true;
       }
    }
    return false;
}

function changeRole() {
    var table = JsonObject;
    var member = document.getElementById('member').innerHTML;
    var rolelist = document.getElementsByClassName('role-option');
    var user;
    
    for(var key in table.data){
        if (table.data[key].name == member) {
            user = key;
            table.data[key].roles = [];
            for(var i=0; i<rolelist.length; i++){
                if (rolelist[i].checked == true){
                    if(rolelist[i].value == "Scrum Master" && key != username){
                        alert("You will lose your Scrum Master permissions if you assign another team member with that role");
                        table.data[key].roles.push(rolelist[i].value);
                        table.data[username].roles.splice(0,1);
                        user = "undefined";
                    }
                    else{
                        table.data[key].roles.push(rolelist[i].value);
                    }
                }
            }
            if (key == username && !table.data[key].roles.includes("Scrum Master")){
                alert("You can't remove the Scrum Master role from yourself, you can only change it to another team member");
                table.data[key].roles.unshift("Scrum Master");
            }
        }
    }
    $.ajax({
        type: "PUT",
        url: "http://127.0.0.1:5984/dashboard/people",
        contentType: "application/json",
        data: JSON.stringify(table),
        dataType: "json",
        success: function(data) {
            if (user == username) {
                setRoleOnSession(member);
            }
            else if(user == "undefined"){
                sessionStorage.setItem("user_role",table.data[username].roles);
                window.location.href = "process.html"; 
            }
            else{
                showMemberRoles(member);   
            }
        }
    });

    //xml.open("PUT", "http://127.0.0.1:5984/dashboard/people " + table + "'", true);
    //xml.send();
    /*var filhos = document.getElementById("memberslist").children;
    for(var i = 0;i<filhos.length;i++){
        if(filhos[i].innerHTML.includes(name))
            {
                filhos[i].innerHTML=name+ " - " +role
            }
    }*/
    /*json = "{'role':'" + role + "'}";
    xmlStart();
    xml.onreadystatechange = function () {
		if (xml.readyState == 4 && xml.status == 200) {
			JsonObject = JSON.parse(xml.responseText);
			console.log(JsonObject);
            //var roles = JsonObject.data;
            //return callback(roles);
		}
		else if (xml.readyState == 4) {
			JsonObject = JSON.parse(xml.responseText);
			window.alert("changeRoles: " + JsonObject.message);
		}
	}
    xml.open("PUT", "http://127.0.0.1:5984/dashboard/people -d '" + json + "'", true);
    //xml.open("GET", host + "/projects/" + project_id + "/repository/files?file_path=roles.json&ref=new_integration", true);
    //xml.setRequestHeader('PRIVATE-TOKEN', private_token);
    xml.send();
    */
}

function showRolesMenu(){
    if(isPermitted(["Scrum Master"])){
        getRoles(function(roles){
            document.getElementById("rolesmenu").innerHTML = "";
            var menustring = "<div class='col-xs-4 role'><div class='dropdown col-xs-5 droproles'><button class='btn btn-primary dropdown-toggle' style='margin-left:30px;' type='button' data-toggle='dropdown'>Members<span class='caret'></span></button><ul class='dropdown-menu'></ul></div></div>"
            document.getElementById("rolesmenu").innerHTML = menustring;
            var menu = document.getElementById("rolesmenu").firstChild;
            for(var key in roles){
                var li = document.createElement("li");
                var a = document.createElement("a");
                a.href="javascript:showMemberRoles('" + roles[key].name + "')";
                a.innerHTML=roles[key].name;
                li.appendChild(a);
                menu.getElementsByClassName("dropdown-menu")[0].appendChild(li);
            }
            document.getElementById("weeklyscore").style.display = "none";
            document.getElementById("scorecardgraph").style.display = "none";
            document.getElementById("rolesmenu").style.display = "block";
            //document.getElementById("rolesmenu").appendChild(menu);
            /*document.getElementById("memberslist").innerHTML = "";
            for(var key in roles){
                if(roles[key].role != "Scrum Master"){
                var li = document.createElement("li");
                li.innerHTML = roles[key].name + " - " + roles[key].role;
                console.log(li.innerHTML);
                document.getElementById("memberslist").appendChild(li);
                //var div = document.createElement("div");
                var div = document.getElementsByClassName('roleboard')[0].cloneNode(true);
                div.getElementsByClassName("choice")[0].href = "javascript:changeRole('Product Owner','" + roles[key].name + "')";
                div.getElementsByClassName("choice")[1].href = "javascript:changeRole('Risk Manager','" + roles[key].name + "')";
                div.getElementsByClassName("choice")[2].href = "javascript:changeRole('Integration Manager','" + roles[key].name +  "')";
                div.getElementsByClassName("choice")[3].href = "javascript:changeRole('Developer','" + roles[key].name + "')";
                document.getElementById("rolelist").appendChild(div);
                }
            }
            document.getElementById("rolelist").removeChild($('.roleboard')[0]);
            document.getElementById("rolesmenu").style.display = "block";
            document.getElementById("weeklyscore").style.display = "none";  */
        });

    }
    else{
        window.alert("This role can't change other members roles");
    }
}

function showMemberRoles(name){
    getRoles(function(roles){
        var rolesmenu = document.getElementById("rolesmenu");
        if(rolesmenu.childNodes[1] != null){
            rolesmenu.removeChild(rolesmenu.childNodes[1]);
        }
        var menu = document.getElementsByClassName("role")[0];
        if(menu.childNodes[1] != null){
            menu.removeChild(menu.childNodes[1]);
        }
        var aux = document.createElement("div");
        var memberrolesstring = "<div class='formboxes'><form id='member-roles'><p id='member'>"+name+"</p><input type='checkbox' class='role-option' name='roles' value='Scrum Master'>Scrum Master<br><input type='checkbox' class='role-option' name='roles' value='Integration Manager'>Integration Manager<br><input type='checkbox' class='role-option' name='roles' value='Risk Manager'>Risk Manager<br><input type='checkbox' class='role-option' name='roles' value='Product Owner'>Product Owner</form></div>";
        aux.innerHTML = memberrolesstring;
        memberroles = aux.firstChild;
        for(var key in roles){
            if (roles[key].name == name) {
                var rolelist = memberroles.getElementsByTagName("input");
                for(var i=0; i<rolelist.length; i++){
                    if(roles[key].roles.includes(rolelist[i].value)){
                        rolelist[i].checked = true;
                    }
                }
            }
        }
        menu.appendChild(memberroles);
        var changebuttonstring = "<div class='col-xs-4 col-xs-push-9 change'><button onclick='changeRole()' form='member-roles' class='btn btn-primary' type='submit' data-toggle='dropdown'>Change</button></div>";
        aux.innerHTML = changebuttonstring;
        changebutton = aux.firstChild;
        rolesmenu.appendChild(changebutton);
    });
}
