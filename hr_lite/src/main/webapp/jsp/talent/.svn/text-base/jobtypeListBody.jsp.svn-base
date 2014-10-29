<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<bean:define id="jForm" name="jobTypeForm" type="com.form.JobTypeForm" />
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
function createJobType(){
	var url = "<%=request.getContextPath()%>/jobtype.do?method=createJobType";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.jobtype.create",user1.getLocale())%>',url,330,670, messageret);
}

function editJobType(jobTypeId){
	//alert(jobTypeId);
	var url = "<%=request.getContextPath()%>/jobtype.do?method=editJobTypeDetails&jobTypeId="+jobTypeId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.jobtype.edit",user1.getLocale())%>',url,330,670, messageret);
	
}
function deleteJobType(jobTypeId){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){



			  document.jobTypeForm.action = "jobtype.do?method=deleteJobType&jobTypeId="+jobTypeId;
			  document.jobTypeForm.submit();
	
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
<html:form action="/jobtype.do?method=searchJobType">
</html:form>

<br><a class="button" href="#" onClick="createJobType()"><%=Constant.getResourceStringValue("admin.jobtype.create",user1.getLocale())%> </a>
<br><br> 


<%
String deletejobtype = (String)request.getAttribute("deletejobtype");
	
if(deletejobtype != null && deletejobtype.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("admin.jobtype.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
	<%}%>	
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editJobType('" + oRecord.getData("jobTypeId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteJobType('" + oRecord.getData("jobTypeId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [

{key:"jobTypeId", hidden:true, sortable:true},
{key:"jobTypeName",label:"<%=Constant.getResourceStringValue("admin.jobtype.jobtypename",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"jobTypeDesc",label:"<%=Constant.getResourceStringValue("admin.jobtype.desc",user1.getLocale())%>",width:250 ,sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.jobtype.status",user1.getLocale())%>", sortable:true,resizeable:true}

       
        ];



    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/jobTypelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"jobTypeId"},
{key:"jobTypeName"},
{key:"jobTypeDesc"},
{key:"status"},
{key:"delete"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobTypeId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobTypeId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
