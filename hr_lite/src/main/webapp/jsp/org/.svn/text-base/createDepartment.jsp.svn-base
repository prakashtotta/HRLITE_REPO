<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
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
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
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

 </HEAD>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<bean:define id="departmentform" name="departmentForm" type="com.form.DepartmentForm" />



<script language="javascript">


function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	    parent.parent.GB_hide();
	   } 
	}


	function closewindow(){
		parent.parent.GB_hide();
	
}
function init(){
setTimeout ( "document.departmentForm.departmentCode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){
	var alertstr = "";
	var showalert=false;
		
	var code = document.departmentForm.departmentCode.value.trim();
	var name=document.departmentForm.departmentName.value.trim();
	 
	
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Dept_code_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Dept_name_required",user1.getLocale())%><br>";
		showalert = true;
		}	
		if(document.departmentForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.departmentForm.action = "dept.do?method=saveDepartment";
 document.departmentForm.submit();


 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
		
	var code = document.departmentForm.departmentCode.value.trim();
	var name=document.departmentForm.departmentName.value.trim();
	 
	
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Dept_code_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Dept_name_required",user1.getLocale())%><br>";
		showalert = true;
		}	
		if(document.departmentForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.departmentForm.action = "dept.do?method=updateDepartment&departmentId="+'<bean:write name="departmentForm" property="departmentId"/>';
 document.departmentForm.submit();
 
	}

function deletedata(){
	
	  document.departmentForm.action = "dept.do?method=deleteDepartment&departmentId="+'<bean:write name="departmentForm" property="departmentId"/>';
 document.departmentForm.submit();
 
	}



</script>

<body class="yui-skin-sam" onload="init()" >

<%
String saveddepartment = (String)request.getAttribute("saveddepartment");
String deleteddepartment = (String)request.getAttribute("deleteddepartment");

if(saveddepartment != null && saveddepartment.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Deparment.savemesssage",user1.getLocale())%></font></td>
			<td> <!-- <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
if(deleteddepartment != null && deleteddepartment.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Deparment.deletemesssage",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<br><br>
<%}else{%>

<html:form action="/dept.do?method=saveDepartment">
	<font color = red ><html:errors /> </font>
<div align="center" class="div">
	

<%

if (departmentform.getReadPreview() != null && departmentform.getReadPreview().equals("2")){


%>

	<br>
	 <fieldset><legend><%=Constant.getResourceStringValue("admin.Deparment.deptdetails",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>	
	<tr>
			
		</tr>
		
		<tr >
			<td><%=Constant.getResourceStringValue("admin.Deparment.deptcode",user1.getLocale())%> </td>
			<td >
			<%=departmentform.getDepartmentCode()%>
			</td>
		</tr>


		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%> </td>
			<td> <%=(departmentform.getDepartmentName()== null)?"":departmentform.getDepartmentName()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.primaryfocusarea",user1.getLocale())%> </td>
			<td><%=(departmentform.getDepartmentDesc()== null)?"":departmentform.getDepartmentDesc()%></td>
		</tr>
		
				
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.notes",user1.getLocale())%>  </td>
			<td><%=(departmentform.getNotes()== null)?"":departmentform.getNotes()%>  </td>
		</tr>
		<%
		String orgurl =null;
		System.out.println("departmentform.getOrgId()"+departmentform.getOrgId());
        if(departmentform.getOrgId() != 0){

   orgurl = "<a href='#'  onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+departmentform.getOrgId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=330"+"'"+")>"+departmentform.getOrgName()+"</a>";

		}

		%>

				<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>  </td>
			
			<td>
			<%=(orgurl == null)?"":orgurl%>
			
			</td>
		</tr>
		

		<tr >
			<td><%=Constant.getResourceStringValue("admin.Deparment.status",user1.getLocale())%> </td>
			<td >
			 <%
			
           if(departmentform.getStatus()!= null && departmentform.getStatus().equals("D")){%>
		   <font color="red"><%=Constant.getResourceStringValue("admin.Deparment.deleted",user1.getLocale())%> </font>
		   <%
			}else{
			%>
			<font color="green"><%=Constant.getResourceStringValue("admin.Deparment.active",user1.getLocale())%></font>
			<%}%>
			</td>
		</tr>
<!--
		 <tr>
			<td>Start Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>

<INPUT TYPE="text" NAME="effectiveStartDate" readonly="true" value="<%=departmentform.getEffectiveStartDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.departmentForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.departmentForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
		
    
 <tr>
			<td>End Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>

<INPUT TYPE="text" NAME="effectiveEndDate" readonly="true" value="<%=departmentform.getEffectiveEndDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.departmentForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.departmentForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>

		 <tr>
			<td>Updated Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>

<INPUT TYPE="text" NAME="updatedDate" readonly="true" value="<%=departmentform.getUpdatedDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.departmentForm.updatedDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.departmentForm.updatedDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
-->

</table>
		</fieldset>
<%}else{%>
<table border="0" width="100%">


 <tr>
			<!--  <td><b><%=Constant.getResourceStringValue("admin.Deparment.createdeptscreen",user1.getLocale())%> </b></td>-->
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		
		<tr >
			<td><%=Constant.getResourceStringValue("admin.Deparment.deptcode",user1.getLocale())%><font color="red">*</font></td>
			<td >
			<html:text property="departmentCode" size="50" maxlength="300"/>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%> <font color="red">*</font></td>
			<td><html:text   property="departmentName" size="50" maxlength="300"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.primaryfocusarea",user1.getLocale())%></td>
			<td><html:text property="departmentDesc" size="50" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
		<html:select property="orgId">
			<bean:define name="departmentForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
		</html:select></td>
		</tr>
						
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.notes",user1.getLocale())%></td>
			<td><html:textarea property="notes" cols="50" rows="3"/></td>
			<!-- <td><textarea name="notes" cols="50" rows="3" maxlength="800" onkeyup="return ismaxlength(this)"></textarea></td> -->

		</tr>
		
		
		<!--
		 <tr>
			<td>Start Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>

<INPUT TYPE="text" NAME="effectiveStartDate" readonly="true" value="<%=departmentform.getEffectiveStartDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.departmentForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.departmentForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
		
    
 <tr>
			<td>End Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>

<INPUT TYPE="text" NAME="effectiveEndDate" readonly="true" value="<%=departmentform.getEffectiveEndDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.departmentForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.departmentForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>

		 <tr>
			<td>Updated Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>

<INPUT TYPE="text" NAME="updatedDate" readonly="true" value="<%=departmentform.getUpdatedDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.departmentForm.updatedDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.departmentForm.updatedDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
-->
		</table>

<table border="0" width="100%">
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
		
		<tr>
			<td><%if (departmentform.getDepartmentId()>0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
	<%}%>	
		</table>

</div>
</html:form>

<%}%>

<script type="text/javascript">
	


<script type="text/javascript">
    YAHOO.util.Event.onDOMReady(function(){

        var Event = YAHOO.util.Event,
            Dom = YAHOO.util.Dom,
            dialog,
            calendar;

        var showBtn = Dom.get("show");

        Event.on(showBtn, "click", function() {

            // Lazy Dialog Creation - Wait to create the Dialog, and setup document click listeners, until the first time the button is clicked.
            if (!dialog) {

                // Hide Calendar if we click anywhere in the document other than the calendar
                Event.on(document, "click", function(e) {
                    var el = Event.getTarget(e);
                    var dialogEl = dialog.element;
                    if (el != dialogEl && !Dom.isAncestor(dialogEl, el) && el != showBtn && !Dom.isAncestor(showBtn, el)) {
                        dialog.hide();
                    }
                });

                function resetHandler() {
                    // Reset the current calendar page to the select date, or 
                    // to today if nothing is selected.
                    var selDates = calendar.getSelectedDates();
                    var resetDate;
        
                    if (selDates.length > 0) {
                        resetDate = selDates[0];
                    } else {
                        resetDate = calendar.today;
                    }
        
                    calendar.cfg.setProperty("pagedate", resetDate);
                    calendar.render();
                }
        
                function closeHandler() {
                    dialog.hide();
                }

                dialog = new YAHOO.widget.Dialog("container", {
                    visible:false,
                    context:["show", "tl", "bl"],
                    buttons:[ {text:"Reset", handler: resetHandler, isDefault:true}, {text:"Close", handler: closeHandler}],
                    draggable:false,
                    close:true
                });
                dialog.setHeader('Pick A Date');
                dialog.setBody('<div id="cal"></div>');
                dialog.render(document.body);

                dialog.showEvent.subscribe(function() {
                    if (YAHOO.env.ua.ie) {
                        // Since we're hiding the table using yui-overlay-hidden, we 
                        // want to let the dialog know that the content size has changed, when
                        // shown
                        dialog.fireEvent("changeContent");
                    }
                });
            }

            // Lazy Calendar Creation - Wait to create the Calendar until the first time the button is clicked.
            if (!calendar) {

                calendar = new YAHOO.widget.Calendar("cal", {
                    iframe:false,          // Turn iframe off, since container has iframe support.
                    hide_blank_weeks:true  // Enable, to demonstrate how we handle changing height, using changeContent
                });
                calendar.render();

                calendar.selectEvent.subscribe(function() {
                    if (calendar.getSelectedDates().length > 0) {

                        var selDate = calendar.getSelectedDates()[0];

                        // Pretty Date Output, using Calendar's Locale values: Friday, 8 February 2008
                        var wStr = calendar.cfg.getProperty("WEEKDAYS_LONG")[selDate.getDay()];
                        var dStr = selDate.getDate();
                        var mStr = calendar.cfg.getProperty("MONTHS_LONG")[selDate.getMonth()];
                        var yStr = selDate.getFullYear();
        
                        Dom.get("date").value = wStr + ", " + dStr + " " + mStr + " " + yStr;
                    } else {
                        Dom.get("date").value = "";
                    }
                    dialog.hide();
                });

                calendar.renderEvent.subscribe(function() {
                    // Tell Dialog it's contents have changed, which allows 
                    // container to redraw the underlay (for IE6/Safari2)
                    dialog.fireEvent("changeContent");
                });
            }

            var seldate = calendar.getSelectedDates();

            if (seldate.length > 0) {
                // Set the pagedate to show the selected date if it exists
                calendar.cfg.setProperty("pagedate", seldate[0]);
                calendar.render();
            }

            dialog.show();
        });
    });
</script>

</body>

