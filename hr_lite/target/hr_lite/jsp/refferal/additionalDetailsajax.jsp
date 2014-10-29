<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<div class="div">
<fieldset><legend>Education details</legend>
<table border="0" width="100%">
<%
List eduList = form.getEducationsList();

%>
<% if(eduList != null && eduList.size()>0){%>
    <tr>
	<td><b>Education</b></td><td><b>Specialization</b></td><td><b>College/University</b></td><td><b>Percentage/Grade</b></td><td><b>Passing year</b></td>
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
<b>Educations not added.</b>
<%}%>

    </table>

</fieldset>
</div>
<br>
<div class="div">

	<fieldset><legend>Certifications details</legend>
<table border="0" width="100%">
<%
List certList = form.getCertificationsList();

%>
<% if(certList != null && certList.size()>0){%>
    <tr>
	<td><b>Name</b></td><td><b>Certified Organization</b></td><td><b>Percentage/Grade</b></td><td><b>Passing year</b></td>
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
<b>Certifications not added.</b>
<%}%>

    </table>

</fieldset>
</div>
<br>

<div class="div">

<fieldset><legend>Experience details</legend>
 <table border="0" width="100%">
<%
List orgList = form.getPreviousOrgList();

%>
<% if(orgList != null && orgList.size()>0){%>
    <tr>
	
	<td><b>Organization</b></td><td><b>Last role</b></td><td><b>Reporting Manager</b></td><td><b>Joining date</b></td><td><b>Relieving date</b></td><td><b>Reason for leave</b></td>
	
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
<b>Experience details not added.</b>
<%}%>

    </table>
</fieldset>
</div>
<br>
<div class="div">

<fieldset><legend>Skills details</legend>
 <table border="0" width="100%">
<%
List skillList = form.getApplicantSkillList();

%>
<% if(skillList != null && skillList.size()>0){%>
    <tr>
	
	<td><b>Skill</b></td><td><b>Years of Experience</b></td><td><b>Rating</b></td>
	
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
Skills not added.
<%}%>

    </table>
</fieldset>

</div>
