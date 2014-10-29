     <link href="jsp/vimco/css/dropdown/themes/vimeo.com/helper.css" media="screen" rel="stylesheet" type="text/css" />
<link href="jsp/vimco/css/dropdown/dropdown.css" media="screen" rel="stylesheet" type="text/css" />
<link href="jsp/vimco/css/dropdown/themes/vimeo.com/default.advanced.css" media="screen" rel="stylesheet" type="text/css" />

<%@ page import="java.util.*"%>
<%@ page import="com.bean.User"%>
<%@ page import="com.common.Common"%>
<%
User user = (User)session.getAttribute(Common.AGENCY_DATA);

%>


<%@ include file="../common/fancybox.jsp" %>

<script language="javascript">	

</script>
<%if(user != null){%>
   <ul id="nav" class="dropdown dropdown-horizontal">
	<a href="login.do?method=backtotalent"><img border="0" style="float:left;" alt="" src="jsp/vimco/menu_left.png" title="Hires360"/></a>
	
	
	<li><a href="<%=request.getContextPath()%>/agencyredemption.do?method=myredemptionlist">My redemptions</a></li>
	<li><a href="<%=request.getContextPath()%>/agjob.do?method=jobsearch">Job Search</a></li>
	<li><a href="<%=request.getContextPath()%>/agjob.do?method=mybookmarks">My bookmarks</a></li>
	<li class="last"><a href="<%=request.getContextPath()%>/agencyops.do?method=searchownapplicantinit">My applicants</a></li>
	

</ul>

<ul class="rightside">
<table>
<tr>
<td>
<div style="height:20px;">
<a href="agencydetails.do?method=mydetails" title="My profile"><%=user.getFirstName()%></a>
</div>
</td>

<td>
<a href="agencyops.do?method=home">
<img src="jsp/images/home.png" width="20" height="20" alt="Home" title="Home" style="border:2px solid black;"  />
</a>
</td>

<td>
<a href="alogin.do?method=logout">
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
