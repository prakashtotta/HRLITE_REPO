<%@ include file="../common/yahooincludes.jsp" %>
<%
User user = (User)request.getSession().getAttribute(Common.USER_DATA);
%>
<script language="javascript">

</script>

<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<form name ="applicantForm" action="/applicant.do">
<b><%=Constant.getResourceStringValue("aquisition.applicant.References",user.getLocale())%></b> <br><br>
	<table border="0" width="100%">

		<tr>
<td></td>
<td>
<%
List refList = form.getRefrenceslist();

if(refList.size()>0){
	%>
	
<%
		for(int i=0;i<refList.size();i++){
		ApplicantReferencee ref = (ApplicantReferencee)refList.get(i);
		

%>
<%
String assignedtourl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+ref.getAssignedTo()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+ref.getAssignedToName()+"</a>";
	%>
<fieldset><legend><%=Constant.getResourceStringValue("applicant.Refrences.Reference",user.getLocale())%> <%=i+1%></legend>

<table border="0" width="500" >
<tr>
<td width="250" ><%=Constant.getResourceStringValue("applicant.Refrences.name",user.getLocale())%> : <%=ref.getReferenceeName()%></td><td>Organization: <%=ref.getReferenceeOrganization()%></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("applicant.Refrences.Designation",user.getLocale())%> :<%=ref.getReferenceeDesignation()%></td><td>Relation with applicant :<%=ref.getReferenceeRelation()%></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("applicant.Refrences.Phone",user.getLocale())%> :  <%=ref.getReferenceePhone()%></td>
<td><%=Constant.getResourceStringValue("applicant.Refrences.Email",user.getLocale())%> :<%=ref.getReferenceeEmail()%></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("applicant.Refrences.Note",user.getLocale())%> :  <%=ref.getNote()%></td><td></td>
</tr>
<% if(ref.getIsVerificationDone() != null && ref.getIsVerificationDone().equals("Y")){
String st = "";
if(ref.getStatus() != null && ref.getStatus().equals("goodtogo")){
	st = "<font color=green> "+" Good to Go "+"</font>";
}
if(ref.getStatus() != null && ref.getStatus().equals("reject")){
	st = "<font color=red>"+"Rejected"+"</font>";
}
%>
<tr>
<td><%=Constant.getResourceStringValue("applicant.Refrences.Is_verification_done",user.getLocale())%> : <%=ref.getIsVerificationDone()%>  &nbsp; <%=st%> </td> <td><%=Constant.getResourceStringValue("applicant.Refrences.Verified_on",user.getLocale())%> : <%=(ref.getUpdatedDate() != null)? DateUtil.convertSourceToTargetTimezone(ref.getUpdatedDate(), user.getTimezone().getTimezoneCode(), user.getLocale()) : ""%></td> 
</tr>
<tr>

<td><%=Constant.getResourceStringValue("applicant.Refrences.Verified_by",user.getLocale())%> :<%=assignedtourl%></td>
<td><%=Constant.getResourceStringValue("applicant.Refrences.Verification_comment",user.getLocale())%> :<%=(ref.getReferenceeFeedback() == null)? "":StringUtils.doSpecialCharacters(ref.getReferenceeFeedback())%> <br>

<div style="margin: 6px 6px 6px 0px;" class="buttonwrapper">
<a class="squarebutton" href="#" onClick="javascript:viewreferencefeedbacks('<%=request.getContextPath()%>/reference.do?method=referencecheckfeedbacks&applicantId=<%=ref.getApplicantId()%>&referenceId=<%=ref.getApplicantReferenceId()%>')"><span><%=Constant.getResourceStringValue("applicant.Refrences.view_feedbacks",user.getLocale())%></span></a> </div>
</td>

</tr>
<%}else {%>
<tr><td><%=Constant.getResourceStringValue("applicant.Refrences.Reviewer",user.getLocale())%> : <%=assignedtourl%></td>
<td>
<div style="margin: 6px 6px 6px 0px;" class="buttonwrapper">
<a class="squarebutton" href="#" onClick="javascript:editreference('<%=request.getContextPath()%>/reference.do?method=editreference&referenceId=<%=ref.getApplicantReferenceId()%>')">
<span><%=Constant.getResourceStringValue("hr.button.edit",user.getLocale())%></span></a> </div>

<% if(ref.getQuestiongroupid()>0 && ref.getIsVerificationDone().equals("N")){%>

<%if(ref.getAssignedTo() == user.getUserId()){%>



<div style="margin: 6px 6px 6px 0px;" class="buttonwrapper">
<a class="squarebutton" href="#" onClick="javascript:startreferenceverification('<%=request.getContextPath()%>/reference.do?method=startrefcheck&applicantId=<%=ref.getApplicantId()%>&questiongroupid=<%=ref.getQuestiongroupid()%>&referenceId=<%=ref.getApplicantReferenceId()%>')">
<span><%=Constant.getResourceStringValue("aquisition.applicant.Startverification",user.getLocale())%></span></a> </div>


<%}%>

<%}%>


</td>
<%}%>

</table>
</fieldset>
<br>
<%}%>

<%}else {%>
<%=Constant.getResourceStringValue("applicant.Refrences.norefmessage",user.getLocale())%>

<%}%>

</td>
</tr>

<tr>
<td></td>
<td>
<%
String addrefurl = request.getContextPath()+"/reference.do?method=addreferencescr&applicantId="+form.getApplicantId();
%>
<div style="margin: 6px 6px 6px 0px;" class="buttonwrapper">
<a class="squarebutton" href="#" onClick="javascript:addreferencedata1('<%=addrefurl%>')">
<span><%=Constant.getResourceStringValue("applicant.Refrences.add_references",user.getLocale())%></span></a> </div>
</td>
</tr>
</table>

</form>

