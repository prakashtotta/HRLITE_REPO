<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<head>
<link rel="stylesheet" type="text/css" href="jsp/ajaxtabs/ajaxtabs.css" />

<script type="text/javascript" src="jsp/ajaxtabs/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>





</head>


<script language="javascript">

function createapplicant(){
	var url = "<%=request.getContextPath()%>/applicant.do?method=createPage";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%>',url,messageret);
	//GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%>',url,800,100%, messageret);
}


function messageret(){
	window.location.reload();
	//history.go(0);

			}



</script>


<body>
<br>
<a  href="#" onClick="javascript:createapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="applicant.do?method=applicanttrackingnext"> <%=Constant.getResourceStringValue("aquisition.applicant.more_options",user1.getLocale())%>>> </a>
<br><br>
<table width="110%">

<div id="pettabs" class="indentmenu">
<ul>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.APPLICATION_SUBMITTED%>" rel="#iframe" class="selected">
<%=Constant.getResourceStringValue("aquisition.applicant.Submitted",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=In Screening" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.In_Screening",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Cleared-In Screening" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Passed-Screening",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Interview Round-1" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Interview_1",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Cleared-Interview Round-1" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Passed-Interview_1",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Interview Round-2" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Interview_2",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Cleared-Interview Round-2" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Passed_Interview_2",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.OFFER_INITIATED%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Offer_initiated",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.READY_TO_RELEASE_OFFER%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Offer_approved",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.OFFER%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Offer_Released",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.OFFER_ACCEPTED%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Offer_accepted",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.OFFER_IN_NEGOTIATION%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Offer_Negotiation",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.OFFER_DECLINED%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Offer_Declined",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.REFERENCE_CHECK%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Reference_check",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.ON_HOLD%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.REJECTED%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Rejected",user1.getLocale())%></a></li>
</ul>
<br style="clear: left" />
</div>

<div id="petsdivcontainer" style="border:1px solid gray; width:1400px; height:800px; padding: 5px; margin-bottom:1em">

</div>

</table>
<script type="text/javascript">

var mypets=new ddajaxtabs("pettabs", "petsdivcontainer")
mypets.setpersist(true)
mypets.setselectedClassTarget("link")
mypets.init()

</script>




	
</body>
</html>

