<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
String searchpagedisplay = (String)request.getAttribute("searchpage");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>


<bean:define id="aform" name="declinedResonsForm" type="com.form.DeclinedResonsForm" /> 
<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function createreason(){
	var url = "<%=request.getContextPath()%>/declinedresonslov.do?method=createDeclineReasons";
	GB_showCenter(' <%=Constant.getResourceStringValue("admin.declinecancelreason.create",user1.getLocale())%>',url,500,750, messageret);
}

function editDeclineReasons(declinedreasonid){
	//alert(declinedreasonid);
	var url = "<%=request.getContextPath()%>/declinedresonslov.do?method=editDeclineReasons&declinedreasonid="+declinedreasonid;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.declinecancelreason.edit_reason",user1.getLocale())%> ',url,500,750, messageret);

	
}

	function messageret(){
	//window.location.reload();

			}
	function resetdata(){
		


		}

</script>


<body class="yui-skin-sam">

<html:form action="/declinedresonslov.do?method=saveDeclinedResons">

<a class="button" href="#" onClick="createreason()"><%=Constant.getResourceStringValue("admin.declinecancelreason.create",user1.getLocale())%></a>
</html:form>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editDeclineReasons('" + oRecord.getData("offerdeclinedreasonId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"offerdeclinedreasonId", hidden:true, sortable:true},
{key:"offerdecliedreasonName",label:"<%=Constant.getResourceStringValue("admin.declinecancelreason.declined_reason_name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"offerdecliedreasonDesc",label:"<%=Constant.getResourceStringValue("admin.declinecancelreason.declined_reason_desc",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"resonType",label:"<%=Constant.getResourceStringValue("admin.declinecancelreason.reason_type",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.Designation.Status",user1.getLocale())%>", sortable:true,resizeable:true}

        ];




    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/declinedresonsListPage.jsp?&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"offerdeclinedreasonId"},
{key:"offerdecliedreasonName"},
{key:"offerdecliedreasonDesc"},
{key:"resonType"},
{key:"status"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=offerdeclinedreasonId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"offerdeclinedreasonId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
