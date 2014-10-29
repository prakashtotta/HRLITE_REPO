<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
String datepattern = Constant.getValue("defaultdateformat");
RefferalEmployee refemp = (RefferalEmployee)session.getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
%>
<%
Set uomReleased = RefBO.getDistinctRedemptionUOMByReferral(refemp,Common.REFERRAL_REDEMPTION_RELEASED);
Set uomunReleased = RefBO.getDistinctRedemptionUOMByReferral(refemp,Common.REFERRAL_REDEMPTION_NOT_RELEASED);
%>
<script type="text/javascript">
function addfriend(){
	var url = "<%=request.getContextPath()%>/referfriend.do?method=createreferfriend";
	GB_showCenter('Refer a friend',url,400,600,messageret);

}
function messageret(){
	//window.location.reload();
}
</script>
<table width="100%">

<tr>
<td>
<b><a class="closelink" href="#" onClick="javascript:addfriend()">Refer a friends</a></b>

</td>
</tr>

<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
<tr>
<td>
<b>Released </b>
</td>
</tr>

<br>

<tr>
<td>



<%          
         Iterator<String> itrreleased = uomReleased.iterator();
         while(itrreleased.hasNext()){
         String uomr =	itrreleased.next();  
		 long totalamt = RefBO.getTotalRedemptionsByUOMBandState(refemp,uomr,Common.REFERRAL_REDEMPTION_RELEASED);
		 long totalamtyettobepaid = RefBO.getTotalRedemptionsNotPiadByUOMBandState(refemp,uomr,Common.REFERRAL_REDEMPTION_RELEASED);
%>		 
		
<table width="100%" bgcolor="#F0FFF0">
<tr>
<td>
<br>Total redemption: <%=totalamt%> <%=uomr%> </b>
</td>
<td>
<br>Total redemption yet to be paid: <%=totalamtyettobepaid%> <%=uomr%> </b>
</td>
<td>
<br>Total paid: <%=totalamt-totalamtyettobepaid%> <%=uomr%> </b>
</td>
</tr>
</table>
	<body class="yui-skin-sam">	
<div id="<%=uomr+"dynamicdata1"%>" class="div"></div>

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
		{key:"refferalSchemeTypeName", label:"Scheme type",sortable:true, resizeable:true},
		{key:"ruleName", label:"Rule",sortable:true, resizeable:true},
		{key:"state", label:"Bonus state",sortable:true, resizeable:true},
		{key:"applicantstate", label:"Applicant state",sortable:true, resizeable:true},
		{key:"eventdate", label:"Event Date", resizeable:true},
		{key:"releaseddate", label:"Released Date", resizeable:true},
		{key:"refferalSchemeAmount", label:"Bonus", sortable:true, resizeable:true},
		{key:"isPaid", label:"isPaid", sortable:true, resizeable:true}
		
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/homebodylistpage.jsp?uomr=<%=uomr%>&state=<%=Common.REFERRAL_REDEMPTION_RELEASED%>&ddd="+(new Date).getTime()+"&");
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
    var myDataTable = new YAHOO.widget.DataTable("<%=uomr+"dynamicdata1"%>", myColumnDefs, myDataSource, myConfigs);
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

<%}%>

</td>
</tr>
<tr>
<td>
<b> </b>
</td>
</tr>

<tr>
<td>
<b>Un-Released </b>
</td>
</tr>
<tr>
<td>



<%          
            Iterator<String> itrunreleased = uomunReleased.iterator();
           while(itrunreleased.hasNext()){
         String uomr =	itrunreleased.next(); 
		 long totalamt = RefBO.getTotalRedemptionsByUOMBandState(refemp,uomr,Common.REFERRAL_REDEMPTION_NOT_RELEASED);
%>	
<table width="100%" bgcolor="#F0FFF0">
<tr>
<td>
<br>Total redemption: <%=totalamt%> <%=uomr%> </b>
</td>
</tr>
</table>
	<body class="yui-skin-sam">	
<div id="<%=uomr+"dynamicdata"%>" class="div"></div>

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
		{key:"refferalSchemeTypeName", label:"Scheme type",sortable:true, resizeable:true},
		{key:"ruleName", label:"Rule",sortable:true, resizeable:true},
		{key:"state", label:"Bonus state",sortable:true, resizeable:true},
		{key:"applicantstate", label:"Applicant state",sortable:true, resizeable:true},
		{key:"eventdate", label:"Event Date", resizeable:true},
		{key:"releaseddate", label:"Released Date", resizeable:true},
		{key:"refferalSchemeAmount", label:"Bonus", sortable:true, resizeable:true}
		
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/homebodylistpage.jsp?uomr=<%=uomr%>&state=<%=Common.REFERRAL_REDEMPTION_NOT_RELEASED%>&ddd="+(new Date).getTime()+"&");
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
        initialRequest: "sort=refredid&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"refredid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("<%=uomr+"dynamicdata"%>", myColumnDefs, myDataSource, myConfigs);
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
<%}%>

</td>
</tr>
</table>
