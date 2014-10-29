<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userSalaryForm" type="com.form.employee.UserSalaryForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<< bank details.jsp >>>");
%>


<html:form action="/usersalary.do?method=logon">
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
		<table border="0" width="100%">
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Account_Number",user1.getLocale())%><font color="red">*</font></td>
			
			<td><html:text property="bankAcountNumber" size="35" maxlength="200"/></td>
		
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Bank_name",user1.getLocale())%><font color="red">*</font></td>
	
			<td><html:text property="bankName" size="35" maxlength="200"/></td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Bank_address",user1.getLocale())%><font color="red">*</font></td>
		
			<td><html:text property="bankAddress" size="35" maxlength="200"/></td>
		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Routing_number",user1.getLocale())%><font color="red">*</font></td>
			
			<td><html:text property="bankRoutingNumber" size="35" maxlength="200"/></td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%><font color="red">*</font></td>

			<td><html:text property="ddamount" size="35" maxlength="200"/></td>
		</tr>
				<tr>
			<td><%=Constant.getResourceStringValue("hr.user.salary.Account_type",user1.getLocale())%><font color="red">*</font></td>
			
			<td>
			<select name="accountType" onchange="showOthertextbox();">
			<option value=""><%=Constant.getResourceStringValue("hr.user.salary.select",user1.getLocale())%></option>
			<option value="<%=Constant.getResourceStringValue("hr.user.salary.accounttype.Savings",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.salary.accounttype.Savings",user1.getLocale())%></option>
			<option value="<%=Constant.getResourceStringValue("hr.user.salary.accounttype.Checking",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.salary.accounttype.Checking",user1.getLocale())%></option>
			<option value="<%=Constant.getResourceStringValue("hr.user.salary.accounttype.Other",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.salary.accounttype.Other",user1.getLocale())%></option>
			</select>
			</td>

		</tr>
		
		<tr>
		<td colspan="2"><span id="accounttypeother"></span></td>
		</tr>
		</table>
</span>
</html:form>

