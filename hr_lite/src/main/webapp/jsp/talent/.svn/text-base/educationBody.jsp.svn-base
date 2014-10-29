<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<bean:define id="eduForm" name="educationForm" type="com.form.EducationForm" />
<%
User user14 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">



<!--there is no custom header content for this example-->

</head>
 
<script language="javascript">
function addEducation(){
	var url = "<%=request.getContextPath()%>/education.do?method=addEducation";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.education.create",user14.getLocale())%>',url,250,600, messageret);
}
function editEducation(educationId){
	var url = "<%=request.getContextPath()%>/education.do?method=editEducation&educationId="+educationId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.education.edit",user14.getLocale())%>',url,250,600, messageret);

	
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
	function searchdata(){
		//alert("hi ");
		var educationName = document.educationForm.educationName.value.trim();
		 document.educationForm.action = "education.do?method=educationlistsearch&educationName="+educationName;
		 document.educationForm.submit();

	}
</script>


<body class="yui-skin-sam">
<html:form action="/education.do?method=logon">
<div class="div">
<table width="50%">
			<tr>
			<td><%=Constant.getResourceStringValue("admin.education.name",user14.getLocale())%> : </td>
			<td><html:text property="educationName" size="40" maxlength="500"/></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.search",user14.getLocale())%>" onClick="searchdata()" class="button"></td>
		</tr>
		</table>
		</div>
</html:form>
 <br><br>


<a class="button" href="#" onClick="addEducation()"><%=Constant.getResourceStringValue("admin.education.create",user14.getLocale())%></a>
 <br><br>
    <b><%=Constant.getResourceStringValue("admin.education.list",user14.getLocale())%></b>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
 <br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editEducation('" + oRecord.getData("educationId") + "')"+ ">" + sData + "</a>";		
 

        };

    // Column definitions
    var myColumnDefs = [
{key:"educationId", hidden:true, sortable:true},
{key:"educationName",label:"<%=Constant.getResourceStringValue("admin.education.name",user14.getLocale())%>",width:150, sortable:true,resizeable:true,formatter:formatUrl},
{key:"educationDesc",label:"<%=Constant.getResourceStringValue("admin.education.desc",user14.getLocale())%>",width:250, sortable:false,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.education.status",user14.getLocale())%>", sortable:true,resizeable:true}

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/educationListPage.jsp?&educationName=<%=eduForm.getEducationName() %>&ddd="+(new Date).getTime()+"&");

    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"educationId"},
{key:"educationName"},
{key:"educationDesc"},
{key:"status"}
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=educationId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"educationId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
