<%@ include file="../common/include.jsp" %>

<html>
<bean:define id="refform" name="refferalForm" type="com.form.RefferalForm"/>
<%
 String path = (String)request.getAttribute("filePath");
 String datepattern = Constant.getValue("defaultdateformat");
 RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

<style>
span1{color:#ff0000;}
</style>

<style type="text/css">


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


	<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	 parent.parent.GB_hide();

	//  window.top.hidePopWin();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
	 //window.top.hidePopWin();
	 //self.close();
}

function updatedata(){

var alertstr = "";
var showalert=false;

 var code = document.refferalForm.employeecode.value.trim();
  var name = document.refferalForm.employeename.value.trim();
   var email = document.refferalForm.employeeemail.value.trim();

		

		if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("refferal.common.Employee_code_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

      if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("refferal.common.Employee_Name_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

		if(email == "" || email == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("refferal.common.Email_Id_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

		if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.refferalForm.action = "refferal.do?method=updateRefferalEmployee&refferalid="+'<bean:write name="refferalForm" property="employeeReferalId"/>';
 document.refferalForm.submit();
}
function retrieveURLOrg(url) {
	   //convert the url to a string
	    document.getElementById("loading").style.visibility = "visible";

		
		url=url+"&orgId="+document.refferalForm.orgId.value;
		
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
}

function retrieveProjcodes(url) {
	   //convert the url to a string
	    document.getElementById("loading1").style.visibility = "visible";

		
		url=url+"&departmentId="+document.refferalForm.departmentId.value;
		
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

function retrieveURL(url) {
	   //convert the url to a string
	    document.getElementById("loadingState").style.visibility = "visible";

		
		url=url+"&cid="+document.refferalForm.countryId.value;
		
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

function deleteuser(){
	 
	  var doyou = confirm("<%=Constant.getResourceStringValue("refferal.common.delete",user1.getLocale())%>");
	  if (doyou == true){

	 	document.refferalForm.action = "refferal.do?method=deleteRefferalEmployee&employeeReferalId=<%=refform.getEmployeeReferalId()%>";
		document.refferalForm.submit();

	   } 
	}
function suspend(){
	
	  var doyou = confirm("<%=Constant.getResourceStringValue("refferal.common.suspend",user1.getLocale())%>");
		  if (doyou == true){

		  document.refferalForm.action = "refferal.do?method=suspendRefferalEmployee&employeeReferalId=<%=refform.getEmployeeReferalId()%>";
		  document.refferalForm.submit();

	   } 
	}	
function activate(){
	
	  var doyou = confirm("<%=Constant.getResourceStringValue("refferal.common.activate",user1.getLocale())%>");
	  if (doyou == true){


 		document.refferalForm.action = "refferal.do?method=activateRefferalEmployee&employeeReferalId=<%=refform.getEmployeeReferalId()%>";
 		document.refferalForm.submit();

	   } 
	}
</script>

<body class="yui-skin-sam">
	<%
	String updatesuccessful=(String)request.getAttribute("updatesuccessful");
	System.out.println("updatesuccessful : "+updatesuccessful);
	%>
	<% if(updatesuccessful != null && updatesuccessful.equals("yes")){%>
	<div class="div">
	<font color="green" align="left">User updated Successfully</font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <a class="closelink" href="#" onClick="closewindow()">close window</a>
	 </div>
<%}%>
<%
	String  isUserdeleted=(String)request.getAttribute("isUserdeleted");
System.out.println("isUserdeleted : "+isUserdeleted);
	if(isUserdeleted != null && isUserdeleted.equals("yes")){

%>
	<div class="div">

	<font color="green" align="left">User deleted Successfully</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <a class="closelink" href="#" onClick="closewindow()">close window</a>
	 </div>
<%} %>

<%
	String  isUsersuspended=(String)request.getAttribute("isUsersuspended");

	if(isUsersuspended != null && isUsersuspended.equals("yes")){

%>
	<div class="div">

	<font color="green" align="left">User suspended Successfully</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <a class="closelink" href="#" onClick="closewindow()">close window</a>
	 </div>
<%} %>
<%
String editpage = (String)request.getAttribute("editpage");
	System.out.println(" refform.getStatus() : "+refform.getStatus());
if(refform.getStatus() != null && refform.getStatus().equals("I") && editpage != null && editpage.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color=red><%=Constant.getResourceStringValue("hr.user.User_suspend",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
	String  isuseractivated=(String)request.getAttribute("isuseractivated");

	if(isuseractivated != null && isuseractivated.equals("yes")){

%>
<div class="div">
	<font color="green" align="left">User activated Successfully</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <a class="closelink" href="#" onClick="closewindow()">close window</a>
	 </div>
<%} %>

<html:form action="/refferal.do?method=updaterefferal">

<div align="center" class="div">

<table border="0" width="100%">
	<font color = red ><html:errors /></font>


	<%if(isUserdeleted != null && isUserdeleted.equals("yes")){ %>	
	<%}else{ %>

	<tr>
			<td></td>
			<td></td>
		</tr>	
		

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%><span1>*</span1> :</td>
			<td><html:text   property="employeecode" maxlength="200"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%> <span1>*</span1> : </td>
			<td><html:text   property="employeename" maxlength="200"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%> <span1>*</span1> : </td>
			<td><html:text   property="employeeemail" maxlength="200" readonly="true"/></td>
		</tr>
				
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.MobileNo",user1.getLocale())%> :</td>
			<td><html:text property="mobileNo" size="20" maxlength="500"/></td>
		</tr>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%> :</td>
			<td><html:text property="phoneHome" size="20" maxlength="200"/></td>
		</tr>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%> :</td>
			<td><html:text property="phoneOffice" size="20" maxlength="200"/></td>
		</tr>

		<tr>
		<%
		String orgdyvalue ="<span id=\"parentOrgId\">";
        if(refform.getOrgId() != 0){

  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+refform.getOrgId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+refform.getOrgName()+"</a>";
 orgdyvalue = "<span id=\"parentOrgId\">"+tempurl1+"</span>";
		}

		%>
		<tr>
				<td><%=Constant.getResourceStringValue("rule.org",user1.getLocale())%> :</td>
				<td><%=orgdyvalue%></span>
				
				</td>
			</tr>

		</tr>
				<tr>
<%
		String deptvalue ="<span id=\"departmentId\">";
        if(refform.getDepartmentId() != 0){

   String deptpurl = "<a href='#' onClick=window.open("+"'"+"dept.do?method=editDepartmentDetails&readPreview=2&id="+refform.getDepartmentId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+refform.getDepartmentName()+"</a>";
 deptvalue = "<span id=\"departmentId\">"+deptpurl+"</span>";
		}

		%>

		<tr>
				<td><%=Constant.getResourceStringValue("rule.department",user1.getLocale())%> :</td>
				<td><%=deptvalue%></span>
				
				</td>
		</tr>
			
		<%
		String projectcodevalue ="<span id=\"projectcodeId\"></span>";
        if(refform.getProjectcodeId() != 0){

   String projectcodeurl = "<a href='#' onClick=window.open("+"'"+"lov.do?method=projectcodepreview&projectcodeId="+refform.getProjectcodeId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+refform.getProjectcode()+"</a>";
 projectcodevalue = "<span id=\"projectcodeId\">"+projectcodeurl+"</span>";
		}

		%>

		<tr>
				<td><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.projectcode",user1.getLocale())%> :</td>
				<td><%=projectcodevalue%></span>
				
				
				</td>
		</tr>
		     <tr>
        <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%> :</td>
			<td>
			<%=(refform.getDesignationName()==null)?"":refform.getDesignationName()%>
			</td>
		</tr>
		
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%> :</td>
			<td>
			<html:select  property="localeId">
			<bean:define name="refferalForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%> :</td>
			<td>
			<html:select  property="timezoneId">
			<bean:define name="refferalForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%> :</td>
			<td>
			<html:select  property="countryId" onchange="retrieveURL('reflogin.do?method=loadState');">
			<bean:define name="refferalForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
		<span class="textboxlabel" id="loadingState" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("aquisition.applicant.Loading_States",user1.getLocale())%> ....</span>
			</td>
		</tr>
		       <tr>
			<td id="state" width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.State",user1.getLocale())%> :</td>
			<td>
			<span id="states">
			<html:select  property="stateId">
			<bean:define name="refferalForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>
		      <tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.City",user1.getLocale())%> :</td>
			<td><html:text property="city" size="20"/></td>
		</tr>
<tr><td></td></tr>
<tr>
<td>
<input type="button" name="update" value="Update" onClick="updatedata()" class="button">

<% if(refform.getStatus() != null && refform.getStatus().equals("I")){%>
<input type="button" name="login" value="Activate" onClick="activate()" class="button">
<%}else {%>
<% if(user1.getEmployeeReferalId()!=refform.getEmployeeReferalId()){%>
<input type="button" name="login" value="Delete" onClick="deleteuser()" class="button">
<input type="button" name="login" value="Suspend" onClick="suspend()" class="button">
<%}%>

<%} %>
<input type="button" name="cancel" value="Cancel" onClick="discard()" class="button">
</td>
<td></td>
</tr>
<%} %>
		</table>
	 </div>

		</html:form>

	</body>


		
