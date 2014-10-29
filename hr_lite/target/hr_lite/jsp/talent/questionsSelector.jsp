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
<title><%=Constant.getResourceStringValue("admin.list.questiongroup.questionsselector.userselector",user1.getLocale())%></title>
<bean:define id="form" name="createUserForm" type="com.form.CreateUserForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  

var boxnu = "<%=boxnumber%>";



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



if(boxnu != -1){
   
  var spanstart = '<span id="approverspan_'+boxnu+'">' + 'Level-'+boxnu+'&nbsp;&nbsp';
  var username1 = '<input type="text" readonly size = "50" maxlength= "100" value="'+c_value+'" name="username_'+boxnu+'"/>';
  var userid1 = '<input type="hidden"  value='+id_value+'  name="userid_'+boxnu+'"/>';
  var spanend = '</span>'; 

var newspanvalue = spanstart+username1+userid1+spanend;
var tmp = "approverspan_"+boxnu;

  window.opener.document.getElementById(tmp).innerHTML = newspanvalue;



	
}

if(boxnu == -1){

var tempurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+id_value+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="hiringMgrId">'+tempurl+'</span>';

window.opener.document.getElementById("hiringMgrId").innerHTML = dyvalue;


//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document[PFormName].hiringMgrId.value=id_value;
//opener.document[PFormName].parentOrgName.value=c_value;

//opener.document[PFormName].evaluationcriteria.value=c_value;
}




self.close();
	   
		}

		function searchcri(){
       
	   document.createUserForm.action = "user.do?method=searchusers&boxnumber=<%=boxnumber%>";
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
   


</script>


</script>
<body  class="yui-skin-sam">
<html:form action="/user.do?method=searchusers&boxnumber=<%=boxnumber%>">
	<div class="div">
	<table border="0" width="100%" colspan="2">
    <th width="35%" valign=top>
    <table border="0" width="100%">
    <tr>
       <td bgcolor="gray">Search Employee</td>
    </tr>
    <tr>
		<td>First Name :</td>
	</tr>
	<tr>
		<td><html:text  property="firstName"/></td>
	</tr>
	<tr>
		<td>Last Name :</td>
	</tr>
	<tr>
		<td><html:text  property="lastName"/></td>
    </tr>
<tr>
			<td>Organization</td>
			</tr>
			<tr><td>
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<option value=""></option>
			<bean:define name="createUserForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						Loading Departments......</span>
			
			</td>
		</tr>
		
		<tr>
			<td>Department</td>
			</tr>
			<tr>
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
		<td><input type="button" name="search" value="search" onClick="searchcri()" class="button">
		<input type="button" name="login" value="cancel" onClick="discard()" class="button"></td>
	</tr>				
 </table></th>
<th width="65%" valign=top>
<table border="0" width="100%">
<tr>
<td bgcolor="gray">Employee</td>
</tr>
</table>
<div id="dynamicdata" class="div">
<%
 List userList = form.getUserList();
 if(userList != null && userList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<td>Name</td><td>Organization</td>
<td>Department</td>


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

<a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=user.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=user.getFirstName()+ " "+user.getLastName()%></a>

	</script>
</td>
<td>
<%=(user.getOrganization() == null)?"":user.getOrganization()%>
</td>
<td>
<%=(user.getDepartment() == null)?"":user.getDepartment()%>

</td>
</tr>


<%

	 }
 }

 %>

<%  if(userList != null && userList.size()<1){ %>
 Data not found.
<%}%>
		  
	<tr>
				<td>
				<%  if(userList != null && userList.size()>0){ %>
				<input type="button" name="login" value="submit" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="cancel" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	      
</table></th>
</table>
</div>
</html:form>
</body>