<%@ include file="../../common/include.jsp" %>
<%@ include file="../../common/greybox.jsp" %>
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

function editbudgetCode(budget_id){
	var url = "<%=request.getContextPath()%>/budgetcode.do?method=editbudgetCode&readPreview=1&id="+budget_id;
	//parent.showPopWin(url, 500, 250, showmessage,true);
	GB_showCenter('<%=Constant.getResourceStringValue("admin.BudgetCode.editbcodescreenname",user1.getLocale())%>',url,500,750, messageret);
}
function createBudgetCode(){
	var url = "<%=request.getContextPath()%>/budgetcode.do?method=createbudgetCode&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.BudgetCode.CreateBudgetCode",user1.getLocale())%>',url,500,750, messageret);
}

function messageret(){
	window.location.reload();

			}

function showmessage(returnval) { 
	window.location.reload();
	}

</script>

<body class="yui-skin-sam">
<br>
 <%=Constant.getResourceStringValue("kra.list",user1.getLocale())%>  <br><br>

<a  href="#" onClick="createBudgetCode()"><%=Constant.getResourceStringValue("add.kra",user1.getLocale())%> </a>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editbudgetCode('" + oRecord.getData("kraId") + "')"+ ">" + sData + "</a>";

        };

	var formatUrlparent = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=viewKra('" + oRecord.getData("parentKraId") + "')"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"kraId", hidden:true, sortable:true},
{key:"parentKraId", hidden:true, sortable:true},
{key:"kraName",label:"<%=Constant.getResourceStringValue("kra.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"kraWeight",label:"<%=Constant.getResourceStringValue("kra.weight",user1.getLocale())%> ", sortable:false,resizeable:true},
{key:"isTrack",label:"<%=Constant.getResourceStringValue("kra.istrack",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"trackStartDate",label:"<%=Constant.getResourceStringValue("kra.trackStartDate",user1.getLocale())%> ", sortable:true,resizeable:true},
{key:"trackEndDate",label:"<%=Constant.getResourceStringValue("kra.trackEndDate",user1.getLocale())%> ", sortable:false,resizeable:true},
{key:"trackingFreqency",label:"<%=Constant.getResourceStringValue("kra.trackingFreqency",user1.getLocale())%> ", sortable:false,resizeable:true},
{key:"parentKraName",label:"<%=Constant.getResourceStringValue("kra.parentKraName",user1.getLocale())%> ", sortable:false,resizeable:true,formatter:formatUrlparent}
		
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("kra.do?method=searchKRAListPage&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	//myDataSource.connMethodPost = true; 
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"kraId"},
{key:"parentKraId"},
{key:"kraName"},
{key:"kraWeight"},
{key:"isTrack"},   
{key:"trackStartDate"},
{key:"trackEndDate"},
{key:"trackingFreqency"},
{key:"parentKraName"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=kraId&dir=asc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"kraId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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


