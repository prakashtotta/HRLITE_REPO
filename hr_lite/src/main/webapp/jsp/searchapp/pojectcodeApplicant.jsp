<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/applicant.do?method=loadProjectCode" >
	        <td>
			<span id="projectcodes">
			<html:select property="projectcodeIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrievejobreqlist();">
			<bean:define name="applicantForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
			</td>
	        
			</td>
</html:form>
</html:html>


