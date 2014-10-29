<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.util.EncryptDecrypt"%>
<%@ include file="../common/include.jsp" %>
<%@ include file="../common/resize.jsp" %>
<%@ include file="../common/jscontroltag.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>


<%
       /*//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);*/
%>

<%

User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);


String requistionId = (String)request.getParameter("requistionId");
String secureid = (String)request.getParameter("secureid");
System.out.println("requistionId : "+requistionId);
System.out.println("secureid : "+secureid);

String method = (String)request.getParameter("method");
if(requistionId != null){
//TreeBO.setTreeviewForRequistion(user1,request,new Long(requistionId).longValue());
BOFactory.getTreeBO().setTreeviewForAgency(user1,request,new Long(requistionId).longValue());
}

%>
<%
String ishavepermission = (String)request.getAttribute("NO_PERMISSION");
if(ishavepermission != null && ishavepermission.equals("yes")){

%>
<b> <font color="red">You don't have permission to see this requisition , Please contact requisition owner.</font></b>
<%}else {%>
<%
JobRequisition jb = (JobRequisition)request.getAttribute("JOB_REQUISTION");
String totalapplicant = (String)request.getAttribute("TOTAL_APPLICANT");
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



function showmessage(returnval) { 
	window.location.reload();
	}


function jobDetails(id){
	//alert("Not implemented yet, need a job details page for not looged in user");
	
	var url = "applicantjob.do?method=jobdetails&reqid="+id;
  window.open(url,  'JobRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=no');
}


function getallapplicants(reqid){
	url="agjob.do?method=recruiterapplicantlist&state=0&requistionId="+reqid;
	location.replace(url)
}

function getallapplicantsbyreq(reqid){
	url="agjob.do?method=requistionapplicantlist&state=0&requistionId="+reqid;
	location.replace(url)
}

function getallapplicantswithstatus(reqid,st){
	url="agjob.do?method=requistionapplicantlist&state="+st+"&requistionId="+reqid;
	location.replace(url)
}

function getapplicantdata(appid,uuid){
	url ="applicantoffer.do?method=requistionApplicantDetailstree&applicantId="+appid+"&secureid="+uuid+"&requistionId=" + "<%=requistionId%>";
	location.replace(url)
}
</script>

<script language="javascript">
var isapplicationsubmitted=<%=isapplicationsubmitted%>;

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


function showmessage(returnval) { 
	window.location.reload();
	}


	function opentjobrequistionpage(){
  window.open("jobreq.do?method=createjobreq", "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function openrequistion(id){
  window.open("agjob.do?method=jobdetailsforAgency&reqid="+id,"JobReq","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/agencyops.do?method=editapplicant&applicantId="+aid;
	//parent.setPopTitle1("Applicant");
	//parent.showPopWin(url, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>', url, messageret);
}

function addapplicant(){

	var url = "<%=request.getContextPath()%>/agencyops.do?method=createApplicantPage&requistionId=<%=requistionId%>&frm=ag";
	//parent.setPopTitle1("Applicant");
	//parent.showPopWin(url, 700, 600, null,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%>', url, messageret);
}


function sendbulkresumeforscreening(appIds){
	var url = "scheduleInterview.do?method=sendbulkresumeforscreening&eventype=In Screening&applicantids="+appIds;
	parent.showPopWin(url, 650, 600, showmessage,true);
}


function compareapplicants(appIds){
	var url = "applicantoffer.do?method=compareapplicants&applicantids="+appIds;
	var urlnew = "jsp/agency/temp.jsp?reurl="+encodeURIComponent(url);
	 window.open(urlnew, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1");

}

</script>

<style>
			.mover {
			background-color: #FF9999;
			color: #FFEEEE;
			}
			
			.special {
			color: #CC0000;
			}
			
			.tafelTree h3, .tafelTree p, .tafelTree ol {
			margin: 0;
			padding: 0;
			}
			
			.tafelTree p {
			padding-bottom: 1em;
			}
			
			.tafelTree h3 {
			color: #009900;
			}
		</style>	
<body class="yui-skin-sam">
<table>

<tr>
<td valign="top">
<div id="resize" class="div">
<div class="data">
	
	

<br>
<table valign="top" bgcolor="#f3f3f3">
<tr>
<td>
<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%> : 
<a href="#" onClick="javascript:jobDetails('<%=EncryptDecrypt.encrypt(String.valueOf(jb.getJobreqId()))%>');return false;"><%=jb.getJobTitle()%></a>
 
</td>
<td>
<%=Constant.getResourceStringValue("Requisition.TotalNoofopenings",user1.getLocale())%> : <%=jb.getNumberOfOpening()%> 
</td>
</tr>
<tr>
<td>
<%=Constant.getResourceStringValue("Requisition.TotalNoofremaining",user1.getLocale())%> : <%=jb.getNumberOfOpeningRemain()%>
</td>
<td>
<%=Constant.getResourceStringValue("Requisition.Total_applicants",user1.getLocale())%> : <%=totalapplicant%>

</td>
</tr>
</table>


<div style="height: 850px; overflow: scroll;" id="dynamicTreeview" />



</div>
</div>
</td>

<td valign="top">

 <%
	

 if(state != null && state.equals(Common.APPLICATION_SUBMITTED)){
	


	 %>
 <div class="div">
			<div id="container"></div>
			<!-- <button id="delete"><%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%></button>-->
			
			<a class="button" href="#" onClick="addapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a>
			
		</div>
<%}else if(method != null && method.equals("requistionapplicantlist")){%>


<a class="button" href="#" onClick="addapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a>

 <%}%>

<% if(method != null && method.equals("requistionapplicantlist")){
	if(state != null && !state.equals("0")){
	%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%> : <%=state%> </b>
<%}}%>

<div id="dynamicdata" class="div"></div>
<% if(method != null && method.equals("requistionApplicantDetailstree")){%>
<jsp:include page="../agency/applicantDetailsBodytree.jsp" flush="true"/>

<%}%>

<% if(method != null && method.equals("requistionapplicantlist")){%>


<script type="text/javascript">
YAHOO.example.DynamicData = function() {

	
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicantoffer.do?method=requistionApplicantDetailstree&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "&requistionId=" + "<%=requistionId%>" +"'"+ ">" + sData + "</a>";
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
					if(recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
                sendbulkresumeforscreening(checknumber);
				}
				
				this.render();
			};

YAHOO.widget.DataTable.prototype.compareRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, tcount = 0 ,
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
				   tcount++;
					}
					
        
				 }
                if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
					 if(tcount < 2){
						alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg2",user1.getLocale())%>");
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
		{key:"referrerName", hidden:true},
//		{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", resizeable:true},
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
			{key:"heighestQualification",label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
			{key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
			{key:"referrerName", hidden:true},
// no need to show owner name//	{key:"ownername", label:"<%=Constant.getResourceStringValue("aquisition.applicant.owner",user1.getLocale())%>", resizeable:true},
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
			
			
           
        ];
	}
    





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/agency/treeapplicantlistpage.jsp?requistionId=<%=requistionId%>&state=<%=state%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},
            {key:"resumeHeader"},
			{key:"email"},
			{key:"phone"},
			{key:"heighestQualification"},
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
    // Update totalRecords on the fly with value from server

	myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.results",user1.getLocale())%> : ' + my_row_total;
   });

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
			});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					for (var i=0;i < l; i++) {
						rs.getRecord(i).setData('Select',check);
					}
					this.render();
				}
			});
			
			
			Event.on('delete','click', function() {
				myDataTable.deleteRowsBy(function (data) {
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
							  baseUrl="${pageContext.request.contextPath}/ajaxagencytreeforreq.do"
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


<%
//Permission check successfull	
}%>

