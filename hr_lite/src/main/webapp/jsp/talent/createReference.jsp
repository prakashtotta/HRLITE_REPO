<%@ include file="../common/include.jsp" %>
 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
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
	   self.parent.location.reload();
	 parent.parent.GB_hide();
	   }
	   
	   //ShowMessage();
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
	var revw = document.referenceForm.assignedtoid.value.trim();
	var qestionset = document.referenceForm.questiongroupId.value.trim();
	var showalert=false;

	if(Name == "" || Name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
		}
	 
	if(revw == "" || revw=="0" || revw == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(qestionset == "" || qestionset=="0" || qestionset == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_set_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.referenceForm.action = "reference.do?method=savereference&applicantId=<%=form.getApplicantId()%>";
   document.referenceForm.submit();
  
	}

	function updatedate(){
		var alertstr = "";
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 
    var Name = document.referenceForm.referenceeName.value.trim();
	var email = document.referenceForm.referenceeEmail.value.trim();
	var revw = document.referenceForm.assignedtoid.value.trim();
	var qestionset = document.referenceForm.questiongroupId.value.trim();
	var showalert=false;

	if(Name == "" || Name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Invalid_Email_ID",user1.getLocale())%><BR>";
		showalert = true;
		}
	 
	if(revw == "" || revw=="0" || revw == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(qestionset == "" || qestionset=="0" || qestionset == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_set_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	//alert("<%=form.getApplicantReferenceId()%>");
	  document.referenceForm.action = "reference.do?method=updatereference&referenceId=<%=form.getApplicantReferenceId()%>";
      document.referenceForm.submit();
	}


	

function opensearchassignedto(){
    var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=refassign");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=650,height=600");

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
String applicantrefupdated= (String)request.getAttribute("applicantrefupdated");
String applicantrefdeleted = (String)request.getAttribute("applicantrefdeleted");

if(applicantrefsaved != null && applicantrefsaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("applicant.Refrences.Reference_is_saved",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
if(applicantrefupdated != null && applicantrefupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("applicant.Refrences.Reference_is_updated",user1.getLocale())%></font></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
if(applicantrefdeleted != null && applicantrefdeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("applicant.Refrences.ReferenceDelete",user1.getLocale())%></td>
			<td></td>
		</tr>
		
	</table>
</div>

<%} else {%>
<br>
<body>
<html:form action="/reference.do?method=savereference">
<body onload="init()">


<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	
	 
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

		<%
		String offerownervalue ="<span id=\"assignedto\"></span>";
      
  String offerownertempurl = "";

if(form.getAssignedTo()>0){

	  offerownertempurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+form.getAssignedTo()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+form.getAssignedToName()+"</a>";

}

 offerownervalue = "<span id=\"assignedto\">"+offerownertempurl+"</span>";


		%>

      <tr>
					<td><%=Constant.getResourceStringValue("applicant.Refrences.Reviewer",user1.getLocale())%><span1>*<span1></td>

		
				<td>
				<%=offerownervalue%>
<a href="#" onClick="opensearchassignedto()"><img src="jsp/images/selector.gif" border="0"/></a>
<input type="hidden" name="assignedtoid" value="<%=form.getAssignedTo()%>"/>
<input type="hidden" name="assignedtoname" value="<%=form.getAssignedToName()%>"/>

				</td>
			</tr>


<%
		String qnsgrpvalue ="<span id=\"questiongroup\"></span>";
      
  String qnsgrptempurl = "";

 

if(form.getQuestiongroupid()>0){

	  qnsgrptempurl = "<a href='#' onClick=window.open("+"'"+"questiongroup.do?method=questionsGroupsummary&readPreview=2&questiongroupId="+form.getQuestiongroupid()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+form.getQuestiongroupName()+"</a>";
}

 qnsgrpvalue = "<span id=\"questiongroup\">"+qnsgrptempurl+"</span>";


		%>

      <tr>
					<td><%=Constant.getResourceStringValue("applicant.Refrences.Questions_set",user1.getLocale())%><span1>*<span1></td>

		
				<td>
				<%=qnsgrpvalue%>
<a href="#" onClick="opensearchquestiongroup()"><img src="jsp/images/selector.gif" border="0"/></a>
<input type="hidden" name="questiongroupId" value="<%=form.getQuestiongroupid()%>"/>
<input type="hidden" name="questiongroupName" value="<%=form.getQuestiongroupName()%>"/>

				</td>
			</tr>

		
<tr>
			<td>
			
			<% if(form.getApplicantReferenceId() > 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedate()" class="button">
			
			<%} else {%>
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			
			<% if(form.getApplicantReferenceId() > 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteref()" class="button">
			<%} %>
			</td>
		</tr>
		</table>

</div>

</html:form>

<%}%>