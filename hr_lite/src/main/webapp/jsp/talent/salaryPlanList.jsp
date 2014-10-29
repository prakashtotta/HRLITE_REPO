<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/jobreq.do?method=retrieveSalaryPlanByOrganization" >

			
		<span id="salaryplans">
			<html:select  property="salaryplanId" styleClass="list">
				<option value=""></option>		
			<bean:define name="jobRequisitionForm" property="salaryplanList" id="salaryplanList" />
            <html:options collection="salaryplanList" property="salaryplanId"  labelProperty="salaryPlanName"/>
			</html:select>
			</span>
			
</html:form>
</html:html>


