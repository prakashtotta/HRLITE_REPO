 <%@ include file="../common/include.jsp" %>
 <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
 <title><%=Constant.getResourceStringValue("hr.user.Assign_username_password",user1.getLocale())%></title>
<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />

 
 <script language="javascript">

function closewindow(){
	 	   window.top.hidePopWin();

	  
	}
function closewindow1(){
	 	   self.close();

	  
	}

function discard1(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	  
      // self.parent.location.reload();
       self.close();
			 parent.parent.GB_hide();
			
	   } 
	}


	function savedata(){

		var password = document.createUserForm.password.value.trim();
		var cpassword = document.createUserForm.cpassword.value.trim();
  
		var showalert=false;
		var alertstr="";


   if(password == "" || password == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.pass_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(cpassword == "" || cpassword == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.confirm_password_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

		if(password != cpassword){
			alert("<%=Constant.getResourceStringValue("hr.user.Validation.Pass_does_not_match",user1.getLocale())%>");
			return false;
		}
 if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.createUserForm.action = "user.do?method=assignusernamesubmit&userId=<%=EncryptDecrypt.encrypt(String.valueOf(cform.getUserId()))%>";
   document.createUserForm.submit();
	}
</script>


<%
String isuseradded = (String)request.getAttribute("isuseradded");
	
if(isuseradded != null && isuseradded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.uname_pass_added_succ",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<html:form action="/user.do?method=assignusernamescr" >
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 <tr>
	  <td colspan="2"> <b><%=Constant.getResourceStringValue("hr.user.Assign_username_password",user1.getLocale())%></b></td><td></td>
	  </tr>
<tr></tr><tr></tr><tr></tr><tr></tr>
	  <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Username",user1.getLocale())%><font color="red">*</font></td>
			<td><%=cform.getUserName()%>  <html:hidden property="userName" /> </td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Password",user1.getLocale())%><font color="red">*</font></td>
			<td><html:password property="password" size="20" maxlength="50"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Confirmation_password",user1.getLocale())%><font color="red">*</font></td>
			<td><html:password property="cpassword" size="20" maxlength="50"/></td>
		</tr>
		<tr><td colspan="2">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">	
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard1()" class="button">
			
			</td>
		</tr>
</table>
</div>
</html:form>