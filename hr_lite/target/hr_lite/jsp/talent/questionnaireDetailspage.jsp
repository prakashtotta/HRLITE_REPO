<%@ include file="../common/include.jsp" %>


<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />
<html>
<head>
	<title><%=Constant.getResourceStringValue("admin.QuestionsGroup.questionnaire_details",user1.getLocale())%></title>
	
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
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</STYLE>
	
</head>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<body class="yui-skin-sam">


<html:form action="/questiongroup.do?method=saveQuestionsGroup" >
<b><%=Constant.getResourceStringValue("admin.QuestionsGroup.questionnaire_details",user1.getLocale())%></b><br><br>
<% if(questionsgroupform.getQuestiongroupId()!=0){%>
	<% QuestionGroups queGroup32 =  BOFactory.getQuestionBO().getQuestionsByQuestionGroup(questionsgroupform.getQuestiongroupId()); %>
	<div class="div">
	<table>
	<% if(queGroup32.getQuestiongroupName()!=null){%>
	<tr>
		<td><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.questiongroup.label",user1.getLocale())%></b></td>
		<td><%=queGroup32.getQuestiongroupName()%></td>
	</tr>

	<%}%>

	<% if(queGroup32.getQuestiongroupDesc()!=null ){%>
	<tr>
		<td><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.description",user1.getLocale())%></b></td>
		<td><%=queGroup32.getQuestiongroupDesc()%></td>
	</tr>

	<%}%>
	</table>
<%}%>




<table>
<tr>
<td valign="top">


<!-- display all question name with option of question group also display edit /delete icon for edit/delet-->

<% QuestionGroups queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(questionsgroupform.getQuestiongroupId());

List questionbygroupList = queGroup.getQuestions();
%>
<% if (questionbygroupList!=null && questionbygroupList.size()>0){%>

	
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
	<tr>

	<td valign="top" width="500">
	<table border="0" cellpadding="0" cellspacing="0">
	 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>
	 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
	      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
	      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="370" height="1"></td>
	      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="200" height="1"></td>
	      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
	 </tr>




	<tr>
	<td>
	<img src="jsp/images/spacer.gif" border="0" width="20">
	</td>
	<td colspan="4">

		<table id="table-1" border="0" bordercolor="#999999">

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


		<td valign="top" >
		<p style="border-top:1px dashed #f00; margin-top:1em; padding-top:1em;" />
		<% if(questions.getTypeVal().equals("text")){%>
		 
		<%=questions.getQuestionName()%> <br><br>

		
		<textarea rows="4" cols="50" name="text7"></textarea>

		
	<%}else if(questions.getTypeVal().equals("radio")){%>
	 
		<%=questions.getQuestionName()%><br>
		<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
		
		if(optionList!=null && optionList.size()>0){
	      for(int j=0;j<optionList.size();j++){

			QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

			%>
			<br><input type="radio" name="radio"/>&nbsp;<%=questionopt.getQuestionOptValue()%>



		<%  }%>

	<%}%>
		<br>
	

	<%}else if(questions.getTypeVal().equals("dropdown")){%>
	<%=questions.getQuestionName()%><br>


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

	<%}else if(questions.getTypeVal().equals("checkbox")){%>
	<%=questions.getQuestionName()%><br>


	<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
		
		if(optionList!=null && optionList.size()>0){%>
		
	    <%  for(int j=0;j<optionList.size();j++){
	
		QuestionOptions questionopt = (QuestionOptions)optionList.get(j);
	
		%>

		<br><input type="checkbox"/><%=questionopt.getQuestionOptValue()%>



		<%  }%>


	<%}%>
	<br>

	<%}else if(questions.getTypeVal().equals("date")){%>
	<%=questions.getQuestionName()%>

	<br>

	<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
        var cal1xx1 = new CalendarPopup("testdiv2");
        cal1xx1.showNavigationDropdowns();
    </SCRIPT>
	<INPUT TYPE="text" NAME="correctAnsDate"  value="" SIZE=50>
	<A HREF="#" onClick="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" TITLE="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" TITLE="select date"></A>

	<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
      



	<br>
	<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

	<%}else if(questions.getTypeVal().equals("number")){%>
	<%=questions.getQuestionName()%>

	<br><br>
	<input type="text" name="number"  size="50"/> 
	<br>

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




	<%}%>
	
	
</td>



</tr>
</table>

</div>
</html:form>
</body>
</html>


