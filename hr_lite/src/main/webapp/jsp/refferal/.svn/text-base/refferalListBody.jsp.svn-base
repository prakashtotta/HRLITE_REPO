<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>


  <%
 
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.REFERRAL_SCREEN);

 if(!StringUtils.isNullOrEmpty(strcolumncontent)){
	 strcolumncontent = ","+strcolumncontent;
	 strcolumncontent= strcolumncontent.substring(0,strcolumncontent.length()-1);
 }
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.REFERRAL_SCREEN);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


   <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.user.Vendor_List",user1.getLocale())%></title>

<!--there is no custom header content for this example-->

</head>
<bean:define id="aform" name="refferalForm" type="com.form.RefferalForm" />
<br>

<b><%=Constant.getResourceStringValue("hr.Redemption.Refferal_Employee",user1.getLocale())%>  </b>
<script language="javascript">

function editUser(jb_id){
	//alert(jb_id);
	var url = "<%=request.getContextPath()%>/reflogin.do?method=editRefferalemployee&userId="+jb_id;
	GB_showCenter('<%=Constant.getResourceStringValue("refferal.common.editrefferauser",user1.getLocale())%>',url,500,750, messageret);	
}
function messageret(){
	window.location.reload();

			}
	function searchcri(){
       
	   document.refferalForm.action = "reflogin.do?method=searchrefferallistpage&departmentId="+document.refferalForm.departmentId.value;
	   document.refferalForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
}
	function resetValue(){
		document.refferalForm.employeecode.value="";
		document.refferalForm.employeename.value="";
		document.refferalForm.employeeemail.value="";
		document.refferalForm.status.value="";
		document.refferalForm.orgId.value="";
		document.refferalForm.departmentId.value="";
		document.refferalForm.projectId.value="";
		

	}
	function retrieveURLOrg(url) {
		   //convert the url to a string
		    document.getElementById("loadingd").style.visibility = "visible";

			
			url=url+"&orgId="+document.refferalForm.orgId.value;
			
		  	//Do the AJAX call
		  	if (window.XMLHttpRequest) { 
			    req = new XMLHttpRequest();    	// Non-IE browsers
		    	req.onreadystatechange = processStateChangeOrg;

			    try {
		    		req.open("GET", url, true);
					
			    } catch (e) {}
			    req.send(null);
		  	} else if (window.ActiveXObject) {
		       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
		    	if (req) {
			    	req.onreadystatechange=processStateChangeOrg;
			        req.open("GET", url, true);
				    req.send();
					
		    	}
		  	}
		}
	function processStateChangeOrg() {
		if (req.readyState == 4) { // Complete
		    if (req.status == 200) { // OK response
		       	//document.getElementById("states").innerHTML = req.responseText;
	    	    //Split the text response into Span elements
	    		spanElements = splitTextIntoSpanOrg(req.responseText);

	    		//Use these span elements to update the page
			    replaceExistingWithNewHtmlOrg(spanElements);
			    //onOtherStateSel();
	    	} 
	    	document.getElementById("loadingd").style.visibility = "hidden";	
	  	}
	}





	function replaceExistingWithNewHtmlOrg(newTextElements){
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
	function splitTextIntoSpanOrg(textToSplit){
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

			
			url=url+"&departmentId="+document.refferalForm.departmentId.value;
			
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
	    		spanElements = splitTextIntoSpanOrg(req.responseText);

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
	function configuareColumns(){
		var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsReferral";
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
	}
	function init(){
		document.refferalForm.employeecode.focus();
		}
</script>


<body class="yui-skin-sam" onLoad="init()">

<html:form action="/reflogin.do?method=searchvendorlistpage">
<div  class="div">
<table border="0" width="1000">
		
		<tr>
			<td width="150"><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%></td>
			<td width="400"><html:text  property="employeecode"/></td>
			<td><%=Constant.getResourceStringValue("refferal.common.Employee_Name",user1.getLocale())%> </td>
			<td><html:text  property="employeename"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("refferal.common.Employee_Email",user1.getLocale())%></td>
			<td><html:text  property="employeeemail"/></td>
	            <td><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%> :</td>
				<td>
				<html:select  property="status">
				<option value=""></option>
				<% if(!StringUtils.isNullOrEmpty(aform.getStatus()) && aform.getStatus().equals("A")){%>
 				<option value="A" selected="yes" ><%=Constant.getResourceStringValue("hr.user.active",user1.getLocale())%></option>
  				<%}else {%>
				<option value="A"><%=Constant.getResourceStringValue("hr.user.active",user1.getLocale())%></option>
  				<%}%>
  				<% if(!StringUtils.isNullOrEmpty(aform.getStatus()) && aform.getStatus().equals("I")){%>
  				<option value="I" selected="yes" <%=Constant.getResourceStringValue("hr.user.inactive",user1.getLocale())%>></option>
  				<%}else {%>
				<option value="I"><%=Constant.getResourceStringValue("hr.user.inactive",user1.getLocale())%></option>
  				<%}%>
				</html:select>
				</td>
		
		</tr>
		<tr>
				<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>:</td>
				<td>
			
					<html:select property="orgId" onchange="retrieveURLOrg('lov.do?method=loadDeptlistWithProjectcode');">
					
					<option value=""></option>
					<bean:define name="refferalForm" property="organizationList" id="organizationList" />
            		<html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			
					</html:select>&nbsp;
					<span class="textboxlabel" id="loadingd" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%> ......</br></span>
						
						
				</td>
				<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>:</td>
				<td>
				<span id="departments">
				
				<html:select property="departmentId" onchange="retrieveProjcodes('lov.do?method=loadProjectCode');">
				<option value=""></option>
				<bean:define name="refferalForm" property="departmentList" id="departmentList" />
            	<html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			
				</html:select>
				<span class="textboxlabel" id="loadingp" STYLE="font-size: smaller;Visibility:hidden";>
					<br>	<%=Constant.getResourceStringValue("admin.ProjectCode.Loadpcode",user1.getLocale())%>....</br></span>

			  
			</td>
		</tr>
		<tr>
				<td><%=Constant.getResourceStringValue("admin.ProjectCode.PorjectCodes",user1.getLocale())%>:</td>
				<td>
					<span id="projectcodes">
					<html:select property="projectId">
					<option value=""></option>
					<bean:define name="refferalForm" property="projectcodeList" id="projectcodeList" />
		
		            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
					</html:select>
					
					</span>
				</td>
		</tr>


		
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
<input type="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetValue()"  class="button"/>

</td>
<td></td>
</tr>
</table>

</html:form>
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>

<br>
<%=Constant.getResourceStringValue("refferal.common.refferal_list",user1.getLocale())%>&nbsp;&nbsp;<a class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
         	  elCell.innerHTML = "<a href='#' onClick=editUser('" + oRecord.getData("employeeReferalId") + "')"+ ">" + sData + "</a>";
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
            YAHOO.util.Connect.asyncRequest('POST', 'reflogin.do',
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
                          '&employeeReferalId=' + record.getData('employeeReferalId') 
            );
        };

    // Column definitions
    var myColumnDefs = [


		{key:"employeeReferalId", hidden:true, sortable:true},
			{key:"employeecode", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%>",sortable:true, resizeable:true,formatter:formatUrl,width:150}
			<%=strcolumncontent%>
			

			
        ];

		
    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/refferalempListPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&employeecode=<%=aform.getEmployeecode()%>&employeename=<%=aform.getEmployeename()%>&employeeemail=<%=aform.getEmployeeemail()%>&orgId=<%=aform.getOrgId()%>&departmentId=<%=aform.getDepartmentId()%>&projectId=<%=aform.getProjectId()%>&status=<%=aform.getStatus()%>");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"employeeReferalId"},
			{key:"employeecode"},
			<%=strkeycontent%>
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "&sort=employeeReferalId&dir=asc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"employeeReferalId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
        myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
        myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);

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



<!--END SOURCE CODE FOR EXAMPLE =============================== -->




</body>
</html>
