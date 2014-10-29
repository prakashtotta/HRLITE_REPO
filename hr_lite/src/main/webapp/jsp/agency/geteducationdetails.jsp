<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
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

<%if(edutype != null && edutype.equals(Common.EDUCATION_COLLEGE)){%>

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
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- education  details -->
<table valign="top" border="0" width="100%">
    <tr valign=top>
	
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Starting_year",user1.getLocale())%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></td>
	<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
    </tr>
	<tr valign=top>
	<td align="left"><input type="hidden" name="eduName" value="<%=edu.getEducationName()%>"/> <%=edu.getEducationName()%></td>
	<td align="left"> <%=edu.getSpecialization()%></td>
	<td align="left"> <%=edu.getInstituteName()%></td>
	<td align="left"> <%=edu.getPercentile()%></td>
	<td align="left"> <%=edu.getStartingYear()%></td>
	<td align="left"> <%=edu.getPassingYear()%></td>
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
	<table cellspacing="5">

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
<a href="#" onClick="deleteAttachment('<%=dleteurl%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="10"  width="9"/></a>
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
<td><a href="#" onClick="deleteeducation('<%=edu.getEducationId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete education" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="20"  width="19"/></a></td>
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
<%=Constant.getResourceStringValue("aquisition.applicant.Educations_not_added",user1.getLocale())%>
<%}%>

   
	</span>
<%} // for education
	%>

<%if(edutype != null && edutype.equals(Common.EDUCATION_CERTIFICATION)){%>

<span id="certifications">
    
<%
List certList = aform.getCertificationsList();

%>
<% if(certList != null && certList.size()>0){%>


<table  border="0" width="100%">

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
<td>
<!-- certification details -->
<table valign="top" border="0" width="100%">
    <tr>
	
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Certified_Organization",user1.getLocale())%></td>
	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></td>
   	<td ><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></td>
    </tr>
	<tr>
	<td align="left"> <%=edu.getEducationName()%></td>
	<td align="left"> <%=edu.getInstituteName()%></td>
	<td align="left"> <%=edu.getPercentile()%></td>
	<td align="left"> <%=edu.getPassingYear()%></td>
    </tr>
	
	

 </table>
 <!-- certificaitons details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getEducationId()+"&action=certification_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid();
%>
<tr>

<td width="125px"><a href="#" onClick="addattachmentscr('<%=attachurl%>');return false">add attachment</a></td>
<td></td>
<td></td>
</tr>


<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("certification_details_proof_attachment")){
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname=certification_details_proof_attachment&applicantId="+aform.getApplicantId()+"&secureid="+aform.getUuid()+"&uuid="+actionattach.getUuid();

%>
<tr>
<td width="125px"></td>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="10"  width="9"/></a>
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
<td><a href="#" onClick="deletecertification('<%=edu.getEducationId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("aquisition.applicant.delete_certification",user1.getLocale())%>" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_education",user1.getLocale())%>" height="20"  width="19"/></a></td>
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
<%=Constant.getResourceStringValue("aquisition.applicant.Certifications_not_added",user1.getLocale())%>
<%}%>

   
	</span>
<%} // for certification
	%>

</html:form>
</html:html>


