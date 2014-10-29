<span id="reguserdatasuccess">
<head>
<%
String savesucces = (String)request.getAttribute("savesucces");
String alreadyexist = (String)request.getAttribute("alreadyexist");
	
if(savesucces != null && savesucces.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green" size="3">Saved user data successfully.</font></td>

		</tr>
	<tr>
			<td><font color="green" size="3">redirecting to login page within 1 second..</font></td>
			
		</tr>
		
	</table>
</div>
<script type="text/javascript">

setTimeout("leave()", 1000);
</script>
<%}%>
<%
if(alreadyexist != null && alreadyexist.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red" size="3">You have already registered with us , contact sales team to get registered again.</font></td>

		</tr>
	
		
	</table>
</div>
<%}%>

</span>