<%@ include file="../common/include.jsp" %>

<bean:define id="aform" name="refferalForm" type="com.form.RefferalForm" />
<style>
span1{color:#ff0000;}
</style>
<script language="javascript">



function verificationcheck(){
	var alertstr = "";
	var showalert=false;
	  var employeeemail = document.refferalForm.employeeemail.value.trim();
      if(employeeemail == "" || employeeemail == null){
     	alertstr = alertstr + "Email id is required.<BR>";
		showalert = true;
		}
	   if (showalert){
     	alert(alertstr);
        return false;
          }

	 document.refferalForm.action = "reflogin.do?method=forgotpassword";
	  document.refferalForm.submit();
}

</script>

<%
System.out.println(" ... forgot password");
String wrongemailid=(String)request.getAttribute("wrongemailid");
String forgotpasswordsent=(String)request.getAttribute("forgotpasswordsent");
%>


<%if(forgotpasswordsent != null && forgotpasswordsent.equals("yes")){%>
<div class="button">
<font color="green">Password sent to your email address.<A href="reflogin.do?method=login">login </a></font>
</div>
<%}else{%>
<html:form action="/reflogin.do?method=forgotpassword" >
<br>
<div style="border:1px solid gray; width:700px; margin-bottom: 1em; padding: 10px" class="div">
<i><b>Employee refferal program - Forgot password</b></i> 
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<%if(wrongemailid != null && wrongemailid.equals("yes")){%>
<div class="div">
<font color="red">Email id not present in database. Are you not registered ? <A class="closlink" href="refferal.do?method=reg">Register now </a></font>
</div>
<%}%>
<tr>
<td>
Email id <span1>*</span1>  : 
</td>
<td>
<html:text property="employeeemail" size="30"/>
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
</html:form>

<%}%>