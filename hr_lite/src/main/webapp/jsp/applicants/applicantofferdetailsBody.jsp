<%@ include file="../common/include.jsp" %>

<%
ApplicantUser user = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
JobApplicant applicant = user.getApplicant();
String path = request.getContextPath()+request.getAttribute("filePath");
String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>


<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.applicant.my.offer.details",user.getLocale())%></title>


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
<style>
span1{color:#ff0000;}
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

<%
String declineoffer = (String)request.getAttribute("declineoffer");
String acceptoffer = (String)request.getAttribute("acceptoffer");

%>

<script language="javascript">

function modificationrequest(){
	var url = "applicantuserops.do?method=sendoffermodifficationrequestscr&applicantId=<%=applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>";
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.offer_modification_request",user.getLocale())%>");
	parent.showPopWin(url, 600, 300, null,true);
}
function offerDeclined(){
	
	var url = "applicantuserops.do?method=offerDeclinedScr&applicantId=<%=applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>";
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant_portal.decline_offer",user.getLocale())%>");
	parent.showPopWin(url, 600, 300, null,true);
	
}



function acceptOffer(){
	
	var url = "applicantuserops.do?method=acceptOfferScr&applicantId=<%=applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>";
	parent.setPopTitle1("<%=Constant.getResourceStringValue("aquisition.applicant.accept_offer",user.getLocale())%>");
	parent.showPopWin(url, 600, 300, null,true);
	
}

function addattachment(){

	
	var alertstr = "";
  var showalert=false;
	var filename = document.applicantForm.attachmentdata.value;

	
	if(filename == "" || filename == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant_portal.select_file",user.getLocale())%><br>";
		showalert = true;
		}


	
	
	
	 if (showalert){
     	alert(alertstr);
        return false;
          }
 	 document.applicantForm.action = "applicantuserops.do?method=applicantpfferattachment&applicantId=<%=applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>";
	   document.applicantForm.submit();
}


</script>



<body class="yui-skin-sam">

	
<%if(request.getAttribute("attachmentsizeexceed") != null ){
String  attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");	

if(attachmentsizeexceed != null && attachmentsizeexceed.equals("yes")){
int limitfileSize =  new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
	 limitfileSize = limitfileSize / (1024*1024);
	 String mbsize= String.valueOf(limitfileSize) + "MB";

%>
<div class="msg"><font color = "white" > Attachment size exceeds size limit <%=mbsize%> , please reduce size or select a different attachment  and try again.</font>

<%}}%>
		
<%if(acceptoffer != null && acceptoffer.equals("yes")){%>

<font color = "white" > Thank you for your offer acceptance !!! Your details are below.</font>

<%}%>
<% if(declineoffer != null && declineoffer.equals("yes")){%>

<font color = "white" > Thank you for your comments !!! We will get back to you.</font>

<%}%>
</div>

<% if(form.getInterviewState().equals(Common.OFFER) || form.getInterviewState().equals(Common.OFFER_ACCEPTED)|| form.getInterviewState().equals(Common.OFFER_DECLINED)|| form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION) || form.getInterviewState().equals(Common.ON_BOARD) ){%>

<html:form action="/applicantuserops.do" enctype="multipart/form-data">
<fieldset><legend><b><%=Constant.getResourceStringValue("hr.applicant.my.offer.details",user.getLocale())%><b></legend>
	<table border="0" width="100%" cellspacing="10"> 
	<font color = red ><html:errors /> </font>



		 <tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.applicant_Name",user.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="fullName"/></td>
		</tr>
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Status",user.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="interviewState"/></td>
		</tr>
		
        <tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Job_Title",user.getLocale())%> : </td>
			<td class="bodytext"><%=form.getJobTitle()%></td>
		</tr>
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Target_joining_date",user.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="targetjoiningdate"/></td>
		</tr>
		
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offered_CTC",user.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="offeredctc"/>&nbsp;<bean:write name="applicantForm" property="offeredctccurrencycode"/></td>
		</tr>
	
	    <tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offer_released_date",user.getLocale())%> : </td>
			<td class="bodytext"><%=DateUtil.convertDateToStringDate(form.getOfferreleasedate(),datepattern)%></td>
		</tr>
			
        
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offer_released_by",user.getLocale())%> :</td>
			<td class="bodytext"><bean:write name="applicantForm" property="offerreleaseddby"/></td>
		</tr>

		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Note",user.getLocale())%> :</td>
			<td class="bodytext">
			<%=(form.getOfferReleaseComment() == null)? "":StringUtils.doSpecialCharacters(form.getOfferReleaseComment())%>
			</td>
		</tr>
		
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offer_owner",user.getLocale())%> :</td>
			<td class="bodytext"><%=form.getOfferownerName()%></td>
		</tr>


		<% if(form.getInterviewState().equals(Common.OFFER_ACCEPTED)){%>
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offer_acceptance_date",user.getLocale())%>: </td>
			<td class="bodytext"><%=DateUtil.convertDateToStringDate(form.getOfferaccepteddate(),datepattern)%></td>
		</tr>
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offer_acceptance_comment",user.getLocale())%> : </td>
			<td class="bodytext">
			<%=(form.getOfferacceptedcomment() == null)? "":StringUtils.doSpecialCharacters(form.getOfferacceptedcomment())%>
			</td>
		</tr>
		<%}%>

	<% if(form.getInterviewState().equals(Common.OFFER_DECLINED)){%>
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offer_declined_date",user.getLocale())%> : </td>
			<td class="bodytext"><%=DateUtil.convertDateToStringDate(form.getOfferdeclineddate(),datepattern)%></td>
		</tr>
		<tr>
			<td width="30%" class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offer_declined_comment",user.getLocale())%> : </td>
			<td class="bodytext">
			<%=(form.getOfferdeclinedcomment() == null)? "":StringUtils.doSpecialCharacters(form.getOfferdeclinedcomment())%>
			</td>
		</tr>
		<%}%>

</table>
<table>

       <tr>

<td>
<b><%=Constant.getResourceStringValue("aquisition.applicant_portal.attachaskedanything",user.getLocale())%></b>

</td>
</tr>

	<tr>

<td>


<%=Constant.getResourceStringValue("aquisition.applicant_portal.File_details",user.getLocale())%> : <input type="text" name="filedetails" value="">
<%=Constant.getResourceStringValue("aquisition.applicant_portal.File",user.getLocale())%> <html:file property="attachmentdata"/> 
<a href="#" onClick="addattachment()" class="button">Add</a>
</td>
</tr>
	
</table>

</fieldset>
</html:form>
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.My_attachments",user.getLocale())%></b></legend>

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
		String offerapproveurl = "applicantuserops.do?method=acceptoffer&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
		String offerrejecturl = "applicantuserops.do?method=declineoffer&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();


%>

<br><br>

<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.Offer_Attachments",user.getLocale())%></b></legend>
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

<br>
<%
String interviewstate = form.getInterviewState();
	%>


<%
List offerdeclinedresonlist = form.getOfferdeclinedreasonslist();

%>

<% if(interviewstate.equals(Common.OFFER)){%>
<ul>
<li><%=Constant.getResourceStringValue("aquisition.applicant_portal.message1",user.getLocale())%>
<% if(Constant.getValue("offer.modification.allowed") != null && Constant.getValue("offer.modification.allowed").equals("yes")){%>
<li><%=Constant.getResourceStringValue("aquisition.applicant_portal.message2",user.getLocale())%>
<%}%>
<li><%=Constant.getResourceStringValue("aquisition.applicant_portal.message3",user.getLocale())%>
</ul>
<br>

<div>


<!-- <button id="show" class="button"><%=Constant.getResourceStringValue("aquisition.applicant_portal.accept_offer",user.getLocale())%></button> -->
<input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant_portal.accept_offer",user.getLocale())%>" onClick="acceptOffer()" class="button"/>

<% if(Constant.getValue("offer.modification.allowed") != null && Constant.getValue("offer.modification.allowed").equals("yes")){%>
<input type="button" name="login" value="Offer modification request" onClick="modificationrequest()" class="button">
<%}%>
<input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant_portal.decline_offer",user.getLocale())%>" onClick="offerDeclined()" class="button"/>
<!-- <button id="reject" class="button"><%=Constant.getResourceStringValue("aquisition.applicant_portal.decline_offer",user.getLocale())%></button> -->
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
		var alertstr="";
		var showalert=false;
		var comm = document.initiateapp2.comment.value;
		var reason = document.initiateapp2.offerdeclinedresonid.value;
		if (comm == "" ||comm == null ) {
			
			alertstr = alertstr +"<%=Constant.getResourceStringValue("aquisition.applicant_portal.comment_mandatory",user.getLocale())%><BR>";
			showalert = true;
		}
	
			
			//alert("Please select reason for decline");
		if(reason == 0){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant_portal.reason_mandatory",user.getLocale())%><BR>";
			showalert = true;
		}
		
		if (showalert){
	     	alert(alertstr);
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
			alert("<%=Constant.getResourceStringValue("aquisition.applicant_portal.comment_mandatory",user.getLocale())%>");
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
<div class="hd"><%=Constant.getResourceStringValue("aquisition.applicant_portal.accept_offer",user.getLocale())%></div>
<div class="bd">
<form name="initiateapp" method="POST" action="">
	<label for="comment"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Comment",user.getLocale())%><font color="red">*</font>:</label><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
	
</form>
</div>
</div>

<div id="dialog2" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("aquisition.applicant_portal.decline_offer",user.getLocale())%></div>
<div class="bd">
<form name="initiateapp2" method="POST" action="">
Reason for decline <Font color="red">*</Font>&nbsp; :
<select name="offerdeclinedresonid">
<option value="0"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Select_One",user.getLocale())%></option>
<% 
	for(int aa=0;aa<offerdeclinedresonlist.size();aa++)
	{
	DeclinedResons offd = (DeclinedResons)offerdeclinedresonlist.get(aa);

%>
  <option value="<%=offd.getOfferdeclinedreasonId()%>"><%=offd.getOfferdecliedreasonName()%></option>
  
 <%}%>
</select>

<br>

	<label for="comment"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Comment",user.getLocale())%><Font color="red">*</Font>&nbsp;:</label><TEXTAREA NAME="comment" COLS=50 ROWS=4></TEXTAREA>

	
</form>
</div>
</div>







<%}%>
</body>
</html>