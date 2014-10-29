
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
function addrefferalscheme(){

	var url = "<%=request.getContextPath()%>/refferalschemetype.do?method=createRefferalSchemeType";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferalSchemeType.create",user1.getLocale())%>',url,300,750, messageret);

}

function editrefferalscheme(id){

	var url = "<%=request.getContextPath()%>/refferalschemetype.do?method=editRefferalSchemeType&id="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferalSchemeType.edit",user1.getLocale())%>',url,300,750, messageret);

	
}

function showmessage(returnval) { 
	window.location.reload();
	}
function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
	window.location.reload();

			}
</script>

<body class="yui-skin-sam">
<br>
 <%=Constant.getResourceStringValue("admin.RefferalSchemeType.rst",user1.getLocale())%>  <br><br>


<a href="#" onClick="addrefferalscheme()"><%=Constant.getResourceStringValue("admin.RefferalSchemeType.create",user1.getLocale())%></a>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editrefferalscheme('" + oRecord.getData("refferalSchemeTypeId") + "')"+ ">" + sData + "</a>";

        };

    	var formatschemeTypelist = function(elCell, oRecord, oColumn, sData) {
            
            // elCell.innerHTML = "<a href='#' onClick=editrefferalscheme('" + oRecord.getData("refferalSchemeTypeId") + "')"+ ">" + sData + "</a>";
            
   		  if(oRecord.getData("uom") == "currency"){
   			  elCell.innerHTML = "<%=Constant.getResourceStringValue("admin.RefferalScheme.type.uom.monetary",user1.getLocale())%>";
   		  }else if(oRecord.getData("uom") == "days"){
   			elCell.innerHTML = "<%=Constant.getResourceStringValue("admin.RefferalScheme.type.uom.days",user1.getLocale())%>";
   		  }

           };

	


    // Column definitions
    var myColumnDefs = [
{key:"refferalSchemeTypeId", hidden:true, sortable:true},
{key:"refferalSchemeName",label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
		{key:"uom",label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.uom",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatschemeTypelist},
		{key:"createdBy",label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.addedby",user1.getLocale())%>", sortable:true,resizeable:true},
		{key:"createdDate",label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.addedon",user1.getLocale())%>", sortable:true,resizeable:true}

       
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/RefferalSchemeTypelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"refferalSchemeTypeId"},
{key:"refferalSchemeName"},
			{key:"createdBy"},
			{key:"createdDate"},
			{key:"uom"}


			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=refferalSchemeTypeId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"refferalSchemeTypeId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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

</body>
</html>
