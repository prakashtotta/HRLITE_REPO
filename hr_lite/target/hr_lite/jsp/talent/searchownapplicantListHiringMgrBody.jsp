<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/greybox.jsp" %>
		  <script type="text/javascript" src="jsp/mutliauto/jquery.tokeninput.js"></script>
	<link rel="stylesheet" href="jsp/mutliauto/token-input-facebook.css" type="text/css" />

<%@ include file="../common/include.jsp" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String toggelOpen=(String)request.getAttribute("toggelOpen");
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


<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
 
%>

<script language="javascript">

function userDetailsOrGroup(id,isgroup){
	var url = "";
	if(isgroup == 'Y'){
		url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id;
	}else{
			url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
		}
	
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}
	 function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsHriringMgr";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}

function sendbulkresumeforscreening(appIds){
	var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=sendbulkresumeforscreening&eventype=<%=Common.IN_SCREENING%>&applicantids="+appIds;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>', url, messageret);

}

function markdeletebulk(appIds){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=markfordeletionbulk&applicantids="+appIds;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>',url,400,600, messageret);

}

function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	//parent.setPopTitle1("Applicant");
	//parent.showPopWin(url, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}
function createapplicant(){

	var url = "<%=request.getContextPath()%>/applicant.do?method=createPage";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%>',url,messageret);
}

function editJobReq(jb_id,juuid){
	//var url = "<%=request.getContextPath()%>/jobreq.do?method=jobreqdetails&approverstatus=yes&offerapplicant=yes&jobreqId="+jb_id+"&secureid="+juuid;;
	//window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=800');
var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);
}

	 function searchapplicant(){
	var tags = $('input[name="tags"]').val();  

document.applicantForm.action = "applicant.do?method=searchownapplicantHiringMgr&tags="+tags;
document.applicantForm.submit();
}

	 function exporttoexcel(){
	
document.applicantForm.action = "applicant.do?method=exporttoexcelHiringMgr";
document.applicantForm.submit();
}


	
	function messageret(){
	window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			} 

</script>
<script language="javascript">

function resetData(){
  document.applicantForm.appliedcri.value="NoValue";
  document.applicantForm.searchfromdate.value="";
   document.applicantForm.searchtodate.value="";
  document.applicantForm.applicantNo.value="";
  document.applicantForm.fullName.value="";
  document.applicantForm.requitionId.value="";
  document.applicantForm.email.value="";
  document.applicantForm.previousOrganization.value="";
   document.applicantForm.interviewState.value="NoValue";
   document.applicantForm.tags.value="";
   document.applicantForm.keywords.value="";
   
   
 
 }  
</script>


<body class="yui-skin-sam">

<html:form action="/applicant.do?method=searchownapplicantHiringMgr">


<div class="container">
<table border="0" width="80%" class="div">
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.keyword",user1.getLocale())%></td>
<td><html:text property="keywords" styleClass="textdynamic" size="30" maxlength="600"/></td>
<td>Tags</td>
<td>
<%
String tagdata=aform.getTagNames();

%>
     <input type="text" id="demo-input-facebook-theme" name="tags" />
        <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
        <script type="text/javascript">
        $(document).ready(function() {
            $("#demo-input-facebook-theme").tokenInput("jsp/talent/getTagDataJsonMuti.jsp", {
				preventDuplicates: true,
			     hintText: "Type in a search tag",
					 prePopulate: [
						<%=tagdata%>
                ],
                theme: "facebook"
            });
        });
        </script>
</td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
<td><html:text property="applicantNo" styleClass="textdynamic" size="30"/></td>
<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
<td><html:text property="fullName" styleClass="textdynamic" size="30" maxlength="600"/></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
<td><html:select  property="requitionId" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />

            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
</td>
<td><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%></td>
			<td>
			<html:select  property="interviewState" styleClass="list">
			<bean:define name="applicantForm" property="interviewstateList" id="interviewstateList" />

            <html:options collection="interviewstateList" property="interviewstateCode"  labelProperty="interviewstateName"/>
			</html:select>
			</td>
</tr>

		<tr>
			
			<td>Current organization</td>
	     <td><input  name="previousOrganization" class="textdynamic" value="<%=aform.getPreviousOrganization()%>" type="text">	
			</td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>
			</td>
		<td><html:text property="email" styleClass="textdynamic" size="30"/></td>
		</tr>
</table>

<div style="background: #f3f3f3;"><a href="#" rel="toggle[rabbit]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> advance parameters </div>
<div id="rabbit" style="width  100%;">


<table border="0" width="80%" class="div">
		
	<tr>
			<td> Date type:
			<html:select  property="appliedcri" styleClass="list">
			<bean:define name="applicantForm" property="searchCriDateList" id="searchCriDateList" />

            <html:options collection="searchCriDateList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
			<td>			
From date :

<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" class="textdynamic" NAME="searchfromdate" readonly="true" value="<%=(aform.getSearchfromdate() == null)? "" :aform.getSearchfromdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.searchfromdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.searchfromdate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>
</td>
<td>
To date :

<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx2">
var cal1xx2 = new CalendarPopup("testdiv2");
cal1xx2.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" class="textdynamic" NAME="searchtodate" readonly="true" value="<%=(aform.getSearchtodate() == null)? "" :aform.getSearchtodate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx2.select(document.applicantForm.searchtodate,'anchor1xx2','<%=datepattern%>'); return false;" TITLE="cal1xx2.select(document.applicantForm.searchtodate,'anchor1xx2','<%=datepattern%>'); return false;" NAME="anchor1xx2" ID="anchor1xx2"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv2" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
<td></td>
</tr>


</table>
</div>


<%
String exporttoexcel = (String)request.getAttribute("exporttoexcel");
%>




<table>
<tr>
<td></td>
<td></td>
</tr>
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchapplicant()" class="button">
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.export.results",user1.getLocale())%>" onClick="exporttoexcel()" class="button">

<%if(exporttoexcel != null && exporttoexcel.equals("yes")){
%>
<td><div class="msg"><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.export.job.started",user1.getLocale())%> <a href="bulkuploadtask.do?method=mybulktasklist"><font color="white"><%=Constant.getResourceStringValue("aquisition.process.job.page",user1.getLocale())%></a></font>
</td>
<%}%>

</td>

</tr>
</table>

</html:form>

<%
	//if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
	
	String fromdate = null;
	String todate = null;
	if(!StringUtils.isNullOrEmpty(aform.getSearchfromdate())){
            fromdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchfromdate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	if(!StringUtils.isNullOrEmpty(aform.getSearchtodate())){
            todate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchtodate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	
	
	%>
<br>
<table>
<tr>
<td><div id="toalrecords"></div> </td>

	 <td>
 <div class="bd">
			<div id="container"></div>
			| <a class="button" href="#" id="delete"><%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%></a> |
						
		</div>
</td>

 <td>
 <div class="bd">
			<div id="container"></div>
			<!--<button id="delete1">-->
			<a class="button" href="#" id="delete1"><%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%></a> |
			
		</div>

</td>

<td><a class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.configuare.columns",user1.getLocale())%></a></td>
</tr>
</table>

<br>

<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.APP_SEARCH_SCREEN_HIRING_MGR);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.APP_SEARCH_SCREEN_HIRING_MGR);

%>


<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&backtolisturl=applicant.do?method=searchownapplicantinitHiringMgr&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "'"+ ">" + sData + "</a>";
        };

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };

var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
		
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "','"+oRecord.getData("juuid")+"')"+ ">"  + sData + "</a>";
		elCell.innerHTML = editreqd;
        };

	var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("isofferownerGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("offerownerId")+ "'"+","+"'"+ oRecord.getData("isofferownerGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };

	var formatUrlForRecruiter = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("recruitergroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("recruitergroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("recruiterId")+ "'"+","+"'"+ oRecord.getData("recruitergroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };



var formatApplicantOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownernamegroup");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownergroupId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else if(oRecord.getData("isGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownername");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        
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
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                sendbulkresumeforscreening(checknumber);
				}
				
				this.render();
			};


	YAHOO.widget.DataTable.prototype.deleteRowsBy1 = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			        
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("uuid") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                markdeletebulk(checknumber);
				}
				
				this.render();
			};

	
	YAHOO.widget.DataTable.prototype.forAllRecords = function (fn,scope) {
		
		if (!Lang.isFunction(fn)) {return;}
		scope || (scope = this);
		for (var rs = this.getRecordSet(), l = rs.getLength(), i = 0; i < l; i++) {
			if (fn.call(scope, rs.getRecord(i), i) === false) {return;}
		}
	};

    // Column definitions
    var myColumnDefs = [
		{key:"Select",label:'<input type="checkbox" id="SelectAll"/>', width:"30", formatter:"checkbox"},
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"juuid", hidden:true},
		{key:"reqId", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
		    <%=strcolumncontent%>
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/searchownapplicantlistpageHiringMgr.jsp?keywords=<%=aform.getKeywords()%>&searchpagedisplay=<%=searchpagedisplay%>&prevorg=<%=aform.getPreviousOrganization()%>&fullname=<%=aform.getFullName()%>&reqid=<%=aform.getRequitionId()%>&interviewstate=<%=aform.getInterviewState()%>&email=<%=aform.getEmail()%>&fromdate=<%=fromdate%>&todate=<%=todate%>&appliedcri=<%=aform.getAppliedcri()%>&applicantNo=<%=aform.getApplicantNo()%>&tagsname=<%=aform.getTagName()%>&ddd="+(new Date).getTime()+"&");
	myDataSource.connMethodPost = true; 
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
           <%=strkeycontent%>
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

	myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.results",user1.getLocale())%> : ' + my_row_total;
   });

    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
	myDataTable.subscribe('checkboxClickEvent', function(oArgs) {
	   
		var elCheckbox = oArgs.target;
		var newValue = elCheckbox.checked;
		var record = this.getRecord(elCheckbox);
		var column = this.getColumn(elCheckbox);
		record.setData(column.key,newValue);
		
		var allChecked = true;
		this.forAllRecords(function (r) {
			 
			if (!r.getData('Select')) {
				allChecked = false;
				return false;
				 
			}
		});
		Dom.get('SelectAll').checked = allChecked;
	});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					
					for (var i=0;i < l; i++) {
						if(rs.getRecord(i) != null){
						rs.getRecord(i).setData('Select',check);
						}
					}
					this.render();
				}
			});
			


			
			Event.on('delete','click', function() {
				myDataTable.deleteRowsBy(function (data) {
					return data.Select;
				});
			});

		Event.on('delete1','click', function() {
				myDataTable.deleteRowsBy1(function (data) {
					return data.Select;
				});
			});
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<%//}%>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

<script type="text/javascript">
	YAHOO.example.BasicLocal = function() {
		// Use a LocalDataSource
		var oDS = new YAHOO.util.LocalDataSource(<%=datastring1%>);
		// Optional to define fields for single-dimensional array
		oDS.responseSchema = {fields : ["state"]};

		// Instantiate the AutoComplete
		var oAC = new YAHOO.widget.AutoComplete("myInput", "myContainer", oDS);
		oAC.prehighlightClassName = "yui-ac-prehighlight";
		oAC.useShadow = true;
		
		return {
			oDS: oDS,
			oAC: oAC
		};
	}();
	</script>

</body>

