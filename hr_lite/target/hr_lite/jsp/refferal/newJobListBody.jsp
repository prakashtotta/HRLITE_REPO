<%@ include file="../common/yahooincludes.jsp" %>
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



  <%
  String datepattern = Constant.getValue("defaultdateformat");

%>

<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />


<body class="yui-skin-sam">
<br>

<b>New jobs (published within <%=Constant.getValue("new.jobs.offset")%> month(s) ) </b> 
<div id="dynamicdata" class="div"></div>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {




var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="edit job requistion" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='refjob.do?method=jobdetails&reqid="+ oRecord.getData("jobreqId") + "'"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},		
            {key:"jobTitle", label:"Job Title", sortable:true, resizeable:true,formatter:formatUrl1},
            {key:"organizationValue", label:"Organization Name", sortable:false, resizeable:true},
		   {key:"departmentValue", label:"Department Name", sortable:false, resizeable:true},
		{key:"locationValue", label:"Location", sortable:false, resizeable:true},
		 {key:"projectcodeValue", label:"Project Code", sortable:false, resizeable:true},
		 {key:"jobtypeValue", label:"Job Type", sortable:false, resizeable:true},
			{key:"workshiftValue", label:"Work Shift", sortable:false, resizeable:true},
		{key:"publishedDate", label:"Job Posted Date", sortable:false, resizeable:true},
		{key:"targetfinishdate", label:"Target finish date", sortable:false, resizeable:true}
				          
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/newjoblistpage.jsp?ddd="+(new Date).getTime()+"&");
  
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
			{key:"targetfinishdate"}
			
			
			
				
		
			
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

