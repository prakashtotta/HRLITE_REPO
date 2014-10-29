<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
	
//User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

	String query = request.getParameter("q");
	
	List<String> jobtitleList = BOFactory.getLovBO().getJobtitlerefferal(query);

	Iterator<String> iterator = jobtitleList.iterator();
	while(iterator.hasNext()) {
		String jobTitle= (String)iterator.next();
		out.println(jobTitle);
	}
%>