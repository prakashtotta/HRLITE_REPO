<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userSalaryForm" type="com.form.employee.UserSalaryForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
List userSalaryList = cform.getUserSalaryComponentList();
%>

<script language="javascript">
function load(size){
	alert(size);
	 for (i = 0; i < size; i++){
		var divname = document.getElementById("bankDetaildata_"+i);
		 divname.style.display = "none";
	 }
	
	
}
</script>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}
</style>
<body onload="load('<%=userSalaryList.size()%>')">
<html:form action="/userimmigration.do?method=logon" enctype="multipart/form-data">

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.salary.assigned",user1.getLocale())%></legend>
		<%
String deleteUserSalarydetails = (String)request.getAttribute("deleteUserSalarydetails");
	
if(deleteUserSalarydetails != null && deleteUserSalarydetails.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.salary.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		 String usersalaryids="";
		if(userSalaryList.size() > 0){
			for(int i = 0; i< userSalaryList.size(); i++){
				UserSalary userSalary = (UserSalary)userSalaryList.get(i);
				usersalaryids = usersalaryids+userSalary.getUserSalaryId()+","; 
		%>
		
		<%}
		}%>
			<div align="left">
			<a class="button" href="#" onClick="deleteUserDetails('<%=usersalaryids%>','usersalary');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
				String addUserSalary = request.getContextPath()+"/usersalary.do?method=addUserSalaryComponent&userId="+cform.getUserId();		
			%>
			<a class="button" href="#" onClick="javascript:addUserSalaryComponent('<%=addUserSalary%>');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a class="button" href="#" onClick="javascript:retrieveData('usersalary');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            </div>
            <br>
<table border="0" width="100%">


		<%
		
		if(userSalaryList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">
		 	<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form,'usersalary')" name="masterCheck"></td>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.salary.Salary_Component",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.salary.Pay_Grade",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.salary.Pay_Frequency",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.salary.Currency",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%></b></td>
				<td align="center" width="15%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.salary.Add_direct_deposite_details",user1.getLocale())%></b></td>
			</tr>
		<%
			for(int i = 0; i< userSalaryList.size(); i++){
				UserSalary userSalary = (UserSalary)userSalaryList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserSalaryURl = request.getContextPath()+"/usersalary.do?method=editUserSalaryComponent&userSalaryId="+userSalary.getUserSalaryId()+"&userId="+cform.getUserId();		
				
		%>
		

			<tr bgcolor=<%=bgcolor%>>
			<td align="center"><input type="checkbox" id="salaryIsActive_<%=userSalary.getUserSalaryId()%>"  name="salaryIsActive"></td>
				<td width="20%">&nbsp;<a class="button" href="#" onClick="editUserSalaryComponent('<%=editUserSalaryURl%>');return false"><%=userSalary.getSalaryComonents()  == null ?"": userSalary.getSalaryComonents()%></a></td>
				<td>&nbsp;<%=userSalary.getSalaryplan() == null ?"":userSalary.getSalaryplan().getSalaryPlanName() %></td>
				<td>&nbsp;<%=userSalary.getCompfrequency() == null ?"":userSalary.getCompfrequency().getCompFrequencyName() %></td>
				<td>&nbsp;<%=userSalary.getSalaryplan() == null ?"":userSalary.getSalaryplan().getCurrencyCode() %></td>
				<td>&nbsp;<%=userSalary.getAmount() == null ?"":userSalary.getAmount()%></td>
				<td align="center">&nbsp;
				<% if(userSalary.getIsBankAcountDetails() != null && userSalary.getIsBankAcountDetails().equals("Y")){%>
				<input type="checkbox" id="showHideDirectdepositdetails_<%=userSalary.getUserSalaryId()%>" name="showHideDirectdepositdetails_<%=userSalary.getUserSalaryId()%>" onClick="showHideDirectDepositDetails(this.form,'<%=userSalary.getUserSalaryId()%>')" >
				
				<%} %>
				</td>
				
			</tr>
			<% if(userSalary.getIsBankAcountDetails() != null && userSalary.getIsBankAcountDetails().equals("Y")){%>
			<tr><td></td>
			<td colspan="5">
			
			<div  id="bankDetaildata_<%=userSalary.getUserSalaryId()%>">
			<table border="0" width="100%">
			<tr bgcolor="#b7b7b7">
				<td ><%=Constant.getResourceStringValue("hr.user.salary.Account_Number",user1.getLocale())%></td>
				<td ><%=Constant.getResourceStringValue("hr.user.salary.Bank_name",user1.getLocale())%></td>
				<td ><%=Constant.getResourceStringValue("hr.user.salary.Bank_address",user1.getLocale())%></td>
				<td ><%=Constant.getResourceStringValue("hr.user.salary.Account_type",user1.getLocale())%></td>
				<td ><%=Constant.getResourceStringValue("hr.user.salary.Routing_number",user1.getLocale())%></td>
				<td ><%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%></td>
			
			</tr>
			<tr bgcolor=" #b7b7b7">
				<td ><%=userSalary.getBankAcountNumber() == null ?"":userSalary.getBankAcountNumber()%></td>
				<td ><%=userSalary.getBankName() == null ?"":userSalary.getBankName()%></td>
				<td ><%=userSalary.getBankAddress() == null ?"":userSalary.getBankAddress()%></td>
				<td ><%=userSalary.getAccountType() == null ?"":userSalary.getAccountType()%></td>
				<td ><%=userSalary.getBankRoutingNumber() == null ?"":userSalary.getBankRoutingNumber()%></td>
				<td><%=userSalary.getDdamount() == null ?"":userSalary.getDdamount()%></td>
			
			</tr>
			</table>
			</div>
			
			</td></tr>
			<%} %>
			
		<%
			}
		}else{
		
		
		%>
		<tr><td align="left"><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>


</table>


</fieldset>
</span>
</html:form>
</body>