<%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<link rel="stylesheet" href="jsp/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />

<script type="text/javascript" src="jsp/dhtmlmodal/windowfiles/dhtmlwindow.js">

/***********************************************
* DHTML Window Widget- © Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for legal use.
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />
<style>
span1{color:#ff0000;}
</style>
<script type="text/javascript">
function openmypage(url){ //Define arbitrary function to run desired DHTML Window widget codes
ajaxwin=dhtmlwindow.open("ajaxbox", "ajax", url, "Organization", "width=450px,height=400px,left=200px,top=100px,resize=1,scrolling=1")
//ajaxwin.onclose=function(){return window.confirm("Close window 3?")} //Run custom code when window is about to be closed
}
</script>
<script language="javascript">


function closewindow(){
	 	   window.top.hidePopWin();
	  
	}

function closewindow2(){
	self.parent.location.reload();
	 	   window.top.hidePopWin();

	  
	}




function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   window.top.hidePopWin();
	   } 
	}

function assignusernamepassword(){
  window.open("user.do?method=assignusernamescr&userId=<%=cform.getUserId()%>", "assignusername","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=400,height=400");
}


function updatedata(){

	var alertstr = "";
   
	var firstName = document.createUserForm.firstName.value;
	var lastName = document.createUserForm.lastName.value;
	var showalert=false;

   if(firstName == "" || firstName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Short_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(lastName == "" || lastName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Full_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	
	


	 if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.createUserForm.action = "user.do?method=updatemydetailsvendor&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();
	}



	

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
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
   

</script>



<%
String isuseradded = (String)request.getAttribute("isuseradded");
	
if(isuseradded != null && isuseradded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.User_added",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>




<html:form action="/user.do?method=logon" >

<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>


<tr>
			<td><b><%=Constant.getResourceStringValue("hr.user.Edit_my_details",user1.getLocale())%></b></td>
			<td>
					
			</td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Short_Name",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="firstName" size="20" maxlength="50"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Full_Name",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="lastName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="emailId" size="20"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>
			<td><html:text property="phoneHome" size="20"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>
			<td><html:text property="phoneOffice" size="20"/></td>
		</tr>
		
		

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>
			<td>
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<bean:define name="createUserForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Location.LoadingState",user1.getLocale())%>......</span>
			</td>
		</tr>

       <tr>
			<td id="state" ><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
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
			<td><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>
			<td><html:text property="city" size="20"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td>
			<html:select  property="localeId">
			<bean:define name="createUserForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td>
			<html:select  property="timezoneId">
			<bean:define name="createUserForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
		</tr>

    

	  

		<tr>
			<td>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>

	
	</table>
</div>

</html:form>

