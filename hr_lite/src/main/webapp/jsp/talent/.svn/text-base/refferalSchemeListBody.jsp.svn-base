
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

function addrefferalscheme(){
	var url = "<%=request.getContextPath()%>/refferalscheme.do?method=createRefferalScheme&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferalScheme.create",user1.getLocale())%>',url,390,600, messageret);
}

function editrefferalscheme(id){
	var url = "<%=request.getContextPath()%>/refferalscheme.do?method=editRefferalScheme&readPreview=1&id="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferalScheme.Edit",user1.getLocale())%>',url,390,600, messageret);	
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


<br><a class="button" href="#" onClick="addrefferalscheme()"><%=Constant.getResourceStringValue("admin.RefferalScheme.create",user1.getLocale())%></a>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editrefferalscheme('" + oRecord.getData("refferalScheme_Id") + "')"+ ">" + sData + "</a>";

        };

	var formatschemeType = function(elCell, oRecord, oColumn, sData) {
          
         // elCell.innerHTML = "<a href='#' onClick=editrefferalscheme('" + oRecord.getData("refferalSchemeTypeId") + "')"+ ">" + sData + "</a>";
         
		  if(oRecord.getData("schemeType") == "E"){
			  elCell.innerHTML = "<%=Constant.getResourceStringValue("admin.RefferalScheme.type.employee.referral",user1.getLocale())%>";
		  }else{
			   elCell.innerHTML = "<%=Constant.getResourceStringValue("admin.RefferalScheme.type.agency.referral",user1.getLocale())%>";
		  }

        };

    // Column definitions
    var myColumnDefs = [
		{key:"refferalScheme_Id", hidden:true, sortable:true},
		{key:"refferalScheme_Name",label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
		{key:"refferalBudgetCodeStr",label:"<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%>", sortable:false,resizeable:true},
		{key:"refferalSchemeName",label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.reward.type",user1.getLocale())%>", sortable:false,resizeable:true},
		{key:"refferalScheme_Amount",label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",user1.getLocale())%>", sortable:true,resizeable:true},
		{key:"uom",label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.uom",user1.getLocale())%>", sortable:true,resizeable:true},
		{key:"ruleStr",label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.rule",user1.getLocale())%>", sortable:false,resizeable:true},
		{key:"schemeType",label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.type",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatschemeType}
       
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/RefferalSchemelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"refferalScheme_Id"},
{key:"refferalScheme_Name"},
{key:"refferalScheme_Amount"},
{key:"refferalBudgetCodeStr"},
{key:"refferalSchemeName"},
{key:"ruleStr"},
{key:"uom"},
{key:"schemeType"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=refferalScheme_Id&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"refferalScheme_Id", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
