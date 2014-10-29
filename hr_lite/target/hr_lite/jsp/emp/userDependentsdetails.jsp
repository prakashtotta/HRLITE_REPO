<%@ include file="../common/include.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userDependentsForm" type="com.form.employee.UserDependentsForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
List dependentslist = cform.getUserDependentsList();
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
<html:form action="/userdependents.do?method=save" enctype="multipart/form-data">

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.assigned.dependents",user1.getLocale())%></legend>
					<%
String userdependentsmultipledeleted = (String)request.getAttribute("userdependentsmultipledeleted");
	
if(userdependentsmultipledeleted != null && userdependentsmultipledeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.userdependents.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		 String userdependentsids="";
		if(dependentslist.size() > 0){
			for(int i = 0; i< dependentslist.size(); i++){
				UserDependents userDependents = (UserDependents)dependentslist.get(i);
				userdependentsids = userdependentsids+userDependents.getUserDependentId()+","; 
		%>
		
		<%}
		}%>

	<div align="left">		
			<a href="#" onClick="deleteUserDetails('<%=userdependentsids%>','userdependents');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			
			
				<%
			String addUserdependentsurl = request.getContextPath()+"/userdependents.do?method=addUserdependents&userId="+cform.getUserId();	
			%>
			<a href="#" onClick="javascript:addUserdependents('<%=addUserdependentsurl%>');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userdependents');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
          </div>
            <br>
            <table border="0" width="100%">


		<%
		
		if(dependentslist.size() > 0){
			
		%>
			<tr bgcolor="#bebabe">
			<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form,'userdependents')" name="masterCheck"></td>
			<td width="40%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Relativename",user1.getLocale())%></b></td>
			<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Relationship",user1.getLocale())%></b></td>
			<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.dependents.dob",user1.getLocale())%></b></td>

		</tr>
		<%
			for(int i = 0; i< dependentslist.size(); i++){
				UserDependents userDependents = (UserDependents)dependentslist.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editEmergencycontacturl = request.getContextPath()+"/userdependents.do?method=editUserDependent&userdependentId="+userDependents.getUserDependentId();		
				
		%>
		<tr bgcolor=<%=bgcolor%>>
		<td align="center"><input type="checkbox" id="userdependentsIsActive_<%=userDependents.getUserDependentId()%>"  name="userdependentsIsActive"></td>
			<td width="40%">&nbsp;<a href="#" onClick="editEmergencyContact('<%=editEmergencycontacturl%>');return false"><%=userDependents.getName()== null ?"": userDependents.getName()%></a></td>
			<td>
			<%
			String relationshipOther=userDependents.getRelationship();
			if(!StringUtils.isNullOrEmpty(userDependents.getRelationship()) && userDependents.getRelationship().equals("Other")){
				relationshipOther = relationshipOther+" >> "+userDependents.getRelationshipOther();
			}
			%>
			&nbsp;<%=relationshipOther%>
			</td>
			
			<%
			String dob="";
			if( userDependents.getDateofbirth() != null){
				
				 if(user1 != null){
				 datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
				 }
				 dob=DateUtil.convertDateToStringDate(userDependents.getDateofbirth(),datepattern);
				
			}
			%>
			<td>&nbsp;<%=dob %></td>

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