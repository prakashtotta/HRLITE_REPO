<%@ include file="../common/include.jsp" %>
<%@ include file="../common/illegelredirection.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);

%>
<html>
<title><%=Constant.getResourceStringValue("Requisition.initiate.approval",user1.getLocale())%></title>
<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />

<script language="javascript"> 

window.name = 'myModal';
</script>


<script language="javascript">

function discard(){
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	 window.close();
	}
}
function closewindow(){
	//window.opener.location.reload(true);
	//window.opener.document.location.href="jobreq.do?method=editjobreq&jobreqId=<%=jobreqform.getJobreqId()%>";
	//window.opener.document.location.href=window.opener.document.location.href;
	 window.close();
	
}

function publishRequistion(){
	var url = "jobreq.do?method=publishJobReqscr&jobreqId=<%=jobreqform.getJobreqId()%>";
  

  
  //window.open(url1, "SearchJobCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  window.showModalDialog(url,"<%=Constant.getResourceStringValue("Requisition.Publish.requistion",user1.getLocale())%>","dialogHeight: 634px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");
   
    window.close();
}

function savedata(){
	if (document.jobRequisitionForm.comment.value.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
	}

		document.jobRequisitionForm.action = "jobreq.do?method=initiateApproval&jobreqId=<%=jobreqform.getJobreqId()%>";
		document.jobRequisitionForm.submit();
	//window.location.reload(true);
	 //window.close();
	  
	
}


function deleteApprovers(id){

	var url="jobtemplate.do?method=deleteApprovers&id="+id;
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		$.ajax({
			type: 'GET',
		  url: url,
		  success: function(data){
		  $('#approverdetails').html(data);
			//completeajx();
		  }
		});

   		setTimeout("retrieveApprovers()",200);
	  }
}
function retrieveApprovers() {
		
    //document.getElementById("loading6").style.visibility = "visible";
	var url="jobtemplate.do?method=getApproversForInitiate&id=<%=jobreqform.getJobreqId()%>&type=job";
	$.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  $('#approverdetails').html(data);
		//completeajx();
	  }
	});

}


</script>

<%
String mandatoryValuesNotFilled = (String)request.getAttribute("mandatoryValuesNotFilled");
String initiateApproval = (String)request.getAttribute("initiateApproval");
if(mandatoryValuesNotFilled != null && mandatoryValuesNotFilled.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><b><%=Constant.getResourceStringValue("requisition.Initiated.approval.mandatory.values.not.filled",user1.getLocale())%></b></font>
			<a href="#" onClick="closewindow()"><font color="white"><b><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
			<td> </td>
		</tr>
		
	</table>
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


<%
}else if(initiateApproval != null && initiateApproval.equals("yes")){

		List approversListReq = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(jobreqform.getJobreqId(), "job");
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td> <font color="white"> <%=Constant.getResourceStringValue("requisition.Initiated.approval.success",user1.getLocale())%></font>
			<% if(approversListReq != null && approversListReq.size()>0){%>
			<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>
			<%}%>
			</td>
			<% if(approversListReq != null && approversListReq.size()==0){%>
			<td><a class="button" href="#" onClick="publishRequistion()">Publish Job</a> </td>
			<%}else{%>
			<td></td>
			<%}%>

		</tr>

				
	</table>
</div>
<%}else{%>

<html:form action="jobreq.do?method=initiateApproval" target='myModal'>
<BR>

<%
List approverList = jobreqform.getApproversList();

%>
<% if(approverList != null && approverList.size()>0){%>
	<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.Approvers",user1.getLocale())%></b></legend>

   <span id="approverdetails">
    <table border="0" bgcolor="#f3f3f3" width="100%">
    <tr>
	
	<td width="50px"><b><%=Constant.getResourceStringValue("Requisition.Level",user1.getLocale())%></b></td><td width="350px"><b><%=Constant.getResourceStringValue("Requisition.Users",user1.getLocale())%></b></td>
	<td width="150px"><b><%=Constant.getResourceStringValue("hr.requistion.approver.system.defind",user1.getLocale())%></b></td>
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
        if(!StringUtils.isNullOrEmpty(japp.getIsGroup()) && japp.getIsGroup().equals("Y")){
			approverurl = "<img src='jsp/images/User-Group-icon.png'><a href='#' onClick=window.open("+"'"+"usergroup.do?method=editusergroup&readPreview=2&usergroupid="+japp.getUserId()+"'"+","+ "'"+"userperview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=430"+"'"+")>"+japp.getApproverName()+"</a>";
		}else{
			approverurl = "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+japp.getUserId()+"'"+","+ "'"+"userperview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=430"+"'"+")>"+japp.getApproverName()+"</a>";
		}
    

	

		%>
<td width="50px"><%=k%></td><td width="350px" wrap><%=approverurl%></td>


<td width="100px"><%=japp.getIsFromSystemRule()%></td>
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
%>
    </table>
	</span>

	</fieldset>
<%
}else{%>

<table><tr><td><font color="red">
No requisition approval rule is configured - so 
this requisition can be published without any approvals.
</font>
</td></tr></table>
<%}%>
<br>
<div class="div">
<table>
<tr>
<td width="10%">
<%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%>
</td>
<td >
<TEXTAREA NAME="comment" COLS=70 ROWS=4></TEXTAREA>
</td>
</tr>

<tr>
<td colspan="2">
	<input type="button" name="login1" value="<%=Constant.getResourceStringValue("aquisition.applicant.Initiate",user1.getLocale())%>" onclick=" return savedata()" class="button">
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
</td>

</tr>
</table>
</div>
</html:form>

<%}%>