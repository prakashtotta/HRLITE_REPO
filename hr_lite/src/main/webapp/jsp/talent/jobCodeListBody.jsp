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

function createjobcode(){
	var url = "<%=request.getContextPath()%>/jobcode.do?method=createJobCode&readPreview=1";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.JobCode.AddJobCodescreenname",user1.getLocale())%>',url,500,750, messageret);
}

function editjobcode(jobcod_id){
	var url = "<%=request.getContextPath()%>/jobcode.do?method=jobCodeDetails&readPreview=2&id="+jobcod_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.JobCode.EditJobCodescreenname",user1.getLocale())%>',url,500,750, messageret);
	
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
 <%=Constant.getResourceStringValue("admin.JobCode.Details",user1.getLocale())%>  <br><br>

<a href="#" onClick="createjobcode()"><%=Constant.getResourceStringValue("admin.JobCode.AddJobCodescreenname",user1.getLocale())%></a>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editjobcode('" + oRecord.getData("jobcodeId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"jobcodeId", hidden:true, sortable:true},
{key:"jobCode",label:"<%=Constant.getResourceStringValue("admin.JobCode.JobCode",user1.getLocale())%>", sortable:true,formatter:formatUrl},
{key:"jobName",label:"<%=Constant.getResourceStringValue("admin.JobCode.JobName",user1.getLocale())%>", sortable:false},
{key:"jobDesc",label:"<%=Constant.getResourceStringValue("admin.JobCode.JobDescription",user1.getLocale())%>", sortable:true},
{key:"jobResponsibility",label:"<%=Constant.getResourceStringValue("admin.JobCode.JobResponsibility",user1.getLocale())%>", sortable:true} 

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/jobCodeListPage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"jobcodeId"},
{key:"jobCode"},
{key:"jobName"},
{key:"jobDesc"},
{key:"jobResponsibility"}
			
       ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobcodeId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobcodeId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
