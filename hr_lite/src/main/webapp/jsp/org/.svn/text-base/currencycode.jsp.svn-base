<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="orgform" name="organizationForm" type="com.form.OrganizationForm" />

<html:form action="/org.do?method=logon" >

<span id="currencycode">

			<%=orgform.getCurrencyCode()%>
			<html:hidden property="budgetCurrency" value="<%=orgform.getCurrencyCode()%>"/>

</span>
</html:form>
</html:html>


