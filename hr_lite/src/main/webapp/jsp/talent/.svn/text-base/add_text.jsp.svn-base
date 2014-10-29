<%@ include file="../common/includejava.jsp" %>
 <bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%

String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
System.out.println("login user userId : "+user1.getUserId());
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String editcomment = (String)request.getParameter("editcomment");
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
	//  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	//  if (doyou == true){
	//	 parent.parent.GB_hide();
	 
	 //  } 
	document.applicantForm.comment.value="";
	setTimeout ( "document.applicantForm.comment.focus(); ", 200 );
	}

function savecomment(){
	
	//document.applicantForm.action = "applicant.do?method=addcomment&applicantId=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>";
	//   document.applicantForm.submit();


	   $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

dataString = $("#commentadd").serialize();
$.ajax({
	type: 'POST',
  url: "applicant.do?method=addcommenttreeajax&applicantId=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>",
data: dataString,
  success: function(data){
  $('#commentspan').html(data);
	completeajx();
  }
});

	   }

function completeajx(){
	 $.unblockUI();
}

</script>

<span id="commentspan">
<html:form action="/applicant.do?method=addcomment" styleId="commentadd">
<br>

<fieldset><legend><%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%></legend>




<%
int totalCommentsLoginUser = 0;
totalCommentsLoginUser = BOFactory.getApplicantBO().getCountOfCommentsByLoginUser(aform.getApplicantId(),user1.getUserId());
System.out.println("totalCommentsLoginUser : "+totalCommentsLoginUser);
List commentList = aform.getCommentList();
long commentId;
int countComments= 0;
if(commentList != null && commentList.size()>0){
	for(int i=0;i<commentList.size();i++){
	ApplicantComment commentdata = (ApplicantComment)commentList.get(i);
	
	commentId=commentdata.getApplicantcommenttId();
	
	
	String bytype = "";

		if(commentdata.getBytype() != null){
		bytype = "("+ commentdata.getBytype() + ")";
		}
%>
<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
<table  border="0" width="100%">
<tr bgcolor="#f3f3f3">
<td>

<b><%=Constant.getResourceStringValue("hr.applicant.action.added.by",user1.getLocale())%></b>&nbsp;<%=commentdata.getCreatedBy()%> <%=bytype%> &nbsp;

<b><%=Constant.getResourceStringValue("hr.applicant.action.added.on",user1.getLocale())%> </b>&nbsp;<%=DateUtil.convertSourceToTargetTimezone(commentdata.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>&nbsp;

<%if(commentdata.getUpdatedDate() != null){ %>
<b><%=Constant.getResourceStringValue("hr.applicant.action.updated.on",user1.getLocale())%> </b>&nbsp;<%=DateUtil.convertSourceToTargetTimezone(commentdata.getUpdatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>&nbsp;	
<%} %>


	<%
		if(commentdata.getIsVisibleToApplicant()!= null && commentdata.getIsVisibleToApplicant().equals("Y")){
			if(! StringUtils.isNullOrEmpty(commentdata.getBytype()) && ! commentdata.getBytype().equals(Common.COMMENT_TYPE_APPLICANT)){
	%> 
		<font color="green"><%=Constant.getResourceStringValue("hr.applicant.comment.visible.to.applicant",user1.getLocale())%> </font>
	 
	<%		}
		}else if(commentdata.getIsVisibleToApplicant()!= null && commentdata.getIsVisibleToApplicant().equals("R")){
	%>
    
    <% if(! StringUtils.isNullOrEmpty(commentdata.getBytype()) && !(commentdata.getBytype().equals(Common.COMMENT_TYPE_AGENCY) || commentdata.getBytype().equals(Common.COMMENT_TYPE_EMPLOYEE_REF))){%>
<font color="green"><%=Constant.getResourceStringValue("hr.applicant.comment.visible.to.referrer",user1.getLocale())%> </font>
	<%}%>

	<%}%>


</td>
</tr>
<tr>
<td>
<%
String fullComment = commentdata.getComment();

%>
<font size="2"><%=(fullComment == null)?"":StringUtils.doSpecialCharacters(fullComment)%> </font>

</td>
<td> 
<%if(user1.getUserId() == commentdata.getByUserId() ){ 
	countComments++;
		if(totalCommentsLoginUser == countComments){
%>
		<input type="hidden" id="applicantcommenttId" name="applicantcommenttId" value="<%=commentId%>"/>
	
	     <input type="hidden" id="editcommenthidden" name="editcommenthidden" value="<%=(commentdata.getComment()==null)?"":commentdata.getComment()%>"/>

	<a href="#" onClick="editcomment('<%=commentdata.getIsVisibleToApplicant()%>'); return false"><img src="jsp/images/EditIcon.gif" border="0" alt="edit comment" title="edit comment" height="20"  width="19"/></a>
	
	<%  }
  } %>
</td>
</tr>
</table>
</div>
<%
	}
%>

<%
}

%>






<table  border="0" width="100%">

<tr>
<td>

<html:textarea property="comment" cols="80" rows="6"/>

</td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("hr.applicant.comment.visible.to",user1.getLocale())%> &nbsp;&nbsp;

			<html:select property="commentVisible">
				<bean:define name="applicantForm" property="commentVisibleList" id="commentVisibleList" />

            <html:options collection="commentVisibleList" property="key"  labelProperty="value"/>
			</html:select>

</td>
</tr>
<tr>
<td>
	<div id="submitbutton" style=display:block>
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savecomment()" class="button">
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="reset()" class="button">
	</div>
	<div id="updatebutton" style=display:none>

	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatecomment()" class="button">
 	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="reset()" class="button">
 	</div>

 	
 </td>
</tr>
</table>

</fieldset>

</html:form>
</span>