<%@ include file="../common/yahooincludes.jsp" %>
<script type="text/javascript" src="jsp/js/animatedcollapse.js"></script>
<script type="text/javascript">
animatedcollapse.addDiv('addworkexp', 'fade=0,speed=400,group=pets1,hide=1,persist=1')
animatedcollapse.addDiv('workexpdetails', 'fade=0,speed=400,group=pets2,hide=1,persist=1')
animatedcollapse.addDiv('personalinfo', 'fade=0,speed=400,group=pets3,hide=0')
animatedcollapse.addDiv('workpreference', 'fade=0,speed=400,group=pets4,hide=1,persist=1')
animatedcollapse.addDiv('education', 'fade=0,speed=400,group=pets5,hide=1,persist=1')
animatedcollapse.addDiv('customvariable', 'fade=0,speed=400,group=pets6,hide=1,persist=1')
animatedcollapse.addDiv('profile', 'fade=0,speed=400,group=pets7,hide=0')
animatedcollapse.addDiv('source', 'fade=0,speed=400,group=pets8,hide=1,persist=1')
animatedcollapse.addDiv('social', 'fade=0,speed=400,group=pets9,hide=1,persist=1')
animatedcollapse.addDiv('background', 'fade=0,speed=400,group=pets10,hide=1,persist=1')
animatedcollapse.addDiv('addskills', 'fade=0,speed=400,group=pets11,hide=1,persist=1')
animatedcollapse.addDiv('skilldetails', 'fade=0,speed=400,group=pets12,persist=1')
animatedcollapse.addDiv('addeducation', 'fade=0,speed=400,group=pets13,hide=1,persist=1')
animatedcollapse.addDiv('educationdetails', 'fade=0,speed=400,group=pets14,persist=1')
animatedcollapse.addDiv('addcertification', 'fade=0,speed=400,group=pets15,hide=1,persist=1')
animatedcollapse.addDiv('certificationdetails', 'fade=0,speed=400,group=pets16,persist=1')
animatedcollapse.addDiv('sourcesubsource', 'fade=0,speed=400,group=pets17,persist=1')
animatedcollapse.addDiv('applicantpersonalinfo', 'fade=0,speed=400,group=pets18,persist=1')
animatedcollapse.addDiv('workpreferenceappdetails', 'fade=0,speed=400,group=pets19,persist=1')
animatedcollapse.addDiv('applicanteducation', 'fade=0,speed=400,group=pets20,persist=1')
animatedcollapse.addDiv('applicantprofile', 'fade=0,speed=400,group=pets21,persist=1')
animatedcollapse.addDiv('applicantsocial', 'fade=0,speed=400,group=pets22,persist=1')
animatedcollapse.addDiv('moreapplicantdetails', 'fade=0,speed=400,group=pets23,persist=1')
animatedcollapse.addDiv('applicantbackground', 'fade=0,speed=400,group=pets24,persist=1')
animatedcollapse.addDiv('eeoinfo', 'fade=0,speed=400,group=pets25,persist=1')
animatedcollapse.addDiv('captcha', 'fade=0,speed=400,group=pets26,persist=1')
animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()

</script>

<link rel="stylesheet" type="text/css" href="jsp/css/tabs.css" />
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />


<%

String ensuperUserKey = (String)session.getAttribute(Common.SUPER_USER_KEY);
String superUserKey = "";
if(ensuperUserKey != null){
superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
}else{
	String jobreqid = (String)session.getAttribute("jobreqid");
	if(jobreqid!=null){
	long superusermenukey = BOFactory.getJobRequistionBO().getSuperUserKeyByReqId(new Long(jobreqid).intValue());
	superUserKey = String.valueOf(superusermenukey);
	session.setAttribute(Common.SUPER_USER_KEY,EncryptDecrypt.encrypt(superUserKey));
	}
}

System.out.println("superUserKeysatyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+superUserKey);
 Map<String,List<String>> screenMap = null;
 List screenfieldList = null;
 List screenfieldListWorkTab = null;
  Map<String,List<String>> screenMapWorkExp=null;
  List screenfieldListEduTab = null;
  Map<String,List<String>> screenMapEducationDetials=null;
if(aform.getRequitionId()>0){
   screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_EXTERNAL,new Long(superUserKey).longValue());
   screenfieldList = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_EXTERNAL,new Long(superUserKey).longValue());
   screenfieldListWorkTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_WORK_EXP_EXTERNAL,new Long(superUserKey).longValue());
   screenMapWorkExp = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_WORK_EXP_EXTERNAL,new Long(superUserKey).longValue());
   screenfieldListEduTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_EDUCATION_EXTERNAL,new Long(superUserKey).longValue());
   screenMapEducationDetials = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_EDUCATION_EXTERNAL,new Long(superUserKey).longValue());
}else{

	screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_TALENTPOOL_EXTERNAL,new Long(superUserKey).longValue());
    screenfieldList = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_TALENTPOOL_EXTERNAL,new Long(superUserKey).longValue());
   	screenfieldListWorkTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL,new Long(superUserKey).longValue());
    screenMapWorkExp = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL,new Long(superUserKey).longValue());
    screenfieldListEduTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL,new Long(superUserKey).longValue());
    screenMapEducationDetials = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL,new Long(superUserKey).longValue());
}



  List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFields = screenMap.get(Common.MANDATORY_FIELDS_LIST);
System.out.println("******* refferaluserdetailsbody.jsp");
String datepattern = Constant.getValue("defaultdateformat");

String applicantId = (String)request.getAttribute("applicantId");
String uuid = (String)request.getAttribute("uuid");
JobApplicant applicant = null;

if(applicantId != null && uuid != null){
	
	applicant =BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId,uuid);

}

String applicant_proccessed= (String)request.getAttribute("applicant_proccessed");
String requistionclosed= (String)request.getAttribute("requistionclosed");
String saveAndNext= (String)request.getAttribute("saveAndNext");
String path = (String)request.getAttribute("filePath");
String refuserid = (String)request.getAttribute("refuserid");
String applicantsaved = (String)request.getAttribute("applicantsaved");
com.bean.Locale locale = null;
RefferalEmployee user1= null;

 	String source = (String)session.getAttribute("source");
	String locale_code = (String)session.getAttribute("locale_code");

if(!StringUtils.isNullOrEmpty(source) && !source.equals("null")){  
 locale= new com.bean.Locale();
locale.setLocaleCode(locale_code);


}else{
session.setAttribute("refuserid",refuserid);
user1 = RefBO.getRefferalEmployee(refuserid);
 locale= user1.getLocale();
}

String backurl = request.getContextPath()+"/refferaluser.do?method=backtoWorkExpSkillstab";
String backtodetails = (String)request.getAttribute("backtodetails");

%> 
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
<style type="text/css">

table.ex2
{
border-collapse:separate;
border-spacing:15px 3px;
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

<script language="javascript">
function savedata(){
	 
    var alertstr = "";
	
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 var numbers=/^[0-9]+$/;
	 var floats =/\.[0-9]{1,2}$/;

   	var fullName = document.applicantForm.fullName.value.trim();
   	
	var showalert=false;
	var checknoticedec="no";
	var showalert=false;
	resumedetails
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
    	alertstr = alertstr + "Name is mandatory<BR>";
		showalert = true;
		}


	if(typeof document.applicantForm.noofyearsexp != 'undefined'){
			var noofyearsexp = document.applicantForm.noofyearsexp.value.trim();
		if (isNaN(noofyearsexp)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.years_of_exp_not_a_number",locale)%><BR>";
			showalert = true;
			}
		}
	if(typeof document.applicantForm.currectctc != 'undefined'){
	      var currectctc = document.applicantForm.currectctc.value.trim();
	if (isNaN(currectctc)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.current_ctc__not_a_number",locale)%><BR>";
	    	showalert = true;
	 }
	}
	if(typeof document.applicantForm.expectedctc != 'undefined'){
	      var expectedctc = document.applicantForm.expectedctc.value.trim();
	 if (isNaN(expectedctc)){
	      alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.expected_ctc__not_a_number",locale)%><BR>";
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
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",locale)%><BR>";
	    	showalert = true;
		 
	 }}else if (checknoticedec=="yes"){
		

	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",locale)%> <BR>";

			showalert = true;
		 
		 
	 }
/*
		if(reg.test(email) ==false){
    	alertstr = alertstr + "Invalid Email-Id<BR>";
		showalert = true;
		}
*/   if(typeof document.applicantForm.note != 'undefined'){
	var note = document.applicantForm.note.value.trim();
	if( note.length > 800){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Note",locale)%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",locale)%><br>";
		showalert = true;
	}
}

if(typeof document.applicantForm.resumedetails != 'undefined'){
	var resumedetails = document.applicantForm.resumedetails.value.trim();
	if( resumedetails.length > 10000){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.resumeCopy",locale)%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",locale)%><br>";
		showalert = true;
	}
}


	 if (showalert){
    	alert(alertstr);
       return false;
         }

	  document.applicantForm.action = "refferaluser.do?method=saveapplicantData&refuserid=<%=refuserid%>&applicantId=<%=applicantId%>&uuid=<%=uuid%>&source=<%=source%>&locale_code=<%=locale_code%>";
	  document.applicantForm.submit();

	}

function acceptandsubmit(){

		  document.applicantForm.action = "refferaluser.do?method=acceptandsubmit&applicantId=<%=applicantId%>&uuid=<%=uuid%>&refuserid=<%=refuserid%>&source=<%=source%>&locale_code=<%=locale_code%>";
	  document.applicantForm.submit();
}

function updatedata(){
	 
    var alertstr = "";
	
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 var numbers=/^[0-9]+$/;
	 var floats =/\.[0-9]{1,2}$/;

   	var fullName = document.applicantForm.fullName.value.trim();
	
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
    	alertstr = alertstr + "Name is mandatory<BR>";
		showalert = true;
		}

	if(typeof document.applicantForm.noofyearsexp != 'undefined'){
		var noofyearsexp = document.applicantForm.noofyearsexp.value.trim();
	if (isNaN(noofyearsexp)){
       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.years_of_exp_not_a_number",locale)%><BR>";
		showalert = true;
		}
	}
if(typeof document.applicantForm.currectctc != 'undefined'){
      var currectctc = document.applicantForm.currectctc.value.trim();
if (isNaN(currectctc)){
       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.current_ctc__not_a_number",locale)%><BR>";
    	showalert = true;
 }
}
if(typeof document.applicantForm.expectedctc != 'undefined'){
      var expectedctc = document.applicantForm.expectedctc.value.trim();
 if (isNaN(expectedctc)){
      alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.expected_ctc__not_a_number",locale)%><BR>";
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
       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",locale)%><BR>";
    	showalert = true;
	 
 }}else if (checknoticedec=="yes"){
	

       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",locale)%> <BR>";

		showalert = true;
	 
	 
 }

	if(typeof document.applicantForm.note != 'undefined'){
	var note = document.applicantForm.note.value.trim();
	if( note.length > 800){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Note",locale)%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",locale)%><br>";
		showalert = true;
	}
}
	if(typeof document.applicantForm.resumedetails != 'undefined'){
		var resumedetails = document.applicantForm.resumedetails.value.trim();
		if( resumedetails.length > 10000){

	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.resumeCopy",locale)%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",locale)%><br>";
			showalert = true;
		}
	}

	 if (showalert){
    	alert(alertstr);
       return false;
         }

	  document.applicantForm.action = "refferaluser.do?method=updateapplicantData&refuserid=<%=refuserid%>&applicantId=<%=applicantId%>&uuid=<%=uuid%>&source=<%=source%>&locale_code=<%=locale_code%>";
	  document.applicantForm.submit();

	}
function next(){

	  document.applicantForm.action = "refferaluser.do?method=nexttoEducationtab&applicantId=<%=applicantId%>&uuid=<%=uuid%>&refuserid=<%=refuserid%>&source=<%=source%>&locale_code=<%=locale_code%>";
	  document.applicantForm.submit();

}
function back(){

	  document.applicantForm.action = "refferaluser.do?method=backtoWorkExpSkillstab&applicantId=<%=applicantId%>&uuid=<%=uuid%>&refuserid=<%=refuserid%>&source=<%=source%>&locale_code=<%=locale_code%>";
	  document.applicantForm.submit();
}
function backtoMain(){

	  document.applicantForm.action = "refferaluser.do?method=backtodetails&applicantId=<%=applicantId%>&uuid=<%=uuid%>&refuserid=<%=refuserid%>&source=<%=source%>&locale_code=<%=locale_code%>";
	  document.applicantForm.submit();
}


function jobDetails(id){
	//alert("Not implemented yet, need a job details page for not looged in user");
	
	var url = "applicantjob.do?method=jobdetails&reqid="+id;
  window.open(url,  'JobRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=no');
}
function deleteresume(){
	var doyou = confirm('<%=Constant.getResourceStringValue("hr.methods.deleteResumemsg",locale)%>');
	//alert("hi ..");
	if(doyou){
	  document.applicantForm.action = "refferaluser.do?method=deleteResume&applicantId=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>&resume=delete&refuserid=<%=refuserid%>";
	  document.applicantForm.submit();
	}
}

</script>


<script language="javascript">



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



function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}   

function deleteAttachment(url){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",locale)%>");
	  if (doyou == true){
		
		window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300");
	
	 
	   } 
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
			alert(eduadded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",locale)%>");
			return;
		}

		for(var i=0;i<len;i++)
		{
			if(edu[i].value.trim() == eduadded)
			{
			alert(eduadded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",locale)%>");
			return;
			}
		}
	}
	var applid = "<%=aform.getApplicantId()%>";
	if(applid == "0"){
		alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",locale)%>");
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
 	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Education_is_required",locale)%><BR>";
	showalert = true;
	}

if(institute == "" || institute == null){
 	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.College_University_is_required",locale)%><BR>";
	showalert = true;
	}


 if (showalert){
 	alert(alertstr);
    return false;
      }


var url="refferaluser.do?method=saveeducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=uuid%>&refuserid=<%=refuserid%>&educationName="+educationName+"&specialization="+specialization+"&institute="+institute+"&percentile="+percentile+"&passingyear="+passingyear+"&startingYear="+startingYear;


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
function retrieveEducations() {
    document.getElementById("loading1").style.visibility = "visible";
			document.applicantForm.educationName.value="";
		    document.applicantForm.specialization.value="";
		    document.applicantForm.instituteName.value="";
		    document.applicantForm.percentile.value="";
		    document.applicantForm.passingYear.value="";
		    document.applicantForm.startingYear.value="";
	var url="refferaluser.do?method=geteducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>&refuserid=<%=refuserid%>";

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
function deleteeducation(id){

	var url="refferaluser.do?method=deleteEducation&eduid="+id;


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
function addCertifications(){
    var alertstr = "";
	var applid = "<%=aform.getApplicantId()%>";
	if(applid == "0"){
		alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",locale)%>");
		return false;
	}


	var certName = document.applicantForm.certName.value;
	var specialization = "";
	var certorgName = document.applicantForm.certorgName.value;
	var certpercentile = document.applicantForm.certpercentile.value;
	var certpassingYear = document.applicantForm.certpassingYear.value;


	var showalert=false;

if(certName == "" || certName == null){
 	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.certificationnamereq",locale)%><BR>";
	showalert = true;
	}



 if (showalert){
 	alert(alertstr);
    return false;
      }


var url="refferaluser.do?method=saveeducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=uuid%>&refuserid=<%=refuserid%>&educationName="+certName+"&specialization="+specialization+"&institute="+certorgName+"&percentile="+certpercentile+"&passingyear="+certpassingYear;


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
function retrieveCertifications() {
    document.getElementById("loading2").style.visibility = "visible";
		document.applicantForm.certName.value="";
		document.applicantForm.certorgName.value="";
		document.applicantForm.certpercentile.value="";
		document.applicantForm.certpassingYear.value="";
	var url="refferaluser.do?method=geteducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>&refuserid=<%=refuserid%>";
	
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
function deletecertification(id){

	var url="refferaluser.do?method=deleteEducation&eduid="+id;


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
function retrieveURL(url) {
	   //convert the url to a string
	    document.getElementById("loading").style.visibility = "visible";

	    url="applicantoffer.do?method=stateListNologin";
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
function init(){
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

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
 </HEAD>

 
<body class="yui-skin-sam" onLoad="init()">   

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

<%

String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");

if(attachmentsizeexceed != null && attachmentsizeexceed.equals("yes")){
	int limitfileSize =  new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
	 limitfileSize = limitfileSize / (1024*1024);
	 String mbsize= String.valueOf(limitfileSize) + "MB";
	String[] mbargs = {mbsize};
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.attachmentsize.exceed",locale,mbargs)%></font> </td>
			<td> <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",locale)%></a></td>
		</tr>
		
	</table>
</div>

<%}%>

<%

JobApplicant oldapplicant = (JobApplicant)request.getAttribute("oldapplicant");

if(oldapplicant != null){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red">Applicant already added by some other source.Please contact Recruiter for details.</font></td>
			
		</tr>
		
	</table>
</div>

<%}%>


<html:form action="/refferaluser.do?method=saveapplicantData" enctype="multipart/form-data">




<%if(applicant_proccessed != null && applicant_proccessed.equals("yes")){ %>
<div align="center">
Dear <%=aform.getFullName()%>,<br>

Your application is with us.  Your application number is : <b><%=aform.getApplicantId()%> </b> for further communication. <br>
Your skills and qualifications are currently under review. <br>
If further information or action is required of you, a representative from our Recruitment Team will contact you.<br>

Thank you,<br>

Recruitment Team <br>
</div>



<%}else if(saveAndNext != null && saveAndNext.equals("0")){ %>

<%if(requistionclosed != null && requistionclosed.equals("yes")){ %>
<b><%=Constant.getResourceStringValue("refferal.job.closed.submitted.profile.applicantpool",locale)%> </b> 
<%}else if(aform.getRequitionId()>0){%>
<b><%=Constant.getResourceStringValue("refferal.job.applying.for",locale)%> &nbsp;&nbsp; <a href="#" onClick="javascript:jobDetails('<%=EncryptDecrypt.encrypt(String.valueOf(aform.getRequitionId()))%>');return false;"><%=aform.getJobTitle()%></a></b> 
<html:hidden property="requitionId"/>
<html:hidden property="jobTitle"/>
<%}else{%>
<b><%=Constant.getResourceStringValue("submitted.profile.to.applicantpool",locale)%> </b> 
<%}%>


<table width="700">
<tr align="right">
<td align="right"><font color="blue"><%=Constant.getResourceStringValue("aquisition.applicant.StepOneofthree",locale)%> </font></td> 
</tr>
</table>
<div id="header">
	<ul id="primary">
		<li><span><b>Step 1 > </b></span></li>
		<li><a href="#">Step 2 > </a></li>
		<li><a href="#">Final submit</a></li>
	</ul>
	</div>
<div id="main">
<div align="left">
		
  <%@ include file="../talent/createApplicantCommonFields.jsp" %>

<%@ include file="../common/captcha.jsp" %>

		<table width="800">
		<tr>
			<td></td>
			<td align = "right">
			<%if(backtodetails != null && backtodetails.equals("yes")){ %>
			<input type="button" name="login" value="Next" onClick="updatedata()">
			<%}else{ %>
			<input type="button" name="login" value="Next" onClick="savedata()">
			<%} %>
			
			</td>
			
		</tr>
	</table>

</div>
</div>
<%}else if(saveAndNext != null && saveAndNext.equals("1")){ 

%>

<%if(requistionclosed != null && requistionclosed.equals("yes")){ %>
<b><%=Constant.getResourceStringValue("refferal.job.closed.submitted.profile.applicantpool",locale)%> </b> 
<%}else if(aform.getRequitionId()>0){%>
<b><%=Constant.getResourceStringValue("refferal.job.applying.for",locale)%> &nbsp;&nbsp; <a href="#" onClick="javascript:jobDetails('<%=EncryptDecrypt.encrypt(String.valueOf(aform.getRequitionId()))%>');return false;"><%=aform.getJobTitle()%></a></b> 
<html:hidden property="requitionId"/>
<html:hidden property="jobTitle"/>
<%}else{%>
<b><%=Constant.getResourceStringValue("submitted.profile.to.applicantpool",locale)%> </b> 
<%}%>

<table width="700">
<tr align="right">
<td align="right"><font color="blue"><%=Constant.getResourceStringValue("aquisition.applicant.Steptwoofthree",locale)%> </font></td>
</tr>
</table>
<div id="header">
	<ul id="primary">
		<li><a href="#">Step 1 > </a></li>
		<li><span><b>Step 2 > </b></span></li>
		<li><a href="#">Final submit</a></li>
	</ul>
	</div>
<div id="main">
<br>
<%@ include file="../talent/screenFieldSortingWorkExpTab.jsp" %>
<!-- work exp start-->
<%@ include file="../talent/workExperience.jsp" %>
<!-- work exp end-->
	<br><br>
<!--Skills start-->
<%@ include file="../talent/addskills.jsp" %>
<!--Skills end-->	<br><br>

	<table width="90%">
			<tr>
			<td >
			<input type="button" name="login" value="Previous" onClick="backtoMain()">
			</td>
			<td  align="right">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.next",locale)%>" onClick="next()">
			</td>
			
		</tr> 
	</table>


</div>


<%}  if(saveAndNext != null && saveAndNext.equals("2")){ %>

<%if(requistionclosed != null && requistionclosed.equals("yes")){ %>
<b><%=Constant.getResourceStringValue("refferal.job.closed.submitted.profile.applicantpool",locale)%> </b> 
<%}else if(aform.getRequitionId()>0){%>
<b><%=Constant.getResourceStringValue("refferal.job.applying.for",locale)%> &nbsp;&nbsp; <a href="#" onClick="javascript:jobDetails('<%=EncryptDecrypt.encrypt(String.valueOf(aform.getRequitionId()))%>');return false;"><%=aform.getJobTitle()%></a></b> 
<html:hidden property="requitionId"/>
<html:hidden property="jobTitle"/>
<%}else{%>
<b><%=Constant.getResourceStringValue("submitted.profile.to.applicantpool",locale)%> </b> 
<%}%>

<table width="700">
<tr align="right">
<td align="right"><font color="blue"><%=Constant.getResourceStringValue("aquisition.applicant.Stepthreeofthree",locale)%> </font></td>
</tr>
</table>
<div id="header">
	<ul id="primary">
		<li><a href="#">Step 1 > </a></li>
		<li><a href="#">Step 2 > </a></li>
		<li><span><b>Final submit</b></span></li>
	</ul>
	</div>
<div id="main">
<br>
<%@ include file="../talent/screenFieldSortingEducationTab.jsp" %>
     <!--Education start-->
<%@ include file="../talent/addeducation.jsp" %>
<!--Education end-->

<br></br>

<!--Certifications start-->
<%@ include file="../talent/addcertifications.jsp" %>
<!--Certifications end-->

<br><br>
<table width="800">
<tr>
<td>
<%
	String orgname = BOFactory.getJobRequistionBO().getOrganizationNameByReqId(aform.getRequitionId());
	String orgnames[] = {orgname,orgname};
%>
<p><%=Constant.getResourceStringValue("aquisition.applicant.self.submit.terms",locale,orgnames)%></p>
</td>
</tr>
</table>
	<table width="800">
			<tr>
			<td >
			<input type="button" name="login" value="Previous" onClick="back()">
			</td>
			<td  align="right">
			<input type="button" name="login" value="Accept and Submit" onClick="acceptandsubmit()">
			</td>
			
		</tr> 
	</table>
</div>
<%}%>   






</html:form>
</body>
