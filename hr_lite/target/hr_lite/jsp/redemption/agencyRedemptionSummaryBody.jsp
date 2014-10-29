<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../common/yahooincludes.jsp" %>

<style type="text/css">
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


<bean:define id="aform" name="agencyRedemptionSummaryForm" type="com.form.AgencyRedemptionSummaryForm" />
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
User agency = aform.getAgency();
%>
<%
Set uomReleased = RefBO.getDistinctRedemptionUOMByAgency(agency,Common.REFERRAL_REDEMPTION_RELEASED);
Set uomunReleased = RefBO.getDistinctRedemptionUOMByAgency(agency,Common.REFERRAL_REDEMPTION_NOT_RELEASED);
%>


<% if(aform.getBackurltolist()!=null){%>


	<% if((aform.getBackurltolist()).equals("user.do?method=vendorlist")){%>


<br><a class="button" href="<%=aform.getBackurltolist()%>"><< <%=Constant.getResourceStringValue("hr.Redemption.back_to_vendor_list",user1.getLocale())%></a>
<%}else{%>
<a href="<%=aform.getBackurltolist()%>"> << <%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%></a>

<%}%>

	<%}%>
<br><br>
<table>
	<tr>
		<td>
		<b><%=Constant.getResourceStringValue("hr.Redemption.Released",user1.getLocale())%> </b>
		</td>
	</tr>
	<tr>
		<td>
<%
		 Map m = RefBO.createJSONGraphAgencyApplicantSummary(agency,user1);
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");


		 Map m2 = RefBO.createJSONGraphAgencyPerformance(agency,user1);
String details2 = (String)m2.get("detail");
String fields2 = (String)m2.get("fields");
String seriesdef2 = (String)m2.get("seriesdef");

		 Map m3 = RefBO.createJSONGraphAgencyPerformanceOverall(agency,user1);
String details3 = (String)m3.get("detail");
String fields3 = (String)m3.get("fields");
String seriesdef3 = (String)m3.get("seriesdef");




%>


<%          
            Iterator<String> itrreleased = uomReleased.iterator();
			int noofuom =0;
			int index =1;
            while(itrreleased.hasNext()){
         String uomr =	itrreleased.next();  
		 long totalamt = RefBO.getTotalRedemptionsByUOMBandStateForAgency(agency,uomr,Common.REFERRAL_REDEMPTION_RELEASED);
		 long totalamtyettobepaid = RefBO.getTotalRedemptionsNotPiadByUOMBandStateForAgency(agency,uomr,Common.REFERRAL_REDEMPTION_RELEASED);

		 Map m1 = RefBO.createJSONGraphForAgencySummary(agency,uomr,user1);
String details1 = (String)m1.get("detail");
String fields1 = (String)m1.get("fields");
String seriesdef1 = (String)m1.get("seriesdef");
String url = Constant.getValue("internal.url") + "jsp/yahoo/build/charts/assets/charts.swf";
String chartdiv ="chart"+index;
index++;
noofuom++;
%>	

<table class="div">
<tr>
<td>
<span class="chart_title1"><%=Constant.getResourceStringValue("hr.Redemption.sumary_year_wise_for_UOM",user1.getLocale())%> : <%=uomr%></span>
<div id='<%=chartdiv%>'></div>	
</td>
<td>
<% if(noofuom == 1){%>
<span class="chart_title1"><%=Constant.getResourceStringValue("hr.Redemption.applicant_summary_yearwise",user1.getLocale())%></span>
<div id="chartapplicantsummary"></div>	
<%}%>
</td>
</tr>
<tr>
<td>
<% if(noofuom == 1){%>
<span class="chart_title1"><%=Constant.getResourceStringValue("hr.Redemption.agency_performance_yearwise",user1.getLocale())%></span>
<div id="chartagencyperformance"></div>	
<%}%>
</td>
<td>
<% if(noofuom == 1){%>
<span class="chart_title1"><%=Constant.getResourceStringValue("hr.Redemption.overall_agency_performance",user1.getLocale())%></span>
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


<table width="100%" bgcolor="#F0FFF0" class="div">
<tr>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.Total_redemption",user1.getLocale())%>: <%=totalamt%> <%=uomr%> </b>
</td>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.Total_redemption_paid",user1.getLocale())%>: <%=totalamtyettobepaid%> <%=uomr%> </b>
</td>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.Total_paid",user1.getLocale())%>: <%=totalamt-totalamtyettobepaid%> <%=uomr%> </b>
</td>
</tr>
</table>
<br>
	<body class="yui-skin-sam">	
<div id="<%=uomr+"dynamicdata1"%>" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='agencyredemptionsummary.do?method=summarydetails&agredid=" + oRecord.getData("agredid") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };


    // Column definitions
    var myColumnDefs = [
			{key:"agredid", hidden:true},
			{key:"uuid", hidden:true},
		     {key:"applicantName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"JobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeTypeName", label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.rst",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"ruleName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.rule",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"state", label:"<%=Constant.getResourceStringValue("hr.Redemption.Bonus_state",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"applicantstate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Applicant_state",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"eventdate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Event_Date",user1.getLocale())%>", resizeable:true},
		{key:"releaseddate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Released_Date",user1.getLocale())%>", resizeable:true},
		{key:"refferalSchemeAmount", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"isPaid", label:"<%=Constant.getResourceStringValue("hr.Redemption.isPaid",user1.getLocale())%>", sortable:true, resizeable:true}
		
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/redemption/agencyRedemptionSummarylistpage.jsp?agencyid=<%=aform.getAgencyId()%>&uomr=<%=uomr%>&state=<%=Common.REFERRAL_REDEMPTION_RELEASED%>&ddd="+(new Date).getTime()+"&");
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

</td>
</tr>
<tr>
<td>
<b> </b>
</td>
</tr>
<tr>
<td>
<b><%=Constant.getResourceStringValue("Agency.unReleased",user1.getLocale())%></b>
</td>
</tr>
<tr>
<td>



<%          
            Iterator<String> itrunreleased = uomunReleased.iterator();
           while(itrunreleased.hasNext()){
         String uomr =	itrunreleased.next(); 
		 long totalamt = RefBO.getTotalRedemptionsByUOMBandStateForAgency(agency,uomr,Common.REFERRAL_REDEMPTION_NOT_RELEASED);
%>	
<table width="100%" bgcolor="#F0FFF0" class="div">
<tr>
<td>
<br><%=Constant.getResourceStringValue("hr.Redemption.total_redemption",user1.getLocale())%>: <%=totalamt%> <%=uomr%> </b>
</td>
</tr>
</table>
<br>
	<body class="yui-skin-sam">	
<div id="<%=uomr+"dynamicdata"%>" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='agencyredemptionsummary.do?method=summarydetails&agredid=" + oRecord.getData("agredid")  +"&secureid=" + oRecord.getData("uuid")+ "'"+ " >" + sData + "</a>";
        };


    // Column definitions
    var myColumnDefs = [
			{key:"agredid", hidden:true},
		{key:"uuid", hidden:true},
		     {key:"applicantName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"JobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"refferalSchemeTypeName", label:"<%=Constant.getResourceStringValue("admin.RefferalSchemeType.rst",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"ruleName", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.rule",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"state", label:"<%=Constant.getResourceStringValue("hr.Redemption.Bonus_state",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"applicantstate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Applicant_state",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"eventdate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Event_Date",user1.getLocale())%>", resizeable:true},
		{key:"releaseddate", label:"<%=Constant.getResourceStringValue("hr.Redemption.Released_Date",user1.getLocale())%>", resizeable:true},
		{key:"refferalSchemeAmount", label:"<%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",user1.getLocale())%>", sortable:true, resizeable:true},
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/redemption/agencyRedemptionSummarylistpage.jsp?agencyid=<%=aform.getAgencyId()%>&uomr=<%=uomr%>&state=<%=Common.REFERRAL_REDEMPTION_NOT_RELEASED%>&ddd="+(new Date).getTime()+"&");
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
