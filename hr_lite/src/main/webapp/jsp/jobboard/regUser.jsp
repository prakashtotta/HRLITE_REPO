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

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="../common/cache.jsp" %>


<!doctype html>

<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="description" content="Hires360 hiring made easy. Talent aquisition connects agencies , employees , referrals and out siders" />
	<meta name="keywords" content="Hire360, talent , resume, free applicant tracking, recruitment software, applicant manage, requisitions , on board, offer, applicant tracking for startup, EEO report" />

	<link href="jsp/jobboard/css/reset.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/core_web.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/colors_blue_and_green.css" rel="stylesheet" type="text/css" title="Blue and green" media="screen" />
	<link href="jsp/jobboard/css/colors_black_and_orange.css" rel="alternate stylesheet" type="text/css" title="Black and orange" media="screen" />
	<link href="jsp/jobboard/css/colors_green_and_brown.css" rel="alternate stylesheet" type="text/css" title="Green and brown" media="screen" />
	<link href="jsp/jobboard/css/colors_yellow_and_black.css" rel="alternate stylesheet" type="text/css" title="Yellow and black" media="screen" />
	<link href="jsp/jobboard/css/colors_teal_and_brown.css" rel="alternate stylesheet" type="text/css" title="Teal and brown" media="screen" />
	<link href="jsp/jobboard/css/colors_cherry_and_yellow.css" rel="alternate stylesheet" type="text/css" title="Cherry and yellow" media="screen" />
	<!--[if lte IE 8]>
		<link href="css/ie.css" rel="stylesheet" type="text/css" media="screen" />
	<![endif]-->
	<link href="http://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet" type="text/css" media="screen" />

	<script type="text/javascript" src="jsp/jobboard/scripts/jquery_minimized_core.js"></script>
		<SCRIPT type="text/javascript" src="jsp/js/jquery.blockUI.js"></SCRIPT>
	<script type="text/javascript" src="jsp/jobboard/scripts/styleswitcher.js"></script>

	<title>Hires360 hiring made easy</title>

</head>

<bean:define id="userRegForm" name="userRegForm" type="com.form.UserRegForm" />

<%
String alreadyexist = (String)request.getAttribute("alreadyexist");
%>

<script language="javascript">	
String.prototype.trim = function() { 
    return this.replace(/^\s+|\s+$/g,""); 
} 
String.prototype.ltrim = function() { 
    return this.replace(/^\s+/,""); 
} 
String.prototype.rtrim = function() { 
    return this.replace(/\s+$/,""); 
} 
</script>

<script language="javascript">


 
function closewindow(){
	 	   parent.parent.GB_hide(); 

	  
	}

function closewindow2(){
	//self.parent.location.reload();
	 	      parent.parent.GB_hide(); 

	  
	}	
function closewindow1(){
	 	   self.close();

	  
	}




$(document).ready(function() {$('#savedata').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
   savedata();
	 }); 
}); 



 
function completeajx(){
	 $.unblockUI();
}




function savedata(){
	var fname=document.userRegForm.firstName.value.trim();
	var lname=document.userRegForm.lastName.value.trim();
	var email=document.userRegForm.emailId.value.trim();
	var OrgName = document.userRegForm.orgName.value.trim();
	var password = document.userRegForm.password.value.trim();
	var cpassword = document.userRegForm.cpassword.value.trim();
	//var subdomain = document.userRegForm.subdomain.value.trim();

	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
	var alertstr="";
	var showalert=false;
	var alphabate=/^[a-zA-Z]+$/;
	var numbers=/^[0-9]+$/;

/*if(subdomain == "" || subdomain == null || subdomain =="mycompany")
	{
 	alertstr = alertstr+"Job board is required.\n";
 	showalert = true;
		}*/

	if(fname == "" || fname == null){
     	alertstr = alertstr + "First name is required.\n";
     	showalert = true;
		}
	if(lname == "" || lname == null)
	{
 	alertstr = alertstr+"Last name is required.\n";
 	showalert = true;
		}
	if(email == "" || email == null)
	{
 	alertstr = alertstr+"Email address is required.\n";
 	showalert = true;
		}
	if(reg.test(email) ==false){
     	alertstr = alertstr + "Email address is not valid.\n";
		showalert = true;
		}
	 
if(OrgName == "" || OrgName == null)
	{
 	alertstr = alertstr+"Company name is required.\n";
 	showalert = true;
		}

if(password == "" || password == null)
	{
 	alertstr = alertstr+"Password is required.\n";
 	showalert = true;
		}

if(password != cpassword)
	{
 	alertstr = alertstr+"Password and Re-type password should be same.\n";
 	showalert = true;
		}



	

	if (showalert){
     	alert(alertstr);
        return false;
          }

 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

/*dataString = $("#account").serialize();
$.ajax({
	type: 'POST',
  url: "reguser.do?method=saveUserdata",
data: dataString,
  success: function(data){
  $('#reguserdatasuccess').html(data);
	completeajx();
  }
});*/

	 document.userRegForm.action = "reguser.do?method=saveUserdata";
  document.userRegForm.submit();
	}

function checkemailexist(){
	var email=document.userRegForm.emailId.value.trim();
   

	$.ajax({
		type: 'GET',
	  url: "reguser.do?method=emailidexistcheck&email="+email,
	  success: function(data){
	  $('#emailidexistmessage').html(data);
		
	  }
	});

}

function checksubdomainexist(){
	var subdomain=document.userRegForm.subdomain.value.trim();
   

	$.ajax({
		type: 'GET',
	  url: "reguser.do?method=subdomainexistcheck&subdomain="+subdomain,
	  success: function(data){
	  $('#subdomainexistmessage').html(data);
		
	  }
	});

}



function leave() {
  window.location = "<%=request.getContextPath()%>/login.do?method=login";
}
function resetEmailid(){
	document.userRegForm.emailId.value="";
}
function resetsubdomain(){
	document.userRegForm.subdomain.value="";
}

</script>



<body>

	<div id="header_wrap">

		<div id="header">


			<div class="clear"></div>

		</div> <!-- End of header -->

	</div> <!-- End of header wrap -->

	<div id="page_title_wrap">

		<div id="page_title">

			<h3>Signup </h3>

			<div class="clear"></div>

		</div>

	</div>

	<div id="content_wrap">

		<div id="content">	

			<div id="main_content_wide_wpadding" class="left">
					
				<html:form action="/reguser.do?method=reg" styleId="account" method="post">

					<%
				if(alreadyexist != null && alreadyexist.equals("yes")){
				%>
				<div align="center">
					<table border="0" width="100%">
					
					
					<tr>
							<td><font color="red" size="3">You have already registered with us , contact sales team to get registered again.</font></td>

						</tr>
					
						
					</table>
				</div>
				<%}%>

						<h3><span>1</span>Tell us a little bit about yourself</h3>

						<ol>
<%
	if(!StringUtils.isNullOrEmpty(Constant.getValue("is.open.source")) && Constant.getValue("is.open.source").equals("yes")){

}else{%>

							<li>
								<label for="first_name">Job Board<font color="red">*</font></label>								
								<html:text property="subdomain" size="30" value="mycompany" onfocus="if (this.value == 'mycompany')this.value = '';" maxlength="450" styleClass="text" onblur="javascript:checksubdomainexist()" />.hires360.com<br>
								<span id="nono" STYLE="font-size: smaller;">Letters and numbers only. No spaces, No dot and no "www".Example: mycompany.hires360.com</span>
								<span id="nono" STYLE="font-size: smaller;">(You can change this later)</span><br>
								<span id="subdomainexistmessage">
								<input type="hidden" name="issubdomainexist" value="no"/>
								</span>
							</li>

<%}%>
							<li>
								<label for="last_name">First Name<font color="red">*</font></label>
								<html:text property="firstName" size="40" maxlength="200" styleClass="text"/>
							</li>

							<li>
								<label for="last_name">Last Name<font color="red">*</font></label>
								<html:text property="lastName" size="40" maxlength="200" styleClass="text"/>
							</li>

							
							<li>
								<label for="company_name">Company name<font color="red">*</font></label>
								<html:text property="orgName" size="40" maxlength="200" styleClass="text"/>
							</li>

							<li>
								<label for="company_name">Office phone</label>
								<html:text property="phoneOffice" size="40" maxlength="20" styleClass="text"/>
							</li>
							<li>
								<label for="country">Country</label>
								<html:select  property="nationalityId" styleClass="list">
								<bean:define name="userRegForm" property="countryList" id="countryList" />

								<html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
								</html:select>
							</li>
							<li>
								<label for="company_name">City</label>
								<html:text property="city" size="40" maxlength="200" styleClass="text"/>
							</li>
							<li>
								<label for="country">Time zone</label>
							<html:select  property="timezoneId" styleClass="list">
							<bean:define name="userRegForm" property="timezoneList" id="timezoneList" />

							<html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
							</html:select>
							</li>
						</ol>

					

					<fieldset>

						<h3><span>2</span>And now some account info</h3>

						<ol>
							<li>
								<label for="email">Email address</label>
								<html:text property="emailId" size="40" maxlength="500" styleClass="text" onblur="javascript:checkemailexist()" /><br>
								<span id="emailidexistmessage">
								<input type="hidden" name="isemailexist" value="no"/>
								</span>
							</li>
							<li>
								<label for="password">Password</label>
								<html:password property="password" size="40" maxlength="50" styleClass="text"/>
							</li>
							<li>
								<label for="retype">Re-type password</label>
								<html:password property="cpassword" size="40" maxlength="50" styleClass="text"/>
							</li>					
						</ol>

					</fieldset>

					
			<html:hidden property="ipaddress" value="<%=request.getRemoteAddr()%>"/>
			<html:hidden property="packagetaken" value="<%=userRegForm.getPackagetaken()%>"/>

						<span id="reguserdatasuccess">
						</span>

						<font size="1">By clicking the button below, you agree to our <a href="http://www.hires360.com/privacy.html">Terms of Use</a>, <a href="http://www.hires360.com/privacy.html">Privacy Policy</a> and <a href="http://www.hires360.com/privacy.html">Anti-spam Policy</a>.</font>
						<table>
						<tr>
						<td width="150px">
						</td>
						<td>
						<a class="call_to_action" href="#" onClick="savedata();return false;">Create your account</a>
						</td>
						</tr>
						</table>

					</fieldset>

				</html:form>

			</div> <!-- End of main content -->

			

			<div class="clear"></div>

		</div> <!-- End of content -->

	</div> <!-- End of content wrap -->

	<div id="footer_wrap">

		<div id="footer">

			<div class="box_wide separator_r">

				<ol id="nav_footer">
					<li><a href="http://www.hires360.com/index.html">Home</a></li>
					<li><a href="http://www.hires360.com/tour.html">Tour</a></li>
					<li><a href="http://www.hires360.com/pricing.html">Pricing &amp; Signup</a></li>
					<li><a href="http://www.hires360.com/contact.html">Contact</a></li>
					<li><a href="http://hires360.com/hr_lite/login.do?method=login">Login</a></li>
				</ol>

				<ul id="nav_policies">
					<li><a href="http://www.hires360.com/simple_text.html">Security information</a></li>
					<li><a href="http://www.hires360.com/simple_text.html">Privacy policy</a></li>
					<li><a href="http://www.hires360.com/simple_text.html">Legal stuff</a></li>
				</ul>
				
				<p id="copyright">Copyright &copy; 2009-2010 Chiteswar Solutions - All Rights Reserved</p>

			</div>

			<div id="social_info" class="box_small separator_r">

				<ul>
					<li id="twitter"><a href="https://twitter.com/#!/hires360">follow us</a> on twitter</li>
					<li id="facebook"><a href="#">become a fan</a> on facebook</li>
				</ul>

			</div>

			<div id="contact_info" class="box_small">

				<h5>Contact</h5>
				<ul>
					<li>+91 8087018625</li>
					<li><a href="#">sales@hires360.com</a></li>
				</ul>

			</div>
			
			<div class="clear"></div>

		</div> <!-- End of footer -->

	</div> <!-- End of footer wrap -->

</body>

</html>