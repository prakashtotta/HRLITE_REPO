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


<%
String questionnairedeleted = (String)request.getAttribute("questionnairedeleted");
if(questionnairedeleted != null && questionnairedeleted.equals("yes")){
%>
<br>
<span>
<font color=red><%=Constant.getResourceStringValue("admin.Questions.Questionnaire.deleted.recalled",user1.getLocale())%> </font> 
</span>
<%}%>