<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
	
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

	String query = request.getParameter("q");
	

	List<String> organizationList = BOFactory.getApplicantBO().getcurrentOrganizationNames(query,user1.getUserId());
	Iterator<String> iterator = organizationList.iterator();
	while(iterator.hasNext()) {
		String organization = (String)iterator.next();
		out.println(organization);
		
	}
%>