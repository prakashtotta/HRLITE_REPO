<%@ include file="../common/include.jsp" %>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>


<%@ include file="../common/greybox.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<bean:define id="aform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
 
%>
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.REQUISION_TEMPLATE_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.REQUISION_TEMPLATE_SCREEN);

%>
<script language="javascript">

function editTemplate(tmpl_id){
	
   var url = "<%=request.getContextPath()%>/jobtemplate.do?method=edittemplate&templateId="+tmpl_id;
	//parent.showPopWin(url, 800, 600, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.edittemp",user1.getLocale())%>', url, messageret);
	
}

function messageret(){
	//window.location.reload();
	//history.go(0);

			}

function showmessage(returnval) { 
	//window.location.reload();
	}




$(document).ready(function() {$('#tmplsearch').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
   test();
	 }); 
}); 


function test() { 
$.ajax({
	type: 'POST',
  url: "jobtemplate.do?method=searchtemplatelistpage&csrfcode="+document.jobRequisitionTemplateForm["csrfcode"].value,
  success: function(data){
  $('#tmpldata').html(data);
	completeajx();
  }
});
} 

 
function completeajx(){
	  $.unblockUI();
}

	function searchcri(){
       
	   document.jobRequisitionTemplateForm.action = "jobtemplate.do?method=searchtemplatelistpage&csrfcode="+document.jobRequisitionTemplateForm["csrfcode"].value;
	   document.jobRequisitionTemplateForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

    var ids ="";
    
    for (var i = 0; i < document.jobRequisitionTemplateForm.orgIds.options.length; i++){
	      if (document.jobRequisitionTemplateForm.orgIds.options[ i ].selected){
	  		ids = ids + document.jobRequisitionTemplateForm.orgIds.options[ i ].value + ",";
	  	}
  	}
	
	url=url+"&orgId="+ids;
	
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

  function resetData(){
  document.jobRequisitionTemplateForm.templateName.value="";
  document.jobRequisitionTemplateForm.jobtypeId.value="";
  document.jobRequisitionTemplateForm.parentOrgId.value="";
  document.jobRequisitionTemplateForm.departmentId.value="";
  document.jobRequisitionTemplateForm.statuscri.value="";
  document.jobRequisitionTemplateForm.jobgradeId.value="";
   
    
 }  

function addTemplate(){
	var url = "<%=request.getContextPath()%>/jobtemplate.do?method=createtemplate";
	//parent.showPopWin(url, 800, 600, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.createtemp",user1.getLocale())%>', url, messageret);
}
function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsRequisionTemplate";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}
function init(){
document.jobRequisitionTemplateForm.templateName.focus();

}

</script>
<body class="yui-skin-sam" onLoad="init()">
 <b><%=Constant.getResourceStringValue("Requisition.searchreq",user1.getLocale())%> </b> 
<br><br>
<html:form action="/jobtemplate.do?method=searchtemplatelistpage">
<div class="div">
<table border="0" width="900">
		
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%></td>
			<td><html:text  styleClass="multipleselect" property="templateName" maxlength="500"/></td>
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionTemplateForm"  property="jobtypeIds" styleClass="multipleselect" multiple="true" size="3">
			<bean:define name="jobRequisitionTemplateForm" property="jobtypeList" id="jobtypeList" />

            <html:options collection="jobtypeList" property="jobTypeId"  labelProperty="jobTypeName"/>
			</html:select>
	     </td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
			<html:select property="orgIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrieveURL('jobreq.do?method=deptlistmultiple');">
			<bean:define name="jobRequisitionTemplateForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>......</span>
			
			</td>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="departmentIds" styleClass="multipleselect" multiple="true" size="3">
			<bean:define name="jobRequisitionTemplateForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</td>
		
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionTemplateForm"  styleClass="multipleselect" multiple="true" size="3" property="statuses">
		
			<bean:define name="jobRequisitionTemplateForm" property="statusList" id="statusList" />

            <html:options collection="statusList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
			
			<td><%=Constant.getResourceStringValue("Requisition.jobgrade",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionTemplateForm"  property="jobgradeIds" styleClass="multipleselect" multiple="true" size="3">
			
			<bean:define name="jobRequisitionTemplateForm" property="jobgradeList" id="jobgradeList" />

            <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			</html:select>
	     </td>
		</tr>
		
<tr>

<td>
<input type="button" id="tmplsearch" name="search" class="button" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()">


<input type="button" name="reset" class="button" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()">
</td>
<td></td>
</tr>

</table>
</div>
</html:form>

<br>

 <b><%=Constant.getResourceStringValue("Requisition.reqtemplist",user1.getLocale())%></b> <br>
<br>
<a class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
<br><br>
	<span id="tmpldata">
<div id="dynamicdata" class="div"></div>




<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var ndata = sData + '<img src="jsp/images/open_button.gif" border="0" alt="edit template" title="<%=Constant.getResourceStringValue("Requisition.edittemp",user1.getLocale())%>" height="20"  width="19"/>';
         elCell.innerHTML = "<a href='#' onClick=editTemplate('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";

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
    YAHOO.util.Connect.asyncRequest('POST', 'jobtemplate.do',
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
                 '&templateId=' + record.getData('templateId') 
    );
};

    // Column definitions
    var myColumnDefs = [
			{key:"templateId", hidden:true},
            {key:"templateName", label:"<%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            <%=strcolumncontent%>
            {key:"status", editor: new YAHOO.widget.RadioCellEditor({radioOptions:["Active","Closed"],disableBtns:true,asyncSubmitter:submitter}), resizeable:true}			
            
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
   
    var myDataSource = new YAHOO.util.DataSource("jobtemplate.do?method=jobtemplatelistpage&searchpagedisplay=<%=searchpagedisplay%>&templatename=<%=aform.getSerachCriteria().getTemplateName()%>&templateid=<%=aform.getSerachCriteria().getTemplateId()%>&orgId=<%=aform.getSerachCriteria().getOrgId()%>&departmentId=<%=aform.getSerachCriteria().getDepartmentIds()%>&jobgradeId=<%=aform.getSerachCriteria().getJobgradeId()%>&jobtypeId=<%=aform.getSerachCriteria().getJobtypeId()%>&statuscri=<%=aform.getSerachCriteria().getStatus()%>&ddd="+(new Date).getTime()+"&");


    
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"templateId"},
            {key:"templateName"},
            <%=strkeycontent%>,
            {key:"status"}
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=templateId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"templateId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
    };
    myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>


</span>
</body>
