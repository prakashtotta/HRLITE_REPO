<%
User usercommon = (User)request.getSession().getAttribute(Common.USER_DATA);
String issessioncommon = "true";
if(usercommon == null){
	issessioncommon="false";
}
%>

	<script language="javascript">
	alert("hi");
var issesionactivecommon = "<%=issessioncommon%>";
function sessionCheck(){
if(issesionactivecommon == "false"){
	
	window.opener.document.location.href=window.opener.document.location.href;
	window.close();
}
}
</script>