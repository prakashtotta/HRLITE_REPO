<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
	
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

	String query = request.getParameter("q");
	
	List<String> jobCodeList = BOFactory.getLovBO().getJobCodeDetails(query);

	Iterator<String> iterator = jobCodeList.iterator();
	while(iterator.hasNext()) {
		String jobCode = (String)iterator.next();
		out.println(jobCode);
	}
%>