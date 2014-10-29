<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");


%>

<script language="javascript">
function closerefresh(){

	window.opener.location.reload(true);

  window.close();
	
}
</script>
<%
String attachmentdeleted = (String)request.getAttribute("attachmentdeleted");
%>
<%
if(attachmentdeleted != null && attachmentdeleted.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green">Attachment deleted successfully.</font></td>
			<td> <a href="#" onclick="closerefresh()">Close window</a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red">Attachment not deleted.</font></td>
			<td> <a href="#" onclick="closerefresh()">Close window</a></td>
		</tr>
		
	</table>
</div>
<%}%>