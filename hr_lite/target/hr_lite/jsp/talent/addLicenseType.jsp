<%@ include file="../common/include.jsp" %>

<bean:define id="jForm" name="licenseTypeForm" type="com.form.LicenseTypeForm" />
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

	var name = document.licenseTypeForm.licenseTypeName.value.trim();
	var decs=document.licenseTypeForm.licenseTypeDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.licensetype.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.licensetype.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.licenseTypeForm.action = "licensetype.do?method=saveLicenseType";
	  document.licenseTypeForm.submit();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.licenseTypeForm.licenseTypeName.value.trim();
	var decs=document.licenseTypeForm.licenseTypeDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.licensetype.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.licensetype.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.licenseTypeForm.action = "licensetype.do?method=updateLicenseType&licenseTypeId=<%=jForm.getLicenseTypeId() %>";
	  document.licenseTypeForm.submit();

	}

function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
		 if (doyou == true){

				  document.licenseTypeForm.action = "licensetype.do?method=deleteLicenseType&fromwhere=lov&licenseTypeId=<%=jForm.getLicenseTypeId()%>";
				  document.licenseTypeForm.submit();
		
	 	}
	 }
</script>

<%
String licensetypesaved = (String)request.getAttribute("licensetypesaved");
	
if(licensetypesaved != null && licensetypesaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.licensetype.saved",user1.getLocale())%> </font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String licensetypeupdated = (String)request.getAttribute("licensetypeupdated");
	
if(licensetypeupdated != null && licensetypeupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.licensetype.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String licensetypedeleted = (String)request.getAttribute("licensetypedeleted");
	
if(licensetypedeleted != null && licensetypedeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.licensetype.deleted",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>
	<%}else{%>	

<body onload="init()" >

<html:form action="/licensetype.do?method=saveLicenseType">
<br>
	<font color = red ><html:errors /> </font>
	<div  class="div">
<table border="0" width="100%">


			<tr>
				<td width="30%"><%=Constant.getResourceStringValue("admin.licensetype.name",user1.getLocale())%><font color="red">*</font></td>
				<td><html:text property="licenseTypeName" size="50" maxlength="300"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.licensetype.desc",user1.getLocale())%></td>
				<td><html:textarea property="licenseTypeDesc" cols="47" rows="5"/></td>
			</tr>


			
</table>
<br><br>
<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(jForm.getLicenseTypeId() != 0){
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