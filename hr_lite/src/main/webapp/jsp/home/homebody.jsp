<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js">

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
	width: 350px;
	height: 300px;
	
}

legend {
	background: #eee;
}
#modelDescription {
	background: #eee;
}

</style>


</head>

<body>

<br>
<%if(PermissionChecker.isPermissionApplied(Common.ADMIN_DASHBOARD,user1)){%>

<%@ include file="dashboardAdmin.jsp" %>
<%}else{%>
<ul id="countrytabs" class="shadetabs">
<li><a href="#" rel="countrycontainer" class="selected"><%=Constant.getResourceStringValue("task.myinterview.dashboard",user1.getLocale())%></a></li>
<li><a href="lov.do?method=callist" rel="countrycontainer"><%=Constant.getResourceStringValue("task.interview",user1.getLocale())%></a></li>
<li><a href="task.do?method=mypendingtasks" rel="countrycontainer"><%=Constant.getResourceStringValue("task.mypendingtasks.title.mypendingtask",user1.getLocale())%></a></li>


<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>


<%if(PermissionChecker.isPermissionApplied(Common.REVIEWER_DASHBOARD,user1)){%>

<%@ include file="dashboardReviewer.jsp" %>
<%}else if(PermissionChecker.isPermissionApplied(Common.HIRING_MANAGER_DASHBOARD,user1)){%>
<%@ include file="dashboardHiringManager.jsp" %>
<%}else if(PermissionChecker.isPermissionApplied(Common.RECRUITER_DASHBOARD,user1)){%>
<%@ include file="dashboardRecruiter.jsp" %>
<%}else {%>
<!--<%@ include file="dashboard.jsp" %>-->

<%}%>
</div>


		<script type="text/javascript">

		var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
		countries.setpersist(true)
		countries.setselectedClassTarget("link") //"link" or "linkparent"
		countries.init()

		countries.onajaxpageload=function(pageurl){
		if (pageurl.indexOf("externalnested.htm")!=-1){
		var provinces=new ddajaxtabs("provincetabs", "provincedivcontainer")
		provinces.setpersist(true)
		provinces.setselectedClassTarget("link") //"link" or "linkparent"
		provinces.init()
		}

		}


		</script>
<%}%>
	
</body>
</html>

