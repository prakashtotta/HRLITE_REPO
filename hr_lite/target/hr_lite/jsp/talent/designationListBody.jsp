
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
 <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<bean:define id="designationForm" name="designationForm" type="com.form.DesignationForm" />

<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<bean:define id="designationForm" name="designationForm" type="com.form.DesignationForm" />

<script language="javascript">




function createDesignation(){
	var url = "<%=request.getContextPath()%>/designation.do?method=createDesignation";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Designation.CreateDesignation",user1.getLocale())%>',url,285,650, messageret);
}
function editDesignation(designation_id){
	var url = "<%=request.getContextPath()%>/designation.do?method=designationdetails&id="+designation_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Designation.EditDesignation",user1.getLocale())%>',url,285,650, messageret);
}


	function messageret(){
	//window.location.reload();

			}

	 function searchDesignation(){
			
		 document.designationForm.action = "designation.do?method=searchDesignationsList";
		 document.designationForm.submit();
		 }
	 function resetData(){
		  document.designationForm.designationName.value="";

		 } 
</script>   


<body class="yui-skin-sam">
<html:form action="/designation.do?method=saveDesignation">
<div class="div">
<table border="0" width="100%">
		<tr>
			<td width="30%" valign="middle"><%=Constant.getResourceStringValue("admin.Designation.DesignationName",user1.getLocale())%> : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><html:text property="designationName" size="40" maxlength="200"/></td>
	
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchDesignation()" class="button">
				<input type="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button" /> 
			</td>
		</tr>
	

</table>
</div>
</html:form>
<br><br>

<a class="button" href="#" onClick="javascript:createDesignation()"><%=Constant.getResourceStringValue("admin.Designation.CreateDesignation",user1.getLocale())%>  </a>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<br><br>
<b><%=Constant.getResourceStringValue("admin.Designation.DesignationList",user1.getLocale())%></b>
<br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editDesignation('" + oRecord.getData("designationId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"designationId", hidden:true, sortable:true},
{key:"designationName",label:"<%=Constant.getResourceStringValue("admin.Designation.DesignationName",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"designationCode",label:"<%=Constant.getResourceStringValue("admin.Designation.DesignationCode",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"designationDesc",label:"<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.Designation.Status",user1.getLocale())%>", sortable:true,resizeable:true}

        ];




    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/designationListPage.jsp?designationCode=<%=designationForm.getDesignationCode()%>&designationName=<%=designationForm.getDesignationName()%>&designationDesc=<%=designationForm.designationDesc%>&searchpagedisplay=<%=searchpagedisplay%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"designationId"},
{key:"designationName"},
{key:"designationCode"},
{key:"designationDesc"},
{key:"status"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=designationId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"designationId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
