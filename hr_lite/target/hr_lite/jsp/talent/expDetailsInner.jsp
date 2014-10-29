<%@include file="/jsp/common/tooltip.jsp" %>


<span id="prevorgdetails">

<%
	List errorList_workexp = (List)request.getAttribute(Common.ERROR_LIST_WORK_EXP);
	if(errorList_workexp != null && errorList_workexp.size()>0){%>
<div align="center">
	<table border="0" width="100%">
	
<% for(int i=0;i<errorList_workexp.size();i++){
	String errorval = (String)errorList_workexp.get(i);
%>	
	        <tr>
			<td><font color="red"><li><%=errorval%></font></td>
			<td></td>
			</tr>
		
<%}%>
		
	</table>
</div>
<%}%>
    
<%
List orgList = aform.getPreviousOrgList();

%>
<% if(orgList != null && orgList.size()>0){%>


<table  border="0" width="100%">

<%	
for(int i=0;i<orgList.size();i++){
	PreviousOrgDetails edu = (PreviousOrgDetails)orgList.get(i);
	String bgcolor="";
	if(i%2==0){
		
		bgcolor="bgcolor=#DCD9D1";
	}else{
		bgcolor="bgcolor=#f3f3f3";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- org details -->
<table valign="top" border="0" width="100%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getPrevOrgDetailsId()+"&action=experience_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid();
%>
    <tr>
	
	<td width="20%"><%=Constant.getResourceStringValue("hr.applicant.Employer",locale)%>
    <td width="20%"><%=Constant.getResourceStringValue("hr.applicant.Job_Title",locale)%>
    <td width="20%"><%=Constant.getResourceStringValue("hr.applicant.City",locale)%> 
    <td width="20%"><%=Constant.getResourceStringValue("hr.applicant.Start_date",locale)%></td>
    <td width="20%"><%=Constant.getResourceStringValue("hr.applicant.End_date",locale)%> </td>
    <td width="125px"><a href="#" onClick="addattachmentscrprevexp('<%=attachurl%>');return false;"><%=Constant.getResourceStringValue("hr.applicant.addsupportingdocument",locale)%></a></td>
   
    </tr>
	<tr>
	

	 
	<td>
		<a href="#"  onMouseover="ddrivetip('<%=Constant.getResourceStringValue("hr.applicant.Country",locale)%> :<%=(edu.getCountry()!=null)?edu.getCountry().getCountryName():""%><br><%=Constant.getResourceStringValue("hr.applicant.State",locale)%> :<%=(edu.getState()!=null)?edu.getState().getStateName():""%><br><%=Constant.getResourceStringValue("hr.applicant.Last_Salary_Drawn",locale)%> : <%=edu.getLastSalary()%><br><%=Constant.getResourceStringValue("hr.applicant.Bonus",locale)%> : <%=edu.getBonus()%><br><%=Constant.getResourceStringValue("hr.applicant.Currency_Type",locale)%> :<%=(edu.getCurrency()!=null)?edu.getCurrency().getCurrencyName():""%><br><%=Constant.getResourceStringValue("hr.applicant.Responsibilities",locale)%> :<%=(edu.getResponsibilities()==null)?"":StringUtils.doSpecialCharacters(edu.getResponsibilities())%><br><%=Constant.getResourceStringValue("hr.applicant.Employer_Contact_Name",locale)%> : <%=edu.getEmployercontactName()%><br><%=Constant.getResourceStringValue("hr.applicant.Employer_Contact_Telephone",locale)%> : <%=edu.getEmployercontactPhone()%><br><%=Constant.getResourceStringValue("hr.applicant.Reporting_Manager",locale)%> : <%=edu.getReportingToName()%><br><%=Constant.getResourceStringValue("hr.applicant.Reason_For_Leaving",locale)%> : <%=edu.getReasonforleave()%>','yellow', 300)";
 onMouseout="hideddrivetip()"><%=edu.getPrevOrgName()== null?"": edu.getPrevOrgName()%></a>
	</td>

	 <td> <%=edu.getRole()== null?"": edu.getRole()%></td>
	 <td> <%=edu.getCity()== null?"": edu.getCity()%></td>
	 <td> <%=edu.getStartdate()== null?"": edu.getStartdate()%></td>
	 <td> <%=edu.getEnddate()== null?"": edu.getEnddate()%></td>
	</tr>

<!--  	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Country",locale)%> :</td><td width="60%" align="left"> <%=(edu.getCountry()!=null)?edu.getCountry().getCountryName():""%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.State",locale)%> :</td><td width="60%" align="left"> <%=(edu.getState()!=null)?edu.getState().getStateName():""%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Start_date",locale)%> :</td><td width="60%" align="left"> <%=edu.getStartdate()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.End_date",locale)%> :</td><td width="60%" align="left"> <%=edu.getEnddate()%></td>
   </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Last_Salary_Drawn",locale)%> :</td><td width="60%" align="left"> <%=edu.getLastSalary()%></td>
   </tr>
 	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Bonus",locale)%> :</td><td width="60%" align="left"> <%=edu.getBonus()%></td>
   </tr>
    <tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Currency_Type",locale)%> :</td><td width="60%" align="left"> 
	<%=(edu.getCurrency()!=null)?edu.getCurrency().getCurrencyName():""%></td>
   </tr>
     <tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Responsibilities",locale)%> :</td><td width="60%" align="left">
	<%=(edu.getResponsibilities()==null)?"":StringUtils.doSpecialCharacters(edu.getResponsibilities())%>
	</td>
   </tr>
     <tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Employer_Contact_Name",locale)%> :</td><td width="60%" align="left"> <%=edu.getEmployercontactName()%></td>
   </tr>
     <tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Employer_Contact_Telephone",locale)%> :</td><td width="60%" align="left"> <%=edu.getEmployercontactPhone()%></td>
   </tr>

  
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Reporting_Manager",locale)%> :</td><td width="60%" align="left"> <%=edu.getReportingToName()%></td>
	</tr>


	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("hr.applicant.Reason_For_Leaving",locale)%> :</td><td width="60%" align="left"> <%=edu.getReasonforleave()%></td>

	</tr>
-->
 </table>
 <!-- org details -->
 <!-- attachements --><br>
 <table valign="top" border="0" width="30%">



<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("experience_details_proof_attachment")){
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname=experience_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid()+"&uuid="+actionattach.getUuid();

%>

<tr>

<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachmentNew('<%=dleteurl%>');return false;"><img src="jsp/images/delete1.png" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("Requisition.delete.attachment",locale)%>" height="10"  width="9"/></a>
</td>
</tr>
<%
		}
	}%>

<%}%>

 </table>
 <!-- attachements -->
</td>
<td>
<!-- delete -->
<table valign="top" border="0" width="10%">
<tr>
<td><a href="#" onClick="deletePreviousOrgNew('<%=edu.getPrevOrgDetailsId()%>');return false;"><img src="jsp/images/delete1.png" border="0" alt="delete work experience details" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_workExperience",locale)%>" height="20"  width="19"/></a></td>
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
<%=Constant.getResourceStringValue("aquisition.applicant.ExpDetailsNotAddMsg",locale)%>
<%}%>


   
	</span>