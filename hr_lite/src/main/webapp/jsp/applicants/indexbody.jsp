<%@ include file="../common/includeLogin.jsp" %>

<html>
<%
String rurl = (String)request.getAttribute("rurl");
		
System.out.println("rurl"+rurl);
%>

<link rel="stylesheet" type="text/css" href="jsp/css/tabs.css" />
<body onLoad="initfocus()">
<div id="header">
	<ul id="primary">
		<li><a href="login.do?method=login">Talent Acquisition</a></li>
		<li><a href="alogin.do?method=login">Agency Portal</a></li>
		<li><span>Applicant Login</span></li>
	</ul>
	</div>
	<div id="main">
	
<html:form action="/applicantlogin.do?method=logon">
<div class="subdiv">

<input type="hidden" name="redirecturl" value="<%=rurl%>">
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1" class="subdiv">
<tr>
<td valign="top" width="50"></td>

<td valign="top" width="600">
<table border="0" cellpadding="0" cellspacing="0" width="600">

<br> <font color = red ><html:errors /> </font>

 <tr>
 <td colspan="5"  background="jap/images/tool_dots.gif"></td>
 </tr>

<tr>
<td>
</td>
<td colspan="4">
     <div class="container">
	<table border="0" bordercolor="#999999" cellpadding="1">	

	<tr><td><br>Email id</td>
	    <td><br><html:text property="username" size="20" styleClass="textsmall" maxlength="50" value=""/></td>
	</tr>

	<tr><td><br>Password</td>
	    <td><br><html:password property="password" size="20" styleClass="textsmall" maxlength="50" value=""/></td>
	</tr>
	<tr><td><br>Login code</td>
	    <td><br><html:text property="applicantCode" size="20" styleClass="textsmall" maxlength="50" value=""/></td>
	</tr>
		
	
	<tr><td><br><input type="submit" name="login" value="Login" class="button"></td><td><br><a class="button" href="applicantoffer.do?method=forgotpasswordsapplicant" target="new">Forgot password? </a></td>
	</tr>
	
	<tr><td colspan="2">&nbsp;</td></tr>
	
	</table>
	</div>
	
</td>
</tr>

<tr><td colspan="5"></td></tr>
<tr><td colspan="5"><br></td></tr>


</table>
</td>

</tr>
</table>
</div>
</html:form>
  </div>  	
</body>
<script language="javascript">
function initfocus(){
	document.loginForm.username.focus();
	
	}
</script>