<%@ include file="../common/include.jsp" %>
  <%

String datepattern = Constant.getValue("defaultdateformat");
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
JobApplicant applicant = user1.getApplicant();

%>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 800px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
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

<script language="javascript">
function boxOffHover(box) {
box.style.background='white';
}

function boxOnHover(box) {
box.style.background='#f3f3f3';
}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 parent.parent.GB_hide();
	 
	   } 
	}

function savedata1(){
	var alertstr = "";
	var showalert=false; 
	var comment = document.applicantForm.comment.value.trim();

	var comment=document.applicantForm.comment.value.trim();
	var newComment2 = comment.replace(/&/g,'andSymbol');
	var newComment3 = newComment2.replace(/#/g,'hashSymbol');
	
	var newComment = newComment3.replace(/\n/g,'^');
	
	if(comment == "" || comment == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.enter.comment.alert",user1.getLocale())%><br>";
		showalert = true;
		}
	 if (showalert){
	     	alert(alertstr);
	        return false;
	       }
	document.applicantForm.action = "applicantuserops.do?method=addcomment&applicantId=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>&comment="+newComment;
	   document.applicantForm.submit();
}

</script>


<html:form action="/applicantuserops.do?method=addcomment">
<br>

<b><%=Constant.getResourceStringValue("hr.applicant.comments",user1.getLocale())%></b>
<br>
<div class="div">

<br>


<%
List commentList = aform.getCommentList();
if(commentList != null && commentList.size()>0){
	for(int i=0;i<commentList.size();i++){
	ApplicantComment commentdata = (ApplicantComment)commentList.get(i);

	String bytype = "";

	if(commentdata.getBytype() != null){
	bytype = "("+ commentdata.getBytype() + ")";
	}
	
%>
<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
<table  border="0" width="100%">
<tr bgcolor="#f3f3f3">
<td>
<b><%=Constant.getResourceStringValue("hr.applicant.action.added.by",user1.getLocale())%></b>&nbsp;&nbsp; <%=commentdata.getCreatedBy().equals(user1.getFullName()) ?"You":commentdata.getCreatedBy()+" "+bytype%>  &nbsp;&nbsp;&nbsp; <b><%=Constant.getResourceStringValue("hr.applicant.action.added.on",user1.getLocale())%> </b>&nbsp;&nbsp; <%=DateUtil.convertSourceToTargetTimezone(commentdata.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%><br>
</td>
</tr>
<tr>
<td>
<%
String fullComment = commentdata.getComment();

%>
<font size="2"><%=(fullComment == null)?"":StringUtils.doSpecialCharacters(fullComment)%></font>
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

<html:textarea property="comment" cols="98" rows="6"/>

</td>
</tr>
<tr>

<td>

	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata1()" class="button">

 <!-- <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"> -->
 </td>
</tr>
</table>

</div>

</html:form>