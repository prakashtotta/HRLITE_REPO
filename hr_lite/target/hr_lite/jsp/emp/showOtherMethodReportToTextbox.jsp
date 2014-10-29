<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userReportToForm" type="com.form.employee.UserReportToForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<< showOtherMethodReportToTextbox.jsp >>>");

%>
<html:form action="/userreportto.do?method=logon">
<span id="reportToMethodOther"><%=Constant.getResourceStringValue("hr.user.salary.please_specify",user1.getLocale())%><span1>*</span1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:text property="reportToMethodOther" size="35" maxlength="200"/></span>
			</html:form>