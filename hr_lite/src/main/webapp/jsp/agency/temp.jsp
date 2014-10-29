<%@ include file="../common/include.jsp" %>

<%
String reurl = (String)request.getParameter("reurl");
System.out.println("rurl"+reurl);
reurl =  request.getContextPath()+"/"+reurl;
%>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<table id='progressbartable1'>
<tr>
    <td></td>
    <td  style="vertical-align:middle"><img src="<%=request.getContextPath()%>/jsp/images/loading.gif"></td>
    <td></td>
</tr>
</table>
<script language="JavaScript">
var locationAfterPreload = "<%=reurl%>";
function checkLoad() {
	location.replace(locationAfterPreload)
}

</script>
<body bgcolor="#FFFFFF">

<center>
<font size="2"><%=Constant.getResourceStringValue("hr.user.Please_wait",user1.getLocale())%>...</font><p>

<script language="JavaScript">

document.getElementById('progressbartable1').style.display = 'inline'; 

checkLoad()

</script>
</center>

</body>
