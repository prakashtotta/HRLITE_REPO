<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
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

function editOrgEmailTemplate(id){
	var url = "<%=request.getContextPath()%>/orgemailtemplate.do?method=editorgemailtemplate&readPreview=1&orgemailtemplateId="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.editscreenname",user1.getLocale())%>', url, 600,700, messageret);
}


function addorgemailfunction(){
	var url = "<%=request.getContextPath()%>/orgemailtemplate.do?method=createorgemailtemplate&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.addscreenname",user1.getLocale())%>', url, 600,700, messageret);
}
	function messageret(){
	window.location.reload();
	}
</script>

<body class="yui-skin-sam">
<br><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.heading",user1.getLocale())%>
 <br><br>

<a  href="#" onClick="javascript:addorgemailfunction()"><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.addscreenname",user1.getLocale())%></a><br>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editOrgEmailTemplate('" + oRecord.getData("orgemailtemplid") + "')"+ ">" + sData + "</a>";

        };
	
    // Column definitions
    var myColumnDefs = [
{key:"orgemailtemplid", hidden:true, sortable:true},
{key:"orgEmailName",label:"<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.name",user1.getLocale())%>", sortable:true,formatter:formatUrl},
{key:"eventCode",label:"<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.type",user1.getLocale())%>", sortable:true},
{key:"orgName",label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>", sortable:false},
{key:"emailtemplatename",label:"<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.tempname",user1.getLocale())%> ", sortable:false},
	{key:"createdBy",label:"<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.Addedby",user1.getLocale())%>", sortable:true},
{key:"createdDate",label:"<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.Adddedon",user1.getLocale())%>", sortable:true},	
	{key:"updatedBy",label:"<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.Modifiedby",user1.getLocale())%>", sortable:true},
	{key:"updatedDate",label:"<%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.Modifiedon",user1.getLocale())%>", sortable:true}
];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/organizationEmailTemplateListpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"orgemailtemplid"},
{key:"eventCode"},
{key:"orgEmailName"},
{key:"orgName"},
{key:"createdBy"},
{key:"createdDate"},
{key:"emailtemplatename"},
{key:"updatedBy"},
{key:"updatedDate"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=orgemailtemplid&dir=asc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"orgemailtemplid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
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

</body>
</html>
