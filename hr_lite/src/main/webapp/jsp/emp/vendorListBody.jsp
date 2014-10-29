
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.VENDOR_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.VENDOR_SCREEN);

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.user.Vendor_List",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<bean:define id="aform" name="createUserForm" type="com.form.CreateUserForm" />

<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>


<script language="javascript">

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsVendor";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}

function adduser(){
	var url = "<%=request.getContextPath()%>/user.do?method=vendorcreatepage";
	GB_showCenter('<%=Constant.getResourceStringValue("hr.user.user_Create_Vendor",user1.getLocale())%>',url,450,650, messageret);
}
function editUser(jb_id){
	//var url = "user.do?method=editvendor&userId="+jb_id;
	//parent.showPopWin(url, 700, 500, null,true);

	var url = "<%=request.getContextPath()%>/user.do?method=editvendor&userId="+jb_id;
	GB_showCenter('<%=Constant.getResourceStringValue("hr.user.user_Edit_Vendor",user1.getLocale())%>',url,450,650, messageret);
}

	function searchcri(){
       
	   document.createUserForm.action = "user.do?method=searchvendorlistpage";
	   document.createUserForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

	function messageret(){
	//window.location.reload();

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

function resetValue(){
	document.createUserForm.firstName.value="";
	document.createUserForm.lastName.value="";
	document.createUserForm.countryId.value="";
	document.createUserForm.stateId.value="";
	document.createUserForm.statuscri.value="";
	document.createUserForm.city.value="";
	document.createUserForm.emailId.value="";
	
}

function init(){
document.createUserForm.firstName.focus();

}
</script>


<body class="yui-skin-sam" onLoad="init()">

<html:form action="/user.do?method=searchvendorlistpage">
<div class="div">
<table border="0" width="100%">
		
		<tr>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Short_Name",user1.getLocale())%></td>
			<td><html:text  property="firstName"/></td>
			<td width="20%"><%=Constant.getResourceStringValue("hr.user.Full_Name",user1.getLocale())%></td>
			<td width="20%"><html:text  property="lastName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>
			<td width="40%">
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="createUserForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Location.LoadingState",user1.getLocale())%>......</span>
			
			</td>
			<td><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
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
			
			
			</td><td><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>
			<td>
			<html:text  property="city"/>
	     </td>
		</tr>
		<tr><td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
		<td><html:text  property="emailId"/></td>
		</tr>
		
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
<input type="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetValue()"  class="button"/>

</td>
<td></td>
</tr>
</table>
</div>
</html:form>

<br>
<!--  <a class="submodal-700-500" href="<%=request.getContextPath()%>/user.do?method=vendorcreatepage">add vendor</a>&nbsp;&nbsp;&nbsp;&nbsp;-->
<a href="#" onClick="adduser()" class ="button"><%=Constant.getResourceStringValue("hr.user.add_Vendor",user1.getLocale())%></a>&nbsp;&nbsp;
<a href="#" onClick="configuareColumns();return false;" class ="button"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>

<br><br>
<%=Constant.getResourceStringValue("hr.user.Vendor_List",user1.getLocale())%>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
         	  elCell.innerHTML = "<a href='#' onClick=editUser('" + oRecord.getData("userId") + "')"+ ">" + sData + "</a>";
        };
	var redemptionsummaryUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='agencyredemptionsummary.do?method=agencyredemptionsummary&backurl=user.do?method=vendorlist&agencyid=" + oRecord.getData("userId")  +"'"+ " >" + "Summary" + "</a>";
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
			{key:"firstName", label:"<%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%>",sortable:true, resizeable:true,formatter:formatUrl,width:150},
             <%=strcolumncontent%>
			{key:"localeValue", hidden:true},
			{key:"status"},
			{key:"redemptionsummary",label:"<%=Constant.getResourceStringValue("hr.user.Redemption_summary",user1.getLocale())%>", sortable:false,resizeable:true,formatter:redemptionsummaryUrl}
			
		        ];

		
    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/emp/userlistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&type=<%=Common.USER_TYPE_VENDOR%>&firstname=<%=aform.getFirstName()%>&lastname=<%=aform.getLastName()%>&statuscri=<%=aform.getStatuscri()%>&countryId=<%=aform.getCountryId()%>&stateId=<%=aform.getStateId()%>&city=<%=aform.getCity()%>&emailId=<%=aform.getEmailId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"userId"},
			{key:"userName"},
            {key:"firstName"},
			{key:"organization"},
            {key:"department"},
			{key:"projectcode"},
            {key:"roleValue"},
			{key:"localeValue"} ,


            <%=strkeycontent%>,
			
			{key:"redemptionsummary"},
			{key:"status"}
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
        myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
        myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);
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
