<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../../common/yahooincludes.jsp" %>
<%@ include file="../../common/greybox.jsp" %>


<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<%
String orgId = (String)request.getAttribute("orgId");

%>

<span id="data">

<a  href="#" onClick="createGoal('<%=orgId%>')"><%=Constant.getResourceStringValue("add.goal",user1.getLocale())%> </a>
<br>
<body class="yui-skin-sam">
<div id="dynamicdata"></div>

<%
if(orgId != null) {
%>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a href='#' onClick=editSystemRule('" + oRecord.getData("goalId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }





    // Column definitions
    var myColumnDefs = [
		   
			{key:"goalId", hidden:true},
            {key:"goalName", label:"<%=Constant.getResourceStringValue("goal.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"goalDesc", label:"<%=Constant.getResourceStringValue("goal.description",user1.getLocale())%>", sortable:true, resizeable:true, width:200},
		  	{key:"departmentName", label:"<%=Constant.getResourceStringValue("rule.department",user1.getLocale())%>", sortable:false, resizeable:true},
	{key:"designationName", label:"<%=Constant.getResourceStringValue("admin.Designation.DesignationName",user1.getLocale())%>", sortable:false, resizeable:true},	
	{key:"timeperiodName", label:"<%=Constant.getResourceStringValue("goal.for.timeperiod",user1.getLocale())%>", sortable:false, resizeable:true, width:200},	
	{key:"createdBy", label:"<%=Constant.getResourceStringValue("rule.createdby",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"createdDate", label:"<%=Constant.getResourceStringValue("rule.createdDate",user1.getLocale())%>", sortable:true, resizeable:true,width:100}				
           
        ];




    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    


    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/performance/goal/goalorglistpage.jsp?orgId=<%=orgId%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"goalId"},
            {key:"goalName"},
            {key:"goalDesc"},
		   {key:"goalType"},
			{key:"departmentName"},
		{key:"designationName"},
			{key:"timeperiodName"},
	        {key:"createdDate"},
			{key:"createdBy"}

			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=goalId&dir=asc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"goalId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

	var highlightEditableCell = function(oArgs) {
            var elCell = oArgs.target;
            if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
                this.highlightCell(elCell);
            }
        };

	myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<%
}
%>
</span>