<%@ include file="../common/yahooincludes.jsp" %>
 <%@ include file="../common/include.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< add dependents >>");
%>
<bean:define id="cform" name="userDependentsForm" type="com.form.employee.UserDependentsForm" />


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
span1{color:#ff0000;}
</style>


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
</STYLE>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<script language="javascript">

function updatedata(){
	var alertstr="";
	var showalert=false;
	var name=document.userDependentsForm.name.value.trim();
	var relationship=document.userDependentsForm.relationship.value.trim();
	var relationshipOther=document.userDependentsForm.relationshipOther.value.trim();
	
    if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relativename.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(relationship == "" || relationship == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relationship.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if(relationship == "Other" && relationshipOther == "" || relationshipOther == null ){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.relationship.please_specify_otherrelationship",user1.getLocale())%> <BR>";
     	showalert = true;
	}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userDependentsForm.action ="userdependents.do?method=updateDependents&userdependentId=<%=cform.getUserDependentId()%>";
	  document.userDependentsForm.submit();


}

function savedata(){


	var alertstr="";
	var showalert=false;
	var name=document.userDependentsForm.name.value.trim();
	var relationship=document.userDependentsForm.relationship.value.trim();
	var relationshipOther=document.userDependentsForm.relationshipOther.value.trim();
	
	
    if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relativename.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(relationship == "NoValue" || relationship == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Relationship.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if(relationship == "Other" && relationshipOther == "" || relationshipOther == null ){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.relationship.please_specify_otherrelationship",user1.getLocale())%> <BR>";
     	showalert = true;
	}
	
	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userDependentsForm.action="userdependents.do?method=saveDependents";
	  document.userDependentsForm.submit();



}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){

	  document.userDependentsForm.action="userdependents.do?method=deleteDependents&userdependentId=<%=cform.getUserDependentId()%>";
	  document.userDependentsForm.submit();
 	}
}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	   parent.parent.GB_hide(); 
	   } 
	}
function closewindow2(){
	parent.parent.GB_hide();

	 
}
function showHideOtherRelationship(){
	var relationship=document.userDependentsForm.relationship.value;
	if(relationship == "Other"){

		document.getElementById('relationshipother').style.display = 'block';
		var url="<%=request.getContextPath()%>/userdependents.do?method=showOtherRelationshipTextbox&showtextbox=YES";
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangeTextbox;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangeTextbox;
		        req.open("GET", url, true);
			    req.send();
				
	    	}
	  	}
		//alert(relationship);
	}else{
		document.getElementById('relationshipother').style.display = 'none';
	}
	
	
}

function processStateChangeTextbox() {

	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlTextbox(spanElements);
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading").style.visibility = "hidden";	
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
function replaceExistingWithNewHtmlTextbox(newTextElements){
	
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
				  
		    	  if(name="relationshipother")document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
  	
}
function init(){
	var relationship=document.userDependentsForm.relationship.value;
	
	if(relationship == "Other"){
		
	}else{
		document.getElementById('relationshipother').style.display = 'none';
	}


}
</script>
<%
String dependentupdated = (String)request.getAttribute("dependentupdated");
	
if(dependentupdated != null && dependentupdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.dependents.updated",user1.getLocale())%></font></td>
			<td><!--   <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String dependentsaved = (String)request.getAttribute("dependentsaved");
	
if(dependentsaved != null && dependentsaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.dependents.saved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String dependentdeleted = (String)request.getAttribute("dependentdeleted");
	
if(dependentdeleted != null && dependentdeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.dependents.deleted",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		</tr>
		
	</table>
</div>
<%
}else{
%>

<body onload="init();">
<html:form action="/userdependents.do?method=saveDependents" enctype="multipart/form-data">
<table border="0" width="100%">

	 	<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Relativename",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="name" size="25" maxlength="200"/>
			<html:hidden property="userId" />
			<html:hidden property="userDependentId" />
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Relationship",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select  property="relationship" onchange="showHideOtherRelationship();">
			<bean:define name="userDependentsForm" property="relationsipList" id="relationsipList" />
            <html:options collection="relationsipList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
		<tr></tr>
		<tr>
		<td width="25%" colspan="2"><span id="relationshipother"><%=Constant.getResourceStringValue("hr.user.relationship.please_specify",user1.getLocale())%><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:text property="relationshipOther" size="25" maxlength="200"/></span>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.dependents.dob",user1.getLocale())%></td>
			<td>
			
		<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
		var cal1xx = new CalendarPopup("testdiv1");
		cal1xx.showNavigationDropdowns();

		</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

	
		<html:text property="dateofbirth" readonly="true" size="25" maxlength="200"/>
		
		<A HREF="#" onClick="cal1xx.select(document.userDependentsForm.dateofbirth,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

		<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
		</tr>

</table>
<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getUserDependentId() != 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button"">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%
			}else{
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%
			}
			%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</td>
		</tr>
		</table>
</html:form>

</body>
<%
}
%>
