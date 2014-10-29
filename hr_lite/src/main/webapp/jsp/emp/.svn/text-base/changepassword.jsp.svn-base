 <%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
 <title><%=Constant.getResourceStringValue("hr.user.Change_password",user1.getLocale())%> </title>
<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />

 
 <script language="javascript">

function closewindow(){
	 	  // window.top.hidePopWin();
	parent.parent.GB_hide();
	  
	}
function closewindow1(){
	 	  //window.top.hidePopWin();
	parent.parent.GB_hide();
	  
	}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   parent.parent.GB_hide();
	   } 
	}


	function savedata(){
        var alertstr = "";
		var currentpassword = document.createUserForm.currentpassword.value.trim();
		var password = document.createUserForm.password.value.trim();
		var cpassword = document.createUserForm.cpassword.value.trim();
     
		var showalert=false;
   if(currentpassword == "" || currentpassword == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.current_password_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
   if(password == "" || password == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.newpass_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
   if(cpassword == "" || cpassword == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.confirm_password_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

  // if(password.length>0 && cpassword.length>0){
	  
   //if(password != cpassword){
	//   alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Pass_does_not_match",user1.getLocale())%><br>";	
	//   showalert = true;
	//	}
  // }

       if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.createUserForm.action = "user.do?method=changepasswordsubmit&userId=<%=cform.getUserId()%>";
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
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.pass_changed_successfully",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<html:form action="/user.do?method=logon" >
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 

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
		<tr><td>
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			
			</td>
		</tr>
</table>
</div>
</html:form>