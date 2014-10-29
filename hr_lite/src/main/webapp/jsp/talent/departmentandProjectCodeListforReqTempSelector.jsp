<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/lov.do?method=logon">



<span id="departments">
				
				<html:select property="departmentId" onchange="retrieveProjcodes('lov.do?method=loadProjectCode');">
				<option value=""></option>
				<bean:define name="lovForm" property="departmentList" id="departmentList" />
            	<html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			
				</html:select>
				<span class="textboxlabel" id="loadingp" STYLE="font-size: smaller;Visibility:hidden";>
					<br>	<%=Constant.getResourceStringValue("admin.ProjectCode.Loadpcode",user1.getLocale())%>......</br></span>

</span>
</html:form>
</html:html>