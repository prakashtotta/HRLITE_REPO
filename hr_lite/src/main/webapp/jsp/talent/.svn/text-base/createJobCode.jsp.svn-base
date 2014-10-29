<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="jobcodeform" name="jobCodeForm" type="com.form.JobCodeForm" />

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    } 

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }

    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>
<style>
span1{color:#ff0000;}
</style>
<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 // self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function closewindow(){
	// self.parent.location.reload();
	 parent.parent.GB_hide();
}
function init(){
setTimeout ( "document.jobCodeForm.jobCode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	 var alertstr = "";
	var showalert=false;
		
	var code = document.jobCodeForm.jobCode.value.trim();
	var name=document.jobCodeForm.jobName.value.trim();
			
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobCode_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
			 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.jobCodeForm.action = "jobcode.do?method=saveJobCode&readPreview=1";
 document.jobCodeForm.submit();
//self.parent.location.reload();
 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
		
	var code = document.jobCodeForm.jobCode.value.trim();
	var name=document.jobCodeForm.jobName.value.trim();
			
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobCode_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
			 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.jobCodeForm.action = "jobcode.do?method=updateJobCode&readPreview=2&id="+'<bean:write name="jobCodeForm" property="jobcodeId"/>';
 document.jobCodeForm.submit();
// self.parent.location.reload();

 //window.top.hidePopWin();
 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.jobCodeForm.action = "jobcode.do?method=DeleteJobCode&id="+'<bean:write name="jobCodeForm" property="jobcodeId"/>';

		  document.jobCodeForm.submit();
	  }
	
}

</script>
<%
String saveJobcode = (String)request.getAttribute("saveJobcode");
	
if(saveJobcode != null && saveJobcode.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.JobCode.datasave",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateJobcode = (String)request.getAttribute("updateJobcode");
	
if(updateJobcode != null && updateJobcode.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.JobCode.dataupdate",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteJobcode = (String)request.getAttribute("deleteJobcode");
	
if(deleteJobcode != null && deleteJobcode.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.JobCode.datadelete",user1.getLocale())%></font></td>
			<td> <!--<a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>

<body class="yui-skin-sam" onload="init()" >

<html:form action="/jobcode.do?method=saveJobCode">


<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	<tr>
			<td><b><u><%=Constant.getResourceStringValue("admin.JobCode.AddJobCodescreenname",user1.getLocale())%></u></b></td>
			<td></td>
		</tr>

		

		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobCode",user1.getLocale())%><span1>*<span1></td>
			<td><html:text property="jobCode" maxlength="300"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobName",user1.getLocale())%><span1>*<span1></td>
			<td><html:text property="jobName" maxlength="300"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobDescription",user1.getLocale())%></td>
			<td><html:textarea property="jobDesc" cols="50" rows="5"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.JobCode.JobResponsibility",user1.getLocale())%></td>
			<td><html:text property="jobResponsibility" maxlength="800"/></td>
		</tr>
		
		
		

	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
		
		<tr> 
			<td><%if(jobcodeform.getReadPreview().equals("2")){%>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			
			<%}%>
			<td></td>
		</tr>

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

