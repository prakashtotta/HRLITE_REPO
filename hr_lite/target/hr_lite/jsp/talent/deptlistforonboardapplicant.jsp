<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/applicant.do?method=logon" >

<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('applicant.do?method=loadProjectCode');">
			<option value=""></option>
			<bean:define name="applicantForm" property="departmentList" id="departmentList" />
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
</span>
</html:form>
</html:html>