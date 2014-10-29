
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userSalaryForm" type="com.form.employee.UserSalaryForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<< bank accounttypeother.jsp >>>");
String showtextbox = (String)request.getAttribute("showtextbox");
%>
<html:form action="/usersalary.do?method=logon">
<span id="accounttypeother"></span>
</html:form>