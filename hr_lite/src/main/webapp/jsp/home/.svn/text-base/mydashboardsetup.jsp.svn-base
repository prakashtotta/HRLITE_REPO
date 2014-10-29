<%@ include file="../common/include.jsp" %>
<html>
	
	<bean:define id="aform" name="dashboardForm" type="com.form.DashboardForm" />
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

List fromColumnsList = aform.getReportList();
List toColumnsList = aform.getDashboardReportList();

%>
<script language="javascript">

function discard(){
	  var doyou = confirm("Are you confirm Discard the changes? (OK = Yes   Cancel = No)");
	  if (doyou == true){
	  parent.parent.GB_hide();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
}


	function updatedata(){
		var todata = "";
		var listbox = document.getElementById('d');

				for(var count=0; count < listbox.options.length; count++) {

					todata = todata + listbox.options[count].value + ",";

				}
	
	  document.dashboardForm.action = "dashboard.do?method=mydashboardsetupsubmit&userId="+'<bean:write name="dashboardForm" property="userid"/>'+"&todata="+todata;
   document.dashboardForm.submit();
   
	}



</script>

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
<%
String mydashboardsetup = (String)request.getAttribute("mydashboardsetup");
String exceeddasboard = (String)request.getAttribute("exceeddasboard");

%>
<% if(mydashboardsetup != null && mydashboardsetup.equals("yes")){%>
<font color="green">Dashboard setup done sucessfully.</font> <a href="#" onClick="closewindow()">close window</a>
	
<%}%>
<% if(exceeddasboard != null && exceeddasboard.equals("yes")){%>
<font color="red">Maximum 9 report can be selected for dashboard. Not saved.</font> <a href="#" onClick="closewindow()">close window</a>
	
<%}%>

<html:form action="/dashboard.do?method=mydashboardsetupsubmit" >

<div align="center">

<table cellspacing="10">
<tr>
<td width="20%"></td>
<td></td>
<td></td>
<td></td>
<td width="20%">&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>
<td></td>
<td align="center" width="30%"><b><%=Constant.getResourceStringValue("home.Reports.available.reports",user1.getLocale())%></b></td>
<td></td>
<td align="center" width="30%"><b><%=Constant.getResourceStringValue("home.Reports.selected.reports",user1.getLocale())%></b></td>
</tr>
<tr>

<td width="20%">&nbsp;&nbsp;&nbsp;</td>
<td align="right">

<SELECT id="s" size="10" multiple>
<% 
for(int i=0;i<fromColumnsList.size();i++){
	ReportNames kv = (ReportNames)fromColumnsList.get(i);
%>
	<OPTION value="<%=kv.getReportId() %>"><%=kv.getReportName()%></OPTION>
<%}%>
</SELECT>

</td>
<td valign="middle">
<a href="#" onclick="listbox_moveacross('s', 'd');return false;"><IMG SRC="jsp/images/right.gif" ALT="up" WIDTH=20 height=20/></a>
<br/>
<a href="#" onclick="listbox_moveacross('d', 's');return false;"><IMG SRC="jsp/images/left.gif" ALT="up" WIDTH=20 height=20/></a>
</td>
<td>

<SELECT id="d"  size="10" multiple>
	<% 
for(int i=0;i<toColumnsList.size();i++){
	DashBoardReport kv1 = (DashBoardReport)toColumnsList.get(i);
%>
	<OPTION name="tolist" value="<%=kv1.getReport().getReportId() %>"><%=kv1.getReport().getReportName() %></OPTION>
<%}%>
</SELECT>

</td>
<td width="20%">&nbsp;&nbsp;&nbsp;</td>
</tr>

		<tr>
		<td></td>
			<td>
			
			
			<input type="button" name="login" value="update" onClick="updatedata()">
			<input type="button" name="login" value="cancel" onClick="discard()"></td>
			<td></td>
		</tr>
</table>


</div>

</html:form>