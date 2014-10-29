<%@ include file="../common/include.jsp" %>
 <html>
  <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<bean:define id="cform" name="jobCategoryForm" type="com.form.JobCategoryForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

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
	setTimeout ( "document.jobCategoryForm.jobCategoryName.focus(); ", 200 );

}

function savedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.jobCategoryForm.jobCategoryName.value.trim();
	var desc=document.jobCategoryForm.jobCategoryDesc.value.trim();


			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobcategory.name.required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobcategory.desc.required",user1.getLocale())%><br>";
		showalert = true;
		}


		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobcategory.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	 	document.jobCategoryForm.action = "jobcategory.do?method=saveJobCategory";
 		document.jobCategoryForm.submit();


 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.jobCategoryForm.jobCategoryName.value.trim();
	var desc=document.jobCategoryForm.jobCategoryDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobcategory.name.required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobcategory.desc.required",user1.getLocale())%><br>";
		showalert = true;
		}

		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobcategory.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	 	document.jobCategoryForm.action = "jobcategory.do?method=updateJobCategory&jobCategoryId="+'<bean:write name="jobCategoryForm" property="jobCategoryId"/>';
 		document.jobCategoryForm.submit();

 
	}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
	document.jobCategoryForm.action = "jobcategory.do?method=deleteJobCategory&jobCategoryId="+'<bean:write name="jobCategoryForm" property="jobCategoryId"/>';
	document.jobCategoryForm.submit();
	 }
}

</script>

<%
String JobCategorysaved = (String)request.getAttribute("JobCategorysaved");
	
if(JobCategorysaved != null && JobCategorysaved.equals("yes")){
	%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.jobcategory.saved",user1.getLocale())%> </font></td>
			<td> <!-- <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String JobCategoryupdated = (String)request.getAttribute("JobCategoryupdated");
	
if(JobCategoryupdated != null && JobCategoryupdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.jobcategory.updated",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String JobCategorydeleted = (String)request.getAttribute("JobCategorydeleted");
	
if(JobCategorydeleted != null && JobCategorydeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.jobcategory.deleted",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>
<%}else{%>	

<body onload="init()">

<html:form action="/jobcategory.do?method=saveJobCategory">

	<font color = red ><html:errors /> </font>
	
	<div class="div">
	<table border="0" width="100%">

			

	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.jobcategory.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="jobCategoryName" size="50" maxlength="300"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.jobcategory.desc",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="jobCategoryDesc" cols="47" rows="5"/></td>
		</tr>
		
	</table>
			
	<table border="0" width="100%">
		<tr>
			<td><% if (cform.getJobCategoryId() != 0){%>			
			
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<%}%>
			<td></td>
		</tr>

		
	</table>
		
</div>

</html:form>

</body>
<%}%>
