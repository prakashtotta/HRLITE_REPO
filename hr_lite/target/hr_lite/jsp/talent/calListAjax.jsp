<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.dao.*"%>



<%
 
  
  
 


  %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ include file="../common/autocomplete.jsp" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<head>


    <meta http-equiv="expires" content="Sun, 01 Dec 2001 12:00:00 EST" />

<title><%=Constant.getResourceStringValue("task.callistbody.title",user1.getLocale())%></title>




<head>




</head>
<span id="calList">



<body class="yui-skin-sam">

<%@ include file="calendercommon.jsp" %>


		

	
</body>
</html>


</span>


