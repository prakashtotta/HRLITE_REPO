<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
////response.setHeader("Cache-Control", "no-cache");
	//	//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>
<style>
span1{color:#ff0000;}
</style>
<html>
<bean:define id="cform" name="charForm" type="com.form.CharForm" />
<script language="javascript">
var returnVal = "something11";
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		//  self.parent.location.reload();
			 parent.parent.GB_hide(); 
	   }
	   
	   //ShowMessage();
	}

function closewindow2(){
	// self.parent.location.reload();
	 parent.parent.GB_hide();

	  
	}
function init(){
setTimeout ( "document.charForm.charName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){

	var alertstr = "";
	var showalert=false;
		
	var name = document.charForm.charName.value.trim();

			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

		if(document.charForm.charDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}

		
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.charForm.action = "char.do?method=saveChar";
   document.charForm.submit();
  
	}

function updatedata(){

	var alertstr = "";
	var showalert=false;
		
	var name = document.charForm.charName.value.trim();
	
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

		if(document.charForm.charDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.charForm.action = "char.do?method=updateChar&charId=<%=cform.getCharId()%>";
   document.charForm.submit();
  
	}


function deletechar(){
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	 document.charForm.action = "char.do?method=deleteChar&charId=<%=cform.getCharId()%>";
   document.charForm.submit();
	  }
  
}
</script>
<body class="yui-skin-sam" onload="init()" >
<%
String charsaved = (String)request.getAttribute("charsaved");
	
if(charsaved != null && charsaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Competencies.savemsg",user1.getLocale())%> </font></td>
			<td> <!-- <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updatechar = (String)request.getAttribute("updatechar");
	
if(updatechar != null && updatechar.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Competencies.updatemsg",user1.getLocale())%></font></td>
			<td> <!--<a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String chardeleted = (String)request.getAttribute("chardeleted");
	
if(chardeleted != null && chardeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Competencies.deletemsg",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>

<html:form action="/char.do?method=saveChar" >


<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td> </td>
			<td></td>
		</tr>
	  
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Competencies.Competencyname",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="charName" size="40" maxlength="500"/></td>
		</tr>
		<!--<tr>
			<td><%=Constant.getResourceStringValue("admin.Competencies.Weight",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="weight" size="5" maxlength="10"/></td>
		</tr>-->
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Competencies.desc",user1.getLocale())%></td>
			<td><html:textarea property="charDesc" cols="50" rows="5" /></td>
		</tr>
		
		
		<tr>
			<td colspan="2">
			<% if(cform.getCharId() == 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<%} else {%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletechar()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<%}%>
			<td></td>
		</tr>

	</table>
</div>

</html:form>

<%}%>
