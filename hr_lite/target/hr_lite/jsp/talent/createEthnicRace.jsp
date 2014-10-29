<%@ include file="../common/include.jsp" %>

<bean:define id="eForm" name="ethnicRaceForm" type="com.form.EthnicRaceForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
 <html>
  <head></head>


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


}

function savedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.ethnicRaceForm.ethnicRaceName.value.trim();
	var decs=document.ethnicRaceForm.ethnicRaceDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.ethnic.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.ethnic.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.ethnicRaceForm.action = "ethnicrace.do?method=saveEthnicRace";
	  document.ethnicRaceForm.submit();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;

	var name = document.ethnicRaceForm.ethnicRaceName.value.trim();
	var decs=document.ethnicRaceForm.ethnicRaceDesc.value.trim();

			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.ethnic.name.required",user1.getLocale())%><br>";
		showalert = true;
	}
	
	if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.ethnic.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
	}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.ethnicRaceForm.action = "ethnicrace.do?method=updateEthnicRace&ethnicRaceId=<%=eForm.getEthnicRaceId() %>";
	  document.ethnicRaceForm.submit();

	}

function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
		 if (doyou == true){

				  document.ethnicRaceForm.action = "ethnicrace.do?method=deleteEthnicRace&fromwhere=lov&ethnicRaceId=<%=eForm.getEthnicRaceId()%>";
				  document.ethnicRaceForm.submit();
		
	 	}
	 }
</script>

<%
String ethnicracesaved = (String)request.getAttribute("ethnicracesaved");
	
if(ethnicracesaved != null && ethnicracesaved.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.ethnic.saved",user1.getLocale())%> </font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String ethnicraceupdated = (String)request.getAttribute("ethnicraceupdated");
	
if(ethnicraceupdated != null && ethnicraceupdated.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.ethnic.updated",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String ethnicracedeleted = (String)request.getAttribute("ethnicracedeleted");
	
if(ethnicracedeleted != null && ethnicracedeleted.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.ethnic.deleted",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>
	<%}else{%>	

<body onload="init()" >

<html:form action="/ethnicrace.do?method=saveEthnicRace">
<br>
	<font color = red ><html:errors /> </font>
	<div class="div">
<table border="0" width="100%">


			<tr>
				<td width="30%"><%=Constant.getResourceStringValue("admin.ethnic.name",user1.getLocale())%><font color="red">*</font></td>
				<td><html:text property="ethnicRaceName" size="50" maxlength="300"/></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.ethnic.desc",user1.getLocale())%></td>
				<td><html:textarea property="ethnicRaceDesc" cols="47" rows="5"/></td>
			</tr>


			
</table>

<br><br>
<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(eForm.getEthnicRaceId() != 0){
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
		</tr>
		</table>
</div>
</html:form>

</body>
<%}%>