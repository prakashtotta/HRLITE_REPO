<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>

<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.MY_REQUISITION_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.MY_REQUISITION_SCREEN);

%>
<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<script language="javascript">

function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);
}

function showmessage(returnval) { 
	window.location.reload();
	}

function editJobReqTemplte(tmpl_id){
		var url = "<%=request.getContextPath()%>/jobtemplate.do?method=edittemplate&templateId="+tmpl_id;
	//parent.showPopWin(url, 800, 600, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.edittemp",user1.getLocale())%>', url, messageret);
}
	function opentjobrequistionpage(){
  window.open("jobreq.do?method=createjobreq", "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


   
function editreq(retval){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+retval;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.createjobreq",user1.getLocale())%>', url, messageret);
}
function messageret(){
	//window.location.reload();
	//history.go(0);

			}

function searchcri(){
       
	   document.jobRequisitionForm.action = "jobreq.do?method=searchMyReqlistpageRecruiter";
	   document.jobRequisitionForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsMyRequision";
    GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	var ids ="";
 
  for (var i = 0; i < document.jobRequisitionForm.orgIds.options.length; i++){
    if (document.jobRequisitionForm.orgIds.options[ i ].selected){
		ids = ids + document.jobRequisitionForm.orgIds.options[ i ].value + ",";
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
  document.jobRequisitionForm.jobreqName.value="";
  document.jobRequisitionForm.jobreqId.value="";

  document.jobRequisitionForm.orgIds.value="";
  document.jobRequisitionForm.departmentIds.value="";
  document.jobRequisitionForm.statecri.value="";
  document.jobRequisitionForm.statuscri.value="";
  document.jobRequisitionForm.jobtypeIds.value="";
  document.jobRequisitionForm.jobreqcode.value="";
  document.jobRequisitionForm.jobgradeIds.value="";
   document.jobRequisitionForm.budgetcodeIds.value="";
   
  
 }  

function init(){
document.jobRequisitionForm.jobreqName.focus();

}


function exporttoexcel(){

document.jobRequisitionForm.action = "jobreq.do?method=exporttoexcelAllSearchMyrequisition&type=<%=Common.RECRUITER_SEARCH_ROLE%>";
document.jobRequisitionForm.submit();

}

function userDetailsOrGroup(id,isgroup){
	var url = "";
	if(isgroup == 'Y'){
		url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id;
	}else{
			url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
		}
	
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}
</script>
<body class="yui-skin-sam" onLoad="init()" >


<html:form action="/jobreq.do?method=searchMyReqlistpageRecruiter">

<table border="0" width="1000" class="div">
		
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%> </td>
			<td><html:text  styleClass="multipleselect" property="jobreqName" maxlength="500"/></td>
			<td><%=Constant.getResourceStringValue("Requisition.number",user1.getLocale())%></td>
			<td>
			<% if(aform.getJobreqId() == 0){%>
			<html:text  styleClass="multipleselect" property="jobreqId" value=""/>
			<%}else{%>
			<html:text  styleClass="multipleselect" property="jobreqId"/>
			<%}%>
	     </td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> </td>
			<td>
			<html:select property="orgIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrieveURL('jobreq.do?method=deptlistmultiple');">
			
			<bean:define name="jobRequisitionForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>........</span>
			
			</td>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> </td>
			<td>
			<span id="departments">
			<html:select styleClass="multipleselect" multiple="true" size="3" property="departmentIds" >
			
			<bean:define name="jobRequisitionForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</td>
		
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.state",user1.getLocale())%> </td>
			<td>
			
			<html:select name="jobRequisitionForm"  styleClass="multipleselect" multiple="true" size="3" property="states">
		
			<bean:define name="jobRequisitionForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="key"  labelProperty="value"/>
			</html:select>
 

			
			
			</td><td><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionForm"  styleClass="multipleselect" multiple="true" size="3" property="statuses">
		
			<bean:define name="jobRequisitionForm" property="statusList" id="statusList" />

            <html:options collection="statusList" property="key"  labelProperty="value"/>
			</html:select>
	     </td>
		</tr>

<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionForm" styleClass="multipleselect" multiple="true" size="3"  property="jobtypeIds">
			
			<bean:define name="jobRequisitionForm" property="jobtypeList" id="jobtypeList" />

            <html:options collection="jobtypeList" property="jobTypeId"  labelProperty="jobTypeName"/>
			</html:select>
			</td>
			<td><%=Constant.getResourceStringValue("Requisition.jobgrade",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionForm" styleClass="multipleselect" multiple="true" size="3" property="jobgradeIds">
			
			<bean:define name="jobRequisitionForm" property="jobgradeList" id="jobgradeList" />

            <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			</html:select>
			</td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.reqcode",user1.getLocale())%></td>
			<td><html:text styleClass="multipleselect" property="jobreqcode" maxlength="500"/></td>
			<td><%=Constant.getResourceStringValue("Requisition.budgetcode",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionForm" styleClass="multipleselect" multiple="true" size="3" property="budgetcodeIds">
			
			<bean:define name="jobRequisitionForm" property="budgetcodeList" id="budgetcodeList" />

            <html:options collection="budgetcodeList" property="budgetId"  labelProperty="budgetCode"/>
			</html:select>
	     </td>
		</tr>
		
<tr>
<td>
<input type="submit" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
<input type="button" name="export to excel" value="<%=Constant.getResourceStringValue("hr.button.exporttoexcel",user1.getLocale())%>" onClick="exporttoexcel()" class="button">
</td>
<td></td>
</tr>
</table>

</html:form>

<%
String exporttoexcel = (String)request.getAttribute("exporttoexcel");
%>

<%if(exporttoexcel != null && exporttoexcel.equals("yes")){
%>
<br>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.export.job.started",user1.getLocale())%> <a href="bulkuploadtask.do?method=mybulktasklist"><font color="white"><%=Constant.getResourceStringValue("aquisition.process.job.page",user1.getLocale())%></a></font></td>
			<td></td>
		</tr>
		
	</table>
</div>
<br>
<%}%>
<table>
<tr>
<td><br><a class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a><br><br>
</td>
<td>
<div id="toalrecords"></div>
</td>
</tr>
</table>
<div id="dynamicdata" class="div"></div>
<table border="0" width="100%">



<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var ndata = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>" height="20"  width="19" align="middle"/>';
		var saveasdata =  '<img src="jsp/images/saveas.gif" border="0" alt="save as job requistion" title="<%=Constant.getResourceStringValue("Requisition.saveasjobreq",user1.getLocale())%>" height="20"  width="19" align="middle"/>';
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + sData + "</a>";
		var saveasqd = "<a href='#' onClick=saveasJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + saveasdata + "</a>";

		 elCell.innerHTML = editreqd;

        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReqTemplte('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

		var summaryUrl = function(elCell, oRecord, oColumn, sData) {
			if(oRecord.getData("statusUiValue")=="Active > Open"){
          elCell.innerHTML = "<a href='jobreq.do?method=requistionapplicantlist&backurl=jobreq.do?method=jobreqList&state=0&requistionId=" + oRecord.getData("jobreqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ " >" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",user1.getLocale())%>" + "</a>";
			}
        };


		
	var formatOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("currentOwnerName");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("currentOwnerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else {
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("currentOwnerName");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("currentOwnerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        };

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},	
		{key:"uuid", hidden:true},	
		{key:"currentOwnerId", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"jobreqName", label:"<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            <%=strcolumncontent%>
		{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary",user1.getLocale())%>", sortable:false, resizeable:true,formatter:summaryUrl}
		
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jobreq.do?method=jobreqListpageRecruiter&searchpagedisplay=<%=searchpagedisplay%>&jobreqname=<%=aform.getSerachCriteria().getJobreqName()%>&jobreqid=<%=aform.getSerachCriteria().getJobreqId()%>&orgId=<%=aform.getSerachCriteria().getOrgId()%>&departmentId=<%=aform.getSerachCriteria().getDepartmentIds()%>&jobgradeId=<%=aform.getSerachCriteria().getJobgradeId()%>&jobtypeId=<%=aform.getSerachCriteria().getJobtypeId()%>&statuscri=<%=aform.getSerachCriteria().getStatus()%>&statecri=<%=aform.getSerachCriteria().getState()%>&jobreqcode=<%=aform.getSerachCriteria().getJobreqcode()%>&uuid=<%=aform.getUuid()%>&budgetcodeid=<%=aform.getSerachCriteria().getBudgetcodeId()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
            {key:"jobreqName"},
			{key:"uuid"},
			{key:"currentOwnerId"},
			{key:"isGroup"},
			<%=strkeycontent%>,
			{key:"summary"}
			
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
 // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
	myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.result.count",user1.getLocale())%> : ' + my_row_total;
	 });
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


</table>
</body>
