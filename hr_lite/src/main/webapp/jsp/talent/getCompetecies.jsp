<%@ include file="../common/include.jsp" %>
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="jobreqtmplform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />


<html:form action="/jobtemplate.do?method=getApplicantSkills" >

 <span id="competecydetails">
    <table border="0" width="800px">
<%
List competencyList = jobreqtmplform.getComptetencyList();

%>
<% if(competencyList != null && competencyList.size()>0){%>
    <tr>
	
	<td ><b>Competency</b></td><td ><b>Is mandatory ?</b></td><td ><b>Minimum required rating</b></td><td><b>Is visible in job details?</b></td><td></td>
	
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

</html:form>
</html:html>


