<%@ include file="../common/include.jsp" %>

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
  <%
 String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

<bean:define id="aform" name="referralRedemptionForm" type="com.form.ReferralRedemptionForm" />
<%
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
 
%>

<script language="javascript">

 function searchapplicant(){
	
document.referralRedemptionForm.action = "referralredemption.do?method=searchmyredeptions";
document.referralRedemptionForm.submit();
}

function init(){

document.referralRedemptionForm.applicantName.focus();

}

function resetdata(){

document.referralRedemptionForm.applicantName.value="";

}

</script>

<body class="yui-skin-sam" onLoad="init()">
<html:form action="/referralredemption.do?method=searchmyredeptions">
<div class="div">
<table border="0" width="100%">
		
	
		<tr>
			<td width="20%">Applicant Name</td>
			<td width="20%"><html:text property="applicantName" styleId="applicantName"  styleClass="textdynamic" size="30" maxlength="600"/></td>
			<td><input type="button" name="search" value="search" onClick="searchapplicant()" class="button">
			<input type="button" name="reset" value="Reset" onClick="resetdata()" class="button">
			
			</td>
			<td>
	</td>
		</tr>

		
<tr>
<td>

</td>
<td></td>
</tr>
</table>
</div>
</html:form>

<br><br>

<b>Applicant redemption list</b>

<br>

 <br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='referralredemptiondetails.do?method=summarydetails&refredid=" + oRecord.getData("refredid") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };


    // Column definitions
    var myColumnDefs = [
			{key:"refredid", hidden:true},
		{key:"uuid", hidden:true},
		     {key:"applicantName", label:"Applicant name", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"JobTitle", label:"Job title",sortable:true, resizeable:true},
		{key:"refferalSchemeName", label:"Scheme name",sortable:true, resizeable:true},
		{key:"refferalSchemeAmount", label:"Bonus", sortable:true, resizeable:true},
		{key:"uom", label:"UOM",sortable:true, resizeable:true},
		{key:"refferalSchemeTypeName", label:"Scheme type",sortable:true, resizeable:true},
		{key:"ruleName", label:"Rule",sortable:true, resizeable:true},
		{key:"state", label:"Bonus state",sortable:true, resizeable:true},
		{key:"applicantstate", label:"Applicant state",sortable:true, resizeable:true},
		{key:"eventdate", label:"Event Date", resizeable:true},
		{key:"releaseddate", label:"Released Date", resizeable:true},
		{key:"isPaid", label:"isPaid", sortable:true, resizeable:true}
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/redemptionlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&applicantname=<%=aform.getApplicantName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"refredid"},
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
			{key:"isPaid"},
			{key:"uuid"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=refredid&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"refredid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

