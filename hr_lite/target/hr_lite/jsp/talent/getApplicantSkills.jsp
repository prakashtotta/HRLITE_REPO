<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
String datepattern = Constant.getValue("defaultdateformat");


com.bean.Locale locale= VariableDataCaptureUtil.getLocale(request);

System.out.println("locale"+locale);



String skillalreadyadded = (String)request.getParameter("skillalreadyadded");

%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />


<html:form action="/applicant.do?method=getApplicantSkills" >

<span id="skilldetails">
    <table border="0" width="100%">
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Skill",locale)%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",locale)%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Rating",locale)%></b></td><td></td>
	
	</tr>


<%

List skillList = aform.getApplicantSkillList();


%>
<% if(skillList != null && skillList.size()>0){
	
for(int i=0;i<skillList.size();i++){
	ApplicantSkills skil = (ApplicantSkills)skillList.get(i);
%>
<tr>

<td><input type="hidden" name="skilName" value="<%=skil.getSkillname()%>"/><%=skil.getSkillname()%></td><td><%=skil.getYearsofexp()%></td><td><%=skil.getRating()%></td><td><a href="#" onClick="deleteskill('<%=skil.getUuid()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete skill" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_skill",locale)%>" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Skills_not_added",locale)%>
<%}%>

    </table>
	</span>

</html:form>
</html:html>


