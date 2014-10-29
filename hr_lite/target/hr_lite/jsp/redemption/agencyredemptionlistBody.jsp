
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.AGENCY_REDEMPTION__SCREEN);
 if(!StringUtils.isNullOrEmpty(strcolumncontent)){
	 strcolumncontent = ","+strcolumncontent;
	 strcolumncontent= strcolumncontent.substring(0,strcolumncontent.length()-1);
 }
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.AGENCY_REDEMPTION__SCREEN);

%>
<style type="text/css">
	#myAutoComplete {
		width:16em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
</style>

<STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
</STYLE>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

  

<bean:define id="aform" name="agencyRedemptionSummaryForm" type="com.form.AgencyRedemptionSummaryForm" />
<%

String isReleased = "false";
	
 if(aform.getState() != null && aform.getState().equals(Common.REFERRAL_REDEMPTION_RELEASED)){
	 isReleased="true";
 }
%>
<script language="javascript">
this.reload_on_close=false;
var isReleased=<%=isReleased%>;

function searchredemptions(){

	 var alertstr = "";
	 var showalert=false;
	 var appno = document.agencyRedemptionSummaryForm.applicantNo.value.trim();
	if(isNaN(appno)){
	      alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.not_a_number",user1.getLocale())%><BR>";
	 	  showalert = true;
	 }

	if (showalert){
		  alert(alertstr);
		  return false;
	 }
	
document.agencyRedemptionSummaryForm.action = "agencyredemptionsummary.do?method=searchagencyredemptionssubmit";
document.agencyRedemptionSummaryForm.submit();
}
 function resetform(){
location.href="agencyredemptionsummary.do?method=agencyredemptionlist";
 }

function markpaid(agrefids){
	var url = "<%=request.getContextPath()%>/agencyredemptionsummary.do?method=markpaidforapplicants&agrefids="+agrefids;
	var doyou = confirm("Are you sure to mark paid? (OK = Yes   Cancel = No)");
	  if (doyou == true){
		document.agencyRedemptionSummaryForm.action = url;
    	document.agencyRedemptionSummaryForm.submit();

	  }


}
	function messageret(){
	//window.location.reload();
	//history.go(0);
	var url = "<%=request.getContextPath()%>/agencyredemptionsummary.do?method=agencyredemptionlist";
	location.replace(url)

			}
document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.agencyRedemptionSummaryForm.search.click();",200);
}
}

function init(){
	document.agencyRedemptionSummaryForm.uom.focus();
	}

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsAgencyRedumption";
    GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}
function exporttoexcel(){

	
document.agencyRedemptionSummaryForm.action = "agencyredemptionsummary.do?method=exporttoexcelAllSearchAgencyRedemption";
document.agencyRedemptionSummaryForm.submit();
}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.agencyRedemptionSummaryForm.orgId.value.trim();
	
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;

	    try {
    		req.open("GET", url, true);
						
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
			req.send();
			
    	}
  	}


}

function processStateChange() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtml(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="departments")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}
   
</script>
<body class="yui-skin-sam" onLoad="init()" >

 <b><%=Constant.getResourceStringValue("hr.Redemption.Search_agency_redemptions",user1.getLocale())%>  </b>
<br>
<br>



<html:form action="/agencyredemptionsummary.do?method=searchagencyredemptionssubmit">
<div class="div">
<table border="0" width="100%">
		
	<tr>
			<td><%=Constant.getResourceStringValue("hr.Redemption.Released_Date",user1.getLocale())%></td>
			<td>
			<select name="cri">
			<% if(!StringUtils.isNullOrEmpty(aform.getReleasecri()) && aform.getReleasecri().equals("before")){%>
  <option value="before" selected="yes" ><%=Constant.getResourceStringValue("task.before",user1.getLocale())%></option>
  <%}else {%>
<option value="before"><%=Constant.getResourceStringValue("task.before",user1.getLocale())%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getReleasecri()) && aform.getReleasecri().equals("on")){%>
  <option value="on" selected="yes" ><%=Constant.getResourceStringValue("task.on",user1.getLocale())%></option>
  <%}else {%>
<option value="on"><%=Constant.getResourceStringValue("task.on",user1.getLocale())%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getReleasecri()) && aform.getReleasecri().equals("after")){%>
  <option value="after" selected="yes" ><%=Constant.getResourceStringValue("task.after",user1.getLocale())%></option>
  <%}else {%>
<option value="after"><%=Constant.getResourceStringValue("task.after",user1.getLocale())%></option>
  <%}%>
 
</select>


<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="releasedate" readonly="true" value="<%=(aform.getReleasedate() == null)? "" :aform.getReleasedate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.agencyRedemptionSummaryForm.releasedate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.agencyRedemptionSummaryForm.releasedate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
			<td><%=Constant.getResourceStringValue("agencyredumption.page.table.configuration.uom",user1.getLocale())%></td>
			<td><html:text property="uom" size="30"/></td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.Redemption.Agency",user1.getLocale())%></td>
			<td>
			<html:select  property="agencyId">
			<option value=""></option>
			<bean:define name="agencyRedemptionSummaryForm" property="agencyList" id="agencyList" />

            <html:options collection="agencyList" property="userId"  labelProperty="firstName"/>
			</html:select>
			</td>
		<td><%=Constant.getResourceStringValue("hr.applicant.Job_Title",user1.getLocale())%></td>
			<td>
			<html:select  property="requitionId">
			<option value=""></option>
			<bean:define name="agencyRedemptionSummaryForm" property="jobtitleList" id="jobtitleList" />

            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
	</td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbc",user1.getLocale())%></td>
		<td>
		<html:select  property="refBudgetId">
			<option value=""></option>
			<bean:define name="agencyRedemptionSummaryForm" property="referralBudgetCodeList" id="referralBudgetCodeList" />

            <html:options collection="referralBudgetCodeList" property="ref_budgetId"  labelProperty="ref_budgetCode"/>
			</html:select>
		</td>
		<td><%=Constant.getResourceStringValue("admin.RefferalScheme.Referralschemes",user1.getLocale())%></td>
		<td>
		<html:select  property="refferalSchemeId">
			<option value=""></option>
			<bean:define name="agencyRedemptionSummaryForm" property="referralSchemeList" id="referralSchemeList" />

            <html:options collection="referralSchemeList" property="refferalScheme_Id"  labelProperty="refferalScheme_Name"/>
			</html:select>
		</td>
		 </tr>

				
		 <tr>
		<td>	
		<%=Constant.getResourceStringValue("admin.Accomplishment.status",user1.getLocale())%>
		</td>
		<td>
		<html:select  property="state">
			<option value=""></option>
			<bean:define name="agencyRedemptionSummaryForm" property="releaseTypeList" id="releaseTypeList" />

            <html:options collection="releaseTypeList" property="key"  labelProperty="value"/>
			</html:select>
		</td>
		<td><%=Constant.getResourceStringValue("agencyredumption.page.table.configuration.Paid",user1.getLocale())%></td>
		<td>
				<html:select  property="isPaid">
			<option value=""></option>
			<bean:define name="agencyRedemptionSummaryForm" property="paidTypeList" id="paidTypeList" />

            <html:options collection="paidTypeList" property="key"  labelProperty="value"/>
			</html:select>
		</td>
		 </tr>
         	 <tr>
		<td>	
		<%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%>
		</td>
			<td>	
	<html:text property="applicantNo" size="30" />
		</td>
		 <td><%=Constant.getResourceStringValue("aquisition.applicant.APPLICANT_NAME",user1.getLocale())%></td>
			<td><html:text property="applicantName" size="30"/></td>
		
		</tr>

		<tr><td><%=Constant.getResourceStringValue("rule.org",user1.getLocale())%></td>
		<td>
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDeptlistWithProjectcode');">
			
			<bean:define name="agencyRedemptionSummaryForm" property="organizationList" id="organizationList" />
           <option value=""></option>
            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>	</span>
			</td>
			<td><%=Constant.getResourceStringValue("rule.department",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="departmentId" >
			 
			<bean:define name="agencyRedemptionSummaryForm" property="departmentList" id="departmentList" />
            <option value=""></option>
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
			</span>
			</td>
			</td>
		</tr>


		
<tr>
<td>

</td>
<td>


</td>
</tr>
</table>


<table width="100%">
	<tr>
		<td>
		<input type="button" name="search" value="search" onClick="searchredemptions()" class="button">
		<input type="button" value="reset" onClick="resetform()" class="button"/>

	</td>
	</tr>
</table>
</div>
</html:form>
<%
String exporttoexcel = (String)request.getAttribute("exporttoexcel");
%>

<%if(exporttoexcel != null && exporttoexcel.equals("yes")){
%>

<div align="left" class="msg">

	<table border="0" width="100%">
	

<tr><td><font color="white">
<%=Constant.getResourceStringValue("aquisition.applicant.export.job.started",user1.getLocale())%></font><font color="white"> <a href="bulkuploadtask.do?method=mybulktasklist"><%=Constant.getResourceStringValue("aquisition.process.job.page",user1.getLocale())%></a></font></td>

</tr>
</table>
</div>

<%}%>




<% 
//if(searchpagedisplay != null && searchpagedisplay.equals("yes"))
//{
	
	String appdate = null;
	if(!StringUtils.isNullOrEmpty(aform.getReleasedate())){
            appdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getReleasedate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	
	
	%>

<br><br>

<b>
<%=Constant.getResourceStringValue("hr.Redemption.Agency_redemptions_list",user1.getLocale())%></b><br><br>
		<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.exporttoexcel",user1.getLocale())%>" onClick="exporttoexcel()" class="button">

		 <!-- <div class="bd">
					<div id="container"></div> -->
		<button id="delete" class="button"><%=Constant.getResourceStringValue("agencyredumption.page.table.configuration.isPaid",user1.getLocale())%></button>
		<!--</div> -->

	    <a class ="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
	


<br><br>





<div id="dynamicdata" class="div" >

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='agencyredemptionsummary.do?method=summarydetails&backtolisturl=agencyredemptionsummary.do?method=agencyredemptionlist&agredid=" + oRecord.getData("agredid") +"&secureid=" + oRecord.getData("uuid") + "'"+">" + sData + "</a>";
        };

    
	
	var Dom = YAHOO.util.Dom,
				Event = YAHOO.util.Event,
				log = function (msg) {
					YAHOO.log(msg,'info','deleteRowsBy demo');
				};

YAHOO.widget.DataTable.prototype.deleteRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("agredid") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                markpaid(checknumber);
				}
				
				this.render();
			};

	

var submitter = function (callback, newValue)
    {
	   
        var record = this.getRecord();
	    var column = this.getColumn();
	//	alert(column);
        var oldValue = this.value;
		//alert(record.getData('address'));
        var datatable = this.getDataTable();

        // send the data to our update page to update the value in the database
        YAHOO.util.Connect.asyncRequest('POST', 'agencyredemptionsummary.do',
        {
			
            success: function (o)
            {
				 callback(true, o.responseText); 
                
            },
            failure: function (o)
            {
                alert(o.statusText);
                callback();
            },
            scope: this
        },
        'action=cellEdit&column=' + column.key +
				     '&method=markpaid' +
                     '&isPaid=' + escape(newValue) +
                      '&agredid=' + record.getData('agredid') +"&csrfcode="+document.agencyRedemptionSummaryForm["csrfcode"].value+
				      '&uuid=' + record.getData('uuid') 
        );
    };

	// Column definitions

    var myColumnDefs = [
		{key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
		{key:"agredid", hidden:true},
		{key:"uuid", hidden:true},
		{key:"applicantName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.APPLICANT_NAME",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl}
             <%=strcolumncontent%>
        ];


    if(isReleased == false){

myColumnDefs = [
		{key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},       
		{key:"agredid", hidden:true},
		{key:"uuid", hidden:true},
		{key:"applicantName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.APPLICANT_NAME",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl}
            <%=strcolumncontent%>
        ];

	}


    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/redemption/agencyredemptionlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&agencyId=<%=aform.getAgencyId()%>&requitionId=<%=aform.getRequitionId()%>&refBudgetId=<%=aform.getRefBudgetId()%>&refferalSchemeId=<%=aform.getRefferalSchemeId()%>&refferalSchemeTypeId=<%=aform.getRefferalSchemeTypeId()%>&ruleId=<%=aform.getRuleId()%>&cri=<%=aform.getReleasecri()%>&applicantNo=<%=aform.getApplicantNo()%>&uom=<%=aform.getUom()%>&applicantName=<%=aform.getApplicantName()%>&state=<%=aform.getState()%>&isPaid=<%=aform.getIsPaid()%>&releasedate=<%=aform.getReleasedate()%>&orgId=<%=aform.getOrgId()%>&departmentId=<%=aform.getDepartmentId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"agredid"},
            {key:"applicantName"},           
		   	<%=strkeycontent%>
			
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
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }

		 var highlightEditableCell = function(oArgs) {
            var elCell = oArgs.target;
            if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
                this.highlightCell(elCell);
            }
        };
        myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
        myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);
	myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
    
myDataTable.subscribe('checkboxClickEvent', function(oArgs) {
				var elCheckbox = oArgs.target;
				var newValue = elCheckbox.checked;
				var record = this.getRecord(elCheckbox);
				var column = this.getColumn(elCheckbox);
				record.setData(column.key,newValue);
			});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					for (var i=0;i < l; i++) {
						rs.getRecord(i).setData('Select',check);
					}
					this.render();
				}
			});
			
			
			Event.on('delete','click', function() {
				myDataTable.deleteRowsBy(function (data) {
					return data.Select;
				});
			});

    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<%//}
%>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->
</div>


</body>

