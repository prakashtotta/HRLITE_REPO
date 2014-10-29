<%@ include file="../common/yahooincludes.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html>
<head>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<script language="javascript">

function addSelection(){
	var url = "selection.do?method=createSelPage";
	parent.showPopWin(url, 600, 200, showmessage,true);
}

function showmessage(returnval) { 
	alert(returnval);
	window.location.reload();
	}

</script>


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



<!--there is no custom header content for this example-->

</head>

<body class="yui-skin-sam">
<a  href="#" onclick="addSelection()"><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.add_selections",user1.getLocale())%></a>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a class='submodal-600-500' href='selection.do?method=selcylesdetails&selId=" + oRecord.getData("selId") + "'"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";

		 

        };

    // Column definitions
    var myColumnDefs = [
			{key:"selId", label:"<%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.Selection_Cycle_Id",user1.getLocale())%>", sortable:true,hidden:true,formatter:formatUrl},
            {key:"selName",label:"<%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.Selection_Cycle",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
		{key:"createdBy",label:"<%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.Created_By",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"createdDate",label:"<%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.Created_Date",user1.getLocale())%>", sortable:true,hidden:true, resizeable:true}
            
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/selectionlistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"selId"},
            {key:"selName"},
			{key:"createdBy"},
			{key:"createdDate"}
            
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=selId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"selId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>
