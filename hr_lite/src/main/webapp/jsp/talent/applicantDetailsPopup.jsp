<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
<%@ page import="com.dao.*"%>
<%@ include file="../talent/applicantScreenCollapsableDivs.jsp" %>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_TALENTPOOL,user1.getSuper_user_key());
List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
String path = (String)request.getAttribute("filePath");

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


%>

<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<%
 boolean isSalaryOfferRead = false;
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

//Set activitySet = ApplicantBO.getUniqueDisplaybleAppPageActivityList(form.getApplicantId(),form.getUuid());


List appusractionsList = ApplicantUserBO.getApplicantActionsList(form.getApplicantId(),form.getUuid());

boolean isApplicantEditAllowed = PermissionBO.isApplicantEditAllowed(user1,hiringmgr.getUserId(),recruiter);
%>


<head>
<link rel="stylesheet" type="text/css" href="jsp/ajaxtabs/ajaxtabs.css" />

<script type="text/javascript" src="jsp/ajaxtabs/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

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
	width: 750px;
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

<style type="text/css">
	#chart
	{
       position:relative;
	   display:block; 
		width: 500px;
		height: 250px;
	

	}

   #chart1
	{
		width: 500px;
		height: 150px;
	}

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
	height: 60px;
	background: #333;
	-moz-border-radius: 40px;
	-webkit-border-radius: 40px;
	margin:20px auto;
}
</style>


</head>
<script language="javascript">
this.reload_on_close=false;

function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	
	//parent.setPopTitle1("Applicant");
	//parent.showPopWin(url, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}

function editapplicantTalentPool(id){
var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicantPoolData&applicantid="+id;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}

function addreferencedata1(url){
	//parent.setPopTitle1("Add refrerence");
	//parent.showPopWin(url, 600, 500, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Add_reference",user1.getLocale())%>', url,500,600, messageret);
}
function editreference(url){
	//parent.setPopTitle1("Edit reference");
	//parent.showPopWin(aid, 600, 500, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Edit_refrerence",user1.getLocale())%>', url,500,600, messageret);
}
function startreferenceverification(url){
	//parent.setPopTitle1("Start Verification");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Startverification",user1.getLocale())%>',url,600,700, messageret);
}
function viewreferencefeedbacks(url){
	//parent.setPopTitle1("Feedbacks");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Feedbacks",user1.getLocale())%>',url,600,700, messageret);
}

function movetoreqtalentpool(url){
	//parent.setPopTitle1("Feedbacks");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.moveto",user1.getLocale())%>',url,400,600, messageret);
}


function approveOffer(url){
	//parent.setPopTitle1("Approve offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>',url,400,600, messageret);
}
function markfordeletion(url){
	//parent.setPopTitle1("Approve offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>',url,400,600, messageret);
}
function undodelete(url){
	//parent.setPopTitle1("Approve offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.undodelete",user1.getLocale())%>',url,400,600, messageret);
}

function rejectOffer(url){
	//parent.setPopTitle1("Reject offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>',url,400,600, messageret);
}

function sendResumeForScrrening(url){
	//parent.setPopTitle1("Send profile for screening");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>',url,500,700, messageret);
}
function goodToGo(url){
	//parent.setPopTitle1("Applicant feedback");
	//parent.showPopWin(aid, 800, 600, null,true);
	//GB_showFullScreen('Applicant feedback',url,messageret);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_feedback",user1.getLocale())%>',url,700,850, messageret);
	
}

function releasehold(url){
	//parent.setPopTitle1("Applicant feedback");
	//parent.showPopWin(aid, 800, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.release_hold",user1.getLocale())%>',url,messageret);
	
}

function rejectApplicant(aid){
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_rejected",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function holdApplicant(url){
	//parent.setPopTitle1("Hold Applicant");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Hold_Applicant",user1.getLocale())%>',url,600,700, messageret);
}
function reassignReview(url){
	//parent.setPopTitle1("Re-assign review");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_assign_review",user1.getLocale())%>',url,400,600, messageret);
}
function declineReview(url){
	//parent.setPopTitle1("Decline review");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Decline_review",user1.getLocale())%>',url,400,600, messageret);
}
function canceloffer(url){
	//parent.setPopTitle1("Cancel offer");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>',url,400,600, messageret);
}
function releaseoffer(url){
	//parent.setPopTitle1("Release offer");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>',url, messageret);
}

function initiateoffer(url){
	//parent.setPopTitle1("Initiate offer");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>',url, messageret);
}

function scheduleinterviewround(url){
	//alert(document.getElementById('chart'));
//	document.getElementById('chart').style.display = 'none';  
//	document.getElementById('chart1').style.display = 'none';  
	//parent.setPopTitle1("Schedule Interview");
	//parent.showPopWin(aid, 850, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Schedule_Interview",user1.getLocale())%>',url,700,850, messageret);
}

function onholdapplicant(url){
	//parent.setPopTitle1("On hold");
	//parent.showPopWin(aid, 850, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%>',url,400,850, messageret);
}
function reInitiateOffer(url){
	//parent.setPopTitle1("Re-Initiate offer");
	//parent.showPopWin(aid, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>',url, messageret);
}

function onboardjoined(url){
	//parent.setPopTitle1("On board - Joined");
	//parent.showPopWin(aid, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%>',url,400,600, messageret);
}

function markresigned(url){
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Mark.Resigned",user1.getLocale())%>',url,400,600, messageret);
}

function initiateOnboarding(url){
	//GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%>',url,600,800, messageret);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%>',url, messageret);
}
function rescheduleInterviewround(aid){
	//parent.setPopTitle1("Reschedule Interview");
	//parent.showPopWin(aid, 850, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reschedule_Interview",user1.getLocale())%>',aid,700,850, messageret);

}

function resendResumeForScrrening(aid){
	//parent.setPopTitle1("Re-Send profile for screening");
	//parent.showPopWin(aid, 600, 500, null,true);

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Send_profile_for_screening",user1.getLocale())%>',aid,500,600, messageret);

}

function createUser(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,300,600, messageret);
}

function editApplicantUser(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,200,400, messageret);
}

function addcomment(url){
		GB_showFullScreen('<%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%>',url,messageret);
}

function downloadapplicantdetails(){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=downloadApplicantDetailPdf&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	  location.href =url;

}
function messageret(){
	window.location.reload();

			}

function boxOffHover(box) {
box.style.background='white';
}

function boxOnHover(box) {
box.style.background='#f3f3f3';
}
function openResume(){
	var tu = "applicant.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");

  
}
</script>  

<script type="text/javascript">
function openURLdelay(urlf){
      var encodejurl = encodeURIComponent(urlf);
	  location.href = "preload1.jsp?reurl="+encodejurl;
	 

		}
</script>
<script type="text/javascript">
function validateUser(id){
	var hidd = 'taskownerhiddenname_'+id;
	var inname = 'taskowner_'+id;
	document.onBoardingForm[inname].value=document.onBoardingForm[hidd].value;
}



function deleteonboardingtask(id,uuid){
	var url = "onboarding.do?method=deleteonboardingtask&taskid="+id+"&taskuuid="+uuid;
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		    document.onBoardingForm.action = url;
   document.onBoardingForm.submit();
	  }
}

function recallapplicant(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.Recall",user1.getLocale())%>",url,300,600, messageret);
	
}

function addtag(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.add.tag",user1.getLocale())%>",url,300,600, messageret);
	
}
function addapplicantactions(url){

	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%>",url,500,600, messageret);
	
}
function editapplicantactions(url){

	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%>",url,500,600, messageret);
	
}

function modificationrequest(){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=offermodifficationrequestOnbehalfscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	
	GB_showCenter("<%=Constant.getResourceStringValue("offer.modification.request",user1.getLocale())%>",url,300,600, messageret);
}

function acceptofferonbehalf(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.accept_offer",user1.getLocale())%>",url,300,500, messageret);
	
}

function declineofferonbehalf(url){

	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.decline_offer",user1.getLocale())%>",url,300,500, messageret);
	
}



function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}

function updatecomment(){
	

	var commentId = document.applicantForm.applicantcommenttId.value.trim();
//	alert(commentId);
	
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

	document.applicantForm.action = "applicant.do?method=editcomment&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>+&commentId="+commentId;
	  
	document.applicantForm.submit();
}
function savecomment(){
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
	document.applicantForm.action = "applicant.do?method=addcommentajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	document.applicantForm.submit();
}
function deletecomment(url){
	 // var doyou = confirm("<%=Constant.getResourceStringValue("aquisition.applicant.delete.comment",user1.getLocale())%>");
	// if (doyou == true){
			//alert(url);
			document.applicantForm.action = url;
			document.applicantForm.submit();
	 //  } 
	}
function editcomment(comment){

	document.applicantForm.comment.value=comment;

	document.getElementById("submitbutton").style.display = 'none';
	document.getElementById("updatebutton").style.display = 'block';
	}
function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
        location.reload(true);

	 
	   } 
	}
function reset(){
	document.applicantForm.comment.value="";
	setTimeout ( "document.applicantForm.comment.focus(); ", 200 );
}
function isIntCheck(value){
	if(isInteger(value)){
	}else{
		alert("Only Integer is allowed");
		return false;
	}
}

function isInteger (s) 
{ 
var i; 

if (isEmpty(s)) 
if (isInteger.arguments.length == 1) return 0; 
else return (isInteger.arguments[1] == true); 

for (i = 0; i < s.length; i++) 
{ 
var c = s.charAt(i); 

if (!isDigit(c)) return false; 
} 

return true; 
} 

function isEmpty(s) 
{ 
return ((s == null) || (s.length == 0)) 
} 

function isDigit (c) 
{ 
return ((c >= "0") && (c <= "9")) 
} 




</script>


<body>
<%@include file="/jsp/common/tooltip.jsp" %>

<table width="100%">
<tr>
<td width="10%">

		<%
		    String changeimageurl = "applicant.do?method=uploadApplicantPhotoscr&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
			if(form.getProfilePhotoId() == 0){
			%>
			
		<a href="jsp/images/user_blank.jpg" rel="lightbox" title="<%=Constant.getResourceStringValue("hr.user.profile.change.image",user1.getLocale())%>" rev="<%=changeimageurl%>"><img src="jsp/images/user_blank.jpg" width="104" height="104" alt="" /></a>
			<%}else{
				String imgurl = "jsp/talent/profilePhoto.jsp?id="+form.getProfilePhotoId();
			%>

			<a href="<%=imgurl%>" rel="lightbox" title="change image" rev="<%=changeimageurl%>"><img src="<%=imgurl%>" width="104" height="104" alt="" /></a>

			<%}%>
</td>
<td width="40%">
<table>
 <tr>

			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
			<td class="bodytext">
			
			<%if(form.getTalentPoolId()>0 && TalentPoolBO.isApplicantFromAssignedTalentPool(user1.getUserId(),form.getApplicantId())){%>
			<a href="#" onClick="editapplicantTalentPool('<%=form.getApplicantId()%>');return false;"><bean:write name="applicantForm" property="fullName"/></a>
			<%}else{%>
			<bean:write name="applicantForm" property="fullName"/>
			<%}%>
			</td>

			<td class="bodytext">
			<!--<a href="#" onClick="window.open('applicant.do?method=activityList&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%></a> -->
 
		<% if(form.getTagId()>0){%>
           <%=Constant.getResourceStringValue("aquisition.applicant.tag",user1.getLocale())%> : <bean:write name="applicantForm" property="tagName"/> 
           <a href="#" onClick="javascript:addtag('<%=request.getContextPath()%>/applicant.do?method=addapplicanttagscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&tagid=<%=form.getTagId()%>')"> <%=Constant.getResourceStringValue("aquisition.applicant.change.tag",user1.getLocale())%></a>

			<%}else{%>
			<a href="#" onClick="javascript:addtag('<%=request.getContextPath()%>/applicant.do?method=addapplicanttagscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>')"> <%=Constant.getResourceStringValue("aquisition.applicant.add.tag",user1.getLocale())%></a>
			<%}%>

			</td>
		</tr>
<tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="applicant_number"/></td> 
			<td class="bodytext"> 

			<%
				String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+form.getApplicantId()+"&action=plain_attachment&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
			%>
			
				<a href="#" onClick="addattachmentscr('<%=attachurl%>')">attachments</a>
			
			
			</td>
		</tr>
		<%
       String tempurl1 = "";
       if(form.getJobTitle() != null){
   tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800"+"'"+")>"+form.getJobTitle()+"</a>";
	   }else if(form.getTalentPoolName() != null){
		tempurl1 = Common.TALENT_POOL + "("+form.getTalentPoolName()+")";
	   }
		%>

		
		<tr>
			<td width="150" class="bodytext"> <%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%> </td>
			<td class="bodytext"><%=tempurl1%></td>
			<td>
				<!-- can add link here -->
			</td>

		</tr>

		<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.ownername",user1.getLocale())%>  </td>
<%if(!StringUtils.isNullOrEmpty(form.getIsGroup()) && form.getIsGroup().equals("Y")){%>
<td class="bodytext"><img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=(form.getOwnerGroup()==null)?"0":form.getOwnerGroup().getUsergrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=(form.getOwnerGroup()==null)?"":form.getOwnerGroup().getUsergrpName()%></a> </td> 
<%}else if(form.getOwnerName() !=null){%>
<td class="bodytext"><img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=form.getOwnerid()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=form.getOwnerName()%></a> </td>
<%}%>

</tr>

</table>
</td>

<td valign="top">
 
 </td>
 </tr>
 </table>
 </fieldset>
</td>
</tr>
</table>

 <table>
 <tr> 
 <%@ include file="applicantbutton.jsp" %> 
 <td>
 <% 
 if(form.getStatus()!=null && !form.getStatus().equals("D")) {
 if(PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter)){
  String delurl = request.getContextPath()+"/applicant.do?method=markfordeletion&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();	 
 %>
<div>
    <button type="button" onClick="javascript:markfordeletion('<%=delurl%>');return false;">
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

<table>
<tr>
<td valign="top" width="60%">

<ul id="countrytabs" class="shadetabs">
<li><a href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<!--<li><a href="applicant.do?method=additionalDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Additionaldetails",user1.getLocale())%></a></li>-->
<!-- <li><a href="applicant.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"> <%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%></a></li> -->
<!--<li><a href="applicant.do?method=applicantlogdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Interviewlog",user1.getLocale())%></a></li>-->
<li><a href="applicant.do?method=getcomments&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%></a></li>
<!--<li><a href="applicant.do?method=applicantevaluationcriteraajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Evaluationcriteria",user1.getLocale())%></a></li>-->
<!--<li><a href="applicant.do?method=applicantofferdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Offerdetails",user1.getLocale())%></a></li>-->
<li><a href="applicant.do?method=activityList&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicantReferenceDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.References",user1.getLocale())%></a></li>
<%
if(form.getInitiateJoiningProcess() != null && (form.getInitiateJoiningProcess().equals(Common.ON_BOARDING_STARTED) || form.getInitiateJoiningProcess().equals(Common.ON_BOARDING_COMPLETED))){
%>
<li><a href="onboarding.do?method=onboardCheckList&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.onboarding",user1.getLocale())%></a></li>
<%}%>
</ul>



<div id="countrydivcontainer" style="border:1px solid gray; width:850px; margin-bottom: 1em; padding: 10px">
<%@ include file="applicantdetailscommon.jsp" %>

</div>

</td>
<td valign="top">
<iframe frameborder="0" height="800px" width="600px" src="applicant.do?method=getEvaluationGraph&uuid=<%=form.getUuid()%>&applicantId=<%=form.getApplicantId()%>"></iframe>

</td>
</tr>
</table>
<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>


	
</body>
</html>

