<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
String datepattern = Constant.getValue("defaultdateformat");

com.bean.Locale locale=null;
com.bean.Timezone timezone = null;


User userAg = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
User user = (User)request.getSession().getAttribute(Common.USER_DATA);
RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

if(userAg != null){

datepattern = DateUtil.getDatePatternFormat(userAg.getLocale());
locale = userAg.getLocale();
timezone = userAg.getTimezone();
}else if(user != null){
	datepattern = DateUtil.getDatePatternFormat(user.getLocale());
    locale = user.getLocale();
	timezone = user.getTimezone();
}else if(userEmp != null){
	datepattern = DateUtil.getDatePatternFormat(userEmp.getLocale());
	locale = userEmp.getLocale();
    timezone = userEmp.getTimezone();
}else if(appuser != null){
	datepattern = DateUtil.getDatePatternFormat(appuser.getLocale());
	locale = appuser.getLocale();
    timezone = appuser.getTimezone();
}else{
String refuserid = (String)session.getAttribute("refuserid");
RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
locale= refEmp.getLocale();
timezone = refEmp.getTimezone();

}

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
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("hr.applicant.attachment.deleted",locale)%></font></td>
			<td>  <a href="#" onclick="closerefresh()"><%=Constant.getResourceStringValue("hr.button.close_window",locale)%></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("hr.applicant.attachment.not.deleted",locale)%></font></td>
			<td> <a href="#" onclick="closerefresh()"><%=Constant.getResourceStringValue("hr.button.close_window",locale)%></a> </td>
		</tr>
		
	</table>
</div>
<%}%>