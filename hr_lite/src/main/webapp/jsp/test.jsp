<html:form action="/login.do?method=logon" >
<input type="hidden" name="redirecturl" value="<%=rurl%>">
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr>
<td valign="top" width="50"></td>

<td valign="top" width="700">
<table border="0" cellpadding="0" cellspacing="0" width="700">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>
 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="100" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="340" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="240" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>

 <tr><td colspan="2" class="BodyToolboxHeader">&nbsp;&nbsp;Login</td></tr>
 <font color = red ><html:errors /> </font>

 <tr>
 <td colspan="5"  background="jap/images/tool_dots.gif"></td>
 </tr>

<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>
<td colspan="4">
    <div class="container">
	<table border="0" bordercolor="#999999" cellpadding="1">	

	<tr><td><bean:message key="loginForm.username"/></td>
	    <td><html:text property="username" size="20" styleClass="textsmall" maxlength="50" value=""/></td>
	</tr>
	<tr>
	<td></td>
	<td></td>
	</tr>
	<tr><td><br><bean:message key="loginForm.password"/></td>
	    <td><br><html:password property="password" size="20" styleClass="textsmall" maxlength="50" value=""/></td>
	</tr>
	<tr><td><br>Remember me</td>
	    <td><br><html:checkbox  property="remme"/></td>
	</tr>
	
	<tr><td>&nbsp;</td><td><br><input type="submit" id="login" name="login" value="Login" class="button" ></td>
	</tr>
	
	<tr><td colspan="2">&nbsp;</td></tr>
	
	</table>
	</div>
	
</td>
</tr>

<tr><td colspan="5"><a class="button" href="login.do?method=forgotpassword">Forgot password? </a> &nbsp; &nbsp; <a class="button" href="reguser.do?method=reg&pkg=AD1F8F7D96E61267">Sign up </a><br></td></tr>
<tr><td colspan="5"><br></td></tr>

<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>


</table>
</td>

</tr>
</table>
<br>
</html:form>