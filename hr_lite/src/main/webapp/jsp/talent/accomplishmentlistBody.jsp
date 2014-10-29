
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
User user14 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<bean:define id="aform" name="AccomplishmentForm" type="com.form.AccomplishmentForm" />
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">



<!--there is no custom header content for this example-->

</head>
 
<script language="javascript">
function createAccomplishment(){
	var url = "<%=request.getContextPath()%>/accomplishment.do?method=createAccomplishment&readPreview=1";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Accomplishment.Create_accomplishment",user14.getLocale())%>',url,200,600, messageret);
}
function editAccomplishment(acc_id){
	var url = "<%=request.getContextPath()%>/accomplishment.do?method=AccomplishmentDetails&readPreview=2&id="+acc_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Accomplishment.Edit_accomplishment",user14.getLocale())%>',url,200,600, messageret);

	
}
function showmessage(returnval) { 
	window.location.reload();
	}
function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
	window.location.reload();

			}
	function searchdata(){
		//alert("hi ");
		var accname = document.AccomplishmentForm.accName.value.trim();
		 document.AccomplishmentForm.action = "accomplishment.do?method=accomplishmentlistsearch&accomplishmentName="+accname;
		 document.AccomplishmentForm.submit();

	}
</script>


<body class="yui-skin-sam">
<html:form action="/accomplishment.do?method=logon">
<div class="div">
<table width="50%">
			<tr>
			<td><%=Constant.getResourceStringValue("admin.Accomplishment.Name",user14.getLocale())%> : </td>
			<td><html:text property="accName" size="40" maxlength="500"/></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.search",user14.getLocale())%>" onClick="searchdata()" class="button"></td>
		</tr>
		</table>
		</div>
</html:form>
<br><br>

<a class="button" href="#" onClick="createAccomplishment()"><%=Constant.getResourceStringValue("admin.Accomplishment.Create_accomplishment",user14.getLocale())%></a>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<b>Accomplishment list</b>
<br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editAccomplishment('" + oRecord.getData("accId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"accId", hidden:true, sortable:true},
{key:"accName",label:"<%=Constant.getResourceStringValue("admin.Accomplishment.Name",user14.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"accDesc",label:"<%=Constant.getResourceStringValue("admin.Accomplishment.Description",user14.getLocale())%>", sortable:false,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.Accomplishment.status",user14.getLocale())%>", sortable:true,resizeable:true}

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/accopmlishmentListPage.jsp?accomplishmentName=<%=aform.getAccName()%>&ddd="+(new Date).getTime()+"&");

    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"accId"},
{key:"accName"},
{key:"accDesc"},
{key:"status"}
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=accId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"accId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
