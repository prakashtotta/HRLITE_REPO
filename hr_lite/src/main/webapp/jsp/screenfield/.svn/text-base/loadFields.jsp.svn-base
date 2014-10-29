<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.filter.*" %>

<bean:define id="screenForm" name="screenFieldForm" type="com.form.ScreenFieldForm" />
<%
System.out.println("loadFields.jsp .... ");
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
//System.out.println("field list : "+screenForm.getScreenfieldsList());

%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>

<%
ScreenFields screenFields= null;

%>

<html:form action="/screenfield.do?method=loadFields" >
<span id ="fields">
<div class="div">
<table>
	<tr>

		<td width="10%"><b><%=Constant.getResourceStringValue("admin.screenfield.Field_Name",user1.getLocale())%></b></td>
		<td width="10%" align="center"><b><%=Constant.getResourceStringValue("admin.screenfield.isVisible",user1.getLocale())%> </b></td>
		<td width="20%" align="center"><b><%=Constant.getResourceStringValue("admin.screenfield.isMandatory",user1.getLocale())%></b> </td>
	</tr>
	<tr></tr><tr></tr><tr></tr><tr></tr>
	<%
	List fieldList = screenForm.getScreenfieldsList();
	Iterator itr = fieldList.iterator(); 
	for(int i = 0; i < screenForm.getScreenfieldsList().size(); i++){ 
		screenFields = (ScreenFields)fieldList.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	
	<tr bgcolor=<%=bgcolor%>>
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
		
		<td width="10%"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="10%" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>','isvisible_');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>','isMandatory_');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="20%" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
	
	<tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>

			<td>
		
						<% if(screenForm.getScreenfieldsList().size() > 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="savedata()" class="button">
			<%} %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			
	</tr>

</table>
</div>
</span>

			

</html:form>
</html:html>