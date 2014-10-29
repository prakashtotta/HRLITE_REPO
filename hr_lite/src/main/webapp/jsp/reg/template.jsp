<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />

	<title>Job Requisition and Interview Management</title>


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
	<script type="text/javascript" src="jsp/jquery/js/jquery.layout.resizePaneAccordions-1.0.js"></script>

    


	<script type="text/javascript">	
	var myLayout;

	$(document).ready( function() {

		myLayout = $('body').layout({
			west__size:			300
		,	east__size:			300
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
<body>
	
<%@ page errorPage="../common/error.jsp" %>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>

<%

  String pagetitle="";
  pagetitle=request.getParameter("title");
  if(pagetitle==null)
     pagetitle="Interview Management";
     
  String pageBody =  request.getParameter("htmlbody");  
 // out.println(pageBody);
  if(pageBody == null || "".equals(pageBody)){ 
  	pageBody = "LoginBody.jsp";
  }
  

%>

<div class="ui-layout-north ui-widget-content" style="background: #86c9ef;" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
<%@ include file="../common/menu_referral.jsp" %>
	
</div>


<div class="ui-layout-south ui-widget-content" style="display: none;">
<%@ include file="../common/Footer.jsp" %>
</div>

<div class="ui-layout-center" style="display: none;overflow:	auto"> 
	<h3 class="ui-widget-header"><%=pagetitle%></h3>
	<table>
	<tr>
	<td width="1%"><td>
	<td>
<jsp:include page="<%= pageBody %>" flush="true"/>
</td>
</tr>
</table>
</div>


	
</div>

</body>
</html> 















