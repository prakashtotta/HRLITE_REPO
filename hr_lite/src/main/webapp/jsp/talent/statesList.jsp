<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>

<%
System.out.println("..........test");
%>

<html:form action="/location.do?method=loadState" >

<span id ="states">
	<html:select  property="stateId">
	<option value=""></option>
			<bean:define name="locationForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			
</span>

</html:form>
<%
System.out.println(" ********** statesList.jsp  ********* ");
%>
</html:html>