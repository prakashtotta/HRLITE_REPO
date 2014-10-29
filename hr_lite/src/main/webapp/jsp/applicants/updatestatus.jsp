<%@ include file="../common/yahooincludes.jsp" %>
<bean:define id="cform" name="applicantUserForm" type="com.form.ApplicantUserForm" />
<%
String status="";
if(cform.getStatus().equals("I")){
	status="InActive";
}else if(cform.getStatus().equals("A")){
	status="Active";
}
%>
<%=status%>
