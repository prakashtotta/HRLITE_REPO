<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<html>

 <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<style>
span1{color:#ff0000;}
</style>
  

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
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
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</STYLE>

 </HEAD>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

  <%
 String datepattern = Constant.getValue("defaultdateformat");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);

String userName=user1.getEmployeecode();

if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
 
%>

<script type="text/javascript">


$(function() {

	//$("#reviewername").autocomplete('jsp/talent/getUserData.jsp');
	$("#fullName").autocomplete({
		url: 'jsp/refferal/getReferralApplicantNames.jsp'
		
	     
		
});
	$("#previousOrganization").autocomplete({
		url: 'jsp/refferal/getCurrentOrganization.jsp'
	     
		
});
});
</script>
<script language="javascript">



function editapplicant(aid,uuid){
	var url = "<%=request.getContextPath()%>/applicantoffer.do?method=editapplicantforRefferal&applicantId="+aid+"&uuId="+uuid;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
}
function messageret(){
	window.location.reload();
}
 function searchapplicant(){
	
document.applicantForm.action = "refops.do?method=searchownapplicant";
document.applicantForm.submit();
}
function resetdata(){
	
document.applicantForm.applieddate.value="";
document.applicantForm.applicantNo.value="";
document.applicantForm.fullName.value="";
document.applicantForm.requitionId.value="";
document.applicantForm.email.value="";
document.applicantForm.previousOrganization.value="";

}

function init(){

document.applicantForm.applicantNo.focus();

}
</script>
<body class="yui-skin-sam" onLoad="init()">
<html:form action="/applicant.do?method=searchownapplicant">
<div class="div">
<table border="0" width="100%">
		
	<tr>
			<td>Applied Date</td>
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

<INPUT TYPE="text" class="textdynamic" NAME="applieddate" readonly="true" value="<%=(aform.getSearchapplieddate() == null)? "" :aform.getSearchapplieddate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.applieddate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.applieddate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.no",user1.getLocale())%></td>
			<td><html:text property="applicantNo" size="30" styleClass="textdynamic"/></td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
			<td><html:text property="fullName" styleClass="textdynamic" styleId="fullName" size="30" maxlength="600"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_organization",user1.getLocale())%></td>

			<td><html:text property="previousOrganization" styleClass="textdynamic" styleId="previousOrganization" size="30" maxlength="600"/></td>
<!--  
			<td><label for="myInput"></label>
	<div id="myAutoComplete">
		<input id="myInput" name="previousOrganization" value="<%=aform.getPreviousOrganization()%>" type="text">
		
		<div id="myContainer"></div>
	</div>
-->
	</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
			<td><html:select  property="requitionId" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />

            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
			<td><html:text property="email" size="30" styleClass="textdynamic"/></td>
		
		</tr>
		
<tr>
<td>
<input type="button" name="search" value="Search" onClick="searchapplicant()" class="button">
<input type="button" name="search1" value="Reset" onClick="resetdata()" class="button">
</td>
<td></td>
</tr>
</table>
</div>
</html:form>

<%
	//if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
	
	String appdate = null;
	if(user1 != null){ 
	if(!StringUtils.isNullOrEmpty(aform.getSearchapplieddate())){
            appdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchapplieddate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	}else{
		appdate = aform.getSearchapplieddate();
	}
	
	%>


<br><br>

<b>Applicant List</b> <br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='refops.do?method=applicantDetails&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };
	
	var editUrl = function(elCell, oRecord, oColumn, sData) {
		if( oRecord.getData("createdBy") == '<%=user1.getEmployeename()%>'){
	 		elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "','"+oRecord.getData("uuid")+"')"+ ">" + "edit" + "</a>";
		}
        };
   
    var editUrl2 = function(elCell, oRecord, oColumn, sData) {
      
       	elCell.innerHTML = "<a href='#' onClick=window.open("+"'"+"refjob.do?method=jobdetailsforReferral&uuid="+oRecord.getData("uuid")+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=800"+"'"+")>"+oRecord.getData("jobTitle")+"</a>";  
               };
    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"reqId", hidden:true},
            {key:"fullName", label:"Name", sortable:true, resizeable:true,formatter:formatUrl,width:180},
             {key:"email", label:"Email",sortable:true, resizeable:true},
		{key:"phone", label:"Phone",sortable:true, resizeable:true},
		{key:"heighestQualification", label:"Qualification", sortable:true, resizeable:true},
		{key:"interviewState", label:"Status",sortable:true, resizeable:true,width:150},
		{key:"appliedDate", label:"Applied Date",sortable:true, resizeable:true,width:135},
		{key:"jobTitle", label:"Applied For",sortable:true, resizeable:true,width:200,formatter:editUrl2},
		{key:"referrerName",hidden:true,label:"Referrer Name",sortable:true, resizeable:true},
		{key:"createdBy", label:"Added by",sortable:true, resizeable:true},
		{key:"ownername", label:"Owner", resizeable:true},
		{key:"edit", label:"Edit", resizeable:true,formatter:editUrl}
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/searchownapplicantlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&prevorg=<%=aform.getPreviousOrganization()%>&fullname=<%=aform.getFullName()%>&reqid=<%=aform.getRequitionId()%>&email=<%=aform.getEmail()%>&appdate=<%=appdate%>&cri=<%=aform.getAppliedcri()%>&applicantNo=<%=aform.getApplicantNo()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},           
		   	{key:"email"},
		{key:"phone"},
		{key:"heighestQualification"},
		{key:"interviewState"},
		{key:"appliedDate"},
		{key:"jobTitle"},
		{key:"referrerName"},
		{key:"ownername"},
		{key:"uuid"},
		{key:"reqId"},
			{key:"createdBy"},
		{key:"edit"}
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

