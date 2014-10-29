<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />

	<title>Applicant Details</title>
<%@ include file="../common/yahooonly.jsp" %>
	<link rel="stylesheet" type="text/css" href="jsp/jquery/layout-default-latest.css" />
	<link rel="stylesheet" type="text/css" href="jsp/jquery/themes/base/jquery.ui.all.css" />
	<!-- CUSTOMIZE/OVERRIDE THE DEFAULT CSS -->
	<style type="text/css">


     .msg {
   border-top: 1px solid #96d1f8;
   background: #7f93bc;
   padding: 5px 10px;
   -webkit-border-radius: 4px;
   -moz-border-radius: 4px;
   border-radius: 4px;
   text-shadow: rgba(0,0,0,0) 0 0px 0;
   color: white;
   font-size: 14px;
   font-family: Georgia, serif;
   text-decoration: none;
   vertical-align: middle;
   }

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
		  <script type="text/javascript" src="jsp/mutliauto/jquery.tokeninput.js"></script>
    <link rel="stylesheet" href="jsp/mutliauto/token-input.css" type="text/css" />
	<script type="text/javascript" src="jsp/jquery/js/jquery-ui-latest.js"></script>
	<script type="text/javascript" src="jsp/jquery/js/jquery.layout-latest.js"></script>
	<script type="text/javascript" src="jsp/jquery/js/jquery.layout.resizePaneAccordions.min-1.0.js"></script>


	<script type="text/javascript">
	var myLayout;
	$(document).ready( function() {

		myLayout = $('body').layout({
			east__size:			500
		,	east__initClosed:	false

			// RESIZE Accordion widget when panes resize
		
		,	east__onresize:		$.layout.callbacks.resizePaneAccordions
		});


				// TABS INSIDE EAST-PANE
		myLayout.panes.east.tabs({
			show:				$.layout.callbacks.resizePaneAccordions // resize tab2-accordion when tab is activated
		});
		myLayout.sizeContent("east"); // resize pane-content-elements after creating east-tabs

		




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



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String secureidtmp = request.getParameter("secureid");
String backtolisturl = request.getParameter("backtolisturl");

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

<div class="ui-layout-center" style="overflow: auto"> 
	<h3 class="ui-widget-header msg" ><font color="white">Applicant Details</font></h3>
<!-- body start-->
<table>
<tr>
<td width="2%"></td>
<td>
<span id="applicantdata">
</span>
</td>
</tr>
</table>
<!-- body end -->
</div>

	


	
	<div id="tabs-east" class="ui-layout-east no-padding">


    
	<ul>
		<li><a href="#tab-panel-east-1" class="msg"><font color="white">More actions</font></a></li>
		<li><a href="#tab-panel-east-2" class="msg"><font color="white">Scoring</font></a></li>
		
	</ul>

	<div class="ui-layout-content ui-widget-content" style="border-top: 0;">
		<div id="tab-panel-east-1" class="full-height no-padding add-scrollbar">
			<div class="ui-tabs-panel outline" >
				
				<span id="rightsidetab">
				</span>
			</div>
		</div>

		<div id="tab-panel-east-2" class="full-height no-padding">
			
			<span id="evaluationcriteria">
			</span>
		</div>
		
	</div>






</div>

</body>
</html> 

<script language="javascript">
callJavascriptOpenfunc();

function callJavascriptOpenfunc(){
 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });

var id= "<%=secureidtmp%>";
	callToajax(id);
	callToajaxrightsidetab(id);
	callToajaxGraph(id);
	
}

function callToajax(id){
	$.ajax({
	type: 'GET',
  url: "applicant.do?method=applicantDetailsForTree&secureid="+id+"&backtolisturl=<%=backtolisturl%>",
  success: function(data){
  $('#applicantdata').html(data);
	completeajx();
  }
});
}





function callToajaxrightsidetab(id){
	$.ajax({
	type: 'GET',
  url: "applicant.do?method=applicantDetailsForTreeRightTab&secureid="+id,
  success: function(data){
  $('#rightsidetab').html(data);
	completeajx();
  }
});
}

function callToajaxGraph(id){
	$.ajax({
	type: 'GET',
  url: "applicant.do?method=getEvaluationGraph&uuid="+id,
  success: function(data){
  $('#evaluationcriteria').html(data);
	
  }
});
}



function completeajx(){
	
	 $.unblockUI();
}


</script>


