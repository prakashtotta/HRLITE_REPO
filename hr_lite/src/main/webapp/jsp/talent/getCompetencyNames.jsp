<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.*"%>
<%
	

	String query = request.getParameter("q");
	
	List<String> compList = BOFactory.getCharBO().getCompetencies(query);

	Iterator<String> iterator = compList.iterator();
	while(iterator.hasNext()) {
		String ch = (String)iterator.next();
		out.println(ch);
	}
%>