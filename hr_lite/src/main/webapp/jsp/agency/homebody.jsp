<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../common/includeLogin.jsp" %>


<script type="text/javascript">
animatedcollapse.addDiv('assinedreq', 'fade=0,speed=400,group=pets1')
animatedcollapse.addDiv('relasedstate', 'fade=0,speed=400,group=pets2,hide=1')
animatedcollapse.addDiv('unrelasedstate', 'fade=0,speed=400,group=pets3,hide=1')

animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()

</script>

<style type="text/css">

.button1 {
   border-top: 1px solid #96d1f8;
   background: #3B5999;
   padding: 5px 10px;
   border-radius: 8px;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: #ccc;
   font-size: 14px;
   font-family: Georgia, serif;
   text-decoration: none;
   vertical-align: middle;
   }
.button1:hover {
   border-top-color: #3B5998;
   background: #3B5998;
   color: white;
   }

	#chart
	{
       position:relative;
	   display:block; 
		width: 600px;
		height: 250px;
	

	}

   #chart1
	{
		width: 600px;
		height: 150px;
	}
	   #chart2
	{
		width: 600px;
		height: 150px;
	}
	   #chart3
	{
		width: 600px;
		height: 150px;
	}
	   #chart4
	{
		width: 600px;
		height: 150px;
	}
	   #chart5
	{
		width: 600px;
		height: 150px;
	}

   #chartapplicantsummary
	{
		width: 600px;
		height: 150px;
	}

	#chartagencyperformance
	{
		width: 600px;
		height: 150px;
	}

		#chartagencyoverallperformance
	{
		width: 600px;
		height: 150px;
	}
</style>
<%
String url = Constant.getValue("internal.url") + "jsp/yahoo/build/charts/assets/charts.swf";
User agency = (User)session.getAttribute(Common.AGENCY_DATA);
%>

<!-- assiged requitions -->

<div class="button1"><a href="#" rel="toggle[assinedreq]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>  My Active Requistions </div>
<div id="assinedreq" style="width  100%;">
	
	<table>
	<tr>
	<td>
 	<body class="yui-skin-sam">
	<div id="myreqdynamicdata"></div>
	</body>

</td>
<td>
</td>
</tr>
</table>
</div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
		

	var summaryUrl = function(elCell, oRecord, oColumn, sData) {

			  elCell.innerHTML = "<a href='agjob.do?method=requistionapplicantlist&state=0&requistionId=" + oRecord.getData("jobreqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ " >" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",agency.getLocale())%>" + "</a>";
		
		};

		var jobdetails = function(elCell, oRecord, oColumn, sData) {

			  elCell.innerHTML = "<a href='agjob.do?method=jobdetails&reqid=" + oRecord.getData("jobreqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + oRecord.getData("jobTitle") + "</a>";
		
		};



    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
			{key:"templateId", hidden:true},	
			{key:"uuid", hidden:true},
			{key:"status", hidden:true},
            {key:"jobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",agency.getLocale())%>", sortable:true, resizeable:true,formatter:jobdetails},
            {key:"organizationValue", label:"<%=Constant.getResourceStringValue("admin.organization.organization",agency.getLocale())%>", sortable:false, resizeable:true},
			{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",agency.getLocale())%>", sortable:false, resizeable:true},
			{key:"recruiterName", label:"<%=Constant.getResourceStringValue("Requisition.Recruiter",agency.getLocale())%>", sortable:true, resizeable:true},
			{key:"publishedDate", label:"<%=Constant.getResourceStringValue("Requisition.JobPostedDate",agency.getLocale())%>", sortable:true, resizeable:true},
			{key:"targetfinishdate", label:"<%=Constant.getResourceStringValue("Requisition.Target.finish.date",agency.getLocale())%>", sortable:true, resizeable:true},
			//{key:"agencyRefferalSchemeName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.Referralschemes",agency.getLocale())%>", sortable:false, resizeable:true},
			{key:"state", label:"<%=Constant.getResourceStringValue("Requisition.status",agency.getLocale())%>", sortable:false, resizeable:true},
			{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary",agency.getLocale())%>", sortable:false, resizeable:true,formatter:summaryUrl}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/myjobrequisitionlistpage.jsp?currentuserid=<%=agency.getUserId()%>&currentusername=<%=agency.getUserName()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
            {key:"jobreqName"},
			{key:"jobTitle"},
            {key:"organizationValue"},
		   {key:"departmentValue"},
			{key:"locationValue"},
		{key:"projectcodeValue"},
			{key:"jobtypeValue"},
			{key:"workshiftValue"},
			{key:"hiringMgrValue"},
			{key:"templateName"},
			{key:"state"},
			{key:"status"},
			{key:"templateId"},
			{key:"recruiterName"},
			{key:"publishedDate"},
			{key:"targetfinishdate"},
			{key:"agencyRefferalSchemeName"},
			{key:"uuid"},
			{key:"summary"}
			
			
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=5", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:5 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("myreqdynamicdata", myColumnDefs, myDataSource, myConfigs);
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
Set uomReleased = RefBO.getDistinctRedemptionUOMByAgency(agency,Common.REFERRAL_REDEMPTION_RELEASED);
Set uomunReleased = RefBO.getDistinctRedemptionUOMByAgency(agency,Common.REFERRAL_REDEMPTION_NOT_RELEASED);
%>
<div class="button1"><a href="#" rel="toggle[relasedstate]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>  <%=Constant.getResourceStringValue("Agency.Released",agency.getLocale())%> </div>
<div id="relasedstate" style="width  100%;">


<%
		 Map m = RefBO.createJSONGraphAgencyApplicantSummary(agency,agency);
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");


		 Map m2 = RefBO.createJSONGraphAgencyPerformance(agency,agency);
String details2 = (String)m2.get("detail");
String fields2 = (String)m2.get("fields");
String seriesdef2 = (String)m2.get("seriesdef");

		 Map m3 = RefBO.createJSONGraphAgencyPerformanceOverall(agency,agency);
String details3 = (String)m3.get("detail");
String fields3 = (String)m3.get("fields");
String seriesdef3 = (String)m3.get("seriesdef");




%>

<%if(uomReleased != null && uomReleased.size()>0){%>

<%}else{%>



<table>
<tr>
<td>
<span class="chart_title1"><%=Constant.getResourceStringValue("Agency.App_summ_year_wise",agency.getLocale())%></span>
<div id="chartapplicantsummary"></div>	
</td>
<td>

<span class="chart_title1"><%=Constant.getResourceStringValue("Agency.Performance_year_wise",agency.getLocale())%></span>
<div id="chartagencyperformance"></div>	

</td>
</tr>
<tr>
<td>
<span class="chart_title1"><%=Constant.getResourceStringValue("Agency.Overall_performance",agency.getLocale())%></span>
<div id="chartagencyoverallperformance"></div>	
</td>
<td>
</td>
</tr>
</table>


<%}%>



<%          
            Iterator<String> itrreleased = uomReleased.iterator();
			int noofuom =0;
			int index =1;
            while(itrreleased.hasNext()){
         String uomr =	itrreleased.next();  
		 long totalamt = RefBO.getTotalRedemptionsByUOMBandStateForAgency(agency,uomr,Common.REFERRAL_REDEMPTION_RELEASED);
		 long totalamtyettobepaid = RefBO.getTotalRedemptionsNotPiadByUOMBandStateForAgency(agency,uomr,Common.REFERRAL_REDEMPTION_RELEASED);

		 		 Map m1 = RefBO.createJSONGraphForAgencySummary(agency,uomr,agency);
String details1 = (String)m1.get("detail");
String fields1 = (String)m1.get("fields");
String seriesdef1 = (String)m1.get("seriesdef");

String chartdiv ="chart"+index;
index++;
noofuom++;
%>	

<table width="100%" bgcolor="#F0FFF0">
<tr>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.Total_redemption",agency.getLocale())%>: <%=totalamt%> <%=uomr%> </b>
</td>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.Total_redemption_paid",agency.getLocale())%>: <%=totalamtyettobepaid%> <%=uomr%> </b>
</td>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.Total_paid",agency.getLocale())%>: <%=totalamt-totalamtyettobepaid%> <%=uomr%> </b>
</td>
</tr>
</table>

	<body class="yui-skin-sam">	
<div id="<%=uomr+"dynamicdata1"%>"></div>

<table>
<tr>
<td>
<span class="chart_title1"><%=Constant.getResourceStringValue("Agency.Redemption_summary_year_wise_UOM",agency.getLocale())%> : <%=uomr%></span>
<div id='<%=chartdiv%>'></div>	
</td>
<td>
<% if(noofuom == 1){%>
<span class="chart_title1"><%=Constant.getResourceStringValue("Agency.App_summ_year_wise",agency.getLocale())%></span>
<div id="chartapplicantsummary"></div>	
<%}%>
</td>
</tr>
<tr>
<td>
<% if(noofuom == 1){%>
<span class="chart_title1"><%=Constant.getResourceStringValue("Agency.Performance_year_wise",agency.getLocale())%></span>
<div id="chartagencyperformance"></div>	
<%}%>
</td>
<td>
<% if(noofuom == 1){%>
<span class="chart_title1"><%=Constant.getResourceStringValue("Agency.Overall_performance",agency.getLocale())%></span>
<div id="chartagencyoverallperformance"></div>	
<%}%>
</td>
</tr>
</table>

<script type="text/javascript">

	YAHOO.widget.Chart.SWFURL = "<%=url%>";

//--- data

	YAHOO.example.monthlyExpenses1 =
	[
<%=details1%>
		
	];

	var myDataSource1 = new YAHOO.util.DataSource( YAHOO.example.monthlyExpenses1 );
	myDataSource1.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource1.responseSchema =
	{
		fields: <%=fields1%>
	};

//--- chart

	var seriesDef1 =
	[
		<%=seriesdef1%>

	];

	YAHOO.example.formatCurrencyAxisLabel1 = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "<%=uomr%> ",
			thousandsSeparator: ",",
			decimalPlaces: 0
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.agencysummary;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 1;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Amount";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( '<%=chartdiv%>', myDataSource1,
	{
		series: seriesDef1,
		xField: "agencysummary",
		yAxis: currencyAxis1,
		xAxis: categoryAxis,
		style:
		{
			padding: 20,
			legend:
			{
				display: "right",
				padding: 10,
				spacing: 5,
				font:
				{
					family: "Arial",
					size: 13
				}
			}
		},
		dataTipFunction: YAHOO.example.getDataTipText1,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

</script>


		



<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='agencyredemptiondetails.do?method=summarydetails&agredid=" + oRecord.getData("agredid") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };


    // Column definitions
    var myColumnDefs = [
			{key:"agredid", hidden:true},
		{key:"uuid", hidden:true},
		     {key:"applicantName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",agency.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"JobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.name",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeTypeName", label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.rst",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"ruleName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.rule",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"state", label:"<%=Constant.getResourceStringValue("hr.Redemption.Bonus_state",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"applicantstate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Applicant_state",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"eventdate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Event_Date",agency.getLocale())%>", resizeable:true},
		{key:"releaseddate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Released_Date",agency.getLocale())%>", resizeable:true},
		{key:"refferalSchemeAmount", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",agency.getLocale())%>", sortable:true, resizeable:true},
		{key:"isPaid", label:"<%=Constant.getResourceStringValue("hr.Redemption.isPaid",agency.getLocale())%>", sortable:true, resizeable:true}
		
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/homebodyAgencylistpage.jsp?uomr=<%=uomr%>&state=<%=Common.REFERRAL_REDEMPTION_RELEASED%>&ddd="+(new Date).getTime()+"&");
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
        initialRequest: "sort=agredid&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"agredid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

<script type="text/javascript">

	YAHOO.widget.Chart.SWFURL = "<%=url%>";

//--- data

	YAHOO.example.monthlyExpenses1 =
	[
<%=details%>
		
	];

	var myDataSource1 = new YAHOO.util.DataSource( YAHOO.example.monthlyExpenses1 );
	myDataSource1.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource1.responseSchema =
	{
		fields: <%=fields%>
	};

//--- chart

	var seriesDef1 =
	[
		<%=seriesdef%>

	];

	YAHOO.example.formatCurrencyAxisLabel1 = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 0
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.agencyapplicantsummary;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Applicants count";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartapplicantsummary", myDataSource1,
	{
		series: seriesDef1,
		xField: "agencyapplicantsummary",
		yAxis: currencyAxis1,
		xAxis: categoryAxis,
		style:
		{
			padding: 20,
			legend:
			{
				display: "right",
				padding: 10,
				spacing: 5,
				font:
				{
					family: "Arial",
					size: 13
				}
			}
		},
		dataTipFunction: YAHOO.example.getDataTipText1,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

</script>

<script type="text/javascript">

	YAHOO.widget.Chart.SWFURL = "<%=url%>";

//--- data

	YAHOO.example.monthlyExpenses1 =
	[
<%=details2%>
		
	];

	var myDataSource1 = new YAHOO.util.DataSource( YAHOO.example.monthlyExpenses1 );
	myDataSource1.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource1.responseSchema =
	{
		fields: <%=fields2%>
	};

//--- chart

	var seriesDef1 =
	[
		<%=seriesdef2%>

	];

	YAHOO.example.formatCurrencyAxisLabel1 = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 2
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.agencyperformance;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText +"%";
	}

	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Percentage Ratio";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartagencyperformance", myDataSource1,
	{
		series: seriesDef1,
		xField: "agencyperformance",
		yAxis: currencyAxis1,
		xAxis: categoryAxis,
		style:
		{
			padding: 20,
			legend:
			{
				display: "right",
				padding: 10,
				spacing: 5,
				font:
				{
					family: "Arial",
					size: 13
				}
			}
		},
		dataTipFunction: YAHOO.example.getDataTipText1,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

</script>

<script type="text/javascript">

	YAHOO.widget.Chart.SWFURL = "<%=url%>";

//--- data

	YAHOO.example.monthlyExpenses1 =
	[
<%=details3%>
		
	];

	var myDataSource1 = new YAHOO.util.DataSource( YAHOO.example.monthlyExpenses1 );
	myDataSource1.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource1.responseSchema =
	{
		fields: <%=fields3%>
	};

//--- chart

	var seriesDef1 =
	[
		<%=seriesdef3%>

	];

	YAHOO.example.formatCurrencyAxisLabel1 = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 2
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.agencyperformanceoverall;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText +"%";
	}

	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Percentage Ratio";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartagencyoverallperformance", myDataSource1,
	{
		series: seriesDef1,
		xField: "agencyperformanceoverall",
		yAxis: currencyAxis1,
		xAxis: categoryAxis,
		style:
		{
			padding: 20,
			legend:
			{
				display: "right",
				padding: 10,
				spacing: 5,
				font:
				{
					family: "Arial",
					size: 13
				}
			}
		},
		dataTipFunction: YAHOO.example.getDataTipText1,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

</script>
</div>

<br>
<div class="button1"><a href="#" rel="toggle[unrelasedstate]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("hr.Redemption.Un_Released",agency.getLocale())%> </div>
<div id="unrelasedstate" style="width  100%;">
<table>

<tr>
<td>



<%          
            Iterator<String> itrunreleased = uomunReleased.iterator();
           while(itrunreleased.hasNext()){
         String uomr =	itrunreleased.next(); 
		 long totalamt = RefBO.getTotalRedemptionsByUOMBandStateForAgency(agency,uomr,Common.REFERRAL_REDEMPTION_NOT_RELEASED);
%>	
<table width="100%" bgcolor="#F0FFF0">
<tr>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.Total_redemption",agency.getLocale())%>: <%=totalamt%> <%=uomr%> </b>
</td>
</tr>
</table>
	<body class="yui-skin-sam">	
<div id="<%=uomr+"dynamicdata"%>"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='agencyredemptiondetails.do?method=summarydetails&agredid=" + oRecord.getData("agredid") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };


    // Column definitions
    var myColumnDefs = [
			{key:"agredid", hidden:true},
		{key:"uuid", hidden:true},
		     {key:"applicantName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",agency.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"JobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.name",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeTypeName", label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.rst",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"ruleName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.rule",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"state", label:"<%=Constant.getResourceStringValue("hr.Redemption.Bonus_state",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"applicantstate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Applicant_state",agency.getLocale())%>",sortable:true, resizeable:true},
		{key:"eventdate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Event_Date",agency.getLocale())%>", resizeable:true},
		{key:"releaseddate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Released_Date",agency.getLocale())%>", resizeable:true},
		{key:"refferalSchemeAmount", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",agency.getLocale())%>", sortable:true, resizeable:true}
		
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/homebodyAgencylistpage.jsp?uomr=<%=uomr%>&state=<%=Common.REFERRAL_REDEMPTION_NOT_RELEASED%>&ddd="+(new Date).getTime()+"&");
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
</div>
