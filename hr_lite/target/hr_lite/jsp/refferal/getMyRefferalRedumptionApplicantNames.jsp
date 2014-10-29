<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%

RefferalEmployee refemp = (RefferalEmployee)session.getAttribute(Common.EMPLOYEE_REFFERAL_DATA);

	String query = request.getParameter("q");
	
	//List<String> applicantList = ApplicantBO.getReferralApplicantNames(refemp,query);
	List<String> applicantList = RefBO.getMyReferralRedumptionApplicantNames(refemp,query);
	Iterator<String> iterator = applicantList.iterator();
	while(iterator.hasNext()) {
		String applicant = (String)iterator.next();
		out.println(applicant);
	}
%>