<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="aform" name="configureColumnsForm" type="com.form.ConfigureColumnsForm" />

<%
List fromColumnsList = aform.getFromColumnsList();
List toColumnsList = aform.getToColumnsList();
%>
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

    function updatedata(){
	var todata = "";
	var fromdata = "";
	var listbox = document.getElementById('d');
	var listbox1 = document.getElementById('s');
			for(var count=0; count < listbox.options.length; count++) {

				todata = todata + listbox.options[count].value + ",";

			}
			for(var count=0; count < listbox1.options.length; count++) {

				fromdata = fromdata + listbox1.options[count].value + ",";

			}

 document.configureColumnsForm.action = "configureColumns.do?method=configureColumnsGenericSave&todata="+todata+"&fromdata="+fromdata+"&screenname="+"<%=aform.getScreenName()%>";
 document.configureColumnsForm.submit();

 
	}

	function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
}

	</SCRIPT>
	
</HEAD>

<%
String configurecolumns = (String)request.getAttribute("configurecolumns");
%>
<%if(configurecolumns != null && configurecolumns.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.configure.columns.success",user1.getLocale())%> </font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<br>
<BODY>
<html:form action="/configureColumns.do?method=configureColumnsGenericSave" >
<table>
<tr>
<td width="20%"></td>
<td></td>
<td></td>
<td></td>
<td width="20%">&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>

<td width="20%">&nbsp;&nbsp;&nbsp;</td>
<td>
<%=Constant.getResourceStringValue("avilable.columns",user1.getLocale())%>
<SELECT id="s" size="10" multiple>
<% 
for(int i=0;i<fromColumnsList.size();i++){
KeyValue kv = (KeyValue)fromColumnsList.get(i);
%>
	<OPTION value="<%=kv.getKey()%>"><%=kv.getValue()%></OPTION>
<%}%>
</SELECT>
</td>
<td valign="center">
<a href="#" onclick="listbox_moveacross('s', 'd');return false;"><IMG SRC="jsp/images/right.gif" ALT="up" WIDTH=20 height=20/></a>
<br/>
<a href="#" onclick="listbox_moveacross('d', 's');return false;"><IMG SRC="jsp/images/left.gif" ALT="up" WIDTH=20 height=20/></a>
</td>
<td>
<%=Constant.getResourceStringValue("selected.columns",user1.getLocale())%> &nbsp;&nbsp;&nbsp;
<a href="#" onclick="listbox_move('d', 'up')"><IMG SRC="jsp/images/up.gif" ALT="up" WIDTH=20 height=20/></a>
<a href="#" onclick="listbox_move('d', 'down')"><IMG SRC="jsp/images/down.gif" ALT="down" WIDTH=20 height=20/></a>
<SELECT id="d"  size="10" multiple>
	<% 
for(int i=0;i<toColumnsList.size();i++){
KeyValue kv1 = (KeyValue)toColumnsList.get(i);
%>
	<OPTION name="tolist" value="<%=kv1.getKey()%>"><%=kv1.getValue()%></OPTION>
<%}%>
</SELECT>
</td>
<td width="20%">&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
<tr>
<td></td>
<td colspan="3">
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()">
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
</td></tr>
</table>

</html:form>
<%}%>
</BODY>
</HTML>