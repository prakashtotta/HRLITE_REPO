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
String count = (String)request.getAttribute("count");
%>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<title> Vendor list selector </title>
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
if(usernamestr != null && usernamestr.length()>0){
usernamestr = usernamestr.substring(0, usernamestr.length()-1);
}

%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  

var boxnu = "<%=boxnumber%>";
var close1 = "<%=close%>";

var clickcountuserids = "";

	function discard(){
       self.close();
		}



if(close1 == "yes"){
	var usarr = '<%=useridstr%>'.split(','); 
var usnamearr = '<%=usernamestr%>'.split(','); 

var usrlen=usarr.length;
var usrnamelen=usnamearr.length;

    // opener.element.getElementByID("tx1").value = "search result";
var c_value = "";
var id_value = "";
var d_value = "";
 


if (usrlen != undefined)
{

 for (var i=0; i < usrlen; i++)
   {
 
	  if (usarr[i] != undefined) { 
      c_value = c_value + usnamearr[i] + ",";
	  id_value = id_value + usarr[i] +",";
	  var tempurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=editvendor&readPreview=2&userId="+usarr[i]+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=590,height=400"+"'"+")>"+usnamearr[i]+"</a>&nbsp;&nbsp;";
      d_value = d_value + tempurl;
	  }
   }
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;







var dyvalue = '<span id="ownerlist">'+d_value+'</span>';

	
if(boxnu == "systemrule"){
    //window.opener.document.getElementById("ownerlist").innerHTML = dyvalue;
   // window.opener.document['systemRuleForm'].userids.value=id_value.trim();
	//window.opener.document['systemRuleForm'].watchlistnamesnew.value=c_value.trim();
	//window.opener.document['systemRuleForm'].usernames.value=c_value.trim();


var sel = window.opener.document.forms['systemRuleForm'].elements['users'];
	window.opener.document['systemRuleForm'].userids.value=id_value.trim();
	window.opener.document['systemRuleForm'].usernames.value=c_value.trim();


if (usrlen != undefined)
{

 for (var i=0; i < usrlen; i++)
   {
 
	  if (usarr[i] != undefined) { 

	
	 window.opener.setUserValues(usnamearr[i],usarr[i]);
	  }
   }
}


}
if(boxnu == "jobreq"){
	var countvendor=window.opener.document['jobRequisitionForm'].countvendor.value;
    window.opener.document.getElementById("vendorlist").innerHTML = dyvalue;
    window.opener.document['jobRequisitionForm'].userids.value=id_value.trim();
	window.opener.document['jobRequisitionForm'].usernames.value=c_value.trim();
    window.opener.document['jobRequisitionForm'].countvendor.value=(+countvendor+<%=count%>);
}
self.close();
}

	function savedata(fields){


		
		var count="0";
       for(i=0;i<fields.length;i++){
		if(fields[i].checked== true){
              count++;
			   
			}
		}
		

		window.location.href = "user.do?method=vendorlistsearchpublishwindow&boxnumber=<%=boxnumber%>" +"&close=yes"+"&count="+count;
		//document.createUserForm.submit();
	
		}

		function searchcri(){
       
	   document.createUserForm.action = "user.do?method=vendorlistsearchpublishwindow&boxnumber=<%=boxnumber%>";
	   document.createUserForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loadingState").style.visibility = "visible";

	
	url=url+"&cid="+document.createUserForm.countryId.value;
	
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
    	document.getElementById("loadingState").style.visibility = "hidden";	
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
				  
		    	  if(name="states")	document.getElementById(name).innerHTML = content;
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

var url="user.do?method=setuseridsinsession&userid="+userid+"&username="+username;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	
	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}

}

function resetdata(){

	document.createUserForm.firstName.value="";
	document.createUserForm.lastName.value="";
	document.createUserForm.countryId.value="";
	document.createUserForm.stateId.value="";
	document.createUserForm.city.value="";
	

}

</script>
<body class="yui-skin-sam">
<html:form action="/user.do?method=vendorlistsearch&boxnumber=<%=boxnumber%>">
	
    <div class="div">
    <table border="0" width="100%">
    <tr>
       <td colspan="4" bgcolor="gray"><b><%=Constant.getResourceStringValue("hr.user.Search_vendors",user1.getLocale())%></b></td> 
    </tr>
    <tr>
		<td><%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%> :</td><td><html:text  property="firstName"/></td>
		<td><%=Constant.getResourceStringValue("hr.user.Primary_contact_full_name",user1.getLocale())%> :</td><td><html:text  property="lastName"/></td>
	</tr>


<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>
			<td>
			<html:select  property="countryId"  onchange="retrieveURL('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="createUserForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>
			<span class="textboxlabel" id="loadingState" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Location.LoadingState",user1.getLocale())%>......</span>
			</td>
			<td id="state"><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
			<td>
			<span id="states">
			<html:select  property="stateId">
			<option value=""></option>
			<bean:define name="createUserForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>
	 <tr>
		<td><%=Constant.getResourceStringValue("admin.Location.city",user1.getLocale())%> :</td><td><html:text  property="city"/></td>
		
	</tr>

 </table>

  <table>
	<tr>
		<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button" class="buttoon">
		<input type="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" name="reset" onclick="resetdata()" class="button" class="buttoon"/>
		
	    </td>
	</tr>				
 </table>
 </div>
<br>
<div class="div">
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("hr.user.Vendor_List",user1.getLocale())%></b></td>
</tr>
</table>
<div id="dynamicdata">

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
<%
 if(userList != null && userList.size()>0){
%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("hr.user.Primary_contact_full_name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>

</tr>
 <%

 


	 for(int i=0;i<userList.size();i++){

		 User user = (User)userList.get(i);
   		 String val = user.getFirstName();
		 String bgcolor = "";
	     if(i%2 == 0)bgcolor ="#B2D2FF";
             String checked = "";
	     if(checkeduserids.contains(String.valueOf(user.getUserId()))){
		     checked = "checked=true";
	     }

%>


	<tr bgcolor=<%=bgcolor%>>
		<td ><input type="checkbox" <%=checked%> name="userId" id="<%=user.getUserId()%>" value="<%=val%>" onClick="clickcount('<%=user.getUserId()%>','<%=val%>')">

		<a href="#" onClick="window.open('user.do?method=editvendor&readPreview=2&userId=<%=user.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=590,height=400')"><%=user.getFirstName()%></a>

		

		</td>
		<td>
		<%=(user.getLastName() == null)?"":user.getLastName()%>
		</td>
		<td>
		<%=(user.countryValue == null)?"":user.countryValue%>
		
		</td>
		<td>
		<%=(user.getCity() == null)?"":user.getCity()%>
		
		</td>
		<td>
		<%=(user.getPhoneOffice() == null)?"":user.getPhoneOffice()%>
		
		</td>
	</tr>


<%

	 }
 %>
 	</table>
 <% 
 }

 %>

<%  if(userList != null && userList.size()<1){ %>
<%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%><%}%>
<table>
		  
	<tr>
				<td>
				<%  if(userList != null && userList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata(document.createUserForm.userId)" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	      
</table>

</div>
</html:form>
</body>