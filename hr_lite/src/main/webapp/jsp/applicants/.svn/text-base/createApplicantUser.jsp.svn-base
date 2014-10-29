 <%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

 <title><%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%> </title>
<bean:define id="cform" name="applicantUserForm" type="com.form.ApplicantUserForm" />

 
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
		
	  document.applicantUserForm.action = "applicantUser.do?method=saveApplicantUser&applicantId=<%=cform.getApplicantId()%>&uuid=<%=cform.getUuid()%>";
   document.applicantUserForm.submit();
	}

		function updatedata(){
		
	  document.applicantUserForm.action = "applicantUser.do?method=updateApplicantUser&applicantId=<%=cform.getApplicantId()%>&uuid=<%=cform.getUuid()%>";
   document.applicantUserForm.submit();
	}
		function deletedata(){
			var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
			  if (doyou == true){
				  document.applicantUserForm.action = "applicantUser.do?method=deleteApplicantUser&applicantId=<%=cform.getApplicantId()%>&uuid=<%=cform.getUuid()%>";
				   document.applicantUserForm.submit();
			  }
			
			
		}
</script>


<%
String isuseradded = (String)request.getAttribute("isuseradded");
	
if(isuseradded != null && isuseradded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.applicant_User_created",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>


<%
String isuserupdated = (String)request.getAttribute("isuserupdated");
	
if(isuserupdated != null && isuserupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.applicant_User_updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>


<%
String email_id_alerady_exist = (String)request.getAttribute("email_id_alerady_exist");
	
if(email_id_alerady_exist != null && email_id_alerady_exist.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.email_alredy_exist",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String isuserdeleted = (String)request.getAttribute("isuserdeleted");
	
if(isuserdeleted != null && isuserdeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.applicant_User_deleted",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<br><br>

<%}else{ %>
<html:form action="/applicantUser.do?method=saveApplicantUser" >
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 <tr>
	  <td> </td><td></td>
	  </tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	 <% if(cform.getApplicantCode()!=null){%>
	  <tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.login.code",user1.getLocale())%></td>
			<td><%=cform.getApplicantCode()%></td>
		</tr>
	 <%}%>
	  <tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
			<td><html:text property="emailId" size="20" maxlength="50" disabled="true"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td>
			<html:select  property="localeId">
			<bean:define name="applicantUserForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td>
			<html:select  property="timezoneId">
			<bean:define name="applicantUserForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
		</tr>
		</table>
		<br>
		<table width="100%">
		<tr><td>
		<% if(cform.getApplicantUserId() > 0){%>
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="updatedata()" class="button">	
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">	
		<%}else{%>
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">	
		<%}%>
		

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			
			</td>
		</tr>
</table>
</div>
</html:form>
<%}%>