<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>

<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ include file="../common/autocomplete.jsp" %>

  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String assigneddate = null;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
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



<bean:define id="aform" name="taskForm" type="com.form.TaskForm" />
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>

<script language="javascript">



function taskdetails(idvalue,uuid,tasktype){
   // alert(idvalue);
 
	var url = "";
	//parent.setPopTitle1("Create applicant");
	//parent.showPopWin(url, 700, 600, null,true);

var ty = unescape(tasktype);
if(ty == '<%=Common.APPLICANT_IN_QUEUE%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=pendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.OFFER_APPROVAL_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=pendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.APPLICANT_REVIEW_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=pendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.OFFER_RELEASE_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=pendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.REFERENCE_CHECK_TASK%>'){
location.href="applicant.do?method=applicantDetails&backtolisturl=task.do?method=pendingtasks&applicantId="+idvalue+"&secureid="+uuid;
}
if(ty == '<%=Common.REQUISTION_APPROVAL_TASK%>'){
var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+idvalue;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%>', url, messageret);
}
if(ty == '<%=Common.REQUISTION_PUBLISH_TASK%>'){
var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+idvalue;
    //parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%>', url, messageret);
}
}

function messageret(){
	//window.location.reload();

			}
 function searchtasks(){
	
document.taskForm.action = "task.do?method=searchpendingtasks";
document.taskForm.submit();
}
document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.taskForm.search.click();",200);
}
}

function validateassignedbyUserName(){
document.taskForm.assignedbyUserName.value=document.taskForm.assignedbyUserNamehidden.value;
}
function validateAssignedtoUserName(){
document.taskForm.assignedtoUserName.value=document.taskForm.asigndtohidden.value;
}
function validatereassigndto(){
document.taskForm.updatedBy1.value=document.taskForm.reassigndtohidden.value;
}


function resetdata(){
document.taskForm.tasktype.value="NoValue";
document.taskForm.assigneddate.value="";
document.taskForm.assignedbyUserName.value="";
document.taskForm.assignedtoUserName.value="";
document.taskForm.updatedBy.value="";
document.taskForm.assignedbyUserId.value="";
document.taskForm.assignedtoUserId.value="";
document.taskForm.updatedBy1.value="";
document.taskForm.reassigndtohidden.value="";


  
}
</script>
<br>
<b><%=Constant.getResourceStringValue("task.pendingtasks.pendingtasklist",user1.getLocale())%></b> <br>

<body class="yui-skin-sam" onkeypress="onkeypress()">

<html:form action="/task.do?method=searchpendingtasks">

<table border="0" width="100%" class="div">
		
	<tr>
			<td><%=Constant.getResourceStringValue("task.assignedon",user1.getLocale())%></td>
			<td>
			<select name="cri">
			<% if(!StringUtils.isNullOrEmpty(aform.getAssignedcri()) && aform.getAssignedcri().equals("before")){%>
  <option value="before" selected="yes" ><%=Constant.getResourceStringValue("task.before",user1.getLocale())%></option>
  <%}else {%>
<option value="before"><%=Constant.getResourceStringValue("task.before",user1.getLocale())%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAssignedcri()) && aform.getAssignedcri().equals("on")){%>
  <option value="on" selected="yes" ><%=Constant.getResourceStringValue("task.on",user1.getLocale())%></option>
  <%}else {%>
<option value="on"><%=Constant.getResourceStringValue("task.on",user1.getLocale())%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAssignedcri()) && aform.getAssignedcri().equals("after")){%>
  <option value="after" selected="yes" ><%=Constant.getResourceStringValue("task.after",user1.getLocale())%></option>
  <%}else {%>
<option value="after"><%=Constant.getResourceStringValue("task.after",user1.getLocale())%></option>
  <%}%>
 
</select>


<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<script type="text/javascript">
	
$(function() {

	$("#assignedbyUserName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.taskForm.assignedbyUserId.value=+item.data;
		document.taskForm.assignedbyUserNamehidden.value=item.value;
		}
		});

		$("#assignedtoUserName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.taskForm.assignedtoUserId.value=+item.data;
		document.taskForm.asigndtohidden.value=item.value;
		}
		});

		$("#updatedBy1").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.taskForm.updatedBy.value=item.value;
		document.taskForm.reassigndtohidden.value=item.value;
		}
		});

});
</script>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="assigneddate" readonly="true" value="<%=aform.getSearchassigneddate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.taskForm.assigneddate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.taskForm.assigneddate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>          
            <td><%=Constant.getResourceStringValue("task.assignedby",user1.getLocale())%></td>	
			<td>
		         <input type="hidden" id="assignedbyUserNamehidden" name="assignedbyUserNamehidden" value='<%=(aform.getAssignedbyUserName()==null)?"":aform.getAssignedbyUserName()%>'/>
				<input type="text"  id="assignedbyUserName" name="assignedbyUserName"  autocomplete="off" value="<%=(aform.getAssignedbyUserName()==null)?"":aform.getAssignedbyUserName()%>" onblur="validateassignedbyUserName()" >
				<span id="assignedbyUserId"></span>
				<%
                String assignedbyuserId = String.valueOf(aform.getAssignedbyUserId());
                 %>
                  <html:hidden  property="assignedbyUserId" value="<%=assignedbyuserId%>"/>
			</td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("task.tasktype",user1.getLocale())%></td>
			<td>
			<html:select  property="tasktype">
			<bean:define name="taskForm" property="tasktypeList" id="tasktypeList" />

            <html:options collection="tasktypeList" property="key"  labelProperty="value"/>
			</html:select>
			</td>


             <td><%=Constant.getResourceStringValue("task.assignedto",user1.getLocale())%></td>	
			<td>
		         <input type="hidden" id="asigndtohidden" name="asigndtohidden" value='<%=(aform.getAssignedtoUserName()==null)?"":aform.getAssignedtoUserName()%>'/>
				<input type="text"  id="assignedtoUserName" name="assignedtoUserName"  autocomplete="off" value="<%=(aform.getAssignedtoUserName()==null)?"":aform.getAssignedtoUserName()%>" onblur="validateAssignedtoUserName()" >
				<span id="assignedtoUserId"></span>
				<%
                String assignedtouserId = String.valueOf(aform.getAssignedtoUserId());
                 %>
                  <html:hidden  property="assignedtoUserId" value="<%=assignedtouserId%>"/>
			</td>
           
		</tr>
		<tr>
			

             <td><%=Constant.getResourceStringValue("task.reassignedby",user1.getLocale())%></td>	
			 <td>
		         <input type="hidden" id="reassigndtohidden" name="reassigndtohidden" value='<%=(aform.getUpdatedBy()==null)?"":aform.getUpdatedBy()%>'/>
				<input type="text"  id="updatedBy1" name="updatedBy1"  autocomplete="off" value='<%=(aform.getUpdatedBy()==null)?"":aform.getUpdatedBy()%>' onblur="validatereassigndto()" >
				<span id="updatedBy"></span>
				<%
                String reassignedupdatedBy = String.valueOf((aform.getUpdatedBy()==null)?"":aform.getUpdatedBy());
                 %>
                  <html:hidden  property="updatedBy" value="<%=reassignedupdatedBy%>"/>
			 </td>




			
	<td></td>
			<td>
			
			</td>
			</tr>

		
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchtasks()" class="button">
<input type="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button"/>
</td>
<td>

</td>
</tr>
</table>

</html:form>

<% if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
	
	
	if(!StringUtils.isNullOrEmpty(aform.getSearchassigneddate())){
            assigneddate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchassigneddate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}

	
}
	%>

<br>





<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {


var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=taskdetails(" +"'"+ oRecord.getData("idvalue")+ "'"+","+"'"+ oRecord.getData("uuid")+ "'"+","+ "'"+escape(oRecord.getData("tasktype"))+"'"+ ")"+ ">" + oRecord.getData("taskname") + "</a>";
         
        };


    // Column definitions
    var myColumnDefs = [
			{key:"taskId", hidden:true},
		{key:"uuid", hidden:true},
			{key:"idvalue", hidden:true},
           {key:"taskname", label:"<%=Constant.getResourceStringValue("task.taskname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:editUrl},
             {key:"tasktype", label:"<%=Constant.getResourceStringValue("task.tasktype",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"assignedbyUserName", label:"<%=Constant.getResourceStringValue("task.assignedby",user1.getLocale())%>",sortable:true, resizeable:true},
			   {key:"createdDate", label:"<%=Constant.getResourceStringValue("task.assignedon",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"assignedtoUserName", label:"<%=Constant.getResourceStringValue("task.assignedto",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"updatedBy",label:"<%=Constant.getResourceStringValue("task.reassignedby",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"updatedDate", label:"<%=Constant.getResourceStringValue("task.reassignedon",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"status", label:"<%=Constant.getResourceStringValue("task.status",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"assignedtoUserName1", label:"<%=Constant.getResourceStringValue("task.taskowner",user1.getLocale())%>", sortable:false ,resizeable:true}
			
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/task/pendingtaskslistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&cri=<%=aform.getAssignedcri()%>&assigneddate=<%=assigneddate%>&assignedbyUserId=<%=aform.getAssignedbyUserId()%>&assignedtoUserId=<%=aform.getAssignedtoUserId()%>&tasktype=<%=aform.getTasktype()%>&updatedBy=<%=aform.getUpdatedBy()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"taskId"},
            {key:"idvalue"},
            {key:"tasktype"},
            {key:"assignedbyUserName"},
			{key:"assignedtoUserName"},
			{key:"assignedtoUserName1"},
			{key:"createdDate"},
		{key:"updatedBy"},
		{key:"updatedDate"},
		{key:"status"},
			{key:"taskname"},
			{key:"uuid"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=taskId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"taskId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>
