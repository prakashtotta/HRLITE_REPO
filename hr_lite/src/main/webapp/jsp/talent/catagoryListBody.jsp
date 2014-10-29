<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%@ page import="java.io.*"%>

<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="cform" name="catagoryform" type="com.form.CatagoryForm" /> 
<%
String searchpagedisplay = (String)request.getAttribute("searchpageCatagory");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function createCatagory(){
	var url = "<%=request.getContextPath()%>/catagory.do?method=createCatagory";
	GB_showCenter("<%=Constant.getResourceStringValue("admin.category.pagetitle.create",user1.getLocale())%>",url,300,630, messageret);
}

function editCategory(cat_id){
	var url = "<%=request.getContextPath()%>/catagory.do?method=categoryDetails&catagoryid="+cat_id;
	GB_showCenter("<%=Constant.getResourceStringValue("admin.category.pagetitle.edit",user1.getLocale())%>",url,300,630, messageret);

	
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

	function searchTags(){

       document.catagoryform.action = "catagory.do?method=searchCatagoryListpage";
	   document.catagoryform.submit();
       
	}

	function resetdata(){
      document.catagoryform.catName.value="";
      


	}

</script>


<body class="yui-skin-sam">

<html:form action="/catagory.do?method=saveCatagory">
<div class="div">
<table border="0" width="100%">
	<tr> 
		<td width="50%"><%=Constant.getResourceStringValue("admin.category.listbodypage.categoryname",user1.getLocale())%></td>
		<td><html:text property="catName" size="22" maxlength="300"/></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onclick="searchTags()" class="button" />
		<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onclick="resetdata()" class="button"/></td>
	</tr>
</table>
</div>
</html:form>
<br><br>
<a class="button" href="#" onClick="createCatagory()"><%=Constant.getResourceStringValue("admin.category.pagetitle.create",user1.getLocale())%></a>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editCategory('" + oRecord.getData("catId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"catId", hidden:true, sortable:true},
{key:"catName",label:"<%=Constant.getResourceStringValue("admin.category.listbodypage.categoryname",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"catDesc",label:"<%=Constant.getResourceStringValue("admin.variable.variable_description",user1.getLocale())%>", sortable:false,resizeable:true}



        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/catagoryListPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&categorygname=<%=cform.getCatName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"catId"},
{key:"catName"},
{key:"catDesc"}


			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=catId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"catId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
