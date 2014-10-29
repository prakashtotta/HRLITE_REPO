<%@ include file="../common/include.jsp" %>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />
<%
System.out.println("variabletype in form : "+bForm.getVariableType());
%>
<html:form action="/businessrule.do?method=loadCriterias" >

<span id ="criteralist">
<html:select  property="variableCriteria" onchange="showFilterTextBox(document.businessRuleForm.variableCriteria.value,document.businessRuleForm.variableName.value,document.businessRuleForm.variableType.value);">
				
				<bean:define name="businessRuleForm" property="filterCriteriasList" id="filterCriteriasList" />
	
	            <html:options collection="filterCriteriasList" property="key"  labelProperty="value"/>

			
			</html:select>
			<html:hidden property="variableType"/>		
</span>
</html:form>
</html:html>


