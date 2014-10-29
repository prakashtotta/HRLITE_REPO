<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
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

function addGroupChar(){
	var url = "<%=request.getContextPath()%>/groupchar.do?method=createGroupChar";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.competenciesgroup.CreateCompetencyGroup",user1.getLocale())%>',url,500,750, messageret);
	
}
function showmessage(returnval) { 
	alert(returnval);
	window.location.reload();
	}


function editCharGroup(jb_id){
	var url = "<%=request.getContextPath()%>/groupchar.do?method=editGroupChar&groupCharId="+jb_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.competenciesgroup.EditCompetencyGroup",user1.getLocale())%>',url,500,750, messageret);
	
}

function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
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
<form name="groupCharForm">
<br>
<a  href="#" onclick="addGroupChar()"><%=Constant.getResourceStringValue("admin.competenciesgroup.CreateCompetencyGroup",user1.getLocale())%> </a> <br><br>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
         	  elCell.innerHTML = "<a href='#' onClick=editCharGroup('" + oRecord.getData("groupCharId") + "')"+ ">" + sData + "</a>";
        };

		

    // Column definitions
    var myColumnDefs = [
			{key:"groupCharId", hidden:true},
            {key:"groupCharName",label:"<%=Constant.getResourceStringValue("admin.competenciesgroup.CompetencygroupName",user1.getLocale())%> ", sortable:true, resizeable:true,formatter:formatUrl},
		{key:"createdBy",label:"<%=Constant.getResourceStringValue("admin.competenciesgroup.Createdby",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"createdDate",label:"<%=Constant.getResourceStringValue("admin.competenciesgroup.Createdon",user1.getLocale())%>", sortable:true, resizeable:true}
		

			
		
            
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/groupcharlistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"groupCharId"},
            {key:"groupCharName"},
			 {key:"createdBy"},
			 {key:"createdDate"}

        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=groupCharId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"groupCharId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
</form>
</body>
</html>
