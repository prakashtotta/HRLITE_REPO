<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%
User user = (User)request.getSession().getAttribute(Common.USER_DATA);
Map widgetmap = BOFactory.getUserBO().getWidgetsMap(user.getUserId());
%>
<%@ include file="../common/collapsable.jsp" %>
<script type="text/javascript">
animatedcollapse.addDiv('assinedreq', 'fade=0,speed=400,group=pets1')
animatedcollapse.addDiv('applicantofferprocess', 'fade=0,speed=400,group=pets2,hide=1')
animatedcollapse.addDiv('applicantjoiningprocess', 'fade=0,speed=400,group=pets3,hide=1')

animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()

</script>

<link rel="stylesheet" type="text/css" href="jsp/style.css" />

<script language="javascript">

function targetSlipingdate() { 
 document.getElementById("targetslipingloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/targetSlipingDateRecruiter.jsp",
  success: function(data){
  $('#targetdateslip').html(data);
	document.getElementById("targetslipingloading").style.visibility = "hidden";	
  }
});
} 

function filledActiveRequistion() { 
 document.getElementById("filledActiveRequistionloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/filledActiveRequistionRecruiter.jsp",
  success: function(data){
  $('#filledActiveRequistion').html(data);
	document.getElementById("filledActiveRequistionloading").style.visibility = "hidden";	
  }
});
} 


function onboardapplicants() { 
 document.getElementById("onboardapplicantsloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/onBoradJoinedRecruiter.jsp",
  success: function(data){
  $('#onboardapplicants').html(data);
	document.getElementById("onboardapplicantsloading").style.visibility = "hidden";	
  }
});
} 

function offerdeclinedapplicants() { 
 document.getElementById("offerdeclinedapplicantsloading").style.visibility = "visible";

$.ajax({
	type: 'GET',
  url: "jsp/home/offerDeclinedJoinedRecruiter.jsp",
  success: function(data){
  $('#offerdeclinedapplicants').html(data);
	document.getElementById("offerdeclinedapplicantsloading").style.visibility = "hidden";	
  }
});
} 

function todaysinterviews() { 
 document.getElementById("todaysinterviewsloading").style.visibility = "visible";


 var m_names = new Array("January", "February", "March", 
        "April",
		"May",
		"June", 
		"July", 
		"August", 
		"September", 
        "October",
		"November", 
		"December");

var d = new Date();
var curr_date = d.getDate();
var curr_month = d.getMonth();
var curr_year = d.getFullYear();
var newdate = m_names[curr_month] + " "+curr_date
+ "," +" "+ curr_year;

$.ajax({
	type: 'GET',
  url: "jsp/home/todaysinterviewsRecruiter.jsp?date="+newdate,
  success: function(data){
  $('#todaysinterviews').html(data);
	document.getElementById("todaysinterviewsloading").style.visibility = "hidden";	
  }
});
} 



function configuredashboard(){
	var url = "<%=request.getContextPath()%>/dashboard.do?method=configureDashBoard";
GB_showCenter('<%=Constant.getResourceStringValue("hr.user.configure.dashboard",user1.getLocale())%>',url,400,500, messageretrefresh);
}
function messageretrefresh(){
	window.location.reload();
}
function configuareColumns(screenname){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsGeneric&screenname="+screenname;
GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}
function editJobReq(jb_id){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+jb_id;
   // parent.setPopTitle1("Job requisition");
	//parent.showPopWin(url, 850, 650, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>', url, messageret);
}

function showmessage(returnval) { 
	window.location.reload();
	}

function editJobReqTemplte(tmpl_id){
		var url = "<%=request.getContextPath()%>/jobtemplate.do?method=edittemplate&templateId="+tmpl_id;
	//parent.showPopWin(url, 800, 600, showmessage,true);
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.edittemp",user.getLocale())%>', url, messageret);
}

function addRequisition(){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=createjobreq";
	parent.setPopTitle1("<%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%>");
	parent.showPopWin(url, 600, 300, editreq);
}
   
function editreq(retval){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=editjobreq&jobreqId="+retval;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	GB_showFullScreen('<%=Constant.getResourceStringValue("Requisition.createjobreq",user.getLocale())%>', url, messageret);
}

function userDetails(id){
	var url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
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



function requistionDetails(id,juuid){
	var url = "<%=request.getContextPath()%>/jobreq.do?method=jobreqdetails&approverstatus=yes&offerapplicant=yes&jobreqId="+id+"&secureid="+juuid;;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=800');
}


function messageret(){
	//window.location.reload();
	//history.go(0);
	location.href="dashboard.do?method=dashboardlist";

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



<br>

&nbsp;&nbsp;<a  href="jobreq.do?method=myjobreqListRecruiter" ><%=Constant.getResourceStringValue("Requisition.my.requistions",user1.getLocale())%></a>
&nbsp;&nbsp;<a  href="#" onClick="configuredashboard()"><%=Constant.getResourceStringValue("hr.user.configure.dashboard",user1.getLocale())%></a>

<br><br>

<div id="main_container">
	
	
    
   <div id="main_content"> 
<table>
<tr>
<td valign="top" width="25%">

  
    
   <div class="left_content">

		<% 
		   String value = (String)widgetmap.get("FILLED_ACTIVE_REQ");
		   String isFilledActiveReqVisible="false";
		   if(value != null && value.equals("A")){
			   isFilledActiveReqVisible="true";
		   }
		   %>

		   <% if(isFilledActiveReqVisible.equals("true")){%>
    <div class="title_box"><%=Constant.getResourceStringValue("filled.active.requisitions",user1.getLocale())%></div>
    
        <div class="border_box">
		<span id="filledActiveRequistion">
		</span>
		<span  id="filledActiveRequistionloading">
		<img src="jsp/images/indicator.gif"/>
		</span>
       </div>  
	   <%}%>
        
         <% 
		   value = (String)widgetmap.get("ONBOARDING_APPLICANTS");
		   String isOnboardAppVisible="false";
		   if(value != null && value.equals("A")){
			   isOnboardAppVisible="true";
		   }
		   %>

		   <% if(isOnboardAppVisible.equals("true")){%>
     <div class="title_box">On board applicants ( 90 days)</div>  
     <div class="border_box">
        <span id="onboardapplicants">
		</span>
		<span  id="onboardapplicantsloading">
		<img src="jsp/images/indicator.gif"/>
		</span>
     </div>  
      <%}%>
     <% 
		   value = (String)widgetmap.get("IMP_LINKS");
		   String isImpLinkVisible="false";
		   if(value != null && value.equals("A")){
			   isImpLinkVisible="true";
		   }
		   %>

		   <% if(isImpLinkVisible.equals("true")){%>
     <div class="title_box">Important links</div>  
     <div class="border_box">
	<%@ include file="implinks.jsp" %>
     </div>  
	  <%}%>
     
     <!--<div class="banner_adds">
     
     <a href="#"><img src="jsp/images1/bann2.jpg" alt="" title="" border="0" /></a>
     </div>  -->  
        
    
   </div><!-- end of left content -->
   
   </td>
   <td valign="top" width="50%">
   
   <div class="center_content">
			<% 
		   value = (String)widgetmap.get("MY_ACTIVE_REQ");
		   String isMyActiveReqVisible="false";
		   if(value != null && value.equals("A")){
			   isMyActiveReqVisible="true";
		   }
		   %>

		   <% if(isMyActiveReqVisible.equals("true")){%>
<div style="background: #f3f3f3;"><a href="#" rel="toggle[assinedreq]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Assigned to me Requisitions </div>
<div id="assinedreq" style="width  100%;">

<table>
<tr>
<td>
<a href="#" onClick="configuareColumns('<%=Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS%>');return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
 <!--Assigned to me Requistions-->

	<body class="yui-skin-sam">
	<div id="dynamicdata"></div>
	</body>
</td>
</tr>
</table>
</div>

			<%}%>
<br>
			<% 
		   value = (String)widgetmap.get("OFFER_IN_PROCESS");
		   String isOfferInProcessVisible="false";
		   if(value != null && value.equals("A")){
			   isOfferInProcessVisible="true";
		   }
		   %>

		   <% if(isOfferInProcessVisible.equals("true")){%>
<div style="background: #f3f3f3;"><a href="#" rel="toggle[applicantofferprocess]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Applicants in offer process </div>
<div id="applicantofferprocess" style="width  100%;">
<table>
<tr>
<td>
<a href="#" onClick="configuareColumns('<%=Common.DASHBOARD_RECRUITER_IN_OFFER_PROCESS%>');return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
 <!--Applicants in offer process  -->

	<body class="yui-skin-sam">
	<div id="dynamicdata2"></div>
	</body>	
</td>

</tr>
</table>
</div>
				<%}%>
<br>
			<% 
		   value = (String)widgetmap.get("OFFER_ACCEPTED");
		   String isOfferAcceptedVisible="false";
		   if(value != null && value.equals("A")){
			   isOfferAcceptedVisible="true";
		   }
		   %>

		   <% if(isOfferAcceptedVisible.equals("true")){%>
<div style="background: #f3f3f3;"><a href="#" rel="toggle[applicantjoiningprocess]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Offer accepted applicants (joining within 30 days) </div>
<div id="applicantjoiningprocess" style="width  100%;">
<table>
<tr>
<td>
<a href="#" onClick="configuareColumns('<%=Common.DASHBOARD_RECRUITER_IN_JOINED_PROCESS%>');return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
<!-- Offer accepted applicants (joining within 30 days) -->

	<body class="yui-skin-sam">
	<div id="dynamicdata1"></div>
	</body>

</td>

</tr>
</table>
</div>
<%}%>  
</div><!-- end of center content -->
	
   </td>
   <td valign="top" width="25%">
   
   <div class="right_content">
   			<% 
		   value = (String)widgetmap.get("REQ_SLIPING_TARGET");
		   String isReqSlipingTargetVisible="false";
		   if(value != null && value.equals("A")){
			   isReqSlipingTargetVisible="true";
		   }
		   %>

		   <% if(isReqSlipingTargetVisible.equals("true")){%>
   <div class="title_box">Requisitions Sliping target</div>  
   <div class="border_box">
     <span id="targetdateslip">

	 </span>
	 <span  id="targetslipingloading">
	 <img src="jsp/images/indicator.gif"/>
	 </span>
     </div>  
			<%}%>
     	  <% 
		   value = (String)widgetmap.get("OFFER_DECLINED");
		   String isOfferDeclinedVisible="false";
		   if(value != null && value.equals("A")){
			   isOfferDeclinedVisible="true";
		   }
		   %>

		   <% if(isOfferDeclinedVisible.equals("true")){%> 
     <div class="title_box">Offer declined (90 days)</div>  
     <div class="border_box">
       <span id="offerdeclinedapplicants">

	 </span>
	 <span  id="offerdeclinedapplicantsloading">
	 <img src="jsp/images/indicator.gif"/>
	 </span>
     </div>  
         <%}%>
      	  <% 
		   value = (String)widgetmap.get("RECENT_INTERVIEWS");
		   String isRecentIntVisible="false";
		   if(value != null && value.equals("A")){
			   isRecentIntVisible="true";
		   }
		   %>   
		   <% if(isRecentIntVisible.equals("true")){%> 
	 <div class="title_box">Recent interviews</div>  
     <div class="border_box">
       <span id="todaysinterviews">

	 </span>
	 <span  id="todaysinterviewsloading">
	 <img src="jsp/images/indicator.gif"/>
	 </span>
     </div>
     
           <%}%>  
     
       
     
   </div><!-- end of right content -->   

   </td>
   </tr>
   </table>
   
            
   </div><!-- end of main content -->
   
   
   
  
</div>
<!-- end of main_container -->
</body>



<%
 String strcolumncontentassignedreq = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS);
 String strkeycontentassignedreq = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS);

%>







<script type="text/javascript">

YAHOO.example.DynamicData = function() {



var formatUrl2 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requisition" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + sData + "</a>";
       
        }

	var summaryUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='jobreq.do?method=requistionapplicantlist&state=0&requistionId=" + oRecord.getData("jobreqId")+"&secureid=" + oRecord.getData("uuid") +"'"+ ">" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>" + "</a>";
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
		{key:"uuid", hidden:true},
			{key:"currentOwnerId", hidden:true},
			{key:"isGroup", hidden:true},
            {key:"jobreqName", label:"<%=Constant.getResourceStringValue("Requisition.jobreqname",user.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl2},
			<%=strcolumncontentassignedreq%>
		{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary",user.getLocale())%>", sortable:false, resizeable:true,formatter:summaryUrl}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/assignedtomejobrequisitionlistpage.jsp?currentuserid=<%=user.getUserId()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"currentOwnerId"},
			{key:"isGroup"},
        <%=strkeycontentassignedreq%>
	
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=5", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:5 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
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


<%
 String strcolumncontentjoiningprocess = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.DASHBOARD_RECRUITER_IN_JOINED_PROCESS);
 String strkeycontentjoiningprocess = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.DASHBOARD_RECRUITER_IN_JOINED_PROCESS);

%>

<script type="text/javascript">

YAHOO.example.DynamicData1 = function() {

	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&applicantId=" + oRecord.getData("applicantId") + "&secureid=" + oRecord.getData("uuid") +"'"+ " class='submodal-600-500'>" + sData + "</a>";
        };


var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
	
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requisition" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=requistionDetails('" + oRecord.getData("reqId") + "','"+oRecord.getData("juuid")+"')"+ ">" + sData + "</a>";
        
        }

var formatYesNo = function(elCell, oRecord, oColumn, sData) {
		 if(oRecord.getData("initiateJoiningProcess")	== "Y"){
			  elCell.innerHTML = "<%=Constant.getResourceStringValue("hr.text.yes",user.getLocale())%>";
		 }else{
			 elCell.innerHTML =  "<%=Constant.getResourceStringValue("hr.text.no",user.getLocale())%>";
		 }
        
        
        };





var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else{
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("offerownerId")+ "'"+","+"'"+ oRecord.getData("isofferownerGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
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

		var formatUrlForRecruiter = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("recruitergroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("recruitergroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("recruiterId")+ "'"+","+"'"+ oRecord.getData("recruitergroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };


    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"reqId", hidden:true},	
		{key:"uuid", hidden:true},
		{key:"juuid", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrl},
         <%=strcolumncontentjoiningprocess%>
				{key:"isofferownerGroup", hidden:true}
	
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/mytargetjoiningapplicantslistpageRecruiter.jsp?interviewstate=<%=Common.OFFER_ACCEPTED%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
             <%=strkeycontentjoiningprocess%>
			
	
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=5", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:5 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata1", myColumnDefs, myDataSource, myConfigs);
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

<%
 String strcolumncontentofferprocess = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.DASHBOARD_RECRUITER_IN_OFFER_PROCESS);
 String strkeycontentofferprocess = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.DASHBOARD_RECRUITER_IN_OFFER_PROCESS);

%>

<script type="text/javascript">

YAHOO.example.DynamicData2 = function() {

	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&applicantId=" + oRecord.getData("applicantId") + "&secureid=" + oRecord.getData("uuid") +"'"+ " class='submodal-600-500'>" + sData + "</a>";
        };


var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requisition" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=requistionDetails('" + oRecord.getData("reqId") +  "','"+oRecord.getData("juuid")+"')"+ ">" + sData + "</a>";
        
        };



var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else{
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
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

    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"reqId", hidden:true},	
		{key:"uuid", hidden:true},
		{key:"juuid", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
           <%=strcolumncontentofferprocess%>
				{key:"isofferownerGroup", hidden:true}

					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/home/myofferapplicantslistpageRecruiter.jsp?ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
           <%=strkeycontentofferprocess%>
			
			
	
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=5", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:5 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata2", myColumnDefs, myDataSource, myConfigs);
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







<script language="javascript">       
	  document.getElementById('progressbartable1').style.display = 'none';  
</script>

<script language="javascript">
var isFilledActiveReqVisible="<%=isFilledActiveReqVisible%>";
if(isFilledActiveReqVisible == "true"){
filledActiveRequistion();
}
var isReqSlipingTargetVisible="<%=isReqSlipingTargetVisible%>";
if(isReqSlipingTargetVisible == "true"){
targetSlipingdate();
}
var isOnboardAppVisible="<%=isOnboardAppVisible%>";
if(isOnboardAppVisible == "true"){
onboardapplicants();
}
var isOfferDeclinedVisible="<%=isOfferDeclinedVisible%>";
if(isOfferDeclinedVisible == "true"){
offerdeclinedapplicants();
}
var isRecentIntVisible="<%=isRecentIntVisible%>";
if(isRecentIntVisible == "true"){
todaysinterviews();
}
</script>