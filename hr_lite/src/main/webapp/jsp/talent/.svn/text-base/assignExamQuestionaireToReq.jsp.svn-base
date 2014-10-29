

<script language="javascript">

function assignExamScr(){

	var tu = "jobreq.do?method=assignExamScreen&jobreqid="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
	  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=250");
		

}
function editAssignExamScr(){


		var tu = "jobreq.do?method=editExamScreen&jobreqid="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
	   window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=250");
		

}

function examdetails(examId){
	
	//alert("Exam Id : "+examId);
	var tu = "mockquestionset.do?method=MockQuestionSetDetails&catid="+examId+"&readonlymode=yes";

	  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
	  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=200");
		
   	
}


function assignQuestionnaireScr(){
	var tu = "jobreq.do?method=assignQuestionnaireScreen&jobreqid="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
	  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=250");

}
function editQuestionnaireScr(){


	var tu = "jobreq.do?method=editQuestionnaireScreen&jobreqid="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
   window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=250");
	

}
function questionnairedetails(questiongroupId){
	
	//alert(questiongroupId);
	  var tu = "questiongroup.do?method=questionnaireDetailspage&questiongroupId="+questiongroupId;
	  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
	  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=500");
		
   	
}


</script>
<fieldset><legend><b><%=Constant.getResourceStringValue("hr.applicant.assign.exam1",user1.getLocale())%></b></legend>

<table border="0" width="100%">

<%if(jobreqform.getMockexamsetId() == 0){ %>
	<tr>
	
	<td>
		
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%>" onClick="assignExamScr()" class="button">
	 	
	  </td>
	</tr>
<%}else{%>

	<tr>
	<td><%=Constant.getResourceStringValue("hr.applicant.exam",user1.getLocale())%></td><td><%=Constant.getResourceStringValue("hr.applicant.exam.passpercentage",user1.getLocale())%></td><td><%=Constant.getResourceStringValue("aquisition.applicant.your_comments",user1.getLocale())%></td>
	
	</tr>
	<tr>
	<td width="20%"><a href="#" onclick="examdetails('<%=jobreqform.getMockexamsetId()%>');return false;"><%=jobreqform.getExamName()%></a></td><td width="15%"><%=jobreqform.getPassPercentage()%></td><td width="40%"><%=jobreqform.getMcomckexamsetcomment()%></td>
 	<td>
 	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>" onClick="editAssignExamScr()" class="button">
 	</td>
	</tr>
<%} %>
</table>

</fieldset>

<br>
<br>

<fieldset><legend><b><%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaire1",user1.getLocale())%></b></legend>

<table border="0" width="100%">
<%if(jobreqform.getQuestiongroupId() == 0){ %>
<tr>

<td width="70%">
	
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaire",user1.getLocale())%>" onClick="assignQuestionnaireScr()" class="button">

 </td>
</tr>
<%}else{%>
	<tr>
	<td><%=Constant.getResourceStringValue("hr.applicant.Questionnaire",user1.getLocale())%></td><td><%=Constant.getResourceStringValue("hr.applicant.Questionnaire.heading",user1.getLocale())%></td><td></td>
	
	</tr>
	<tr>
	<td width="20%"><a href="#" onclick="questionnairedetails('<%=jobreqform.getQuestiongroupId()%>');return false;"><%=jobreqform.getQuestiongroupName()%></a></td><td width="55%"><%=jobreqform.getQuestiongroupcomment()%></td>
	<td>
 	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>" onClick="editQuestionnaireScr()" class="button">
 	</td>
 	<td>

<%} %>
</table>
</fieldset>