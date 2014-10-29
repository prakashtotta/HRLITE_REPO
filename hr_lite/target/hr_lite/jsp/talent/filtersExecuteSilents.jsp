<%@ page import="java.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%
    User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
	List errorList = (List)request.getAttribute(Common.ERROR_LIST);
	if(errorList != null && errorList.size()>0){%>
<div align="center">
	<table border="0" width="100%">
	
<% for(int i=0;i<errorList.size();i++){
	String errorval = (String)errorList.get(i);
%>	
	        <tr>
			<td align="left"><font color="red"><%=errorval%></font></td>
			<td></td>
			</tr>
		
<%}%>
		
	</table>
</div>
<%}else{%>

<%=Constant.getResourceStringValue("aquisition.applicant.filters.no.error",user1.getLocale())%>
<%}%>