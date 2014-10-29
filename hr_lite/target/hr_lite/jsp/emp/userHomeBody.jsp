<%@ include file="../common/yahooincludes.jsp" %>


<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js"></script>





</head>
<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'inline';   

</script>
<body>

<ul id="countrytabs" class="shadetabs">
<%
String userHometab = (String)request.getAttribute("userHome");
if(userHometab != null && userHometab.equals("user")){
%>
   <li><a href="user.do?method=userListwithPag" rel="" class="selected"><%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%></a></li>

<%}else{%>
   <li><a href="user.do?method=userListwithPag" rel="" class=""><%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%></a></li>
<%}%>

<% if(userHometab != null && userHometab.equals("userGroup")){
%>
   <li><a href="usergroup.do?method=usergrouplist" rel="" class="selected"><%=Constant.getResourceStringValue("hr.user.group_search",user1.getLocale())%></a></li>
<%}else{%>
   <li><a href="usergroup.do?method=usergrouplist" rel=""><%=Constant.getResourceStringValue("hr.user.group_search",user1.getLocale())%></a></li>
<%}%>

</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
<span id='progressbartable1'>

</span>
<%
String userHome = (String)request.getAttribute("userHome");
	
if(userHome != null && userHome.equals("user")){
%>
    <%@ include file="userListBody.jsp" %>

<%}else if(userHome != null && userHome.equals("userGroup")){%>

<%@ include file="userGroupListBody.jsp" %>
   
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

	
</body>
</html>
