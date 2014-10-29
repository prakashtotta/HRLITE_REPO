
<%@ include file="../common/include.jsp" %>
 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="form" name="referenceForm" type="com.form.ReferenceForm" />

<script language="javascript">
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	    self.parent.location.reload();
	 parent.parent.GB_hide();
	   }
	   
	   //ShowMessage();
	}



function savedata(){

	var alertstr = "";
    
	var ischecked=false;
	var showalert=false;
for (var i=0; i < document.referenceForm.status.length; i++)
   {
   if (document.referenceForm.status[i].checked)
      {
    var rad_val = document.referenceForm.status[i].value;
	ischecked=true;
      }
   }
	
	if(ischecked == false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Questions.statusalert",user1.getLocale())%><BR>";
		showalert = true;
		}

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.referenceForm.action = "reference.do?method=saverefcheck&applicantId=<%=form.getApplicantId()%>&questiongroupid=<%=form.getQuestiongroupid()%>&referenceId=<%=form.getApplicantReferenceId()%>";
   document.referenceForm.submit();
  
	}


	



function closewindow(){
 self.parent.location.reload();
	 parent.parent.GB_hide();
 
	  
	}


	


</script>


<%
String refanswersaved = (String)request.getAttribute("refanswersaved");

if(refanswersaved != null && refanswersaved.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><%=Constant.getResourceStringValue("admin.Questions.Ans_save_msg",user1.getLocale())%></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
List qnsset = null;
QuestionGroups qnsgroup = form.getQnsgroup();
if(qnsgroup != null){
qnsset = qnsgroup.getQuestions();
}
%>

<body>
<html:form action="/reference.do?method=saverefcheck">


<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	
	<% if(qnsset != null){
		int i=1;
%>
<tr>
			<td><%=Constant.getResourceStringValue("admin.Questions",user1.getLocale())%></td>
			
		</tr>
<%	
	for(int j=0;j<qnsset.size();j++){
		Questions qns = (Questions)qnsset.get(j);

%>

<tr>
			<td><b><%=Constant.getResourceStringValue("admin.Questions.Question_no",user1.getLocale())%> : <%=i%> &nbsp;&nbsp;<%=qns.getQuestionName()%> </b><br>

			<% if(qns.getQuestionType().equals(Common.QUESTION_TYPE_TEXT)){%>

<TEXTAREA NAME="qns_<%=qns.getQuestionId()%>" COLS=60 ROWS=3></TEXTAREA>


			<%}%>

		  <% if(qns.getQuestionType().equals(Common.QUESTION_TYPE_RADIO)){%>

<% if(qns.getOptionValue1() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue1()%>"> <%=qns.getOptionValue1()%>
<%}%>
<% if(qns.getOptionValue2() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue2()%>"> <%=qns.getOptionValue2()%>
<%}%>
<% if(qns.getOptionValue3() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue3()%>"> <%=qns.getOptionValue3()%>
<%}%>
<% if(qns.getOptionValue4() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue4()%>"> <%=qns.getOptionValue4()%>
<%}%>
<% if(qns.getOptionValue5() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue5()%>"> <%=qns.getOptionValue5()%>
<%}%>



			<%}%>

			</td>
			
		</tr>


<%
	i++;
	}
	}// end of if

	%>

	
<% if(form.getIsVerificationDone() != null && form.getIsVerificationDone().equals("Y")){%>
<%}else {%>

<tr>
			<td>
			
			<br><b><%=Constant.getResourceStringValue("admin.Questions.feedbackMsg",user1.getLocale())%> </b><br>
			<TEXTAREA NAME="yourfeedback" COLS=60 ROWS=4></TEXTAREA>
            
			</td>
		
		</tr>

		<tr>
			<td> <%=Constant.getResourceStringValue("admin.QuestionsGroup.Status",user1.getLocale())%> : 
			<input type="radio" name="status" value="goodtogo"><%=Constant.getResourceStringValue("aquisition.applicant.good_to_go",user1.getLocale())%> 
			
            <input type="radio" name="status" value="reject"><%=Constant.getResourceStringValue("applicant.Refrences.reject",user1.getLocale())%> 
			</td>
		
		</tr>

		<tr>
			<td>
			
			
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
			</td>
		
		</tr>
		<%}%>
		</table>

</div>

</html:form>

