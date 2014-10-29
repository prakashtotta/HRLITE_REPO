<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function requisionFormMap(){
	var url = "<%=request.getContextPath()%>/variablemap.do?method=requisionFormMap&formcode=REQUISITION_FORM&formname=REQUISITION FORM";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.variable.mapping.editformmapping",user1.getLocale())%>',url,500,750, messageret);
}

function applicantFormMap(){
	var url = "<%=request.getContextPath()%>/variablemap.do?method=requisionFormMap&formcode=APPLICANT_FORM&formname=APPLICANT FORM";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.variable.mapping.editformmapping",user1.getLocale())%>',url,500,750, messageret);
}

function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
	window.location.reload();

			}

</script>

<body class="yui-skin-sam">
<br>
<br><br>

<a class="button" href="#" onClick="requisionFormMap()"><%=Constant.getResourceStringValue("admin.variable.mapping.requisionformmap",user1.getLocale())%></a>

<br>





</body>
</html>
