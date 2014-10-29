<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
  <%
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
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
 
%>

<script language="javascript">

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


	 function searchapplicant(){
	
document.applicantForm.action = "applicant.do?method=searchownapplicant";
document.applicantForm.submit();
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
	
	function messageret(){
	//window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			}

</script>
<script language="javascript">





function resetData(){


  //document.applicantForm.cri.value="";
  document.applicantForm.applicantNo.value="";
  document.applicantForm.fullName.value="";
  document.applicantForm.email.value="";
  document.applicantForm.interviewState.value="";

  document.applicantForm.previousOrganization.value="";
 
  
   
   
    
 }  
</script>
<br>
 <b><%=Constant.getResourceStringValue("aquisition.applicant.Search.My.Applicants",user1.getLocale())%>  </b><br>
<body class="yui-skin-sam">

<html:form action="/applicant.do?method=searchownapplicant">

<table border="0" width="100%">
		
	        <tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
			<td><html:text property="applicantNo" size="30"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
			<td><html:text property="fullName" size="30" maxlength="600"/></td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
			<td><html:text property="email" size="30"/></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_organization",user1.getLocale())%></td>
			<td><label for="myInput"></label>
	<div id="myAutoComplete">
		<input id="myInput" name="previousOrganization" value="<%=aform.getPreviousOrganization()%>" type="text">
		
		<div id="myContainer"></div>
	</div>
	</td>
		</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%></td>
			<td>
			<html:select  property="interviewState">
			<option value=""></option>
			<bean:define name="applicantForm" property="interviewstateList" id="interviewstateList" />

            <html:options collection="interviewstateList" property="interviewstateCode"  labelProperty="interviewstateName"/>
			</html:select>
			</td>
			<td></td>
			<td></td>
		
		</tr>
		
<tr>
<td>
<input type="submit" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchapplicant()">
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()">
</td>
<td></td>
</tr>
</table>

</html:form>

<%
	//if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
	
	String appdate = null;
	if(!StringUtils.isNullOrEmpty(aform.getSearchapplieddate())){
            appdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchapplieddate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	
	
	%>


<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&backtolisturl=applicant.do?method=searchownapplicantinit&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "'"+ ">" + sData + "</a>";
        };

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
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
    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl,width:180},
             {key:"email", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"phone", label:"<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"heighestQualification", label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"referrerName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"createdBy", label:"<%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", formatter:formatApplicantOwner, resizeable:true},
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/searchownapplicantlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&prevorg=<%=aform.getPreviousOrganization()%>&fullname=<%=aform.getFullName()%>&reqid=<%=aform.getRequitionId()%>&interviewstate=<%=aform.getInterviewState()%>&email=<%=aform.getEmail()%>&appdate=<%=appdate%>&cri=<%=aform.getAppliedcri()%>&applicantNo=<%=aform.getApplicantNo()%>&ddd="+(new Date).getTime()+"&");
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
			{key:"createdBy"},
			{key:"ownerId"},
			{key:"ownergroupId"},
			{key:"ownernamegroup"},
			{key:"isGroup"},
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
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

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

