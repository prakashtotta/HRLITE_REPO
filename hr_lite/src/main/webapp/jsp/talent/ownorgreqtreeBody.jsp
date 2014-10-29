<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/resize.jsp" %>
<%@ include file="../common/jscontroltag.jsp" %>
<%@ include file="../common/greybox.jsp" %>



<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String orgId = (String)request.getParameter("orgId");
String requistionId = (String)request.getParameter("requistionId");
String hiringmgrid = (String)request.getParameter("hiringmgrid");
String deptId = (String)request.getParameter("deptId");
String method = (String)request.getParameter("method");
BOFactory.getTreeBO().setTreeview(request,String.valueOf(user1.getOrganization().getOrgId()));
String state = (String)request.getParameter("state");
 String isapplicationsubmitted = "false";
	
 if(state != null && state.equals(Common.APPLICATION_SUBMITTED)){
	 isapplicationsubmitted="true";
 }
%>

<head>




<link rel="stylesheet" type="text/css" href="jsp/ajaxtabs/ajaxtabs.css" />

<script type="text/javascript" src="jsp/ajaxtabs/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 800px;
	border: 1px solid #999;
	padding: 10px;
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
<style>
    #resize {
        border: 0px solid black;
        height: 100%;
        width: 400px;
        background-color: #fff;
    }
    #resize div.data {
        overflow: hidden;
        height: 100%;
        width: 100%;
    }
</style>

</head>
<script language="javascript">

function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);
}

function messageret(){
	window.location.reload();
	//history.go(0);

			}
function addRequisition(){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=createjobreq";
	parent.setPopTitle1("<%=Constant.getResourceStringValue("Requisition.createjobreq",user1.getLocale())%>");
	parent.showPopWin(url, 600, 300, editreq);
}
   
function editreq(retval){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+retval;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>', url, messageret);

}

function showmessage(returnval) { 
	window.location.reload();
	}

function getrequisitionbyorg(orgid){
		url="jobreq.do?method=jobreqtreelistorg&orgId="+orgid+"&hiringmgrid=0&deptId=0";
	location.replace(url)
}

function getrequisitionbyorgdept(orgid,deptid){
	url="jobreq.do?method=jobreqtreelistorg&orgId="+orgid+"&hiringmgrid=0&deptId="+deptid;
	location.replace(url)
}

function getallapplicants(reqid){
	url="jobreq.do?method=applicantliststatustree&state=0&requistionId="+reqid;
	location.replace(url)
}

function getallapplicantswithstatus(reqid,st){
	url="jobreq.do?method=applicantliststatustree&state="+st+"&requistionId="+reqid;
	location.replace(url)
}

function getapplicantdata(appid,uuid){
	url ="applicant.do?method=applicantDetailstreeajax&applicantId="+appid+"&secureid="+uuid;
	location.replace(url)
}
</script>

<script language="javascript">
var isapplicationsubmitted=<%=isapplicationsubmitted%>;



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

function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
		GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant",user1.getLocale())%>', url, messageret);
}

function sendbulkresumeforscreening(appIds){
	var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=sendbulkresumeforscreening&eventype=<%=Common.IN_SCREENING%>&applicantids="+appIds;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>', url, messageret);

}

function markdeletebulk(appIds){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=markfordeletionbulk&applicantids="+appIds;
	GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%>',url,400,600, messageret);

}

function compareapplicants(appIds){
	var url = "applicant.do?method=compareapplicants&applicantids="+appIds;
	var urlnew = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
	 window.open(urlnew, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1");

}
function addapplicant(){
	var url = "<%=request.getContextPath()%>/applicant.do?method=createPage&requistionId=<%=requistionId%>";
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%>', url, messageret);
}
</script>

	
<body class="yui-skin-sam">

<table>

<tr>
<td valign="top">
<div id="resize">
<div class="data">
	
	<b> 
<%=Constant.getResourceStringValue("Requisition.My.Organization.Requistions",user1.getLocale())%></b><br><br>
<div style="height: 850px; overflow: scroll;" id="dynamicTreeview" />


</div>
</div>
</td>

<td valign="top">

<%
if(orgId != null) {
%>
<br>
 <%=Constant.getResourceStringValue("Requisition.reqlist",user1.getLocale())%>  <br><br>

<a  href="#" onClick="addRequisition()"><%=Constant.getResourceStringValue("Requisition.createjobreq",user1.getLocale())%></a>
<%}else if(requistionId != null){

%>


<%
JobRequisition jb = (JobRequisition)request.getAttribute("jobrequisitiondata");

if(jb != null){
	String hiringmgrurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+jb.getHiringmgr().getUserId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+jb.hiringMgrValue+"</a>";
%>
<table bgcolor="gray">
<tr>
<td width="50%"><%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%> : <%=jb.getJobreqName()%> </td><td width="50%"><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%> : <%=jb.getJobTitle()%></td>
</tr>
<tr>
<td width="50%"><%=Constant.getResourceStringValue("Requisition.jobcode",user1.getLocale())%> : <%=jb.getJobreqcode()%> </td><td width="50%"><%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%> : <%=hiringmgrurl%></td>
<%=Constant.getResourceStringValue("Requisition.detailWillComeHere",user1.getLocale())%>
</tr>
</table>

<%}%>

<br><br>
 <%=Constant.getResourceStringValue("aquisition.applicant",user1.getLocale())%>  <br> 
<% if(method != null && !method.equals("applicantDetailstreeajax")){%>
 <table>
<tr>


	 <td>
 <div class="bd">
			<div id="container"></div>
			<button id="delete"><%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%></button>
			
		</div>
</td>

 <td>
 <div class="bd">
			<div id="container"></div>
			<button id="delete1"><%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%></button>
			
		</div>

</td>

 <td>
 <div class="bd">
			<div id="container"></div>
			<button id="compare"><%=Constant.getResourceStringValue("aquisition.applicant.compare",user1.getLocale())%></button>
			
		</div>

</td>


<td>
<a  href="#" onClick="javascript:addapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a>
</td>
</tr>
</table>

<%}%>
<br>

 <%}%>

 <br>
<% if(method != null && method.equals("applicantliststatustree")){
	if(state != null && !state.equals("0")){
	%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%> : <%=state%> </b>
<%}}%>
<div id="toalrecords"></div>
<div id="dynamicdata"></div>
<% if(method != null && method.equals("applicantDetailstreeajax")){%>
<jsp:include page="../talent/applicantDetailsBodytree.jsp" flush="true"/>
<%}%>

<% if(method != null && method.equals("applicantliststatustree")){%>


<script type="text/javascript">
YAHOO.example.DynamicData = function() {

	
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetailstreeajax&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "'"+ " >" + sData + "</a>";
        };

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };

var Dom = YAHOO.util.Dom,
				Event = YAHOO.util.Event,
				log = function (msg) {
					YAHOO.log(msg,'info','deleteRowsBy demo');
				};

YAHOO.widget.DataTable.prototype.deleteRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                sendbulkresumeforscreening(checknumber);
				}
				
				this.render();
			};


	YAHOO.widget.DataTable.prototype.deleteRowsBy1 = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			        
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("uuid") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                markdeletebulk(checknumber);
				}
				
				this.render();
			};

YAHOO.widget.DataTable.prototype.compareRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, tcount = 0 ,
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
				   tcount++;
					}
					
        
				 }
                if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg2",user1.getLocale())%>");
					return false;
				}else{
					 if(tcount < 2){
						alert("");
					return false;
					}
					if(tcount > 4){
						alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg6",user1.getLocale())%>");
					return false;
					}
                compareapplicants(checknumber);
				}
				
				this.render();
			};

	
	YAHOO.widget.DataTable.prototype.forAllRecords = function (fn,scope) {
		
		if (!Lang.isFunction(fn)) {return;}
		scope || (scope = this);
		for (var rs = this.getRecordSet(), l = rs.getLength(), i = 0; i < l; i++) {
			if (fn.call(scope, rs.getRecord(i), i) === false) {return;}
		}
	};



    // Column definitions

	var myColumnDefs = [
		{key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
           {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"email", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"phone", label:"<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>",sortable:true, resizeable:true},
		//{key:"countryName", sortable:true, resizeable:true},
		{key:"heighestQualification",label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
		//{key:"qualifications", sortable:true, resizeable:true},
		{key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"referrerName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", resizeable:true},
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];

	if(isapplicationsubmitted == false){

		 myColumnDefs = [
		  {key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
		  {key:"applicantId", hidden:true},
			 {key:"uuid", hidden:true},
           {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             {key:"email", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"phone", label:"<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>",sortable:true, resizeable:true},
		//{key:"countryName", sortable:true, resizeable:true},
		{key:"heighestQualification",label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
		//{key:"qualifications", sortable:true, resizeable:true},
		{key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"referrerName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", resizeable:true},
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];
	}
    





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/treeapplicantlistpage.jsp?requistionId=<%=requistionId%>&state=<%=state%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},
            {key:"resumeHeader"},
			{key:"email"},
		{key:"phone"},
	//	{key:"countryName"},
		{key:"heighestQualification"},
		//{key:"qualifications"},
		{key:"interviewState"},
		{key:"appliedDate"},
		{key:"jobTitle"},
		{key:"referrerName"},
		{key:"ownername"},
			{key:"uuid"},
			{key:"edit"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);

	myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.results",user1.getLocale())%> : ' + my_row_total;
   });
    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }


			myDataTable.subscribe('checkboxClickEvent', function(oArgs) {
	   
		var elCheckbox = oArgs.target;
		var newValue = elCheckbox.checked;
		var record = this.getRecord(elCheckbox);
		var column = this.getColumn(elCheckbox);
		record.setData(column.key,newValue);
		
		var allChecked = true;
		this.forAllRecords(function (r) {
			 
			if (!r.getData('Select')) {
				allChecked = false;
				return false;
				 
			}
		});
		Dom.get('SelectAll').checked = allChecked;
	});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					
					for (var i=0;i < l; i++) {
						if(rs.getRecord(i) != null){
						rs.getRecord(i).setData('Select',check);
						}
					}
					this.render();
				}
			});
			


			
			Event.on('delete','click', function() {
				myDataTable.deleteRowsBy(function (data) {
					return data.Select;
				});
			});

		Event.on('delete1','click', function() {
				myDataTable.deleteRowsBy1(function (data) {
					return data.Select;
				});
			});

		Event.on('compare','click', function() {
				myDataTable.compareRowsBy(function (data) {
					return data.Select;
				});
			});
			
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();
</script>



<%}%>

<% if(method != null && method.equals("jobreqtreelistorg")){%>

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

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},		
            {key:"jobreqName", label:"<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"organizationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%>", sortable:false, resizeable:true},
		   {key:"departmentValue", label:"<%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%>", sortable:false, resizeable:true},
		 {key:"projectcodeValue", label:"<%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%>", sortable:false, resizeable:true},
		 {key:"hiringMgrValue", label:"<%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%>", sortable:false, resizeable:true},
		 {key:"state", label:"<%=Constant.getResourceStringValue("Requisition.state",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"status", label:"<%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%>", sortable:true, resizeable:true}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/orgtreejobrequisitionlistpage.jsp?orgId=<%=orgId%>&hiringmgrid=<%=hiringmgrid%>&deptId=<%=deptId%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
            {key:"jobreqName"},
            {key:"organizationValue"},
		   {key:"departmentValue"},
			{key:"locationValue"},
		{key:"projectcodeValue"},
			{key:"jobtypeValue"},
			{key:"workshiftValue"},
			{key:"hiringMgrValue"},
			{key:"templateName"},
			{key:"state"},
			{key:"templateId"},
			{key:"status"}
			
			
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>



<%}%>

</td>
</tr>
</table>
<script>

(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var resize = new YAHOO.util.Resize('resize');
})();
</script>

</body>
</html>

<jscontrols-ajax:treeview-dyn tree="${requestScope.dynamicTreeview}"
							  source="dynamicTreeview"
							  baseUrl="${pageContext.request.contextPath}/ajaxreqtree.do"
							  imgBase="${pageContext.request.contextPath}/jsp/jscontroltag/treeview/"
							  width="100%"
							  height="850"
					 	 	  var="objDynamicTreeview"
					 	 	  checkboxesThreeState="false"
					 	 	  multiline="true"
					 	 	  defaultImg="page.gif" 
					 	 	  defaultImgOpen="folderopen.gif"
					 	 	  defaultImgClose="folder.gif" 
					 	 	  behaviourDrop="sibling" /> 