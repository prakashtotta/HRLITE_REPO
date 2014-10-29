
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<bean:define id="projectcodeform" name="projectCodesForm" type="com.form.ProjectCodesForm" />
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function createProjectCode(){
	var url = "<%=request.getContextPath()%>/projectcodes.do?method=createProjectCodes&readPreview=3";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.ProjectCode.createscreenname",user1.getLocale())%>',url,400,600, messageret);
}
function editprojectCode(id){
	var url = "<%=request.getContextPath()%>/projectcodes.do?method=editProjectCodes&readPreview=1&projectCodeId="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.ProjectCode.editscreenname",user1.getLocale())%>',url,400,600, messageret);
}


function showmessage(returnval) { 
	//window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}

	function retrieveURL(url) {
	   //convert the url to a string
	    document.getElementById("loading").style.visibility = "visible";

		
		url=url+"&orgId="+document.projectCodesForm.orgId.value.trim();
		
		
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

function searchdata(){
	document.projectCodesForm.action="projectcodes.do?method=searchProjectCode";
	document.projectCodesForm.submit();
}
</script>

<body class="yui-skin-sam">
<html:form action="/projectcodes.do?method=searchProjectCode">
<b>Project code search</b> <br><br>
<div  class="div">
 <table border="0" width="100%">
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.Organization",user1.getLocale())%></td>
			
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('projectcodes.do?method=loadDepartments');">
			<bean:define name="projectCodesForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>....</span>
		
			</td>
		
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.Department",user1.getLocale())%></td>
			
			<td >
			<span id="departments">
			<html:select property="departmentId" >
			<option value="0"></option>
			<bean:define name="projectCodesForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
			</span>
		
			</td>

			<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchdata()" class="button"></td>
		</tr>
		</table>
		</div>
</html:form>
<br>
<a class="button" href="#" onClick="createProjectCode()"><%=Constant.getResourceStringValue("admin.ProjectCode.createscreenname",user1.getLocale())%></a> <br><br>

 <b><%=Constant.getResourceStringValue("admin.ProjectCode.details",user1.getLocale())%></b><br><br>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editprojectCode('" + oRecord.getData("projectId") + "')"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"projectId", hidden:true, sortable:true},
{key:"projCode",label:"<%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"projName",label:"<%=Constant.getResourceStringValue("admin.ProjectCode.name",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"effectiveStartDate",label:"<%=Constant.getResourceStringValue("admin.ProjectCode.startdate",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"effectiveEndDate",label:"<%=Constant.getResourceStringValue("admin.ProjectCode.enddate",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"orgName",label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>", sortable:false,resizeable:true},
{key:"departmentName",label:"<%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>", sortable:false,resizeable:true}

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/projectCodelistpage.jsp?orgId=<%=projectcodeform.getOrgId() %>&departmentId=<%=projectcodeform.getDepartmentId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"projectId"},
{key:"projCode"},
{key:"projName"},
{key:"effectiveStartDate"},
{key:"effectiveEndDate"},
{key:"orgName"},
{key:"departmentName"}

					
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=projectId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"projectId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
