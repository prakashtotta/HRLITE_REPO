 <%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

 <title><%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%> </title>
<bean:define id="cform" name="applicantUserActionsForm" type="com.form.ApplicantUserActionsForm" />

<style>
span1{color:#ff0000;}
</style>
 <script language="javascript">

function closewindow(){
	 	   parent.parent.GB_hide();
	  
	}
function closewindow1(){
	 	 parent.parent.GB_hide();

	  
	}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
	   } 
	}

function deleteappuseraction(){

var alertstr = "";
	var showalert=false; 
	var name=document.applicantUserActionsForm.creatorComment.value.trim();

		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%> <br>";
		showalert = true;
		}
		 if (showalert){
     	alert(alertstr);
        return false;
          }

	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){


		    document.applicantUserActionsForm.action = "applicantUserActions.do?method=deleteapplicantactions&actionid=<%=cform.getAppuseractionId()%>&applicantId=<%=cform.getApplicantId()%>&uuid=<%=cform.getUuid()%>";
   document.applicantUserActionsForm.submit();
	  }
}

	function savedata(){
		var alertstr = "";
	var showalert=false; 
	var name=document.applicantUserActionsForm.creatorComment.value.trim();

		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%> <br>";
		showalert = true;
		}
		 if (showalert){
     	alert(alertstr);
        return false;
          }
		
	  document.applicantUserActionsForm.action = "applicantUserActions.do?method=addapplicantactions&applicantId=<%=cform.getApplicantId()%>&uuid=<%=cform.getUuid()%>";
   document.applicantUserActionsForm.submit();
	}


	function updatedata(){
		var alertstr = "";
	var showalert=false; 
	var name=document.applicantUserActionsForm.creatorComment.value.trim();

		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%> <br>";
		showalert = true;
		}
		 if (showalert){
     	alert(alertstr);
        return false;
          }
		
	  document.applicantUserActionsForm.action = "applicantUserActions.do?method=updateapplicantactions&actionid=<%=cform.getAppuseractionId()%>&applicantId=<%=cform.getApplicantId()%>&uuid=<%=cform.getUuid()%>";
   document.applicantUserActionsForm.submit();
	}


	function opensearchquestiongroup(){
  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=questiongroupselector&boxnumber=questiongroup");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
   }


</script>


<%
String isactionadded = (String)request.getAttribute("isactionadded");
String isactiondeleted = (String)request.getAttribute("isactiondeleted");
String isactionalreadyadded = (String)request.getAttribute("isactionalreadyadded");

%>
<html:form action="/applicantUserActions.do?method=addapplicantactions" >
<% if(isactionalreadyadded != null && isactionalreadyadded.equals("yes")){%>
<div class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.applicant.applicant.actions.already.added",user1.getLocale())%></font></font></td>
			<td></td>
		</tr>
		
	</table>


</div>
<%}%>	
<%
if(isactionadded != null && isactionadded.equals("yes")){
%>
<div class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.applicant.applicant.actions.saved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>
<%}%>
<%
if(isactiondeleted != null && isactiondeleted.equals("yes")){%>
<div class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.applicant.applicant.actions.deleted",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>
<%}else{%>

<br>
<div class="div">



	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
 
 <tr>
	  <td></td><td></td>
	  </tr><tr></tr><tr></tr><tr></tr>



		<tr>
			<td><%=Constant.getResourceStringValue("hr.applicant.applicant.actions",user1.getLocale())%></td>
			<td>
			<html:select  property="actionName" >
			<bean:define name="applicantUserActionsForm" property="actionsList" id="actionsList" />

            <html:options collection="actionsList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:textarea property="creatorComment" cols="60" rows="5"/>
			</td>
		</tr>

</table>
<table width="100%">
		<tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			
			<td>
			<% if(cform.getAppuseractionId()>0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteappuseraction()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">	
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
				
			</td>
		</tr>
</table>
</div>
<%}%>
</html:form>

