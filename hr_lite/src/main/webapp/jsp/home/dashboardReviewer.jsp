<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%
User user = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<style type="text/css">
	#chart
	{
       position:relative;
	   display:block; 
		width: 600px;
		height: 250px;
	

	}


   #chartapplicantsummary
	{
		width: 600px;
		height: 150px;
	}

	#chartapplicantscreening
	{
		width: 600px;
		height: 150px;
	}

		#chartreviewerperformance
	{
		width: 600px;
		height: 300px;
	}

	#chartscreenerperformance
	{
		width: 600px;
		height: 300px;
	}

	
</style>

<%
String url = Constant.getValue("internal.url") + "jsp/yahoo/build/charts/assets/charts.swf";
		 Map m = BOFactory.getDashBoardBO().createJSONGraphReviewerApplicantSummary(user.getUserId(),user);
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");

		 Map m1 = BOFactory.getDashBoardBO().createJSONGraphReviewerApplicantScreeningSummary(user.getUserId(),user);
String details1 = (String)m1.get("detail");
String fields1 = (String)m1.get("fields");
String seriesdef1 = (String)m1.get("seriesdef");


		 Map m2 = BOFactory.getDashBoardBO().createJSONGraphReviewerPerformanceSummary(user.getUserId(),user);
String details2 = (String)m2.get("detail");
String fields2 = (String)m2.get("fields");
String seriesdef2 = (String)m2.get("seriesdef");


		 Map m3 = BOFactory.getDashBoardBO().createJSONGraphScreenerPerformanceSummary(user.getUserId(),user);
String details3 = (String)m3.get("detail");
String fields3 = (String)m3.get("fields");
String seriesdef3 = (String)m3.get("seriesdef");

%>

<table>
<tr>
<td>
<span class="chart_title1">Applicant interview year wise</span>
<div id="chartapplicantsummary"></div>	
</td>
<td>
<span class="chart_title2">Applicant screening year wise</span>
<div id="chartapplicantscreening"></div>	
</td>
</tr>
<tr>
<td>
<span class="chart_title1">Reviewer performance year wise</span>
<div id="chartreviewerperformance"></div>	
</td>
<td>
<span class="chart_title1">Screening performance year wise</span>
<div id="chartscreenerperformance"></div>	
</td>
</tr>
</table>



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
		var toolTipText = series.displayName + " " + item.reviewerapplicantsummary;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}

	var clickPieChart = function (ev) {
  
	 alert("Have event object " + ev.item.reviewerapplicantsummary  + ev.seriesIndex ); 
	 alert("Have event object " ); 
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
		xField: "reviewerapplicantsummary",
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

	 mychart.subscribe("itemClickEvent", clickPieChart);

</script>


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
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 0
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.screenerapplicantsummary;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}


	var clickPieChart = function (ev) {
  
	 alert("Have event object " + ev.item.screenerapplicantsummary  + ev.seriesIndex ); 
	 alert("Have event object " ); 
	}
	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Applicants count";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartapplicantscreening", myDataSource1,
	{
		series: seriesDef1,
		xField: "screenerapplicantsummary",
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
	mychart.subscribe("itemClickEvent", clickPieChart);

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
			decimalPlaces: 0
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.reviewerperformancesummary;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}


	var clickPieChart = function (ev) {
  
	 alert("Have event object " + ev.item.reviewerperformancesummary  + ev.seriesIndex ); 
	 alert("Have event object " ); 
	}
	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Applicants count";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartreviewerperformance", myDataSource1,
	{
		series: seriesDef1,
		xField: "reviewerperformancesummary",
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
	mychart.subscribe("itemClickEvent", clickPieChart);

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
			decimalPlaces: 0
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.screenerperformancesummary;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}


	var clickPieChart = function (ev) {
  
	 alert("Have event object " + ev.item.screenerperformancesummary  + ev.seriesIndex ); 
	 alert("Have event object " ); 
	}
	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Applicants count";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartscreenerperformance", myDataSource1,
	{
		series: seriesDef1,
		xField: "screenerperformancesummary",
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
	mychart.subscribe("itemClickEvent", clickPieChart);

</script>


































<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'none';   

</script>




