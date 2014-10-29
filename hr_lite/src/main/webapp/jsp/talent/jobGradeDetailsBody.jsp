<%@ include file="../common/include.jsp" %>
<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
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
<!--<form name ="jobGradeForm" action="/jobgrade.do?method=deletjobgrade">
	<table border="0" width="100%">-->
	<div class="div">
	<font color = red ><html:errors /> </font>
	<tr>
			<td> <%=Constant.getResourceStringValue("admin.JobGrade.details",user1.getLocale())%></td>
			<td></td>
		</tr>
	
		
		<tr>
			<td> <%=Constant.getResourceStringValue("admin.JobGrade.name",user1.getLocale())%> :</td>
			<td><bean:write name="jobGradeForm" property="jobGradeName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.desc",user1.getLocale())%>:</td>
			<td><bean:write name="jobGradeForm" property="jobGradeDesc"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.resp",user1.getLocale())%>:</td>
			<td><bean:write name="jobGradeForm" property="jobGradeResponsibility"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobGrade.status",user1.getLocale())%>:</td>
			<td><bean:write name="jobGradeForm" property="status"/></td>
		</tr>
		
	<tr>
			<td></td>
			<td>
			
             	<logic:iterate id="permission" name="jobGradeForm" property="permissionsList" type="com.bean.Permissions" scope="request">
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
</div>
</form>
</body>