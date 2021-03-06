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
Map widgetmap = BOFactory.getUserBO().getWidgetsMap(user.getUserId());
%>

<!DOCTYPE html> 
<HTML> 
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<TITLE>Layouts Inside Tabs</TITLE>

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
		overflow:		visible;
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
				overflow:	hidden;
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
		overflow:		hidden;
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

	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery-latest.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery-ui-latest.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery.layout-latest.js"></SCRIPT>

	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery.layout.resizeTabLayout.min-1.2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="jsp/jquery/js/jquery.layout.resizePaneAccordions.min-1.0.js"></SCRIPT>

    <SCRIPT type="text/javascript" src="jsp/jquery/js/themeswitchertool.js"></SCRIPT> 
	<SCRIPT type="text/javascript" src="jsp/jquery/js/debug.js"></SCRIPT>

	<SCRIPT type="text/javascript">
	/*
	 *	Utility methos used for UI Theme Selector
	 */
	function toggleCustomTheme () {
		$('body').toggleClass('custom');
		resizePageLayout();
	};

	function resizePageLayout () {
		var pageLayout = $("body").data("layout");
		if (pageLayout) pageLayout.resizeAll();
	};


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
  <style>
              /* menu */
        #menu{ margin:0px; padding:0px; list-style:none; color:#fff; line-height:45px; display:inline-block; float:left; z-index:1000; }
        #menu a { color:#fff; text-decoration:none; }
        #menu > li {background:#172322 none repeat scroll 0 0; cursor:pointer; float:left; position:relative;padding:0px 10px;}
        #menu > li a:hover {color:#B0D730;}
        #menu .logo {background:transparent none repeat scroll 0% 0%; padding:0px; background-color:Transparent;}
        /* sub-menus*/
        #menu ul { padding:0px; margin:0px; display:block; display:inline;}
        #menu li ul { position:absolute; left:-10px; top:0px; margin-top:45px; width:150px; line-height:16px; background-color:#172322; color:#0395CC; /* for IE */ display:none; }
        #menu li:hover ul { display:block;}
        #menu li ul li{ display:block; margin:5px 20px; padding: 5px 0px;  border-top: dotted 1px #606060; list-style-type:none; }
        #menu li ul li:first-child { border-top: none; }
        #menu li ul li a { display:block; color:#0395CC; }
        #menu li ul li a:hover { color:#7FCDFE; }
        /* main submenu */
        #menu #main { left:0px; top:-20px; padding-top:20px; background-color:#7cb7e3; color:#fff; z-index:999;}
        /* search */
        .searchContainer div { background-color:#fff; display:inline; padding:5px;}
        .searchContainer input[type="text"] {border:none;}
        .searchContainer img { vertical-align:middle;}
        /* corners*/
        #menu .corner_inset_left { position:absolute; top:0px; left:-12px;}
        #menu .corner_inset_right { position:absolute; top:0px; left:150px;}
        #menu .last { background:transparent none repeat scroll 0% 0%; margin:0px; padding:0px; border:none; position:relative; border:none; height:0px;}
        #menu .corner_left { position:absolute; left:0px; top:0px;}
        #menu .corner_right { position:absolute; left:132px; top:0px;}
        #menu .middle { position:absolute; left:18px; height: 20px; width: 115px; top:0px;}
    
    </style>



	
	<%@ include file="../common/dashboardinclude.jsp" %>
<%@ include file="../common/greybox.jsp" %>

</HEAD> 

<script language="javascript">


function targetSlipingdate() { 
 document.getElementById("targetslipingloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jobreq.do?method=targetSlipingdate",
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
  url: "jsp/home/filledActiveRequistionHiringMgr.jsp",
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
  url: "jsp/home/onBoradJoinedHiringMgr.jsp",
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
  url: "jsp/home/offerDeclinedJoinedHiringMgr.jsp",
  success: function(data){
  $('#offerdeclinedapplicants').html(data);
	document.getElementById("offerdeclinedapplicantsloading").style.visibility = "hidden";	
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
  url: "jsp/home/todaysinterviewsHiringMgr.jsp?date="+newdate,
  success: function(data){
  $('#todaysinterviews').html(data);
	document.getElementById("todaysinterviewsloading").style.visibility = "hidden";	
  }
});
} 


function configuareColumns(screenname){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsGeneric&screenname="+screenname;
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user.getLocale())%>',url,500,700, messageret);
}
function configuredashboard(){
	var url = "<%=request.getContextPath()%>/dashboard.do?method=configureDashBoard";
GB_showCenter('<%=Constant.getResourceStringValue("hr.user.configure.dashboard",user.getLocale())%>',url,400,500, messageretrefresh);
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

<DIV id="outer-north" onmouseover="pageLayout.allowOverflow('north')" onmouseout="pageLayout.resetOverflow(this)">

    <div style="margin-left:10px;">
        <ul id="menu">
            <li class="logo">
                <img style="float:left;" alt="" src="jsp/vimco/menu_left.png"/>
                <ul id="main">
                    <li>Welcome to <b>Hire360</b> Talent aquisition portal</li>
                    <li class="last">
                        <img class="corner_left" alt="" src="jsp/vimco/corner_blue_left.png"/>
                        <img class="middle" alt="" src="jsp/vimco/dot_blue.png"/>
                        <img class="corner_right" alt="" src="jsp/vimco/corner_blue_right.png"/>
                    </li>
                </ul>
                
            </li>
            <li><a href="#">Login</a>
            </li>
            <li><a href="#">Help</a>
                <ul id="help">
                    <li>
                        <img class="corner_inset_left" alt="" src="jsp/vimco/corner_inset_left.png"/>
                        <a href="#">General help</a>
                        <img class="corner_inset_right" alt="" src="jsp/vimco/corner_inset_right.png"/>
                    </li>
                    <li><a href="#">Posts</a></li>
                    <li><a href="#">Pages</a></li>
                    <li class="last">
                        <img class="corner_left" alt="" src="jsp/vimco/corner_left.png"/>
                        <img class="middle" alt="" src="jsp/vimco/dot.gif"/>
                        <img class="corner_right" alt="" src="jsp/vimco/corner_right.png"/>
                    </li>
                </ul>
            </li>
			<li><a href="#">test</a>
                <ul id="test">
                    <li>
                        <img class="corner_inset_left" alt="" src="jsp/vimco/corner_inset_left.png"/>
                        <a href="#">General help</a>
                        <img class="corner_inset_right" alt="" src="jsp/vimco/corner_inset_right.png"/>
                    </li>
                    <li><a href="#">Posts</a></li>
                    <li><a href="#">Pages</a></li>
                    <li class="last">
                        <img class="corner_left" alt="" src="jsp/vimco/corner_left.png"/>
                        <img class="middle" alt="" src="jsp/vimco/dot.gif"/>
                        <img class="corner_right" alt="" src="jsp/vimco/corner_right.png"/>
                    </li>
                </ul>
            </li>
            <li class="searchContainer">
                <div>
                <input type="text" id="searchField" />
                <img src="jsp/vimco/magnifier.png" alt="Search" onclick="alert('You clicked on search button')" /></div>
                <ul id="search">
                    <li>
                        <img class="corner_inset_left" alt="" src="jsp/vimco/corner_inset_left.png"/>
                        <input id="cbxAll" type="checkbox" />All
                        <img class="corner_inset_right" alt="" src="jsp/vimco/corner_inset_right.png"/>
                    </li>
                    <li><input id="Articles" type="checkbox" />Articles</li>
                    <li><input id="Tutorials" type="checkbox" />Tutorials</li>
                    <li><input id="Reviews" type="checkbox" />Reviews</li>
                    <li><input id="Resources" type="checkbox" />Resources</li>
                    <li class="last">
                        <img class="corner_left" alt="" src="jsp/vimco/corner_left.png"/>
                        <img class="middle" alt="" src="jsp/vimco/dot.gif"/>
                        <img class="corner_right" alt="" src="jsp/vimco/corner_right.png"/>
                    </li>
                </ul>
            </li>
        </ul>
      
    </div>
  
</div>




<DIV id="outer-center" class="hidden">

	<UL id="tabbuttons" class="hidden">
		<LI class="tab1"><A href="#tab1">My home page</A></LI>
		<LI class="tab2"><A href="#tab2">Outer Tab Two</A></LI>
		<LI class="tab3"><A href="#tab3">Outer Tab Three</A></LI>
	</UL>

	<DIV id="tabpanels">

	<DIV id="tab1" class="tab-panel ui-tabs-hide">
			<DIV class="ui-layout-north ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-active">
										&nbsp;&nbsp;<a  href="#" style="color: blue;text-decoration: underline;" onClick="addRequisition()"><%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%></a>
&nbsp;&nbsp;<a  style="color: blue;text-decoration: underline;" href="jobreq.do?method=myjobreqList" ><%=Constant.getResourceStringValue("Requisition.my.requistions",user.getLocale())%></a>
&nbsp;&nbsp;<a  style="color: blue;text-decoration: underline;" href="#" onClick="configuredashboard()"><%=Constant.getResourceStringValue("hr.user.configure.dashboard",user.getLocale())%></a>
				</DIV>
			</DIV>
			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					satya Statusbar - tab3
				</DIV>
			</DIV>
			<DIV id="innerTabs" class="ui-layout-center container tabs">
				<DIV class="ui-widget-header ui-corner-top"> Center -Center </DIV>
				<UL>
					<LI class="tab1"><A href="#simpleTab1">Dashboard</A></LI>
					<LI class="tab2"><A href="#simpleTab2">My task</A></LI>
					<LI class="tab3"><A href="#simpleTab3">Interviews</A></LI>
				</UL>
				<DIV class="ui-widget-content" style="border-top: 0;">

					<DIV id="simpleTab1" class="container" style="height: 100%;">
						<DIV class="accordion">
				
							 <% 
						String value = (String)widgetmap.get("MY_ACTIVE_REQ");
					   String isMyActiveReqVisible="false";
					   if(value != null && value.equals("A")){
						   isMyActiveReqVisible="true";
					   }
						%>

					 <% if(isMyActiveReqVisible.equals("true")){%>
							<H3><A href="#">My active requistions</A></H3>
							<DIV>
							<a href="#" onClick="configuareColumns('<%=Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS%>');return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user.getLocale())%></a>
								<body class="yui-skin-sam">
								<div id="dynamicdata"></div>
								</body>
							</DIV>
							<%}%>
								<% 
							   value = (String)widgetmap.get("OFFER_IN_PROCESS");
							   String isOfferInProcessVisible="false";
							   if(value != null && value.equals("A")){
								   isOfferInProcessVisible="true";
							   }
							   %>

							   <% if(isOfferInProcessVisible.equals("true")){%>
							<H3><A href="#">Applicants in offer process</A></H3>
							<DIV>
							<a href="#" onClick="configuareColumns('<%=Common.DASHBOARD_RECRUITER_IN_OFFER_PROCESS%>');return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user.getLocale())%></a>
                               	<body class="yui-skin-sam">
								<div id="dynamicdata2"></div>
								</body>	
							</DIV>
					           <%}%>
							   <% 
							   value = (String)widgetmap.get("OFFER_ACCEPTED");
							   String isOfferAcceptedVisible="false";
							   if(value != null && value.equals("A")){
								   isOfferAcceptedVisible="true";
							   }
							   %>
							   <% if(isOfferAcceptedVisible.equals("true")){%>
							<H3><A href="#">Offer accepted applicants (joining within 90 days)</A></H3>
							<DIV>
							<a href="#" onClick="configuareColumns('<%=Common.DASHBOARD_RECRUITER_IN_JOINED_PROCESS%>');return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user.getLocale())%></a>
			

							<body class="yui-skin-sam">
							<div id="dynamicdata1"></div>
							</body>
							</DIV>
							<%}%>
						</DIV>
					</DIV>

					<DIV id="simpleTab2">
						Tab #2 Content
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
						<P>List of Items</P>
					</DIV>

					<DIV id="simpleTab3"> Tab #1 Content </DIV>

				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">Center-Center Footer</DIV>
			</DIV>
			<DIV class="ui-layout-west">

    <% 
		   value = (String)widgetmap.get("FILLED_ACTIVE_REQ");
		   String isFilledActiveReqVisible="false";
		   if(value != null && value.equals("A")){
			   isFilledActiveReqVisible="true";
		   }
		   %>

		   <% if(isFilledActiveReqVisible.equals("true")){%>
		   <DIV class="ui-layout-north">
	<DIV class="ui-widget-header ui-corner-top"><%=Constant.getResourceStringValue("filled.active.requisitions",user.getLocale())%></DIV>
	<DIV class="ui-widget-content">
		<span id="filledActiveRequistion">
		</span>
		<span  id="filledActiveRequistionloading">
		<img src="jsp/images/indicator.gif"/>
		</span>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
	</DIV>
	 <%}%>


         <% 
		   value = (String)widgetmap.get("ONBOARDING_APPLICANTS");
		   String isOnboardAppVisible="false";
		   if(value != null && value.equals("A")){
			   isOnboardAppVisible="true";
		   }
		   %>

		   <% if(isOnboardAppVisible.equals("true")){%>
		   <DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">On board applicants ( 90 days)</DIV>
	<DIV class="ui-widget-content">
		 <span id="onboardapplicants">
		</span>
		<span  id="onboardapplicantsloading">
		<img src="jsp/images/indicator.gif"/>
		</span>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
	</DIV>
    <%}%>


         <% 
		   value = (String)widgetmap.get("IMP_LINKS");
		   String isImpLinkVisible="false";
		   if(value != null && value.equals("A")){
			   isImpLinkVisible="true";
		   }
		   %>

		   <% if(isImpLinkVisible.equals("true")){%>
		   <DIV class="ui-layout-south">
	<DIV class="ui-widget-header ui-corner-top">Important links</DIV>
	<DIV class="ui-widget-content">
		<%@ include file="implinks.jsp" %>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
	</DIV>
	<%}%>

			</DIV>
	<DIV class="ui-layout-east">
   			<% 
		   value = (String)widgetmap.get("REQ_SLIPING_TARGET");
		   String isReqSlipingTargetVisible="false";
		   if(value != null && value.equals("A")){
			   isReqSlipingTargetVisible="true";
		   }
		   %>
		   <% if(isReqSlipingTargetVisible.equals("true")){%>
			<DIV class="ui-layout-north">

				<DIV class="ui-widget-header ui-corner-top">Requistions Slipping target</DIV>
				<DIV class="ui-widget-content">
				 <span id="targetdateslip">

				</span>
				 <span  id="targetslipingloading">
				 <img src="jsp/images/indicator.gif"/>
				 </span>
		
				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>

			</DIV>
			<%}%>

			<% 
		   value = (String)widgetmap.get("OFFER_DECLINED");
		   String isOfferDeclinedVisible="false";
		   if(value != null && value.equals("A")){
			   isOfferDeclinedVisible="true";
		   }
		   %>

		   <% if(isOfferDeclinedVisible.equals("true")){%> 

			<DIV class="ui-layout-center">
				<DIV class="ui-widget-header ui-corner-top">Offer declined (90 days)</DIV>
				<DIV class="ui-widget-content">
			    <span id="offerdeclinedapplicants">
				 </span>
				 <span  id="offerdeclinedapplicantsloading">
				 <img src="jsp/images/indicator.gif"/>
				 </span>

				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
			</DIV>
			  <%}%>
		<% 
		   value = (String)widgetmap.get("RECENT_INTERVIEWS");
		   String isRecentIntVisible="false";
		   if(value != null && value.equals("A")){
			   isRecentIntVisible="true";
		   }
		   %>   
		   <% if(isRecentIntVisible.equals("true")){%> 
			<DIV class="ui-layout-south">
				<DIV class="ui-widget-header ui-corner-top">Recent interviews</DIV>
				<DIV class="ui-widget-content">
				   <span id="todaysinterviews">

				 </span>
				 <span  id="todaysinterviewsloading">
				 <img src="jsp/images/indicator.gif"/>
				 </span>
				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
			</DIV>
			<%}%>
			</DIV>
		</DIV><!-- /#tab1 -->


		<DIV id="tab2" class="tab-panel ui-tabs-hide">
			<DIV class="ui-layout-north ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-active">
					Toolbar - tab1
				</DIV>
			</DIV>
			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					Statusbar - tab1
				</DIV>
			</DIV>
			<DIV class="ui-layout-center">
				<DIV class="ui-widget-header ui-corner-top">Center-Center</DIV>
				<DIV id="notes" class="ui-widget-content">
					<H4>Layout Version</H4>
					<P><STRONG>This page uses Layout 1.3 RC-29.15 (or higher).</STRONG>
						The content-sizing in this version was improved for both accuracy and speed.
						It also does auto-resizing of directly nested-layouts.
						Plus new functionality was added so layouts could be created when not visible.
					</P>
					<H4>Layout Notes</H4>
					<UL>
						<LI>BODY has 20px left/right padding for cosmetic purposes</LI>
						<LI>BODY has min-width/min-height, so page/layout will scroll if gets too small</LI>
						<LI>The Tabs are sortable (grab-n-drag to rearrange)</LI>
					</UL>
					<H4>Themes</H4>
					<UL>
						<LI>Click '<STRONG>Toggle Custom Theme</STRONG>' above to remove the
							custom theme that is active onLoad. 
						</LI>
						<LI>Click '<STRONG>Resize Layout</STRONG>' above after selecting a new
							UI Theme from the picklist because Layout may need to adjust header/footer 
							heights to account for different padding and/or borders in the new themes.
						</LI>
					</UL>
				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">Center-Center Footer</DIV>
			</DIV>
			<DIV class="ui-layout-west">
<DIV class="ui-layout-north">
	<DIV class="ui-widget-header ui-corner-top">West-North</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">West-North Footer&nbsp; (will&nbsp;wrap&nbsp;when&nbsp;narrow)</DIV>
</DIV>
<DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">West-Center</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">West-Center Footer&nbsp; (will&nbsp;wrap&nbsp;when&nbsp;narrow)</DIV>
</DIV>
<DIV class="ui-layout-south">
	<DIV class="ui-widget-header ui-corner-top">West-South</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">West-South Footer</DIV>
</DIV>
			</DIV>
			<DIV class="ui-layout-east">
<DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">East-Center</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-Center Footer&nbsp; (will&nbsp;wrap&nbsp;when&nbsp;narrow)</DIV>
</DIV>
<DIV class="ui-layout-south">
	<DIV class="ui-widget-header ui-corner-top">East-South</DIV>
	<DIV class="ui-widget-content">
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-South Footer</DIV>
</DIV>
			</DIV>
		</DIV><!-- /#tab2 -->

		<DIV id="tab3" class="tab-panel ui-tabs-hide">
			<DIV class="ui-layout-north ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-active">
					Toolbar - tab2
				</DIV>
			</DIV>
			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					Statusbar - tab2
				</DIV>
			</DIV>
			<DIV class="ui-layout-center">
				<DIV class="ui-widget-header ui-corner-top">Center-Center</DIV>
				<DIV class="ui-widget-content container">
<DIV class="accordion">

		<H3><A href="#">Section 1</A></H3>
		<DIV>
			<P>Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. 
				Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc.</P>
			<P>Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. 
				Vestibulum a velit eu ante scelerisque vulputate.</P>
		</DIV>

		<H3><A href="#">Section 2</A></H3>
		<DIV>
			<P style="font-weight: bold;">Sed Non Urna</P>
			<P>Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus.
				Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit,
				faucibus interdum tellus libero ac justo.</P>
			<P>Vivamus non quam. In suscipit faucibus urna.</P>
		</DIV>

		<H3><A href="#">Section 3</A></H3>
		<DIV>
			<P>Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
				Phasellus pellentesque purus in massa. Aenean in pede.</P>
			<P>Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, 
				magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.</P>
			<UL>
				<LI>List item one</LI>
				<LI>List item two</LI>
				<LI>List item three</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
			</UL>
		</DIV>

		<H3><A href="#">Section 4</A></H3>
		<DIV>
			<P>Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames 
				ac turpis egestas.</P>
			<P>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
				Aenean lacinia mauris vel est.</P>
			<P>Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
				Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</P>
		</DIV>

</DIV>
				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">Center-Center Footer</DIV>
			</DIV>
			<DIV class="ui-layout-west">
<DIV class="accordion">

		<H3><A href="#">Section 1</A></H3>
		<DIV>
			<P>Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. 
				Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc.</P>
			<P>Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. 
				Vestibulum a velit eu ante scelerisque vulputate.</P>
		</DIV>

		<H3><A href="#">Section 2</A></H3>
		<DIV>
			<P style="font-weight: bold;">Sed Non Urna</P>
			<P>Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus.
				Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit,
				faucibus interdum tellus libero ac justo.</P>
			<P>Vivamus non quam. In suscipit faucibus urna.</P>
		</DIV>

		<H3><A href="#">Section 3</A></H3>
		<DIV>
			<P>Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
				Phasellus pellentesque purus in massa. Aenean in pede.</P>
			<P>Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, 
				magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.</P>
			<UL>
				<LI>List item one</LI>
				<LI>List item two</LI>
				<LI>List item three</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
				<LI>Another Item</LI>
			</UL>
		</DIV>

		<H3><A href="#">Section 4</A></H3>
		<DIV>
			<P>Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames 
				ac turpis egestas.</P>
			<P>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
				Aenean lacinia mauris vel est.</P>
			<P>Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
				Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</P>
		</DIV>

</DIV>
<!--
<DIV class="ui-layout-north">
	<DIV class="ui-widget-header ui-corner-top">West-North</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">West-North Footer</DIV>
</DIV>
<DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">West-Center</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">West-Center Footer</DIV>
</DIV>
<DIV class="ui-layout-south">
	<DIV class="ui-widget-header ui-corner-top">West-South</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">West-South Footer</DIV>
</DIV>
-->
			</DIV>
			<DIV class="ui-layout-east">
<DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">East-Center</DIV>
	<DIV class="ui-widget-content">
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
		<P>List of Items</P>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-Center Footer</DIV>
</DIV>
<DIV class="ui-layout-south">
	<DIV class="ui-widget-header ui-corner-top">East-South</DIV>
	<DIV class="ui-widget-content">
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
		<DIV><A href="#">Another Item</A></DIV>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-South Footer</DIV>
</DIV>
			</DIV>
		</DIV><!-- /#tab3 -->

		
	</DIV><!-- /#tabpanels -->

</DIV><!-- /#outer-center -->

</BODY>
</HTML>

<%
 String strcolumncontentassignedreq = ScreenSettingUtils.getApplicationScreenSettings(user,Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS);
 String strkeycontentassignedreq = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS);

%>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("jobreqId") + "');return false;"+ ">" + sData + "</a>";
		
		 elCell.innerHTML = editreqd;

        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReqTemplte('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }


	var summaryUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='jobreq.do?method=requistionapplicantlist&backurl=dashboard.do?method=dashboardlist&state=0&requistionId=" + oRecord.getData("jobreqId")+"&secureid=" + oRecord.getData("uuid") +"'"+ ">" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>" + "</a>";
        };


	var formatOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("currentOwnerName");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("currentOwnerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else {
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("currentOwnerName");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("currentOwnerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        };

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"templateId", hidden:true},
			{key:"currentOwnerId", hidden:true},
			{key:"isGroup", hidden:true},
            {key:"jobreqName", label:"<%=Constant.getResourceStringValue("Requisition.jobreqname",user.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
		<%=strcolumncontentassignedreq%>
		{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>", sortable:false, resizeable:true,formatter:summaryUrl}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/myjobrequisitionlistpage.jsp?currentuserid=<%=user.getUserId()%>&currentusername=<%=user.getUserName()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"currentOwnerId"},
			{key:"isGroup"},
 <%=strkeycontentassignedreq%>
			
			
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<%
 String strcolumncontentofferprocess = ScreenSettingUtils.getApplicationScreenSettings(user,Common.DASHBOARD_RECRUITER_IN_OFFER_PROCESS);
 String strkeycontentofferprocess = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.DASHBOARD_RECRUITER_IN_OFFER_PROCESS);

%>

<script type="text/javascript">

YAHOO.example.DynamicData2 = function() {

	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&backtolisturl=dashboard.do?method=dashboardlist&applicantId=" + oRecord.getData("applicantId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + sData + "</a>";
        };


var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
        
        }

var formatYesNo = function(elCell, oRecord, oColumn, sData) {
		 if(oRecord.getData("initiateJoiningProcess")	== "Y"){
			  elCell.innerHTML = "<%=Constant.getResourceStringValue("hr.text.yes",user.getLocale())%>";
		 }else{
			 elCell.innerHTML =  "<%=Constant.getResourceStringValue("hr.text.no",user.getLocale())%>";
		 }
        
        
        }

var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else{
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("offerownerId")+ "'"+","+"'"+ oRecord.getData("isofferownerGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };

	var formatApplicantOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownernamegroup");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownergroupId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else if(oRecord.getData("isGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownername");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        };

	var formatUrlForRecruiter = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("recruitergroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("recruitergroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("recruiterId")+ "'"+","+"'"+ oRecord.getData("recruitergroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };


    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"reqId", hidden:true},	
		{key:"uuid", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
		{key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrl},
    <%=strcolumncontentofferprocess%>
				{key:"isofferownerGroup", hidden:true}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/myofferapplicantslistpage.jsp?ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            <%=strkeycontentofferprocess%>
			
	
		
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata2", myColumnDefs, myDataSource, myConfigs);
    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<%
 String strcolumncontentjoiningprocess = ScreenSettingUtils.getApplicationScreenSettings(user,Common.DASHBOARD_RECRUITER_IN_JOINED_PROCESS);
 String strkeycontentjoiningprocess = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.DASHBOARD_RECRUITER_IN_JOINED_PROCESS);

%>

<script type="text/javascript">

YAHOO.example.DynamicData1 = function() {

	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&backtolisturl=dashboard.do?method=dashboardlist&applicantId=" + oRecord.getData("applicantId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + sData + "</a>";
        };


var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
        
        };

var formatYesNo = function(elCell, oRecord, oColumn, sData) {
		 if(oRecord.getData("initiateJoiningProcess")	== "Y"){
			  elCell.innerHTML = "<%=Constant.getResourceStringValue("hr.text.yes",user.getLocale())%>";
		 }else{
			 elCell.innerHTML =  "<%=Constant.getResourceStringValue("hr.text.no",user.getLocale())%>";
		 }
        
        
        };


var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else{
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("offerownerId")+ "'"+","+"'"+ oRecord.getData("isofferownerGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };
	var formatApplicantOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownernamegroup");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownergroupId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else if(oRecord.getData("isGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownername");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        };

	var formatUrlForRecruiter = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("recruitergroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("recruitergroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("recruiterId")+ "'"+","+"'"+ oRecord.getData("recruitergroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };


    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"reqId", hidden:true},	
		{key:"uuid", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrl},
          <%=strcolumncontentjoiningprocess%>
				{key:"isofferownerGroup", hidden:true}
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/mytargetjoiningapplicantslistpage.jsp?interviewstate=<%=Common.OFFER_ACCEPTED%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
           <%=strkeycontentjoiningprocess%>		
	
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=5", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:5 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata1", myColumnDefs, myDataSource, myConfigs);
    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<script language="javascript">
var isFilledActiveReqVisible="<%=isFilledActiveReqVisible%>";
if(isFilledActiveReqVisible == "true"){
filledActiveRequistion();
}
var isReqSlipingTargetVisible="<%=isReqSlipingTargetVisible%>";
if(isReqSlipingTargetVisible == "true"){
targetSlipingdate();
}
var isOnboardAppVisible="<%=isOnboardAppVisible%>";
if(isOnboardAppVisible == "true"){
onboardapplicants();
}
var isOfferDeclinedVisible="<%=isOfferDeclinedVisible%>";
if(isOfferDeclinedVisible == "true"){
offerdeclinedapplicants();
}
var isRecentIntVisible="<%=isRecentIntVisible%>";
if(isRecentIntVisible == "true"){
todaysinterviews();
}
</script>

