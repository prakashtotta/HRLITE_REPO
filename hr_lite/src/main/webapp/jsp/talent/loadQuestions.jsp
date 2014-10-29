<%@ include file="../common/include.jsp" %>
<%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<html:html>
<bean:define id="qnsform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />

<html:form action="/questiongroup.do?method=loadQuestions" >

<span id ="questionslist">

<%=Constant.getResourceStringValue("admin.Questions.name",user1.getLocale())%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<html:select styleClass="singleselect" property="questionId" onchange="retrieveCriterias();">
	<option value=""></option>
			<bean:define  name="questionGroupForm" property="questionsList" id="questionsList" />
            <html:options collection="questionsList" property="questionId"  labelProperty="questionNameType"/>
			</html:select>
		<span class="textboxlabel" id="loadingcri" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>	
</span>
</html:form>
</html:html>


	