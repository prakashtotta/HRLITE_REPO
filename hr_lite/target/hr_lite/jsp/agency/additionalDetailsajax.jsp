<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<div class="div"><legend><%=Constant.getResourceStringValue("aquisition.applicant.Education_details",user1.getLocale())%></legend>
<table border="0" width="100%">
<%
List eduList = form.getEducationsList();

%>
<% if(eduList != null && eduList.size()>0){%>
    <tr>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></b></td><td></td>
	</tr>




<%	
for(int i=0;i<eduList.size();i++){
	EducationDetails edu = (EducationDetails)eduList.get(i);
%>
<tr>
<td><%=edu.getEducationName()%></td><td><%=edu.getSpecialization()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td><td><%=edu.getPassingYear()%></td>
</tr>
<%
}
}else{%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.Educations_not_added",user1.getLocale())%></b>
<%}%>

    </table>

</div>
	<div class="div"><legend><%=Constant.getResourceStringValue("aquisition.applicant.Certifications_details",user1.getLocale())%></legend>
<table border="0" width="100%">
<%
List certList = form.getCertificationsList();

%>
<% if(certList != null && certList.size()>0){%>
    <tr>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Certified_Organization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></b></td><td></td>
	</tr>




<%	
for(int i=0;i<certList.size();i++){
	EducationDetails edu = (EducationDetails)certList.get(i);
%>
<tr>
<td><%=edu.getEducationName()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td><td><%=edu.getPassingYear()%></td>
</tr>
<%
}
}else{%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.Certifications_not_added",user1.getLocale())%></b>
<%}%>

    </table>

</div>

<div class="div"><legend><%=Constant.getResourceStringValue("aquisition.applicant.Experience_details",user1.getLocale())%></legend>
 <table border="0" width="100%">
<%
List orgList = form.getPreviousOrgList();

%>
<% if(orgList != null && orgList.size()>0){%>
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",user1.getLocale())%></b></td><td></td>
	
	</tr>



<%	
for(int i=0;i<orgList.size();i++){
	PreviousOrgDetails edu = (PreviousOrgDetails)orgList.get(i);
%>
<tr>

<td><%=edu.getPrevOrgName()%></td><td><%=edu.getRole()%></td><td><%=edu.getReportingToName()%></td<td><%=edu.getStartdate()%></td><td><%=edu.getEnddate()%></td><td><%=edu.getReasonforleave()%></td>

</tr>
<%
}
}else{%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.ExpDetailsNotAddMsg",user1.getLocale())%>
<%}%>

    </table>
</div>
<div class="div"><legend><%=Constant.getResourceStringValue("aquisition.applicant.Skills",user1.getLocale())%></legend>
 <table border="0" width="100%">
<%
List skillList = form.getApplicantSkillList();

%>
<% if(skillList != null && skillList.size()>0){%>
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Rating",user1.getLocale())%></b></td><td></td>
	
	</tr>



<%	
for(int i=0;i<skillList.size();i++){
	ApplicantSkills skil = (ApplicantSkills)skillList.get(i);
%>
<tr>

<td><%=skil.getSkillname()%></td><td><%=skil.getYearsofexp()%></td><td><%=skil.getRating()%></td>

</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Skills_not_added",user1.getLocale())%>
<%}%>

    </table>
</div>


