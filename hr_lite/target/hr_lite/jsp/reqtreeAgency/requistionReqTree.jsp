<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />

	<title>Applicants</title>
<%@ include file="../common/yahooonly.jsp" %>
	<link rel="stylesheet" type="text/css" href="jsp/jquery/layout-default-latest.css" />
	<link rel="stylesheet" type="text/css" href="jsp/jquery/themes/base/jquery.ui.all.css" />
	<!-- CUSTOMIZE/OVERRIDE THE DEFAULT CSS -->
	<style type="text/css">



	/* remove padding and scrolling from elements that contain an Accordion OR a content-div */
	.ui-layout-center ,	/* has content-div */
	.ui-layout-west ,	/* has Accordion */
	.ui-layout-east ,	/* has content-div ... */
	.ui-layout-east .ui-layout-content { /* content-div has Accordion */
		padding: 0;
		overflow: auto;
	}
	.ui-layout-center P.ui-layout-content {
		line-height:	1.4em;
		margin:			0; /* remove top/bottom margins from <P> used as content-div */
	}
	h3, h4 { /* Headers & Footer in Center & East panes */
		font-size:		1.1em;
		background:		#EEF;
		border:			1px solid #BBB;
		border-width:	0 0 1px;
		padding:		7px 10px;
		margin:			0;
	}
	.ui-layout-east h4 { /* Footer in East-pane */
		font-size:		0.9em;
		font-weight:	normal;
		border-width:	1px 0 0;
	}
	</style>

	<!-- REQUIRED scripts for layout widget -->
	<script type="text/javascript" src="jsp/jquery/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="jsp/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="jsp/jquery/js/jquery-ui-latest.js"></script>
	<script type="text/javascript" src="jsp/jquery/js/jquery.layout-latest.js"></script>
	<script type="text/javascript" src="jsp/jquery/js/jquery.layout.resizePaneAccordions.min-1.0.js"></script>

    <script type="text/javascript" src="jsp/jquery/js/themeswitchertool.js"></script> 
	<script type="text/javascript" src="jsp/jquery/js/debug.js"></script>

	<script type="text/javascript">
	var myLayout;
	$(document).ready( function() {

		myLayout = $('body').layout({
			west__size:			400
	
			// RESIZE Accordion widget when panes resize
		,	west__onresize:		$.layout.callbacks.resizePaneAccordions
		
		});


		

		// ACCORDION - in the West pane
		$("#accordion1").accordion({ fillSpace:	true });
		





	});


	</script>

</head>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@include file="../common/tooltip.jsp" %>
<link rel="stylesheet" type="text/css" href="jsp/reqtreeAgency/js/jquery/plugins/simpleTree/style.css" />
<link rel="stylesheet" type="text/css" href="jsp/reqtreeAgency/style.css" />
<script type="text/javascript" src="jsp/reqtreeAgency/js/jquery/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="jsp/reqtreeAgency/js/jquery/plugins/simpleTree/jquery.simple.tree.js"></script>
<script type="text/javascript" src="jsp/reqtreeAgency/js/langManager.js" ></script>
<script type="text/javascript" src="jsp/reqtreeAgency/js/treeOperations.js"></script>
<script type="text/javascript" src="jsp/reqtreeAgency/js/init.js"></script>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

%>

  <link href="jsp/css/cal.cs" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jsp/js/CalendarPopup.js"></script>



<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>




<body class="yui-skin-sam">

<div class="ui-layout-north ui-widget-content"  style="background: #86c9ef;" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
<%@ include file="../common/menu_agency.jsp" %>
	
</div>


<div class="ui-layout-south ui-widget-content" style="display: none;"> 
<%@ include file="../common/Footer.jsp" %>
</div>

<div class="ui-layout-center" style="overflow: auto"> 
<span id="applicantdata">
	<h3 class="ui-widget-header">Applicant List</h3>
<!-- body start-->

<table>
<tr>
<td width="10px">
</td>
<td width="200px">
<a  href="#" onClick="javascript:addapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a>
</td>
<td>
<div id="toalrecords"></div>
</td>
</tr>
</table>
<table>
<tr>
<td width="10px">
</td>
<td>
<div id="dynamicdata"></div>
</td>
</table>


</span>
<!-- body end -->
</div>
<%
String requistionId = (String)request.getParameter("requistionId");
String secureid = (String)request.getParameter("secureid");
%>
<script language="javascript">
function openrequistion(id){
	
  window.open("jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+id,"JobReq","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function allapplicants(){
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });

	location.href="agjob.do?method=requistionapplicantlist&state=0&requistionId=<%=requistionId%>&secureid=<%=secureid%>";


}
</script>
<%

//BOFactory.getTreeBO().setTreeviewForRequistionUIRevamp(user1,request,new Long(requistionId).longValue(),secureid);

BOFactory.getTreeBO().setTreeviewForAgency(user1,request,new Long(requistionId).longValue());

String method = (String)request.getParameter("method");
String ishavepermission = (String)request.getAttribute("NO_PERMISSION");
if(ishavepermission != null && ishavepermission.equals("yes")){

%>
<b> <font color="red">You don't have permission to see this requisition , Please contract requisition owner.</font></b>
<%}else {%>
<%
JobRequisition jb = (JobRequisition)request.getAttribute("JOB_REQUISTION");
String totalapplicant = (String)request.getAttribute("TOTAL_APPLICANT");
String state = (String)request.getParameter("state");
state="0";
 String isapplicationsubmitted = "false";
	
 if(state != null && state.equals(Common.APPLICATION_SUBMITTED)){
	 isapplicationsubmitted="true";
 }

 Map<String, Integer> countMap = BOFactory.getTreeBO().getCountMapOfApplicantsByRequitionIdAndStatus(new Long(requistionId).longValue(),user1.getUserId());
%>
	
<div class="ui-layout-west" style="overflow: auto; height:700px;">

		<h3 class="ui-widget-header">Requisition tree &nbsp; <a href="agjob.do?method=jobdetails&reqid=<%=jb.getJobreqId()%>&secureid=<%=jb.getUuid()%>" target="new">
		<font color="blue" size="3"><%=jb.getJobTitle()%></font></a> &nbsp; 

<a  href="#" onClick="javascript:addapplicant()"><img border="0" src="jsp/images/new_button.gif" alt="Add applicant" width="18" height="18" /></a>
</a></h3>


			
			<div id="annualWizard">	
			<ul class="simpleTree" id='pdfTree'>		
					<li class="root" id='0'><a href="#" onClick="allapplicants()"><%=jb.getJobTitle()%></a>
						<ul>
						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.APPLICATION_SUBMITTED%>'><span> Application Submitted (<%=(countMap.get(Common.APPLICATION_SUBMITTED)==null)?"0":countMap.get(Common.APPLICATION_SUBMITTED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.APPLICATION_SUBMITTED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.APPLICATION_SUBMITTED%>}</li></ul></li>
						
						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.IN_SCREENING%>'><span> In Screening (<%=(countMap.get(Common.IN_SCREENING)==null)?"0":countMap.get(Common.IN_SCREENING)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.IN_SCREENING%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.IN_SCREENING%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.CLEARED_IN_SCREENING%>'><span> Cleared In Screening (<%=(countMap.get(Common.CLEARED_IN_SCREENING)==null)?"0":countMap.get(Common.CLEARED_IN_SCREENING)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.CLEARED_IN_SCREENING%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.CLEARED_IN_SCREENING%>}</li></ul></li>
                       
					  <%
						  int maximumround = new Integer(Constant.getValue("maximum.interview.rounds")).intValue();
						for(int i1=1;i1<=maximumround;i1++){
						String tmpround = "Interview Round-"+i1;
						String clearedtmpround = "Cleared-Interview Round-"+i1;
                        
						%>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=tmpround%>'><span> <%=tmpround%> (<%=(countMap.get(tmpround)==null)?"0":countMap.get(tmpround)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=tmpround%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=tmpround%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=clearedtmpround%>'><span> <%=clearedtmpround%> (<%=(countMap.get(clearedtmpround)==null)?"0":countMap.get(clearedtmpround)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=clearedtmpround%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=clearedtmpround%>}</li></ul></li>


						<%}%>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.OFFER_INITIATED%>'><span> <%=Common.OFFER_INITIATED%> (<%=(countMap.get(Common.OFFER_INITIATED)==null)?"0":countMap.get(Common.OFFER_INITIATED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.OFFER_INITIATED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.OFFER_INITIATED%>}</li></ul></li>


						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.OFFER_APPROVAL_REJECTED%>'><span> <%=Common.OFFER_APPROVAL_REJECTED%> (<%=(countMap.get(Common.OFFER_APPROVAL_REJECTED)==null)?"0":countMap.get(Common.OFFER_APPROVAL_REJECTED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.OFFER_APPROVAL_REJECTED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.OFFER_APPROVAL_REJECTED%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.READY_TO_RELEASE_OFFER%>'><span> <%=Common.READY_TO_RELEASE_OFFER%> (<%=(countMap.get(Common.READY_TO_RELEASE_OFFER)==null)?"0":countMap.get(Common.READY_TO_RELEASE_OFFER)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.READY_TO_RELEASE_OFFER%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.READY_TO_RELEASE_OFFER%>}</li></ul></li>


						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.OFFER%>'><span> <%=Common.OFFER%> (<%=(countMap.get(Common.OFFER)==null)?"0":countMap.get(Common.OFFER)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.OFFER%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.OFFER%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.OFFER_IN_NEGOTIATION%>'><span> <%=Common.OFFER_IN_NEGOTIATION%> (<%=(countMap.get(Common.OFFER_IN_NEGOTIATION)==null)?"0":countMap.get(Common.OFFER_IN_NEGOTIATION)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.OFFER_IN_NEGOTIATION%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.OFFER_IN_NEGOTIATION%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.OFFER_ACCEPTED%>'><span> <%=Common.OFFER_ACCEPTED%> (<%=(countMap.get(Common.OFFER_ACCEPTED)==null)?"0":countMap.get(Common.OFFER_ACCEPTED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.OFFER_ACCEPTED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.OFFER_ACCEPTED%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.OFFER_CANCELED%>'><span> <%=Common.OFFER_CANCELED%> (<%=(countMap.get(Common.OFFER_CANCELED)==null)?"0":countMap.get(Common.OFFER_CANCELED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.OFFER_CANCELED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.OFFER_CANCELED%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.OFFER_DECLINED%>'><span> <%=Common.OFFER_DECLINED%> (<%=(countMap.get(Common.OFFER_DECLINED)==null)?"0":countMap.get(Common.OFFER_DECLINED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.OFFER_DECLINED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.OFFER_DECLINED%>}</li></ul></li>


						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.REJECTED%>'><span> <%=Common.REJECTED%> (<%=(countMap.get(Common.REJECTED)==null)?"0":countMap.get(Common.REJECTED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.REJECTED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.REJECTED%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.ON_HOLD%>'><span> <%=Common.ON_HOLD%> (<%=(countMap.get(Common.ON_HOLD)==null)?"0":countMap.get(Common.ON_HOLD)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.ON_HOLD%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.ON_HOLD%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.MARK_DELETED%>'><span> <%=Common.MARK_DELETED%> (<%=(countMap.get(Common.MARK_DELETED)==null)?"0":countMap.get(Common.MARK_DELETED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.MARK_DELETED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.MARK_DELETED%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.ON_BOARD%>'><span> <%=Common.ON_BOARD%> (<%=(countMap.get(Common.ON_BOARD)==null)?"0":countMap.get(Common.ON_BOARD)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.ON_BOARD%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.ON_BOARD%>}</li></ul></li>

						<li class='text' id='<%=jb.getJobreqId()%>_<%=Common.RESIGNED%>'><span> <%=Common.RESIGNED%> (<%=(countMap.get(Common.RESIGNED)==null)?"0":countMap.get(Common.RESIGNED)%>)</span><ul class='ajax'><li id='<%=jb.getJobreqId()%>_<%=Common.RESIGNED%>'>{url:jsp/reqtreeAgency/manageTreeData.jsp?action=getElementList&ownerEl=<%=jb.getJobreqId()%>_<%=Common.RESIGNED%>}</li></ul></li>

						</ul>	
					</li>
				
			</ul>
			</div>


</div>

	
	
</body>
</html> 


<script language="javascript">


function callJavascriptOpenfunc(id){
 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
	
    var reqidvaluefromid = id.substr(0,id.indexOf("_"));
	if(reqidvaluefromid != "" && reqidvaluefromid == "<%=requistionId%>"){
		callToajaxTreeByState(id,'<%=secureid%>');
	}else{

	callToajax(id);
	
	}
}

function callToajax(id){
	
	$.ajax({
	type: 'GET',
  url: "agencyops.do?method=applicantDetailsForTree&secureid="+id+"&ddd="+(new Date).getTime(),
  success: function(data){
  $('#applicantdata').html(data);
	completeajx();
  }
});
}

function callToajaxTreeByState(id,requuid){
	
	$.ajax({
	type: 'GET',
  url: "jsp/reqtreeAgency/requisitionReqTreeByStateAgency.jsp?secureid="+requuid+"&id="+id+"&ddd="+(new Date).getTime(),
  success: function(data){
  $('#applicantdata').html(data);
	completeajx();
  }
});
}



function callToajaxrightsidetab(id){
	$.ajax({
	type: 'GET',
  url: "applicant.do?method=applicantDetailsForTreeRightTab&secureid="+id+"&ddd="+(new Date).getTime(),
  success: function(data){
  $('#rightsidetab').html(data);
	completeajx();
  }
});
}

function callToajaxGraph(id){
	$.ajax({
	type: 'GET',
  url: "applicant.do?method=getEvaluationGraph&uuid="+id+"&ddd="+(new Date).getTime(),
  success: function(data){
  $('#evaluationcriteria').html(data);
	
  }
});
}

function tabnameapplicantmoreaction(id){
	$.ajax({
	type: 'GET',
  url: "jsp/reqtree/tabnameapplicantmoreaction.jsp?id="+id+"&ddd="+(new Date).getTime(),
  success: function(data){
  $('#tabnameapplicantmoreaction').html(data);
	
  }
});
}
function tabnameapplicantScoring(id){
	$.ajax({
	type: 'GET',
  url: "jsp/reqtree/tabnameapplicantScoring.jsp?id="+id+"&ddd="+(new Date).getTime(),
  success: function(data){
  $('#tabnameapplicantScoring').html(data);
	
  }
});
}


function completeajx(){
	
	 $.unblockUI();
}
</script>

<script language="javascript">
var isapplicationsubmitted=<%=isapplicationsubmitted%>;


function jobDetails(id,uuid){
	//alert("Not implemented yet, need a job details page for not looged in user");
	
	var url = "agjob.do?method=jobdetails&reqid="+id+"&secureid="+uuid;

	window.open(url,  'JobRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=no');
}

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsApplicantSummary";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}
function userDetailsOrGroup(id,isgroup){
	var url = "";
	if(isgroup == 'Y'){
		url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id;
	}else{
			url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
		}
	
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}
function editJobReq(jb_id){
	
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);
}

function messageret(){
	//window.location.reload();
	//history.go(0);

			}


function showmessage(returnval) { 
	//window.location.reload();
	}


	function opentjobrequistionpage(){
		
  window.open("jobreq.do?method=createjobreq", "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function openrequistion(id){
	
  window.open("jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+id,"JobReq","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/agencyops.do?method=editapplicant&applicantId="+aid;
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>', url, messageret);
	callBoxFancy(url);
}

function addapplicant(){

	var url = "<%=request.getContextPath()%>/agencyops.do?method=createApplicantPage&requistionId=<%=requistionId%>&frm=ag";
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%>', url, messageret);
	callBoxFancy(url);
}


function sendbulkresumeforscreening(appIds){
	
	var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=sendbulkresumeforscreening&eventype=<%=Common.IN_SCREENING%>&requistionId=<%=requistionId%>&applicantids="+appIds;
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>', url, messageret);
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>',url,500,700, messageret);
}

function markdeletebulk(appIds){
	
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=markfordeletionbulk&applicantids="+appIds;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>',url,400,600, messageret);

}

function compareapplicants(appIds){
	
	var url = "applicant.do?method=compareapplicants&applicantids="+appIds;
	var urlnew = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
	 window.open(urlnew, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1");

}

</script>



<script type="text/javascript">
YAHOO.example.DynamicData = function() {

	
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		

          elCell.innerHTML = "<a href='#' onClick=callJavascriptOpenfunc('" + oRecord.getData("uuid") + "')"+ ">" +" " + sData + "</a>";
	
        };

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };

var Dom = YAHOO.util.Dom,
				Event = YAHOO.util.Event,
				log = function (msg) {
					YAHOO.log(msg,'info','deleteRowsBy demo');
				};

YAHOO.widget.DataTable.prototype.deleteRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
                sendbulkresumeforscreening(checknumber);
				}
				
				this.render();
			};

YAHOO.widget.DataTable.prototype.compareRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, tcount = 0 ,
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
				   tcount++;
					}
					
        
				 }
                if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
					 if(tcount < 2){
						alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg2",user1.getLocale())%>");
					return false;
					}
					if(tcount > 4){
						alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg6",user1.getLocale())%>");
					return false;
					}
                compareapplicants(checknumber);
				}
				
				this.render();
			};

	


    // Column definitions

	var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
           {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"email", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"phone", label:"<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>",sortable:true, resizeable:true},
		//{key:"countryName", sortable:true, resizeable:true},
		{key:"heighestQualification",label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
		//{key:"qualifications", sortable:true, resizeable:true},
		{key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"referrerName", hidden:true},
//		{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", resizeable:true},
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];

	if(isapplicationsubmitted == false){

		 myColumnDefs = [
		  	{key:"applicantId", hidden:true},
			{key:"uuid", hidden:true},
           	{key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"email", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"phone", label:"<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"heighestQualification",label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
			{key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"referrerName", hidden:true},
// no need to show owner name//	{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", resizeable:true},
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
			
			
           
        ];
	}
    





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/reqtreeAgency/treeapplicantlistpageAgency.jsp?requistionId=<%=requistionId%>&state=<%="0"%>&secureid=<%=secureid%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},
            {key:"resumeHeader"},
			{key:"email"},
			{key:"phone"},
			{key:"heighestQualification"},
			{key:"interviewState"},
			{key:"appliedDate"},
			{key:"jobTitle"},
			{key:"referrerName"},
			{key:"ownername"},
			{key:"uuid"},
			{key:"edit"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Update totalRecords on the fly with value from server

	myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.results",user1.getLocale())%> : ' + my_row_total;
   });

    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }


	myDataTable.subscribe('checkboxClickEvent', function(oArgs) {
				var elCheckbox = oArgs.target;
				var newValue = elCheckbox.checked;
				var record = this.getRecord(elCheckbox);
				var column = this.getColumn(elCheckbox);
				record.setData(column.key,newValue);
			});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					for (var i=0;i < l; i++) {
						rs.getRecord(i).setData('Select',check);
					}
					this.render();
				}
			});
			
			
			Event.on('delete','click', function() {
				myDataTable.deleteRowsBy(function (data) {
					return data.Select;
				});
			});

		Event.on('compare','click', function() {
				myDataTable.compareRowsBy(function (data) {
					return data.Select;
				});
			});
			
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();
</script>


<%}// tree and applicant list end%>