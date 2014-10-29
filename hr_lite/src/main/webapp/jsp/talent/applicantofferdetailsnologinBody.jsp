<%@ include file="../common/include.jsp" %>

<%
String path = request.getContextPath()+request.getAttribute("filePath");
String datepattern = Constant.getValue("email.defaultdateformat");
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>


<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Dialog Quickstart Example</title>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}

</style>


<!--begin custom header content for this example-->
<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>

<style type="text/css">
#example {
    height:30em;
}

label { 
    display:block;
    float:left;
    width:45%;
    clear:left;
}

.clear {
    clear:both;
}

#resp {
    margin:10px;
    padding:5px;
    border:1px solid #ccc;
    background:#fff;
}

#resp li {
    font-family:monospace
}

.yui-pe .yui-pe-content {
    display:none;
}
</style>

<!--end custom header content for this example-->


 </HEAD>



<script language="javascript">

function modificationrequest(){
	var url = "applicantoffer.do?method=sendoffermodifficationrequestscr&applicantId=<%=form.getApplicantId()%>";
	parent.showPopWin(url, 600, 300, null,true);
}

function addattachment(){

	
	var alertstr = "";
  var showalert=false;
	var filename = document.applicantForm.attachmentdata.value;
	//var filedetails = document.applicantForm.filedetails.value;
	
	if(filename == "" || filename == null){
     	alertstr = alertstr + "Please select a file.<br>";
		showalert = true;
		}

	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
 	 document.applicantForm.action = "applicantoffer.do?method=applicantpfferattachment&applicantId=<%=form.getApplicantId()%>";
	   document.applicantForm.submit();
}


</script>



<body class="yui-skin-sam">

<%if(session.getAttribute("attachmentsizeexceed") != null ){
String  attachmentsizeexceed = (String)session.getAttribute("attachmentsizeexceed");	
request.getSession().removeAttribute("attachmentsizeexceed");
if(attachmentsizeexceed != null && attachmentsizeexceed.equals("yes")){
int limitfileSize =  new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
	 limitfileSize = limitfileSize / (1024*1024);
	 String mbsize= String.valueOf(limitfileSize) + "MB";

%>
<font color = red > Attachment size exceeds size limit <%=mbsize%> , please reduce size or select a different attachment  and try again.</font>

<%}}%>
		
<% if(form.getInterviewState().equals(Common.OFFER_ACCEPTED)){%>

<font color = green > Thank you for your acceptance !!! Your details are below.</font>

<%}%>
<% if(form.getInterviewState().equals(Common.OFFER_DECLINED)){%>

<font color = red > Thank you for your comments !!! We will get back to you.</font>

<%}%>
<% if(form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>

<font color = red > Thank you for your comments !!! We will get back to you.</font>

<%}%>

<% if(form.getInterviewState().equals(Common.OFFER) || form.getInterviewState().equals(Common.OFFER_ACCEPTED)){%>

<html:form action="/applicantoffer.do" enctype="multipart/form-data">
<fieldset><legend>Offer details</legend>
	<table border="0" width="100%" cellspacing="10"> 
	<font color = red ><html:errors /> </font>
		
		 <tr>
			<td width="150" class="bodytext">Applicant name : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="fullName"/></td>
		</tr>
		
        <tr>
			<td width="150" class="bodytext">Job Title : </td>
			<td class="bodytext"><%=form.getJobTitle()%></td>
		</tr>
		<tr>
			<td class="bodytext">Target joining date : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="targetjoiningdate"/></td>
		</tr>
		
		<tr>
			<td class="bodytext">Offered CTC : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="offeredctc"/>&nbsp;<bean:write name="applicantForm" property="offeredctccurrencycode"/></td>
		</tr>
	
	    <tr>
			<td class="bodytext">Offer released date : </td>
			<td class="bodytext"><%=DateUtil.convertDateToStringDate(form.getOfferreleasedate(),datepattern)%></td>
		</tr>
			
        
		<tr>
			<td class="bodytext">Offer released by :</td>
			<td class="bodytext"><bean:write name="applicantForm" property="offerreleaseddby"/></td>
		</tr>

		<tr>
			<td class="bodytext">Note :</td>
			<td class="bodytext"><bean:write name="applicantForm" property="offerReleaseComment"/></td>
		</tr>
		
		<tr>
			<td class="bodytext">Offer owner :</td>
			<td class="bodytext"><%=form.getOfferownerName()%></td>
		</tr>


		<% if(form.getOfferaccepteddate() != null){%>
		<tr>
			<td class="bodytext">Offer acceptance date : </td>
			<td class="bodytext"><%=DateUtil.convertDateToStringDate(form.getOfferaccepteddate(),datepattern)%></td>
		</tr>
		<tr>
			<td class="bodytext">Offer acceptance comment : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="offeracceptedcomment"/></td>
		</tr>
		<%}%>



       <tr>
<td></td>
<td>
<b>Attach education certificates and experience letters</b>
</td>
</tr>

	<tr>
<td></td>
<td>
File details : <input type="text" name="filedetails" value="">
File <html:file property="attachmentdata"/> 
<a href="#" onClick="addattachment()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add"/></a>
</td>
</tr>
	
</table>

</fieldset>
</html:form>
<fieldset><legend>My attachments</legend>

	<table border="0" width="100%"> 

		<tr>
<td></td>
<td>
<%
List appattachmentList = form.getApplicantAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int i=0;i<appattachmentList.size();i++){
		ApplicantOfferAttachment offerattach = (ApplicantOfferAttachment)appattachmentList.get(i);
		

%>
<tr>
<td><%=(offerattach.getAttahmentdetails()==null)?"":offerattach.getAttahmentdetails()%></td>
<td>
<a href="applicantoffer.do?method=offerattachmentdetails&offerattachmentid=<%=offerattach.getUuid()%>"><%=offerattach.getAttahmentname()%></a></td>
</tr>
<%}%>
</table>
<%}%>

</table>
</fieldset>



		<%
		String offerapproveurl = "applicantoffer.do?method=acceptoffer&applicantId="+form.getApplicantId();
		String offerrejecturl = "applicantoffer.do?method=declineoffer&applicantId="+form.getApplicantId();


%>

<br><br>

<fieldset><legend>Offer Attachments</legend>
	<table border="0" width="100%"> 

		<tr>
<td></td>
<td>
<%
List offerattachmentList = form.getOfferReleaseAttachmentList();

if(offerattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int i=0;i<offerattachmentList.size();i++){
		ApplicantOfferAttachment offerattach = (ApplicantOfferAttachment)offerattachmentList.get(i);
		

%>
<tr>
<td><%=(offerattach.getAttahmentdetails()==null)?"":offerattach.getAttahmentdetails()%></td>
<td>
<a href="applicantoffer.do?method=offerattachmentdetails&offerattachmentid=<%=offerattach.getUuid()%>"><%=offerattach.getAttahmentname()%></a></td>
</tr>
<%}%>
</table>
<%}%>

</table>
<br>
</fieldset>


<%
String interviewstate = form.getInterviewState();
	%>


<%
List offerdeclinedresonlist = form.getOfferdeclinedreasonslist();

%>

<% if(interviewstate.equals(Common.OFFER)){%>
<ul>
<li>If you are ok with the offer details - click "accept offer" button.
<% if(Constant.getValue("offer.modification.allowed") != null && Constant.getValue("offer.modification.allowed").equals("yes")){%>
<li>If you want to change joining date or offered CTC - click "offer modification request" button.
<%}%>
<li>If you are not willing to accept offer - click "decline offer" button.
</ul>
<br>

<div>
<button id="show">accept offer</button>
<% if(Constant.getValue("offer.modification.allowed") != null && Constant.getValue("offer.modification.allowed").equals("yes")){%>
<input type="button" name="login" value="offer modification request" onClick="modificationrequest()">
<%}%>
<button id="reject">decline offer</button>
</div>
<%}%>
	
<script>
YAHOO.namespace("example.container");

YAHOO.util.Event.onDOMReady(function () {
	
	// Define various event handlers for Dialog
	var handleSubmit = function() {
		
		//this.submit();
		var comm = document.initiateapp.comment.value;
		if (comm == "") {
			alert("Please enter comment.");
			return false;
		}
		document.initiateapp.action = "<%=offerapproveurl%>";
   document.initiateapp.submit();
	};


var handleSubmit2 = function() {
		
		//this.submit();
		var comm = document.initiateapp2.comment.value;
		if (comm == "") {
			alert("Please enter comment.");
			return false;
		}
		document.initiateapp2.action = "<%=offerrejecturl%>";
   document.initiateapp2.submit();
	};

	
	


	var handleCancel = function() {
		this.cancel();
	};
	var handleSuccess = function(o) {
		var response = o.responseText;
		response = response.split("<!")[0];
		document.getElementById("resp").innerHTML = response;
	};
	var handleFailure = function(o) {
		alert("Submission failed: " + o.status);
	};

    // Remove progressively enhanced content class, just before creating the module
    YAHOO.util.Dom.removeClass("dialog1", "yui-pe-content");
	 YAHOO.util.Dom.removeClass("dialog2", "yui-pe-content");
	
	 

	// Instantiate the Dialog
	YAHOO.example.container.dialog1 = new YAHOO.widget.Dialog("dialog1", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});

	// Instantiate the Dialog
	YAHOO.example.container.dialog2 = new YAHOO.widget.Dialog("dialog2", 
							{ width : "40em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit2, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});


	
	

	// Validate the entries in the form to require that both first and last name are entered
	YAHOO.example.container.dialog1.validate = function() {
		var data = this.getData();
		if (data.comment == "") {
			alert("Please enter comment.");
			return false;
		} else {
			return true;
		}
	};

	// Wire up the success and failure handlers
	YAHOO.example.container.dialog1.callback = { success: handleSuccess,
						     failure: handleFailure };
	
	// Render the Dialog
	YAHOO.example.container.dialog1.render();
	YAHOO.example.container.dialog2.render();
	
	

	YAHOO.util.Event.addListener("show", "click", YAHOO.example.container.dialog1.show, YAHOO.example.container.dialog1, true);
	YAHOO.util.Event.addListener("reject", "click", YAHOO.example.container.dialog2.show, YAHOO.example.container.dialog2, true);
	
	
	
});
</script>

<div id="dialog1" class="yui-pe-content">
<div class="hd">accept offer</div>
<div class="bd">
<form name="initiateapp" method="POST" action="">
	<label for="comment">Comment:</label><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
	
</form>
</div>
</div>

<div id="dialog2" class="yui-pe-content">
<div class="hd">decline offer</div>
<div class="bd">
<form name="initiateapp2" method="POST" action="">
Reason for decline :
<select name="offerdeclinedresonid">
<option value="0">Select One</option>
<% 
	for(int aa=0;aa<offerdeclinedresonlist.size();aa++)
	{
	DeclinedResons offd = (DeclinedResons)offerdeclinedresonlist.get(aa);

%>
  <option value="<%=offd.getOfferdeclinedreasonId()%>"><%=offd.getOfferdecliedreasonName()%></option>
  
 <%}%>
</select>

<br>

	<label for="comment">Comment:</label><TEXTAREA NAME="comment" COLS=50 ROWS=4></TEXTAREA>

	
</form>
</div>
</div>







<%}%>
</body>
</html>