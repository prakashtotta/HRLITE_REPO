<%@ include file="../common/includejava.jsp" %>

<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<style type="text/css" media="screen">
#wrap {
	width:960px;
	margin:30px auto;
}
.circle {
	display: block;
	display: block;
	width: 160px;
	height: 60px;
	background: #333;
	-moz-border-radius: 40px;
	-webkit-border-radius: 40px;
	margin:20px auto;
}
.title_box{
width:196px;
height:30px;
margin:5px 0 0 0;
text-align:center;
font-size:13px;
font-weight:bold;
line-height:30px;
background: #B2D2FF;

}
</style>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<title><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%> </title>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<table border="0" width="100%">
<tr bgcolor="#f3f3f3">
<td width="50%" align="center"><b><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%> - <bean:write name="applicantForm" property="fullName"/> </b></td>
<td align="center">
<b><%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%> : <bean:write name="applicantForm" property="interviewState"/></b>
</td>
</tr>
</table>
<%
List activityList = form.getActivityList();
%>

<table border="0" width="100%">
<tr bgcolor="#f3f3f3">
<td width="20%"><%=Constant.getResourceStringValue("hr.applicant.activity.on",user1.getLocale())%></td>
<td width="25%"><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%></td>
<td width="15%"><%=Constant.getResourceStringValue("hr.applicant.activity.by",user1.getLocale())%></td>
<td width="15%"><%=Constant.getResourceStringValue("hr.applicant.activity.to",user1.getLocale())%></td>
<td width="25%"><%=Constant.getResourceStringValue("hr.applicant.activity.note",user1.getLocale())%></td>
</tr>
<% 
if(activityList != null){
for(int i=0;i<activityList.size();i++){
	ApplicantActivity activity = (ApplicantActivity)activityList.get(i);

	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>
<tr bgcolor=<%=bgcolor%>>
<td><%=DateUtil.convertSourceToTargetTimezone(activity.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
<td><%=(activity.getActivityName() == null)?"":Constant.getResourceStringValue(activity.getActivityName(),user1.getLocale())%></td>
<td class="bodytext">
<% if(activity.getCreatedByType() == null || activity.getCreatedByType().equals(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL)){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=activity.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getUserFullName()%></a> 
<%}else if(activity.getCreatedByType() != null && activity.getCreatedByType().equals(Common.APPLICANT_CREATED_BY_TYPE_AGENCY)){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=editvendor&readPreview=2&userId=<%=activity.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getUserFullName()%></a> 
<%}else{%>
<%=activity.getUserFullName()%>
<%}%>
</td>
<td>
<%if(!StringUtils.isNullOrEmpty(activity.getIsgroup()) && activity.getIsgroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=activity.getAssignedTouserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=(activity.getAssignedToUserName()==null)?"":activity.getAssignedToUserName()%></a>  
<%}else if(activity.getAssignedTouserId() != 0){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=activity.getAssignedTouserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getAssignedToUserName()%></a> 
<%}%>
</td>
<td><%=(activity.getComment() == null)?"":StringUtils.doSpecialCharacters(activity.getComment())%></td>

</tr>

<%}
}
%>
</table>


<table>

<% 
int i1=1;
int noofactivityonerow=4;
if(activityList != null){
for(int i=0;i<activityList.size();i++){
	ApplicantActivity activity = (ApplicantActivity)activityList.get(i);

	
%>
<% if(i%noofactivityonerow == 0){%>
<tr>
<%}%>
<td>
		<span class='circle' style='background:#eee;'><%=(activity.getActivityName() == null)?"":Constant.getResourceStringValue(activity.getActivityName(),user1.getLocale())%> <br>
       <% if(activity.getCreatedByType() == null || activity.getCreatedByType().equals(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL)){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=activity.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getUserFullName()%></a> 
<%}else if(activity.getCreatedByType() != null && activity.getCreatedByType().equals(Common.APPLICANT_CREATED_BY_TYPE_AGENCY)){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=editvendor&readPreview=2&userId=<%=activity.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getUserFullName()%></a> 
<%}else{%>
<%=activity.getUserFullName()%>
<%}%>

		</span>
		
		</td>
		<% if(i1<activityList.size()){%>
		<td><img src="jsp/images/arrow.jpg" height="30" width="30"></img></td>
		<%}%>



<%
	i1++;
}
%>
<%
 }
 %>




 </table>



 <br><br>

 <%
List slredyappliedList = form.getAlreayappliedlist();
if(slredyappliedList != null && slredyappliedList.size()>0){
	for(int kk=0;kk<slredyappliedList.size();kk++){
		JobApplicant applicanttemp =(JobApplicant)slredyappliedList.get(kk);
 activityList = BOFactory.getApplicantBO().getActivityList(applicanttemp.getApplicantId(), applicanttemp.getUuid());
 String detailsurl="applicant.do?method=applicantDetails&applicantId="+applicanttemp.getApplicantId()+"&secureid="+applicanttemp.getUuid();
%>
<div class="title_box"><a href="<%=detailsurl%>" target="new"><%=Constant.getResourceStringValue("hr.applicant.archive.activity",user1.getLocale())%> : <%=kk+1%> </a></div>  


<table border="0" width="100%">
<tr bgcolor="#B2D2FF">
<td width="20%"><%=Constant.getResourceStringValue("hr.applicant.activity.on",user1.getLocale())%></td>
<td width="25%"><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%></td>
<td width="15%"><%=Constant.getResourceStringValue("hr.applicant.activity.by",user1.getLocale())%></td>
<td width="15%"><%=Constant.getResourceStringValue("hr.applicant.activity.to",user1.getLocale())%></td>
<td width="25%"><%=Constant.getResourceStringValue("hr.applicant.activity.note",user1.getLocale())%></td>
</tr>
<% 
if(activityList != null){
for(int i=0;i<activityList.size();i++){
	ApplicantActivity activity = (ApplicantActivity)activityList.get(i);

	String bgcolor = "";
	if(i%2 == 1)bgcolor ="#B2D2FF";
%>
<tr bgcolor=<%=bgcolor%>>
<td><%=DateUtil.convertSourceToTargetTimezone(activity.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
<td><%=(activity.getActivityName() == null)?"":Constant.getResourceStringValue(activity.getActivityName(),user1.getLocale())%></td>
<td class="bodytext">
<% if(activity.getCreatedByType() == null || activity.getCreatedByType().equals(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL)){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=activity.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getUserFullName()%></a> 
<%}else if(activity.getCreatedByType() != null && activity.getCreatedByType().equals(Common.APPLICANT_CREATED_BY_TYPE_AGENCY)){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=editvendor&readPreview=2&userId=<%=activity.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getUserFullName()%></a> 
<%}else{%>
<%=activity.getUserFullName()%>
<%}%>
</td>
<td>
<%if(!StringUtils.isNullOrEmpty(activity.getIsgroup()) && activity.getIsgroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=activity.getAssignedTouserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=(activity.getAssignedToUserName()==null)?"":activity.getAssignedToUserName()%></a>  
<%}else if(activity.getAssignedTouserId() != 0){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=activity.getAssignedTouserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getAssignedToUserName()%></a> 
<%}%>
</td>
<td><%=(activity.getComment() == null)?"":StringUtils.doSpecialCharacters(activity.getComment())%></td>

</tr>

<%}
}
%>


</table>
<br>
<%
	
	}// end of slredyappliedList for loop
} // end of slredyappliedList null check
%>