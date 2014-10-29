<%@ include file="../common/include.jsp" %>

<link rel="stylesheet" type="text/css" href="jsp/tabcontent/tabcontent.css" />

<script type="text/javascript" src="jsp/tabcontent/tabcontent.js">

/***********************************************
* Tab Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>
 <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<style>
span1{color:#ff0000;}
</style>
  <%
  User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
 Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN,user1.getSuper_user_key());
  List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFields = screenMap.get(Common.MANDATORY_FIELDS_LIST);

String datepattern = Constant.getValue("defaultdateformat");


if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

}

String fromWhere = (String)request.getAttribute("frm");
System.out.println("fromWhere : "+fromWhere);
com.bean.Locale locale = user1.getLocale();
%>

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
</style>

<STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
</STYLE>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width:95%;
	border: 1px solid #999;
	padding: 10px;
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

</style>

 </HEAD>



<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">

function discard(){
	  var doyou = confirm("Are you sure you would like to discard these changes..... ( OK = yes Cancel = No)");
	  if (doyou == true){
		  self.parent.location.reload();
	 parent.parent.GB_hide();
	 
	   } 
	}


function closewindow(){
	
parent.parent.GB_hide();

	  
	}							
       
      

 function updatedata(){
	
     var alertstr = "";
    var fullName = document.applicantForm.fullName.value;
	var email = document.applicantForm.email.value;
	var jobt1 = document.applicantForm.jobt1.value;
	var sourceTypeId = document.applicantForm.sourceTypeId.value;
	var showalert=false;

	if(fullName == "" || fullName == null){
     	alertstr = alertstr + "Name is  mandatory.<BR>";
		showalert = true;
		}

	if(email == "" || email == null){
     	alertstr = alertstr + "Email Id is  mandatory.<BR>";
		showalert = true;
		}

	if(jobt1 == "" || jobt1 == null){
     	alertstr = alertstr + "Job requisition is  mandatory.<BR>";
		showalert = true;
		}

	if(sourceTypeId == "" || sourceTypeId == null){
     	alertstr = alertstr + "Resume source is  mandatory.<BR>";
		showalert = true;
		}

	 if (showalert){
     	alert(alertstr);
        return false;
          }

	 

	
	  document.applicantForm.action = "applicantoffer.do?method=updateapplicantData&applicantId=<%=aform.getApplicantId()%>";
	  document.applicantForm.submit();
	  // window.top.hidePopWin();
	
	

	}

function savedata2(){
	
     var alertstr = "";
    var fullName = document.applicantForm.fullName.value;
	var email = document.applicantForm.email.value;
	var jobt1 = document.applicantForm.jobt1.value;
	var dob =document.applicantForm.dateofbirth.value;
	var sourceTypeId = document.applicantForm.sourceTypeId.value;
	var showalert=false;

	if(fullName == "" || fullName == null){
     	alertstr = alertstr + "Name is  mandatory.<BR>";
		showalert = true;
		}

	if(email == "" || email == null){
     	alertstr = alertstr + "Email Id is  mandatory.<BR>";
		showalert = true;
		}
	if(dob == "" || dob == null){
     	alertstr = alertstr + "Date of Birth is mandatory.<BR>";
		showalert = true;
		}

	if(jobt1 == "" || jobt1 == null){
     	alertstr = alertstr + "Job requisition is  mandatory.<BR>";
		showalert = true;
		}

	if(sourceTypeId == "" || sourceTypeId == null){
     	alertstr = alertstr + "Resume source is  mandatory.<BR>";
		showalert = true;
		}

	 if (showalert){
     	alert(alertstr);
        return false;
          }

	 

	
	  document.applicantForm.action = "applicantoffer.do?method=saveapplicantData";
	  document.applicantForm.submit();
	  // window.top.hidePopWin();
	
	

	}


function retrieveURLresumesource(url) {
   //convert the url to a string
    document.getElementById("loadingsource").style.visibility = "visible";

	
	var urlnew="applicantoffer.do?method=loadResumeSources"+"&typeid="+document.applicantForm.sourceTypeId.value;

	//alert(urlnew);
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeResumeSource;

	    try {
    		req.open("GET", urlnew, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			alert(urlnew);
	    	req.onreadystatechange=processStateChangeResumeSource;
	        req.open("GET", urlnew, true);
		    req.send();
			
    	}
  	}
}

function addPreviousOrg(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("Please save applicant first");
			return false;
		}


		var prevOrgName = document.applicantForm.prevOrgName.value;
		var role = document.applicantForm.role.value;
		var startDate = document.applicantForm.startDate.value;
		var endDate = document.applicantForm.endDate.value;
        var reasonforleave = document.applicantForm.reasonforleave.value;
		var reportingToName = document.applicantForm.reportingToName.value;

		var showalert=false;

	if(prevOrgName == "" || prevOrgName == null){
     	alertstr = alertstr + "Organization name is required.<BR>";
		showalert = true;
		}



	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="applicantoffer.do?method=savepreviousOrgdetails&applicantid=<%=aform.getApplicantId()%>&prevOrgName="+prevOrgName+"&role="+role+"&startDate="+startDate+"&endDate="+endDate+"&reasonforleave="+reasonforleave+"&reportingto="+reportingToName;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveOrganizations()",200);

	}

function addCertifications(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("Please save applicant first");
			return false;
		}


		var certName = document.applicantForm.certName.value;
		var specialization = "";
		var certorgName = document.applicantForm.certorgName.value;
		var certpercentile = document.applicantForm.certpercentile.value;
		var certpassingYear = document.applicantForm.certpassingYear.value;


		var showalert=false;

	if(certName == "" || certName == null){
     	alertstr = alertstr + "Certification name is required.<BR>";
		showalert = true;
		}



	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="applicantoffer.do?method=saveeducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&educationName="+certName+"&specialization="+specialization+"&institute="+certorgName+"&percentile="+certpercentile+"&passingyear="+certpassingYear;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveCertifications()",200);

	}


function deletecertification(id){

	var url="applicantoffer.do?method=deleteEducation&eduid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveCertifications()",200);


}

function deleteeducation(id){

	var url="applicantoffer.do?method=deleteEducation&eduid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveEducations()",200);


}

function deleteskill(id){

	var url="applicantoffer.do?method=deleteSkill&skillid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveSkills()",200);


}


function deletePreviousOrg(id){

	var url="applicantoffer.do?method=deletePrevOg&prevorgid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveOrganizations()",200);


}



	function addEducation(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("Please save applicant first");
			return false;
		}


		var educationName = document.applicantForm.educationName.value;
		var specialization = document.applicantForm.specialization.value;
		var institute = document.applicantForm.instituteName.value;
		var percentile = document.applicantForm.percentile.value;
		var passingyear = document.applicantForm.passingYear.value;


		var showalert=false;

	if(educationName == "" || educationName == null){
     	alertstr = alertstr + "Education is required.<BR>";
		showalert = true;
		}

	if(institute == "" || institute == null){
     	alertstr = alertstr + "College/University is required.<BR>";
		showalert = true;
		}


	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="applicantoffer.do?method=saveeducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&educationName="+educationName+"&specialization="+specialization+"&institute="+institute+"&percentile="+percentile+"&passingyear="+passingyear;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveEducations()",200);

	}



function retrieveOrganizations() {
    document.getElementById("loading3").style.visibility = "visible";
			document.applicantForm.prevOrgName.value="";
		document.applicantForm.role.value="";
		document.applicantForm.startDate.value="";
		document.applicantForm.endDate.value="";
        document.applicantForm.reasonforleave.value="";
	    document.applicantForm.reportingToName.value="";
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



function retrieveCertifications() {
    document.getElementById("loading2").style.visibility = "visible";
			document.applicantForm.certName.value="";
		document.applicantForm.certorgName.value="";
		document.applicantForm.certpercentile.value="";
		document.applicantForm.certpassingYear.value="";
	var url="applicantoffer.do?method=geteducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processCert;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processCert;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}


	function retrieveEducations() {
    document.getElementById("loading1").style.visibility = "visible";
				document.applicantForm.educationName.value="";
		    document.applicantForm.specialization.value="";
		    document.applicantForm.instituteName.value="";
		    document.applicantForm.percentile.value="";
		    document.applicantForm.passingYear.value="";
	var url="applicantoffer.do?method=geteducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processEdu;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processEdu;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&cid="+document.applicantForm.countryId.value;
	
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


	 
function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
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

function processStateChangeResumeSource() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlResumeSource(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingsource").style.visibility = "hidden";	
  	}
}


function processEdu() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmledu(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading1").style.visibility = "hidden";	
  	}
}

function processCert() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlCert(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading2").style.visibility = "hidden";	
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


function replaceExistingWithNewHtmledu(newTextElements){
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
				  
		    	  if(name="educations")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function replaceExistingWithNewHtmlCert(newTextElements){
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
				  
		    	  if(name="certifications")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
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



function replaceExistingWithNewHtmlResumeSource(newTextElements){
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
				  
		    	  if(name="subsource")
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
   

function opensearchjobreq(){
 
window.open('lov.do?method=requisitionselector','SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');

}




</script>

<%
String applicantsaved = (String)request.getAttribute("applicantsaved");
	String datastring1 = (String)request.getAttribute("datastring1");
	String path = (String)request.getAttribute("filePath");
	
if(applicantsaved != null && applicantsaved.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green">Applicant data is saved successfully.</font></td>
			<!-- <td> <a href="#" onclick="closewindow()">close window</a></td> -->
		</tr>
		
	</table>
</div>

<%}%>
<%
String applicantupdated = (String)request.getAttribute("applicantupdated");

if(applicantupdated != null && applicantupdated.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green">Applicant data is updated successfully.</font></td>
			<!-- <td> <a href="#" onclick="closewindow()">close window</a></td> -->
		</tr>
		
	</table>
</div>

<%}%>
	<body class="yui-skin-sam">
<html:form action="/applicantoffer.do?method=saveapplicantData" enctype="multipart/form-data">

<ul id="countrytabs" class="shadetabs">
<li><a href="#" rel="country1" class="selected">Details</a></li>
<li><a href="#" rel="country2">Work experience/Skills</a></li>
<li><a href="#" rel="country3">Education</a></li>
</ul>
<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
<div id="country1" class="tabcontent">
<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

        
		<%
       		String tempurl1 = "";
      		if(aform.getJobTitle() != null){
   				//tempurl1 = "<a href='#' onClick=window.open("+"'"+"agjob.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+aform.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+aform.getJobTitle()+"</a>";
   				if(fromWhere != null && fromWhere.equals("ag")){
   					tempurl1 = "<a href='#' onClick=window.open("+"'"+"agjob.do?method=jobdetailsforAgency&reqid="+aform.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=800"+"'"+")>"+aform.getJobTitle()+"</a>";
   				}else {
   					tempurl1 = "<a href='#' onClick=window.open("+"'"+"refjob.do?method=jobdetailsforReferral&reqid="+aform.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=800"+"'"+")>"+aform.getJobTitle()+"</a>";
   				}
   				
	   		}
		%>
		

			<tr>

				<td>Job Title</td>

				<td>
				<input type="hidden"  name="reqid1" value="<%=aform.getRequitionId()%>"/>
				<input type="hidden" name="jobt1"  value="<%=aform.getJobTitle()%>"/>
				<%=tempurl1%>
				
				</td>

			</tr>

	 
		<tr>
			<td>Name<span1>*</span1></td>
			<td><html:text property="fullName" size="50" maxlength="600"/></td>
		</tr>
		<tr>
			<td>Gender<span1>*</span1></td>
			<td>Male<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("M"))? "Checked=true" : "" %> value="M">&nbsp;&nbsp;Female<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("F"))? "Checked=true" : "" %> value="F"></td>
		</tr>
		<tr>
		<!-- <%="satya"+aform.getHidesource()%> -->
   
   <%if(aform.getHidesource()!=null && aform.getHidesource().equals("refferal")){%>
   <html:hidden property="employeecode" /> 
<html:hidden property="referrerName" />
 <html:hidden property="referrerEmail" /> 
  <html:hidden property="sourceTypeId"/> 
  <html:hidden property="hidesource"/> 

    <%}else if(aform.getHidesource()!=null && aform.getHidesource().equals("vendor")){%>
      <html:hidden property="sourceTypeId"/> 
	   <html:hidden property="vendorId"/>
	  <html:hidden property="hidesource"/>
	<%}%>

<%@ include file="../talent/createApplicantCommonFields.jsp" %>

		<tr>
			<td>
			<% if(aform.getApplicantId()!=0){  %>
			<input type="button" name="login" value="save" onClick="updatedata()">
			<%}else{%>
			<input type="button" name="login" value="save" onClick="savedata2()">
			<%}%>
			<input type="button" name="login" value="cancel" onClick="discard()"></td>
			<td></td>
		</tr>


	</table>
</div>
</div>
<div id="country2" class="tabcontent">
<div align="center">

<fieldset><legend>Work experience</legend>
	<table border="0" width="100%">

    <tr>
	<td><b>Organization</b></td><td><b>Last role</b></td><td><b>Reporting Manager</b></td><td><b>Joining date</b></td><td><b>Relieving date</b></td><td><b>Reason for Leaving</b></td><td></td>
	</tr>
    <tr>
	<td>
	<html:text property="prevOrgName" size="20"/>
	</td>
	<td><html:text property="role" size="20"/></td>
	<td><html:text property="reportingToName" size="20"/></td>
	<td><html:text property="startDate" size="20"/></td>
	<td><html:text property="endDate" size="20"/></td>
	<td><html:text property="reasonforleave" size="30"/></td>
	<td><a href="#" onClick="addPreviousOrg()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add"/>
	<span class="textboxlabel" id="loading3" STYLE="font-size: smaller;Visibility:hidden";>
						Please wait......</span>
	</td>
	</tr>

    
	</table>

   <span id="prevorgdetails">
    <table border="0" width="100%">
<%
List orgList = aform.getPreviousOrgList();

%>
<% if(orgList != null && orgList.size()>0){%>
    <tr>
	
	<td><b>Organization</b></td><td><b>Last role</b></td><td><b>Reporting Manager</b></td><td><b>Joining date</b></td><td><b>Relieving date</b></td><td><b>Reason for Leaving</b></td><td></td>
	
	</tr>



<%	
for(int i=0;i<orgList.size();i++){
	PreviousOrgDetails edu = (PreviousOrgDetails)orgList.get(i);
%>
<tr>

<td><%=edu.getPrevOrgName()%></td><td><%=edu.getRole()%></td><td><%=edu.getReportingToName()%></td><td><%=edu.getStartdate()%></td><td><%=edu.getEnddate()%></td><td><%=edu.getReasonforleave()%></td><td><a href="#" onClick="deletePreviousOrg('<%=edu.getPrevOrgDetailsId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete organization" title="delete organization" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
Experience details not added.
<%}%>

    </table>
	</span>

	</fieldset>
	<br>
<fieldset><legend>Skills</legend>
<table border="0" width="100%">

    <tr>
	<td><b>Skill</b></td><td><b>Years of Experience</b></td><td><b>Rating</b></td><td></td>
	</tr>
    <tr>
	<td>
	<html:select  property="skillname">
			<bean:define name="applicantForm" property="skillList" id="skillList" />

            <html:options collection="skillList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	<td>
		<html:select  property="yearsofexpskill">
			<bean:define name="applicantForm" property="skillYearsList" id="skillYearsList" />

            <html:options collection="skillYearsList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	<td>
	<html:select  property="ratingskill">
			<bean:define name="applicantForm" property="skillRatingList" id="skillRatingList" />

            <html:options collection="skillRatingList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	<td><a href="#" onClick="addSkills()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add"/></a>
	<span class="textboxlabel" id="loading4" STYLE="font-size: smaller;Visibility:hidden";>
						Please wait......</span>
	</td>
	</tr>

    
	</table>

   <span id="skilldetails">
    <table border="0" width="100%">
<%
List skillList = aform.getApplicantSkillList();

%>
<% if(skillList != null && skillList.size()>0){%>
    <tr>
	
	<td><b>Skill</b></td><td><b>Years of Experience</b></td><td><b>Rating</b></td><td></td>
	
	</tr>



<%	
for(int i=0;i<skillList.size();i++){
	ApplicantSkills skil = (ApplicantSkills)skillList.get(i);
%>
<tr>

<td><%=skil.getSkillname()%></td><td><%=skil.getYearsofexp()%></td><td><%=skil.getRating()%></td>
<td><a href="#" onClick="deleteskill('<%=skil.getSkillId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete skill" title="delete skill" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
Skills not added.
<%}%>

    </table>
	</span>

	</fieldset>


	</div>
</div>

<div id="country3" class="tabcontent">
<div align="center">
     <fieldset><legend>Education details</legend>
	<table border="0" width="100%">

    <tr>
	<td><b>Education</b></td><td><b>Specialization</b></td><td><b>College/University</b></td><td><b>Percentage/Grade</b></td><td><b>Passing year</b></td><td></td>
	</tr>
    <tr>
	<td><html:select  property="educationName">
			<option value=""></option>
			<bean:define name="applicantForm" property="educationNamesList" id="educationNamesList" />

            <html:options collection="educationNamesList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	<td><html:text property="specialization" size="40"/></td>
	<td><html:text property="instituteName" size="40"/></td>
	<td><html:text property="percentile" size="5"/></td>
	<td><html:text property="passingYear" size="5"/></td>
	<td><a href="#" onClick="addEducation()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add"/></a>
	<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
						Please wait......</span>
	</td>
	</tr>

    
	</table>

    <span id="educations">
    <table border="0" width="100%">
<%
List eduList = aform.getEducationsList();

%>
<% if(eduList != null && eduList.size()>0){%>
    <tr>
	<td><b>Education</b></td><td><b>Specialization</b></td><td><b>College/University</b></td><td><b>Percentage/Grade</b></td><td><b>Passing year</b></td><td></td>
	</tr>




<%	
for(int i=0;i<eduList.size();i++){
	EducationDetails edu = (EducationDetails)eduList.get(i);
%>
<tr>
<td><%=edu.getEducationName()%></td><td><%=edu.getSpecialization()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td><td><%=edu.getPassingYear()%></td><td><a href="#" onClick="deleteeducation('<%=edu.getEducationId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete education" title="delete education" height="20"  width="19"/></a></td>
</tr>
<%
}
}else{%>
Education details not added.
<%}%>

    </table>
	</span>

	</fieldset>
<br>
<fieldset><legend>Certifications details</legend>
	<table border="0" width="100%">

    <tr>
	<td><b>Name</b></td><td><b>Certified Organization</b></td><td><b>Percentage/Grade</b></td><td><b>Passing year</b></td><td></td>
	</tr>
    <tr>
	<td>
	<html:text property="certName" size="40"/>
	</td>
	<td><html:text property="certorgName" size="40"/></td>
	<td><html:text property="certpercentile" size="5"/></td>
	<td><html:text property="certpassingYear" size="5"/></td>
	<td><a href="#" onClick="addCertifications()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add"/></a>
	<span class="textboxlabel" id="loading2" STYLE="font-size: smaller;Visibility:hidden";>
						Please wait......</span>
	</td>
	</tr>

    
	</table>

    <span id="certifications">
    <table border="0" width="100%">
<%
List certList = aform.getCertificationsList();

%>
<% if(certList != null && certList.size()>0){%>
    <tr>
	<td><b>Name</b></td><td><b>Certified Organization</b></td><td><b>Percentage/Grade</b></td><td><b>Passing year</b></td><td></td>
	</tr>




<%	
for(int i=0;i<certList.size();i++){
	EducationDetails edu = (EducationDetails)certList.get(i);
%>
<tr>
<td><%=edu.getEducationName()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td><td><%=edu.getPassingYear()%></td>
<td><a href="#" onClick="deletecertification('<%=edu.getEducationId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete certification" title="delete certification" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
Certifications not added.
<%}%>

    </table>
	</span>

	</fieldset>
	</div>
</div>
</html:form>


<script type="text/javascript">
	YAHOO.example.BasicLocal = function() {
		// Use a LocalDataSource
		var oDS = new YAHOO.util.LocalDataSource(<%=datastring1%>);
		// Optional to define fields for single-dimensional array
		oDS.responseSchema = {fields : ["state"]};

		// Instantiate the AutoComplete
		var oAC = new YAHOO.widget.AutoComplete("myInput", "myContainer", oDS);
		oAC.prehighlightClassName = "yui-ac-prehighlight";
		oAC.useShadow = true;
		
		return {
			oDS: oDS,
			oAC: oAC
		};
	}();
	</script>

	<script type="text/javascript">

var countries=new ddtabcontent("countrytabs")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()


</script>
</body>
