<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
  <%

String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

%>

<html>

<bean:define id="jobreqtmplform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />
<%

String saveastrue=(String)request.getAttribute("saveas");
String jobtemplatecreated=(String)request.getAttribute("jobtemplatecreated");
String savetemplate=(String)request.getAttribute("savetemplate");

String updatejobtemplatecreated=(String)request.getAttribute("updatejobtemplatecreated");
String deletejobtemplatecreated=(String)request.getAttribute("deletejobtemplatecreated");
String edittemplate=(String)request.getAttribute("edittemplate");
String makeactive=(String)request.getAttribute("makeactive");
String approveralreadyadded=(String)request.getAttribute("approveralreadyadded");

List formvariableList = jobreqtmplform.getFormVariablesList();
%>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</style>
<style>
span1{color:#ff0000;}
</style>
<style type="text/css">
.yui-navset button {
    position:absolute;
    top:0;
    right:0;
}
</style>


<%@ include file="../common/tooltipnewcss.jsp" %>


<script language="javascript">



function validateUserhm(){
	if(document.jobRequisitionTemplateForm.highiringmanagernamehidden.value == "" || document.jobRequisitionTemplateForm.highiringmanagernamehidden.value == null){
		document.jobRequisitionTemplateForm.hiringMgrName.value = document.jobRequisitionTemplateForm.hiringMgrName.value;
	}else{
		document.jobRequisitionTemplateForm.hiringMgrName.value=document.jobRequisitionTemplateForm.highiringmanagernamehidden.value;
	}

}
function validateUserrecruiter(){
	

	if(document.jobRequisitionTemplateForm.recruiterNamehidden.value == "" || document.jobRequisitionTemplateForm.recruiterNamehidden.value == null){
		document.jobRequisitionTemplateForm.recruiterName.value = document.jobRequisitionTemplateForm.recruiterName.value;
	}else{
		document.jobRequisitionTemplateForm.recruiterName.value =document.jobRequisitionTemplateForm.recruiterNamehidden.value; 

}
	
}
function opensearchassignedto(){
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=addhighiringmanager");
  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("user.do?method=assignedtoselector&boxnumber=addhighiringmanager", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function closewindow(){
	//self.parent.location.reload();
parent.parent.GB_hide(); 
	  
	}	

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
	   } 
	}
function initfocus(){
setTimeout ( "document.jobRequisitionTemplateForm.templateName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){

	var alertstr = "";
	var showalert=false;
	
	var name = document.jobRequisitionTemplateForm.templateName.value.trim();
	var hiringMrgname = document.jobRequisitionTemplateForm.hiringMgrName.value.trim();


	var  minyearsofExpRequired= document.jobRequisitionTemplateForm.minyearsofExpRequired.value.trim();
	var maxyearsofExpRequired= document.jobRequisitionTemplateForm.maxyearsofExpRequired.value.trim();
	 
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Templatename_required",user1.getLocale())%><br>";
		showalert = true;
		}		

		if(hiringMrgname == "" || hiringMrgname == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.HiringMrgname_required",user1.getLocale())%><br>";
			showalert = true;
		}
		var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;
		if(orgidvalue == 0 ){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_is_required",user1.getLocale())%><BR>";
			showalert = true;
		}

		
		if(document.jobRequisitionTemplateForm.templateDesc.value.length > 500 ){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_500_characters",user1.getLocale())%><BR>";
			showalert = true;
		}
		if(document.jobRequisitionTemplateForm.jobInstructions.value.length> 800 ){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.exceeded_800_characters",user1.getLocale())%><BR>";
			showalert = true;
		}
		if(document.jobRequisitionTemplateForm.jobDetails.value.length> 2000 ){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.exceeded_2000_characters",user1.getLocale())%><BR>";
			showalert = true;
		}
		if(document.jobRequisitionTemplateForm.jobRoles.value.length> 1000 ){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.exceeded_1000_characters",user1.getLocale())%><BR>";
			showalert = true;
		}

	
	
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.jobRequisitionTemplateForm.action = "jobtemplate.do?method=savejobtemplate&hiringManagerId="+document.jobRequisitionTemplateForm.hiringMgrId.value+"&recruiterId="+document.jobRequisitionTemplateForm.recruiterId.value.trim();
   document.jobRequisitionTemplateForm.submit();
  // window.opener.location.href="/lov.do?method=rolelist&a=1";
//self.close();
  // window.top.hidePopWin();
   
	}
function numberrequired(){
	var alertstr = "";
	var showalert=false;

	var  minyearsofExpRequired= document.jobRequisitionTemplateForm.minyearsofExpRequired.value.trim();
	var maxyearsofExpRequired= document.jobRequisitionTemplateForm.maxyearsofExpRequired.value.trim();
	var defaultStandardHours= document.jobRequisitionTemplateForm.defaultStandardHours.value.trim();
	var durationinmonths= document.jobRequisitionTemplateForm.durationinmonths.value.trim();
	if(isNaN(minyearsofExpRequired)){
		setTimeout ( "document.jobRequisitionTemplateForm.minyearsofExpRequired.focus(); ", 200 );
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.number_required",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(isNaN(maxyearsofExpRequired)){
		setTimeout ( "document.jobRequisitionTemplateForm.maxyearsofExpRequired.focus(); ", 200 );
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.number_required",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(isNaN(defaultStandardHours)){

		setTimeout ( "document.jobRequisitionTemplateForm.defaultStandardHours.focus(); ", 200 );
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.number_required",user1.getLocale())%><BR>";
		showalert = true;
		
	}
	if(isNaN(durationinmonths)){

		setTimeout ( "document.jobRequisitionTemplateForm.durationinmonths.focus(); ", 200 );
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.number_required",user1.getLocale())%><BR>";
		showalert = true;
		
	}
	 if (showalert){
	     	alert(alertstr);
	        return false;
	  }
}
function disableDurationInMonths(){
	var jobname = document.jobRequisitionTemplateForm.jobtypeId.value;
	if(jobname == 1){
		document.jobRequisitionTemplateForm.durationinmonths.disabled = true;
	}else{
		document.jobRequisitionTemplateForm.durationinmonths.disabled = false;
	}
	
}
	function updatedata(){
		var alertstr = "";
	var showalert=false;
	 
	var name=document.jobRequisitionTemplateForm.templateName.value.trim();
	 
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Templatename_required",user1.getLocale())%><br>";
		showalert = true;
		}		
		var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;
	if(orgidvalue == 0 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_is_required",user1.getLocale())%><BR>";
		showalert = true;
	}


	
	if(document.jobRequisitionTemplateForm.templateDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.jobRequisitionTemplateForm.jobInstructions.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.jobRequisitionTemplateForm.jobDetails.value.length> 2000 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.exceeded_2000_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.jobRequisitionTemplateForm.jobRoles.value.length> 1000 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.exceeded_1000_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionTemplateForm.action = "jobtemplate.do?method=updatejobtemplate&templateId="+'<bean:write name="jobRequisitionTemplateForm" property="templateId"/>'+"&hiringMgrId="+document.jobRequisitionTemplateForm.hiringMgrId.value.trim()+"&recruiterId="+document.jobRequisitionTemplateForm.recruiterId.value.trim();
   document.jobRequisitionTemplateForm.submit();
   //self.parent.location.reload();

  // window.top.hidePopWin();
   
	}


function manageCustomVariables(){
	var url = "variablemap.do?method=manageVariableMappingscr&formcode=REQUISITION_TMPL_FORM&formname=REQUISITION TMPL FORM&id=<%=jobreqtmplform.getTemplateId()%>";
     var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.initiate.approval",user1.getLocale())%>","dialogHeight: 434px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

  window.document.location.href="jobtemplate.do?method=edittemplate&templateId=<%=jobreqtmplform.getTemplateId()%>&tabno=5";

}

function addwatchers(){
	var tu = "watchlist.do?method=watchList&type=TEMPLATE&applicantId=0&reqid=<%=jobreqtmplform.getTemplateId()%>";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchlocation(){
  window.open("location.do?method=selectlocation", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchorg(){
  window.open("org.do?method=orgselector&orgId=1", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchhiringmgr(){
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=userselector&boxnumber=-1");
  window.open(url,"SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("user.do?method=userselector&boxnumber=-1", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchapprover(){
	var tu = "user.do?method=assignedtoselector&boxnumber=approvertemplate";
  // var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(tu,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
  //window.open("user.do?method=userselector&boxnumber="+boxnumber, "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchdepartment(){
	var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectorg.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("dept.do?method=departmentlocator&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function opensearchprojectcode(){
	var deptvalue = document.jobRequisitionTemplateForm.departmentId.value;
	var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;
	if(deptvalue == 0 || deptvalue == ""){
		alert("<%=Constant.getResourceStringValue("Requisition.selectdept.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("lov.do?method=projectcodelocator&readPreview=3&deptId="+deptvalue+"&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchjobgrade(){
	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=jobgradelocator");
 window.open(url, "SearchJobGrade","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("lov.do?method=jobgradelocator", "SearchJobGrade","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchjobcode(){
  window.open("lov.do?method=jobcodeselector", "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchsalaryplan(){
   var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=salaryplanselector&orgid="+orgidvalue);
  window.open(url, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("lov.do?method=salaryplanselector", "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchbudgetcode(){
	var deptvalue = document.jobRequisitionTemplateForm.departmentId.value;
	var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;

   var tu = "lov.do?method=budgetcodeselector&orgId="+orgidvalue+"&deptId="+deptvalue;
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchreferralscheme(){
	var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;
	var deptvalue = document.jobRequisitionTemplateForm.departmentId.value;

	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=1&orgid="+orgidvalue+"&deptid="+deptvalue);
 // window.open("user.do?method=userselector&readPreview=3&boxnumber=-1", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=950,height=600");
}


function opensearchagencyreferralscheme(){
	var orgidvalue = document.jobRequisitionTemplateForm.parentOrgId.value;
	var deptvalue = document.jobRequisitionTemplateForm.departmentId.value;

	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=2&orgid="+orgidvalue+"&deptid="+deptvalue);
 // window.open("user.do?method=userselector&readPreview=3&boxnumber=-1", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}

function saveasTemplate(){
var alertstr = "";
	var showalert=false;
	 
	var name=document.jobRequisitionTemplateForm.templateName.value.trim();
	 
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Templatename_required",user1.getLocale())%><br>";
		showalert = true;
		}		
	
	 if (showalert){
     	alert(alertstr);
        return false;
          }
       var doyou = confirm("<%=Constant.getResourceStringValue("Requisition.doyoumsg",user1.getLocale())%>");
	  if (doyou == true){
	    
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionTemplateForm.action = "jobtemplate.do?method=saveastemplate&templateId="+'<bean:write name="jobRequisitionTemplateForm" property="templateId"/>';;
   document.jobRequisitionTemplateForm.submit();

   
	   }
}

function makeactive(){

       var doyou = confirm("<%=Constant.getResourceStringValue("Requisition.ActiveTempmsg",user1.getLocale())%>");
	  if (doyou == true){
	    
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionTemplateForm.action = "jobtemplate.do?method=makeactive&templateId="+'<bean:write name="jobRequisitionTemplateForm" property="templateId"/>';;
   document.jobRequisitionTemplateForm.submit();

   
	   }
}

function closetemplte(){

       var doyou = confirm("<%=Constant.getResourceStringValue("Requisition.CloseTempmsg",user1.getLocale())%>");
	  if (doyou == true){
	    
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionTemplateForm.action = "jobtemplate.do?method=closetemplate&templateId="+'<bean:write name="jobRequisitionTemplateForm" property="templateId"/>';;
   document.jobRequisitionTemplateForm.submit();

   
	   }
}


function deletetemplte(){

       var doyou = confirm("<%=Constant.getResourceStringValue("Requisition.DeleteTempmsg",user1.getLocale())%>");
	  if (doyou == true){
	    
	 document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionTemplateForm.action = "jobtemplate.do?method=deletetemplate&templateId="+'<bean:write name="jobRequisitionTemplateForm" property="templateId"/>';;
   document.jobRequisitionTemplateForm.submit();

   
	   }
}



function addAccomplishment(){
        var alertstr = "";
		var tmplid = "<%=jobreqtmplform.getTemplateId()%>";
		if(tmplid == "0"){
			alert("<%=Constant.getResourceStringValue("validation.Please_save_temp",user1.getLocale())%>");
			return false;
		}


		var accomplishmentname = escape(document.jobRequisitionTemplateForm.accomplishmentname.value);
		var isaccommandatory1 = "";//document.jobRequisitionTemplateForm.iscompmandatory.value;
		//var minimumratingaccom = document.jobRequisitionTemplateForm.minimumratingaccom.value;
		//alert(document.jobRequisitionTemplateForm.group1.value);
		var showalert=false;

		if(accomplishmentname == "" || accomplishmentname == null){
	     	alertstr = alertstr + "Accomplishment required.<BR>";
			showalert = true;
			}



		 if (showalert){
	     	alert(alertstr);
	        return false;
	          }
	for (var i=0; i < document.jobRequisitionTemplateForm.isaccommandatory.length; i++)
   {
   if (document.jobRequisitionTemplateForm.isaccommandatory[i].checked)
      {
       isaccommandatory1 = document.jobRequisitionTemplateForm.isaccommandatory[i].value;
      }
   }


var url="jobtemplate.do?method=saveAccompleshment&type=tmpl&templateId=<%=jobreqtmplform.getTemplateId()%>&accomplishmentname="+accomplishmentname+"&isaccommandatory="+isaccommandatory1;


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
    


       setTimeout("retrieveAccomplishments()",200);

	}

function addCompetency(){
        var alertstr = "";
		var tmplid = "<%=jobreqtmplform.getTemplateId()%>";
		if(tmplid == "0"){
			alert("<%=Constant.getResourceStringValue("validation.Please_save_temp",user1.getLocale())%>");
			return false;
		}


		var competencyname = escape(document.jobRequisitionTemplateForm.competencyname.value);
		var iscompmandatory1 = "";//document.jobRequisitionTemplateForm.iscompmandatory.value;
		var minimumrating = document.jobRequisitionTemplateForm.minimumrating.value;
		//alert(document.jobRequisitionTemplateForm.group1.value);
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
	for (var i=0; i < document.jobRequisitionTemplateForm.iscompmandatory.length; i++)
   {
   if (document.jobRequisitionTemplateForm.iscompmandatory[i].checked)
      {
       iscompmandatory1 = document.jobRequisitionTemplateForm.iscompmandatory[i].value;
      }
   }
	for (var i=0; i < document.jobRequisitionTemplateForm.isVisible.length; i++)
	   {
	   if (document.jobRequisitionTemplateForm.isVisible[i].checked)
	      {
		   isVisible = document.jobRequisitionTemplateForm.isVisible[i].value;
	      }
	   }

var url="jobtemplate.do?method=saveCompetency&type=tmpl&templateId=<%=jobreqtmplform.getTemplateId()%>&competencyname="+competencyname+"&iscompmandatory="+iscompmandatory1+"&minimumrating="+minimumrating+"&isVisible="+isVisible;


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



function addApprovers(){
        var alertstr = "";
		var tmplid = "<%=jobreqtmplform.getTemplateId()%>";
        var isgroupval = document.jobRequisitionTemplateForm.isgroup.value;
		var appid = document.jobRequisitionTemplateForm.approverId.value;
		var appname =escape(document.jobRequisitionTemplateForm.approverName.value);
		if(tmplid == "0"){
			alert("<%=Constant.getResourceStringValue("validation.Please_save_temp",user1.getLocale())%>");
			return false;
		}
		if(appname == "" || appname == null){
	     	alert("<%=Constant.getResourceStringValue("Requisition.validation.ApproverName",user1.getLocale())%>");
			
		}	

		


	var url="jobtemplate.do?method=saveApprover&type=tmpl&templateId=<%=jobreqtmplform.getTemplateId()%>&approvername="+appname+"&approverid="+appid+"&isgroup="+isgroupval;
	document.jobRequisitionTemplateForm.action = url;
	document.jobRequisitionTemplateForm.submit();

//url = encodeURIComponent(url);

/*
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
function otherselected(){
	
	
	//document.getElementById("otherminEdu").style.visibility = "hidden";
	
	var educationName = document.jobRequisitionTemplateForm.educationName.value;

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
		 document.jobRequisitionTemplateForm.otherMinimumLevelOfEducation.style.visibility="hidden";  
		//alert(educationName);
		// var d=document.getElementById("mineducation");
		// d.innerHTML+="Enter other Min level of education : <input type='text' name='minimumEdu'>";
	}else{

		document.getElementById("mineducation").style.visibility = "hidden";
		document.jobRequisitionTemplateForm.otherMinimumLevelOfEducation.style.visibility="hidden"; 
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
    	//document.getElementById("loadingp").style.visibility = "hidden";	
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


function deleteAccomplishment(id){

	var url="jobtemplate.do?method=deleteAccomplishment&id="+id;


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
    


       setTimeout("retrieveAccomplishments()",200);


}

function deleteApprovers(id){

	var url="jobtemplate.do?method=deleteApprovers&id="+id;


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
    


       setTimeout("retrieveApprovers()",200);


}




	function retrieveCompetency() {
    document.getElementById("loading4").style.visibility = "visible";
		document.jobRequisitionTemplateForm.competencyname.value="";
	var url="jobtemplate.do?method=getCompetencies&id=<%=jobreqtmplform.getTemplateId()%>&type=tmpl";

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

	function retrieveApprovers() {

    document.getElementById("loading6").style.visibility = "visible";
		document.jobRequisitionTemplateForm.approverName.value="";
		document.jobRequisitionTemplateForm.approverId.value="";
		document.jobRequisitionTemplateForm.approverNamehidden.value="";
		
		var url="jobtemplate.do?method=getApprovers&id=<%=jobreqtmplform.getTemplateId()%>&type=tmpl";

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


	function retrieveAccomplishments() {
    document.getElementById("loading5").style.visibility = "visible";
		document.jobRequisitionTemplateForm.accomplishmentname.value="";
	var url="jobtemplate.do?method=getAccomplishments&id=<%=jobreqtmplform.getTemplateId()%>&type=tmpl";

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

function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

	function validateUser(){
	document.jobRequisitionTemplateForm.approverName.value=document.jobRequisitionTemplateForm.approverNamehidden.value;
}

function init(){
       
	  document.getElementById('progressbartable1').style.display = 'none';   
	  initfocus();
		}

function retrieveURLOrg(url) {
   //convert the url to a string
    document.getElementById("loadingd").style.visibility = "visible";
    var tdata="";
	
	url=url+"&orgId="+document.jobRequisitionTemplateForm.parentOrgId.value;

    $.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#departments");  
	  $('#departments').html(tdata);
		
	  }
	});

    document.getElementById("loadingd").style.visibility = "hidden";

	if(typeof document.jobRequisitionTemplateForm.projectcodeId != 'undefined'){
	
		retrieveProjcodesbyOrganization();
	}
	if(typeof document.jobRequisitionTemplateForm.budgetcodeId != 'undefined'){
	
		retrieveBudgetCode();
	}
	if(typeof document.jobRequisitionTemplateForm.salaryplanId != 'undefined'){
	
		retrieveSalaryPlanByOrganization();
	}
   
}


function retrieveSalaryPlanByOrganization() {

	var parentOrgId=document.jobRequisitionTemplateForm.parentOrgId.value;
	
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
	 


function retrieveProjcodes(url) {
   //convert the url to a string

	
	if(typeof document.jobRequisitionTemplateForm.projectcodeId != 'undefined'){
	    document.getElementById("loadingp").style.visibility = "visible";	
		var departmentId="0";
	if(typeof document.jobRequisitionTemplateForm.departmentId != 'undefined'){
		departmentId=document.jobRequisitionTemplateForm.departmentId.value;
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

function retrieveProjcodesbyOrganization() {
   //convert the url to a string
   // document.getElementById("loadingp").style.visibility = "visible";
   
	var parentOrgId=document.jobRequisitionTemplateForm.parentOrgId.value;
	var departmentId="0";
	if(typeof document.jobRequisitionTemplateForm.departmentId != 'undefined'){
		departmentId=document.jobRequisitionTemplateForm.departmentId.value;
	}
		url="jobtemplate.do?method=loadprojectcodebyOrganization&parentOrgId="+parentOrgId+"&departmentId="+departmentId;
		
		$.ajax({
			type: 'GET',
		  url: url,
		  success: function(data){
		  tdata = $(data).find("#projectcodes");  
		  $('#projectcodes').html(tdata);
			
		  }
		});
}
	 

function retrieveBudgetCode() {
   //convert the url to a string

    document.getElementById("loadingp").style.visibility = "visible";
    var parentOrgId=document.jobRequisitionTemplateForm.parentOrgId.value;

    var departmentId="0";
    if(typeof document.jobRequisitionTemplateForm.departmentId != 'undefined'){
    	departmentId=document.jobRequisitionTemplateForm.departmentId.value;
    }
    	
    	
    	url="jobtemplate.do?method=loadBudgetCode&departmentId="+departmentId+"&orgId="+parentOrgId;
    	
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


function retrieveBudgetCodebyOrganization() {
   //convert the url to a string

var parentOrgId=document.jobRequisitionTemplateForm.parentOrgId.value;
var departmentId="0";
if(typeof document.jobRequisitionTemplateForm.departmentId != 'undefined'){
	departmentId=document.jobRequisitionTemplateForm.departmentId.value;
}
	
	
	url="jobtemplate.do?method=loadBudgetCodebyOrganization&parentOrgId="+parentOrgId+"&departmentId="+departmentId;
	
  	$.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#budgetcodescodes");  
	  $('#budgetcodescodes').html(tdata);
		
	  }
	});

}


function opensearchrecruiter(){

var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=recruiterselector&readPreview=3&boxnumber=-1");
window.open(url, "SearchRecruter","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
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
			url: 'jsp/talent/getUserData.jsp',
				minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
			     
		
				onItemSelect: function(item) {
			    var text = 'You selected <b>' + item.value + '</b>';
			    if (item.data.length) {
			        text += ' <i>' + item.data.join(', ') + '</i>';
			    }
			  
			document.jobRequisitionTemplateForm.recruiterId.value=item.data;
			//document.jobRequisitionTemplateForm.recruiterName.value=item.value;
			document.jobRequisitionTemplateForm.recruiterNamehidden.value = item.value;
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
		  
		document.jobRequisitionTemplateForm.hiringMgrId.value=item.data;
		//alert(item.value);
		document.jobRequisitionTemplateForm.highiringmanagernamehidden.value=item.value;
		
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
			 document.jobRequisitionTemplateForm.approverName.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.jobRequisitionTemplateForm.approverId.value=idval.substring(1,idval.length);
			 document.jobRequisitionTemplateForm.isgroup.value="Y";
			 document.jobRequisitionTemplateForm.approverNamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.jobRequisitionTemplateForm.approverId.value=item.data;
		 document.jobRequisitionTemplateForm.isgroup.value="N";
		 document.jobRequisitionTemplateForm.approverName.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.jobRequisitionTemplateForm.approverNamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		
		}
		});

});


	</script>


<body onload="init()" class="yui-skin-sam">

 





<html:form action="/jobtemplate.do?method=savejobtemplate" onsubmit="savedata()">


<table  width="100%">
<%
			if(jobreqtmplform.getTemplateId()>0){
			%>
			<font><tr bgcolor="#7f93bc"><td class="yui-dt"><font color="white"><%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%> : &nbsp;<b><%= jobreqtmplform.getTemplateName()%></b>&nbsp;&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%> : &nbsp;<b><%=jobreqtmplform.getStatus()%></b> &nbsp;&nbsp;&nbsp;
			<%}else{%>

				<!--  <b><%=Constant.getResourceStringValue("Requisition.createtemp",user1.getLocale())%></b>-->

				


			<%}%>
				</td>
			</tr>
			<tr><td bgcolor="#F5FFFA">
			<% if(jobreqtmplform.getStatus() != null && (jobreqtmplform.getStatus().equals(Common.CLOSED) || jobreqtmplform.getStatus().equals(Common.DELETED))){ %>
           		<font color="red"><b><%=Constant.getResourceStringValue("Requisition.Template.is",user1.getLocale())%>  <%=jobreqtmplform.getStatus()%> </b> </font>
			   <% if(!jobreqtmplform.getStatus().equals(Common.DELETED)){%>
			    <a  href="#" onClick="deletetemplte()"><img src="jsp/images/delete.gif" border="0" alt="Delete" title="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" height="20"  width="19"/></a>
				<%}%>
		   <%}else{%>
				<%
				if(jobreqtmplform.getTemplateId()>0){
				%>
	             <a  href="#" onClick="updatedata()"><img src="jsp/images/save_button.gif" border="0" alt="update template" title="<%=Constant.getResourceStringValue("Requisition.update.temp",user1.getLocale())%>" height="20"  width="19"/></a>
				 <a  href="#" onClick="saveasTemplate()"><img src="jsp/images/saveas.gif" border="0" alt="save as" title="<%=Constant.getResourceStringValue("hr.button.save_as",user1.getLocale())%>" height="20"  width="19"/></a>
					 <% if(!jobreqtmplform.getStatus().equals("Active")){%>
					  <a  href="#" onClick="makeactive()"><img src="jsp/images/approve.png" border="0" alt="Active" title="<%=Constant.getResourceStringValue("Requisition.active",user1.getLocale())%>" height="20"  width="19"/></a>
					<%}%>
				  <a  href="#" onClick="closetemplte()"><img src="jsp/images/cancel_button.gif" border="0" alt="Close template" title="<%=Constant.getResourceStringValue("Requisition.Closetemplate",user1.getLocale())%>" height="20"  width="19"/></a>
				  <a  href="#" onClick="deletetemplte()"><img src="jsp/images/delete.gif" border="0" alt="Delete" title="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" height="20"  width="19"/></a>
				<a  href="#" onClick="addwatchers()"><img src="jsp/images/watchicon.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.watchers",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.watchers",user1.getLocale())%>" height="20"  width="19"/></a>
			 	 <%} else { %>
			   	<a  href="#" onClick="savedata()"><img src="jsp/images/save_button.gif" border="0" alt="Save template" title="<%=Constant.getResourceStringValue("Requisition.savetemplate",user1.getLocale())%>" height="20"  width="19"/></a>
	              
			    <%} %>
			 <%}%>
			   <a  href="#" onClick="discard()"><img src="jsp/images/close.gif" border="0" alt="discard" title="<%=Constant.getResourceStringValue("hr.button.discard",user1.getLocale())%>" height="20"  width="19"/></a>
			<% if(saveastrue != null && saveastrue.equals("true")){%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='jsp/images/greentick.png' border='0' width='20' height='20'/>
			&nbsp;<font color="green"><b> <%=Constant.getResourceStringValue("Requisition.saveas",user1.getLocale())%> </b> </font>
			<%}%>
		<% if(savetemplate != null && savetemplate.equals("yes")){%>
		
		<div class="msg"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("Requisition.tempsave",user1.getLocale())%>  </font> 
		<!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>
		<%}%>
		
		<% if(deletejobtemplatecreated != null && deletejobtemplatecreated.equals("yes")){%>
		<div class="msg"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("Requisition.Tempdelete",user1.getLocale())%>  </font><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>
		<%}%>

	</td></tr>
</table>
<br>
<table id='progressbartable1'>
<tr>
    <td></td>
    <td  style="vertical-align:middle"><img src="jsp/images/loading.gif"></td>
    <td></td>
</tr>
</table>
<%
String tabselected5 = "";
String tabselected6 = "";
if(jobreqtmplform.getTabselected()==5){
	tabselected5="class=\"selected\"";
}
if(jobreqtmplform.getTabselected()==6){
	tabselected6="class=\"selected\"";
}
String tabselected4 = "";
System.out.println("tab selected : "+jobreqtmplform.getTabselected());
if(jobreqtmplform.getTabselected()== 4){
	tabselected4="class=\"selected\"";
}
%>
<div id="demo" class="yui-navset">
      <ul class="yui-nav">
        <li class="selected"><a href="#tab1"><em><%=Constant.getResourceStringValue("Requisition.reqdata",user1.getLocale())%></em></a></li>
        <li><a href="#tab2"><em><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></em></a></li>
        <li><a href="#tab3"><em><%=Constant.getResourceStringValue("Requisition.evaluation",user1.getLocale())%></em></a></li>
		<li <%=tabselected4%>><a href="#tab4"><em><%=Constant.getResourceStringValue("Requisition.approvals",user1.getLocale())%></em></a></li>
		<li <%=tabselected5%>><a href="#tab5"><em><%=Constant.getResourceStringValue("Custom.variables",user1.getLocale())%></em></a></li>
		<%if(!StringUtils.isNullOrEmpty(user1.getPackagetaken()) && Constant.isPackageContainFunction(user1.getPackagetaken(),"filter")){%>
		<li <%=tabselected6%>><a href="#tab6"><em><%=Constant.getResourceStringValue("admin.requistion.filters",user1.getLocale())%></em></a></li>
		<%}%>
    </ul> 
 
    
	
	
    <div class="yui-content">
        <div id="tab1">
			<!-- tab 1 start -->
		<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

	
	<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.isinternaljaob",user1.getLocale())%></td>
			<td><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isInternal" maxlength="10" id="1" <%=(jobreqtmplform.getInternal()!= null && jobreqtmplform.getInternal().equals("Y"))? "Checked=true" : "" %> value="Y">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isInternal" id="0" <%=(jobreqtmplform.getInternal()!= null && jobreqtmplform.getInternal().equals("N"))? "Checked=true" : "" %> value="N"></td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="templateName" size="50" maxlength="200"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
			<td>
			<html:select property="designationId">
			<option value=""></option>
			<bean:define name="jobRequisitionTemplateForm" property="designationList" id="designationList" />
            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>	
			</td>
		</tr>


		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.job.category",user1.getLocale())%></td>
			<td>
			<html:select property="jobCategoryId">
			<option value=""></option>
			<bean:define name="jobRequisitionTemplateForm" property="jobCategoryList" id="jobCategoryList" />

            <html:options collection="jobCategoryList" property="jobCategoryId"  labelProperty="jobCategoryName"/>
			</html:select>
			</td>
		</tr>
						
			
<tr>
			<td width="40%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select property="parentOrgId" onchange="retrieveURLOrg('jobtemplate.do?method=loadDeptlistWithProjectcode');">
				<bean:define name="jobRequisitionTemplateForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			
			&nbsp;
			<span class="textboxlabel" id="loadingd" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%> ......</span>

			
			  
			</td>
			</tr>


	<tr>
			<td width="40%"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="departmentId" onchange="retrieveProjcodes('jobtemplate.do?method=loadProjectCode');">
			<option value=""></option>
			<bean:define name="jobRequisitionTemplateForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			<span class="textboxlabel" id="loadingp" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.ProjectCode.load",user1.getLocale())%>......</span>
			
			  
			</td>
			</tr>

<tr>
			<td width="40%"><%=Constant.getResourceStringValue("admin.ProjectCode.PorjectCode",user1.getLocale())%></td>
			<td>
			<span id="projectcodes">
			<html:select property="projectcodeId">
			<option value=""></option>
			<bean:define name="jobRequisitionTemplateForm" property="projectcodeList" id="projectcodeList" />

            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
			</html:select>
			
			</span>
			  
			</td>
			</tr>
		

			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%><font color="red">*</font></td>


				<td>
				<input type="hidden" name="highiringmanagernamehidden">

                 <input type="text" id="hiringMgrName" name="hiringMgrName" autocomplete="off" value="<%=(jobreqtmplform.getHiringMgrName()==null)?"":jobreqtmplform.getHiringMgrName()%>" onblur="validateUserhm()">

				
				<span id="assignedto"></span>
				<a href="#" onClick="opensearchassignedto()"><img src="jsp/images/selector.gif" border="0"/></a>
				<br>
				
				<%
				String hiringmghid = String.valueOf(jobreqtmplform.getHiringMgrId());
				%>
				<html:hidden  property="hiringMgrId" value="<%=hiringmghid%>"/>

				</td>

			</tr>	
			<tr>
				<td></td>
				<td>
					<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
				</td>
			</tr>


	  <%
		System.out.println("jobreqtmplform.getJobgradeId()"+jobreqtmplform.getJobgradeId());
		String jobgradevalue ="<span id=\"jobgradeId\">";
        if(jobreqtmplform.getJobgradeId() != 0){

         String jobgradetempurl = "<a href='#' onClick=window.open("+"'"+"jobgrade.do?method=jobGradeDetails&readPreview=2&id="+jobreqtmplform.getJobgradeId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=180"+"'"+")>"+jobreqtmplform.getJobgradeName()+"</a>";
         jobgradevalue = "<span id=\"jobgradeId\">"+jobgradetempurl+"</span>";
		}

	  %>


		<tr>
				<td><%=Constant.getResourceStringValue("Requisition.jobgrade",user1.getLocale())%></td>

				<td>
				<html:select  property="jobgradeId">
			      <bean:define name="jobRequisitionTemplateForm" property="jobgradeList" id="jobgradeList" />
                  <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			    </html:select>
			               <a href="#"  onClick="opensearchjobgrade()"><img src="jsp/images/selector.gif" border="0"/></a>
			   </td>
</tr>
   <%
       System.out.println("jobreqtmplform.getSalaryplanId()"+jobreqtmplform.getSalaryplanId());
		String salaryplavalue ="<span id=\"salaryplanId\">";
        if(jobreqtmplform.getSalaryplanId() != 0){

   String salaryplantempurl = "<a href='#' onClick=window.open("+"'"+"salaryplan.do?method=editsaaryplan&readPreview=2&planId="+jobreqtmplform.getSalaryplanId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230"+"'"+")>"+jobreqtmplform.getSalaryplanName()+"</a>";
 salaryplavalue = "<span id=\"salaryplanId\">"+salaryplantempurl+"</span>";
		}

   %>



	<tr>
				<td><%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%></td>

				<td>
			<span id="salaryplans">
			<html:select  property="salaryplanId">
				<option value=""></option>		
			<bean:define name="jobRequisitionTemplateForm" property="salaryplanList" id="salaryplanList" />
            <html:options collection="salaryplanList" property="salaryplanId"  labelProperty="salaryPlanName"/>
			</html:select>
			</span>
			<a href="#" onClick="opensearchsalaryplan()"><img src="jsp/images/selector.gif" border="0"/></a>
			<span class="textboxlabel" id="loadinsalaryplan" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.salaryplan.load",user1.getLocale())%>......</span>
			</td>
</tr>
		
<% 
        System.out.println("jobreqtmplform.getBudgetcodeId()"+jobreqtmplform.getBudgetcodeId());
		String budgetcodevalue ="<span id=\"budgetcodeId\">";
        if(jobreqtmplform.getBudgetcodeId() != 0){

   String budgetcodetempurl = "<a href='#' onClick=window.open("+"'"+"budgetcode.do?method=editbudgetCode&readPreview=2&id="+jobreqtmplform.getBudgetcodeId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=260"+"'"+")>"+jobreqtmplform.getBudgetcodeName()+"</a>";
 budgetcodevalue = "<span id=\"budgetcodeId\">"+budgetcodetempurl+"</span>";
		}

		%>

		<tr>
				<td><%=Constant.getResourceStringValue("Requisition.budgetcode",user1.getLocale())%></td>

				<td>
                 <span id="budgetcodescodes">
				<html:select  property="budgetcodeId">
			<option value=""></option>
			<bean:define name="jobRequisitionTemplateForm" property="budgetCodeList" id="budgetCodeList" />
            <html:options collection="budgetCodeList" property="budgetId"  labelProperty="budgetCode"/>
			</html:select>
			</span>
				<a href="#" onClick="opensearchbudgetcode()"><img src="jsp/images/selector.gif" border="0"/></a>
</tr>


<!--  below code is commented purposefully 
			/**** START ******/
		

		<%
			System.out.println("jobreqtmplform.getRefferalSchemeId()"+jobreqtmplform.getRefferalSchemeId());
			String referralvalue ="<span id=\"referralId\">";
        	if(jobreqtmplform.getRefferalSchemeId() != 0){

   			String referraltempurl = "<a href='#' onClick=window.open("+"'"+"refferalscheme.do?method=editRefferalScheme&readPreview=2&id="+jobreqtmplform.getRefferalSchemeId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=300"+"'"+")>"+jobreqtmplform.getRefferalSchemeName()+"</a>";
			 referralvalue = "<span id=\"referralId\">"+referraltempurl+"</span>";
			}

		%>
		<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Employee.Referral.Scheme",user1.getLocale())%><font color="red">*</font></td>

				<td>
                
				<html:select  property="refferalSchemeId">
					<option value=""></option>
					<bean:define name="jobRequisitionTemplateForm" property="refferalSchemeList" id="refferalSchemeList" />
           	 		<html:options collection="refferalSchemeList" property="refferalScheme_Id"  labelProperty="refferalScheme_Name"/>
					</html:select>
					</span>
				<a href="#" onClick="opensearchreferralscheme()"><img src="jsp/images/selector.gif" border="0"/></a>
		</tr>


		

		<%
			System.out.println("jobreqtmplform.getAgencyRefferalSchemeId()"+jobreqtmplform.getAgencyRefferalSchemeId());
			String agreferralvalue ="<span id=\"agencyreferralId\">";
        	if(jobreqtmplform.getAgencyRefferalSchemeId() != 0){

   			String referraltempurl = "<a href='#' onClick=window.open("+"'"+"refferalscheme.do?method=editRefferalScheme&readPreview=2&id="+jobreqtmplform.getAgencyRefferalSchemeId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=300"+"'"+")>"+jobreqtmplform.getAgencyRefferalSchemeName()+"</a>";
 			System.out.println(".....referraltempurl .... : "+referraltempurl);
   			agreferralvalue = "<span id=\"agencyreferralId\">"+referraltempurl+"</span>";
			}

		%>

		<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Agency.Referral.Scheme",user1.getLocale())%><font color="red">*</font></td>

				<td>
                
				<html:select  property="agencyRefferalSchemeId">
				<option value=""></option>
				<bean:define name="jobRequisitionTemplateForm" property="agencyRefferalSchemeList" id="agencyRefferalSchemeList" />
            	<html:options collection="agencyRefferalSchemeList" property="refferalScheme_Id"  labelProperty="refferalScheme_Name"/>
				</html:select>
				</span>
				<a href="#" onClick="opensearchagencyreferralscheme()"><img src="jsp/images/selector.gif" border="0"/></a>
		</tr>

		/**** END ******/  -->		
		
		
		<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Recruiter",user1.getLocale())%></td>
				<td><span id="recruiterId"></span>
				<input type="hidden" name="recruiterNamehidden">
				<input type="text"  id="recruiterName" name="recruiterName" autocomplete="off" value="<%=(jobreqtmplform.getRecruiterName() == null) ? "" :jobreqtmplform.getRecruiterName() %>" onblur="validateUserrecruiter()"/>

				&nbsp;&nbsp;<a href="#" onClick="opensearchrecruiter()"><img src="jsp/images/selector.gif" border="0"/></a>
				<html:hidden  property="recruiterId"/>
				<br>
  				
				
				</td>
		</tr>
		<tr>
		<td></td>
		<td>  <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
				
		</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.notes",user1.getLocale())%></td>
			<td><html:textarea property="templateDesc" cols="70" rows="5"/></td>
		</tr>
	

		

	</table>
</div>

	<!-- tab 1 end -->	
		</div>
	
        <div id="tab2">
		
			<!-- tab 2 start -->	
				
		<div align="center" class="div">
	<table border="0" width="100%">

	<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.Introduction",user1.getLocale())%></td>
			<td><html:textarea property="jobInstructions" cols="50" rows="3"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></td>
			<td><html:textarea property="jobDetails" cols="50" rows="5"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.JobRoles",user1.getLocale())%></td>
			<td><html:textarea property="jobRoles" cols="50" rows="3"/></td>
		</tr>

		
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DefaultStandardWorkingHour",user1.getLocale())%><font color="red">*</font></td>
			<td>
	
			<html:select  property="stdworkinghoursunitName">
		
			<bean:define name="jobRequisitionTemplateForm" property="stdworkinghoursunitList" id="stdworkinghoursunitList" />
            <html:options collection="stdworkinghoursunitList" property="key"  labelProperty="value"/>
			</html:select>
			<html:text property="defaultStandardHours" size="4" maxlength="4" onblur="numberrequired()"/>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minexp",user1.getLocale())%></td>
			<td><html:text size="4" property="minyearsofExpRequired" maxlength="2" onblur="numberrequired()"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.maxexp",user1.getLocale())%></td>
			<td><html:text size="4" property="maxyearsofExpRequired" maxlength="2" onblur="numberrequired()"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minedu",user1.getLocale())%></td>
			<!-- <td><html:text size="50" property="minimumLevelOfEducation" maxlength="200"/></td>-->
			<td><html:select  property="educationName" onchange="otherselected();">
				
				<bean:define name="jobRequisitionTemplateForm" property="educationNamesList" id="educationNamesList" />
	            <html:options collection="educationNamesList" property="key"  labelProperty="value"/>
				
			</html:select>&nbsp;<span id="mineducation">
			<%if(jobreqtmplform.getEducationName() != null){ %>
				<%if(jobreqtmplform.getEducationName().equals("Other") ){ %>
					 <% if((edittemplate != null && edittemplate.equals("yes")) || (jobtemplatecreated != null && jobtemplatecreated.equals("yes")) || (updatejobtemplatecreated != null && updatejobtemplatecreated.equals("yes"))|| (saveastrue != null && saveastrue.equals("true"))|| (makeactive != null && makeactive.equals("true"))){%>
					 	 <% if(jobreqtmplform.getOtherMinimumLevelOfEducation() != null){%>
					 		<input  type='text' size="30" name='otherMinimumLevelOfEducation' maxlength="200" value=<%=jobreqtmplform.getOtherMinimumLevelOfEducation()%>>
		
						 <%} %>
					 <%} %>
				 <%} %>
			 <%} %> 
			<!--<div id="mineducation">Enter other Min level of education : <input type='text' name='otherMinimumLevelOfEducation' maxlength="200"></div>-->
			
	</td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.otherExp",user1.getLocale())%></td>
			<td><html:text size="46" property="otherExperience" maxlength="400"/></td>
		</tr>


<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionTemplateForm"  property="jobtypeId" onchange="disableDurationInMonths()">
			<bean:define name="jobRequisitionTemplateForm" property="jobtypeList" id="jobtypeList" />

            <html:options collection="jobtypeList" property="jobTypeId"  labelProperty="jobTypeName"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DurationinMonths",user1.getLocale())%></td>
			<td><html:text size="3" property="durationinmonths" maxlength="3" onblur="numberrequired()"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionTemplateForm"  property="workshiftId">
			<bean:define name="jobRequisitionTemplateForm" property="workshiftList" id="workshiftList" />

            <html:options collection="workshiftList" property="shiftId"  labelProperty="shiftName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.FLSA.Status",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionTemplateForm"  property="flsastatusId">
			<bean:define name="jobRequisitionTemplateForm" property="flsastatusList" id="flsastatusList" />

            <html:options collection="flsastatusList" property="flsaId"  labelProperty="flsaName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.compfreq",user1.getLocale())%></td>
			<td>
			<html:select name="jobRequisitionTemplateForm"  property="compfrequencyId">
			<bean:define name="jobRequisitionTemplateForm" property="compfrequencyList" id="compfrequencyList" />

            <html:options collection="compfrequencyList" property="compFrequencyId"  labelProperty="compFrequencyName"/>
			</html:select>
			</td>
		</tr>


		
	</table>
	</div>
		<!-- tab 2 end -->
		</div>
		<div id="tab3">
	<!-- tab 3 start -->	
<BR>
<b><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></b><br><br>
<div class="div">
<table border="0" width="100%">

    <tr>
	<td><b><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td>
	<td></td>
	</tr>
    <tr>
	<td>
	<html:text property="competencyname" styleId="competencyname" maxlength="500"/>
    </td>
	</td>
	<td>
	<input type="radio" name="iscompmandatory" value="on"><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%>  <input type="radio" name="iscompmandatory" value="off" checked> <%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%>
	</td>
	<td>
	<html:select  property="minimumrating">
			<bean:define name="jobRequisitionTemplateForm" property="skillRatingList" id="skillRatingList" />

            <html:options collection="skillRatingList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	<td>
	<%=Constant.getResourceStringValue("Requisition.Is.visible.in.JobDetails",user1.getLocale())%>	<%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isVisible" maxlength="10" value="Y">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isVisible"  value="N" checked>
	<a href="#" onClick="addCompetency();return false" class="button">Add</a>
	<span class="textboxlabel" id="loading4" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>
</div>
<br>
   <div class="div">

   <span id="competecydetails">

    <table border="0"  width="800px">
<%
List competencyList = jobreqtmplform.getComptetencyList();

%>
<% if(competencyList != null && competencyList.size()>0){%>
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td>
	<td><b>	<%=Constant.getResourceStringValue("Requisition.Is.visible.in.JobDetails",user1.getLocale())%></b></td>
	<td></td>
	
	</tr>



<%	
for(int i=0;i<competencyList.size();i++){
	JobTemplateCompetency jcomp = (JobTemplateCompetency)competencyList.get(i);
	String str="";
	if(jcomp.getImportance() == 6 ){
		str ="N/A";
	}else{
		str=String.valueOf(jcomp.getImportance()); 
	}
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>
<tr bgcolor=<%=bgcolor%>>

<td width="208px"><%=jcomp.getCharName()%></td>
<td width="97px"><%=(jcomp.getMandatory().equals("on"))?"Yes":"No"%></td>
<td width="162px"><%=str%></td>
<td><%=(jcomp.getIsVisible().equals("Y"))?"Yes":"No"%> </td>
<td width="50px"><a href="#" onClick="deletecompetency('<%=jcomp.getJbTmplcompId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete competency" title="delete competency" height="20"  width="19"/></a></td>
</tr>
<%
}
}else{%>
<!--Competency not added.-->
<%}%>

    </table>
	</span>

</div>
<BR>

<b><%=Constant.getResourceStringValue("Requisition.Accomplishments",user1.getLocale())%></b><br><br>
<div class="div">
<table border="0" width="100%">

    <tr>
	<td><b><%=Constant.getResourceStringValue("Requisition.Accomplishments",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<!--  <td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td><td></td>-->
	</tr>
    <tr>
	<td>
	<html:text property="accomplishmentname" styleId="accomplishmentname" maxlength="500"/>
    </td>
	</td>
	<td width="380px">
	<input type="radio" name="isaccommandatory" value="on"> <%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%> <input type="radio" name="isaccommandatory" value="off" checked> <%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%>
	</td>
	<!--  <td>
	<html:select  property="minimumratingaccom">
			<bean:define name="jobRequisitionTemplateForm" property="skillRatingList" id="skillRatingList" />

            <html:options collection="skillRatingList" property="key"  labelProperty="value"/>
			</html:select>
	</td>-->
	<td><a href="#" onClick="addAccomplishment();return false" class="button">Add</a>
	<span class="textboxlabel" id="loading5" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>
	</div>
	<br>
	<div class="div">
   <span id="accomplishmentdetails">
    <table border="0" width="800px">
<%
List accomplishmentList = jobreqtmplform.getAccomplishmentList();

%>
<% if(accomplishmentList != null && accomplishmentList.size()>0){%>
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("Requisition.Accomplishments",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<!-- <td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td><td></td> -->
	
	</tr>



<%	
for(int i=0;i<accomplishmentList.size();i++){
	JobTemplateAccomplishment jtacc = (JobTemplateAccomplishment)accomplishmentList.get(i);
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>
<tr bgcolor=<%=bgcolor%>>

<td width="132px" wrap><%=jtacc.getAccName()%></td><td width="180px"><%=(jtacc.getMandatory().equals("on"))?"Yes":"No"%></td><!--  <td width="250px"><%=jtacc.getImportance()%></td>-->
<td width="50px"><a href="#" onClick="deleteAccomplishment('<%=jtacc.getJbTmplAccId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete competency" title="delete competency" height="20"  width="19"/></a></td>
</tr>
<%
}
}else{%>
<!--Competency not added.-->
<%}%>

    </table>
	</span>
	</div>





<!-- tab 3 end -->		
		</div>
        <div id="tab4" class="div">
		<!-- tab 4 start -->	
<BR>
		<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.Approvers",user1.getLocale())%></b></legend>
<table border="0" width="550px">

    
    <tr>
	<td>
	<input type="hidden" name="approverNamehidden">
	
	 <input type="text" id="approverName" name="approverName" maxlength="500" autocomplete="off" onblur="validateUser()">
	<input type="hidden" name="approverId" />
	<input type="hidden" name="isgroup" />
	<a href="#" onClick="opensearchapprover()"><img src="jsp/images/selector.gif" border="0"/></a>

    </td>

	<td><a href="#" onClick="addApprovers()" class="button">Add</a>
	<span class="textboxlabel" id="loading6" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>
		</fieldset>
		<br>
		<div class="div">

   <span id="approverdetails">
    <table border="0" width="800px">
<%
List approverList = jobreqtmplform.getApproversList();

%>
<% if(approverList != null && approverList.size()>0){%>
	<%
	if(approveralreadyadded != null && approveralreadyadded.equals("yes")){
	%>
	<tr>
			<td colspan="4">
			<font color="red"> <b><%=Constant.getResourceStringValue("Requisition.approver.alreadyaded",user1.getLocale())%></b> </font>

			</td>
	</tr>
	<%}%>
    <tr>
	
	<td width="50px"><b><%=Constant.getResourceStringValue("Requisition.Level",user1.getLocale())%></b></td>
	<td width="300px"><b><%=Constant.getResourceStringValue("Requisition.Users",user1.getLocale())%></b></td>
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
			approverurl = "<img src='jsp/images/User-Group-icon.png'><a href='#' onClick=window.open("+"'"+"usergroup.do?method=editusergroup&readPreview=2&usergroupid="+japp.getUserId()+"'"+","+ "'"+"userperview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+japp.getApproverName()+"</a>";
		}else{
			approverurl = "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+japp.getUserId()+"'"+","+ "'"+"userperview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+japp.getApproverName()+"</a>";
		}

		}

		%>


<td width="50px"><%=k%></td>
<td width="300px" wrap><%=approverurl%></td>

<td width="50px">
<a href="#" onClick="deleteApprovers('<%=japp.getJbTmplApproverId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete approvers" title="delete approvers" height="20"  width="19"/></a>

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

<%
	List errorlist = (List)request.getAttribute(Common.ERROR_LIST);
%>
<% if(errorlist != null && errorlist.size()>0){
	for(int i=0;i<errorlist.size();i++){
		String errormsttmp = (String)errorlist.get(i);
%>
<li><font color="red"><%=errormsttmp%></font></li>

<%}}%>


<div id="tab5" class="div">
<!-- tab 5 start -->
<%@ include file="customvariableReqTmpl.jsp" %>
<!-- tab 6 end -->	
</div>
	<%if(!StringUtils.isNullOrEmpty(user1.getPackagetaken()) && Constant.isPackageContainFunction(user1.getPackagetaken(),"filter")){%>
<div id="tab6" class="div">
<!-- tab 5 start -->
<%@ include file="filtersReqTmpl.jsp" %>
<!-- tab 6 end -->	
</div>
<%}%>

    </div>
</div>



</html:form>



<script>
(function() {
    var tabView = new YAHOO.widget.TabView('demo');
    
    var addTab = function() {
        var labelText = window.prompt('<%=Constant.getResourceStringValue("Requisition.promptmsg",user1.getLocale())%>');
        var content = window.prompt('<%=Constant.getResourceStringValue("Requisition.promptmsg1",user1.getLocale())%>');
        if (labelText && content) {
            tabView.addTab( new YAHOO.widget.Tab({ label: labelText, content: content }) );
        }
    };

    //var button = document.createElement('button');
    //button.innerHTML = 'add tab';

    //YAHOO.util.Event.on(button, 'click', addTab);
    //tabView.appendChild(button);

    //YAHOO.log("The example has finished loading; as you interact with it, you'll see log messages appearing here.", "info", "example");
})();
</script>


<%@include file="/jsp/common/tooltipnew.jsp" %>

<div id="shiny_box" class="shiny_box" style="display:none;">
	<span class="tl"></span><span class="tr"></span>
	<div class="shiny_box_body"></div>
	<span class="bl"></span><span class="br"></span>
</div>



<script>
$().ready(function(){
	$('.titleHintBox').inputHintBox({div:$('#shiny_box'),div_sub:'.shiny_box_body',source:'attr',attr:'title',incrementLeft:5});
	$('.titleHintBox2').inputHintBox({className:'simple_box',source:'attr',attr:'title',incrementLeft:5});
	$('.titleHintBox3').inputHintBox({className:'simple_box',html:'Same text for more inputs',incrementLeft:5});
});
</script>
</body>