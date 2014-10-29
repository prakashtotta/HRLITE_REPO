<%@ include file="../common/include.jsp" %>
<%@ include file="../talent/applicantScreenCollapsableDivs.jsp" %>

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
  RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
  Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_REFERRAL,user1.getSuper_user_key());
  List screenfieldList = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_REFERRAL,user1.getSuper_user_key());
  List screenfieldListWorkTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_WORK_EXP_REFERRAL,user1.getSuper_user_key());
  Map<String,List<String>> screenMapWorkExp = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_WORK_EXP_REFERRAL,user1.getSuper_user_key());
        List screenfieldListEduTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_EDUCATION_REFERRAL,user1.getSuper_user_key());
   Map<String,List<String>> screenMapEducationDetials = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_EDUCATION_REFERRAL,user1.getSuper_user_key());
  List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFields = screenMap.get(Common.MANDATORY_FIELDS_LIST);

  
String datepattern = Constant.getValue("defaultdateformat");

if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
String applicantId = (String)request.getParameter("applicantId");
//String uuid = (String)request.getParameter("uuid");
String uuid = (String)request.getAttribute("uuid");
System.out.println("uuid : "+uuid);
JobApplicant applicant = null;

if(applicantId != null && uuid != null){
	
	applicant =BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId,uuid);

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
	document.getElementById('progressbartable1').style.display = 'inline';
     var alertstr = "";
    var fullName = document.applicantForm.fullName.value;
	
	var showalert=false;
	var checknoticedec="no";
	var showalert=false;
	var k;
	if(typeof document.applicantForm.noticeperiod != 'undefined'){
	      var noticeperiod = document.applicantForm.noticeperiod.value.trim();
	for(k=0;k<noticeperiod.length;k++){
		if(noticeperiod[k]=="."){
	     checknoticedec="yes";
		 break;

		}

	}
	}
	

	if(fullName == "" || fullName == null){
     	alertstr = alertstr + "Name is  mandatory.<BR>";
		showalert = true;
		}


	if(typeof document.applicantForm.noofyearsexp != 'undefined'){
			var noofyearsexp = document.applicantForm.noofyearsexp.value.trim();
		if (isNaN(noofyearsexp)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.years_of_exp_not_a_number",user1.getLocale())%><BR>";
			showalert = true;
			}
		}
	if(typeof document.applicantForm.currectctc != 'undefined'){
	      var currectctc = document.applicantForm.currectctc.value.trim();
	if (isNaN(currectctc)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.current_ctc__not_a_number",user1.getLocale())%><BR>";
	    	showalert = true;
	 }
	}
	if(typeof document.applicantForm.expectedctc != 'undefined'){
	      var expectedctc = document.applicantForm.expectedctc.value.trim();
	 if (isNaN(expectedctc)){
	      alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.expected_ctc__not_a_number",user1.getLocale())%><BR>";
		showalert = true;
	 }
	}
	if(typeof document.applicantForm.relyearsofexp != 'undefined'){
	    var relyearsofexp = document.applicantForm.relyearsofexp.value.trim();
	if (isNaN(relyearsofexp)){
	    alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.relevant_year_of_exp_not_a_number",locale)%><BR>";
		showalert = true;
	}
	}
	if(typeof document.applicantForm.noticeperiod != 'undefined'){
	      var noticeperiod = document.applicantForm.noticeperiod.value.trim();
	 if (isNaN(noticeperiod)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",user1.getLocale())%><BR>";
	    	showalert = true;
		 
	 }}else if (checknoticedec=="yes"){
		

	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",user1.getLocale())%> <BR>";

			showalert = true;
		 
		 
	 }
    if(typeof document.applicantForm.note != 'undefined'){
		var note = document.applicantForm.note.value.trim();
	if(note.length > 800){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Note",locale)%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",locale)%><br>";
		showalert = true;
	}
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
	document.getElementById('progressbartable1').style.display = 'inline';
     var alertstr = "";
    var fullName = document.applicantForm.fullName.value;
	
	var showalert=false;
	var checknoticedec="no";
	var showalert=false;
	var k;
	if(typeof document.applicantForm.noticeperiod != 'undefined'){
	      var noticeperiod = document.applicantForm.noticeperiod.value.trim();
	for(k=0;k<noticeperiod.length;k++){
		if(noticeperiod[k]=="."){
	     checknoticedec="yes";
		 break;

		}

	}
	}

	if(fullName == "" || fullName == null){
     	alertstr = alertstr + "Name is  mandatory.<BR>";
		showalert = true;
		}


	if(typeof document.applicantForm.noofyearsexp != 'undefined'){
			var noofyearsexp = document.applicantForm.noofyearsexp.value.trim();
		if (isNaN(noofyearsexp)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.years_of_exp_not_a_number",user1.getLocale())%><BR>";
			showalert = true;
			}
		}
	if(typeof document.applicantForm.currectctc != 'undefined'){
	      var currectctc = document.applicantForm.currectctc.value.trim();
	if (isNaN(currectctc)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.current_ctc__not_a_number",user1.getLocale())%><BR>";
	    	showalert = true;
	 }
	}
	if(typeof document.applicantForm.expectedctc != 'undefined'){
	      var expectedctc = document.applicantForm.expectedctc.value.trim();
	 if (isNaN(expectedctc)){
	      alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.expected_ctc__not_a_number",user1.getLocale())%><BR>";
		showalert = true;
	 }
	}
	if(typeof document.applicantForm.relyearsofexp != 'undefined'){
	    var relyearsofexp = document.applicantForm.relyearsofexp.value.trim();
	if (isNaN(relyearsofexp)){
	    alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.relevant_year_of_exp_not_a_number",locale)%><BR>";
		showalert = true;
	}
	}
	if(typeof document.applicantForm.noticeperiod != 'undefined'){
	      var noticeperiod = document.applicantForm.noticeperiod.value.trim();
	 if (isNaN(noticeperiod)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",user1.getLocale())%><BR>";
	    	showalert = true;
		 
	 }}else if (checknoticedec=="yes"){
		

	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",user1.getLocale())%> <BR>";

			showalert = true;
		 
		 
	 }
    if(typeof document.applicantForm.note != 'undefined'){
		var note = document.applicantForm.note.value.trim();
	if(note.length > 800){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Note",locale)%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",locale)%><br>";
		showalert = true;
	}
	}

	 if (showalert){
     	alert(alertstr);
        return false;
          }

	 

	
	  document.applicantForm.action = "applicantoffer.do?method=saveapplicantDataforRefferal";
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


var url="applicantoffer.do?method=savepreviousOrgdetailsforrefferal&applicantid=<%=aform.getApplicantId()%>&prevOrgName="+prevOrgName+"&role="+role+"&startDate="+startDate+"&endDate="+endDate+"&reasonforleave="+reasonforleave+"&reportingto="+reportingToName;


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


var url="applicantoffer.do?method=saveeducationdetailsforReferral&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&educationName="+certName+"&specialization="+specialization+"&institute="+certorgName+"&percentile="+certpercentile+"&passingyear="+certpassingYear;


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



function addEducation(edu){
	    var alertstr = "";
		var len=0;
		var educations;
		if(edu == undefined){
			len=0;
	
		}else{
			len=edu.length;
			educations=edu.value;
		}
		
		var eduadded = document.applicantForm.educationName.value.trim();
		//alert(eduadded +" "+educations );
		if(len != 0 ){
			if(educations != undefined && educations == eduadded){
				alert(eduadded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",user1.getLocale())%>");
				return;
			}
	
			for(var i=0;i<len;i++)
			{
				if(edu[i].value.trim() == eduadded)
				{
				alert(eduadded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",user1.getLocale())%>");
				return;
				}
			}
		}
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
		var startingYear = document.applicantForm.startingYear.value;



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


var url="applicantoffer.do?method=saveeducationdetailsforReferral&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&educationName="+educationName+"&specialization="+specialization+"&institute="+institute+"&percentile="+percentile+"&passingyear="+passingyear+"&startingYear="+startingYear;


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
	var url="applicantoffer.do?method=getpreviousOrgdetailsforrefferal&applicantid=<%=aform.getApplicantId()%>";

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
	var url="applicantoffer.do?method=geteducationdetailsforReferral&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>";

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
		    document.applicantForm.startingYear.value="";
		    document.applicantForm.passingYear.value="";
	var url="applicantoffer.do?method=geteducationdetailsforReferral&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>";

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
	
	url="applicantoffer.do?method=stateListNologin";

	
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	//url=url+"&cid="+document.applicantForm.countryId.value;
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
function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}   


function deleteAttachment(url){

		
		//  document.applicantForm.action = url;
	   //document.applicantForm.submit();
	  // window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
	  // window.location.reload();
	 

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


function deleteresume(){
	var doyou = confirm("Are you sure you would like to delete resume ..... ( OK = yes Cancel = No)");
	  if (doyou == true){
	  document.applicantForm.action = "applicantoffer.do?method=deleteResumeforref&applicantId=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>&resume=delete";
	  document.applicantForm.submit();
	  }
	}

function init(){
	document.getElementById('progressbartable1').style.display = 'none'; 
	if(typeof document.applicantForm.primarySkill != 'undefined'){
	var primaryskilvalue = document.applicantForm.primarySkill.value;
	if(primaryskilvalue == 'Others'){
		document.applicantForm.primarySkillOther.style.visibility="visible"; 
			 
	}else{
		document.applicantForm.primarySkillOther.style.visibility="hidden"; 
	}
	}
}
</script>

<%
String applicantsaved = (String)request.getAttribute("applicantsaved");
	String datastring1 = (String)request.getAttribute("datastring1");
	String path = (String)request.getAttribute("filePath");
	
if(applicantsaved != null && applicantsaved.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green">Applicant data is saved successfully.</font></td>
			<!-- <td> <a href="#" onclick="closewindow()">close window</a></td>-->
		</tr>
		
	</table>
</div>

<%}%>

<%

String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");

if(attachmentsizeexceed != null && attachmentsizeexceed.equals("yes")){
	int limitfileSize =  new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
	 limitfileSize = limitfileSize / (1024*1024);
	 String mbsize= String.valueOf(limitfileSize) + "MB";
	String[] mbargs = {mbsize};
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.attachmentsize.exceed",locale,mbargs)%></font> </td>
			<td> </td>
		</tr>
		
	</table>
</div>

<%}%>

<%

JobApplicant oldapplicant = (JobApplicant)request.getAttribute("oldapplicant");

if(oldapplicant != null){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red">Applicant already added by some other source.Please contact Recruiter for details.</font></td>
			
		</tr>
		
	</table>
</div>

<%}%>
<%
String applicantupdated = (String)request.getAttribute("applicantupdated");

if(applicantupdated != null && applicantupdated.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green">Applicant data is updated successfully.</font></td>
			<!--  <td> <a href="#" onclick="closewindow()">close window</a></td>-->
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
			<td><font color="red"><%=errorval%></font></td>
			<td></td>
			</tr>
		
<%}%>
		
	</table>
</div>

<%}%>

	<body class="yui-skin-sam" onLoad="init()">
<html:form action="/applicantoffer.do?method=saveapplicantDataforRefferal" enctype="multipart/form-data">
</br>
<ul id="countrytabs" class="shadetabs">
<li><a href="#" rel="country1" class="selected">Details</a></li>
<% if(BOFactory.getApplicantBO().isTabVisible(Common.APPLICANT_SCREEN_WORK_EXP_REFERRAL,user1.getSuper_user_key())){%>
<li><a href="#" rel="country2"><%=Constant.getResourceStringValue("aquisition.applicant.WorkExpSkills",user1.getLocale())%></a></li>
<%}%>
<% if(BOFactory.getApplicantBO().isTabVisible(Common.APPLICANT_SCREEN_EDUCATION_REFERRAL,user1.getSuper_user_key())){%>
<li><a href="#" rel="country3"><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></a></li>
<%}%>
</ul>
<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
<div id="country1" class="div">
<div align="left" >

	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>


   <%
       		String tempurl1 = "";
      		if(aform.getJobTitle() != null){
   				   				
   					tempurl1 = "<a href='#' onClick=window.open("+"'"+"applicantjob.do?method=jobdetails&reqid="+EncryptDecrypt.encrypt(String.valueOf(aform.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=800"+"'"+")>"+aform.getJobTitle()+"</a>";
   				
   				
	   		}
		%>
		

			<tr>

				<td width="40%">Job Title<span1></span1></td>

				<td>
				<input type="hidden"  name="reqid1" value="<%=aform.getRequitionId()%>"/>
				<input type="hidden" name="jobt1"  value="<%=aform.getJobTitle()%>"/>
				<%=tempurl1%></td>

		</tr>
        
</table>



		<!-- <%="satya"+aform.getHidesource()%> -->
   
   
   <html:hidden property="employeecode" /> 
<html:hidden property="referrerName" />
 <html:hidden property="referrerEmail" /> 
  <html:hidden property="sourceTypeId"/> 
  <html:hidden property="hidesource"/> 

  
<%@ include file="../talent/createApplicantCommonFields.jsp" %>

<table border="0" width="100%">
		<tr>
			<td>
						<span id='progressbartable1'>
						<img src="jsp/images/indicator.gif" height="20"  width="40"/>
						</span>
			<% if(aform.getApplicantId()!=0){  %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="updatedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata2()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
	</table>

</div>
</div>
<div id="country2" class="tabcontent">
<div align="center" >
<!--work exp start-->
<%@ include file="../talent/workExperience.jsp" %>
<!--work exp end-->
	<br>
<!--Skills start-->
<%@ include file="../talent/addskills.jsp" %>
<!--Skills end-->


	</div>
</div>

<div id="country3" class="tabcontent">
<div align="center">
     <!--Education start-->
<%@ include file="../talent/addeducation.jsp" %>
<!--Education end-->
<br>

<!--Certifications start-->
<%@ include file="../talent/addcertifications.jsp" %>
<!--Certifications end-->

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
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()


</script>
</body>
