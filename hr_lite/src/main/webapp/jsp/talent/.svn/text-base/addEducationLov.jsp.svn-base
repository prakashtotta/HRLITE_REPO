<%@ include file="../common/include.jsp" %>

<bean:define id="eduForm" name="educationForm" type="com.form.EducationForm" />
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

	var name = document.educationForm.educationName.value.trim();
	var decs=document.educationForm.educationDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.education.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.education.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.educationForm.action = "education.do?method=saveEducation";
	  document.educationForm.submit();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.educationForm.educationName.value.trim();
	var decs=document.educationForm.educationDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.education.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.education.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
     
	 	document.educationForm.action = "education.do?method=updateEducation&educationId=<%=eduForm.getEducationId() %>";
	 	document.educationForm.submit();

	}

function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
		 if (doyou == true){

				  document.educationForm.action = "education.do?method=deleteEducation&educationId=<%=eduForm.getEducationId() %>";
				  document.educationForm.submit();
		
	 	}
	 }
</script>

<%
String educationsaved = (String)request.getAttribute("educationsaved");
	
if(educationsaved != null && educationsaved.equals("yes")){
%>
<div align="center" class="msg" >
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.education.saved",user1.getLocale())%> </font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String educationupdated = (String)request.getAttribute("educationupdated");
	
if(educationupdated != null && educationupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.education.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String educationdeleted = (String)request.getAttribute("educationdeleted");
	
if(educationdeleted != null && educationdeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.education.deleted",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font>--> </a></td>
		</tr>
		
	</table>
</div>
	<%}else{%>	

<body onload="init()" >

<html:form action="/education.do?method=saveEducation">
<br>
	<font color = red ><html:errors /> </font>
	<div class="div">

<table border="0" width="100%">


			<tr>
				<td width="30%"><%=Constant.getResourceStringValue("admin.education.name",user1.getLocale())%><font color="red">*</font></td>
				<td><html:text property="educationName" size="50" maxlength="300"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.education.desc",user1.getLocale())%></td>
				<td><html:textarea property="educationDesc" cols="47" rows="5"/></td>
			</tr>


			
</table>
<br><br>
<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(eduForm.getEducationId() != 0){
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