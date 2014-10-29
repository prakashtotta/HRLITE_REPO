<%@ include file="../common/include.jsp" %>

 <%
  String path = (String)request.getAttribute("filePath");
  String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
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
<script type="text/javascript">


$(function() {

	//$("#reviewername").autocomplete('jsp/talent/getUserData.jsp');
	$("#jobreqcode").autocomplete({
		url: 'jsp/agency/getJobCode.jsp'
		
});
	$("#jobTitle").autocomplete({
		url: 'jsp/agency/getJobTitle.jsp'
		
});

});
</script>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
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
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>
<script language="javascript">
function editapplicant(aid){
	var url = "jobs.do?method=editapplicant&applicantId="+aid;
	parent.showPopWin(url, 700, 600, null,true);
}
 function searchjobs(){
	
document.jobRequisitionForm.action = "agjob.do?method=jobsearchsubmit";
document.jobRequisitionForm.submit();
}
 function resetdata(){

 document.jobRequisitionForm.cri.value="";
document.jobRequisitionForm.searchposteddate.value="";
document.jobRequisitionForm.jobreqcode.value="";
document.jobRequisitionForm.jobTitle.value="";
document.jobRequisitionForm.locationId.value="";
document.jobRequisitionForm.jobtypeId.value="";
document.jobRequisitionForm.jobgradeId.value="";
document.jobRequisitionForm.parentOrgId.value="";
document.jobRequisitionForm.departmentId.value="";

setTimeout("retrieveURL('agjob.do?method=loadDepartments')",200);

}

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.jobRequisitionForm.parentOrgId.value.trim();
	
	
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
   

function init(){

document.jobRequisitionForm.jobreqcode.focus();

}
</script>

<body class="yui-skin-sam" onLoad="init()">
<div class="div">
<html:form action="/agjob.do?method=jobsearchsubmit">

<table border="0" width="100%">
		
	<tr>
			<td><%=Constant.getResourceStringValue("Requisition.JobPostedDate",user1.getLocale())%></td>
			<td>
			<select name="cri" class="list">
			<option value=""></option>
			<% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("before")){%>
  <option value="before" selected="yes" ><%=Constant.getResourceStringValue("task.before",user1.getLocale())%></option>
  <%}else {%>
<option value="before"><%=Constant.getResourceStringValue("task.before",user1.getLocale())%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("on")){%>
  <option value="on" selected="yes" ><%=Constant.getResourceStringValue("task.on",user1.getLocale())%></option>
  <%}else {%>
<option value="on"><%=Constant.getResourceStringValue("task.on",user1.getLocale())%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("after")){%>
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

<INPUT TYPE="text" class="textdynamic" NAME="searchposteddate" readonly="true" value="<%=aform.getSearchposteddate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.jobRequisitionForm.searchposteddate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.jobRequisitionForm.searchposteddate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
			<td><%=Constant.getResourceStringValue("Requisition.jobcode",user1.getLocale())%></td>
			<td><html:text property="jobreqcode" styleClass="textdynamic" styleId="jobreqcode"  maxlength="500"/></td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
			<td><html:text property="jobTitle" styleClass="textdynamic" styleId="jobTitle" size="30" maxlength="600"/></td>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionForm"  property="locationId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="locationList" id="locationList" />

            <html:options collection="locationList" property="locationId"  labelProperty="locationName"/>
			</html:select>
	</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionForm"  property="jobtypeId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="jobtypeList" id="jobtypeList" />

            <html:options collection="jobtypeList" property="jobTypeId"  labelProperty="jobTypeName"/>
			</html:select>
			</td>

			<td><%=Constant.getResourceStringValue("Requisition.jobgrade",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionForm"  property="jobgradeId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="jobgradeList" id="jobgradeList" />

            <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			</html:select>
			</td>
		
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
			<html:select property="parentOrgId" onchange="retrieveURL('agjob.do?method=loadDepartments');" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>....</span>
			
			</td>
			<td><%=Constant.getResourceStringValue("refferal.agencyreferralportal.jobsearch.department",user1.getLocale())%></td>
			<td>

			<span id="departments">
			<html:select property="departmentId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
			</span>
			
			</td>
		
		</tr>

		
		
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchjobs()" class="button">
<input type="button" name="search1" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button">
</td>
<td></td>
</tr>
</table>

</html:form>
</div>
<%
	
	String postdate = null;
	if(user1 != null){ 
	if(!StringUtils.isNullOrEmpty(aform.getSearchposteddate())){
            postdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchposteddate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	}else{
   postdate = aform.getSearchposteddate();
	}
	
	
	%>


<br>
<br>
<b><%=Constant.getResourceStringValue("Requisition.reqlist",user1.getLocale())%></b>
<br>
<br>
<div id="dynamicdata" class="div">

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {




var summaryUrl = function(elCell, oRecord, oColumn, sData) {

  elCell.innerHTML = "<a href='agjob.do?method=requistionapplicantlist&state=0&requistionId=" + oRecord.getData("jobreqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ " >" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",user1.getLocale())%>" + "</a>";

};


var jobdetails = function(elCell, oRecord, oColumn, sData) {

			  elCell.innerHTML = "<a href='agjob.do?method=jobdetails&backurl=agjob.do?method=jobsearch&reqid=" + oRecord.getData("jobreqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ ">" + oRecord.getData("jobTitle") + "</a>";
		
		};
    // Column definitions
    var myColumnDefs = [
		{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},
		{key:"uuid", hidden:true},		
		{key:"state", hidden:true},
		{key:"status", hidden:true},
        {key:"jobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%>", sortable:true, resizeable:true,formatter:jobdetails},
        {key:"organizationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"departmentValue", label:"<%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"projectcodeName", label:"<%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"jobtypeValue", label:"<%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"workshiftValue", label:"<%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"publishedDate", label:"<%=Constant.getResourceStringValue("Requisition.JobPostedDate",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"targetfinishdate", label:"<%=Constant.getResourceStringValue("Requisition.Target.finish.date",user1.getLocale())%>", sortable:false, resizeable:true},
		{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary",user1.getLocale())%>", sortable:false, resizeable:true,formatter:summaryUrl}          
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/jobrequisitionsearchlistpage.jsp?jobTilte=<%=aform.getJobTitle()%>&postdate=<%=postdate%>&>&cri=<%=aform.getAppliedcri()%>&locationId=<%=aform.getLocationId()%>&orgId=<%=aform.getParentOrgId()%>&departmentId=<%=aform.getDepartmentId()%>&jobgradeId=<%=aform.getJobgradeId()%>&jobtypeId=<%=aform.getJobtypeId()%>&jobreqcode=<%=aform.getJobreqcode()%>&searchpagedisplay=<%=searchpagedisplay%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
			{key:"state"},
			{key:"status"},
            {key:"jobTitle"},
            {key:"organizationValue"},
		   	{key:"departmentValue"},
			{key:"locationValue"},
			{key:"projectcodeName"},
			{key:"jobtypeValue"},
			{key:"workshiftValue"},
			{key:"publishedDate"},
			{key:"targetfinishdate"},
			{key:"uuid"},
			{key:"summary"}

			
			
				
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
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


</div>

</body>

