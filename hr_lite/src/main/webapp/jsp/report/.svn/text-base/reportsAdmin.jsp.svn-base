<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<%

User user = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<!DOCTYPE html> 
<HTML> 
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<TITLE>Hires360 My home page</TITLE>

	<LINK rel="stylesheet" type="text/css" href="jsp/jquery/themes/base/jquery.ui.all.css">

	<STYLE type="text/css">

	p {
		margin:		1ex 0;
	}
	.custom a			{ color: blue; text-decoration: none; }
	.custom a:visited	{ color: purple; }
	.custom a:hover		{ color: red; text-decoration: underline; }

	/*
	 *	Page Content Styles
	 */
	/* Loading... display */
	#page-loading {
		color:		#FFF;
		font-size:	3em;
		position:	absolute;
		left:		40%;
		top:		40%;
	}
	/* Notes in center-pane of Tab1 */
	BLOCKQUOTE,
	UL { margin-top: 1ex; margin-bottom: 1em; }
	LI { margin-top: 1ex; margin-bottom: 1ex; }
	H4 { margin: 1em 0 1ex; font-size: 1.1em; }
	#notes { padding: 1em 1.5em; }
	#notes > H4 { margin-top: 0; }

	/*
	 *	Generic Layout Styles
	*/
	.container { /* override default style */
		padding:	0 !important;
		overflow:	hidden !important;
	}
	.ui-layout-pane {
		padding:	0;
		overflow:	hidden;
	}
	.ui-widget-content { /* used as the ui-layout-content class */
		padding:	1ex;
		overflow:	auto;
	}
	.hidden { /* apply to elements to avoid Flash of Content */
		display:	none;
	}
	.allow-overflow	{
		overflow:		auto;
	}
	/* HIDE all pane-elements until initialized */
	.ui-layout-north,
	.ui-layout-south,
	.ui-layout-west,
	.ui-layout-east,
	.ui-layout-center {
		display:	none;
	}

	/*
	 *	Outer-Tabs Layout - Structural
	 *
	 *	NOTE: already set 'ui-layout-pane' to be: overflow: hidden; padding: 0
	 */
	#outer-center { /* #tabbuttonss & #tabpanels container */
		background:		#DDD;	/* need a background to contrast panes - overridden by custom theme */
		padding:		0;		/* not sure why need this again? */
		}
		#tabbuttons {	/* tabsContainerLayout - north-pane */
			overflow:	visible; /* so tabs (LI) can overlap bottom-border */
		}
		#tabpanels {}	/* tabsContainerLayout - center-pane */
			#tabpanels .tab-panel { /* tab-layout containers */
				width:		100%;
				height:		100%;
				padding:	0;
				overflow:	auto;
			}
	/*
	 *	Inner-Tabs
	 */
	#innerTabs.ui-layout-center {
		padding:	0;
		}
		#innerTabs > DIV:first-child {} 	/* header-title */
		#innerTabs > UL {} 					/* tab-buttons - header */
		#innerTabs .ui-widget-content {}	/* tab-panels-wrapper - content-pane */
			#innerTabs > DIV {} 			/* tab-content wrappers: #simpleTab1, #simpleTab2, etc. */
		.custom #innerTabs > UL {			/* custom theme */
			border-radius:			0;		/* override UI theme */
			-moz-border-radius:		0;
			-webkit-border-radius:	0;
			}
			.custom #innerTabs > UL LI:first-child { /* first tab */
				margin-left:	10px;
			}
	/*
	 *	Accordions
	 */
	.accordion { /* Accordion containers */
		height:		100%;
	}

	/*
	 *	Outer Layout - Cosmetic
	 */
	#outer-north {
		/*font-weight:	bold;
		padding:		1em 1em;*/
		}
		#outer-north div.buttons {
			float:			right;
			margin-right:	200px;
			margin-top:		-4px;
			}
	#outer-south {
		padding:		.5em 1em;
	}

	/*
	 *	Custom Theme
	 */
	/* outer-layout colors */
	body.custom , /* class used to trigger 'custom theme' for all elements */
	.custom #outer-north ,
	.custom #outer-center ,
	.custom #outer-south {
		background:		#776;
	}
	/* Tabs Wrapper & Buttons*/
	#outer-center.ui-tabs ,
	#outer-center.ui-tabs>ul {
		border-bottom:	0;
		padding-bottom:	0;
		border-radius:			0;
		-moz-border-radius:		0;
		-webkit-border-radius:	0;
		}
		.custom #outer-center.ui-tabs ,
		.custom #outer-center.ui-tabs>ul {
			background:	transparent;
			border:		0;
			padding:	0;
			}
			.custom #tabbuttons LI { /* tab wrapper */
				border:			0;
				margin-left:	5px;
			}
			.custom #tabbuttons LI:first-child { /* first tab */
				margin-left:	15px;
			}
			.custom #tabbuttons LI A { /* tab links/style */
				font-weight:	bold;
				cursor:			pointer !important; /* override theme - for 'sorting' */
				border-radius:					0;
				-moz-border-radius:				0;
				-webkit-border-radius:			0;
				border-top-radius:				4px;
				-moz-border-radius-topleft: 	4px;
				-moz-border-radius-topright: 	4px;
				-webkit-border-top-left-radius: 4px;
				-webkit-border-top-right-radius: 4px;
			}
	/*	Tab Panels & Contents */
	.toolbar {
		overflow:		auto;
		/*border-top:		0 !important;*/
	}
	.custom .toolbar { /* north AND south panes */
		background:		#666; /* changed below to match tab-color */
		color:			#FFF !important; /* override Theme */
		font-weight:	bold;
		padding:		6px 2em;
	}
	.custom #tabpanels .tab-panel {
		border-radius:			8px;
		-moz-border-radius:		8px;
		-webkit-border-radius:	8px;
	}
	.custom .ui-layout-north > .toolbar { /* north 'toolbar' */
		border:			0;
		border-top-radius:				8px;
		-moz-border-radius-topleft: 	8px;
		-moz-border-radius-topright: 	8px;
		-webkit-border-top-left-radius: 8px;
		-webkit-border-top-right-radius: 8px;
	}
	.custom .ui-layout-south > .toolbar { /* south 'statusbar' */
		border-bottom-radius:				8px;
		-moz-border-radius-bottomleft: 		8px;
		-moz-border-radius-bottomright: 	8px;
		-webkit-border-bottom-left-radius:	8px;
		-webkit-border-bottom-right-radius: 8px;
	}
	/*
	 *	Can't pad #tabpanels .tab-panel containers because have width/height = 100%
	 *	so must pad outer-edges of inner-panes instead to get nice whitespace
	*/
	.tab-panel .ui-layout-west { padding-left:	10px; } /* same as west__spacing_closed */
	.tab-panel .ui-layout-east { padding-right:	10px; } /* same as east__spacing_closed */
	/*  
	 *	Every inner-layout is made up of header, content and footer panels
	 */
	.ui-widget-header {
		padding:		4px 1em;
	}
	.ui-widget-footer {
		font-size:		.85em;
		font-weight:	normal !important;
	}
	.custom .ui-widget-header {
		border:			0;
		color:			#FFF;
		font-size:		.85em;
		font-weight:	bold;
		letter-spacing:	1px;
		text-transform:	uppercase;
	}
	.custom .ui-widget-footer { /* NOTE: no such class, so footer ALSO has: ui-widget-header */
		border:			0;
		color:			#FFF;
		letter-spacing:	normal;
		text-transform:	none;
		text-align:		center;
		padding:		3px 1ex 4px;
	}
	.custom .ui-widget-content {
		/* padding & overflow were already set above under 'Generic Layout Styles' */
		border:			0;
		background:		#F9F9F9;
		color:			#000;
	}
	/* #tab1 = blue-green */
	.custom #tabbuttons .tab1 a 	{ background: #007182; color: #FFF; } /* override UI Theme */
	.custom #tabpanels #tab1,
	.custom #tab1 .ui-layout-resizer-sliding ,
	.custom #tab1 .ui-layout-west ,		/* sidebar panes too - for when 'sliding' */
	.custom #tab1 .ui-layout-east,
	.custom #tab1 .ui-layout-resizer-closed { border: 1px solid #378c99; }
	.custom #tab1 .toolbar			{ border:	0; }
	.custom #tab1 .toolbar ,
	.custom #tab1 .ui-widget-header { background: #007182; } /* override UI Theme */
	.custom #tab1 .ui-widget-footer	{ background: #378c99; }
	/*
	.custom #tab1 > .ui-layout-center ,
	.custom #tab1 .ui-layout-pane .ui-layout-pane { border: 2px solid #378c99; }
	.custom #tab1 .ui-widget-content	{ border: 1px solid #007182; }
	*/
	/* #tab2 = green */
	.custom #tabbuttons .tab2 a 	{ background: #16b81e; color: #FFF; } /* override UI Theme */
	.custom #tabpanels #tab2,
	.custom #tab2 .ui-layout-resizer-sliding ,
	.custom #tab2 .ui-layout-west ,		/* sidebar panes too - for when 'sliding' */
	.custom #tab2 .ui-layout-east	{ background: #88e48d; }
	.custom #tab2 .ui-layout-resizer-closed { border: 1px solid #4cbf52; }
	.custom #tab2 .toolbar ,
	.custom #tab2 .ui-widget-header { background: #16b81e; border: 0; }
	.custom #tab2 .ui-widget-footer { background: #4cbf52; border: 0; }
	/*
	.custom #tab2 > .ui-layout-center ,
	.custom #tab2 .ui-layout-pane .ui-layout-pane { border: 2px solid #4cbf52; }
	.custom #tab2 .ui-widget-content	{ border: 1px solid #16b81e; }
	*/
	/* #tab3 = purple */
	.custom #tabbuttons .tab3 a 	{ background: #6f2ab8; color: #FFF; } /* override UI Theme */
	.custom #tabpanels #tab3,
	.custom #tab3 .ui-layout-resizer-sliding ,
	.custom #tab3 .ui-layout-west ,		/* sidebar panes too - for when 'sliding' */
	.custom #tab3 .ui-layout-east	{ background: #c3a2e4; }
	.custom #tab3 .ui-layout-resizer-closed { border: 1px solid #9055cf; }
	.custom #tab3 .toolbar ,
	.custom #tab3 .ui-widget-header	{ background: #6f2ab8; }
	.custom #tab3 .ui-widget-footer	{ background: #9055cf; }
	/*
	.custom #tab3 > .ui-layout-center ,
	.custom #tab3 .ui-layout-pane .ui-layout-pane { border: 2px solid #9055cf; }
	.custom #tab3 .ui-widget-content{ border: 1px solid #6f2ab8; }
	*/


	/*
	 *	Layout Resizers 
	 */
	.ui-layout-resizer-sliding {
		background-color:	#BBB; /* overridden by custom theme, above */
	}
	.ui-layout-resizer-open-hover,
	.ui-layout-resizer-drag, /* cloned bar */
	.ui-layout-resizer-dragging {
		background:			#BBB !important; /* need higher specificity */
		opacity: 			0.5 !important;  /* ditto - not sure why? */
		filter: 			Alpha(Opacity=50) !important;
	}
	.ui-layout-resizer-dragging-limit {
		background:			#d44848 !important;
	}
	.ui-layout-resizer-closed-hover {
		background:			#ebd5aa;
	}

	/*
	 *	Layout Togglers 
	 */
	.ui-layout-toggler {
		background-color: #FFF;
		opacity:		.60;
		filter:			Alpha(Opacity=60);
	}
	.ui-layout-resizer-open-hover .ui-layout-toggler, 
	.ui-layout-toggler-hover {
		opacity:		1;
		filter:			Alpha(Opacity=100);
	}
	.ui-layout-toggler .ui-icon			{ margin:		0 auto; } /* default - center horizontally */
	.ui-layout-toggler-north .ui-icon ,
	.ui-layout-toggler-south .ui-icon	{ margin-top:	-3px; } /* tweak arrow vertical-center */
	.ui-layout-toggler-west .ui-icon ,
	.ui-layout-toggler-east .ui-icon	{ margin-left:	-3px; } /* tweak arrow horizontal-center */
	/* assign arrows for each side & state */
	.ui-layout-toggler-north-closed .ui-icon, 
	.ui-layout-toggler-south-open .ui-icon {
	 	background-position: -64px -192px;	/* ui-icon-circle-triangle-s */
		background-position: -128px -16px;	/* ui-icon-triangle-2-n-s */
		background-position: -64px -16px;	/* ui-icon-triangle-1-s */
	}
	.ui-layout-toggler-south-closed .ui-icon, 
	.ui-layout-toggler-north-open .ui-icon {
	 	background-position: -96px -192px;	/* ui-icon-circle-triangle-n */
		background-position: -128px -16px;	/* ui-icon-triangle-2-n-s */
		background-position: 0 -16px;		/* ui-icon-triangle-1-n */
	}
	.ui-layout-toggler-west-closed .ui-icon, 
	.ui-layout-toggler-east-open .ui-icon {
	 	background-position: -48px -192px;	/* ui-icon-circle-triangle-e */
		background-position: -144px -16px;	/* ui-icon-triangle-2-e-w */
		background-position: -32px -16px;	/* ui-icon-triangle-1-e */
	}
	.ui-layout-toggler-east-closed .ui-icon, 
	.ui-layout-toggler-west-open .ui-icon {
	 	background-position: -80px -192px;	/* ui-icon-circle-triangle-w */
		background-position: -144px -16px;	/* ui-icon-triangle-2-e-w */
		background-position: -96px -16px;	/* ui-icon-triangle-1-w */
	}
	/* ALT Graphics
	.ui-layout-toggler-east-closed, 
	.ui-layout-toggler-west-open	{ background: transparent url(/img/toggle-lt.gif) no-repeat right center; }
	.ui-layout-toggler-west-closed, 
	.ui-layout-toggler-east-open	{ background: transparent url(/img/toggle-rt.gif) no-repeat left center; }
	.ui-layout-toggler-south-closed, 
	.ui-layout-toggler-north-open	{ background: transparent url(/img/toggle-up.gif) no-repeat center bottom; }
	.ui-layout-toggler-north-closed, 
	.ui-layout-toggler-south-open	{ background: transparent url(/img/toggle-dn.gif) no-repeat center top; }
	*/

	</STYLE>


	<script type="text/javascript" src="jsp/autocomplete/jquery.min.js"></script>
	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery-ui-latest.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery.layout-latest.js"></SCRIPT>

	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery.layout.resizeTabLayout.min-1.2.js"></SCRIPT>


	<SCRIPT type="text/javascript">
	/*
	 *	Utility methos used for UI Theme Selector
	 */



	/*
	 *	Define options for all the layouts
	 */

	var pageLayoutOptions = {
		name:					'pageLayout' // only for debugging
	,	resizeWithWindowDelay:	50		// delay calling resizeAll when window is *still* resizing
	//,	resizeWithWindowMaxDelay: 2000	// force resize every XX ms while window is being resized
	,	resizable:				false
	,	slidable:				false
	,	closable:				false
	,	north__paneSelector:	"#outer-north"
	,	center__paneSelector:	"#outer-center" 
	,	south__paneSelector:	"#outer-south" 
	,	south__spacing_open:	0
	,	north__spacing_open:	0
	
	//	add a child-layout inside the center-pane
	,	center__childOptions: {
			name:					'tabsContainerLayout' // only for debugging
		,	resizable:				false
		,	slidable:				false
		,	closable:				false
		,	north__paneSelector:	"#tabbuttons"
		,	center__paneSelector:	"#tabpanels" 
		,	spacing_open:			0
		,	center__onresize:		$.layout.callbacks.resizeTabLayout // resize ALL visible layouts nested inside
		}
	};


	//	define sidebar options here because are used for BOTH east & west tab-panes (see below)
	var sidebarLayoutOptions = {
		name:					'sidebarLayout' // only for debugging
	,	showErrorMessages:		false	// some panes do not have an inner layout
	,	resizeWhileDragging:	true
	,   north__size:			"30%"
	,   south__size:			"30%"
	,	minSize:				100
	,	center__minHeight:		100
	,	spacing_open:			10
	,	spacing_closed:			10
	,	contentSelector:		".ui-widget-content"
	,	togglerContent_open:	'<div class="ui-icon"></div>'
	,	togglerContent_closed:	'<div class="ui-icon"></div>'
	};

	//	options used for the tab-panel-layout on all 3 tabs
	var tabLayoutOptions = {
		name:					'tabPanelLayout' // only for debugging
	,	resizeWithWindow:		false	// required because layout is 'nested' inside tabpanels container
	//,	resizeWhileDragging:	true	// slow in IE because of the nested layouts
	,	resizerDragOpacity:		0.5
	,	north__resizable:		false
	,	south__resizable:		false
	,	north__closable:		false
	,	south__closable:		false
	,	west__minSize:			300
	,	east__minSize:			300
	,	center__minWidth:		300
	,	spacing_open:			10
	,	spacing_closed:			10
	,	contentSelector:		".ui-widget-content"
	,	togglerContent_open:	'<div class="ui-icon"></div>'
	,	togglerContent_closed:	'<div class="ui-icon"></div>'
	,	triggerEventsOnLoad:	true // so center__onresize is triggered when layout inits
	,	center__onresize:		$.layout.callbacks.resizePaneAccordions // resize ALL Accordions nested inside
	,	west__onresize:			$.layout.callbacks.resizePaneAccordions // ditto for west-pane
	/*
	,	center__onresize: function (pane, $Pane) {
			$Pane.find(":ui-accordion").accordion("resize");
		}
	*/

	//	add child-layouts for BOTH the east/west panes (sidebars)
	//	sidebarLayoutOptions was created above so they could be used twice here
	,	west__childOptions:		sidebarLayoutOptions
	,	east__childOptions:		sidebarLayoutOptions
	};


var pageLayout;
	$(document).ready( function() {

		// create the page-layout, which will ALSO create the tabs-wrapper child-layout
		pageLayout = $("body").layout( pageLayoutOptions ); 

	


		// init the tabs inside the center-pane
		// NOTE: layout.center = NEW pane-instance object
		pageLayout.center.pane
			.tabs({
				// using callback addon
				show: $.layout.callbacks.resizeTabLayout

				/* OR using a manual/custom callback
				show: function (evt, ui) {
					var tabLayout = $(ui.panel).data("layout");
					if ( tabLayout ) tabLayout.resizeAll();
				}*/
			})
			// make the tabs sortable
			.find(".ui-tabs-nav") .sortable({ axis: 'x', zIndex: 2 }) .end()
		;
		// after creating the tabs, resize the tabs-wrapper layout...
		// we can access this layout as a 'child' property of the outer-center pane
		pageLayout.center.child.resizeAll();

		// init inner-tabs inside outer-tab #3
		$("#innerTabs").tabs({
			// look for and resize inner-accordion(s) each time a tab-panel is shown
			show: $.layout.callbacks.resizePaneAccordions
		});

		// init ALL the tab-layouts - all use the same options
		// layout-initialization will _complete_ the first time each layout becomes 'visible'
		$("#tab1").layout( tabLayoutOptions );
		$("#tab2").layout( tabLayoutOptions );
		$("#tab3").layout( tabLayoutOptions );

		// init ALL accordions (all have .accordion class assigned)
		// accordions' 'height' (fillSpace) will be reset as each becomes 'visible'
		$(".accordion").accordion({ fillSpace: true });

		/* UI pseudo-classes allow all UI elements to be easily found...
		alert( 'Number of Accordion widgets = '+ $(":ui-accordion").length );
		alert( 'Number of Tabs widgets = '+ $(":ui-tabs").length );
		*/

		//addThemeSwitcher('#outer-north',{ top: '13px', right: '20px' });
		// if a theme is applied by ThemeSwitch *onLoad*, it may change the height of some content,
		// so we need to call resizeLayout to 'correct' any header/footer heights affected
		// call multiple times so fast browsers update quickly, and slower ones eventually!
		// NOTE: this is only necessary because we are changing CSS *AFTER LOADING* (eg: themeSwitcher)
		//setTimeout( resizePageLayout, 1000 ); /* allow time for browser to re-render for theme */
		//setTimeout( resizePageLayout, 5000 ); /* for really slow browsers */
		
	});
	</SCRIPT> 

	
	<%@ include file="../common/yahooonly.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/calenderInt.jsp" %>
<%@include file="/jsp/common/tooltip.jsp" %>
<SCRIPT type="text/javascript" src="jsp/tooltip/tooltip.js"></SCRIPT>
<LINK rel="stylesheet" type="text/css" href="jsp/tooltip/tooltip.css">

<style type="text/css">
	#cal1Container {
	   margin:1em;

	}

	#caleventlog {
		float:left;
		width:50em;
		margin:1em;
		background-color:#eee;
		border:1px solid #000;
	}
	#caleventlog .bd {
		overflow:auto;
		height:30em;
		padding:2px;
	}
	#caleventlog .hd {
		background-color:white;
		border-bottom:1px solid #000;
		font-weight:bold;
		padding:2px;
	}
	#caleventlog .entry {
		margin:0;	
	}
</style>

<style type="text/css">
#example {
    height:50em;
}

label { 
    display:block;
    float:left;
    width:45%;
    clear:left;
}

.clear {
    clear:both;
}

#resp {
    margin:10px;
    padding:10px;
    border:1px solid #ccc;
    background:#fff;
}

#resp li {
    font-family:monospace
}

.yui-pe .yui-pe-content {
    display:none;
}
</style>


</HEAD> 

<script language="javascript">
function dashboardsetup(url){
	GB_showCenter('<%=Constant.getResourceStringValue("hr.user.Dashboard_setup",user.getLocale())%>', url, 600,600, messageret);
}
function reportpage(reportname){

	if(reportname =="timetofill"){
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=timetofillReport",
		  success: function(data){

		  $('#reportdata').html(data);
	
		  }
		});
	}else if(reportname =="vendorperfcomp"){

		$.ajax({
			type: 'GET',
		  url: "reports.do?method=vendorPerformanceComparisonReport",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});
	}else if(reportname =="sourcesofresume"){

		$.ajax({
			type: 'GET',
		  url: "reports.do?method=sourcesOfResumeReport",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});
	}else if(reportname =="recruiterefficiency"){

		$.ajax({
			type: 'GET',
		  url: "reports.do?method=recruiterEfficiencyReport",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});
	}else if(reportname =="requisitionopeningstatistics"){
		//alert("requisitionopeningstatistics");
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=requisitionOpeningOtatisticsReport",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});
	
	}else if(reportname =="declinedofferdratio"){
		//alert("declinedofferdratio");
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=declineOfferdRatio",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});

	}else if(reportname =="applicantofferedratio"){
		//alert("applicantofferedratio");
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=applicantOfferedRatio",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});

	}else if(reportname =="offervsacceptance"){
		//alert("applicantofferedratio");
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=offervsacceptance",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});

	}else if(reportname =="eeo"){
		//alert("applicantofferedratio");
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=eeoreport",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});

	}else if(reportname =="interviewprocessefficiency"){
		//alert("applicantofferedratio");
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=interviewprocessefficiency",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});

	}else if(reportname =="budgettracking"){
		//alert("applicantofferedratio");
		$.ajax({
			type: 'GET',
		  url: "reports.do?method=budgettracking",
		  success: function(data){
		  $('#reportdata').html(data);
	
		  }
		});

	}
	
	
	
	
/*
	$.ajax({
		type: 'GET',
	  url: "jsp/report/timetofillbody.jsp",
	  success: function(data){
	  $('#reportdata').html(data);

	  }
	});
*/


}

function targetSlipingdate() { 
 document.getElementById("targetslipingloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/targetSlipingDateAdmin.jsp",
  success: function(data){
  $('#targetdateslip').html(data);
	document.getElementById("targetslipingloading").style.visibility = "hidden";	
  }
});
} 

function filledActiveRequistion() { 
 document.getElementById("filledActiveRequistionloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/filledActiveRequistionAdmin.jsp",
  success: function(data){
  $('#filledActiveRequistion').html(data);
	document.getElementById("filledActiveRequistionloading").style.visibility = "hidden";	
  }
});
} 


function onboardapplicants() { 
 document.getElementById("onboardapplicantsloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/onBoradJoinedAdmin.jsp",
  success: function(data){
  $('#onboardapplicants').html(data);
	document.getElementById("onboardapplicantsloading").style.visibility = "hidden";	
  }
});
} 

function offerdeclinedapplicants() { 
 document.getElementById("offerdeclinedapplicantsloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/offerDeclinedJoinedAdmin.jsp",
  success: function(data){
  $('#offerdeclinedapplicants').html(data);
	document.getElementById("offerdeclinedapplicantsloading").style.visibility = "hidden";	
  }
});
} 

function mytask() { 
// document.getElementById("mytaskloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "task.do?method=mypendingtasksajax",
  success: function(data){
  $('#mytask').html(data);
	//document.getElementById("mytaskloading").style.visibility = "hidden";	
  }
});
} 
function reports() { 
// document.getElementById("mytaskloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/dashboard.jsp",
  success: function(data){
	
  $('#reportdata').html(data);
	//document.getElementById("mytaskloading").style.visibility = "hidden";	
  }
});
}

function calList() { 
 document.getElementById("calListloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "lov.do?method=callistajax",
  success: function(data){
  $('#calList').html(data);
	document.getElementById("calListloading").style.visibility = "hidden";	
  }
});
} 



function todaysinterviews() { 
 document.getElementById("todaysinterviewsloading").style.visibility = "visible";


 var m_names = new Array("January", "February", "March", 
        "April",
		"May",
		"June", 
		"July", 
		"August", 
		"September", 
        "October",
		"November", 
		"December");

var d = new Date();
var curr_date = d.getDate();
var curr_month = d.getMonth();
var curr_year = d.getFullYear();
var newdate = m_names[curr_month] + " "+curr_date
+ "," +" "+ curr_year;

$.ajax({
	type: 'GET',
  url: "jsp/home/todaysinterviewsAdmin.jsp?date="+newdate,
  success: function(data){
  $('#todaysinterviews').html(data);
	document.getElementById("todaysinterviewsloading").style.visibility = "hidden";	
  }
});
} 

</script>

<script type="text/javascript">
function configuareColumns(screenname){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsGeneric&screenname="+screenname;
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user.getLocale())%>',url,500,700, messageret);
}
function configuredashboard(){
	var url = "<%=request.getContextPath()%>/dashboard.do?method=configureDashBoard";
GB_showCenter('<%=Constant.getResourceStringValue("hr.user.configure.dashboard",user.getLocale())%>',url,300,500, messageretrefresh);
}

function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>', url, messageret);
}

function showmessage(returnval) { 
	//window.location.reload();
	}

function editJobReqTemplte(tmpl_id){
		var url = "<%=request.getContextPath()%>/jobtemplate.do?method=edittemplate&templateId="+tmpl_id;
	//parent.showPopWin(url, 800, 600, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.edittemp",user.getLocale())%>', url, messageret);
}

function addRequisition(){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=createjobreq";
	parent.setPopTitle1("<%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%>");
	parent.showPopWin(url, 600, 300, editreq);
}
   
function editreq(retval){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+retval;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%>', url, messageret);
}

function userDetails(id){
	var url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}


function messageret(){
	//window.location.reload();
	//history.go(0);
	//location.href="dashboard.do?method=dashboardlist";

			}

function messageretrefresh(){
	window.location.reload();
	//history.go(0);
	//location.href="dashboard.do?method=dashboardlist";

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


</script>
<BODY> 


<DIV id="outer-north" style="overflow:hidden;" onmouseover="pageLayout.allowOverflow('north')" onmouseout="pageLayout.resetOverflow(this)">

<%@ include file="../common/menu.jsp" %>
  
</div>




<DIV id="outer-center" class="hidden">

	<UL id="tabbuttons" class="hidden">
		<!--<LI class="tab1"><A href="#tab1">My home page</A></LI>-->
		<!--<LI class="tab2"><A href="#tab2">Requistions</A></LI>-->
		<LI class="tab3"><A href="#tab3">Reports</A></LI>
		<div style=" position: absolute;right: 20px; ">
<a  style="color: blue;text-decoration: underline;" href="#" onClick="configuredashboard()"><%=Constant.getResourceStringValue("hr.user.configure.dashboard",user.getLocale())%></a>
		</div>
	</UL>

	<DIV id="tabpanels">

	<DIV id="tab1" class="tab-panel ui-tabs-hide">
			<!--<DIV class="ui-layout-north ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-active">
<a  style="color: blue;text-decoration: underline;" href="#" onClick="configuredashboard()"><%=Constant.getResourceStringValue("hr.user.configure.dashboard",user.getLocale())%></a>
				</DIV>
			</DIV>-->
			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					<%@ include file="../common/Footer.jsp" %>
				</DIV>
			</DIV>
			<DIV id="innerTabs" class="ui-layout-center container tabs">
				<!--<DIV class="ui-widget-header ui-corner-top"> Dashboard </DIV>-->
				<UL>
					<LI class="tab1"><A href="#simpleTab1">Dashboard</A></LI>
					<LI class="tab2"><A href="#simpleTab2">My tasks</A></LI>
					<LI class="tab3"><A href="#simpleTab3">Interviews</A></LI>
				</UL>
				<DIV class="ui-widget-content" style="border-top: 0;">

					<DIV id="simpleTab1" class="container" style="height: 100%;">
					<!--dashboard start-->
						<DIV class="accordion">
				
					
								
							   
						</DIV>
					</DIV>
					
				<!--dashboard end-->
					<DIV id="simpleTab2" style="height:380px">
						<!--my task start-->
							<body class="yui-skin-sam">
							<div id="dynamicdatamypendingtask1"></div>
							</body>
						<!--my task end-->
					</DIV>

					<DIV id="simpleTab3"> 
					<!--interviews  start-->
					<i><!-- <%=Constant.getResourceStringValue("aquisition.interview.list.note",user.getLocale())%> --></i>
								<span id="calList">
								</span>
								<span  id="calListloading">
								<img src="jsp/images/indicator.gif"/>
								</span>
					
					<!--interviews  stop-->
					</DIV>

				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
			</DIV>



		</DIV><!-- /#tab1 -->


		<DIV id="tab2" class="tab-panel ui-tabs-hide">

			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					<%@ include file="../common/Footer.jsp" %>
				</DIV>
			</DIV>
			<DIV class="ui-layout-center">
				<DIV class="ui-widget-header ui-corner-top">Center-Center</DIV>
				<DIV id="notes" class="ui-widget-content">
					
				</DIV>
				
			</DIV>
			
			
			<DIV class="ui-layout-east">
<DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">East-Center</DIV>
	<DIV class="ui-widget-content">
		
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-Center Footer&nbsp; (will&nbsp;wrap&nbsp;when&nbsp;narrow)</DIV>
</DIV>
<DIV class="ui-layout-south">
	<DIV class="ui-widget-header ui-corner-top">East-South</DIV>
	<DIV class="ui-widget-content">
		
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-South Footer</DIV>
</DIV>
			</DIV>
		</DIV><!-- /#tab2 -->

		<DIV id="tab3" class="tab-panel ui-tabs-hide">

			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					<%@ include file="../common/Footer.jsp" %>
				</DIV>
			</DIV>
			<DIV class="ui-layout-center" style="overflow:auto">
				<DIV class="ui-widget-content container">
				<span id="reportdata">
				
				</span>

				</DIV>
				
			</DIV>
			
			<DIV class="ui-layout-east">
<DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">Reports Link</DIV>
	<DIV class="ui-widget-content">
<%@ include file="../report/reportLinks.jsp" %>
      

	</DIV>

</DIV>
		<DIV class="ui-layout-south">
			<DIV class="ui-widget-header ui-corner-top">Configure</DIV>
			<DIV class="ui-widget-content">
			 <%
		String editdashboard = request.getContextPath()+"/dashboard.do?method=mydashboardsetup&userId="+user.getUserId();
		%>

				<DIV><A href="#" onClick="javascript:dashboardsetup('<%=editdashboard%>')">Configure reports</A></DIV>

			</DIV>
			
		</DIV>
</DIV>
		</DIV><!-- /#tab3 -->

		
	</DIV><!-- /#tabpanels -->

</DIV><!-- /#outer-center -->

</BODY>
</HTML>

<script language="javascript">


reports();



</script>



