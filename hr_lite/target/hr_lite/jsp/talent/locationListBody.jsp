
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
 <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>


<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<bean:define id="locationform" name="locationForm" type="com.form.LocationForm" />

<script language="javascript">


 function searchlocation(){
	
document.locationForm.action = "location.do?method=searchlocationslist";
document.locationForm.submit();
}

function editlocation(location_id){
	var url = "<%=request.getContextPath()%>/location.do?method=locationdetails&id="+location_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Location.editlocationscreenname",user1.getLocale())%>',url,400,600, messageret);
}
	

function createLocation(){
	var url = "<%=request.getContextPath()%>/location.do?method=createLocation&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Location.addlocationscreenname",user1.getLocale())%>',url,400,600, messageret);
}
	
	
function showmessage(returnval) { 
	//window.location.reload();
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
  document.locationForm.countryId.value="";
  document.locationForm.stateId.value="";
  document.locationForm.city.value="";
  document.locationForm.locationName.value="";
  document.locationForm.locationCode.value="";
 } 
function showmessage(returnval) { 
	//window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}

function init(){
document.locationForm.locationName.focus();

}

</script>   

 
<body class="yui-skin-sam" onLoad="init()">

<html:form action="/location.do?method=searchlocationslist">
<b>Search locations</b><br><br>
<div class="div">
<table border="0" width="100%">
		
	<tr>
			<td width="15%"><%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%> </td>
			<td>
			<html:text property="locationName" maxlength="100"/>
           </td>
			<td width="10%"><%=Constant.getResourceStringValue("admin.Location.city",user1.getLocale())%>  </td>
			<td>
			<html:text property="city"/>
			</td>
	</tr>
	<tr>
		<td><%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%></td>
		<td  width="45%"><html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
		<option value=""></option>
			<bean:define name="locationForm" property="countryList" id="countryList" />


            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Location.LoadingState",user1.getLocale())%></span>
		</td>
		<td><%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%> </td>
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
<td colspan="2">
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchlocation()" class="button">
<input type="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button" /> 
</td>
<td>
</td>
</tr>
</table>
</div>
</html:form>
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>

<br><br>
<a class="button" href="#" onClick="javascript:createLocation()"><%=Constant.getResourceStringValue("admin.Location.addlocationscreenname",user1.getLocale())%>  </a>
<br><br>

<b><%=Constant.getResourceStringValue("admin.Location.LocationList",user1.getLocale())%></b>   <br><br>



<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editlocation('" + oRecord.getData("locationId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"locationId", hidden:true, sortable:true},
{key:"locationName",label:"<%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"countryName",label:"<%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%>", sortable:false,resizeable:true},
{key:"stateName",label:"<%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%>", sortable:false,resizeable:true},
{key:"city",label:"<%=Constant.getResourceStringValue("admin.Location.city",user1.getLocale())%> ", sortable:true,resizeable:true},
{key:"zip",label:"<%=Constant.getResourceStringValue("admin.Location.zip",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"phone",label:"<%=Constant.getResourceStringValue("admin.Location.phone",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"fax",label:"<%=Constant.getResourceStringValue("admin.Location.fax",user1.getLocale())%>", sortable:true,resizeable:true}

        ];




    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/locationListPage.jsp?city=<%=locationform.getCity()%>&stateid=<%=locationform.getStateId()%>&countryid=<%=locationform.getCountryId()%>&locationcode=<%=locationform.getLocationCode()%>&locationname=<%=locationform.getLocationName()%>&searchpagedisplay=<%=searchpagedisplay%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"locationId"},
{key:"locationName"},
{key:"locationCode"},
{key:"zip"},
{key:"phone"},
{key:"fax"},
{key:"city"},
{key:"stateName"},
{key:"countryName"},
{key:"regionName"}


			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=locationId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"locationId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>
