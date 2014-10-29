     <link href="jsp/vimco/css/dropdown/themes/vimeo.com/helper.css" media="screen" rel="stylesheet" type="text/css" />
<link href="jsp/vimco/css/dropdown/dropdown.css" media="screen" rel="stylesheet" type="text/css" />
<link href="jsp/vimco/css/dropdown/themes/vimeo.com/default.advanced.css" media="screen" rel="stylesheet" type="text/css" />

<%@ page import="java.util.*"%>
<%@ page import="com.bean.ApplicantUser"%>
<%@ page import="com.common.Common"%>
<%
ApplicantUser appuser = (ApplicantUser)session.getAttribute(Common.APPLICANT_USER_DATA);

%>




<script language="javascript">	

</script>
<%if(appuser != null){%>
   <ul id="nav" class="dropdown dropdown-horizontal">
	<a href="login.do?method=backtotalent"><img border="0" style="float:left;" alt="" src="jsp/vimco/menu_left.png" title="Hires360"/></a>
	
	
	<li><a href="editapplicant.do?method=myprofilemainpage">My profile</a></li>
	<% if(appuser.getApplicant().getInterviewState().equals(Common.OFFER) || appuser.getApplicant().getInterviewState().equals(Common.OFFER_ACCEPTED) || appuser.getApplicant().getInterviewState().equals(Common.OFFER_DECLINED)  || appuser.getApplicant().getInterviewState().equals(Common.ON_BOARD) || appuser.getApplicant().getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>

	<li><a href="applicantuserops.do?method=applicantofferdetails&applicantId=<%=appuser.getApplicant().getApplicantId()%>&secureid=<%=appuser.getApplicant().getUuid()%>">Offer details</a></li>
	<%}%>
	<li class="last"><a href="applicantuserops.do?method=getcomments&applicantId=<%=appuser.getApplicant().getApplicantId()%>&secureid=<%=appuser.getApplicant().getUuid()%>">comments</a></li>

	

</ul>

<ul class="rightside">
<table>
<tr>
<td>
<div style="height:20px;">
<a href="editapplicant.do?method=myprofilemainpage" title="My profile"><%=appuser.getApplicant().getFullName()%></a>
</div>


</td>

<td>
<a href="applicantlogin.do?method=home">
<img src="jsp/images/home.png" width="20" height="20" alt="Home" title="Home" style="border:2px solid black;"  />
</a>
</td>

<td>
<a href="applicantlogin.do?method=logout">
<img src="jsp/images/Log-Out-icon.png" width="20" height="20" alt="Logout" title="Logout" style="border:2px solid black;"  />
</a>
</td>
</tr>
</table>
</ul>

<%}else{//end of menu %>
  <ul id="nav" class="dropdown dropdown-horizontal">
	<img style="float:left;" alt="" src="jsp/vimco/menu_left.png"/>
</ul>
<%}%>

  <link rel="stylesheet" type="text/css" href="jsp/css/subModal.css" />
	<script type="text/javascript" src="jsp/js/common.js"></script>
	<script type="text/javascript" src="jsp/js/subModal.js"></script>
