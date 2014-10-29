 <%@ include file="../common/include.jsp" %>
 <%
String path = (String)request.getAttribute("filePath");
 ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
 <title><%=Constant.getResourceStringValue("hr.user.Change_password",user1.getLocale())%> </title>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
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
	  var doyou = confirm('<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>');
	  if (doyou == true){
		  parent.parent.GB_hide();
	   //window.top.hidePopWin();
	   } 
	}


	function savedata(){

		var alertstr = "";
        var showalert=false;

		var password = document.applicantForm.password.value;
		var cpassword = document.applicantForm.cpassword.value;
        var currentpassword = document.applicantForm.currentpassword.value;

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

		//if(password != cpassword){
		//   alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Pass_does_not_match",user1.getLocale())%><br>";	
		//   showalert = true;
		//}


		
       if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.applicantForm.action = "editapplicant.do?method=changepasswordsubmit&applicantId=<%=aform.getApplicantId()%>";

   document.applicantForm.submit();
	}
</script>




<html:form action="/editapplicant.do?method=logon" >
<%
String ispasswordchanged = (String)request.getAttribute("ispasswordchanged");

	
if(ispasswordchanged != null && ispasswordchanged.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.pass_changed_successfully",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else {%>

<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 <tr>
	  <td> <b></b></td><td></td>
	  </tr>

	  <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Current_password",user1.getLocale())%><font color="red">*</font></td>
			<td><html:password property="currentpassword"  size="20" maxlength="50" /></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Password",user1.getLocale())%><font color="red">*</font></td>
			<td><html:password property="password" size="20" maxlength="50"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Confirmation_password",user1.getLocale())%><font color="red">*</font></td>
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