

<SCRIPT LANGUAGE="JavaScript">
function retrieveURLPrevOrg(url) {

   //convert the url to a string
    document.getElementById("loadingprev").style.visibility = "visible";


   // url="editapplicant.do?method=prevOrgloadState";
   url="user.do?method=loadState";
	url=url+"&cid="+document.applicantForm.country_id_prevorg.value;
	//alert(url);
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangePrev;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangePrev;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}

function processStateChangePrev() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlPrevCountry(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingprev").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlPrevCountry(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="statesPrevOrg")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


function addPreviousOrgNew(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",locale)%>");
			return false;
		}


		var prevOrgName = "";
		var role ="";
		var startDate="";
		var endDate="";
		var reasonforleave="";
		var reportingToName="";
		var city_Previousorg="";
		var  country_id_prevorg="";
		var state_id_prevorg ="";
		var lastSalary ="";
		 var bonus ="";
		 var currency_id_prevorg="";
		 var responsibilities ="";
		var employercontactName = "";
		var employercontactPhone = "";
		if(typeof document.applicantForm.prevOrgName != 'undefined'){
			prevOrgName = document.applicantForm.prevOrgName.value;
		}
		if(typeof document.applicantForm.role != 'undefined'){
			role = document.applicantForm.role.value;
		}
		if(typeof document.applicantForm.startDate != 'undefined'){
			startDate = document.applicantForm.startDate.value;
		}
		if(typeof document.applicantForm.endDate != 'undefined'){
			endDate = document.applicantForm.endDate.value;
		}
		if(typeof document.applicantForm.reasonforleave != 'undefined'){
			 reasonforleave = document.applicantForm.reasonforleave.value;
		}
		if(typeof document.applicantForm.reportingToName != 'undefined'){
			 reportingToName = document.applicantForm.reportingToName.value;
		}
		if(typeof document.applicantForm.city_Previousorg != 'undefined'){
			 city_Previousorg = document.applicantForm.city_Previousorg.value;
		}
		if(typeof document.applicantForm.country_id_prevorg != 'undefined'){
			 country_id_prevorg = document.applicantForm.country_id_prevorg.value;
		}
		if(typeof document.applicantForm.state_id_prevorg != 'undefined'){
			state_id_prevorg = document.applicantForm.state_id_prevorg.value;
		}
		if(typeof document.applicantForm.lastSalary != 'undefined'){
			 lastSalary = document.applicantForm.lastSalary.value;
		}
		if(typeof document.applicantForm.bonus != 'undefined'){
			 bonus = document.applicantForm.bonus.value;
		}
		if(typeof document.applicantForm.currency_id_prevorg != 'undefined'){
			 currency_id_prevorg = document.applicantForm.currency_id_prevorg.value;
		}
		if(typeof document.applicantForm.responsibilities != 'undefined'){
			 responsibilities = document.applicantForm.responsibilities.value;
		}
		if(typeof document.applicantForm.employercontactName != 'undefined'){
			 employercontactName = document.applicantForm.employercontactName.value;
		}
		if(typeof document.applicantForm.employercontactPhone != 'undefined'){
			employercontactPhone = document.applicantForm.employercontactPhone.value;
		}
		
	
		
		var showalert=false;

	


	 if (showalert){
     	alert(alertstr);
        return false;
          }

document.getElementById("loading3").style.visibility = "visible";

var parameters="method=savepreviousOrgdetails&applicantid=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>&prevOrgName="+prevOrgName+"&role="+role+"&startDate="+startDate+"&endDate="+endDate+"&reasonforleave="+reasonforleave+"&reportingto="+reportingToName+"&city_Previousorg="+city_Previousorg;
var param1 = "&country_id_prevorg="+country_id_prevorg+"&state_id_prevorg="+state_id_prevorg+"&lastSalary="+lastSalary+"&bonus="+bonus+"&currency_id_prevorg="+currency_id_prevorg+"&responsibilities="+responsibilities+"&employercontactName="+employercontactName+"&employercontactPhone="+employercontactPhone;

var securityparam = "&csrfcode="+document.applicantForm["csrfcode"].value;

var allparameters = parameters+param1+securityparam;

//url = encodeURIComponent(url);


//	resetWorkExperience();
      //Do the AJAX call
	  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangePrevOrg;

	    try {
    		req.open("POST", "applicantoffer.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(allparameters);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangePrevOrg;
    		req.open("POST", "applicantoffer.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(allparameters);
			
    	}
  
	}



}

function resetWorkExperience(){
	if(typeof document.applicantForm.prevOrgName != 'undefined'){
			 document.applicantForm.prevOrgName.value="";
		}
		if(typeof document.applicantForm.role != 'undefined'){
			document.applicantForm.role.value="";
		}
		if(typeof document.applicantForm.startDate != 'undefined'){
			document.applicantForm.startDate.value="";
		}
		if(typeof document.applicantForm.endDate != 'undefined'){
			 document.applicantForm.endDate.value="";
		}
		if(typeof document.applicantForm.reasonforleave != 'undefined'){
			 document.applicantForm.reasonforleave.value="";
		}
		if(typeof document.applicantForm.reportingToName != 'undefined'){
			  document.applicantForm.reportingToName.value="";
		}
		if(typeof document.applicantForm.city_Previousorg != 'undefined'){
			  document.applicantForm.city_Previousorg.value="";
		}
		if(typeof document.applicantForm.country_id_prevorg != 'undefined'){
			  document.applicantForm.country_id_prevorg.value="";
		}
		if(typeof document.applicantForm.state_id_prevorg != 'undefined'){
			 document.applicantForm.state_id_prevorg.value="";
		}
		if(typeof document.applicantForm.lastSalary != 'undefined'){
			 document.applicantForm.lastSalary.value="";
		}
		if(typeof document.applicantForm.bonus != 'undefined'){
			  document.applicantForm.bonus.value="";
		}
		if(typeof document.applicantForm.currency_id_prevorg != 'undefined'){
			  document.applicantForm.currency_id_prevorg.value="";
		}
		if(typeof document.applicantForm.responsibilities != 'undefined'){
			  document.applicantForm.responsibilities.value="";
		}
		if(typeof document.applicantForm.employercontactName != 'undefined'){
			  document.applicantForm.employercontactName.value="";
		}
		if(typeof document.applicantForm.employercontactPhone != 'undefined'){
			 document.applicantForm.employercontactPhone.value="";
		}
		
  
}
function processStateChangePrevOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlPrevOrg(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading3").style.visibility = "hidden";	
  	}
}


function replaceExistingWithNewHtmlPrevOrg(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="prevorgdetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function addattachmentscrprevexp(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}

function deleteAttachmentNew(url){
	//alert(url);
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",locale)%>");
	  if (doyou == true){
		
		window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300");
	
	 
	   } 
}

function deletePreviousOrgNew(id){

    var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",locale)%>");
	  if (doyou == true){

	var url="applicantoffer.do?method=deletePrevOg&prevorgid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processOrg;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveOrganizations()",200);

	  }
}


function retrieveOrganizations() {
    document.getElementById("loading3").style.visibility = "visible";
			
	var url="applicantoffer.do?method=getpreviousOrgdetails&applicantid=<%=aform.getApplicantId()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processOrg;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function processOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlOrg(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading3").style.visibility = "hidden";	
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
				  
		    	  if(name="prevorgdetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}
</script>





<% if(WORK_EXPERIENCE_List.size()>0){%>


<fieldset><legend>Work experience</legend>

<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[addworkexp]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> add work experience  </div>
<div id="addworkexp" style="width  100%;">

	<table border="0" width="100%">
<%

 for(int i=0;i<WORK_EXPERIENCE_List.size();i++){
	ScreenFields scf = (ScreenFields)WORK_EXPERIENCE_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER.toString())){%>
    <tr>
	<td>Employer :
	 <% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER.toString())){%>
			<span1>*</span1>
	<%}%>
	</td>
	<td><html:text property="prevOrgName" size="60"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_JOB_TITLE.toString())){%>
	<tr>
	<td>Job Title  :
	<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_JOB_TITLE.toString())){%>
			<span1>*</span1>
	<%}%>
	</td>
	<td><html:text property="role" size="60"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_CITY.toString())){%>
	<tr>
	<td>City  :
	<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_CITY.toString())){%>
			<span1>*</span1>
	<%}%>
	</td>
	<td><html:text property="city_Previousorg" size="60"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_COUNTRY.toString())){%>

		<tr>
			<td>Country :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_COUNTRY.toString())){%>
			<span1>*</span1>
			<%}%>
			</td>
			<td>
			<html:select  property="country_id_prevorg" onchange="retrieveURLPrevOrg('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="applicantForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingprev" STYLE="font-size: smaller;Visibility:hidden";>
			loading......</span>
			</td>
	    </tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_STATE.toString())){%>
		<tr>
			<td>State :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_STATE.toString())){%>
			<span1>*</span1>
			<%}%>
			</td>
			<td>
			<span id="statesPrevOrg">
			<html:select  property="state_id_prevorg">
			<option value=""></option>
			<bean:define name="applicantForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>
			</span>
			</td>
		</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_START_DATE.toString())){%>

	
 	   <tr>
			<td>Start date :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_START_DATE.toString())){%>
			<span1>*</span1>
			<%}%>
				
			</td>
</td>
			<td>
			
			<SCRIPT LANGUAGE="JavaScript" ID="jscal4xx">
var cal4xx = new CalendarPopup("testdiv4");
cal4xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="startDate" readonly="true" value="" SIZE=25>
<A HREF="#" onClick="cal4xx.select(document.applicantForm.startDate,'anchor4xx','MMM d, yyyy'); return false;" TITLE="select date" NAME="anchor4xx" ID="anchor4xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv4" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			
			</td>
		</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_END_DATE.toString())){%>
<tr>
			<td>End date :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_END_DATE.toString())){%>
			<span1>*</span1>
			<%}%>
				
			</td>
			<td>
			
			<SCRIPT LANGUAGE="JavaScript" ID="jscal3xx">
var cal3xx = new CalendarPopup("testdiv3");
cal3xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="endDate" readonly="true" value="" SIZE=25>
<A HREF="#" onClick="cal3xx.select(document.applicantForm.endDate,'anchor3xx','MMM d, yyyy'); return false;" TITLE="select date" NAME="anchor3xx" ID="anchor3xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv3" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			
			</td>
		</tr>

<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.LAST_SALARY_DRAWN.toString())){%>


	<tr>
	<td>Last Salary Drawn :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.LAST_SALARY_DRAWN.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td><html:text property="lastSalary" size="20"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.BONUS.toString())){%>
	<tr>
	<td>Bonus :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.BONUS.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td><html:text property="bonus" size="20"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_CURRENCY.toString())){%>
	<tr>
	<td>Currency Type  :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_CURRENCY.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td>
	<html:select  property="currency_id_prevorg">
			<bean:define name="applicantForm" property="currencyList" id="currencyList" />
            <html:options collection="currencyList" property="currencyId"  labelProperty="currencyName"/>
			</html:select>
	</td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.RESPONSIBILITIES.toString())){%>
	<tr>
	<td>Responsibilities :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.RESPONSIBILITIES.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td><html:textarea property="responsibilities" cols="57" rows="5"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_CONTACT_NAME.toString())){%>
    <tr>
	<td>Employer Contact Name :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_CONTACT_NAME.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td><html:text property="employercontactName" size="60"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMPLOYER_CONTACT_PHONE.toString())){%>
	<tr>
	<td>Employer Contact Telephone :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.EMPLOYER_CONTACT_PHONE.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td><html:text property="employercontactPhone" size="60"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.REPORTING_MANAGER.toString())){%>
	<tr>
	<td>Reporting Manager :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.REPORTING_MANAGER.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td><html:text property="reportingToName" size="60"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.REASON_FOR_LEAVING.toString())){%>
	<tr>
	<td>Reason For Leaving :
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.REASON_FOR_LEAVING.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td><html:text property="reasonforleave" size="60"/></td>
	</tr>
<%}%>
<%}// end of for loop%>

    <tr>

	<td><br>
	<input type="button" name="add work experience" value="add work experience" onClick="addPreviousOrgNew();return false" class="button"/>
	<input type="button" name="reset" value="reset" onClick="resetWorkExperience();return false" class="button"/>
	<!--<a href="#" onClick="addPreviousOrgNew()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add"/>-->
	<span class="textboxlabel" id="loading3" STYLE="font-size: smaller;Visibility:hidden";>
						Please wait......</span>
	</td>
	<td>
	
	</td>
	</tr>

    
	</table>

	</div>
<br>
<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[workexpdetails]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> work experience details </div>
<div id="workexpdetails" style="width  100%;" class="div"> 

   <%@ include file="../talent/expDetailsInner.jsp" %>

</div>

	</fieldset>
<%}// if WORK_EXPERIENCE_List size greater then 0%>