

<fieldset>
<div class="msg"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Education_details",user1.getLocale())%>  </div>
<table border="0" width="100%">
<%
List eduList = form.getEducationsList();

%>
<% if(eduList != null && eduList.size()>0){%>
    <tr bgcolor="#B2D2FF">
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Starting_year",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></b></td>
	</tr>




<%	
for(int i=0;i<eduList.size();i++){
	EducationDetails edu = (EducationDetails)eduList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>
<tr <%=bgcolor%> >
<td><%=edu.getEducationName()%></td><td><%=edu.getSpecialization()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td>
<td><%=(edu.getStartingYear()==null)?"":edu.getStartingYear()%></td><td><%=edu.getPassingYear()%></td>

<td>
<%
List appattachmentList1 = edu.getAttachmentList();

if(appattachmentList1 != null && appattachmentList1.size()>0){
	for(int j=0;j<appattachmentList1.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList1.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("education_details_proof_attachment")){
	%>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a><br>
<%
}
}
}%>
</td>

</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.education_details.no.added",user1.getLocale())%>
<%}%>

    </table>

</fieldset>
<br>
<fieldset>
<div class="msg"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Certifications_details",user1.getLocale())%>  </div>
<table border="0" width="100%">
<%
List certList = form.getCertificationsList();

%>
<% if(certList != null && certList.size()>0){%>
     <tr bgcolor="#B2D2FF">
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Certified_Organization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></b></td><td></td>
	</tr>




<%	
for(int i=0;i<certList.size();i++){
	EducationDetails edu = (EducationDetails)certList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>
<tr <%=bgcolor%> >
<td><%=edu.getEducationName()%></td><td><%=edu.getInstituteName()%></td><td><%=edu.getPercentile()%></td><td><%=edu.getPassingYear()%></td>
<TD>
<%
List appattachmentList1 = edu.getAttachmentList();

if(appattachmentList1 != null && appattachmentList1.size()>0){
	for(int j=0;j<appattachmentList1.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList1.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("certification_details_proof_attachment")){
	%>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a><br>
<%
}
}
}%>
</TD>
</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Certifications_not_added",user1.getLocale())%>
<%}%>

    </table>

</fieldset>
<br>
<fieldset>
<div class="msg"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Experience_details",user1.getLocale())%>  </div>
 <table border="0" width="100%">
<%
List orgList = form.getPreviousOrgList();

%>
<% if(orgList != null && orgList.size()>0){%>
     <tr bgcolor="#B2D2FF">
	
	<td><b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></b></td>
	
	</tr>



<%	
for(int i=0;i<orgList.size();i++){
	PreviousOrgDetails edu = (PreviousOrgDetails)orgList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>
<tr <%=bgcolor%> >

<td><%=edu.getPrevOrgName()%></td><td><%=edu.getRole()%></td><td><%=edu.getReportingToName()%></td><td><%=edu.getStartdate()%></td><td><%=edu.getEnddate()%></td><td><%=edu.getReasonforleave()%></td>
<td>
<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("experience_details_proof_attachment")){
	%>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a><br>
<%
}
}
}%>
</td>

</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.ExpDetailsNotAddMsg",user1.getLocale())%>

<%}%>

    </table>
</fieldset>
<br>
<fieldset>
<div class="msg"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Skills",user1.getLocale())%> </div>
 <table border="0" width="100%">
<%
List skillList = form.getApplicantSkillList();

%>
<% if(skillList != null && skillList.size()>0){%>
     <tr bgcolor="#B2D2FF">
	
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
<%=Constant.getResourceStringValue("aquisition.applicant.Skills_not_added",user1.getLocale())%>.
<%}%>

    </table>
</fieldset>


