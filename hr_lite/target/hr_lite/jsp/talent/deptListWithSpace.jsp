<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/lov.do?method=loadDepartmentsWithOutProjectCodeAndSpace" >

			<td>
			<span id="departments">
			<html:select property="deptId" onchange="setdeptvalue(this);">
			<option value=""></option>
			<bean:define name="lovForm" property="departmentList" id="departmentList" />
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
			</td>
</html:form>
</html:html>


