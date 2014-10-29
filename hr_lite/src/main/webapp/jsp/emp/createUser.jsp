<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>






<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
	
	fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}



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
	<style>
span1{color:#ff0000;}
</style>

<style type="text/css">
.imageBox {
width:300;
height:100;
background-image:url("/pix/smile.gif");
}
</style>

<link rel="stylesheet" href="jsp/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />

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
	//self.parent.location.reload();
	 	      parent.parent.GB_hide(); 

	  
	}	
function closewindow1(){
	 	   self.close();

	  
	}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  //self.parent.location.reload();
	   //window.top.hidePopWin();
	   parent.parent.GB_hide(); 
	   } 
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
function init(){
setTimeout ( "document.createUserForm.employeecode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){
	var empcode=document.createUserForm.employeecode.value.trim();
	var fname=document.createUserForm.firstName.value.trim();
	var lname=document.createUserForm.lastName.value.trim();
	var email=document.createUserForm.emailId.value.trim();
	var middleName=document.createUserForm.middleName.value.trim();
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
	var alertstr="";
	var showalert=false;
	var alphabate=/^[a-zA-Z]+$/;
	var numbers=/^[0-9]+$/;

    if(empcode == "" || empcode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Empcode_req",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if(fname == "" || fname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Fname_req",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if(lname == "" || lname == null)
	{
 	alertstr = alertstr+"<%=Constant.getResourceStringValue("hr.Validation.lname_req",user1.getLocale())%><BR>";
 	showalert = true;
		}

	if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Invalid_Email",user1.getLocale())%><BR>";
		showalert = true;
		}
	 var orgidvalue = document.createUserForm.orgId.value;
	if(orgidvalue == 0){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.org_mandatory",user1.getLocale())%><BR>";
		showalert = true;
	}

	if(empcode.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(middleName.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.middle_Name",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(fname.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	if(lname.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}

	

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.createUserForm.action = "user.do?method=save";
   document.createUserForm.submit();
	}
function updatedata(){
	var alertstr="";
	var showalert=false;
	var empcode=document.createUserForm.employeecode.value.trim();
	var fname=document.createUserForm.firstName.value.trim();
	var lname=document.createUserForm.lastName.value.trim();
	var email=document.createUserForm.emailId.value.trim();
	var middleName=document.createUserForm.middleName.value.trim();


	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	var alphabate=/^[a-zA-Z]+$/;
	var numbers=/^[0-9]+$/;
	
	 
	/*
		if(alphabate.test(fname)==false){
     	alertstr = alertstr + "First Name is  mandatory.<BR>";
     	showalert = true;
		}
	if(alphabate.test(lname) ==false)
	{
 	alertstr = alertstr+"Last name Is mandatory.<BR>";
 	showalert = true;
		}
	 if(email == "" || email == null)
	{
 	alertstr = alertstr+"EmailID Is mandatory.<BR>";
 	showalert = true;
		}*/

	    if(empcode == "" || empcode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Empcode_req",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if(fname == "" || fname == null){
     	alertstr = alertstr + " <%=Constant.getResourceStringValue("hr.Validation.Fname_req",user1.getLocale())%><BR>";
     	showalert = true;
		}
	if(lname == "" || lname == null)
	{
 	alertstr = alertstr+"<%=Constant.getResourceStringValue("hr.Validation.lname_req",user1.getLocale())%><BR>";
 	showalert = true;
		}

	if(reg.test(email) ==false || email== "eg : jhonsmith@abc.com"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Invalid_Email",user1.getLocale())%><BR>";
		showalert = true;
		}
	var orgidvalue = document.createUserForm.orgId.value;
	if(orgidvalue == 0 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.org_mandatory",user1.getLocale())%><BR>";
		showalert = true;
	}

	if(empcode.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%>"+" is too long<br>";
		showalert = true;
	}
	if(middleName.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.middle_Name",user1.getLocale())%>"+" is too long<br>";
		showalert = true;
	}
	if(fname.length > 200){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%>"+" is too long<br>";
		showalert = true;
	}
	if(lname.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%>"+" is too long<br>";
		showalert = true;
	}

	

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.createUserForm.action = "user.do?method=update&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();
	}



	function opensearchorg(){
  window.open("org.do?method=orgselector&orgId=1", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}

function opensearchdepartment(){
	var orgidvalue = document.createUserForm.orgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectorg.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("dept.do?method=departmentlocator&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function assignusernamepassword(){
  window.open("user.do?method=assignusernamescr&userId=<%=EncryptDecrypt.encrypt(String.valueOf(cform.getUserId()))%>", "assignusername","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=450,height=250");
}


function opensearchprojectcode(){
	var orgidvalue = document.createUserForm.orgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectdept.alert",user1.getLocale())%>");
		return false;
	}
 
  window.open("lov.do?method=projectcodelocator&deptId=<%=cform.getDepartmentId()%>&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loadingState").style.visibility = "visible";

	
	url=url+"&cid="+document.createUserForm.countryId.value;
	
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
    	document.getElementById("loadingState").style.visibility = "hidden";	
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
				  
		    	  if(name="states")	document.getElementById(name).innerHTML = content;
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
  
   
   function retrieveURLOrg(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.createUserForm.orgId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeOrg;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}

function retrieveProjcodes(url) {
   //convert the url to a string
    document.getElementById("loading1").style.visibility = "visible";

	
	url=url+"&departmentId="+document.createUserForm.departmentId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeProj;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeProj;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}
	 


function processStateChangeOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrg(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlOrg(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function processStateChangeProj() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrg(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlProj(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading1").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlOrg(newTextElements){
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
				  
		    	  if(name="departments")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
	retrieveProjcodesbyOrganization();
}

function replaceExistingWithNewHtmlProj(newTextElements){
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
				  
		    	  if(name="projectcodes")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function splitTextIntoSpanOrg(textToSplit){
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







function retrieveProjcodesbyOrganization() {
   //convert the url to a string
   // document.getElementById("loadingp").style.visibility = "visible";
  var orgid=document.createUserForm.orgId.value;
   var departmentid=document.createUserForm.departmentId.value;
 
	url="user.do?method=loadprojectcodebyOrganization&orgid="+orgid+"&departmentid="+departmentid;
	//url=url+"&departmentId="+document.jobRequisitionForm.departmentId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeProjbyOrganization;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeProjbyOrganization;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}



function processStateChangeProjbyOrganization() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrgbyOrganization(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlProjbyOrganization(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingp").style.visibility = "hidden";	
  	}
}


function replaceExistingWithNewHtmlProjbyOrganization(newTextElements){
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
				  
		    	  if(name="projectcodes")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
	
}


function splitTextIntoSpanOrgbyOrganization(textToSplit){
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
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.User_deleted",user1.getLocale())%><font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
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
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.User_added",user1.getLocale())%></font></td>
			<td><!-- <a class="closelink" href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>


<%
String isuserupdated = (String)request.getAttribute("isuserupdated");
	
if(isuserupdated != null && isuserupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
		<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.User_updated",user1.getLocale())%></font></td>
			<td><!-- <a class="closelink" href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
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
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.User_suspend",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>
<body onload="init()">
<html:form action="/user.do?method=logon" >

<div align="center" class="div">
	
<%

if (cform.getReadPreview() != null && cform.getReadPreview().equals("2")){


%>

<br>
	 <fieldset><legend><%=Constant.getResourceStringValue("hr.user.User_Details",user1.getLocale())%></legend>
	 <div class="personPopupResult">
	 <table border="0" width="100%">
	<tr>
	
	   <tr>
			<td>
			<%
		    String changeimageurl = "user.do?method=uploadUserPhotoscr&userId="+cform.getUserId();
			if(cform.getProfilePhotoId() == 0){
			%>
			
		<a href="jsp/images/user_blank.jpg" rel="lightbox"><img src="jsp/images/user_blank.jpg" width="104" height="104" alt="" /></a>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+cform.getProfilePhotoId();
			%>

			<a href="<%=imgurl%>" rel="lightbox"><img src="<%=imgurl%>" width="104" height="104" alt="" /></a>

			<%}%>
			</td>
			<td>
	
    
  	
			</td>
		</tr>
	    <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Usertype",user1.getLocale())%></td>
			<td><bean:write name="cform" property="type"/></td>
		</tr>
	
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%></td>
			<td><%=(cform.getFirstName()==null)?"":cform.getFirstName()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.middle_Name",user1.getLocale())%></td>
			<td><%=(cform.getMiddleName()==null)?"":cform.getMiddleName()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%> </td>
			<td><%=(cform.getLastName()==null)?"":cform.getLastName()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%> </td>
			<td><%=(cform.getEmailId()==null)?"":cform.getEmailId()%></td>
		</tr>
				<tr>
			<td><%=Constant.getResourceStringValue("hr.user.MobileNo",user1.getLocale())%></td>
			  <td><%=(cform.getMobileNo()==null)?"":cform.getMobileNo()%></td> 
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>
			<td><%=(cform.getPhoneHome()==null)?"":cform.getPhoneHome()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>
			<td><%=(cform.getPhoneOffice()==null)?"":cform.getPhoneOffice()%></td>
		</tr>
		

			<%
		String orgurl =null;
		
        if(cform.getOrgId() != 0){

   orgurl = "<a href='#'  onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+cform.getOrgId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=330"+"'"+")>"+cform.getParentOrgName()+"</a>";

		}

		%>

				<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> : </td>
			<td>
			<%=(orgurl == null)?"":orgurl%>
			
			</td>
		</tr>

		
		
			<%
		String depturl =null;
		System.out.println("cform.getDepartmentId()"+cform.getDepartmentId());
        if(cform.getDepartmentId() != 0){

   depturl = "<a href='#'  onClick=window.open("+"'"+"dept.do?method=editDepartmentDetails&readPreview=2&id="+cform.getDepartmentId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230"+"'"+")>"+cform.getDepartment()+"</a>";

		}

		%>

				<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> : </td>
			<td>
			<%=(depturl == null)?"":depturl%>
			
			</td>
		</tr>

		
		
			<%
		String projectturl =null;
		
        if(cform.getProjectcodeId() != 0){

   projectturl = "<a href='#'  onClick=window.open("+"'"+"projectcodes.do?method=editProjectCodes&readPreview=2&projectCodeId="+cform.getProjectcodeId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=290"+"'"+")>"+cform.getProjectcode()+"</a>";

		}

		%>

				<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%> : </td>
			<td>
			<%=(projectturl == null)?"":projectturl%>
			
			</td>
		</tr>
        <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%></td>
			<td>
			<%=(cform.getDesignationName()==null)?"":cform.getDesignationName()%>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Role",user1.getLocale())%></td>
			<td>
			<%=(cform.getRoleName()==null)?"":cform.getRoleName()%>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td>
			<%=(cform.getLocaleName()==null)?"":cform.getLocaleName()%>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td>
			<%=(cform.getTimezoneName()==null)?"":cform.getTimezoneName()%>
			</td>
		</tr>

		

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Nationality",user1.getLocale())%></td>
			<td>
			<%=(cform.getNationalityName()==null)?"":cform.getNationalityName()%>
			</td>
		</tr>


      <tr>
			<td><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>
			<td><%=(cform.getCity()==null)?"":cform.getCity()%></td>
		</tr>

   </table>
   </div>
		</fieldset>   

		
<%}else{%>


<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<tr>
			
			<td>
			
			<% if (cform.getUserId() > 0 ){
if(cform.getStatus() != null && cform.getStatus().equals("A")){
	
%>

<a class="closelink" href="#"  onClick="assignusernamepassword()"><%=Constant.getResourceStringValue("hr.user.Assign_username_password",user1.getLocale())%></a>
 
	<%
		}
			}

	%>
	
			
			</td>
	</tr>
</table>
<br>
<table border="0" width="100%">
		
	    <tr>
			<td width="20%"><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%><font color="red">*</font></td>
			<td width="30%"><html:text property="employeecode" size="20" maxlength="200"/></td>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%><font color="red">*</font></td>
			<td width="30%"><html:text property="firstName" size="20" maxlength="200"/></td>
		</tr>
		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.middle_Name",user1.getLocale())%></td>
			<td width="30%"><html:text property="middleName" maxlength="200"/></td>
		
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%><font color="red">*</font></td>
			<td width="30%"><html:text property="lastName" maxlength="500"/></td>
		</tr>
		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%><font color="red">*</font></td>
			<td width="30%"><html:text property="emailId" size="20" maxlength="500"  /></td>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>
			<td width="30%"><html:text property="phoneOffice" size="20" maxlength="20"/></td>
		</tr>

		
		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%><font color="red">*</font></td>
			<td width="30%">
			<html:select property="orgId" onchange="retrieveURLOrg('user.do?method=loadDeptlistWithProjectcode');">
			
			<bean:define name="createUserForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%> ......</span>

			  
			</td>
			<td width="20%"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td width="30%">
			<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('user.do?method=loadProjectCode');">
			<option value=""></option>
			<bean:define name="createUserForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
			<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.ProjectCode.Loadpcode",user1.getLocale())%> ......</span>
			</td>
			
		</tr>
    <tr>
			<td width="20%"><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%></td>
			<td width="30%">
			<span id="projectcodes">
			<html:select property="projectId" onchange="">
			<option value=""></option>
			<bean:define name="createUserForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
			</td>

			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Nationality",user1.getLocale())%></td>
			<td width="30%">
			<html:select  property="nationalityId">
			<bean:define name="createUserForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>
			</td>

		</tr>
		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%></td>
			<td width="30%">
			<html:select  property="designationId">
			<option value="0"></option>
			<bean:define name="createUserForm" property="designationList" id="designationList" />

            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>
			</td>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Role",user1.getLocale())%></td>
			<td width="30%">
			<html:select  property="roleId">
			<bean:define name="createUserForm" property="roleList" id="roleList" />

            <html:options collection="roleList" property="roleId"  labelProperty="roleName"/>
			</html:select>
			</td>

		</tr>
	



		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.SSNNumber",user1.getLocale())%></td>
				<td width="30%">
			<html:text property="ssnNumber" size="20" maxlength="200"/>
			</td>
			

			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td width="30%">
			<html:select  property="localeId">
			<bean:define name="createUserForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>
		</table>
				
	<table border="0" width="100%">
		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td >
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
<html:hidden property="applicantId"/>
			<% if (cform.getUserId() > 0 ){%>
<a class="button" href="#" onClick="updatedata()"><%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%></a>			
<% if(cform.getStatus() != null && !cform.getStatus().equals("D")){%>
<a class="button" href="#" onClick="deleteuser()"><%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%></a>			
<%}%>
<% if(cform.getStatus() != null && cform.getStatus().equals("A")){%>
<a class="button" href="#" onClick="suspend()"><%=Constant.getResourceStringValue("hr.button.suspend",user1.getLocale())%></a>			
<%}%>
<% if(cform.getStatus() != null && cform.getStatus().equals("I")){%>
<a class="button" href="#" onClick="activate()"><%=Constant.getResourceStringValue("hr.button.activate",user1.getLocale())%></a>			
<%}%>
			<%}else{%>
			<a class="button" href="#" onClick="savedata()">
    <%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%></a>
			<%}%>
			<a class="button" href="#" onClick="discard()">
    <%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%></a>
			</td>
			<td></td>
		</tr>

		<%}%>
	</table>
</div>

</html:form>
</body>


<%}%>

   <script type="text/javascript">
GB_myShow = function(caption, url, /* optional */ height, width, callback_fn) {
    var options = {
        caption: caption,
        height: height || 500,
        width: width || 500,
        fullscreen: false,
        show_loading: false,
        callback_fn: callback_fn
    }
    var win = new GB_Window(options);
    return win.show(url);
}
</script>