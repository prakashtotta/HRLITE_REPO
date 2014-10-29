<%@ include file="../header.jsp" %>
<%@ include file="../greybox.jsp" %>


<%
 String uids = (String)request.getAttribute("uids");
  String sendendorse = (String)request.getAttribute("saveendorsement");
%>

<script language="javascript">
function savecomment(){
	if(document.faceBookUserForm.comment.value == ""){
		alert("Comment is required.");
		return false;
	}
	if(document.faceBookUserForm.comment.value.legth >500){
		alert("Maximum 500 chars allowed.");
		return false;
	}
	document.faceBookUserForm.action = "networkhome.do?method=saveendorsement&uids=<%=uids%>";
	document.faceBookUserForm.submit();
}
</script>

<% if(sendendorse != null && sendendorse.equals("yes")){%>
<font color="green">Endorsement given !!! </font> 
	
<%}else{%>

<html:form action="/networkhome.do?method=endorsementScreen">

<table  border="0" width="100%">
<tr>
<td>
<textarea rows="8" cols="55" name="comment"></textarea>
</td>
</tr>
<tr>
<td>
<input type="button" name="save" value="Endorse" onClick="savecomment()">
</td>
</tr>

</table>
</html:form>

<%}%>