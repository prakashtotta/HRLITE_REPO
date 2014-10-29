	<%@ include file="../common/include.jsp" %>


	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>
	<%@ page import="com.bean.*"%>
	<%@ page import="com.bean.onboard.*"%>

  

	<bean:define id="sform" name="onBoardingForm" type="com.form.OnBoardingForm" />

	<%
	////response.setHeader("Cache-Control", "no-cache");
	//		//response.setHeader("Pragma", "no-cache");
	//		//response.setIntHeader("Expires", 0);
	%>

	
	  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

%>



	
	<html:form action="/onboarding.do?method=scheduleInterview">

<%
Onboarding onboard = sform.getOnboarding();
JobApplicant applicant = sform.getJobapplicant();
JobRequisition requistion = sform.getRequisition();
%>
<table width="100%">
 <tr bgcolor=#f3f3f3>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%> : <b><%=applicant.getFullName()%></b></td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%> : <b><%=applicant.getApplicant_number()%></b></td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%> : <b><%=applicant.getEmail()%></b></td>
		
	</tr>
 <tr bgcolor=#f3f3f3>
		<td><%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%> : <b><%=requistion.getRequisition_number()%> # <%=requistion.getJobreqName()%></b></td>
		<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%> : <b><%=requistion.getJobTitle()%></b></td>
		<td><%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%> : <b><%=requistion.hiringMgrValue%></b></td>
		
	</tr>
 <tr bgcolor=#f3f3f3>
		<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> : <b><%=requistion.organizationValue%></b></td>
		<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> : <b><%=requistion.departmentValue%></b></td>
		<td><%=Constant.getResourceStringValue("admin.ProjectCode.PorjectCode",user1.getLocale())%> : <b><%=requistion.projectcodeValue%></b></td>
		
	</tr>
 <tr bgcolor=#f3f3f3>
		<td>On boarding date : <b><%=DateUtil.convertDateToStringDate(onboard.getOnBoardingDate(),datepattern)%></b></td>
		<td>Status : <b>
		<% if (onboard.getStatus() != null && onboard.getStatus().equals("C")){%>
		<%=Constant.getResourceStringValue("job.common.completed",user1.getLocale())%>
		<%}else{%>
         <%=Constant.getResourceStringValue("job.common.inprogress",user1.getLocale())%>
		 <%}%>
		 </b>
		</td>
		<td>
		</td>
		
	</tr>
</table>
 <table  border="0" width="100%">
 <tr bgcolor=#f3f3f3>
		<td><b></b></td><td><b>Task</b></td><td><b>Description</b></td>
		<td><b>Owner</b></td><td><b>Scheduled date</b></td><td><b>Criteria</b></td>

	</tr>
<%
List tasklist = sform.getOnboardingTaskList();

%>
  
  <%
	 
  String trcolor = "<tr>";
  int k=1;
  for(int i=0;i<tasklist.size();i++){
	  OnBoardingTask task = (OnBoardingTask)tasklist.get(i);
	  if((i%2)==1)trcolor="<tr bgcolor=#f3f3f3>";
  %>
   <input type="hidden" name="taskownerid_<%=task.getOnboardingTaskId()%>" value="<%=task.getAssignedtoUserId()%>"/>
    <input type="hidden" name="taskownerhiddenname_<%=task.getOnboardingTaskId()%>" />
       <%=trcolor%>
		<td>
		<% if(task.getStatus() != null && task.getStatus().equals("A")){%>
		<a href="#" onClick="deleteonboardingtask('<%=task.getOnboardingTaskId()%>','<%=task.getTaskuuid()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete" title="delete" height="20"  width="19"/>
		<%}else{%>
		<img src="jsp/images/tick.png" border="0" alt="task completed" title="task completed" height="20"  width="19"/>
		<%}%>
		</td><td><b><%=task.getTaskName()%></b></td><td><%=task.getTaskDesc()%></td>
		<td>
       <%if(!StringUtils.isNullOrEmpty(task.getIsGroup()) && task.getIsGroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=task.getAssignedtoUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=task.getAssignedtoUserName()%></a> 
<%}else{%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=task.getAssignedtoUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=task.getAssignedtoUserName()%></a> 
<%}%>

 </td><td><%=DateUtil.convertDateToStringDate(task.getEventDate(),datepattern)%></td><td><%=task.getNoofdays()%> days <%=task.getEventType()%> On boarding date</td>
     </tr>

     
     <%
      List attributes = task.getAttributeValues();
	 if(attributes != null && attributes.size()>0){

	  %>
      <tr><td></td><td></td><td></td><td></td><td></td><td>
	  <table>
	  <tr bgcolor=#f3f3f3><td>Attribute</td><td>Value</td></tr>
	  <% for(int p=0;p<attributes.size();p++){
		  OnBoardingTaskAttributeValues attr = (OnBoardingTaskAttributeValues)attributes.get(p);
	   %>
	  <tr><td><%=attr.getAttribute()%><%=(attr.getIsMandatory() != null && attr.getIsMandatory().equals("Y"))?"<font color=red>*</red>":""%></td><td><%=(attr.getAttributeValue()==null)?"":attr.getAttributeValue()%></td></tr>
      
	  <%
		  } // end for loop
	  %>
	  </table>
	  </td></tr>
	 <%}//end of null
	 %>
	 <tr><td></td><td></td><td></td><td></td><td></td><td>
	 <%if(!StringUtils.isNullOrEmpty(task.getTaskOwnerComment())){%>
	  <table>
	  <tr bgcolor=#f3f3f3>
	  <td>
	  [ <%=(task.getTaskOwnerComment()==null)?"":task.getTaskOwnerComment()%> ]
	  </td></tr>
	  </table>
	  <%}%>
	  </td></tr>

<%
	k++;
  }
%>

	</html:form>
<script language="javascript">
	window.print();
	</script>
	