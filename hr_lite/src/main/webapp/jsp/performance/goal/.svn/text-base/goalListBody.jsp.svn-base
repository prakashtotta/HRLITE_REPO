<%@ include file="../../common/include.jsp" %>
<%@ include file="../../common/greybox.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">

function editbudgetCode(budget_id){
	var url = "<%=request.getContextPath()%>/budgetcode.do?method=editbudgetCode&readPreview=1&id="+budget_id;
	//parent.showPopWin(url, 500, 250, showmessage,true);
	GB_showCenter('<%=Constant.getResourceStringValue("admin.BudgetCode.editbcodescreenname",user1.getLocale())%>',url,500,750, messageret);
}
function createGoal(){
	var url = "<%=request.getContextPath()%>/goal.do?method=createOrgGoal";
	location.href=url;
	//GB_showFullScreen('<%=Constant.getResourceStringValue("add.goal",user1.getLocale())%>',url,messageret);
}

function messageret(){
	window.location.reload();

			}

function showmessage(returnval) { 
	window.location.reload();
	}

</script>

<body class="yui-skin-sam">
<br>
 <%=Constant.getResourceStringValue("goals",user1.getLocale())%>  <br><br>

<a  href="#" onClick="createGoal()"><%=Constant.getResourceStringValue("add.goal",user1.getLocale())%> </a>