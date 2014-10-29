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
<!--<form name ="locationForm" action="/location.do?method=deleterole">
	<table border="0" width="100%">-->
	<font color = red ><html:errors /> </font>
	<tr>
			<td> <%=Constant.getResourceStringValue("admin.Location.locationDetails",user1.getLocale())%> </td>
			<td></td>
		</tr>

	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%></td>
			<td><bean:write name="locationForm" property="locationName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.locationcode",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="locationCode"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="country"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="state"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.city",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="city"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.address",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="address"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.zip",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="zip"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.phone",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="phone"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.fax",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="fax"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.notes",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="notes"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.createddate",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="createdDate"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.createdby",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="createdBy"/></td>
		</tr>

		<td><%=Constant.getResourceStringValue("admin.Location.status",user1.getLocale())%> </td>
			<td><bean:write name="locationForm" property="status"/></td>
		</tr>
	<tr>
			<td></td>
			<td>
			
             	<logic:iterate id="permission" name="locationForm" property="permissionsList" type="com.bean.Permissions" scope="request">
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