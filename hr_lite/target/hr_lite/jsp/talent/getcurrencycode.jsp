<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="jobRequisitionForm" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />

<html:form action="/jobreq.do?method=logon" >

<span id="currectctccurrencycodesp">

			<%=(jobRequisitionForm.getCurrecyCode() == null)?"":jobRequisitionForm.getCurrecyCode()%>
			</span>
</html:form>
</html:html>


