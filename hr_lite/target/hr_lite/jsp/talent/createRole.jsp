<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<style>
span1{color:#ff0000;}

</style>
<bean:define id="aform" name="roleForm" type="com.form.RoleForm" />
<SCRIPT>
		function listbox_move(listID, direction) {

			var listbox = document.getElementById(listID);
			var selIndex = listbox.selectedIndex;

			if(-1 == selIndex) {
				alert("Please select an option to move.");
				return;
			}

			var increment = -1;
			if(direction == 'up')
				increment = -1;
			else
				increment = 1;

			if((selIndex + increment) < 0 ||
				(selIndex + increment) > (listbox.options.length-1)) {
				return;
			}

			var selValue = listbox.options[selIndex].value;
			var selText = listbox.options[selIndex].text;
			listbox.options[selIndex].value = listbox.options[selIndex + increment].value
			listbox.options[selIndex].text = listbox.options[selIndex + increment].text

			listbox.options[selIndex + increment].value = selValue;
			listbox.options[selIndex + increment].text = selText;

			listbox.selectedIndex = selIndex + increment;
		}

		function listbox_moveacross(sourceID, destID) {
			var src = document.getElementById(sourceID);
			var dest = document.getElementById(destID);

			for(var count=0; count < src.options.length; count++) {

				if(src.options[count].selected == true) {
						var option = src.options[count];

						var newOption = document.createElement("option");
						newOption.value = option.value;
						newOption.text = option.text;
						newOption.selected = true;
						try {
								 dest.add(newOption, null); //Standard
								 src.remove(count, null);
						 }catch(error) {
								 dest.add(newOption); // IE only
								 src.remove(count);
						 }
						count--;

				}

			}

		}
		function listbox_selectall(listID, isSelect) {

			var listbox = document.getElementById(listID);
			for(var count=0; count < listbox.options.length; count++) {

				listbox.options[count].selected = isSelect;

			}
		}


</script>


<script language="javascript">
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
		
 self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function closewindow(){
	 //self.parent.location.reload();
	 parent.parent.GB_hide();
}

 function savedata(){
	 var todata = "";
	var fromdata = "";
	var listbox = document.getElementById('d');
	var listbox1 = document.getElementById('s');
	
	 var name=document.roleForm.roleName.value.trim();
	var code=document.roleForm.roleCode.value.trim();
	var desc=document.roleForm.roleDesc.value.trim();
	var alertstr="";
	var showalert=false;
	 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.role.rolename.mandatory",user1.getLocale())%><BR>";
     	showalert = true;
		}
	if(listbox == "" || listbox == null){
     	alertstr = alertstr + "Please select permissions<BR>";
     	showalert = true;
		}

	/*if(code == "" || code == null)
	{
 	alertstr = alertstr+"<%=Constant.getResourceStringValue("admin.role.rolecode.mandatory",user1.getLocale())%><BR>";
	document.getElementById('roleCode').value = ""; 
    document.getElementById('roleCode').focus();
 	showalert = true;
		}*/
	if(code == "" || code == null){
	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.role.rolecode.mandatory",user1.getLocale())%><br>";
	showalert = true;
	}	

	if(desc == "" || desc == null)
	{
 	alertstr = alertstr+"<%=Constant.getResourceStringValue("validation.Disc_mandatory",user1.getLocale())%><BR>";
 	showalert = true;
		}
	if(listbox.length == 0)
	{
 	alertstr = alertstr+"<%=Constant.getResourceStringValue("admin.role.permissions.mandatory",user1.getLocale())%><BR>";
 	showalert = true;
	}

	
		if(document.roleForm.roleDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	
			for(var count=0; count < listbox.options.length; count++) {

				todata = todata + listbox.options[count].value + ",";

			}
			for(var count=0; count < listbox1.options.length; count++) {

				fromdata = fromdata + listbox1.options[count].value + ",";

			}
 document.roleForm.action = "role.do?method=saveRole&todata="+todata+"&fromdata="+fromdata;
 document.roleForm.submit();

 

 
	}


	
	

 function updatedata(){
    var todata = "";
	var fromdata = "";
	var listbox = document.getElementById('d');

	var listbox1 = document.getElementById('s');
	 var name=document.roleForm.roleName.value.trim();
		var code=document.roleForm.roleCode.value.trim();
		var desc=document.roleForm.roleDesc.value.trim();
		var alertstr="";
		var showalert=false;

	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.role.rolename.mandatory",user1.getLocale())%><BR>";
     	showalert = true;
		}
	if(code == "" || code == null)
	{
 	alertstr = alertstr+"<%=Constant.getResourceStringValue("admin.role.rolecode.mandatory",user1.getLocale())%><BR>";
 	showalert = true;
		}
	if(desc == "" || desc == null)
	{
 	alertstr = alertstr+"<%=Constant.getResourceStringValue("validation.Disc_mandatory",user1.getLocale())%><BR>";
 	showalert = true;
		}
		if(document.roleForm.roleDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
		

		if(listbox.length == 0){
	 	alertstr = alertstr+"<%=Constant.getResourceStringValue("admin.role.permissions.mandatory",user1.getLocale())%><BR>";
	 	showalert = true;
		}
		
	if (showalert){
     	alert(alertstr);
        return false;
          }
	
			for(var count=0; count < listbox.options.length; count++) {

				todata = todata + listbox.options[count].value + ",";

			}
			for(var count=0; count < listbox1.options.length; count++) {

				fromdata = fromdata + listbox1.options[count].value + ",";

			}
 document.roleForm.action = "role.do?method=updateRole&todata="+todata+"&fromdata="+fromdata+"&roleid="+'<bean:write name="roleForm" property="roleId"/>';
 document.roleForm.submit();

 

 
	}

function init(){
setTimeout ( "document.roleForm.roleCode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
</script>


<%
String updateRole = (String)request.getAttribute("updateRole");
	
if(updateRole != null && updateRole.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.role.savemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<br><br>
<%}%>

<%
List fromColumnsList = aform.getFromColumnsList();
List toColumnsList = aform.getToColumnsList();
%>

<html:form action="/role.do?method=saveRole" >
<body onload="init()">
	<font color = red ><html:errors /> </font>
	<div  class="div">

	<table border="0" width="100%">

	
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.rolecode",user1.getLocale())%><font color="red">*</font> </td>
			<td><html:text property="roleCode" size="60" maxlength="200" /></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.rolename",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="roleName" size="60" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.desc",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="roleDesc" cols="60" rows="5" /></td>
		</tr>

</table>
<table  border="0" width="100%">

<tr>
<td></td>
<td><b><%=Constant.getResourceStringValue("avilable.permissions",user1.getLocale())%></b></td>
<td></td>
<td><b><%=Constant.getResourceStringValue("selected.permissions",user1.getLocale())%></b></td>
</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.role.permissions",user1.getLocale())%><font color="red">*</font></td>
			



<td valign="left">

<SELECT id="s" size="25" multiple>
<% 
for(int i=0;i<fromColumnsList.size();i++){
KeyValue kv = (KeyValue)fromColumnsList.get(i);
%>
	<OPTION value="<%=kv.getKey()%>"><%=kv.getValue()%></OPTION>
<%}%>
</SELECT>
</td>
<td valign="center" width=40 >
<a class="button" href="#" onclick="listbox_moveacross('s', 'd');return false;">&gt;&gt;</a>
<br/><br/><br/>
<a class="button" href="#" onclick="listbox_moveacross('d', 's');return false;">&lt;&lt;</a>
</td>
<td valign="left">

<SELECT  id="d"  size="25" multiple>
<% 
for(int i=0;i<toColumnsList.size();i++){
KeyValue kv1 = (KeyValue)toColumnsList.get(i);


%> 

   
	<OPTION  name="tolist" value="<%=kv1.getKey()%>"><%=kv1.getValue()%></OPTION>
	
<%}%>

</SELECT>
</td>

			
</tr>

		<tr>
			<td colspan="2">
			<div>
			<logic:notEqual  name="roleForm" property="roleId" scope="request"  value="0">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%> " onClick="updatedata()" class="button">
			</logic:notEqual>
			<logic:equal name="roleForm" property="roleId" scope="request" value="0">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%> " onClick="savedata()" class="button">
			</logic:equal>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%> " onClick="discard()" class="button">
			</div>
			</td>
			
			<td></td>
		</tr>

	</table>
</div>
</body>
</html:form>

