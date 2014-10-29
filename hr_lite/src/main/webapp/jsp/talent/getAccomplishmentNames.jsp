<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.*"%>
<%
	

	String query = request.getParameter("q");
	
	List<String> accompList = BOFactory.getCharBO().getAccomplishments(query);

	Iterator<String> iterator = accompList.iterator();
	while(iterator.hasNext()) {
		String ch = (String)iterator.next();
		out.println(ch);
	}
%>