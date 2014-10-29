<%@ include file="../common/include.jsp" %>

<bean:define id="aform" name="refferalForm" type="com.form.RefferalForm" />

<%
String backfromgetemail = (String)request.getAttribute("backfromgetemail");
String emailidnotcorrect = (String)request.getAttribute("emailidnotcorrect");
String isUseralreadyexist= (String)request.getAttribute("isUseralreadyexist");
%>

<script language="javascript">
var backfromgetemail = "<%=backfromgetemail%>";
var returnVal = "something11";

function savedata(){
	var alertstr = "";
	
	var employeeemail = document.refferalForm.employeeemail.value.trim();
   
	var showalert=false;

	

			if(employeeemail == "" || employeeemail == null){
     	alertstr = alertstr + "Email id is  mandatory.<BR>";
		showalert = true;
		}

		

 if (showalert){
     	alert(alertstr);
        return false;
          }

	

	
	  document.refferalForm.action = "refferal.do?method=getdetailbyemail";
	  document.refferalForm.submit();

}

function init(){
	
	if(backfromgetemail != null && backfromgetemail == "yes" ){
	returnVal = "<%=aform.getEmployeeemail()%>";
    window.top.hidePopWin(true);
		
}
}

</script>

<%if(emailidnotcorrect != null && emailidnotcorrect.equals("yes")){%>

<font color="red" class="button">Email id wrong. Please try with correct email id.</font>
<%}%>
<%if(isUseralreadyexist != null && isUseralreadyexist.equals("yes")){%>

<font color="red" class="button">This user already exists. Please try with another email id.</font>
<%}%>

<body onload="init()">
<html:form action="/refferal.do?method=getdetailbyemail" >
<br>
<div class="button">
<i><b>Employee email</b></i> 
<table border="0" width="100%">
	
<tr>
<td>
Email id *: 
</td>
<td>
<html:text property="employeeemail" size="30"/>
</td>
</tr>

<tr>
<td>
<input type="button" name="login" value="submit" onClick="savedata()" class="button">
</td>
<td>

</td>
</tr>

</div>
</html:form>

</body>