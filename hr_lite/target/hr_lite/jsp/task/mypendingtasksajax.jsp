

<%@include file="/jsp/common/tooltip.jsp" %>

  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String assigneddate = null;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("task.mypendingtasks.title.mypendingtask",user1.getLocale())%></title>



<span id="mytask">



<script language="javascript">





function taskaction(idvalue,uuid,tasktype,action){
	//alert(idvalue+uuid+tasktype+action);

	if(tasktype == '<%=Common.OFFER_APPROVAL_TASK%>'){
		approveOffer(idvalue,uuid,tasktype,action);
	}
	if(tasktype == '<%=Common.OFFER_RELEASE_TASK%>'){
		releaseoffer(idvalue,uuid,tasktype,action);
	}

	if(tasktype == '<%=Common.APPLICANT_REVIEW_TASK%>'){
		if(action == 'addfeedback'){
			addFeedback(idvalue,uuid,tasktype,action);
			}
		if(action == 'reassign'){
			reassignReview(idvalue,uuid,tasktype,action);
			}

		if(action == 'decline'){
			declineReview(idvalue,uuid,tasktype,action);
			}
		
	}

	if(tasktype == '<%=Common.APPLICANT_IN_QUEUE%>'){
		if(action == 'scheduleinterview'){
			scheduleinterviewround(idvalue,uuid,tasktype,action);
			}
		if(action == 'initiateoffer'){
			initiateoffer(idvalue,uuid,tasktype,action);
			}
	}

	if(tasktype == '<%=Common.REQUISTION_APPROVAL_TASK%>'){
		if(action == 'approve'){
			approveRequistion(idvalue,uuid,tasktype,action);
			}
		if(action == 'reject'){
			rejectRequistion(idvalue,uuid,tasktype,action);
		}
	}

   if(tasktype == '<%=Common.REQUISTION_PUBLISH_TASK%>'){
		if(action == 'publish'){
			publishRequistion(idvalue,uuid,tasktype,action);
			}
		
	}

   if(tasktype == '<%=Common.ONBOARDING_TASK%>'){
		if(action == 'onboarding'){
			onboardingApplicant(idvalue,uuid,tasktype,action);
			}
		
	}

	



	

}

function approveOffer(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/applicant.do?method=offerapproverejectcommenttaskpage&applicantId="+idvalue+"&secureid="+uuid+"&action="+action;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>',url,400,600, messageret);
}
function releaseoffer(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/applicant.do?method=releaseofferscr&applicantId="+idvalue;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>',url, messageret);
}
function addFeedback(idvalue,uuid,tasktype,action){
	 var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&frompage=task&status=1&applicantId="+idvalue+"&uuid="+uuid;
			
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_feedback",user1.getLocale())%>',url,700,850, messageret);
	
}
function reassignReview(idvalue,uuid,tasktype,action){
var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=interviwreassign&frompage=task&applicantId="+idvalue+"&uuid="+uuid;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_assign_review",user1.getLocale())%>',url,400,600, messageret);
}

function declineReview(idvalue,uuid,tasktype,action){
var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=declineinterview&frompage=task&applicantId="+idvalue+"&uuid="+uuid;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Decline_review",user1.getLocale())%>',url,400,600, messageret);
}
function initiateoffer(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId="+idvalue;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>',url, messageret);
}
function scheduleinterviewround(idvalue,uuid,tasktype,action){
var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&frompage=task&applicantId="+idvalue+"&uuid="+uuid;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Schedule_Interview",user1.getLocale())%>',url,700,850, messageret);
}
function approveRequistion(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=approverejectReqSrc&jobreqId="+idvalue+"&uuid="+uuid+"&type=approve";
	GB_showCenter('<%=Constant.getResourceStringValue("Requisition.Approve_requisition",user1.getLocale())%>',url,400,600, messageret);
}
function rejectRequistion(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=approverejectReqSrc&jobreqId="+idvalue+"&uuid="+uuid+"&type=reject";
	GB_showCenter('<%=Constant.getResourceStringValue("Requisition.Reject_requisition",user1.getLocale())%>',url,400,600, messageret);
}
function publishRequistion(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=publishJobReqscr&jobreqId="+idvalue+"&uuid="+uuid;
  
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.Publish.requistion",user1.getLocale())%>","dialogHeight: 534px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");
   
   window.document.location.href="task.do?method=mypendingtasks";
}
function onboardingApplicant(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/onboarding.do?method=loadTask&taskid="+idvalue+"&secureid="+uuid;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.onboarding.task",user1.getLocale())%>', url, messageret);
}



function taskdetails(idvalue,uuid,tasktype){
   // alert(idvalue);
 
	var url = "";
	//parent.setPopTitle1("Create applicant");
	//parent.showPopWin(url, 700, 600, null,true);

var ty = unescape(tasktype);
if(ty == '<%=Common.APPLICANT_IN_QUEUE%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.OFFER_APPROVAL_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.APPLICANT_REVIEW_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.OFFER_RELEASE_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.REFERENCE_CHECK_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.REQUISTION_APPROVAL_TASK%>'){
var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+idvalue;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%>', url, messageret);
}
if(ty == '<%=Common.REQUISTION_PUBLISH_TASK%>'){
var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+idvalue;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%>', url, messageret);
}

if(ty == '<%=Common.ONBOARDING_TASK%>'){
var url = "<%=request.getContextPath()%>/onboarding.do?method=loadTask&taskid="+idvalue+"&secureid="+uuid;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.onboarding.task",user1.getLocale())%>', url, messageret);
}
}

function messageret(){
	//window.location.reload();

			}
</script>


<body class="yui-skin-sam">
<%@ include file="mypendingtaskcommon.jsp" %>
</body>

</html>

</span>
