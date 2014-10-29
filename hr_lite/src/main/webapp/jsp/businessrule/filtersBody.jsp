<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<%
User user14 = (User)session.getAttribute(Common.USER_DATA);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user14.getLocale())%></title>

</head>

<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />

<script language="javascript">
function createFilter(){
	var url = "<%=request.getContextPath()%>/businessrule.do?method=createFilter";
	GB_showFullScreen('<%=Constant.getResourceStringValue("admin.businessRule.createFilter",user14.getLocale())%>',url, messageret);
}
function editFilter(id){
	var url = "<%=request.getContextPath()%>/businessrule.do?method=editFilter&fltid="+id;
	GB_showFullScreen('<%=Constant.getResourceStringValue("admin.businessRule.editFilter",user14.getLocale())%>',url, messageret);
}


function searchFilters(){
	      document.businessRuleForm.action = "businessrule.do?method=searchfilterList";
	   document.businessRuleForm.submit();
}



function showmessage(returnval) { 
	//window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}
	function resetdata(){
		document.businessRuleForm.name.value="";
		document.businessRuleForm.variableName.value="";
		document.businessRuleForm.variableType.value="";
				}

</script>

<body class="yui-skin-sam">

<html:form action="/businessrule.do?method=filterList">
<div class="div">
<table width="40%">
	<tr>
		<td><%=Constant.getResourceStringValue("admin.businessRule.name",user14.getLocale())%></td>
		<td><html:text property="name" size="50" maxlength="600"/></td>
	</tr>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.filter.varible.name",user14.getLocale())%></td>
		<td>
		<html:select  property="variableName">
			<option value=""></option>
				<bean:define name="businessRuleForm" property="screenfieldsList" id="screenfieldsList" />
	            <html:options collection="screenfieldsList" property="key"  labelProperty="value"/>
			</html:select>
		</td>
	</tr>
	<tr>
	<td>
	<%=Constant.getResourceStringValue("admin.filter.type",user14.getLocale())%>
	</td>
	<td>
	<html:select  property="variableType">
		<option value=""></option>
				<bean:define name="businessRuleForm" property="typeList" id="typeList" />
	            <html:options collection="typeList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	</tr>
	<tr>
	<td colspan="2"> <br><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user14.getLocale())%>" onclick="searchFilters()"class="button"/>

	<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user14.getLocale())%>" onclick="resetdata()" class="button"/>

	</td>
	<td></td>
	<td></td>
	</tr>
	
</table>
</div>
</html:form>
<br><br>
<a class="button" href="#" onClick="createFilter()"><%=Constant.getResourceStringValue("admin.businessRule.createFilter",user14.getLocale())%></a>
<br><br>
<b>Filter criteria list</b>
<br><br>

<div class="div">
<table>
<tr>
<td valign="top">
<div id="dynamicdata"></div>
</td>
<td width="50px">
</td>
<td valign="top">
<font size="2">
<%=Constant.getResourceStringValue("filter.example.help",user14.getLocale())%>
</font>
<br><br>
<font size="2">
<%=Constant.getResourceStringValue("filter.example.execute.help",user14.getLocale())%>
</font>
</td>
</tr>
</table>
</div>
<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editFilter('" + oRecord.getData("filterConditionId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };


		var populateDefaultdate = function(elCell, oRecord, oColumn, sData) {

		if(oRecord.getData("variableType") == 'date'){
			if(oRecord.getData("filterCriteria") == 'BETWEEN'){
			elCell.innerHTML = oRecord.getData("filterValueDate1") + " and "+oRecord.getData("filterValueDate2");
			}else{
				elCell.innerHTML = oRecord.getData("filterValueDate1");
			}
		}else{
			if(oRecord.getData("filterCriteria") == 'BETWEEN'){
				elCell.innerHTML = oRecord.getData("filterValue1") + " and "+oRecord.getData("filterValue2");
			}else{
			elCell.innerHTML = oRecord.getData("filterValue1");
			}
		}
		if(oRecord.getData("variableType") == 'list'){
			elCell.innerHTML = oRecord.getData("valueName");
		}
		
         
     
        };

    // Column definitions
    var myColumnDefs = [
{key:"filterConditionId", hidden:true, sortable:true},
{key:"filterValueDate1", hidden:true, sortable:true},
{key:"filterValueDate2", hidden:true, sortable:true},
{key:"valueName", hidden:true, sortable:true},
{key:"name",label:"<%=Constant.getResourceStringValue("admin.filter.name",user14.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"variableName",label:"<%=Constant.getResourceStringValue("admin.filter.varible.name",user14.getLocale())%>", sortable:true,resizeable:true},
{key:"variableType",label:"<%=Constant.getResourceStringValue("admin.filter.type",user14.getLocale())%>", sortable:true,resizeable:true},
{key:"variableOrigin",label:"<%=Constant.getResourceStringValue("admin.filter.varible.origin",user14.getLocale())%>", sortable:true,resizeable:true},
{key:"filterCriteria",label:"<%=Constant.getResourceStringValue("admin.businessRule.criteria",user14.getLocale())%>", sortable:false,resizeable:true},
{key:"filterValue1",label:"<%=Constant.getResourceStringValue("admin.filter.value",user14.getLocale())%>", sortable:true,resizeable:true,formatter:populateDefaultdate}

  
       ];
     

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/businessrule/filterListPage.jsp?name=<%=bForm.getName()%>&variabletype=<%=bForm.getVariableType()%>&variablename=<%=bForm.getVariableName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"filterConditionId"},
{key:"filterValueDate1"},
{key:"filterValueDate2"},
{key:"valueName"},
{key:"name"},
{key:"variableName"},
{key:"variableType"},
{key:"variableOrigin"},
{key:"filterCriteria"},
{key:"filterValue1"},
{key:"filterValue2"}


			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=filterConditionId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"filterConditionId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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