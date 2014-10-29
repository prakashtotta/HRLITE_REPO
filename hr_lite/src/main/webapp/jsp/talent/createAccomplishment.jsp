<%@ include file="../common/include.jsp" %>
<%@ page import="com.form.*" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="aform" name="AccomplishmentForm" type="com.form.AccomplishmentForm" />
<style>
span1{color:#ff0000;}
</style>

<script language="javascript">
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function discard1(){
	 //self.parent.location.reload();
	 parent.parent.GB_hide();
	}
function init(){
setTimeout ( "document.AccomplishmentForm.accName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	var alertstr = "";
	var showalert=false;
	var accName=document.AccomplishmentForm.accName.value.trim(); 
	var desc = document.AccomplishmentForm.accDesc.value.trim();
	
	if(accName == "" || accName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Accomplishment.Name_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Accomplishment.desc_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	 if (showalert){
	     	alert(alertstr);
	        return false;
	   }
	document.AccomplishmentForm.action = "accomplishment.do?method=saveAccomplishment&readPreview=1";
	document.AccomplishmentForm.submit();
	//self.parent.location.reload();
	 
	}
function updatedata(){
	var alertstr = "";
	var showalert=false;
	var accName=document.AccomplishmentForm.accName.value.trim(); 
	var desc = document.AccomplishmentForm.accDesc.value.trim();
	
	if(accName == "" || accName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Accomplishment.Name_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Accomplishment.desc_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	 if (showalert){
	     	alert(alertstr);
	        return false;
	   }
	document.AccomplishmentForm.action = "accomplishment.do?method=updateAccomplishment&readPreview=2&id="+'<bean:write name="AccomplishmentForm" property="accId"/>';
	 document.AccomplishmentForm.submit();
	 //self.parent.location.reload();
	 
	}

function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	document.AccomplishmentForm.action = "accomplishment.do?method=deleteAccomplishment&id="+'<bean:write name="AccomplishmentForm" property="accId"/>';

	document.AccomplishmentForm.submit();
	  }
	
}

</script>
<%
String saveaccomplishment = (String)request.getAttribute("saveaccomplishment");
	
if(saveaccomplishment != null && saveaccomplishment.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Accomplishment.savemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateaccomplishment = (String)request.getAttribute("updateaccomplishment");
	
if(updateaccomplishment != null && updateaccomplishment.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Accomplishment.updatemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String Accomplishmentdeleted = (String)request.getAttribute("deleteaccomplishment");
	
if(Accomplishmentdeleted != null && Accomplishmentdeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Accomplishment.deletemsg",user1.getLocale())%></font></td>
			<td><!--   <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>

<%}else{%>

<body onload="init()" >
<html:form action="/accomplishment.do?method=saveAccomplishment">


<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	
	
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Accomplishment.Name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="accName" maxlength="500"/></td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Accomplishment.Description",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="accDesc" cols="40" rows="5" /></td>
		</tr>
		

	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	       
	<tr><td colspan="2"><% if(aform.getReadPreview().equals("2")){%>
	        <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	      <%} else {%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			
			
		<%}%>
			
			<td></td>
		</tr>

		</table>
</div>

</html:form>
</body>


	<%}%>	
