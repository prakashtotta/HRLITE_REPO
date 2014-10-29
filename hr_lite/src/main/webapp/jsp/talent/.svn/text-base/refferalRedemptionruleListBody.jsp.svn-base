<%@ include file="../common/greybox.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.create_rule",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<bean:define id="ruleform" name="refferalRedemptionRuleForm" type="com.form.RefferalRedemptionRuleForm" />

<script language="javascript">
function createRedemptionrule(){
	var url = "<%=request.getContextPath()%>/rulelovlist.do?method=createRedumptionrule&readPreview=1";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.create_redumption_rule",user1.getLocale())%>',url,500,600, messageret);
}

function editRedemptionrule(id){
	var url = "<%=request.getContextPath()%>/rulelovlist.do?method=editRule&readPreview=2&id="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.edit_redumption_rule",user1.getLocale())%>',url,500,600, messageret);
}

function deleteRedemptionrule(id){
	//document.refferalRedemptionRuleForm.action = "rulelovlist.do?method=dedeleteRule&readPreview=1&id="+'<bean:write name="refferalRedemptionRuleForm" property="ruleId"/>';
   // document.refferalRedemptionRuleForm.submit();
  	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
   var url = "<%=request.getContextPath()%>/rulelovlist.do?method=dedeleteRule&id="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.redumption_Rule_Delete",user1.getLocale())%>',url,200,500, messageret);
	 }
}

function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
	window.location.reload();

			}

</script>

<body class="yui-skin-sam">
<br>
 <%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Details",user1.getLocale())%> <br><br>

<a  href="#" onClick="createRedemptionrule()"><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.create_rule",user1.getLocale())%></a>



<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
           elCell.innerHTML = "<a href='#' onClick=editRedemptionrule('" + oRecord.getData("ruleId") + "')"+ ">" + sData + "</a>";

        };

		var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteRedemptionrule('" + oRecord.getData("ruleId") + "')"+ ">" + "delete" + "</a>";
         
        };	

    // Column definitions
    var myColumnDefs = [
{key:"ruleId", hidden:true, sortable:true},
//{key:"ruleId",label:"ruleId", sortable:true,formatter:formatUrl},
{key:"ruleName",label:"<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
{key:"ruleDesc",label:"<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Desc",user1.getLocale())%>", sortable:true, resizeable:true},
{key:"creditAfterdays",label:"<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days",user1.getLocale())%>", sortable:true, resizeable:true}
//{key:"delete",label:"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>",sortable:false, resizeable:true,formatter:deleteUrl} 

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/rulelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"ruleId"},
{key:"ruleName"},
{key:"ruleDesc"},
{key:"creditAfterdays"}


					
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=ruleId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"ruleId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
