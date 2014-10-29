<%@page contentType="text/xml" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page import="com.bo.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>

<%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
    
	String query = request.getParameter("input");
	String cri = request.getParameter("cri");
    System.out.println("query"+query);
	System.out.println("cri"+cri);
	
	/*List<String> reqList = JobRequistionBO.getRequistionData(query,cri);

	Iterator<String> iterator = reqList.iterator();
	while(iterator.hasNext()) {
		String req = (String)iterator.next();
		out.println(req);
	}*/
 

	String xml = BOFactory.getJobRequistionBO().getRequistionDataXML(query,cri,user1);
	out.println(xml);
%>