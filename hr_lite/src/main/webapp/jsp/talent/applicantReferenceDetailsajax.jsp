<%@ include file="../common/include.jsp" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>



<%@ include file="referncedetailscommon.jsp" %>


