
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="tform" name="tagsform" type="com.form.TagsForm" /> 
<%
String searchpagedisplay = (String)request.getAttribute("searchpage");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function createTags(){
	var url = "<%=request.getContextPath()%>/tags.do?method=createtags";
	GB_showCenter("<%=Constant.getResourceStringValue("admin.tags.bodypage.createtag",user1.getLocale())%>",url,250,600, messageret);
}

function editjTags(tagid){
	var url = "<%=request.getContextPath()%>/tags.do?method=tagsDetails&tagid="+tagid;
	GB_showCenter("<%=Constant.getResourceStringValue("admin.tags.bodypage.edittag",user1.getLocale())%>",url,250,600, messageret);

	
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

       document.tagsform.action = "tags.do?method=searchTagsListpage";
	   document.tagsform.submit();

	}

	function resetdata(){
      document.tagsform.tagName.value="";
      document.tagsform.tagType.value="";


	}

</script>


<body class="yui-skin-sam">

<html:form action="/tags.do?method=saveTags">
<div class="div">

<table width="100%">
	<tr> 
		<td><%=Constant.getResourceStringValue("admin.tags.bodypage.search.tagname",user1.getLocale())%></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%=Constant.getResourceStringValue("admin.tags.bodypage.search.tagtype",user1.getLocale())%></td>
		 
	</tr>
	<tr>
		 
		 <td><html:text property="tagName" size="30" maxlength="300"/></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		 <td><html:text property="tagType" size="30" maxlength="300"/></td> 
	</tr>
	
	<tr>
		<td colspan="4"><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onclick="searchTags()" class="button"/>&nbsp;<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onclick="resetdata()" class="button"/></td>
	</tr>
</table>
</div>
</html:form>
<br><br>

<a class="button" href="#" onClick="createTags()"><%=Constant.getResourceStringValue("admin.tags.bodypage.createtag",user1.getLocale())%></a>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<b><%=Constant.getResourceStringValue("admin.tags.bodypage.lebal.taglist",user1.getLocale())%></b>
<br><br>

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editjTags('" + oRecord.getData("tagId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"tagId", hidden:true, sortable:true},
{key:"tagName",label:"<%=Constant.getResourceStringValue("aquisition.applicant.configutaion.tagName",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl}



        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/tagsListPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&tagsname=<%=tform.getTagName()%>&type=<%=tform.getTagType()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"tagId"},
{key:"tagName"},
{key:"tagType"}

			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=tagId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"tagId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
