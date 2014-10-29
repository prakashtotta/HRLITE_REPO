<%@ page import="java.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.bo.*"%>
<%@ page import="com.dao.*"%>
<%@ page import="com.test.*"%>
<%@ page import="com.util.*"%>



<!--
http://localhost:8081/hr/jsp/testengine/pmptech20.jsp?catId=1&applicantId=41&uuid=8b837935-52b0-4772-84cd-e7bfdf9b05cc
-->


<%
String catId = (String)request.getParameter("catId");
String applicantId = (String)request.getParameter("applicantId");
String uuid = (String)request.getParameter("uuid");

List questionsIdList = new ArrayList();
MockTest mock1 = BOFactory.getMockTestBO().getMockTest(catId,applicantId,uuid);
boolean isdone = false;
boolean isExamAssigned = true;
if(mock1 != null){
	if(mock1.getResult() != null && mock1.getResult().equals("ASSIGNED")){
		
		BOFactory.getMockTestBO().startMockTest(mock1.getTestId());
	    questionsIdList = BOFactory.getMockTestBO().getQuestionsIdListMockTestResult(mock1,catId,mock1.getTestId());
	}else{
       isdone = true;
	}
}else{
	isExamAssigned = false;
// mock1 = BOFactory.getMockTestBO().createMockTest(catId,applicantId,uuid);

//questionsIdList = BOFactory.getMockTestBO().CopyQuestions(mock1,catId);
}
if(mock1 != null){
if(!isdone){
Collections.sort(questionsIdList);
session.setAttribute("questionsIdList",questionsIdList);
session.setAttribute("testid",mock1.getTestId());
session.setAttribute("catId",catId);
session.setAttribute("app_name",mock1.getApplicantName());

response.sendRedirect("pmptechmock20.jsp?in=0");
}else{
%>
<b>Hi, <%=mock1.getApplicantName()%> , <br>You have already attended the exam, we will get back to you. <br> Thanks <br> Recruitment team</b>
<%}}%>
<% if(!isExamAssigned){%>
<b>Test is not assigned to you. Contact recruitment team.</b>
<%}%>





  

	

