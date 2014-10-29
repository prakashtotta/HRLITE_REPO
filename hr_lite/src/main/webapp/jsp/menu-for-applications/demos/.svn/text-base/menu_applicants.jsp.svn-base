<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<%@ page import="java.util.*"%>
<%@ page import="com.bean.ApplicantUser"%>
<%@ page import="com.common.Common"%>
<%
ApplicantUser appuser = (ApplicantUser)session.getAttribute(Common.APPLICANT_USER_DATA);

%>
<HEAD>
	
	
	<link rel="stylesheet" href="jsp/menu-for-applications/demos/css/demos.css" media="screen" type="text/css">
	<script type="text/javascript" src="jsp/menu-for-applications/js/menu-for-applications.js"></script>
	<style type="text/css">
	body{
		margin:0px;
	}
	#otherMenu{
		width:500px;
		border-left:1px solid #000;
		border-right:1px solid #000;
	}
	#thirdMenu{
		width:600px;
		
	}
	
	#fourthmenu{
		width:230px;
		height:230px;
	}
	</style>
</head>

 <BODY>
<%if(appuser != null){%>
<table cellpadding="0" cellspacing="0" width="100%">
<tr valign="top" class="DHTMLSuite_menuBar_top">
     	  <td width="70%">
 
<!-- This <ul><li> list is the source of a menuModel object -->
<ul id="menuModel5">
	
	<li id="50003" itemType="separator"></li>
	<!--  <li id="50004"><a href="<%=request.getContextPath()%>/editapplicant.do?method=editApplicantprofile">My profile</a>-->
		  <li id="50004"><a href="<%=request.getContextPath()%>/editapplicant.do?method=myprofilemainpage">My profile</a>
	</li>
<% if(appuser.getApplicant().getInterviewState().equals(Common.OFFER) || appuser.getApplicant().getInterviewState().equals(Common.OFFER_ACCEPTED) || appuser.getApplicant().getInterviewState().equals(Common.OFFER_DECLINED)  || appuser.getApplicant().getInterviewState().equals(Common.ON_BOARD) || appuser.getApplicant().getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>
		<li id="50005" itemType="separator"></li>
	<li id="50006"><a href="<%=request.getContextPath()%>/applicantuserops.do?method=applicantofferdetails&applicantId=<%=appuser.getApplicant().getApplicantId()%>&secureid=<%=appuser.getApplicant().getUuid()%>">My offer details</a>
	</li>
<%}%>
<li id="50005" itemType="separator"></li>
<li id="50007"><a href="<%=request.getContextPath()%>/applicantuserops.do?method=getcomments&applicantId=<%=appuser.getApplicant().getApplicantId()%>&secureid=<%=appuser.getApplicant().getUuid()%>">comments</a>

	
	
</ul>
<div id="fifthMenu">

</div>
</td>
<td align="right" width="30%">welcome&nbsp;<a href="<%=request.getContextPath()%>/editapplicant.do?method=myprofilemainpage"><%=appuser.getApplicant().getFullName()%></a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/applicantlogin.do?method=home">home</a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/applicantlogin.do?method=logout">logout</a>
	  </td>
</tr>

</table>
<%}%>
<script type="text/javascript">

// First menu
var menuModel = new DHTMLSuite.menuModel();
DHTMLSuite.commonObj.setCssCacheStatus(false);
/* Fifth menu - created fro markup */
var menuModel5 = new DHTMLSuite.menuModel();
menuModel5.addItemsFromMarkup('menuModel5');
menuModel5.init();

var menuBar5 = new DHTMLSuite.menuBar();
menuBar5.addMenuItems(menuModel5);
menuBar5.setTarget('fifthMenu');
menuBar5.init();
</script>
 </BODY>
</HTML>
