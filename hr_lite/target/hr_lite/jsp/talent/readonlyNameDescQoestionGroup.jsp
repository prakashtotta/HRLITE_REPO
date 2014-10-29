<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.*"%>
<%
User user16 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;}

</style>
</head>


<script language="javascript">
function change(e, color){
    var el=window.event? event.srcElement: e.target
    if (el.tagName=="INPUT"&&el.type=="button")
    el.style.backgroundColor=color
}
	</script>
 
 <html:form action="/questiongroup.do?method=saveQuestionsGroup34345">
<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />
			
<span id="editNameDesc">

<% if(questionsgroupform.getQuestiongroupId()!=0){%>
	<% QuestionGroups queGroup32 =  BOFactory.getQuestionBO().getQuestionsByQuestionGroup(questionsgroupform.getQuestiongroupId()); %>
<% if(queGroup32.getQuestiongroupName()!=null){%>
<b><i><%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.edidlink.label",user16.getLocale())%></i></b>
<a href="#" onClick="editQuestionGroupNameDesc()"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.questiongroup.alt",user16.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.questiongroup.alt",user16.getLocale())%>" height="20"  width="19"/></a>




<br><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.questiongroup.label",user16.getLocale())%></b>
<%=queGroup32.getQuestiongroupName()%>


<%}%>

<% if(queGroup32.getQuestiongroupDesc()!=null ){%>
<br><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.description",user16.getLocale())%></b>&nbsp;<%=queGroup32.getQuestiongroupDesc()%>



<%}%>
<%}%>




</span>
</html:form>
</html:html>