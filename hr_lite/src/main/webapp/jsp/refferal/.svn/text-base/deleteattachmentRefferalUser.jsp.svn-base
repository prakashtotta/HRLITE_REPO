<%@ include file="../common/include.jsp" %>
<%@ page import="com.dao.*"%>
<%@ page import="com.bean.Locale;"%>
<%
String path = (String)request.getAttribute("filePath");
String refuserid = (String)request.getAttribute("refuserid");
//ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
RefferalEmployee refEmp = RefferalDAO.getRefferalEmployee(refuserid);
Locale locale= refEmp.getLocale();
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
			<td><font color="green"><%=Constant.getResourceStringValue("hr.applicant.attachment.deleted",locale)%></font></td>
			<td> <a href="#" onclick="closerefresh()"><%=Constant.getResourceStringValue("hr.button.close_window",locale)%></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("hr.applicant.attachment.not.deleted",locale)%></font></td>
			<td> <a href="#" onclick="closerefresh()"><%=Constant.getResourceStringValue("hr.button.close_window",locale)%></a></td>
		</tr>
		
	</table>
</div>
<%}%>