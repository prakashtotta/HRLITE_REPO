
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

function editrefferalbudgetCode(id){
	var url = "<%=request.getContextPath()%>/refferalBudgetcode.do?method=editRefferalbudgetCode&readPreview=1&id="+id;
	//parent.showPopWin(url, 500, 250, showmessage,true);
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.editscreenname",user1.getLocale())%>',url,360,600, messageret);
}
function createrefferalBudgetCode(){
	var url = "<%=request.getContextPath()%>/refferalBudgetcode.do?method=createrefferalbudgetCode&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.addscreenname",user1.getLocale())%>',url,360,600, messageret);
}

function messageret(){
	//window.location.reload();

			}

function showmessage(returnval) { 
	//window.location.reload();
	}

</script>

<body class="yui-skin-sam">


<br><a class="button"  href="#" onClick="createrefferalBudgetCode()"> <%=Constant.getResourceStringValue("admin.RefferalBudgetCode.create",user1.getLocale())%></a>
<br><br>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editrefferalbudgetCode('" + oRecord.getData("ref_budgetId") + "')"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"ref_budgetId", hidden:true, sortable:true},
{key:"ref_budgetCode",label:"<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"ref_budgetCentreName",label:"<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.name",user1.getLocale())%>", sortable:false, resizeable:true},
{key:"ref_budgetamount",label:"<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.amt",user1.getLocale())%>", sortable:false, resizeable:true},
{key:"ref_budgetCurrency",label:"<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.curr",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"orgName",label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"deptName",label:"<%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"ref_budgetYear",label:"<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.year",user1.getLocale())%>", sortable:false, resizeable:true}
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/refferalBudgetCodeListpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
 
{key:"ref_budgetId"},
{key:"ref_budgetCode"},
{key:"ref_budgetCentreName"},
{key:"ref_budgetamount"},
{key:"ref_budgetCurrency"},
	{key:"orgName"},
	{key:"deptName"},
	{key:"ref_budgetYear"}

	
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=ref_budgetId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"ref_budgetId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
