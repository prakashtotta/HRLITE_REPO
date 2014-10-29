<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />

	<title>Applicants</title>

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
		overflow: hidden;
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
	<script type="text/javascript" src="jsp/autocomplete/jquery.min.js"></script>
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
			west__size:			450
		,	east__size:			200
			// RESIZE Accordion widget when panes resize
		,	west__onresize:		$.layout.callbacks.resizePaneAccordions
		,	east__onresize:		$.layout.callbacks.resizePaneAccordions
		});

		// ACCORDION - in the West pane
		$("#accordion1").accordion({ fillSpace:	true });
		
		// ACCORDION - in the East pane - in a 'content-div'
		$("#accordion2").accordion({
			fillSpace:	true
		,	active:		1
		});




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
<%@ include file="../common/dashboardinclude.jsp" %>
<link rel="stylesheet" type="text/css" href="jsp/jtree/js/jquery/plugins/simpleTree/style.css" />
<link rel="stylesheet" type="text/css" href="jsp/jtree/style.css" />
<script type="text/javascript" src="jsp/jtree/js/jquery/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="jsp/jtree/js/jquery/plugins/simpleTree/jquery.simple.tree.js"></script>
<script type="text/javascript" src="jsp/jtree/js/langManager.js" ></script>
<script type="text/javascript" src="jsp/jtree/js/treeOperations.js"></script>
<script type="text/javascript" src="jsp/jtree/js/init.js"></script>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

%>

  <link href="jsp/css/cal.cs" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jsp/js/CalendarPopup.js"></script>



<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>




<body class="yui-skin-sam">

<div class="ui-layout-north ui-widget-content"  style="background: #86c9ef;" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
<%@ include file="../common/menu.jsp" %>
	
</div>


<div class="ui-layout-south ui-widget-content" style="display: none;"> 
<%@ include file="../common/Footer.jsp" %>
</div>

<div class="ui-layout-center"> 
	<h3 class="ui-widget-header">Applicant List</h3>
<!-- body start-->





<!-- body end -->
</div>
<script language="javascript">
function openrequistion(id){
	
  window.open("jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+id,"JobReq","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
</script>
<%
String requistionId = (String)request.getParameter("requistionId");
String secureid = (String)request.getParameter("secureid");

BOFactory.getTreeBO().setTreeviewForRequistionUIRevamp(user1,request,new Long(requistionId).longValue(),secureid);

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
 String isapplicationsubmitted = "false";
	
 if(state != null && state.equals(Common.APPLICATION_SUBMITTED)){
	 isapplicationsubmitted="true";
 }
%>
	
<div class="ui-layout-west" style="display: none;">

	<div id="accordion1" class="basic">

			<h3><a href="#">Requisition tree</a></h3>
			<div>
			<table valign="top" bgcolor="#f3f3f3">
			<tr>
			<td>
			<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%> : <a href="#" onClick="openrequistion('<%=EncryptDecrypt.encrypt(String.valueOf(jb.getJobreqId()))%>')"><%=jb.getJobreqName()%></a>
			</td>
			<td>
			<%=Constant.getResourceStringValue("Requisition.TotalNoofopenings",user1.getLocale())%> : <%=jb.getNumberOfOpening()%> 
			</td>
			</tr>
			<tr>
			<td>
			<%=Constant.getResourceStringValue("Requisition.TotalNoofremaining",user1.getLocale())%> : <%=jb.getNumberOfOpeningRemain()%>
			</td>
			<td>
			<%=Constant.getResourceStringValue("Requisition.Total_applicants",user1.getLocale())%> : <%=totalapplicant%>

			</td>
			</tr>
			</table>
			<div id="annualWizard">	
			<ul class="simpleTree" id='pdfTree'>		
					<li class="root" id='0'><a href="#" onClick="openrequistion('<%=EncryptDecrypt.encrypt(String.valueOf(jb.getJobreqId()))%>')"><%=jb.getJobreqName()%></a>
						<ul>
						<li class='text' id='8'><span>vxcxbvc</span><ul class='ajax'><li id='8'>{url:manageTreeData.jsp?action=getElementList&ownerEl=8}</li></ul></li>
<li class='text' id='7'><span>Others</span><ul class='ajax'><li id='7'>{url:manageTreeData.jsp?action=getElementList&ownerEl=7}</li></ul></li>
<li class='text' id='6'><span>ERP SAP consultant</span><ul class='ajax'><li id='6'>{url:manageTreeData.jsp?action=getElementList&ownerEl=6}</li></ul></li>
<li class='text' id='5'><span>HR functions</span><ul class='ajax'><li id='5'>{url:manageTreeData.jsp?action=getElementList&ownerEl=5}</li></ul></li>


						</ul>	
					</li>
				
			</ul>
			</div>
			</div>

			

			

	</div>
</div>

<%}// tree end%>


	
</div>

</body>
</html> 

<div class="contextMenu" id="myMenu1">	
		<li class="addFolder">
			<img src="jsp/jtree/js/jquery/plugins/simpleTree/images/folder_add.png" /> </li>
		<!--<li class="addDoc"><img src="js/jquery/plugins/simpleTree/images/page_add.png" /> </li>	-->
		<li class="edit"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/folder_edit.png" /> </li>
		<li class="delete"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/folder_delete.png" /> </li>
		<li class="expandAll"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/expand.png"/> </li>
		<li class="collapseAll"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/collapse.png"/> </li>	
</div>
<div class="contextMenu" id="myMenu2">
		<!--<li class="edit"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/page_edit.png" /> </li>-->
		<li class="delete"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/page_delete.png" /> </li>
</div>

