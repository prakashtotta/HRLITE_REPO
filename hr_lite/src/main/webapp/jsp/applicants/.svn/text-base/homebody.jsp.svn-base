<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.Format"%>
<%@ page import="com.bean.testengine.*"%>



<%
ApplicantUser user = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
JobApplicant applicant = user.getApplicant();
%>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 1200px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}


</style>
<style type="text/css" media="screen">
#wrap {
	width:960px;
	margin:30px auto;
}
.circle {
	display: block;
	display: block;
	height: auto;
	width: auto;
	background: #333;
	-moz-border-radius: 40px;
	-webkit-border-radius: 40px;
	margin:20px auto;
}
</style>
<script language="javascript">
this.reload_on_close=false;

function performaction(url){
		//GB_showFullScreen('<%=Constant.getResourceStringValue("hr.applicant.applicant.actions",user.getLocale())%>',url,messageret);
		GB_showCenter('<%=Constant.getResourceStringValue("hr.applicant.applicant.actions",user.getLocale())%>',url,500,700, messageret);
}

function addcomment(url){
		GB_showFullScreen('<%=Constant.getResourceStringValue("hr.applicant.comments",user.getLocale())%>',url,messageret);
}


function messageret(){
	//window.location.reload();

			}


</script>



<%
Set activitySet = BOFactory.getApplicantBO().getUniqueDisplaybleAppPageActivityList(applicant.getApplicantId(),applicant.getUuid());
List appusractionsList = ApplicantUserBO.getApplicantActionsList(applicant.getApplicantId(),applicant.getUuid());

List eventList = BOFactory.getApplicantBO().getApplicationEventList(String.valueOf(applicant.getApplicantId()));
String intstate = BOFactory.getApplicantBO().getInterviewStateById(applicant.getUuid());
%>


<%=Constant.getResourceStringValue("hr.applicant.applicant.status",user.getLocale())%> : <%=Constant.getResourceStringValue(intstate,user.getLocale())%>

<br>




<% if(appusractionsList != null && appusractionsList.size()>0){%>
<fieldset><legend><%=Constant.getResourceStringValue("hr.applicant.require.my.actions",user.getLocale())%></legend>
<table width="1000px">

<tr>
<td width="35%"><b><%=Constant.getResourceStringValue("hr.applicant.action.name",user.getLocale())%></b></td>
<td width="40%"><b><%=Constant.getResourceStringValue("hr.applicant.action.comment",user.getLocale())%></b></td>
<td align="left" width="20%"><b><%=Constant.getResourceStringValue("hr.applicant.action.added.on",user.getLocale())%></b></td>
<td align="left" width="10%"><b><%=Constant.getResourceStringValue("hr.applicant.action.status",user.getLocale())%></b></td>
</tr>

<% if(appusractionsList != null && appusractionsList.size()>0){
	int k=1;
    for(int i=0;i<appusractionsList.size();i++){
    ApplicantUserActions action = (ApplicantUserActions)appusractionsList.get(i);
	String actionurl = request.getContextPath()+"/applicantuserops.do?method=performactionscr&actionname="+action.getActionName()+"&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
	String offerurl = request.getContextPath()+"/applicantuserops.do?method=applicantofferdetails&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
	
	String bgcolor="";
		if(i%2==0)bgcolor="bgcolor=#f3f3f3";
	%>
		<tr <%=bgcolor%> >
		<td width="35%">
				<% if(action.getActionName() != null && action.getActionName().equals("offer_accept_decline")){%>
				#<%=k%>&nbsp;<a  href="<%=offerurl%>" ><%=action.getActionName()%> </a>
				
				<%}else{%>
				#<%=k%>&nbsp;<a  href="#" onClick="javascript:performaction('<%=actionurl%>')"><%=Constant.getResourceStringValue(action.getActionName(),user.getLocale())%> </a>
				<%}%>
				</td>
				<td width="40%">
				<%=(action.getCreatorComment() == null)? "":StringUtils.doSpecialCharacters(action.getCreatorComment())%>
				</td>
				<td align="left" width="20%">
				
				<%=DateUtil.convertSourceToTargetTimezone(action.getCreatedDate(), user.getTimezone().getTimezoneCode(), user.getLocale())%>
				</td>
			<td align="left" width="10%">
				<% if(action.getStatus().equals("A")){%>
				<%=Constant.getResourceStringValue("hr.applicant.action.status.open",user.getLocale())%>
				<%}else{%>
				<%=Constant.getResourceStringValue("hr.applicant.action.status.completed",user.getLocale())%>
				<%}%>
			</td>
			</tr>
		<%
		k++;
		}
  	}// end of appusractionsList  
	%>
</table>
</fieldset>
<br>
<%}%>

<fieldset><legend><%=Constant.getResourceStringValue("hr.applicant.my.interview",user.getLocale())%></legend>
<table>

<tr>
<td width="35%"> <b><%=Constant.getResourceStringValue("hr.applicant.action.review.state",user.getLocale())%> </b></td> <td align="right" width="35%"><b><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Review_Date",user.getLocale())%></b></td> 
<td align="right" width="30%"><b><%=Constant.getResourceStringValue("hr.applicant.action.status",user.getLocale())%></b></td>
</tr>

<% if (eventList != null && eventList.size() > 0){
	for(int i=0;i<eventList.size();i++){
	JobApplicationEvent event = (JobApplicationEvent)eventList.get(i);
	String eventheader = Common.INTERVIEW_ROUND;
	if(event.getEventType() == 0){
       eventheader = Constant.getResourceStringValue("Resume_Screening",user.getLocale());
	}else if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED)){
       eventheader = Constant.getResourceStringValue("Exam_Screening",user.getLocale());
	}else if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED)){
		eventheader = Constant.getResourceStringValue("Questionnaire_Assigned",user.getLocale());
	}else{
		eventheader = Common.INTERVIEW_ROUND + "-"+event.getEventType();
	}
String bgcolor="";
	if(i%2==0)bgcolor="bgcolor=#f3f3f3";
	
%>

<tr <%=bgcolor%> >
<td width="35%"><%=Constant.getResourceStringValue(eventheader,user.getLocale())%> </td> 
<td align="right" width="35%">
<%



%> 

<!-- below code is commented purposefully for showing short date -->
<%=DateUtil.convertSourceToTargetTimezone(event.getInterviewDate(), user.getTimezone().getTimezoneCode(), user.getLocale())%> 
<% if((event.getDurationhr() != null && !event.getDurationhr().equals("0")) || (event.getDurationminute() != null && !event.getDurationminute().equals("00"))){ %>
<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.for",user.getLocale())%>
<%}%>
<% if(event.getDurationhr() != null && !event.getDurationhr().equals("0")){%>
<%=event.getDurationhr()%> <%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.hr",user.getLocale())%>
<%}%>
<% if(event.getDurationminute() != null && !event.getDurationminute().equals("00")){%>
<%=event.getDurationminute()%> <%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.minute",user.getLocale())%>
<%}%>


</td> 
<td align="right" width="30%">
<%
	String sttausmsg = "";
    String urlstr="";
	if(event.getStatus() == 0) {
		sttausmsg = "In Review";
	}
	if(event.getStatus() == 1) {
		sttausmsg = "Cleared Review";
	}
	if(event.getStatus() == 2) {
		sttausmsg = "Rejected";
	}
	if(event.getStatus() == 3) {
		sttausmsg = "On Hold";
	}

	if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 0){
		sttausmsg = "To be attended";	

	}
	if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 1){
		sttausmsg = "Pass";
	}
	if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 2){
		sttausmsg = "Fail";
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
<%=Constant.getResourceStringValue(sttausmsg,user.getLocale())%>

<% if(event.getEventType() == Common.getEventType(Common.EXAM_ASSIGNED) && event.getStatus() == 0){
MockTest mocktest = BOFactory.getMockTestBO().getMockTest(String.valueOf(event.getTestId()));
        String urlstrtemp = Constant.getValue("external.url")+"jsp/testengine/pmptech20.jsp?catId="+mocktest.getCat().getCatId()+"&applicantId="+user.getApplicantId()+"&uuid="+mocktest.getUuid();
		
        
%>
<a href="<%=urlstrtemp%>" target="new"><%=mocktest.getCat().getDisplayName()%></a>
<%}%>
<%if(event.getEventType() == Common.getEventType(Common.QUESTIONNAIRE_ASSIGNED) && event.getStatus() == 0){
QuestionGroupApplicants qnsgrpapp = BOFactory.getMockTestBO().getQuestionNaire(event.getTestId());
 String urlstrtempqns = Constant.getValue("external.url")+"questionnaire.do?method=questionnaire&gpid="+event.getTestId()+"&5&applicantid="+user.getApplicantId()+"&uuid="+qnsgrpapp.getUuid();
%>
<a href="<%=urlstrtempqns%>" target="new"><%=qnsgrpapp.getQuestiongroup().getQuestiongroupName()%></a>


<%}%>

</td>

<%
	}
} // end of event list
%>

</table>
</fieldset>

<br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.applicant.activities",user.getLocale())%></legend>
<table height="100%">

<tr>
<% 

int i1=1;
if(activitySet != null){
	int totalactsize = activitySet.size();
	
	Iterator iter = activitySet.iterator();
    while (iter.hasNext()) {
	ApplicantActivity activity = (ApplicantActivity)iter.next();
   

%>

<%



%> 
<td>
		<span class='circle' style='background:#eee;font-size: smaller'><%=(activity.getActivityName() == null)?"":Constant.getResourceStringValue(activity.getActivityName(),user.getLocale())%> <br>
		
		<!-- below code is commented purposefully for showing short date -->
		<%=DateUtil.convertSourceToTargetTimezone(activity.getCreatedDate(), user.getTimezone().getTimezoneCode(), user.getLocale())%>
		
		</span>
		
		</td>
		<% if(i1<totalactsize){%>
		<td><img src="jsp/images/arrow.jpg" height="30" width="20"></img></td>
		<%}%>

<%
	i1++;
	}
 }
 %>
 </tr>
 </table>
 </fieldset>