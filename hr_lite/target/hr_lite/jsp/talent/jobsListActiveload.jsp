<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/applicant.do?method=loadJobsListActive" >

		<span id="jobreqlist">
			<html:select  property="requitionId">
			<option value=""></option>
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />

            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>

	      </span>







</html:form>
</html:html>