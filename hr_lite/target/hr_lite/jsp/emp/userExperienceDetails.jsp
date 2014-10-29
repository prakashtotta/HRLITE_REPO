<%@ include file="../common/include.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userExperienceForm" type="com.form.employee.UserExperienceForm" />


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

List userExpList = cform.getUserExperiencesList();
%>



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
<body>
<html:form action="/userexperience.do?method=logon" enctype="multipart/form-data">

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.Work_Experience",user1.getLocale())%></legend>
		<%
String deleteUserExperienceMultiple = (String)request.getAttribute("deleteUserExperienceMultiple");
	
if(deleteUserExperienceMultiple != null && deleteUserExperienceMultiple.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.userexperience.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		 String userexpids="";
		if(userExpList.size() > 0){
			for(int i = 0; i< userExpList.size(); i++){
				OrganizationDetails organizationDetails = (OrganizationDetails)userExpList.get(i);
				userexpids = userexpids+organizationDetails.getOrgDetailsId() +","; 
		%>
		
		<%}
		}%>
			<div align="left">
			<a href="#" onClick="deleteUserDetails('<%=userexpids%>','userexperience');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
				String addUserSalary = request.getContextPath()+"/userexperience.do?method=addUserExperienceDetails&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserExperience('<%=addUserSalary%>');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userexperience');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            </div>
            <br>
<table border="0" width="100%">


		<%
		
		if(userExpList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">

				<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form,'userexperience')" name="masterCheck"></td>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.WorkExperience.Employer",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.WorkExperience.Job_Title",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.WorkExperience.Start_date",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.WorkExperience.End_date",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.WorkExperience.City",user1.getLocale())%></b></td>
			
			</tr>
		<%
			for(int i = 0; i< userExpList.size(); i++){
				OrganizationDetails organizationDetails = (OrganizationDetails)userExpList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserSalaryURl = request.getContextPath()+"/userexperience.do?method=editUserExperienceDetails&orgDetailsId="+organizationDetails.getOrgDetailsId() +"&userId="+cform.getUserId();		
				
		%>
		

			<tr bgcolor=<%=bgcolor%>>

				<td align="center"><input type="checkbox" id="userexpIsActive_<%=organizationDetails.getOrgDetailsId()%>"  name="userexpIsActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserWorkExperience('<%=editUserSalaryURl%>');return false"><%=organizationDetails.getPrevOrgName()  == null ?"":organizationDetails.getPrevOrgName()%></a></td>
				<td>&nbsp;<%=organizationDetails.getRole() == null ?"":organizationDetails.getRole() %></td>
				<td>&nbsp;<%=organizationDetails.getStartdate() == null ?"":organizationDetails.getStartdate() %></td>
				<td>&nbsp;<%=organizationDetails.getEnddate() == null ?"":organizationDetails.getEnddate()%></td>
				<td>&nbsp;<%=organizationDetails.getCity() == null ?"":organizationDetails.getCity()%></td>

				
			</tr>

			
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