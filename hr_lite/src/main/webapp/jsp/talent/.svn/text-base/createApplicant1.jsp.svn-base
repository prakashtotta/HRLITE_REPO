<%@ include file="../common/include.jsp" %>

  <%
  Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN);
  List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFields = screenMap.get(Common.MANDATORY_FIELDS_LIST);
String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

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
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 // self.parent.location.reload();
	 parent.parent.GB_hide();
	 
	   } 
	}


function closewindow(){

//self.parent.location.reload();	

parent.parent.GB_hide();

	  
	}		
	

function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}

function deleteAttachment(url){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		
		window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300");
	
	 
	   } 
}
       
  function init(){
setTimeout ( "document.applicantForm.fullName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}


 function updatedata(){
	
    var alertstr = "";
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 var numbers=/^[0-9]+$/;
	 var floats =/\.[0-9]{1,2}$/;
    var fullName = document.applicantForm.fullName.value.trim();
	var email = document.applicantForm.email.value.trim();
	var jobt1 = document.applicantForm.requitionId.value.trim();
	var dob = document.applicantForm.dateofbirth.value.trim();
	var sourceTypeId = document.applicantForm.sourceTypeId.value;
	var noofyrsexp=document.applicantForm.noofyearsexp.value;
	var ExpCTC=document.applicantForm.expectedctc.value.trim();
	var CurrCtc=document.applicantForm.currectctc.value.trim();
	var showalert=false;

	if(fullName == "" || fullName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(dob == "" || dob == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Dobis_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	/*if(email == "" || email == null){
     	alertstr = alertstr + "Email Id is  mandatory.<BR>";
		showalert = true;
		}*/

		if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
		}



	if(jobt1 == "" || jobt1 == null || jobt1== "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobTitle_mandatory",user1.getLocale())%><BR>";

		showalert = true;
		}

	if(sourceTypeId == "" || sourceTypeId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Resumesource_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	/*if(RefEmpCode == "" || RefEmpCode == null){
     	alertstr = alertstr + "Reffral employee code is  mandatory.<BR>";
		showalert = true;
		}
		if(RefEmpName == "" || RefEmpName == null){
     	alertstr = alertstr + "Refferal employee name is  mandatory.<BR>";
		showalert = true;
		}
		if(RefEmpemail == "" || RefEmpemail == null){
     	alertstr = alertstr + "Refferal employee email is  mandatory.<BR>";
		showalert = true;
		}*/
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
	  document.applicantForm.action = "applicant.do?method=updateapplicantData&applicantId="+<%=aform.getApplicantId()%>;
	  document.applicantForm.submit();
	  // window.top.hidePopWin();
	
	

	}

function savedata2(){
	
     var alertstr = "";
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 var numbers=/^[0-9]+$/;
	 var floats =/\.[0-9]{1,2}$/;
    var fullName = document.applicantForm.fullName.value.trim();
	var email = document.applicantForm.email.value.trim();
	var jobt1 = document.applicantForm.requitionId.value.trim();
	var dob = document.applicantForm.dateofbirth.value.trim();
	var sourceTypeId = document.applicantForm.sourceTypeId.value;
	var noofyrsexp=document.applicantForm.noofyearsexp.value;
	var ExpCTC=document.applicantForm.expectedctc.value.trim();
	var CurrCtc=document.applicantForm.currectctc.value.trim();
	var showalert=false;

	if(fullName == "" || fullName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(dob == "" || dob == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Dobis_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	/*if(email == "" || email == null){
     	alertstr = alertstr + "Email Id is  mandatory.<BR>";
		showalert = true;
		}*/

		if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
		}



	if(jobt1 == "" || jobt1 == null || jobt1== "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobTitle_mandatory",user1.getLocale())%><BR>";

		showalert = true;
		}

	if(sourceTypeId == "" || sourceTypeId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Resumesource_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
		//if(numbers.test(noofyrsexp)==false){
     	//alertstr = alertstr + "Enter only numbers in years of experience.<BR>";
     	//showalert = true;
		//}
		

	 if (showalert){
     	alert(alertstr);
        return false;
          }

	 var url ="applicant.do?method=saveapplicantData";

	
	  document.applicantForm.action = "applicant.do?method=saveapplicantData";
	  document.applicantForm.submit();
	  // window.top.hidePopWin();
	
	

	}


function retrieveURLresumesource() {
   //convert the url to a string
    document.getElementById("loadingsource").style.visibility = "visible";

	
	var urlnew="applicant.do?method=loadResumeSources"+"&typeid="+document.applicantForm.sourceTypeId.value;

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
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",user1.getLocale())%>");
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
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.orgnamereqmsg",user1.getLocale())%><BR>";
		showalert = true;
		}



	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="applicant.do?method=savepreviousOrgdetails&applicantid=<%=aform.getApplicantId()%>&prevOrgName="+prevOrgName+"&role="+role+"&startDate="+startDate+"&endDate="+endDate+"&reasonforleave="+reasonforleave+"&reportingto="+reportingToName;


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
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",user1.getLocale())%>");
			return false;
		}


		var certName = document.applicantForm.certName.value;
		var specialization = "";
		var certorgName = document.applicantForm.certorgName.value;
		var certpercentile = document.applicantForm.certpercentile.value;
		var certpassingYear = document.applicantForm.certpassingYear.value;


		var showalert=false;

	if(certName == "" || certName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.certificationnamereq",user1.getLocale())%><BR>";
		showalert = true;
		}



	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="applicant.do?method=saveeducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&educationName="+certName+"&specialization="+specialization+"&institute="+certorgName+"&percentile="+certpercentile+"&passingyear="+certpassingYear;


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

	var url="applicant.do?method=deleteEducation&eduid="+id;


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

	var url="applicant.do?method=deleteEducation&eduid="+id;


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

	var url="applicant.do?method=deleteSkill&skillid="+id;


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

	var url="applicant.do?method=deletePrevOg&prevorgid="+id;


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
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",user1.getLocale())%>");
			return false;
		}


		var educationName = document.applicantForm.educationName.value;
		var specialization = document.applicantForm.specialization.value;
		var institute = document.applicantForm.instituteName.value;
		var percentile = document.applicantForm.percentile.value;
		var passingyear = document.applicantForm.passingYear.value;
		var startingYear = document.applicantForm.startingYear.value;


		var showalert=false;

	if(educationName == "" || educationName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Education_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(institute == "" || institute == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.College_University_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}


	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="applicant.do?method=saveeducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&educationName="+educationName+"&specialization="+specialization+"&institute="+institute+"&percentile="+percentile+"&passingyear="+passingyear+"&startingYear="+startingYear;


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


function addSkills(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",user1.getLocale())%>");
			return false;
		}


		var skillname = document.applicantForm.skillname.value;
		var yearsofexpskill = document.applicantForm.yearsofexpskill.value;
		var ratingskill = document.applicantForm.ratingskill.value;
	
		

var url="applicant.do?method=saveapplicantSkills&applicantid=<%=aform.getApplicantId()%>&skillname="+skillname+"&yearsofexpskill="+yearsofexpskill+"&ratingskill="+ratingskill;


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

function retrieveOrganizations() {
    document.getElementById("loading3").style.visibility = "visible";
		document.applicantForm.prevOrgName.value="";
		document.applicantForm.role.value="";
		document.applicantForm.startDate.value="";
		document.applicantForm.endDate.value="";
        document.applicantForm.reasonforleave.value="";
	    document.applicantForm.reportingToName.value="";
	var url="applicant.do?method=getpreviousOrgdetails&applicantid=<%=aform.getApplicantId()%>";

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
function retrieveSkills() {
    document.getElementById("loading4").style.visibility = "visible";
		document.applicantForm.skillname.value="";
		document.applicantForm.yearsofexpskill.value="";
		document.applicantForm.ratingskill.value="";
	var url="applicant.do?method=getApplicantSkills&applicantid=<%=aform.getApplicantId()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processSkills;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processSkills;
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
	var url="applicant.do?method=geteducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";

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
	var url="applicant.do?method=geteducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";

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


function retrieveCurrencyCodes(url) {
   //convert the url to a string
    
	url=url+"&requisitionId="+document.applicantForm.requitionId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeCurrency;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeCurrency;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	setTimeout("getCurrencyCodeExpected()",300);
}

function getCurrencyCodeExpected() {
   //convert the url to a string
    

	
	url="jobreq.do?method=getcurrencycodeexpected"+"&requisitionId="+document.applicantForm.requitionId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeCurrencyExpected;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeCurrencyExpected;
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

function processStateChangeCurrency() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlCurrencyCodeCurrent(spanElements);
			//replaceExistingWithNewHtmlCurrencyCodeExpected(spanElements);
		    //onOtherStateSel();
    	} 
    	
  	}
}

function processStateChangeCurrencyExpected() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    //replaceExistingWithNewHtmlCurrencyCodeCurrent(spanElements);
			replaceExistingWithNewHtmlCurrencyCodeExpected(spanElements);
		    //onOtherStateSel();
    	} 
    	
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

function processSkills() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlSkills(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading4").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtml(newTextElements){
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
				  
		    	  if(name="states")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function replaceExistingWithNewHtmlCurrencyCodeCurrent(newTextElements){
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
				  
		    	  if(name="currectctccurrencycodesp")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function replaceExistingWithNewHtmlCurrencyCodeExpected(newTextElements){
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
				  
		    	  if(name="expectedctccurrencycodesp")
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

function replaceExistingWithNewHtmlSkills(newTextElements){
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
				  
		    	  if(name="skilldetails")
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
	var tu = "lov.do?method=requisitionselector&frompage=createapppage";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  'SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');

//window.open('lov.do?method=requisitionselector&dummyvar=1','SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');

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
			<td><font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.AppDataSaveMsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
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
			<td><font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.AppDataUpdateMsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
 
<%
	List errorList = (List)request.getAttribute(Common.ERROR_LIST);
	if(errorList != null && errorList.size()>0){%>
<div align="center">
	<table border="0" width="100%">
	
<% for(int i=0;i<errorList.size();i++){
	String errorval = (String)errorList.get(i);
%>	
	        <tr>
			<td><font color="red"><li><%=errorval%></font></td>
			<td></td>
			</tr>
		
<%}%>
		
	</table>
</div>
<%}%>


	<body class="yui-skin-sam" onload="init()">    
<html:form action="/applicant.do?method=saveapplicantData" enctype="multipart/form-data">

<ul id="countrytabs" class="shadetabs">
<li><a href="#" rel="country1" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<li><a href="#" rel="country2"><%=Constant.getResourceStringValue("aquisition.applicant.WorkExpSkills",user1.getLocale())%></a></li>
<li><a href="#" rel="country3"><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%> </a></li>
</ul>
<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
<div id="country1" class="tabcontent">
<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

        
<% if(aform.getApplicantId() > 0 ){%>
			<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
			<td><%=aform.getApplicantId()%></td>
			<td></td>
			<td></td>
		</tr>
	<%}%> 
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="fullName" size="50" maxlength="600"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.gender",user1.getLocale())%><span1>*</span1></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.gender.male",user1.getLocale())%><input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("M"))? "Checked=true" : "" %> value="M">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.gender.female",user1.getLocale())%><input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("F"))? "Checked=true" : "" %> value="F"></td>
		</tr>
		<tr>


<% if(screenFields.contains(FieldCodes.ApplicantScreen.SOURCE_SUBSOURCE.toString())){%>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Source",user1.getLocale())%><span1>*</span1></td>
			<td>
			
			<html:select  property="sourceTypeId" onchange="retrieveURLresumesource();">
			<option value=""></option>
			<bean:define name="applicantForm" property="sourceTypeList" id="sourceTypeList" />

            <html:options collection="sourceTypeList" property="resumeSourceTypeId"  labelProperty="resumeSourceTypeName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingsource" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_sub_source",user1.getLocale())%>......</span>
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Sub_Source",user1.getLocale())%><span1>*</span1></td>
			<td>
            <span id="subsource">
			
			<%if(aform.getSourceTypeId()==Common.RESUME_SOURCE_AGENCY_ID){%>
	<html:select  property="vendorId">
	<option value=""></option>
			<bean:define name="applicantForm" property="vendorsList" id="vendorsList" />

            <html:options collection="vendorsList" property="userId"  labelProperty="lastName"/>
			</html:select>
<%} else if(aform.getSourceTypeId()==Common.RESUME_SOURCE_FERERRAL_ID){%>
    <%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%> : <html:text property="employeecode" size="30"/> <br>
	<%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%> : <html:text property="referrerName" size="30"/> <br>
	<%=Constant.getResourceStringValue("aquisition.applicant.Referrer_Email",user1.getLocale())%> : <html:text property="referrerEmail" size="30"/> 
<%} else {%>


	<html:select  property="sourceId">
	<option value=""></option>
			<bean:define name="applicantForm" property="sourceList" id="sourceList" />

            <html:options collection="sourceList" property="resumeSourcesId"  labelProperty="resumeSourcesName"/>
			</html:select>
<%}%>
			</span>
			</td>
		</tr>
		<tr>
			
		</tr>

<%}//source fields visibility check%>


		
 	   <tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",user1.getLocale())%><span1>*</span1></td>
			<td>
			
			<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="dateofbirth" readonly="true" value="<%=aform.getDateofbirth()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.dateofbirth,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.dateofbirth,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="email" size="50" maxlength="500"/></td>


		</tr>
		<tr>
			 <td><%=Constant.getResourceStringValue("aquisition.applicant.PassportNo",user1.getLocale())%></td>
			<td><html:text property="passportno" size="50"/></td>
								<td><%=Constant.getResourceStringValue("aquisition.applicant.Alternate_Email",user1.getLocale())%></td>
			<td><html:text property="alternateemail" size="50" maxlength="500"/></td>	
		</tr>
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%></td>
			<td><html:text property="phone" size="30" maxlength="50"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Alternate_phone",user1.getLocale())%></td>
			<td><html:text property="alternatephone" size="30" maxlength="50"/></td>
		</tr>
		<%
		System.out.println("aform.getRequitionId()"+aform.getRequitionId());
		String jobdyvalue ="<span id=\"requitionId\">";
        if(aform.getRequitionId() != 0){

  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreq&jobreqId="+aform.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+aform.getJobTitle()+"</a>";
 jobdyvalue = "<span id=\"requitionId\">"+tempurl1+"</span>";
		}
%>
		<tr>
				<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%><span1>*</span1></td>

				<td>
				<html:select  property="requitionId" onchange="retrieveCurrencyCodes('jobreq.do?method=getcurrencycode');">
			<option value=""></option>
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />
            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
				<a href="#" onClick="opensearchjobreq()"><img src="jsp/images/selector.gif" border="0"/></a>
				
				
				</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",user1.getLocale())%></td>
			<td><html:text property="primarySkill" size="30"/></td>
		</tr>
		

				
			</td>
		</tr>
		

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Country",user1.getLocale())%></td>
			<td>
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="applicantForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_States",user1.getLocale())%>......</span>
			</td>
			<td id="state" ><%=Constant.getResourceStringValue("aquisition.applicant.State",user1.getLocale())%></td>
			<td>
			<span id="states">
			<html:select  property="stateId">
			<option value=""></option>
			<bean:define name="applicantForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>

       <tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.City",user1.getLocale())%></td>
			<td><html:text property="city" size="50" maxlength="200"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Prefered_location",user1.getLocale())%></td>
			<td><html:text property="preferedlocation" size="50"/></td>
		</tr>

        <tr>

		    <td><%=Constant.getResourceStringValue("aquisition.applicant.Current_designation",user1.getLocale())%></td>
			<td><html:text property="currentdesignation" size="50"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_organization",user1.getLocale())%></td>
			<td>
			
			<label for="myInput"></label>
	<div id="myAutoComplete">
		<input id="myInput" name="previousOrganization" value="<%=aform.getPreviousOrganization()%>" type="text">
		
		<div id="myContainer"></div>
	</div>

			</td>
		</tr>
		
		<tr>
		    <td><%=Constant.getResourceStringValue("aquisition.applicant.Profile_Header",user1.getLocale())%></td>
			<td><html:text property="resumeHeader" size="50"/></td>
			
			
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%></td>
			<td><html:text property="noofyearsexp" size="10" maxlength="2"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Heighest_Qualification",user1.getLocale())%></td>
			<td><html:text property="heighestQualification" size="50" maxlength="200"/></td>
			<td><%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%></td>
			<td><html:text property="qualifications" size="50" maxlength="200"/></td>
		</tr>
        
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_CTC",user1.getLocale())%></td>
			<td><html:text property="currectctc" size="20" maxlength="200"/>		
			<span id="currectctccurrencycodesp">
			<%=(aform.getCurrectctccurrencycode() == null)?"":aform.getCurrectctccurrencycode()%>
			</span>
			
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Expected_CTC",user1.getLocale())%></td>
			<td><html:text property="expectedctc" size="20" maxlength="200"/>
			<span id="expectedctccurrencycodesp"><%=(aform.getExpectedctccurrencycode() == null)?"":aform.getExpectedctccurrencycode()%></span>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Notice_period",user1.getLocale())%> :</td>
			<td><html:text property="noticeperiod" size="20"/><%=Constant.getResourceStringValue("aquisition.applicant.days",user1.getLocale())%> </td>
			<td></td>
			<td></td>
		</tr>

		<tr>

		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Resume_upload",user1.getLocale())%></td>
			<td><html:file property="resumeData"/> 
			<% if(aform.getResumename()!=null){  %>
			<a href="<%=request.getContextPath()%>/download/file?filename=<%=path%>" ><bean:write name="applicantForm" property="resumename"/></a>
			<%}%> 
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.resumeCopy",user1.getLocale())%></td> 
			<td><html:textarea property="resumedetails" cols="60" rows="5"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Note",user1.getLocale())%> :</td> 
			<td><html:textarea property="note" cols="60" rows="5"/></td>
		</tr>

		<tr>
			<td>
			<% if(aform.getApplicantId()!=0){  %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata2()">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"></td>
			<td></td>
		</tr>


	</table>
</div>
</div>
<div id="country2" class="tabcontent">
<div align="center">

<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Work_experience",user1.getLocale())%></legend>
	<table border="0" width="100%">

    <tr>

	<td><b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",user1.getLocale())%></b></td><td></td>
	
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
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>

   <span id="prevorgdetails">
    
<%
List orgList = aform.getPreviousOrgList();

%>
<% if(orgList != null && orgList.size()>0){%>


<table  border="0" width="100%">

<%	
for(int i=0;i<orgList.size();i++){
	PreviousOrgDetails edu = (PreviousOrgDetails)orgList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- org details -->
<table valign="top" border="0" width="60%">
    <tr>
	
	<td width="40%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPrevOrgName()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getRole()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getReportingToName()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getStartdate()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getEnddate()%></td>
   </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getReasonforleave()%></td>

	</tr>

 </table>
 <!-- org details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getPrevOrgDetailsId()+"&action=experience_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid();
%>
<tr>

<td width="125px"><a href="#" onClick="addattachmentscr('<%=attachurl%>')">add attachment</a></td>
<td></td>
<td></td>
</tr>


<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("experience_details_proof_attachment")){
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname=experience_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid()+"&uuid="+actionattach.getUuid();

%>
<tr>
<td width="125px"></td>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="10"  width="9"/></a>
</td>
</tr>
<%
		}
	}%>

<%}%>

 </table>


 <!-- attachements -->
 <td>
<!-- delete -->
<table valign="top" border="0" width="10%">
<tr>
<td><a href="#" onClick="deletePreviousOrg('<%=edu.getPrevOrgDetailsId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete organization" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="20"  width="19"/></a></td>
 </td>
</tr>
 </table>
<!-- delete end -->
 </td>
 </tr>
<%
}
 %>

 </table>
<%
}else{

%>
<%=Constant.getResourceStringValue("aquisition.applicant.ExpDetailsNotAddMsg",user1.getLocale())%>
<%}%>

   
	</span>

	</fieldset>
	<br>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Skills",user1.getLocale())%></legend>
<table border="0" width="100%">

    <tr>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Rating",user1.getLocale())%></b></td><td></td>
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
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
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
	
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Rating",user1.getLocale())%></b></td><td></td>
	
	</tr>



<%	
for(int i=0;i<skillList.size();i++){
	ApplicantSkills skil = (ApplicantSkills)skillList.get(i);
%>
<tr>

<td><%=skil.getSkillname()%></td><td><%=skil.getYearsofexp()%></td><td><%=skil.getRating()%></td>
<td><a href="#" onClick="deleteskill('<%=skil.getSkillId()%>')"><img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("aquisition.applicant.delete_skill",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_skill",user1.getLocale())%>" height="20"  width="19"/></a></td>
</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Skills_not_added",user1.getLocale())%>.
<%}%>

    </table>
	</span>

	</fieldset>


	</div>
</div>

<div id="country3" class="tabcontent">
<div align="center">

     <fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Education_details",user1.getLocale())%></legend>
	<table border="0" width="100%">

    <tr>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Starting_year",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></b></td><td></td>
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
	<td><html:text property="startingYear" size="10"/></td>
	<td><html:text property="passingYear" size="10"/></td>
	<td><a href="#" onClick="addEducation()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add"/></a>
	<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>

     <span id="educations">
    
<%
List eduList = aform.getEducationsList();

%>
<% if(eduList != null && eduList.size()>0){%>


<table  border="0" width="100%">

<%	
for(int i=0;i<eduList.size();i++){
	EducationDetails edu = (EducationDetails)eduList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- org details -->
<table valign="top" border="0" width="500px">
    <tr>
	
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getEducationName()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getSpecialization()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getInstituteName()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPercentile()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getStartingYear()%></td>
   </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPassingYear()%></td>
   </tr>
	

 </table>
 <!-- org details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getEducationId()+"&action=education_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid();
%>
<tr>

<td width="100px"><a href="#" onClick="addattachmentscr('<%=attachurl%>')">add attachment</a></td>
<td></td>
<td></td>
</tr>


<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("education_details_proof_attachment")){
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname=education_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid()+"&uuid="+actionattach.getUuid();

%>
<tr>
<td width="100px"></td>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="10"  width="9"/></a>
</td>
</tr>
<%
		}
	}%>

<%}%>

 </table>


 <!-- attachements -->
 <td>
<!-- delete -->
<table valign="top" border="0" width="10%">
<tr>
<td><a href="#" onClick="deleteeducation('<%=edu.getEducationId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete education" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="20"  width="19"/></a></td>
 </td>
</tr>
 </table>
<!-- delete end -->
 </td>
 </tr>
<%
}
 %>

 </table>
<%
}else{

%>
<%=Constant.getResourceStringValue("aquisition.applicant.Educations_not_added",user1.getLocale())%>
<%}%>

   
	</span>

	</fieldset>
<br>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Certifications_details",user1.getLocale())%></legend>
	<table border="0" width="100%">

    <tr>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Certified_Organization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></b></td><td></td>
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
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>

    <span id="certifications">
    
<%
List certList = aform.getCertificationsList();

%>
<% if(certList != null && certList.size()>0){%>


<table  border="0" width="100%">

<%	
for(int i=0;i<certList.size();i++){
	EducationDetails edu = (EducationDetails)certList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- org details -->
<table valign="top" border="0" width="60%">
    <tr>
	
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getEducationName()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Certified_Organization",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getInstituteName()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPercentile()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPassingYear()%></td>
    </tr>
	
	

 </table>
 <!-- org details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getEducationId()+"&action=certification_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid();
%>
<tr>

<td width="125px"><a href="#" onClick="addattachmentscr('<%=attachurl%>')">add attachment</a></td>
<td></td>
<td></td>
</tr>


<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("certification_details_proof_attachment")){
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname=certification_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid()+"&uuid="+actionattach.getUuid();

%>
<tr>
<td width="125px"></td>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="10"  width="9"/></a>
</td>
</tr>
<%
		}
	}%>

<%}%>

 </table>


 <!-- attachements -->
 <td>
<!-- delete -->
<table valign="top" border="0" width="10%">
<tr>
<td><a href="#" onClick="deletecertification('<%=edu.getEducationId()%>')"><img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("aquisition.applicant.delete_certification",user1.getLocale())%>" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_certification",user1.getLocale())%>" height="20"  width="19"/></a></td>
 </td>
</tr>
 </table>
<!-- delete end -->
 </td>
 </tr>
<%
}
 %>

 </table>
<%
}else{

%>
<%=Constant.getResourceStringValue("aquisition.applicant.Certifications_not_added",user1.getLocale())%>
<%}%>

   
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
