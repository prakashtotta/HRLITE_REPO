<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>




<script type="text/javascript" src="jsp/yahoo/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/json/json-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/element/element-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/swf/swf-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/charts/charts-min.js"></script>

<%
 
 
 



  %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<style type="text/css">
	#chart
	{
       position:relative;
	   display:block; 
		width: 500px;
		height: 250px;
	

	}

   #chart1
	{
		width: 500px;
		height: 150px;
	}
#chart_scoring
	{
       position:relative;
	   display:block; 
		width: 500px;
		height: 250px;
	

	}

	#chartaveragescore
	{
		width: 500px;
		height: 100px;
	}
	
</style>



<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<%

User user1 = (User)session.getAttribute(Common.USER_DATA);
Map m = BOFactory.getApplicantBO().createJSONGraphForApplicant(form.getApplicantId());
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");



Map m1 = BOFactory.getApplicantBO().createJSONGraphForApplicantAccomplishments(form.getApplicantId());
String details1 = (String)m1.get("detail");
String fields1 = (String)m1.get("fields");
String seriesdef1 = (String)m1.get("seriesdef");

Map m2 = BOFactory.getApplicantBO().createJSONGraphForApplicantKeywordScoring(form.getApplicantId());
String details2 = (String)m2.get("detail");
String fields2 = (String)m2.get("fields");
String seriesdef2 = (String)m2.get("seriesdef");

String url = Constant.getValue("internal.url") + "jsp/yahoo/build/charts/assets/charts.swf";
%>

<body class="yui-skin-sam">
 
		<p class="heading"><%=Constant.getResourceStringValue("aquisition.applicant.keyword.score",user1.getLocale())%>  </p> 

<div id="chart_scoring"></div>
<div class="chart_title_scoring"><%=Constant.getResourceStringValue("aquisition.applicant.keyword.average.score",user1.getLocale())%></div>
<div id="chartaveragescore"></div>
</fieldset>



<br>
<p class="heading"><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_required",user1.getLocale())%>   </p> 

<br>
<%=Constant.getResourceStringValue("aquisition.applicant.grades",user1.getLocale())%>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_required",user1.getLocale())%></legend>
<div class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competenciesratings",user1.getLocale())%></div>
<div id="chart"></div>
<table>
<%
List comptetencyList = form.getComptetencyList();
if(comptetencyList != null && comptetencyList.size()>0){
%>
<tr>
<td width="33%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="43%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></b></td>
<td width="23%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
</tr>
<%


   for(int i=0;i<comptetencyList.size();i++){
	JobTemplateCompetency comp = (JobTemplateCompetency)comptetencyList.get(i);
%>

<tr>
<td><%=comp.getCharName()%></td><td><%=comp.getImportance()%></td><td><%=(comp.getMandatory() != null && comp.getMandatory().equals("on"))?"Yes":"No"%></td>
</tr>

<%
   }
}else{
%>
<tr>
<td>
<%=Constant.getResourceStringValue("aquisition.applicant.Competencies_are_not_defind",user1.getLocale())%>
</td>
</tr>

<%
}
%>
</table>
</fieldset>

<br>
<p class="heading"><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments_required",user1.getLocale())%>  </p> 


<div class="chart_title1"><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishmentsratings",user1.getLocale())%></div>
<div id="chart1"></div>
<table>
<%
List accList = form.getAccomplishmentList();
if(accList != null && accList.size()>0){
%>
<tr>
<td width="63%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="23%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
</tr>
<%
   for(int i=0;i<accList.size();i++){
	JobTemplateAccomplishment acc = (JobTemplateAccomplishment)accList.get(i);
%>

<tr>
<td><%=acc.getAccName()%></td><td><%=(acc.getMandatory() != null && acc.getMandatory().equals("on"))?"Yes":"No"%></td>
</tr>

<%
   }
}else{
%>
<tr>
<td>
<%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments_are_not_defind",user1.getLocale())%>
</td>
</tr>
<%
}
%>
</table>

</fieldset>
 


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
		var toolTipText = series.displayName + " " + item.competency;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis = new YAHOO.widget.NumericAxis();
	currencyAxis.minimum = 1;
	currencyAxis.labelFunction = YAHOO.example.formatCurrencyAxisLabel;

	var mychart = new YAHOO.widget.ColumnChart( "chart", myDataSource,
	{
		series: seriesDef,
		xField: "competency",
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
		var toolTipText = series.displayName + " " + item.accomplishment;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 1;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;

	var mychart = new YAHOO.widget.ColumnChart( "chart1", myDataSource1,
	{
		series: seriesDef1,
		xField: "accomplishment",
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
			decimalPlaces: 3
		});
	}

	YAHOO.example.getDataTipText2 = function( item, index, series )
	{
		var toolTipText = series.displayName + " " + item.keyword;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel2( item[series.yField] );
		return toolTipText;
	}

	var currencyAxis2 = new YAHOO.widget.NumericAxis();
	currencyAxis2.minimum = 0;
	currencyAxis2.labelFunction = YAHOO.example.formatCurrencyAxisLabel2;

	var mychart = new YAHOO.widget.ColumnChart( "chart_scoring", myDataSource2,
	{
		series: seriesDef2,
		xField: "keyword",
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

	YAHOO.example.annualSales =
	[
		{ averagescored: "<%=Constant.getResourceStringValue("aquisition.applicant.keyword.average.score.text",user1.getLocale())%>", averagescore: <%=form.getScoreAve()%> }
		
	];

	var salesData = new YAHOO.util.DataSource( YAHOO.example.annualSales );
	salesData.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	salesData.responseSchema = { fields: [ "averagescored", "averagescore" ] };

//--- chart

	var seriesDef =
	[
		{
			xField: "averagescore",
			displayName: "<%=Constant.getResourceStringValue("aquisition.applicant.keyword.average.score",user1.getLocale())%>"
		}
	];

	//used to format x axis
	YAHOO.example.numberToCurrency = function( value )
	{
		return YAHOO.util.Number.format(Number(value), {prefix: "", thousandsSeparator: ","});
	}

     
	 YAHOO.example.formatCurrencyAxisLabel3 = function( value )
	{
		return YAHOO.util.Number.format( value,
		{
			prefix: "",
			thousandsSeparator: ",",
			decimalPlaces: 3
		});
	}


	//Numeric Axis for our currency
	var currencyAxis = new YAHOO.widget.NumericAxis();
	currencyAxis.stackingEnabled = true;
	currencyAxis.labelFunction = YAHOO.example.formatCurrencyAxisLabel3;

	var mychart = new YAHOO.widget.StackedBarChart( "chartaveragescore", salesData,
	{
		series: seriesDef,
		yField: "averagescored",
		xAxis: currencyAxis
	});

</script>
</body>
</span>



