<%@ include file="../common/yahooincludes.jsp" %>
 <%@ include file="../common/include.jsp" %>

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< add contact >>");
%>

<bean:define id="cform" name="userEmergencyContactForm" type="com.form.employee.UserEmergencyContactForm" />


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
span1{color:#ff0000;}
</style>



<script language="javascript">

function updatedata(){
	var alertstr="";
	var showalert=false;
	var name=document.userEmergencyContactForm.name.value.trim();
	var relationship=document.userEmergencyContactForm.relationship.value.trim();
	
    if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relativename.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(relationship == "" || relationship == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relationship.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userEmergencyContactForm.action = "useremergencycontact.do?method=updateEmergencyContact";
	  document.userEmergencyContactForm.submit();


}

function savedata(){
	var alertstr="";
	var showalert=false;
	var name=document.userEmergencyContactForm.name.value.trim();
	var relationship=document.userEmergencyContactForm.relationship.value.trim();
	
    if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relativename.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(relationship == "" || relationship == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relationship.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userEmergencyContactForm.action = "useremergencycontact.do?method=saveEmergencyContact";
	  document.userEmergencyContactForm.submit();


}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
	  document.userEmergencyContactForm.action = "useremergencycontact.do?method=deleteEmergencyContact";
	  document.userEmergencyContactForm.submit();
 	}
	
}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	   parent.parent.GB_hide(); 
	   } 
	}
function closewindow2(){
	parent.parent.GB_hide();
	 // document.userEmergencyContactForm.action = "useremergencycontact.do?method=emergencyContactdetails";
	 // document.userEmergencyContactForm.submit();
	 
}
</script>
<%
String emergencycontactupdated = (String)request.getAttribute("emergencycontactupdated");
	
if(emergencycontactupdated != null && emergencycontactupdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td align="left"><font color="white"><%=Constant.getResourceStringValue("hr.user.Emergency_Contact_updated",user1.getLocale())%></font></td>
			<td align="left"><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String emergencycontactsaved = (String)request.getAttribute("emergencycontactsaved");
	
if(emergencycontactsaved != null && emergencycontactsaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td align="left"><font color="white"><%=Constant.getResourceStringValue("hr.user.Emergency_Contact_saved",user1.getLocale())%></font></td>
			<td align="left"><!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String emergencycontactdeleted = (String)request.getAttribute("emergencycontactdeleted");
	
if(emergencycontactdeleted != null && emergencycontactdeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td align="left"><font color="white"><%=Constant.getResourceStringValue("hr.user.Emergency_Contact_deleted",user1.getLocale())%></font></td>
			<td align="left"><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}else{
%>

<html:form action="/useremergencycontact.do?method=logon" enctype="multipart/form-data">
<table border="0" width="100%">

	 	<tr>
			<td align="left" width="25%"><%=Constant.getResourceStringValue("hr.user.Relativename",user1.getLocale())%><span1>*</span1></td>
			<td align="left"><html:text property="name" size="40" maxlength="200"/>
			<html:hidden property="userId" />
			<html:hidden property="emergencyContactId" />
			</td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Relationship",user1.getLocale())%><span1>*</span1></td>
			<td align="left"><html:text property="relationship" size="40" maxlength="200"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>
			<td align="left"><html:text property="phoneHome" size="40" maxlength="200"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.MobileNo",user1.getLocale())%></td>
			<td align="left"><html:text property="mobileNo" size="40" maxlength="200"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.OfficePhone",user1.getLocale())%></td>
			<td align="left"><html:text property="phoneOffice" size="40" maxlength="200"/></td>
		</tr>
</table>
<br>
		<table border="0" width="100%">
		<tr>
			<td align="left">
			<%
			if(cform.getEmergencyContactId() != 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%
			}else{
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%
			}
			%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</td>
			<td>
			</td>
		</tr>
		</table>
</html:form>


<%
}
%>