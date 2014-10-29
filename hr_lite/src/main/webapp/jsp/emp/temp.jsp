<%@ include file="../common/include.jsp" %>

<%
String reurl = (String)request.getParameter("reurl");
System.out.println("rurl"+reurl);
reurl =  request.getContextPath()+"/"+reurl;
%>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>



<script language="JavaScript">
var locationAfterPreload = "<%=reurl%>";
function checkLoad() {
	location.replace(locationAfterPreload)
}

</script>
<body bgcolor="#FFFFFF">

<center>
<font size="2"><%=Constant.getResourceStringValue("hr.user.Please_wait",user1.getLocale())%>...</font><p>
<span id='progressbartable1'>
<img src="<%=request.getContextPath()%>/jsp/images/loading.gif"/>
</span>
<script language="JavaScript">

document.getElementById('progressbartable1').style.display = 'inline'; 

checkLoad()

</script>
</center>

</body>
