<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
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

 

<bean:define id="aform" name="bulkUploadTaskForm" type="com.form.BulkUploadTaskForm" />
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>

<script language="javascript">



function taskdetails(idvalue){
    var url = "<%=request.getContextPath()%>/bulkuploadtask.do?method=bulkloadtaskdetails&taskid="+idvalue;
 
	//GB_showFullScreen('Task details',url, messageret);

	GB_showCenter('<%=Constant.getResourceStringValue("task.graybox.taskdetails",user1.getLocale())%>',url,500,750, messageret);
	//parent.setPopTitle1("Create applicant");
	//parent.showPopWin(url, 700, 600, null,true);


}

function messageret(){
	//window.location.reload();

			}
 function searchtasks(){
	
document.bulkUploadTaskForm.action = "bulkuploadtask.do?method=searcjbulktasks";
document.bulkUploadTaskForm.submit();
}

function validateUserhm(){

	document.bulkUploadTaskForm.createdBy.value=document.bulkUploadTaskForm.primaryownernamehidden.value;

}

function resetdata()
{
document.bulkUploadTaskForm.cri.value="";
document.bulkUploadTaskForm.assigneddate.value="";
document.bulkUploadTaskForm.createdBy.value="";
document.bulkUploadTaskForm.tasktype.value="NoValue";
document.bulkUploadTaskForm.status.value="NoValue";
}
document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.bulkUploadTaskForm.search.click();",200);
}
}
</script>
<script type="text/javascript">
	
$(function() {

	$("#createdBy").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	    minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.bulkUploadTaskForm.createdbyid.value=+item.data;
		document.bulkUploadTaskForm.primaryownernamehidden.value=item.value;
		//alert(item.data);
		//alert(item.value);
		}
		});

     

});



	</script>
<br>
<b><%=Constant.getResourceStringValue("task.exportuploadtasklist.pagename",user1.getLocale())%> </b> <br>

<body class="yui-skin-sam">

<html:form action="/bulkuploadtask.do?method=searcjbulktasks">

<table border="0" width="100%" class="div">
		
	<tr>
			<td><%=Constant.getResourceStringValue("task.assignedon",user1.getLocale())%></td>
			<td>
			<select name="cri">

			  <%if(!StringUtils.isNullOrEmpty(aform.getAssignedcri()) && aform.getAssignedcri().equals("before")){%>
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
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="assigneddate" readonly="true" value="<%=aform.getSearchassigneddate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.bulkUploadTaskForm.assigneddate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.bulkUploadTaskForm.assigneddate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
			

			<td><%=Constant.getResourceStringValue("task.exportuploadtasklist.taskaddedby",user1.getLocale())%></td>
			
			<td>
				<input type="hidden" name="primaryownernamehidden" value='<%=(aform.getCreatedBy()==null)?"":aform.getCreatedBy()%>'/>
				<input type="text"  id="createdBy" name="createdBy"  autocomplete="off" value="<%=(aform.getCreatedBy()==null)?"":aform.getCreatedBy()%>" onblur="validateUserhm()" >
				<span id="createdbyid"></span>
				<%
                String createdbyid = String.valueOf(aform.getCreatedbyid());
                 %>
                  <html:hidden  property="createdbyid" value="<%=createdbyid%>"/>


				</td>
		
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("task.tasktype",user1.getLocale())%></td>
			<td>
			<html:select  property="tasktype">
			<bean:define name="bulkUploadTaskForm" property="tasktypeList" id="tasktypeList" />

            <html:options collection="tasktypeList" property="key"  labelProperty="value"/>
			</html:select>
			</td>

			<td><%=Constant.getResourceStringValue("task.status",user1.getLocale())%></td>
			<td>
			<html:select  property="status">
			<bean:define name="bulkUploadTaskForm" property="statusList" id="statusList" />

            <html:options collection="statusList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
	
		</tr>
		


		
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchtasks()" class="button">
<input type="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button" />
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
	 elCell.innerHTML = "<a href='#' onClick=taskdetails(" +"'"+ oRecord.getData("bulkuploadtaskid")+ "'" + ")"+ ">" + oRecord.getData("bulkuploadtaskid") + "</a>";
         
        };

    // Column definitions
    var myColumnDefs = [
		   {key:"bulkuploadtaskid", label:"<%=Constant.getResourceStringValue("task.bulktaskid",user1.getLocale())%>", sortable:true, resizeable:true,formatter:editUrl, width:100},
             {key:"tasktype", label:"<%=Constant.getResourceStringValue("task.tasktype",user1.getLocale())%>",sortable:true, resizeable:true,width:200},
			{key:"createdBy", label:"<%=Constant.getResourceStringValue("task.addedby",user1.getLocale())%>",sortable:true, resizeable:true,width:100},
			   {key:"createdDate", label:"<%=Constant.getResourceStringValue("task.addedon",user1.getLocale())%>",sortable:true, resizeable:true,width:100},
		{key:"uploadedFileName", label:"<%=Constant.getResourceStringValue("task.uploadfilename",user1.getLocale())%>",sortable:true, resizeable:true,width:200},
		{key:"filesList",label:"<%=Constant.getResourceStringValue("task.filelist",user1.getLocale())%>", sortable:true, resizeable:true, width:300},
		{key:"updatedDate", label:"<%=Constant.getResourceStringValue("task.completedon",user1.getLocale())%>",sortable:true, resizeable:true,width:100},
		{key:"status", label:"<%=Constant.getResourceStringValue("task.status",user1.getLocale())%>",sortable:true, resizeable:true,width:100}

			
           
        ];




    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/task/bulkuploadaskslistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&cri=<%=aform.getAssignedcri()%>&assigneddate=<%=assigneddate%>&createdBy=<%=aform.getCreatedbyid()%>&tasktype=<%=aform.getTasktype()%>&status=<%=aform.getStatus()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"bulkuploadtaskid"},
            {key:"tasktype"},
            {key:"createdBy"},
            {key:"uploadedFileName"},
			{key:"status"},
			{key:"filesList"},
			{key:"successfilesList"},
		{key:"failfilesList"},
		{key:"createdDate"},
		{key:"updatedDate"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=bulkuploadtaskid&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"bulkuploadtaskid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
