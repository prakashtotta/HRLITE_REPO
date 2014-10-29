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
<title><%=Constant.getResourceStringValue("lov.lovlist.title.lovlistdetails",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<bean:define id="lovListForm" name="lovListForm"	type="com.form.LovListForm"/>

<script language="javascript">


function editLov(lovid){
	//alert(lovid);
	var url = "<%=request.getContextPath()%>/lovlist.do?method=editLov&readPreview=1&lovid="+lovid;
	GB_showCenter('<%=Constant.getResourceStringValue("lov.lovlist.editlov",user1.getLocale())%>',url,500,750, messageret);
}
	

function createLov(){
	var url = "<%=request.getContextPath()%>/lovlist.do?method=createLov&readPreview=1";
	GB_showCenter('<%=Constant.getResourceStringValue("lov.lovlist.createlov",user1.getLocale())%>',url,500,750, messageret);
}


function resetData(){

 } 
function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
	window.location.reload();

			}

function init(){


}

</script>   

<body class="yui-skin-sam" onLoad="init()">

<html:form action="/lovlist.do?method=searchlocationslist">


</html:form>

<!--<a href="#" onClick="javascript:createLov();return false"><%=Constant.getResourceStringValue("lov.lovlist.createlovlist",user1.getLocale())%>  </a>-->
<br>
<%=Constant.getResourceStringValue("lov.lovlist.lovlist",user1.getLocale())%>   <br><br>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editLov('" + oRecord.getData("lovListId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
	{key:"lovListId", hidden:true, sortable:true},
	{key:"lovListCode",label:"<%=Constant.getResourceStringValue("lov.lovlist.lovcode",user1.getLocale())%>",width:300,sortable:true,resizeable:true,formatter:formatUrl},
	//{key:"lovListValueCode",label:"<%=Constant.getResourceStringValue("lov.lovlist.lovvaluecode",user1.getLocale())%>", sortable:true,resizeable:true},
	//{key:"lovListValueName",label:"<%=Constant.getResourceStringValue("lov.lovlist.lovvalue",user1.getLocale())%>", sortable:true,resizeable:true},
	{key:"status",label:"<%=Constant.getResourceStringValue("lov.lovlist.status",user1.getLocale())%>",width:100, sortable:true,resizeable:true}
	//{key:"localeId",label:"<%=Constant.getResourceStringValue("lov.lovlist.locale",user1.getLocale())%>", sortable:true,resizeable:true}
        ];




    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/lovlist/lovlistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

	{key:"lovListId"},
	{key:"lovListCode"},
	//{key:"lovListValueCode"},
	//{key:"lovListValueName"},
	{key:"status"}
	//{key:"localeId"}


			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=lovListId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"lovListId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

