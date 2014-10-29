<%@ include file="../common/include.jsp" %>

<bean:define id="cform" name="applicantForm" type="com.form.ApplicantForm" />
<style>
span1{color:#ff0000;}
</style>
<script language="javascript">



function verificationcheck(){
	var alertstr = "";
	var showalert=false;
	  var employeeemail = document.applicantForm.email.value.trim();
      var applicantCode = document.applicantForm.applicantCode.value.trim();
      if(employeeemail == "" || employeeemail == null){
     	alertstr = alertstr + "Email id is required.<BR>";
		showalert = true;
		}
		 if(applicantCode == "" || applicantCode == null){
     	alertstr = alertstr + "Login code is required.<BR>";
		showalert = true;
		}
	   if (showalert){
     	alert(alertstr);
        return false;
          }

	 document.applicantForm.action = "applicantoffer.do?method=forgotpassword&emailId="+employeeemail+"&applicantCode="+applicantCode;
	  document.applicantForm.submit();
}

</script>

<%
System.out.println(" ... forgot password");
String wrongemailid=(String)request.getAttribute("wrongemailid");
String forgotpasswordsent=(String)request.getAttribute("forgotpasswordsent");
%>


<%if(forgotpasswordsent != null && forgotpasswordsent.equals("yes")){%>
<div class="msg"><font color="white"><br>Password sent to your email address. .........<A class="button" href="applicantlogin.do?method=login">login </a></font></div><br>
<%}else{%>
<html:form action="/applicantoffer.do?method=forgotpassword&emailId=" >
<br>
<div class="div">
<i><b>Applicant - Forgot password</b></i> 
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<%if(wrongemailid != null && wrongemailid.equals("yes")){%>

<br><div class="msg"><font color="white">The Email id you entered is incorrect.</font></div><br>

<%}%>
<tr>
<td>
Email id <span1>*</span1>  : 
</td>
<td>
<html:text property="email" size="30"/>
</td>
</tr>
<tr>
<td>
Login code <span1>*</span1>  : 
</td>
<td>
<input name="applicantCode" value="" size="30">
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