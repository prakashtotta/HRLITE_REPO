
<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<%
String applicantId = (String)request.getAttribute("applicantId");
String offerapproverId = (String)request.getAttribute("offerapproverId");
String type = (String)request.getAttribute("type");
String commentsadded = (String)request.getAttribute("commentsadded");
String commentsrejected = (String)request.getAttribute("commentsrejected");

String url = "";
String title = "";
if(type != null && type.equals("approve")){
	url = "applicant.do?method=approveoffer&applicantId="+applicantId+"&offerapproverId="+offerapproverId;
	title = "Approve";
}else{
   	url = "applicant.do?method=rejectoffer&applicantId="+applicantId+"&offerapproverId="+offerapproverId;
	title = "Reject";

}

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
    var comment = document.applicantForm.comment.value;
	
	
	var showalert=false;

	if(comment == "" || comment == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%>\n";
		showalert = true;
		}

	

	

	 if (showalert){
     	alert(alertstr);
        return false;
          }
		
		  document.applicantForm.action = '<%=url%>';
	   document.applicantForm.submit();
	
	   
		}
</script>
<%	
if(commentsadded != null && commentsadded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_approved_successfully",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>

		</tr>
		
	</table>
</div>

<%}else{%>
<%	
if(commentsrejected != null && commentsrejected.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Approve_rejected_successfully",user1.getLocale())%></font></td>
		<td><!--<a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>

		</tr>
		
	</table>
</div>

<%}else{%>

<form name="applicantForm" method="POST" action="applicant.do">
<br>
<table>
<tr>
<td>
<% if(form.getSalaryPlan() != null && !StringUtils.isNullOrEmpty(form.getOfferedctc()) && form.getSalaryPlan().getToRangeAmount() < new Long(form.getOfferedctc()).longValue()){%>
			<font color="red">
			
			<%=Constant.getResourceStringValue("admin.offeredctc.exceed.from.salaryplan",user1.getLocale())%> <br>
			<%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%> #
			<%=form.getSalaryPlan().getFromRangeAmount()%> -- <%=form.getSalaryPlan().getToRangeAmount()%>  <%=form.getSalaryPlan().getCurrencyCode()%>
			</font>
			<br>
			<%=Constant.getResourceStringValue("aquisition.applicant.Offered_CTC",user1.getLocale())%> # 
			<bean:write name="applicantForm" property="offeredctc"/> &nbsp;<bean:write name="applicantForm" property="offeredctccurrencycode"/>
<%}%>
</td>
</tr>
</table>
<table class="div">
<tr>
<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%> :</td><td><TEXTAREA NAME="comment" COLS=65 ROWS=8></TEXTAREA></td></tr>
<tr><td colspan="2">	
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	</td></tr>
</table>
</form>
<%}}%>