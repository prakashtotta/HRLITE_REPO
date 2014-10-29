<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
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
		height: 150px;
	}
	 #chart2
	{
		width: 500px;
		height: 150px;
	}
	 #chart3
	{
		width: 500px;
		height: 150px;
	}

</style>
<body>
<table>
<tr>
<td valign="top" width="500">
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_ratings_by_applicant",user1.getLocale())%></span>
<div id="chart"></div>
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_ratings_column_wise",user1.getLocale())%></span>
<div id="chart3"></div>
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competenciesratings",user1.getLocale())%> </span>
<div id="chart1"></div>
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishmentsratings",user1.getLocale())%> </span>
<div id="chart2"></div>

</td>
<td width="1000" valign="top">
<table>


<% if(form.getApplicantList() != null && form.getApplicantList().size() == 2){%>
<tr>
<%
JobApplicant applicant1 = (JobApplicant)form.getApplicantList().get(0);
JobApplicant applicant2 = (JobApplicant)form.getApplicantList().get(1);
String url1 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant1.getApplicantId()+"&secureid="+applicant1.getUuid();
String url2 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant2.getApplicantId()+"&secureid="+applicant2.getUuid();
%>
<td valign="top" width="600">
<iframe src ="<%=url1%>" width="100%" height="500">


</iframe> 
</td>
<td valign="top" width="600">
<iframe src ="<%=url2%>" width="100%" height="500">


</iframe> 
</td>
</tr>
<%}%>
<% if(form.getApplicantList() != null && (form.getApplicantList().size() > 2 && form.getApplicantList().size() <= 4 )){%>
<tr>
<%
JobApplicant applicant1 = (JobApplicant)form.getApplicantList().get(0);
JobApplicant applicant2 = (JobApplicant)form.getApplicantList().get(1);
JobApplicant applicant3 = (JobApplicant)form.getApplicantList().get(2);
String url4 = null;
JobApplicant applicant4 = null;
if(form.getApplicantList().size() == 4){
           applicant4 = (JobApplicant)form.getApplicantList().get(3);
		    url4 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant4.getApplicantId()+"&secureid="+applicant4.getUuid();
}

String url1 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant1.getApplicantId()+"&secureid="+applicant1.getUuid();
String url2 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant2.getApplicantId()+"&secureid="+applicant2.getUuid();
String url3 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant3.getApplicantId()+"&secureid="+applicant3.getUuid();

%>
<td width="600" valign="top">

<iframe src ="<%=url1%>" width="100%" height="500">


</iframe> 


</td>
<td width="600" valign="top">
<iframe src ="<%=url2%>" width="100%" height="500">


</iframe> 
</td>
</tr>
<tr>
<td width="600" valign="top">
<iframe src ="<%=url3%>" width="100%" height="500">


</iframe> 
</td>
<td width="600" valign="top">
<% if(url4 != null){%>
<iframe src ="<%=url4%>" width="100%" height="500">


</iframe> 
<%}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Data_not_available",user1.getLocale())%>
<%}%>

</td>
</tr>
<%}%>
<% if(form.getApplicantList() != null && (form.getApplicantList().size() > 4 && form.getApplicantList().size() <= 6 )){%>
<tr>
<%
JobApplicant applicant1 = (JobApplicant)form.getApplicantList().get(0);
JobApplicant applicant2 = (JobApplicant)form.getApplicantList().get(1);
JobApplicant applicant3 = (JobApplicant)form.getApplicantList().get(2);
JobApplicant applicant4 = (JobApplicant)form.getApplicantList().get(3);
JobApplicant applicant5 = (JobApplicant)form.getApplicantList().get(4);

String url6 = null;
JobApplicant applicant6 = null;
if(form.getApplicantList().size() == 6){
           applicant6 = (JobApplicant)form.getApplicantList().get(5);
		    url6 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant6.getApplicantId()+"&secureid="+applicant6.getUuid();
}

String url1 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant1.getApplicantId()+"&secureid="+applicant1.getUuid();
String url2 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant2.getApplicantId()+"&secureid="+applicant2.getUuid();
String url3 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant3.getApplicantId()+"&secureid="+applicant3.getUuid();
String url4 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant4.getApplicantId()+"&secureid="+applicant4.getUuid();
String url5 = "applicantoffer.do?method=applicantDetailsForCompare&applicantId="+applicant5.getApplicantId()+"&secureid="+applicant5.getUuid();

%>
<td width="600" valign="top">

<iframe src ="<%=url1%>" width="100%" height="500">


</iframe> 


</td>
<td width="600" valign="top">
<iframe src ="<%=url2%>" width="100%" height="500">


</iframe> 
</td>
</tr>
<tr>
<td width="600" valign="top">
<iframe src ="<%=url3%>" width="100%" height="500">


</iframe> 
</td>
<td width="600" valign="top">
<iframe src ="<%=url4%>" width="100%" height="500">


</iframe> 

</td>
</tr>
<tr>
<td width="600" valign="top">
<iframe src ="<%=url5%>" width="100%" height="500">


</iframe> 
</td>
<td width="600" valign="top">
<% if(url6 != null){%>
<iframe src ="<%=url6%>" width="100%" height="500">


</iframe> 
<%}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Data_not_available",user1.getLocale())%>
<%}%>

</td>
</tr>
<%}%>
</table>
</td>
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





</body>