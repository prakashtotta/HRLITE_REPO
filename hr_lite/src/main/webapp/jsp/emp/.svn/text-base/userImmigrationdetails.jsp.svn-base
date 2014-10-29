<%@ include file="../common/include.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userImigrationsForm" type="com.form.employee.UserImigrationsForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
List imigrationList = cform.getUserImigrationList();
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
<html:form action="/userimmigration.do?method=logon" enctype="multipart/form-data">

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Imigration.assignedList",user1.getLocale())%></legend>
					<%
String userImmigrationmultipledeleted = (String)request.getAttribute("userImmigrationmultipledeleted");
	
if(userImmigrationmultipledeleted != null && userImmigrationmultipledeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.userimmigration.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		 String imigrationsids="";
		if(imigrationList.size() > 0){
			for(int i = 0; i< imigrationList.size(); i++){
				Imigrations imigrations = (Imigrations)imigrationList.get(i);
				imigrationsids = imigrationsids+imigrations.getImigrationId()+","; 
		%>
		
		<%}
		}%>
			<div align="left">	
			<a class="button" href="#" onClick="deleteUserDetails('<%=imigrationsids%>','immigration');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
				String addimmigration = request.getContextPath()+"/userimmigration.do?method=addUserImigration&userId="+cform.getUserId();		
			%>
			<a class="button" href="#" onClick="javascript:addUserImigrationinfo('<%=addimmigration%>');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a class="button" href="#" onClick="javascript:retrieveData('immigration');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            </div>
            <br>
	<table border="0" width="100%">


		<%
	
		if(imigrationList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">

		 	<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form,'immigration')" name="masterCheck"></td>
				<td width="20%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Imigration.Document",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Imigration.DocumentNo",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Imigration.IssuedBy",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Imigration.IssuedDate",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Imigration.DateofExpiry",user1.getLocale())%></b></td>
				
			</tr>
		<%
			for(int i = 0; i< imigrationList.size(); i++){
				Imigrations imigrations = (Imigrations)imigrationList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserImmigrationurl = request.getContextPath()+"/userimmigration.do?method=editUserImmigration&imigrationId="+imigrations.getImigrationId();		
				
		%>
		<tr bgcolor=<%=bgcolor%>>

			<td align="center"><input type="checkbox" id="userImmigrationIsActive_<%=imigrations.getImigrationId()%>"  name="userImmigrationIsActive"></td>
			
			<td width="20%">&nbsp;<a href="#" onClick="editImmigration('<%=editUserImmigrationurl%>');return false"><%=imigrations.getPassportVisaType() == null ?"": imigrations.getPassportVisaType()%></a></td>
			<td>&nbsp;<%=imigrations.getPassportNo() == null ?"":imigrations.getPassportNo()%></td>
			
			<td>&nbsp;<%=imigrations.getIssueCountry() == null ?"":imigrations.getIssueCountry().getCountryName() %></td>
			<%
			String issueDate="";
			if( imigrations.getPassportIssueDate() != null){
				
				 if(user1 != null){
				 datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
				 }
				 issueDate=DateUtil.convertDateToStringDate(imigrations.getPassportIssueDate(),datepattern);
				
			}
			%>
			<td>&nbsp;<%=issueDate %></td>
						<%
			String xpiryDate="";
			if( imigrations.getPassportExpiryDate() != null){
				
				 if(user1 != null){
				 datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
				 }
				 xpiryDate=DateUtil.convertDateToStringDate(imigrations.getPassportExpiryDate(),datepattern);
				
			}
			%>
			<td>&nbsp;<%=xpiryDate %></td>
			
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