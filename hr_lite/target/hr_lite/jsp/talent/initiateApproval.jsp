<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>
<%
String jobreqId = (String)request.getParameter("jobreqId");
%>
<html>

<script language="javascript">
var returnVal = "something11";
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   window.top.hidePopWin();
	   }
	   
	   //ShowMessage();
	}



function savedata(){
	
	  document.jobRequisitionForm.action = "<%=request.getContextPath()%>/jobreq.do?method=initiateApproval&jobreqId=<%=jobreqId%>";
   document.jobRequisitionForm.submit();
window.parent.reload()  
 self.close();
	
   
	}



</script>

<html:form action="/jobreq.do?method=initiateApproval" >


	<table border="0" width="100%" class="div">
	<font color = red ><html:errors /> </font>
	<tr>
			<td><%=Constant.getResourceStringValue("Requisition.initiate.approval",user1.getLocale())%></td>
			<td></td>
		</tr>
	  
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.your_comments",user1.getLocale())%> :<BR></td>
<td><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA></td>

		</tr>
		
		
		
		<tr>
			<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant.Initiate",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>

	</table>


</html:form>
