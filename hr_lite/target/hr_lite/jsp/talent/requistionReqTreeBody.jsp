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


String requistionId = (String)request.getParameter("requistionId");
String secureid = (String)request.getParameter("secureid");


String method = (String)request.getParameter("method");
if(method != null && method.equals("requistionApplicantDetailstree")){
	BOFactory.getTreeBO().setTreeviewForRequistion(user1,request,new Long(requistionId).longValue());
}else{
if(requistionId != null){
BOFactory.getTreeBO().setTreeviewForRequistion(user1,request,new Long(requistionId).longValue(),secureid);
}
}


%>
<%
String ishavepermission = (String)request.getAttribute("NO_PERMISSION");
if(ishavepermission != null && ishavepermission.equals("yes")){

%>
<b> <font color="red">You don't have permission to see this requisition , Please contract requisition owner.</font></b>
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

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsApplicantSummary";
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
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


function getallapplicants(reqid){
	
	url="jobreq.do?method=recruiterapplicantlist&state=0&requistionId="+reqid;
	location.replace(url)
}

function getallapplicantsbyreq(reqid,uuid){
	
	url="jobreq.do?method=requistionapplicantlist&state=0&requistionId="+reqid+"&secureid="+uuid;;
	location.replace(url)
}

function getallapplicantswithstatus(reqid,uuid,st){
	
	url="jobreq.do?method=requistionapplicantlist&backurl=jobreq.do?method=jobreqList&state="+st+"&requistionId="+reqid+"&secureid="+uuid;
	location.replace(url)
}

function getapplicantdata(appid,uuid){
	
	url ="applicant.do?method=requistionApplicantDetailstree&applicantId="+appid+"&secureid="+uuid+"&requistionId=" + "<%=requistionId%>";
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
	
  window.open("jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+id,"JobReq","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function editapplicant(aid){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicant&applicantId="+aid;
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>', url, messageret);
}

function addapplicant(){
	
	var url = "<%=request.getContextPath()%>/applicant.do?method=createPage&requistionId=<%=requistionId%>";
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%>', url, messageret);
}


function sendbulkresumeforscreening(appIds){
	
	var url = "<%=request.getContextPath()%>/scheduleInterview.do?method=sendbulkresumeforscreening&eventype=<%=Common.IN_SCREENING%>&requistionId=<%=requistionId%>&applicantids="+appIds;
	//GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>', url, messageret);
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>',url,500,700, messageret);
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

</script>


<body class="yui-skin-sam">
<table>

<tr>
<td valign="top">
<div id="resize">
<div class="data">
<%
String backurl = (String)request.getAttribute("backurl");
	
if(backurl != null){%>
<a href="<%=backurl%>"> << back</a>
</a>
<%}%>

<br>
<table valign="top" bgcolor="#f3f3f3">
<tr>
<td>
<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%> : <a href="#" onClick="openrequistion('<%=EncryptDecrypt.encrypt(String.valueOf(jb.getJobreqId()))%>')"><%=jb.getJobreqName()%></a>
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
<% if(method != null && !method.equals("requistionApplicantDetailstree")){%>

<br>
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
<br>
<%}%>





 <br>
<% if(method != null && method.equals("requistionapplicantlist")){
	if(state != null && !state.equals("0")){
	%>
<b><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%> : <%=state%> </b>
<%}}%>
<table>
<tr>
<td>
<div id="toalrecords"></div>
</td>
<td width="20"></td>
<% if(method != null && method.equals("requistionapplicantlist")){%>
<td>
<a href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.configuare.columns",user1.getLocale())%></a>
</td>
<%}%>
</tr>
</table>
<div id="dynamicdata"></div>
<% if(method != null && method.equals("requistionApplicantDetailstree")){%>
<jsp:include page="../talent/applicantDetailsBodytree.jsp" flush="true"/>

<%}%>




<% if(method != null && method.equals("requistionapplicantlist")){%>

<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.APPLICANT_SUMMARY_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.APPLICANT_SUMMARY_SCREEN);

%>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {

	
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		
		if(oRecord.getData("filterError") > 0){
			elCell.innerHTML = "<a href='applicant.do?method=requistionApplicantDetailstree&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "&requistionId=" + "<%=requistionId%>" +"'"+ "  >"+" <img src='jsp/images/red_start_icon.jpg' border='0' height='10' width='10'/>" + sData + "</a>";
		}else{
          elCell.innerHTML = "<a href='applicant.do?method=requistionApplicantDetailstree&applicantId=" + oRecord.getData("applicantId") +"&secureid=" + oRecord.getData("uuid") + "&requistionId=" + "<%=requistionId%>" +"'"+ "  >" + sData + "</a>";
		}
        };


var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };

var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
		
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
		elCell.innerHTML = editreqd;
        };

	var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("isofferownerGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("offerownerId")+ "'"+","+"'"+ oRecord.getData("isofferownerGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };

	var formatUrlForRecruiter = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("recruitergroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("recruitergroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("recruiterId")+ "'"+","+"'"+ oRecord.getData("recruitergroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };



var formatApplicantOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownernamegroup");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownergroupId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else if(oRecord.getData("isGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownername");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        
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
		{key:"reqId", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
		    <%=strcolumncontent%>
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];

	if(isapplicationsubmitted == false){

		 myColumnDefs = [
			   {key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
		  {key:"applicantId", hidden:true},
			{key:"uuid", hidden:true},
		{key:"reqId", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
		    <%=strcolumncontent%>
		//{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
			
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];
	}
    





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/treeapplicantlistpage.jsp?requistionId=<%=requistionId%>&secureid=<%=secureid%>&state=<%=state%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
           <%=strkeycontent%>
			
			
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
							  baseUrl="${pageContext.request.contextPath}/ajaxrecruitertreeforreq.do"
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
<script language="javascript">
    var $js = jQuery.noConflict();
	</script>
<script>
$=$js;
</script>
