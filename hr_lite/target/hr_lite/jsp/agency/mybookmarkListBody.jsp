<%@ include file="../common/include.jsp" %>

 <%
  String path = (String)request.getAttribute("filePath");
  String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
String reqId = (String)request.getAttribute("requistionId");
String uuid = (String)request.getAttribute("uuid");

%>
<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<script language="javascript">

function deletebookmark(id){

	
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
		
	document.jobRequisitionForm.action = "agjob.do?method=deleteMyBookmark&favjobid="+id;
	document.jobRequisitionForm.submit();

		
		
		
	 }

}



</script>

<body class="yui-skin-sam">

<%if(reqId != null){ %>

<a  href="<%=request.getContextPath()%>/agjob.do?method=jobdetails&reqid=<%=reqId%>&secureid=<%=uuid%>" ><%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%></a>
<%} %>

<html:form action="/agjob.do?method=saveQuestionsGroup" >

</html:form>



<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {

var jobdetails = function(elCell, oRecord, oColumn, sData) {

			  elCell.innerHTML = "<a href='agjob.do?method=jobdetails&backurl=agjob.do?method=mybookmarks&reqid=" + oRecord.getData("reqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + oRecord.getData("jobTitle") + "</a>";
		
		};
    var formatUrldelete = function(elCell, oRecord, oColumn, sData) {


           elCell.innerHTML = "<a href='#' onClick=deletebookmark('" + oRecord.getData("favjobid") + "')"+ ">" + sData + "</a>";
			   
		   
		   
		  
    };

    // Column definitions
    var myColumnDefs = [
			{key:"reqId", hidden:true},
			{key:"favjobid", hidden:true},
			{key:"uuid", hidden:true},
		    {key:"jobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%>", sortable:true, resizeable:true,formatter:jobdetails},
            {key:"comment", label:"<%=Constant.getResourceStringValue("refferal.agencyreferralportal.mybookmarks.bookmarkname",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"createdDate", label:"<%=Constant.getResourceStringValue("admin.Location.createddate",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"delete", label:"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>", resizeable:true,formatter:formatUrldelete}
		
		
			
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/mybookmarklistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"favjobid"},
            {key:"reqId"},
            {key:"jobTitle"},
			{key:"comment"},
		{key:"createdDate"},
			{key:"uuid"},
		{key:"delete"}
			
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=favjobid&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"favjobid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>