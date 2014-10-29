<%@ include file="../common/yahooincludes.jsp" %>

<bean:define id="aform" name="bulkUploadTaskForm" type="com.form.BulkUploadTaskForm" />

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String assigneddate = null;

BulkUploadTask task = aform.getTask();
String cdate ="";
String udate ="";
if(task.getCreatedDate() != null){
			cdate=DateUtil.convertDateToStringDate(task.getCreatedDate(),datepattern);
		}

if(task.getUpdatedDate() != null){
			udate=DateUtil.convertDateToStringDate(task.getUpdatedDate(),datepattern);
		}
%>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 700px;
	border: 1px solid #999;
	padding: 10px;
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}

</style>

<fieldset><legend><strong><%=Constant.getResourceStringValue("task.graybox.jobdetails",user1.getLocale())%></strong></legend>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("job.jobid",user1.getLocale())%> : <span id="modelDescription"><%=task.getBulkuploadtaskid()%></span></td>
<td><%=Constant.getResourceStringValue("job.jobtype",user1.getLocale())%> : <%=task.getTasktype()%></span></td>
</tr>
<% if(task.getJobreqId()>0){%>
<tr>
<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%> : <span id="modelDescription"><%=task.getJobTitle()%></span></td><td></td>
</tr>
<%}%>
<tr>
<td><%=Constant.getResourceStringValue("task.addedby",user1.getLocale())%> : <span id="modelDescription"><%=task.getCreatedBy()%></span></td><td><%=Constant.getResourceStringValue("task.addedon",user1.getLocale())%> : <span id="modelDescription"><%=cdate%></span></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("task.Job_executed_on",user1.getLocale())%> : <span id="modelDescription"><%=udate%></span></td><td><%=Constant.getResourceStringValue("task.status",user1.getLocale())%> : <span id="modelDescription"><b> <%=task.getStatus()%></span></b></td>
</tr>
<tr><td>

</td>
<% if(task.getTasktype() != null && (task.getTasktype().equals(Common.EXPORT_APPLICANTS) || task.getTasktype().equals(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS) || task.getTasktype().equals(Common.EXPORT_MYREQUISTIONS_SEARCH_RESULTS)|| task.getTasktype().equals(Common.EXPORT_APPLICANTS_SEARCH) || task.getTasktype().equals(Common.EXPORT_MYREQUISTIONS_HIRING_MANAGER_SEARCH_RESULTS) || task.getTasktype().equals(Common.EXPORT_MYREQUISTIONS_RECRUITER_SEARCH_RESULTS)||task.getTasktype().equals(Common.EXPORT_AGENCY_REDEMPTION_SEARCH_RESULTS)||task.getTasktype().equals(Common.EXPORT_REFERAL_REDEMPTION_SEARCH_RESULTS))){%>

<% if(task.getStatus() != null && task.getStatus().equals(Common.BULK_UPLOAD_STATUS_COMPLETED)){%>

<tr>
<td>
<a href="<%=request.getContextPath()%>/download/file?filename=<%=task.getUploadedFileName()%>"><%=Constant.getResourceStringValue("task.download",user1.getLocale())%>  <%=task.getUploadedFileName()%></a>
</td><td></td>
</tr>

<%}}else if (task.getTasktype() != null && (task.getTasktype().equals(Common.BULK_UPLOAD_USERS))){
String successFile = request.getContextPath()+"/download/file?filename="+task.getBulkuploadtaskid()+"_success.txt";
String failFile = request.getContextPath()+"/download/file?filename="+task.getBulkuploadtaskid()+"_error.txt";
%>
<tr>
<td><%=Constant.getResourceStringValue("task.uploadfilename",user1.getLocale())%> : <span id="modelDescription"><%=task.getUploadedFileName()%></span></td><td></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("task.Sucess_files_list",user1.getLocale())%> : 
<a href="<%=successFile%>">success.txt</a></td>
<td><%=Constant.getResourceStringValue("task.Fail_files_list",user1.getLocale())%> :<a href="<%=failFile%>">error.txt</a> </td>
</tr>

<%}else{%>
<tr>
<td><%=Constant.getResourceStringValue("task.uploadfilename",user1.getLocale())%> : <span id="modelDescription"><%=task.getUploadedFileName()%></span></td><td><%=Constant.getResourceStringValue("task.filelist",user1.getLocale())%> : <span id="modelDescription"><%=(task.getFilesList() == null)?"":task.getFilesList()%></span></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("task.Sucess_files_list",user1.getLocale())%> : <span id="modelDescription"><%=(task.getSuccessfilesList() == null)?"":task.getSuccessfilesList()%></td><td><%=Constant.getResourceStringValue("task.Fail_files_list",user1.getLocale())%> : <span id="modelDescription"><%=(task.getFailfilesList() == null)?"":task.getFailfilesList()%></span></td>
</tr>
<%}%>

<% if(task.getErrorDesc() != null){%>
<tr>
<td><%=Constant.getResourceStringValue("task.Error",user1.getLocale())%> : <span id="modelDescription"><%=task.getErrorDesc()%></span></td><td></td>
</tr>
<%}%>
</table>
</fieldset>
