<span id="emailidexistmessage">

<%
String emailidexistcheckfail = (String)request.getAttribute("emailidexistcheckfail");
	
if(emailidexistcheckfail != null && emailidexistcheckfail.equals("yes")){
%>
<font color="red" size="2">Email Id already exists, please change email id.</font>


<script type="text/javascript">

resetEmailid();
</script>

<%} else if(emailidexistcheckfail != null && emailidexistcheckfail.equals("notvalid")){%>
<font color="red" size="2">Email Id is not valid.</font>

<script type="text/javascript">

resetEmailid();
</script>

<%}else {%>
<!--<font color="green" size="2">Ok.</font>-->
<%}%>
</span>