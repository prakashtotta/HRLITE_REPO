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
	GB_showFullScreen('Create Applicant',url,messageret);
}
function messageret(){
	window.location.reload();
	//history.go(0);

			}



</script>


<body>
<br>
<a  href="#" onClick="javascript:createapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a> &nbsp;&nbsp;&nbsp;&nbsp;

<a href="applicant.do?method=applicanttracking"> << <%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%> </a>
<br><br>
<table width="110%">

<div id="pettabs" class="indentmenu">
<ul>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Interview Round-3" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Interview_3",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Cleared-Interview Round-3" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Passed_Interview_3",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Interview Round-4" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Interview_4",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Cleared-Interview Round-4" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Passed_Interview_4",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Interview Round-5" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Interview_5",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=Cleared-Interview Round-5" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Passed_Interview_5",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.OFFER_CANCELED%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.Offercanceled",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicanttrackingbystatus&status=<%=Common.ON_BOARD%>" rel="#iframe">
<%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%></a></li>

</ul>
<br style="clear: left" />
</div>

<div id="petsdivcontainer" style="border:1px solid gray; width:1400px; height:800px; padding: 5px; margin-bottom:1em">

</div>

</table>
<script type="text/javascript">

var mypets=new ddajaxtabs("pettabs", "petsdivcontainer")
mypets.setpersist(false)
mypets.setselectedClassTarget("link")
mypets.init()

</script>




	
</body>
</html>

