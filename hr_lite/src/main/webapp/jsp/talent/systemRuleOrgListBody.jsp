<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>

<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
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
String orgId = (String)request.getParameter("orgId");

if(orgId == null){
	Organization orgparent = BOFactory.getOrganizationBO().getParentOrganizationInfo(user1.getSuper_user_key());
	orgId= String.valueOf(orgparent.getOrgId());
}

List<String> treedatalist = BOFactory.getOrganizationBO().getJavaScriptTreeForSystemRule(orgId,user1);


String nodeselectdata =  BOFactory.getOrganizationBO().getJavaScriptTreeForSelectNode(orgId);

%>

<script language="javascript">

function editSystemRule(rul_id){

	var url = "<%=request.getContextPath()%>/sysrule.do?method=editSystemRule&ruleid="+rul_id+"&orgid="+<%=orgId%>;
	
	//parent.showPopWin(url, 600, 500, showmessage,true);
	 GB_showCenter('edit system rule',url,350,600, showmessage);

}
function addSystemrule(oldIdvalue){
	var url = "<%=request.getContextPath()%>/sysrule.do?method=createSystemrule&orgid="+oldIdvalue;
	 GB_showCenter('create system rule',url,600,600, showmessage);

}


function manageApplicantFlters(){
	var url = "businessrule.do?method=manageApplicantFilterSrc&type=ALL_APPLICANTS&reqId=0";
     var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("admin.manage.applicant.filters",user1.getLocale())%>","dialogHeight: 434px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

 
}

function showmessage(returnval) { 
	//window.location.reload();
	}





function messageret1(){
//	window.location.reload();
}

$(document).ready(function() {
	    <%=treedatalist.get(1)%>
}); 


function retriveData(urld) { 
$.ajax({
  url: urld,
  success: function(data){
  $('#data').html(data);
	completeajx();
  }
});
} 

 
function completeajx(){
	  $.unblockUI();
}


			
</script>
<bean:define id="systemform" name="systemRuleForm" type="com.form.SystemRuleForm" />
<div class="div">
<table border="0" width="100%">

<tr>
<td valign="top" width="350px">
	

<div class="dtree">

	

	<%=treedatalist.get(0)%>

</div>



</td>




<td valign="top"  width="650px">

   <span id="data">

<a class="button" href="#" onclick="addSystemrule('<%=orgId%>')"> <%=Constant.getResourceStringValue("admin.systemrules.addsystemrule",user1.getLocale())%></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="button" href="#" onclick="manageApplicantFlters()"> <%=Constant.getResourceStringValue("admin.businessRule.manage.rule.applicant.filter",user1.getLocale())%></a>
   <br>
   <br> <b><%=Constant.getResourceStringValue("rule.list",user1.getLocale())%></b>
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
		  // {key:"publishToEmpRef", label:"<%=Constant.getResourceStringValue("rule.publishToEmpRef",user1.getLocale())%>", sortable:true, resizeable:true},
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

</td>
</tr>
</table>
<%=nodeselectdata%>