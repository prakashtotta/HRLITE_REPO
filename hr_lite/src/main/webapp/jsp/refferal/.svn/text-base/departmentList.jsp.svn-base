<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>

<html:form action="/lovnologin.do?method=logon" >

<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('lovnologin.do?method=loadProjectCode');" styleClass="list">
			<option value=""></option>
			<bean:define name="lovForm" property="departmentList" id="departmentList" />
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
</span>
</html:form>
</html:html>