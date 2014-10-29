<%@ include file="../common/include.jsp" %>

<html>
<%
String rurl = (String)request.getAttribute("rurl");
		
System.out.println("rurl"+rurl);
%>

<script language="javascript">
function refreg(){
var url = "<%=request.getContextPath()%>/refferal.do?method=emailscr";
parent.setPopTitle1("Enter Email id");
parent.showPopWin(url, 400, 200, registrationPage);
}


function registrationPage(retval){
	if(retval != null){
	location.href="<%=request.getContextPath()%>/refferal.do?method=reg&emailid="+retval;
	}
			}
</script>

<link rel="stylesheet" type="text/css" href="jsp/css/tabs.css" />
<div id="header">
	<ul id="primary">
		<li><a href="login.do?method=login">Talent Aquisition</a></li>
		<li><span>Referral Portal</span></li>
		<li><a href="alogin.do?method=login">Agency Portal</a></li>
	</ul>
	</div>

	<div id="main" class="div">

<html:form action="/reflogin.do?method=logon" >
<input type="hidden" name="redirecturl" value="<%=rurl%>">
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr>
<td valign="top" width="50"></td>

<td valign="top" width="700">
<table border="0" cellpadding="0" cellspacing="0">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>
 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="100" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="340" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="240" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>

 <tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;Login</td></tr>
 <font color = red ><html:errors /> </font>
 <%

String notactivated=(String)request.getAttribute("notactivated");

%>
 <%if(notactivated != null && notactivated.equals("yes")){%>

<br><div class="msg"><font color="white">User is not activated. please click on <A href="refferal.do?method=verfificationstartscr">New verification code? </a></font></div><br>
<%}%>
 <tr>
 <td colspan="5"  background="jap/images/tool_dots.gif"></td>
 </tr>

<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>
<td colspan="4">

	<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">	

	<tr><td>Email</td><td>  <html:text property="employeeemail" size="20" maxlength="50" /></td>
	</tr>
	<tr><td><br>Password</td><td>
	    <br><html:password property="password" size="20" maxlength="50" /></td>
	</tr>
	<tr><td><br>Remember me </td><td> <br><html:checkbox  property="remme"/></td>
	</tr>
	
	
	<tr><td></td><td><input type="submit" name="login" value="Login" class="button"></td>
	</tr>
	
	<tr></td><td><td colspan="2">&nbsp;</td></tr>
	
	</table>
	
	
</td>
</tr>

<tr><td colspan="5" class="ModuleText"><b>Note: Employee job refferal portal</b></td></tr>

<tr><td colspan="5"><a class="button" href="#"  onClick="javascript:refreg()">Not yet registered? </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="button" href="refferal.do?method=verfificationstartscr">New verification code? </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="button" href="reflogin.do?method=forgotpasswordscr">Forgot password? </a><br></td></tr>
<tr><td colspan="5"><br></td></tr>

<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>


</table>
</td>

</tr>
</table>
</html:form>
    	
</div>