<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<style type="text/css">
	#chart
	{
		width: 500px;
		height: 250px;
	}

   #chart1
	{
		width: 500px;
		height: 150px;
	}

</style>
<body>
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competenciesratings",user1.getLocale())%></span>
<div id="chart"></div>
<%


Map m = BOFactory.getApplicantBO().createJSONGraphForApplicantsList(form.getApplicantList());
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");

System.out.println(details);
System.out.println(fields);
System.out.println(seriesdef);

String url = Constant.getValue("internal.url") + "jsp/yahoo/build/charts/assets/charts.swf";
%>


<script type="text/javascript">

	YAHOO.widget.Chart.SWFURL = "<%=url%>";

//--- data

	YAHOO.example.monthlyExpenses =
	[
<%=details%>
		
	];

	var myDataSource = new YAHOO.util.DataSource( YAHOO.example.monthlyExpenses );
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema =
	{
		fields: <%=fields%>
	};

//--- chart

	var seriesDef =
	[
		<%=seriesdef%>

	];

	YAHOO.example.formatCurrencyAxisLabel = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 2
		});
	}

	YAHOO.example.getDataTipText = function( item, index, series )
	{
		var toolTipText = "Average score for "+series.displayName;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis = new YAHOO.widget.NumericAxis();
	currencyAxis.minimum = 1;
	currencyAxis.labelFunction = YAHOO.example.formatCurrencyAxisLabel;

	var mychart = new YAHOO.widget.ColumnChart( "chart", myDataSource,
	{
		series: seriesDef,
		xField: "appname",
		yAxis: currencyAxis,
		dataTipFunction: YAHOO.example.getDataTipText,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

</script>
















</body>