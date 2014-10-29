<%@ include file="../common/includejava.jsp" %>


<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<%
List eventList = form.getEventList();
%>

<% if (eventList != null && eventList.size() > 0){
	for(int i=0;i<eventList.size();i++){
	JobApplicationEvent event = (JobApplicationEvent)eventList.get(i);
	String eventheader = Common.INTERVIEW_ROUND;
	if(event.getEventType() == 0){
       eventheader = Constant.getResourceStringValue("Resume_Screening",user1.getLocale());
	}else if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED)){
       eventheader = Constant.getResourceStringValue("Exam_Screening",user1.getLocale());
	}else if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED)){
		eventheader = Constant.getResourceStringValue("Questionnaire_Assigned",user1.getLocale());
	}else{
		eventheader = Common.INTERVIEW_ROUND + "-"+event.getEventType();
	}

	
%>
<fieldset>
<div style="background: #f3f3f3;  text-align:left"><a href="#" data-openimage="jsp/images/Collapsed.gif" ><img src="jsp/images/Expanded.gif" border="0" /></a> <%=eventheader%>  </div>
<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">

<table border="0" width="100%">

<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Scheduled_By",user1.getLocale())%> : </td>
<td class="bodytext"><img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituserbyUsername&readPreview=2&username=<%=event.getCreatedBy()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=event.getCreatedByName()%></a> on <%=DateUtil.convertSourceToTargetTimezone(event.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
</tr>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Scheduler_Comments",user1.getLocale())%> : </td>
<td class="bodytext"><span id="modelDescription">
<%=(event.getNotes() == null)?"":StringUtils.doSpecialCharacters(event.getNotes())%>
</span></td>
</tr>

<% if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED)){%>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Exam_name",user1.getLocale())%> : </td>
<td class="bodytext"><span id="modelDescription">
<a href="<%=request.getContextPath()%>/jsp/testengine/pmpans.jsp?testid=<%=event.getTestId()%>&applicantid=<%=form.getApplicantId()%>&uuid=<%=form.getUuid()%>" target="new"><%=event.getInterviewState().substring(event.getInterviewState().lastIndexOf("^^")+3)%></a>
</span></td>
</tr>
<%}%>
<% if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED)){%>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("hr.applicant.Questionnaire",user1.getLocale())%> : </td>
<td class="bodytext"><span id="modelDescription">
<a href="#" onClick="window.open('questiongroup.do?method=questionnaire&gpid=<%=event.getTestId()%>&applicantid=<%=form.getApplicantId()%>&uuid=<%=form.getUuid()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=event.getInterviewState().substring(event.getInterviewState().lastIndexOf("^^")+3)%></a>

</span></td>
</tr>
<%}%>
<tr>
<% if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED)){%>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Exam_Date",user1.getLocale())%> : </td>
<% }else if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED)){%>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaires.Answered.on",user1.getLocale())%> : </td>
<% }else if(event.getEventType() == Common.getEventType(Common.IN_SCREENING)){%>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Review_Date",user1.getLocale())%> : </td>
<%}else{%>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Interview_Date",user1.getLocale())%> : </td>

<%}%>
<%

String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(event.getInterviewDate(),
		datepattern, user1.getTimezone()
		.getTimezoneCode(), TimeZone
		.getDefault().getID());

if( event.getInterviewState().equals("In Screening") || event.getInterviewState().equals("Cleared-In Screening")){
%>
	<td class="bodytext"><%=converteddate%>
<%}else{%>
	<td class="bodytext"><%=DateUtil.convertSourceToTargetTimezone(event.getInterviewDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%> 
<%}%>

<% if((event.getDurationhr() != null && !event.getDurationhr().equals("0")) || (event.getDurationminute() != null && !event.getDurationminute().equals("00"))){ %>
<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.for",user1.getLocale())%>
<%}%>
<% if(event.getDurationhr() != null && !event.getDurationhr().equals("0")){%>
<%=event.getDurationhr()%> <%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.hr",user1.getLocale())%>
<%}%>
<% if(event.getDurationminute() != null && !event.getDurationminute().equals("00")){%>
<%=event.getDurationminute()%> <%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.minute",user1.getLocale())%>
<%}%>

</td>
</tr>
<% if((event.getEventType() != Common.getEventType(Common.EXAM_ASSIGNED)) && (event.getEventType() != Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED))){%>
<tr>
<td width="40%"class="bodytext">
<% if(event.getInterviewState().equals("In Screening") || event.getInterviewState().equals("Cleared-In Screening")) {%>
<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer",user1.getLocale())%> 
<%}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Interviewer",user1.getLocale())%> 
<%}%>
: </td>

<td class="bodytext">
<%if(!StringUtils.isNullOrEmpty(event.getIsGroup()) && event.getIsGroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=(event.getOwnerGroup()==null)?"0":event.getOwnerGroup().getUsergrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=(event.getOwnerGroup()==null)?"":event.getOwnerGroup().getUsergrpName()%></a> 

<%}else{%>
<img src="jsp/images/user.gif">
<a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=event.getOwner().getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=event.getOwner().getFirstName()+ " "+event.getOwner().getLastName()%></a> 
<%}%>

<br>
<%
	if(!StringUtils.isNullOrEmpty(event.getReassignedGraph())){
	%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Re_assigned_graph",user1.getLocale())%> :</b> &nbsp;&nbsp;  <%=event.getReassignedGraph()%>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>
<b><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Re_assigned_comments",user1.getLocale())%> : </b>&nbsp;&nbsp;<%=(event.getReassignedCommentGraph() == null)? "":StringUtils.doSpecialCharacters(event.getReassignedCommentGraph())%>
<%}%>
</td>
</tr>
<%--
<%
List interviewwatchlist = BOFactory.getApplicantBO().getInterviewWatchList(event.getJobAppEventId());
	%>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Watch_list",user1.getLocale())%> : </td>
<td class="bodytext">

<% 
	    if(interviewwatchlist != null && interviewwatchlist.size()>0){
		for(int qq=0;qq<interviewwatchlist.size();qq++){
		InterviewWatchList intwatch = (InterviewWatchList)interviewwatchlist.get(qq);
%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=intwatch.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=intwatch.getUsername()%> </a> &nbsp;&nbsp;

<%}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.No_watcher_added",user1.getLocale())%>
<%}%>

</td>
</tr>
--%>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Mode_of_Interview",user1.getLocale())%> : </td>
<td class="bodytext"><%=Constant.getModeOfInterviewName(event.getModofinterviewid())%></td>
</tr>
<%}// event type 777 end %>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Status",user1.getLocale())%> : </td>
<%
String sttausmsg = "";

		if(event.getInterviewState().equals("In Screening") || event.getInterviewState().equals("Cleared-In Screening")){
			
		    
			if(event.getStatus() == 0) {
				sttausmsg = "In Screening";
			}
			if(event.getStatus() == 1) {
				sttausmsg = "Cleared Screening";
			}
			if(event.getStatus() == 2) {
				sttausmsg = "Rejected";
			}
			if(event.getStatus() == 4) {
				sttausmsg = "Declined";
			}
			if(event.getStatus() == 3) {
				sttausmsg = "On Hold - by "+event.getUpdatedByName() + " on "+DateUtil.convertSourceToTargetTimezone(event.getUpdatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale());
			}
		}else{
			
		    
			if(event.getStatus() == 0) {
				//sttausmsg = "In Interview";
				sttausmsg = "To be conducted";
			}
			if(event.getStatus() == 1) {
				sttausmsg = "Cleared Interview";
			}
			if(event.getStatus() == 2) {
				sttausmsg = "Rejected";
			}
			if(event.getStatus() == 4) {
				sttausmsg = "Declined";
			}
			if(event.getStatus() == 3) {
				sttausmsg = "On Hold - by "+event.getUpdatedByName() + " on "+DateUtil.convertSourceToTargetTimezone(event.getUpdatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale());
			}
			
		}

	if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 0){
		sttausmsg = "To be attended";
	}
	if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 1){
       MockTest mocktest = BOFactory.getMockTestBO().getMockTestByTestDetails(String.valueOf(event.getTestId()),String.valueOf(form.getApplicantId()),form.getUuid());
	   if(mocktest.getEndTime() != null){ 
		sttausmsg = "Pass  "+Constant.getResourceStringValue("hr.applicant.exam.score.achived.percentage",user1.getLocale())+" : "+mocktest.getPercentage();
		}else{
			sttausmsg = "Pass";
		}
		
		
	}
	if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 2){
		        MockTest mocktest = BOFactory.getMockTestBO().getMockTestByTestDetails(String.valueOf(event.getTestId()),String.valueOf(form.getApplicantId()),form.getUuid());
		 if(mocktest.getEndTime() != null){ 
		sttausmsg = "Fail  "+Constant.getResourceStringValue("hr.applicant.exam.score.achived.percentage",user1.getLocale())+" : "+mocktest.getPercentage();
		}else{
			sttausmsg = "Fail";
		}
		
	}
	if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 3){
		sttausmsg = "Exam deleted (Recalled)";
	}

	if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED) && event.getStatus() == 0){
		sttausmsg = "Not answered";
	}
	if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED) && event.getStatus() == 1){
		sttausmsg = "Answered";
	}
	if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED) && event.getStatus() == 2){
		sttausmsg = "Answered";
	}
	if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED) && event.getStatus() == 3){
		sttausmsg = "Questionnaire deleted (Recalled)";
	}
%>
<td class="bodytext">
<%if(event.getStatus() == 4 || event.getStatus() == 2) {%>
<font color="red"><%=sttausmsg%> </font>
<%}else if(event.getStatus() == 1) {%>
<font color="green"><%=sttausmsg%> </font>
<%}else{%>
<%=sttausmsg%>
<%}%>


</td>
</tr>
<% if((event.getEventType() != Common.getEventType(Common.EXAM_ASSIGNED)) && (event.getEventType() != Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED))){%>
<% if(event.getStatus() == 2){
		String rejectionrootcause = BOFactory.getLovBO().getRejectedReasonName(form.getApplicantId());		
%>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Rejection_root_cause",user1.getLocale())%> : </td>
<td class="bodytext"><span id="modelDescription"><%=(rejectionrootcause == null)? "":rejectionrootcause%></span></td>
</tr>
<%}%>
<%}%>

<% if((event.getEventType() != Common.getEventType(Common.EXAM_ASSIGNED)) && (event.getEventType() != Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED))){%>
<tr>
<%if(event.getInterviewState().equals("In Screening") || event.getInterviewState().equals("Cleared-In Screening")){
%>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer_Comments",user1.getLocale())%> : 
<%}else{ %>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Interviewer_Comments",user1.getLocale())%> : 
<%} %>

<%if(!StringUtils.isNullOrEmpty(event.getIsGroup()) && event.getIsGroup().equals("Y")){
	if(!StringUtils.isNullOrEmpty(event.getUpdatedBy())){
User updatedbyuser = UserBO.getUserByUserName(event.getUpdatedBy());
%>

<img src="jsp/images/user.gif">
<a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=updatedbyuser.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=updatedbyuser.getFirstName()+ " "+updatedbyuser.getLastName()%></a> 
<%
 }
}
%>
</td>
<td class="bodytext">

<span id="modelDescription">
<%=(event.getInterviewerComments() == null)?"Not yet Commented":StringUtils.doSpecialCharacters(event.getInterviewerComments())%>
</span>

</td>
</tr>
<%}// event type 777 for exam%>
<%
	if(event.getEvtmplFileName() != null && event.getEvtmplFileName() != null && event.getEvtmplFileName().length()>0){
	String evfilepath = request.getContextPath()+"/download/file?filename="+event.getJobAppEventId()+"/"+event.getEvtmplFileName();
	
%>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Evaluation_template_file",user1.getLocale())%> : </td>
<td class="bodytext"><a href="<%=evfilepath%>" ><%=event.getEvtmplFileName()%></a></td>
</tr>
<%}%>

<%
	if(event.getEvtmplId() != 0 && event.getEvTmplfeedback() != null){
	
%>
<tr>
<td width="40%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Evaluation_sheet_feedback",user1.getLocale())%> : </td>
<td><span id="modelDescription"><%=event.getEvTmplfeedback()%> </span> </td>
</tr>
<%}%>




</table>
<%--
<% if((event.getEventType() != Common.getEventType(Common.EXAM_ASSIGNED)) && (event.getEventType() != Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED))){%>

<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Competencies",user1.getLocale())%></legend>
<table>
<%
List comptetencyList = event.getCompetencyRatingList();
if(comptetencyList != null && comptetencyList.size()>0){
%>
<tr>
<td width="33%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="23%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></b></td>
<td width="23%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
<td width="20%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.reviewer.rating",user1.getLocale())%></b></td>
</tr>
<%


   for(int jj=0;jj<comptetencyList.size();jj++){
	ApplicantRating comp = (ApplicantRating)comptetencyList.get(jj);
%>

<tr>
<td><%=comp.getName()%></td><td><%=comp.getMinimumRatingRequired()%></td><td><%=(comp.getIsMandatory() != null && comp.getIsMandatory().equals("on"))?"Yes":"No"%></td>
<td><%=comp.getYourrating()%></td>
</tr>

<%
   }
}
%>
</table>
</fieldset>
<br>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments",user1.getLocale())%></legend>
<table>
<%
List accompList = event.getAccomplishmentRatingList();
if(accompList != null && accompList.size()>0){
%>
<tr>
<td width="33%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="33%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
<td width="30%"><b>.<%=Constant.getResourceStringValue("aquisition.applicant.reviewer.rating",user1.getLocale())%></b></td>
</tr>
<%


   for(int jj=0;jj<accompList.size();jj++){
	ApplicantRating acomp = (ApplicantRating)accompList.get(jj);
%>

<tr>
<td><%=acomp.getName()%></td><td><%=(acomp.getIsMandatory() != null && acomp.getIsMandatory().equals("on"))?"Yes":"No"%></td>
<td><%=acomp.getYourrating()%></td>
</tr>

<%
   }
}
%>
</table>
</fieldset>

<%}
// for exam , no need competency and accomplishments
%>
--%>
</div>


</fieldset>
<br>

<%}}%>

