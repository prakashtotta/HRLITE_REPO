<%@ include file="../common/includeLogin.jsp" %>
<script type="text/javascript" src="jsp/js/jquery.blockUI.js"></script>
<html>
<%
String rurl = (String)request.getAttribute("rurl");
		
System.out.println("rurl"+rurl);
%>
<script language="javascript">
$(document).ready(function() {$('#login').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
  // retrieveData("emergencycontactinfo");
	 }); 
});
</script>

<link rel="stylesheet" type="text/css" href="jsp/css/tabs.css" />
<body onLoad="initfocus()">
<html:form action="/login.do?method=logon">
<table border="0" width="100%">
<tr>
<td valign="top" >

<div id="header">
	<ul id="primary">
		<li><span>Talent Acquisition</span></li>
		<li><a href="alogin.do?method=login">Agency Portal</a></li>
		<li><a href="applicantlogin.do?method=login">Applicant Login</a></li>
	</ul>
	</div>
	<div id="main">
		

<div class="subdiv">
<input type="hidden" name="redirecturl" value="<%=rurl%>">
<table border="0" cellpadding="1" cellspacing="1">
<tr>
<td valign="top" width="50"></td>

<td valign="top" width="400">
<table border="0" cellpadding="0" cellspacing="0" width="400">

<br> <font color = red ><html:errors /> </font>

 <tr>
 <td colspan="5"  background="jap/images/tool_dots.gif"></td>
 </tr>

<tr>
<td>
</td>
<td colspan="4" >
    <div class="container">
	<table border="0" bordercolor="#999999" cellpadding="1">	

	<tr><td><br><bean:message key="loginForm.username"/></td>
	    <td><br><html:text property="username" size="20" styleClass="textsmall" maxlength="50" value=""/></td>
	</tr>
	<tr>
	<td></td>
	<td></td>
	</tr>
	<tr><td><bean:message key="loginForm.password"/></td>
	    <td><html:password property="password" size="20" styleClass="textsmall" maxlength="50" value=""/></td>
	</tr>
	<tr><td>Remember me</td>
	    <td><html:checkbox  property="remme"/></td>
	</tr>
	
	<tr>
	<td><br><input type="submit" id="login" name="login" value="Login" class="button" ></td>
	<td><br><a class="button" href="login.do?method=forgotpassword" target="new">Forgot password? </a> &nbsp; &nbsp; <a class="button" href="reguser.do?method=reg&pkg=AD1F8F7D96E61267" target="new">Sign up </a></td>
	</tr>
	
	<tr><td colspan="2">&nbsp;</td></tr>
	
	</table>
	</div>
	
</td>
</tr>

<tr><td colspan="5">

</td></tr>
<tr><td colspan="5"><br></td></tr>



</table>

</td>

</tr>
</table>
<br>

  </div>  	

</div>
 <br/><br/>
<img src="jsp/images/login_background_half1.PNG" align="right" height="135px" width="240px"/>
 
</td>
<td  width="100%" align="right" valign="baseline">
<br/>
<div align="left" >

<img src="jsp/images/login_background_half2.PNG" height="435px" width="509px"/>
</div>
 <!-- <a href="applicantlogin.do?method=login">Applicant login </a> -->
</td>
</tr>
</table>

<!-- 
<div align="right" >
	<img src="jsp/images/login_background_half1.PNG" height="162px" width="652px"/>
</div>
 -->
</html:form>
</body>
<script language="javascript">
function initfocus(){
	document.loginForm.username.focus();
	
	}
</script>