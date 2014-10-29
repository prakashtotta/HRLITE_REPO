<%@ include file="../common/include.jsp" %>
<%@ page import="com.dao.*"%>
<%@ page import="com.bean.Locale;"%>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<br><br><br>
<div align="center" class="div">
Dear <%=aform.getFullName()%>,<br>

Thank you for submitting your job application.  Your application number is : <b><%=aform.getApplicant_number()%> </b> for further communication. <br>
Your skills and qualifications are currently under review. <br>
If further information or action is required of you, a representative from our Recruitment Team will contact you.<br>

Thank you,<br>

Recruitment Team <br>

<br>
<a class="closelink" href="javascript:window.close()">close window</a>
</div>