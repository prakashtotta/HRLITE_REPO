<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/resize.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<style>
    #resize {
        border: 0px solid black;
        height: 100%;
        width: 400px;
        background-color: #fff;
    }
    #resize div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }
</style>

<script language="javascript">

function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	//parent.setPopTitle1("Applicant");
	//parent.showPopWin(url, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}
function createapplicant(){

	var url = "<%=request.getContextPath()%>/applicant.do?method=createPage";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapplicant",user1.getLocale())%>',url,messageret);
}

	
	
	function messageret(){
	window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			}

</script>
<br>
 <%=Constant.getResourceStringValue("aquisition.applicant.list",user1.getLocale())%>  <br><br>



<body class="yui-skin-sam">

<table>

<tr>
<td valign="top">
<div id="resize">
<div style="height: 300px; overflow: scroll;">
<jsp:include page="../jtree/talentPool.jsp" flush="true"/>
</div>
</div>
</td>

<td valign="top">

</td>
</tr>
</table>


<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>

