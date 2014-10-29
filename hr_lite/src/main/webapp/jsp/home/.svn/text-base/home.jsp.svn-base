<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>

<%@ include file="../common/cache.jsp" %>


<%


User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>


<%if(PermissionChecker.isPermissionApplied(Common.REVIEWER_DASHBOARD,user1)){%>
<jsp:include page="dashboardReviewerNew.jsp" flush="true" />
<%}else if(PermissionChecker.isPermissionApplied(Common.HIRING_MANAGER_DASHBOARD,user1)){%>
<jsp:include page="dashboardHiringManagerNew.jsp" flush="true" />
<%}else if(PermissionChecker.isPermissionApplied(Common.RECRUITER_DASHBOARD,user1)){%>
<jsp:include page="dashboardRecruiterNew.jsp" flush="true" />
<%}else {%>
<%@ include file="dashboardAdmin.jsp" %>

<%}%>