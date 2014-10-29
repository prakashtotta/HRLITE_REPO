
<%@ include file="../common/include.jsp" %>
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />
<style>
span1{color:#ff0000;}
</style>
 <html>
  <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>



<style>
td.dragHandle {
	background-position: center; cursor: move; 
}
td.showDragHandle {
	background-position: center; cursor: move; background-image: url("jsp/images/updown2.gif"); background-repeat: no-repeat;
}
td.dottedline{
	border-top:1px dashed #f00; margin-top:1em; padding-top:1em;
}

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
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</STYLE>


</style>
<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;}

</style>
</head>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<script language="javascript">
function change(e, color){
    var el=window.event? event.srcElement: e.target
    if (el.tagName=="INPUT"&&el.type=="button")
    el.style.backgroundColor=color
}


function backtoaddquestionlistpage2(){

var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
if (doyou == true){
document.questionGroupForm.action = "questiongroup.do?method=backAddQuestionpage&questiongroupId=<%=questionsgroupform.getQuestiongroupId()%>";
      document.questionGroupForm.submit();

}

}

function deletequestionfromgroup(questionid,groupid,name){

  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){

   document.questionGroupForm.action = "questiongroup.do?method=deletequestionfromgroup&questionid="+questionid+"&questiongroupid="+groupid+"&questiongroupname="+name;
      document.questionGroupForm.submit();
 
}
}

function deletequestionsGroup(id){

	
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
	document.questionGroupForm.action = "questiongroup.do?method=questionsGroupdelete&questiongroupId="+id;
	document.questionGroupForm.submit();

		
		
		
	 }

}

function messageret(){
	window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			} 







function editQuestionGroupNameDesc(){
	//document.getElementById("editablelink").style.display = 'none';
     var url="questiongroup.do?method=editQuestiongroupNameDesc&questiongroupId=<%=questionsgroupform.getQuestiongroupId()%>";
		
		
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
				  
		    	  if(name="editNameDesc")	document.getElementById(name).innerHTML = content;
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








function updatedata(){

	var alertstr = "";
	var showalert=false;
	var name=document.questionGroupForm.questiongroupName.value.trim();
	var desc=document.questionGroupForm.questiongroupDesc.value.trim();
	
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_group_Name_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_disc_required",user1.getLocale())%><br>";
		showalert = true;
		}
	
	if(desc.length > 500){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.QuestionsGroup.desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}		
	 	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	//document.getElementById("editablelink").style.display = 'none';
     var url="questiongroup.do?method=updategroupNameDesc&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname="+name+"&questiongroupdesc="+desc;
		
		
		//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeName;

	    try {
    		req.open("GET", url, true);
						
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeName;
	        req.open("GET", url, true);
			req.send();
			
    	}
  	}


}

function processStateChangeName() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanName(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlName(spanElements);
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading").style.visibility = "hidden";	
  	}
	
}
function replaceExistingWithNewHtmlName(newTextElements){
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
				  
		    	  if(name="editNameDesc")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
	
	
}

function splitTextIntoSpanName(textToSplit){
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


</script>


<script type="text/javascript" src="jsp/autocomplete/jquery.min.js"></script>

<script type='text/javascript'>

 var $jq = jQuery.noConflict(true);

</script>
<script type="text/javascript" src="jsp/dragdrop/jquery.tablednd_0_5_questionnaire.js"></script>


<script type="text/javascript">

$jq(document).ready(function() {
	
	$jq('#table-1').tableDnD({
		  onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=0; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			
		   $jq('#AjaxResult').load("<%=request.getContextPath()%>/jsp/talent/arrangeQuestionsInGroup.jsp?qnsgroupid=<%=questionsgroupform.getQuestiongroupId()%>&arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 


});

</script>
<body class="yui-skin-sam">




<!--<a  href="#" onClick="createquestiongroup()"><%=Constant.getResourceStringValue("admin.QuestionsGroup.CreateQuestionsGroup",user1.getLocale())%></a>-->

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<% if(questionsgroupform.getQuestiongroupId()!=0){%>
<br>
<a class="button" href="questiongroup.do?method=questionsgrouplist"  target="_parent"><%=Constant.getResourceStringValue("admin.QuestionsGroup.listpage.createnew_link",user1.getLocale())%></a>&nbsp;
<a class="button" href="questiongroup.do?method=questionsgrouplist"  target="_parent"><%=Constant.getResourceStringValue("admin.QuestionsGroup.listpage.backtolistpage_link",user1.getLocale())%></a>
<br><br>
<%}%>

<html:form action="/questiongroup.do?method=saveQuestionsGroup" >
<span id="editNameDesc">

<% if(questionsgroupform.getQuestiongroupId()!=0){%>
	<% QuestionGroups queGroup32 =  BOFactory.getQuestionBO().getQuestionsByQuestionGroup(questionsgroupform.getQuestiongroupId()); %>
<% if(queGroup32.getQuestiongroupName()!=null){%>
<b><i><%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.edidlink.label",user1.getLocale())%></i></b>
<a href="#" onClick="editQuestionGroupNameDesc()"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.questiongroup.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.questiongroup.alt",user1.getLocale())%>" height="20"  width="19"/></a>




<br><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.questiongroup.label",user1.getLocale())%></b>
<%=queGroup32.getQuestiongroupName()%>


<%}%>

<% if(queGroup32.getQuestiongroupDesc()!=null ){%>
<br><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.description",user1.getLocale())%></b>&nbsp;<%=queGroup32.getQuestiongroupDesc()%>



<%}%>
<%}%>





</span>




<table>
<tr>
<td valign="top">


<%
String questionnaireupdated = (String)request.getAttribute("questionnaireupdated");

	if(questionnaireupdated != null && questionnaireupdated.equals("yes")){%>

<br>
	<div class="msg"><font color="white"><%=Constant.getResourceStringValue("admin.QuestionsGroup.updated.q_group",user1.getLocale())%></font></div>

<br>
<% }%>


<%
String saveQuestionsGroup = (String)request.getAttribute("saveQuestionsGroup");

	if(saveQuestionsGroup != null && saveQuestionsGroup.equals("yes")){%>



<!-- display all question name with option of question group also display edit /delete icon for edit/delet-->

<% QuestionGroups queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(questionsgroupform.getQuestiongroupId());

List questionbygroupList = queGroup.getQuestions();
%>
<% if (questionbygroupList!=null && questionbygroupList.size()>0){%>

<div class="div">	
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr>

<td valign="top" width="500">
<table border="0" cellpadding="0" cellspacing="0">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>
 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="370" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="200" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>




<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>
<td colspan="4">

<table id="table-1" border="0" bordercolor="#999999">

<%
	
if (questionbygroupList!=null && questionbygroupList.size()>0){
	
StringTokenizer tockens = new StringTokenizer(queGroup.getQuestionsSeq(),",");

	while(tockens!=null && tockens.hasMoreTokens()){
				String tmpqnsid = tockens.nextToken();

for(int i=0; i<questionbygroupList.size(); i++){
Questions questions = (Questions)questionbygroupList.get(i);
   if(tmpqnsid != null && tmpqnsid.equals(String.valueOf(questions.getQuestionId()))){
%>


<tr  id="1.<%=questions.getQuestionId()%>" >

 <td class="dragHandle" width="25" valign="center"><img src="jsp/images/updown2.gif"></td>
<td valign="top" >
<p style="border-top:1px dashed #f00; margin-top:1em; padding-top:1em;" />
<% if(questions.getTypeVal().equals("text")){%>
 
<%=questions.getQuestionName()%> <br><br>

<textarea rows="4" cols="50" name="text7"></textarea>

<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0" >

<a href="questiongroup.do?method=editQuestion1&questionid=<%=questions.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname=<%=questionsgroupform.getQuestiongroupName()%>" target="_parent"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>
&nbsp;


<a href="#" onClick='deletequestionfromgroup("<%=questions.getQuestionId()%>","<%=questionsgroupform.getQuestiongroupId()%>","<%=questionsgroupform.getQuestiongroupName()%>")'>
<img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>



	<%}else if(questions.getTypeVal().equals("radio")){%>
	 
	<%=questions.getQuestionName()%><br>
	<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){
      for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>
<br><input type="radio" name="radio"/>&nbsp;<%=questionopt.getQuestionOptValue()%>



	<%  }%>

	<%}%>
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">
<a href="questiongroup.do?method=editQuestion1&questionid=<%=questions.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname=<%=questionsgroupform.getQuestiongroupName()%>" target="_parent"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>
&nbsp;
<a href="#" onClick='deletequestionfromgroup("<%=questions.getQuestionId()%>","<%=questionsgroupform.getQuestiongroupId()%>","<%=questionsgroupform.getQuestiongroupName()%>")'>
<img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>
 

<%}else if(questions.getTypeVal().equals("dropdown")){%>
<%=questions.getQuestionName()%><br>


<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){%>
	<br><select name="select1">
    <%  for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>

<option><%=questionopt.getQuestionOptValue()%></option>



	<%  }%>
</select>


	<%}%>
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">
<a href="questiongroup.do?method=editQuestion1&questionid=<%=questions.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname=<%=questionsgroupform.getQuestiongroupName()%>" target="_parent"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>
&nbsp;
<a href="#" onClick='deletequestionfromgroup("<%=questions.getQuestionId()%>","<%=questionsgroupform.getQuestiongroupId()%>","<%=questionsgroupform.getQuestiongroupName()%>")'>
<img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>

<%}else if(questions.getTypeVal().equals("checkbox")){%>
<%=questions.getQuestionName()%><br>


<% List optionList = BOFactory.getQuestionBO().getAllquestionOptionByQuestion(questions.getQuestionId());
	
	if(optionList!=null && optionList.size()>0){%>
	
    <%  for(int j=0;j<optionList.size();j++){

QuestionOptions questionopt = (QuestionOptions)optionList.get(j);

%>

<br><input type="checkbox"/><%=questionopt.getQuestionOptValue()%>



	<%  }%>


	<%}%>
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">
<a href="questiongroup.do?method=editQuestion1&questionid=<%=questions.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname=<%=questionsgroupform.getQuestiongroupName()%>" target="_parent"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>
&nbsp;
<a href="#" onClick='deletequestionfromgroup("<%=questions.getQuestionId()%>","<%=questionsgroupform.getQuestiongroupId()%>","<%=questionsgroupform.getQuestiongroupName()%>")'>
<img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>

<%}else if(questions.getTypeVal().equals("date")){%>
<%=questions.getQuestionName()%>

<br>

<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
                 var cal1xx1 = new CalendarPopup("testdiv2");
                  cal1xx1.showNavigationDropdowns();
                 </SCRIPT>
	<INPUT TYPE="text" NAME="correctAnsDate"  value="" SIZE=50>
<A HREF="#" onClick="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" TITLE="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" TITLE="select date"></A>

<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
    </div>          



<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">

<a href="questiongroup.do?method=editQuestion1&questionid=<%=questions.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname=<%=questionsgroupform.getQuestiongroupName()%>" target="_parent"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>
&nbsp;
<a href="#" onClick='deletequestionfromgroup("<%=questions.getQuestionId()%>","<%=questionsgroupform.getQuestiongroupId()%>","<%=questionsgroupform.getQuestiongroupName()%>")'>
<img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>

<%}else if(questions.getTypeVal().equals("number")){%>
<%=questions.getQuestionName()%>

<br><br>
<input type="text" name="number"  size="50"/> 
<br>
<IMG SRC="jsp/images/spacer.gif" WIDTH="400" HEIGHT="0" BORDER="0">
<a href="questiongroup.do?method=editQuestion1&questionid=<%=questions.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname=<%=questionsgroupform.getQuestiongroupName()%>" target="_parent"><img src="jsp/images/edit2.jpg" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.editbutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>
&nbsp;
<a href="#" onClick='deletequestionfromgroup("<%=questions.getQuestionId()%>","<%=questionsgroupform.getQuestiongroupId()%>","<%=questionsgroupform.getQuestiongroupName()%>")'>
<img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.deletebutton.alt",user1.getLocale())%>" height="20"  width="19"/></a>

<%}%>
</td>

</tr>
<%}}}%>
 

<%}%>


</table>
	
	
</td>
</tr>

<tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;</td></tr>
<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>
</table>
</td>

</tr>
</table>
<%}else{%>

<!---if not  question of  question group then display your added question will be  displayed here-->

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr>
<td valign="top" width="20"></td>
<td valign="top" width="500">
<table border="0" cellpadding="0" cellspacing="0">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>

 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="100" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="40" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="450" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>




<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>
<td colspan="4">

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">



<tr>
<td> <i><%=Constant.getResourceStringValue("admin.QuestionsGroup.bodypage.blankmessage_question",user1.getLocale())%></i></td>

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

<%}%>



	<%}else{%>
	
	
 <b><%=Constant.getResourceStringValue("admin.QuestionsGroup.detail",user1.getLocale())%></b><br>
 <% String deletequestiongroup = (String)request.getAttribute("deletequestiongroup");%>

       <% if(deletequestiongroup != null && deletequestiongroup.equals("yes")){%>
<font color="red"><%=Constant.getResourceStringValue("admin.QuestionsGroup.deletemessage.q_group",user1.getLocale())%></font>  
	<%}%>
	</b><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		
          
          elCell.innerHTML = "<a href='questiongroup.do?method=backAddQuestionpage&questiongroupId=" +oRecord.getData("questiongroupId")+"'"+">"+ sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deletequestionsGroup('" + oRecord.getData("questiongroupId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [
{key:"questiongroupId", hidden:true, sortable:true},
{key:"questiongroupName",label:"<%=Constant.getResourceStringValue("admin.QuestionsGroup.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"questiongroupDesc",label:"<%=Constant.getResourceStringValue("admin.QuestionsGroup.desc",user1.getLocale())%>", sortable:false,resizeable:true},
{key:"delete",label:"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%> ",sortable:false,formatter:deleteUrl,resizeable:true} 
       
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/QuestionsGroupListpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"questiongroupId"},
{key:"questiongroupName"},
{key:"questiongroupDesc"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=questiongroupId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"questiongroupId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

</td>

<%}%>

<td valign="top">


<% if(saveQuestionsGroup != null && saveQuestionsGroup.equals("yes")){%>

<!--after save edit question -->
<%@ include file="questionSelectForQuestionGroup.jsp" %>


<%}else{%>

<div id="create" style="display:block;" class="div"> 
<!--create question group-->
<img src="jsp/images/spacer.gif" width="310" height="50">
<iframe src="questiongroup.do?method=createQuestiongroup" style="width:600px;height:200px;border:2px ridge;">
  <p>Your browser does not support iframes.</p>
</iframe> 

</div>
<%}%>
</td>
</tr>
</table>

<div id="AjaxResult" style="visibility=hidden">

</div>
</html:form>
</body>
</html>


