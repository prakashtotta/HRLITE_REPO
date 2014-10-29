<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.criteria.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>




<%@ include file="../common/cache.jsp" %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>
<bean:define id="massemailform" name="massEmailForm" type="com.form.MassEmailForm" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>

 <script type="text/javascript" src="jsp/js/nicEdit.js"></script>
 <script type="text/javascript">
 //<![CDATA[
 bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
 //]]>  
 </script>

<!--there is no custom header content for this example-->
<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;}

</style>
</head>
<script language="javascript">
function change(e, color){
    var el=window.event? event.srcElement: e.target
    if (el.tagName=="INPUT"&&el.type=="button")
    el.style.backgroundColor=color
}
function sendEmail(applicantids){
    var i;
    var checkedvalue="";
  var jobId=document.massEmailForm.jobId.value;
  var subjectemail=document.massEmailForm.subjectemail.value;
  	var content = nicEditors.findEditor('messageemail').getContent();
	document.massEmailForm.messageemail.value=content;
  var messageemail=document.massEmailForm.messageemail.value.trim();
  var newmwssagemail=messageemail.replace(/\##/g,'HashHashsymbol');

  var checkboxlength=document.massEmailForm.chechbox1.length;
    for(i=0;i<checkboxlength;i++){
     if(document.massEmailForm.chechbox1[i].checked) {

    checkedvalue=checkedvalue+document.massEmailForm.chechbox1[i].value+",";


	 }
	}

  if(subjectemail ==null || subjectemail==""  || messageemail==null || messageemail==""){
	 if((subjectemail ==null || subjectemail=="") && (messageemail==null || messageemail=="")){
     var doyou = confirm("<%=Constant.getResourceStringValue("user.massemail.validation.subject_message",user1.getLocale())%>");
	 }else if((messageemail!=null || messageemail=="") && (subjectemail ==null || subjectemail=="")){
     var doyou = confirm("<%=Constant.getResourceStringValue("user.massemail.validation.subject",user1.getLocale())%>");
	 }else if((messageemail==null || messageemail=="") && (subjectemail!=null || subjectemail!="")){
     var doyou = confirm("<%=Constant.getResourceStringValue("user.massemail.validation.message",user1.getLocale())%>");
	 }

}else{

var url="massemail.do?method=sentEmailToAllApplicants&applicantids="+applicantids+"&subjectemail="+subjectemail+"&jobId="+jobId+"&messageemail="+newmwssagemail+"&interviewstates="+checkedvalue;
	
	
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangesentmail;

	    try {
    		req.open("GET", url, true);
						
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangesentmail;
	        req.open("GET", url, true);
			req.send();
			
    	}
  	}



}
	 if (doyou == true){


 var url="massemail.do?method=sentEmailToAllApplicants&applicantids="+applicantids+"&subjectemail="+subjectemail+"&jobId="+jobId+"&messageemail="+newmwssagemail+"&interviewstates="+checkedvalue;
	
	
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangesentmail;

	    try {
    		req.open("GET", url, true);
						
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangesentmail;
	        req.open("GET", url, true);
			req.send();
			
    	}
  	}


}
}


function processStateChangesentmail() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpansentemail(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlsentemail(spanElements);
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtmlsentemail(newTextElements){
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
				  
		    	  if(name="applicants")	document.getElementById(name).innerHTML = content;


			  }	   			
   	 	}
	}

	
}

function splitTextIntoSpansentemail(textToSplit){
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



function init(){
    if(document.massEmailForm.jobId.value=="0"){
       document.getElementById("allcheckbox").style.display = 'none';
	}else{
       document.getElementById("allcheckbox").style.display = 'block';
	}
	  
	 
}

function viewchekbox(){
	var checkboxlength=document.massEmailForm.chechbox1.length;
	if(document.massEmailForm.jobId.value=="0"){
    document.getElementById("allcheckbox").style.display = 'none';

	}else{
    document.getElementById("allcheckbox").style.display = 'block';
	for(i=0;i<checkboxlength;i++){
		 
     document.massEmailForm.chechbox1[i].checked=false;
	 }

    }
var url="massemail.do?method=refressmassEmailListpage"
	
	
	
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

function searchaapplicant(){
  
    var i;
    var checkedvalue="";
    var checkornot="checked";
    var jobId=document.massEmailForm.jobId.value;
	var checkboxlength=document.massEmailForm.chechbox1.length;
    for(i=0;i<checkboxlength;i++){
     if(document.massEmailForm.chechbox1[i].checked) {

    checkedvalue=checkedvalue+document.massEmailForm.chechbox1[i].value+",";


	 }



}

if(checkedvalue.length=="0"){
checkornot="unchecked";
}


var url="massemail.do?method=searchapplicantForMassEmail&jobId="+jobId+"&interviewstates="+checkedvalue+"&checkornot="+checkornot;
	
	
	
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
    	//document.getElementById("loading").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtml(newTextElements){
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
				  
		    	  if(name="applicants")	document.getElementById(name).innerHTML = content;
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
function PreviewMessage(emailtemplateid){
	var alertstr = "";
	  var showalert=false;
		var messageString =document.massEmailForm.messageemail.value;

	  if(messageString == "" || messageString == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("user.massemail.emptyMessage",user1.getLocale())%><br>";
			showalert = true;
		}
	  if (showalert){
	     	alert(alertstr);
	        return false;
	          }
	var url ="massemail.do?method=previewMessage&emailtemplateid="+emailtemplateid;
	window.open(url, "PreviewMessage","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

}
</script>
<body onload="init()" class="yui-skin-sam">


<html:form action="/massemail.do?method=saveTags">
<br>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><%=Constant.getResourceStringValue("user.massemail.send_an_email_to_many_applicants_at_nce",user1.getLocale())%></b><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="brown"><%=Constant.getResourceStringValue("user.massemail.please_note_abusing_this_batch_emailer",user1.getLocale())%></font>

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr>
<td valign="top" width="20"></td>
<td valign="top" width="500">
<table border="0" cellpadding="1" cellspacing="0">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>

 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="700" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="40" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="450" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>




<tr>

<td colspan="4">

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1" width="1200">



<tr>
<!--step one form start-->

<td width="25%" valign="top">
<br>
<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("user.massemail.step1_choose_some_applicant",user1.getLocale())%></b><br>
<HR size="0" width="280" color="red">
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("user.massemail.select_a_job",user1.getLocale())%><br><br>

 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <html:select property="jobId" onchange="viewchekbox()">

   <bean:define name="massEmailForm" property="jobList" id="jobList" />
   <option value="0"><%=Constant.getResourceStringValue("user.massemail.jolist.firstlabel",user1.getLocale())%></option>	
   <html:options collection="jobList" property="jobreqId"  labelProperty="jobreqName"/>
   </html:select>


<%
List interviewStates = Constant.getInterviewStatesForMassEmail();
%>

<% if(massemailform.getJobId()!=0){%>

<div id="allcheckbox" style="display:block;">
<% for(int i=0;i<interviewStates.size();i++){
	InterviewState int2 = (InterviewState)interviewStates.get(i);
%>

<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chechbox1" value="<%=int2.getInterviewstateCode()%>" onClick="searchaapplicant()"/><%=int2.getInterviewstateName()%>

<%}%>

</div>


<%}else{%>

<div id="allcheckbox" style="display:none;">

<% for(int i=0;i<interviewStates.size();i++){
	InterviewState int2 = (InterviewState)interviewStates.get(i);
%>

<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chechbox1" value="<%=int2.getInterviewstateCode()%>" onClick="searchaapplicant()"/><%=int2.getInterviewstateName()%>

<%}%>




</div>


<%}%>

</td>


<!--step two form start-->
<td width="75%" valign="top,left">
<br>
<b><%=Constant.getResourceStringValue("user.massemail.step2_create_and_send_message",user1.getLocale())%></b><br>
<HR size="0" width="920" color="red">
<br>
<%=Constant.getResourceStringValue("user.massemail.From",user1.getLocale())%>
&nbsp;<%=user1.getFirstName()%>&nbsp;<%=user1.getLastName()%>&nbsp;&lt;<%=user1.getEmailId()%>&gt;

<br><%=Constant.getResourceStringValue("user.massemail.recipients",user1.getLocale())%>
<!--<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="alsosendtouser">Include me in cc list
 (<%=user1.getEmailId()%>) 
 
 -->



<span id="applicants">
<table>
<tr>
<td>
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr >
<td valign="top" width="510"  bgcolor="#B2D2FF">
<table border="0" cellpadding="0" cellspacing="0">
 

 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="400" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>




<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>
<td colspan="4">

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1" >



<tr valign="center">
<td > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><font color="red"><%=Constant.getResourceStringValue("user.massemail.matching_applicants_appear_here",user1.getLocale())%></font></i>

</td>

</tr>
</table>
	
	
</td>
</tr>



<tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;</td></tr>
<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>
</table>
</td>

</tr>
</table>
</td>
<td valign="top">


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="sentemail" disabled="true" value="<%=Constant.getResourceStringValue("user.massemail..send_Batch_Message",user1.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("user.massemail..send_Batch_Message",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("user.massemail..send_Batch_Message",user1.getLocale())%>" height="20"  width="19"  class="initial2"  onMouseover="change(event, 'darksalmon')" onMouseout="change(event, '#c00')" />

</td>
</tr>
</table>



</span>
<br>
<table>
<tr>
<td>

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr >
<td valign="top" width="500">

<%=Constant.getResourceStringValue("user.massemail.subject",user1.getLocale())%>
<br><input type="text" id="subjectemail" name="subjectemail" value="<%=massemailform.getSubjectemail()==null?"":massemailform.getSubjectemail()%>" size="79">
<br>
<br><%=Constant.getResourceStringValue("user.massemail.message",user1.getLocale())%>
<br><textarea name="messageemail" id="messageemail"rows="10" cols="76" ><%=(massemailform.getEmailtemplateData()==null)?"":massemailform.getEmailtemplateData()%></textarea>

<td>
</tr>


</table>

</td>
<td>

<br>
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("user.massemail.use_these_tokens_message",user1.getLocale())%>
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="brown"><%=Constant.getResourceStringValue("user.massemail.be_sure_to_includebracket",user1.getLocale())%></font>




<br>
<table>
<tr><td width="14"></td><td>
<%=com.util.MergeUtil.getAvailableTags(Common.MASS_EMAIL_TO_APPLICANT)%>
</td>
</table>

</td>
</tr>
</table>

<br>
<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="email" onClick="sendEmail()" value="Sent mail"/>-->

</td>

</tr>
</table>
	
	
</td>
</tr>

<tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;</td></tr>
<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>
</table>
</td>

</tr>
<tr>


</tr>
</table>


<table>






</html:form>


</body>
</html>
