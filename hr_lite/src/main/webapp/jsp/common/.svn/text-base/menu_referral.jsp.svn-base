     <link href="jsp/vimco/css/dropdown/themes/vimeo.com/helper.css" media="screen" rel="stylesheet" type="text/css" />
<link href="jsp/vimco/css/dropdown/dropdown.css" media="screen" rel="stylesheet" type="text/css" />
<link href="jsp/vimco/css/dropdown/themes/vimeo.com/default.advanced.css" media="screen" rel="stylesheet" type="text/css" />

<%@ page import="java.util.*"%>
<%@ page import="com.bean.RefferalEmployee"%>
<%@ page import="com.common.Common"%>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%
RefferalEmployee employee = (RefferalEmployee)session.getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
   
%>




<script language="javascript">	

</script>
<%if(employee != null){%>
   <ul id="nav" class="dropdown dropdown-horizontal">
	<a href="login.do?method=backtotalent"><img border="0" style="float:left;" alt="" src="jsp/vimco/menu_left.png" title="Hires360"/></a>
	
	
	<li><a href="referralredemption.do?method=myredemptionlist">My redemptions</a></li>
	<li><a href="<%=request.getContextPath()%>/refjob.do?method=jobsearch">Job Search</a></li>
	<li><a href="<%=request.getContextPath()%>/refjob.do?method=mybookmarks">My bookmarks</a></li>
	<li><a href="<%=request.getContextPath()%>/refops.do?method=searchownapplicantinit">My applicants</a></li>
	<li class="last"><a href="<%=request.getContextPath()%>/referfriend.do?method=searchreferfriends">Refer friends</a></li>

	

</ul>

<ul class="rightside">
<table>
<tr>
<td>
<div style="height:20px;">
<a href="login.do?method=backtomydetails" title="My profile"><%=employee.getEmployeename()%></a>
</div>


</td>
<td>
<a href="login.do?method=backtotalent" title="talent portal">
| talent portal
</a>
</td>
<td>
<a href="refferal.do?method=home">
<img src="jsp/images/home.png" width="20" height="20" alt="Home" title="Home" style="border:2px solid black;"  />
</a>
</td>

<td>
<a href="login.do?method=logout">
<img src="jsp/images/Log-Out-icon.png" width="20" height="20" alt="Logout" title="Logout" style="border:2px solid black;"  />
</a>
</td>
</tr>
</table>
</ul>

<%}else{//end of menu %>
<%
String wsint = (String)session.getAttribute("wsint");
if(wsint == null || wsint.equals("no")){
%>

  <ul id="nav" class="dropdown dropdown-horizontal">
	<%
	
	String superUserKeymenu = (String)session.getAttribute(Common.SUPER_USER_KEY);
  String jobreqid = (String)session.getAttribute("jobreqid");

			
  if(superUserKeymenu != null){
    UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(new Long(EncryptDecrypt.decrypt(superUserKeymenu)).longValue());
	String subdomainnamemenu = userregdata.getSubdomain()+"."+Constant.getValue("domain.name");
%>
		<%if(userregdata.getLogoPhotoId() == 0){%>
			
		<a href="http://<%=subdomainnamemenu%>" ><img src="jsp/jobboard/images/logo_green.png" width="165" height="53" alt="" border="0"/></a>
			<%}else{
				String imgurlmenu = "jsp/emp/profilePhoto.jsp?id="+userregdata.getLogoPhotoId();
			%>

			<a href="#" ><img src="<%=imgurlmenu%>" border="0" width="165" height="53" alt="" /></a>

			<%}%>
<%}else if(jobreqid != null){
long superusermenukey = BOFactory.getJobRequistionBO().getSuperUserKeyByReqId(new Long(jobreqid).intValue());
UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(superusermenukey);
String	 subdomainnamemenu = userregdata.getSubdomain()+"."+Constant.getValue("domain.name");
%>

<%if(userregdata.getLogoPhotoId() == 0){%>
			
		<a href="http://<%=subdomainnamemenu%>" ><img src="jsp/jobboard/images/logo_green.png" width="165" height="53" alt="" border="0"/></a>
			<%}else{
				String imgurlmenu = "jsp/emp/profilePhoto.jsp?id="+userregdata.getLogoPhotoId();
			%>

			<a href="#" ><img src="<%=imgurlmenu%>" border="0" width="165" height="53" alt="" /></a>

			<%}%>

<%}else{%>
<img style="float:left;" alt="" src="jsp/vimco/menu_left.png"/>
<%}%>
</ul>
<%}%>
<%}%>

  <link rel="stylesheet" type="text/css" href="jsp/css/subModal.css" />
	<script type="text/javascript" src="jsp/js/common.js"></script>
	<script type="text/javascript" src="jsp/js/subModal.js"></script>
