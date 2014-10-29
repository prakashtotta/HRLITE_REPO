<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="refferalSchemeform" name="refferalSchemeForm" type="com.form.RefferalSchemeForm" />

<html:form action="/refferalscheme.do?method=logon" >

<span id="currencycode">

			<%=refferalSchemeform.getUom()%>
			<html:hidden property="uom" value="<%=refferalSchemeform.getUom()%>"/>
			

</span>
</html:form>
</html:html>


