<%@ include file="../../common/include.jsp" %>
<%@ include file="../../common/greybox.jsp" %>
<%@ include file="../../common/autocomplete.jsp" %>
<script type="text/javascript" src="jsp/js/jquery.blockUI.js"></script>

<bean:define id="goalForm" name="goalForm" type="com.performance.form.GoalForm" />

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String 	userId = (String)request.getAttribute("userId");
String 	empName = (String)request.getAttribute("empName");
if(empName == null){
	empName = "";
}
long timep = 0;
Long timeLongv = (Long)session.getAttribute("TIMEPERIOD_ID");
System.out.println("timeperiodIdtimeperiodIdtimeperiodIdtimeperiodIdtimeperiodId"+timeLongv);
if(timeLongv != null){
	goalForm.setTimePeriodId(timeLongv.longValue());
	timep = timeLongv.longValue();
}
List<String> treedatalist = BOFactory.getUserBO().getJavaScriptTreeSubOrdinates(user1.getUserId(),user1,userId,timep);


%>

<html>


<style>
span1{color:#ff0000;}
</style>

<style type="text/css"> 


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



<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
}

function viewGoals(){	

	  document.goalForm.action = "goal.do?method=viewinitiatedgoals";
 document.goalForm.submit();
 	}






function sendForReview(userIdvalu){
		var url = "<%=request.getContextPath()%>/usergoal.do?method=sendForReview&userId="+userIdvalu;
		GB_showCenter('<%=Constant.getResourceStringValue("send.for.review.goals.to.user",user1.getLocale())%>',url,500,600, messageret1);
}

function modifykra(id){
		var url = "<%=request.getContextPath()%>/usergoal.do?method=editUserGoalKRA&usergoalkraid="+id;
		GB_showCenter('<%=Constant.getResourceStringValue("modify.kra",user1.getLocale())%>',url,700,750, messageretrefresh);
}

function messageret(){
	window.location.reload();
}

function messageret1(){
		window.location.reload();
}
function messageretrefresh(){
		var useridhidden= document.goalForm.useridhidden.value;
		var empName = document.goalForm.empName.value;
		location.href="goal.do?method=viewinitiatedgoals&userId="+useridhidden+"&empName="+empName;
}

function validateUser(){
	if(document.goalForm.empName.value == ""){
		document.goalForm.userId.value="0";
	}
	
	//document.lovForm.reviewername.value=document.lovForm.reviewernamehidden.value;
}


$(function() {

	$("#empName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		  
		document.goalForm.userId.value=item.data;
		document.goalForm.empName.value=item.value;
		//alert(item.data);
		}
		});

});
</script>

<script type="text/javascript"> 
//$(document).ajaxStop($.unblockUI);      
function test() {    
//$.ajax({ url: 'goal.do?method=viewinitiatedgoals&userId=38&empName=testy dssdcx', cache: false }); 
} 

/* $(document).ready(function() {$('#sd2').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
   //test();
	 }); */


$(document).ready(function() {
	    <%=treedatalist.get(1)%>
}); 


function retriveData(urld) { 
	document.goalForm.empName.value="";
$.ajax({
  url: urld,
  success: function(data){
  $('#goaldata').html(data);
	completeajx();
  }
});
} 

 
function completeajx(){
	  $.unblockUI();
}




function retriveData1(url) {
   //convert the url to a string


	
	
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


completeajx();

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
				  
		    	  if(name="goaldata")	document.getElementById(name).innerHTML = content;
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


function setTimePeriodValue(){	
      var timeperiodid = document.goalForm.timePeriodId.value;
	  if(timeperiodid == "0") {
		  alert("<%=Constant.getResourceStringValue("timeperiod.is.not.set",user1.getLocale())%>");
		  return false;
	  }
	 
	  document.goalForm.action = "goal.do?method=settimeperiod&timePeriodId="+timeperiodid;
 document.goalForm.submit();

 	}

    </script>


<%
 String initiateGoalsSubmit = (String)request.getAttribute("initiateGoalsSubmit");


if(initiateGoalsSubmit != null && initiateGoalsSubmit.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color=green><%=Constant.getResourceStringValue("goals.initiated.successfully",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
 String timeperiodset = (String)request.getParameter("timeperiodset");


if(timeperiodset != null && timeperiodset.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color=green><%=Constant.getResourceStringValue("timeperiod.is.set",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
 


if(goalForm.getTimePeriodId()==0){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font size="12" color=red><%=Constant.getResourceStringValue("timeperiod.is.not.set",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>


<table border="0" width="100%">

<tr>
<td valign="top" >
	

<div class="dtree">

	<p><a href="javascript: d.openAll();"><%=Constant.getResourceStringValue("admin.organization.orglistbody.openall",user1.getLocale())%></a> | <a href="javascript: d.closeAll();"><%=Constant.getResourceStringValue("admin.organization.orglistbody.closeall",user1.getLocale())%> </a></p> <br>

	<%=treedatalist.get(0)%>

	

</div>



</td>

<td width="5%"></td>


<td valign="top" align="left" >

<body class="yui-skin-sam">


<html:form action="/goal.do?method=saveOrgGoal">

<div align="center">

<br>


<table border="0" width="100%">
<tr>
<td>

<b><%=Constant.getResourceStringValue("view.goals",user1.getLocale())%></b>

</td>
</tr>

</table>

<br>
	<table border="0" width="100%" bgcolor="#f3f3f3">
	<font color = red ><html:errors /> </font>

	
		
		
		<tr>
            <td valign="left" width="100">
			<%=Constant.getResourceStringValue("goal.for.timeperiod",user1.getLocale())%>
			</td>
		   <td valign="left" width="200">
		    <html:select  property="timePeriodId" onchange="setTimePeriodValue()">
			<option value="0"></option>
			<bean:define name="goalForm" property="timePeriodList" id="timePeriodList" />
            <html:options collection="timePeriodList" property="timePeriodId"  labelProperty="startenddate"/>
			</html:select>
			</td>
			<td valign="left"><%=Constant.getResourceStringValue("viewgoal.employee.name",user1.getLocale())%></td>
			
			<td valign="left" width="600">

				 <input type="hidden" name="userId" value="<%=userId%>"/>

				<input type="hidden" name="empNamehidden" value="<%=empName%>">
                 <input type="text" id="empName" name="empName" value="<%=empName%>" autocomplete="off" onblur="validateUser()">
				 <input type="button" name="viewgoals" value="<%=Constant.getResourceStringValue("view.goal.submit",user1.getLocale())%>" onClick="viewGoals()">
			</td>

			<td valign="left">
			
			</td>
	

		</tr>

</table>
<span id="goaldata">
<br>
<table border="0" width="100%">
<tr>
<td>
<b><%=Constant.getResourceStringValue("goals.for",user1.getLocale())%> : <%=(empName=="")?"N/A":empName%></b>
</td>
</tr>
</table>

<%

List goalList =  BOFactory.getGoalBO().userGoalsList(userId,timeLongv);
%>
<br>
<table width="100%" border="1">
<tr bgcolor="#f3f3f3">
<td><%=Constant.getResourceStringValue("goal.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("goal.KRAs",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("kra.weight",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("kra.kpis",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("goal.actions",user1.getLocale())%></td>
<tr>

<%
if(goalList != null && goalList.size()>0){
	for(int i=0;i<goalList.size();i++){
		UserGoals goal = (UserGoals)goalList.get(i);
	if(StringUtils.isNullOrEmpty(goal.getGoal().getGoalName()))continue;
		
%>

<tr valign="top" height="100">
<td width="10%"> <b><%=goal.getGoal().getGoalName()%> </b>
</td>
<td width="20%"> <%=(goal.getKraName()==null)?"":goal.getKraName()%> <br>
<i><font color="33CCCC"><%=(goal.getKradesc()==null)?"":goal.getKradesc()%></i>
</td>
<td width="10%"> <b><%=(goal.getKraWeight()==0.0)?"":goal.getKraWeight()%> </b>
</td>
<td width="50%">

<%
List<UserGoalsKpi> kpiList = goal.getKpiList();
if(kpiList != null && kpiList.size()>0){
%>

<table align="center" width="80%" border="1">
<tr bgcolor="#f3f3f3">
<td width="50%"><%=Constant.getResourceStringValue("kra.tasks",user1.getLocale())%></td>
<td width="50%"><%=Constant.getResourceStringValue("kra.measures",user1.getLocale())%></td>
</tr>
<%
for(int j=0;j<kpiList.size();j++){
				UserGoalsKpi gkpi = (UserGoalsKpi)kpiList.get(j);
%>
<tr>
<td>
<%=gkpi.getKpiName()%>
</td>
<td>
<%=gkpi.getKraMeasure()%>
</td>

</tr>

<%}%>
</table>

<%}else{%>
<table align="center" width="80%" border="1">
</table>
<%}%>

</td>
<td width="15%">
<% if(goal.getModifiable()!= null && goal.getModifiable().equals("Y")){%>
<a href="#" onClick="addkra('<%=goal.getUserGoalId()%>');return false;"><%=Constant.getResourceStringValue("add.kra",user1.getLocale())%></a>
<%}%>
<% if(goal.getKramodifiable()!= null && goal.getKramodifiable().equals("Y")){%>
<br><a href="#" onClick="modifykra('<%=goal.getUserGoalId()%>');return false;"><%=Constant.getResourceStringValue("modify.kra",user1.getLocale())%></a>
<%}%>
</td>
<tr>

<%}}%>
</table>

<%
if(!StringUtils.isNullOrEmpty(userId)){
%>
<br>
<table border="0" width="100%">
<tr>
<td>
 <input type="button" name="sendforreview" value="<%=Constant.getResourceStringValue("send.for.review.goals.to.user",user1.getLocale())%>" onClick="sendForReview('<%=userId%>')">
</td>
</tr>
</table>
<%}%>
<input type="hidden" name="useridhidden" value="<%=userId%>">
</span>
	
</div>

</html:form>


</body>


</td>
</tr>
<tr>
<td></td>
<td width="5%"></td>
<td>


</td>
</tr>


</table>

<%=treedatalist.get(2)%>



