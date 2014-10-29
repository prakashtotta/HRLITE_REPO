<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<%@ page import="java.util.*"%>
<%@ page import="com.bean.RefferalEmployee"%>
<%@ page import="com.common.Common"%>
<%
RefferalEmployee employee = (RefferalEmployee)session.getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
   
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
<%if(employee != null){%>
<table cellpadding="0" cellspacing="0" width="100%">
<tr valign="top" class="DHTMLSuite_menuBar_top">
     	  <td width="70%">
 
<!-- This <ul><li> list is the source of a menuModel object -->
<ul id="menuModel5">
	<li id="50000"><a href="<%=request.getContextPath()%>/referralredemption.do?method=myredemptionlist" title="My Refferal redemptions">My Refferal redemptions</a>
		
	</li>
	<li id="50001" itemType="separator"></li>
	<li id="50002"><a href="<%=request.getContextPath()%>/refjob.do?method=jobsearch">Job Search</a>
		
	</li>
	<li id="50003" itemType="separator"></li>
	<li id="50004"><a href="<%=request.getContextPath()%>/refjob.do?method=mybookmarks">My bookmarks</a>
	</li>
	<li id="50005" itemType="separator"></li>
	<li id="50006"><a href="<%=request.getContextPath()%>/refops.do?method=searchownapplicantinit">My applicants</a>
		
	</li>
	<li id="50007" itemType="separator"></li>
	<li id="50008"><a href="<%=request.getContextPath()%>/referfriend.do?method=searchreferfriends">Refer friends</a>
		
	</li>
	
	
</ul>
<div id="fifthMenu">

</div>
</td>
<td width="30%" align="right">welcome&nbsp;<b><%=employee.getEmployeename()%></b>&nbsp;&nbsp;
&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/login.do?method=backtotalent">talent portal</a>&nbsp;&nbsp<a href="<%=request.getContextPath()%>/refferal.do?method=home">home</a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/login.do?method=logout">logout</a>
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
