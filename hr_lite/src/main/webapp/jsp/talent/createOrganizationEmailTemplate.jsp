<%@ include file="../common/include.jsp" %>
<%@ page import="com.form.*"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
<html>
<bean:define id="createorgemailtempform" name="organizationEmailTemplateForm" type="com.form.OrganizationEmailTemplateForm" />

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

<script language="javascript">
function opensearchquestion(){
  window.open("orgemailtemplate.do?method=orgEmaillistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function init(){
setTimeout ( "document.organizationEmailTemplateForm.orgEmailName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	var alertstr = "";
	var showalert=false;
		
	 
	var name=document.organizationEmailTemplateForm.orgEmailName.value.trim();
	//var questionname=organizationEmailTemplateForm.;
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		var emialtmplid = document.organizationEmailTemplateForm.emailtemplateId.value;
	if(emialtmplid == 0){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.EmailTemp_req",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.organizationEmailTemplateForm.orgEmailDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	//if(questionname == "" || questionname == null){
    // 	alertstr = alertstr + "Namef is required.<br>";
	//	showalert = true;
	//	}
		 if (showalert){
     	alert(alertstr);
        return false;
          }

	
	  document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=saveOrganizationEmailTemplate&readPreview=2";
 document.organizationEmailTemplateForm.submit();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
		
	 
	var name=document.organizationEmailTemplateForm.orgEmailName.value.trim();
	 
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		var emialtmplid = document.organizationEmailTemplateForm.emailtemplateId.value;
	if(emialtmplid == 0){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.EmailTemp_req",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.organizationEmailTemplateForm.orgEmailDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=updateOrganizationEmailTemplate&readPreview=2&id="+'<bean:write name="organizationEmailTemplateForm" property="orgemailtemplid"/>';
 document.organizationEmailTemplateForm.submit();



 
	}
function deletedata(){
	
	  document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=deleteOrganizationEmailTemplate&readPreview=2&id="+'<bean:write name="organizationEmailTemplateForm" property="orgemailtemplid"/>';
 document.organizationEmailTemplateForm.submit();



 
	}

	

	function closewindow(){
		 self.parent.location.reload();
		 parent.parent.GB_hide();
}

</script>

<%
String saveOrganizationEmailTemplate = (String)request.getAttribute("saveOrganizationEmailTemplate");
String duplicateexist = (String)request.getAttribute("duplicateexist");
String deleteOrganizationEmailTemplate = (String)request.getAttribute("deleteOrganizationEmailTemplate");
String UpdateOrganizationEmailTemplate = (String)request.getAttribute("UpdateOrganizationEmailTemplate");


%>

<body class="yui-skin-sam">

<% if(deleteOrganizationEmailTemplate != null && deleteOrganizationEmailTemplate.equals("yes")){%>
<div class="div"><font color="white"> <%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.deletemsg",user1.getLocale())%></font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>

<%}else{%>

	<% if(saveOrganizationEmailTemplate != null && saveOrganizationEmailTemplate.equals("yes")){%>
<div class="div"><font color="white"><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.savemsg",user1.getLocale())%> </font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>

<%}else{%>

	<% if(UpdateOrganizationEmailTemplate != null && UpdateOrganizationEmailTemplate.equals("yes")){%>
<div class="div"><font color="white"><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.updatemsg",user1.getLocale())%> </font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>
		
<%}else{%>
<br><br>
<html:form action="/orgemailtemplate.do?method=saveOrganizationEmailTemplate">
<body onload="init()">

<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	<tr>
			<td></td>
			<td></td>
		</tr>
			<tr>
			<td></td>
			<td><b><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.heading",user1.getLocale())%></b></td>
		</tr>
	

<% if(duplicateexist != null && duplicateexist.equals("yes")){%>
<font color="red"> <%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.alertmsg",user1.getLocale())%></font> 
<%}%>

	<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			
			<td>
			
			<html:select property="orgId" >
			<bean:define name="organizationEmailTemplateForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			
		
			</td>
		</tr>
		
		
	    
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.type",user1.getLocale())%></td>
			<td>			
			<html:select property="functiontype" >
			<bean:define name="organizationEmailTemplateForm" property="functionTypeList" id="functionTypeList" />

            <html:options collection="functionTypeList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.tempname",user1.getLocale())%><span1>*<span1></td>
			
 <%
		String jobdyvalue ="<span id=\"emailtemplate\">";
        if(createorgemailtempform.getEmailtemplateId() != 0){
			
  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"emailtemplate.do?method=editemailtemplate&emailtemplateId="+createorgemailtempform.getEmailtemplateId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=800"+"'"+")>"+createorgemailtempform.getEmailtemplatename()+"</a>";
 jobdyvalue = "<span id=\"emailtemplate\">"+tempurl1+"</span>";
	
		
		
}
		
%>	

		
			<td><%=jobdyvalue%></span>




<a href="#" onClick="opensearchquestion()"><img src="jsp/images/selector.gif" border="0"/></a>
          
				<html:hidden  property="emailtemplateId"/>
				<html:hidden  property="emailtemplatename" />
				</td>
		
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.name",user1.getLocale())%><span1>*<span1></td>
			<td><html:text property="orgEmailName" maxlength="200"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.desc",user1.getLocale())%></td>
			<td><html:textarea property="orgEmailDesc" cols="50" rows="4"/></td>
		</tr>
		

	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  
		
		<tr> 
			<td><%if ((createorgemailtempform.getReadPreview()).equals("1")){%>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>

		<tr><td></td><td><i><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.note",user1.getLocale())%></i></td></tr>

		
		
		</table>
</div>

</html:form>
<%}}}%>
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

