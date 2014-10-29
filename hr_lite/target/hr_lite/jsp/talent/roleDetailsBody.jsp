<%@ include file="../common/include.jsp" %>
<%
response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setIntHeader("Expires", 0);
%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<br>

<script language="javascript">

function deleteRole(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		 // var gg =  "job/role.do?method=deleterole&roleid="+'<bean:write name="roleForm" property="roleId"/>';
		 // alert(gg);
	 document.roleForm.action = "<%=request.getContextPath()%>/role.do?method=roledelete&roleid="+
			'<bean:write name="roleForm" property="roleId"/>';
   document.roleForm.submit();
 
	   } 
	}

</script>
<body>
<form name ="roleForm" action="/role.do?method=deleterole">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.role.RoleDetails",user1.getLocale())%> </td>
			<td></td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.rolecode",user1.getLocale())%> </td>
			<td><bean:write name="roleForm" property="roleCode"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.rolename",user1.getLocale())%></td>
			<td><bean:write name="roleForm" property="roleName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.decs",user1.getLocale())%></td>
			<td><bean:write name="roleForm" property="roleDesc"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.permissions",user1.getLocale())%></td>
			<td><b><%=Constant.getResourceStringValue("admin.role.PermissionName",user1.getLocale())%>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
			  <%=Constant.getResourceStringValue("admin.role.PermissionDescription",user1.getLocale())%> </b></td>
			  
		</tr>
	<tr>
			<td></td>
			<td>
			
             	<logic:iterate id="permission" name="roleForm" property="permissionsList" type="com.bean.Permissions" scope="request">
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
			<a href="<%=request.getContextPath()%>/role.do?method=roledelete&roleid=
			<bean:write name="roleForm" property="roleId"/>"  ><%=Constant.getResourceStringValue("admin.role.deleterole",user1.getLocale())%></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="submodal-400-250" href="<%=request.getContextPath()%>/role.do?method=editrole&roleid=
			<bean:write name="roleForm" property="roleId"/>"  ><%=Constant.getResourceStringValue("admin.role.edituserscreenname",user1.getLocale())%></a>
			
			 <td>
			<td>
			
			</td>
		</tr>

	</table>

</form>
</body>