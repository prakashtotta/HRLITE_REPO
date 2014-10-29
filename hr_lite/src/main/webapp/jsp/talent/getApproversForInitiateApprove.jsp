<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);

%>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="jobreqtmplform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />


<html:form action="/jobtemplate.do?method=getApproversForInitiate" >

    <span id="approverdetails">
    <table border="0" bgcolor="#f3f3f3" width="100%">
<%
List approverList = jobreqtmplform.getApproversList();

%>
<% if(approverList != null && approverList.size()>0){%>
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
        
    approverurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+japp.getUserId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=430"+"'"+")>"+japp.getApproverName()+"</a>";

	

		%>

<td width="50px"><%=k%></td><td width="350px" wrap><%=approverurl%></td>
<td width="100px"><%=japp.getIsFromSystemRule()%></td>
<td width="50px">
<% if(japp.getIsFromSystemRule() != null && japp.getIsFromSystemRule().equals("Y")){
}else{	
%>
<a href="#" onClick="deleteApprovers('<%=japp.getJbTmplApproverId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete approvers" title="delete approvers" height="20"  width="19"/></a>
<%}%>
</td>
</tr>
<%
	k++;
}
}%>


    </table>
	</span>
</html:form>
</html:html>


