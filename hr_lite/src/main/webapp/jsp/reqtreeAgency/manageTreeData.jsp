<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>

<%
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String action = request.getParameter("action");
String ownerEl = request.getParameter("ownerEl");




String content = "";
if(action != null && action.equals("getElementList")){
	if(ownerEl == null){
		//content = TalentPoolBO.getTalentPoolElementsByOwnerId(0);
	}else{
	content = BOFactory.getTreeBO().getApplicantsTreeByVendor(ownerEl,user1.getUserId());
	}
}





%>

<%=content%>

