<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/applicant.do?method=loadActiveJobsListMultiple" >

		<span id="jobreqlist">
			<html:select styleClass="multipleselect" multiple="true" size="3" property="requitionIds">
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />

            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>

	      </span>







</html:form>
</html:html>