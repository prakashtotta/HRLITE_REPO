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
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
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
boolean isSalaryOfferRead =false;
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
%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/ajaxtabs/ajaxtabs.css" />

<script type="text/javascript" src="jsp/ajaxtabs/ajaxtabs.js"></script>



</head>


<script language="javascript">


this.reload_on_close=false;


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






function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}







function viewUserProfile(url){
	location.href=url;
}


function downloadapplicantdetails(){
	
	var url = "<%=request.getContextPath()%>/agencyops.do?method=downloadApplicantDetailPdf&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	  location.href =url;

}


function openResume(){
	
	var tu = "agencyops.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	
  // var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(tu,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");
}







function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/agencyops.do?method=editapplicant&applicantId="+aid;
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>', url, messageretApp1);

   callBoxFancy(url);
}
function addcomment(url){
	
		GB_showFullScreen('<%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%>',url,messageretApp);
}

function messageretApp(){

	callJavascriptOpenfunc('<%=form.getUuid()%>');

			}


function messageretApp1(){
	//location.href=window.location;
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

	  $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

dataString = $("#commentadd").serialize();
$.ajax({
	type: 'GET',
  url: "agencyops.do?method=addcomment&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>",
data: dataString,
  success: function(data){
  $('#commentspan').html(data);
	completeajx();
  }
});

}

function completeajx(){
	 $.unblockUI();
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

	document.applicantForm.action = "applicant.do?method=editcommenttree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>&commentId="+commentId+"&comment="+newComment+"&rurl="+encodeURIComponent(window.location);
	  
	document.applicantForm.submit();
}


function jobDetails(id){
	//alert("Not implemented yet, need a job details page for not looged in user");
	
	var url = "agjob.do?method=jobdetailsforAgency&reqid="+id;
  window.open(url,  'JobRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=no');
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
<table width="100%" border="0">
<tr>
<td width="10%">
		<%
		    String changeimageurl = "agencyops.do?method=uploadApplicantPhotoscr&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
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
			
			<a href="#" onClick="editapplicant('<%=form.getApplicantId()%>');return false;"><bean:write name="applicantForm" property="fullName"/></a>
			
			</td>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="applicant_number"/></td> 
		</tr>
<tr>
			
			<td class="bodytext"> 
            
			</td>
		</tr>
		
  
        <tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%></td>
			<td class="bodytext"><a href="#" onClick="javascript:jobDetails('<%=EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))%>');return false;">
		<%=form.getJobTitle()%></font></td>
			<td width="30%"class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.ownername",user1.getLocale())%>  </td>
<%if(!StringUtils.isNullOrEmpty(form.getIsGroup()) && form.getIsGroup().equals("Y")){%>
<td class="bodytext"><%=(form.getOwnerGroup()==null)?"":form.getOwnerGroup().getUsergrpName()%> </td>
<%}else{%>
<td class="bodytext"><%=form.getOwnerName()%> </td>
<%}%>
		</tr>

</table>

</td>
</tr>
</table>


<!-- earlier applicamntbutton tabale was here -->
 
<br>
<ul id="countrytabs" class="shadetabs">
<li><a class="button" href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<li><a class="button" href="agencyops.do?method=getcomments&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%></a></li>

</ul>



<div id="countrydivcontainer" style="border:1px solid gray; width:850px; margin-bottom: 1em; padding: 10px">
<%@ include file="../talent/applicantdetailscommon.jsp" %>

</div>


<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>

</body>
</html>



