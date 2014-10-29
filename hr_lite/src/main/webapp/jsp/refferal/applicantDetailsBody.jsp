<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.dao.*"%>


<%
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_REFERRAL,user1.getSuper_user_key());
List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);


String datepattern = Constant.getValue("defaultdateformat");

String path = (String)request.getAttribute("filePath");
String backurl = request.getHeader("Referer");
//request.getContextPath()+"/refops.do?method=searchownapplicantinit";

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



<script language="javascript">
this.reload_on_close=false;

function back(url){
	  //document.applicantForm.action = url;
	  //document.applicantForm.submit();
	  location.href = document.referrer;//"refops.do?method=searchownapplicantinit";
	  
}
</script>  

<script type="text/javascript">
function openURLdelay(urlf){
      var encodejurl = encodeURIComponent(urlf);
	  location.href = "preload1.jsp?reurl="+encodejurl;
	 

		}
function openResume(){
	var tu = "refops.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
        //var url = "jsp/common/preload1.jsp?reurl="+encodeURIComponent(tu);
  		window.open(tu,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");

  
}

function downloadapplicantdetails(){
	
	var url = "<%=request.getContextPath()%>/refops.do?method=downloadApplicantDetailPdf&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	location.href =url;

}

function jobDetails(id){
	//alert("Not implemented yet, need a job details page for not looged in user");
	
	var url = "applicantjob.do?method=jobdetails&reqid="+id;
  window.open(url,  'JobRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=no');
}

function savecomment(){

	  $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

dataString = $("#commentadd").serialize();
$.ajax({
	type: 'GET',
  url: "refops.do?method=addcomment&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>",
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
</script>

</head>
<body>


<br><a class="button" href="#" onClick="back('<%=backurl%>')"> << <%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%></a>
<br>
<br>

<%@include file="/jsp/common/tooltip.jsp" %>

<div class="div">
<table>

			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="fullName"/></td>
		</tr>
<tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.no",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="applicantId"/></td>
		</tr>
		<% 
       		String tempurl1 = "";
      		if(form.getJobTitle() != null){
   				
   			tempurl1 = "<a href='#' onClick=window.open("+"'"+"refjob.do?method=jobdetailsforReferral&reqid="+form.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=800"+"'"+")>"+form.getJobTitle()+"</a>";

	   		
      		%>
        <tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>  </td>
			<!--  <td class="bodytext"><a href="refjob.do?method=jobdetailsforReferral&reqid=<%=form.getRequitionId()%>"><%=form.getJobTitle()%></a></td>-->
			<td><a href="#" onClick="javascript:jobDetails('<%=EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))%>');return false;"><%=form.getJobTitle()%></a></td>
		</tr>
        <%}else if(form.getTalentPoolName() != null){
		tempurl1 = Common.TALENT_POOL + "("+form.getTalentPoolName()+")";
		 %>
        <tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>  </td>
			<!--  <td class="bodytext"><a href="refjob.do?method=jobdetailsforReferral&reqid=<%=form.getRequitionId()%>"><%=form.getJobTitle()%></a></td>-->
			<td><%=tempurl1%></td>
		</tr>
        <%}%>
	  
</table>
<br>
<table>
<tr>
<td valign="top" width="60%">

<ul id="countrytabs" class="shadetabs">
<li><a href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<li><a href="refops.do?method=getcomments&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%></a></li>
<!--  <li><a href="refops.do?method=additionalDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Additionaldetails",user1.getLocale())%></a></li>-->
<!--<li><a href="refops.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%></a></li>-->
<!--<li><a href="refops.do?method=applicantlogdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Interviewlog",user1.getLocale())%></a></li>-->

</ul>



<div id="countrydivcontainer" style="border:1px solid gray; width:850px; margin-bottom: 1em; padding: 10px" class="div">
<!-- earlier was call to applicatdetailscommon.jsp-->
<%
User hiringmgr = BOFactory.getJobRequistionBO().getHiringManagerByReqId(form.getRequitionId());
%>

<%@ include file="../talent/applicantdetailscommon.jsp" %>

<!-- earlier was call to applicatdetailscommon.jsp-->
</div>
</td>
<td valign="top">

</td>
</tr>
</table>
<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>

</div>

	
</body>
</html>

