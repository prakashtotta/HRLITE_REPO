<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">




<!--there is no custom header content for this example-->

</head>
<script language="javascript">


/*function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	//parent.setPopTitle1("Applicant");
	//parent.showPopWin(url, 700, 600, null,true);
	GB_showFullScreen('Edit Applicant',url,messageret);
}*/


function editapplicant(aid){

	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,700,1000, messageret);
}

	function messageret(){
	window.location.reload();
	//history.go(0);

			}

</script>

<body class="yui-skin-sam">
<br>
 <%=Constant.getResourceStringValue("aquisition.applicant.list",user1.getLocale())%> <br>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "'"+ " target='new'>" + sData + "</a>";
        };
var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };
    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
           {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl,width:150},
             {key:"email", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"phone", label:"<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>",sortable:true, resizeable:true},
		//{key:"countryName", sortable:true, resizeable:true},
		//{key:"heighestQualification",label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
		//{key:"qualifications", sortable:true, resizeable:true},
		{key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",sortable:true, resizeable:true,width:140},
		{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true,width:135},
		{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true,width:180},
		{key:"referrerName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"createdBy", label:"<%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", resizeable:true},
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/applicanttrackinglistpage.jsp?status=<%=form.getInterviewState()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},
            {key:"resumeHeader"},
			{key:"email"},
		{key:"phone"},
	//	{key:"countryName"},
		{key:"heighestQualification"},
		//{key:"qualifications"},
		{key:"interviewState"},
		{key:"appliedDate"},
		{key:"jobTitle"},
		{key:"referrerName"},
			{key:"createdBy"},
		{key:"ownername"},
			{key:"uuid"},
			{key:"edit"}
			
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>
