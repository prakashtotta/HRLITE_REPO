<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/jobreq.do?method=loadProjectCode" >

<span id="departments">
			<html:select property="departmentId" styleClass="list" onchange="retrieveProjcodes('jobreq.do?method=loadProjectCode');">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="departmentList" id="departmentList" />
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
</span>
</html:form>
</html:html>


