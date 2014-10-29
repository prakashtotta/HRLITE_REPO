<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />

	<title>Layout with Accordion</title>

	<link rel="stylesheet" type="text/css" href="../jquery/layout-default-latest.css" />
	<link rel="stylesheet" type="text/css" href="../jquery/themes/base/jquery.ui.all.css" />
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
	<script type="text/javascript" src="../jquery/js/jquery-latest.js"></script>
	<script type="text/javascript" src="../jquery/js/jquery-ui-latest.js"></script>
	<script type="text/javascript" src="../jquery/js/jquery.layout-latest.js"></script>


    <script type="text/javascript" src="../jquery/js/themeswitchertool.js"></script> 
	<script type="text/javascript" src="../jquery/js/debug.js"></script>

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


		// THEME SWITCHER
		//addThemeSwitcher('.ui-layout-north',{ top: '12px', right: '5px' });
		// if a new theme is applied, it could change the height of some content,
		// so call resizeAll to 'correct' any header/footer heights affected
		// NOTE: this is only necessary because we are changing CSS *AFTER LOADING* using themeSwitcher
		//setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */

	});
	</script>

</head>
<body>

<div class="ui-layout-north ui-widget-content" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
     <link href="../vimco/css/dropdown/themes/vimeo.com/helper.css" media="screen" rel="stylesheet" type="text/css" />
<link href="../vimco/css/dropdown/dropdown.css" media="screen" rel="stylesheet" type="text/css" />
<link href="../vimco/css/dropdown/themes/vimeo.com/default.advanced.css" media="screen" rel="stylesheet" type="text/css" />

<%@ page import="java.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.resources.*"%>
 

<%
String superUserKey="0";
User usernenu = (User)session.getAttribute(Common.USER_DATA);

Menu  menu = null;
if(usernenu != null){
 menu = usernenu.getMenu();
 superUserKey = EncryptDecrypt.encrypt(String.valueOf(usernenu.getSuper_user_key()));
}
   
%>
<script language="javascript">	
function openTalentPool(url){
 
 var url = "jsp/jtree/talentPool.jsp";
  
    var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.open(url, "TalentPool","location=0,status=0,scrollbars=1,menubar=0,resizable=1,left=10, top=10,width=1200,height=700");

}
</script>
<%if(usernenu != null){%>
   <ul id="nav" class="dropdown dropdown-horizontal">
	<img style="float:left;" alt="" src="../vimco/menu_left.png"/>
	<li class="dir">About Us
		<ul>
			<li class="first"><a href="./">History</a></li>
			<li><a href="./">Our Vision</a></li>
			<li><a href="./" class="dir">The Team</a>
				<ul>
					<li class="first"><a href="./">Brigita</a></li>
					<li><a href="./">John</a></li>
					<li><a href="./">Michael</a></li>
					<li><a href="./">Peter</a></li>
					<li class="last"><a href="./">Sarah</a></li>
				</ul>
			</li>
			<li><a href="./">Clients</a></li>
			<li><a href="./">Testimonials</a></li>
			<li><a href="./">Press</a></li>
			<li class="last"><a href="./">FAQs</a></li>
		</ul>
	</li>
	<li class="dir">Services
		<ul>
			<li class="first"><a href="./">Product Development</a></li>
			<li><a href="./">Delivery</a></li>
			<li><a href="./">Shop Online</a></li>
			<li><a href="./">Support</a></li>
			<li class="last"><a href="./">Training &amp; Consulting</a></li>
		</ul>
	</li>
	<li class="dir">Products
		<ul>
			<li class="first"><a href="./" class="dir">New</a>
				<ul>
					<li class="first"><a href="./">Corporate Use</a></li>
					<li class="last"><a href="./">Private Use</a></li>
				</ul>
			</li>
			<li><a href="./" class="dir">Used</a>
				<ul>
					<li class="first"><a href="./">Corporate Use</a></li>
					<li class="last"><a href="./">Private Use</a></li>
				</ul>
			</li>
			<li><a href="./">Featured</a></li>
			<li><a href="./">Top Rated</a></li>
			<li class="last"><a href="./">Prices</a></li>
		</ul>
	</li>
	<li><a href="./">Gallery</a></li>
	<li><a href="./">Events</a></li>
	<li><a href="./">Careers</a></li>
	<li><a href="./" class="dir">Contact Us</a>
		<ul>
			<li class="first"><a href="./">Enquiry Form</a></li>
			<li><a href="./" class="dir">Map &amp; Driving Directions</a>
				<ul>
					<li class="first"><a href="./">Map</a></li>
					<li class="last"><a href="./">Driving Directions</a></li>
				</ul>
			</li>
			<li class="last"><a href="./">Your Feedback</a></li>
		</ul>
	</li>
	<li class="dir last">
		<form method="post" action="">
			<fieldset>
				<label for="search">Search:</label>
				<input type="text" id="search" class="text" value="Search!" onfocus="if (this.value == 'Search!') this.value = '';" onblur="if (this.value == '') this.value = 'Search!';" maxlength="255" />
				<input type="image" src="../vimco/images/vimeo.com/btn_search.png" class="button" />
			</fieldset>
		</form>
		<ul>
			<li class="first"><a href="./">Search result #1</a></li>
			<li><a href="./">Search result #2</a></li>
			<li><a href="./">Search result #3</a></li>
			<li><a href="./">Search result #4</a></li>
			<li class="last"><a href="./">Search result #5</a></li>
		</ul>
	</li>

</ul>

<ul class="rightside">
<table>
<tr>
<td>
<div style="border:2px solid black;height:20px;">
<a href="user.do?method=mydetails"><%=usernenu.getFirstName()+" "+usernenu.getLastName()%></a>
</div>


</td>
<td>
<a href="dashboard.do?method=dashboardlist">
<img src="jsp/images/home.png" width="20" height="20" alt="Home" style="border:2px solid black;"  />
</a>
</td>
<td>
<a href="login.do?method=logout">
<img src="jsp/images/Log-Out-icon.png" width="20" height="20" alt="Logout" style="border:2px solid black;"  />
</a>
</td>
</tr>
</table>
</ul>

<%}else{//end of menu %>
  <ul id="nav" class="dropdown dropdown-horizontal">
	<img style="float:left;" alt="" src="jsp/vimco/menu_left.png"/>
</ul>
<%}%>



	
</div>


<div class="ui-layout-south ui-widget-content ui-state-error" style="display: none;"> South Pane </div>

<div class="ui-layout-center" style="display: none;"> 
	<h3 class="ui-widget-header">Center Pane</h3>
	<p class="ui-layout-content ui-widget-content">
		<b>NOTE</b>: As of UI v1.7.1, the Accordion widget has a 'resize' method.
		If you were previously using the patched version of ui.accordion, 
		you should upgrade to the latest versions of jQuery and jQuery UI.
	</p>
</div>


</div>

</body>
</html> 