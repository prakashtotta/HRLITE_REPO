<%@ include file="../common/include.jsp" %>
<%@ page import="com.dao.*"%>
<%@ page import="com.bean.Locale;"%>
<%
String refuserid = (String)request.getAttribute("refuserid");
System.out.println("refuserid "+refuserid);
com.bean.Locale locale = null;
if(refuserid != null && !refuserid.equals("null")&& !refuserid.equals("external-jobpost")){
RefferalEmployee refEmp = RefferalDAO.getRefferalEmployee(refuserid);
 locale= refEmp.getLocale();
}else{
	String locale_code = (String)session.getAttribute("locale_code");
	 locale= new com.bean.Locale();
	locale.setLocaleCode(locale_code);
}
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
System.out.println("test");

%>

<html:form action="/refferaluser.do?method=getApplicantSkills" >
<div class="div">
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

<td><input type="hidden" name="skilName" value="<%=skil.getSkillname()%>"/><%=skil.getSkillname()%></td><td><%=skil.getYearsofexp()%></td><td><%=skil.getRating()%></td><td><a href="#" onClick="deleteskill('<%=skil.getSkillId()%>');return false;"><img src="jsp/images/delete.gif" border="0" alt="delete skill" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_skill",locale)%>" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
<%=Constant.getResourceStringValue("aquisition.applicant.Skills_not_added",locale)%>
<%}%>

    </table>
	</span>
</div>
</html:form>
</html:html>


