<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/jobreq.do?method=logon" >

		<span id="projectcodes">
			<html:select property="projectcodeId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
</html:form>
</html:html>


