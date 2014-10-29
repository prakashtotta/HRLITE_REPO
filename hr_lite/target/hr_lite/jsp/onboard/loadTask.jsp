	<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>

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
OnBoardingTask task = sform.getOnboardingTask();
JobApplicant applicant = sform.getJobapplicant();
%>

<style>
span1{color:#ff0000;}
</style>
 
<script language="javascript">

	function closewindow(){
		//self.parent.location.reload();
	  parent.parent.GB_hide();
}



	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
			 
		   parent.parent.GB_hide();
		   } 
		}

	function savedata(){

		 document.onBoardingForm.action = "onboarding.do?method=submitOnboardingTask&taskid=<%=task.getOnboardingTaskId()%>&secureid=<%=task.getTaskuuid()%>";
         document.onBoardingForm.submit();
	   
		}

</script>  
<%
String submittedOBoardingTask = (String)request.getAttribute("submittedOBoardingTask");
String mandatoryValuesNotAdded 	= (String)request.getAttribute("mandatoryValuesNotAdded");
if(submittedOBoardingTask != null && submittedOBoardingTask.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.task.completed",user1.getLocale())%></td>
			<td><!--  <a href="#" onClick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<%
if(mandatoryValuesNotAdded != null && mandatoryValuesNotAdded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("validation.add.mandatory.values",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>

<table width="100%">
<tr>
<td>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.applicant.details",user1.getLocale())%></legend>
<table width="100%">
<tr>
			<td width="10%" class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.configutaion.fullName",user1.getLocale())%> : </td>
			<td width="30%" class="bodytext">
			<a href="applicant.do?method=applicantDetails&applicantId=<%=applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>" target="new"><%=applicant.getFullName()%></a></td>
			<td width="20%"></td>
			<td width="10%" class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant_portal.Job_Title",user1.getLocale())%> : </td>
			<td width="30%" class="bodytext"><%=StringUtils.isNullOrEmpty(applicant.getJobTitle())?"":applicant.getJobTitle()%></td> 
		</tr>


</table>
</fieldset>
</td>
</tr>
<tr>
<td>

<fieldset><legend><%=Constant.getResourceStringValue("task.graybox.taskdetails",user1.getLocale())%></legend>

	<body class="yui-skin-sam">
	<html:form action="/onboarding.do?method=submitOnboardingTask">
 <table align="left" width="100%">
 <tr bgcolor=#f3f3f3>
		<td><b>Task</b></td><td><b>Description</b></td><td><b>Owner</b></td><td><b>Scheduled date</b></td>

	</tr>

  
  
  <tr>
  
		<td><b><%=task.getTaskName()%></b></td><td><%=task.getTaskDesc()%></td>
		<td>
		 <%if(!StringUtils.isNullOrEmpty(task.getIsGroup()) && task.getIsGroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=task.getAssignedtoUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=task.getAssignedtoUserName()%></a> 
<%}else{%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=task.getAssignedtoUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=task.getAssignedtoUserName()%></a> 
<%}%>
		
		 </td><td><%=DateUtil.convertDateToStringDate(task.getEventDate(),datepattern)%></td>
     </tr>
	
     
     <%
      List attributes = task.getAttributeValues();
	 if(attributes != null && attributes.size()>0){

	  %>
      <tr><td></td><td>
	  <table>
	  <tr bgcolor=#f3f3f3><td>Attribute</td><td>Value</td></tr>
	  <% for(int p=0;p<attributes.size();p++){
		  OnBoardingTaskAttributeValues attr = (OnBoardingTaskAttributeValues)attributes.get(p);
	   %>
	  <tr><td><%=attr.getAttribute()%><%=(attr.getIsMandatory() != null && attr.getIsMandatory().equals("Y"))?"<font color=red>*</red>":""%></td><td><TEXTAREA NAME="taskattvalue_<%=attr.getAttid()%>" COLS=40 ROWS=2><%=(attr.getAttributeValue()==null)?"":attr.getAttributeValue()%></TEXTAREA></td></tr>
      
	  <%
		  } // end for loop
	  %>
	  </table></td><td></td><td></td><td></td></tr>
	 <%}//end of null
	 %>


   

</table>
</td>
</tr>
</fieldset>
<tr>
<td>
<table>
<tr>
<td>
Comment : 
</td>
<td>
<TEXTAREA NAME="comments" COLS=60 ROWS=6><%=(task.getTaskOwnerComment()==null)?"":task.getTaskOwnerComment()%></TEXTAREA>
</td>
</tr>
<tr>
				<td>
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">
			
				</td>
				<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"></td>
			</tr>
</table>
</td>
</tr>

</table>
</html:form>

<%}%>
</body>