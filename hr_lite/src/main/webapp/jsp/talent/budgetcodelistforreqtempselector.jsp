<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/lov.do?method=loadProjectCode" >

<span id="budgetcodescodes">
			<html:select property="budgetId" >
			<option value=""></option>
			<bean:define name="lovForm" property="budgetCodeList" id="budgetCodeList" />

            <html:options collection="budgetCodeList" property="budgetId"  labelProperty="budgetCode"/>
			</html:select>
	      </span>
	      
	      

</html:form>
</html:html>