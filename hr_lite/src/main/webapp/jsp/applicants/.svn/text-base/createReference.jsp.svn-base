<%@ include file="../common/include.jsp" %>
 <%
 ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="form" name="referenceForm" type="com.form.ReferenceForm" />
<style>
span1{color:#ff0000;}
</style>
<script language="javascript">
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   // self.parent.location.reload();
		//parent.parent.GB_hide();
		  window.opener.location.reload(true);
		  window.close();
	   }
	   
	   //ShowMessage();
	}
function closerefresh(){
	
	window.opener.location.reload(true);


  window.close();
	
}

function init(){
setTimeout ( "document.referenceForm.referenceeName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}


function savedata(){
	var alertstr = "";
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 
    var Name = document.referenceForm.referenceeName.value.trim();
	var email = document.referenceForm.referenceeEmail.value.trim();

	var showalert=false;

	if(Name == "" || Name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
		}
	 

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.referenceForm.action = "reference.do?method=savereferenceData&applicantId=<%=form.getApplicantId()%>";
   document.referenceForm.submit();
  
	}


	function updatedate(){
		var alertstr = "";
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 
    var Name = document.referenceForm.referenceeName.value.trim();
	var email = document.referenceForm.referenceeEmail.value.trim();

	var showalert=false;

	if(Name == "" || Name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
		}
	 

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.referenceForm.action = "reference.do?method=updatereferencescreen&referenceId=<%=form.getApplicantReferenceId()%>";
   document.referenceForm.submit();
	}


	

function opensearchassignedto(){
    var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=refassign");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("user.do?method=assignedtoselector&boxnumber=refassign", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchquestiongroup(){
  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=questiongroupselector&boxnumber=questiongroup");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 // window.open("lov.do?method=questiongroupselector&boxnumber=questiongroup", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function closewindow(){
 self.parent.location.reload();
	 parent.parent.GB_hide();
  
	}


	function deleteref(){
	
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		
	   document.referenceForm.action = "reference.do?method=deletereference&referenceId=<%=form.getApplicantReferenceId()%>";
	  document.referenceForm.submit();

	   } 
	}


</script>


<%
String applicantrefsaved = (String)request.getAttribute("applicantrefsaved");
String applicantrefdeleted = (String)request.getAttribute("applicantrefdeleted");

if(applicantrefsaved != null && applicantrefsaved.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("applicant.Refrences.Reference_is_saved",user1.getLocale())%></font>
			<a href="#" onclick="closerefresh()">close window</a>
			</td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
if(applicantrefdeleted != null && applicantrefdeleted.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.ReferenceDelete",user1.getLocale())%></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%} else {%>

<body>
<html:form action="/reference.do?method=savereferenceData">
<body onload="init()">


<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.add_references",user1.getLocale())%></td>
			<td></td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.name",user1.getLocale())%><span1>*<span1></td>
			<td><html:text property="referenceeName" size="50" maxlength="600"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.Working_Organization",user1.getLocale())%></td>
			<td><html:text property="referenceeOrganization" size="50" maxlength="600"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.Designation",user1.getLocale())%></td>
			<td><html:text property="referenceeDesignation" size="50" maxlength="600"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.Phone",user1.getLocale())%></td>
			<td><html:text property="referenceePhone" size="50" maxlength="600"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.Email",user1.getLocale())%><span1>*<span1></td>
			<td><html:text property="referenceeEmail" size="50" maxlength="600"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.Relation_with_applicant",user1.getLocale())%></td>
			<td><html:text property="referenceeRelation" size="50" maxlength="600"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("applicant.Refrences.notes",user1.getLocale())%></td>
			<td><html:textarea property="note" cols="50" rows="3" /></td>
		</tr>


		
<tr>
			<td>
			
			<% if(form.getApplicantReferenceId() > 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedate()">
			
			<%} else {%>
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
			

			</td>
		</tr>
		</table>

</div>

</html:form>

<%}%>