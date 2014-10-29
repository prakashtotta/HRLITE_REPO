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

	<TITLE>Hires360 My home page</TITLE>

	<LINK rel="stylesheet" type="text/css" href="jsp/jquery/themes/base/jquery.ui.all.css">

	<STYLE type="text/css">

		.button1 {
   border-top: 1px solid #96d1f8;
   background: #3B5999;
   padding: 5px 10px;
   border-radius: 8px;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: #ccc;
   font-size: 14px;
   font-family: Georgia, serif;
   text-decoration: none;
   vertical-align: middle;
   }
.button1:hover {
   border-top-color: #3B5998;
   background: #3B5998;
   color: white;
   }

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


	<SCRIPT type="text/javascript" src="jsp/jquery/jquery-1.7.1.js"></SCRIPT>

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
		//pageLayout = $("body").layout( pageLayoutOptions ); 

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


function targetSlipingdate() { 
 document.getElementById("targetslipingloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jobreq.do?method=targetSlipingdate"+"&ddd="+(new Date).getTime(),
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
  url: "jsp/home/filledActiveRequistionHiringMgr.jsp"+"?ddd="+(new Date).getTime(),
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
  url: "jsp/home/onBoradJoinedHiringMgr.jsp"+"?ddd="+(new Date).getTime(),
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
 url: "jsp/home/reqOpenStatus.jsp"+"?type=hiringmgr&ddd="+(new Date).getTime(),
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
  url: "task.do?method=mypendingtasksajax"+"&ddd="+(new Date).getTime(),
  success: function(data){
  $('#mytask').html(data);
	//document.getElementById("mytaskloading").style.visibility = "hidden";	
  }
});
} 

function calList() { 
 document.getElementById("calListloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "lov.do?method=callistajax"+"&ddd="+(new Date).getTime(),
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
  url: "jsp/home/todaysinterviewsHiringMgr.jsp?date="+newdate,
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
	location.href="dashboard.do?method=dashboardlist"+"&ddd="+(new Date).getTime();
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
		<LI class="tab1"><A href="#tab1">My home page</A></LI>
		<!--<LI class="tab2"><A href="#tab2">Interviews</A></LI>-->
		<!--<LI class="tab3"><A href="#tab3">Reports</A></LI>-->

	</UL>
		<div style="position: absolute;right: 20px;">
		<a  style="color: blue;text-decoration: underline;" href="#" onClick="configuredashboard()"><%=Constant.getResourceStringValue("hr.user.configure.dashboard",user.getLocale())%></a>
		</div>


	<DIV id="tabpanels">

	<DIV id="tab1" class="tab-panel ui-tabs-hide">
			<!--<DIV class="ui-layout-north ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-active">
north links
				</DIV>
			</DIV>-->
			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					<%@ include file="../common/Footer.jsp" %>
				</DIV>
			</DIV>
			<DIV id="innerTabs" class="ui-layout-center container tabs">
				<!--<DIV class="ui-widget-header ui-corner-top"> Dashboard </DIV>-->
	<%
int mytasksize = TaskBO.getCountOfPendingTasksBysearchTypeandAssignedby(user.getUserId(),null,"0");
	%>
		
				<UL>
					<LI class="tab1"><A href="#simpleTab1">Dashboard</A></LI>
					<% if(mytasksize>0){%>
					<LI class="tab2"><A href="#simpleTab2"><img src="jsp/images/icon_yellow_star_highlight.png" border="0" width="20" height="20"/>My tasks</A></LI>
					<%}else{%>
					<LI class="tab2"><A href="#simpleTab2">My tasks</A></LI>
					<%}%>
					<!--<LI class="tab3"><A href="#simpleTab3">Interviews</A></LI>-->
				</UL>
				<DIV class="ui-widget-content" style="border-top: 0;">

					<DIV id="simpleTab1" class="container" style="height: 100%;">
					<!--dashboard start-->
						<DIV class="accordion">
				
							 <% 
						String value = (String)widgetmap.get("MY_ACTIVE_REQ");
					   String isMyActiveReqVisible="false";
					   if(value != null && value.equals("A")){
						   isMyActiveReqVisible="true";
					   }
						%>

					 <% if(isMyActiveReqVisible.equals("true")){%>
							<H3><A href="#" class="button1"><font color="white">My requisitions</font></A></H3>
							<DIV>
								<a  href="#" style="color: blue;" onClick="addRequisition()"><%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%></a>
								<body class="yui-skin-sam">
								<div id="DynamicDataActiveReq"></div>
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
							<H3><A href="#" class="button1"><font color="white">Applicants in offer process</font></A></H3>
							<DIV>

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
							<H3><A href="#" class="button1"><font color="white">Offer accepted applicants (joining within <%=Constant.getValue("dashboard.noofdays.offset.config")%> days)</font></A></H3>
							<DIV>

			

							<body class="yui-skin-sam">
							<div id="dynamicdata1"></div>
							</body>
							</DIV>
							<%}%>
						</DIV>
					</DIV>
				<!--dashboard end-->
					<DIV id="simpleTab2" style="height:400px">
						<!--my task start-->
								<body class="yui-skin-sam">
							<div id="dynamicdatamypendingtask1"></div>
							</body>

						<!--my task end-->
						<%@include file="../common/tooltip.jsp" %>
					</DIV>

					<DIV id="simpleTab3"> 
					<!--interviews  start-->
		
					
					<!--interviews  stop-->
					</DIV>

				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
			</DIV>
<% 
String isFilledActiveReqVisible="false";
 String isOnboardAppVisible="false";
if(((String)widgetmap.get("FILLED_ACTIVE_REQ") != null && ((String)widgetmap.get("FILLED_ACTIVE_REQ")).equals("A")) || 
((String)widgetmap.get("ONBOARDING_APPLICANTS") != null && ((String)widgetmap.get("ONBOARDING_APPLICANTS")).equals("A")) ||
((String)widgetmap.get("IMP_LINKS") != null && ((String)widgetmap.get("IMP_LINKS")).equals("A"))){
%>

			<DIV class="ui-layout-west">

    <% 
		   value = (String)widgetmap.get("FILLED_ACTIVE_REQ");
		   
		   if(value != null && value.equals("A")){
			   isFilledActiveReqVisible="true";
		   }
		   %>

		   <% if(isFilledActiveReqVisible.equals("true")){%>
		   <DIV class="ui-layout-north">
	<DIV class="ui-widget-header ui-corner-top button1"><%=Constant.getResourceStringValue("filled.active.requisitions",user.getLocale())%></DIV>
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
		  
		   if(value != null && value.equals("A")){
			   isOnboardAppVisible="true";
		   }
		   %>

		   <% if(isOnboardAppVisible.equals("true")){%>
		   <DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top button1">On board applicants ( <%=Constant.getValue("dashboard.noofdays.offset.config")%> days)</DIV>
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
	<DIV class="ui-widget-header ui-corner-top button1">Important links</DIV>
	<DIV class="ui-widget-content">
		<%@ include file="implinks.jsp" %>
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom"></DIV>
	</DIV>
	<%}%>

			</DIV>

	 <%}%>

	 <% 
String isReqSlipingTargetVisible="false";
  String isOfferDeclinedVisible="false";
   String isRecentIntVisible="false";
if(((String)widgetmap.get("REQ_SLIPING_TARGET") != null && ((String)widgetmap.get("REQ_SLIPING_TARGET")).equals("A")) || 
((String)widgetmap.get("OFFER_DECLINED") != null && ((String)widgetmap.get("OFFER_DECLINED")).equals("A")) ||
((String)widgetmap.get("RECENT_INTERVIEWS") != null && ((String)widgetmap.get("RECENT_INTERVIEWS")).equals("A"))){
%>
	<DIV class="ui-layout-east">
   			<% 
		   value = (String)widgetmap.get("REQ_SLIPING_TARGET");
		   
		   if(value != null && value.equals("A")){
			   isReqSlipingTargetVisible="true";
		   }
		   %>
		   <% if(isReqSlipingTargetVisible.equals("true")){%>
			<DIV class="ui-layout-north">

				<DIV class="ui-widget-header ui-corner-top button1">Requisitions Slipping target</DIV>
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
		  
		   if(value != null && value.equals("A")){
			   isOfferDeclinedVisible="true";
		   }
		   %>

		   <% if(isOfferDeclinedVisible.equals("true")){%> 

			<DIV class="ui-layout-center">
				<DIV class="ui-widget-header ui-corner-top button1">Open Requisitions State</DIV>
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
		  
		   if(value != null && value.equals("A")){
			   isRecentIntVisible="true";
		   }
		   %>   
		   <% if(isRecentIntVisible.equals("true")){%> 
			<DIV class="ui-layout-south">
				<DIV class="ui-widget-header ui-corner-top button1">Recent interviews
				<% if(user.getMenu() != null && user.getMenu().isCalendarAccess()){%>
				&nbsp;&nbsp;<a  style="color: blue;text-decoration: underline;" href="calendar.do?method=calendarView">calendar</a>
				<%}%>
				</DIV>
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

			<%}//end of right pane%>
		</DIV><!-- /#tab1 -->


	<DIV id="tab2" class="tab-panel ui-tabs-hide">

			<DIV class="ui-layout-south ui-widget">
				<DIV class="toolbar ui-widget-content ui-state-default">
					<%@ include file="../common/Footer.jsp" %>
				</DIV>
			</DIV>
			<DIV class="ui-layout-center" >
				<DIV class="ui-widget-content">
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

		

		<H3><A href="#">Section 4</A></H3>
		<DIV>
			
		</DIV>

</DIV>
				</DIV>
				<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">Center-Center Footer</DIV>
			</DIV>
			<DIV class="ui-layout-west">
<DIV class="accordion">

		

		

</DIV>

			</DIV>
			<DIV class="ui-layout-east">
<DIV class="ui-layout-center">
	<DIV class="ui-widget-header ui-corner-top">East-Center</DIV>
	<DIV class="ui-widget-content">
		
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-Center Footer</DIV>
</DIV>
<DIV class="ui-layout-south">
	<DIV class="ui-widget-header ui-corner-top">East-South</DIV>
	<DIV class="ui-widget-content">
		
	</DIV>
	<DIV class="ui-widget-footer ui-widget-header ui-corner-bottom">East-South Footer</DIV>
</DIV>
			</DIV>
		</DIV><!-- /#tab3 -->

		
	</DIV><!-- /#tabpanels -->

</DIV><!-- /#outer-center -->

</BODY>
</HTML>

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

//calList();


</script>

<%
 String strcolumncontentassignedreq = ScreenSettingUtils.getApplicationScreenSettings(user,Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS);
 String strkeycontentassignedreq = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS);

%>

<script type="text/javascript">

YAHOO.example.DynamicDataActiveReq = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("jobreqId") + "');return false;"+ ">" + sData + "</a>";
		
		 elCell.innerHTML = editreqd;

        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requisition" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReqTemplte('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }


	var summaryUrl = function(elCell, oRecord, oColumn, sData) {
		if(oRecord.getData("statusUiValue")=="Active > Open"){
		if(oRecord.getData("allApplicantViewed")=='N'){
          elCell.innerHTML = "<a href='jobreq.do?method=requistionapplicantlist&backurl=dashboard.do?method=dashboardlist&state=0&requistionId=" + oRecord.getData("jobreqId")+"&secureid=" + oRecord.getData("uuid") +"'"+ ">" + "<img src='jsp/images/icon_yellow_star_highlight.png' border='0' width='18' height='18' title='new applicant added' alt='new applicant added'/>"+"<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>" + "</a>";
		 }else{
          elCell.innerHTML = "<a href='jobreq.do?method=requistionapplicantlist&backurl=dashboard.do?method=dashboardlist&state=0&requistionId=" + oRecord.getData("jobreqId")+"&secureid=" + oRecord.getData("uuid") +"'"+ ">" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>" + "</a>";
		 }
		}
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
var formatHiringMgr = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("hiringMgrValue");
				
				elCell.innerHTML = "<a class='personPopupTrigger' rel='"+oRecord.getData("hiringMgrIdValue")+","+"N"+"' href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("hiringMgrIdValue")+ "'"+","+"'"+ "N"+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			
        };
    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"templateId", hidden:true},
			{key:"currentOwnerId", hidden:true},
		{key:"allApplicantViewed", hidden:true},
			{key:"isGroup", hidden:true},
		{key:"hiringMgrIdValue", hidden:true},
		{key:"configureColumn", label:"<a href='#' onClick=configuareColumns('DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS');return false; title='<%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user.getLocale())%>'><img src='jsp/images/setting.png' height='15' width='15' border='0'/></a>"},
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
    var myDataSourceActiveReq = new YAHOO.util.DataSource("jsp/home/myjobrequisitionlistpage.jsp?currentuserid=<%=user.getUserId()%>&currentusername=<%=user.getUserName()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSourceActiveReq.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSourceActiveReq.maxCacheEntries    = 1;
    myDataSourceActiveReq.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"currentOwnerId"},
			{key:"isGroup"},
			{key:"hiringMgrIdValue"},
			{key:"allApplicantViewed"},
			{key:"configureColumn"},
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
    var myDataTableActiveReq = new YAHOO.widget.DataTable("DynamicDataActiveReq", myColumnDefs, myDataSourceActiveReq, myConfigs);
    // Update totalRecords on the fly with value from server
   myDataTableActiveReq.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSourceActiveReq,
        dt: myDataTableActiveReq
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
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requisition" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
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
		{key:"configureColumn", label:"<a href='#' onClick=configuareColumns('DASHBOARD_RECRUITER_IN_OFFER_PROCESS');return false; title='<%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user.getLocale())%>'><img src='jsp/images/setting.png' height='15' width='15' border='0'/></a>"},
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
			{key:"configureColumn"},
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
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requisition" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
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
		{key:"configureColumn", label:"<a href='#' onClick=configuareColumns('DASHBOARD_RECRUITER_IN_JOINED_PROCESS');return false; title='<%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user.getLocale())%>'><img src='jsp/images/setting.png' height='15' width='15' border='0'/></a>"},
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
			{key:"configureColumn"},
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


<script type="text/javascript">
YAHOO.example.dynamicdatamypendingtask1 = function() {


var editUrl = function(elCell, oRecord, oColumn, sData) {

	 var editreqd = "<a href='#' onMouseover=\"ddrivetip('<b>"+sData+"</b><br><%=Constant.getResourceStringValue("Requisition.right.click.to.view.otheroptions",user.getLocale())%>','#DFDFFF')\"; onMouseout=hideddrivetip(); onClick=taskdetails(" +"'"+ oRecord.getData("idvalue")+ "'"+","+"'"+ oRecord.getData("uuid")+ "'"+","+ "'"+escape(oRecord.getData("tasktype"))+"'"+ ")"+ ">" + oRecord.getData("taskname") + "</a>";
	

		 elCell.innerHTML = editreqd;

	
         
        };
       var statusCol = function(elCell, oRecord, oColumn, sData) {
			if(oRecord.getData("status") == "A"){
				elCell.innerHTML="<%=Constant.getResourceStringValue("task.active",user.getLocale())%>";
			}
           };

    // Column definitions
    var myColumnDefs = [
			{key:"taskId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"idvalue", hidden:true},
           {key:"taskname", label:"<%=Constant.getResourceStringValue("task.taskname",user.getLocale())%>", sortable:true, resizeable:true,formatter:editUrl},
             {key:"tasktype", label:"<%=Constant.getResourceStringValue("task.tasktype",user.getLocale())%>",sortable:true, resizeable:true},
			{key:"assignedbyUserName", label:"<%=Constant.getResourceStringValue("task.assignedby",user.getLocale())%>",sortable:true, resizeable:true},
			   {key:"createdDate", label:"<%=Constant.getResourceStringValue("task.assignedon",user.getLocale())%>",sortable:true, resizeable:true},
		{key:"assignedtoUserName", label:"<%=Constant.getResourceStringValue("task.assignedto",user.getLocale())%>",sortable:true, resizeable:true},
		{key:"status", label:"<%=Constant.getResourceStringValue("task.status",user.getLocale())%>",sortable:true, resizeable:true,formatter:statusCol},
		{key:"eventdate", label:"<%=Constant.getResourceStringValue("task.due.date",user.getLocale())%>",sortable:true, resizeable:true},
		{key:"assignedtoUserName1", label:"<%=Constant.getResourceStringValue("task.taskowner",user.getLocale())%>", resizeable:true}
			
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/task/mypendingtaskslistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"taskId"},
            {key:"idvalue"},
            {key:"tasktype"},
            {key:"assignedbyUserName"},
			{key:"assignedtoUserName"},
			{key:"assignedtoUserName1"},
			{key:"createdDate"},
		{key:"updatedBy"},
		{key:"updatedDate"},
		{key:"status"},
			{key:"taskname"},
			{key:"eventdate"},
			{key:"uuid"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=taskId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"taskId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdatamypendingtask1", myColumnDefs, myDataSource, myConfigs);
    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    };




	var clickHandler = function() {
					var target = myContextMenu.contextEventTarget,
						record = myDataTable.getRecord(target),
						column = myDataTable.getColumn(target);
					taskaction(record.getData('idvalue'),record.getData('uuid'),record.getData('tasktype'),this.cfg.getProperty('classname'));
						//alert(target);
					/*alert(
						'Clicked on menu "' + this.cfg.getProperty('classname') +
				'" in column "' + column.key +
				'" for SKU "' + record.getData('idvalue') + '"'

					);*/
				};
       
	 var myContextMenu = new YAHOO.widget.ContextMenu("mycontextmenu",
                {trigger:myDataTable.getTbodyEl()});
	 myContextMenu.render("dynamicdatamypendingtask1");

       myContextMenu.beforeShowEvent.subscribe(function(type, aArgs, dt) {
					var target = this.contextEventTarget,
						record = dt.getRecord(target),
						column = dt.getColumn(target);
					while (this.removeItem(0)) {}
					
						if(record.getData("tasktype") == '<%=Common.OFFER_APPROVAL_TASK%>'){			
					    this.addItem({text:'Approve Offer',classname:'approve',onclick:{fn:clickHandler}});
						this.addItem({text:'Reject Offer',classname:'reject',onclick:{fn:clickHandler}});
						//this.clickEvent.subscribe(onContextMenuClick, myDataTable);
						}else if(record.getData("tasktype") == '<%=Common.OFFER_RELEASE_TASK%>'){			
					    this.addItem({text:'Release Offer',classname:'releaseoffer',onclick:{fn:clickHandler}});
						
						}else if(record.getData("tasktype") == '<%=Common.APPLICANT_REVIEW_TASK%>'){			
					    this.addItem({text:'Add feedback',classname:'addfeedback',onclick:{fn:clickHandler}});
						this.addItem({text:'Re-assign',classname:'reassign',onclick:{fn:clickHandler}});
						this.addItem({text:'Decline',classname:'decline',onclick:{fn:clickHandler}});

						}else if(record.getData("tasktype") == '<%=Common.APPLICANT_INTERVIEW_TASK%>'){			
					    this.addItem({text:'Add feedback',classname:'addfeedback',onclick:{fn:clickHandler}});
						this.addItem({text:'Re-assign',classname:'reassign',onclick:{fn:clickHandler}});
						this.addItem({text:'Decline',classname:'decline',onclick:{fn:clickHandler}});

						}else if(record.getData("tasktype") == '<%=Common.APPLICANT_IN_QUEUE%>'){
							
					    this.addItem({text:'Schedule interview',classname:'scheduleinterview',onclick:{fn:clickHandler}});
						this.addItem({text:'Initiate offer',classname:'initiateoffer',onclick:{fn:clickHandler}});
						
						}else if(record.getData("tasktype") == '<%=Common.REQUISTION_APPROVAL_TASK%>'){			
					    this.addItem({text:'Approve',classname:'approve',onclick:{fn:clickHandler}});
						this.addItem({text:'Reject',classname:'reject',onclick:{fn:clickHandler}});
						//this.addItem({text:'Re-assign',classname:'reassign',onclick:{fn:clickHandler}});

						}else if(record.getData("tasktype") == '<%=Common.REQUISTION_PUBLISH_TASK%>'){			
					    this.addItem({text:'Publish',classname:'publish',onclick:{fn:clickHandler}});
						
						}else if(record.getData("tasktype") == '<%=Common.ONBOARDING_TASK%>'){			
					    this.addItem({text:'On-boarding',classname:'onboarding',onclick:{fn:clickHandler}});
						
						}else{
						this.addItem("Under construction");
						//this.clickEvent.subscribe(onContextMenuClick, myDataTable);
						}
						//this.addItems(menu);
						//this.setHeader(column.label || column.key);
						//this.setFooter(record.getData('jobreqName'));
						this.render();
					 
				},myDataTable);


					




    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();
</script>

<script language="javascript">





function taskaction(idvalue,uuid,tasktype,action){
	//alert(idvalue+uuid+tasktype+action);

	if(tasktype == '<%=Common.OFFER_APPROVAL_TASK%>'){
		approveOffer(idvalue,uuid,tasktype,action);
	}
	if(tasktype == '<%=Common.OFFER_RELEASE_TASK%>'){
		releaseoffer(idvalue,uuid,tasktype,action);
	}

	if(tasktype == '<%=Common.APPLICANT_REVIEW_TASK%>'){
		if(action == 'addfeedback'){
			addFeedback(idvalue,uuid,tasktype,action);
			}
		if(action == 'reassign'){
			reassignReview(idvalue,uuid,tasktype,action);
			}

		if(action == 'decline'){
			declineReview(idvalue,uuid,tasktype,action);
			}
		
	}
	if(tasktype == '<%=Common.APPLICANT_INTERVIEW_TASK%>'){
		if(action == 'addfeedback'){
			addFeedback(idvalue,uuid,tasktype,action);
			}
		if(action == 'reassign'){
			reassignReview(idvalue,uuid,tasktype,action);
			}

		if(action == 'decline'){
			declineReview(idvalue,uuid,tasktype,action);
			}
		
	}

	if(tasktype == '<%=Common.APPLICANT_IN_QUEUE%>'){
		if(action == 'scheduleinterview'){
			scheduleinterviewround(idvalue,uuid,tasktype,action);
			}
		if(action == 'initiateoffer'){
			initiateoffer(idvalue,uuid,tasktype,action);
			}
	}

	if(tasktype == '<%=Common.REQUISTION_APPROVAL_TASK%>'){
		if(action == 'approve'){
			approveRequistion(idvalue,uuid,tasktype,action);
			}
		if(action == 'reject'){
			rejectRequistion(idvalue,uuid,tasktype,action);
		}
	}

   if(tasktype == '<%=Common.REQUISTION_PUBLISH_TASK%>'){
		if(action == 'publish'){
			publishRequistion(idvalue,uuid,tasktype,action);
			}
		
	}

   if(tasktype == '<%=Common.ONBOARDING_TASK%>'){
		if(action == 'onboarding'){
			onboardingApplicant(idvalue,uuid,tasktype,action);
			}
		
	}

}

function approveOffer(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/applicant.do?method=offerapproverejectcommenttaskpage&applicantId="+idvalue+"&secureid="+uuid+"&action="+action;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user.getLocale())%>',url,400,600, messageretrefresh);
}
function releaseoffer(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/applicant.do?method=releaseofferscr&applicantId="+idvalue;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user.getLocale())%>',url, messageretrefresh);
}
function addFeedback(idvalue,uuid,tasktype,action){
	 var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&frompage=task&status=1&applicantId="+idvalue+"&uuid="+uuid;
			
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_feedback",user.getLocale())%>',url, messageretrefresh);
	
}
function reassignReview(idvalue,uuid,tasktype,action){
var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=interviwreassign&frompage=task&applicantId="+idvalue+"&uuid="+uuid;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Re_assign_review",user.getLocale())%>',url,400,600, messageretrefresh);
}

function declineReview(idvalue,uuid,tasktype,action){
var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=declineinterview&frompage=task&applicantId="+idvalue+"&uuid="+uuid;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Decline_review",user.getLocale())%>',url,400,600, messageretrefresh);
}
function initiateoffer(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId="+idvalue;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user.getLocale())%>',url, messageretrefresh);
}
function scheduleinterviewround(idvalue,uuid,tasktype,action){
var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&frompage=task&applicantId="+idvalue+"&uuid="+uuid;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Schedule_Interview",user.getLocale())%>',url,500,650, messageretrefresh);
}
function approveRequistion(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=approverejectReqSrc&jobreqId="+idvalue+"&uuid="+uuid+"&type=approve";
	GB_showCenter('<%=Constant.getResourceStringValue("Requisition.Approve_requisition",user.getLocale())%>',url,400,600, messageretrefresh);
}
function rejectRequistion(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=approverejectReqSrc&jobreqId="+idvalue+"&uuid="+uuid+"&type=reject";
	GB_showCenter('<%=Constant.getResourceStringValue("Requisition.Reject_requisition",user.getLocale())%>',url,400,600, messageretrefresh);
}
function publishRequistion(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=publishJobReqscr&jobreqId="+idvalue+"&uuid="+uuid;
  
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.Publish.requistion",user.getLocale())%>","dialogHeight: 534px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");
   
   window.document.location.href="dashboard.do?method=dashboardlist";
}
function onboardingApplicant(idvalue,uuid,tasktype,action){
	var url = "<%=request.getContextPath()%>/onboarding.do?method=loadTask&taskid="+idvalue+"&secureid="+uuid;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.onboarding.task",user.getLocale())%>', url, messageretrefresh);
}



function taskdetails(idvalue,uuid,tasktype){
   // alert(idvalue);
 
	var url = "";
	//parent.setPopTitle1("Create applicant");
	//parent.showPopWin(url, 700, 600, null,true);

var ty = unescape(tasktype);
if(ty == '<%=Common.MANUAL_ADD_TASK%>'){
edittask(idvalue);
}
if(ty == '<%=Common.APPLICANT_IN_QUEUE%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.OFFER_APPROVAL_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.APPLICANT_REVIEW_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.APPLICANT_INTERVIEW_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.OFFER_RELEASE_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.REFERENCE_CHECK_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=mypendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.REQUISTION_APPROVAL_TASK%>'){
var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+idvalue;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.jobrequisition",user.getLocale())%>', url, messageretrefresh);
}
if(ty == '<%=Common.REQUISTION_PUBLISH_TASK%>'){
var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+idvalue;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.jobrequisition",user.getLocale())%>', url, messageretrefresh);
}

if(ty == '<%=Common.ONBOARDING_TASK%>'){
var url = "<%=request.getContextPath()%>/onboarding.do?method=loadTask&taskid="+idvalue+"&secureid="+uuid;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.onboarding.task",user.getLocale())%>', url, messageretrefresh);
}
}

function edittask(id){
	
	var url = "<%=request.getContextPath()%>/task.do?method=edittask&tasdkid="+id;
	
	GB_showCenter("<%=Constant.getResourceStringValue("hr.update.task",user.getLocale())%>",url,400,500, messageretrefresh);
}

</script>





