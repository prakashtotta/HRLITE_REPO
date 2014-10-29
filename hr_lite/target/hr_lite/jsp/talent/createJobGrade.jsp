<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="jobgradeform" name="jobGradeForm" type="com.form.JobGradeForm" />

<style>
span1{color:#ff0000;}
</style>





<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		//  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function closewindow(){
	// self.parent.location.reload();
	 parent.parent.GB_hide();
}
function init(){
setTimeout ( "document.jobGradeForm.jobGradeName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	var alertstr = "";
	var showalert=false;
		
	var desc = document.jobGradeForm.jobGradeDesc.value.trim();
	var name=document.jobGradeForm.jobGradeName.value.trim();
	var responsibility=document.jobGradeForm.jobGradeResponsibility.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobGradeName_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobGradeDesc_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(responsibility == "" || responsibility == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Rsesp_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}	
	if(responsibility.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.JobGrade.resp",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.jobGradeForm.action = "jobgrade.do?method=saveJobGrade&readPreview=3";
 document.jobGradeForm.submit();
//self.parent.location.reload();
 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
		
	var desc = document.jobGradeForm.jobGradeDesc.value.trim();
	var name=document.jobGradeForm.jobGradeName.value.trim();
	var responsibility=document.jobGradeForm.jobGradeResponsibility.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobGradeName_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobGradeDesc_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(responsibility == "" || responsibility == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Rsesp_is_required",user1.getLocale())%><br>";
		showalert = true;
		}	
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}	
	if(responsibility.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.JobGrade.resp",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.jobGradeForm.action = "jobgrade.do?method=updateJobGrade&readPreview=1&id="+'<bean:write name="jobGradeForm" property="jobgradeId"/>';
 document.jobGradeForm.submit();
 //self.parent.location.reload();

 //window.top.hidePopWin();
 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.jobGradeForm.action = "jobgrade.do?method=DeleteJobGrade&id="+'<bean:write name="jobGradeForm" property="jobgradeId"/>';

		  document.jobGradeForm.submit();
	  }
	
}
</script>
<%
 //String saveJobGrade = (String)request.getAttribute("saveJobGrade");
//String updateJobGrade = (String)request.getAttribute("updateJobGrade");
//String deleteJobGrade = (String)request.getAttribute("deleteJobGrade");
%>
<%
String saveJobGrade = (String)request.getAttribute("saveJobGrade");
	
if(saveJobGrade != null && saveJobGrade.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>&nbsp;&nbsp;&nbsp;<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.JobGrade.datasave",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateJobGrade = (String)request.getAttribute("updateJobGrade");
	
if(updateJobGrade != null && updateJobGrade.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>&nbsp;&nbsp;&nbsp;<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.JobGrade.dataupdate",user1.getLocale())%></font></td>
			<td> <!-- <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteJobGrade = (String)request.getAttribute("deleteJobGrade");
	
if(deleteJobGrade != null && deleteJobGrade.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.JobGrade.datadeleted",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>
<%}else{%>	

<body class="yui-skin-sam" onload="init()">

<html:form action="/jobgrade.do?method=saveJobGrade">


<div align="center" class="div">


<%

if ((jobgradeform.getReadPreview()).equals("2")){

%>	

	 <fieldset><legend><%=Constant.getResourceStringValue("admin.JobGrade.details",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>
	
	
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.name",user1.getLocale())%></td>
			<td><%=(jobgradeform.getJobGradeName()==null)?"":jobgradeform.getJobGradeName()%></td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.desc",user1.getLocale())%></td>
			<td><%=(jobgradeform.getJobGradeDesc()==null)?"":jobgradeform.getJobGradeDesc()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.resp",user1.getLocale())%></td>
			<td><%=(jobgradeform.getJobGradeResponsibility()==null)?"":jobgradeform.getJobGradeResponsibility()%></td>
		</tr>
		</table>
		</fieldset>
		
	<%}else{%>	

	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="jobGradeName" size="60" maxlength="300"/></td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.desc",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="jobGradeDesc" cols="60" rows="5"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.resp",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="jobGradeResponsibility" cols="60" rows="5"/></td>
		</tr>
		


	</table>
		<table border="0" width="100%">
		<tr>
			<td><%if (jobgradeform.getJobgradeId()!= 0){%>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
		<%}%>	
		</table>
		
</div>

</html:form>
	
</body>

		<%}%>