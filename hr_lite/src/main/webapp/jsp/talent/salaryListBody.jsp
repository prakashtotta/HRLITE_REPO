<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<bean:define id="salaryform" name="salaryForm" type="com.form.SalaryForm" />
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
function addsalaryplan(){
	var url = "<%=request.getContextPath()%>/salaryplan.do?method=createSalary&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.SalaryPlan.AddsalaryPlan",user1.getLocale())%>',url,320,700, messageret);
}

function editsalaryplan(plan_id){
	var url = "<%=request.getContextPath()%>/salaryplan.do?method=editsaaryplan&readPreview=1&planId="+plan_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.SalaryPlan.EditSalaryPlanscreenname",user1.getLocale())%>',url,320,700, messageret);
	
}
function deletesalaryplan(plan_id){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
	
		 var url = "<%=request.getContextPath()%>/salaryplan.do?method=salarydelete&readPreview=1&planId="+plan_id;
			GB_showCenter('',url,200,500, messageret);
			
	
 	}
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

<!--
<a class="submodal-500-300" href="<%=request.getContextPath()%>/salaryplan.do?method=createSalary&readPreview=3">add salary Plan details</a>
-->
<br><a class="button" href="#" onClick="addsalaryplan()"><%=Constant.getResourceStringValue("admin.SalaryPlan.AddsalaryPlan",user1.getLocale())%> </a>
<br><br>
<b>Salary plan list</b>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editsalaryplan('" + oRecord.getData("salaryplanId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deletesalaryplan('" + oRecord.getData("salaryplanId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [
{key:"salaryplanId", hidden:true, sortable:true},
{key:"salaryPlanName",label:"<%=Constant.getResourceStringValue("admin.SalaryPlan.PlanName",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"salaryPlanDesc",label:"<%=Constant.getResourceStringValue("admin.SalaryPlan.PlanDescription",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"fromRangeAmount",label:"<%=Constant.getResourceStringValue("admin.SalaryPlan.FromAmount",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"toRangeAmount",label:"<%=Constant.getResourceStringValue("admin.SalaryPlan.ToAmount",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"currencyCode",label:"<%=Constant.getResourceStringValue("admin.SalaryPlan.CurrencyCode",user1.getLocale())%>", sortable:true,resizeable:true},	
{key:"salaryPlanYear",label:"<%=Constant.getResourceStringValue("admin.SalaryPlan.year",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"delete",label:"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>",sortable:false,formatter:deleteUrl,resizeable:true} 
       
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/salarylistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"salaryPlanName"},
{key:"salaryPlanDesc"},
{key:"fromRangeAmount"},
{key:"toRangeAmount"},
{key:"currencyCode"},
{key:"salaryplanId"},
{key:"salaryPlanYear"},
{key:"delete"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=salaryplanId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"salaryplanId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
