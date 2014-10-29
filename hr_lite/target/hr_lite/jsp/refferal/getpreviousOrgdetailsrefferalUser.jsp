<%@ include file="../common/include.jsp" %>
<%@ page import="com.dao.*"%>
<%@ page import="com.bean.Locale;"%>
<%
System.out.println("getpreviousOrgdetailsrefferalUser.jsp");
String applicantid = (String)request.getAttribute("applicantid");
String uuid = (String)request.getAttribute("uuid");
String refuserid = (String)request.getAttribute("refuserid");
System.out.println("applicant Id "+applicantid);
System.out.println("UUId "+uuid);
System.out.println("refuserid "+refuserid);


RefferalEmployee refEmp = RefferalDAO.getRefferalEmployee(refuserid);

Locale locale= refEmp.getLocale();
System.out.println("locale "+locale);
JobApplicant applicant = null;
if(applicantid != null && uuid != null){

	applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantid,uuid);
}


%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
System.out.println("test");

%>

<html:form action="/refferaluser.do?method=getpreviousOrgdetails" >

<span id="prevorgdetails">
    
<%
List orgList = aform.getPreviousOrgList();
System.out.println("orgList size : "+orgList.size());
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
	
	<td width="40%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",locale)%></td><td width="60%" align="left"> <%=edu.getPrevOrgName()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",locale)%></td><td width="60%" align="left"> <%=edu.getRole()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",locale)%></td><td width="60%" align="left"> <%=edu.getReportingToName()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",locale)%></td><td width="60%" align="left"> <%=edu.getStartdate()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",locale)%></td><td width="60%" align="left"> <%=edu.getEnddate()%></td>
   </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",locale)%></td><td width="60%" align="left"> <%=edu.getReasonforleave()%></td>
	
	</tr>

 </table>
 <!-- org details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%

String attachurl = "refferaluser.do?method=actionattachmentaddscr&idvalue="+edu.getPrevOrgDetailsId()+"&action=experience_details_proof_attachment"+"&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid()+"&refuserid="+refuserid;
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
			title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",locale)%>" height="10"  width="9"/></a>
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
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",locale)%>" height="20"  width="19"/></a></td>
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

</html:form>
</html:html>


