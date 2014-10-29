<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
	
User agency = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

String query = request.getParameter("q");
	
List<String> jobtitleList = BOFactory.getJobRequistionBO().getJobtitleforAgency(query,agency.getUserId());

	Iterator<String> iterator = jobtitleList.iterator();
	while(iterator.hasNext()) {
		String jobTitle= (String)iterator.next();
		out.println(jobTitle);
	}
%>