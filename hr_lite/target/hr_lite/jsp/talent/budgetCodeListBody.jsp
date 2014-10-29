<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

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
	GB_showCenter('<%=Constant.getResourceStringValue("admin.BudgetCode.editbcodescreenname",user1.getLocale())%>',url,350,800, messageret);
}
function createBudgetCode(){
	var url = "<%=request.getContextPath()%>/budgetcode.do?method=createbudgetCode&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.BudgetCode.CreateBudgetCode",user1.getLocale())%>',url,350,800, messageret);
}

function messageret(){
	//window.location.reload();

			}

function showmessage(returnval) { 
	//window.location.reload();
	}

</script>

<body class="yui-skin-sam">


<br><a class="button"  href="#" onClick="createBudgetCode()"><%=Constant.getResourceStringValue("admin.BudgetCode.CreateBudgetCode",user1.getLocale())%> </a>
<br><br>
<b>Budget code list</b>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editbudgetCode('" + oRecord.getData("budgetId") + "')"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"budgetId", hidden:true, sortable:true},
{key:"budgetCode",label:"<%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"budgetCentreName",label:"<%=Constant.getResourceStringValue("admin.BudgetCode.Budegetname",user1.getLocale())%> ", sortable:false,resizeable:true},
{key:"budgetamount",label:"<%=Constant.getResourceStringValue("admin.BudgetCode.BudgetAmount",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"budgetCurrency",label:"<%=Constant.getResourceStringValue("admin.BudgetCode.Currency",user1.getLocale())%> ", sortable:true,resizeable:true},
{key:"orgName",label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> ", sortable:false,resizeable:true},
{key:"deptName",label:"<%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> ", sortable:false,resizeable:true},
{key:"budgetYear",label:"<%=Constant.getResourceStringValue("admin.BudgetCode.BudgetYear",user1.getLocale())%> ", sortable:true,resizeable:true}    
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/budgetCodelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"budgetCode"},
{key:"budgetCentreName"},
{key:"budgetamount"},
{key:"orgName"},
{key:"deptName"},   
{key:"budgetYear"},
{key:"budgetId"},
	{key:"budgetCurrency"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=budgetId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"budgetId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
