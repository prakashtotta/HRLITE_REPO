<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
 <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title> <%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>
<!--
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

-->

<!--there is no custom header content for this example-->

</head>
<script language="javascript">

function createUser(){
	var url = "<%=request.getContextPath()%>/role.do?method=createRole&readPreview=1";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.role.createuserscreenname",user1.getLocale())%>',url,750,800, messageret);
	
}


function editRole(jb_id){
	var url = "<%=request.getContextPath()%>/role.do?method=editrole&readPreview=2&roleid="+jb_id;
	//var url = "role.do?method=editrole&roleid="+jb_id;
	//parent.showPopWin(url, 600, 400, null,true);
	GB_showCenter('<%=Constant.getResourceStringValue("admin.role.edituserscreenname",user1.getLocale())%>',url,750,800, messageret);
}

function deleteRole(jb_id){
	//var doyou = confirm("Are you confirm to delete? (OK = Yes   Cancel = No)");
	
	 // if (doyou == 'true'){
		  
document.roleForm.action = "role.do?method=roledelete&roleid="+jb_id;
   document.roleForm.submit();
 
	  // } 
	
}
function showmessage(returnval) { 
	//window.location.reload();
	}

function messageret(){
	//window.location.reload();

			}

</script>

<body class="yui-skin-sam">

<a href="#" onClick="createUser()"><img src="jsp/images/new_button.gif" border="0" alt="create user" title="Create Role" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=request.getContextPath()%>/role.do?method=rolelist"><img src="jsp/images/refresh.png" width="47" height="47" border="0"/></a>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<form name="roleForm">
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML = "<a href='#' onClick=editRole('" + oRecord.getData("roleId") + "')"+ ">" + sData + "</a>";

        };

		var deleteUrl = function(elCell, oRecord, oColumn, sData) {
			 
            elCell.innerHTML = "<a href='#' onClick=deleteRole('" + oRecord.getData("roleId") + "')"+ ">" + "delete" + "</a>";
        };

    // Column definitions
    var myColumnDefs = [
		{key:"roleId",hidden:true},
	    {key:"roleCode",label:"<%=Constant.getResourceStringValue("admin.role.rolecode",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
		{key:"roleName",label:"<%=Constant.getResourceStringValue("admin.role.rolename",user1.getLocale())%>", sortable:true,resizeable:true},
		{key:"roleDesc",label:"<%=Constant.getResourceStringValue("admin.role.desc",user1.getLocale())%> ", sortable:false,resizeable:true}
	        
        ];
		


    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/rolelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
           {key:"roleCode"},
            {key:"roleName"},
			{key:"roleDesc"},
			{key:"roleId"}            
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=roleId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"roleId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

     // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->
</form>
</body>
</html>
