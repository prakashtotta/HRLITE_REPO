<%@ include file="../common/include.jsp" %>

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
		url: 'jsp/refferal/getJobCode.jsp'
		//url: 'jsp/agency/getJobCode.jsp',
	     
		
});
	$("#jobTitle").autocomplete({
		url: 'jsp/refferal/getJobTitlerefferal.jsp'

		
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
  String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
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
	
document.jobRequisitionForm.action = "refjob.do?method=jobsearchsubmit";
document.jobRequisitionForm.submit();
}
 function resetdata(){	
document.jobRequisitionForm.searchposteddate.value="";
document.jobRequisitionForm.jobreqcode.value="";
document.jobRequisitionForm.jobTitle.value="";
document.jobRequisitionForm.locationId.value="";
document.jobRequisitionForm.jobtypeId.value="";
document.jobRequisitionForm.jobgradeId.value="";
document.jobRequisitionForm.parentOrgId.value="";
document.jobRequisitionForm.departmentId.value="";
}
function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.jobRequisitionForm.parentOrgId.value;
	
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
<!-- 
<br>
 Search Jobs   &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="refjob.do?method=newjobs">New jobs (published within <%=Constant.getValue("new.jobs.offset")%> month(s) )</a>
 -->

<html:form action="/jobs.do?method=jobsearchsubmit">
<div class="div">
<table border="0" width="100%">
		
	<tr>
			<td>Job Posted Date</td>
			<td>
			<select name="cri" class="list">
			<% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("before")){%>
  <option value="before" selected="yes" >before</option>
  <%}else {%>
<option value="before">before</option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("on")){%>
  <option value="on" selected="yes" >on</option>
  <%}else {%>
<option value="on">on</option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("after")){%>
  <option value="after" selected="yes" >after</option>
  <%}else {%>
<option value="after">after</option>
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
				<td>Job code</td>
			
		<td><html:text property="jobreqcode" styleClass="textdynamic" styleId="jobreqcode"  maxlength="500"/></td>
		</tr>
		<tr>
			<td>Job Title</td>
			
			<td><html:text property="jobTitle" styleClass="textdynamic" styleId="jobTitle" size="30" maxlength="600" /></td>
			
			<td>Location</td>
			<td>
			<html:select name="jobRequisitionForm"  property="locationId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="locationList" id="locationList" />

            <html:options collection="locationList" property="locationId"  labelProperty="locationName"/>
			</html:select>
	</td>
		</tr>
		<tr>
			<td>Job type</td>
			<td>
			<html:select name="jobRequisitionForm"  property="jobtypeId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="jobtypeList" id="jobtypeList" />

            <html:options collection="jobtypeList" property="jobTypeId"  labelProperty="jobTypeName"/>
			</html:select>
			</td>
			<td>Job grade</td>
			<td>
			<html:select name="jobRequisitionForm"  property="jobgradeId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="jobgradeList" id="jobgradeList" />

            <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			</html:select>
			</td>
		
		</tr>

		<tr>
			<td>Organization</td>
			<td>
			<html:select property="parentOrgId" onchange="retrieveURL('lovnologin.do?method=loadDeptlist');" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						Loading Departments......</span>
			
			</td>
			<td>Department</td>
			<td>
			<span id="departments">
			<html:select property="departmentId" styleClass="list">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</td>
		
		</tr>

		
		
<tr>
<td>
<input type="button" name="search" value="Search" onClick="searchjobs()" class="button">
<input type="button" name="search1" value="Reset" onClick="resetdata()" class="button">
</td>
<td></td>
</tr>
</table>
</div>
</html:form>

<% //if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
	
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

<b>Job List</b>
<br>
<br>
<div id="dynamicdata" class="div"></div>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {




var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="edit job requistion" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='refjob.do?method=jobdetails&uuid="+ oRecord.getData("uuid") + "'"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"templateId", hidden:true},		
            {key:"jobTitle", label:"Job Title", sortable:true, resizeable:true,formatter:formatUrl1},
            {key:"organizationValue", label:"Organization Name", sortable:false, resizeable:true},
		   {key:"departmentValue", label:"Department Name", sortable:false, resizeable:true},
		{key:"locationValue", label:"Location", sortable:false, resizeable:true},
		 {key:"projectcodeName", label:"Project Name", sortable:false, resizeable:true},
		 {key:"jobtypeValue", label:"Job Type", sortable:false, resizeable:true},
			{key:"workshiftValue", label:"Work Shift", sortable:false, resizeable:true},
		{key:"publishedDate", label:"Job Posted Date", sortable:false, resizeable:true},
		{key:"targetfinishdate", label:"Target finish date", sortable:false, resizeable:true}
				          
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/jobrequisitionsearchlistpage.jsp?jobTilte=<%=aform.getJobTitle()%>&postdate=<%=postdate%>&>&cri=<%=aform.getAppliedcri()%>&locationId=<%=aform.getLocationId()%>&orgId=<%=aform.getParentOrgId()%>&departmentId=<%=aform.getDepartmentId()%>&jobgradeId=<%=aform.getJobgradeId()%>&jobtypeId=<%=aform.getJobtypeId()%>&jobreqcode=<%=aform.getJobreqcode()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
			{key:"uuid"},
            {key:"jobTitle"},
            {key:"organizationValue"},
		   {key:"departmentValue"},
			{key:"locationValue"},
		{key:"projectcodeName"},
			{key:"jobtypeValue"},
			{key:"workshiftValue"},
			{key:"publishedDate"},
			{key:"targetfinishdate"}
			
			
			
				
		
			
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
<%//}%>



</body>

