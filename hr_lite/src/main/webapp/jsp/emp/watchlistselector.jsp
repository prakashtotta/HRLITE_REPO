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
String close = request.getParameter("close");
System.out.println("close"+close);
%>

<%
List checkeduserids1 = new ArrayList();
List checkedusernames1 = new ArrayList();
if(session.getAttribute("checkeduserids") != null){
checkeduserids1 = (ArrayList)session.getAttribute("checkeduserids");

}
if(session.getAttribute("checkedusernames") != null){
checkedusernames1 = (ArrayList)session.getAttribute("checkedusernames");

}

String useridstr = "";
for(int i=0;i<checkeduserids1.size();i++){
	useridstr = useridstr + checkeduserids1.get(i)+",";
}
if(useridstr != null && useridstr.length()>0){
useridstr = useridstr.substring(0, useridstr.length()-1);
}

String usernamestr = "";
for(int i=0;i<checkedusernames1.size();i++){
	System.out.println("checkedusernames1.get(i)"+checkedusernames1.get(i));
	usernamestr = usernamestr + checkedusernames1.get(i)+",";
}
System.out.println("usernamestr"+usernamestr);
if(usernamestr != null && usernamestr.length()>0){
usernamestr = usernamestr.substring(0, usernamestr.length()-1);
}
System.out.println("usernamestr"+usernamestr);
%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  

var boxnu = "<%=boxnumber%>";
var close1 = "<%=close%>";

var clickcountuserids = "";

	function discard(){
       self.close();
		}

	function resetData(){
		  document.createUserForm.firstName.value="";
		  document.createUserForm.lastName.value="";
		  document.createUserForm.orgId.value="";
		  document.createUserForm.departmentId.value="";
		  

		  setTimeout("retrieveURL('user.do?method=loadDepartments')",200);
		  
		 
		 }  

if(close1 == "yes"){
	//document.write('Search employee');
	document.write('<br>');
	var usarr = '<%=useridstr%>'.split(','); 
var usnamearr = '<%=usernamestr%>'.split(','); 
//document.write("usnamearr"+usnamearr);
var usrlen=usarr.length;
var usrnamelen=usnamearr.length;

    // opener.element.getElementByID("tx1").value = "search result";
var c_value = "";
var id_value = "";
var d_value = "";
 var useridarray=new Array();


if (usrlen != undefined)
{

 for (var i=0; i < usrlen; i++)
   {
 
	  if (usarr[i] != undefined) { 
      c_value = c_value + usnamearr[i] + ",";
	  id_value = id_value + usarr[i] +",";
       useridarray[i]=usarr[i];
	  var tempurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+usarr[i]+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+usnamearr[i]+"</a>&nbsp;&nbsp;";
      d_value = d_value + tempurl;
	  }
   }
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;







var dyvalue = '<span id="watchlist">'+d_value+'</span>';

if(boxnu == "usergroup"){
	//var idvalseperatecoma=id_value;

    var getuseridlist = window.opener.document['userGroupForm'].users.length;
	var check1="false";	
	var idarray=new Array();
    var idarraynew1=new Array();
	if(getuseridlist > 0){
	for(i=0;i<getuseridlist;i++){
   idarray[i]=window.opener.document['userGroupForm'].users.options[i].value+",";  
  }
for(j=0;j<useridarray.length;j++){
     for(m=0;m<idarray.length;m++){
        if(useridarray[j]+","==idarray[m]){
        check1="true";
	    break;
        }
       }
       if(check1=="true"){
	   break;
    }
  }
}
	
if(check1=="false"){
	var sel = window.opener.document.forms['userGroupForm'].elements['users'];
	window.opener.document['userGroupForm'].userids.value=id_value.trim();
	window.opener.document['userGroupForm'].usernames.value=c_value.trim();	
    window.opener.document['userGroupForm'].useridlist.value=idarray+" "+id_value;

if (usrlen != undefined)
{

 for (var i=0; i < usrlen; i++)
   {
 
	  if (usarr[i] != undefined) { 

	
	 window.opener.setUserValues(usnamearr[i],usarr[i]);
	  }
   }
}
self.close();
}else{
alert("selected user already exists please deselect duplicate user");
}

}else if(boxnu == "initiategoal"){


	var getuseridlist = window.opener.document['goalForm'].users.length;
	var check1="false";	
	var idarray=new Array();
    var idarraynew1=new Array();
	if(getuseridlist > 0){
	for(i=0;i<getuseridlist;i++){
   idarray[i]=window.opener.document['goalForm'].users.options[i].value+",";  
  }
for(j=0;j<useridarray.length;j++){
     for(m=0;m<idarray.length;m++){
        if(useridarray[j]+","==idarray[m]){
        check1="true";
	    break;
        }
       }
       if(check1=="true"){
	   break;
    }
  }
}
	
if(check1=="false"){
	var sel = window.opener.document.forms['goalForm'].elements['users'];
	window.opener.document['goalForm'].userids.value=id_value.trim();
	window.opener.document['goalForm'].usernames.value=c_value.trim();	
    window.opener.document['goalForm'].useridlist.value=idarray+" "+id_value;

if (usrlen != undefined)
{

 for (var i=0; i < usrlen; i++)
   {
 
	  if (usarr[i] != undefined) { 

	
	 window.opener.setUserValues(usnamearr[i],usarr[i]);
	  }
   }
}
self.close();
}else{
alert("selected employee already exists");
}

}else if(boxnu == "addreviewer"){
	window.opener.document['scheduleInterviewForm'].watchlistids.value=id_value.trim();
	window.opener.document['scheduleInterviewForm'].watchlistnamesnew.value=c_value.trim();
	window.opener.document['scheduleInterviewForm'].watchlistnames.value=c_value.trim();
self.close();
}else if(boxnu == "systemrule"){
    window.opener.document.getElementById("ownerlist").innerHTML = dyvalue;
    window.opener.document['systemRuleForm'].userids.value=id_value.trim();
	//window.opener.document['systemRuleForm'].watchlistnamesnew.value=c_value.trim();
	window.opener.document['systemRuleForm'].usernames.value=c_value.trim();

self.close();
}else{


window.opener.document.getElementById("watchlist").innerHTML = dyvalue;

window.opener.document[PFormName].watchlistids.value=id_value.trim();
window.opener.document[PFormName].watchlistnames.value=c_value.trim();

window.location.href = "user.do?method=removeuseridsinsession";
self.close();
}
//self.close();
}else{

}



	function savedata(fields){	
		var checkval="false";
		for(i=0;i<fields.length;i++){
			if(fields[i].checked== true){
               checkval="true";
			   break;
			}else{
               checkval="false";
			}
			}
		if(checkval=="true"){



	// only refresh the page and add close=yes 
window.location.href = "user.do?method=watchlistsearch&boxnumber=<%=boxnumber%>" +"&close=yes";
//document.createUserForm.submit();
		}else{
         alert("<%=Constant.getResourceStringValue("hr.usergroup.createpage.userselector.notselectedmessage",user1.getLocale())%>");
		}
		}

		function searchcri(){
       
	   document.createUserForm.action = "user.do?method=watchlistsearch&boxnumber=<%=boxnumber%>";
	   document.createUserForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}




function appendSelectedUsers(theSel, newText, newValue)
{
	alert(theSel+" "+theSel.length+" "+newText);
  if (theSel.length == 0) {
    var newOpt1 = new Option(newText, newValue);
    theSel.options[0] = newOpt1;
    theSel.selectedIndex = 0;
  } else if (theSel.selectedIndex != -1) {
    var selText = new Array();
    var selValues = new Array();
    var selIsSel = new Array();
    var newCount = -1;
    var newSelected = -1;
    var i;
    for(i=0; i<theSel.length; i++)
    {
      newCount++;
      selText[newCount] = theSel.options[i].text;
      selValues[newCount] = theSel.options[i].value;
      selIsSel[newCount] = theSel.options[i].selected;
      alert(newCount+" "+ theSel.selectedIndex);
      if (newCount == theSel.selectedIndex) {
        newCount++;
        selText[newCount] = newText;
        selValues[newCount] = newValue;
        selIsSel[newCount] = false;
        newSelected = newCount - 1;
      }
    }
    for(i=0; i<=newCount; i++)
    {
      var newOpt = new Option(selText[i], selValues[i]);
      theSel.options[i] = newOpt;
      theSel.options[i].selected = selIsSel[i];
    }
  }
}

function insertSelectedUsers(theSel, newText, newValue)
{
  if (theSel.length == 0) {
    var newOpt1 = new Option(newText, newValue);
    theSel.options[0] = newOpt1;
    theSel.selectedIndex = 0;
  } else if (theSel.selectedIndex != -1) {
    var selText = new Array();
    var selValues = new Array();
    var selIsSel = new Array();
    var newCount = -1;
    var newSelected = -1;
    var i;
    for(i=0; i<theSel.length; i++)
    {
      newCount++;
      if (newCount == theSel.selectedIndex) {
        selText[newCount] = newText;
        selValues[newCount] = newValue;
        selIsSel[newCount] = false;
        newCount++;
        newSelected = newCount;
      }
      selText[newCount] = theSel.options[i].text;
      selValues[newCount] = theSel.options[i].value;
      selIsSel[newCount] = theSel.options[i].selected;
    }
    for(i=0; i<=newCount; i++)
    {
      var newOpt = new Option(selText[i], selValues[i]);
      theSel.options[i] = newOpt;
      theSel.options[i].selected = selIsSel[i];
    }
  }
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
	 

function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
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
   
function clickcount(userid,username){

var securityparam = "&csrfcode="+document.createUserForm["csrfcode"].value;
	var parameters="method=setuseridsinsession&userid="+userid+"&username="+username+securityparam;
//mypostrequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
//mypostrequest.send(parameters)


	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeNo;
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
    	}
  	}
}


function clickcount(userid,username){

var securityparam = "&csrfcode="+document.createUserForm["csrfcode"].value;
	var parameters="method=setuseridsinsession&userid="+userid+"&username="+username+securityparam;
//mypostrequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
//mypostrequest.send(parameters)


	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeNo;
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
    	}
  	}
}



function clickcountallselected(allids,allnames){

var securityparam = "&csrfcode="+document.createUserForm["csrfcode"].value;
	var parameters="method=setuseridsinsessionallselect&userids="+allids+"&usernames="+allnames+securityparam;
//mypostrequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
//mypostrequest.send(parameters)


	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeNo;
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
    	}
  	}
}

/*var url="user.do?method=setuseridsinsession&userid="+userid+"&username="+username;

  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    
alert("on window");
	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {
		
		}
	    req.send(null);
  	} else if (window.ActiveXObject) {
		
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	//req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
*/

function processStateChangeNo() {
	
}



function removesessionbyunckeckedAll(){

var securityparam = "&csrfcode="+document.createUserForm["csrfcode"].value;
	var parameters="method=removeuseridsinsession"+securityparam;
//mypostrequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
//mypostrequest.send(parameters)


	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			
	    	req.onreadystatechange=processStateChangeNo;
    		req.open("POST", "user.do", true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send(parameters);
			
    	}
  	}
}

function uncheckAll(field,getUserId1,getVal1)
{
	
for (i = 0; i < field.length; i++){
	field[i].checked = false ;
}
removesessionbyunckeckedAll();
}

function checkAll(field,getUserId1,getVal1)
{
  
   var arrayval=new Array();
   var arrayname=new Array();
   var allids="";
   var allnames="";
	for(j=0;j<getUserId1.length;j++){
     //arrayval[j]=getUserId1[j].value;
     allids=allids+","+getUserId1[j].value;
	}
//alert(arrayval[4]);
	for(k=0;k<getVal1.length;k++){
     //arrayname[k]=getVal1[k].value;
      allnames=allnames+","+getVal1[k].value;
	}
	//alert(allids);
	for(i = 0; i < field.length; i++){
     field[i].checked = true ;
	 
      
   
	}
	clickcountallselected(allids,allnames);

}

function checkselectalluser(field,getUserId1,getVal1){

	if(document.createUserForm.checkalluser.checked){
		checkAll(field,getUserId1,getVal1);
	}else{
   
		uncheckAll(field,getUserId1,getVal1);
	}



}

</script>
<body class="yui-skin-sam">
<html:form action="/user.do?method=searchusers&boxnumber=<%=boxnumber%>">
	
    <table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("hr.user.Search_Employee",user1.getLocale())%></td>
</tr>
</table>
<div class="div">
    <table border="0" width="100%">
    
    <tr>
		<td><%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%> </td><td><html:text  property="firstName"/></td>
		<td><%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%> </td><td><html:text  property="lastName"/></td>
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
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>...</span>
			
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
	


			
 </table>
 <br>
  <table>
 	<tr>
		<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
		<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button"></td>
		<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>"onClick="discard()" class="button"></td><td></td><td></td>
	</tr>				
 </table>
</div>
<br>
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("hr.user.Employee",user1.getLocale())%></td>
</tr>
</table>
<div id="dynamicdata" class="div">

<%
List checkeduserids = new ArrayList();
if(session.getAttribute("checkeduserids") != null){
checkeduserids = (ArrayList)session.getAttribute("checkeduserids");

}
 List userList = form.getUserList();
 if(userList != null && userList.size()>0){
%>

<pagination-tag:pager action="user" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
 <%

 if(userList != null){
%>
<tr>
<td>
<%
if(userList != null && userList.size()>0){
%>
<input type="checkbox" name="checkalluser" onClick="checkselectalluser(document.createUserForm.userId,document.createUserForm.getUserId1,document.createUserForm.getVal1)"/>
<%}%>
&nbsp;
<%=Constant.getResourceStringValue("hr.user.Name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("hr.user.Role",user1.getLocale())%></td>


</tr>


<%
	 for(int i=0;i<userList.size();i++){

		 User user = (User)userList.get(i);
    String val = user.getFirstName()+ " "+ user.getLastName();
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
    String checked = "";
	if(checkeduserids.contains(String.valueOf(user.getUserId()))){
		checked = "checked=true";
	}

%>


<tr bgcolor=<%=bgcolor%>>
<input type="hidden" name="getUserId1" value="<%=user.getUserId()%>"/>
<input type="hidden" name="getVal1" value="<%=val%>" />
<td ><input type="checkbox" <%=checked%> name="userId" id="<%=user.getUserId()%>" value="<%=val%>" onClick="javascript:clickcount('<%=user.getUserId()%>','<%=val%>')">

<a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=user.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=user.getFirstName()+ " "+user.getLastName()%></a>

	</script>

</td>
<td>
<%=(user.getOrganization() == null)?"":user.getOrganization().getOrgName()%>
</td>
<td>
<%=(user.getDepartment() == null)?"":user.getDepartment().getDepartmentName()%>

</td>
<td><%=(user.getRole().getRoleName() == null)?"":user.getRole().getRoleName()%></td>
</tr>


<%

	 }
 }

 %>

<%  if(userList != null && userList.size()<1){ %>
<%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%><%}%>

		  
	<tr>
				<td>
				<%  if(userList != null && userList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata(document.createUserForm.userId)" class="button">
				
			<%}%>
				</td>
				
			</tr>	      
</table>

</div>
</html:form>
</body>