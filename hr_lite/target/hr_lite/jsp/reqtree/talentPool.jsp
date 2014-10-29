<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ include file="../common/csrfInjection.jsp" %>
<script language="javascript">
window.name = 'myModal';
</script>
<link rel="stylesheet" type="text/css" href="../css/button.css" />

<link rel="stylesheet" type="text/css" href="../yahoo/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../yahoo/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="../yahoo/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="../yahoo/build/resize/assets/skins/sam/resize.css" />
<script type="text/javascript" src="../yahoo/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../yahoo/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../yahoo/build/json/json-min.js"></script>
<script type="text/javascript" src="../yahoo/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="../yahoo/build/element/element-min.js"></script>
<script type="text/javascript" src="../yahoo/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="../yahoo/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="../yahoo/build/datatable/datatable-min.js"></script>



<script type="text/javascript" src="../yahoo/build/element/element-min.js"></script>
<script type="text/javascript" src="../yahoo/build/resize/resize-min.js"></script>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String issession = "true";
if(user1 == null){
	issession="false";
	
	//response.sendRedirect("../common/illegelaccess.jsp");
}else {

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="keywords"  content="" />
<meta name="description" content="" />
<title>Talent Pool</title>
<link rel="stylesheet" type="text/css" href="js/jquery/plugins/simpleTree/style.css" />
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery/plugins/simpleTree/jquery.simple.tree.js"></script>
<script type="text/javascript" src="js/langManager.js" ></script>
<script type="text/javascript" src="js/treeOperations.js"></script>
<script type="text/javascript" src="js/init.js"></script>
</head>
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

<%
String rootdata = TalentPoolBO.getTalentPoolListWithTreeFormat(user1.getUserId());
%>

<body class="yui-skin-sam">
<div class="contextMenu" id="myMenu1">	
		<li class="addFolder">
			<img src="js/jquery/plugins/simpleTree/images/folder_add.png" /> </li>
		<!--<li class="addDoc"><img src="js/jquery/plugins/simpleTree/images/page_add.png" /> </li>	-->
		<li class="edit"><img src="js/jquery/plugins/simpleTree/images/folder_edit.png" /> </li>
		<li class="delete"><img src="js/jquery/plugins/simpleTree/images/folder_delete.png" /> </li>
		<li class="expandAll"><img src="js/jquery/plugins/simpleTree/images/expand.png"/> </li>
		<li class="collapseAll"><img src="js/jquery/plugins/simpleTree/images/collapse.png"/> </li>	
</div>
<div class="contextMenu" id="myMenu2">
		<!--<li class="edit"><img src="js/jquery/plugins/simpleTree/images/page_edit.png" /> </li>-->
		<li class="delete"><img src="js/jquery/plugins/simpleTree/images/page_delete.png" /> </li>
</div>
<table>
<tr>
<td><a href="talentPool.jsp" target='myModal'>refresh</a></td><td width="400px"><td valign="right"><a href="#" onClick="addApplicant();return false">add applicant to talent pool</a></td>
</tr>
</table>
<table>

<tr>
<td valign="top">
<div id="resize">

	<div id="annualWizard">	
			<ul class="simpleTree" id='pdfTree'>		
					<li class="root" id='0'>Talent pools
						<ul>
						
	                  <%=rootdata%>


						</ul>	
					</li>
				
			</ul>
			

	</div>	

</div> 
</td>
<td valign="top">
<%@ include file="/jsp/talentpool/talentPoolSearch.jsp" %> 
</td>
</tr>
</table>




<div id='processing'>
<img src = "jsp/jtree/js/jquery/plugins/simpleTree/images/spinner.gif"/>
</div>


<script>

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var resize = new YAHOO.util.Resize('resize');
})();
</script>

</body>
</html>

    <script type="text/javascript">
        var GB_ROOT_DIR = "../greybox/";
    </script>

    <script type="text/javascript" src="../greybox/AJS.js"></script>
    <script type="text/javascript" src="../greybox/AJS_fx.js"></script>
    <script type="text/javascript" src="../greybox/gb_scripts.js"></script>
    <link href="../greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />

<script language="javascript">
var issesionactive = "<%=issession%>";
if(issesionactive == "false"){
	window.close();
}

function callJavascriptOpenfunc(id){

	
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicantPoolData&id="+id;
	GB_showCenter('Applicants',url, 600,1000, messageret1);
	
//window.showModalDialog(url,"<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>","dialogHeight: 600px; dialogWidth: 1000px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; menubar=no; toolbar=no; center: Yes; resizable: Yes; status: off;");

			}

function addApplicant(){
	var url = "<%=request.getContextPath()%>/applicant.do?method=createPageApplicantPool";
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.createapplicant",user1.getLocale())%>',url, 600,1050, messageret1);

	//window.showModalDialog(url,"<%=Constant.getResourceStringValue("aquisition.applicant.createapplicant",user1.getLocale())%>","dialogHeight: 600px; dialogWidth: 1000px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; menubar=no; toolbar=no; center: Yes; resizable: Yes; status: off;");

			}

function messageret1(){
	window.location.reload();
			}
</script>


<%}%>

<script language="javascript">
var issesionactive = "<%=issession%>";
if(issesionactive == "false"){
	window.opener.document.location.href=window.opener.document.location.href;
	window.close();
}
</script>