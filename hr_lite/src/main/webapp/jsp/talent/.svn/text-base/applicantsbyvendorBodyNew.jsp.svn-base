<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />

	<title>Applicants</title>
<%@ include file="../common/include.jsp" %>
	<%--<link rel="stylesheet" type="text/css" href="jsp/jquery/layout-default-latest.css" />
	<link rel="stylesheet" type="text/css" href="jsp/jquery/themes/base/jquery.ui.all.css" />--%>
	<!-- CUSTOMIZE/OVERRIDE THE DEFAULT CSS -->
	<style type="text/css">



	/* remove padding and scrolling from elements that contain an Accordion OR a content-div */
	.ui-layout-center ,	/* has content-div */
	.ui-layout-west ,	/* has Accordion */
	.ui-layout-east ,	/* has content-div ... */
	.ui-layout-east .ui-layout-content { /* content-div has Accordion */
		padding: 0;
		overflow: auto;
	}
	.ui-layout-center P.ui-layout-content {
		line-height:	1.4em;
		margin:			0; /* remove top/bottom margins from <P> used as content-div */
	}
	h3, h4 { /* Headers & Footer in Center & East panes */
		font-size:		1.1em;
		background:		#EEF;
		border:			1px solid #BBB;
		border-width:	0 0 1px;
		padding:		7px 10px;
		margin:			0;
	}
	.ui-layout-east h4 { /* Footer in East-pane */
		font-size:		0.9em;
		font-weight:	normal;
		border-width:	1px 0 0;
	}
	</style>

	<!-- REQUIRED scripts for layout widget -->
	<script type="text/javascript" src="jsp/jquery/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="jsp/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="jsp/jquery/js/jquery-ui-latest.js"></script>
	<script type="text/javascript" src="jsp/jquery/js/jquery.layout-latest.js"></script>



	<script type="text/javascript">
	var myLayout;
	var wes = screen.width/3.2;
	var eas = screen.width/8;
	$(document).ready( function() {

		myLayout = $('body').layout({
			west__size:			wes
		,	east__size:			eas
			// RESIZE Accordion widget when panes resize
		,	west__onresize:		$.layout.callbacks.resizePaneAccordions
		,	east__onresize:		$.layout.callbacks.resizePaneAccordions
		});

		// ACCORDION - in the West pane
		$("#accordion1").accordion({ fillSpace:	true });
		
		myLayout.panes.east.tabs({
			show:				$.layout.callbacks.resizePaneAccordions // resize tab2-accordion when tab is activated
		});
		myLayout.sizeContent("east"); // resize pane-content-elements after creating east-tabs




	});
	</script>

</head>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/greybox.jsp" %>




<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


%>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />


<%@ include file="../talent/applicantserachJS.jsp" %>

  <link href="jsp/css/cal.cs" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jsp/js/CalendarPopup.js"></script>



<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>


<%

String noofrowsinpagination = (aform.getNoofrows()==null)?Constant.getValue("default.pagination.size"):aform.getNoofrows();
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
String savedsearch = (String)request.getAttribute("savedsearch");

String isapplicationsubmitted = "false";
	
 if(aform.getInterviewState() != null && aform.getInterviewState().equals(Common.APPLICATION_SUBMITTED)){
	 isapplicationsubmitted="true";
 }
%>
<script language="javascript">
this.reload_on_close=false;
var isapplicationsubmitted=<%=isapplicationsubmitted%>;
function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
	callBoxFancy(url);
}
 function searchapplicant(){
	 var uuid = "";
	 if(typeof document.applicantForm.searchuuid != 'undefined'){
	 uuid = document.applicantForm.searchuuid.value;
	
	 }else{
		 uuid = "<%=aform.getSearchcriteria().getSearchuuid()%>";
		 
	 }

 
document.applicantForm.action = "applicant.do?method=searchapplicantbyvendor&searchuuid="+uuid;
document.applicantForm.submit();
}

function openUrl(url){
		 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
		 location.href=url;

}


$(document).ready(function() {$('#applicantsearchbutton').click(function() {    
	// $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
   searchapplicants();
	 }); 
}); 

function searchapplicants() { 

	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });

	 var uuid = "";
	 if(typeof document.applicantForm.searchuuid != 'undefined'){
	 uuid = document.applicantForm.searchuuid.value;
	
	 }else{
		 uuid = "<%=aform.getSearchcriteria().getSearchuuid()%>";
		 
	 }
   
		var fromdate = document.applicantForm.searchfromdate.value;
		var todate = document.applicantForm.searchtodate.value;
		var dateofbirth = document.applicantForm.dateofbirth.value;
		var dateofbirth1 = document.applicantForm.dateofbirth1.value;;
		var earliest_start_date = document.applicantForm.earliest_start_date.value;
		var gender = document.applicantForm.gender;
		var gvalue="";

for (i=0; i<gender.length; i++){

  if (gender[i].checked == true)
  gvalue = gender[i].value ;

}

	
dataString = $("#applicantsearch").serialize();
$.ajax({
	type: 'POST',
  url: "applicant.do?method=searchapplicantbyvendor&searchuuid="+uuid+"&csrfcode="+document.applicantForm["csrfcode"].value+"&searchfromdate="+fromdate+"&searchtodate="+todate+"&dateofbirth="+dateofbirth+"&dateofbirth1="+dateofbirth1+"&earliest_start_date="+earliest_start_date+"&gender="+gvalue,
data: dataString,
  success: function(data){
  $('#applicantdata').html(data);
	completeajx();
  }
});
} 

 
function completeajx(){
	
	 $.unblockUI();
}


function savesearchdata1(uuid){

	 

    var url = "<%=request.getContextPath()%>/applicant.do?method=savesearchapplicantbyvendorscr&searchuuid="+uuid;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.save_search",user1.getLocale())%>',url,200,500, messageret);

}

function deletesavesearch(id){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
document.applicantForm.action = "applicant.do?method=deletesavesearch&searchuuid="+id;
document.applicantForm.submit();
	  }
}




function sendbulkresumeforscreening(appIds){
	var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=sendbulkresumeforscreening&eventype=<%=Common.IN_SCREENING%>&applicantids="+appIds;
	//parent.showPopWin(url, 650, 600, showmessage,true);

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>', url, messageret);

}

function markdeletebulk(appIds){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=markfordeletionbulk&applicantids="+appIds;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>',url,400,600, messageret);

}

	 function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsAllApplicants";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}


	function messageret(){
	//window.location.reload();
	//history.go(0);
//var url = "<%=request.getContextPath()%>/applicant.do?method=applicantsbyvendor";
//location.replace(url)

			}
function showmessage(returnval) { 
	//window.location.reload();
	}

function savesearchrow(){
	var t = document.getElementById("savetextbutton").style.display ='none';
	 var d=document.getElementById("savetext");
	 d.innerHTML+="<%=Constant.getResourceStringValue("aquisition.applicant.Save_search_name",user1.getLocale())%> : <input type='text' sieze='60' name='savedsearchname'> <input type='button' name='savesearchdata' value='<%=Constant.getResourceStringValue("aquisition.applicant.save_search_save",user1.getLocale())%>' onClick='savesearchdata1()'>";
}



function createapplicant(){

	var url = "<%=request.getContextPath()%>/applicant.do?method=createPage";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapplicant",user1.getLocale())%>',url,messageret);
}
function exportapplicants(){
	var url = "<%=request.getContextPath()%>/applicant.do?method=exportapplicantsscr";
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Exportapplicants",user1.getLocale())%>', url, 600,700, messageret1);
}

function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;

	   //parent.setPopTitle1("Job requisition");
		//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);
}

function exporttoexcel(){

	var uuid = "";
	 if(typeof document.applicantForm.searchuuid != 'undefined'){
	 uuid = document.applicantForm.searchuuid.value;
	
	 }else{
		 uuid = "<%=aform.getSearchcriteria().getSearchuuid()%>";
		 
	 }
	
document.applicantForm.action = "applicant.do?method=exporttoexcelAllSearch&searchuuid="+uuid;
document.applicantForm.submit();
}


function userDetailsOrGroup(id,isgroup){
	var url = "";
	if(isgroup == 'Y'){
		url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id;
	}else{
			url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
		}
	
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}

function messageret1(){
	
			} 




</script>
<script language="javascript">

function resetdata(){
location.href="applicant.do?method=applicantsbyvendor";
  	
 }  


</script>
<script language="javascript">

function retrieveURL(url) {

   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	url="applicantoffer.do?method=stateListNologin";
	url=url+"&cid="+document.applicantForm.countryId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}

function processStateChange() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtml(newTextElements){
	//loop through newTextElements ffffffffff
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="states")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}

function retrieveURLresumesource() {
   //convert the url to a string
    document.getElementById("loadingsource").style.visibility = "visible";

	
	var urlnew="applicant.do?method=loadResumeSources"+"&typeid="+document.applicantForm.sourceTypeId.value;

	//alert(urlnew);
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeResumeSource;

	    try {
    		req.open("GET", urlnew, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeResumeSource;
	        req.open("GET", urlnew, true);
		    req.send();
			
    	}
  	}
}

function processStateChangeResumeSource() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlResumeSource(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingsource").style.visibility = "hidden";	
  	}
}


function replaceExistingWithNewHtmlResumeSource(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="subsource")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function retrieveURLOrg(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	 var ids ="";
 
  for (var i = 0; i < document.applicantForm.orgIds.options.length; i++){
    if (document.applicantForm.orgIds.options[ i ].selected){
		ids = ids + document.applicantForm.orgIds.options[ i ].value + ",";
	}
	}

	
	url=url+"&orgId="+ids;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeOrg;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}

	 setTimeout("retrievejobreqlist()",200);
}





function retrieveProjcodes(url) {
   //convert the url to a string
    document.getElementById("loading1").style.visibility = "visible";

	var deptids ="";
	
	 for (var i = 0; i < document.applicantForm.departmentIds.options.length; i++){
    if (document.applicantForm.departmentIds.options[ i ].selected){
		deptids = deptids + document.applicantForm.departmentIds.options[ i ].value + ",";
	}
	}

	url=url+"&departmentId="+deptids;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeProj;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeProj;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	setTimeout("retrievejobreqlist()",200);
}
	 

function retrievejobreqlist() {
	
   //convert the url to a string
    document.getElementById("loadingjob").style.visibility = "visible";

	var orgids ="";
	var deptids ="";
	var projids ="";
 
  for (var i = 0; i < document.applicantForm.orgIds.options.length; i++){
    if (document.applicantForm.orgIds.options[ i ].selected){
		orgids = orgids + document.applicantForm.orgIds.options[ i ].value + ",";
	}
	}


  for (var i = 0; i < document.applicantForm.departmentIds.options.length; i++){
    if (document.applicantForm.departmentIds.options[ i ].selected){
		deptids = deptids + document.applicantForm.departmentIds.options[ i ].value + ",";
	}
	}


  for (var i = 0; i < document.applicantForm.projectcodeIds.options.length; i++){
    if (document.applicantForm.projectcodeIds.options[ i ].selected){
		projids = projids + document.applicantForm.projectcodeIds.options[ i ].value + ",";
	}
	}
	
	   
    var url = "applicant.do?method=loadActiveJobsListMultiple";
	 url=url+"&orgId="+orgids;
	 url=url+"&departmentId="+deptids;
	 url=url+"&projectCodeId="+projids;
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeJob;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeJob;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}

function processStateChangeOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrg(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlOrg(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function processStateChangeJob() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrg(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlJob(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingjob").style.visibility = "hidden";	
  	}
}


function processStateChangeProj() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrg(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlProj(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading1").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlOrg(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="departments")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function replaceExistingWithNewHtmlJob(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="jobreqlist")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


function replaceExistingWithNewHtmlProj(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="projectcodes")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function splitTextIntoSpanOrg(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}


function retrieveCriterias() {
   //convert the url to a string
  // document.businessRuleForm.variableCriteria.value = "NoValue";
  //document.applicantForm.answerOptioncri.value= "NoValue";
   		document.getElementById("filter1").style.visibility = "hidden";
		document.getElementById("filter2").style.visibility = "hidden";
		document.getElementById("filterbutton").style.visibility = "visible";
		
    document.getElementById("loadingcri").style.visibility = "visible";


	var urlnew="question.do?method=loadCriterias"+"&questionId="+document.applicantForm.questionId.value;

	//alert(urlnew);
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeCriteria;

	    try {
    		req.open("GET", urlnew, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeCriteria;
	        req.open("GET", urlnew, true);
		    req.send();
			
    	}
  	}
}

function processStateChangeCriteria() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlCriteria(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingcri").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlCriteria(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="criteralist")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


function retrieveQuestions() {
   //convert the url to a string
  // document.businessRuleForm.variableCriteria.value = "NoValue";
  //document.applicantForm.answerOptioncri.value= "NoValue";
   		document.getElementById("filter1").style.visibility = "hidden";
		document.getElementById("filter2").style.visibility = "hidden";
    document.getElementById("loadingqns").style.visibility = "visible";


	var urlnew="questiongroup.do?method=loadQuestions"+"&groupid="+document.applicantForm.quetionnaireId.value;

	//alert(urlnew);
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeQuestion;

	    try {
    		req.open("GET", urlnew, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeQuestion;
	        req.open("GET", urlnew, true);
		    req.send();
			
    	}
  	}
}

function processStateChangeQuestion() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlQuestion(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingqns").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlQuestion(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="questionslist")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function saveQuestionFilters(){
	document.getElementById("loadingqnsfilters").style.visibility = "visible";
var answerOption="";
if(typeof document.applicantForm.answerOption != 'undefined'){
for (var i = 0; i < document.applicantForm.answerOption.options.length; i++){
    if (document.applicantForm.answerOption.options[ i ].selected){
		answerOption = document.applicantForm.answerOption.options[ i ].value ;
	}
	}
}
var filtercri = "";
if(typeof document.applicantForm.filtercri != 'undefined'){
	filtercri = document.applicantForm.filtercri.value;
}

	 var uuid = "";
	 if(typeof document.applicantForm.searchuuid != 'undefined'){
	 uuid = document.applicantForm.searchuuid.value;
	 
	 }else{
		 uuid = "<%=aform.getSearchcriteria().getSearchuuid()%>";
	
	 }


var securityparam = "&csrfcode="+document.applicantForm["csrfcode"].value;
var params = "method=savequestionfilters&searchuuid="+uuid+"&questionId="+document.applicantForm.questionId.value+"&filtercri="+filtercri+"&answerOption="+answerOption+"&filterValue1="+document.applicantForm.filterValue1.value+"&filterValue2="+document.applicantForm.filterValue2.value+securityparam;

//url = encodeURIComponent(url);



      //Do the AJAX call
	  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeSaveQnsFilter;

	    try {
    		req.open("POST", "applicant.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(params);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeSaveQnsFilter;
    		req.open("POST", "applicant.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(params);
			
    	}
  
	}


	
}


function deletequestionsfilters(searchuuid,uuid){

	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	var url="applicant.do?method=deletequestionsfilter&searchuuid="+searchuuid+"&uuid="+uuid;

      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangedelfilter;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangedelfilter;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveFilters()",200);
}

}

function processStateChangedelfilter() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}


function retrieveFilters() {
    //document.getElementById("loading4").style.visibility = "visible";
		//document.applicantForm.skillname.value="";
		//document.applicantForm.yearsofexpskill.value="";
		//document.applicantForm.ratingskill.value="";

 var uuid = "";
	 if(typeof document.applicantForm.searchuuid != 'undefined'){
	 uuid = document.applicantForm.searchuuid.value;
	
	 }else{
		 uuid = "<%=aform.getSearchcriteria().getSearchuuid()%>";
		
	 }
	 

	var url="applicant.do?method=retrieveFilters&searchuuid="+uuid;
	

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processFilters;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processFilters;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function processFilters() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlFilters(spanElements);
			
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading4").style.visibility = "hidden";	
  	}
}


function processStateChangeSaveQnsFilter() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);
    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlSaveQnsFilter(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingqnsfilters").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlSaveQnsFilter(newTextElements){
	//loop through newTextElements ffffffffff
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="questionsfilters"){
					  
   					document.getElementById(name).innerHTML = content;
				  }
			  }	   			
   	 	}
	}
}

function replaceExistingWithNewHtmlFilters(newTextElements){
	//loop through newTextElements ffffffffff
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="questionsfilters"){
					  
   					document.getElementById(name).innerHTML = content;
				  }
			  }	   			
   	 	}
	}
}

function showFilterTextBox(){
	var answerOptioncri = document.applicantForm.filtercri.value;
	if(answerOptioncri == 'NoValue'){
		document.applicantForm.filterValue1.value="";
		document.applicantForm.filterValue2.value="";
	}
	if(answerOptioncri == 'BETWEEN'){
		document.getElementById("filter1").style.visibility = "visible";	
		document.getElementById("filter2").style.visibility = "visible";
	}else if(answerOptioncri == 'NoValue'){
		document.getElementById("filter1").style.visibility = "hidden";
		document.getElementById("filter2").style.visibility = "hidden";
	}else if(answerOptioncri == 'NOT_NULL'){
		document.getElementById("filter1").style.visibility = "hidden";
		document.getElementById("filter2").style.visibility = "hidden";
	}else{
		document.getElementById("filter1").style.visibility = "visible";
		document.getElementById("filter2").style.visibility = "hidden";
	}	
}

function init(){
		initSearch();
	document.applicantForm.fullName.focus();
	

	}
</script>


<body class="yui-skin-sam" onLoad="init()">

<div class="ui-layout-north ui-widget-content"  style="background: #86c9ef;" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
<%@ include file="../common/menu.jsp" %>
	
</div>


<div class="ui-layout-south ui-widget-content" style="display: none;"> 
<%@ include file="../common/Footer.jsp" %>
</div>

<div class="ui-layout-center" style="overflow: auto"> 
	<h3 class="ui-widget-header">Applicant List</h3>
<!-- search result start-->

<span id="applicantdata">
<br>
<table>
<tr>
<td><div id="toalrecords" class="msg"> </div></td>
 <td>

 <div class="bd">
			<div id="container"></div>
			  <a class="button" href="#" id="delete" ><%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%></a> | 
			
			
		</div>
</td>

 <td>
 <div class="bd">
			<div id="container"></div>
			<a class="button" href="#" id="delete1"><%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%></a> |
			
			
		</div>

</td>
<!--<td>

<a href="#" onClick="javascript:exportapplicants()" ><%=Constant.getResourceStringValue("aquisition.applicant.Exportapplicants",user1.getLocale())%></a> |
</td>
-->
<td>

<a class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a> 
</td>

<td>
<% if(searchpagedisplay != null && searchpagedisplay.equals("yes")){%>
<input type="button" name="savesearch" value="<%=Constant.getResourceStringValue("aquisition.applicant.save_search",user1.getLocale())%>" onClick="savesearchdata1('<%=aform.getSearchcriteria().getSearchuuid()%>')">
<%}%>
</td>

</tr>
</table>

	<br>



<div id="dynamicdata"></div>
				

</span>



<!-- search result end -->
</div>

<div class="ui-layout-west" style="overflow: auto;display: none;">
	
       <h3 class="ui-widget-header">Search parameters</h3>
			
			<div>
<html:form action="/applicant.do?method=searchapplicantbyvendor" styleId="applicantsearch">
<%
String exporttoexcel = (String)request.getAttribute("exporttoexcel");
%>
<%if(exporttoexcel != null && exporttoexcel.equals("yes")){
%>
<div class="msg"><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.export.job.started",user1.getLocale())%> <a href="bulkuploadtask.do?method=mybulktasklist"><%=Constant.getResourceStringValue("aquisition.process.job.page",user1.getLocale())%></a></font></div>
<%}%>
<table>
<tr>
<td>

 <%=Constant.getResourceStringValue("No_Of_Rows_colon",user1.getLocale())%>
 			<html:select  property="noofrows">
			<bean:define name="applicantForm" property="paginationRowsList" id="paginationRowsList" />
            <html:options collection="paginationRowsList" property="key"  labelProperty="value"/>
			</html:select>

</td>
<td>
<input type="button"  id="applicantsearchbutton" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" class="button">
  <input type="button" name="search1"  value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button"/>
<!--  <input type="button" name="search1" value=".....<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()">-->
<% //if(searchpagedisplay != null && searchpagedisplay.equals("yes")){%>
<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.exporttoexcel",user1.getLocale())%>" onClick="exporttoexcel()" class="button">
<%//}%>


</td>

</tr>
<tr>
<td valign="left">

</td>
<td>

</td>
</tr>
</table>
<!-- default search param start -->
<div class="container">
<table border="0" class="div">
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.keyword",user1.getLocale())%></td>
<td><html:text styleClass="text" property="keywords" size="20" maxlength="200"/></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
<td>
<html:select  property="applicantNo_criteria" onchange="unhideApplicantId()" styleClass="list">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
</html:select>
<%
String appidvalue = (aform.getApplicantId()==0)?"":String.valueOf(aform.getApplicantId());
%>
<html:text property="applicantNo" size="20" maxlength="20" value="<%=appidvalue%>" styleClass="textdynamic"/>
<%
String app1value = (aform.getApplicantId1()==Integer.MAX_VALUE)?"":String.valueOf(aform.getApplicantId1());
%>
<html:text property="applicantId1" size="20" maxlength="20"  value="<%=app1value%>" styleClass="textdynamic"/>
</td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
<td>
<html:select  property="applicantName_criteria" styleClass="list">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
</html:select>
<html:text styleClass="textdynamic" property="fullName" size="20" maxlength="200"/>
</td>
</tr>
<tr>

<td><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%></td>
			<td>
			<html:select  styleClass="multipleselect" multiple="true" size="3" property="interviewStates">
			<bean:define name="applicantForm" property="interviewstateList" id="interviewstateList" />
            <html:options collection="interviewstateList" property="interviewstateCode"  labelProperty="interviewstateName"/>
			</html:select>
			</td>
</tr>

</table>
<!-- default search param end-->



<script type="text/javascript" src="jsp/js/animatedcollapse.js"></script>

<script type="text/javascript">
animatedcollapse.addDiv('sourceappsearch', 'fade=0,speed=400,group=pets1,hide=1,persist=1');
animatedcollapse.addDiv('personalappsearch', 'fade=0,speed=400,group=pets2,hide=1,persist=1');
animatedcollapse.addDiv('workpreferenceappsearch', 'fade=0,speed=400,group=pets3,hide=1,persist=1');
animatedcollapse.addDiv('educationappsearch', 'fade=0,speed=400,group=pets4,hide=1,persist=1');
animatedcollapse.addDiv('advanceparameterappsearch', 'fade=0,speed=400,group=pets5,hide=1,persist=1');
animatedcollapse.addDiv('questionaires', 'fade=0,speed=400,group=pets6,hide=1,persist=1');
animatedcollapse.addDiv('examdv', 'fade=0,speed=400,group=pets7,hide=1,persist=1');
animatedcollapse.addDiv('customvariableappsearch', 'fade=0,speed=400,group=pets7,hide=1,persist=1');

animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()
</script>

<!-- source start -->
 <div  class="msg"><a href="#" rel="toggle[sourceappsearch]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.resume.source",user1.getLocale())%> </div>
<div id="sourceappsearch" style="width  100%;">
<table border="0" width="100%" class="div">
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Source",user1.getLocale())%></td>
		<td align="left">
			<html:select styleClass="list"  property="sourceTypeId" onchange="retrieveURLresumesource();">
			<option value=""></option>
			<bean:define name="applicantForm" property="sourceTypeList" id="sourceTypeList" />

            <html:options collection="sourceTypeList" property="resumeSourceTypeId"  labelProperty="resumeSourceTypeName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingsource" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_sub_source",user1.getLocale())%>......</span>
		</td>
	</tr>
	<tr>
		<td>
		<%=Constant.getResourceStringValue("aquisition.applicant.SubSource",user1.getLocale())%>
		</td>
		<td valign="left">
		<span id="subsource">
			
			<%if(aform.getSourceTypeId()==Common.RESUME_SOURCE_AGENCY_ID){%>
			<html:select styleClass="list"  property="sourceId">
			<option value=""></option>
			<bean:define name="applicantForm" property="vendorsList" id="vendorsList" />

            <html:options collection="vendorsList" property="userId"  labelProperty="firstName"/>
			</html:select>
<%} else if(aform.getSourceTypeId()==Common.RESUME_SOURCE_FERERRAL_ID){%>
			<table width="100%">

			<tr><td width="50%"><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%></td> 
			<td><html:text styleClass="textdynamic" property="employeecode" size="30" maxlength="100"/> </td>
			</tr>
			<tr><td width="50%"><%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%></td> 
			<td><html:text styleClass="textdynamic" property="referrerName" size="30" maxlength="200"/> </td>
			</tr>
			<tr><td width="50%"><%=Constant.getResourceStringValue("aquisition.applicant.Referrer_Email",user1.getLocale())%></td> 
			<td> <html:text styleClass="textdynamic" property="referrerEmail" size="30" maxlength="200"/> </td>
			</tr>
			</table>
<%
//}else if(aform.getSourceTypeId()>0){
}else{
%>


			<html:select styleClass="list"  property="sourceId">
			<option value=""></option>
			<bean:define name="applicantForm" property="sourceList" id="sourceList" />

			<html:options collection="sourceList" property="resumeSourcesId"  labelProperty="resumeSourcesName"/>
			</html:select>
<%}%>
			</span>
		</td>
	</tr>
</table>
</div>
<!-- source end -->


<!-- personal info start -->

<div class="msg"><a href="#" rel="toggle[personalappsearch]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.personal.informations",user1.getLocale())%> </div>
<div id="personalappsearch" style="width  100%;">
<table border="0" width="100%" class="div">
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.gender",user1.getLocale())%></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.gender.male",user1.getLocale())%>
			<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("M"))? "Checked=true" : "" %> value="M">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.gender.female",user1.getLocale())%>
			<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("F"))? "Checked=true" : "" %> value="F">
			&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.gender.no",user1.getLocale())%>
			<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("N"))? "Checked=true" : "" %> value="N">
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",user1.getLocale())%></td>
			<td>

             <html:select styleClass="list"  property="dob_criteria" onchange="unhidedateofbirth()">
			<bean:define name="applicantForm" property="criteriaDateList" id="criteriaDateList" />

            <html:options collection="criteriaDateList" property="key"  labelProperty="value"/>
			</html:select>

				<SCRIPT LANGUAGE="JavaScript" ID="jscal3xx">
				var cal3xx = new CalendarPopup("testdiv3");
				cal3xx.showNavigationDropdowns();
				
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
		<INPUT TYPE="text" class="textdynamic" NAME="dateofbirth" readonly="true" value="<%=aform.getDateofbirth()%>" SIZE=25>
		<A HREF="#" onClick="cal3xx.select(document.applicantForm.dateofbirth,'anchor3xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor3xx"><img src="jsp/images/calbtn.gif" width="18" height="18"  alt="Calendar" ></A>
		
		<DIV ID="testdiv3" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
					
					
			
		<SCRIPT LANGUAGE="JavaScript" ID="jscal6xx">
			var cal6xx = new CalendarPopup("testdiv6");
			cal6xx.showNavigationDropdowns();

		</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

		<INPUT TYPE="text" class="textdynamic" NAME="dateofbirth1" readonly="true" value="<%=aform.getDateofbirth1()%>" SIZE=25>
		<A HREF="#" onClick="cal6xx.select(document.applicantForm.dateofbirth1,'anchor6xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor6xx" ID="anchor6xx"><img src="jsp/images/calbtn.gif" width="18" height="18" id="dob_criteriacalimage" alt="Calendar" ></A>
		
		<DIV ID="testdiv6" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>

		</tr>

	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.PassportNo",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="passport_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="passportno" size="30" maxlength="100"/>
		</td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.SSNNo",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="ssn_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="ssnno" size="30" maxlength="100"/>
		</td>
	</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.TaxIdNo",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="taxno_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="taxidno" size="30" maxlength="100"/>
		</td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="email_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="email" size="30" maxlength="200"/>
		</td>
	</tr>

	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Country",user1.getLocale())%></td>
		<td>
			<html:select styleClass="list"  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="applicantForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_States",user1.getLocale())%>......</span>
		</td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.State",user1.getLocale())%></td>
		<td>
		<span id="states">
			<html:select styleClass="list"  property="stateId">
			<option value=""></option>
			<bean:define name="applicantForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
		</td>
	</tr>

	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.City",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="city_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="city" size="30" maxlength="200"/>
		</td>
		</tr>
		<tr>
		<td>
		<%=Constant.getResourceStringValue("aquisition.applicant.FELONY_CONVICTION",user1.getLocale())%>
		</td>
		<td>
		<html:select styleClass="list"  property="felony_conviction" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
		</html:select>
		</td>
	</tr>
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.ZIPCODE",user1.getLocale())%></td>
		<td>
		<%
		
			String zipcodevalue = (StringUtils.isNullOrEmpty(aform.getZipcode())  || aform.getZipcode().equals("0"))?"":String.valueOf(aform.getZipcode());
			String milesWithinvalue = (StringUtils.isNullOrEmpty(aform.getMilesWithin()) || aform.getMilesWithin().equals("0.0"))?"":String.valueOf(aform.getMilesWithin());
		%>
		<html:text styleClass="textdynamic" property="zipcode" size="15" maxlength="10" value="<%=zipcodevalue%>"/> &nbsp;&nbsp;
		<%=Constant.getResourceStringValue("aquisition.applicant.MILESWITHIN",user1.getLocale())%>&nbsp;&nbsp;
		<html:text styleClass="textdynamic" property="milesWithin" size="15" maxlength="5" value="<%=milesWithinvalue%>"/> 
		</td>

	</tr>

</table>

</div>

<!-- personal info end -->

<!-- work preference start -->

<div class="msg"><a href="#" rel="toggle[workpreferenceappsearch]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.work.preference",user1.getLocale())%> </div>
<div id="workpreferenceappsearch" style="width  100%;">
<table border="0" width="100%" class="div">
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_CTC",user1.getLocale())%></td>
		<td>
			<html:select styleClass="list"  property="currectctc_criteria" onchange="unhidecurrectctc()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>
		<%
		
			String currectctcvalue = (aform.getCurrectctc().equals("0"))?"":String.valueOf(aform.getCurrectctc());
			String currectctc1value = (aform.getCurrectctc1().equals("0.0"))?"":String.valueOf(aform.getCurrectctc1());
		%>	
		<html:text styleClass="textdynamic" property="currectctc" size="20" maxlength="50" value="<%=currectctcvalue%>"/>
		<html:text styleClass="textdynamic" property="currectctc1" size="20" maxlength="50" value="<%=currectctc1value%>"/>
		</td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Expected_CTC",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="expectedctc_criteria" onchange="unhideexpectedctc()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>

		<%
		
			String expectedctcvalue = (aform.getExpectedctc().equals("0"))?"":String.valueOf(aform.getExpectedctc());
			String expectedctc1value = (aform.getExpectedctc1().equals("0.0"))?"":String.valueOf(aform.getExpectedctc1());
		%>	

			<html:text styleClass="textdynamic" property="expectedctc" size="20" maxlength="50" value="<%=expectedctcvalue%>"/>
			<html:text styleClass="textdynamic" property="expectedctc1" size="20" maxlength="50" value="<%=expectedctc1value%>"/>
			</td>

		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Notice_period",user1.getLocale())%></td>
		<td>
			<html:select styleClass="list"  property="noticeperiod_criteria" onchange="unhidenoticeperiod()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>

			<%
		
			String noticeperiodcvalue = (StringUtils.isNullOrEmpty(aform.getNoticeperiod()) || aform.getNoticeperiod().equals("0"))?"":String.valueOf(aform.getNoticeperiod());
			String noticeperiod1value = (StringUtils.isNullOrEmpty(aform.getNoticeperiod1()) || aform.getNoticeperiod1().equals("0.0"))?"":String.valueOf(aform.getNoticeperiod1());
		%>	

		<html:text styleClass="textdynamic" property="noticeperiod" size="20" maxlength="20" value="<%=noticeperiodcvalue%>"/>
		
		<html:text styleClass="textdynamic" property="noticeperiod1" size="20" maxlength="20" value="<%=noticeperiod1value%>"/>
		</td>
			</tr>
			<tr>		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_designation",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="currentdesignation_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
			<html:text styleClass="textdynamic" property="currentdesignation" size="30" maxlength="100"/>
			</td>

		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_organization",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="org_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="previousOrganization" size="30" maxlength="200"/>
		</td>
			</tr>
			<tr>		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="noofyearsexp_criteria" onchange="unhidenoofyearsexp()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>
			<%
             String noofyearsexpstr ="";
			 if(aform.getNoofyearsexp() == 0.0 || aform.getNoofyearsexp() == Integer.MAX_VALUE){
				 noofyearsexpstr="";
			 }else{
				 noofyearsexpstr= String.valueOf(aform.getNoofyearsexp());
			 }
			%>
			<html:text styleClass="textdynamic" property="noofyearsexp" size="30" maxlength="5" value="<%=noofyearsexpstr%>"/>

						<%
             String noofyearsexp1value ="";
			 if(aform.getNoofyearsexp1() == 0.0 || aform.getNoofyearsexp1() == Integer.MAX_VALUE){
				 noofyearsexp1value="";
			 }else{
				 noofyearsexp1value= String.valueOf(aform.getNoofyearsexp1());
			 }
			%>
			<html:text styleClass="textdynamic" property="noofyearsexp1" size="20" maxlength="20"  value="<%=noofyearsexp1value%>"/>
			</td>

		</tr>


		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Profile_Header",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="resumeHeader_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="resumeHeader" size="30"  maxlength="100"/>
		</td>
			</tr>
			<tr>		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.EARLIEST_START_DATE",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="earliest_start_date_criteria" onchange="unhideearliest_start_date_criteria()">
			<bean:define name="applicantForm" property="criteriaDateList" id="criteriaDateList" />

            <html:options collection="criteriaDateList" property="key"  labelProperty="value"/>
			</html:select>
			<SCRIPT LANGUAGE="JavaScript" ID="jsca4xx">
				var cal4xx = new CalendarPopup("testdiv4");
				cal4xx.showNavigationDropdowns();
			</SCRIPT>
		<INPUT TYPE="text" class="textdynamic" NAME="earliest_start_date" readonly="true" value="<%=aform.getEarliest_start_date()%>" SIZE=25>
		<A HREF="#" onClick="cal4xx.select(document.applicantForm.earliest_start_date,'anchor4xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor2xx" ID="anchor4xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			<DIV ID="testdiv4" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			
			
			<SCRIPT LANGUAGE="JavaScript" ID="jsca5xx">
				var cal5xx = new CalendarPopup("testdiv5");
				cal5xx.showNavigationDropdowns();
			</SCRIPT>
		<INPUT TYPE="text" class="textdynamic" NAME="earliest_start_date1" readonly="true" value="<%=aform.getEarliest_start_date1()%>" SIZE=25>
		<A HREF="#" ID="anchor5xx" onClick="cal5xx.select(document.applicantForm.earliest_start_date1,'anchor5xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor5xx" ID="anchor5xx"><img src="jsp/images/calbtn.gif" width="18" height="18" id="earliest_start_datecalbutton" alt="Calendar" ></A>
			<DIV ID="testdiv5" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>	
			
			</td>

		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_WEEKENDS",user1.getLocale())%></td>
		<td>
			<html:select styleClass="list"  property="work_on_weekends" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
		</td>
			</tr>
			<tr>	
			<td><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_EVENINGS",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="work_on_evenings" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_OVERTIME",user1.getLocale())%></td>
		<td>
			<html:select styleClass="list"  property="work_on_overtime" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
		</td>
			</tr>
			<tr>		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.WANT_TO_RELOCATE",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="want_to_relocate" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Prefered_location",user1.getLocale())%></td>
		<td>
		<html:select styleClass="list"  property="preferedlocation_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text styleClass="textdynamic" property="preferedlocation" size="30" maxlength="100"/>
		</td>
		


		</tr>

</table>
</div>
<!-- work preference end -->
<!-- education start -->
<div class="msg"><a href="#" rel="toggle[educationappsearch]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.education.details",user1.getLocale())%> </div>
<div id="educationappsearch" style="width  100%;">
<table border="0" width="100%" class="div">
		<tr>
		   <td><%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",user1.getLocale())%></td>
			<td>
			 <html:select styleClass="multipleselect" multiple="true" size="3" property="primarySkills">
			<bean:define name="applicantForm" property="lovList" id="lovList" />
            <html:options collection="lovList" property="technialSkillName"  labelProperty="technialSkillName"/>
			</html:select>
			
			</td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Heighest_Qualification",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="heighestQualification_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
				<html:text styleClass="textdynamic" property="heighestQualification" size="30" maxlength="100"/>
			</td>
		</tr>
		<tr>
		   <td><%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="qualifications_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
			<html:text styleClass="textdynamic" property="qualifications" size="30" maxlength="100"/>
			</td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.COLLEGE_NAME",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="college_name_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
				<html:text styleClass="textdynamic" property="college_name" size="30" maxlength="100"/>
			</td>
		</tr>

		<tr>
		   <td><%=Constant.getResourceStringValue("aquisition.applicant.COLLEGE_GPA",user1.getLocale())%></td>
			<td>
			<html:select styleClass="list"  property="college_GPA_criteria" onchange="unhideCollgeGPA()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />
            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>

			<%
		
			String college_GPAvalue = (StringUtils.isNullOrEmpty(aform.getCollege_GPA()) || aform.getCollege_GPA().equals("0"))?"":String.valueOf(aform.getCollege_GPA());
			String college_GPA1value = (StringUtils.isNullOrEmpty(aform.getCollege_GPA1()) || aform.getCollege_GPA1().equals("0.0"))?"":String.valueOf(aform.getCollege_GPA1());
			%>	

			<html:text styleClass="textdynamic" property="college_GPA" size="20" maxlength="20" value="<%=college_GPAvalue%>"/>
			<html:text styleClass="textdynamic" property="college_GPA1" size="20" maxlength="20" value="<%=college_GPA1value%>"/>
			</td>

		</tr>
</table>
</div>
<!-- education end -->
<!-- advance parameter start -->

<div class="msg"><a href="#" rel="toggle[advanceparameterappsearch]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.advanceparameter",user1.getLocale())%> </div>
<div id="advanceparameterappsearch" style="width  100%;">


<table border="0" width="100%" class="div">
		<tr>
		   <td><%=Constant.getResourceStringValue("aquisition.applicant.LANGUAGES_SPOKEN",user1.getLocale())%></td>
			<td>
		  <html:select styleClass="list"  property="languages_spoken_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		  </html:select>
		   <html:text styleClass="textdynamic" property="languages_spoken" size="30"  maxlength="100"/>
			
			</td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Search.Date_type_colon",user1.getLocale())%></td>
			<td>
				<html:select styleClass="list"  property="appliedcri">
			<bean:define name="applicantForm" property="searchCriDateList" id="searchCriDateList" />

            <html:options collection="searchCriDateList" property="key"  labelProperty="value"/>
			</html:select>
			</td>

		</tr>
		<tr>
			<td>


<%=Constant.getResourceStringValue("aquisition.applicant.Search.From_date_colon",user1.getLocale())%>
</td><td>
<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" class="textdynamic" NAME="searchfromdate" readonly="true" value="<%=(aform.getSearchfromdate() == null)? "" :aform.getSearchfromdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.searchfromdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>
</td>
</tr>
<tr>
<td>

<%=Constant.getResourceStringValue("aquisition.applicant.Search.To_date_colon",user1.getLocale())%>
</td>

			
<td>
<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx2">
var cal1xx2 = new CalendarPopup("testdiv2");
cal1xx2.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" class="textdynamic" NAME="searchtodate" readonly="true" value="<%=(aform.getSearchtodate() == null)? "" :aform.getSearchtodate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx2.select(document.applicantForm.searchtodate,'anchor1xx2','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx2" ID="anchor1xx2"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv2" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
</tr>


		 <tr>
          <td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
			<html:select  property="orgIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrieveURLOrg('applicant.do?method=deptlistmultiple');">
			<bean:define name="applicantForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%> ......</span>

			  
			</td>

		
			</tr>
			<tr>
		

			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select  property="departmentIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrieveProjcodes('applicant.do?method=loadProjectCodeMultiple');">
			<bean:define name="applicantForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
			<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.ProjectCode.Loadpcode",user1.getLocale())%> ......</span>
			</td>
		
		 </tr>
		 <tr>

			<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%></td>
			<td>
			<span id="projectcodes">
			<html:select  property="projectcodeIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrievejobreqlist();">
			<bean:define name="applicantForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
			</td>
			</tr>
			<tr>
		
            		<td><%=Constant.getResourceStringValue("Requisition.screen.REQUISITION_NAME",user1.getLocale())%></td>
			<td>
			
			<span id="jobreqlist">
			<html:select  styleClass="multipleselect" multiple="true" size="3" property="requitionIds">
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />
            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobreqName"/>
			</html:select>
	
			</span>
					
						<span class="textboxlabel" id="loadingjob" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>
			</td>

		 </tr>

	<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.tag",user1.getLocale())%></td>
			<td>
			<html:select   styleClass="multipleselect" multiple="true" size="3" property="tagIds" >
			<bean:define name="applicantForm" property="tagList" id="tagList" />
            <html:options collection="tagList" property="tagId"  labelProperty="tagName"/>
			</html:select>
			</td>


</tr>		

</table>
</div>
<!-- advance parameter end -->
<!-- questionaires start -->

<div class="msg"><a href="#" rel="toggle[questionaires]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.custom.questions",user1.getLocale())%> </div>
<div id="questionaires" style="width  100%;">
<table border="0" width="100%" class="div">
		<tr>
		<td><%=Constant.getResourceStringValue("admin.Questions.Questionnaire",user1.getLocale())%></td>
		<td>
			<html:select styleClass="list"  property="quetionnaireId" onchange="retrieveQuestions();">
			<option value=""></option>
			<bean:define name="applicantForm" property="quetionnaireList" id="quetionnaireList" />

            <html:options collection="quetionnaireList" property="questiongroupId"  labelProperty="questiongroupName"/>
			</html:select>
			<span class="textboxlabel" id="loadingqns" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>

	</td>
		

		</tr>

</table>
<table border="0" width="100%">
<tr>
<td>
		<span id="questionslist">
	
		</span>
</td>
<td>
		<span id="criteralist">
	
		</span>
</td>
<td>
		<div id="filter1"><html:text styleClass="textdynamic" property="filterValue1" size="25" maxlength="100"/></div>
</td>
<td>
		<div id="filter2"><html:text styleClass="textdynamic" property="filterValue2" size="25" maxlength="100"/></div>
</td>


</table>
<table>
<tr>
<td width="50%">
<td align="left">
<div id="filterbutton"><input type="button" name="add" value="<%=Constant.getResourceStringValue("admin.Questions.add.to.filter",user1.getLocale())%>" onClick="saveQuestionFilters()" class="button"/>

			<span class="textboxlabel" id="loadingqnsfilters" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>
			</div>
</td>
</tr>
</table>

<table width="100%">

<span id="questionsfilters">
<%
List<SearchApplicantQuestions> qnscriList = aform.getQuestionCriList();
%>


<% if(qnscriList != null) {
for(int i=0;i<qnscriList.size();i++){
	SearchApplicantQuestions saq = qnscriList.get(i);
%>
<li><b><%=saq.getQuestion().getQuestionName()%></b> (<%=saq.getQuestion().getTypeVal()%>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<% if(saq.getQuestion().getTypeVal()!=null && saq.getQuestion().getTypeVal().equals("dropdown")){%>
EQUALS &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=saq.getAnswerOption()%>
<%}else{%>

<%=saq.getFiltercri()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<%=saq.getFilterValue1()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<% if(saq.getFilterValue2() != null){%>

<%=saq.getFilterValue2()%>

<%}%>
<%}%>
<a href="#" onClick="deletequestionsfilters('<%=saq.getSearchuuid()%>','<%=saq.getUuid()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete skill" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete.qns.filter",user1.getLocale())%>" height="20"  width="19"/></a>
<br>
<%}}%>
	
</span>
</table>
</div>
<!-- questionaires end -->

<!-- exam start -->

<div class="msg"><a href="#" rel="toggle[examdv]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>Exams </div>
<div id="examdv" style="width  100%;">

<table border="0" width="100%" class="div">
<tr>
		<td>Exam</td>
		<td>
			<html:select styleClass="list"  property="mockQuestionSetId">
			<option value=""></option>
			<bean:define name="applicantForm" property="mockQuestionSetList" id="mockQuestionSetList" />

            <html:options collection="mockQuestionSetList" property="catId"  labelProperty="name"/>
			</html:select>
			
	    </td>
</tr>
<tr>
        <td> Percentage of marks </td>
		<td>
		<html:select styleClass="list"  property="mockquestionset_criteria" onchange="unhideMockQuestionSet()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
		</html:select>
		</td>
</tr>
<tr>
        <td> </td>
        <td>
		<html:text styleClass="textdynamic" property="mockquestionsetValue1" size="20" maxlength="20"/>  
		<html:text styleClass="textdynamic" property="mockquestionsetValue2" size="20" maxlength="20"/>
		</td>
		

		</tr>

</table>
</div>
</div>
<!-- exam end -->

<!-- custom filed start -->

<%@ include file="customvariableApplicantSearch.jsp" %>
<!-- custom filed end -->

<table>
<tr>
<td>

 <%=Constant.getResourceStringValue("No_Of_Rows_colon",user1.getLocale())%>
 			<html:select styleClass="list"  property="noofrows">
			<bean:define name="applicantForm" property="paginationRowsList" id="paginationRowsList" />
            <html:options collection="paginationRowsList" property="key"  labelProperty="value"/>
			</html:select>

</td>
<td>
<input type="button" onClick="searchapplicants()" id="applicantsearchbutton" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" class="button">
  <input type="button" name="search1"  value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button"/>
<!--  <input type="button" name="search1" value=".....<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()">-->
<% //if(searchpagedisplay != null && searchpagedisplay.equals("yes")){%>
<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.exporttoexcel",user1.getLocale())%>" onClick="exporttoexcel()" class="button">
<%//}%>
<%if(exporttoexcel != null && exporttoexcel.equals("yes")){
%>
<div class="msg"><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.export.job.started",user1.getLocale())%> <a href="bulkuploadtask.do?method=mybulktasklist"><%=Constant.getResourceStringValue("aquisition.process.job.page",user1.getLocale())%></a></font></div>
<%}%>

</td>

</tr>
<tr>
<td valign="left">

</td>
<td>

</td>
</tr>
</table>
</html:form>
			

	

	</div>
</div>

		<!-- east pane -->
		<div id="tabs-east" class="ui-layout-east no-padding">

		<h3 class="ui-widget-header">Searches</h3>
    
		<ul class="allow-overflow">
			<li><a href="#tab-panel-east-1">Saved</a></li>
			<li><a href="#tab-panel-east-2">Recent</a></li>
			
		</ul>

			<div class="ui-layout-content ui-widget-content" style="border-top: 0;">
				<div id="tab-panel-east-1" class="full-height no-padding">
			
					<table>
					
						<% 
						int k2=0;
				boolean issavessearch= false;
				List savedsearchesList = aform.getSavedsearchesList();
				if(savedsearchesList != null && savedsearchesList.size()>0){
					for(int i=0;i<savedsearchesList.size();i++){
						SearchApplicant searchapp = (SearchApplicant)savedsearchesList.get(i);
							if(searchapp.getSaveshare() != null && !searchapp.getSaveshare().equals(Common.SEARCH_RECENT)){
								issavessearch = true;
							String bgcolor = "";
							if(k2%2 == 0)bgcolor ="#B2D2FF";
                            k2++;
							%>


					<tr bgcolor=<%=bgcolor%>>
				<td>
				<font size="2">
				<a href="#" onClick="openUrl('applicant.do?method=viewSavedSearchResult&searchuuid=<%=searchapp.getSearchuuid()%>')"><%=searchapp.getSavedsearchname()%></a>&nbsp;<a  href="#" onClick="deletesavesearch('<%=searchapp.getSearchuuid()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete" title="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" height="16"  width="16"/></a> 
				</font>
				</td>
				</tr>

				<%
				}
				}
				}%>
				<% if(!issavessearch){%>
				<tr>
				<td>
				<%=Constant.getResourceStringValue("aquisition.applicant.no.saved.searches",user1.getLocale())%>
				</td>
				</tr>
				<%}%>

				</table>
					
				</div>

				<div id="tab-panel-east-2" class="full-height no-padding">
				<table>
					<%
					int k1=0;
					issavessearch = false;
					if(savedsearchesList != null && savedsearchesList.size()>0){
						for(int i=0;i<savedsearchesList.size();i++){
							SearchApplicant searchapp = (SearchApplicant)savedsearchesList.get(i);
							if(searchapp.getSaveshare() != null && searchapp.getSaveshare().equals(Common.SEARCH_RECENT)){
								issavessearch = true;
							String bgcolor = "";
							if(k1%2 == 0)bgcolor ="#B2D2FF";
                            k1++;
							%>      


					<tr bgcolor=<%=bgcolor%>>
					<td>
					<font size="2">
					<a href="#" onClick="openUrl('applicant.do?method=viewSavedSearchResult&searchuuid=<%=searchapp.getSearchuuid()%>')"><%=searchapp.getSavedsearchname()%></a>&nbsp;<a  href="#" onClick="deletesavesearch('<%=searchapp.getSearchuuid()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete" title="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" height="16"  width="16"/></a> 
					</font>
					<br>

					</td>
					</tr>
					<%
					}
					}
				    }%>
				<% if(!issavessearch){%>
				<tr>
				<td>
				<%=Constant.getResourceStringValue("aquisition.applicant.no.recent.searches",user1.getLocale())%>
				</td>
				</tr>
				<%}%>
				
					</table>
				</div>
		
			</div>

		</div>



</body>
</html> 
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.ALL_APP_SEARCH_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.ALL_APP_SEARCH_SCREEN);

%>
<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&backtolisturl=applicant.do?method=applicantsbyvendor&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "'"+ ">" + sData + "</a>";
        };

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };
	var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("isofferownerGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("offerownerId")+ "'"+","+"'"+ oRecord.getData("isofferownerGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };


var formatApplicantOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownernamegroup");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownergroupId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else if(oRecord.getData("isGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownername");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        };

	var formatUrlForRecruiter = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
			if(oRecord.getData("recruiterId")!=0){
	        if(oRecord.getData("recruitergroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("recruitergroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("recruiterId")+ "'"+","+"'"+ oRecord.getData("recruitergroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        
        };
    
	var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
		
       var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
       /* var editreqd = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=jobreqdetails&approverstatus=yes&offerapplicant=yes&jobreqId="+oRecord.getData("reqId")+"&secureid="+oRecord.getData("juuid")+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800"+"'"+")>"+sData+"</a>";*/
		elCell.innerHTML = editreqd;
        };
	var Dom = YAHOO.util.Dom,
				Event = YAHOO.util.Event,
				log = function (msg) {
					YAHOO.log(msg,'info','deleteRowsBy demo');
				};

YAHOO.widget.DataTable.prototype.deleteRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                sendbulkresumeforscreening(checknumber);
				}
				
				this.render();
			};


	YAHOO.widget.DataTable.prototype.deleteRowsBy1 = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			        
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("uuid") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                markdeletebulk(checknumber);
				}
				
				this.render();
			};

	
	YAHOO.widget.DataTable.prototype.forAllRecords = function (fn,scope) {
		
		if (!Lang.isFunction(fn)) {return;}
		scope || (scope = this);
		for (var rs = this.getRecordSet(), l = rs.getLength(), i = 0; i < l; i++) {
			if (fn.call(scope, rs.getRecord(i), i) === false) {return;}
		}
	};
	
	
	// Column definitions

    var myColumnDefs = [
		{key:"Select",label:'<input type="checkbox" id="SelectAll"/>', width:"30", formatter:"checkbox"},
			{key:"applicantId", hidden:true},
			{key:"uuid", hidden:true},
		{key:"juuid", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl,width:180},
             <%=strcolumncontent%>
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%> ", sortable:false, resizeable:true,formatter:editUrl}
        ];


    if(isapplicationsubmitted == false){

myColumnDefs = [
		{key:"Select",label:'<input type="checkbox" id="SelectAll"/>', width:"30", formatter:"checkbox"},
		{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
	{key:"juuid", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl,width:180},
             <%=strcolumncontent%>
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
        ];

	}


    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("applicant.do?method=searchapplicantListPage&searchuuid=<%=aform.getSearchcriteria().getSearchuuid()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
	 //myDataSource.connMethodPost = true; 
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
             <%=strkeycontent%>
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    var noofrowspagin = '<%=noofrowsinpagination%>';

    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results="+noofrowspagin, // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:noofrowspagin }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

	myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.results",user1.getLocale())%> : ' + my_row_total;
   });

    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    


myDataTable.subscribe('checkboxClickEvent', function(oArgs) {
	   
		var elCheckbox = oArgs.target;
		var newValue = elCheckbox.checked;
		var record = this.getRecord(elCheckbox);
		var column = this.getColumn(elCheckbox);
		record.setData(column.key,newValue);
		
		var allChecked = true;
		this.forAllRecords(function (r) {
			 
			if (!r.getData('Select')) {
				allChecked = false;
				return false;
				 
			}
		});
		Dom.get('SelectAll').checked = allChecked;
	});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					
					for (var i=0;i < l; i++) {
						if(rs.getRecord(i) != null){
						rs.getRecord(i).setData('Select',check);
						}
					}
					this.render();
				}
			});
			


			
			Event.on('delete','click', function() {
				myDataTable.deleteRowsBy(function (data) {
					return data.Select;
				});
			});

		Event.on('delete1','click', function() {
				myDataTable.deleteRowsBy1(function (data) {
					return data.Select;
				});
			});

    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

