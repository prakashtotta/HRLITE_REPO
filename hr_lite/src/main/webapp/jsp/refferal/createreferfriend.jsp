<%@ include file="../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="aForm" name="referAFriendForm" type="com.form.ReferAFriendForm" />
<html>
<head>
<script language="javascript">
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
			 parent.parent.GB_hide();
	   } 
	}
function  closewindow(){
	parent.parent.GB_hide();
	
}

function savedata(){
    var alertstr = "";
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	var name = document.referAFriendForm.name.value;
	var emailId = document.referAFriendForm.emailId.value;
	var showalert=false;
	
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(reg.test(emailId) == false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
	     	alert(alertstr);
	        return false;
	 }

	  document.referAFriendForm.action = "referfriend.do?method=savefriendsdata";
	  document.referAFriendForm.submit();
}

function updatedata(){
    var alertstr = "";
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	var name = document.referAFriendForm.name.value;
	var emailId = document.referAFriendForm.emailId.value;
	var showalert=false;
	
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(reg.test(emailId) == false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
	     	alert(alertstr);
	        return false;
	 }
	  document.referAFriendForm.action = "referfriend.do?method=updatefriendsdata&referafriendId="+<%=aForm.getReferafriendId()%>;
	  document.referAFriendForm.submit();
}

function deletedata(){
	  document.referAFriendForm.action = "referfriend.do?method=deletefriendsdata&referafriendId="+<%=aForm.getReferafriendId()%>;
	  document.referAFriendForm.submit();
}
function init(){
	setTimeout ( "document.referAFriendForm.name.focus(); ", 200 );
	}
</script>
<style type="text/css">

table.ex2
{
border-collapse:separate;
border-spacing:25px 3px;
}
span1{color:#ff0000;}
</style>

</head>
<%
String isfriendadded = (String)request.getAttribute("isfriendadded");
String isFriendexist= (String)request.getAttribute("isFriendexist");
String editfriends = (String)request.getAttribute("editfriends");
String isfriendupdated = (String)request.getAttribute("isfriendupdated");
String deletefriend= (String)request.getAttribute("deletefriend");
String requistionclosed= (String)request.getAttribute("requistionclosed");
%>
<%
if(isfriendadded != null && isfriendadded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("refferal.referafriend.addDatamsg",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
if(isFriendexist != null && isFriendexist.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("refferal.referafriend.friendalreadexists",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
if(isfriendupdated != null && isfriendupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("refferal.referafriend.updateDatamsg",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
if(deletefriend != null && deletefriend.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("refferal.referafriend.deleteDatamsg",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%} else {%>

<br>

<body class="yui-skin-sam" onload="init()">
<br>

<html:form action="/referfriend.do?method=savefriendsdata" >
<div align="center" class="div">
	 <table >

	 <%
if(requistionclosed != null && requistionclosed.equals("yes")){
%>

	
<%=Constant.getResourceStringValue("refferal.referafriend.refering.job.closed",user1.getLocale())%>	
	<br>	


<%}else if(aForm.getJobRequisitionId()>0){%>
		<tr>
			<td><%=Constant.getResourceStringValue("refferal.referafriend.refering.jobtitle",user1.getLocale())%></td>
			<td><%=aForm.getJobTitle()%></td>
			<html:hidden property="jobRequisitionId"/>
			<html:hidden property="jobTitle" />
		</tr>
<%}%>

 		<tr>
			<td><%=Constant.getResourceStringValue("refferal.referafriend.Name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="name" maxlength="500" size="60"/></td>
		</tr>
		 		<tr>
			<td><%=Constant.getResourceStringValue("refferal.referafriend.emailid",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="emailId" maxlength="500" size="60"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("refferal.referafriend.notes",user1.getLocale())%></td>
			<td><html:textarea property="note" cols="57" rows="5"/></td>
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
			<tr>
				<td></td>
				<td >
				<%if(editfriends != null && editfriends.equals("yes")){%>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("refferal.referafriend.resend",user1.getLocale())%>" onClick="updatedata()" class="button">&nbsp;
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">&nbsp;
				<%}else{%>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("refferal.referafriend.send",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;
				<%}%>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
				
				</td>
			</tr>
		</table>
		
	</div>

</html:form>
</body>


<%} %>
</html>