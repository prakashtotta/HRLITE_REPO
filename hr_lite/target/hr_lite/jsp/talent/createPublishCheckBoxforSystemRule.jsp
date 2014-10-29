<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html:html>
<bean:define id="systemform" name="systemRuleForm" type="com.form.SystemRuleForm" />

<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/sysrule.do?method=logon" >

			<span id="publish">
			<tr>
           
		   <td>
			<%=Constant.getResourceStringValue("rule.publishToExternal",user1.getLocale())%>
	       <%
		if(systemform.getPublishToExternal()!=null && systemform.getPublishToExternal().equals("Y")){%>
         <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" checked="true"/>
		<%}else{%>
		 <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" />
		<% }%>
		  <%=Constant.getResourceStringValue("rule.publishToEmpRef",user1.getLocale())%>
		 <%
		 if(systemform.getPublishToEmpRef()!=null && systemform.getPublishToEmpRef().equals("Y")){%>
		 <input type="checkbox" name="publishToEmpRef" id="publishToEmpRef"  value="Y" checked="true"/>
		<% }else{%>
		 <input type="checkbox" name="publishToEmpRef" id="publishToEmpRef"  value="Y" /></td>
		<% }%>
		</tr>
	       </span>


</html:form>
</html:html>
