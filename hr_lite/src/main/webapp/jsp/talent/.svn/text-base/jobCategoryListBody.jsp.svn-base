
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<bean:define id="cform" name="jobCategoryForm" type="com.form.JobCategoryForm" />


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
function addjobcategory(){
	var url = "<%=request.getContextPath()%>/jobcategory.do?method=createJobCategory";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.jobcategory.create",user1.getLocale())%>',url,200,600, messageret);
}

function editJobCategory(jobCategoryId){

	var url = "<%=request.getContextPath()%>/jobcategory.do?method=editJobCategory&jobCategoryId="+jobCategoryId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.jobcategory.edit",user1.getLocale())%>',url,200,600, messageret);
	
}
function deleteJobCategory(jobCategoryId){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){

		 var url = "<%=request.getContextPath()%>/jobcategory.do?method=deleteJobCategory&readPreview=1&jobCategoryId="+jobCategoryId;
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
<br>

<a class="button" href="#" onClick="addjobcategory()"><%=Constant.getResourceStringValue("admin.jobcategory.create",user1.getLocale())%> </a>

<br><br>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editJobCategory('" + oRecord.getData("jobCategoryId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteJobCategory('" + oRecord.getData("jobCategoryId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [
{key:"jobCategoryId", hidden:true, sortable:true},
{key:"jobCategoryName",label:"<%=Constant.getResourceStringValue("admin.jobcategory.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"jobCategoryDesc",label:"<%=Constant.getResourceStringValue("admin.jobcategory.desc",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.jobcategory.status",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"delete",label:"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>",sortable:false,formatter:deleteUrl,resizeable:true} 

        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/jobCategoryListPage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"jobCategoryId"},
{key:"jobCategoryName"},
{key:"jobCategoryDesc"},
{key:"status"},
{key:"delete"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobCategoryId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobCategoryId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
