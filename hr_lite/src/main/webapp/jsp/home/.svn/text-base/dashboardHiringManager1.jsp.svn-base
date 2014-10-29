<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../common/greybox.jsp" %>
<%
User user = (User)request.getSession().getAttribute(Common.USER_DATA);

%>
<script language="javascript">

function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>', url, messageret);
}

function showmessage(returnval) { 
	window.location.reload();
	}

function editJobReqTemplte(tmpl_id){
		var url = "<%=request.getContextPath()%>/jobtemplate.do?method=edittemplate&templateId="+tmpl_id;
	//parent.showPopWin(url, 800, 600, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.edittemp",user.getLocale())%>', url, messageret);
}

function addRequisition(){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=createjobreq";
	parent.setPopTitle1("<%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%>");
	parent.showPopWin(url, 600, 300, editreq);
}
   
function editreq(retval){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+retval;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%>', url, messageret);
}

function userDetails(id){
	var url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}


function messageret(){
	//window.location.reload();
	//history.go(0);

			}

</script>

<style type="text/css">
	#chart
	{
       position:relative;
	   display:block; 
		width: 600px;
		height: 250px;
	

	}


   #chartrequisitionssummary
	{
		width: 600px;
		height: 150px;
	}

	#chartrequisitionssummaryopen
	{
		width: 1200px;
		height: 300px;
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
		 Map m = BOFactory.getDashBoardBO().createJSONGraphForHiringManagerRequistionSummary(user.getUserId(),user);
String details = (String)m.get("detail");
String fields = (String)m.get("fields");
String seriesdef = (String)m.get("seriesdef");

		 Map m2 = BOFactory.getDashBoardBO().createJSONGraphForHiringManagerOpenRequistionSummary(user.getUserId(),user);
String details2 = (String)m2.get("detail");
String fields2 = (String)m2.get("fields");
String seriesdef2 = (String)m2.get("seriesdef");



%>


<table>
<tr>
<td valign="top">
<br>
 Offer accepted applicants (joining within 30 days) <br>

	<body class="yui-skin-sam">
	<div id="dynamicdata1"></div>
	</body>

</td>
<td>
<br>
 My Active Requistions  <br>

	<body class="yui-skin-sam">
	<div id="dynamicdata"></div>
	</body>
</td>
</tr>
</table>
<table>
<tr>
<td width="600px">
 Applicants in offer process  <br>

	<body class="yui-skin-sam">
	<div id="dynamicdata2"></div>
	</body>	
</td>
<td>
<span class="chart_title1">Requistions summary year wise</span>
<div id="chartrequisitionssummary"></div>	
</td>
</tr>
</table>

<table>
<tr>
<td>
<span class="chart_title1">Open Published Requistions summary </span>
<div id="chartrequisitionssummaryopen"></div>	
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
		var toolTipText = series.displayName ;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}

	var clickPieChart = function (ev) {
  
	 alert("Have event object " + ev.item.hiringmanagerrequistionummary  + ev.seriesIndex ); 
	 alert("Have event object " ); 
	}

  


	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Requistions count";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Year";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartrequisitionssummary", myDataSource1,
	{
		series: seriesDef1,
		xField: "hiringmanagerrequistionummary",
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
		var toolTipText = series.displayName;// + " " + item.hiringmanagerrequistionummaryopen;
		toolTipText += "\n" + YAHOO.example.formatCurrencyAxisLabel1( item[series.yField] );
		return toolTipText;
	}

	var clickPieChart = function (ev) {
  
	 alert("Have event object " + ev.item.hiringmanagerrequistionummaryopen  + ev.seriesIndex ); 
	 alert("Have event object " ); 
	}

  


	var currencyAxis1 = new YAHOO.widget.NumericAxis();
	currencyAxis1.minimum = 0;
	currencyAxis1.labelFunction = YAHOO.example.formatCurrencyAxisLabel1;
	currencyAxis1.title="Number";

    var categoryAxis = new YAHOO.widget.CategoryAxis();   
    categoryAxis.title = "Requistions";   
  


	var mychart = new YAHOO.widget.ColumnChart( "chartrequisitionssummaryopen", myDataSource1,
	{
		series: seriesDef1,
		xField: "hiringmanagerrequistionummaryopen",
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

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var ndata = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19" align="middle"/>';
		var saveasdata =  '<img src="jsp/images/saveas.gif" border="0" alt="save as job requistion" title="<%=Constant.getResourceStringValue("Requisition.saveasjobreq",user.getLocale())%>" height="20"  width="19" align="middle"/>';
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + sData + "</a>";
		var saveasqd = "<a href='#' onClick=saveasJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + saveasdata + "</a>";

		 elCell.innerHTML = editreqd;

        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReqTemplte('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

	var summaryUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='jobreq.do?method=requistionapplicantlist&state=0&requistionId=" + oRecord.getData("jobreqId") +"'"+ " >" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>" + "</a>";
        };




    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},		
            {key:"jobreqName", label:"<%=Constant.getResourceStringValue("Requisition.jobreqname",user.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"projectcodeValue", label:"<%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user.getLocale())%>", sortable:false, resizeable:true},
						{key:"status", label:"<%=Constant.getResourceStringValue("Requisition.status",user.getLocale())%>", sortable:true, resizeable:true},
		{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>", sortable:false, resizeable:true,formatter:summaryUrl}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/myjobrequisitionlistpage.jsp?currentuserid=<%=user.getUserId()%>&currentusername=<%=user.getUserName()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
            {key:"jobreqName"},
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


<script type="text/javascript">

YAHOO.example.DynamicData1 = function() {

	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&applicantId=" + oRecord.getData("applicantId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + sData + "</a>";
        };


var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
        
        };

var formatYesNo = function(elCell, oRecord, oColumn, sData) {
		 if(oRecord.getData("initiateJoiningProcess")	== "Y"){
			  elCell.innerHTML = "<%=Constant.getResourceStringValue("hr.text.yes",user.getLocale())%>";
		 }else{
			 elCell.innerHTML =  "<%=Constant.getResourceStringValue("hr.text.no",user.getLocale())%>";
		 }
        
        
        };


var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user.getLocale())%>" title="<%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=userDetails('" + oRecord.getData("offerownerId") + "')"+ ">" + sData + "</a>";
        
        };


    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"reqId", hidden:true},	
		{key:"uuid", hidden:true},
		{key:"offerownerId", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrl},
   {key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user.getLocale())%>",sortable:true, resizeable:true},
		{key:"targetjoiningdateStr", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Target_joining_date",user.getLocale())%>",sortable:true, resizeable:true},
	{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user.getLocale())%>",sortable:false, resizeable:true,formatter:formatUrljobreq},  
		{key:"initiateJoiningProcess", label:"<%=Constant.getResourceStringValue("aquisition.applicant.configutaion.initiateJoiningProcess",user.getLocale())%>",sortable:true, resizeable:true},  
    {key:"offerownerName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user.getLocale())%>",sortable:false, resizeable:true,formatter:formatUrlofferowner}					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/mytargetjoiningapplicantslistpage.jsp?interviewstate=<%=Common.OFFER_ACCEPTED%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},
            {key:"interviewState"},
		   {key:"targetjoiningdateStr"},
			{key:"reqId"},
		{key:"jobTitle"},
			{key:"uuid"},
			{key:"initiateJoiningProcess"},
			{key:"offerownerId"},
			{key:"offerownerName"}
			
	
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=5", // Initial request for first page of data
        dynamicData1: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:5 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata1", myColumnDefs, myDataSource, myConfigs);
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



<script type="text/javascript">

YAHOO.example.DynamicData2 = function() {

	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&applicantId=" + oRecord.getData("applicantId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + sData + "</a>";
        };


var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
        
        }

var formatYesNo = function(elCell, oRecord, oColumn, sData) {
		 if(oRecord.getData("initiateJoiningProcess")	== "Y"){
			  elCell.innerHTML = "<%=Constant.getResourceStringValue("hr.text.yes",user.getLocale())%>";
		 }else{
			 elCell.innerHTML =  "<%=Constant.getResourceStringValue("hr.text.no",user.getLocale())%>";
		 }
        
        
        }

var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user.getLocale())%>" title="<%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=userDetails('" + oRecord.getData("offerownerId") + "')"+ ">" + sData + "</a>";
        
        }




    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"reqId", hidden:true},	
		{key:"uuid", hidden:true},
		{key:"offerownerId", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrl},
   {key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user.getLocale())%>",sortable:true, resizeable:true},
		{key:"offerreleasedate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Offer_released_date",user.getLocale())%>",sortable:true, resizeable:true},
	{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user.getLocale())%>",sortable:false, resizeable:true,formatter:formatUrljobreq},  
    {key:"offerownerName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user.getLocale())%>",sortable:false, resizeable:true,formatter:formatUrlofferowner}  
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/myofferapplicantslistpage.jsp?ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},
            {key:"interviewState"},
		   {key:"offerreleasedate"},
			{key:"reqId"},
		{key:"jobTitle"},
			{key:"uuid"},
			{key:"offerownerId"},
			{key:"offerownerName"}
			
	
		
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=5", // Initial request for first page of data
        dynamicData2: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:5 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata2", myColumnDefs, myDataSource, myConfigs);
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



<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'none';   

</script>