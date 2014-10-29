<%@ include file="../common/include.jsp" %>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="questionsform" name="questionForm" type="com.form.QuestionForm" />

<html:form action="/question.do?method=loadCriterias" >

<span id ="criteralist">
<html:hidden property="qnsType"/>	
<% if(questionsform.getQnsType() != null && questionsform.getQnsType().equals("dropdown")){%>

			<html:select  property="answerOption">	
				<bean:define name="questionForm" property="optionList" id="optionList" />	
	            <html:options collection="optionList" property="questionOptValue"  labelProperty="questionOptValue"/>			
			</html:select>
<% }else if(questionsform.getQnsType() != null && questionsform.getQnsType().equals("radio")){%>

			<html:select  property="answerOption">	
				<bean:define name="questionForm" property="optionList" id="optionList" />	
	            <html:options collection="optionList" property="questionOptValue"  labelProperty="questionOptValue"/>			
			</html:select>


<%}else if(questionsform.getQnsType() != null && questionsform.getQnsType().equals("date")){%>
			<html:select  property="filtercri" onchange="showFilterTextBox();">	
			<option value=""></option>
				<bean:define name="questionForm" property="criteriaNumericList" id="criteriaNumericList" />	
	            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>			
			</html:select>
<%}else if(questionsform.getQnsType() != null && questionsform.getQnsType().equals("number")){%>
			<html:select  property="filtercri" onchange="showFilterTextBox();">	
			<option value=""></option>
				<bean:define name="questionForm" property="criteriaNumericList" id="criteriaNumericList" />	
	            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>			
			</html:select>
<%}else{%>
			<html:select  property="filtercri" onchange="showFilterTextBox();">		
			<option value=""></option>
				<bean:define name="questionForm" property="criteriaStringList" id="criteriaStringList" />	
	            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>			
			</html:select>
<%}%>


	
</span>
</html:form>
</html:html>


