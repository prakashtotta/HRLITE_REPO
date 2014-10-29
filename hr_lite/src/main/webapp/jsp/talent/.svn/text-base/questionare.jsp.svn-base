<%@ include file="../common/include.jsp" %>

<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />

<html:form action="/questiongroup.do?method=saveQuestionsGroup" >

<!-- display all question name with option of question group also display edit /delete icon for edit/delet-->



<% 
QuestionGroups queGroup = questionsgroupform.getQuestionGroup();

List questionbygroupList = queGroup.getQuestions();

Map<Long, QuestionnaireAnswers> answerMap = questionsgroupform.getAnswerMap();
%>


	
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr>
<td valign="top" width="50"></td>
<td valign="top" width="700">
<table border="0" cellpadding="0" cellspacing="0">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>
 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="440" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="370" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="200" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
	  
 </tr>
<br><br>
<b><%=questionsgroupform.getHeadingComment()%></b>

<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>
<td colspan="4">

<table id="table-1" border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">

<%
	
if (questionbygroupList!=null && questionbygroupList.size()>0){
	
StringTokenizer tockens = new StringTokenizer(queGroup.getQuestionsSeq(),",");

	while(tockens!=null && tockens.hasMoreTokens()){
				String tmpqnsid = tockens.nextToken();

for(int i=0; i<questionbygroupList.size(); i++){
Questions questions = (Questions)questionbygroupList.get(i);
   if(tmpqnsid != null && tmpqnsid.equals(String.valueOf(questions.getQuestionId()))){
%>

<tr  id="1.<%=questions.getQuestionId()%>" >

 
<td  valign="top">
<p style="border-top:1px dashed #f00; margin-top:1em; padding-top:1em;" />
<% if(questions.getTypeVal().equals("text")){%>
 
<%=questions.getQuestionName()%> <br>

<textarea rows="4" cols="50" name="text7"></textarea>
<br>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.correct.answer",user1.getLocale())%> : <%=(StringUtils.isNullOrEmpty(questions.getCorrectAns()))?Constant.getResourceStringValue("admin.NA",user1.getLocale()):questions.getCorrectAns()%>

<% 
	if (questionsgroupform.getQuestionnaireStatus() != null && questionsgroupform.getQuestionnaireStatus().equals("DONE")){
	QuestionnaireAnswers qnsa = answerMap.get(questions.getQuestionId());
%>
<br>
<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.Answered",user1.getLocale())%> : <%=qnsa.getAnswer()%>

<%}%>

<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0" >






	<%}else if(questions.getTypeVal().equals("radio")){%>
	 
	<%=questions.getQuestionName()%>
	<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){
      for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>
<br><input type="radio"/>&nbsp;<%=questionopt.getQuestionOptValue()%>



	<%  }%>

	<%}%>
<br>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.correct.answer",user1.getLocale())%> : <%=(StringUtils.isNullOrEmpty(questions.getCorrectAns()))?Constant.getResourceStringValue("admin.NA",user1.getLocale()):questions.getCorrectAns()%>

<% 
	if (questionsgroupform.getQuestionnaireStatus() != null && questionsgroupform.getQuestionnaireStatus().equals("DONE")){
	QuestionnaireAnswers qnsa = answerMap.get(questions.getQuestionId());
%>
<br>
<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.Answered",user1.getLocale())%> : <%=qnsa.getAnswer()%>

<%}%>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

 

<%}else if(questions.getTypeVal().equals("dropdown")){%>
<%=questions.getQuestionName()%>


<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){%>
	<br><select name="select1">
    <%  for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>

<option><%=questionopt.getQuestionOptValue()%></option>



	<%  }%>
</select>


	<%}%>
<br>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.correct.answer",user1.getLocale())%> : <%=(StringUtils.isNullOrEmpty(questions.getCorrectAns()))?Constant.getResourceStringValue("admin.NA",user1.getLocale()):questions.getCorrectAns()%>

<% 
	if (questionsgroupform.getQuestionnaireStatus() != null && questionsgroupform.getQuestionnaireStatus().equals("DONE")){
	QuestionnaireAnswers qnsa = answerMap.get(questions.getQuestionId());
%>
<br>
<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.Answered",user1.getLocale())%> : <%=qnsa.getAnswer()%>

<%}%>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">


<%}else if(questions.getTypeVal().equals("checkbox")){%>
<%=questions.getQuestionName()%>


<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){%>
	
    <%  for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>

<br><input type="checkbox"/><%=questionopt.getQuestionOptValue()%>



	<%  }%>


	<%}%>
<br>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.correct.answer",user1.getLocale())%> : <%=(StringUtils.isNullOrEmpty(questions.getCorrectAns()))?Constant.getResourceStringValue("admin.NA",user1.getLocale()):questions.getCorrectAns()%>

<% 
	if (questionsgroupform.getQuestionnaireStatus() != null && questionsgroupform.getQuestionnaireStatus().equals("DONE")){
	QuestionnaireAnswers qnsa = answerMap.get(questions.getQuestionId());
%>
<br>
<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.Answered",user1.getLocale())%> : <%=qnsa.getAnswer()%>

<%}%>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">


<%}else if(questions.getTypeVal().equals("date")){
	String calgentext = VariableDataCaptureUtil.generateJavaScriptForQuestions(questions,request);
	%>
<%=questions.getQuestionName()%>

<br>
<%=calgentext%>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.correct.answer",user1.getLocale())%> : 

<%=(questions.getCorrectAnsDate()==null)?Constant.getResourceStringValue("admin.NA",user1.getLocale()):DateUtil.convertDateToStringDate(questions.getCorrectAnsDate(),datepattern)%>

<% 
	if (questionsgroupform.getQuestionnaireStatus() != null && questionsgroupform.getQuestionnaireStatus().equals("DONE")){
	QuestionnaireAnswers qnsa = answerMap.get(questions.getQuestionId());
%>
<br>
<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.Answered",user1.getLocale())%> : <%=DateUtil.convertDateToStringDate(qnsa.getAnswerDate(),datepattern)%>

<%}%>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

<%}else if(questions.getTypeVal().equals("number")){%>
<%=questions.getQuestionName()%>

<br>
<input type="text" name="number"  size="50"/> 
<br>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.correct.answer",user1.getLocale())%> : <%=(StringUtils.isNullOrEmpty(questions.getCorrectAns()))?Constant.getResourceStringValue("admin.NA",user1.getLocale()):questions.getCorrectAns()%>

<% 
	if (questionsgroupform.getQuestionnaireStatus() != null && questionsgroupform.getQuestionnaireStatus().equals("DONE")){
	QuestionnaireAnswers qnsa = answerMap.get(questions.getQuestionId());
%>
<br>
<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.Answered",user1.getLocale())%> : <%=qnsa.getAnswer()%>

<%}%>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">


<%}%>
</td>
</tr>
<%}}}%>
 

<%}%>


</table>
	
	
</td>
</tr>

<tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;</td></tr>
<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>
</table>
</td>

</tr>
</table>
</html:form>

