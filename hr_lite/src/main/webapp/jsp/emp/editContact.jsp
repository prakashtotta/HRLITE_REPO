<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/include.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="cform" name="userContactForm" type="com.form.employee.UserContactForm" />

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

function updatedata(){
 //alert("hi ..");
	  document.userContactForm.action = "usercontact.do?method=update";
	   document.userContactForm.submit();


}

function savedata(){
 //alert("hi ..");
	  document.userContactForm.action = "usercontact.do?method=save";
	   document.userContactForm.submit();


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

function retrieveURL(url) {

	   //convert the url to a string
	    document.getElementById("loading").style.visibility = "visible";

		url=url+"&cid="+document.userContactForm.countryId.value;
		//alert(url);
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
function replaceExistingWithNewHtml(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="states")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}
</script>
<%
String contactinfosaved = (String)request.getAttribute("contactinfosaved");
	
if(contactinfosaved != null && contactinfosaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.usercontactsaved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String contactinfoupdated = (String)request.getAttribute("contactinfoupdated");
	
if(contactinfoupdated != null && contactinfoupdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.usercontactupdated",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<html:form action="/usercontact.do?method=logon" enctype="multipart/form-data">

<table border="0" width="100%">


	    <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Other.Email",user1.getLocale())%></td>
			<td><html:text property="otherEmailId" size="40" maxlength="200"/>
			<html:hidden property="userId"/>
			<html:hidden property="userContactId"/>
			</td>
			
		</tr>
	    <tr>
			<td><%=Constant.getResourceStringValue("hr.user.MobileNo",user1.getLocale())%></td>
			
			<td><html:text property="mobileNo" size="40" maxlength="200"/></td>
		</tr>
	   <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>

			<td><html:text property="phoneHome" size="40" maxlength="200"/></td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.address1",user1.getLocale())%></td>

			<td><html:textarea property="address" cols="37" rows="5"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.address2",user1.getLocale())%></td>

			<td><html:textarea property="address2" cols="37" rows="5"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>

			<td><html:text property="city" size="40" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>

			<td>			
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="userContactForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_States",user1.getLocale())%>......</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
		<td>
			<span id="states">
			<html:select  property="stateId">
			<option value=""></option>
			<bean:define name="userContactForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.ZipCode",user1.getLocale())%></td>
		
			<td><html:text property="zip_code" size="40" maxlength="200"/></td>
		</tr>
		</table>
		<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getUserContactId() != 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
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
