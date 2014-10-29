
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userSalaryForm" type="com.form.employee.UserSalaryForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<< bank accounttypeother.jsp >>>");

%>
<html:form action="/usersalary.do?method=logon">
<span id="accounttypeother"><%=Constant.getResourceStringValue("hr.user.salary.please_specify",user1.getLocale())%><span1>*</span1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:text property="accountTypeOther" size="35" maxlength="200"/></span>
			</html:form>