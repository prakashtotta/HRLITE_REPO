<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userDependentsForm" type="com.form.employee.UserDependentsForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<< relationshipother.jsp >>>");

%>
<html:form action="/userdependents.do?method=logon">
		<td width="25%" colspan="2">
		<span id="relationshipother"><%=Constant.getResourceStringValue("hr.user.relationship.please_specify",user1.getLocale())%><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:text property="relationshipOther" size="25" maxlength="200"/></span>
</td>
			</html:form>