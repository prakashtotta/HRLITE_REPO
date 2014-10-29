<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<%
System.out.println(".......... // statesList.jsp  ...... ");
%>

<html:form action="/reflogin.do?method=loadState" >

<span id="states">

	<html:select  property="stateId">

	<option value=""></option>

			<bean:define name="refferalForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>

			</html:select>

			
</span>

</html:form>
</html:html>