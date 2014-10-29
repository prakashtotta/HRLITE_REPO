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

<br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.Education",user1.getLocale())%></legend>
<%
String deleteUserEducationMultiple = (String)request.getAttribute("deleteUserEducationMultiple");
	
if(deleteUserEducationMultiple != null && deleteUserEducationMultiple.equals("yes")){
%>
<div align="left">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("hr.user.usereducation.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		List userEducationList = cform.getUserEducationDetailsList();
		 String usereducatonids="";
		if(userEducationList.size() > 0){
			for(int i = 0; i< userEducationList.size(); i++){
				UserEducationDetails userEducationDetails = (UserEducationDetails)userEducationList.get(i);
				usereducatonids = usereducatonids+userEducationDetails.getEducationId()+","; 
		%>
		
		<%}
		}%>
		<div align="left">	
			<a href="#" onClick="deleteUserDetails('<%=usereducatonids%>','Education');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
				String addUserEducationUrl = request.getContextPath()+"/usereducationdetails.do?method=addUserEducation&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserEducationUrl%>','Education');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
         </div>  
            <br>
            <table border="0" width="100%">


		<%
		
		
		if(userEducationList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">

				<td align="center" width="5%"><input type="checkbox" id="educaitonMasterCheck" onClick="checkAll(this.form,'Education')" name="educaitonMasterCheck"></td>
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
		
				<td align="center"><input type="checkbox" id="userEducationIsActive_<%=userEducationDetails.getEducationId()%>"  name="userEducationIsActive"></td>
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
		<tr><td align="left"><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>


</table>
            



</fieldset>
<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.Skills",user1.getLocale())%></legend>
<%
String deleteUserSkillsMultiple = (String)request.getAttribute("deleteUserSkillsMultiple");
	
if(deleteUserSkillsMultiple != null && deleteUserSkillsMultiple.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.useskill.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		List userskillsList = cform.getUserSkillsDetailsList();
		
		 String userskillsids="";
		if(userskillsList.size() > 0){
			for(int i = 0; i< userskillsList.size(); i++){
				UserSkills userSkills = (UserSkills)userskillsList.get(i);
				userskillsids = userskillsids+userSkills.getSkillId()+","; 
		%>
		
		<%}
		}%>
			<div align="left">	
			<a href="#" onClick="deleteUserDetails('<%=userskillsids%>','Skills');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			
			<%
				String addUserSkillsUrl = request.getContextPath()+"/userskills.do?method=addUserSkills&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserSkillsUrl%>','Skills');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
           </div>
            <br>
            
              <table border="0" width="100%">


		<%
		
		
		if(userskillsList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">
		 	<td align="center" width="5%"><input type="checkbox" id="skillsMasterCheck" onClick="checkAll(this.form,'Skills')" name="skillsMasterCheck"></td>
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

			<td align="center"><input type="checkbox" id="userskillsIsActive_<%=userSkills.getSkillId()%>"  name="userskillsIsActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserQualificationDetails('<%=editUserQualificationURl%>','Skills');return false"><%=userSkills.getSkillname()  == null ?"": userSkills.getSkillname()%></a></td>
				<td>&nbsp;<%=userSkills.getYearsofexp() == null ?"":userSkills.getYearsofexp()  %></td>
				<td>&nbsp;<%=userSkills.getRating()  == null ?"":userSkills.getRating() %></td>
				
			</tr>

			
		<%
			}
		}else{
		
		
		%>
		<tr><td align="left"><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>
		


</table>

</fieldset>
<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.Languages",user1.getLocale())%></legend>
<%
String deleteUserLanguagesMultiple = (String)request.getAttribute("deleteUserLanguagesMultiple");
	
if(deleteUserLanguagesMultiple != null && deleteUserLanguagesMultiple.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.userlanguage.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		List userlanguagesList = cform.getUserLanguagesDetailsList();

		
		 String userlanguageids="";
		if(userlanguagesList.size() > 0){
			for(int i = 0; i< userlanguagesList.size(); i++){
				UserLanguages userLanguages = (UserLanguages)userlanguagesList.get(i);
				userlanguageids = userlanguageids+userLanguages.getUserLangId()+","; 
		%>
		
		<%}
		}%>
			<div align="left">	
			<a href="#" onClick="deleteUserDetails('<%=userlanguageids%>','Languages');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
				String addUserLanguages = request.getContextPath()+"/userlanguages.do?method=addUserLanguages&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserLanguages%>','Languages');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
           
           </div>
            <br>
            
            
        <table border="0" width="100%">


		<%
		
		
		if(userlanguagesList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">

				<td align="center" width="5%"><input type="checkbox" id="languageMasterCheck" onClick="checkAll(this.form,'Languages')" name="languageMasterCheck"></td>
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

			<td align="center"><input type="checkbox" id="userLanguageIsActive_<%=userLanguages.getUserLangId()%>"  name="userLanguageIsActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserQualificationDetails('<%=editUserQualificationURl%>','Languages');return false"><%=userLanguages.getLanguage()  == null ?"": userLanguages.getLanguage().getLanguageName() %></a></td>
				<td>&nbsp;<%=userLanguages.getFluency().trim().equals("NoValue") ? "":userLanguages.getFluency()%></td>
				<td>&nbsp;<%=userLanguages.getRating()  == null ?"":userLanguages.getRating()%></td>
				<td>&nbsp;<%=userLanguages.getComment()  == null ?"":userLanguages.getComment() %></td>
			</tr>

			
		<%
			}
		}else{
		
		
		%>
		<tr><td align="left"><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>

</table>

</fieldset>
<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.Qualifications.License",user1.getLocale())%></legend>
			
<%
String deleteUserLicenseMultiple = (String)request.getAttribute("deleteUserLicenseMultiple");
	
if(deleteUserLicenseMultiple != null && deleteUserLicenseMultiple.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.userlicense.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		List userLicensesList = cform.getUserLicenseDetailsList();

		
		 String userlicenseids="";
		if(userLicensesList.size() > 0){
			for(int i = 0; i< userLicensesList.size(); i++){
				UserLicenses userlicenses = (UserLicenses)userLicensesList.get(i);
				userlicenseids = userlicenseids+userlicenses.getUserLicenseId()+","; 
		%>
		
		<%}
		}%>
			<div align="left">	
			<a href="#" onClick="deleteUserDetails('<%=userlicenseids%>','License');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
				String addUserLiacence = request.getContextPath()+"/userlicenses.do?method=addUserLicenseDetails&userId="+cform.getUserId();		
			%>
			<a href="#" onClick="javascript:addUserQualification('<%=addUserLiacence%>','License');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a href="#" onClick="javascript:retrieveData('userqualification');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
           </div>
            <br>
            
            
         
        <table border="0" width="100%">


		<%
		
		
		if(userLicensesList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">
		 	<td align="center" width="5%"><input type="checkbox" id="licenseMasterCheck" onClick="checkAll(this.form,'License')" name="licenseMasterCheck"></td>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Type",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Number",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Issuing_Authority",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Issued_Date",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Expiry_Date",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.Comments",user1.getLocale())%></b></td>
				
			</tr>
			
			
		<%
			for(int i = 0; i< userLicensesList.size(); i++){
				UserLicenses userlicenses = (UserLicenses)userLicensesList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserQualificationURl = request.getContextPath()+"/userlicenses.do?method=editUserLicenseDetails&userLicenseId="+userlicenses.getUserLicenseId() +"&userId="+cform.getUserId();		
				
		%>
		

			<tr bgcolor=<%=bgcolor%>>
			<td align="center"><input type="checkbox" id="userLicenseIsActive_<%=userlicenses.getUserLicenseId()%>"  name="userLicenseIsActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserQualificationDetails('<%=editUserQualificationURl%>','License');return false"><%=userlicenses.getLicensetype()  == null ?"": userlicenses.getLicensetype().getLicenseTypeName() %></a></td>
				<td>&nbsp;<%=userlicenses.getLicenseNumber() == null ?"":userlicenses.getLicenseNumber()%></td>
				<td>&nbsp;<%=userlicenses.getLicenseIssueAuthority()  == null ?"":userlicenses.getLicenseIssueAuthority()%></td>
				<td>&nbsp;<%=userlicenses.getIssueDate()  == null ?"":userlicenses.getIssueDate()%></td>
				<td>&nbsp;<%=userlicenses.getExpireDate()  == null ?"":userlicenses.getExpireDate()%></td>
				<td>&nbsp;<%=userlicenses.getComment()  == null ?"":userlicenses.getComment()%></td>
			</tr>

			
		<%
			}
		}else{
		
		
		%>
		<tr><td align="left"><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>

</table>
            



</fieldset>

<br><br>

</fieldset>
</span>
</html:form>
</body>