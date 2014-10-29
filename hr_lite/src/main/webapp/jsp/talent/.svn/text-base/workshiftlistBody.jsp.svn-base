<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<bean:define id="wForm" name="workShiftForm" type="com.form.WorkShiftForm" />
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
function createWorkShift(){
	var url = "<%=request.getContextPath()%>/workshift.do?method=createWorkShift";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.workshift.create",user1.getLocale())%>',url,250,700, messageret);
}

function editWorkShift(shiftId){
	//alert(jobTypeId);
	var url = "<%=request.getContextPath()%>/workshift.do?method=editWorkShift&shiftId="+shiftId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.workshift.edit",user1.getLocale())%>',url,250,700, messageret);
	
}
function deleteWorkShift(shiftId){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){



			  document.workShiftForm.action = "workshift.do?method=deleteWorkShift&shiftId="+shiftId;
			  document.workShiftForm.submit();
	
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
<html:form action="/workshift.do?method=searchWorkShift">
</html:form>

<br><a class="button" href="#" onClick="createWorkShift()"><%=Constant.getResourceStringValue("admin.workshift.create",user1.getLocale())%> </a>
<br><br>

<b>Work shift list</b><br><br>

<%
String workshiftdeleted = (String)request.getAttribute("workshiftdeleted");
	
if(workshiftdeleted != null && workshiftdeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.workshift.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
	<%}%>	
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editWorkShift('" + oRecord.getData("shiftId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteWorkShift('" + oRecord.getData("shiftId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [

{key:"shiftId", hidden:true, sortable:true},
{key:"shiftName",label:"<%=Constant.getResourceStringValue("admin.workshift.workshiftname",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"shiftDesc",label:"<%=Constant.getResourceStringValue("admin.workshift.desc",user1.getLocale())%>",width:250 ,sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.workshift.status",user1.getLocale())%>", sortable:true,resizeable:true}

        ];



    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/workshiftlistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"shiftId"},
{key:"shiftName"},
{key:"shiftDesc"},
{key:"status"},
{key:"delete"}

        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=shiftId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"shiftId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
