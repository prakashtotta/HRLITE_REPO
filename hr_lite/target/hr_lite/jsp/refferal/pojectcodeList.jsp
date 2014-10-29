<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<%
System.out.println("satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
%>
<html:form action="/lovnologin.do?method=logon" >

		<span id="projectcodes">
			<html:select property="projectcodeId" >
			<option value=""></option>
			<bean:define name="lovForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
</html:form>
</html:html>


