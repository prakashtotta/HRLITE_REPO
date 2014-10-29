   <%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
   
 <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String orgId = (String)request.getAttribute("orgId");

%>  
   <span id="data">

<a class="button" href="#" onclick="addSystemrule('<%=orgId%>')"> <%=Constant.getResourceStringValue("admin.systemrules.addsystemrule",user1.getLocale())%></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="button" href="#" onclick="manageApplicantFlters()"> <%=Constant.getResourceStringValue("admin.businessRule.manage.rule.applicant.filter",user1.getLocale())%></a>
<br><br>
<b>Rule list</b>
<br><br>

<body class="yui-skin-sam">
<div id="dynamicdata" class="div"></div>

<%
if(orgId != null) {
%>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a href='#' onClick=editSystemRule('" + oRecord.getData("systemRuleId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var submitter = function (callback, newValue)
    {
	   
        var record = this.getRecord();
	    var column = this.getColumn();
	//	alert(column);
        var oldValue = this.value;
		//alert(record.getData('address'));
        var datatable = this.getDataTable();

        // send the data to our update page to update the value in the database
        YAHOO.util.Connect.asyncRequest('POST', 'sysrule.do',
        {
			
            success: function (o)
            {
				 callback(true, o.responseText); 
                
            },
            failure: function (o)
            {
                alert(o.statusText);
                callback();
            },
            scope: this
        },
        'action=cellEdit&column=' + column.key +
				     '&method=updatestatus' +
                     '&status=' + escape(newValue) +
                      '&ruleid=' + record.getData('systemRuleId') 
        );
    };



    // Column definitions
    var myColumnDefs = [
		   
			{key:"systemRuleId", hidden:true},
            {key:"ruleName", label:"<%=Constant.getResourceStringValue("rule.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"ruleType", label:"<%=Constant.getResourceStringValue("rule.type",user1.getLocale())%>", sortable:true, resizeable:true},
	//	   {key:"publishToEmpRef", label:"<%=Constant.getResourceStringValue("rule.publishToEmpRef",user1.getLocale())%>", sortable:true, resizeable:true},
		//	{key:"publishToExternal", label:"<%=Constant.getResourceStringValue("rule.publishToExternal",user1.getLocale())%>", sortable:true, resizeable:true},
		   {key:"orgName", label:"<%=Constant.getResourceStringValue("rule.org",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"deptName", label:"<%=Constant.getResourceStringValue("rule.department",user1.getLocale())%>", sortable:false, resizeable:true},
	{key:"userNames", label:"<%=Constant.getResourceStringValue("rule.user.names",user1.getLocale())%>", sortable:false, resizeable:true,width:200},	
	{key:"createdBy", label:"<%=Constant.getResourceStringValue("rule.createdby",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"createdDate", label:"<%=Constant.getResourceStringValue("rule.createdDate",user1.getLocale())%>", sortable:true, resizeable:true},
	{key:"status",  editor: new YAHOO.widget.RadioCellEditor({radioOptions:["A","I"],disableBtns:true,asyncSubmitter: submitter})}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    


    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/systemrulelistpage.jsp?orgId=<%=orgId%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"systemRuleId"},
            {key:"ruleName"},
            {key:"createdBy"},
		   {key:"ruleType"},
			{key:"status"},
		{key:"publishToEmpRef"},
			{key:"publishToExternal"},
			{key:"orgName"},
			{key:"deptName"},
			{key:"userNames"},
	        {key:"createdDate"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=systemRuleId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"systemRuleId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

</body>
</span>