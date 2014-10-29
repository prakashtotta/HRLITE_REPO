<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>
<br>

<script language="javascript">

function deleteSalary(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		 // var gg =  "job/role.do?method=deleterole&roleid="+'<bean:write name="roleForm" property="roleId"/>';
		 // alert(gg);
	 document.salaryForm.action = "<%=request.getContextPath()%>/salaryplan.do?method=salarydelete&planId="+
			'<bean:write name="salaryForm" property="salaryplanId"/>';
   document.salaryForm.submit();
 
	   } 
	}

</script>
<body>
<form name ="salaryForm" action="/salary.do?method=deleterole">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.SalaryDetails",user1.getLocale())%></td>
			<td></td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanId",user1.getLocale())%></td>
			<td><bean:write name="salaryForm" property="salaryplanId"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanName",user1.getLocale())%></td>
			<td><bean:write name="salaryForm" property="salaryPlanName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanDescription",user1.getLocale())%></td>
			<td><bean:write name="salaryForm" property="salaryPlanDesc"/></td>
		</tr>


      <tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.FromAmount",user1.getLocale())%></td>
			<td><bean:write name="salaryForm" property="fromRangeAmount"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.ToAmount",user1.getLocale())%> </td>
			<td><bean:write name="salaryForm" property="toRangeAmount"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.CurrencyCode",user1.getLocale())%> </td>
			<td><bean:write name="salaryForm" property="currencyCode"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.StartDate",user1.getLocale())%></td>
			<td><bean:write name="salaryForm" property="effectiveStartDate"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.EndDate",user1.getLocale())%></td>
			<td><bean:write name="salaryForm" property="effectiveEndDate"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.Status",user1.getLocale())%></td>
			<td><bean:write name="salaryForm" property="status"/></td>
		</tr>
		
	<tr>
			<td></td>
			<td>
			
             	<logic:iterate id="permission" name="salaryForm" property="permissionsList" type="com.bean.Permissions" scope="request">
<bean:write name="permission" property="perName"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<bean:write name="permission" property="perDesc"/>
<br>
</logic:iterate>

			</td>
		</tr>

		
	
		
		<tr>
			<td>
		<!--	<a href="#" onclick="deleteRole()" >delete role</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
			<a href="<%=request.getContextPath()%>/salaryplan.do?method=salarydelete&planId=
			<bean:write name="salaryForm" property="salaryplanId"/>"  ><%=Constant.getResourceStringValue("admin.SalaryPlan.deleterole",user1.getLocale())%></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="submodal-400-250" href="<%=request.getContextPath()%>/salaryplan.do?method=editsalary&planId=
			<bean:write name="salaryForm" property="salaryplanId"/>"  ><%=Constant.getResourceStringValue("admin.SalaryPlan.editrole",user1.getLocale())%></a>
			
			 <td>
			<td>
			
			</td>
		</tr>

	</table>

</form>
</body>