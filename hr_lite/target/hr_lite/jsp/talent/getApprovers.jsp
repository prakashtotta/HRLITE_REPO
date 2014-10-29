<%@ include file="../common/include.jsp" %>
<bean:define id="jobreqtmplform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
String approveralreadyadded = (String)request.getAttribute("approveralreadyadded");
//String approveralreadyadded = jobreqtmplform.getApproveralreadyadded();
%>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<body>
<html:form action="/jobtemplate.do?method=getApprovers" >

 <span id="approverdetails">

  <table border="0" bgcolor="#f3f3f3" width="800px">
<%
List approverList = jobreqtmplform.getApproversList();

%>
<% if(approverList != null && approverList.size()>0){%>
	<%
	
		System.out.println("approver add : "+approveralreadyadded);	
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
	<td width="100px"><b><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Status",user1.getLocale())%></b></td>
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
<td width="100px" wrap><%=(japp.getApproved() != null && japp.getApproved().equals("Y"))?Constant.getResourceStringValue("Requisition.approved",user1.getLocale()):Constant.getResourceStringValue("Requisition.Not_Approved",user1.getLocale())%></td>
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
</html:form>
</body>
</html:html>


