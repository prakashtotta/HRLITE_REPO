<%@ include file="../common/include.jsp" %>

<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<%
User hiringmgr = form.getHiringmgr();

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
	width: 800px;
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
		width: 500px;
		height: 250px;

	}

   #chart1
	{
		width: 500px;
		height: 150px;
	}

</style>


</head>
<script language="javascript">

function addreferencedata1(url){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Add_reference",user1.getLocale())%>");
	parent.showPopWin(url, 600, 500, null,true);
}
function editreference(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Edit_refrerence",user1.getLocale())%>");
	parent.showPopWin(aid, 600, 500, null,true);
}
function startreferenceverification(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Startverification",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function viewreferencefeedbacks(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Feedbacks",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function approveOffer(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>");
	parent.showPopWin(aid, 600, 400, null,true);
}
function rejectOffer(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>");
	parent.showPopWin(aid, 600, 400, null,true);
}

function sendResumeForScrrening(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function goodToGo(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_feedback",user1.getLocale())%>");
	parent.showPopWin(aid, 800, 600, null,true);
}
function rejectApplicant(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_rejected",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function holdApplicant(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Hold_Applicant",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function reassignReview(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Re_assign_review",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function declineReview(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Decline_review",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function canceloffer(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>");
	parent.showPopWin(aid, 600, 400, null,true);
}
function releaseoffer(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}

function initiateoffer(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}

function scheduleinterviewround(aid){
	//alert(document.getElementById('chart'));
//	document.getElementById('chart').style.display = 'none';  
//	document.getElementById('chart1').style.display = 'none';  
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Schedule_Interview",user1.getLocale())%>");
	parent.showPopWin(aid, 850, 400, null,true);
}

function onholdapplicant(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%>");
	parent.showPopWin(aid, 850, 400, null,true);
}
function reInitiateOffer(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}

function onboardjoined(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%>");
	parent.showPopWin(aid, 600, 400, null,true);
}
function rescheduleInterviewround(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Reschedule_Interview",user1.getLocale())%>");
	parent.showPopWin(aid, 850, 400, null,true);
}

function resendResumeForScrrening(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Re_Send_profile_for_screening",user1.getLocale())%>");
	parent.showPopWin(aid, 600, 500, null,true);
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
<%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%> <br>
<div class="div">

<table>
 <tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="fullName"/></td>
		</tr>
		<%
       String tempurl1 = "";
       if(form.getJobTitle() != null){
   tempurl1 = "<a href='#' onClick=window.open("+"'"+"agjob.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+form.getJobTitle()+"</a>";
	   }
		%>
        <tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%> </td>
			<td class="bodytext"><%=tempurl1%></td>
		</tr>

</table>
</div>

<br><br>
<table>
<tr>
<td valign="top" width="60%">

<ul id="countrytabs" class="shadetabs">
<li><a href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<li><a href="applicantoffer.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer">
<%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%></a></li>
<li><a href="applicantoffer.do?method=applicantlogdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer">
<%=Constant.getResourceStringValue("aquisition.applicant.Interviewlog",user1.getLocale())%></a></li>
</ul>



<div id="countrydivcontainer" style="border:1px solid gray; width:600px; margin-bottom: 1em; padding: 10px">
<%@ include file="applicantdetailscommon.jsp" %>
</div>
</td>
</tr>
</table>
<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>


	
</body>
</html>

