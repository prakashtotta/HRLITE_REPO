<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>

<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<%




%>
<script language="javascript">


function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);
}
function messageret(){
	//window.location.reload();
	//history.go(0);

			}

function showmessage(returnval) { 
	window.location.reload();
	}

function editJobReqTemplte(tmpl_id){
		var url = "<%=request.getContextPath()%>/jobtemplate.do?method=edittemplate&templateId="+tmpl_id;
	//parent.showPopWin(url, 800, 600, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.edittemp",user1.getLocale())%>', url, messageret);
}
	function opentjobrequistionpage(){
  window.open("jobreq.do?method=createjobreq", "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

</script>





<br>
<b> <%=Constant.getResourceStringValue("Requisition.myreqapprovallist",user1.getLocale())%></b> <br><br>



<br>
<body class="yui-skin-sam">
<div id="dynamicdata"></div>
<table border="0" width="100%">



<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var ndata = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>" height="20"  width="19" align="middle"/>';
		var saveasdata =  '<img src="jsp/images/saveas.gif" border="0" alt="save as job requistion" title="<%=Constant.getResourceStringValue("Requisition.saveasjobreq",user1.getLocale())%>" height="20"  width="19" align="middle"/>';
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + sData + "</a>";
		var saveasqd = "<a href='#' onClick=saveasJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + saveasdata + "</a>";

		 elCell.innerHTML = editreqd;

        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReqTemplte('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},		
            {key:"jobreqName", label:"<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"organizationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%>", sortable:false, resizeable:true},
		   {key:"departmentValue", label:"<%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>", sortable:false, resizeable:true},
		 {key:"projectcodeValue", label:"<%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%>", sortable:false, resizeable:true},
		 {key:"jobtypeValue", label:"<%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%>", sortable:false, resizeable:true},
			{key:"workshiftValue", label:"<%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"hiringMgrValue", label:"<%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"templateName", label:"<%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl1},
		{key:"state", label:"<%=Constant.getResourceStringValue("Requisition.state",user1.getLocale())%>", sortable:true, resizeable:true}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/myapprovaljobrequisitionlistpage.jsp?cuurentownerid=<%=user1.getUserId()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
            {key:"jobreqName"},
            {key:"organizationValue"},
		   {key:"departmentValue"},
			{key:"locationValue"},
		{key:"projectcodeValue"},
			{key:"jobtypeValue"},
			{key:"workshiftValue"},
			{key:"hiringMgrValue"},
			{key:"templateName"},
			{key:"state"},
			{key:"templateId"}
			
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
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


</table>
</body>
