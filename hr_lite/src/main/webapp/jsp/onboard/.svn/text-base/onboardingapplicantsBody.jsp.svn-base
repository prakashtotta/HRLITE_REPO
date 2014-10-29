
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
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
String noofrowsinpagination = (aform.getNoofrows()==null)?Constant.getValue("default.pagination.size"):aform.getNoofrows();
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
String savedsearch = (String)request.getAttribute("savedsearch");
String isNotStartedJoiningProces = "false";
	
 if(aform.getInterviewState() != null && aform.getInterviewState().equals(Common.OFFER_ACCEPTED)){
	 isNotStartedJoiningProces="true";
 }
%>
<script language="javascript">
this.reload_on_close=false;
var isNotStartedJoiningProces=<%=isNotStartedJoiningProces%>;
function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}
 function searchapplicant(){
	
document.applicantForm.action = "applicant.do?method=searchOnBoardingapplicants";
document.applicantForm.submit();
}


function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);
}

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


function initiateOnboarding(appIds){
	var url = "<%=request.getContextPath()%>/onboarding.do?method=initiateOnBoarding&applicantids="+appIds;
	//parent.showPopWin(url, 650, 600, showmessage,true);

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%>', url, messageret);

}
	 function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsOnBoardApplicants";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}


	function messageret(){
	//window.location.reload();
	//history.go(0);
var url = "<%=request.getContextPath()%>/applicant.do?method=onboardingapplicants";
	location.replace(url)

			}
function showmessage(returnval) { 
	window.location.reload();
	}





function exportapplicants(){
	var url = "<%=request.getContextPath()%>/applicant.do?method=exportapplicantsscr";
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Exportapplicants",user1.getLocale())%>', url, 600,700, messageret1);
}



function exporttoexcel(){
	
document.applicantForm.action = "applicant.do?method=exporttoexcelOnBoardSearch";
document.applicantForm.submit();
}

function messageret1(){
	
			} 

</script>

<script language="javascript">
function retrieveURLOrg(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.applicantForm.orgId.value;
	
  	$.ajax({
			type: 'GET',
		  url: url,
		  success: function(data){
	
		  $('#departments').html(data);

		  }
		});
	document.getElementById("loading").style.visibility = "hidden";

}







document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.applicantForm.search.click();",200);
}
}



function resetdata(){
   document.applicantForm.appliedcri.value="NoValue";
   document.applicantForm.searchfromdate.value="";
   document.applicantForm.searchtodate.value="";
   document.applicantForm.applicantNo.value="";
   document.applicantForm.fullName.value="";
   document.applicantForm.requitionId.value="";
   document.applicantForm.previousOrganization.value="";
   document.applicantForm.interviewState.value="NoValue";
   document.applicantForm.vendorId.value="";
   document.applicantForm.orgId.value="";
   document.applicantForm.departmentId.value="";
   document.applicantForm.onboardingProcessStatus.value=""; 
   document.applicantForm.tagId.value=""; 
 }  
</script>


 <% if(savedsearch != null && savedsearch.equals("yes")){%>
<div class="msg"><font color="white"><b><%=Constant.getResourceStringValue("aquisition.applicant.searchsavemsg",user1.getLocale())%></b></font></div><br>
 <%}%>

<body class="yui-skin-sam">

<html:form action="/applicant.do?method=searchapplicantbyvendor">


<div class="div" >

<table width="100%">
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
			<td><html:text property="applicantNo" size="30"/></td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%> </td>
		<td><html:text property="fullName" size="30" maxlength="600"/></td>
        </tr>

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Vendor",user1.getLocale())%></td>
			<td>
			<html:select  property="vendorId">
			<option value=""></option>
			<bean:define name="applicantForm" property="vendorList" id="vendorList" />

            <html:options collection="vendorList" property="userId"  labelProperty="lastName"/>
			</html:select>
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%></td>
		<td>
		<html:select  property="interviewState">
			<bean:define name="applicantForm" property="interviewstateList" id="interviewstateList" />
         
            <html:options collection="interviewstateList" property="interviewstateCode"  labelProperty="interviewstateName"/>
			</html:select>
		</td>

		</tr>
		
		 <tr>
          <td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
			<html:select property="orgId" onchange="retrieveURLOrg('lov.do?method=loadDepartmentsWithOutProjectCodeAndSpace');">
			<option value=""></option>
			<bean:define name="applicantForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%> ......</span>

			  
			</td>

			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="departmentId">
			<option value=""></option>
			<bean:define name="applicantForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
			
			</td>
		
		 </tr>
		
		 <tr>
          <td><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.initiateJoiningProcess",user1.getLocale())%></td>
			<td>
			<html:select property="onboardingProcessStatus">
			<option value=""></option>
			<bean:define name="applicantForm" property="onboardingProcessStatusList" id="onboardingProcessStatusList" />

            <html:options collection="onboardingProcessStatusList" property="key"  labelProperty="value"/>
			</html:select>
					  
			</td>

					<td><%=Constant.getResourceStringValue("aquisition.applicant.tag",user1.getLocale())%></td>
			<td>
			<html:select  property="tagName" >
			<option value=""></option>
			<bean:define name="applicantForm" property="tagList" id="tagList" />
            <html:options collection="tagList" property="tagName"  labelProperty="tagName"/>
			</html:select>
			</td>
		
		 </tr>
		 <tr>
		
		<td>	
		<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%>
		</td>
		<td>
			<html:select  property="requitionId">
			<option value=""></option>
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />

            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
		</td>
		<td></td>
		<td></td>
		 </tr>

		

</table>
<br>
<div  class="msg"><a href="#" rel="toggle[workpreferenceappdetails]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Date criteria  </div><br>

<table border="0" width="80%">
		
		<tr>
			<td>
				Date type :		<html:select  property="appliedcri">
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

<INPUT TYPE="text" NAME="searchfromdate" readonly="true" value="<%=(aform.getSearchfromdate() == null)? "" :aform.getSearchfromdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.searchfromdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.searchfromdate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

To date :
<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx2">
var cal1xx2 = new CalendarPopup("testdiv2");
cal1xx2.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="searchtodate" readonly="true" value="<%=(aform.getSearchtodate() == null)? "" :aform.getSearchtodate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx2.select(document.applicantForm.searchtodate,'anchor1xx2','<%=datepattern%>'); return false;" TITLE="cal1xx2.select(document.applicantForm.searchtodate,'anchor1xx2','<%=datepattern%>'); return false;" NAME="anchor1xx2" ID="anchor1xx2"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv2" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
			

		
		</tr>

</table>

<table>
<tr>
<td>

 No of rows :
 			<html:select  property="noofrows">
			<bean:define name="applicantForm" property="paginationRowsList" id="paginationRowsList" />
            <html:options collection="paginationRowsList" property="key"  labelProperty="value"/>
			</html:select>

</td>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchapplicant()" class="button">
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onclick="resetdata()" class="button"/>
<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.exporttoexcel",user1.getLocale())%>" onClick="exporttoexcel()" class="button">
</td>
<td></td>
<td>

</td>
</tr>
</table>
</div>
</html:form>

<% 
//if(searchpagedisplay != null && searchpagedisplay.equals("yes"))
//{
	
	String fromdate = null;
	String todate = null;
	if(!StringUtils.isNullOrEmpty(aform.getSearchfromdate())){
            fromdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchfromdate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	if(!StringUtils.isNullOrEmpty(aform.getSearchtodate())){
            todate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchtodate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	
	
	
	%>

<%
String exporttoexcel = (String)request.getAttribute("exporttoexcel");
%>

<%if(exporttoexcel != null && exporttoexcel.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.export.job.started",user1.getLocale())%> </font><a href="bulkuploadtask.do?method=mybulktasklist"><font color="white"><%=Constant.getResourceStringValue("aquisition.process.job.page",user1.getLocale())%></a></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.ON_BOARDING_SEARCH_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.ON_BOARDING_SEARCH_SCREEN);

%>

<br>

<b><%=Constant.getResourceStringValue("aquisition.applicant.list",user1.getLocale())%></b> <br><br>

<%
	
	
 if(isNotStartedJoiningProces != null && isNotStartedJoiningProces.equals("true")){
	


	 %>
 <div class="bd">
			<div id="container"></div>
			<button id="delete" class="button"><%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%></button>
			
		</div>
<%}%>



<!--<a  href="#" onClick="javascript:exportapplicants()"><%=Constant.getResourceStringValue("aquisition.applicant.Exportapplicants",user1.getLocale())%></a>&nbsp;&nbsp;--><a  class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a><br>
<br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };

   
	var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
		
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
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
					if(recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                initiateOnboarding(checknumber);
				}
				
				this.render();
			};

	
	// Column definitions

    var myColumnDefs = [
		{key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
			{key:"applicantId", hidden:true},
			{key:"uuid", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl,width:180},
             <%=strcolumncontent%>
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%> ", sortable:false, resizeable:true,formatter:editUrl}
        ];


    if(isNotStartedJoiningProces == false){

myColumnDefs = [
{key:"applicantId", hidden:true},
	{key:"uuid", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl,width:180},
             <%=strcolumncontent%>
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
        ];

	}


    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/onboard/onboardingapplicantslistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&prevorg=<%=aform.getPreviousOrganization()%>&email=<%=aform.getEmail()%>&interviewstate=<%=aform.getInterviewState()%>&fullname=<%=aform.getFullName()%>&vendorid=<%=aform.getVendorId()%>&reqid=<%=aform.getRequitionId()%>&fromdate=<%=fromdate%>&todate=<%=todate%>&appliedcri=<%=aform.getAppliedcri()%>&applicantNo=<%=aform.getApplicantNo()%>&orgId=<%=aform.getOrgId()%>&departmentId=<%=aform.getDepartmentId()%>&projectcodeId=<%=aform.getProjectcodeId()%>&onboardpstatus=<%=aform.getOnboardingProcessStatus()%>&tagName=<%=aform.getTagName()%>&ddd="+(new Date).getTime()+"&");
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
    var noofrowspagin = '<%=noofrowsinpagination%>';

    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results="+noofrowspagin, // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:noofrowspagin }) // Enables pagination 
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



</body>

