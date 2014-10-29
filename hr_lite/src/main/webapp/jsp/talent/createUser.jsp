<%@ include file="../common/include.jsp" %>
<html>
<script language="javascript">

function discard(){
	  var doyou = confirm("Are you confirm Discard the changes? (OK = Yes   Cancel = No)");
	  if (doyou == true){
	   //window.top.hidePopWin();
	   parent.parent.GB_hide(); 
	   } 
	}

function savedata(){
	  document.createUserForm.action = "user.do?method=save";
   document.createUserForm.submit();
   parent.parent.GB_hide(); 
	}

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&cid="+document.createUserForm.countryId.value;
	alert(url);
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
   

</script>
<html:form action="/user.do?method=logon" >

<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td>Create User</td>
			<td></td>
		</tr>
	 
		<tr>
			<td>First Name*</td>
			<td><html:text property="firstName" size="20" maxlength="50"/></td>
		</tr>
		<tr>
			<td>Middle Name</td>
			<td><html:text property="middleName"/></td>
		</tr>
		<tr>
			<td>Last Name*</td>
			<td><html:text property="lastName"/></td>
		</tr>
		<tr>
			<td>Email*</td>
			<td><html:text property="emailId" size="20"/></td>
		</tr>
		<tr>
			<td>Home Phone</td>
			<td><html:text property="phoneHome" size="20"/></td>
		</tr>
		<tr>
			<td>Office Phone</td>
			<td><html:text property="phoneOffice" size="20"/></td>
		</tr>
		<tr>
			<td>Department</td>
			<td>
			<html:select  property="deptId">
			<bean:define name="createUserForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td>Role</td>
			<td>
			<html:select  property="roleId">
			<bean:define name="createUserForm" property="roleList" id="roleList" />

            <html:options collection="roleList" property="roleId"  labelProperty="roleName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td>Locale</td>
			<td>
			<html:select  property="langId">
			<bean:define name="createUserForm" property="languageList" id="languageList" />

            <html:options collection="languageList" property="langId"  labelProperty="langName"/>
			</html:select>
			</td>
		</tr>

		

		<tr>
			<td>Country</td>
			<td>
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<bean:define name="createUserForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						Loading States......</span>
			</td>
		</tr>

       <tr>
			<td id="state" >State</td>
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
	  <td>Assign Username and Password </td><td></td>
	  </tr>

	  <tr>
			<td>username*</td>
			<td><html:text property="userName" size="20" maxlength="50"/></td>
		</tr>

		<tr>
			<td>password*</td>
			<td><html:password property="password" size="20" maxlength="50"/></td>
		</tr>

		<tr>
			<td><input type="button" name="login" value="save" onClick="savedata()"><input type="button" name="login" value="cancel" onClick="discard()"></td>
			<td></td>
		</tr>


	</table>
</div>

</html:form>