
<%@ page import="java.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.common.Common"%>




<%
User user = (User)session.getAttribute(Common.USER_DATA);
Role role = null;
if(user != null){
	 role = user.getRole();
	 System.out.println(role.getRoleCode());
	 
}

   
%>

<%if(user != null){%>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>

  <%if(role != null && role.getRoleCode().equalsIgnoreCase(Common.ADMIN_ROLE_CODE)){%>
 <tr>
<td><img src="jsp/images/spacer.gif" width="10"></td>
<td colspan="4">

   	<tr><td class="BodyToolboxHeader"><br></td></tr>
	<tr><td class="BodyToolboxHeader"><b>Admin</b></td></tr>

	<tr><td class="BodyText"><b>Configurations</b></td></tr>

	<tr>
	<td><li><a href="lovlist.jsp">LOV list</a></td>

	</tr>
	
	
 	<tr><td class="BodyText"><b>Jobs</b></td></tr>
	
	<tr>
	<td><li><a class="submodal-600-500" href="<%=request.getContextPath()%>/user.do?method=firstpage">create user</a></td>

	</tr>
	
     
	
</td>
</tr>

<%} else {%>
 <tr>
<td><img src="jsp/images/spacer.gif" width="10"></td>
<td colspan="4">

   	<tr><td class="BodyToolboxHeader"><br></td></tr>
	<tr><td class="BodyToolboxHeader"><b>HR</b></td></tr>
	
 	<tr><td class="BodyText"><b>Jobs</b></td></tr>
	
	<tr>
	<td><li><a class="submodal" href="example.html">create job</a></td>

	</tr>
	<tr>
	<td><a href="controller?c=test" ><li type=disc>Manage</a></td>

	</tr>
	<tr>
	<td><a href="controller?c=test" ><li type=disc>Manage</a></td>

	</tr>
	<tr>
	<td><a href="controller?c=test" ><li type=disc>Manage</a></td>

	</tr>
	<tr>
	<td><a href="controller?c=test" ><li type=disc>Manage</a></td>

	</tr>
     
	
</td>
</tr>
<%}%>
</table>

<%}%>