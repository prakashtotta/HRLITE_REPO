
<%@ page import="java.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.test.*"%>
 
<!--
http://localhost:8081/hr/jsp/testengine/xmlupload.jsp?cat_id=1&fname=pmp-mocknew.xml
-->

<%
String catId = (String)request.getParameter("cat_id");
String fname = (String)request.getParameter("fname");
MockUtil t = new MockUtil();
t.addMockQuestions(Integer.parseInt(catId),fname);

%>