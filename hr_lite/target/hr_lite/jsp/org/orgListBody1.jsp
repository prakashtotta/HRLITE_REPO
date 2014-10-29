<%@ include file="../common/include.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>

<head>
	<title><%=Constant.getResourceStringValue("admin.organization.orglistbody.Destroydrop&raquo",user1.getLocale())%></title>

	<link rel="StyleSheet" href="jsp/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="jsp/dtree/dtree.js"></script>

</head>

<%
////response.setHeader("Cache-Control", "no-cache");
//		//response.setHeader("Pragma", "no-cache");
//		//response.setIntHeader("Expires", 0);
%>

<%
String treedata = BOFactory.getOrganizationBO().getJavaScriptTree();
System.out.println(treedata);
String orgId = (String)request.getParameter("orgId");
System.out.println(orgId);
%>
<table border="0" width="100%">

<tr>
<td valign="top" width="500">
	<table border="0" width="100%">

<div class="dtree">

	<p><a class="button" href="javascript: d.openAll();"><%=Constant.getResourceStringValue("admin.organization.orglistbody.openall",user1.getLocale())%></a> | <a class="button" href="javascript: d.closeAll();"><%=Constant.getResourceStringValue("admin.organization.orglistbody.closeall",user1.getLocale())%></a></p> <br>

	<%=treedata%>

</div>


</table>
</td>




<td valign="top" width="500">
<table border="0" width="100%">
<%
if(orgId != null) {
%>
<body class="yui-skin-sam">
<br>
 <b><%=Constant.getResourceStringValue("admin.organization.orglistbody.ChildOrganizationsList",user1.getLocale())%></b>  <br><br>

<a class="button" href="<%=request.getContextPath()%>/org.do?method=addorg"><%=Constant.getResourceStringValue("admin.organization.orglistbody.addorganization",user1.getLocale())%></a>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='org.do?method=applicantDetails&orgId=" + oRecord.getData("orgId") + "'"+ " class='submodal-600-500'>" + sData + "</a>";
        };

    // Column definitions
    var myColumnDefs = [
			{key:"orgId", hidden:true},
            {key:"orgCode", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationCode",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"orgName", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%>", sortable:true, resizeable:true},
		   {key:"locationCodeValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.LocationCode",user1.getLocale())%>", sortable:true, resizeable:true},
			{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"organizationTypeValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationType",user1.getLocale())%>", sortable:true, resizeable:true}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
	var url1 = "jsp/org/orglistpage.jsp?orgId=<%=orgId%>&ddd="+(new Date).getTime()+"&";

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/org/orglistpage.jsp?orgId=<%=orgId%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"orgId"},
            {key:"orgCode"},
            {key:"orgName"},
		   {key:"locationCodeValue"},
			{key:"locationValue"},
		{key:"organizationTypeValue"}
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=orgId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"orgId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
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

<%}%>
</table>
</td>
</tr>
</table>