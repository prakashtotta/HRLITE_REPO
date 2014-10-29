<%@ include file="../common/include.jsp" %>


  <%
 String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

<bean:define id="aform" name="agencyRedemptionForm" type="com.form.AgencyRedemptionForm" />
<%
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
 
%>

<script type="text/javascript">


$(function() {



	//$("#reviewername").autocomplete('jsp/talent/getUserData.jsp');
	$("#applicantName").autocomplete({
		url: 'jsp/agency/getMyRefferalRedumptionApplicantNames.jsp'
			
});

});
	</script>
<script language="javascript">




 function searchapplicant(){
	
document.agencyRedemptionForm.action = "agencyredemption.do?method=searchmyredeptions";
document.agencyRedemptionForm.submit();
}
 function resetdata(){
	 document.agencyRedemptionForm.applicantName.value="";
 }
function init(){

document.agencyRedemptionForm.applicantName.focus();

}
</script>


<body class="yui-skin-sam" onLoad="init()" >
<html:form action="/agencyredemption.do?method=searchmyredeptions">
<div class="div" >
<table border="0" width="100%">
		
	
		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
			<td align="left" width="20%"><html:text property="applicantName" styleId="applicantName"  size="30" styleClass="textdynamic" maxlength="600"/></td><td>
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchapplicant()" class="button">
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button">
		</td>
			
		</tr>

		

</table>
</div>
</html:form>





 <br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='agencyredemptiondetails.do?method=summarydetails&agredid=" + oRecord.getData("agredid") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };


    // Column definitions
    var myColumnDefs = [
			{key:"agredid", hidden:true},
		{key:"uuid", hidden:true},
		     {key:"applicantName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"JobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeAmount", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"uom", label:"UOM",sortable:true, resizeable:true},
		{key:"refferalSchemeTypeName", label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.rst",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"ruleName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.rule",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"state", label:"<%=Constant.getResourceStringValue("hr.Redemption.Bonus_state",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"applicantstate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Applicant_state",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"eventdate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Event_Date",user1.getLocale())%>", resizeable:true},
		{key:"releaseddate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Released_Date",user1.getLocale())%>", resizeable:true}
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/redemptionlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&applicantname=<%=aform.getApplicantName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"agredid"},
            {key:"applicantName"},           
		   	{key:"JobTitle"},
		{key:"refferalSchemeName"},
		{key:"refferalSchemeAmount"},
		{key:"uom"},
		{key:"refferalSchemeTypeName"},
		{key:"ruleName"},
		{key:"state"},
		{key:"applicantstate"},
		{key:"eventdate"},
			{key:"releaseddate"},
				{key:"uuid"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=agredid&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"agredid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

<%//}%>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->



</body>

