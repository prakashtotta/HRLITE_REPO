<%@ include file="../common/include.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>

<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">



<!--there is no custom header content for this example-->

</head>
 
<script language="javascript">
function createOnBoardingTaskDefi(){
	var url = "<%=request.getContextPath()%>/OnBoardingTaskDefi.do?method=createOnBoardingTaskDefi&readPreview=1";
	GB_showCenter('Create On boarding task defination',url,500,800, messageret);
}
function editOnBoardingTaskDefi(taskdef_id){
	var url = "<%=request.getContextPath()%>/OnBoardingTaskDefi.do?method=OnBoardingTaskDefiDetails&readPreview=2&id="+taskdef_id;
	GB_showCenter('Edit On boarding task defination',url,500,750, messageret);

	
}
function showmessage(returnval) { 
	//window.location.reload();
	}
function showmessage(returnval) { 
	//window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}

</script>


<body class="yui-skin-sam">
<br>
 Create On boarding task defination details   <br><br>


<a href="#" onClick="createOnBoardingTaskDefi()">Create On boarding task defination</a>
 <br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editOnBoardingTaskDefi('" + oRecord.getData("taskdefid") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"taskdefid", hidden:true, sortable:true},
{key:"taskName",label:"Task defination name", sortable:true, resizeable:true,formatter:formatUrl},
{key:"taskDesc",label:"Task defination description", sortable:false, resizeable:true},
{key:"primaryOwnerValue",label:"Primary owner name", sortable:true, resizeable:true},
{key:"eventType",label:"Event type", sortable:false, resizeable:true},
{key:"createdBy",label:"Created by", sortable:false, resizeable:true},
{key:"createdDate",label:"Created date", sortable:false, resizeable:true}
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/OnBoardingTaskDefilistPage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"taskdefid"},
{key:"taskName"},
{key:"taskDesc"},
{key:"primaryOwnerValue"},
{key:"eventType"},
{key:"createdBy"},
{key:"createdDate"}
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=taskdefid&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"taskdefid", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
