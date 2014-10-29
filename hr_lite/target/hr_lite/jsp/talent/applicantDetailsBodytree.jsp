<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.dao.*"%>
<%@ page import="com.security.*"%>




<%
 
 

  


  %>

  <script type="text/javascript" src="jsp/js/animatedcollapse.js">

<script type="text/javascript">
animatedcollapse.addDiv('addworkexp', 'fade=0,speed=400,group=pets1')
animatedcollapse.addDiv('workexpdetails', 'fade=0,speed=400,group=pets2,hide=0')
animatedcollapse.addDiv('personalinfo', 'fade=0,speed=400,group=pets3,hide=0')
animatedcollapse.addDiv('workpreference', 'fade=0,speed=400,group=pets4,hide=0')
animatedcollapse.addDiv('education', 'fade=0,speed=400,group=pets5,hide=0')
animatedcollapse.addDiv('customvariable', 'fade=0,speed=400,group=pets6,hide=0')
animatedcollapse.addDiv('profile', 'fade=0,speed=400,group=pets7,hide=0')
animatedcollapse.addDiv('source', 'fade=0,speed=400,group=pets8,hide=0')
animatedcollapse.addDiv('social', 'fade=0,speed=400,group=pets9,hide=0')
animatedcollapse.addDiv('background', 'fade=0,speed=400,group=pets10,hide=0')
animatedcollapse.addDiv('addskills', 'fade=0,speed=400,group=pets11')
animatedcollapse.addDiv('skilldetails', 'fade=0,speed=400,group=pets12')
animatedcollapse.addDiv('addeducation', 'fade=0,speed=400,group=pets13')
animatedcollapse.addDiv('educationdetails', 'fade=0,speed=400,group=pets14')
animatedcollapse.addDiv('addcertification', 'fade=0,speed=400,group=pets15')
animatedcollapse.addDiv('certificationdetails', 'fade=0,speed=400,group=pets16')
animatedcollapse.addDiv('sourcesubsource', 'fade=0,speed=400,group=pets17')
animatedcollapse.addDiv('applicantpersonalinfo', 'fade=0,speed=400,group=pets18')
animatedcollapse.addDiv('workpreferenceappdetails', 'fade=0,speed=400,group=pets19')
animatedcollapse.addDiv('applicanteducation', 'fade=0,speed=400,group=pets20')
animatedcollapse.addDiv('applicantprofile', 'fade=0,speed=400,group=pets21')
animatedcollapse.addDiv('applicantsocial', 'fade=0,speed=400,group=pets22')
animatedcollapse.addDiv('moreapplicantdetails', 'fade=0,speed=400,group=pets23')
animatedcollapse.addDiv('applicantbackground', 'fade=0,speed=400,group=pets24')
animatedcollapse.addDiv('eeoinfo', 'fade=0,speed=400,group=pets25')


animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()

</script>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ include file="../common/greybox.jsp" %>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
  String screencode= form.getScreenCode();
  if(screencode == null){
 screencode=Common.APPLICANT_SCREEN;
  }
Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(screencode,user1.getSuper_user_key());
List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
String path = (String)request.getAttribute("filePath");

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%

ResumeSources rc= null;
User usr=null;
if(form.getSourceId() != 0){
rc = BOFactory.getApplicantBO().getResumeSourcesById(String.valueOf(form.getSourceId()));

}
if(form.getVendorId() != 0){
	usr=UserDAO.getUserByVendorIdandTypeVendor(form.getVendorId());
}

%>
<%

User hiringmgr = form.getHiringmgr();
if(hiringmgr == null){
	hiringmgr = new User();
}
Recruiter recruiter = form.getRecruiter();


boolean isApplicantEditAllowed = PermissionBO.isApplicantEditAllowed(user1,hiringmgr.getUserId(),recruiter);
if(isApplicantEditAllowed){
	BOFactory.getApplicantBO().updateIsViewFlag(form.getApplicantId());
}
%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/ajaxtabs/ajaxtabs.css" />

<script type="text/javascript" src="jsp/ajaxtabs/ajaxtabs.js"></script>



</head>


<script language="javascript">


this.reload_on_close=false;

function editJobReq(jb_id){
	
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageretApp);
}

function hideIframe(){
	
	document.getElementById("evalgraph").style.display="none";
}
function chnageImage(url){
	window.open(url, "profileimage","location=0,status=0,scrollbars=1,menubar=0,resizable=1,left=10, top=10,width=400,height=200");
}
function addreferencedata1(url){
	
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Add_reference",user1.getLocale())%>', url,500,600, messageretApp);
}
function editapplicantTalentPool(id){
	
var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicantPoolData&applicantid="+id;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageretApp);
}
function editreference(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Edit_refrerence",user1.getLocale())%>', url,500,600, messageretApp);
}
function startreferenceverification(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Startverification",user1.getLocale())%>',url,600,700, messageretApp);
}
function viewreferencefeedbacks(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Feedbacks",user1.getLocale())%>',url,600,700, messageretApp);
}
function movetoreqtalentpool(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.moveto",user1.getLocale())%>',url,400,600, messageretApp);
}

function approveOffer(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>',url,400,600, messageretApp);
}
function rejectOffer(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>',url,400,600, messageretApp);
}

function sendResumeForScrrening(url){
	
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>',url,500,600, messageretApp);
}
function goodToGo(url){

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_feedback",user1.getLocale())%>',url,messageretApp);
	
}
function releasehold(url){

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.release_hold",user1.getLocale())%>',url,messageretApp);
	
}

function initiateOnboarding(url){

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%>',url, messageretApp);
}

function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}

function printonboarding(){
	var url = "onboarding.do?method=onboardprintview&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	window.open(url, "OnBoarding","location=0,status=0,scrollbars=1,menubar=0,resizable=1");
}


function markfordeletion(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>',url,400,600, messageretApp);
}

function undodelete(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.undodelete",user1.getLocale())%>',url,400,600, messageretApp);
}

function rejectApplicant(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_rejected",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function holdApplicant(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Hold_Applicant",user1.getLocale())%>',url,400,600, messageretApp);
}
function reassignReview(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_assign_review",user1.getLocale())%>',url,400,600, messageretApp);
}
function declineReview(url){
	
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Decline_review",user1.getLocale())%>',url,400,600, messageretApp);
}
function canceloffer(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>',url,400,600, messageretApp);
}
function releaseoffer(url){

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>',url, messageretApp);
}

function initiateoffer(url){

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>',url, messageretApp);
}

function scheduleinterviewround(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Schedule_Interview",user1.getLocale())%>',url,500,600, messageretApp);
}

function onholdapplicant(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%>',url,400,850, messageretApp);
}
function reInitiateOffer(url){

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>',url, messageretApp);
}

function onboardjoined(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%>',url,400,600, messageretApp);
}

function markresigned(url){
	
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Mark.Resigned",user1.getLocale())%>',url,400,600, messageretApp);
}
function convertToUser(url){
	
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.convert.to.user",user1.getLocale())%>',url,500,600, messageretApp);
}
function viewUserProfile(url){
	location.href=url;
}
function rescheduleInterviewround(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reschedule_Interview",user1.getLocale())%>',url,500,600, messageretApp);
}

function resendResumeForScrrening(url){

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Send_profile_for_screening",user1.getLocale())%>',url,500,600, messageretApp);
}

function downloadapplicantdetails(){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=downloadApplicantDetailPdf&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	  location.href =url;

}
function deleteonboardingtask(id,uuid){
	var url = "onboarding.do?method=deleteonboardingtask&taskid="+id+"&taskuuid="+uuid;
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		    document.onBoardingForm.action = url;
   document.onBoardingForm.submit();
	  }
}
function addwatchers(){
	var tu = "watchlist.do?method=watchList&type=APPLICANT&applicantId=<%=form.getApplicantId()%>&reqid=<%=form.getRequitionId()%>";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function openResume(){
	var tu = "applicant.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");
}

function assignmockexam(url){
	
	GB_showCenter('<%=Constant.getResourceStringValue("hr.applicant.assign.exams.Questionnaires",user1.getLocale())%>',url,550,800, messageretApp);
}

function recallapplicant(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.Recall",user1.getLocale())%>",url,300,600, messageretApp);
	
}

function addtag(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.add.tag",user1.getLocale())%>",url,300,600, messageretApp);
	
}


function addapplicantactions(url){

	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%>",url,500,600, messageretApp);
	
}
function editapplicantactions(url){

	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%>",url,500,600, messageretApp);
	
}

function createUser(url){
	
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,300,600, messageretApp);
}

function editApplicantUser(url){
	
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,300,600, messageretApp);
}

function addcomment(url){
	
		GB_showFullScreen('<%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%>',url,messageretApp);
}
function modificationrequest(){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=offermodifficationrequestOnbehalfscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	
	GB_showCenter("<%=Constant.getResourceStringValue("offer.modification.request",user1.getLocale())%>",url,300,600, messageretApp);
}

function acceptofferonbehalf(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.accept_offer",user1.getLocale())%>",url,300,500, messageretApp);
	
}

function declineofferonbehalf(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.decline_offer",user1.getLocale())%>",url,300,500, messageretApp);
	
}
function editapplicant(aid){

	
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageretApp);
	callBoxFancy(url);
}
function messageretApp(){

	callJavascriptOpenfunc('<%=form.getUuid()%>');

			}

function deleteattachment(uuid){	
      var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){

        var url="applicant.do?method=deleteattachmentrelatedtooffer&appuuid=<%=form.getUuid()%>&attachuuid="+uuid;


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    
  }
 setTimeout("pageRefresh()",200);
 
}

function pageRefresh(){
	 location.href=window.location;
}
function processStateChange(){
}
function boxOffHover(box) {
box.style.background='white';
}

function boxOnHover(box) {
box.style.background='#f3f3f3';
}
function savecomment(){

	
	
	/*document.applicantForm.action = "applicant.do?method=addcommenttreeajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>"+"&rurl="+encodeURIComponent(window.location);
	document.applicantForm.submit();*/



	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

dataString = $("#commentadd").serialize();
$.ajax({
	type: 'POST',
  url: "applicant.do?method=addcommenttreeajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>",
data: dataString,
  success: function(data){
  $('#commentspan').html(data);
	completeajx();
  }
});

}



function editcomment(isvisibletoApplicant){
	//var newstring = comment.replace(/^/g,"\n"); // not works here
	//var newstring = comment.replace("^","\n");  //
	
	
	var	comment=document.applicantForm.editcommenthidden.value;

	
	document.applicantForm.comment.value=comment;
	
		document.applicantForm.commentVisible.options.value= isvisibletoApplicant;
		

	

	document.getElementById("submitbutton").style.display = 'none';
	document.getElementById("updatebutton").style.display = 'block';
	}

	function updatecomment(){
	

	var commentId = document.applicantForm.applicantcommenttId.value.trim();
/*
		var comment=document.applicantForm.comment.value.trim();
		var newComment2 = comment.replace(/&/g,'andSymbol');
		var newComment3 = newComment2.replace(/#/g,'hashSymbol');
		
		var newComment = newComment3.replace(/\n/g,'^');

	
	var showalert=false;
	var alertstr = "";
	var comment=document.applicantForm.comment.value.trim();
	if( comment == "" || comment == null){
		alertstr = alertstr +"<%=Constant.getResourceStringValue("aquisition.applicant.enter.comment",user1.getLocale())%>";
		showalert = true;
	}
	 if (showalert){
	     alert(alertstr);
	      return false;
	  }

	document.applicantForm.action = "applicant.do?method=editcommenttree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>&commentId="+commentId+"&comment="+newComment+"&rurl="+encodeURIComponent(window.location);
	  
	document.applicantForm.submit();
*/

	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

dataString = $("#commentadd").serialize();
$.ajax({
	type: 'POST',
  url: "applicant.do?method=editcommenttree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>"+"&commentId="+commentId,
data: dataString,
  success: function(data){
  $('#commentspan').html(data);
  document.applicantForm.comment.value="";
	completeajx();
  }
});
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
        location.reload(true);

	 
	   } 
	}

	
</script>  

<script type="text/javascript">
function openURLdelay(urlf){
      var encodejurl = encodeURIComponent(urlf);
	  location.href = "preload1.jsp?reurl="+encodejurl;
	 

		}
</script>


<body>


<%@include file="/jsp/common/tooltip.jsp" %>
<table width="100%" border="0" class="div">
<tr>
<td width="10%">
		<%
		    String changeimageurl = "applicant.do?method=uploadApplicantPhotoscr&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
			if(form.getProfilePhotoId() == 0){
			%>
			
		<a href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="<%=Constant.getResourceStringValue("hr.user.profile.change.image",user1.getLocale())%>" rev="<%=changeimageurl%>"><img src="jsp/images/user_blank.jpg" width="104" height="104" alt="" /></a>
			<%}else{
				String imgurl = "jsp/talent/profilePhoto.jsp?id="+form.getProfilePhotoId();
			%>

			<a href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="change image" rev="<%=changeimageurl%>"><img src="<%=imgurl%>" width="104" height="104" alt="" /></a>

			<%}%>




</td>
<td valign="top">
<table>
<tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
			<td class="bodytext">
			<% if(isApplicantEditAllowed){%>
			<a href="#" onClick="editapplicant('<%=form.getApplicantId()%>');return false;"><bean:write name="applicantForm" property="fullName"/></a>
			<%}else if(form.getTalentPoolId()>0 && TalentPoolBO.isApplicantFromAssignedTalentPool(user1.getUserId(),form.getApplicantId())){%>
			<a href="#" onClick="editapplicantTalentPool('<%=form.getApplicantId()%>');return false;"><bean:write name="applicantForm" property="fullName"/></a>
			<%}else{%>
			<bean:write name="applicantForm" property="fullName"/>
			<%}%>
			</td>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="applicant_number"/></td> 
		</tr>
<tr>
			
			<td class="bodytext"> 
            
			</td>
		</tr>
		<%
       String tempurl1 = "";
       if(form.getRequisitionName() != null){
		   if(isApplicantEditAllowed){
  // tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+form.getJobTitle()+"</a>";
tempurl1 = "<a href='#' onClick=editJobReq("+"'"+form.getRequitionId() +"')>"+form.getRequisitionName()+"</a>";


		   }else{
			   tempurl1 = form.getRequisitionName();
		   }
	   }else if(form.getTalentPoolName() != null){
		tempurl1 = Common.TALENT_POOL + "("+form.getTalentPoolName()+")";
	   }
		%>
        <tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%></td>
			<td class="bodytext"><%=tempurl1%></td>
			<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.ownername",user1.getLocale())%>  </td>
<%if(!StringUtils.isNullOrEmpty(form.getIsGroup()) && form.getIsGroup().equals("Y")){%>
<td class="bodytext"><img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=(form.getOwnerGroup()==null)?"0":form.getOwnerGroup().getUsergrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=(form.getOwnerGroup()==null)?"":form.getOwnerGroup().getUsergrpName()%></a> </td>
<%}else{%>
<td class="bodytext"><img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=form.getOwnerid()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=form.getOwnerName()%></a> </td>
<%}%>
		</tr>

</table>

</td>
</tr>
</table>
<table>
 <tr>
 <% if(form.getBackurltoapplicantlist()!=null){%>
<td>
<% if((form.getBackurltoapplicantlist()).equals("applicant.do?method=applicantsbyvendor")){%>
<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << <%=Constant.getResourceStringValue("Agency.back_to_allapplicants",user1.getLocale())%>" class="button" ></a>

<%}else if((form.getBackurltoapplicantlist()).equals("applicant.do?method=searchownapplicantinit")){%>
<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << <%=Constant.getResourceStringValue("Agency.back_to_myapplicants",user1.getLocale())%>" class="button" ></a>
<%} else if((form.getBackurltoapplicantlist()).equals("applicantUser.do?method=applicantUsersList")){%>

<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << <%=Constant.getResourceStringValue("Agency.back_to_applicant_users",user1.getLocale())%>" class="button" ></a>

<%} else if((form.getBackurltoapplicantlist()).equals("applicant.do?method=searchownapplicantinitHiringMgr")){%>

<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << <%=Constant.getResourceStringValue("Agency.back_to_applicants",user1.getLocale())%>" class="button" ></a>

<%} else if((form.getBackurltoapplicantlist()).equals("dashboard.do?method=dashboardlist")){%>

<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << <%=Constant.getResourceStringValue("Agency.back_to_dashboard",user1.getLocale())%>" class="button" ></a>

<%} else if((form.getBackurltoapplicantlist()).equals("task.do?method=mypendingtasks")){%>

<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << <%=Constant.getResourceStringValue("Agency.back_to_my_tasks",user1.getLocale())%>" class="button" ></a>


<%} else if((form.getBackurltoapplicantlist()).equals("task.do?method=pendingtasks")){%>

<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << Back to pending task list " class="button"></a>

<%}else{%>

<a href="<%=form.getBackurltoapplicantlist()%>" style="text-decoration: none;"><input type="button" name="backbutton" value=" << <%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%>" class="button" ></a>
<%}%>
</td>
<%}%>
<td>
 <%@ include file="applicantbutton.jsp" %>
</td>
 <td>
 <% 
 if(form.getStatus()!=null && !form.getStatus().equals("D")) {
 if( isApplicantEditAllowed){
  String delurl = request.getContextPath()+"/applicant.do?method=markfordeletion&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();	 
 %>
<div>
    <button type="button" onClick="javascript:markfordeletion('<%=delurl%>');return false;" class="button">
         <%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>
    </button>
</div>
 <%
 }
 }
 %>
 </td>

 </tr>
 </table>

<!-- earlier applicamntbutton tabale was here -->
<%
boolean isSalaryOfferRead = PermissionChecker.isPermissionApplied(Common.SALARY_AND_OFFER_INFORMATION_READ, user1);

%>
 
<br>
<ul id="countrytabs" class="shadetabs">
<li><a href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<!--<li><a href="applicant.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer">
<%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%></a></li>-->
<li><a href="applicant.do?method=applicantlogdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer">
<%=Constant.getResourceStringValue("aquisition.applicant.Interviewlog",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=getcomments&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%></a></li>
<!--<li><a href="applicant.do?method=applicantevaluationcriteraajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Evaluationcriteria",user1.getLocale())%></a></li>-->
<% if(isSalaryOfferRead){%>
<li><a href="applicant.do?method=applicantofferdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer">
<%=Constant.getResourceStringValue("aquisition.applicant.Offerdetails",user1.getLocale())%></a></li>
<%}%>
<li><a href="applicant.do?method=activityList&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%></a></li>
<!--<li><a href="applicant.do?method=applicantReferenceDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.References",user1.getLocale())%></a></li>-->

<li>
<% if(form.getFilterError()>0){%>

<a href="applicant.do?method=filtersExecuteSilents&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><img src='jsp/images/red_start_icon.jpg' border='0' height='10' width='10'/><%=Constant.getResourceStringValue("aquisition.applicant.filter.result",user1.getLocale())%></a>
<%}else{%>
<a href="applicant.do?method=filtersExecuteSilents&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.filter.result",user1.getLocale())%></a>
<%}%>
</li>
<%
if(form.getInitiateJoiningProcess() != null && (form.getInitiateJoiningProcess().equals(Common.ON_BOARDING_STARTED) || form.getInitiateJoiningProcess().equals(Common.ON_BOARDING_COMPLETED))){
%>
<li><a href="onboarding.do?method=onboardCheckList&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.onboarding",user1.getLocale())%></a></li>
<%}%>
</ul>



<div id="countrydivcontainer" style="border:1px solid gray; width:750px; margin-bottom: 1em; padding: 10px">
<%@ include file="applicantdetailscommon.jsp" %>

</div>


<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>

</body>
</html>



