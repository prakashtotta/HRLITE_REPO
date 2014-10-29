<%@ page import="com.common.*"%>
<%@ page import="com.resources.*"%>
<!doctype html>

<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="description" content="A brief statement describing your company or product." />
	<meta name="keywords" content="keywords, related to, your company, or your product" />

	<link href="css/reset.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/core.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/colors_blue_and_green.css" rel="stylesheet" type="text/css" title="Blue and green" media="screen" />
	<link href="css/colors_black_and_orange.css" rel="alternate stylesheet" type="text/css" title="Black and orange" media="screen" />
	<link href="css/colors_green_and_brown.css" rel="alternate stylesheet" type="text/css" title="Green and brown" media="screen" />
	<link href="css/colors_yellow_and_black.css" rel="alternate stylesheet" type="text/css" title="Yellow and black" media="screen" />
	<link href="css/colors_teal_and_brown.css" rel="alternate stylesheet" type="text/css" title="Teal and brown" media="screen" />
	<link href="css/colors_cherry_and_yellow.css" rel="alternate stylesheet" type="text/css" title="Cherry and yellow" media="screen" />
	<link href="css/slider.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/lightbox.css" rel="stylesheet" type="text/css" media="screen" />
	<!--[if lte IE 8]>
		<link href="css/ie.css" rel="stylesheet" type="text/css" media="screen" />
	<![endif]-->
	<link href="http://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet" type="text/css" media="screen" />

	<script type="text/javascript" src="scripts/styleswitcher.js"></script>
	<script type="text/javascript" src="scripts/lightbox.js"></script>
	<script src="scripts/jquery_minimized_core.js"></script>
	<script src="scripts/jquery_slider.js"></script>
	<script type="text/javascript" src="scripts/date.format.js"></script>
		 <script src="http://connect.facebook.net/en_US/all.js"></script>


	<title>TalentNetwork</title>

</head>

<script language="javascript">


/*window.fbAsyncInit = function() {
    FB.init({ appId: '<%=Constant.getValue("app.key")%>', status: true, cookie: true,oauth : true,
        xfbml: true
    });
}*/
  

function fblogin() { 



 window.top.location = 'https://www.facebook.com/dialog/oauth?client_id=<%=Constant.getValue("app.key")%>&redirect_uri=<%=Constant.getValue("app.url")%>index.jsp&scope=email,user_checkins,user_location,user_work_history,user_education_history,friends_location,friends_work_history,friends_education_history,publish_actions,user_hometown,read_stream,publish_stream,offline_access&fbconnect=1&_fb_noscript=1';
          } 




</script>

<body>

<div id="fb-root"></div>

	<div id="header_wrap">

		<div id="header">

			<h1 id="logo"><a href="index.html" title="Go to Spark homepage">Spark</a></h1>

			<ol id="nav">
				<a href="#" onClick="fblogin();return false;">Click here to enter <img src="images/arrow.png" border="0" width="50" height="20"/></a>  
				
				</li>
			</ol>
			
			<div class="clear"></div>

		</div> <!-- End of header -->

	</div> <!-- End of header wrap -->
		
	<div id="pitch_wrap">

		<div id="pitch">
		
	       

			
			<div class="ie7_sliderFix"></div> <!-- Empty div added in order to fix bug in Internet Explorer 7 -->
			<div class="clear"></div>

		</div> <!-- End of pitch -->

	</div> <!-- End of pitch wrap -->

	<div id="addendum_wrap">

		<div id="addendum">

			<span id="friends"></span>

			<div id="OutPut" title="This is Title"> 
			</div> 

			<div id="OutPut1" title="This is Title"> 
			</div> 

			

			<div class="clear"></div>

		</div> <!-- End of addendum -->

	</div> <!-- End of addendum wrap -->

	

			
			
			<div class="clear"></div>

		</div> <!-- End of content -->

	</div> <!-- End of content wrap -->

	<div id="footer_wrap">

		<div id="footer">

			<div class="box_wide separator_r">

				<ol id="nav_footer">
					<li><a href="index.html">Home</a></li>
					<li><a href="tour.html">Tour</a></li>
					<li><a href="pricing.html">Pricing &amp; Signup</a></li>
					<li><a href="blog.html">Blog</a></li>
					<li><a href="contact.php">Contact</a></li>
					<li><a href="login.html">Login</a></li>
				</ol>

				<ul id="nav_policies">
					<li><a href="simple_text.html">Security information</a></li>
					<li><a href="simple_text.html">Privacy policy</a></li>
					<li><a href="simple_text.html">Legal stuff</a></li>					
				</ul>
				
				<p id="copyright">Copyright &copy; 2009-2010 Your Site, LLC - All Rights Reserved</p>

			</div>

			<div id="social_info" class="box_small separator_r">

				<ul>
					<li id="twitter"><a href="#">follow us</a> on twitter</li>
					<li id="facebook"><a href="#">become a fan</a> on facebook</li>
				</ul>

			</div>

			<div id="contact_info" class="box_small">

				<h5>Contact</h5>
				<ul>
					<li>555 443 3221</li>
					<li><a href="#">contact@yourcompany.com</a></li>
				</ul>

			</div>
			
			<div class="clear"></div>

		</div> <!-- End of footer -->

	</div> <!-- End of footer wrap -->

</body>

</html>