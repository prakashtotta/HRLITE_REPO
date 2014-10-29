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

	<script type="text/javascript" src="jsp/scripts/styleswitcher.js"></script>
	<script type="text/javascript" src="jsp/scripts/lightbox.js"></script>
	<script src="jsp/scripts/jquery_minimized_core.js"></script>
	<script src="jsp/scripts/jquery_slider.js"></script>
	<script type="text/javascript" src="jsp/scripts/date.format.js"></script>
		 <script src="http://connect.facebook.net/en_US/all.js"> </script>


	<title>TalentNetwork</title>

</head>

<script language="javascript">


window.fbAsyncInit = function() {
    FB.init({ appId: '395990430460186', status: true, cookie: true,
        xfbml: true
    });

    FB.Event.subscribe('auth.login', function(resp) {
		 if (resp.authResponse) {
gotoApp();
   } else {
    alert('User cancelled login or did not fully authorize.');
   }
       
    });

};


function fblogin() { 
 window.top.location = 'https://www.facebook.com/dialog/oauth?client_id=395990430460186&redirect_uri=<%=Constant.getValue("app.url")%>index.jsp&scope=email,user_checkins,user_location,user_work_history,user_education_history,friends_location,friends_work_history,friends_education_history,publish_actions,user_hometown&fbconnect=1&_fb_noscript=1';
          } 

function gotoApp() {


        FB.api("/me",
                function (response) {
			//"work"][0]["employer"]["name"]); 
			//alert('email is ' + response.email);
			//alert('id is ' + response.id);
                    //alert('Name is ' + response.work[0].employer.name);
					


            if(typeof response.email == 'undefined'){
				
			 }else{
			//var urlvalue="networkhome.do?method=home&email="+response.email+"&fid="+response.id+"&name="+response.name+"&tdate="+tdate+"&time="+time;
	  
			//	window.location = urlvalue;


            var output = ""; 
for (var key in response) { 
    output += key + "=" + response[key] + "<br/>"; 
    
	
} 
 
for(var i in response.work){
	output += i + "=" + response.work[i].employer.name + "<br/>"; 
	output += i + "=" + response.work[i].employer.id + "<br/>"; 
	
}

for(var j in response.education){
	output += j + "=" + response.education[j].school.name + "<br/>"; 
	output += j + "=" + response.education[j].school.id + "<br/>"; 
	output += j + "=" + response.education[j].type + "<br/>"; 
}
for(var k in response.location){
	output += k + "=" + response.location.name + "<br/>"; 
 
}



var obj=document.getElementById("OutPut"); 
obj.innerHTML = output;

				 }
          });
        
    }



function getFriends() { 

	FB.init({
        appId: '395990430460186',
        status: true, 
        cookie: true, 
        xfbml: true 
    });  

var str="";
var incv=0;
    FB.api('/me/friends', function(response) { 
		alert("Hi"+response.data);
        if(response.data) { 
			
            $.each(response.data,function(index,friend) { 
                //alert(friend.name + ' has id:' + friend.id +"pic"+ response.data[0].pic_square ); 
				var content="";
				if(incv ==0){
					content = "<tr>";
				}
				
				 content = content +'<td>'+friend.name+'<br>' 
                    + '<img src="//graph.facebook.com/'+friend.id+'/picture"/><input type="checkbox" checked="true" name="'+friend.name+'"></td>'; 
			
				
				incv = incv +1;
				if(incv==4){
					content = content+ "</tr>";
					incv=0;
				}
				
				$('#friends').append(content); 
            }); 
        } else { 
            alert("Error!"); 
        } 
    }); 

} 

</script>

<body onLoad="gotoApp()">

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

			<div class="clear"></div>

		</div> <!-- End of addendum -->

	</div> <!-- End of addendum wrap -->

	<div id="content_wrap">

		<div id="content">

			<div id="main_content_wide" class="left">

				<div class="box_medium feature separator_r">

					<img src="images/icon_feature_01.png" width="60" height="55" alt="" />
					<h3>Another feature</h3>
					<p>A short sentence describing just one of the many fantastic features that your product has.</p>

				</div>

				<div class="box_medium feature">

					<img src="images/icon_feature_02.png" width="60" height="55" alt="" />
					<h3>Some other feature</h3>
					<p>A short sentence describing just one of the many fantastic features that your product has.</p>

				</div>

				<div class="box_medium feature separator_r">

					<img src="images/icon_feature_03.png" width="60" height="55" alt="" />
					<h3>Yet another one</h3>
					<p>A short sentence describing just one of the many fantastic features that your product has.</p>

				</div>

				<div class="box_medium feature">

					<img src="images/icon_feature_04.png" width="60" height="55" alt="" />
					<h3>And the last one</h3>
					<p>A short sentence describing just one of the many fantastic features that your product has.</p>

				</div>

				<div id="blurb">

					<p>Are you convinced now? You can get all this and much more starting from just $35/month. <a href="signup.html">Sign up right now</a></p>

				</div>

			</div> <!-- End of main content -->

			<div id="aside_medium" class="right">

				<div id="latest_posts" class="box_medium">

					<h3>Latest blog posts</h3>
					<ol>
						<li>
							<a href="blog_post.html">An entry from the company&#39;s blog</a>
							<p>Short excerpt from the start of the blog post. It contains only several lines of text.</p>
						</li>
						<li>
							<a href="blog_post.html">Title for some other entry from the blog</a>
							<p>Another short excerpt from the start of the post. It contains only several lines of text.</p>							
						</li>
					</ol>

				</div>

				<div id="newsletter" class="box_medium">

					<h3>Newsletter</h3>
					<p>Stay up to date with the latest news about us.</p>
					<form action="" method="post">
						<fieldset>
							<input type="text" name="" />
							<button type="submit">subscribe</button>
						</fieldset>	
					</form>

				</div>

			</div> <!-- End of aside -->
			
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