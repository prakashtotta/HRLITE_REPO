<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="locationForm" type="com.form.LocationForm" />
<html:form action="/location.do?method=searchgroupchars">
<div class="div">

	<table border="0" width="100%">
    
    <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%> :</td>
				<td><bean:write name="locationForm" property="locationName"/></td>
			</tr>

	    <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.locationcode",user1.getLocale())%> :</td>
				<td><bean:write name="locationForm" property="locationCode"/></td>
			</tr>	
			
			 <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.address",user1.getLocale())%> :</td>
				<td><bean:write name="locationForm" property="address"/> <br>
				<bean:write name="locationForm" property="city"/><br>
                 <bean:write name="locationForm" property="stateValue"/><br>
				 <bean:write name="locationForm" property="countryValue"/><br>
				 <bean:write name="locationForm" property="zip"/>
				</td>
			</tr>	

			 <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.phone",user1.getLocale())%> :</td>
				<td> <bean:write name="locationForm" property="phone"/></td>
			</tr>	

			 <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.fax",user1.getLocale())%> :</td>
				<td><bean:write name="locationForm" property="fax"/></td>
			</tr>	

			 <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.notes",user1.getLocale())%> :</td>
				<td><bean:write name="locationForm" property="notes"/></td>
			</tr>	

			 <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.createdby",user1.getLocale())%> :</td>
				<td><bean:write name="locationForm" property="createdBy"/></td>
			</tr>
			<tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.Location.createddate",user1.getLocale())%> :</td>
				<td><bean:write name="locationForm" property="createdDate"/></td>
			</tr>	
				
</table>
</div>


</html:form>