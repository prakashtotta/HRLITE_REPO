<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
JobApplicant applicant = user1.getApplicant();
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
System.out.println("test");
String edutype = aform.getEduType();
String spaname = "<span id=\"educations\">";
if(edutype != null && edutype.equals(Common.EDUCATION_CERTIFICATION)){
	spaname = "<span id=\"certifications\">";
}
%>

<html:form action="/applicant.do?method=geteducationdetails" >

<%=spaname%>
    <table border="0" width="100%">

    <tr>
	<%if(edutype != null && edutype.equals(Common.EDUCATION_CERTIFICATION)){%>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Certified_Organization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></b></td><td></td>
	<%}else{%>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td><td>
	<b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%> </b></td><td></td>
	<%}%>
	</tr>


<%
List eduList = aform.getEducationsList();
System.out.println("eduList.size()"+eduList.size());
%>
<% if(eduList != null && eduList.size()>0){
	
for(int i=0;i<eduList.size();i++){
	EducationDetails edu = (EducationDetails)eduList.get(i);
%>
<tr>
<%if(edutype != null && edutype.equals(Common.EDUCATION_CERTIFICATION)){%>
<td><%=edu.getEducationName()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td><td><%=edu.getPassingYear()%></td><td><a href="#" onClick="deletecertification('<%=edu.getEducationId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete certification" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_certification",user1.getLocale())%>" height="20"  width="19"/></a></td>
<%}else{%>
<td><%=edu.getEducationName()%></td><td><%=edu.getSpecialization()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td><td><%=edu.getPassingYear()%></td><td><a href="#" onClick="deleteeducation('<%=edu.getEducationId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete education" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_education",user1.getLocale())%>" height="20"  width="19"/></a></td>
<%}%>
</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Educations_not_added",user1.getLocale())%>.
<%}%>

    </table>
	</span>

</html:form>
</html:html>


