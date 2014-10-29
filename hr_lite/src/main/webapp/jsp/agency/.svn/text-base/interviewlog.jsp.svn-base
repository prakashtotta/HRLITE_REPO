<%@ include file="../common/include.jsp" %>

<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
List eventList = form.getEventList();
%>

<% if (eventList != null && eventList.size() > 0){
	for(int i=0;i<eventList.size();i++){
	JobApplicationEvent event = (JobApplicationEvent)eventList.get(i);
	String eventheader = Common.INTERVIEW_ROUND;
	if(event.getEventType() == 0){
       eventheader = "Resume Screening";
	}else{
		eventheader = Common.INTERVIEW_ROUND + "-"+event.getEventType();
	}

	User createbyuser = UserBO.getUserByUserName(event.getCreatedBy());
%>
<fieldset><legend><%=eventheader%></legend>
<table border="0" width="100%">

<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Scheduled_By",user1.getLocale())%> : </td>
<td class="bodytext"><a href="#"><%=createbyuser.getFirstName()+ " "+createbyuser.getLastName()%></a> on 
<%=DateUtil.convertDateToStringDate(event.getCreatedDate(),datepattern)%>
</td>
</tr>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Scheduler_Comments",user1.getLocale())%> : </td>
<td class="bodytext"><span id="modelDescription"><%=(event.getNotes() == null)? "Not yet commented":event.getNotes()%></span></td>
</tr>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Review_Date",user1.getLocale())%> : </td>
<td class="bodytext">
<%=DateUtil.convertDateToStringDate(event.getInterviewDate(),datepattern)%>

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
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer",user1.getLocale())%> : </td>
<td class="bodytext">
<a href="#"><%=event.getOwner().getFirstName()+ " "+event.getOwner().getLastName()%></a> <br>
<%
	if(!StringUtils.isNullOrEmpty(event.getReassignedGraph())){
	%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Re_assigned_graph",user1.getLocale())%> :</b> &nbsp;&nbsp;  <%=event.getReassignedGraph()%>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>
<b><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Re_assigned_comments",user1.getLocale())%> : </b>&nbsp;&nbsp;<%=(event.getReassignedCommentGraph() == null)? "":StringUtils.doSpecialCharacters(event.getReassignedCommentGraph())%>
<%}%>
</td>
</tr>
<%
List interviewwatchlist = BOFactory.getApplicantBO().getInterviewWatchList(event.getJobAppEventId());
	%>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Watch_list",user1.getLocale())%> : </td>
<td class="bodytext">

<% 
	    if(interviewwatchlist != null && interviewwatchlist.size()>0){
		for(int qq=0;qq<interviewwatchlist.size();qq++){
		InterviewWatchList intwatch = (InterviewWatchList)interviewwatchlist.get(qq);
%>

<a href="#"><%=intwatch.getUsername()%> </a> &nbsp;&nbsp;

<%}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.No_watcher_added",user1.getLocale())%>
<%}%>

</td>
</tr>

<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Mode_of_Interview",user1.getLocale())%> : </td>
<td class="bodytext"><%=Constant.getModeOfInterviewName(event.getModofinterviewid())%></td>
</tr>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Status",user1.getLocale())%> : </td>
<%
	String sttausmsg = "";
    
	if(event.getStatus() == 0) {
		sttausmsg = "In Interview";
	}
	if(event.getStatus() == 1) {
		sttausmsg = "Cleared Interview";
	}
	if(event.getStatus() == 2) {
		sttausmsg = "Rejected";
	}
	if(event.getStatus() == 3) {
		sttausmsg = "On Hold - by "+event.getUpdatedByName() + " on "+event.getUpdatedDate();
	}
%>
<td class="bodytext"><%=sttausmsg%>

</td>
</tr>
<% if(event.getStatus() == 2){
		String rejectionrootcause = BOFactory.getLovBO().getRejectedReasonName(form.getApplicantId());		
%>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Rejection_root_cause",user1.getLocale())%> : </td>
<td class="bodytext"><span id="modelDescription"><%=(rejectionrootcause == null)? "":rejectionrootcause%></span></td>
</tr>
<%}%>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Interviewer_Comments",user1.getLocale())%> : </td>
<td class="bodytext"><span id="modelDescription"><%=(event.getInterviewerComments() == null)? "Not yet commented":event.getInterviewerComments()%></span></td>
</tr>
<%
	if(event.getEvtmplFileName() != null && event.getEvtmplFileName() != null && event.getEvtmplFileName().length()>0){
	String evfilepath = request.getContextPath()+"/download/file?filename="+event.getJobAppEventId()+"/"+event.getEvtmplFileName();
	
%>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Evaluation_template_file",user1.getLocale())%> : </td>
<td class="bodytext"><a href="<%=evfilepath%>" ><%=event.getEvtmplFileName()%></a></td>
</tr>
<%}%>

<%
	if(event.getEvtmplId() != 0 && event.getEvTmplfeedback() != null){
	
%>
<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Evaluation_sheet_feedback",user1.getLocale())%> : </td>
<td><span id="modelDescription"><%=event.getEvTmplfeedback()%> </span> </td>
</tr>
<%}%>




</table>
</fieldset>

<%}}%>

