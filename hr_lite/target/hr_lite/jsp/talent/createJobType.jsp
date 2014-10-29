<%@ include file="../common/include.jsp" %>
<bean:define id="jForm" name="jobTypeForm" type="com.form.JobTypeForm" />
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

	var name = document.jobTypeForm.jobTypeName.value.trim();
	var decs=document.jobTypeForm.jobTypeDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobtype.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobtype.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.jobTypeForm.action = "jobtype.do?method=saveJobType";
	  document.jobTypeForm.submit();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.jobTypeForm.jobTypeName.value.trim();
	var decs=document.jobTypeForm.jobTypeDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobtype.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.jobtype.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.jobTypeForm.action = "jobtype.do?method=updateJobType&jobTypeId=<%=jForm.getJobTypeId() %>";
	  document.jobTypeForm.submit();
	}

function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
		 if (doyou == true){

				  document.jobTypeForm.action = "jobtype.do?method=deleteJobType&fromwhere=lov&jobTypeId=<%=jForm.getJobTypeId() %>";
				  document.jobTypeForm.submit();
		
	 	}
	 }
</script>

<%
String savejobtype = (String)request.getAttribute("savejobtype");
	
if(savejobtype != null && savejobtype.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.jobtype.saved",user1.getLocale())%> </font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updatejobtype = (String)request.getAttribute("updatejobtype");
	
if(updatejobtype != null && updatejobtype.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.jobtype.updated",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deletejobtype = (String)request.getAttribute("deletejobtype");
	
if(deletejobtype != null && deletejobtype.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.jobtype.deleted",user1.getLocale())%></font></td>
			<td><!-- <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>
	<%}else{%>	

<body onload="init()" >

<html:form action="/jobtype.do?method=saveJobType">
<br>
	<font color = red ><html:errors /> </font>
	<div class="div">
<table border="0" width="100%">


			<tr>
				<td width="30%"><%=Constant.getResourceStringValue("admin.jobtype.name",user1.getLocale())%><font color="red">*</font></td>
				<td><html:text property="jobTypeName" size="50" maxlength="300"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.jobtype.desc",user1.getLocale())%></td>
				<td><html:textarea property="jobTypeDesc" cols="47" rows="5"/></td>
			</tr>


			
</table>

<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(jForm.getJobTypeId() != 0){
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
		<br>
</div>

</html:form>

</body>
<%}%>