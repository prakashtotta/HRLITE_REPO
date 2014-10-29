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

//function deleteRole(){
	//  var doyou = confirm("Are you confirm to delete? (OK = Yes   Cancel = No)");
	//  if (doyou == true){
		 // var gg =  "job/role.do?method=deleterole&roleid="+'<bean:write name="roleForm" property="roleId"/>';
		 // alert(gg);
	// document.roleForm.action = "<%=request.getContextPath()%>/role.do?method=roledelete&roleid="+
	//		'<bean:write name="roleForm" property="roleId"/>';
  // document.roleForm.submit();
 
	 //  } 
	//}

</script>
<body>
<!--<form name ="jobCodeForm" action="/jobcode.do?method=deletjobcode">
	<table border="0" width="100%">-->
	<font color = red ><html:errors /> </font>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.Details",user1.getLocale())%></td>
			<td></td>
		</tr>

	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobCode",user1.getLocale())%></td>
			<td><bean:write name="jobCodeForm" property="jobCode"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobName",user1.getLocale())%></td>
			<td><bean:write name="jobCodeForm" property="jobName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobDescription",user1.getLocale())%> </td>
			<td><bean:write name="jobCodeForm" property="jobDesc"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobResponsibility",user1.getLocale())%></td>
			<td><bean:write name="jobCodeForm" property="jobResponsibility"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.Status",user1.getLocale())%> </td>
			<td><bean:write name="jobCodeForm" property="status"/></td>
		</tr>
		
	<tr>
			<td></td>
			<td>
			
             	<logic:iterate id="permission" name="jobCodeForm" property="permissionsList" type="com.bean.Permissions" scope="request">
<bean:write name="permission" property="perName"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<bean:write name="permission" property="perDesc"/>
<br>
</logic:iterate>

			</td>
		</tr>

		
	
		
		<tr>
			<td>
		<!--	<a href="#" onclick="deleteRole()" >delete role</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/role.do?method=roledelete&roleid=
			<bean:write name="roleForm" property="roleId"/>"  >delete role</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="submodal-400-250" href="<%=request.getContextPath()%>/role.do?method=editrole&roleid=
			<bean:write name="roleForm" property="roleId"/>"  >edit role</a>
			-->
			 <td>
			<td>
			
			</td>
		</tr>

	</table>

</form>
</body>