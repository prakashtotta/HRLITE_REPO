
<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%

String markfordeletion = (String)request.getAttribute("markfordeletion");
String applicantids = (String)request.getAttribute("applicantids");
String delurl = "applicant.do?method=markfordeletionbulksubmit&applicantids="+applicantids;	

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
		
		  document.applicantForm.action = "<%=delurl%>";
	   document.applicantForm.submit();
	
	   
		}
</script>
<%	
if(markfordeletion != null && markfordeletion.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.mark.delete.msg",user1.getLocale())%></font></td>
<td><!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>

		</tr>
		
	</table>
</div>

<%}else{%>


<form name="applicantForm" method="POST" action="applicant.do">
<table>
<tr>
<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%> :</td><td><TEXTAREA NAME="comment" COLS=65 ROWS=8></TEXTAREA></td></tr>
<tr><td>	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	</td></tr><td></td>
</table>
</form>
<%}%>