<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<bean:define id="jForm" name="licenseTypeForm" type="com.form.LicenseTypeForm" />
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
function addLicenseType(){
	var url = "<%=request.getContextPath()%>/licensetype.do?method=addLicenseType";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.licensetype.create",user1.getLocale())%>',url,400,650, messageret);
}

function editLicenseType(licenseTypeId){

	var url = "<%=request.getContextPath()%>/licensetype.do?method=editLicenseType&licenseTypeId="+licenseTypeId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.licensetype.edit",user1.getLocale())%>',url,400,650, messageret);
	
}
function deleteLicenseType(licenseTypeId){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){



			  document.licenseTypeForm.action ="licensetype.do?method=deleteLicenseType&licenseTypeId="+licenseTypeId;
			  document.licenseTypeForm.submit();
	
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
<html:form action="/licensetype.do?method=searchLicenseType">
</html:form>

<a href="#" onClick="addLicenseType()"><%=Constant.getResourceStringValue("admin.licensetype.create",user1.getLocale())%> </a>

<%
String licensetypedeleted = (String)request.getAttribute("licensetypedeleted");
	
if(licensetypedeleted != null && licensetypedeleted.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("admin.licensetype.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
	<%}%>	
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editLicenseType('" + oRecord.getData("licenseTypeId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteLicenseType('" + oRecord.getData("licenseTypeId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [

{key:"licenseTypeId", hidden:true, sortable:true},
{key:"licenseTypeName",label:"<%=Constant.getResourceStringValue("admin.licensetype.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"licenseTypeDesc",label:"<%=Constant.getResourceStringValue("admin.licensetype.desc",user1.getLocale())%>",width:250 ,sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.licensetype.status",user1.getLocale())%>", sortable:true,resizeable:true}

       
        ];



    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/licensetypelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"licenseTypeId"},
{key:"licenseTypeName"},
{key:"licenseTypeDesc"},
{key:"status"},
{key:"delete"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=licenseTypeId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"licenseTypeId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
