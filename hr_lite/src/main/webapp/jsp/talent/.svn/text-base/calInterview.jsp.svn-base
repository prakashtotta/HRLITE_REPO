<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%
String date = (String)request.getParameter("date");
String reviewerid = (String)request.getParameter("reviewerid");
String deptartmentid = (String)request.getParameter("deptartmentid");
String orgid = (String)request.getParameter("orgid");


User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

List applicanteventList = BOFactory.getApplicantBO().getAllInterviewsByUserOrgDept(user1,reviewerid,date,orgid,deptartmentid);



String data = "";
if(applicanteventList.size()>0){

data ="<table width='100%'><tr><td width=150><b>Applicant Name</b></td><td width=130><b>Interview round</b></td><td width=100><b>Scheduled By</b></td><td width=100><b>Interview Time</b></td><td width=100><b>Interviewer</b></td></tr>";


for(int i=0;i<applicanteventList.size();i++){
	JobApplicationEvent event = (JobApplicationEvent)applicanteventList.get(i);
	String eventheader = Common.INTERVIEW_ROUND;
	if(event.getEventType() == 0){
       eventheader = "Resume Screening";
	}else{
		eventheader = Common.INTERVIEW_ROUND + "-"+event.getEventType();
	}
	String bgcolor = "";
	if(i%2 == 1)bgcolor ="#edf5ff";
	String applicantname1 = event.getApplicant().getFullName();
    if(!StringUtils.isNullOrEmpty(applicantname1)){
	applicantname1 = applicantname1.replace("\"", "");
	}

	String applicanturl = "<a href='applicant.do?method=applicantDetails&applicantId="+event.getApplicant().getApplicantId()+"&secureid="+event.getApplicant().getUuid()+"'>"+applicantname1+"</a>";

String ownerurl = "";
if(!StringUtils.isNullOrEmpty(event.getIsGroup()) && event.getIsGroup().equals("Y")){
	ownerurl =  "<img src='jsp/images/User-Group-icon.png'><a href='#'  onClick=window.open("+"'"+"usergroup.do?method=editusergroup&readPreview=2&usergroupid="+event.getOwnerGroup().getUsergrpId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=630"+"'"+")>"+event.getOwnerGroup().getUsergrpName()+"</a>";
}else{
 ownerurl =  "<img src='jsp/images/user.gif'><a href='#'  onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+event.getOwner().getUserId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=630"+"'"+")>"+event.getOwner().getFirstName()+" " +event.getOwner().getLastName()+"</a>";
}

String schedulerurl =  "<img src='jsp/images/user.gif'><a href='#'  onClick=window.open("+"'"+"user.do?method=edituserbyUsername&readPreview=2&username="+event.getCreatedBy()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=630"+"'"+")>"+event.getCreatedByName()+"</a>";


	String hrs = "";
	if(event.getDurationhr() != null && event.getDurationhr().equals("0")){

	}else if(event.getDurationhr() != null) {
		hrs = event.getDurationhr() + " hr(s)";
	}

	String min = "";
	if(event.getDurationminute() != null && event.getDurationminute().equals("00")){

	}else if(event.getDurationminute() != null){
		min = event.getDurationminute() + " minute(s)";
	}


data = data+ "<tr bgcolor="+bgcolor+"><td>"+applicanturl+"</td>"+"<td>"+event.getInterviewState()+"</td>"+"<td>"+schedulerurl+"</td>";
if(event.getEventType() != 0){
	data = data+"<td>"+DateUtil.convertSourceToTargetTimezoneReturnTime(event.getInterviewDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale()) + "&nbsp;"+  hrs + "&nbsp;"+ min +"</td>";
}else{
	data = data+"<td>"+"</td>";
}

data = data+"<td>"+ownerurl+"</td></tr>";



}

data = data+ "</table>";
}else{

	data ="No interviews";
}
				
%>
{
     "replyCode":201, "replyText":"Data Follows", "data":"<%=data%>"
}