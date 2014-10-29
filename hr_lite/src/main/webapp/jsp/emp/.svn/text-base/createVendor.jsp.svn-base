<%@ include file="../common/include.jsp" %>

<%@ include file="../common/greybox.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
<link rel="stylesheet" href="jsp/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
   fieldset1 {
	width: 550px;
	border: 1px solid #999;
	
}
fieldset {
	width: 550px;
	border: 1px solid #999;
	padding: 10px;
}
	/* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>

<script type="text/javascript" src="jsp/dhtmlmodal/windowfiles/dhtmlwindow.js">

/***********************************************
* DHTML Window Widget- © Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for legal use.
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />

<script type="text/javascript">
function openmypage(url){ //Define arbitrary function to run desired DHTML Window widget codes
ajaxwin=dhtmlwindow.open("ajaxbox", "ajax", url, "Organization", "width=450px,height=400px,left=200px,top=100px,resize=1,scrolling=1")
//ajaxwin.onclose=function(){return window.confirm("Close window 3?")} //Run custom code when window is about to be closed
}
</script>
<script language="javascript">


function closewindow(){

	 	  parent.parent.GB_hide(); 
	  
	}

function closewindow2(){

	 	  parent.parent.GB_hide(); 

	  
	}

function deleteuser(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.createUserForm.action = "user.do?method=delete&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();

	   } 
	}

function suspend(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.suspendmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.createUserForm.action = "user.do?method=suspend&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();

	   } 
	}	

function activate(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.activatemsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.createUserForm.action = "user.do?method=activate&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();

	   } 
	}	


function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	    parent.parent.GB_hide();
	   } 
	}

function assignusernamepassword(){
  window.open("user.do?method=assignusernamescr&userId=<%=EncryptDecrypt.encrypt(String.valueOf(cform.getUserId()))%>", "assignusername","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=450,height=250");
}
function init(){
setTimeout ( "document.createUserForm.firstName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){

var alertstr = "";
var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	var firstName = document.createUserForm.firstName.value.trim();
	var lastName = document.createUserForm.lastName.value.trim();
	var email = document.createUserForm.emailId.value.trim();
	var ophone = document.createUserForm.phoneOffice.value.trim();
	var city = document.createUserForm.city.value.trim();
	var showalert=false;

   if(firstName == "" || firstName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Short_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(lastName == "" || lastName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Full_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
/* if(email == "" || email == null){
     	alertstr = alertstr + "Email Id is  mandatory.<BR>";
		showalert = true;
		}*/

	if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Invalid_Email",user1.getLocale())%><BR>";
		showalert = true;
		}

	 if(ophone == "" || ophone == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Ophone_number_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(city == "" || city == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.City_name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	
	if(firstName.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(lastName.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Primary_contact_full_name",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(email.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}

	if(ophone.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(city.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}


	 if (showalert){
     	alert(alertstr);
        return false;
          }

	

	  document.createUserForm.action = "user.do?method=savevendor";
   document.createUserForm.submit();
	}

function updatedata(){

	var alertstr = "";
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	var firstName = document.createUserForm.firstName.value.trim();
	var lastName = document.createUserForm.lastName.value.trim();
	var email = document.createUserForm.emailId.value.trim();
	var hphone = document.createUserForm.phoneHome.value.trim();
	var ophone = document.createUserForm.phoneOffice.value.trim();
	var city = document.createUserForm.city.value.trim();

	var showalert=false;

   if(firstName == "" || firstName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Short_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(lastName == "" || lastName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Full_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
/* if(email == "" || email == null){
     	alertstr = alertstr + "Email Id is  mandatory.<BR>";
		showalert = true;
		}*/

	if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Invalid_Email",user1.getLocale())%><BR>";
		showalert = true;
		}

	 if(ophone == "" || ophone == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Ophone_number_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(city == "" || city == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.City_name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	
	if(firstName.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(lastName.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Primary_contact_full_name",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(email.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}

	if(ophone.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(city.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}

	 if (showalert){
     	alert(alertstr);
        return false;
          }


	  document.createUserForm.action = "user.do?method=updatevendor&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();
	}



	function opensearchorg(){
  window.open("org.do?method=orgselector&orgId=1", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchdepartment(){
	var orgidvalue = document.createUserForm.parentOrgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectorg.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("lov.do?method=departmentlocator&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function opensearchprojectcode(){
	var orgidvalue = document.createUserForm.parentOrgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectdept.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("lov.do?method=projectcodelocator&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";


	url=url+"&readPreview=3"+"&cid="+document.createUserForm.countryId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;
	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}


function processStateChange() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtml(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="states")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}



</script>

<% if(cform.getStatus() != null && cform.getStatus().equals("D")){%>

<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.User_added",user1.getLocale())%><font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}else {%>

<%
String isuseradded = (String)request.getAttribute("isuseradded");
	
if(isuseradded != null && isuseradded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.Vendor_added_succ",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String vendorUpdated = (String)request.getAttribute("vendorUpdated");
	
if(vendorUpdated != null && vendorUpdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.Vendor_updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%

	
if(cform.getStatus() != null && cform.getStatus().equals("I")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.Vendor_suspend",user1.getLocale())%></font></td>
			<td> <!--<a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		</tr>
		
	</table>
</div>

<%}%>

<body onload="init()">
<html:form action="/user.do?method=logon" >

<div align="center">
<%

if (cform.getReadPreview() != null && cform.getReadPreview().equals("2")){


%>
	
	<br><fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.vendor.details",user1.getLocale())%></legend>

    

<table border="0" width="100%">


	
	 
	
	
	   <tr>
			<td>
			<%if(cform.getProfilePhotoId() == 0){%>
			<img src="jsp/images/user_blank.jpg" width="104" height="104"/>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+cform.getProfilePhotoId();
			%>
            <a href="<%=imgurl%>" rel="gb_imageset[nice_pics]" title="<%=Constant.getResourceStringValue("hr.user.profile_image",user1.getLocale())%>">
            <img src="<%=imgurl%>" width="104" height="104"/>
			<%}%>
			</td>
			<td></td>
		</tr>	
	 
		<tr>
			<td><%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%></td>
			<td><%=(cform.getFirstName()==null)?"":cform.getFirstName()%></td>
			
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Primary_contact_full_name",user1.getLocale())%></td>
			<td><%=(cform.getLastName()==null)?"":cform.getLastName()%></td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%></td>
			<td><%=(cform.getEmailId()==null)?"":cform.getEmailId()%></td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>
			<td><%=(cform.getPhoneHome()==null)?"":cform.getPhoneHome()%></td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>
			<td><%=(cform.getPhoneOffice()==null)?"":cform.getPhoneOffice()%></td>
			
		</tr>
		


		

		<tr>
			<td>
			
			<%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>
			<td><%=(cform.getCountryName()==null)?"":cform.getCountryName()%></td>
			
			
		</tr>

       <tr>
			<td><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
			<td><%=(cform.getStateName()==null)?"":cform.getStateName()%></td>
			
		</tr>
      <tr>
			<td><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>
			<td><%=(cform.getCity()==null)?"":cform.getCity()%></td>
		</tr>
      

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td><%=(cform.getLocaleName()==null)?"":cform.getLocaleName()%></td>
			
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td><%=(cform.getTimezoneName()==null)?"":cform.getTimezoneName()%></td>
			
			
		</tr>
     </table>
	
	 </fieldset>  

		
<%}else{%>

</div>
<div class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<tr>
			
			<td>
			<% if (cform.getUserId() > 0 ){
	              System.out.println("cform.getStatus()"+cform.getStatus());
					if(cform.getStatus() != null && cform.getStatus().equals("A")){
	
			%>

<br><a class="button" href="#"  onClick="assignusernamepassword()"><%=Constant.getResourceStringValue("hr.user.Assign_username_password",user1.getLocale())%></a><br><br>
 
			<%
					}	
				}

			%>
			
			</td>
		</tr>
	<table border="0" width="100%"> 
		<tr>
			<td><%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="firstName" size="40" maxlength="200"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Primary_contact_full_name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="lastName" size="40" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="emailId" size="40" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>
			<td><html:text property="phoneHome" size="40" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="phoneOffice" size="40" maxlength="200"/></td>
		</tr>
		
		

		

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
    			<bean:define name="createUserForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Location.LoadingState",user1.getLocale())%>......</span>
			</td>
		</tr>


       <tr>
			<td id="state" ><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
			<td>
			<span id="states">
			<html:select  property="stateId">
			<bean:define name="createUserForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>
      <tr>
			<td><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="city" size="40" maxlength="200"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td>
			<html:select  property="localeId">
			<bean:define name="createUserForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td>
			<html:select  property="timezoneId">
			<bean:define name="createUserForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
		</tr>

    
</table>
<br>

<table border="0" width="100%">
	  

		<tr>
	
			<td>
			<% if (cform.getUserId() > 0){%>
				<% if(cform.getStatus() != null && !cform.getStatus().equals("I")){%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
				<%}%>
				<% if(cform.getStatus() != null && !cform.getStatus().equals("D")){%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteuser()" class="button">
				<%}%>
				<% if(cform.getStatus() != null && cform.getStatus().equals("A")){%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.suspend",user1.getLocale())%>" onClick="suspend()" class="button">
				<%}%>
				<% if(cform.getStatus() != null && cform.getStatus().equals("I")){%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.activate",user1.getLocale())%>" onClick="activate()" class="button">
				<%}%>
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			
		</tr>
</table>
		<%}%>
	
</div>


</html:form>

<%}%>