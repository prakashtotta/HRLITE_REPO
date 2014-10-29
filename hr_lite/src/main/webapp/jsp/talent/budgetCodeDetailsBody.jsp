<%@ include file="../common/include.jsp" %>
<%
////response.setHeader("Cache-Control", "no-cache");
	//	//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<br>

<script language="javascript">

function deleteBudgetCode(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		 // var gg =  "job/role.do?method=deleterole&roleid="+'<bean:write name="roleForm" property="roleId"/>';
		 // alert(gg);
	 document.budgetCodeForm.action = "<%=request.getContextPath()%>/budgetcode.do?method=budgetCodedelete&id="+
			'<bean:write name="budgetCodeForm" property="budgetId"/>';
   document.salaryForm.submit();
 
	   } 
	}

</script>
<body>
<form name ="budgetCodeForm" action="/budgetcode.do?method=deleterole">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.CreateBudgetCode",user1.getLocale())%> </td>
			<td></td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user1.getLocale())%> </td>
			<td><bean:write name="budgetCodeForm" property="budgetCode"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.Budegetname",user1.getLocale())%></td>
			<td><bean:write name="budgetCodeForm" property="budgetCentreName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetDescription",user1.getLocale())%></td>
			<td><bean:write name="budgetCodeForm" property="budgetCentreDesc"/></td>
		</tr>


      <tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetMonth",user1.getLocale())%></td>
			<td><bean:write name="budgetCodeForm" property="budgetMonth"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetQuater",user1.getLocale())%></td>
			<td><bean:write name="budgetCodeForm" property="budgetQuarter"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetYear",user1.getLocale())%></td>
			<td><bean:write name="budgetCodeForm" property="budgetYear"/></td>
		</tr>
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.Status",user1.getLocale())%></td>
			<td><bean:write name="budgetCodeForm" property="status"/></td>
		</tr>
		
	<tr>
			<td></td>
			<td>
			
             	<logic:iterate id="permission" name="budgetCodeForm" property="permissionsList" type="com.bean.Permissions" scope="request">
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
			<a href="<%=request.getContextPath()%>/budgetcode.do?method=budgetCodedelete&id=
			<bean:write name="budgetCodeForm" property="budgetId"/>"  ><%=Constant.getResourceStringValue("admin.BudgetCode.deletebudgetcode",user1.getLocale())%> </a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="submodal-400-250" href="<%=request.getContextPath()%>/budgetcode.do?method=editBudgetCode&id=
			<bean:write name="budgetCodeForm" property="budgetId"/>"  ><%=Constant.getResourceStringValue("admin.BudgetCode.editbudgetcode",user1.getLocale())%></a>
		
			 <td>
			<td>
			
			</td>
		</tr>

	</table>

</form>
</body>