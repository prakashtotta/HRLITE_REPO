<%@ include file="../common/include.jsp" %>
<%@ include file="../talent/applicantScreenCollapsableDivs.jsp" %>


<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
  <%
  User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
  String screencode= aform.getScreenCode();
  if(screencode == null){
 screencode=Common.APPLICANT_SCREEN;
  }
   ScreenFieldData scrconfdata = BOFactory.getBusinessFilterBO().screenFieldConfigurationsByScreenCode(screencode,user1.getSuper_user_key());
  Map<String,List<String>> screenMap = scrconfdata.getScreenMap();
  List screenfieldList =scrconfdata.getScreenfieldList();
  List screenfieldListWorkTab = scrconfdata.getScreenfieldListWorkTab();
  Map<String,List<String>> screenMapWorkExp = scrconfdata.getScreenMapWorkExp();
  List screenfieldListEduTab = scrconfdata.getScreenfieldListEduTab();
   Map<String,List<String>> screenMapEducationDetials = scrconfdata.getScreenMapEducationDetials();

  List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFields = screenMap.get(Common.MANDATORY_FIELDS_LIST);
String datepattern = Constant.getValue("defaultdateformat");

if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
String skillalreadyadded = (String)request.getParameter("skillalreadyadded");
String educationalreadyadded = (String)request.getParameter("educationalreadyadded");

com.bean.Locale locale = user1.getLocale();



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
span1{color:#ff0000;}
</style>

<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
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

      
	  
  function init(){

		document.getElementById('progressbartable1').style.display = 'none';  
	document.getElementById('progressbartable2').style.display = 'none'; 
       
	//setTimeout ( "document.applicantForm.fullName.focus(); ", 200 );

//document.roleForm.roleCode.focus();
   if(typeof document.applicantForm.primarySkill != 'undefined'){
	var primaryskilvalue = document.applicantForm.primarySkill.value;
	if(primaryskilvalue == 'Others'){
		document.applicantForm.primarySkillOther.style.visibility="visible"; 
			 
	}else{
		document.applicantForm.primarySkillOther.style.visibility="hidden"; 
	}
   }
}


 function updatedata(){
	
    var alertstr = "";
	var fullName = document.applicantForm.fullName.value.trim();
	var jobt1 = document.applicantForm.requitionId.value.trim();

//var checkdecimal="no";
var checknoticedec="no";
var showalert=false;
//var i;
var k;
//for(i=0;i<lengthnoofyear;i++){
//	if(noofyearsexp[i]=="."){
//     checkdecimal="yes";
//	 break;

//	}

//}

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

	
	if(jobt1 == "" || jobt1 == null || jobt1== "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobTitle_mandatory",user1.getLocale())%><BR>";

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
if(typeof document.applicantForm.relyearsofexp != 'undefined'){
	var relyearsofexp = document.applicantForm.relyearsofexp.value.trim();
	 if (isNaN(relyearsofexp)){
	      alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.relevant_year_of_exp_not_a_number",user1.getLocale())%><BR>";
		showalert = true;
	 }
}
	 if (showalert){
     	alert(alertstr);
        return false;
          }

	  	document.getElementById('progressbartable1').style.display = 'inline';  
	document.getElementById('progressbartable2').style.display = 'inline'; 
	  document.applicantForm.action = "applicant.do?method=updateapplicantData&applicantId="+<%=aform.getApplicantId()%>;
	  document.applicantForm.submit();
	 
	}

function savedata2(){
	
    var alertstr = "";
	var fullName = document.applicantForm.fullName.value.trim();
	var jobt1 = document.applicantForm.requitionId.value.trim();
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
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	
	if(jobt1 == "" || jobt1 == null || jobt1== "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobTitle_mandatory",user1.getLocale())%><BR>";

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
if(typeof document.applicantForm.relyearsofexp != 'undefined'){
	var relyearsofexp = document.applicantForm.relyearsofexp.value.trim();
	 if (isNaN(relyearsofexp)){
	      alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.relevant_year_of_exp_not_a_number",user1.getLocale())%><BR>";
		showalert = true;
	 }
}



	if (showalert){
	     	alert(alertstr);
	        return false;
	}

			  	document.getElementById('progressbartable1').style.display = 'inline';  
	document.getElementById('progressbartable2').style.display = 'inline'; 
	  document.applicantForm.action = "applicant.do?method=saveapplicantData";
	  document.applicantForm.submit();
	 
	
	

	}




   

function opensearchjobreq(){
	var tu = "lov.do?method=requisitionselector&frompage=createapppage";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  'SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');

}

function deleteresume(){
	var doyou = confirm("Are you sure you would like to delete resume ..... ( OK = yes Cancel = No)");
	  if (doyou == true){
	  document.applicantForm.action = "applicant.do?method=deleteResume&applicantId=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>&resume=delete";
	  document.applicantForm.submit();
	  }
	}

function openConfiguration(){
	  var width = 800;
    var height = 700;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
	  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("screenfield.do?method=editscreenfield&screencode=APPLICANT_SCREEN");


    var windowFeatures = "width=" + width + ",height=" + height + ",status,resizable,location=0,status=0,scrollbars=1,menubar=0,left=" + left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
    myWindow = window.open(url, "OpenConfiguration", windowFeatures);

}

</script>

<%@ include file="createApplicantJS.jsp" %>

<%
String applicantsaved = (String)request.getAttribute("applicantsaved");
	String datastring1 = (String)request.getAttribute("datastring1");
	String path = (String)request.getAttribute("filePath");
	
if(applicantsaved != null && applicantsaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.AppDataSaveMsg",user1.getLocale())%></font></td>
			<td> </td>
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
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.attachmentsize.exceed",user1.getLocale(),mbargs)%></font> </td>
			<td> </td>
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
			<td><font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.alredyexist.same.emailid",user1.getLocale())%></font> <a href="applicant.do?method=applicantDetails&applicantId=<%=oldapplicant.getApplicantId()%>&secureid=<%=oldapplicant.getUuid()%>" target="new">view old profile.</a></td>
			<td> </td>
		</tr>
		
	</table>
</div>

<%}%>
<%

String applicantupdated = (String)request.getAttribute("applicantupdated");

if(applicantupdated != null && applicantupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.AppDataUpdateMsg",user1.getLocale())%></font></td>
			<td> </td>
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



	<body class="yui-skin-sam" onload="init()">    
<html:form action="/applicant.do?method=saveapplicantData" enctype="multipart/form-data">

<table border="0" width="100%">
		<tr>
			<td width="40%">
									<span id='progressbartable1'>
						<img src="jsp/images/indicator.gif" height="20"  width="40"/>
						</span>
			<% if(aform.getApplicantId()!=0){  %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update.applicant",user1.getLocale())%>" onClick="updatedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.create.applicant",user1.getLocale())%>" onClick="savedata2()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td align="right">
			<% if(PermissionChecker.isPermissionApplied(Common.ADMIN_CONFIGURATIONS, user1)){%>
			<a href="#" onClick="openConfiguration()" title="<%=Constant.getResourceStringValue("screen.setting.help",user1.getLocale())%>"><img src="jsp/images/setting.png" width="25" height="25" border="0"></a>
			<%}%>
			</td>
		</tr>
</table>
<br>
<ul id="countrytabs" class="shadetabs">
<li><a href="#" rel="country1" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<% if(BOFactory.getApplicantBO().isTabVisible(Common.APPLICANT_SCREEN_WORK_EXP,user1.getSuper_user_key()) && (aform.getApplicant_number() > 0)){%>
<li><a href="#" rel="country2"><%=Constant.getResourceStringValue("aquisition.applicant.WorkExpSkills",user1.getLocale())%></a></li>
<%}%>
<% if(BOFactory.getApplicantBO().isTabVisible(Common.APPLICANT_SCREEN_EDUCATION,user1.getSuper_user_key()) && (aform.getApplicant_number() > 0)){%>
<li><a href="#" rel="country3"><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%> </a></li>
<%}%>
</ul>
<div style="border:1px solid gray; width:100%;">
<div id="country1" class="tabcontent">
<div align="left">
     <div >
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

        
<% if(aform.getApplicant_number() > 0 ){%>
			<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
			<td><%=aform.getApplicant_number()%></td>
			
			
		</tr>
	<%}%> 

		<%
		
		String jobdyvalue ="<span id=\"requitionId\">";
        if(aform.getRequitionId() != 0){

  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"applicantjob.do?method=jobdetails&reqid="+EncryptDecrypt.encrypt(String.valueOf(aform.getRequitionId()))+"'"+","+ "'"+"RequistionPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+aform.getRequisitionName()+"</a>";
 jobdyvalue = "<span id=\"requitionId\">"+tempurl1+"</span>";
		}
%>
		<tr>


				<% if(aform.getRequitionId() != 0 ){%>
				<td width="40%"><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
                <td>
                 <html:hidden  property="requitionId"/> <%=jobdyvalue%>
				<%}else{%>
				<td width="40%"><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%><font color="red">*</font></td>
                <td>
				<html:select  property="requitionId" onchange="retrieveCurrencyCodes('jobreq.do?method=getcurrencycode');" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />
            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
				<a href="#" onClick="opensearchjobreq()"><img src="jsp/images/selector.gif" border="0"/></a>
			<%}%>
			</td>

		</tr>

</table>
</div>

<%@ include file="../talent/souceSubsource.jsp" %>


<%@ include file="../talent/createApplicantCommonFields.jsp" %>

<table border="0" width="100%">
		<tr>
			<td width="40%">
						<span id='progressbartable2'>
						<img src="jsp/images/indicator.gif" height="20"  width="40"/>
						</span>
			<% if(aform.getApplicantId()!=0){  %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update.applicant",user1.getLocale())%>" onClick="updatedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.create.applicant",user1.getLocale())%>" onClick="savedata2()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
</table>

</div>
</div>
<% if(aform.getApplicant_number() > 0 ){%>
<div id="country2" class="tabcontent">
<br>
<div align="left">

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
<br>
<div align="left">
<!--Education start-->
<%@ include file="../talent/addeducation.jsp" %>
<!--Education end-->
    
<br>
<!--Certifications start-->
<%@ include file="../talent/addcertifications.jsp" %>
<!--Certifications end-->
	</div>
</div>
<%}%>
</html:form>



	<script type="text/javascript">

var countries=new ddtabcontent("countrytabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()


</script>
</body>
