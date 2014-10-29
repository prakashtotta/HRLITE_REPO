<%@ include file="../common/yahooincludes.jsp" %>

<%@ include file="../common/greybox.jsp" %>


<%@ include file="../talent/applicantScreenCollapsableDivs.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
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

System.out.println("pathpathpathpathpathpathpathpathpathpath"+path);


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

//Set activitySet = ApplicantBO.getUniqueDisplaybleAppPageActivityList(form.getApplicantId(),form.getUuid());


List appusractionsList = ApplicantUserBO.getApplicantActionsList(form.getApplicantId(),form.getUuid());

boolean isApplicantEditAllowed = PermissionBO.isApplicantEditAllowed(user1,hiringmgr.getUserId(),recruiter);
boolean isSalaryOfferRead = PermissionChecker.isPermissionApplied(Common.SALARY_AND_OFFER_INFORMATION_READ, user1);

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
#chart_scoring
	{
       position:relative;
	   display:block; 
		width: 500px;
		height: 250px;
	

	}

	#chartaveragescore
	{
		width: 500px;
		height: 100px;
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

function hideIframe(){
	
	document.getElementById("evalgraph").style.display="none";
}

function editapplicant(aid){

	hideIframe();
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}

function editapplicantTalentPool(id){
hideIframe();
var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicantPoolData&applicantid="+id;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}

function addreferencedata1(url){
	hideIframe();

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Add_reference",user1.getLocale())%>', url,500,600, messageret);
}
function editreference(url){
	hideIframe();

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Edit_refrerence",user1.getLocale())%>', url,500,600, messageret);
}
function startreferenceverification(url){
	hideIframe();

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Startverification",user1.getLocale())%>',url,600,700, messageret);
}

function assignmockexam(url){
	hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("hr.applicant.assign.exams.Questionnaires",user1.getLocale())%>',url,600,1200, messageret);
}



function viewreferencefeedbacks(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Feedbacks",user1.getLocale())%>',url,600,700, messageret);
}

function movetoreqtalentpool(url){
	hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.moveto",user1.getLocale())%>',url,400,600, messageret);
}


function approveOffer(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>',url,400,600, messageret);
}
function markfordeletion(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>',url,400,600, messageret);
}
function undodelete(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.undodelete",user1.getLocale())%>',url,400,600, messageret);
}

function rejectOffer(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>',url,400,600, messageret);
}

function sendResumeForScrrening(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>',url,500,700, messageret);
}
function goodToGo(url){
	hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_feedback",user1.getLocale())%>',url,700,850, messageret);
	
}

function releasehold(url){
hideIframe();
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.release_hold",user1.getLocale())%>',url,messageret);
	
}
function downloadapplicantdetails(){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=downloadApplicantDetailPdf&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	  location.href =url;

}

function rejectApplicant(aid){
	hideIframe();
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_rejected",user1.getLocale())%>");
	parent.showPopWin(aid, 700, 600, null,true);
}
function holdApplicant(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Hold_Applicant",user1.getLocale())%>',url,600,700, messageret);
}
function reassignReview(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_assign_review",user1.getLocale())%>',url,400,600, messageret);
}
function declineReview(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Decline_review",user1.getLocale())%>',url,400,600, messageret);
}
function canceloffer(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>',url,400,600, messageret);
}
function releaseoffer(url){
hideIframe();
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>',url, messageret);
}

function initiateoffer(url){
hideIframe();
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>',url, messageret);
}

function scheduleinterviewround(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Schedule_Interview",user1.getLocale())%>',url,700,850, messageret);
}

function onholdapplicant(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%>',url,400,850, messageret);
}
function reInitiateOffer(url){
hideIframe();
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>',url, messageret);
}

function onboardjoined(url){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%>',url,400,600, messageret);
}

function markresigned(url){
	hideIframe();
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Mark.Resigned",user1.getLocale())%>',url,400,600, messageret);
}

function initiateOnboarding(url){
	hideIframe();
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%>',url, messageret);
}
function rescheduleInterviewround(aid){
hideIframe();
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Reschedule_Interview",user1.getLocale())%>',aid,700,850, messageret);

}

function resendResumeForScrrening(aid){
	hideIframe();

	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_Send_profile_for_screening",user1.getLocale())%>',aid,500,600, messageret);

}

function createUser(url){
	hideIframe();
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,300,600, messageret);
}

function editApplicantUser(url){
	hideIframe();
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%>",url,300,600, messageret);
}

function addcomment(url){
	hideIframe();
		GB_showFullScreen('<%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%>',url,messageret);
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
function messageret(){
	window.location.reload();

			}


function boxOffHover(box) {
box.style.background='white';
}

function boxOnHover(box) {
box.style.background='#f3f3f3';
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
hideIframe();
	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.Recall",user1.getLocale())%>",url,300,600, messageret);
	
}

function addtag(url){
hideIframe();
	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.add.tag",user1.getLocale())%>",url,200,400, messageret);
	
}
function addapplicantactions(url){
hideIframe();
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%>",url,500,600, messageret);
	
}
function editapplicantactions(url){
	hideIframe();
	
 
	GB_showCenter("<%=Constant.getResourceStringValue("hr.applicant.edit.action",user1.getLocale())%>",url,500,600, messageret);
	
}

function modificationrequest(){
	hideIframe();
	var url = "<%=request.getContextPath()%>/applicant.do?method=offermodifficationrequestOnbehalfscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	
	GB_showCenter("<%=Constant.getResourceStringValue("offer.modification.request",user1.getLocale())%>",url,300,600, messageret);
}

function acceptofferonbehalf(url){
hideIframe();
	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.accept_offer",user1.getLocale())%>",url,300,500, messageret);
	   
}

function declineofferonbehalf(url){
hideIframe();
	GB_showCenter("<%=Constant.getResourceStringValue("aquisition.applicant.decline_offer",user1.getLocale())%>",url,300,500, messageret);
	
}



function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}

function backtoapplicantlist(){

document.applicantForm.action ="applicant.do?method=applicantsbyvendor";
document.applicantForm.submit();
}

function updatecomment(){
	

	var commentId = document.applicantForm.applicantcommenttId.value.trim();
//	alert(commentId);
		var comment=document.applicantForm.comment.value.trim();
		var newComment2 = comment.replace(/&/g,'andSymbol');
		var newComment3 = newComment2.replace(/#/g,'hashSymbol');
		
		var newComment = newComment3.replace(/\n/g,'^');
	//alert(newComment);
	
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

	document.applicantForm.action = "applicant.do?method=editcomment&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>&commentId="+commentId+"&comment="+newComment;;
	  
	document.applicantForm.submit();
}
function savecomment(){
	var showalert=false;
	var alertstr = "";

	var comment=document.applicantForm.comment.value.trim();
	var newComment2 = comment.replace(/&/g,'andSymbol');
	var newComment3 = newComment2.replace(/#/g,'hashSymbol');
	var newComment = newComment3.replace(/\n/g,'^');

	if( comment == "" || comment == null){
		alertstr = alertstr +"<%=Constant.getResourceStringValue("aquisition.applicant.enter.comment",user1.getLocale())%>";
		showalert = true;
	}
	 if (showalert){
	     alert(alertstr);
	      return false;
	  }
	document.applicantForm.action = "applicant.do?method=addcommentajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>&comment="+newComment;
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
function editcomment(comment,isvisibletoApplicant){
	//var newstring = comment.replace(/^/g,"\n"); // not works here
	//var newstring = comment.replace("^","\n");  //
	
	//alert(comment[2])
	var commentlength=comment.length;
	var newstring="";

	for(var l=0;l<commentlength;l++){
		if(comment[l] != "^"){
			newstring=newstring+comment[l];
		}else if(comment[l] =="^"){
			newstring=newstring+"\n";

		}
	}
	
	document.applicantForm.comment.value=newstring;
	if(isvisibletoApplicant == 'Y'){
		document.getElementById('isappvisible').checked = true;
		
	}
	

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

<table width="100%" border="0">
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
<table style='table-layout:fixed'>
 <tr>

			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
			<td class="bodytext">		
			<bean:write name="applicantForm" property="fullName"/>			
			</td>

			<td class="bodytext">
            <%=Constant.getResourceStringValue("aquisition.applicant.tag",user1.getLocale())%> : <bean:write name="applicantForm" property="tagName"/> 
          
			</td>
		</tr>
<tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="applicantId"/></td> 
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

		   if(isApplicantEditAllowed){
   tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800"+"'"+")>"+form.getJobTitle()+"</a>";
           }else{
			   tempurl1 = form.getJobTitle();
		   }

	   }else if(form.getTalentPoolName() != null){
		tempurl1 = Common.TALENT_POOL + "("+form.getTalentPoolName()+")";
	   }
		%>

		
		<tr>
			<td width="150" class="bodytext"> <%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%> </td>
			<td class="bodytext"><%=tempurl1%></td>
			<td>
			
			</td>

		</tr>

		<tr>
<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.ownername",user1.getLocale())%>  </td>
<%if(!StringUtils.isNullOrEmpty(form.getIsGroup()) && form.getIsGroup().equals("Y")){%>
<td class="bodytext"><img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=(form.getOwnerGroup()==null)?"0":form.getOwnerGroup().getUsergrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=(form.getOwnerGroup()==null)?"":form.getOwnerGroup().getUsergrpName()%></a> </td> 
<%}else{%>
<td class="bodytext"><img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=form.getOwnerid()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=form.getOwnerName()%></a> </td>
<%}%>
<td class="bodytext">
<a  href="#" onClick="addwatchers();return false;"><%=Constant.getResourceStringValue("Requisition.watchers",user1.getLocale())%></a></td>
</tr>

</table>
</td>

<td valign="top">
</td>
</tr>
</table>
 

<table>
<tr>


<td valign="top" width="60%">

<ul id="countrytabs" class="shadetabs">
<li><a href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<!--<li><a href="applicant.do?method=additionalDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Additionaldetails",user1.getLocale())%></a></li>-->

<li><a href="applicant.do?method=applicantlogdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Interviewlog",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=getcomments&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%></a></li>
<!--<li><a href="applicant.do?method=applicantevaluationcriteraajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Evaluationcriteria",user1.getLocale())%></a></li>-->
<li><a href="applicant.do?method=applicantofferdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Offerdetails",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=activityList&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicantReferenceDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.References",user1.getLocale())%></a></li>
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



<div id="countrydivcontainer" style="border:1px solid gray; width:850px; margin-bottom: 1em; padding: 10px">
<%@ include file="applicantdetailscommon.jsp" %>

</div>

</td>
<td valign="top">
<iframe id="evalgraph" frameborder="0" height="1200px" width="600px" src="applicant.do?method=getEvaluationGraph&uuid=<%=form.getUuid()%>&applicantId=<%=form.getApplicantId()%>"></iframe>


</td>
</tr>



</table>
<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>

	
</body>
</html>

