<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.TagsBO"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bo.*"%>

<%
    
	User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

	String query = request.getParameter("q");
 
	String data = BOFactory.getTagsBO().getAllTagsAutoSuggestJSONMulti(user1.getSuper_user_key(),Common.TAG_TYPE_APPLICANT,query);

	data = "["+data+"]";


	
%>

<%=data%>