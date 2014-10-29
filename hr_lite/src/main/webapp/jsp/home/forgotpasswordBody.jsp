<%@ include file="../common/include.jsp" %>

<bean:define id="aform" name="loginForm" type="com.form.LoginForm" />

<style>
span1{color:#ff0000;}
</style>
<script language="javascript">



function verificationcheck(){
	var alertstr = "";
	var showalert=false;
	  var username = document.loginForm.username.value.trim();
      if(username == "" || username == null){
     	alertstr = alertstr + "username  is required.<BR>";
		showalert = true;
		}
	   if (showalert){
     	alert(alertstr);
        return false;
          }

	 document.loginForm.action = "login.do?method=forgotpasswordsubmit";
	  document.loginForm.submit();
}

</script>

<%

String wrongemailid=(String)request.getAttribute("wrongemailid");
String forgotpasswordsent=(String)request.getAttribute("forgotpasswordsagsent");
%>


<%if(forgotpasswordsent != null && forgotpasswordsent.equals("yes")){%>
<div class="msg"><font color="white"><br>Password sent to your email address.<A href="login.do?method=login">login</a></font></div><br>
<%}else{%>
<html:form action="/login.do?method=forgotpasswordsubmit" >
<br>
<div style="border:1px solid gray; width:600px;">
<div class="container">
<i><b>Forgot password</b></i> 
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<%if(wrongemailid != null && wrongemailid.equals("yes")){%>

<br><div class="msg"><font color="white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Username is invalid,Please try with valid username..</font></div><br> <!--  Are you not registered ? <A href="refferal.do?method=reg">Register now </a></font>-->
<%}%>
<tr>
<td>
Email id <font color="red">*</font> : 
</td>
<td>
<html:text property="username" size="30" styleClass="textdynamic"/>
</td>
</tr>

<tr>
<td>
<input type="button" name="login" value="submit" onClick="verificationcheck()" class="button">
</td>
<td>

</td>
</tr>
</div>
</div>
</html:form>

<%}%>