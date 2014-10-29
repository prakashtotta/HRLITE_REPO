<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

%>



<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}
</style>
<body>
<html:form action="/user.do?method=logon" enctype="multipart/form-data">

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications",user1.getLocale())%></legend>

<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.Education",user1.getLocale())%></legend>
			<%
				String addUserEducationUrl = request.getContextPath()+"/usereducationdetails.do?method=addUserEducation&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserEducationUrl%>','Education');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            <br><br>
            <table border="0" width="100%">


		<%
		List userEducationList = cform.getUserEducationDetailsList();
		
		if(userEducationList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">
		 	<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form)" name="masterCheck"></td>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.name",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.Specialization",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.College_University",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.Percentage_Grade",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.start_month_year",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.passing_month_year",user1.getLocale())%></b></td>
			
			</tr>
			
			
		<%
			for(int i = 0; i< userEducationList.size(); i++){
				UserEducationDetails userEducationDetails = (UserEducationDetails)userEducationList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserQualificationURl = request.getContextPath()+"/usereducationdetails.do?method=editUserQualificationDetails&educationId="+userEducationDetails.getEducationId()+"&userId="+cform.getUserId();		
				
		%>
		

			<tr bgcolor=<%=bgcolor%>>
			<td align="center"><input type="checkbox" id="isActive"  name="isActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserQualificationDetails('<%=editUserQualificationURl%>','Education');return false"><%=userEducationDetails.getEducationName()  == null ?"": userEducationDetails.getEducationName()%></a></td>
				<td>&nbsp;<%=userEducationDetails.getSpecialization() == null ?"":userEducationDetails.getSpecialization() %></td>
				<td>&nbsp;<%=userEducationDetails.getInstituteName() == null ?"":userEducationDetails.getInstituteName() %></td>
				<td>&nbsp;<%=userEducationDetails.getPercentile() == null ?"":userEducationDetails.getPercentile() %></td>
				<td>&nbsp;<%=userEducationDetails.getStartingYear() == null ?"":userEducationDetails.getStartingYear()%></td>
				<td>&nbsp;<%=userEducationDetails.getPassingYear() == null ?"":userEducationDetails.getPassingYear()%></td>
			</tr>

			
		<%
			}
		}else{
		
		
		%>
		<tr><td><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>


</table>
            



</fieldset>
<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.Skills",user1.getLocale())%></legend>
			<%
				String addUserSkillsUrl = request.getContextPath()+"/userskills.do?method=addUserSkills&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserSkillsUrl%>','Skills');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            <br><br>
            
              <table border="0" width="100%">


		<%
		List userskillsList = cform.getUserSkillsDetailsList();
		
		if(userskillsList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">
		 	<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form)" name="masterCheck"></td>
				<td width="40%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.YearsOfExperience",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.rating",user1.getLocale())%></b></td>
				
			</tr>
			
			
		<%
			for(int i = 0; i< userskillsList.size(); i++){
				UserSkills userSkills = (UserSkills)userskillsList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserQualificationURl = request.getContextPath()+"/userskills.do?method=editUserSkillDetails&skillId="+userSkills.getSkillId()+"&userId="+cform.getUserId();		
				
		%>
		

			<tr bgcolor=<%=bgcolor%>>
			<td align="center"><input type="checkbox" id="isActive"  name="isActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserQualificationDetails('<%=editUserQualificationURl%>','Skills');return false"><%=userSkills.getSkillname()  == null ?"": userSkills.getSkillname()%></a></td>
				<td>&nbsp;<%=userSkills.getYearsofexp() == null ?"":userSkills.getYearsofexp()  %></td>
				<td>&nbsp;<%=userSkills.getRating()  == null ?"":userSkills.getRating() %></td>
				
			</tr>

			
		<%
			}
		}else{
		
		
		%>
		<tr><td><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>
		


</table>

</fieldset>
<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.Languages",user1.getLocale())%></legend>
			<%
				String addUserLanguages = request.getContextPath()+"/userlanguages.do?method=addUserLanguages&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserLanguages%>','Languages');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            <br><br>
            
            
                          <table border="0" width="100%">


		<%
		List userlanguagesList = cform.getUserLanguagesDetailsList();
		
		if(userlanguagesList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">
		 	<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form)" name="masterCheck"></td>
				<td width="40%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Language",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.Fluency",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.Competency",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.Comments",user1.getLocale())%></b></td>
				
			</tr>
			
			
		<%
			for(int i = 0; i< userlanguagesList.size(); i++){
				UserLanguages userLanguages = (UserLanguages)userlanguagesList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserQualificationURl = request.getContextPath()+"/userlanguages.do?method=editUserLanguageDetails&userLangId="+userLanguages.getUserLangId()+"&userId="+cform.getUserId();		
				
		%>
		

			<tr bgcolor=<%=bgcolor%>>
			<td align="center"><input type="checkbox" id="isActive"  name="isActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserQualificationDetails('<%=editUserQualificationURl%>','Languages');return false"><%=userLanguages.getLanguage()  == null ?"": userLanguages.getLanguage().getLanguageName() %></a></td>
				<td>&nbsp;<%=userLanguages.getFluency() == null ?"":userLanguages.getFluency()  %></td>
				<td>&nbsp;<%=userLanguages.getRating()  == null ?"":userLanguages.getRating()%></td>
				<td>&nbsp;<%=userLanguages.getComment()  == null ?"":userLanguages.getComment() %></td>
			</tr>

			
		<%
			}
		}else{
		
		
		%>
		<tr><td><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>

</table>

</fieldset>
<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.License",user1.getLocale())%></legend>
			<%
				String addUserLiacence = request.getContextPath()+"/userlicenses.do?method=addUserLicenseDetails&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserLiacence%>','License');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            <br><br>
...... under construction ...... >>>>>>>>>>>>>>>...


</fieldset>

<br><br>

</fieldset>
</span>
</html:form>
</body>