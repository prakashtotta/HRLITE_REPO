<%@ include file="../common/include.jsp" %>
<%
String datepattern = Constant.getValue("defaultdateformat");
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
       eventheader = "Resume Screening";
	}else{
		eventheader = Common.INTERVIEW_ROUND + "-"+event.getEventType();
	}

	User createbyuser = UserBO.getUserByUserName(event.getCreatedBy());
%>
<div class="closelink">
<fieldset><legend><%=eventheader%></legend>
<table border="0" width="100%">

<tr>
<td width="30%"class="bodytext">Scheduled By : </td>
<td class="bodytext"><a href="#"><%=createbyuser.getFirstName()+ " "+createbyuser.getLastName()%></a> on 
<%=DateUtil.convertDateToStringDate(event.getCreatedDate(),datepattern)%>
</td>
</tr>
<tr>
<td width="30%"class="bodytext">Scheduler Comments : </td>
<td class="bodytext"><span id="modelDescription"><%=(event.getNotes() == null)? "Not yet commented":event.getNotes()%></span></td>
</tr>
<tr>
<td width="30%"class="bodytext">Review Date : </td>
<td class="bodytext">
<%=DateUtil.convertDateToStringDate(event.getInterviewDate(),datepattern)%>

<% if((event.getDurationhr() != null && !event.getDurationhr().equals("0")) || (event.getDurationminute() != null && !event.getDurationminute().equals("00"))){ %>
for
<%}%>
<% if(event.getDurationhr() != null && !event.getDurationhr().equals("0")){%>
<%=event.getDurationhr()%> hr(s)
<%}%>
<% if(event.getDurationminute() != null && !event.getDurationminute().equals("00")){%>
<%=event.getDurationminute()%> minute(s)
<%}%>

</td>
</tr>
<tr>
<td width="30%"class="bodytext">Reviewer : </td>
<td class="bodytext">
<a href="#"><%=event.getOwner().getFirstName()+ " "+event.getOwner().getLastName()%></a> <br>
<%
	if(!StringUtils.isNullOrEmpty(event.getReassignedGraph())){
	%>
<b>Re-assigned graph :</b> &nbsp;&nbsp;  <%=event.getReassignedGraph()%>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>
<b>Re-assigned comments : </b>&nbsp;&nbsp;<%=(event.getReassignedCommentGraph() == null)? "":StringUtils.doSpecialCharacters(event.getReassignedCommentGraph())%>
<%}%>
</td>
</tr>
<%
List interviewwatchlist = BOFactory.getApplicantBO().getInterviewWatchList(event.getJobAppEventId());
	%>
<tr>
<td width="30%"class="bodytext">Watch list : </td>
<td class="bodytext">

<% 
	    if(interviewwatchlist != null && interviewwatchlist.size()>0){
		for(int qq=0;qq<interviewwatchlist.size();qq++){
		InterviewWatchList intwatch = (InterviewWatchList)interviewwatchlist.get(qq);
%>

<a href="#"><%=intwatch.getUsername()%> </a> &nbsp;&nbsp;

<%}
}else{%>
No watcher added.
<%}%>

</td>
</tr>

<tr>
<td width="30%"class="bodytext">Mode of Interview : </td>
<td class="bodytext"><%=Constant.getModeOfInterviewName(event.getModofinterviewid())%></td>
</tr>
<tr>
<td width="30%"class="bodytext">Status : </td>
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
<td width="30%"class="bodytext">Rejection root cause : </td>
<td class="bodytext"><span id="modelDescription"><%=(rejectionrootcause == null)? "":rejectionrootcause%></span></td>
</tr>
<%}%>
<tr>
<td width="30%"class="bodytext">Interviewer Comments : </td>
<td class="bodytext"><span id="modelDescription"><%=(event.getInterviewerComments() == null)? "Not yet commented":event.getInterviewerComments()%></span></td>
</tr>
<%
	if(event.getEvtmplFileName() != null && event.getEvtmplFileName() != null && event.getEvtmplFileName().length()>0){
	String evfilepath = request.getContextPath()+"/download/file?filename="+event.getJobAppEventId()+"/"+event.getEvtmplFileName();
	
%>
<tr>
<td width="30%"class="bodytext">Evaluation template File : </td>
<td class="bodytext"><a href="<%=evfilepath%>" ><%=event.getEvtmplFileName()%></a></td>
</tr>
<%}%>

<%
	if(event.getEvtmplId() != 0 && event.getEvTmplfeedback() != null){
	
%>
<tr>
<td width="30%"class="bodytext">Evaluation sheet feedback: </td>
<td><span id="modelDescription"><%=event.getEvTmplfeedback()%> </span> </td>
</tr>
<%}%>




</table>
</fieldset>
</div>
<%}}%>

