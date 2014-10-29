	<%@ include file="../common/include.jsp" %>
	<%@ include file="../common/greybox.jsp" %>
	<%@ include file="../common/autocomplete.jsp" %>
	<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="sform" name="taskForm" type="com.form.TaskForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>


	
	<br>

	<style type="text/css">
	#myAutoComplete {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}
	#myAutoComplete1 {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}

  
	</style>
<style>
span1{color:#ff0000;}
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

<script type="text/javascript">
var ccids="";
var ccnames="";


String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}
function strStartsWith(str, prefix) { 
	return str.indexOf(prefix) === 0;
	}

$(function() {

	$("#assignedtoUserName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
			minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
		     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		  
		document.taskForm.assignedtoUserId.value=item.data;
		document.taskForm.assignedtoUserNamehidden.value=item.value;
		//alert(item.data);
		}
		});




});

	</script>


	<script language="javascript">


function validateUser(){
	//alert(document.scheduleInterviewForm.reviewerid.value);
	document.taskForm.assignedtoUserName.value=document.taskForm.assignedtoUserNamehidden.value;
}
	function closewindow(){
		parent.parent.GB_hide();
 
	}



	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		  parent.parent.GB_hide();
		   } 
		}

	function savedata(){

		var alertstr = "";
    var date = document.taskForm.date.value;
	var assignedtoUserId = document.taskForm.assignedtoUserId.value;
	var taskname = document.taskForm.taskname.value;
	var notes = document.taskForm.notes.value;

	var showalert=false;

	if(date == "" || date == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Date_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

	if(assignedtoUserId == "" || assignedtoUserId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.add.task.assign.to.mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

	if(taskname == "" || taskname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.add.task.assign.name.mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

	if(notes != "" && notes != null){
		if(notes.length >600){
			alertstr = alertstr + "Details not more than 600 chars<br>";
			showalert = true;
		}
     	
		
		}
	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
		
		  document.taskForm.action = "task.do?method=addtask&assignedtoUserId="+document.taskForm.assignedtoUserId.value;
	  document.taskForm.submit();
	  
	   
		}
	function updatedata(){
	
       document.taskForm.action = "task.do?method=updatetask&tasdkid=<%=sform.getTaskId()%>";
	  document.taskForm.submit();
	  
	   
		}



	</script>



<%
String addtask = (String)request.getAttribute("addtask");



	
if(addtask != null && addtask.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.add.task.assign.task.successfull",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>
<!--<body class="yui-skin-sam" onload='top.location.reload(true);'>-->
<%}else {%>

	<body class="yui-skin-sam">
	<html:form action="/task.do?method=addtask">

	<div align="center" class="div">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
		

	 <tr>
		<% if(sform.getTaskId()>0){%>
		<td><%=Constant.getResourceStringValue("hr.add.task.assign.to",user1.getLocale())%>:</td>
		<td>
		<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=sform.getAssignedtoUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=sform.getAssignedtoUserName()%></a> 
		</td>

		<%}else{%>
		<td><%=Constant.getResourceStringValue("hr.add.task.assign.to",user1.getLocale())%><font color="red">*</font>:</td>

				<td>
                <input type="hidden" name="assignedtoUserNamehidden">
                 <input type="text" id="assignedtoUserName" name="assignedtoUserName" autocomplete="off"   onblur="validateUser()">

<input type="hidden" name="assignedtoUserId" />
<br>
<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
				</td>
			<%}%>

			</tr>

<% if(sform.getTaskId()>0){%>
 <tr>
<td>Assigned by: </td>
<td>
		<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=sform.getAssignedbyUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=sform.getAssignedbyUserName()%></a> 
		</td>
 </tr>
<%}%>


	 <tr>
		<% if(sform.getTaskId()>0){%>
		<td><%=Constant.getResourceStringValue("hr.add.task.assign.task.name",user1.getLocale())%>:</td>
		<td>
		<%=sform.getTaskname()%>
		</td>
			<%}else{%>
			<td><%=Constant.getResourceStringValue("hr.add.task.assign.task.name",user1.getLocale())%><font color="red">*</font>:</td>
				<td>
                <html:text property="taskname" size="50" maxlength="200"/>

				</td>
			<%}%>
			</tr>


              <tr>
				<% if(sform.getTaskId()>0){%>
				<td>Due Date:</td>
				<td>
		<%=sform.getEventdate()%>
		</td>
			<%}else{%>
		<td>Due Date<font color="red">*</font>:</td>
      <td>
	  <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	 <INPUT TYPE="text" NAME="date" readonly="true" value="" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.taskForm.date,'anchor1xx','<%=datepattern%>'); return false;" TITLE="select date" NAME="anchor1xx" ID="anchor1xx">
<img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

    </td>
	<%}%>
	  </tr>



			<tr>
				<td>Details:</td>
				<td><html:textarea property="notes" cols="60" rows="4"/></td>
			</tr>

<% if(sform.getTaskId()>0 && sform.getStatus() != null && sform.getStatus().equals("A")){%>
			<tr>
				<td>Status:</td>
				<td><input type="checkbox" name="status" /> Mark as completed </td>
			</tr>
<%}%>

 

			<tr>
				<td>
				
				<% if(sform.getTaskId()>0){%>
				<% if(sform.getTaskId()>0){%>
					<input type="button" name="login" value="Update" onClick="updatedata()" class="button">
					<%}%>
				<%}else{%>
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.add.task.assign.task",user1.getLocale())%>" onClick="savedata()" class="button">

				<%}%>

			</tr>

		</table>
	</div>

	</html:form>


	<%}%>





	</body>