<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userEmergencyContactForm" type="com.form.employee.UserEmergencyContactForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" >>>>>>> "+cform.getUserEmergencyContactList().size());
List emergencycontactlist = cform.getUserEmergencyContactList();
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
<html:form action="/useremergencycontact.do?method=logon" enctype="multipart/form-data">

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.assigned.emergency.contact",user1.getLocale())%></legend>

		<%
String emergencyContactsdeleted = (String)request.getAttribute("emergencyContactsdeleted");
	
if(emergencyContactsdeleted != null && emergencyContactsdeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.emergencycontact.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
				<%
		 String emergencycontactids="";
		if(emergencycontactlist.size() > 0){
			for(int i = 0; i< emergencycontactlist.size(); i++){
				EmergencyContact emergencyContact = (EmergencyContact)emergencycontactlist.get(i);
				emergencycontactids = emergencycontactids+emergencyContact.getEmergencyContactId()+","; 
		%>
		
		<%}
		}%>
			<div align="left">		
			<a href="#" onClick="deleteUserDetails('<%=emergencycontactids%>','emergencycontactinfo');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
			String addEmergencycontact = request.getContextPath()+"/useremergencycontact.do?method=addEmergencyContact&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addEmergencycontactinfo('<%=addEmergencycontact%>');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('emergencycontactinfo');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            
            </div>
            <br>
	<table border="0" width="100%">


		<%
	
		if(emergencycontactlist.size() > 0){

			%>
		<tr bgcolor="#bebabe">
	 	<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form,'emergencycontactinfo')" name="masterCheck"></td>
			<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Relativename",user1.getLocale())%></b></td>
			<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Relationship",user1.getLocale())%></b></td>
			<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></b></td>
			<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.MobileNo",user1.getLocale())%></b></td>
			<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.OfficePhone",user1.getLocale())%></b></td>
			
		</tr>
			<%
			for(int i = 0; i< emergencycontactlist.size(); i++){
				EmergencyContact emergencyContact = (EmergencyContact)emergencycontactlist.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editEmergencycontacturl = request.getContextPath()+"/useremergencycontact.do?method=editEmergencyContact&emergencyContactId="+emergencyContact.getEmergencyContactId();		
				
		%>
		<tr bgcolor=<%=bgcolor%>>

		<td align="center"><input type="checkbox" id="emergencyContactIsActive_<%=emergencyContact.getEmergencyContactId()%>"  name="emergencyContactIsActive"></td>
		
			<td width="25%">&nbsp;<a href="#" onClick="editEmergencyContact('<%=editEmergencycontacturl%>');return false"><%=emergencyContact.getName()== null ?"": emergencyContact.getName()%></a></td>
			<td>&nbsp;<%=emergencyContact.getRelationship()== null ?"":emergencyContact.getRelationship()%></td>
			<td>&nbsp;<%=emergencyContact.getPhoneHome()== null ?"":emergencyContact.getPhoneHome()%></td>
			<td>&nbsp;<%=emergencyContact.getMobileNo() == null ?"":emergencyContact.getMobileNo()%></td>
			<td>&nbsp;<%=emergencyContact.getPhoneOffice() == null ?"":emergencyContact.getPhoneOffice()%></td>
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