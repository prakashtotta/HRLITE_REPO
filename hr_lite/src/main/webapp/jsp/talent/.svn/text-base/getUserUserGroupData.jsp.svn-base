<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.UserBO"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%
	
    User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
	String query = request.getParameter("q");
 
	List<String> usersList = UserBO.getUserUserGroupDataByQueryAutoSuggest(user1,query);

	Iterator<String> iterator = usersList.iterator();
	while(iterator.hasNext()) {
		String userd = (String)iterator.next();
		out.println(userd);
	}
	
%>