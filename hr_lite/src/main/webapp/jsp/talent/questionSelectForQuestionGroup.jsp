<%@ include file="../common/include.jsp" %>
<%
String createnewquestion = (String)request.getAttribute("createnewquestion");
String editquestion = (String)request.getAttribute("editquestion");
//String saveQuestion = (String)request.getAttribute("saveQuestion");
	if(createnewquestion != null && createnewquestion.equals("yes")){%>







<div class="div">

<table border="0" bordercolor="#999999" cellpadding="0" cellspacing="0">
<tr>
<td valign="top" width="20"></td>
<td valign="top" width="500">
<table border="0" cellpadding="0" cellspacing="0">


<img src="jsp/images/spacer.gif" border="0" width="20">
<iframe src="questiongroup.do?method=createQuestionpage&quesgroupId=<%=questionsgroupform.getQuestiongroupId()%>" style="width:560px;height:360px;border:2px ridge;">
  <p>Your browser does not support iframes.</p>
</iframe> 


</table>
	
	
</td>
</tr>


</table>
</td>






	<%}else if(editquestion != null && editquestion.equals("yes")){%>

<table border="0" bordercolor="#999999" cellpadding="0" cellspacing="0">
<tr>
<td valign="top" width="20"></td>
<td valign="top" width="500">
<table border="0" cellpadding="0" cellspacing="0">
 

      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="290" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="200" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="0"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>
<img src="jsp/images/spacer.gif" border="0" width="20">
<iframe src="questiongroup.do?method=editQuestion&questionid=<%=questionsgroupform.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname=<%=questionsgroupform.getQuestiongroupName()%>" style="width:560px;height:350px;border:2px ridge;">
  <p>Your browser does not support iframes.</p>
</iframe>
</tr>
</table>
	
	
</td>
</tr>


</table>
</td>



<%}else{%>

<table border="0" bordercolor="#999999" cellpadding="0" cellspacing="0">
<tr>
<td valign="top" width="20"></td>
<td valign="top" width="500">
<table border="0" cellpadding="0" cellspacing="0">
 

     
 </tr>
 <img src="jsp/images/spacer.gif" border="0" width="20">
<iframe src="questiongroup.do?method=backAddQuestionpage1&questiongroupId=<%=questionsgroupform.getQuestiongroupId()%>" style="width:560px;height:200px;border:2px ridge;" >
  <p>Your browser does not support iframes.</p>
</iframe>
</tr>
</table>
	
	
</td>
</tr>


</table>
</div>
</td>



<%}%>