
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<script language="javascript">
function editscreen(code){
	var url = "<%=request.getContextPath()%>/screenfield.do?method=editscreenfield&screencode="+code;
	GB_showFullScreen('Edit Screen fields',url, messageret);
}
function editscreenReq(code){
	var url = "<%=request.getContextPath()%>/screenfield.do?method=editscreenfieldReq&screencode="+code;
	GB_showFullScreen('Edit Screen fields',url, messageret);
}

function applicantFormMap(screenName){
	var url = "<%=request.getContextPath()%>/variablemap.do?method=requisionFormMap&formcode="+screenName+"&formname="+screenName;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.variable.mapping.editformmapping",user1.getLocale())%>',url,500,750, messageret);
}

function showmessage(returnval) { 
	//window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}



</script>


<body>

<% if(!StringUtils.isNullOrEmpty(user1.getPackagetaken()) && Constant.isPackageContainFunction(user1.getPackagetaken(),"screenSetting")){%>

<table width="1000">
<tr>
<td width="33%">

 <fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN')">Details tab</a>
</td>
</tr>
<tr>
</tr>
<tr>
<td>
<br><a class="button"  href="#" onClick="editscreen('APPLICANT_SCREEN_WORK_EXP')">Work experience/Skill tab</a>
</td>
</tr>
<tr>
<td>
<br><a class="button"  href="#" onClick="editscreen('APPLICANT_SCREEN_EDUCATION')">Education tab</a><br><br>
</td>
</tr>
</table>
</fieldset>
</td>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.referral",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_REFERRAL')">Details tab</a>
</td>
</tr>
<tr>

</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_WORK_EXP_REFERRAL')">Work experience/Skill tab</a>
</td>
</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_EDUCATION_REFERRAL')">Education tab</a><br><br>
</td>
</tr>
</table>
</fieldset>
</td>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.agency",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_AGENCY')">Details tab</a>
</td>
</tr>
<tr>

</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_WORK_EXP_AGENCY')">Work experience/Skill tab</a>
</td>
</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_EDUCATION_AGENCY')">Education tab</a><br><br>
</td>
</tr>
</table>
</fieldset>
</td>
</tr>
<tr>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.talentpool",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_TALENTPOOL')">Details tab</a>
</td>
</tr>
<tr>

</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_WORK_EXP_TALENTPOOL')">Work experience/Skill tab</a>
</td>
</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_EDUCATION_TALENTPOOL')">Education tab</a><br><br>
</td>
</tr>
</table>
</fieldset>
</td>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.talentpool.external",user1.getLocale())%></b></legend>

<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_TALENTPOOL_EXTERNAL')">Details tab</a>
</td>
</tr>
<tr>

</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL')">Work experience/Skill tab</a>
</td>
</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL')">Education tab</a><br><br>
</td>
</tr>
</table>
</fieldset>
</td>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.external",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_EXTERNAL')">Details tab</a>
</td>
</tr>
<tr>

</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_WORK_EXP_EXTERNAL')">Work experience/Skill tab</a>
</td>
</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_EDUCATION_EXTERNAL')">Education tab</a><br><br>
</td>
</tr>
</table>
</fieldset>
</td>
</tr>
<tr>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.profile",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_PROFILE')">Details tab</a>
</td>
</tr>
<tr>

</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_WORK_EXP_PROFILE')">Work experience/Skill tab</a>
</td>
</tr>
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN_EDUCATION_PROFILE')">Education tab</a><br><br>
</td>
</tr>
</table>
</fieldset>
</td>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("requisition.screen",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreenReq('REQUISITION_SCREEN')"><%=Constant.getResourceStringValue("requisition.screen.data",user1.getLocale())%></a><br><br>
</td>
</tr>
<tr>
</tr>

</table>
</fieldset>
</td>
<td width="33%">
</td>
</tr>

</table>
<%}else{%>

<table width="1000">
<tr>
<td width="33%">

 <fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreen('APPLICANT_SCREEN')">applicant screen</a>
</td>
</tr>
</table>
</td>
<td width="33%">
<fieldset><legend><b><%=Constant.getResourceStringValue("requisition.screen",user1.getLocale())%></b></legend>
<table width="100%">
<tr>
<td>
<br><a class="button" href="#" onClick="editscreenReq('REQUISITION_SCREEN')">requisition screen</a>
</td>
</tr>
</table>
</td>
<td width="33%">
</td>
</tr>
</table>

<%}%>

</body>