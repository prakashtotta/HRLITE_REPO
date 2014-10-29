<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String orgId = (String)request.getAttribute("orgId");
if(orgId == null)orgId="0";
%>

<span id="data">
<b><%=Constant.getResourceStringValue("admin.organization.orglistbody.ChildOrganizationsList",user1.getLocale())%> </b>
<br><br>
<div id="dynamicdata" class="div"></div>
<table border="0" width="100%">
<%
if(orgId != null) {
%>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a href='#' onClick=editOrg('" + oRecord.getData("orgId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrlParent = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a href='#' onClick=editOrg('" + oRecord.getData("parent_org_id") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }



    // Column definitions
    var myColumnDefs = [
			{key:"orgId", hidden:true},
		{key:"parent_org_id", hidden:true},
            {key:"orgCode", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationCode",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"orgName", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%>", sortable:true, resizeable:true},
		   	{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>", sortable:false, resizeable:true},
		   {key:"organizationTypeValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationType",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"currencyCode", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.organizationcurrency",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"parentOrgName", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.parentorganization",user1.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrlParent}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    


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
		{key:"organizationTypeValue"},
			{key:"currencyCode"},
			{key:"parent_org_id"},
			{key:"parentOrgName"}
	
			
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

<%
}
%>
</table>
</body>
<br><br>


<br>

 <%
 String depturl = request.getContextPath()+"/dept.do?method=createDepartment&orgId="+orgId+"&readPreview=3";
 %>
<a class="button" href="#" onClick="javascript:createDepartment('<%=depturl%>')"><%=Constant.getResourceStringValue("admin.Deparment.createdeptscreen",user1.getLocale())%></a>
<br><br>

 <b><%=Constant.getResourceStringValue("admin.Deparment.DepartmentsList",user1.getLocale())%> </b> <br><br>
<body class="yui-skin-sam">
<div id="dynamicdata1" class="div"></div>

<%
if(orgId != null) {
%>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {

		  elCell.innerHTML = "<a href='#' onClick=editDepartment('" + oRecord.getData("departmentId") + "')"+ ">" + sData + "</a>";
        };

    // Column definitions
    var myColumnDefs = [
			{key:"departmentId", hidden:true},
            {key:"departmentCode", label:"<%=Constant.getResourceStringValue("admin.Deparment.deptcode",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"departmentName", label:"<%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%>", sortable:true, resizeable:true},
		   {key:"departmentDesc", label:"<%=Constant.getResourceStringValue("admin.Deparment.primaryfocusarea",user1.getLocale())%>", sortable:true, resizeable:true},
		 {key:"organizationname", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>", sortable:false, resizeable:true}
			
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    


    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/org/deptlistpage.jsp?orgId=<%=orgId%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"departmentId"},
            {key:"departmentCode"},
            {key:"departmentName"},
		   {key:"departmentDesc"},
			{key:"organizationname"}
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=departmentId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"departmentId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata1", myColumnDefs, myDataSource, myConfigs);
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

<%
}
%>

</span>