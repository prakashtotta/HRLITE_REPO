<%@ include file="../common/include.jsp" %>
 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="form" name="referenceForm" type="com.form.ReferenceForm" />

<script language="javascript">
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   window.top.hidePopWin();
	   }
	   
	   //ShowMessage();
	}




	


function closewindow(){
//	self.parent.location.reload();
	 	   window.top.hidePopWin();

		   self.parent.location.reload();
	  
	}


	


</script>




<%
List qnsset = form.getQuestionanswersList();

%>

<body>
<html:form action="/reference.do?method=saverefcheck">


<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Feedbacks",user1.getLocale())%></td>
			
		</tr>
	<% if(qnsset != null){
		int i=1;
//Iterator itr = qnsset.iterator();
	
	for(int j=0;j<qnsset.size();j++){
		QuestionAnswers qns = (QuestionAnswers)qnsset.get(j);

%>

<tr>
			<td><b><%=Constant.getResourceStringValue("admin.Questions.Question_no",user1.getLocale())%> : <%=i%> &nbsp;&nbsp;<%=qns.getQuestionName()%> </b><br>

			<% if(qns.getQuestionType().equals(Common.QUESTION_TYPE_TEXT)){%>

<TEXTAREA NAME="qns_<%=qns.getQuestionId()%>" COLS=60 ROWS=3 readonly="true"> <%=qns.getAnswer()%></TEXTAREA>


			<%}%>

		  <% if(qns.getQuestionType().equals(Common.QUESTION_TYPE_RADIO)){%>

<% if(qns.getOptionValue1() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue1()%>" <%=(qns.getOptionValue1()!= null && qns.getOptionValue1().equals(qns.getAnswer()))?"checked":""%>> <%=qns.getOptionValue1()%>
<%}%>
<% if(qns.getOptionValue2() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue2()%>" <%=(qns.getOptionValue2()!= null && qns.getOptionValue2().equals(qns.getAnswer()))?"checked":""%> > <%=qns.getOptionValue2()%>
<%}%>
<% if(qns.getOptionValue3() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue3()%>" <%=(qns.getOptionValue3()!= null && qns.getOptionValue3().equals(qns.getAnswer()))?"checked":""%>> <%=qns.getOptionValue3()%>
<%}%>
<% if(qns.getOptionValue4() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue4()%>" <%=(qns.getOptionValue4()!= null && qns.getOptionValue4().equals(qns.getAnswer()))?"checked":""%>> <%=qns.getOptionValue4()%>
<%}%>
<% if(qns.getOptionValue5() != null) {%>
<input type="radio" name="qns_<%=qns.getQuestionId()%>" value="<%=qns.getOptionValue5()%>" <%=(qns.getOptionValue5()!= null && qns.getOptionValue5().equals(qns.getAnswer()))?"checked":""%>> <%=qns.getOptionValue5()%>
<%}%>



			<%}%>

			</td>
			 
		</tr>


<%
	i++;
	}
	}// end of if

	%>

	<tr>
			<td>
			
			<br><b><%=Constant.getResourceStringValue("applicant.Refrences.Overall_feedback_and_comments",user1.getLocale())%></b> <br>
			<TEXTAREA NAME="yourfeedback" COLS=60 ROWS=4 readonly="true"><%=form.getReferenceeFeedback()%></TEXTAREA>
            
			</td>
		
		</tr>

		<tr>
			<td> <%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%> : 
			<input type="radio" name="status" value="goodtogo" <%=(form.getStatus()!= null && form.getStatus().equals("goodtogo"))?"checked":""%>> 
			<%=Constant.getResourceStringValue("applicant.Refrences.Good_to_go",user1.getLocale())%>
			
            <input type="radio" name="status" value="reject" <%=(form.getStatus()!= null && form.getStatus().equals("reject"))?"checked":""%>>
			<%=Constant.getResourceStringValue("applicant.Refrences.reject",user1.getLocale())%>
			</td>
		
		</tr>

		
		</table>

</div>

</html:form>

