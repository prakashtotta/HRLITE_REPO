<%@ include file="../common/include.jsp" %>
<%@ include file="../common/collapsable.jsp" %>
<script type="text/javascript">
animatedcollapse.addDiv('addworkexp', 'fade=0,speed=400,group=pets1,hide=0')
animatedcollapse.addDiv('workexpdetails', 'fade=0,speed=400,group=pets2,hide=0')
animatedcollapse.addDiv('personalinfo', 'fade=0,speed=400,group=pets3,hide=0')
animatedcollapse.addDiv('workpreference', 'fade=0,speed=400,group=pets4,hide=0')
animatedcollapse.addDiv('education', 'fade=0,speed=400,group=pets5,hide=0')
animatedcollapse.addDiv('customvariable', 'fade=0,speed=400,group=pets6,hide=0')
animatedcollapse.addDiv('profile', 'fade=0,speed=400,group=pets7,hide=0')
animatedcollapse.addDiv('source', 'fade=0,speed=400,group=pets8,hide=0')
animatedcollapse.addDiv('social', 'fade=0,speed=400,group=pets9,hide=0')
animatedcollapse.addDiv('background', 'fade=0,speed=400,group=pets10,hide=0')
animatedcollapse.addDiv('addskills', 'fade=0,speed=400,group=pets11,hide=0')
animatedcollapse.addDiv('skilldetails', 'fade=0,speed=400,group=pets12')
animatedcollapse.addDiv('addeducation', 'fade=0,speed=400,group=pets13,hide=0')
animatedcollapse.addDiv('educationdetails', 'fade=0,speed=400,group=pets14')
animatedcollapse.addDiv('addcertification', 'fade=0,speed=400,group=pets15,hide=0')
animatedcollapse.addDiv('certificationdetails', 'fade=0,speed=400,group=pets16')
animatedcollapse.addDiv('sourcesubsource', 'fade=0,speed=400,group=pets17')
animatedcollapse.addDiv('applicantpersonalinfo', 'fade=0,speed=400,group=pets18')
animatedcollapse.addDiv('workpreferenceappdetails', 'fade=0,speed=400,group=pets19')
animatedcollapse.addDiv('applicanteducation', 'fade=0,speed=400,group=pets20')
animatedcollapse.addDiv('applicantprofile', 'fade=0,speed=400,group=pets21')
animatedcollapse.addDiv('applicantsocial', 'fade=0,speed=400,group=pets22')
animatedcollapse.addDiv('moreapplicantdetails', 'fade=0,speed=400,group=pets23')
animatedcollapse.addDiv('applicantbackground', 'fade=0,speed=400,group=pets24')


animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()

</script>

  <%
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
String datepattern = Constant.getValue("defaultdateformat");
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

}
com.bean.Locale locale = user1.getLocale();
JobApplicant applicant = user1.getApplicant();

Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_PROFILE,applicant.getSuper_user_key());
  List screenfieldList = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_PROFILE,applicant.getSuper_user_key());
  List screenfieldListWorkTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_WORK_EXP_PROFILE,applicant.getSuper_user_key());
  Map<String,List<String>> screenMapWorkExp = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_WORK_EXP_PROFILE,applicant.getSuper_user_key());
  List screenfieldListEduTab = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.APPLICANT_SCREEN_EDUCATION_PROFILE,applicant.getSuper_user_key());
   Map<String,List<String>> screenMapEducationDetials = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_EDUCATION_PROFILE,applicant.getSuper_user_key());

  List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFields = screenMap.get(Common.MANDATORY_FIELDS_LIST);


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
	width:800px;
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
		  self.parent.location.reload();
	 parent.parent.GB_hide();
	 
	   } 
	}


function deleteAttachment(url){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		
		window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300");
	
	 
	   } 
}				
       
  function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}    
  function next(){

		  document.applicantForm.action = "editapplicant.do?method=nexttoEducationtab";
		  document.applicantForm.submit();

 }

   function backfirstpage(){

		  document.applicantForm.action = "editapplicant.do?method=editApplicantprofile";
		  document.applicantForm.submit();

 }
	
 function updatedata(){
	
    var alertstr = "";
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var fullName = document.applicantForm.fullName.value.trim();

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
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
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
	if(typeof document.applicantForm.noticeperiod != 'undefined'){
	      var noticeperiod = document.applicantForm.noticeperiod.value.trim();
	 if (isNaN(noticeperiod)){
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",user1.getLocale())%><BR>";
	    	showalert = true;
		 
	 }else if (checknoticedec=="yes"){
		
	
	       alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.notice_period__not_a_number",user1.getLocale())%> <BR>";
	
			showalert = true;
		 
		 
	 }
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


	
	  document.applicantForm.action = "editapplicant.do?method=updateapplicantData&applicantId=<%=aform.getApplicantId()%>&reqId=<%=aform.getRequitionId()%>&jobtitle=<%=aform.getJobTitle()%>";
	  document.applicantForm.submit();
	  // window.top.hidePopWin();
	
	

	}

 function backto2ndpage(url){

	  document.applicantForm.action = url;
	  document.applicantForm.submit();
}

function retrieveURLresumesource() {
   //convert the url to a string
    document.getElementById("loadingsource").style.visibility = "visible";

	
	var urlnew="editapplicant.do?method=loadResumeSources"+"&typeid="+document.applicantForm.sourceTypeId.value;

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


var url="editapplicant.do?method=saveeducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&educationName="+certName+"&specialization="+specialization+"&institute="+certorgName+"&percentile="+certpercentile+"&passingyear="+certpassingYear;


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

	var url="editapplicant.do?method=deleteEducation&eduid="+id;


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

	var url="editapplicant.do?method=deleteEducation&eduid="+id;


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

	var url="editapplicant.do?method=deleteSkill&skillid="+id;


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


var url="editapplicant.do?method=saveeducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&educationName="+educationName+"&specialization="+specialization+"&institute="+institute+"&percentile="+percentile+"&passingyear="+passingyear+"&startingYear="+startingYear;;


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


function addSkills(skill){
        var alertstr = "";

    	var len=0;
    	var skills;
    	if(skill == undefined){
    		len=0;

    	}else{
    		len=skill.length;
    		skills=skill.value;
    	}
    	
    	var skilladded = document.applicantForm.skillname.value.trim();
    	///alert(skilladded +" "+skills );
    	if(len != 0 ){
    		if(skills != undefined && skills == skilladded){
    			alert(skilladded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",locale)%>");
    			return;
    		}

    		for(var i=0;i<len;i++)
    		{
    			if(skill[i].value.trim() == skilladded)
    			{
    			alert(skilladded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",locale)%>");
    			return;
    			}
    		}
    	}
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",user1.getLocale())%>");
			return false;
		}


		var skillname = document.applicantForm.skillname.value;
		var yearsofexpskill = document.applicantForm.yearsofexpskill.value;
		var ratingskill = document.applicantForm.ratingskill.value;
	
		

var url="editapplicant.do?method=saveapplicantSkills&applicantid=<%=aform.getApplicantId()%>&skillname="+skillname+"&yearsofexpskill="+yearsofexpskill+"&ratingskill="+ratingskill;


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


function retrieveSkills() {
    document.getElementById("loading4").style.visibility = "visible";
		document.applicantForm.skillname.value="";
		document.applicantForm.yearsofexpskill.value="";
		document.applicantForm.ratingskill.value="";
	var url="editapplicant.do?method=getApplicantSkills&applicantid=<%=aform.getApplicantId()%>";

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
		
	var url="editapplicant.do?method=geteducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";

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
		    document.applicantForm.startingYear.value="";
	var url="editapplicant.do?method=geteducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";

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
	var tu = "lov.do?method=requisitionselector&dummyvar=1";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes");

//window.open('lov.do?method=requisitionselector&dummyvar=1','SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');

}

function changepassword(applicantId){
	
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=changePassword&applicantId="+applicantId;
	GB_showCenter('Change Password',url,200,400, messageret1);
}
function messageret1(){
	
			}
function init(){
	setTimeout ( "document.applicantForm.fullName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
	var primaryskilvalue = document.applicantForm.primarySkill.value;
	if(primaryskilvalue == 'Others'){
		document.applicantForm.primarySkillOther.style.visibility="visible"; 
			 
	}else{
		document.applicantForm.primarySkillOther.style.visibility="hidden"; 
	}
}
function deleteresume(){

		var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deleteResumemsg",user1.getLocale())%>");
	 if (doyou == true){

	  document.applicantForm.action = "editapplicant.do?method=deleteResume&applicantId="+<%=aform.getApplicantId()%>+"&resume=delete&reqId="+<%=aform.getRequitionId()%>;
	  document.applicantForm.submit();
	 }
	}
</script>

<%	
	String saveAndNext= (String)request.getAttribute("saveAndNext");
	String datastring1 = (String)request.getAttribute("datastring1");
	String path = (String)request.getAttribute("filePath");
	String backurl = request.getContextPath()+"/editapplicant.do?method=backtoWorkExpSkillstab";
	
%>

<%
String applicantupdated = (String)request.getAttribute("applicantupdated");

if(applicantupdated != null && applicantupdated.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/greentick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.AppDataUpdateMsg",user1.getLocale())%></font></td>
			
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
<html:form action="/editapplicant.do?method=saveapplicantData" enctype="multipart/form-data">
<!--  
<ul id="countrytabs" class="shadetabs">
<li><a href="#" rel="country1" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<li><a href="#" rel="country2"><%=Constant.getResourceStringValue("aquisition.applicant.WorkExpSkills",user1.getLocale())%></a></li>
<li><a href="#" rel="country3"><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%> </a></li>
</ul>-->

<!-- <a href="#" onclick="changepassword(<%=aform.getApplicantId()%>)">change password</a> -->

<%if(saveAndNext != null && saveAndNext.equals("0")){ %>




	<fieldset><legend>General Details</legend>

		<table border="0" width="100%">	
        <%
			System.out.println("aform.getRequitionId()"+aform.getRequitionId());
			String jobdyvalue ="<span id=\"requitionId\">";
	        if(aform.getRequitionId() != 0){
	
	  		String tempurl1 = "<a href='#' onClick=window.open("+"'"+"applicantjob.do?method=jobdetails&reqid="+EncryptDecrypt.encrypt(String.valueOf(aform.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=800"+"'"+")>"+aform.getJobTitle()+"</a>";
	 		jobdyvalue = "<span id=\"requitionId\">"+tempurl1+"</span>";
			}
		%>
		<tr>
				<td width="40%">Applied For :</td>
				
				<td class="bodytext" ><%=jobdyvalue%></td>
		</tr>
	 
		</table>
 	  
 	  <table border="0" width="100%">	

			<%@ include file="../talent/createApplicantCommonFields.jsp" %>

	  </table>
	
	</fieldset>

<table border="0" width="100%">	
		<tr>
			<td></td>
			<td align = "left">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="updatedata()">
			</td>
			
		</tr>
		

	</table>
<div align="center">
<%}else if(saveAndNext != null && saveAndNext.equals("1")){ %>

<!--work exp start-->
<%@ include file="../talent/screenFieldSortingWorkExpTab.jsp" %>
<%@ include file="../talent/workExperience.jsp" %>
<!--work exp end-->
<%}  if(saveAndNext != null && saveAndNext.equals("2")){ %>
<br>
<!--Skills start-->
<%@ include file="../talent/screenFieldSortingWorkExpTab.jsp" %>
<%@ include file="../talent/addskills.jsp" %>
<!--Skills end-->
	<br>
<!--  remove back and next button
	
-->

<%}  if(saveAndNext != null && saveAndNext.equals("3")){ %>
<!--  remove back button

-->
<br>
<%@ include file="../talent/screenFieldSortingEducationTab.jsp" %>
    <!--Education start-->
<%@ include file="../talent/addeducation.jsp" %>
<!--Education end-->

<%}  if(saveAndNext != null && saveAndNext.equals("4")){ %>
<!--Certifications start-->
<%@ include file="../talent/screenFieldSortingEducationTab.jsp" %>
<%@ include file="../talent/addcertifications.jsp" %>
<!--Certifications end-->
<%}%>
	

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
