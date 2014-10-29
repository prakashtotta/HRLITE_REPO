<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<title><%=Constant.getResourceStringValue("aquisition.applicant.compare",user1.getLocale())%></title>
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
		height: 250px;
	}
	 #chart2
	{
		width: 500px;
		height: 250px;
	}
	 #chart3
	{
		width: 500px;
		height: 250px;
	}

</style>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>
<%@ include file="../common/resize.jsp" %>

<style>
    #resize_0 {
        border: 1px solid black;
        height: 800px;
        width: 600px;
        background-color: #fff;
    }
    #resize_0 div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }
#resize_1 {
        border: 1px solid black;
        height: 800px;
        width: 600px;
        background-color: #fff;
    }
    #resize_1 div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }
#resize_2 {
        border: 1px solid black;
       height: 800px;
        width: 600px;
        background-color: #fff;
    }
    #resize_2 div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }
#resize_3 {
        border: 1px solid black;
        height: 800px;
        width: 600px;
        background-color: #fff;
    }
    #resize_3 div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }
#resize_4 {
        border: 1px solid black;
      height: 800px;
        width: 600px;
        background-color: #fff;
    }
    #resize_4 div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }
#resize_5 {
        border: 1px solid black;
       height: 800px;
        width: 600px;
        background-color: #fff;
    }
    #resize_5 div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }

</style>
<body class="yui-skin-sam">
<table>
<tr>
<td valign="top">
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_ratings_by_applicant",user1.getLocale())%></span>
<div id="chart"></div>
</td>
<td valign="top">
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_ratings_column_wise",user1.getLocale())%></span>
<div id="chart3"></div>
</td>
<td valign="top">
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competenciesratings",user1.getLocale())%> </span>
<div id="chart1"></div>
</td>
<td valign="top">
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishmentsratings",user1.getLocale())%> </span>
<div id="chart2"></div>
</td>

</tr>
</table>

<table>
<tr>
<tr>
<%
List appList = form.getApplicantList();
for(int i=0;i<appList.size();i++){
	JobApplicant applicantd = (JobApplicant)appList.get(i);
	String urltemp = "applicant.do?method=applicantDetailsForCompare&applicantId="+applicantd.getApplicantId()+"&secureid="+applicantd.getUuid();
%>
<td valign="top">
<div id="resize_<%=i%>">
<div class="data">

<iframe id="evalgraph" frameborder="0" height="100%" width="100%" src="<%=urltemp%>"></iframe>

</div>
</div>
</td>

<%}%>
</tr>
</table>


<%


Map m = BOFactory.getApplicantBO().createJSONGraphForApplicantsList(form.getApplicantList());
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");

System.out.println(details);
System.out.println(fields);
System.out.println(seriesdef);

Map m1 = BOFactory.getApplicantBO().createLineChartJSONGraphForApplicantsList(form.getApplicantList(),Common.COMPETENCY_TYPE);
String details1 = (String)m1.get("detail");
String fields1 = (String)m1.get("fields");
String seriesdef1 = (String)m1.get("seriesdef");

Map m2 = BOFactory.getApplicantBO().createLineChartJSONGraphForApplicantsList(form.getApplicantList(),Common.ACCOMPLISHMENT_TYPE);
String details2 = (String)m2.get("detail");
String fields2 = (String)m2.get("fields");
String seriesdef2 = (String)m2.get("seriesdef");

Map m3 = BOFactory.getApplicantBO().createColumnChartJSONGraphForApplicantsList(form.getApplicantList(),Common.COMPETENCY_TYPE);
String details3 = (String)m3.get("detail");
String fields3 = (String)m3.get("fields");
String seriesdef3 = (String)m3.get("seriesdef");

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
var toolTipText = "<%=Constant.getResourceStringValue("aquisition.applicant.Average_score_for",user1.getLocale())%>"+series.displayName;
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
		dataTipFunction: YAHOO.example.getDataTipText,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

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
			decimalPlaces: 2
		});
	}

	YAHOO.example.getDataTipText1 = function( item, index, series )
	{
		var toolTipText = series.displayName + " : <%=Constant.getResourceStringValue("aquisition.applicant.Average",user1.getLocale())%> " + item.competency +" <%=Constant.getResourceStringValue("aquisition.applicant.score",user1.getLocale())%> ";
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 1;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;

	var mychart = new YAHOO.widget.LineChart( "chart1", myDataSource1,
	{
		series: seriesDef1,
		xField: "competency",
		yAxis: currencyAxis1,
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

	YAHOO.example.monthlyExpenses2 =
	[
<%=details2%>
		
	];

	var myDataSource2 = new YAHOO.util.DataSource( YAHOO.example.monthlyExpenses2 );
	myDataSource2.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource2.responseSchema =
	{
		fields: <%=fields2%>
	};

//--- chart

	var seriesDef2 =
	[
		<%=seriesdef2%>

	];

	YAHOO.example.formatCurrencyAxisLabel2 = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 2
		});
	}

	YAHOO.example.getDataTipText2 = function( item, index, series )
	{
		var toolTipText = series.displayName + " : <%=Constant.getResourceStringValue("aquisition.applicant.Average",user1.getLocale())%> " + item.competency +" <%=Constant.getResourceStringValue("aquisition.applicant.score",user1.getLocale())%> ";
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel2( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis2 = new YAHOO.widget.NumericAxis();
	currencyAxis2.minimum = 1;
	currencyAxis2.labelFunction = YAHOO.example.formatCurrencyAxisLabel2;

	var mychart = new YAHOO.widget.LineChart( "chart2", myDataSource2,
	{
		series: seriesDef2,
		xField: "competency",
		yAxis: currencyAxis2,
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
		dataTipFunction: YAHOO.example.getDataTipText2,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

</script>



<script type="text/javascript">

	YAHOO.widget.Chart.SWFURL = "<%=url%>";

//--- data

	YAHOO.example.monthlyExpenses3 =
	[
<%=details3%>
		
	];

	var myDataSource3 = new YAHOO.util.DataSource( YAHOO.example.monthlyExpenses3 );
	myDataSource3.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource3.responseSchema =
	{
		fields: <%=fields3%>
	};

//--- chart

	var seriesDef3 =
	[
		<%=seriesdef3%>

	];

	YAHOO.example.formatCurrencyAxisLabel3 = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 2
		});
	}

	YAHOO.example.getDataTipText3 = function( item, index, series )
	{
		var toolTipText = series.displayName + " : <%=Constant.getResourceStringValue("aquisition.applicant.Average",user1.getLocale())%> " + item.competency1 +" <%=Constant.getResourceStringValue("aquisition.applicant.score",user1.getLocale())%> ";
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel3( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis3 = new YAHOO.widget.NumericAxis();
	currencyAxis3.minimum = 1;
	currencyAxis3.labelFunction = YAHOO.example.formatCurrencyAxisLabel3;

	var mychart = new YAHOO.widget.ColumnChart( "chart3", myDataSource3,
	{
		series: seriesDef3,
		xField: "competency1",
		yAxis: currencyAxis3,
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
		dataTipFunction: YAHOO.example.getDataTipText3,
		//only needed for flash player express install
		expressInstall: "assets/expressinstall.swf"
	});

</script>




<script>

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var resize_0 = new YAHOO.util.Resize('resize_0');
})();
</script>
<script>

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var resize_1 = new YAHOO.util.Resize('resize_1');
})();
</script>
<script>

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var resize_2 = new YAHOO.util.Resize('resize_2');
})();
</script>

<script>

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var resize_2 = new YAHOO.util.Resize('resize_3');
})();
</script>

</body>