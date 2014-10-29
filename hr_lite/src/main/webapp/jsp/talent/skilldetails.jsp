<span id="skilldetails">
    <table border="0" width="100%">

<%

List skillList = aform.getApplicantSkillList();


%>
<% if(skillList != null && skillList.size()>0){%>
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Skill",locale)%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",locale)%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Rating",locale)%></b></td><td></td>
	
	</tr>



	
<%for(int i=0;i<skillList.size();i++){
	ApplicantSkills skil = (ApplicantSkills)skillList.get(i);
%>
<tr>

<td><input type="hidden" name="skilName" value="<%=skil.getSkillname()%>"/><%=skil.getSkillname()%></td><td><%=skil.getYearsofexp()%></td><td><%=skil.getRating()%></td><td><a href="#" onClick="deleteskill('<%=skil.getUuid()%>');return false"><img src="jsp/images/delete1.png" border="0" alt="delete skill" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_skill",locale)%>" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Skills_not_added",locale)%>
<%}%>

    </table>
	</span>