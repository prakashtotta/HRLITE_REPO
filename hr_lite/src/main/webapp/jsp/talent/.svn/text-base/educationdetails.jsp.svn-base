<span id="educations">
    
<%
List eduList = aform.getEducationsList();

%>
<% if(eduList != null && eduList.size()>0){%>


<table  border="0" width="100%">

<%	
for(int i=0;i<eduList.size();i++){
	EducationDetails edu = (EducationDetails)eduList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#F0F0F0"; 
	}else{
		bgcolor="bgcolor=#F8F8F8";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- education  details -->
<table valign="top" border="0" width="100%">
    <tr valign=top>	
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Education",locale)%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",locale)%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.College_University",locale)%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",locale)%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Starting_year",locale)%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",locale)%></td>
    </tr>
	<tr valign=top>
	<td align="left"><input type="hidden" name="eduName" value="<%=edu.getEducationName()%>"/> <%=edu.getEducationName()%></td>
	<td align="left"> <%=edu.getSpecialization()== null?"": edu.getSpecialization()%></td>
	<td align="left"> <%=edu.getInstituteName() == null?"": edu.getInstituteName()%></td>
	<td align="left"> <%=edu.getPercentile() == null?"": edu.getPercentile()%></td>
	<td align="left"> <%=edu.getStartingYear() == null?"": edu.getStartingYear()%></td>
	<td align="left"> <%=edu.getPassingYear() == null?"": edu.getPassingYear()%></td>
   </tr>
	

 </table>
 <!-- education details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getEducationId()+"&action=education_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid();
%>
<tr>

<td width="100px"><a href="#" onClick="addattachmentscr('<%=attachurl%>');return false">add attachment</a></td>
<td></td>
<td></td>
</tr>


<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5" >

<%
		for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("education_details_proof_attachment")){
	
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname=education_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid()+"&uuid="+actionattach.getUuid();

%>
	<tr>
		<td width="100px"></td>
		<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
		<td>
		<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
		<a href="#" onClick="deleteAttachment('<%=dleteurl%>');return false"><img src="jsp/images/delete1.png" border="0" alt="delete attachment" 
		title="<%=Constant.getResourceStringValue("Requisition.delete.attachment",locale)%>" height="10"  width="9"/></a>
		</td>
	</tr>
<%
		}
	}%>

<%}%>

 </table>


 <!-- attachements -->
 <td>
<!-- delete -->
<table valign="top" border="0" width="10%">
<tr>
<td><a href="#" onClick="deleteeducation('<%=edu.getUuid()%>')"><img src="jsp/images/delete1.png" border="0" alt="delete education" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_education",locale)%>" height="20"  width="19"/></a></td>
 </td>
</tr>
 </table>
<!-- delete end -->
 </td>
 </tr>
<%
}
 %>

 </table>
<%
}else{

%>
<%=Constant.getResourceStringValue("aquisition.applicant.Educations_not_added",locale)%>
<%}%>

   
	</span>