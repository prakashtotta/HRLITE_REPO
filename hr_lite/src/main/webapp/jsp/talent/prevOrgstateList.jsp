<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>

<%
System.out.println(".... test .....");
%>

<html:form action="/applicant.do?method=loadState" >


			<span id="statesPrevOrg">
			<html:select  property="state_id_prevorg">
			<option value=""></option>
			<bean:define name="applicantForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>
			</span>
</html:form>
</html:html>


