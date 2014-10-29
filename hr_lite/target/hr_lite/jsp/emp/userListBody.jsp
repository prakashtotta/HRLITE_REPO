<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

 

<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.USER_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.USER_SCREEN);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<%
////response.setHeader("Cache-Control", "no-cache");
//		//response.setHeader("Pragma", "no-cache");
//		//response.setIntHeader("Expires", 0);
%>
<bean:define id="aform" name="createUserForm" type="com.form.CreateUserForm" />

<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>

<script language="javascript">

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsUser";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,350,700, messageret);
}

function adduser(){
	var url = "<%=request.getContextPath()%>/user.do?method=firstpage"
	GB_showCenter('<%=Constant.getResourceStringValue("hr.user.Create_User",user1.getLocale())%>',url,350,750, messageret);

}

function editUser(jb_id){
	var url = "<%=request.getContextPath()%>/user.do?method=edituser&userId="+jb_id;
	GB_showCenter('<%=Constant.getResourceStringValue("hr.user.Edit_User",user1.getLocale())%>',url,350,750, messageret);	
}

 

function showmessage(returnval) { 
	window.location.reload();
	}


	function messageret(){
	//window.location.reload();

			}

		function searchcri(){
       
	   document.createUserForm.action = "user.do?method=searchuserlistpage";
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
function retrieveProjcodes(url) {
	   //convert the url to a string
	    document.getElementById("loadingp").style.visibility = "visible";

		
		url=url+"&departmentId="+document.createUserForm.departmentId.value;
		
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangeProj;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangeProj;
		        req.open("GET", url, true);
			    req.send();
				
	    	}

	  	}

	}
		 


	function processStateChangeProj() {
		if (req.readyState == 4) { // Complete
		    if (req.status == 200) { // OK response
		       	//document.getElementById("states").innerHTML = req.responseText;
	    	    //Split the text response into Span elements
	    		spanElements = splitTextIntoSpan(req.responseText);

	    		//Use these span elements to update the page
			    replaceExistingWithNewHtmlProj(spanElements);
			    //onOtherStateSel();
	    	} 
	    	document.getElementById("loadingp").style.visibility = "hidden";	
	  	}
	}


	function replaceExistingWithNewHtmlProj(newTextElements){
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
					  
			    	  if(name="projectcodes")	document.getElementById(name).innerHTML = content;
				  }	   			
	   	 	}
		}

	}
	
function resetData(){
  document.createUserForm.firstName.value="";
  document.createUserForm.orgId.value="";
  document.createUserForm.departmentId.value="";
  document.createUserForm.statuscri.value="";
  document.createUserForm.designationId.value="";
  document.createUserForm.userName.value="";
  document.createUserForm.projectId.value="";
  document.createUserForm.lastName.value="";
  document.createUserForm.emailId.value="";
   
    
 }  

function init(){
document.createUserForm.firstName.focus(); 

}
</script>

 <b><%=Constant.getResourceStringValue("hr.user.Search_users",user1.getLocale())%>  </b>  <br><br>
<body class="yui-skin-sam" onLoad="init()">

<html:form action="/user.do?method=searchuserlistpage">

<div class="div">
<table border="0" width="50%">
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%></td>
			<td><html:text  property="firstName"/></td><td><%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%></td>
			<td>
			<html:text  property="lastName"/>
	     </td>
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
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>......</span>
			
			</td>
			<td width="15%"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('user.do?method=loadProjectCode');">
			<option value=""></option>
			<bean:define name="createUserForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			<span class="textboxlabel" id="loadingp" STYLE="font-size: smaller;Visibility:hidden";>
						load project code ....</span>
			</td>
		
		</tr>

		<tr>
			
			<td width="15%"><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%></td>
			<td width="40%">
			<span id="projectcodes">
			<html:select property="projectId" >
			<option value=""></option>
			<bean:define name="createUserForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
	        </span>
			</td>
	        
			</td>
		
		
			<td><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%></td>
			<td>
			<html:select  property="designationId">
			<option value=""></option>
			<bean:define name="createUserForm" property="designationList" id="designationList" />

            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>
	     </td>
		</tr>

		<tr>
		 <td><%=Constant.getResourceStringValue("hr.user.Username",user1.getLocale())%> </td>
		 <td><html:text  property="userName"/></td>
		 

        <td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%> </td>
		 <td><html:text  property="emailId"/></td>

</tr>
<tr>

		 <td><%=Constant.getResourceStringValue("hr.user.Status",user1.getLocale())%></td>
			<td>
			
			<select name="statuscri">
				<option value=""></option>
				<% if(!StringUtils.isNullOrEmpty(aform.getStatuscri()) && aform.getStatuscri().equals("A")){%>
				  <option value="A" selected="yes" ><%=Constant.getResourceStringValue("hr.user.active",user1.getLocale())%></option>
				  <%}else {%>
				<option value="A"><%=Constant.getResourceStringValue("hr.user.active",user1.getLocale())%></option>
				  <%}%>
				  <% if(!StringUtils.isNullOrEmpty(aform.getStatuscri()) && aform.getStatuscri().equals("I")){%>
				  <option value="I" selected="yes" ><%=Constant.getResourceStringValue("hr.user.suspended",user1.getLocale())%></option>
				  <%}else {%>
				<option value="I"><%=Constant.getResourceStringValue("hr.user.suspended",user1.getLocale())%></option>
				  <%}%>
 
			</select>
			
			
			</td>
		</tr>
		
<tr>
<td colspan="4">
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
</td>
<td></td>
</tr>
</table>
</div>
</html:form>





<br>
<a class="button" href="#" onClick="adduser()"><%=Constant.getResourceStringValue("hr.user.Create_User",user1.getLocale())%></a>&nbsp;&nbsp;<a class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
<br><br>
<b><%=Constant.getResourceStringValue("hr.user.User_List",user1.getLocale())%></b>

<%
	//if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
%>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
         	  elCell.innerHTML = "<a href='#' onClick=editUser('" + oRecord.getData("userId") + "')"+ ">" + sData + "</a>";
        };

	var profileUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='user.do?method=userprofile&url=user.do?method=userListwithPag&userId=" + oRecord.getData("userIdEnc") +"'"+ ">" + "profile" + "</a>";
        };

var refProfileUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='refferal.do?method=referralprofile&url=user.do?method=userListwithPag&userId=" + oRecord.getData("userIdEnc") +"'"+ ">" + "refferal profile" + "</a>";
        };

var submitter = function (callback, newValue)
    {
	   
        var record = this.getRecord();
	    var column = this.getColumn();
	//	alert(column);
        var oldValue = this.value;
		//alert(record.getData('address'));
        var datatable = this.getDataTable();

        // send the data to our update page to update the value in the database
        YAHOO.util.Connect.asyncRequest('POST', 'user.do',
        {
			
            success: function (o)
            {
				 callback(true, o.responseText); 
                
            },
            failure: function (o)
            {
                alert(o.statusText);
                callback();
            },
            scope: this
        },
        'action=cellEdit&column=' + column.key +
				     '&method=updatestatus' +
                     '&status=' + escape(newValue) +
                      '&userId=' + record.getData('userId') 
        );
    };
    // Column definitions
    var myColumnDefs = [
		
		{key:"userId", hidden:true},
		{key:"userIdEnc", hidden:true},

			{key:"firstName", label:"<%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%>",sortable:true,formatter:formatUrl, resizeable:true},
           <%=strcolumncontent%>
				{key:"profile", label:"profile",sortable:false, resizeable:true,formatter:profileUrl},
				{key:"refProfileUrl", label:"refferal profile",sortable:false, resizeable:true,formatter:refProfileUrl}

        ];

		
    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/emp/userlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&type=<%=Common.USER_TYPE_EMPLOYEE%>&statuscri=<%=aform.getStatuscri()%>&orgId=<%=aform.getOrgId()%>&departmentId=<%=aform.getDepartmentId()%>&projectId=<%=aform.getProjectId()%>&designationId=<%=aform.getDesignationId()%>&firstname=<%=aform.getFirstName()%>&userName=<%=aform.getUserName()%>&lastname=<%=aform.getLastName()%>&emailId=<%=aform.getEmailId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"userId"},
			{key:"userIdEnc"},
            {key:"firstName"},
            <%=strkeycontent%>,
			{key:"profile"},
			{key:"refProfileUrl"}
			
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=userId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"userId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
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
	 var highlightEditableCell = function(oArgs) {
            var elCell = oArgs.target;
            if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
                this.highlightCell(elCell);
            }
        };

	myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();
</script>

<%
//}
%>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>
