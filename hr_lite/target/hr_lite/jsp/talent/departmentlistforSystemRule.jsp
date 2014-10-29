<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/sysrule.do?method=sdfdf" >

<span id="departments">
			<html:select property="deptId" onchange="setdepartmentvalue();">
			<option value="0"></option>
			<bean:define name="systemRuleForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
	        </span>	
</html:form>
</html:html>


