<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
String datepattern = Constant.getValue("defaultdateformat");
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
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
	width:700px;
	border: 1px solid #999;
	padding: 30px;
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

p.ex {color:#4169E1;
	  font-size:110%;	
	}

</style>
<script type="text/javascript">
function generaldetails(){
	//alert("this is general detals section");
	//editapplicant.do?method=editApplicantprofile
	//document.applicantForm.action = "editapplicant.do?method=editApplicantprofile";
	//document.applicantForm.submit();

	var url = "<%=request.getContextPath()%>/editapplicant.do?method=editApplicantprofile&saveAndNext=0";
	GB_showFullScreen('General Details',url, messageret);
	
}
function workexperience(){
	//alert("this is workexperience section");
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=editApplicantprofile&saveAndNext=1";
	GB_showFullScreen('Work Experience Details',url, messageret);
}
function skills(){
	//alert("this is skills section");
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=editApplicantprofile&saveAndNext=2";
	GB_showFullScreen('Skills Details',url, messageret);
}
function educationdetails(){
	//alert("this is educationdetails section");
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=editApplicantprofile&saveAndNext=3";
	GB_showFullScreen('Education Details',url, messageret);
}
function certificationdetails(){
	//alert("this is certificationdetails section");
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=editApplicantprofile&saveAndNext=4";
	GB_showFullScreen('Certifications Details',url, messageret);
}
function references(){
	//alert("this is certificationdetails section");
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=applicantReferenceDetails&applicantId=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>";
	GB_showFullScreen('References',url, messageret);
}
function formINine(){
	var applicantId="<%=aform.getApplicantId()%>";
	//alert(applicantId);
	var url = "<%=request.getContextPath()%>/inineform.do?method=inineformscr&applicantId="+applicantId ;
	GB_showFullScreen('I-9 Entry',url, messageret);
}
function messageret(){
	window.location.reload();
}
function changepassword(applicantId){
	
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=changePassword&applicantId="+applicantId;
	GB_showCenter('Change Password',url,200,400, messageret1);
}

function messageret1(){
	
			}
function downloadapplicantdetails(){
	
	var url = "<%=request.getContextPath()%>/editapplicant.do?method=downloadApplicantDetailPdf&applicantid=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>";
	location.href =url;

}

</script>
 </HEAD>



<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<body>
<html:form action="/editapplicant.do?method=editApplicantprofile" >

<br>

<%
JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(aform.getRequitionId()));

%>
&nbsp;<a href="#" onclick="changepassword(<%=aform.getApplicantId()%>)">change password</a>
<br><br>
<fieldset>
<%
			
			String jobdyvalue ="<span id=\"requitionId\">";
	        if(jb.getJobreqId() != 0){
	
	  		String tempurl1 = "<a href='#' onClick=window.open("+"'"+"applicantjob.do?method=jobdetails&reqid="+EncryptDecrypt.encrypt(String.valueOf(jb.getJobreqId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=800"+"'"+")>"+jb.getJobTitle()+"</a>";
	 		jobdyvalue = "<span id=\"requitionId\">"+tempurl1+"</span>";
			}
		%>
<table border="0" width="100%"><tr>
<td><b>Applied For :</b> <%=jobdyvalue%> :

 <%=jb.getOrganization().getLocation() == null?"":jb.getOrganization().getLocation().locationName %> <%=jb.getDepartment() == null ?"":" - "+ jb.getDepartment().getDepartmentName()%>  <%=jb.getMinimumLevelOfEducation() == null ?"":" - "+ jb.getMinimumLevelOfEducation()%>
</td>
<td align="right">
<a href="#" onclick="downloadapplicantdetails();return false"><img src="jsp/images/PDF_icon.jpg" border="0" alt="Close" title="Download Applicant Details" height="30"  width="29"/></a> 
</td></tr>
</table>

<font color="red"><b>
____________________________________________________________________________________________________________
</b></font>
<br><br>
<table>
		<tr>
		<td><img src="jsp/images/deleteicon.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td><td>- you must complete this page before you can submit your application.</td>
	</tr>
		<tr>
		<td><img src="jsp/images/questionMark.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td><td>- this page includes optional questions which you can complete if you wish so.</td>
	</tr>
	
</table>
<br>
<font color="red"><b>
____________________________________________________________________________________________________________
</b></font><br><br>

<table width="100%">
	<tr>
		<td><img src="jsp/images/deleteicon.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td>
		<td width="60%"><p class="ex"><b>General details</b></p></td>
		<td>Mandatory Section</td> <td><a onClick="generaldetails()"><img src="jsp/images/editbutton.jpg" border="0" alt="Edit Section" title="Edit Section" height="21"  width="74"/></a></td>
	
	</tr>
	<tr>
		<td><img src="jsp/images/questionMark.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td>
		<td><p class="ex"><b>Work Experience</b></p></td>
		<td>Non-Mandatory Section</td> 
		<td><a onClick="workexperience()"><img src="jsp/images/editbutton.jpg" border="0" alt="Edit Section" title="Edit Section" height="21"  width="74"/></a></td>
	
	</tr>
	<tr>
		<td><img src="jsp/images/questionMark.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td>
		<td><p class="ex"><b>Education Details</b></p></td>
		<td>Non-Mandatory Section</td> <td><a onClick="educationdetails()"><img src="jsp/images/editbutton.jpg" border="0" alt="Edit Section" title="Edit Section" height="21"  width="74"/></a></td>
	</tr>
	<tr>
		<td><img src="jsp/images/questionMark.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td>
		<td><p class="ex"><b>Certification Details</b></p></td>
		<td>Non-Mandatory Section</td> <td><a onClick="certificationdetails()"><img src="jsp/images/editbutton.jpg" border="0" alt="Edit Section" title="Edit Section" height="21"  width="74"/></a></td>
	</tr>
	<tr>
		<td><img src="jsp/images/questionMark.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td>
		<td><p class="ex"><b>Skills</b></p></td>
		<td>Non-Mandatory Section</td> <td><a onClick="skills()"><img src="jsp/images/editbutton.jpg" border="0" alt="Edit Section" title="Edit Section" height="21"  width="74"/></a></td>
	
	</tr>
	<!--<tr>
		<td><img src="jsp/images/questionMark.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td>
		<td><p class="ex"><b>References</b></p></td>
		<td>Non-Mandatory Section</td> <td><a onClick="references()"><img src="jsp/images/editbutton.jpg" border="0" alt="Edit Section" title="Edit Section" height="21"  width="74"/></a></td>
	</tr>-->
	
		<tr>
		<td><img src="jsp/images/questionMark.jpg" border="0" alt="Edit Section" title="Edit Section" height="30"  width="30"/></td>
		<td><p class="ex"><b>Form I-9</b></p></td>
		<td>Non-Mandatory Section</td> <td><a onClick="formINine()"><img src="jsp/images/editbutton.jpg" border="0" alt="Edit Section" title="Edit Section" height="21"  width="74"/></a></td>
	</tr>
</table>

<br><br><br><br><br><br><br><br><br><br>



</fieldset>
</html:form>
</body>