<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.bean.*"%>
<%
String boxnumber = (String)request.getAttribute("boxnumber");

%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<title> <%=Constant.getResourceStringValue("hr.user.User_Selector",user1.getLocale())%> </title>
<bean:define id="form" name="createUserForm" type="com.form.CreateUserForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  

var boxnu = "<%=boxnumber%>";

var isgrp = "<%=form.getIsUserGroupSearch()%>";

	function discard(){
       self.close();
		}
	function resetData(){
		  document.createUserForm.firstName.value="";
		  document.createUserForm.lastName.value="";
		  document.createUserForm.orgId.value="";
		  document.createUserForm.departmentId.value="";
		  document.createUserForm.isUserGroupSearch.value="";
		  document.createUserForm.groupName.value="";
		  

		  setTimeout("retrieveURL('user.do?method=loadDepartments')",200);
		  
		 
		 }  
	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.createUserForm.userId.length; 
if(len == undefined) len = 1; 

if (document.createUserForm.userId.length != undefined)
{

 for (var i=0; i < document.createUserForm.userId.length; i++)
   {
   if (document.createUserForm.userId[i].checked == true)
      {
	   
      c_value =document.createUserForm.userId[i].value + "\n";
	  id_value =  document.createUserForm.userId[i].id;
      }
   }
}else{
	if (document.createUserForm.userId.checked == true) {
	c_value = document.createUserForm.userId.value + "\n";
	  id_value = document.createUserForm.userId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;
var tempurl = "";
if(isgrp != null && isgrp == 'Y'){

 tempurl = "<img src='jsp/images/User-Group-icon.png'><a href='#' onClick=window.open("+"'"+"usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id_value+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+c_value+"</a>";

}else{
 tempurl = "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+id_value+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+c_value+"</a>";
}


var dyvalue = '<span id="assignedto">'+tempurl+'</span>';


if(boxnu == "addreviewer"){
	window.opener.document['scheduleInterviewForm'].reviewerid.value=id_value.trim();
	//window.opener.document.getElementById("assignedto").innerHTML = dyvalue;
	window.opener.document['scheduleInterviewForm'].reviewername.value=c_value.trim();
	window.opener.document['scheduleInterviewForm'].reviewernamehidden.value=c_value.trim();

	if(isgrp != null && isgrp == 'Y'){
		window.opener.document['scheduleInterviewForm'].isgroup.value='Y';
	}else{
		window.opener.document['scheduleInterviewForm'].isgroup.value='N';
	}
	

}else if(boxnu == "intschreassign"){
	//window.opener.document.getElementById("assignedto").innerHTML = dyvalue;
	window.opener.document['scheduleInterviewForm'].reviewerid.value=id_value.trim();
	window.opener.document['scheduleInterviewForm'].reviewername.value=c_value.trim();
	window.opener.document['scheduleInterviewForm'].reviewernamehidden.value=c_value.trim();
	if(isgrp != null && isgrp == 'Y'){
		window.opener.document['scheduleInterviewForm'].isgroup.value='Y';
	}else{
		window.opener.document['scheduleInterviewForm'].isgroup.value='N';
	}

}else if(boxnu == "watchlistadd"){
	//window.opener.document.getElementById("assignedto").innerHTML = dyvalue;
	window.opener.document['watchListForm'].userUserGrpId.value=id_value.trim();
	window.opener.document['watchListForm'].userUserGrpName.value=c_value.trim();
	window.opener.document['watchListForm'].userUserGrpNamehidden.value=c_value.trim();
	if(isgrp != null && isgrp == 'Y'){
		window.opener.document['watchListForm'].isGroup.value='Y';
	}else{
		window.opener.document['watchListForm'].isGroup.value='N';
	}

}else if(boxnu == "intschofferowner"){
	window.opener.document.getElementById("assignedto").innerHTML = dyvalue;
	window.opener.document['applicantForm'].offerownerId.value=id_value.trim();
window.opener.document['applicantForm'].offerownerName.value=c_value.trim();
if(isgrp != null && isgrp == 'Y'){
		window.opener.document['applicantForm'].isgroup.value='Y';
	}else{
		window.opener.document['applicantForm'].isgroup.value='N';
	}
}else if(boxnu == "refassign"){
	window.opener.document.getElementById("assignedto").innerHTML = dyvalue;
	window.opener.document['referenceForm'].assignedtoid.value=id_value.trim();
window.opener.document['referenceForm'].assignedtoname.value=c_value.trim();

}else if(boxnu == "addhighiringmanager"){
	window.opener.document['jobRequisitionTemplateForm'].hiringMgrId.value=id_value.trim();
	window.opener.document['jobRequisitionTemplateForm'].hiringMgrName.value=c_value.trim();
	window.opener.document['jobRequisitionTemplateForm'].highiringmanagernamehidden.value=c_value.trim();
	
}else if(boxnu == "approvertemplate"){
	
	window.opener.document['jobRequisitionTemplateForm'].approverId.value=id_value.trim();
	window.opener.document['jobRequisitionTemplateForm'].approverName.value=c_value.trim();
	window.opener.document['jobRequisitionTemplateForm'].approverNamehidden.value=c_value.trim();

	if(isgrp != null && isgrp == 'Y'){
		window.opener.document['jobRequisitionTemplateForm'].isgroup.value='Y';
	}else{
		window.opener.document['jobRequisitionTemplateForm'].isgroup.value='N';
	}
	
}else if(boxnu == "addhighiringmanagerreq"){
	window.opener.document['jobRequisitionForm'].hiringMgrId.value=id_value.trim();
	window.opener.document['jobRequisitionForm'].hiringMgrName.value=c_value.trim();
	window.opener.document['jobRequisitionForm'].highiringmanagernamehidden.value=c_value.trim();
	
}else if(boxnu == "reqexpenseuseradd"){
	window.opener.document['requistionExpenseForm'].voucherOwnerId.value=id_value.trim();
	window.opener.document['requistionExpenseForm'].voucherOwnerName.value=c_value.trim();
	window.opener.document['requistionExpenseForm'].voucherOwnerNamehidden.value=c_value.trim();
	
}else if(boxnu == "addprimaryowner"){
	window.opener.document['onBoardingTaskDefiForm'].primaryOwnerId.value=id_value.trim();
	window.opener.document['onBoardingTaskDefiForm'].primaryOwnername.value=c_value.trim();
	window.opener.document['onBoardingTaskDefiForm'].primaryownernamehidden.value=c_value.trim();

	if(isgrp != null && isgrp == 'Y'){
		window.opener.document['onBoardingTaskDefiForm'].isGroup.value='Y';
	}else{
		window.opener.document['onBoardingTaskDefiForm'].isGroup.value='N';
	}
	
}else if(boxnu == "addrecruiter"){
	window.opener.document['jobRequisitionForm'].recruiterId.value=id_value.trim();
	window.opener.document['jobRequisitionForm'].recruiterName.value=c_value.trim();
	window.opener.document['jobRequisitionForm'].recruiternamehidden.value=c_value.trim();

	if(isgrp != null && isgrp == 'Y'){
		window.opener.document['jobRequisitionForm'].isgrouprecruiter.value='Y';
	}else{
		window.opener.document['jobRequisitionForm'].isgrouprecruiter.value='N';
	}
		
}else if(boxnu == "assignedto"){
	window.opener.document.getElementById("reassignedto").innerHTML = dyvalue;
window.opener.document['initiateapp3'].reassignedto.value=id_value.trim();
window.opener.document['initiateapp3'].reassignedtoname.value=c_value.trim();
if(isgrp != null && isgrp == 'Y'){
		window.opener.document['initiateapp3'].isgroup.value='Y';
	}else{
		window.opener.document['initiateapp3'].isgroup.value='N';
	}

}else if(boxnu == "talentpool"){
	window.opener.document['talentPoolForm'].assignedtoid.value=id_value.trim();
	window.opener.document['talentPoolForm'].reviewername.value=c_value.trim();
	window.opener.document['talentPoolForm'].reviewernamehidden.value=c_value.trim();
    if(isgrp != null && isgrp == 'Y'){
		window.opener.document['talentPoolForm'].isGroup.value='Y';
	}else{
		window.opener.document['talentPoolForm'].isGroup.value='N';
	}


}else if(boxnu == "approverreq"){
	window.opener.document['jobRequisitionForm'].approverId.value=id_value.trim();
	window.opener.document['jobRequisitionForm'].approverName.value=c_value.trim();
	window.opener.document['jobRequisitionForm'].approverNamehidden.value=c_value.trim();
    if(isgrp != null && isgrp == 'Y'){
		window.opener.document['jobRequisitionForm'].isgroup.value='Y';
	}else{
		window.opener.document['jobRequisitionForm'].isgroup.value='N';
	}


}else{

window.opener.document.getElementById("reassignedto").innerHTML = dyvalue;
window.opener.document['initiateapp3'].reassignedto.value=id_value.trim();
window.opener.document['initiateapp3'].reassignedtoname.value=c_value.trim();
}





self.close();
	   
		}

		function searchcri(){
       
	   document.createUserForm.action = "user.do?method=searchassigneduser&boxnumber=<%=boxnumber%>";
	   document.createUserForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.createUserForm.orgId.value;
	
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

function enabletxtbox(){

	 document.createUserForm.groupName.readOnly = false
	
}
function disabletxtbox(){
	document.createUserForm.groupName.value="";
	document.createUserForm.groupName.readOnly = true
	
}

function init(){
	var usergrop="<%=form.getIsUserGroupSearch()%>";
	if(usergrop == "Y"){
		document.createUserForm.groupName.readOnly = false
	}else{
		document.createUserForm.groupName.readOnly = true
	}
	document.createUserForm.firstName.focus();
}
</script>
<body class="yui-skin-sam" onLoad="init()" onSubmit="searchcri()">

<html:form action="/user.do?method=searchassigneduser&boxnumber=<%=boxnumber%>">
	    <table border="0" width="100%">
	<tr bgcolor="gray">
	<td><b><%=Constant.getResourceStringValue("hr.user.Search_User_Selector",user1.getLocale())%></b></td>
	</tr>
	</table>
		<div class="div">

	<table border="0" width="100%" colspan="2">
    <table border="0" width="100%">

    <tr>
       
    
		<td><%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%></td>
	
		<td><html:text  property="firstName"/></td>
	
		<td><%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%> </td>
	
		<td><html:text  property="lastName"/></td>

		</tr>
		<tr>
   
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<option value=""></option>
			<bean:define name="createUserForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>........</span>
			
			</td>
		
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			
			<td>
			<span id="departments">
			<html:select property="departmentId" >
			<option value=""></option>
			<bean:define name="createUserForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			</td>
			</tr>
	
<% if(!Constant.notforusergroupsearch.contains(boxnumber)) {%>

    <tr>
       
		<td>
		<%=Constant.getResourceStringValue("hr.user.group_search",user1.getLocale())%>
		</td>
		
	
		<td>
		<%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isUserGroupSearch" maxlength="10" onclick="enabletxtbox()" id="1" <%=(form.getIsUserGroupSearch()!= null && form.getIsUserGroupSearch().equals("Y"))? "Checked=true" : "" %> value="Y">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isUserGroupSearch" onclick="disabletxtbox()" id="0" <%=(form.getIsUserGroupSearch()!= null && form.getIsUserGroupSearch().equals("N"))? "Checked=true" : "" %> value="N">
		</td>

		<td><%=Constant.getResourceStringValue("hr.user.group_name",user1.getLocale())%> </td>
	
		<td><html:text  property="groupName"/></td>

	</tr>
<%}%>

<table valign="left" border="0" width="100%">
	<tr>
		<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button"> 
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
		
	</tr>	
</table>
 </table>
</div>
<br>
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("hr.user.Employee",user1.getLocale())%></b></td>
</tr>
</table>
<div  class="div">

<div id="dynamicdata">
<%
 List userList = form.getUserList();
 if(userList != null && userList.size()>0){
%>
<pagination-tag:pager action="user" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>

<%if(form.getIsUserGroupSearch() != null && form.getIsUserGroupSearch().equals("Y")){ %>
<!--user group list -->

<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("hr.user.group_name",user1.getLocale())%></td>
</tr>

<%

 
 if(userList != null){

	 for(int i=0;i<userList.size();i++){

		 UserGroup usergrp = (UserGroup)userList.get(i);
    String val = usergrp.getUsergrpName();
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="userId" id="<%=usergrp.getUsergrpId()%>" value="<%=val%>">
<img src="jsp/images/User-Group-icon.png">
<a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=usergrp.getUsergrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500')"><%=val%></a>

	
</td>

<%
	 }
 }
 %>

</table>
<!--user group list end -->
<%}else{%>
<!--users list -->
<table border="0" width="100%">
<%  if(userList != null && userList.size()>0){ %>
<tr>
<td><b><%=Constant.getResourceStringValue("hr.user.Name",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("hr.user.Role",user1.getLocale())%></b></td>


</tr>
<%}%>
 <%

 
 if(userList != null){

	 for(int i=0;i<userList.size();i++){

		 User user = (User)userList.get(i);
    String val = user.getFirstName()+ " "+ user.getLastName();
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="userId" id="<%=user.getUserId()%>" value="<%=val%>">
<img src="jsp/images/user.gif">
<a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=user.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=user.getFirstName()+ " "+user.getLastName()%></a>


</td>
<td>
<%=(user.getOrganization() == null)?"":user.getOrganization().getOrgName()%>
</td>
<td>
<%=(user.getDepartment() == null)?"":user.getDepartment().getDepartmentName()%>

</td>
<td>
<%=(user.getRole() == null)?"":user.getRole().getRoleName() %>

</td>
</tr>


<%

	 }
 }

 %>
<!--user list end -->
<%}%>
<%  if(userList != null && userList.size()<1){ %>
 <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>
<%}%>
		  
	<tr>
				<td>
				<%  if(userList != null && userList.size()>0){ %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
					
			
			<%}%>
				</td>
				
			</tr>	      
</table>
</table>
</div>
</div>
</html:form>
</body>