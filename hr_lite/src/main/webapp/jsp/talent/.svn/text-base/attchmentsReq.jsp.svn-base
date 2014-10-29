<b><%=Constant.getResourceStringValue("Requisition.Upload.attachments",user1.getLocale())%></b>
<br><br>
<div class="div">

<table>
<tr>
<td></td><td></td>
</tr>
<tr>
			
			<td>
			<%=Constant.getResourceStringValue("Requisition.Attachement.details",user1.getLocale())%> : <input type="text" class="text" name="attahmentdetails" style="width:280px;" value="">
			<html:file property="attachmentdata"/> 
			<%=Constant.getResourceStringValue("Requisition.Is.visible.in.JobDetails",user1.getLocale())%>	<%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="visibleInJobDetails" maxlength="10" id="1" value="Y">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="visibleInJobDetails" checked="true" id="0" value="N">
			<%  if(jobreqform.getIsapprovalInitiated() == 1 && !isEditReqAfterPublishPermission){ %>
			<a class="button" href="#" onClick="approvalinitiated()"><%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%></a>
			<%}else{ %>
			<a class="button" href="#" onClick="addattachment()"><%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%></a>
			<%} %>
			</td>
		</tr>


</table>
</div>
<br>
<%
List attachmentList = jobreqform.getAttachmentList();

if(attachmentList != null && attachmentList.size()>0){
	%>
<b><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></b>
<br><br>

<div class="div">
	<table border="0" width="100%"> 

		<tr>
<td></td>
<td>

	<table border="0" width="100%" CELLPADDING=6>
	<tr>

	<td><b><%=Constant.getResourceStringValue("Requisition.Attachement.details",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.file.name",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.Visible_in_job_details",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.added.by",user1.getLocale())%></b></td>
	<td>&nbsp;&nbsp<b> <%=Constant.getResourceStringValue("Requisition.added.date",user1.getLocale())%></b></td>
	</tr>

<%
		for(int i=0;i<attachmentList.size();i++){
		RequistionAttachments attach = (RequistionAttachments)attachmentList.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e6e6e6"; else bgcolor ="#cacaca";

%>
<tr bgcolor=<%=bgcolor%>>
<td><%=(attach.getAttahmentdetails()==null)?"":attach.getAttahmentdetails()%></td>
<td>

<a href="jobreq.do?method=attachementdetails&attachmentid=<%=attach.getUuid()%>"><%=attach.getAttahmentname()%></a>
</td>

<td align="center">
<%=(attach.getVisibleInJobDetails()!=null && attach.getVisibleInJobDetails().equals("Y"))?"Yes":"No"%>
</td>
<td><%=attach.getCreatedBy()%></td>
<td><%=DateUtil.convertSourceToTargetTimezone(attach.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
<%//  if(jobreqform.getIsapprovalInitiated() == 1){ %>
<!--  <td width="50px"><a href="#" onClick="approvalinitiated()"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" title="<%=Constant.getResourceStringValue("Requisition.delete.attachment",user1.getLocale())%>" height="20"  width="19"/></a></td>-->
<%//}else{ %>
<td>
<a href="#" onClick="deleteattachement('jobreq.do?method=deleteattachment&jobreqId=<%=jobreqform.getJobreqId()%>&uuid=<%=attach.getUuid()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" title="<%=Constant.getResourceStringValue("Requisition.delete.attachment",user1.getLocale())%>" height="20"  width="19"/></a>
</td>
<%//} %>

</tr>
<%}%>
</table>

</td>
</tr>
</table>
</div>
<%}%>