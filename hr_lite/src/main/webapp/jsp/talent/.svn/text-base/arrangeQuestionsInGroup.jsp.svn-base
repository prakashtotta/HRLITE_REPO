<%@ page import="com.bo.*"%>
<%
String arrangestr = (String)request.getParameter("arrangestr");
String qnsgroupid = (String)request.getParameter("qnsgroupid");
System.out.println("arrangestr"+arrangestr);
System.out.println("qnsgroupid"+qnsgroupid);

BOFactory.getQuestionBO().arrangeQuestionInGroup(qnsgroupid,arrangestr);
%>