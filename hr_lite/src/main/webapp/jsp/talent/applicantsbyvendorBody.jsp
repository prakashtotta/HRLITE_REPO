<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/resize.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/csrfInjection.jsp" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

%>
<%@ include file="../common/collapsable.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<script type="text/javascript" src="jsp/js/jquery.blockUI.js"></script>
<script type="text/javascript">

animatedcollapse.addDiv('personal', 'fade=0,speed=400,persist=1,group=pets,hide=1')
animatedcollapse.addDiv('advanceparameter', 'fade=0,speed=400,persist=1,group=pets0,hide=1')
animatedcollapse.addDiv('savedsearches', 'fade=0,speed=400,group=pets1,hide=1')
animatedcollapse.addDiv('recentsearches', 'fade=0,speed=400,group=pets2,hide=1')
animatedcollapse.addDiv('source', 'fade=0,speed=400,persist=1,group=pets3,hide=1')
animatedcollapse.addDiv('workpreference', 'fade=0,speed=400,persist=1,group=pets4,hide=1')
animatedcollapse.addDiv('education', 'fade=0,speed=400,persist=1,group=pets5,hide=1')
animatedcollapse.addDiv('questionaires', 'fade=0,speed=400,persist=1,group=pets6,hide=1')
animatedcollapse.addDiv('customvariable', 'fade=0,speed=400,persist=1,group=pets7,hide=1')


animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}

animatedcollapse.init()

</script>

<style>
 
    #resize {
        border: 0px solid black;
        height: 100%;
        width: 100%;
        background-color: #fff;
    }
    #resize div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }

	.singleselect { 
	 width: 400px;	
	}
	.multipleselect {
		 width: 250px;	
	}
</style>

  <link href="jsp/css/cal.cs" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jsp/js/CalendarPopup.js"></script>


<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>


<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />

<%@ include file="../talent/applicantserachJS.jsp" %>

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
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>',url,messageret);
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




$(document).ready(function() {$('#applicantsearchbutton').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
   test();
	 }); 
}); 

function test() { 

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
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.save_search",user1.getLocale())%>',url,400,600, messageret);

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
<br>
 <%=Constant.getResourceStringValue("aquisition.applicant.Search_Applicants",user1.getLocale())%>  <br>
 
<br>


<body class="yui-skin-sam" onLoad="init()">

<html:form action="/applicant.do?method=searchapplicantbyvendor" styleId="applicantsearch">


<div id="resize">
<div class="data">
<div style="overflow: scroll;">
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.keyword",user1.getLocale())%></td>
<td><html:text property="keywords" size="47" maxlength="200"/></td>

<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
<td>
<html:select  property="applicantNo_criteria" onchange="unhideApplicantId()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
</html:select>
<html:text property="applicantNo" size="20" maxlength="20"/>
<%
String app1value = (aform.getApplicantId1()==Integer.MAX_VALUE)?"":String.valueOf(aform.getApplicantId1());
%>
<html:text property="applicantId1" size="20" maxlength="20"  value="<%=app1value%>"/></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
<td>
<html:select  property="applicantName_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
</html:select>
<html:text property="fullName" size="30" maxlength="200"/></td>


<td><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%></td>
			<td>
			<html:select styleClass="multipleselect" multiple="true" size="3" property="interviewStates">
			<bean:define name="applicantForm" property="interviewstateList" id="interviewstateList" />
            <html:options collection="interviewstateList" property="interviewstateCode"  labelProperty="interviewstateName"/>
			</html:select>
			</td>
</tr>

</table>

<!-- source start -->
 <div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[source]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.resume.source",user1.getLocale())%> </div>
<div id="source" style="width  100%;">
<table border="0" width="100%">
	<tr>
		<td width="10%"><%=Constant.getResourceStringValue("aquisition.applicant.Source",user1.getLocale())%></td>
		<td width="40%" align="left">
			<html:select  property="sourceTypeId" onchange="retrieveURLresumesource();">
			<option value=""></option>
			<bean:define name="applicantForm" property="sourceTypeList" id="sourceTypeList" />

            <html:options collection="sourceTypeList" property="resumeSourceTypeId"  labelProperty="resumeSourceTypeName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingsource" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_sub_source",user1.getLocale())%>......</span>
		</td>
		<td width="10%">
		<%=Constant.getResourceStringValue("aquisition.applicant.SubSource",user1.getLocale())%>
		</td>
		<td width="40%" valign="left">
		<span id="subsource">
			
			<%if(aform.getSourceTypeId()==Common.RESUME_SOURCE_AGENCY_ID){%>
			<html:select  property="sourceId">
			<option value=""></option>
			<bean:define name="applicantForm" property="vendorsList" id="vendorsList" />

            <html:options collection="vendorsList" property="userId"  labelProperty="firstName"/>
			</html:select>
<%} else if(aform.getSourceTypeId()==Common.RESUME_SOURCE_FERERRAL_ID){%>
			<table width="100%">

			<tr><td width="50%"><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%></td> 
			<td><html:text property="employeecode" size="30" maxlength="100"/> </td>
			</tr>
			<tr><td width="50%"><%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%></td> 
			<td><html:text property="referrerName" size="30" maxlength="200"/> </td>
			</tr>
			<tr><td width="50%"><%=Constant.getResourceStringValue("aquisition.applicant.Referrer_Email",user1.getLocale())%></td> 
			<td> <html:text property="referrerEmail" size="30" maxlength="200"/> </td>
			</tr>
			</table>
<%
//}else if(aform.getSourceTypeId()>0){
}else{
%>


			<html:select  property="sourceId">
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

<div style="background: #f3f3f3;"><a href="#" rel="toggle[personal]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.personal.informations",user1.getLocale())%> </div>
<div id="personal" style="width  100%;">
<table border="0" width="100%">
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.gender",user1.getLocale())%></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.gender.male",user1.getLocale())%>
			<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("M"))? "Checked=true" : "" %> value="M">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.gender.female",user1.getLocale())%>
			<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("F"))? "Checked=true" : "" %> value="F">
			&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.gender.no",user1.getLocale())%>
			<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("N"))? "Checked=true" : "" %> value="N">
			</td>
		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",user1.getLocale())%></td>
			<td>

             <html:select  property="dob_criteria" onchange="unhidedateofbirth()">
			<bean:define name="applicantForm" property="criteriaDateList" id="criteriaDateList" />

            <html:options collection="criteriaDateList" property="key"  labelProperty="value"/>
			</html:select>

				<SCRIPT LANGUAGE="JavaScript" ID="jscal3xx">
				var cal3xx = new CalendarPopup("testdiv3");
				cal3xx.showNavigationDropdowns();
				
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
		<INPUT TYPE="text" NAME="dateofbirth" readonly="true" value="<%=aform.getDateofbirth()%>" SIZE=25>
		<A HREF="#" onClick="cal3xx.select(document.applicantForm.dateofbirth,'anchor3xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor3xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
		<DIV ID="testdiv3" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
					
					
			
		<SCRIPT LANGUAGE="JavaScript" ID="jscal6xx">
			var cal6xx = new CalendarPopup("testdiv6");
			cal6xx.showNavigationDropdowns();

		</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

		<INPUT TYPE="text" NAME="dateofbirth1" readonly="true" value="<%=aform.getDateofbirth1()%>" SIZE=25>
		<A HREF="#" onClick="cal6xx.select(document.applicantForm.dateofbirth1,'anchor6xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor6xx" ID="anchor6xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
		<DIV ID="testdiv6" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>

		</tr>

	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.PassportNo",user1.getLocale())%></td>
		<td>
		<html:select  property="passport_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="passportno" size="30" maxlength="100"/>
		</td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.SSNNo",user1.getLocale())%></td>
		<td>
		<html:select  property="ssn_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="ssnno" size="30" maxlength="100"/>
		</td>
	</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.TaxIdNo",user1.getLocale())%></td>
		<td>
		<html:select  property="taxno_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="taxidno" size="30" maxlength="100"/>
		</td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
		<td>
		<html:select  property="email_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="email" size="30" maxlength="200"/>
		</td>
	</tr>

	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Country",user1.getLocale())%></td>
		<td>
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="applicantForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_States",user1.getLocale())%>......</span>
		</td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.State",user1.getLocale())%></td>
		<td>
		<span id="states">
			<html:select  property="stateId">
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
		<html:select  property="city_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="city" size="30" maxlength="200"/>
		</td>
		<td>
		<%=Constant.getResourceStringValue("aquisition.applicant.FELONY_CONVICTION",user1.getLocale())%>
		</td>
		<td>
		<html:select  property="felony_conviction" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
		</html:select>
		</td>
	</tr>
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.ZIPCODE",user1.getLocale())%></td>
		<td>
		<html:text property="zipcode" size="15" maxlength="10"/> &nbsp;&nbsp;
		<%=Constant.getResourceStringValue("aquisition.applicant.MILESWITHIN",user1.getLocale())%>&nbsp;&nbsp;
		<html:text property="milesWithin" size="15" maxlength="5"/> 
		</td>
		<td>
		
		</td>
		<td>
		
		</td>
	</tr>

</table>

</div>

<!-- personal info end -->
<!-- work preference start -->

<div style="background: #f3f3f3;"><a href="#" rel="toggle[workpreference]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.work.preference",user1.getLocale())%> </div>
<div id="workpreference" style="width  100%;">
<table border="0" width="100%">
		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_CTC",user1.getLocale())%></td>
		<td>
			<html:select  property="currectctc_criteria" onchange="unhidecurrectctc()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>
			
		<html:text property="currectctc" size="20" maxlength="50"/>
		<html:text property="currectctc1" size="20" maxlength="50"/>
		</td>
		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Expected_CTC",user1.getLocale())%></td>
			<td>
			<html:select  property="expectedctc_criteria" onchange="unhideexpectedctc()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>
			<html:text property="expectedctc" size="20" maxlength="50"/>
			<html:text property="expectedctc1" size="20" maxlength="50"/>
			</td>

		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Notice_period",user1.getLocale())%></td>
		<td>
			<html:select  property="noticeperiod_criteria" onchange="unhidenoticeperiod()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />

            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>
		<html:text property="noticeperiod" size="20" maxlength="20"/>
		
		<html:text property="noticeperiod1" size="20" maxlength="20"/>
		</td>
		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_designation",user1.getLocale())%></td>
			<td>
			<html:select  property="currentdesignation_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
			<html:text property="currentdesignation" size="30" maxlength="100"/>
			</td>

		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_organization",user1.getLocale())%></td>
		<td>
		<html:select  property="org_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="previousOrganization" size="30" maxlength="200"/>
		</td>
		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%></td>
			<td>
			<html:select  property="noofyearsexp_criteria" onchange="unhidenoofyearsexp()">
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
			<html:text property="noofyearsexp" size="30" maxlength="5" value="<%=noofyearsexpstr%>"/>

						<%
             String noofyearsexp1value ="";
			 if(aform.getNoofyearsexp1() == 0.0 || aform.getNoofyearsexp1() == Integer.MAX_VALUE){
				 noofyearsexp1value="";
			 }else{
				 noofyearsexp1value= String.valueOf(aform.getNoofyearsexp1());
			 }
			%>
			<html:text property="noofyearsexp1" size="20" maxlength="20"  value="<%=noofyearsexp1value%>"/>
			</td>

		</tr>


		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Profile_Header",user1.getLocale())%></td>
		<td>
		<html:select  property="resumeHeader_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="resumeHeader" size="30"  maxlength="100"/>
		</td>
		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.EARLIEST_START_DATE",user1.getLocale())%></td>
			<td>
			<html:select  property="earliest_start_date_criteria" onchange="unhideearliest_start_date_criteria()">
			<bean:define name="applicantForm" property="criteriaDateList" id="criteriaDateList" />

            <html:options collection="criteriaDateList" property="key"  labelProperty="value"/>
			</html:select>
			<SCRIPT LANGUAGE="JavaScript" ID="jsca4xx">
				var cal4xx = new CalendarPopup("testdiv4");
				cal4xx.showNavigationDropdowns();
			</SCRIPT>
		<INPUT TYPE="text" NAME="earliest_start_date" readonly="true" value="<%=aform.getEarliest_start_date()%>" SIZE=25>
		<A HREF="#" onClick="cal4xx.select(document.applicantForm.earliest_start_date,'anchor4xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor2xx" ID="anchor4xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			<DIV ID="testdiv4" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			
			
			<SCRIPT LANGUAGE="JavaScript" ID="jsca5xx">
				var cal5xx = new CalendarPopup("testdiv5");
				cal5xx.showNavigationDropdowns();
			</SCRIPT>
		<INPUT TYPE="text" NAME="earliest_start_date1" readonly="true" value="<%=aform.getEarliest_start_date1()%>" SIZE=25>
		<A HREF="#" ID="anchor5xx" onClick="cal5xx.select(document.applicantForm.earliest_start_date1,'anchor5xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor5xx" ID="anchor5xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			<DIV ID="testdiv5" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>	
			
			</td>

		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_WEEKENDS",user1.getLocale())%></td>
		<td>
			<html:select  property="work_on_weekends" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
		</td>
		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_EVENINGS",user1.getLocale())%></td>
			<td>
			<html:select  property="work_on_evenings" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_OVERTIME",user1.getLocale())%></td>
		<td>
			<html:select  property="work_on_overtime" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
		</td>
		
			<td><%=Constant.getResourceStringValue("aquisition.applicant.WANT_TO_RELOCATE",user1.getLocale())%></td>
			<td>
			<html:select  property="want_to_relocate" >
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Prefered_location",user1.getLocale())%></td>
		<td>
		<html:select  property="preferedlocation_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		</html:select>
		<html:text property="preferedlocation" size="30" maxlength="100"/>
		</td>
		
			<td></td>
			<td>
		
			</td>

		</tr>

</table>
</div>
<!-- work preference end -->
<!-- education start -->
<div style="background: #f3f3f3;"><a href="#" rel="toggle[education]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.education.details",user1.getLocale())%> </div>
<div id="education" style="width  100%;">
<table border="0" width="100%">
		<tr>
		   <td><%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",user1.getLocale())%></td>
			<td>
			 <html:select styleClass="multipleselect" multiple="true" size="3" property="primarySkills">
			<bean:define name="applicantForm" property="lovList" id="lovList" />
            <html:options collection="lovList" property="lovListValueCode"  labelProperty="lovListValueName"/>
			</html:select>
			
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Heighest_Qualification",user1.getLocale())%></td>
			<td>
			<html:select  property="heighestQualification_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
				<html:text property="heighestQualification" size="30" maxlength="100"/>
			</td>
		</tr>
		<tr>
		   <td><%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%></td>
			<td>
			<html:select  property="qualifications_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
			<html:text property="qualifications" size="30" maxlength="100"/>
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.COLLEGE_NAME",user1.getLocale())%></td>
			<td>
			<html:select  property="college_name_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />
            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
			</html:select>
				<html:text property="college_name" size="30" maxlength="100"/>
			</td>
		</tr>

		<tr>
		   <td><%=Constant.getResourceStringValue("aquisition.applicant.COLLEGE_GPA",user1.getLocale())%></td>
			<td>
			<html:select  property="college_GPA_criteria" onchange="unhideCollgeGPA()">
			<bean:define name="applicantForm" property="criteriaNumericList" id="criteriaNumericList" />
            <html:options collection="criteriaNumericList" property="key"  labelProperty="value"/>
			</html:select>

          <%
             String collegegpastr ="";
			 if(aform.getCollege_GPA() != null && aform.getCollege_GPA().length()>0){
				 
				 if(aform.getCollege_GPA().equals("0.0")){
				 collegegpastr="";
				 }else{
					 collegegpastr = aform.getCollege_GPA();
				 }
			 }else{
				 collegegpastr = aform.getCollege_GPA();
			 }
			%>

			<html:text property="college_GPA" size="20" maxlength="20"/>
			<html:text property="college_GPA1" size="20" maxlength="20"/>
			</td>
			<td></td>
			<td>
			
			</td>
		</tr>
</table>
</div>
<!-- education end -->
<!-- advance parameter start -->

<div style="background: #f3f3f3;"><a href="#" rel="toggle[advanceparameter]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.advanceparameter",user1.getLocale())%> </div>
<div id="advanceparameter" style="width  100%;">


<table border="0" width="100%">
		<tr>
		   <td><%=Constant.getResourceStringValue("aquisition.applicant.LANGUAGES_SPOKEN",user1.getLocale())%></td>
			<td>
		  <html:select  property="languages_spoken_criteria">
			<bean:define name="applicantForm" property="criteriaStringList" id="criteriaStringList" />

            <html:options collection="criteriaStringList" property="key"  labelProperty="value"/>
		  </html:select>
		   <html:text property="languages_spoken" size="30"  maxlength="100"/>
			
			</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Search.Date_type_colon",user1.getLocale())%></td>
			<td>
				<html:select  property="appliedcri">
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

<INPUT TYPE="text" NAME="searchfromdate" readonly="true" value="<%=(aform.getSearchfromdate() == null)? "" :aform.getSearchfromdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.searchfromdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>
		
<td>
<%=Constant.getResourceStringValue("aquisition.applicant.Search.To_date_colon",user1.getLocale())%>
</td><td>
<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx2">
var cal1xx2 = new CalendarPopup("testdiv2");
cal1xx2.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="searchtodate" readonly="true" value="<%=(aform.getSearchtodate() == null)? "" :aform.getSearchtodate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx2.select(document.applicantForm.searchtodate,'anchor1xx2','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx2" ID="anchor1xx2"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv2" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
</tr>


		 <tr>
          <td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
			<html:select property="orgIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrieveURLOrg('applicant.do?method=deptlistmultiple');">
			<bean:define name="applicantForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%> ......</span>

			  
			</td>

		

		

			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="departmentIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrieveProjcodes('applicant.do?method=loadProjectCodeMultiple');">
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
			<html:select property="projectcodeIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrievejobreqlist();">
			<bean:define name="applicantForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
			</td>
		
            		<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
			<td>
			
			<span id="jobreqlist">
			<html:select styleClass="multipleselect" multiple="true" size="3" property="requitionIds">
			<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />
            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
	
			</span>
					
						<span class="textboxlabel" id="loadingjob" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>
			</td>

		 </tr>

	<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.tag",user1.getLocale())%></td>
			<td>
			<html:select  styleClass="multipleselect" multiple="true" size="3" property="tagIds" >
			<bean:define name="applicantForm" property="tagList" id="tagList" />
            <html:options collection="tagList" property="tagId"  labelProperty="tagName"/>
			</html:select>
			</td>

		
			<td></td>
			<td>
			
			</td>
</tr>		

</table>
</div>

<!-- advance parameter end -->
<!-- questionaires start -->

<div style="background: #f3f3f3;"><a href="#" rel="toggle[questionaires]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.custom.questions",user1.getLocale())%> </div>
<div id="questionaires" style="width  100%;">
<table border="0" width="100%">
		<tr>
		<td width="10%"><%=Constant.getResourceStringValue("admin.Questions.Questionnaire",user1.getLocale())%></td>
		<td width="40%">
			<html:select styleClass="singleselect" property="quetionnaireId" onchange="retrieveQuestions();">
			<option value=""></option>
			<bean:define name="applicantForm" property="quetionnaireList" id="quetionnaireList" />

            <html:options collection="quetionnaireList" property="questiongroupId"  labelProperty="questiongroupName"/>
			</html:select>
			<span class="textboxlabel" id="loadingqns" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>

	</td>
		
			<td width="10%">
			</td>
			<td width="40%">		
			</td>

		</tr>

</table>
<table border="0" width="100%">
<tr>
<td width="40%">
		<span id="questionslist">
	
		</span>
</td>
<td width="20%">
		<span id="criteralist">
	
		</span>
</td>
<td>
		<div id="filter1"><html:text property="filterValue1" size="25" maxlength="100"/></div>
</td>
<td>
			<div id="filter2"><html:text property="filterValue2" size="25" maxlength="100"/></div>
</td>

<tr>
<td width="40%">

	<div id="filterbutton"><input type="button" name="add" value="<%=Constant.getResourceStringValue("admin.Questions.add.to.filter",user1.getLocale())%>" onClick="saveQuestionFilters()" class="button"/>
			<span class="textboxlabel" id="loadingqnsfilters" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>
			</div>
</td>
<td></td><td></td><td></td>
</tr>

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
<!-- custom filed start -->

<%@ include file="customvariableApplicantSearch.jsp" %>
<!-- custom filed end -->

<%
String exporttoexcel = (String)request.getAttribute("exporttoexcel");
%>
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
<input type="button" id="applicantsearchbutton" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" class="button">
  <input type="button" name="search1"  value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button"/>
<!--  <input type="button" name="search1" value=".....<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()">-->
<% if(searchpagedisplay != null && searchpagedisplay.equals("yes")){%>
<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.exporttoexcel",user1.getLocale())%>" onClick="exporttoexcel()" class="button">
<%}%>
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



<table width="100%">
<tr>
<td>
<% if(savedsearch != null && savedsearch.equals("yes")){%>
<div class="msg"><font color="white"><b><%=Constant.getResourceStringValue("aquisition.applicant.searchsavemsg",user1.getLocale())%></b></font><br></div>
 <%}%>
 </td>
 </tr>
</table>
<!--saved search start -->
<div style="background: #f3f3f3;"><a href="#" rel="toggle[savedsearches]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("aquisition.applicant.saved.searches",user1.getLocale())%> </div>
<div id="savedsearches" style="width  100%;">
<table>
<% 
List savedsearchesList = aform.getSavedsearchesList();
if(savedsearchesList != null && savedsearchesList.size()>0){
%>
<tr>
<td>
</td>
</tr>
<tr>
<td>
<%
	int k=0;
	for(int i=0;i<savedsearchesList.size();i++){
SearchApplicant searchapp = (SearchApplicant)savedsearchesList.get(i);
if(searchapp.getSaveshare() != null && !searchapp.getSaveshare().equals(Common.SEARCH_RECENT)){
%>


<a href="applicant.do?method=viewSavedSearchResult&searchuuid=<%=searchapp.getSearchuuid()%>"><%=searchapp.getSavedsearchname()%></a>&nbsp;<a  href="#" onClick="deletesavesearch('<%=searchapp.getSearchuuid()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete" title="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" height="16"  width="16"/></a>&nbsp;&nbsp;&nbsp;&nbsp;


<%
	k++;
   if(k>4){
%>
<br>
<%
	k=0;
	}
%>
<%
}
}
%>
 </td>
</tr>
<%
}
%>

</table>
</div>
<!--saved search end -->
<!--recent search start -->
<div style="background: #f3f3f3;"><a href="#" rel="toggle[recentsearches]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("aquisition.applicant.most.recent.searches",user1.getLocale())%> </div>
<div id="recentsearches" style="width  100%;">
<table>
<%
if(savedsearchesList != null && savedsearchesList.size()>0){
%>
<tr>
<td>
</td>
</tr>
<tr>
<td>
<%  int k=0;
	for(int i=0;i<savedsearchesList.size();i++){
SearchApplicant searchapp = (SearchApplicant)savedsearchesList.get(i);
if(searchapp.getSaveshare() != null && searchapp.getSaveshare().equals(Common.SEARCH_RECENT)){
%>


<a href="applicant.do?method=viewSavedSearchResult&searchuuid=<%=searchapp.getSearchuuid()%>"><%=searchapp.getSavedsearchname()%></a>&nbsp;<a  href="#" onClick="deletesavesearch('<%=searchapp.getSearchuuid()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete" title="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" height="16"  width="16"/></a>&nbsp;&nbsp;&nbsp;&nbsp;


<%
	k++;
   if(k>4){
%>
<br>
<%
	k=0;
	}
%>
<%
}
}
%>
 </td>
</tr>
<%
}
%>
 

</table>
</div>
<!--recent search end -->
</div>
</div>
</div>

</html:form>



<% 
//if(searchpagedisplay != null && searchpagedisplay.equals("yes"))
//{
	
	String fromdate = null;
	String todate = null;
	if(!StringUtils.isNullOrEmpty(aform.getSearchfromdate())){
            fromdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchfromdate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	if(!StringUtils.isNullOrEmpty(aform.getSearchtodate())){
            todate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchtodate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	
	
	
	%>




<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.ALL_APP_SEARCH_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.ALL_APP_SEARCH_SCREEN);

%>



<table>
<tr>
<td><div id="toalrecords"></div></td>
<td><a  href="#" onClick="javascript:createapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a></td>
<%
	

 if(isapplicationsubmitted != null && isapplicationsubmitted.equals("true")){
	


	 %>
	 <td>
 <div class="bd">
			<div id="container"></div>
			<a href="#" id="delete"><%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%></a>
			
		</div>
</td>
<%}%>
 <td>
 <div class="bd">
			<div id="container"></div>
			<a href="#" id="delete1"><%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%></a>
			
		</div>

</td>
<td>

<a  href="#" onClick="javascript:exportapplicants()"><%=Constant.getResourceStringValue("aquisition.applicant.Exportapplicants",user1.getLocale())%></a>&nbsp;&nbsp;<a href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
</td>
</tr>
</table>

	<span id="applicantdata">

<table align="right">
<tr>
<td>
<% if(searchpagedisplay != null && searchpagedisplay.equals("yes")){%>
<input type="button" name="savesearch" value="<%=Constant.getResourceStringValue("aquisition.applicant.save_search",user1.getLocale())%>" onClick="savesearchdata1('<%=aform.getSearchcriteria().getSearchuuid()%>')">
<%}%>
</td>

</tr>
</table>

<div id="dynamicdata"></div>


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
		
  //      var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
        var editreqd = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=jobreqdetails&approverstatus=yes&offerapplicant=yes&jobreqId="+oRecord.getData("reqId")+"&secureid="+oRecord.getData("juuid")+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800"+"'"+")>"+sData+"</a>";
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

</span>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

<script>



(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var resize = new YAHOO.util.Resize('resize');
})();
</script>



</body>

