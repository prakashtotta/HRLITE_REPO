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
System.out.println("*** applicantFiltersBody.jsp ****");
User user15 = (User)session.getAttribute(Common.USER_DATA);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<bean:define id="bgroupForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user15.getLocale())%></title>



</head>
<script language="javascript">
function createApplicantFiltergroup(){
	var url = "<%=request.getContextPath()%>/businessrule.do?method=createApplicantFilter";
	GB_showFullScreen('<%=Constant.getResourceStringValue("admin.businessRule.createApplicantFiltergrp",user15.getLocale())%>',url, messageret);
}
function editApplicantFiltergroup(bCrId){
	//alert(bCrId);

	var url = "<%=request.getContextPath()%>/businessrule.do?method=editApplicantFilter&fltid="+bCrId;
	GB_showFullScreen('<%=Constant.getResourceStringValue("admin.businessRule.editApplicantFiltergrp",user15.getLocale())%>',url,messageret);
	

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
	function searchFilters(){
	      document.businessRuleForm.action = "businessrule.do?method=searchApplicantGroupfilterList";
	   document.businessRuleForm.submit();
}

</script>

<body class="yui-skin-sam">

<html:form action="/businessrule.do?method=applicantFilters">
<div  class="div">
<table width="40%">
	<tr>
		<td><%=Constant.getResourceStringValue("admin.businessRule.name",user15.getLocale())%></td>
		<td><html:text property="name" size="50" maxlength="600"/></td>
	</tr>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.filter.varible.name",user15.getLocale())%></td>
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
	<%=Constant.getResourceStringValue("admin.filter.type",user15.getLocale())%>
	</td>
	<td>
	<html:select  property="variableType">
		<option value=""></option>
				<bean:define name="businessRuleForm" property="typeList" id="typeList" />
	            <html:options collection="typeList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	<td></td>
	<td></td>
		
	</tr>
	<tr>
	<td colspan="2">
	<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user15.getLocale())%>" onclick="searchFilters()" class="button"/>
	
	<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user15.getLocale())%>" onclick="resetdata()" class="button"/>
	</td>
	<td></td>
	<td></td>
	</tr>
	
</table>
</div>
<br><br>
</html:form>

<a  href="#" onClick="createApplicantFiltergroup()"><%=Constant.getResourceStringValue("admin.businessRule.createApplicantFiltergrp",user15.getLocale())%></a>
<br><br>
<b>Applicant filter list</b>
<br><br>

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editApplicantFiltergroup('" + oRecord.getData("businessCriteraId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };


		var populateDefaultdate = function(elCell, oRecord, oColumn, sData) {

			if(oRecord.getData("variableType") == 'date'){
				if(oRecord.getData("variableCriteria") == 'BETWEEN'){
				elCell.innerHTML = oRecord.getData("filterValueDate1") + " and "+oRecord.getData("filterValueDate2");
				}else{
					elCell.innerHTML = oRecord.getData("filterValueDate1");
				}
			}else{
				if(oRecord.getData("variableCriteria") == 'BETWEEN'){
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
{key:"businessCriteraId", hidden:true, sortable:true},
{key:"filterValueDate1", hidden:true, sortable:true},
{key:"filterValueDate2", hidden:true, sortable:true},
{key:"valueName", hidden:true, sortable:true},
{key:"name",label:"<%=Constant.getResourceStringValue("admin.filter.name",user15.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"variableName",label:"<%=Constant.getResourceStringValue("admin.filter.varible.name",user15.getLocale())%>", sortable:true,resizeable:true},
{key:"variableType",label:"<%=Constant.getResourceStringValue("admin.filter.type",user15.getLocale())%>", sortable:true,resizeable:true},
{key:"variableOrigin",label:"<%=Constant.getResourceStringValue("admin.filter.varible.origin",user15.getLocale())%>", sortable:true,resizeable:true},
{key:"variableCriteria",label:"<%=Constant.getResourceStringValue("admin.businessRule.criteria",user15.getLocale())%>", sortable:false,resizeable:true},
{key:"filterValue1",label:"<%=Constant.getResourceStringValue("admin.filter.value",user15.getLocale())%>", sortable:true,resizeable:true,formatter:populateDefaultdate},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.filter.status",user15.getLocale())%>", sortable:true,resizeable:true}
  
  
       ];
     

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/businessrule/applicantfilterListPage.jsp?name=<%=bgroupForm.getName()%>&variabletype=<%=bgroupForm.getVariableType()%>&variablename=<%=bgroupForm.getVariableName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"businessCriteraId"},
{key:"filterValueDate1"},
{key:"filterValueDate2"},
{key:"valueName"},
{key:"name"},
{key:"variableName"},
{key:"variableType"},
{key:"variableOrigin"},
{key:"variableCriteria"},
{key:"filterValue1"},
{key:"filterValue2"},
{key:"status"}

			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=businessCriteraId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"businessCriteraId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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