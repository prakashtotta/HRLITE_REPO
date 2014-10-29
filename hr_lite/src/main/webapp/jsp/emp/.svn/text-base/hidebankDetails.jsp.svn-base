<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userSalaryForm" type="com.form.employee.UserSalaryForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

%>


<span id="isDirectDepositeData">
		<table border="0" width="100%">
		<tr>
		<td>
		<% if(cform.getIsBankAcountDetails() != null && cform.getIsBankAcountDetails().equals("Y")){%>
		<input type="checkbox" id="isBankAcountDetails" onClick="isBankDetails()" name="isBankAcountDetails" Value="Y" checked>
		<%}else{ %>
		<input type="checkbox" id="isBankAcountDetails" onClick="isBankDetails()"  name="isBankAcountDetails" Value="N">
		<%} %>
		
		<%=Constant.getResourceStringValue("hr.user.salary.Add_direct_deposite_details",user1.getLocale())%></td>
			
		</tr>
		</table>
		<br><br>
</span>