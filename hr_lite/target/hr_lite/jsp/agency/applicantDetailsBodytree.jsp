<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.util.EncryptDecrypt"%>
<%@ include file="../common/include.jsp" %>



<%
  
 
 
  


  %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.dao.*"%>

<%
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN,user1.getSuper_user_key());
List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);


String path = (String)request.getAttribute("filePath");

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<%

ResumeSources rc= null;
User usr=null;
if(form.getSourceId() != 0){
rc = BOFactory.getApplicantBO().getResumeSourcesById(String.valueOf(form.getSourceId()));

}
if(form.getVendorId() != 0){
	usr=UserDAO.getUserByVendorIdandTypeVendor(form.getVendorId());
}

%>
<%

User hiringmgr = form.getHiringmgr();
Recruiter recruiter = form.getRecruiter();
List appusractionsList = ApplicantUserBO.getApplicantActionsList(form.getApplicantId(),form.getUuid());
%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/ajaxtabs/ajaxtabs.css" />

<script type="text/javascript" src="jsp/ajaxtabs/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
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

<style type="text/css">
	#chart
	{
		width: 600px;
		height: 250px;
	}

   #chart1
	{
		width: 600px;
		height: 250px;
	}

</style>

</head>

<script language="javascript">
this.reload_on_close=false;

function openResume(){
	var tu = "agencyops.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
   var url = tu;
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");
}

function addreferencedata1(url){
	//parent.setPopTitle1("Add refrerence");
	//parent.showPopWin(url, 600, 500, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Add_reference",user1.getLocale())%>', url,500,600, messageret);
}
function editreference(url){
	//parent.setPopTitle1("Edit reference");
	//parent.showPopWin(aid, 600, 500, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Edit_refrerence",user1.getLocale())%>', url,500,600, messageret);
}
function startreferenceverification(url){
	//parent.setPopTitle1("Start Verification");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Startverification",user1.getLocale())%>',url,600,700, messageret);
}
function viewreferencefeedbacks(url){
	//parent.setPopTitle1("Feedbacks");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Feedbacks",user1.getLocale())%>',url,600,700, messageret);
}
function approveOffer(url){
	//parent.setPopTitle1("Approve offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>',url,400,600, messageret);
}
function rejectOffer(url){
	//parent.setPopTitle1("Reject offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>',url,400,600, messageret);
}

function sendResumeForScrrening(url){
	//parent.setPopTitle1("Send profile for screening");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>',url,600,700, messageret);
}
function goodToGo(url){
	//parent.setPopTitle1("Applicant feedback");
	//parent.showPopWin(aid, 800, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_feedback",user1.getLocale())%>',url,messageret);
	
}
function releasehold(url){
	//parent.setPopTitle1("Applicant feedback");
	//parent.showPopWin(aid, 800, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.release_hold",user1.getLocale())%>',url,messageret);
	
}

function rejectApplicant(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_rejected",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function holdApplicant(url){
	//parent.setPopTitle1("Hold Applicant");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Hold_Applicant",user1.getLocale())%>',url,600,700, messageret);
}
function reassignReview(url){
	//parent.setPopTitle1("Re-assign review");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_assign_review",user1.getLocale())%>',url,600,700, messageret);
}
function declineReview(url){
	//parent.setPopTitle1("Decline review");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Decline_review",user1.getLocale())%>',url,600,700, messageret);
}
function canceloffer(url){
	//parent.setPopTitle1("Cancel offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>',url,400,600, messageret);
}
function releaseoffer(url){
	//parent.setPopTitle1("Release offer");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>',url, messageret);
}

function initiateoffer(url){
	//parent.setPopTitle1("Initiate offer");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>',url, messageret);
}

function scheduleinterviewround(url){
	//alert(document.getElementById('chart'));
//	document.getElementById('chart').style.display = 'none';  
//	document.getElementById('chart1').style.display = 'none';  
	//parent.setPopTitle1("Schedule Interview");
	//parent.showPopWin(aid, 850, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Schedule_Interview",user1.getLocale())%>',url,700,850, messageret);
}

function onholdapplicant(url){
	//parent.setPopTitle1("On hold");
	//parent.showPopWin(aid, 850, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%>',url,400,850, messageret);
}
function reInitiateOffer(url){
	//parent.setPopTitle1("Re-Initiate offer");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>',url, messageret);
}

function onboardjoined(url){
	//parent.setPopTitle1("On board - Joined");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%>',url,400,600, messageret);
}
function rescheduleInterviewround(aid){
	//parent.setPopTitle1("Reschedule Interview");
	//parent.showPopWin(aid, 850, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reschedule_Interview",user1.getLocale())%>',url,400,850, messageret);
}

function resendResumeForScrrening(url){
	//parent.setPopTitle1("Re-Send profile for screening");
	//parent.showPopWin(aid, 600, 500, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Send_profile_for_screening",user1.getLocale())%>',url,500,600, messageret);
}

function recallapplicant(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.Recall",user1.getLocale())%>",url,300,600, messageret);
	
}

function addtag(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.add.tag",user1.getLocale())%>",url,300,600, messageret);
	
}


function addapplicantactions(url){

	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%>",url,500,600, messageret);
	
}
function editapplicantactions(url){

	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%>",url,500,600, messageret);
	
}

function createUser(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,300,600, messageret);
}

function editApplicantUser(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,300,600, messageret);
}

function addcomment(url){
		GB_showFullScreen('<%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%>',url,messageret);
}
function jobDetails(id){
	//alert("Not implemented yet, need a job details page for not looged in user");
	
	var url = "applicantjob.do?method=jobdetails&reqid="+id;
  window.open(url,  'JobRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=no');
}
function messageret(){
	window.location.reload();

			}


function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
        location.reload(true);

	 
	   } 
	}
</script>  

<script type="text/javascript">
function openURLdelay(urlf){
      var encodejurl = encodeURIComponent(urlf);
	  location.href = "preload1.jsp?reurl="+encodejurl;
	 

		}
</script>


<body>


<%@include file="/jsp/common/tooltip.jsp" %>
<div class="div">
<table>
<tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
			<td class="bodytext">
			<% if(PermissionBO.isApplicantEditAllowed(user1,hiringmgr.getUserId(),recruiter)){%>
			<a href="#" onClick="editapplicant('<%=form.getApplicantId()%>');return false;"><bean:write name="applicantForm" property="fullName"/></a>
			<%}else{%>
			<bean:write name="applicantForm" property="fullName"/>
			<%}%>
			</td>

		</tr>
<tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="applicantId"/></td> 

		</tr>

        <tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%></td>
			<td class="bodytext"><a href="#" onClick="javascript:jobDetails('<%=EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))%>');return false;"><%=form.getJobTitle()%></a></td>
		</tr>

		<tr>


</tr>

</table>
<div>
 <table>
 <tr>
 <td>
 <%@ include file="applicantbutton.jsp" %>
 </td>
 </tr>
 </table>


<ul id="countrytabs" class="shadetabs">
<li><a href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<li><a href="agencyops.do?method=additionalDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Additionaldetails",user1.getLocale())%></a></li>
<li><a href="applicantoffer.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%></a></li>


</ul>



<div id="countrydivcontainer" style="border:1px solid gray; width:850px; margin-bottom: 1em; padding: 10px">

<%@ include file="../talent/applicantdetailscommon.jsp" %>

</div>


<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>




</body>
</html>



