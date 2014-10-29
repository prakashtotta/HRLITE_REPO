<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/yahooincludes.jsp" %>

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

String orgId = "2";//(String)request.getParameter("orgId");
System.out.println("orgId in deptListBody.jsp"+orgId);
%>


<br>
<b> <%=Constant.getResourceStringValue("admin.Deparment.DepartmentsList",user1.getLocale())%></b> <br><br>

<a class="button" class="submodal-600-500" href="<%=request.getContextPath()%>/dept.do?method=adddept"><%=Constant.getResourceStringValue("admin.Deparment.createdeptscreen",user1.getLocale())%></a>
<br>
<body class="yui-skin-sam">
<div id="dynamicdata" class="div"></div>

<%
if(orgId != null) {
%>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='dept.do?method=applicantDetails&departmentId=" + oRecord.getData("departmentId") + "'"+ " >" + sData + "</a>";
        };

    // Column definitions
    var myColumnDefs = [
			{key:"departmentId", hidden:true},
            {key:"departmentCode", label:"<%=Constant.getResourceStringValue("admin.Deparment.deptcode",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"departmentName", label:"<%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%>", sortable:true, resizeable:true},
		   {key:"departmentDesc", label:"<%=Constant.getResourceStringValue("admin.Deparment.desc",user1.getLocale())%>", sortable:true, resizeable:true}
			
					
           
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
		   {key:"departmentDesc"}
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=departmentId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"departmentId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
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
