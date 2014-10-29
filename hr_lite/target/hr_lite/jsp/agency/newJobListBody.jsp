<%@ include file="../common/yahooincludes.jsp" %>
 <%@ include file="../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<style type="text/css">
	#myAutoComplete {
		width:16em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
</style>

<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>




<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />


<body class="yui-skin-sam">
<br>

<b><%=Constant.getResourceStringValue("Requisition.New_jobs_published_within",user1.getLocale())%> <%=Constant.getValue("new.jobs.offset")%> <%=Constant.getResourceStringValue("Requisition.month",user1.getLocale())%> ) </b> 
<div id="dynamicdata" class="div"></div>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {




var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="edit job requistion" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='agjob.do?method=jobdetails&reqid="+ oRecord.getData("jobreqId") + "'"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var jobdetails = function(elCell, oRecord, oColumn, sData) {

			  elCell.innerHTML = "<a href='agjob.do?method=jobdetails&reqid=" + oRecord.getData("jobreqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + oRecord.getData("jobTitle") + "</a>";
		
		};

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},
		{key:"uuid", hidden:true},		
            {key:"jobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%>", sortable:true, resizeable:true,formatter:jobdetails},
            {key:"organizationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%>", sortable:false, resizeable:true},
		   {key:"departmentValue", label:"<%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>", sortable:false, resizeable:true},
		 {key:"projectcodeValue", label:"<%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%>", sortable:false, resizeable:true},
		 {key:"jobtypeValue", label:"<%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%>", sortable:false, resizeable:true},
			{key:"workshiftValue", label:"<%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"publishedDate", label:"<%=Constant.getResourceStringValue("Requisition.JobPostedDate",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"targetfinishdate", label:"<%=Constant.getResourceStringValue("Requisition.Target.finish.date",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"agencyRefferalSchemeName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.Referralschemes",user1.getLocale())%>", sortable:false, resizeable:true}
				          
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/newjoblistpage.jsp?ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
            {key:"jobTitle"},
            {key:"organizationValue"},
		   {key:"departmentValue"},
			{key:"locationValue"},
		{key:"projectcodeValue"},
			{key:"jobtypeValue"},
			{key:"workshiftValue"},
			{key:"publishedDate"},
			{key:"targetfinishdate"},
			{key:"agencyRefferalSchemeName"},
			{key:"uuid"}
			
			
			
				
		
			
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




</body>

