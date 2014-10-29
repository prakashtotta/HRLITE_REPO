
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>
<bean:define id="mockquestionsetform" name="mockQuestionSetForm" type="com.form.MockQuestionSetForm" /> 
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function createMockQuestionSet(){
	var url = "<%=request.getContextPath()%>/mockquestionset.do?method=createMockQuestionSet";
	GB_showCenter("<%=Constant.getResourceStringValue("admin.mockquestionset.pagetitle.create",user1.getLocale())%>",url,300,600, messageret);
}

function editMockQuestionSet(catid){
	
	var url = "<%=request.getContextPath()%>/mockquestionset.do?method=MockQuestionSetDetails&catid="+catid;
	GB_showCenter("<%=Constant.getResourceStringValue("admin.mockquestionset.pagetitle.edit",user1.getLocale())%>",url,300,600, messageret);

	
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



function searchMockQuestionSet(){
  
       document.mockQuestionSetForm.action = "mockquestionset.do?method=searchMockQuestionSet";
	   document.mockQuestionSetForm.submit();
	}

function resetdata(){
     
	 document.mockQuestionSetForm.name.value=""; 
     document.mockQuestionSetForm.displayName.value=""; 
     document.mockQuestionSetForm.description.value=""; 
	}



</script>


<body class="yui-skin-sam">
<html:form action="/mockquestionset.do?method=saveTalentpool">
<div class="div">
<table width="100%">
	<tr> 
	
		<td><%=Constant.getResourceStringValue("admin.businessRule.name",user1.getLocale())%></td>
		
		<td><html:text property="name" size="30" maxlength="300"/></td><td>&nbsp;&nbsp;&nbsp;</td>
	
	     <td><%=Constant.getResourceStringValue("admin.mockquestionset.displayname",user1.getLocale())%></td>
		<td> <html:text property="displayName" size="30" maxlength="300"/></td>
	     
		
	</tr>
	
	<tr>
		<td><%=Constant.getResourceStringValue("onboarding.taskdef.page.table.configuration.taskDesc",user1.getLocale())%></td>
		<td><html:text property="description" size="30" maxlength="500"/></td>
		
	</tr>
		
			
			
	<tr>
		<td colspan="2"><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onclick="searchMockQuestionSet()" class="button"/>&nbsp;<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onclick="resetdata()" class="button"/></td>
	</tr>
</table>
</div>
<br><br>
</html:form>


<a class="button" href="#" onClick="createMockQuestionSet()"><%=Constant.getResourceStringValue("admin.mockquestionset.listpage.createexamsetlov",user1.getLocale())%></a>
<br><br>
<b>Exam list</b>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editMockQuestionSet('" + oRecord.getData("catId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"catId", hidden:true, sortable:true},
{key:"name",label:"<%=Constant.getResourceStringValue("admin.businessRule.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"displayName",label:"<%=Constant.getResourceStringValue("admin.mockquestionset.displayname",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"description",label:"<%=Constant.getResourceStringValue("onboarding.taskdef.page.table.configuration.taskDesc",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("requisition.page.table.configutaion.status",user1.getLocale())%>", sortable:true,resizeable:true}
  
        ];
     

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/testengine/mockquestionsetListPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&name=<%=mockquestionsetform.getName()%>&displayname=<%=mockquestionsetform.getDisplayName()%>&description=<%=mockquestionsetform.getDescription()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"catId"},
{key:"name"},
{key:"displayName"},
{key:"description"},
{key:"status"}


			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=catId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"catId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
