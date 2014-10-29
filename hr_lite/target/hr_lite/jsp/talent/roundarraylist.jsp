<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>

<%
response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setIntHeader("Expires", 0);
%>

<%

List roundlist = BOFactory.getLovBO().getAllRoundList();
String[] fields = {"roundName"};
String datastring1 = com.util.StringUtils.createYahooStringArray(roundlist,fields);
System.out.println(datastring1);
%>

<%=datastring1%>