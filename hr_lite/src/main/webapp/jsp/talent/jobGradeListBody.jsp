<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<%@ page import="java.io.*"%>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="jobgradeform" name="jobGradeForm" type="com.form.JobGradeForm" /> 
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
function createjobgrade(){
	var url = "<%=request.getContextPath()%>/jobgrade.do?method=createJobGrade&readPreview=3";
	GB_showCenter(' <%=Constant.getResourceStringValue("admin.JobGrade.createjobgrade",user1.getLocale())%>',url,320,700, messageret);
}

function editjobgrade(jobcod_id){
	var url = "<%=request.getContextPath()%>/jobgrade.do?method=jobGradeDetails&readPreview=1&id="+jobcod_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.JobGrade.editscreenname",user1.getLocale())%> ',url,320,700, messageret);

	
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

	function searchJobGrade(){

       document.jobGradeForm.action = "jobgrade.do?method=searchJobGradeListpage";
	   document.jobGradeForm.submit();

	}
	function resetdata(){
		document.jobGradeForm.jobGradeName.value="";


		}

</script>


<body class="yui-skin-sam">

<html:form action="/jobgrade.do?method=searchJobGradeListpage">
<b>Job grade search</b><br><br>
<div class="div">
<table width="100%">
<tr> 
	<td><%=Constant.getResourceStringValue("admin.JobGrade.name",user1.getLocale())%> </td>
	<td><html:text property="jobGradeName" size="22" maxlength="300"/> </td>
	</tr>
	<tr>
	<td colspan="2"><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onclick="searchJobGrade()" class="button"/>&nbsp;<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onclick="resetdata()" class="button"/>
	
	</tr>
</table>
</div>
</html:form>
<br><br>
<a class="button" href="#" onClick="createjobgrade()"><%=Constant.getResourceStringValue("admin.JobGrade.createjobgrade",user1.getLocale())%></a>
<br><br>
<b>Job grade list</b>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editjobgrade('" + oRecord.getData("jobgradeId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"jobgradeId", hidden:true, sortable:true},
{key:"jobGradeName",label:"<%=Constant.getResourceStringValue("admin.JobGrade.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"jobGradeDesc",label:"<%=Constant.getResourceStringValue("admin.JobGrade.desc",user1.getLocale())%>", sortable:false,resizeable:true},
{key:"jobGradeResponsibility",label:"<%=Constant.getResourceStringValue("admin.JobGrade.resp",user1.getLocale())%>", sortable:true,resizeable:true}

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/jobGradeListPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&jobgradename=<%=jobgradeform.getJobGradeName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"jobgradeId"},
{key:"jobGradeName"},
{key:"jobGradeDesc"},
{key:"jobGradeResponsibility"}
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobgradeId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobgradeId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
