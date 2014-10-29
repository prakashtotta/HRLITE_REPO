<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />

<html:form action="/agjob.do?method=logon" >

			<td>
			<span id="departments">
			<html:select property="departmentId" styleClass="list">
			
			<bean:define name="jobRequisitionForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			</td>
</html:form>
</html:html>


