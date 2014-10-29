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
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<%
String boxnumber = (String)request.getAttribute("boxnumber");

%>
<title><%=Constant.getResourceStringValue("hr.user.User_Selector",user1.getLocale())%>  </title>
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








if(boxnu != -1 && boxnu != 99){
	var check1="false";

if(check1=="false"){
  var spanstart = '<div  id="taskassign_'+boxnu+'">';
  var username1 = '<input type="hidden" value="'+c_value+'" name="taskownername_'+boxnu+'"/>';
  var userid1 = '<input type="hidden"  value='+id_value+'  name="taskownerid_'+boxnu+'"/>';
  var isusergroup ="";
  var userlink ="";
if(isgrp != null && isgrp == 'Y'){
  isusergroup = '<input type="hidden"  value="Y"  name="isGroup_'+boxnu+'"/>';

  


  userlink = "<img src='jsp/images/User-Group-icon.png'><a href='#' onClick=window.open("+"'"+"usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id_value+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+c_value+"</a>";


}else{
	isusergroup = '<input type="hidden"  value="N"  name="isGroup_'+boxnu+'"/>';

	userlink = "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+id_value+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+c_value+"</a>";
}
var selectorlink = '<a href="#" onClick="opensearchtaskassign('+boxnu+');return false;"><img src="jsp/images/selector.gif" border="0"/></a>';
  var spanend = '</div>'; 

var newspanvalue = spanstart+username1+userlink+userid1+isusergroup+selectorlink+spanend;
var tmp = "taskassign_"+boxnu;

  window.opener.document.getElementById(tmp).innerHTML = newspanvalue;


  
 // alert(chechapprovenum);
 self.close();

}else{
alert("<%=Constant.getResourceStringValue("aquisition.applicant.approver.selector.existsmessage",user1.getLocale())%>");
}
	
}

}

		function searchcri(){
       
	   document.createUserForm.action = "user.do?method=searchOnboardTaskUserSelector&boxnumber=<%=boxnumber%>";
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
   
function resetData(){
  document.createUserForm.firstName.value="";
  document.createUserForm.lastName.value="";
  document.createUserForm.orgId.value="";
  document.createUserForm.departmentId.value="";
  document.createUserForm.groupName.value="";
  
  setTimeout("retrieveURL('user.do?method=loadDepartments')",200);
 
    
 }  
function init(){
document.createUserForm.firstName.focus();
//document.roleForm.roleCode.focus();
}


</script>
<body class="yui-skin-sam" onLoad="init()" onSubmit="searchcri()">

<html:form action="/user.do?method=searchusers&boxnumber=<%=boxnumber%>">
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("hr.user.Search_Employee",user1.getLocale())%></td>
</tr>
</table>

     <table border="0" width="100%">

    <tr>
		<td><%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%></td>
		<td><html:text  property="firstName" maxlength="200"/></td>
		<td><%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%></td>
		<td><html:text  property="lastName" maxlength="500"/></td>
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
						<%=Constant.getResourceStringValue("admin.BudgetCode.LoadingDepartments",user1.getLocale())%></span>
			
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
	
<tr>
       
		<td>
		<%=Constant.getResourceStringValue("hr.user.group_search",user1.getLocale())%>
		</td>
		
	
		<td>
		<%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isUserGroupSearch" maxlength="10" id="1" <%=(form.getIsUserGroupSearch()!= null && form.getIsUserGroupSearch().equals("Y"))? "Checked=true" : "" %> value="Y">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isUserGroupSearch" id="0" <%=(form.getIsUserGroupSearch()!= null && form.getIsUserGroupSearch().equals("N"))? "Checked=true" : "" %> value="N">
		</td>

		<td><%=Constant.getResourceStringValue("hr.user.group_name",user1.getLocale())%> </td>
	
		<td><html:text  property="groupName"/></td>

	</tr>
	<tr></tr>
	<tr>
		<td align="left">
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()"  class="button"></td>
        <td><input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>

	</tr>				
 </table>
 <br>
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("hr.user.Employee",user1.getLocale())%></td>
</tr>
</table>
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
<a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=usergrp.getUsergrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=val%></a>

	
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
<tr>
<td><%=Constant.getResourceStringValue("hr.user.Name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>


</tr>
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
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	      
</table>

</div>
</html:form>
</body>