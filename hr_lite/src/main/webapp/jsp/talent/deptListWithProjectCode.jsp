<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/user.do?method=logon" >

<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('user.do?method=loadProjectCode');">
			<option value=""></option>
			<bean:define name="createUserForm" property="departmentList" id="departmentList" />
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
</span>
</html:form>
</html:html>


