<%@ include file="../common/yahooincludes.jsp" %>
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/container/assets/skins/sam/container.css" />
<%@ include file="../common/autocomplete.jsp" %>

<%
User user1 = (User)session.getAttribute(Common.USER_DATA);

String saverequisition = (String)request.getAttribute("saverequisition");
String editjobrequisition = (String)request.getAttribute("editjobrequisition");
String isDirtySaveSuccess = (String)request.getAttribute("isDirtySaveSuccess");
if(StringUtils.isNullOrEmpty(isDirtySaveSuccess)){
	isDirtySaveSuccess="false";
}
String approveralreadyadded = (String)request.getAttribute("approveralreadyadded");

boolean isEditReqAfterPublishPermission = PermissionChecker.isPermissionApplied(Common.EDIT_REQUISITION_AFTER_PUBLISH, user1);

//
String juju = (String)request.getAttribute("juju");
String olx = (String)request.getAttribute("olx");
String oodle = (String)request.getAttribute("oodle");
String trovit = (String)request.getAttribute("trovit");
String twitjobsearch = (String)request.getAttribute("twitjobsearch");
String workhound = (String)request.getAttribute("workhound");
String yakaz = (String)request.getAttribute("yakaz");
String indeed = (String)request.getAttribute("indeed");
String simplyhired = (String)request.getAttribute("simplyhired");
String jooble = (String)request.getAttribute("jooble");
String careervital = (String)request.getAttribute("careervital");
String jobdiesel = (String)request.getAttribute("jobdiesel");
%>
<%@ include file="screenFieldSortingReq.jsp" %>
<%
int jobdetailsothersize = JOB_DETAILS_OTHERS_List.size();
%>


<html>
<title><%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%> </title>
<link rel="stylesheet" type="text/css" href="jsp/tabcontent/tabcontent.css" />

<script type="text/javascript" src="jsp/tabcontent/tabcontent.js">


</script>




<script type="text/javascript">
function openuserdetails(url){ //Define arbitrary function to run desired DHTML Window widget codes
ajaxwin=dhtmlwindow.open("ajaxbox", "ajax", url, "User details", "width=650px,height=800px,resize=1,scrolling=1")
//ajaxwin.onclose=function(){return window.confirm("Close window 3?")} //Run custom code when window is about to be closed
}
</script>

<%
	java.util.List autoCompleteItems = new java.util.ArrayList();
	autoCompleteItems.add(new net.sourceforge.jscontrolstags.server.autocomplete.AutocompleteItem("cos()", "Cosinus function"));
	autoCompleteItems.add(new net.sourceforge.jscontrolstags.server.autocomplete.AutocompleteItem("sin()", "Sinus function"));
	for(int i=0; i<200; i++) {
		autoCompleteItems.add(new net.sourceforge.jscontrolstags.server.autocomplete.AutocompleteItem("Parameter_" + i, "Description of parameter_" + i));
	}
	request.setAttribute("autoCompleteItems", autoCompleteItems);
%>



	<jscontrols:autocomplete 
        source="accomplishment_1" 
        className="autocomplete"
        items="${requestScope.autoCompleteItems}"
         /> 

<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<%
String modaltitle="Initiate Approval";
String approvalurl ="";
String rejecturl ="";
String reassignurl ="";
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String publishurl = "jobreq.do?method=publishJobReq&jobreqId="+jobreqform.getJobreqId();
String closeurl = "jobreq.do?method=closerequisition&jobreqId="+jobreqform.getJobreqId();
String deleteurl ="jobreq.do?method=deleterequisition&jobreqId="+jobreqform.getJobreqId();
String recallurl ="jobreq.do?method=recalljobreq&jobreqId="+jobreqform.getJobreqId();

//String saveAsurl ="jobreq.do?method=saveasjobrequisition&jobreqId="+jobreqform.getJobreqId();
String saveastrue=(String)request.getAttribute("saveas");




List formvariableList = jobreqform.getFormVariablesList();


%>

<%
List approverList = jobreqform.getApproversList();

boolean isapproverloggedin = false;
if(approverList != null){
	if(approverList.size() == 0){
		approvalurl = "";
	}else{
		approvalurl = "jobreq.do?method=initiateApproval&jobreqId="+jobreqform.getJobreqId();
	}
}
 rejecturl ="jobreq.do?method=rejectreq&jobreqId="+jobreqform.getJobreqId();
 reassignurl ="jobreq.do?method=reassignreq&jobreqId="+jobreqform.getJobreqId();
 
if(jobreqform.getIsapprovalInitiated() == 1 && jobreqform.getIsrejected() != 1){ 
	for(int j=0;j<approverList.size();j++){
		isapproverloggedin = false;
	
	   JobTemplateApprovers lastjapp1 = null;
	   if(j != 0){
		  lastjapp1 = (JobTemplateApprovers)approverList.get(j-1);
	   }
	   
		JobTemplateApprovers japp1 = (JobTemplateApprovers)approverList.get(j);
		if(BOFactory.getJobRequistionBO().isLoggedInUserIsApprover(user1.getUserId(),japp1.getUserId(),japp1.getIsGroup())&& !japp1.getApproved().equals("Y")){
			if(lastjapp1 != null){
				if(lastjapp1.getApproved().equals("Y") && !japp1.getApproved().equals("Y")){
				modaltitle="Approve";
				isapproverloggedin = true;
		        approvalurl =  "jobreq.do?method=approvereq&jobreqId="+jobreqform.getJobreqId()+"&jbTmplApproverId="+japp1.getJbTmplApproverId();
				rejecturl = "jobreq.do?method=rejectreq&jobreqId="+jobreqform.getJobreqId()+"&jbTmplApproverId="+japp1.getJbTmplApproverId();
				reassignurl = "jobreq.do?method=reassignreq&jobreqId="+jobreqform.getJobreqId()+"&jbTmplApproverId="+japp1.getJbTmplApproverId();
				}
			}else {
	
				if(!japp1.getApproved().equals("Y")){
				modaltitle="Approve";
				isapproverloggedin = true;
		        approvalurl =  "jobreq.do?method=approvereq&jobreqId="+jobreqform.getJobreqId()+"&jbTmplApproverId="+japp1.getJbTmplApproverId();
				rejecturl = "jobreq.do?method=rejectreq&jobreqId="+jobreqform.getJobreqId()+"&jbTmplApproverId="+japp1.getJbTmplApproverId();
				reassignurl = "jobreq.do?method=reassignreq&jobreqId="+jobreqform.getJobreqId()+"&jbTmplApproverId="+japp1.getJbTmplApproverId();
				}
	
			}
	   	break;
		}
	}//for
}

%>

<%
boolean isHiringMgrOrRecruiterLoggedIn=false;
boolean isAllReqPermission=false;
if(PermissionBO.isHiringManagerOrRecruiter(user1,jobreqform.getHiringMgrId(),jobreqform.getRecruiterId(),jobreqform.getIsgrouprecruiter())){
	isHiringMgrOrRecruiterLoggedIn=true;
}
if(PermissionChecker.isPermissionApplied(Common.ALL_REQUISITIONS, user1)){
	isAllReqPermission = true;
}
%>
<head>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<style>
span1{color:#ff0000;}
</style>
  

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
</style>
<%@ include file="../common/tooltipnewcss.jsp" %>

<link rel="stylesheet" type="text/css" href="jsp/css/calreq.css" />


<style type="text/css">
.yui-navset button {
    position:absolute;
    top:0;
    right:0;
}
</style>
<!--begin custom header content for this example-->
<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>

<style>
span1{color:#ff0000;}
</style>

<style type="text/css">
#example {
    height:30em;
}

label { 
    display:block;
    float:left;
    width:45%;
    clear:left;
}

.clear {
    clear:both;
}

#resp {
    margin:10px;
    padding:5px;
    border:1px solid #ccc;
    background:#fff;
}

#resp li {
    font-family:monospace
}
.datepatern{
   float: right;
   display :block;
   width: 25em;
   position:absolute;
   background-color:white;
   layer-background-color:block;
   top:60px;
   left: 508px;
   z-index: 1;
}

.yui-pe .yui-pe-content {
    display:none;
}
</style>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<script language="javascript">

var isDirtySaveSuccess = "<%=isDirtySaveSuccess%>";

function validateUserhm(){
	if(document.jobRequisitionForm.highiringmanagernamehidden.value == "" || document.jobRequisitionForm.highiringmanagernamehidden.value == null){
		document.jobRequisitionForm.hiringMgrName.value = document.jobRequisitionForm.hiringMgrName.value;
	}else{
	document.jobRequisitionForm.hiringMgrName.value=document.jobRequisitionForm.highiringmanagernamehidden.value;

	}
}

function validateUserrecruiter(){
	if(document.jobRequisitionForm.recruiterNamehidden.value == "" || document.jobRequisitionForm.recruiterNamehidden.value == null){
		ddocument.jobRequisitionForm.recruiterName.value = document.jobRequisitionForm.recruiterName.value;
	}else{
	document.jobRequisitionForm.recruiterName.value =document.jobRequisitionForm.recruiterNamehidden.value; 
	}
}


function openConfiguration(){
	  var width = 800;
    var height = 700;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
	  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("screenfield.do?method=editscreenfieldReq&screencode=REQUISITION_SCREEN");


    var windowFeatures = "width=" + width + ",height=" + height + ",status,resizable,location=0,status=0,scrollbars=1,menubar=0,left=" + left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
    myWindow = window.open(url, "OpenConfiguration", windowFeatures);
}

function opensearchassignedtohiring(){
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=addhighiringmanagerreq");
  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

var jobreqidscript = "<%=jobreqform.getJobreqId()%>";
var issaveas="<%=saveastrue%>";


function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		   self.parent.location.reload();
	   parent.parent.GB_hide();
	   } 
	}


function deleteattachement(url){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		    document.jobRequisitionForm.action = url;
   document.jobRequisitionForm.submit();
	  }
}

function form_is_modified()
{
oForm = document.jobRequisitionForm;
	var el, opt, hasDefault, i = 0, j;
	while (el = oForm.elements[i++]) {
		switch (el.type) {
			case 'text' :
                   	case 'textarea' :
                   	case 'hidden' :
                         	if (!/^\s*$/.test(el.value) && el.value != el.defaultValue) return true;
                         	break;
                   	case 'checkbox' :
                   	case 'radio' :
                         	if (el.checked != el.defaultChecked) return true;
                         	break;
                   	case 'select-one' :
                   	case 'select-multiple' :
                         	j = 0, hasDefault = false;
                         	while (opt = el.options[j++])
                                	if (opt.defaultSelected) hasDefault = true;
                         	j = hasDefault ? 0 : 1;
                         	while (opt = el.options[j++]) 
                                	if (opt.selected != opt.defaultSelected) return true;
                         	break;
		}
	}
	return false;
}
function UIvalidationCheck(){
	var alertstr = "";
	var showalert=false; 
	var name=document.jobRequisitionForm.jobreqName.value.trim();
	 var titel=document.jobRequisitionForm.jobTitle.value.trim();
	 var possitionName=document.jobRequisitionForm.jobPosition.value.trim();
	 var openings=document.jobRequisitionForm.numberOfOpening.value.trim();
	 var FinishDate=document.jobRequisitionForm.targetfinishdate.value.trim();
	 var salaryplanIdvalue=document.jobRequisitionForm.salaryplanId.value.trim();
	 var hiringMgrIdvalue=document.jobRequisitionForm.hiringMgrId.value.trim();
	 var recruiterName=document.jobRequisitionForm.recruiterName.value.trim();
	 var budgetcodeIdvalue=document.jobRequisitionForm.budgetcodeId.value.trim();
	 
      	
		  if(budgetcodeIdvalue == "" || budgetcodeIdvalue == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.budget.code.alert",user1.getLocale())%><br>";
		showalert = true;
		}
	 
	  
		if(salaryplanIdvalue == "" || salaryplanIdvalue == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.salary.plan.alert",user1.getLocale())%><br>";
		showalert = true;
		}	
	
	   

		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobTitle.Name.Mandatory",user1.getLocale())%><br>";
		showalert = true;
		}		
	 if(titel == "" || titel == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobTitle_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(possitionName == "" || possitionName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Possitionname_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(openings == "" || openings == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.No_ofOpenings_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
		
		if(FinishDate == "" || FinishDate == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Finishdate_required",user1.getLocale())%><br>";
		showalert = true;
		}

		if(hiringMgrIdvalue == "" || hiringMgrIdvalue == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.hiring.mgr.alert",user1.getLocale())%><br>";
		showalert = true;
		}
		if(recruiterName == "" || recruiterName == null){
			document.jobRequisitionForm.recruiterId.value="0";
			}
	 if (showalert){
     	alert(alertstr);
        return false;
          }else{
			  return true;
		  }
}

function checkvalidation(){
	var alertstr = "";
	var showalert=false; 
	
	var numbers=/^[0-9]+$/;
	
	
	

	
	if(typeof document.jobRequisitionForm.numberOfOpening != 'undefined'){
		var openings=document.jobRequisitionForm.numberOfOpening.value.trim();
	if(openings < 0 ){
		 alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.minus.value.notallowed",user1.getLocale())%><%=Constant.getResourceStringValue("Requisition.TotalNoofopenings",user1.getLocale())%><br>";
		 showalert = true;
	 }
	}
if(typeof document.jobRequisitionForm.defaultStandardHours != 'undefined'){
	var defaultStandardHours=document.jobRequisitionForm.defaultStandardHours.value;
	if(numbers.test(defaultStandardHours)==false){
	    alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.DefaultStandardWorkingHour",user1.getLocale())%>&nbsp;<%=Constant.getResourceStringValue("validation.value_in_number",user1.getLocale())%><br>";
		showalert = true;
	 }
}
if(typeof document.jobRequisitionForm.durationinmonths != 'undefined'){
	var durationinmonths=document.jobRequisitionForm.durationinmonths.value;
	if(numbers.test(durationinmonths)==false){
	    alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.DurationinMonths",user1.getLocale())%>&nbsp;<%=Constant.getResourceStringValue("validation.value_in_number",user1.getLocale())%><br>";
		showalert = true;
	 }
}
if(typeof document.jobRequisitionForm.minyearsofExpRequired != 'undefined'){
	var minyearsofExpRequired=document.jobRequisitionForm.minyearsofExpRequired.value;
	if(numbers.test(minyearsofExpRequired)==false){
	    alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.minexp",user1.getLocale())%>&nbsp;<%=Constant.getResourceStringValue("validation.value_in_number",user1.getLocale())%><br>";
		showalert = true;
	 }
}
if(typeof document.jobRequisitionForm.maxyearsofExpRequired != 'undefined'){
		var maxyearsofExpRequired=document.jobRequisitionForm.maxyearsofExpRequired.value;
	if(numbers.test(maxyearsofExpRequired)==false){
	    alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.maxexp",user1.getLocale())%>&nbsp;<%=Constant.getResourceStringValue("validation.value_in_number",user1.getLocale())%><br>";
		showalert = true;
	 }
} 
	if (showalert){
 		alert(alertstr);
    	return false;
     }else{
		  return true;
	 }
}
function savedata(){
	
	//if(UIvalidationCheck()==false)  return false;
	if(checkvalidation()==false)  return false;
	var recidval = 0;

	
	if(typeof document.jobRequisitionForm.recruiterName != 'undefined'){
	var recruiterName=document.jobRequisitionForm.recruiterName.value.trim();
	if(recruiterName == "" || recruiterName == null){
			document.jobRequisitionForm.recruiterId.value="0";
			document.jobRequisitionForm.isgrouprecruiter.value="N";
			recidval=0;
			}else{
				recidval = document.jobRequisitionForm.recruiterId.value.trim();
			}
	}
	document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=updatejobreq&jobreqId="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>'+"&hiringMgrId="+document.jobRequisitionForm.hiringMgrId.value.trim()+"&recruiterId="+recidval;
  document.jobRequisitionForm.method="POST";
   document.jobRequisitionForm.submit();

   
	}
	function refreshjobreq(){

		  document.getElementById('progressbartable1').style.display = 'inline'; 
		  document.jobRequisitionForm.action = "jobreq.do?method=editjobreq&jobreqId="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
	   document.jobRequisitionForm.submit();
		

	   
		}
function resetjobreq(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.reset.msg",user1.getLocale())%>");
	  if (doyou == true){
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=resetjobreq&jobreqId="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
   document.jobRequisitionForm.submit();
	  }
 
	}

function recalljobreq(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.recall.msg",user1.getLocale())%>");
	  if (doyou == true){
		  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=recalljobreq&jobreqId="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
   document.jobRequisitionForm.submit();
	  }
}

function download(){
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=downloadPdf&uuid="+'<bean:write name="jobRequisitionForm" property="uuid"/>';
	   document.jobRequisitionForm.submit();
}

function saveasJobReq(name,jobtitle,isCompetencyChecked,isAccomplishmentChecked,isApproversChecked,isAttachmentsChecked){

	    
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=saveasjobrequisition&uuid="+'<bean:write name="jobRequisitionForm" property="uuid"/>'+"&jobreqName="+name+"&jobTitle="+jobtitle+"&isCompetencyChecked="+isCompetencyChecked+"&isAccomplishmentChecked="+isAccomplishmentChecked+"&isApproversChecked="+isApproversChecked+"&isAttachmentsChecked="+isAttachmentsChecked;
	   document.jobRequisitionForm.submit();

   
   
	}
	

function addattachment(){

	
	var alertstr = "";
  var showalert=false;
	var filename = document.jobRequisitionForm.attachmentdata.value;
	var filedetails = document.jobRequisitionForm.attahmentdetails.value;
	
	if(filename == "" || filename == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.attacment.alert1",user1.getLocale())%><br>";
		showalert = true;
		}

	if(filedetails == "" || filedetails == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.attacment.alert2",user1.getLocale())%><br>";
		showalert = true;
		}
	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
 	 document.jobRequisitionForm.action = "jobreq.do?method=addattachment&jobreqId="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
	   document.jobRequisitionForm.submit();
}





function editJobReq(jb_id){
	
	var url = "jobreq.do?method=editjobreq&jobreqId="+jb_id;
	window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}

function opensearchlocation(){
 var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("location.do?method=selectlocation&readPreview=3");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchorg(){
 
  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("org.do?method=orgselector&readPreview=3&orgId=1");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}




function opensearchrecruiter(){
	document.getElementById("recruitersave").style.visibility = "hidden";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=addrecruiter");
  window.open(url, "SearchRecruiter","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchassignedto(){
    var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=assignedto");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  
}

function opensearchapprover(boxnumber){
	var tu = "user.do?method=assignedtoselector&boxnumber="+boxnumber;
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 
}

function addwatchers(){
	var tu = "watchlist.do?method=watchList&type=REQ&applicantId=0&reqid="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  
}
function addComments(){
	var tu = "jobreq.do?method=commentList&uuid="+'<bean:write name="jobRequisitionForm" property="uuid"/>';
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");

  
}


function opensearchdepartment(){
	var orgidvalue = document.jobRequisitionForm.parentOrgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectorg.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("dept.do?method=departmentlocator&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function opensearchprojectcode(){
	var deptvalue = document.jobRequisitionForm.departmentId.value;
	var orgidvalue = document.jobRequisitionForm.parentOrgId.value;
	if(deptvalue == 0 || deptvalue == ""){
		alert("<%=Constant.getResourceStringValue("Requisition.selectdept.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("lov.do?method=projectcodelocator&readPreview=3&deptId="+deptvalue+"&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchjobgrade(){
	 var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=jobgradelocator");
  window.open(url,"SearchJobGrade","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  
}
function opensearchjobcode(){
  window.open("lov.do?method=jobcodeselector", "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchsalaryplan(){
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=salaryplanselector");
  window.open(url, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 
}
function opensearchbudgetcode(){
	var deptvalue = "0";
	if(typeof document.jobRequisitionForm.departmentId != 'undefined'){
  deptvalue = document.jobRequisitionForm.departmentId.value;
	}
 
	var orgidvalue = document.jobRequisitionForm.parentOrgId.value;
	
   var tu = "lov.do?method=budgetcodeselector&orgId="+orgidvalue+"&deptId="+deptvalue;
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}



function opensearchreferralscheme(k){
	var orgidvalue = document.jobRequisitionForm.parentOrgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectdept.and.org.alert1",user1.getLocale())%>");
		return false;
	}
	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=1&orgid="+orgidvalue+"&type=E&boxnumber="+k);
 
 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}




function opensearchagencyreferralscheme(k){
	var orgidvalue = document.jobRequisitionForm.parentOrgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectdept.and.org.alert1",user1.getLocale())%>");
		return false;
	}
	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=2&orgid="+orgidvalue+"&type=V&boxnumber="+k);

 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}
function opensearchsalaryplan(){
	var orgidvalue = document.jobRequisitionForm.parentOrgId.value;
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=salaryplanselector&orgid="+orgidvalue);
  window.open(url, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

}

function initiateApproval(){
   var isDirtySave= false;

 	if(form_is_modified()){
		 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.dirty.requition.msg",user1.getLocale())%>");
	  if (doyou == true){
		  isDirtySave = true;
		  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=updatejobreq&isDirtySave=true&jobreqId="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>'+"&hiringMgrId="+document.jobRequisitionForm.hiringMgrId.value.trim()+"&recruiterId="+document.jobRequisitionForm.recruiterId.value.trim();
  document.jobRequisitionForm.method="POST";
   document.jobRequisitionForm.submit();
	  }else{
		 // return false;
		  isDirtySave = true;
	  }
	}
	
	if(!isDirtySave){
	var url = "jobreq.do?method=initiateApprovalscr&jobreqId=<%=jobreqform.getJobreqId()%>";
   
    var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.showModalDialog(url,"ok .. <%=Constant.getResourceStringValue("Requisition.initiate.approval",user1.getLocale())%>","dialogHeight: 634px; dialogWidth: 850px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

    
   window.document.location.href="jobreq.do?method=editjobreq&jobreqId=<%=jobreqform.getJobreqId()%>&tabno=1";
	}

	 
}

function createJobTitle(){
	var url="<%=request.getContextPath()%>/designation.do?method=createDesignation";
	 window.showModalDialog(url,"New Job Title","dialogHeight: 434px; dialogWidth: 650px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

    
   window.document.location.href="jobreq.do?method=editjobreq&jobreqId=<%=jobreqform.getJobreqId()%>&tabno=1";
}

function manageCustomVariables(){
	var url = "variablemap.do?method=manageVariableMappingscr&formcode=REQUISITION_FORM&formname=REQUISITION FORM&id=<%=jobreqform.getJobreqId()%>";
     var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.initiate.approval",user1.getLocale())%>","dialogHeight: 434px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

  window.document.location.href="jobreq.do?method=editjobreq&jobreqId=<%=jobreqform.getJobreqId()%>&tabno=7";
}

function publishRequistion(){
	var url = "jobreq.do?method=publishJobReqscr&jobreqId=<%=jobreqform.getJobreqId()%>";
  

  
  //window.open(url1, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");


  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.Publish.requistion",user1.getLocale())%>","dialogHeight: 634px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

   
   window.document.location.href="jobreq.do?method=editjobreq&jobreqId=<%=jobreqform.getJobreqId()%>&tabno=8";
}


function openExpenseScreen(){
	var url = "reqexp.do?method=requistionExpenses&jobreqId=<%=jobreqform.getJobreqId()%>";
  

  
  

  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.add.expenses",user1.getLocale())%>","dialogHeight: 600px; dialogWidth: 1100px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");
   
}



function approvalinitiated(){
	var value='<%=jobreqform.getState()%>';
	if(value=="<%=Common.IN_APPROVAL%>"){
    alert("<%=Constant.getResourceStringValue("Requisition.alert.pendingapproval.alert1",user1.getLocale())%>");
    }else if(value=="<%=Common.APPROVED%>"){
    alert("<%=Constant.getResourceStringValue("Requisition.alert.approved.alert1",user1.getLocale())%>");
	}else if(value=="<%=Common.ACTIVE%>"){
    alert("<%=Constant.getResourceStringValue("Requisition.alert.inactive.alert1",user1.getLocale())%>");
	}else{

	alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%> ");
}
}
function init(){
       
	  document.getElementById('progressbartable1').style.display = 'none';  
	  document.getElementById('testdiv1').style.display = 'none'; 
      
	  if(isDirtySaveSuccess == "true"){
	var url = "jobreq.do?method=initiateApprovalscr&jobreqId=<%=jobreqform.getJobreqId()%>";
   
    var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.initiate.approval",user1.getLocale())%>","dialogHeight: 434px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

   window.document.location.href="jobreq.do?method=editjobreq&jobreqId=<%=jobreqform.getJobreqId()%>&tabno=1";
	}
	  
	  initfocus();

	  
		}





function saveRecruiterdata(){
        var alertstr = "";
        document.getElementById("recruitersave").style.visibility = "visible";
        document.getElementById("loading3").style.visibility = "visible";
		var recruiterId = document.jobRequisitionForm.recruiterId.value;
		var recruiterName = document.jobRequisitionForm.recruiterName.value;
		var isgrouprecruiter = document.jobRequisitionForm.isgrouprecruiter.value;
		var showalert=false;

	if(recruiterName == "" || recruiterName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.add.recruiter.alert",user1.getLocale())%><BR>";
		showalert = true;
		}



	 if (showalert){
     	alert(alertstr);
        return false;
          }

	 
var url="jobreq.do?method=saverecruiterajax&requisitionId=<%=jobreqform.getJobreqId()%>&recruiterId="+recruiterId+"&recruiterName="+recruiterName+"&isgrouprecruiter="+isgrouprecruiter;


//url = encodeURIComponent(url);


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
    


      // setTimeout("retrieveOrganizations()",200);

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
    	document.getElementById("loading3").style.visibility = "hidden";	
    	
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
				  
		    	  if(name="recruitersave")
   					document.getElementById(name).innerHTML = content;
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



function addCompetency(){
        var alertstr = "";


		var competencyname = escape(document.jobRequisitionForm.competencyname.value);
		var iscompmandatory1 = "";//document.jobRequisitionForm.iscompmandatory.value;
		var minimumrating = document.jobRequisitionForm.minimumrating.value;
		var isVisible = "";
		
		var showalert=false;

		if(competencyname == "" || competencyname == null){
	     	alertstr = alertstr + "Competency required.<BR>";
			showalert = true;
			}



		 if (showalert){
	     	alert(alertstr);
	        return false;
	          }
		//alert(document.jobRequisitionForm.group1.value);
	for (var i=0; i < document.jobRequisitionForm.iscompmandatory.length; i++)
   {
   if (document.jobRequisitionForm.iscompmandatory[i].checked)
      {
       iscompmandatory1 = document.jobRequisitionForm.iscompmandatory[i].value;
      }
   }
	for (var i=0; i < document.jobRequisitionForm.isVisible.length; i++)
	   {
	   if (document.jobRequisitionForm.isVisible[i].checked)
	      {
		   isVisible = document.jobRequisitionForm.isVisible[i].value;
	      }
	   }

var url="jobreq.do?method=saveCompetency&type=job&reqId=<%=jobreqform.getJobreqId()%>&competencyname="+competencyname+"&iscompmandatory="+iscompmandatory1+"&minimumrating="+minimumrating+"&isVisible="+isVisible;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveCompetency()",200);

	}


	function retrieveCompetency() {
    document.getElementById("loading4").style.visibility = "visible";
		document.jobRequisitionForm.competencyname.value="";
	var url="jobtemplate.do?method=getCompetencies&id=<%=jobreqform.getJobreqId()%>&type=job";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processComptencies;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processComptencies;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function deletecompetency(id){

	var url="jobtemplate.do?method=deleteComptecy&id="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveCompetency()",200);


}

function processComptencies() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlCompetencies(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading4").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlCompetencies(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="competecydetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


function addAccomplishment(){
        var alertstr = "";
		var accomplishmentname = escape(document.jobRequisitionForm.accomplishmentname.value);
		var isaccommandatory1 = "";
		//var minimumratingaccom = document.jobRequisitionForm.minimumratingaccom.value;
		var showalert=false;

		if(accomplishmentname == "" || accomplishmentname == null){
	     	alertstr = alertstr + "Accomplishment required.<BR>";
			showalert = true;
			}



		 if (showalert){
	     	alert(alertstr);
	        return false;
	          }
	
	for (var i=0; i < document.jobRequisitionForm.isaccommandatory.length; i++)
   {
   if (document.jobRequisitionForm.isaccommandatory[i].checked)
      {
       isaccommandatory1 = document.jobRequisitionForm.isaccommandatory[i].value;
      }
   }

var url="jobreq.do?method=saveAccompleshment&type=job&reqId=<%=jobreqform.getJobreqId()%>&accomplishmentname="+accomplishmentname+"&isaccommandatory="+isaccommandatory1;
//url = encodeURIComponent(url);
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;
	    try {
    		req.open("GET", url, true);
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
       setTimeout("retrieveAccomplishments()",200);

	}

	function retrieveAccomplishments() {
    document.getElementById("loading5").style.visibility = "visible";
		document.jobRequisitionForm.accomplishmentname.value="";
	var url="jobtemplate.do?method=getAccomplishments&id=<%=jobreqform.getJobreqId()%>&type=job";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processAcomplishments;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processAcomplishments;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function processAcomplishments() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlAccomplishments(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading5").style.visibility = "hidden";	
  	}
}


function replaceExistingWithNewHtmlAccomplishments(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="accomplishmentdetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


function deleteAccomplishment(id){

	var url="jobtemplate.do?method=deleteAccomplishment&id="+id;
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
       setTimeout("retrieveAccomplishments()",200);
}


function addApprovers(){
        var alertstr = "";
    	var showalert=false; 
	var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	//var approverName =  document.jobRequisitionForm.approverName.value;
	var appid = document.jobRequisitionForm.approverId.value;
	var isgroupval = document.jobRequisitionForm.isgroup.value;
	var appname =escape(document.jobRequisitionForm.approverName.value);

	if(isapprovalinitiated1 == 1){
	alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%> ");
		return false;
	}
	if(appname == "" || appname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.validation.ApproverName",user1.getLocale())%>";
		showalert = true;
		}
	 if (showalert){
	     	alert(alertstr);
	        return false;
	          }

var url="jobreq.do?method=saveApprover&type=job&jobreqid=<%=jobreqform.getJobreqId()%>&approvername="+appname+"&approverid="+appid+"&isgroup="+isgroupval;

document.jobRequisitionForm.action = url;
document.jobRequisitionForm.submit();

/* commented purposefully 

      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}


     setTimeout("retrieveApprovers()",200);
*/
	

	}

	function retrieveApprovers() {
    document.getElementById("loading6").style.visibility = "visible";

		document.jobRequisitionForm.approverName.value="";
		document.jobRequisitionForm.approverId.value="";
		document.jobRequisitionForm.approverNamehidden.value="";
	var url="jobreq.do?method=getApprovers&id=<%=jobreqform.getJobreqId()%>&type=job&approveralreadyadded=<%=approveralreadyadded%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processApprovers;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processApprovers;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}
	function deleteApprovers(id){

	var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	if(isapprovalinitiated1 == 1){
	alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%> ");
		return false;
	}
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
		var url="jobreq.do?method=deleteApprovers&id="+id+"&reqid="+<%=jobreqform.getJobreqId()%>;
		document.jobRequisitionForm.action = url;
		document.jobRequisitionForm.submit();
	  }

/*
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}

       setTimeout("retrieveApprovers()",200);
*/
}

function processApprovers() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlApprovers(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading6").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlApprovers(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="approverdetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

	function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

function validateUser(){
	document.jobRequisitionForm.approverName.value=document.jobRequisitionForm.approverNamehidden.value;
}






function retrieveURLOrg(url) {
   //convert the url to a string
    document.getElementById("loadingd").style.visibility = "visible";
    var tdata="";
	
	url=url+"&parentorgId="+document.jobRequisitionForm.parentOrgId.value;

    $.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#departments");  
	  $('#departments').html(tdata);
		
	  }
	});

 document.getElementById("loadingd").style.visibility = "hidden";

if(typeof document.jobRequisitionForm.projectcodeId != 'undefined'){
//setTimeout("retrieveProjcodesbyOrganization()",200);
retrieveProjcodesbyOrganization();
}
if(typeof document.jobRequisitionForm.budgetcodeId != 'undefined'){
//setTimeout("retrieveBudgetCode()",300);
retrieveBudgetCode();
}
if(typeof document.jobRequisitionForm.salaryplanId != 'undefined'){
//setTimeout("retrieveSalaryPlanByOrganization()",400);
retrieveSalaryPlanByOrganization();
}
}




function retrieveProjcodes(url) {

	if(typeof document.jobRequisitionForm.projectcodeId != 'undefined'){
    document.getElementById("loadingp").style.visibility = "visible";	
	var departmentId="0";
if(typeof document.jobRequisitionForm.departmentId != 'undefined'){
	departmentId=document.jobRequisitionForm.departmentId.value;
}
	url=url+"&departmentId="+departmentId;


    $.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#projectcodes");  
	  $('#projectcodes').html(tdata);
		
	  }
	});
   
   document.getElementById("loadingp").style.visibility = "hidden";	

	}
	retrieveBudgetCodebyOrganization();
}




function retrieveBudgetCode() {
   //convert the url to a string
    document.getElementById("loadingp").style.visibility = "visible";
var parentOrgId=document.jobRequisitionForm.parentOrgId.value;

var departmentId="0";
if(typeof document.jobRequisitionForm.departmentId != 'undefined'){
	departmentId=document.jobRequisitionForm.departmentId.value;
}
	
	
	url="jobreq.do?method=loadBudgetCode&departmentId="+departmentId+"&orgId="+parentOrgId;
	
  	    $.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#budgetcodescodes");  
	  $('#budgetcodescodes').html(tdata);
		
	  }
	});
   document.getElementById("loadingp").style.visibility = "hidden";
}



function retrieveProjcodesbyOrganization() {

var parentOrgId=document.jobRequisitionForm.parentOrgId.value;
var departmentId="0";
if(typeof document.jobRequisitionForm.departmentId != 'undefined'){
	departmentId=document.jobRequisitionForm.departmentId.value;
}
	url="jobreq.do?method=loadprojectcodebyOrganization&parentOrgId="+parentOrgId+"&departmentId="+departmentId;
	
	$.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#projectcodes");  
	  $('#projectcodes').html(tdata);
		
	  }
	});
}
	 





function retrieveSalaryPlanByOrganization() {

var parentOrgId=document.jobRequisitionForm.parentOrgId.value;

	url="jobreq.do?method=retrieveSalaryPlanByOrganization&parentOrgId="+parentOrgId;
	
  	$.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#salaryplans");  
	  $('#salaryplans').html(tdata);
		
	  }
	});
}
	 




function retrieveBudgetCodebyOrganization() {
   //convert the url to a string
    //document.getElementById("loadingp").style.visibility = "visible";
var parentOrgId=document.jobRequisitionForm.parentOrgId.value;
var departmentId="0";
if(typeof document.jobRequisitionForm.departmentId != 'undefined'){
	departmentId=document.jobRequisitionForm.departmentId.value;
}
	
	
	url="jobreq.do?method=loadBudgetCodebyOrganization&parentOrgId="+parentOrgId+"&departmentId="+departmentId;
	
  	$.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#budgetcodescodes");  
	  $('#budgetcodescodes').html(tdata);
		
	  }
	});

}


</script>


<script type="text/javascript">

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}
function strStartsWith(str, prefix) { 
	return str.indexOf(prefix) === 0;
	}


$(function() {

	$("#recruiterName").autocomplete({
		url: 'jsp/talent/getUserUserGroupData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    
		  
		  var itemvaluedata = ""+item.data;
		  var itemvaluedataname = ""+item.value;
		  
		 
         if(strStartsWith(itemvaluedata,"g")){
			 document.jobRequisitionForm.recruiterName.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.jobRequisitionForm.recruiterId.value=idval.substring(1,idval.length);
			 document.jobRequisitionForm.isgrouprecruiter.value="Y";
			 document.jobRequisitionForm.recruiternamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.jobRequisitionForm.recruiterId.value=item.data;
		 document.jobRequisitionForm.isgrouprecruiter.value="N";
		 document.jobRequisitionForm.recruiterName.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.jobRequisitionForm.recruiternamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		}
		});

	$("#hiringMgrName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
			minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
		     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		  
		document.jobRequisitionForm.hiringMgrId.value=item.data;
		document.jobRequisitionForm.highiringmanagernamehidden.value=item.value;
		//alert(item.data);
		}
		});

     $("#competencyname").autocomplete({
		url: 'jsp/talent/getCompetencyNames.jsp',
			minChars: '<%=Constant.getValue("autocomplete.min.chars")%>'
		
});

     $("#accomplishmentname").autocomplete({
		url: 'jsp/talent/getAccomplishmentNames.jsp',
			minChars: '<%=Constant.getValue("autocomplete.min.chars")%>'
		
});

	$("#approverName").autocomplete({
		url: 'jsp/talent/getUserUserGroupData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {

			  var itemvaluedata = ""+item.data;
		  var itemvaluedataname = ""+item.value;
		  
		 
         if(strStartsWith(itemvaluedata,"g")){
			 document.jobRequisitionForm.approverName.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.jobRequisitionForm.approverId.value=idval.substring(1,idval.length);
			 document.jobRequisitionForm.isgroup.value="Y";
			 document.jobRequisitionForm.approverNamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.jobRequisitionForm.approverId.value=item.data;
		 document.jobRequisitionForm.isgroup.value="N";
		 document.jobRequisitionForm.approverName.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.jobRequisitionForm.approverNamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		
		}
		});


});
function IsNewPositionYesselected(){
	var isNewPositionvalue0 = document.jobRequisitionForm.isNewPosition[0].value;
	if(isNewPositionvalue0 == "Y" ){
		//alert(isNewPositionvalue0);
		document.getElementById("isnewposition").style.visibility = "hidden";
		document.jobRequisitionForm.isNewPositionNoText.style.visibility="hidden";  
		
	}
}
function IsNewPositionNoselected(){

	var isNewPositionvalue1 = document.jobRequisitionForm.isNewPosition[1].value;
	
	
		 //alert(isNewPositionvalue1);

			if(isNewPositionvalue1 == "N" ){

			
				var url="jobreq.do?method=createText";
			    //Do the AJAX call
				if (window.XMLHttpRequest) { 
				    req = new XMLHttpRequest();    	// Non-IE browsers
			  	req.onreadystatechange = processStateChangecreateNotext;

				    try {

			  		req.open("GET", url, true);
						
				    } catch (e) {}
				    req.send(null);
				} else if (window.ActiveXObject) {
			     	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
			  	if (req) {
				    	req.onreadystatechange=processStateChangecreateNotext;
				        req.open("GET", url, true);
					    req.send();
						
			  	}
				}
				 document.getElementById("isnewposition").style.visibility = "visible";
				 document.jobRequisitionForm.isNewPositionNoText.style.visibility="hidden";  
			}
			
			 
}
function processStateChangecreateNotext() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrgcreatetext(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlcreateNotext(spanElements);
		    //onOtherStateSel();
    	} 

  	}
}
function replaceExistingWithNewHtmlcreateNotext(newTextElements){
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
				  
		    	  if(name="isnewposition")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}
function otherselected(){
	
	
	var educationName = document.jobRequisitionForm.educationName.value;

	if(educationName == "Other" ){
		var url="jobtemplate.do?method=createText";
	    //Do the AJAX call
		if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	  	req.onreadystatechange = processStateChangecreatetext;

		    try {

	  		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
		} else if (window.ActiveXObject) {
	     	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	  	if (req) {
		    	req.onreadystatechange=processStateChangecreatetext;
		        req.open("GET", url, true);
			    req.send();
				
	  	}
		}
		 document.getElementById("mineducation").style.visibility = "visible";
		 document.jobRequisitionForm.otherMinimumLevelOfEducation.style.visibility="hidden";  
		//alert(educationName);
		// var d=document.getElementById("mineducation");
		// d.innerHTML+="Enter other Min level of education : <input type='text' name='minimumEdu'>";
	}else{

		document.getElementById("mineducation").style.visibility = "hidden";
		document.jobRequisitionForm.otherMinimumLevelOfEducation.style.visibility="hidden"; 
	}
	

}
function processStateChangecreatetext() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrgcreatetext(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlcreatetext(spanElements);
		    //onOtherStateSel();
    	} 

  	}
}


function replaceExistingWithNewHtmlcreatetext(newTextElements){
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
				  
		    	  if(name="mineducation")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}




function splitTextIntoSpanOrgcreatetext(textToSplit){
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

function initfocus(){
	//setTimeout ( "document.jobRequisitionForm.jobreqName.focus(); ", 200 );
	
	}

	function divVisible(){
		document.getElementById('testdiv1').style.display = 'inline'; 
	}



function retrieveActivity() {
	
    document.getElementById("loadingactivitylog").style.visibility = "visible";
	var url="jobreq.do?method=getActivity&uuid=<%=jobreqform.getUuid()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeAactivity;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeAactivity;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function processStateChangeAactivity() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml_New(spanElements,"activitylog");
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingactivitylog").style.visibility = "hidden";	
    	
  	}
}

function retrieveOfferedApplicants() {
	
    document.getElementById("loadingofferedapplicants").style.visibility = "visible";
	var url="jobreq.do?method=getOfferedApplicants&uuid=<%=jobreqform.getUuid()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeOfferedApplicants;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeOfferedApplicants;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function processStateChangeOfferedApplicants() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml_New(spanElements,"offeredapplicants");
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingofferedapplicants").style.visibility = "hidden";	
    	
  	}
}

function replaceExistingWithNewHtml_New(newTextElements,emname){
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
				  
		    	  if(name==emname)
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


	</script>





<!--bgcolor="#edf5ff"  for tab colors -->

<body  onload="init()" class="yui-skin-sam">






<%
String tabselected1 = "";
String tabselected2 = "";
String tabselected3 = "";
String tabselected4 = "";
String tabselected5 = "";
String tabselected6 = "";
String tabselected7 = "";
String tabselected8 = "";
String tabselected9 = "";
String tabselected10 = "";


if(jobreqform.getTabselected()==1){
	tabselected1="class=\"selected\"";
}
if(jobreqform.getTabselected()==2){
	tabselected2="class=\"selected\"";
}
if(jobreqform.getTabselected()==3){
	tabselected3="class=\"selected\"";
}
if(jobreqform.getTabselected()==4){
	tabselected4="class=\"selected\"";
}
if(jobreqform.getTabselected()==5){
	tabselected5="class=\"selected\"";
}
if(jobreqform.getTabselected()==6){
	tabselected6="class=\"selected\"";
}
if(jobreqform.getTabselected()==7){
	tabselected7="class=\"selected\"";
}
if(jobreqform.getTabselected()==8){
	tabselected8="class=\"selected\"";
}
if(jobreqform.getTabselected()==9){
	tabselected9="class=\"selected\"";
}
if(jobreqform.getTabselected()==10){
	tabselected10="class=\"selected\"";
}

%>



<!--<div class="exampleIntro">
			
</div>-->

<html:form action="jobreq.do?method=updatejobreq" enctype="multipart/form-data" onsubmit="return savedata()">
<div id="demo" class="yui-navset">
<%
 String viewforselector = (String)request.getAttribute("viewforselector");

%>






<table  width="100%">
<tr  bgcolor="#edf5ff">
<td class="yui-dt"><%=Constant.getResourceStringValue("Requisition.number.1",user1.getLocale())%> :&nbsp;<b><%= jobreqform.getRequisition_number()%> </b>&nbsp;<%=Constant.getResourceStringValue("Requisition.reqcode",user1.getLocale())%> :&nbsp;<b><%= jobreqform.getJobreqcode()%></b>&nbsp; <%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%>:&nbsp;<b><%=jobreqform.getState()%>&nbsp;>&nbsp;<%=jobreqform.getStatus()%></b>&nbsp;<%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%> :&nbsp;<b><%= (jobreqform.getTemplateName()==null)?Constant.getResourceStringValue("admin.NA",user1.getLocale()):jobreqform.getTemplateName()%></b>&nbsp;<%=Constant.getResourceStringValue("Requisition.Remaining_positions.1",user1.getLocale())%> :&nbsp;<b><%= jobreqform.getNumberOfOpeningRemain()%></b>
&nbsp;
<%=Constant.getResourceStringValue("aquisition.applicant.configutaion.ownername",user1.getLocale())%> :
<%if(!StringUtils.isNullOrEmpty(jobreqform.getIsGroup()) && jobreqform.getIsGroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=jobreqform.getCurrentOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=jobreqform.getCurrentOwnerName()%></a> 
<%}else{%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=jobreqform.getCurrentOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=jobreqform.getCurrentOwnerName()%></a>
<%}%>
<br>


</td>
</tr>
<tr bgcolor="#F5FFFA">
<td>
<font color = red ><html:errors /> </font>
           

           <%  if(jobreqform.getStatus() != null && (jobreqform.getStatus().equals(Common.CLOSED) || jobreqform.getStatus().equals(Common.DELETED))){ %>
           <font color="red"><b><%=Constant.getResourceStringValue("Requisition.requisition.is",user1.getLocale())%>  <%=jobreqform.getStatus()%> </b> </font>
		   <% if(!jobreqform.getStatus().equals(Common.DELETED)){%>
		   <%if(isHiringMgrOrRecruiterLoggedIn || isAllReqPermission){%>
		    <a href="#" id="delete"><img src="jsp/images/delete.gif" border="0" alt="delete requisition" title="<%=Constant.getResourceStringValue("Requisition.delete.req",user1.getLocale())%>" height="20"  width="19"/></a>
			<%}%>
		   <%}%>
		   <%}else{%>
           
		   
		   <%  if(jobreqform.getState() != null && jobreqform.getState().equals(Common.ACTIVE)){ %>
		      <% if(isEditReqAfterPublishPermission){%>
			<a  href="#" onClick="savedata()"><img src="jsp/images/save_button.gif" border="0" alt="update requisition" title="update requisition" height="20"  width="19"/></a>	
			  <%}%>
		   <%}%>

          <%  if(jobreqform.getIsapprovalInitiated() == 1){ %>
		
         <% } else if (!jobreqform.getState().equals(Common.ACTIVE)){ %>
           <%if(isHiringMgrOrRecruiterLoggedIn || isAllReqPermission || user1.getUserName().equals(jobreqform.getCreatedBy())){%>
		    <a  href="#" onClick="savedata()"><img src="jsp/images/save_button.gif" border="0" alt="update requisition" title="update requisition" height="20"  width="19"/></a>	  
			<%}%>
			

			<%if(isHiringMgrOrRecruiterLoggedIn || isAllReqPermission){%>
			<a href="javascript:initiateApproval()"><img src="jsp/images/approveandpublish.gif" border="0" alt="initiate approval and publish job" title="initiate approval and publish job" height="19"  width="147"/></a>
			<%}%>

		 <%}%>
		
		 <a  href="#" id="saveAs"><img src="jsp/images/saveas.gif" border="0" alt="save as" title="<%=Constant.getResourceStringValue("hr.button.save_as",user1.getLocale())%>" height="20"  width="19"/></a>
		 <%if(isHiringMgrOrRecruiterLoggedIn || isAllReqPermission){%>
		 <a href="#" id="close"><img src="jsp/images/cancel_button.gif" border="0" alt="close requisition" title="<%=Constant.getResourceStringValue("Requisition.Close.req",user1.getLocale())%>" height="20"  width="19"/></a>
		 <a href="#" id="delete"><img src="jsp/images/delete.gif" border="0" alt="delete requisition" title="<%=Constant.getResourceStringValue("Requisition.delete.req",user1.getLocale())%>" height="20"  width="19"/></a>
		 <%}%>
		 <%}%>
		 
		 <a  href="<%=request.getContextPath()%>/jobreq.do?method=downloadPdf&uuid=<%=jobreqform.getUuid()%>"><img src="jsp/images/PDF_icon.jpg" border="0" alt="Close" title="download pdf" height="20"  width="19"/></a> 
		 <%if(isHiringMgrOrRecruiterLoggedIn || isAllReqPermission){%>
		 <a  href="#" onClick="discard()"><img src="jsp/images/close.gif" border="0" alt="Close" title="<%=Constant.getResourceStringValue("hr.button.close",user1.getLocale())%>" height="20"  width="19"/></a> 
		  <%}%>
		 
		 <% if(isapproverloggedin){%>


<a href="#" id="show"><img src="jsp/images/approve.png" border="0" alt="approve" title="<%=Constant.getResourceStringValue("Requisition.approve",user1.getLocale())%>" height="20"  width="19"/></a>
<a href="#" id="reject"><img src="jsp/images/reject.jpg" border="0" alt="reject" title="<%=Constant.getResourceStringValue("Requisition.reject",user1.getLocale())%>" height="20"  width="19"/></a>
<a href="#" id="reassign"><img src="jsp/images/reassign.jpg" border="0" alt="reassign" title="<%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%>" height="20"  width="19"/></a>




<%}%>





<%
	boolean isallapproved = BOFactory.getJobRequistionBO().isAllApproved(jobreqform.getJobreqId(),"job");
%>
<% if(isallapproved && (jobreqform.getStatus() != null && jobreqform.getStatus().equals(Common.OPEN))){
	if( PermissionBO.isUserHasRequistionTask(user1,jobreqform.getJobreqId(),Common.REQUISTION_PUBLISH_TASK) || isAllReqPermission) {
	%>
<a href="javascript:publishRequistion()"><img src="jsp/images/publish.gif" border="0" alt="publish" title="publish job" height="19"  width="67"/></a>
<%
	}
	}%>


<% 

if(jobreqform.getIsapprovalInitiated() == 1 && jobreqform.getIsrejected() != 1 && (jobreqform.getStatus() != null && jobreqform.getStatus().equals(Common.OPEN))){
	if(isHiringMgrOrRecruiterLoggedIn || isAllReqPermission){

%>
<a href="#" id="recall"><img src="jsp/images/recall.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.recallreq",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.recallreq",user1.getLocale())%>" height="20"  width="19"/></a>
	
<%
}
}
%>


<% 

if(jobreqform.getIsapprovalInitiated() == 1 && jobreqform.getIsrejected() == 1){
	if(isHiringMgrOrRecruiterLoggedIn || isAllReqPermission){
%>
<a  href="#" onClick="resetjobreq()"><img src="jsp/images/reset.gif" border="0" alt="reset requistion" title="<%=Constant.getResourceStringValue("Requisition.resetreq",user1.getLocale())%>" height="20"  width="19"/></a>	
<%
}
}
%>
<a  href="#" onClick="refreshjobreq()"><img src="jsp/images/refresh.png" border="0" alt="reset requistion" title="<%=Constant.getResourceStringValue("Requisition.refreshreq",user1.getLocale())%>" height="20"  width="19"/></a>	

<a  href="#" onClick="addwatchers()"><img src="jsp/images/watchicon.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.watchers",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.watchers",user1.getLocale())%>" height="20"  width="19"/></a>

<a  href="#" onClick="addComments()"><img src="jsp/images/comment.jpg" border="0" alt="<%=Constant.getResourceStringValue("Requisition.comments",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.comments",user1.getLocale())%>" height="20"  width="19"/></a>

<a  href="#" onClick="openExpenseScreen()"><img src="jsp/images/icon_expense.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.expenses",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.expenses",user1.getLocale())%>" height="20"  width="19"/></a>
<% if(saveastrue != null && saveastrue.equals("true")){%>
&nbsp;&nbsp;&nbsp;<font color="green"><b><%=Constant.getResourceStringValue("Requisition.saveas",user1.getLocale())%>  </b> </font>
<%}%>


<br>



<%

if(saverequisition != null && saverequisition.equals("yes")){
%><div class="msg">
<span id='requisitionsave'>
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"> <b><%=Constant.getResourceStringValue("Requisition.savemsg",user1.getLocale())%></b> </font>
</span>
<%
}%>
</div>

<%if(saverequisition != null && saverequisition.equals("no")){
%><div class="msg">
<span id='requisitionsave'>
<font color="white"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><%=Constant.getResourceStringValue("Requisition.not.savemsg",user1.getLocale())%></b> </font>
</span>
<%
}%>

</div>
<%
	List errorList = (List)request.getAttribute(Common.ERROR_LIST);
	if(errorList != null && errorList.size()>0){%>
<div align="center">
	<table border="0" width="100%">
	
<% for(int i=0;i<errorList.size();i++){
	String errorval = (String)errorList.get(i);
%>	
	        <tr>
			<td><font color="red"><li><%=errorval%></li></font></td>
			</tr>
		
<%}%>
		
	</table>
</div>
<%}%>

<span id='progressbartable1'>
<img src="jsp/images/indicator.gif" height="20"  width="40"/>
</span>

</td>
</tr>
</table>

   <ul id="countrytabs" class="shadetabs">
   <li><a href="#" rel="tab1" <%=tabselected1%>><%=Constant.getResourceStringValue("Requisition.reqdata",user1.getLocale())%></a></li>
      <%if(jobdetailsothersize > 0){ %>
        <li><a href="#" rel="tab2" <%=tabselected2%>><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></a></li>
      <%} %>
        <li><a href="#" rel="tab3" <%=tabselected3%>><%=Constant.getResourceStringValue("Requisition.evaluation",user1.getLocale())%></a></li>
		<li><a href="#" rel="tab4" <%=tabselected4%>><%=Constant.getResourceStringValue("Requisition.approvals",user1.getLocale())%></a></li>	
		<%  if( (jobreqform.getStatus() != null && (jobreqform.getStatus().equals(Common.OPEN))) && (jobreqform.getState() != null && (jobreqform.getState().equals(Common.ACTIVE)))){ %>
		<li><a href="#" rel="tab5" <%=tabselected5%>><%=Constant.getResourceStringValue("Requisition.offered.applicants",user1.getLocale())%></a></li>
		 <%}%>
		<li><a href="#" rel="tab6" <%=tabselected6%>><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></a></li>
		<!--<li><a href="#" rel="tab7" <%=tabselected7%>><%=Constant.getResourceStringValue("Custom.variables",user1.getLocale())%></a></li>-->
		<%  if( (jobreqform.getStatus() != null && (jobreqform.getStatus().equals(Common.OPEN))) && (jobreqform.getState() != null && (jobreqform.getState().equals(Common.ACTIVE)))){ %>
		<li><a href="#" rel="tab8" <%=tabselected8%>><%=Constant.getResourceStringValue("hr.requistion.publish.info",user1.getLocale())%></a></li>
        <%}%>
		<%if(!StringUtils.isNullOrEmpty(user1.getPackagetaken()) && Constant.isPackageContainFunction(user1.getPackagetaken(),"filter")){%>
		<li><a href="#" rel="tab9" <%=tabselected9%>><%=Constant.getResourceStringValue("admin.requistion.filters",user1.getLocale())%></a></li>
		<%}%>
		<li><a href="#" rel="tab10" <%=tabselected9%>><%=Constant.getResourceStringValue("hr.req.activity",user1.getLocale())%></a></li>
		
    </ul>  
<div style="border:1px solid gray; width:90%; margin-bottom: 1em; padding: 10px">
 
        <div id="tab1" class="tabcontent">
			<!-- tab 1 start -->
		<div align="center">
		<div class="container">

<table border="0" width="100%">
<tr>
<td>
<span class="textboxlabel" id="floehelp" STYLE="font-size: smaller;">
<%=Constant.getResourceStringValue("Requisition.flow.help",user1.getLocale())%>
</span>
</td>
<td>
<% if(PermissionChecker.isPermissionApplied(Common.ADMIN_CONFIGURATIONS, user1)){%>
<a href="#" onClick="openConfiguration()" title="<%=Constant.getResourceStringValue("screen.setting.help",user1.getLocale())%>"><img src="jsp/images/setting.png" width="25" height="25" border="0"></a>
<%}%>
</td>
</tr>

</table>
	<table border="0" width="100%">


		<input type="hidden" name="isapprovalinitiated" value="<%=jobreqform.getIsapprovalInitiated()%>">


<%

 for(int i=0;i<JOB_DETAILS_List.size();i++){
	ScreenFields scf = (ScreenFields)JOB_DETAILS_List.get(i);
     
%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.IS_INTERNAL_JOB.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.IS_INTERNAL_JOB.toString())){%>
		<tr>
			<td width="30%">
			<%=Constant.getResourceStringValue("Requisition.isinternaljaob",user1.getLocale())%>
		<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.IS_INTERNAL_JOB.toString())){%>
			<font color="red">*</font>
			<%}%>	
			</td>
			<td width="70%"><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isInternal" maxlength="10" id="1" <%=(jobreqform.getInternal()!= null && jobreqform.getInternal().equals("Y"))? "Checked=true" : "" %> value="Y" styleClass="titleHintBox"  title="<%=Constant.getResourceStringValue("Is internal is for internal use",user1.getLocale())%>">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isInternal" id="0" <%=(jobreqform.getInternal()!= null && jobreqform.getInternal().equals("N"))? "Checked=true" : "" %> value="N"></td>
		</tr>
<%}%>
<%}%> 

<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.REQUISITION_NAME.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.REQUISITION_NAME.toString())){%>
		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%>
			<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.REQUISITION_NAME.toString())){%>
			<font color="red">*</font>
			<%}%>	
			</td>
			<td width="70%"><html:text property="jobreqName" size="70" maxlength="500" styleClass="text titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.jobreqname.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%> 
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_TITLE.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_TITLE.toString())){%>
		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%>
			<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_TITLE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td width="70%">
			<html:select property="designationId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.jobtitle.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="designationList" id="designationList" />

            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>	
			<input type="button" onClick="createJobTitle()" title="New" value="New" class="button"/>
			</td>
		</tr>
<%}%>
<%}%> 
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_POSITION_NAME.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_POSITION_NAME.toString())){%>
		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.jobpossitionname",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_POSITION_NAME.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td width="70%"><html:text property="jobPosition" size="70" maxlength="500" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.jobpossitionname.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%> 
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.NO_OF_OPENING.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.NO_OF_OPENING.toString())){%>
		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.Noofopenings",user1.getLocale())%>
		<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.NO_OF_OPENING.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td width="70%"><html:text property="numberOfOpening" size="10" maxlength="2" styleClass="textdynamic titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Noofopenings.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%> 
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.IS_NEW_POSITION.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.IS_NEW_POSITION.toString())){%>
		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.IsNewPosition",user1.getLocale())%>
			<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.IS_NEW_POSITION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td width="70%">
			<%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isNewPosition" maxlength="10" id="1" <%=(jobreqform.getIsnewPositions()!= null && jobreqform.getIsnewPositions().equals("Y"))? "Checked=true" : "" %> value="Y" onchange="IsNewPositionYesselected();" title="<%=Constant.getResourceStringValue("Requisition.IsNewPosition.help",user1.getLocale())%>" class="titleHintBox">&nbsp;&nbsp;
			<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isNewPosition" id="0" <%=(jobreqform.getIsnewPositions()!= null && jobreqform.getIsnewPositions().equals("N"))? "Checked=true" : "" %> value="N" onchange="IsNewPositionNoselected();" title="<%=Constant.getResourceStringValue("Requisition.IsNewPosition.help",user1.getLocale())%>" class="titleHintBox">
			&nbsp;&nbsp;<span id="isnewposition">
				  
				  <% /* this fix is for a strange problem encountered only on certain tomcats and not on all  */%>
				 <% /* this breaks but not on all tomcats:: <html:text size="30" property='isnewpositionno' maxlength="200" value="<%=(jobreqform.getIsnewpositionno()==null)?"":jobreqform.getIsnewpositionno()"/> */ %>
				 <% String isNewPos = ""; %>

				<%if(!StringUtils.isNullOrEmpty(jobreqform.getIsnewPositions())){%>
				<%if( jobreqform.getIsnewPositions().equals("N") ){ %>
					 <% if((saverequisition != null && saverequisition.equals("yes"))||(editjobrequisition != null && editjobrequisition.equals("yes"))|| (saveastrue != null && saveastrue.equals("true"))){%>
					 	 <% if(jobreqform.getIsnewpositionno() != null){%>
							<html:text size="30" property='isnewpositionno' maxlength="200" value="<%=(jobreqform.getIsnewpositionno()==null)?isNewPos:jobreqform.getIsnewpositionno()%>"/>
						 <%} %>
					 <%} %>
				 <%} %>
			 <%} %> 
			</span>
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.TARGET_FINISH_DATE.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.TARGET_FINISH_DATE.toString())){%>
<tr>
<td width="30%">
			<%=Constant.getResourceStringValue("Requisition.Target.finish.date",user1.getLocale())%>
				<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.TARGET_FINISH_DATE.toString())){%>
			<font color="red">*</font>
			<%}%>
</td>
		
<td width="70%">
			
			<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();
</SCRIPT>

<html:text property="targetfinishdate" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Target.finish.date.help",user1.getLocale())%>" readonly="true" value="<%=jobreqform.getTargetfinishdate()%>"/>
<A HREF="#" onClick="divVisible();cal1xx.select(document.jobRequisitionForm.targetfinishdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="select date" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" /></A>
<DIV ID="testdiv1" class="datepatern"></DIV>				
</td>
</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.CATEGORY.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.CATEGORY.toString())){%>
<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.category",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.CATEGORY.toString())){%>
			<font color="red">*</font>
			<%}%>
            </td>
			<td width="70%">
			
			<html:select property="categoryId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.category.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="categoryList" id="categoryList" />

            <html:options collection="categoryList" property="catId"  labelProperty="catName"/>
			</html:select>
			
			
			  
			</td>
			</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_CATEGORY.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_CATEGORY.toString())){%>
<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.job.category",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_CATEGORY.toString())){%>
			<font color="red">*</font>
			<%}%>
            </td>
			<td width="70%">
			
			<html:select property="jobCategoryId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.job.sector.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="jobCategoryList" id="jobCategoryList" />

            <html:options collection="jobCategoryList" property="jobCategoryId"  labelProperty="jobCategoryName"/>
			</html:select>
			
			
			  
			</td>
			</tr>
<%}%>
<%}%>		
<!--  code for list box -->
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.ORGANIZATION.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.ORGANIZATION.toString())){%>
<tr>
			<td width="30%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.ORGANIZATION.toString())){%>
			<font color="red">*</font>
			<%}%>
            </td>
			<td width="70%">
			<html:select property="parentOrgId" onchange="retrieveURLOrg('jobreq.do?method=loadDeptlistWithProjectcode');" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.organization.help",user1.getLocale())%>">
			<bean:define name="jobRequisitionForm" property="organizationList" id="organizationList" />
            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			
			&nbsp;
			<span class="textboxlabel" id="loadingd" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%> ......</span>

			
			  
			</td>
			</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.DEPARTMENT.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.DEPARTMENT.toString())){%>
	<tr>
			<td width="30%"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.DEPARTMENT.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td width="70%">
			<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('jobreq.do?method=loadProjectCode');" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Deparment.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			<span class="textboxlabel" id="loadingp" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.ProjectCode.load",user1.getLocale())%>......</span>
			
			  
			</td>
			</tr>

<%}%>
<%}%>
		
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.PROJECT_CODE.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.PROJECT_CODE.toString())){%>
<tr>
			<td width="30%"><%=Constant.getResourceStringValue("admin.ProjectCode.PorjectCode",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.PROJECT_CODE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td width="70%">
			<span id="projectcodes">
			<html:select property="projectcodeId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.ProjectCode.help",user1.getLocale())%>">
			
			<bean:define name="jobRequisitionForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
			
			</span>
			  
			</td>
			</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_LOCATION.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_LOCATION.toString())){%>
<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.screen.JOB_LOCATION",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_LOCATION.toString())){%>
			<font color="red">*</font>
			<%}%>
            </td>
			<td width="70%">
			
			<html:select property="locationId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.job.location.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="locationList" id="locationList" />

            <html:options collection="locationList" property="locationId"  labelProperty="locationName"/>
			</html:select>
			
			
			  
			</td>
			</tr>
<%}%>
<%}%>		
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.PRIMARY_SKILL_REQUIRED.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.PRIMARY_SKILL_REQUIRED.toString())){%>
         <tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.primary.skill.required",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.PRIMARY_SKILL_REQUIRED.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td width="70%">

			<html:select  property="primarySkill" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Primary.skill.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="primarySkillList" id="primarySkillList" />
            <html:options collection="primarySkillList" property="technialSkillName"  labelProperty="technialSkillName"/>
			</html:select>
  
			</td>
			</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.HIRING_MANAGER.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.HIRING_MANAGER.toString())){%>

			<tr>
					<td width="30%"><%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%>
              <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.HIRING_MANAGER.toString())){%>
			<font color="red">*</font>
			<%}%>
              </td>

		
				<td width="70%">
				<input type="hidden" name="highiringmanagernamehidden" >
                 <input type="text" id="hiringMgrName" class="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.hiringmgr.help",user1.getLocale())%>" name="hiringMgrName" value="<%=jobreqform.getHiringMgrName()%>" autocomplete="off" onblur="validateUserhm()">
				
				<span id="assignedto"></span>
<a href="#" onClick="opensearchassignedtohiring()"><img src="jsp/images/selector.gif" border="0"/></a> <br>

<%
String hiringmghid = String.valueOf(jobreqform.getHiringMgrId());
%>
<html:hidden  property="hiringMgrId" value="<%=hiringmghid%>"/>

				</td>
			</tr>	
			<tr>
				<td width="30%"></td>
				<td width="70%">
					<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
				</td>
			</tr>
<%}%>
<%}%>	
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_GRADE.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_GRADE.toString())){%>
		 <%

		
		String jobgradevalue ="<span id=\"jobgradeId\">";
        if(jobreqform.getJobgradeId() != 0){

         String jobgradetempurl = "<a href='#' onClick=window.open("+"'"+"jobgrade.do?method=jobGradeDetails&readPreview=2&id="+jobreqform.getJobgradeId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=180"+"'"+")>"+jobreqform.getJobgradeName()+"</a>";
         jobgradevalue = "<span id=\"jobgradeId\">"+jobgradetempurl+"</span>";
		}

	  %>


		<tr>
				<td width="30%"><%=Constant.getResourceStringValue("Requisition.jobgrade",user1.getLocale())%>
              <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_GRADE.toString())){%>
			<font color="red">*</font>
			<%}%>
               </td>

				<td width="70%">
				<html:select  property="jobgradeId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.jobgrade.help",user1.getLocale())%>">
				<option value=""></option>
			      <bean:define name="jobRequisitionForm" property="jobgradeList" id="jobgradeList" />
                  <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			    </html:select>
			               <a href="#"  onClick="opensearchjobgrade()"><img src="jsp/images/selector.gif" border="0"/></a>
			   </td>
</tr>

<%}%>
<%}%>	

<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.SALARY_PLAN.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.SALARY_PLAN.toString())){%>
	<tr>
				<td width="30%"><%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.SALARY_PLAN.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>

				<td width="70%">
				

            <span id="salaryplans">
			<html:select  property="salaryplanId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.salaryplan.help",user1.getLocale())%>">
				<option value=""></option>		
			<bean:define name="jobRequisitionForm" property="salaryplanList" id="salaryplanList" />
            <html:options collection="salaryplanList" property="salaryplanId"  labelProperty="salaryPlanName"/>
			</html:select>
			</span>
			<a href="#" onClick="opensearchsalaryplan()"><img src="jsp/images/selector.gif" border="0"/></a>
			<span class="textboxlabel" id="loadinsalaryplan" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.salaryplan.load",user1.getLocale())%>......</span>

			
             </td>

				
</tr>
<%}%>
<%}%>		
<% 
        System.out.println("jobreqform.getBudgetcodeId()"+jobreqform.getBudgetcodeId());
		String budgetcodevalue ="<span id=\"budgetcodeId\">";
        if(jobreqform.getBudgetcodeId() != 0){

   String budgetcodetempurl = "<a href='#' onClick=window.open("+"'"+"budgetcode.do?method=editbudgetCode&readPreview=2&id="+jobreqform.getBudgetcodeId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=260"+"'"+")>"+jobreqform.getBudgetcodeName()+"</a>";
 budgetcodevalue = "<span id=\"budgetcodeId\">"+budgetcodetempurl+"</span>";
		}

		%>


<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.BUDGET_CODE.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.BUDGET_CODE.toString())){%>
		<tr>
				<td width="30%"><%=Constant.getResourceStringValue("Requisition.budgetcode",user1.getLocale())%>
			<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.BUDGET_CODE.toString())){%>
			<font color="red">*</font>
			<%}%>
				</td>

				<td width="70%">
                 <span id="budgetcodescodes">
				<html:select  property="budgetcodeId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.budgetcode.help",user1.getLocale())%>">
				<option value=""></option>					
			<bean:define name="jobRequisitionForm" property="budgetcodeList" id="budgetcodeList" />
            <html:options collection="budgetcodeList" property="budgetId"  labelProperty="budgetCode"/>
			</html:select>
			</span>
				<a href="#" onClick="opensearchbudgetcode()"><img src="jsp/images/selector.gif" border="0"/></a>
</tr>
<%}%>
<%}%>
	

		<%
		String recruitervalue ="<span id=\"recruiterId\"></span>";
        

		%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.RECRUITER.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.RECRUITER.toString())){%>
		<tr>
				<td width="30%"><%=Constant.getResourceStringValue("Requisition.Recruiter",user1.getLocale())%>
		<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.RECRUITER.toString())){%>
			<font color="red">*</font>
			<%}%>
					</td>
				<td width="70%"><span id="recruiterId"></span>
                 <input type="hidden" name="recruiternamehidden">
				 <input type="text"  id="recruiterName" class="text titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.Recruiter.help",user1.getLocale())%>" name="recruiterName" autocomplete="off" value="<%=(jobreqform.getRecruiterName() == null) ? "" :jobreqform.getRecruiterName() %>" onblur="validateUserRecruiter()"/>

				<a href="#" onClick="opensearchrecruiter()"><img src="jsp/images/selector.gif" border="0"/></a>
				
				<html:hidden  property="recruiterId"/>
				<input type="hidden" name="isgrouprecruiter" value="<%=jobreqform.getIsgrouprecruiter()%>" />
				<!--<html:hidden  property="recruiterName"/> -->

				<%if(! StringUtils.isNullOrEmpty(jobreqform.getState()) &&! jobreqform.getState().equals(Common.DRAFT)){%>
				<input type="button" onClick="saveRecruiterdata()" title="<%=Constant.getResourceStringValue("Requisition.save.recruiter",user1.getLocale())%>" value="<%=Constant.getResourceStringValue("Requisition.save.recruiter",user1.getLocale())%>" class="button"/>
	<span class="textboxlabel" id="loading3" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("Requisition.Saving.recruiter",user1.getLocale())%>......</span>
  				<%}
  				%>

				</td>
		</tr>

		<tr>
		<td width="30%"></td>
		<td width="70%">  <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
				<span id="recruitersave"></span>
		</td>
		</tr>

<%}%>
<%}%>

		
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.NOTES.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.NOTES.toString())){%>		
		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.notes",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.NOTES.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td> 
			<td width="70%"><html:textarea property="jobreqDesc" cols="80" rows="5" styleClass="textarea titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.notes.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%>
	
<%
//JOB_DETAILS_List end of loop
}%>
</table>

</div>
</div>
<%@ include file="customvariableReq.jsp" %>
	<!-- tab1 end -->	
</div>
        <div id="tab2" class="tabcontent">
		
			<!-- tab 2 start -->	
				
		<div align="center">
		<div class="container">
	<table border="0" width="100%">
<%

 for(int i=0;i<JOB_DETAILS_OTHERS_List.size();i++){
	ScreenFields scf = (ScreenFields)JOB_DETAILS_OTHERS_List.get(i);
     
%>

	<tr>
			<td></td>
			<td></td>
		</tr>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.INTRODUCTION.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.INTRODUCTION.toString())){%>	
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.Introduction",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.INTRODUCTION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:textarea property="jobInstructions" cols="80" rows="5" styleClass="textarea titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Introduction.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_DETAILS.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_DETAILS.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_DETAILS.toString())){%>
			<font color="red">*</font>
			<%}%>
            </td>
			<td><html:textarea property="jobDetails" cols="80" rows="5" styleClass="textarea titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.jobreqdetails.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_ROLES.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_ROLES.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.JobRoles",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_ROLES.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:textarea property="jobRoles" cols="80" rows="5" styleClass="textarea titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.JobRoles.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.DEFAULT_STANDARD_WORKING_HOURS.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.DEFAULT_STANDARD_WORKING_HOURS.toString())){%>	
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DefaultStandardWorkingHour",user1.getLocale())%>
			<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.DEFAULT_STANDARD_WORKING_HOURS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>

			<html:select  property="stdworkinghoursunitName" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.DefaultStandardWorkingHour.help",user1.getLocale())%>">
			<bean:define name="jobRequisitionForm" property="stdworkinghoursunitList" id="stdworkinghoursunitList" />
            <html:options collection="stdworkinghoursunitList" property="key"  labelProperty="value"/>
			</html:select>
			
			<html:text property="defaultStandardHours" size="4" maxlength="200" styleClass="text"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.DURATION_IN_MONTHS.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.DURATION_IN_MONTHS.toString())){%>	
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DurationinMonths",user1.getLocale())%>
			<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.DURATION_IN_MONTHS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text size="4" property="durationinmonths" maxlength="3" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.DurationinMonths.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.MIN_EXPERIENCE_REQUIRED.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_EXPERIENCE_REQUIRED.toString())){%>	
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minexp",user1.getLocale())%>
           	<% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.DURATION_IN_MONTHS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text size="4" property="minyearsofExpRequired" maxlength="2" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.minexp.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.MAX_EXPERIENCE_REQUIRED.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.MAX_EXPERIENCE_REQUIRED.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.maxexp",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.MAX_EXPERIENCE_REQUIRED.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text size="4" property="maxyearsofExpRequired" maxlength="2" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.maxexp.help",user1.getLocale())%>"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.MIN_LEVEL_OF_EDUCATION.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_LEVEL_OF_EDUCATION.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minedu",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.MIN_LEVEL_OF_EDUCATION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<!-- <td><html:text size="50" property="minimumLevelOfEducation" maxlength="200"/></td>-->
			
			<td><html:select  property="educationName" onchange="otherselected();" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.minedu.help",user1.getLocale())%>">
			    <option value=""></option>
				<bean:define name="jobRequisitionForm" property="educationNamesList" id="educationNamesList" />
	            <html:options collection="educationNamesList" property="key"  labelProperty="value"/>	
			</html:select>&nbsp;<span id="mineducation">
						<%if(jobreqform.getEducationName() != null){ %>
				<%if(jobreqform.getEducationName().equals("Other") ){ %>
					 <% if((saverequisition != null && saverequisition.equals("yes"))||(editjobrequisition != null && editjobrequisition.equals("yes"))|| (saveastrue != null && saveastrue.equals("true"))){%>
					 	 <% if(jobreqform.getOtherMinimumLevelOfEducation() != null){%>
					 		<html:text  size="30" property='otherMinimumLevelOfEducation' maxlength="200" value="<%=jobreqform.getOtherMinimumLevelOfEducation()%>"/> 
		
						 <%} %>
					 <%} %>
				 <%} %>
			 <%} %> 
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.OTHER_EXP_REQUIRED.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.OTHER_EXP_REQUIRED.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.OTHER_EXP_REQUIRED.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text size="46" property="otherExperience" maxlength="400" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.otherExp.help",user1.getLocale())%>"/></td>
		</tr>

<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.JOB_TYPE.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_TYPE.toString())){%>
<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.JOB_TYPE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select name="jobRequisitionForm"  property="jobtypeId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.jobtype.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="jobtypeList" id="jobtypeList" />

            <html:options collection="jobtypeList" property="jobTypeId"  labelProperty="jobTypeName"/>
			</html:select>
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.WORK_SHIFT.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.WORK_SHIFT.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.WORK_SHIFT.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select name="jobRequisitionForm"  property="workshiftId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.workshift.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="workshiftList" id="workshiftList" />

            <html:options collection="workshiftList" property="shiftId"  labelProperty="shiftName"/>
			</html:select>
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.FLSA_STATUS.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.FLSA_STATUS.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.FLSA.Status",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.FLSA_STATUS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select name="jobRequisitionForm"  property="flsastatusId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.FLSA.Status.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="flsastatusList" id="flsastatusList" />

            <html:options collection="flsastatusList" property="flsaId"  labelProperty="flsaName"/>
			</html:select>
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString())){%>
<% if(screenFields.contains(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString())){%>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.compfreq",user1.getLocale())%>
            <% if(mandatoryFields.contains(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select name="jobRequisitionForm"  property="compfrequencyId" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.compfreq.help",user1.getLocale())%>">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="compfrequencyList" id="compfrequencyList" />

            <html:options collection="compfrequencyList" property="compFrequencyId"  labelProperty="compFrequencyName"/>
			</html:select>
			</td>
		</tr>
<%}%>
<%}%>
<%
	// end of JOB_DETAILS_OTHERS_List loop
}
%>
			
	</table>
	</div>
	</div>
		<!-- tab 2 end -->
		</div>
		<div id="tab3" class="tabcontent">
		
<!-- tab 3 start -->

<jsp:include page="evaluationTab.jsp">	
   <jsp:param name="reqId"	value="<%=jobreqform.getJobreqId()%>" />
   <jsp:param name="isapprovalinitiated"	value="<%=jobreqform.getIsapprovalInitiated()%>" />
   <jsp:param name="state"	value="<%=jobreqform.getState()%>" />
</jsp:include>



<!-- tab 3 end -->		
		</div>
        <div id="tab4" class="tabcontent">



		<!-- tab 4 start -->	


<BR>
		<b><%=Constant.getResourceStringValue("Requisition.Approvers",user1.getLocale())%></b>
		<br>		<br>

		<div class="div">
<table border="0" width="600px">



	
    <tr>
	<td>
	<input type="hidden" name="approverNamehidden">
	<input type="text" property="approverName"  id="approverName" class="text titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.Approvers.help",user1.getLocale())%>" maxlength="500" autocomplete="off" onblur="validateUser()"/>
	<input type="hidden" name="approverId" />
	<input type="hidden" name="isgroup" />
	<a href="#" onClick="opensearchapprover('approverreq')"><img src="jsp/images/selector.gif" border="0"/></a><br>
	<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>

    </td>

	<td><a class="button" href="#" onClick="addApprovers();return false"><%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%></a>
	<span class="textboxlabel" id="loading6" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>
	</div>
	<br>
	<div class="div">
   <span id="approverdetails">
    <table border="0" width="800px">
    <tr>
			<td colspan="4">
				<%
				if(approveralreadyadded != null && approveralreadyadded.equals("yes")){
		%>
			<font color="red"> <b><%=Constant.getResourceStringValue("Requisition.approver.alreadyaded",user1.getLocale())%></b> </font>
		<%}%>
			</td>
	</tr>
<%
//List approverList = jobreqform.getApproversList();

%>
<% if(approverList != null && approverList.size()>0){%>
    <tr>
	
	<td width="50px"><b><%=Constant.getResourceStringValue("Requisition.Level",user1.getLocale())%></b></td>
	<td width="300px"><b><%=Constant.getResourceStringValue("Requisition.Users",user1.getLocale())%></b></td>
	<td width="100px"><b><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Status",user1.getLocale())%></b></td>
	<td width="200px"><b><%=Constant.getResourceStringValue("hr.requistion.approver.system.defind",user1.getLocale())%></b></td>
	<td width="50px"></td>
	
	</tr>



<%	
	int k=1;
for(int i=0;i<approverList.size();i++){
	JobTemplateApprovers japp = (JobTemplateApprovers)approverList.get(i);
%>
<tr>

		<%
		String approverurl ="";
        if(japp.getJbTmplApproverId() != 0){


		if(!StringUtils.isNullOrEmpty(japp.getIsGroup()) && japp.getIsGroup().equals("Y")){
			approverurl = "<img src='jsp/images/User-Group-icon.png'><a href='#' onClick=window.open("+"'"+"usergroup.do?method=editusergroup&readPreview=2&usergroupid="+japp.getUserId()+"'"+","+ "'"+"userperview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=430"+"'"+")>"+japp.getApproverName()+"</a>";
		}else{
			approverurl = "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+japp.getUserId()+"'"+","+ "'"+"userperview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=430"+"'"+")>"+japp.getApproverName()+"</a>";
		}

		}

		%>

<td width="50px"><%=k%></td>
<td width="300px" wrap><%=approverurl%></td>
<td width="100px" wrap>
<%
	String approvalstatusmsg = "";
if(japp.getApproved() != null && japp.getApproved().equals("Y")){
	approvalstatusmsg = Constant.getResourceStringValue("Requisition.approved",user1.getLocale());
}else if(japp.getApproved() != null && japp.getApproved().equals("R")){
	
		approvalstatusmsg = Constant.getResourceStringValue("Requisition.rejected",user1.getLocale());
}else{
	approvalstatusmsg = Constant.getResourceStringValue("Requisition.Not_Approved",user1.getLocale());
}
%>
<%=approvalstatusmsg%></td>
<td width="200px">
<%=(japp.getIsFromSystemRule()==null)?"N":japp.getIsFromSystemRule()%>
</td>
<td width="50px">
<% if(japp.getIsFromSystemRule() != null && japp.getIsFromSystemRule().equals("Y")){
}else{	
%>
<a href="#" onClick="deleteApprovers('<%=japp.getJbTmplApproverId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete approvers" title="<%=Constant.getResourceStringValue("Requisition.Delete_Approvers",user1.getLocale())%>" height="20"  width="19"/></a>
<%}%>
</td>
</tr>
<%
	k++;
}
}else{%>
<!--Competency not added.-->
<%}%>

    </table>
	</span>
</div>

<!-- tab 4 end -->	
</div>
<%  if( (jobreqform.getStatus() != null && (jobreqform.getStatus().equals(Common.OPEN))) && (jobreqform.getState() != null && (jobreqform.getState().equals(Common.ACTIVE)))){ %>
<div id="tab5" class="tabcontent">
<!-- tab 5 start -->


<script>
setTimeout("retrieveOfferedApplicants()",300);
</script>

<span class="textboxlabel" id="loadingofferedapplicants" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%> ......</span>
<span id="offeredapplicants">
</span>



<!-- tab 5 end -->	
</div>
<%}%>
<div id="tab6" class="tabcontent">
<!-- tab 6 start -->
<%@ include file="attchmentsReq.jsp" %>

<!-- tab 6 end -->	
</div>



<div id="tab7" class="tabcontent">
<!-- tab 7 start -->

<!-- tab 7 end -->	
</div>

<%  if( (jobreqform.getStatus() != null && (jobreqform.getStatus().equals(Common.OPEN))) && (jobreqform.getState() != null && (jobreqform.getState().equals(Common.ACTIVE)))){ %>
<div id="tab8" class="tabcontent">
<!-- tab 8 start -->
<%@ include file="publishInfoDetailsReq.jsp" %>


<!-- tab 8 end -->	
</div>
<%}%>


<div id="tab9" class="tabcontent">
<!-- tab 9 start -->
<%@ include file="filtersReq.jsp" %>


<!-- tab 9 end -->	
</div>

<div id="tab10" class="tabcontent">
<!-- tab 10 start -->



<iframe id="evalgraph" frameborder="0" height="800px" width="96%" src="jobreq.do?method=getActivity&uuid=<%=jobreqform.getUuid()%>"></iframe>

<!-- tab 10 end -->	
</div>

		</div>
    </div>



</html:form>

<script type="text/javascript">

var countries=new ddtabcontent("countrytabs")
countries.setpersist(false)
countries.setselectedClassTarget("link") //"link" or "linkparent"
countries.init()


</script>


<script language="javascript">
function addElement() {
	var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	if(isapprovalinitiated1 == 1){
		alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%>");
		return false;
	}
  var ni = document.getElementById('myDiv');
  var numi = document.getElementById('theValue');
  var num = (document.getElementById('theValue').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  var competency1 = '<input type="text" size = "50" maxlength= "100" name="competency_'+num+'"/>';
  var mandatory1 = '<input type="checkbox"   checked name="mandatory_'+num+'"/>';
  var spacevalue = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    var importance1 = '<input type="text" size = "5" maxlength= "2" name="importance_'+num+'"/>';

  newdiv.innerHTML = ''+competency1+spacevalue+''+mandatory1+spacevalue+''+importance1+  '  <a href=\'#\' onclick=\'removeElement('+divIdName+')\'><img src=\"jsp/images/delete.gif\" border=\"0\"/></a>';

  ni.appendChild(newdiv);
  } else {
	  alert("<%=Constant.getResourceStringValue("Requisition.alert3",user1.getLocale())%>");
  }
}

function addElement1() {
	var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	if(isapprovalinitiated1 == 1){
		alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%>");
		return false;
	}
  var ni = document.getElementById('myDiv1');
  var numi = document.getElementById('theValue1');
  var num = (document.getElementById('theValue1').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my1'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  var competency1 = '<input type="text" size = "50" maxlength= "100"  name="accomplishment_'+num+'"/>';
  var mandatory1 = '<input type="checkbox"   checked name="accmandatory_'+num+'"/>';
  var spacevalue = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    var importance1 = '<input type="text" size = "5" maxlength= "2" name="accimportance_'+num+'"/>';

  newdiv.innerHTML = ''+competency1+spacevalue+''+mandatory1+spacevalue+''+importance1+  '  <a href=\'#\' onclick=\'removeElement1('+divIdName+')\'><img src=\"jsp/images/delete.gif\" border=\"0\"/></a>';

  ni.appendChild(newdiv);
  } else {
	  alert("<%=Constant.getResourceStringValue("Requisition.alert3",user1.getLocale())%>");
  }
}

function addElement2() {
var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	if(isapprovalinitiated1 == 1){
		alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%>");
		return false;
	}
  var ni = document.getElementById('myDiv2');
  var numi = document.getElementById('theValue2');
  var num = (document.getElementById('theValue2').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my2'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  var spanstart = '<span id="approverspan_'+num+'">';
  var username1 = '<input type="text" readonly size = "50" maxlength= "100" value="" name="username_'+num+'"/>';
  var userid1 = '<input type="hidden"    name="userid_'+num+'"/>';
   var levelid = '<input type="hidden"    name="level_'+num+'"/>';
   var leveltext = 'Level-'+num+'&nbsp;&nbsp';
    var spanend = '</span>'; 
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+levelid+''+username1+''+userid1+ ''+spanend+''+'<a href=\'#\' onclick=\'opensearchapprover('+num+')\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>'+''+'  <a href=\'#\' onclick=\'removeElement2('+divIdName+')\'><img src=\"jsp/images/delete.gif\" border=\"0\"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("<%=Constant.getResourceStringValue("Requisition.alert3",user1.getLocale())%>");
  }
}

function removeElement(divNum) {
	var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	if(isapprovalinitiated1 == 1){
		alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%>");
		return false;
	}
var d = document.getElementById('myDiv');
d.removeChild(divNum);
}
function removeElement1(divNum) {
	var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	if(isapprovalinitiated1 == 1){
		alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%>");
		return false;
	}
var d = document.getElementById('myDiv1');
d.removeChild(divNum);
}
function removeElement2(divNum) {
	var isapprovalinitiated1 =  document.jobRequisitionForm.isapprovalinitiated.value;
	if(isapprovalinitiated1 == 1){
		alert("<%=Constant.getResourceStringValue("Requisition.alert1",user1.getLocale())%> "+"<%=jobreqform.getState()%>"+" <%=Constant.getResourceStringValue("Requisition.alert2",user1.getLocale())%>");
		return false;
	}
var d = document.getElementById('myDiv2');
d.removeChild(divNum);
}

</script>

<script>
YAHOO.namespace("example.container");

YAHOO.util.Event.onDOMReady(function () {
	
	// Define various event handlers for Dialog
	var handleSubmit = function() {
		var inappurl = "<%=approvalurl%>";
		if (inappurl == "") {
			alert("<%=Constant.getResourceStringValue("Requisition.add.approver.alert",user1.getLocale())%>");
			return false;
		}
		//this.submit();
		var comm = document.initiateapp.comment.value;
	   if (comm.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
		}
		document.getElementById('progressbartable1').style.display = 'inline'; 
		document.initiateapp.action = "<%=approvalurl%>";
   document.initiateapp.submit();
	};


var handleSubmit2 = function() {
		
		//this.submit();
		var comm = document.initiateapp2.comment.value;
	   if (comm.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
		}
		document.getElementById('progressbartable1').style.display = 'inline'; 
		document.initiateapp2.action = "<%=rejecturl%>";
   document.initiateapp2.submit();
	};

	var handleSubmit3 = function() {
		var alertstr = "";
		var showalert=false; 
		var assineduser = document.initiateapp3.reassignedto.value;
		if (assineduser == "") {
			alertstr = alertstr +"<%=Constant.getResourceStringValue("Requisition.select.user.alert",user1.getLocale())%><br>";
			showalert = true;
		}
		//this.submit();
		var comm = document.initiateapp3.comment.value;
	   if (comm.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
		}
		 if (showalert){
		     	alert(alertstr);
		        return false;
		  }
		document.getElementById('progressbartable1').style.display = 'inline'; 
		document.initiateapp3.action = "<%=reassignurl%>";
   document.initiateapp3.submit();
	};

var handleSubmit4 = function() {
		
		//this.submit();
		var comm = document.initiateapp4.comment.value;
		if (comm == "") {
			//alert("<%=Constant.getResourceStringValue("Requisition.enter.comment.alert",user1.getLocale())%>");
			//return false;
		}
		document.getElementById('progressbartable1').style.display = 'inline'; 
		document.initiateapp4.action = "<%=publishurl%>";
   document.initiateapp4.submit();
	};

	var handleSubmit5 = function() {
		
		//this.submit();
		var comm = document.initiateapp5.comment.value;
	   if (comm.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
		}
		document.getElementById('progressbartable1').style.display = 'inline'; 
		document.initiateapp5.action = "<%=closeurl%>";
   document.initiateapp5.submit();
	};

	var handleSubmit6 = function() {
		
		//this.submit();
		var comm = document.initiateapp6.comment.value;
	   if (comm.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
		}
		document.getElementById('progressbartable1').style.display = 'inline'; 
		document.initiateapp6.action = "<%=deleteurl%>";
   document.initiateapp6.submit();
	};
	var handleSubmit7 = function() {

		var alertstr = "";
		var showalert=false; 

		//this.submit();
		var name = document.initiateapp7.jobreqName.value;
		var isCompetencyChecked;
		var isAccomplishmentChecked;
		var isApproversChecked;
		var isAttachmentsChecked;


		if (name == "" || name == null) {
			
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobTitle.Name.Mandatory",user1.getLocale())%><br>";
			showalert = true;
		}
		

		if(document.initiateapp7.competency.checked == true)

		{
			isCompetencyChecked = "true";
		}else{
			isCompetencyChecked = "false";
		}
		if(document.initiateapp7.accomplishment.checked == true)
		{ 
			isAccomplishmentChecked = "true";
		}else{
			isAccomplishmentChecked = "false";
		}
		if(document.initiateapp7.approvers.checked == true)
		{ 
			isApproversChecked = "true";
		}else{
			isApproversChecked = "false";
		}
		if(document.initiateapp7.attachments.checked == true)
		{ 
			isAttachmentsChecked="true";
		}else{
			isAttachmentsChecked="false";
		}

		
		
	  	document.getElementById('progressbartable1').style.display = 'inline'; 
	  	var urlsaveas = "jobreq.do?method=saveasjobrequisition&uuid="+'<bean:write name="jobRequisitionForm" property="uuid"/>'
									  	+"&jobreqName="+encodeURIComponent(name)
									  	+"&isCompetencyChecked="+isCompetencyChecked
									  	+"&isAccomplishmentChecked="+isAccomplishmentChecked
									  	+"&isApproversChecked="+isApproversChecked
									  	+"&isAttachmentsChecked="+isAttachmentsChecked;
	  	document.initiateapp7.action = urlsaveas;
		 if (showalert){
		     	alert(alertstr);
		        return false;
		  }
	  	document.initiateapp7.submit();
   		
   		
		//saveasJobReq(name,jobtitle,isCompetencyChecked,isAccomplishmentChecked,isApproversChecked,isAttachmentsChecked);
	};

	var handleSubmit8 = function() {
		
		//this.submit();
		var comm = document.initiateapp8.comment.value;
	   if (comm.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
		}
		document.getElementById('progressbartable1').style.display = 'inline'; 
		document.initiateapp8.action = "<%=recallurl%>";
   document.initiateapp8.submit();
	};
	var handleCancel = function() {
		this.cancel();
	};
	var handleSuccess = function(o) {
		var response = o.responseText;
		response = response.split("<!")[0];
		document.getElementById("resp").innerHTML = response;
	};
	var handleFailure = function(o) {
		alert("<%=Constant.getResourceStringValue("Requisition.Submissionfailed.alert",user1.getLocale())%> : " + o.status);
	};

    // Remove progressively enhanced content class, just before creating the module
    YAHOO.util.Dom.removeClass("dialog1", "yui-pe-content");
	 YAHOO.util.Dom.removeClass("dialog2", "yui-pe-content");
	  YAHOO.util.Dom.removeClass("dialog3", "yui-pe-content");
	   YAHOO.util.Dom.removeClass("dialog4", "yui-pe-content");
	   YAHOO.util.Dom.removeClass("dialog5", "yui-pe-content");
	YAHOO.util.Dom.removeClass("dialog6", "yui-pe-content");
	YAHOO.util.Dom.removeClass("dialog7", "yui-pe-content");
	YAHOO.util.Dom.removeClass("dialog8", "yui-pe-content");
	// Instantiate the Dialog
	YAHOO.example.container.dialog1 = new YAHOO.widget.Dialog("dialog1", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});

	// Instantiate the Dialog
	YAHOO.example.container.dialog2 = new YAHOO.widget.Dialog("dialog2", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit2, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});
	// Instantiate the Dialog
	YAHOO.example.container.dialog3 = new YAHOO.widget.Dialog("dialog3", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit3, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});

// Instantiate the Dialog
	YAHOO.example.container.dialog4 = new YAHOO.widget.Dialog("dialog4", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit4, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});
// Instantiate the Dialog
	YAHOO.example.container.dialog5 = new YAHOO.widget.Dialog("dialog5", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Close requisition", handler:handleSubmit5, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});
	// Instantiate the Dialog
	YAHOO.example.container.dialog6 = new YAHOO.widget.Dialog("dialog6", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Delete requisition", handler:handleSubmit6, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});
	// Instantiate the Dialog
	YAHOO.example.container.dialog7 = new YAHOO.widget.Dialog("dialog7", 
							{ width : "30em",
							  height : "20em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Save As", handler:handleSubmit7, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});
		// Instantiate the Dialog
	YAHOO.example.container.dialog8 = new YAHOO.widget.Dialog("dialog8", 
							{ width : "30em",							  
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Recall", handler:handleSubmit8, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});
	// Validate the entries in the form to require that both first and last name are entered
	YAHOO.example.container.dialog1.validate = function() {
		var data = this.getData();
		if (data.comment == "") {
			alert("Please enter comment.");
			return false;
		} else {
			return true;
		}
	};

	// Wire up the success and failure handlers
	YAHOO.example.container.dialog1.callback = { success: handleSuccess,
						     failure: handleFailure };
	
	// Render the Dialog
	YAHOO.example.container.dialog1.render();
	YAHOO.example.container.dialog2.render();
	YAHOO.example.container.dialog3.render();
	YAHOO.example.container.dialog4.render();
   YAHOO.example.container.dialog5.render();
   YAHOO.example.container.dialog6.render();
   YAHOO.example.container.dialog7.render();
   YAHOO.example.container.dialog8.render();
	YAHOO.util.Event.addListener("show", "click", YAHOO.example.container.dialog1.show, YAHOO.example.container.dialog1, true);
	YAHOO.util.Event.addListener("reject", "click", YAHOO.example.container.dialog2.show, YAHOO.example.container.dialog2, true);
	YAHOO.util.Event.addListener("reassign", "click", YAHOO.example.container.dialog3.show, YAHOO.example.container.dialog3, true);
	YAHOO.util.Event.addListener("publish", "click", YAHOO.example.container.dialog4.show, YAHOO.example.container.dialog4, true);
	YAHOO.util.Event.addListener("close", "click", YAHOO.example.container.dialog5.show, YAHOO.example.container.dialog5, true);
	YAHOO.util.Event.addListener("delete", "click", YAHOO.example.container.dialog6.show, YAHOO.example.container.dialog6, true);
	YAHOO.util.Event.addListener("saveAs", "click", YAHOO.example.container.dialog7.show, YAHOO.example.container.dialog7, true);
	YAHOO.util.Event.addListener("recall", "click", YAHOO.example.container.dialog8.show, YAHOO.example.container.dialog8, true);
	
});
</script>



<div id="dialog1" class="yui-pe-content">
<div class="hd"><%=modaltitle%></div>
<div class="bd">
<form name="initiateapp" method="POST" action="">
	<%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>:<TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
	
</form>
</div>
</div>

<div id="dialog2" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("Requisition.reject",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp2" method="POST" action="">
	<%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>:<TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>

	
</form>
</div>
</div>
<div id="dialog3" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp3" method="POST" action="">
<table>
<tr>
<font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.reassign_review.note",user1.getLocale())%> </font>
<td width="100"><%=Constant.getResourceStringValue("Requisition.assignto",user1.getLocale())%>
<span id="reassignedto"></span>
<a href="#" onClick="opensearchassignedto()"><img src="jsp/images/selector.gif" border="0"/></a>
<input type="hidden" name="reassignedto" value=""/>
<input type="hidden" name="reassignedtoname" value=""/>
<input type="hidden" name="isgroup" value=""/>
</td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>
<TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
</td>
</tr>
</table>
	
</form>
</div>
</div>

<div id="dialog4" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("Requisition.Publish",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp4" method="POST" action="">

	<label for="comment"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>:</label><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>

	<label for="comment1"><%=Constant.getResourceStringValue("Requisition.commentmsg",user1.getLocale())%></label>
</form>
</div>
</div>

<div id="dialog5" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("Requisition.Close.req",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp5" method="POST" action="">
	<label for="comment"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>:</label><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
<label for="comment1"><%=Constant.getResourceStringValue("Requisition.commentmsg1",user1.getLocale())%></label>
	
</form>
</div>
</div>
<div id="dialog6" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("Requisition.delete.req",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp6" method="POST" action="">
	<label for="comment"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>:</label><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
<label for="comment1"><%=Constant.getResourceStringValue("Requisition.commentmsg2",user1.getLocale())%></label>
	
</form>
</div>
</div>
<div id="dialog7" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("equisition.saveAs.req",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp7" method="POST" action="">
	<label for="name"><%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%><font color="red">*</font>:</label><input type="text" NAME="jobreqName" size="30"><br></br>
	<b>Do you want to show values of following fields :</b><br></br>
	<input type="checkbox" name="competency" value="" /> Competency 
	<input type="checkbox" name="accomplishment" value="" /> Accomplishment <br></br>
	<input type="checkbox" name="approvers" value="" /> Approvers &nbsp;&nbsp;&nbsp;<br></br>
	<input type="checkbox" name="attachments" value="" /> Attachments <br></br>
</form>
</div>
</div>
<div id="dialog8" class="yui-pe-content">
<div class="hd"><%=Constant.getResourceStringValue("Requisition.recallreq",user1.getLocale())%></div>
<div class="bd">
<form name="initiateapp8" method="POST" action="">
	<label for="comment"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>:</label><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA>
</label>
</form>
</div>
</div>


<%--<%@include file="/jsp/common/tooltipnew.jsp" %>--%>

<div id="shiny_box" class="shiny_box" style="display:none;">
	<span class="tl"></span><span class="tr"></span>
	<div class="shiny_box_body"></div>
	<span class="bl"></span><span class="br"></span>
</div>



<script>
<%--$().ready(function(){
	$('.titleHintBox').inputHintBox({div:$('#shiny_box'),div_sub:'.shiny_box_body',source:'attr',attr:'title',incrementLeft:5});
	$('.titleHintBox2').inputHintBox({className:'simple_box',source:'attr',attr:'title',incrementLeft:5});
	$('.titleHintBox3').inputHintBox({className:'simple_box',html:'Same text for more inputs',incrementLeft:5});
});--%>
</script>
</body>





