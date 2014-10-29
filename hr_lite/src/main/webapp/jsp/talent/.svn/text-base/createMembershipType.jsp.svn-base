<%@ include file="../common/include.jsp" %>
 <html>
  <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<bean:define id="cform" name="membershipTypeForm" type="com.form.MembershipTypeForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}

function closewindow()
		{
	// self.parent.location.reload();
	 parent.parent.GB_hide();
	}
function init(){
	setTimeout ( "document.membershipTypeForm.membershipTypeName.focus(); ", 200 );

}

function savedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.membershipTypeForm.membershipTypeName.value.trim();
	var desc=document.membershipTypeForm.membershipTypeDesc.value.trim();


			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.membershiptype.name.required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.membershiptype.desc.required",user1.getLocale())%><br>";
		showalert = true;
		}


		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.membershiptype.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	 	document.membershipTypeForm.action = "membershiptypes.do?method=saveMembershipType";
 		document.membershipTypeForm.submit();


 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.membershipTypeForm.membershipTypeName.value.trim();
	var desc=document.membershipTypeForm.membershipTypeDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.membershiptype.name.required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.membershiptype.desc.required",user1.getLocale())%><br>";
		showalert = true;
		}

		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.membershiptype.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	 	document.membershipTypeForm.action = "membershiptypes.do?method=updateMembershipType&membershipTypeId="+'<bean:write name="membershipTypeForm" property="membershipTypeId"/>';
 		document.membershipTypeForm.submit();

 
	}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
	document.membershipTypeForm.action = "membershiptypes.do?method=deleteMembershipType&membershipTypeId="+'<bean:write name="membershipTypeForm" property="membershipTypeId"/>';
	document.membershipTypeForm.submit();
	 }
}

</script>

<%
String membershipTypeSaved = (String)request.getAttribute("membershipTypeSaved");
	
if(membershipTypeSaved != null && membershipTypeSaved.equals("yes")){
	%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.membershiptype.saved",user1.getLocale())%> </font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String membershipTypeupdated = (String)request.getAttribute("membershipTypeupdated");
	
if(membershipTypeupdated != null && membershipTypeupdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.membershiptype.updated",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String membershipTypedeleted = (String)request.getAttribute("membershipTypedeleted");
	
if(membershipTypedeleted != null && membershipTypedeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.membershiptype.deleted",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>
<%}else{%>	

<body onload="init()">

<html:form action="/membershiptypes.do?method=saveMembershipType">

	<font color = red ><html:errors /> </font>
	<br>
	<table border="0" width="100%">

			

	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.membershiptype.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="membershipTypeName" size="50" maxlength="300"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.membershiptype.desc",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="membershipTypeDesc" cols="47" rows="5"/></td>
		</tr>
		
	</table>
			<br><br>
	<table border="0" width="100%">
		<tr>
			<td><% if (cform.getMembershipTypeId() != 0){%>			
			
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()">		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"></td>
			<%}%>
			<td></td>
		</tr>

		
	</table>
		


</html:form>

</body>
<%}%>
