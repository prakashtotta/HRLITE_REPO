<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
%>

<script language="javascript">
function closerefresh(){

	window.opener.location.reload(true);

  window.close();
	
}
</script>
<%
String attachmentdeleted = (String)request.getAttribute("attachmentdeleted");
%>
<%
if(attachmentdeleted != null && attachmentdeleted.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("hr.applicant.attachment.deleted",user1.getLocale())%></font></td>
			<td> <a class="closelink" href="#" onclick="closerefresh()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("hr.applicant.attachment.not.deleted",user1.getLocale())%></font></td>
			<td> <a class="closelink" href="#" onclick="closerefresh()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>
<%}%>