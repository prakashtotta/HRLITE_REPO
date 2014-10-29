<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ include file="../common/calenderInt.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<head>


    <meta http-equiv="expires" content="Sun, 01 Dec 2001 12:00:00 EST" />

<title><%=Constant.getResourceStringValue("task.callistbody.title",user1.getLocale())%></title>




<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js"></script>
</head>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>



<!--begin custom header content for this example-->
<style type="text/css">
	#cal1Container {
	   margin:1em;
	}

	#caleventlog {
		float:left;
		width:65em;
		margin:1em;
		background-color:#eee;
		border:1px solid #000;
	}
	#caleventlog .bd {
		overflow:auto;
		height:30em;
		padding:2px;
	}
	#caleventlog .hd {
		background-color:white;
		border-bottom:1px solid #000;
		font-weight:bold;
		padding:2px;
	}
	#caleventlog .entry {
		margin:0;	
	}
</style>

<style type="text/css">
#example {
    height:50em;
}

label { 
    display:block;
    float:left;
    width:45%;
    clear:left;
}

.clear {
    clear:both;
}

#resp {
    margin:10px;
    padding:10px;
    border:1px solid #ccc;
    background:#fff;
}

#resp li {
    font-family:monospace
}

.yui-pe .yui-pe-content {
    display:none;
}
</style>

<!--end custom header content for this example-->

</head>

<body class="yui-skin-sam">
<br>

<ul id="countrytabs" class="shadetabs">
<li><a href="dashboard.do?method=dashboardlist" rel="countrycontainer"><%=Constant.getResourceStringValue("task.myinterview.dashboard",user1.getLocale())%></a></li>
<li><a href="lov.do?method=callist" rel="countrycontainer" class="selected"><%=Constant.getResourceStringValue("task.interview",user1.getLocale())%></a></li>
<li><a href="task.do?method=mypendingtasks" rel="countrycontainer"><%=Constant.getResourceStringValue("task.mypendingtasks.title.mypendingtask",user1.getLocale())%></a></li>

<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:1200px; height:700px; margin-bottom: 1em; padding: 10px">
<%@ include file="calendercommon.jsp" %>
</div>

		

	
</body>
</html>




