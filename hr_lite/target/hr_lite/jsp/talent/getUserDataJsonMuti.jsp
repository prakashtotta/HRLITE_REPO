<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.UserBO"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bo.*"%>

<%
    
	User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

	String query = request.getParameter("q");
 
	String data = BOFactory.getUserBO().getUserDataByQueryAutoSuggestJSONMulti(user1,query);

	data = "["+data+"]";


	
%>

<%=data%>