<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


%>

<font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.applicantsearchheader.error",user1.getLocale())%> </font>