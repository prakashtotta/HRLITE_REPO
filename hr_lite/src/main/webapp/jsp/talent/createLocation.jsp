<%@ include file="../common/include.jsp" %>
<html>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;  
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }
legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>
<script language="javascript">
function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";


	url=url+"&readPreview=3"+"&cid="+document.locationForm.countryId.value;
	
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
				  
		    	  if(name="states")
   					document.getElementById(name).innerHTML = content;
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


function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}


	
function init(){
setTimeout ( "document.locationForm.locationCode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
var alertstr = "";
	var showalert=false;
var city = document.locationForm.city.value.trim();
	var country=document.locationForm.countryId.value.trim();
		
		if(country == "" || country == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Country_required",user1.getLocale())%><br>";
		showalert = true;
		}

		if(city == "" || city == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.City_required",user1.getLocale())%><br>";
		showalert = true;
		}
		 


	if(document.locationForm.address.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Address_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.locationForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.locationForm.action = "location.do?method=saveLocation";
 document.locationForm.submit();
  
	}

function deletelocation(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	  document.locationForm.action = "location.do?method=deleteLocation&id="+'<bean:write name="locationForm" property="locationId"/>';
 document.locationForm.submit();
	  }
  
	}


function updatedata(){
	var alertstr = "";
	var showalert=false;


	var city = document.locationForm.city.value.trim();
	var country=document.locationForm.countryId.value.trim();
		
		if(country == "" || country == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Country_required",user1.getLocale())%><br>";
		showalert = true;
		}

		if(city == "" || city == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.City_required",user1.getLocale())%><br>";
		showalert = true;
		}
		 


	if(document.locationForm.address.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Address_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.locationForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.locationForm.action = "location.do?method=updateLocation&id="+'<bean:write name="locationForm" property="locationId"/>';
 document.locationForm.submit();

 
	}

	function closewindow(){
		 //self.parent.location.reload();

		 //self.parent.location.reload();
		 parent.parent.GB_hide();
	}

function loadcountry(){
	var rid=document.locationForm.regionId.value;
	//document.locationForm.action = "location.do?method=loadCountry&readPreview=3&rid="+rid;
   // document.locationForm.submit();
  
    document.getElementById("loadingcountry").style.visibility = "visible";
	url="location.do?method=loadCountry&readPreview=3&rid="+rid;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeCountry;
	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeCountry;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    

}
function processStateChangeCountry() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response

    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlCountry(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingcountry").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlCountry(newTextElements){
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
				  
		    	  if(name="countries")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
  	setStatenull();
}
function setStatenull() {

	 // document.getElementById("loadingcountry").style.visibility = "hidden";

		
		url="location.do?method=setStateNull";
		
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangesetStatenull;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangesetStatenull;
		        req.open("GET", url, true);
			    req.send();
				
	    	}

	  	}

	}
function processStateChangesetStatenull() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response

    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlsetstatenull(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtmlsetstatenull(newTextElements){
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
				  
		    	  if(name="states")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
  	
}
function loadstate(){
	document.locationForm.action = "location.do?method=loadState&readPreview=3&cid="+'<bean:write name="locationForm" property="countryId"/>';
    document.locationForm.submit();
   
}
</script>
<bean:define id="locationform" name="locationForm" type="com.form.LocationForm" />

<%
if(locationform.getStatus() != null && locationform.getStatus().equals("D")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Location.deletemsg",user1.getLocale())%> </font></td>

			<td><!-- <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>
<%}%>
<%
String locationupdated = (String)request.getAttribute("locationupdated");
	
if(locationupdated != null && locationupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Location.updatemsg",user1.getLocale())%></font></td>
			<td><!-- <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String locationsaved = (String)request.getAttribute("locationsaved");
	
if(locationsaved != null && locationsaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Location.savemsg",user1.getLocale())%></font></td>
			<td> <!--<a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String locationdeleted = (String)request.getAttribute("locationdeleted");
	
if(locationdeleted != null && locationdeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Location.deletemsg",user1.getLocale())%></font></td>
			<td> <!--<a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<body class="yui-skin-sam" onload="init()">
<html:form action="/location.do?method=saveLocation">
<div class="div">	
<%

if (locationform.getReadPreview() != null && locationform.getReadPreview().equals("2")){

%>	

<br>
	 <fieldset><legend><b><%=Constant.getResourceStringValue("admin.Location.locationDetails",user1.getLocale())%><b></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>	
	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%> </td>
			<td>
			<%=(locationform.getCountryName()==null)?"":locationform.getCountryName()%>
			
			</td>
		</tr>

		 <tr>
			<td id="state" ><%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%> </td>
			<td>
			<%=(locationform.getStateName()==null)?"":locationform.getStateName()%>
			
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.city",user1.getLocale())%> </td>
			<td><%=(locationform.getCity()==null)?"":locationform.getCity()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.address",user1.getLocale())%> </td>
			<td><%=(locationform.getAddress()==null)?"":locationform.getAddress()%></td>
		</tr>
		 <tr>
				<td><%=Constant.getResourceStringValue("admin.Location.zip",user1.getLocale())%> </td>
                <td><%=(locationform.getZip()==null)?"":locationform.getZip()%></td>
	  </tr>	

	  <tr>
				<td><%=Constant.getResourceStringValue("admin.Location.phone",user1.getLocale())%> </td>
	            <td><%=(locationform.getPhone()==null)?"":locationform.getPhone()%></td>
	  
	  </tr>	

	  <tr>
			   <td><%=Constant.getResourceStringValue("admin.Location.fax",user1.getLocale())%> </td>
			   <td><%=(locationform.getFax()==null)?"":locationform.getFax()%></td>
		</tr>
		
				


		</table>
		</fieldset>
		
	<%}else{%>	

	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
		<tr>
			<td></td>
			<td></td>
		</tr>
	
	<tr>
 		
			<td><%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%><font color="red">*</font></td>
			<td>
				<span id="countries">
				<html:select  property="countryId"  onchange="retrieveURL('location.do?method=loadState');">
			   	<option value=""></option>
				<bean:define name="locationForm" property="countryList" id="countryList" />
	            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
				
				</html:select>&nbsp;
				</span>
				<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
				<%=Constant.getResourceStringValue("admin.Location.LoadingState",user1.getLocale())%>
				</span>
			
			
			</td>
		</tr>
	
<%
String selectcountry = (String)request.getAttribute("selectcountry");
	

%>
		
		<tr>
			<td id="state" ><%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%></td>
			<td>
			<span id="states">
			<html:select  property="stateId">
	
			<bean:define name="locationForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.city",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="city" maxlength="200" size="60"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.address",user1.getLocale())%></td>
			<td><html:textarea property="address" cols="60" rows="4"/></td>
		</tr>
		 <tr>
				<td><%=Constant.getResourceStringValue("admin.Location.zip",user1.getLocale())%></td>
                <td><html:text property="zip" maxlength="100" /></td>
	  </tr>	

	  <tr>
				<td><%=Constant.getResourceStringValue("admin.Location.phone",user1.getLocale())%></td>
	            <td><html:text property="phone" maxlength="100"/></td>
	  
	  </tr>	

	  <tr>
			   <td><%=Constant.getResourceStringValue("admin.Location.fax",user1.getLocale())%></td>
			   <td><html:text property="fax" maxlength="100"/></td>
		</tr>
		
		<tr>
			  <td><%=Constant.getResourceStringValue("admin.Location.notes",user1.getLocale())%></td>
			  <td><html:textarea property="notes" cols="50" rows="4"/></td>
		</tr>
			
		<tr>
		<td colspan="2"><%if (locationform.getLocationId() > 0){%>
			<%
					if(locationform.getStatus() != null && !locationform.getStatus().equals("D")){
					%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletelocation()" class="button">
					<%}%>
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
	<%}%>	
		</table>

</div>
</html:form>


</body>

<%}%>