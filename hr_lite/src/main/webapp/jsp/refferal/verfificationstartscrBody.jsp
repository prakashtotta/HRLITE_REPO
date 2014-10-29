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

	 document.refferalForm.action = "refferal.do?method=startverificationcheck";
	  document.refferalForm.submit();
}

</script>



<%

String wrongemailid=(String)request.getAttribute("wrongemailid");
String emailidnotcorrect = (String)request.getAttribute("emailidnotcorrect");
%>



<html:form action="/refferal.do?method=startverificationcheck" >
<br>
<div style="border:1px solid gray; width:700px; margin-bottom: 1em; padding: 10px" class="div">
<div class="div">

<i><b>Employee verification for refferal program</b></i> <br><br>
</div>
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<%if(wrongemailid != null && wrongemailid.equals("yes")){%>
<div class="div">
<font color="red">Email id not present in database. Are you not registered ? <A href="refferal.do?method=reg&emailid=<%=aform.getEmployeeemail()%>">Register now </a></font>
</div>
<%}%>
<%if(emailidnotcorrect != null && emailidnotcorrect.equals("yes")){%>
<div class="div">
<font color="red">Email id wrong. Please try with correct email id.</font>
</div>
<%}%>
<br><br>
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

