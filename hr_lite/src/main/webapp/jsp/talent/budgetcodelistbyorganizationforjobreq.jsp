<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/jobreq.do?method=logon" >

<span id="budgetcodescodes">
			<html:select  property="budgetcodeId" styleClass="list">
			<option value=""></option>			
			<bean:define name="jobRequisitionForm" property="budgetcodeList" id="budgetcodeList" />
            <html:options collection="budgetcodeList" property="budgetId"  labelProperty="budgetCode"/>
			</html:select>
	      </span>

</html:form>
</html:html>