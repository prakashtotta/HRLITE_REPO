<%@ include file="../common/include.jsp" %>

 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="form" name="locationForm" type="com.form.LocationForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>

<script language="javascript">

var  PFormName= opener.document.forms[0].name;  

 
	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.locationForm.locationid.length; 
if(len == undefined) len = 1; 

if (document.locationForm.locationid.length != undefined)
{

 for (var i=0; i < document.locationForm.locationid.length; i++)
   {
   if (document.locationForm.locationid[i].checked == true)
      {
	   
      c_value =document.locationForm.locationid[i].value + "\n";
	  id_value =  document.locationForm.locationid[i].id;
      }
   }
}else{
	if (document.locationForm.locationid.checked == true) {
	c_value = document.locationForm.locationid.value + "\n";
	  id_value = document.locationForm.locationid.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"location.do?method=locationdetails&readPreview=2&id="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=320"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="locationid">'+tempurl+'</span>';

 if(PFormName == 'jobRequisitionForm'){

opener.document[PFormName].locationId.value=id_value;


}
 if(PFormName == 'organizationForm'){
	 window.opener.document.getElementById("locationid").innerHTML = dyvalue;
opener.document[PFormName].locationId.value=id_value;
opener.document[PFormName].locationName.value=c_value.trim();


}

//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

//opener.document[PFormName].locationId.value=id_value;
//opener.document[PFormName].evaluationcriteria.value=c_value;




self.close();
	   
		}

		function searchcri(){
       
	   document.locationForm.action = "location.do?method=searchlocations&readPreview=1";
	   document.locationForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
 function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&cid="+document.locationForm.countryId.value;
	
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
function resetData(){
  document.locationForm.locationName.value="";
  document.locationForm.locationCode.value="";

  document.locationForm.countryId.value="";
  document.locationForm.stateId.value="";
  document.locationForm.regionId.value="";
  
    
 }  
</script>
<html:form action="/location.do?method=searchlocations&readPreview=1">

<table border="0" width="100%" colspan="2">
<tr width="40%" valign=top>

<table border="0" width="100%">
<tr>
    <td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.Location.searchlocation",user1.getLocale())%></b></td>
</tr>
<tr>
     <td><%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%></td>
</tr>
<tr>
    <td><html:text property="locationName"/></td>
</tr>	

<tr>
     <td><%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%></td>
</tr>
<tr>
	<td>
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="locationForm" property="countryList" id="countryList" />


            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Location.LoadingState",user1.getLocale())%>......</span>
			</td>
</tr>
<tr>
     <td><%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%></td>
</tr>
<tr>
	<td>
			<span id="states">
			<html:select  property="stateId">
			<option value=""></option>
			<bean:define name="locationForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
</tr>



<tr>
	<td>								
       <input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">	
	    <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
		   <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	</td>				
</tr>
</table>
</tr>
<tr width="60%" valign=top>

<table border="0" width="100%">

<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%><b></td>
</tr>
</table>
<%
  List locationsList = form.getLocationsList();
 if(locationsList != null && locationsList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<td><b><%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%></b></td>
</tr>

 <%

 
 if(locationsList != null){

	 for(int i=0;i<locationsList.size();i++){

		 Location loc = (Location)locationsList.get(i);


%>


<tr>
<td ><input type="radio" name="locationid" id="<%=loc.getLocationId()%>" value="<%=loc.getLocationName()%>">

<a href="#" onClick="window.open('location.do?method=locationdetails&readPreview=2&id=<%=loc.getLocationId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=320')"><%=loc.getLocationName()%></a>

	</script>
</td>

<td>
<%=(loc.countryName == null)?"":loc.countryName%>
</td>
<td>
<%=(loc.stateName == null)?"":loc.stateName%>
</td>
</tr>


<%

	 }
 }

 %>
<%  if(locationsList != null && locationsList.size()<1){ %>
<tr>
				<td>
<font color="red"><b> <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%> </b></font>
</td>
				
			</tr>
<%}%>
		  
	<tr>
				<td>
				<%  if(locationsList != null && locationsList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	 		  
		      

</table>
</tr>
</table>
</html:form>