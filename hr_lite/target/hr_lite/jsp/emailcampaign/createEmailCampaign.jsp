<%@ include file="../common/include.jsp" %>
<bean:define id="eForm" name="emailCampaignForm" type="com.form.EmailCampaignForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
 <html>
  <head></head>


<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}

function closewindow()
		{
	// self.parent.location.reload();
	 parent.parent.GB_hide();
	}
function init(){


}

function savedata(){
	var alertstr = "";
	var showalert=false;
	var numbers=/^[0-9]+$/;
	var name = document.emailCampaignForm.emailCampaignName.value.trim();
	var decs=document.emailCampaignForm.emailCampaignDesc.value.trim();
	var port=document.emailCampaignForm.smptoport.value.trim();
	var password=document.emailCampaignForm.smtppassword.value.trim();
	var confPassword=document.emailCampaignForm.smtpcnfpassword.value.trim();
			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignName.required",user1.getLocale())%><br>";
		showalert = true;
	}


	if(port == "" || port == null){
	}else{
			if(numbers.test(port)==false){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.enter_To_amt_in_integer",user1.getLocale())%><br>";
			showalert = true;
			}
	}
	if(password != confPassword){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.password_not_match",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.emailCampaignForm.action = "emailcampaign.do?method=saveEmailCampaign";
	  document.emailCampaignForm.submit();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
	var numbers=/^[0-9]+$/;
	var name = document.emailCampaignForm.emailCampaignName.value.trim();
	var decs=document.emailCampaignForm.emailCampaignDesc.value.trim();
	var port=document.emailCampaignForm.smptoport.value.trim();
	var password=document.emailCampaignForm.smtppassword.value.trim();
	var confPassword=document.emailCampaignForm.smtpcnfpassword.value.trim();
			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignName.required",user1.getLocale())%><br>";
		showalert = true;
	}


	if(port == "" || port == null){
	}else{
			if(numbers.test(port)==false){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.enter_To_amt_in_integer",user1.getLocale())%><br>";
			showalert = true;
			}
	}
	if(password != confPassword){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.password_not_match",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.emailCampaignForm.action = "emailcampaign.do?method=updateEmailCampaign&emailCampaignId=<%=eForm.getEmailCampaignId()%>";
	  document.emailCampaignForm.submit();
	}

function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
		 if (doyou == true){

				  document.emailCampaignForm.action = "emailcampaign.do?method=deleteEmailCampaign&fromwhere=lov&emailCampaignId=<%=eForm.getEmailCampaignId()%>";
				  document.emailCampaignForm.submit();
		
	 	}
	 }
</script>

<%
String emailCampaignSaved = (String)request.getAttribute("emailCampaignSaved");
	
if(emailCampaignSaved != null && emailCampaignSaved.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.emailcampaign.saved",user1.getLocale())%> </font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
String errormsg = (String)request.getAttribute("error");
	
if(errormsg != null){
%>
<div align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td>&nbsp;&nbsp;&nbsp;<font color="red"><%=errormsg%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
String emailCampaignupdated = (String)request.getAttribute("emailCampaignupdated");
	
if(emailCampaignupdated != null && emailCampaignupdated.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.emailcampaign.updated",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String emailcampaigndeleted = (String)request.getAttribute("emailcampaigndeleted");
	
if(emailcampaigndeleted != null && emailcampaigndeleted.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.emailcampaign.deleted",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a>--></td>
		</tr>
		
	</table>
</div>
	<%}else{%>	
<body onload="init()" >

<html:form action="/emailcampaign.do?method=saveEmailCampaign">
<br>
<div class="div">
<table border="0" width="100%">

	<font color = red ><html:errors /> </font>
			<tr>
				<td width="30%"><%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignName",user1.getLocale())%><font color="red">*</font></td>
				<td><html:text property="emailCampaignName" size="50" maxlength="300"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignDesc",user1.getLocale())%></td>
				<td><html:textarea property="emailCampaignDesc" cols="47" rows="5"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignemail",user1.getLocale())%></td>
				<td><html:text property="emailCampaignemail" size="50" maxlength="300"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignFormat",user1.getLocale())%></td>
				<td>
				<%String emailCampaignSubFormat="applicantName#jobcode"; %>
				<%=emailCampaignSubFormat%><html:hidden property="emailCampaignFormat" value="applicantName#jobcode"/></td>
			</tr>			
			
			<tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.smtpserver",user1.getLocale())%></td>
				<td><html:text property="smtpserver" size="50" maxlength="300"/></td>
			</tr>
		    <tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.smptport",user1.getLocale())%></td>
				<td><html:text property="smptoport" size="50" maxlength="300"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.smtpuser",user1.getLocale())%></td>
				<td><html:text property="smtpuser" size="50" maxlength="300"/></td>
			</tr>		
			<tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.password",user1.getLocale())%></td>
				<td><html:text property="smtppassword" size="50" maxlength="300"/></td>
			</tr>	
			<tr>
				<td><%=Constant.getResourceStringValue("admin.emailcampaign.confirmpassword",user1.getLocale())%></td>
				<td><html:text property="smtpcnfpassword" size="50" maxlength="300"/></td>
			</tr>		

			
</table>
<br><br>
<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(eForm.getEmailCampaignId() != 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%
			}else{
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%
			}
			%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</td>
		</tr>
		</table>
</div>
</html:form>

</body>
<%}%>