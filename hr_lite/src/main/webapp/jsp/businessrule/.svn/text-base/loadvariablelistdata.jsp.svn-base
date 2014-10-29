<%@ include file="../common/include.jsp" %>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />
<%
String variablecode =(String)request.getParameter("variablecode");
System.out.println("variabletype in form : "+bForm.getVariableType());
System.out.println("size of list  : "+bForm.getVaribalesListdata().size());
%>
<html:form action="/businessrule.do?method=loadCriterias" >

<span id ="variablelistdata">



					<html:select  property="variableListDataId">
					<option value=""></option>
					<bean:define name="businessRuleForm" property="varibalesListdata" id="varibalesListdata" />
		
		            <html:options collection="varibalesListdata" property="variableListDataId"  labelProperty="variableValue"/>
					</html:select>


</span>
</html:form>
</html:html>


