<%@ include file="../common/include.jsp" %>
<%@ page import="com.form.JobRequisitionForm;" %>
<html>
<HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>

  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

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
</STYLE>

 </HEAD>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<bean:define id="departmentform" name="departmentForm" type="com.form.DepartmentForm" />



<script language="javascript">

function discard(){
	  var doyou = confirm("Are you confirm Discard the changes? (OK = Yes   Cancel = No)");
	  if (doyou == true){
	   window.top.hidePopWin();
	   } 
	}

function savedata(){
	
	  document.departmentForm.action = "dept.do?method=saveDepartment";
 document.departmentForm.submit();
 self.parent.location.reload();

 
	}

function updatedata(){
	
	  document.departmentForm.action = "dept.do?method=updateDepartment&departmentId="+'<bean:write name="departmentForm" property="departmentId"/>';
 document.departmentForm.submit();
 self.parent.location.reload();

 window.top.hidePopWin();
 
	}

</script>

<body class="yui-skin-sam">

<html:form action="/dept.do?method=saveDepartment">

<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<%

if ((departmentform.getReadPreview()).equals("2")){


%>

	
	<tr>
			<td><b><u>Create Department</u></b></td>
			<td></td>
		</tr>
		
		<tr >
			<td>Department Code*</td>
			<td >
			<html:text property="departmentCode" readonly="true"/>
			</td>
		</tr>


		<tr>
			<td>Department Name*</td>
			<td><html:text   property="departmentName" readonly="true"/></td>
		</tr>
		
		<tr>
			<td>Department Desc*</td>
			<td><html:text property="departmentDesc" readonly="true"/></td>
		</tr>
		
		<tr>
			<td>Created By*</td>
			<td><html:text property="createdBy" readonly="true"/></td>
		</tr>
		
		<tr>
			<td>Updated By*</td>
			<td><html:text property="updatedBy" readonly="true"/></td>
		</tr>
		
		<tr>
			<td>Notes*</td>
			<td><html:text property="notes" readonly="true"/></td>
		</tr>
		<tr>
			<td>Org Id*</td>
			<td><html:text property="orgId" readonly="true"/></td>
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
<%}else{%>

<tr>
			<td><b><u>Create Department</u></b></td>
			<td></td>
		</tr>
		
		<tr >
			<td>Department Code*</td>
			<td >
			<html:text property="departmentCode" />
			</td>
		</tr>


		<tr>
			<td>Department Name*</td>
			<td><html:text   property="departmentName"/></td>
		</tr>
		
		<tr>
			<td>Department Desc*</td>
			<td><html:text property="departmentDesc"/></td>
		</tr>
		
		<tr>
			<td>Created By*</td>
			<td><html:text property="createdBy"/></td>
		</tr>
		
		<tr>
			<td>Updated By*</td>
			<td><html:text property="updatedBy"/></td>
		</tr>
		
		<tr>
			<td>Notes*</td>
			<td><html:text property="notes"/></td>
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
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
		
		<tr>
			<td><%if ((departmentform.getReadPreview()).equals("1")){%>
			<input type="button" name="login" value="update" onClick="updatedata()">
			<%}else{%>
			<input type="button" name="login" value="save" onClick="savedata()">
			<%}%>
			<input type="button" name="login" value="cancel" onClick="discard()"></td>
			<td></td>
		</tr>
	<%}%>	
		</table>
</div>

</html:form>

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

