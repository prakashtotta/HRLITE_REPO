<%@ include file="../common/include.jsp" %>
<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>
	
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
.yui-navset button {
    position:absolute;
    top:0;
    right:0;
}
</style>
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



<body class="yui-skin-sam">
<%
List offerdeclinedresonlist = form.getOfferdeclinedreasonslist();
String offeracceptonbehalf = "applicant.do?method=acceptofferOnBehalf&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
String offerrejectonbehalf = "applicant.do?method=declineofferOnBehalf&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER)){
	if(BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup())){
%>


<%=Constant.getResourceStringValue("On.behalf.requests",user1.getLocale())%> :
<div>
<button id="show"><%=Constant.getResourceStringValue("aquisition.applicant.accept_offer",user1.getLocale())%></button>
<% if(Constant.getValue("offer.modification.allowed") != null && Constant.getValue("offer.modification.allowed").equals("yes")){%>
<input type="button" name="offer modification request" value="<%=Constant.getResourceStringValue("offer.modification.request",user1.getLocale())%>" onClick="modificationrequest()">
<%}%>
<button id="reject"><%=Constant.getResourceStringValue("aquisition.applicant.decline_offer",user1.getLocale())%></button>
</div>
<%}}%>
	
<script>
YAHOO.namespace("example.container");

YAHOO.util.Event.onDOMReady(function () {
	
	// Define various event handlers for Dialog
	var handleSubmit = function() {
		
		//this.submit();
		var comm = document.initiateapp.comment.value;
		if (comm == "") {
			alert("<%=Constant.getResourceStringValue("Requisition.enter.comment.alert",user1.getLocale())%>");
			return false;
		}
		document.initiateapp.action = "<%=offeracceptonbehalf%>";
   document.initiateapp.submit();
	};


var handleSubmit2 = function() {
		
		//this.submit();
		var comm = document.initiateapp2.comment.value;
		if (comm == "") {
			alert("<%=Constant.getResourceStringValue("Requisition.enter.comment.alert",user1.getLocale())%>");
			return false;
		}
		document.initiateapp2.action = "<%=offerrejectonbehalf%>";
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
			alert("<%=Constant.getResourceStringValue("Requisition.enter.comment.alert",user1.getLocale())%>");
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
<div class="hd"><%=Constant.getResourceStringValue("aquisition.applicant.accept_offer",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp" method="POST" action="">
	<label for="comment"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>*:</label><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
	
</form>
</div>
</div>

<div id="dialog2" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("aquisition.applicant.decline_offer",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp2" method="POST" action="">
<%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_decline",user1.getLocale())%> :
<select name="offerdeclinedresonid">
<option value="0"><%=Constant.getResourceStringValue("aquisition.applicant.Select_One",user1.getLocale())%></option>
<% 
	for(int aa=0;aa<offerdeclinedresonlist.size();aa++)
	{
	DeclinedResons offd = (DeclinedResons)offerdeclinedresonlist.get(aa);

%>
  <option value="<%=offd.getOfferdeclinedreasonId()%>"><%=offd.getOfferdecliedreasonName()%></option>
  
 <%}%>
</select>

<br>

	<label for="comment"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>*:</label><TEXTAREA NAME="comment" COLS=50 ROWS=4></TEXTAREA>

	
</form>
</div>
</div>

</body>