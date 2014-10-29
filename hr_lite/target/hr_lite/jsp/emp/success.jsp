<%@ include file="../common/include.jsp" %>
 <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<script language="javascript">


function closewindow(){

	 parent.parent.GB_hide();
 //window.top.hidePopWin();
		   
	  
	}
</script>
<%
String isVendordeleted = (String)request.getAttribute("isVendordeleted");
	
if(isVendordeleted != null && isVendordeleted.equals("yes")){
%>
<div align="center" class="msg"">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.Vendor_deleted_succ",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String isuserdeleted = (String)request.getAttribute("isuserdeleted");
	
if(isuserdeleted != null && isuserdeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.user_deleted_succ",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --> </td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String isVendorsuspended = (String)request.getAttribute("isVendorsuspended");
	
if(isVendorsuspended != null && isVendorsuspended.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.Vendor_suspended_succ",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String isusersuspended = (String)request.getAttribute("isusersuspended");
	
if(isusersuspended != null && isusersuspended.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.user_suspended_succ",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
String isVendoractivated = (String)request.getAttribute("isVendoractivated");
	
if(isVendoractivated != null && isVendoractivated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.Vendor_activated_succ",user1.getLocale())%></font></td>
			<td> <!--<a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String isuseractivated = (String)request.getAttribute("isuseractivated");
	
if(isuseractivated != null && isuseractivated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.user_activated_succ",user1.getLocale())%></font></td>
			<td> <!--<a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>



