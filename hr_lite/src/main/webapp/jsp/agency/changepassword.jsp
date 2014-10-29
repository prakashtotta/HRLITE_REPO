 <%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

 <title><%=Constant.getResourceStringValue("hr.user.Change_password",user1.getLocale())%> </title>
<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />
<style>
span1{color:#ff0000;}
</style>
 
 <script language="javascript">

function closewindow(){
	 	    parent.parent.GB_hide();

	  
	}
function closewindow1(){
	 	   parent.parent.GB_hide();

	  
	}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   parent.parent.GB_hide();
	   } 
	}


	function savedata(){
        var alertstr ="";
		var password = document.createUserForm.password.value;
		var cpassword = document.createUserForm.cpassword.value;
        var currentpassword = document.createUserForm.currentpassword.value;
		var showalert=false;

	if(currentpassword == "" || currentpassword == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.current_password_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

   if(password == "" || password == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.pass_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(cpassword == "" || cpassword == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.confirm_password_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

		if (showalert){
     	alert(alertstr);
        return false;
          }

		  

		if(password != cpassword){
			alert("<%=Constant.getResourceStringValue("hr.user.new.confirm.Pass_does_not_match",user1.getLocale())%>");
			return false;
		}


	  document.createUserForm.action = "agencydetails.do?method=changepasswordsubmit&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();
	}
</script> 


<%
String isuseradded = (String)request.getAttribute("isuseradded");
	
if(isuseradded != null && isuseradded.equals("yes")){
%>
<div align="center" class="button">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.pass_changed_successfully",user1.getLocale())%></font></td>
			<td ><!--  <a href="#" onclick="closewindow1()" ><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<html:form action="/user.do?method=logon" >
<br>
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 <tr>
	  <td> </td><td></td>
	  </tr>

	  <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Current_password",user1.getLocale())%><font color="red">*</font></td>
			<td><html:password property="currentpassword" size="20" maxlength="50"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Password",user1.getLocale())%><font color="red">*</font></td>
			<td><html:password property="password" size="20" maxlength="50"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Confirmation_password",user1.getLocale())%><font color="red">*</font></td>
			<td><html:password property="cpassword" size="20" maxlength="50"/></td>
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
		
		<tr><td>
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">	

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"  class="button">
			
			</td>
		</tr>
</table>
</div>
</html:form>