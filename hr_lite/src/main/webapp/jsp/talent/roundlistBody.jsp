<%@ include file="../common/yahooincludes.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html>
<head>

<%
response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setIntHeader("Expires", 0);
%>

<script language="javascript">

function addChar(){
	var url = "round.do?method=createRound";
	parent.showPopWin(url, 600, 250, showmessage,true);
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
<form name="charForm">
<a  href="#" onclick="addChar()"><%=Constant.getResourceStringValue("applicant.Round.add_Round",user1.getLocale())%></a>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a class='submodal-600-500' href='round.do?method=rounddetails&roundId=" + oRecord.getData("roundId") + "'"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";

		 

        };

		var deleteUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a class='submodal-600-500' href='char.do?method=rounddetails&roundId=" + oRecord.getData("roundId") + "'"+ ">" + "delete" + "</a>";
		
       //elCell.innerHTML = "<a href='#' onClick='deleteChar("+oRecord.getData("charId")+")'"+ ">" + "delete" + "</a>";

		 

        };

    // Column definitions
    var myColumnDefs = [
			{key:"roundId", label:"<%=Constant.getResourceStringValue("applicant.Round.Round_id",user1.getLocale())%>", sortable:true,hidden:false,formatter:formatUrl},
            {key:"roundName",label:"<%=Constant.getResourceStringValue("applicant.Round.Charteristics",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"avweight",label:"<%=Constant.getResourceStringValue("applicant.Round.Average_Weight",user1.getLocale())%>", sortable:true, resizeable:true}

			
		
            
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/roundlistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"roundId"},
            {key:"roundName"},
			{key:"avweight"}
			
			
            
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=roundId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"roundId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
</form>
</body>
</html>
