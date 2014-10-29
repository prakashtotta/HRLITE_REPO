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

%>

<html:form action="/applicant.do?method=getpreviousOrgdetails" >

<span id="prevorgdetails">
    
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
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- org details -->
<table valign="top" border="0" width="60%">
    <tr>
	
	<td width="40%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPrevOrgName()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getRole()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getReportingToName()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getStartdate()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getEnddate()%></td>
   </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getReasonforleave()%></td>
	
	</tr>

 </table>
 <!-- org details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getPrevOrgDetailsId()+"&action=experience_details_proof_attachment&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
%>
<tr>

<td width="125px"><a href="#" onClick="addattachmentscr('<%=attachurl%>')">add attachment</a></td>
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
		if(actionattach.getAction() != null && actionattach.getAction().equals("experience_details_proof_attachment")){
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname=experience_details_proof_attachment&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid()+"&uuid="+actionattach.getUuid();

%>
<tr>
<td width="125px"></td>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
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
<td><a href="#" onClick="deletePreviousOrg('<%=edu.getPrevOrgDetailsId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete organization" 
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
System.out.println("orgList : "+orgList);
%>
<%=Constant.getResourceStringValue("aquisition.applicant.ExpDetailsNotAddMsg",user1.getLocale())%>
<%}%>

   
	</span>

</html:form>
</html:html>


