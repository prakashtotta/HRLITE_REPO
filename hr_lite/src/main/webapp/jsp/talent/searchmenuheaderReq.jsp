<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String reqid = (String)request.getAttribute("reqid");
String searchpage = (String)request.getAttribute("searchpage");

%>

<script language="javascript">
var searchpage = "<%=searchpage%>";
var returnVal = "something11";
returnVal = "<%=reqid%>";
if(returnVal != "0"){
	window.top.setReqMenuValue(returnVal);
}else{
	window.top.setReqMenuSearchValue(searchpage);
}
	window.top.hidePopWin(true);
	//return false;
</script>