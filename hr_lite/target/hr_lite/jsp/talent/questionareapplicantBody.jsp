<%@ include file="../common/include.jsp" %>

<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>

<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
</STYLE>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />

<script language="javascript">
function savedata(){
document.questionGroupForm.action = "questionnaire.do?method=savequestionnaire&gpid=<%=questionsgroupform.getQuestiongroupapplicantId()%>&applicantid=<%=questionsgroupform.getApplicantId()%>&uuid=<%=questionsgroupform.getUuid()%>";
document.questionGroupForm.submit();
}
</script>

<%

String questionnairesone = (String)request.getAttribute("questionnairesone");

if(questionnairesone != null && questionnairesone.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green">You have answered questions, we will get back to you.</font></td>
			
		</tr>
		
	</table>
</div>

<%}else{%>


<html:form action="/questionnaire.do?method=savequestionnaire" >

<!-- display all question name with option of question group also display edit /delete icon for edit/delet-->




<% 
QuestionGroups queGroup = questionsgroupform.getQuestionGroup();//BOFactory.getQuestionBO().getQuestionsByQuestionGroup(questionsgroupform.getQuestiongroupId());

List questionbygroupList = queGroup.getQuestions();
%>

<div class="div">
	
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
<br>
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

<textarea rows="4" cols="50" name="inp_<%=questions.getQuestionId()%>"></textarea>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0" >






	<%}else if(questions.getTypeVal().equals("radio")){%>
	 
	<%=questions.getQuestionName()%>
	<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){
      for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>
<br><input type="radio"  name="inp_<%=questions.getQuestionId()%>"  value="<%=questionopt.getQuestionOptValue()%>"/>&nbsp;<%=questionopt.getQuestionOptValue()%>



	<%  }%>

	<%}%>
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

 

<%}else if(questions.getTypeVal().equals("dropdown")){%>
<%=questions.getQuestionName()%>


<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){%>
	<br><select name="inp_<%=questions.getQuestionId()%>">
    <%  for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>

<option value="<%=questionopt.getQuestionOptValue()%>"><%=questionopt.getQuestionOptValue()%></option>



	<%  }%>
</select>


	<%}%>
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">


<%}else if(questions.getTypeVal().equals("checkbox")){%>
<%=questions.getQuestionName()%>


<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){%>
	
    <%  for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>

<br><input type="checkbox" name="inp_<%=questions.getQuestionId()%>"  value="<%=questionopt.getQuestionOptValue()%>"/><%=questionopt.getQuestionOptValue()%>



	<%  }%>


	<%}%>
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">


<%}else if(questions.getTypeVal().equals("date")){
	String calgentext = VariableDataCaptureUtil.generateJavaScriptForQuestions(questions,request);
	%>
<%=questions.getQuestionName()%>

<br>
<%=calgentext%>
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

<%}else if(questions.getTypeVal().equals("number")){%>
<%=questions.getQuestionName()%>

<br>
<input type="text" name="inp_<%=questions.getQuestionId()%>"  size="50"/> 
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">


<%}%>
</td>
</tr>
<%}}}%>
 

<%}%>

<tr>
<td>
<input type="button" name="login" value="submit" onClick="savedata()" class="button">
</td>
</table>
	
	
</td>
</tr>

<tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;</td></tr>
<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>
</table>
</td>

</tr>
</table>
</div>
</html:form>

<%}%>