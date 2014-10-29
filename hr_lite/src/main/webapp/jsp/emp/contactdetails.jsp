<%@ include file="../common/yahooincludes.jsp" %>
 <%@ include file="../common/include.jsp" %>

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="cform" name="userContactForm" type="com.form.employee.UserContactForm" />


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
</style>
<html:form action="/usercontact.do?method=logon" enctype="multipart/form-data">

<span id="details_data">
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.contact.details",user1.getLocale())%></legend>



	<table border="0" width="50%">


	    <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Other.Email",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="otherEmailId"/></td>
		</tr>
	    <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.MobileNo",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="mobileNo"/></td>
		</tr>
	   <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="phoneHome"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.address1",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="address"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.address2",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="address2"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="city"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="stateName"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="countryName"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.ZipCode",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="zip_code"/></td>
		</tr>

<%
String editcontactinfo = request.getContextPath()+"/usercontact.do?method=editusercontact&userId="+cform.getUserId();
		
%>




	<tr>
	<td align="left"><a href="#" onClick="javascript:editcontactinfo('<%=editcontactinfo%>')"><%=Constant.getResourceStringValue("hr.user.Edit_details",user1.getLocale())%></a>
            </td>
	<td align="left"><a href="#" onClick="javascript:retrieveData('contactinfo');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a></td>
		</tr>

	</table>
</fieldset>
</span>


</html:form>