
<%@ include file="../../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="userGoalsForm" name="userGoalsForm" type="com.performance.form.UserGoalsForm" />
<%

String sendForReviewSubmit = (String)request.getAttribute("sendForReviewSubmit");
//String delurl = "applicant.do?method=markfordeletionsubmit&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();	

%>

<script language="javascript">

function closewindow(){
	//self.parent.location.reload();
	 	  //  window.top.hidePopWin();
	   parent.parent.GB_hide();
	}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   //window.top.hidePopWin();
		    parent.parent.GB_hide();
		   } 
		}

	function savedata(){

		var alertstr = "";
    var comment = document.userGoalsForm.comment.value;
	
	
	var showalert=false;

	if(comment == "" || comment == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%>\n";
		showalert = true;
		}

	

	

	 if (showalert){
     	alert(alertstr);
        return false;
          }
		
		  document.userGoalsForm.action = "";
	   document.userGoalsForm.submit();
	
	   
		}
</script>
<%	
if(sendForReviewSubmit != null && sendForReviewSubmit.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("goals.send.for.review.to.user.successfully",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>


<html:form action="/usergoal.do?method=sendForReview" >
<table>
<tr>
<td>
<%=Constant.getResourceStringValue("goals.sending.for.review.to",user1.getLocale())%> &nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=userGoalsForm.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=userGoalsForm.getUserName()%></a>
</td>
</tr>
</table>
<table>
<tr>
<td>
<%=Constant.getResourceStringValue("review.note",user1.getLocale())%>
</td>
<td>
<html:textarea property="comment" cols="60" rows="5"/>
</td>
</tr>
<tr>
<td>	
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
</td>
<td>
</td>
</tr>



</table>
</html:form>
<%}%>