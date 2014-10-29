<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
	
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

	String query = request.getParameter("q");
	
	List<String> applicantList = BOFactory.getApplicantBO().getAgencyApplicantNames(query,user1.getUserId());

	Iterator<String> iterator = applicantList.iterator();
	while(iterator.hasNext()) {
		String applicant = (String)iterator.next();
		out.println(applicant);
	}
%>