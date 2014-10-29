<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="jobreqtmplform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />


<html:form action="/jobtemplate.do?method=getApplicantSkills" >

   <span id="accomplishmentdetails">
    <table border="0"  width="800px">
<%
List accomplishmentList = jobreqtmplform.getAccomplishmentList();

%>
<% if(accomplishmentList != null && accomplishmentList.size()>0){%>
    <tr>
	
	<td ><b>Accomplishments</b></td><td ><b>Is mandatory ?</b></td><!--  <td width="250px"><b>Minimum rating required?</b></td><td width="50px"></td>-->
	
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

</html:form>
</html:html>


