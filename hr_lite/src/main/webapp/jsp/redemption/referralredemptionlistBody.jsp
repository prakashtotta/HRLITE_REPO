
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
System.out.println("user data : "+user1);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.REFERRAL_REDEMPTION_SCREEN);
 if(!StringUtils.isNullOrEmpty(strcolumncontent)){
	 strcolumncontent = ","+strcolumncontent;
	 strcolumncontent= strcolumncontent.substring(0,strcolumncontent.length()-1);
 }
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.REFERRAL_REDEMPTION_SCREEN);

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

  <%

String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>

<bean:define id="aform" name="referralRedemptionSummaryForm" type="com.form.ReferralRedemptionSummaryForm" />
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
	
document.referralRedemptionSummaryForm.action = "referralredemptionsummary.do?method=searchreferralredemptionssubmit";
document.referralRedemptionSummaryForm.submit();
}


function markpaid(refredids){
	//var url = "<%=request.getContextPath()%>/referralredemptionsummary.do?method=markpaidforapplicantsSubmit&refredids="+refredids;
	//var doyou = confirm("Are you sure to mark paid? (OK = Yes   Cancel = No)");
	//  if (doyou == true){
	//	document.referralRedemptionSummaryForm.action = url;
   	//	document.referralRedemptionSummaryForm.submit();


	 // }
	 var url = "<%=request.getContextPath()%>/referralredemptionsummary.do?method=markpaidforapplicants&refredids="+refredids;
	  GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markforpaid",user1.getLocale())%>',url,400,600, messageret);

}
	function messageret(){
	//window.location.reload();
	//history.go(0);
var url = "<%=request.getContextPath()%>/referralredemptionsummary.do?method=referralredemptionlist";
	location.replace(url)

			}
	function init(){
		document.referralRedemptionSummaryForm.uom.focus();
		}



function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.referralRedemptionSummaryForm.orgId.value.trim();
	
	
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

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsReferralRedumption";
    GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}

function exporttoexcel(){
	
document.referralRedemptionSummaryForm.action = "referralredemptionsummary.do?method=exporttoexcelAllSearchReferalRedemption";
document.referralRedemptionSummaryForm.submit();
}

function resetdata(){
location.href="referralredemptionsummary.do?method=referralredemptionlist";


}

</script>

 <b>Search referral redemptions  </b>
<br><br>

<body class="yui-skin-sam" onLoad="init()" >

<html:form action="/referralredemptionsummary.do?method=searchreferralredemptionssubmit">
<div class="div">
<table border="0" width="100%">
		
	<tr>
			<td>Released Date</td>
			<td>
			<select name="cri">
			<% if(!StringUtils.isNullOrEmpty(aform.getReleasecri()) && aform.getReleasecri().equals("before")){%>
  <option value="before" selected="yes" ><%=Constant.getResourceStringValue("task.before",user1.getLocale())%></option>
  <%}else {%>
<option value="before">before</option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getReleasecri()) && aform.getReleasecri().equals("on")){%>
  <option value="on" selected="yes" ><%=Constant.getResourceStringValue("task.on",user1.getLocale())%></option>
  <%}else {%>
<option value="on">on</option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getReleasecri()) && aform.getReleasecri().equals("after")){%>
  <option value="after" selected="yes" ><%=Constant.getResourceStringValue("task.after",user1.getLocale())%></option>
  <%}else {%>
<option value="after">after</option>
  <%}%>
 
</select>


<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="releasedate" readonly="true" value="<%=(aform.getReleasedate() == null)? "" :aform.getReleasedate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.referralRedemptionSummaryForm.releasedate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.referralRedemptionSummaryForm.releasedate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
			<td><%=Constant.getResourceStringValue("admin.RefferalSchemeType.uom",user1.getLocale())%></td>
			<td><html:text property="uom" size="30"/></td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%></td>
			<td>
			<html:text property="empcode" size="30"/>
			</td><td><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.jobTitle",user1.getLocale())%></td>
			<td>
			<html:select  property="requitionId">
			<option value=""></option>
			<bean:define name="referralRedemptionSummaryForm" property="jobtitleList" id="jobtitleList" />

            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
	</td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%></td>
		<td>
		<html:select  property="refBudgetId">
			<option value=""></option>
			<bean:define name="referralRedemptionSummaryForm" property="referralBudgetCodeList" id="referralBudgetCodeList" />

            <html:options collection="referralBudgetCodeList" property="ref_budgetId"  labelProperty="ref_budgetCode"/>
			</html:select>
		</td>
		<td><%=Constant.getResourceStringValue("admin.RefferalScheme.Referralscheme",user1.getLocale())%></td>
		<td>
		<html:select  property="refferalSchemeId">
			<option value=""></option>
			<bean:define name="referralRedemptionSummaryForm" property="referralSchemeList" id="referralSchemeList" />

            <html:options collection="referralSchemeList" property="refferalScheme_Id"  labelProperty="refferalScheme_Name"/>
			</html:select>
		</td>
		 </tr>

		 <tr>
		<td>	
		<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>
		</td>
		<td>
		<html:select  property="state">
			<option value=""></option>
			<bean:define name="referralRedemptionSummaryForm" property="releaseTypeList" id="releaseTypeList" />

            <html:options collection="releaseTypeList" property="key"  labelProperty="value"/>
			</html:select>
		</td>
		<td><%=Constant.getResourceStringValue("agencyredumption.page.table.configuration.Paid",user1.getLocale())%></td>
		<td>
				<html:select  property="isPaid">
			<option value=""></option>
			<bean:define name="referralRedemptionSummaryForm" property="paidTypeList" id="paidTypeList" />

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
		 <td><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
			<td><html:text property="applicantName" size="30"/></td>
		
		</tr>
	<tr>
		<td>	
		<%=Constant.getResourceStringValue("refferal.common.Employee_Name",user1.getLocale())%>
		</td>
			<td>	
	<html:text property="empname" size="30" />
		</td>
		 <td><%=Constant.getResourceStringValue("refferal.common.Employee_Email",user1.getLocale())%></td>
			<td><html:text property="empemail" size="30"/></td>
		
		</tr>
		<tr><td><%=Constant.getResourceStringValue("admin.organization.organization",user1.getLocale())%></td>
		<td>
			
			<html:select property="orgId" onchange="retrieveURL('referralredemptionsummary.do?method=loadDeptlistWithProjectcode');">
			
			<bean:define name="referralRedemptionSummaryForm" property="organizationList" id="organizationList" />
           <option value=""></option>
            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>	</span>
			</td>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="departmentId" >
			 
			<bean:define name="referralRedemptionSummaryForm" property="departmentList" id="departmentList" />
             <option value=""></option>
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
			</span>
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
		<input type="button" value="reset" onClick="resetdata()"  class="button"/>
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
<div align="left"  class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.export.job.started",user1.getLocale())%> <a href="bulkuploadtask.do?method=mybulktasklist"><%=Constant.getResourceStringValue("aquisition.process.job.page",user1.getLocale())%></a></font></td>
			<td></td>
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


<b><%=Constant.getResourceStringValue("hr.Redemption.Refferal_Redemption_list",user1.getLocale())%> </b><br><br>
		<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.exporttoexcel",user1.getLocale())%>" onClick="exporttoexcel()" class="button">
		<!-- 
		<div class="bd">
					<div id="container"></div> -->
		<button class="button" id="delete"><%=Constant.getResourceStringValue("agencyredumption.page.table.configuration.isPaid",user1.getLocale())%></button>
		<!-- </div> -->
	
		
		<a class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
		
<br><br>





<div id="dynamicdata" class="div">

<script type="text/javascript">


YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='referralredemptionsummary.do?method=summarydetails&backtolisturl=referralredemptionsummary.do?method=referralredemptionlist&refredid=" + oRecord.getData("refredid") +"&secureid=" + oRecord.getData("uuid") + "'"+">" + sData + "</a>";
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
                   checknumber = checknumber + recs[j].getData("refredid") +",";
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
        YAHOO.util.Connect.asyncRequest('POST', 'referralredemptionsummary.do',
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
                      '&refredid=' + record.getData('refredid') +"&csrfcode="+document.referralRedemptionSummaryForm["csrfcode"].value+
				      '&uuid=' + record.getData('uuid') 
        );
    };

	// Column definitions

    var myColumnDefs = [
		{key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
		{key:"refredid", hidden:true},
		{key:"uuid", hidden:true},
		{key:"applicantName", label:"Applicant name", sortable:true, resizeable:true,formatter:formatUrl}
		<%=strcolumncontent%>
        ];


    if(isReleased == false){

myColumnDefs = [
		{key:"Select",label:'<input type="checkbox"/>', width:"30",formatter:"checkbox"},
		{key:"refredid", hidden:true},
		{key:"uuid", hidden:true},
		{key:"applicantName", label:"Applicant name", sortable:true, resizeable:true,formatter:formatUrl}
		<%=strcolumncontent%>
        ];

	}


    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/redemption/referralredemptionlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&requitionId=<%=aform.getRequitionId()%>&refBudgetId=<%=aform.getRefBudgetId()%>&refferalSchemeId=<%=aform.getRefferalSchemeId()%>&refferalSchemeTypeId=<%=aform.getRefferalSchemeTypeId()%>&ruleId=<%=aform.getRuleId()%>&cri=<%=aform.getReleasecri()%>&applicantNo=<%=aform.getApplicantNo()%>&uom=<%=aform.getUom()%>&applicantName=<%=aform.getApplicantName()%>&state=<%=aform.getState()%>&isPaid=<%=aform.getIsPaid()%>&releasedate=<%=aform.getReleasedate()%>&employeecode=<%=aform.getEmpcode()%>&employeename=<%=aform.getEmpname()%>&employeeemail=<%=aform.getEmpemail()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
        {key:"refredid"},
    	{key:"uuid"},
        {key:"applicantName"},           
    	<%=strkeycontent%>
    	
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=refredid&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"refredid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

