<%@ include file="../common/include.jsp" %>
<%
String userdataexist = (String)request.getAttribute("userdataexist");
%>

<bean:define id="aform" name="refferalForm" type="com.form.RefferalForm" />
<style>
span1{color:#ff0000;}
</style>
<script language="javascript">

function savedata(){
	
	var alertstr = "";
	// var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var employeecode = document.refferalForm.employeecode.value.trim();
	var employeename = document.refferalForm.employeename.value.trim();
	var employeeemail = document.refferalForm.employeeemail.value.trim();
    var password = document.refferalForm.password.value.trim();
	var cpassword = document.refferalForm.cpassword.value.trim();
	var showalert=false;

	if(employeecode == "" || employeecode == null){
     	alertstr = alertstr + "Employee code is  mandatory.<BR>";
		showalert = true;
		}
		if(employeename == "" || employeename == null){
     	alertstr = alertstr + "Name is  mandatory.<BR>";
		showalert = true;
		}

			if(employeeemail == "" || employeeemail == null){
     	alertstr = alertstr + "Email id is  mandatory.<BR>";
		showalert = true;
		}

			if(password == "" || password == null){
     	alertstr = alertstr + "Password is  mandatory.<BR>";
		showalert = true;
		}

					if(cpassword == "" || cpassword == null){
     	alertstr = alertstr + "Confirm Password is  mandatory.<BR>";
		
		showalert = true;
		}

							if(password != cpassword){
     	alertstr = alertstr + "Password and Confirm Password mismatch.<BR>";
		document.refferalForm.cpassword.value="";
		document.refferalForm.password.value="";
		showalert = true;
		}

 if (showalert){
     	alert(alertstr);
        return false;
          }

	

	
	  document.refferalForm.action = "refferal.do?method=regsubmit&userdataexist=<%=userdataexist%>&emailid="+employeeemail;
	  document.refferalForm.submit();

}

function verificationcheck(){
	var alertstr = "";
	var showalert=false;
	  var varificationcode = document.refferalForm.varificationcode.value.trim();
      if(varificationcode == "" || varificationcode == null){
     	alertstr = alertstr + "Verification code is  required.<BR>";
		showalert = true;
		}
	   if (showalert){
     	alert(alertstr);
        return false;
          }

	 document.refferalForm.action = "refferal.do?method=verificationcheck&employeeReferalId=<%=aform.getEmployeeReferalId()%>";
	  document.refferalForm.submit();
}

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loadingstate").style.visibility = "visible";

	
	url=url+"&countryId="+document.refferalForm.countryId.value;

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
    	document.getElementById("loadingstate").style.visibility = "hidden";	
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
	setTimeout("retrieveProjcodes('lovnologin.do?method=loadProjectCode')",300);
	//retrieveProjcodes('lovnologin.do?method=loadProjectCode');
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

</script>

<%

String employeerefsaved=(String)request.getAttribute("employeerefsaved");
String emailidnotcorrect=(String)request.getAttribute("emailidnotcorrect");
String wrongvarificationcode=(String)request.getAttribute("wrongvarificationcode");
String successvarificationcode=(String)request.getAttribute("successvarificationcode");

%>

<%if(successvarificationcode != null && successvarificationcode.equals("yes")){%>

<font color="green">Your account has been created successfully.</font>
<%}else{%>

<html:form action="/refferal.do?method=verificationcheck" >
<br>
<div style="border:1px solid gray; width:700px; margin-bottom: 1em; padding: 10px" class="div">
<i><b>Employee registration for refferal program</b></i> <br><br>
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<%if(emailidnotcorrect != null && emailidnotcorrect.equals("yes")){%>

<font color="red">Email id wrong. Please try with correct email id.</font> <br>
<%}%>
<%if(employeerefsaved != null && employeerefsaved.equals("yes")){%>
<i>Verification code sent to your email id, please enter Verification code.</i> <br>
<%if(wrongvarificationcode != null && wrongvarificationcode.equals("yes")){%>
<font color="red">Wrong verification code.</font>
<%}%>
<tr>
<td>
Verification Code <span1>*</span1> : 
</td>
<td>
<html:text property="varificationcode" size="30"/>
</td>
</tr>
<tr>
<td>
<input type="button" name="login" value="submit" onClick="verificationcheck()" class="button">
</td>
<td>
<%}else{%>

<%if(userdataexist != null && userdataexist.equals("yes")){%>


<% if(aform.getUserName() != null){%>

<tr bgcolor="#f3f3f3">
<td>
User Name <span1>*</span1> : 
</td>
<td>
<html:text property="userName" size="30" disabled="true"/>
</td>
</tr>
<tr bgcolor="#f3f3f3">
<td>
Password <span1>*</span1>  : 
</td>
<td>
<html:password property="password" size="30" disabled="true"/>
</td>
</tr>
<tr bgcolor="#f3f3f3">
<td>
Confirm Password <span1>*</span1>  : 
</td>
<td>
<html:password property="cpassword" size="30" disabled="true"/>
</td>
</tr>

<%}%>

<tr>
<td>
Employee code <span1>*</span1> : 
</td>
<td>
<html:text property="employeecode" size="30" disabled="true"/>
</td>
</tr>
<tr>
<td>
Name <span1>*</span1> : 
</td>
<td>
<html:text property="employeename" size="30" disabled="true"/>
</td>
</tr>
<tr>
<td>
Email id <span1>*</span1>  : 
</td>
<td>
<html:text property="employeeemail" size="30" disabled="true"/>
<input type="hidden" name="emailid" value="<%=(aform.getEmployeeemail() == null ? "" :aform.getEmployeeemail())%>"/>
</td>
</tr>

<tr>
			<td width="40%">Organization</td>
			<td>
			<html:select property="orgId" disabled="true">
			<option value=""></option>
			<bean:define name="refferalForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						Loading Departments ......</span>

			  
			</td>
			</tr>
		<tr>
			<td width="40%">Department</td>
			<td>
			<span id="departments">
			<html:select property="departmentId"  disabled="true">
			<option value=""></option>
			<bean:define name="refferalForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
			<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
						Loading Project codes ......</span>
			</td>
			
		</tr>
    <tr>
			<td width="40%">ProjectCode</td>
			<td>
			<span id="projectcodes">
			<html:select property="projectcodeId" disabled="true">
			<option value=""></option>
			<bean:define name="refferalForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
			</td>
		</tr>
		
<tr>
			<td width="40%">Job grade</td>
			<td>
			
			<html:select property="jobgradeId" >
			<bean:define name="refferalForm" property="jobgradeList" id="jobgradeList" />

            <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			</html:select>
	       
			</td>
		</tr>	
	  
		 
     <tr>
			<td width="40%">Designation</td>
			<td>
			<html:select  property="designationId" disabled="true">
			<option value="0"></option>
			<bean:define name="refferalForm" property="designationList" id="designationList" />

            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td width="40%">Locale</td>
			<td>
			<html:select  property="localeId" disabled="true">
			<bean:define name="refferalForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td width="40%">Timezone</td>
			<td>
			<html:select  property="timezoneId" disabled="true">
			<bean:define name="refferalForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
		</tr>

		

		<tr>
			<td width="40%">Country</td>
			<td>
			<html:select  property="countryId" disabled="true">
			<bean:define name="refferalForm" property="countryList" id="countryList" />
            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingstate" STYLE="font-size: smaller;Visibility:hidden";>
						Loading States......</span>
			</td>
		</tr>

       <tr>
			<td id="state" width="40%">State</td>
			<td>
			<span id="states">
			<html:select  property="stateId" disabled="true">
			<bean:define name="refferalForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>
      <tr>
			<td width="40%">City</td>
			<td><html:text property="city" size="20" disabled="true"/></td>
		</tr>

				<tr>
			<td width="40%">Home Phone</td>
			<td><html:text property="phoneHome" size="20" maxlength="200" disabled="true"/></td>
		</tr>
		<tr>
			<td width="40%">Office Phone</td>
			<td><html:text property="phoneOffice" size="20" maxlength="200" disabled="true"/></td>
		</tr>
        <tr>
			<td width="40%">Mobile Phone</td>
			<td><html:text property="mobileNo" size="20" maxlength="200" disabled="true"/></td>
		</tr>



<%}else{%>

<tr>
<td>
Employee code <span1>*</span1> : 
</td>
<td>
<html:text property="employeecode" size="30" />
</td>
</tr>
<tr>
<td>
Name <span1>*</span1> : 
</td>
<td>
<html:text property="employeename" size="30"/>
</td>
</tr>
<tr>
<td>
Email id <span1>*</span1>  : 
</td>
<td>
<html:text property="employeeemail" size="30" disabled="true"/>

</td>
</tr>

<tr>
			<td width="40%">Organization</td>
			<td>
			<html:select property="orgId" onchange="retrieveURLOrg('lovnologin.do?method=loadDeptlist');">
			<bean:define name="refferalForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						Loading Departments ......</span>

			  
			</td>
			</tr>
		<tr>
			<td width="40%">Department</td>
			<td>
			<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('lovnologin.do?method=loadProjectCode');">
			<bean:define name="refferalForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
			<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
						Loading Project codes ......</span>
			</td>
			
		</tr>
    <tr>
			<td width="40%">ProjectCode</td>
			<td>
			<span id="projectcodes">
			<html:select property="projectcodeId" >
			<option value=""></option>
			<bean:define name="refferalForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
			</td>
		</tr>	

	 <tr>
			<td width="40%">Job grade</td>
			<td>
			
			<html:select property="jobgradeId" >
			
			<bean:define name="refferalForm" property="jobgradeList" id="jobgradeList" />

            <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			</html:select>
	       
			</td>
		</tr>	 
		 
     <tr>
			<td width="40%">Designation</td>
			<td>
			<html:select  property="designationId">
			<option value="0"></option>
			<bean:define name="refferalForm" property="designationList" id="designationList" />

            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td width="40%">Locale</td>
			<td>
			<html:select  property="localeId">
			<bean:define name="refferalForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td width="40%">Timezone</td>
			<td>
			<html:select  property="timezoneId">
			<bean:define name="refferalForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
		</tr>

		

		<tr>
			<td width="40%">Country</td>
			<td>
			<html:select  property="countryId" onchange="retrieveURL('refferal.do?method=loadState');">
			<bean:define name="refferalForm" property="countryList" id="countryList" />
            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingstate" STYLE="font-size: smaller;Visibility:hidden";>
						Loading States......</span>
			</td>
		</tr>

       <tr>
			<td id="state" width="40%">State</td>
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
			<td width="40%">City</td>
			<td><html:text property="city" size="20"/></td>
		</tr>

				<tr>
			<td width="40%">Home Phone</td>
			<td><html:text property="phoneHome" size="20" maxlength="200"/></td>
		</tr>
		<tr>
			<td width="40%">Office Phone</td>
			<td><html:text property="phoneOffice" size="20" maxlength="200"/></td>
		</tr>
        <tr>
			<td width="40%">Mobile Phone</td>
			<td><html:text property="mobileNo" size="20" maxlength="200"/></td>
		</tr>
<%}%>
		

<% if(aform.getUserName() == null){%>
<tr bgcolor="#f3f3f3">
<td>
Password <span1>*</span1>  : 
</td>
<td>
<html:password property="password" size="30"/>
</td>
</tr>
<tr bgcolor="#f3f3f3">
<td>
Confirm Password <span1>*</span1>  : 
</td>
<td>
<html:password property="cpassword" size="30"/>
</td>
</tr>
<%}%>
<tr>
<td>
<input type="button" name="login" value="submit" onClick="savedata()" class="button">
</td>
<td>

</td>
</tr>
<%}%>
</div>
</html:form>

<%}%>