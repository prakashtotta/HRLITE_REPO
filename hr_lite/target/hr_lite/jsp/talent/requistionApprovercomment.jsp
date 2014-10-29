
<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
String jobreqId = (String)request.getAttribute("jobreqId");
String jbTmplApproverId = (String)request.getAttribute("jbTmplApproverId");
String type = (String)request.getAttribute("type");
String approvedcomment = (String)request.getAttribute("approvedcomment");
String rejectedcomment = (String)request.getAttribute("rejectedcomment");

String url = "";
String title = "";
if(type != null && type.equals("approve")){
	url = "jobreq.do?method=approveRejectReqTask&jobreqId="+jobreqId+"&jbTmplApproverId="+jbTmplApproverId+"&type="+type;	
	title = Constant.getResourceStringValue("Requisition.approve",user1.getLocale());
}else{
   	url = "jobreq.do?method=approveRejectReqTask&jobreqId="+jobreqId+"&jbTmplApproverId="+jbTmplApproverId+"&type="+type;	
	title = Constant.getResourceStringValue("Requisition.reject",user1.getLocale());

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
    var comment = document.jobRequisitionForm.comment.value;
	
	
	var showalert=false;

	if(comment == "" || comment == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%>\n";
		showalert = true;
		}

	

	

	 if (showalert){
     	alert(alertstr);
        return false;
          }
		
		  document.jobRequisitionForm.action = '<%=url%>';
	   document.jobRequisitionForm.submit();
	
	   
		}
</script>
<%	
if(approvedcomment != null && approvedcomment.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("requisition.approved_successfully",user1.getLocale())%></font></td>
			<td><a href="#" onClick="closewindow();return false;"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<%	
if(rejectedcomment != null && rejectedcomment.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("requisition.rejected_successfully",user1.getLocale())%></font></td>
			<td><a href="#" onClick="closewindow();return false;"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<b><%=title%></b>
<form name="jobRequisitionForm" method="POST" action="jobreq.do">
<table>
<tr>
<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%> :</td><td><TEXTAREA NAME="comment" COLS=65 ROWS=8></TEXTAREA></td></tr>
<tr><td>	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	</td></tr><td></td>
</table>
</form>
<%}}%>