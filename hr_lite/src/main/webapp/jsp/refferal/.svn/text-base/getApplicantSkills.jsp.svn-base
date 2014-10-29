<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
System.out.println("test");

%>

<html:form action="/applicant.do?method=getApplicantSkills" >
<div class="div">
<span id="skilldetails">
    <table border="0" width="100%">

    <tr>
	
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Rating",user1.getLocale())%></b></td><td></td>
	
	</tr>


<%
List skillList = aform.getApplicantSkillList();

%>
<% if(skillList != null && skillList.size()>0){
	
for(int i=0;i<skillList.size();i++){
	ApplicantSkills skil = (ApplicantSkills)skillList.get(i);
%>
<tr>

<td><input type="hidden" name="skilName" value="<%=skil.getSkillname()%>"/><%=skil.getSkillname()%></td><td><%=skil.getYearsofexp()%></td><td><%=skil.getRating()%></td><td><a href="#" onClick="deleteskill('<%=skil.getSkillId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete skill" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_skill",user1.getLocale())%>" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Skills_not_added",user1.getLocale())%>
<%}%>

    </table>
	</span>
</div>
</html:form>
</html:html>


