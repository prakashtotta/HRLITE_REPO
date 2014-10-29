<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.lov.*" %>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.filter.*" %>
<%
System.out.println(" createbusinessrule.jsp .... ");
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


%>
<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />


<body class="yui-skin-sam" >

<html:form action="/businessrule.do?method=editFilter">

<br>
<div class="div">
<table >
	<tr>
		
		<td><%=Constant.getResourceStringValue("admin.businessRule.name",user1.getLocale())%></td><td> : </td>
		<td><bean:write name="businessRuleForm" property="name" /></td>
	</tr>
	<tr>
		
		<td><%=Constant.getResourceStringValue("admin.businessRule.desc",user1.getLocale())%> </td><td>: </td>

		<td><bean:write name="businessRuleForm" property="desc" /></td>
	</tr>
	
	<tr>
		
		<td><%=Constant.getResourceStringValue("admin.screenfield.Field_Name",user1.getLocale())%> </td><td>: </td>
		<td><bean:write name="businessRuleForm" property="variableName" /></td>
	</tr>
	<tr>
		<td><%=Constant.getResourceStringValue("admin.businessRule.criteria",user1.getLocale())%> </td><td>:</td>
		<td><bean:write name="businessRuleForm" property="variableCriteria" /></td>
	</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("admin.businessRule.filterValue",user1.getLocale())%> </td><td>:</td>
		<td>
<%if(bForm.getVariableType().equals("date") ){%>
<%=DateUtil.convertDateToStringDate(bForm.getFilterValueDate1(),datepattern)%>
<%}else{%>
<bean:write name="businessRuleForm"  property="filterValue1" />
<%}%>

	
<%if(bForm.getVariableCriteria().equals("BETWEEN") ){ %>
		&nbsp;
		<%if(bForm.getVariableType().equals("date") ){%>
		<%=DateUtil.convertDateToStringDate(bForm.getFilterValueDate2(),datepattern)%>
			<%}else{%>
			
			<bean:write name="businessRuleForm"  property="filterValue1" />
			<%}%>
		<%}%>
		
	</tr>
	
</table>

</html:form>
</body>