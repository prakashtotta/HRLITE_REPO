<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/user.do?method=logon" >

			<td>
			<span id="departments">
			<html:select property="departmentId" >
			<option value="0"></option>
			<bean:define name="projectCodesForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
			</span>
		
			</td>
</html:form>
</html:html>


