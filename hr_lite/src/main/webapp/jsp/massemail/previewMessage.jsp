<%@ include file="../common/include.jsp" %>
<%

User user1 = (User)session.getAttribute(Common.USER_DATA);

String emailtemplateid=(String)request.getAttribute("emailtemplateid");
System.out.println("emailtemplateid"+emailtemplateid);

//previewmesssage="<p><br>Dear ddd##applicant_name##,</p><p>##comment##<br><br>##requisition_name##</p>You can login and check details using below url<br><br>##applicant_login_url##<br><br>Regards,<br><br>##my_first_name## ##my_last_name##<br><br>Phone : ##my_office_phone##<br><br>Email : ##my_email_id##<sub style='font-family: yui-tmp;'><br><br></sub> ##organization_name##<br><br><a href='jsp/images/logo.gif' target='_blank'>Talent Acquisition System</a>";
%>
<html>

<head>
<title><%=Constant.getResourceStringValue("user.massemail.previewMessage",user1.getLocale())%></title>
</head>

<body>
<div class="div">
<br>
<b><%=Constant.getResourceStringValue("user.massemail.previewMessage",user1.getLocale())%></b>
<br><br>
<%
EmailTemplates emptl =BOFactory.getLovBO().getEmailTemplateDetails(emailtemplateid);
%>
<%=emptl.getEmailtemplateData() %>
<br><br><br><br>
</div>
</body>
</html>
