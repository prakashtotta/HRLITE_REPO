<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.dao.*"%>
<%@ include file="../talent/applicantScreenCollapsableDivs.jsp" %>

<%
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.APPLICANT_SCREEN_AGENCY,user1.getSuper_user_key());
List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);


String datepattern = Constant.getValue("defaultdateformat");

String path = (String)request.getAttribute("filePath");

%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<%

ResumeSources rc= null;
User usr=null;
if(form.getSourceId() != 0){
rc = BOFactory.getApplicantBO().getResumeSourcesById(String.valueOf(form.getSourceId()));
;
}
if(form.getVendorId() != 0){
	usr=UserDAO.getUserByVendorIdandTypeVendor(form.getVendorId());
}

%>
<head>
<link rel="stylesheet" type="text/css" href="jsp/ajaxtabs/ajaxtabs.css" />

<script type="text/javascript" src="jsp/ajaxtabs/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

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
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}

</style>

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

	}
</style>


</head>
<script language="javascript">
this.reload_on_close=false;

</script>  

<script type="text/javascript">
function openURLdelay(urlf){
      var encodejurl = encodeURIComponent(urlf);
	  location.href = "preload1.jsp?reurl="+encodejurl;
	 

		}
function openResume(){
	var tu = "agencyops.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
        var url = "jsp/common/preload1.jsp?reurl="+encodeURIComponent(tu);
  		window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");

  
}
function downloadapplicantdetails(){
	
	var url = "<%=request.getContextPath()%>/agencyops.do?method=downloadApplicantDetailPdf&applicantid=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
	location.href =url;

}
</script>


<body>
<%@include file="/jsp/common/tooltip.jsp" %>

<table class="div">
<tr>
<%if(form.getBackUrl()!=null){%>
<td width="150"><a class="button" href='<%=form.getBackUrl()%>' ><span>back</span></a><br></td>
<%}%>
</tr>
 <tr>
			
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
			<td class="bodytext"><bean:write name="applicantForm" property="fullName"/></td>
			
			 
		</tr>
<tr>
			<td width="150" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="applicantId"/></td>
		</tr>
		<%
       String tempurl1 = "";
       if(form.getJobTitle() != null){
   tempurl1 = "<a href='#' onClick=window.open("+"'"+"agjob.do?method=jobdetailsforAgency&reqid="+form.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+form.getJobTitle()+"</a>";
	   }
		%>
        <tr>
			<td width="150" class="bodytext"> <%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%> </td>
			<!-- <td class="bodytext"><a href="agjob.do?method=jobdetails&reqid=<%=form.getRequitionId()%>"><%=form.getJobTitle()%></a></td> -->
			<td class="bodytext"><%=tempurl1%></td>
		</tr>
		
		<tr>
		<td><br></td>
		<td><br></td>
        
		</tr>
		<tr></tr>

</table>


<table>
<tr>
<td valign="top" width="60%">

<ul id="countrytabs" class="shadetabs">
<li><a href="#"  rel="#default" class="selected"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<!--  <li><a href="agencyops.do?method=additionalDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Additionaldetails",user1.getLocale())%></a></li>-->
<li><a href="agencyops.do?method=resumeDetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%></a></li>
<!--<li><a href="agencyops.do?method=applicantlogdetailsajax&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer">interview log</a></li>
-->
</ul>



<div id="countrydivcontainer" style="border:1px solid gray; width:850px; margin-bottom: 1em; padding: 10px" class="div">
<%-- <%@ include file="applicantdetailscommon.jsp" %> --%>

<%@ include file="../talent/applicantdetailscommon.jsp" %>

</div>
</td>
<td valign="top">
<span class="chart_title"><%=Constant.getResourceStringValue("aquisition.applicant.Competenciesratings",user1.getLocale())%></span>
<div id="chart"></div>
<span class="chart_title1"><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishmentsratings",user1.getLocale())%></span>
<div id="chart1"></div>
</td>
</tr>

</table>


<script type="text/javascript">

var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
countries.setpersist(true)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()

</script>

<%


Map m = BOFactory.getApplicantBO().createJSONGraphForApplicant(form.getApplicantId());
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");



Map m1 = BOFactory.getApplicantBO().createJSONGraphForApplicantAccomplishments(form.getApplicantId());
String details1 = (String)m1.get("detail");
String fields1 = (String)m1.get("fields");
String seriesdef1 = (String)m1.get("seriesdef");



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
	
</body>
</html>

