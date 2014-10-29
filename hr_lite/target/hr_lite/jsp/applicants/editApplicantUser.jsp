 <%@ include file="../common/include.jsp" %>
 <bean:define id="cform" name="applicantUserForm" type="com.form.ApplicantUserForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


//JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(String.valueOf(cform.getApplicantId()), cform.getUuid());

%>

 <title><%=Constant.getResourceStringValue("hr.applicant.user.create",user1.getLocale())%> </title>


 
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
</script>


<%
String isuseradded = (String)request.getAttribute("isuseradded");
	
if(isuseradded != null && isuseradded.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("hr.user.applicant_User_created",user1.getLocale())%></font></td>
			<td> <!--<a href="#" onclick="closewindow1()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
String email_id_alerady_exist = (String)request.getAttribute("email_id_alerady_exist");
	
if(email_id_alerady_exist != null && email_id_alerady_exist.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.email_alredy_exist",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow1()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<html:form action="/applicantUser.do?method=saveApplicantUser" >
<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 <tr>
	  <td> </td><td></td>
	  </tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	  <tr>
			<td><%=Constant.getResourceStringValue("hr.applicant.user.ID",user1.getLocale())%> :</td>		
			<td><%= StringUtils.isNullOrEmpty(cform.getEmailId())?"":cform.getEmailId()%></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%> :</td>	
			<td><%= StringUtils.isNullOrEmpty(cform.getFullName())?"":cform.getFullName()%></td>

		</tr>


</table>
</div>
</html:form>