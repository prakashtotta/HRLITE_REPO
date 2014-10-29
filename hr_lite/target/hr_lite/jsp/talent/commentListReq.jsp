<%@ include file="../common/include.jsp" %>
<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<%

String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}


%>



<script language="javascript">

function boxOffHover(box) {
box.style.background='white';
}

function boxOnHover(box) {
box.style.background='#f3f3f3';
}
function discard(){
	document.jobRequisitionForm.comment.value="";
	setTimeout ( "document.jobRequisitionForm.comment.focus(); ", 200 );
}

function savecomment(){
	document.jobRequisitionForm.action = "jobreq.do?method=addcomment&uuid=<%=jobreqform.getUuid()%>";
	document.jobRequisitionForm.submit();
}

</script>


<html:form action="/jobreq.do?method=addcomment">


<fieldset><legend><b>Comments</b></legend>




<%
List commentList = jobreqform.getCommentList();
if(commentList != null && commentList.size()>0){
	for(int i=0;i<commentList.size();i++){
	RequisitionComments reqcomment = (RequisitionComments)commentList.get(i);
%>
<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
<table  border="0" width="100%">
<tr bgcolor="#f3f3f3">
<td>

<b><%=Constant.getResourceStringValue("hr.applicant.action.added.by",user1.getLocale())%></b>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=reqcomment.getCreatedById()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=reqcomment.getCreatedByName()%></a> &nbsp;

<b><%=Constant.getResourceStringValue("hr.applicant.action.added.on",user1.getLocale())%> </b>&nbsp;<%=DateUtil.convertSourceToTargetTimezone(reqcomment.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>&nbsp;&nbsp; <b><%=(reqcomment.getCommentType() == null)?"":reqcomment.getCommentType()%></b>

</td>
</tr>
<tr>
<td>
<%
String fullComment = reqcomment.getComment();

%>
<font size="2"><%=(fullComment == null)?"":StringUtils.doSpecialCharacters(fullComment)%> </font>

</td>
<td> 

</td>
</tr>
</table>
</div>

<%
}
}
%>






<table  border="0" width="100%">

<tr>
<td>

<html:textarea property="comment" cols="80" rows="6"/>

</td>
</tr>

<tr>
<td>
	<div id="submitbutton" style=display:block>
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savecomment()" class="button">
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="reset()" class="button">
	</div>

 </td>
</tr>
</table>

</fieldset>

</html:form>