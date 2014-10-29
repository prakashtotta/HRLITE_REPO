 <%@ include file="../common/include.jsp" %>
 <title>Change password </title>
<bean:define id="refform" name="refferalForm" type="com.form.RefferalForm"/>
<style>
span1{color:#ff0000;}
</style>
 <script language="javascript">

function closewindow(){
	 	   window.top.hidePopWin();

	  
	}
function closewindow1(){
	 	 // window.top.hidePopWin();
		 parent.parent.GB_hide();

	  
	}

function discard(){
	  var doyou = confirm("Are you sure you would like to discard the changes? ( OK = yes Cancel = No)");
	  if (doyou == true){
		  parent.parent.GB_hide();
	   //window.top.hidePopWin();
	   } 
	}


	function savedata(){

		var alertstr = "";
        var showalert=false;

		var password = document.refferalForm.password.value;
		var cpassword = document.refferalForm.cpassword.value;
        var currentpassword = document.refferalForm.currentpassword.value;
		

	if(currentpassword == "" || currentpassword == null){
     	alertstr = alertstr + "current password is  mandatory.<BR>";
		showalert = true;
		}

   if(password == "" || password == null){
     	alertstr = alertstr + "password is  mandatory.<BR>";
		showalert = true;
		}

	if(cpassword == "" || cpassword == null){
     	alertstr = alertstr + "confirm password is mandatory.<BR>";
		showalert = true;
		}

		if(password != cpassword){
			alert("Password and Confirm password does not match");
			return false;
		}

       if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.refferalForm.action = "refferal.do?method=changepasswordsubmit&refferalid=<%=refform.getEmployeeReferalId()%>";
   document.refferalForm.submit();
	}
</script>




<html:form action="/refferal.do?method=logon" >
<%
String ispasswordchanged = (String)request.getAttribute("ispasswordchanged");
String currentpasswordwrong = (String)request.getAttribute("currentpasswordwrong");

	
if(ispasswordchanged != null && ispasswordchanged.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="green">password changed successfully.</font></td>
			<td><!--  <a href="#" onclick="closewindow1()">close window</a> --></td>
		</tr>
		
	</table>
</div>

<%}else {%>
	<% 
	if(currentpasswordwrong != null && currentpasswordwrong.equals("yes")){
	%>
	<div align="center" class="div">
		<table border="0" width="100%">
		
		
			<tr>
				<td><font color="red">Current password entered is not correct.</font></td>
				
			</tr>
			
		</table>
	</div>
	<br><br>
	<%}%>
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 <tr>
	  <td> <b></b></td><td></td>
	  </tr>

	  <tr>
			<td>Current password<span1>*</span1></td>
			<td><html:password property="currentpassword"  size="20" maxlength="50"/></td>
		</tr>

		<tr>
			<td>Password<span1>*</span1></td>
			<td><html:password property="password" size="20" maxlength="50"/></td>
		</tr>
		<tr>
			<td>Confirmation password<span1>*</span1></td>
			<td><html:password property="cpassword" size="20" maxlength="50"/></td>
		</tr>
		<tr></tr><tr></tr>
		<tr><td>
		<input type="button" name="login" value="submit" onClick="savedata()" class="button">	
		<input type="button" name="login" value="cancel" onClick="discard()" class="button">
			
			</td>
		</tr>
</table>
</div>
<%} %>
</html:form>