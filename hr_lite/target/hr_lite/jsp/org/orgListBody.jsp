<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>

<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>


<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
////response.setHeader("Cache-Control", "no-cache");
//		//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>

<%

String orgId = (String)request.getParameter("orgId");

if(orgId == null){
	Organization orgparent = BOFactory.getOrganizationBO().getParentOrganizationInfo(user1.getSuper_user_key());
	orgId= String.valueOf(orgparent.getOrgId());
}
List<String> treedatalist = BOFactory.getOrganizationBO().getJavaScriptTree(orgId,user1);

String nodeselectdata =  BOFactory.getOrganizationBO().getJavaScriptTreeForSelectNode(orgId);



%>
<script language="javascript">

function editOrg(org_id){
	
	var url = "<%=request.getContextPath()%>/org.do?method=editorg&orgid="+org_id;
	//parent.showPopWin(url, 600, 500, showmessage,true);
	 GB_showCenter('<%=Constant.getResourceStringValue("admin.organization.orglistbody.editorgscreen",user1.getLocale())%>',url,350,750, showmessage);

}
function orgInfo(org_id){
	
	var url = "<%=request.getContextPath()%>/org.do?method=editorg&readPreview=2&orgid="+org_id;
	//parent.showPopWin(url, 600, 500, showmessage,true);
	 GB_showCenter('<%=Constant.getResourceStringValue("admin.organization.orglistbody.editorgscreen",user1.getLocale())%>',url,350,750, showmessage);

}
function addOrg(){
	var url = "<%=request.getContextPath()%>/org.do?method=createorg";
	 GB_showCenter('<%=Constant.getResourceStringValue("admin.organization.orglistbody.createorgscreen",user1.getLocale())%>',url,350,750, showmessage);

}
function showmessage(returnval) { 
	//window.location.reload();
	}


function createDepartment(url){
	
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Deparment.createdeptscreen",user1.getLocale())%>', url,250,700, messageret1);
}
function editDepartment(id){
	var url = "<%=request.getContextPath()%>/dept.do?method=editDepartmentDetails&id="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Deparment.editdeptscreen",user1.getLocale())%>', url,250,700, messageret1);
}


function messageret1(){
	//window.location.reload();
			}



$(document).ready(function() {
	    <%=treedatalist.get(1)%>
}); 


function retriveData(urld) { 
$.ajax({
  url: urld,
  success: function(data){
  $('#data').html(data);
	completeajx();
  }
});
} 

 
function completeajx(){
	  $.unblockUI();
}


</script>
<table border="0" width="100%">

<tr>
<td bgcolor="#f3f3f3" valign="top" width="100%" height="10px">
<a class="button" href="#" onclick="addOrg()"> <%=Constant.getResourceStringValue("admin.organization.orglistbody.addorganization",user1.getLocale())%></a>
</td>
</tr>
</table>

<table border="0" width="1200px">

<tr>
<td valign="top" width="30%">
	<table border="0" width="100%">

<div class="dtree">

	<br>

	 <%=treedatalist.get(0)%>

</div>


</table>
</td>




<td valign="top"  width="70%">
<br> 
<body class="yui-skin-sam">
<span id="data">
<br><br><b><%=Constant.getResourceStringValue("admin.organization.orglistbody.ChildOrganizationsList",user1.getLocale())%> </b>
<br> <br> 

<div id="dynamicdata" class="div"></div>
<table border="0" width="100%">
<%

if(orgId != null) {
%>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a href='#' onClick=editOrg('" + oRecord.getData("orgId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrlParent = function(elCell, oRecord, oColumn, sData) {
		 elCell.innerHTML = "<a href='#' onClick=editOrg('" + oRecord.getData("parent_org_id") + "')"+ ">" + sData + "</a>";
       
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }



    // Column definitions
    var myColumnDefs = [
			{key:"orgId", hidden:true},
		{key:"parent_org_id", hidden:true},
            {key:"orgCode", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationCode",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"orgName", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%>", sortable:true, resizeable:true},
		   	{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>", sortable:false, resizeable:true},
		   {key:"organizationTypeValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationType",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"currencyCode", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.organizationcurrency",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"parentOrgName", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.parentorganization",user1.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrlParent}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    


    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/org/orglistpage.jsp?orgId=<%=orgId%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"orgId"},
            {key:"orgCode"},
            {key:"orgName"},
		   {key:"locationCodeValue"},
			{key:"locationValue"},
		{key:"organizationTypeValue"},
			{key:"currencyCode"},
			{key:"parent_org_id"},
			{key:"parentOrgName"}
	
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=orgId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"orgId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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

<%
}
%>
</table>
</body>
<br><br>


<br>

 <%
 String depturl = request.getContextPath()+"/dept.do?method=createDepartment&orgId="+orgId+"&readPreview=3";
 %>
<a class="button" href="#" onClick="javascript:createDepartment('<%=depturl%>')"><%=Constant.getResourceStringValue("admin.Deparment.createdeptscreen",user1.getLocale())%></a>
<br><br>

<b> <%=Constant.getResourceStringValue("admin.Deparment.DepartmentsList",user1.getLocale())%> </b> <br> <br>
<body class="yui-skin-sam">
<div id="dynamicdata1" class="div"></div>

<%
if(orgId != null) {
%>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {

		  elCell.innerHTML = "<a href='#' onClick=editDepartment('" + oRecord.getData("departmentId") + "')"+ ">" + sData + "</a>";
        };

    // Column definitions
    var myColumnDefs = [
			{key:"departmentId", hidden:true},
            {key:"departmentCode", label:"<%=Constant.getResourceStringValue("admin.Deparment.deptcode",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"departmentName", label:"<%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%>", sortable:true, resizeable:true},
		   {key:"departmentDesc", label:"<%=Constant.getResourceStringValue("admin.Deparment.primaryfocusarea",user1.getLocale())%>", sortable:true, resizeable:true},
		 {key:"organizationname", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>", sortable:false, resizeable:true}
			
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    


    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/org/deptlistpage.jsp?orgId=<%=orgId%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"departmentId"},
            {key:"departmentCode"},
            {key:"departmentName"},
		   {key:"departmentDesc"},
			{key:"organizationname"}
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=departmentId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"departmentId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata1", myColumnDefs, myDataSource, myConfigs);
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

<%
}
%>

</span>
</td>
</tr>
</table>

<%=nodeselectdata%>