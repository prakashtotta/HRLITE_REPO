<%@ include file="../common/include.jsp" %>
 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>

<%
String saverecruiterajax = (String)request.getAttribute("saverecruiterajax");
%>

<html:form action="/jobreq.do?method=saverecruiterajax" >

<span id ="recruitersave">
<%
if(saverecruiterajax != null && saverecruiterajax.equals("yes")){
%>	
<font color="green"><%=Constant.getResourceStringValue("Requisition.saved_successfully",user1.getLocale())%></font>
<%}else{%>
<font color="red"><%=Constant.getResourceStringValue("Requisition.error_on_save",user1.getLocale())%></font>
<%}%>
</span>
</html:form>
</html:html>


