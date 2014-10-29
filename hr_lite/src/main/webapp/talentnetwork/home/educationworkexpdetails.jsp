<%@ include file="../header.jsp" %>
<%
FaceBookUser ownuser = freader.getOwnUserDataForUI(fuser.getFacebookid(),fuser.getSessionKey());
List<FbUserEmployer> employerList = ownuser.getEmployerList();
List<FbUserSchools> educationList = ownuser.getEducationList();
System.out.println("employerList.size()"+employerList.size());
System.out.println("employerList.size()"+employerList.size());
List fbuserSkillList=BOFactory.getFbUserBO().getFbUsersskillListByUserId(fuser.getUserId());
List fbuserSpecialitiesList=BOFactory.getFbUserBO().getFbUsersSpecialitiesListByUserId(fuser.getUserId());
List fbuserAchievementList=BOFactory.getFbUserBO().getFbUsersAchievementListByUserId(fuser.getUserId());
List certificationList = BOFactory.getFbUserBO().getFBUserCertificationsListByUserId(fuser.getUserId());
%>
<html>
<head>
<script language="javascript">
function editProfile(){
	//alert("Hi ...");

	var url = "<%=request.getContextPath()%>/networkhome.do?method=editFBUserProfile";
	GB_showCenter('Edit Profile',url,600,750, messageret);
	
	
//	var tu = "networkhome.do?method=editFBUserProfile";
//	window.open(tu,  'EditProfile','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');
		
}
function messageret(){
	//window.location.reload();

			}
function updateCertifications(){

	var url = "<%=request.getContextPath()%>/fbuserfertifications.do?method=updateFBUserCertificationsScr";
	GB_showCenter('Update Certifications',url,350,650, messageret);
	
}

</script>
</head>

<span id="educationworkexp">
<table>
				<tr>
				<td>Name:</td><td><%=ownuser.getFullName()%></td>
				</tr>
				<tr>
				<td>Profile heading:</td><td><%=(fuser.getTopLine() == null)?"":fuser.getTopLine()%></td>
				</tr>
				<tr>
				<td>Gender:</td><td><%=(ownuser.getGender() == null)?"":ownuser.getGender()%></td>
				</tr>
				<tr>
				<td>Current city:</td><td><%=(ownuser.getCity() == null)?"":ownuser.getCity()%></td>
				</tr>
				<tr>
				<td>Skills:</td>
				<td>
				<%if(fbuserSkillList.size() > 0){ %>
					<table>
						<tr>
					<%
					for(int i=0;i<fbuserSkillList.size();i++){
						FbUserSkills fbUserSkills = (FbUserSkills)fbuserSkillList.get(i); 
					%>
					
						
							<td><%=i+1%>.<%=fbUserSkills.getSkill()%>&nbsp;&nbsp;</td>
						
					<%} %>
						</tr>
					</table>
				<%} %>

				
				</td>
				</tr>
				<tr>
				<td>Profile url:</td><td><a href="p/<%=ownuser.getFuserName()%>" target="new">http://www.talentnetwork.com/<%=ownuser.getFuserName()%></A></td>
				</tr>
				<tr>
				<td>Summary:</td><td><%=(fuser.getBio() == null)?"":fuser.getBio()%>
						
				</td>
				</tr>
				<tr>
				<td>Specialties:</td>
				<td>
				<%if(fbuserSpecialitiesList.size() > 0){ %>
					<table>
						<tr>
					<%
					for(int i=0;i<fbuserSpecialitiesList.size();i++){
						FbUserSpecialities fbUserSpecialities = (FbUserSpecialities)fbuserSpecialitiesList.get(i);
					%>
					
						
							<td><%=i+1%>.<%=fbUserSpecialities.getSpecialities()%>&nbsp;&nbsp;</td>
						
					<%} %>
						</tr>
					</table>
				<%} %>
					
					</td>
				</tr>
				<tr>
				<td>Achievements:</td>
				<td>
				<%if(fbuserAchievementList.size() > 0){ %>
					<table>
						<tr>
					<%
					for(int i=0;i<fbuserAchievementList.size();i++){
						FbUserAchivements fbUserAchivements = (FbUserAchivements)fbuserAchievementList.get(i);
					%>
					
						
							<td><%=i+1%>.<%=fbUserAchivements.getAchivement()%>&nbsp;&nbsp;</td>
						
					<%} %>
						</tr>
					</table>
				<%} %>
				
				</td>
				</tr>
				<tr>
				<td>
				<a href="#" title="Edit profile" onClick="editProfile()"><img src="talentnetwork/images/editprofile.png" border="0" /></a>
				</td>
				</tr>
				
				</table>
				<br>
<div style="background: #FFCC66;  text-align:left; width:900px">
<table width="900px">
<tr>
<td>
Work Experience details
</td>
<td align="right">
<a href="http://www.facebook.com/<%=ownuser.getFuserName()%>?sk=info&edit=1&ref=update_info&intent=general" target="new"><img src="talentnetwork/images/update.png" border="0" height="27px"/></a>
</td>
<tr>
</table>
</div>
<% if( employerList != null && employerList.size()>0){%>

<table bgcolor="#f3f3f3" border="1" width="900px">
<tr bgcolor="#FFFF99">
<td width="20%">Employer</td><td width="15%">Start Date</td><td width="15%">End Date</td><td width="20%">Position</td><td width="10%">Location</td><td width="20%">Description</td>

<% for(int i=0;i<employerList.size();i++){
  FbUserEmployer fbuseremp = employerList.get(i);
  
 %>
<tr>
<td><%=(fbuseremp.getEmployer()==null)?"":fbuseremp.getEmployer().getName()%></td> 
<td><%=(fbuseremp.getStartDate()==null)?"":DateUtil.convertFaceBookDate(fbuseremp.getStartDate())%></td>
<td><%=(fbuseremp.getEndDate()==null)?"":DateUtil.convertFaceBookDate(fbuseremp.getEndDate())%></td>
<td><%=(fbuseremp.getPosition()==null)?"":fbuseremp.getPosition().getName()%></td>
<td><%=(fbuseremp.getLocation()==null)?"":fbuseremp.getLocation().getName()%></td>
<td><%=(fbuseremp.getDescription()==null)?"":fbuseremp.getDescription()%></td> 
</tr>
<%}%> 
</table>

<%}%> 

<br>

<div style="background: #FFCC66;  text-align:left; width:900px">
<table width="900px">
<tr>
<td>
Education details
</td>
<td align="right">
<a href="http://www.facebook.com/<%=ownuser.getFuserName()%>?sk=info&edit=1&ref=update_info&intent=general" target="new"><img src="talentnetwork/images/update.png" border="0" height="27px"/></a>
</td>
<tr>
</table>
</div>
<% if( educationList != null && educationList.size()>0){%>

<table bgcolor="#f3f3f3" border="1" width="900px">
<tr bgcolor="#FFFF99">
<td width="25%">School</td><td width="10%">Year</td><td width="20%">Concentration</td><td width="15%">Location</td><td width="20%">Type</td>

<% for(int i=0;i<educationList.size();i++){
  FbUserSchools fschool = educationList.get(i);

 %>
<tr>
<td><%=(fschool.getSchool()==null)?"":fschool.getSchool().getName()%></td> 
<td><%=(fschool.getYear()==null)?"":fschool.getYear()%></td>
<td><%=(fschool.getConcentration()==null)?"":fschool.getConcentration().getName()%></td>
<td><%=(fschool.getLocation()==null)?"":fschool.getLocation().getName()%></td>
<td><%=(fschool.getType()==null)?"":fschool.getType()%></td> 
</tr>
<%}%> 
</table>

<%}%> 
<br>
<div style="background: #FFCC66;  text-align:left; width:900px">
	<table width="900px">
		<tr>
		<td>
		Certifications
		</td>
		<td align="right">
		<!--  <a href="http://www.facebook.com/<%=ownuser.getFuserName()%>?sk=info&edit=1&ref=update_info&intent=general" target="new"><img src="talentnetwork/images/update.png" border="0" height="27px"/></a>-->
		<a href="#" onclick="updateCertifications();return false" target="new"><img src="talentnetwork/images/update.png" border="0" height="27px"/></a>
		</td>
		</tr>
	</table>
	<table bgcolor="#f3f3f3" border="1" width="900px">

		<% for(int i=0;i<certificationList.size();i++){
			
				if(i == 0){
		%>
				<tr bgcolor="#FFFF99">
				<td width="40%">&nbsp;Certification Name</td><td width="40%">&nbsp;Organization</td><td>&nbsp;Year</td>
			
	
				</tr>
			
			<% }//END IF
			 
				
			FbUserCertifications fbUserCertifications = (FbUserCertifications)certificationList.get(i);
		
		 %>
		<tr>
			<td>&nbsp;<%=(fbUserCertifications ==null)?"":fbUserCertifications.getCertName()%></td> 
			<td>&nbsp;<%=(fbUserCertifications ==null)?"":fbUserCertifications.getCertOrg()%></td>
			<td>&nbsp;<%=(fbUserCertifications ==null)?"":fbUserCertifications.getYear()%></td>

		</tr>
		<%}%> 
	</table>
	
	
	
	
</div>
<br>
<div style="background: #FFCC66;  text-align:left; width:900px">Endorsements  </div>
<%
List endList = BOFactory.getFbUserBO().getAllEndorsements(fuser.getFacebookid());
%>

<ol class="commentlist group">

<%
	for(int i=0;i<endList.size();i++){
						FbEndorsements endorse = (FbEndorsements)endList.get(i);
						FaceBookUser fuserend = freader.getUserDataFew(endorse.getFromFbId(),fuser.getSessionKey());
%>
<li class="comment even thread-even depth-1 group" id="li-comment-10053">
			
			
									
							
				
				<div class="comment-content-triumph">
				<a href="<%=fuserend.getLink()%>" target="new"><img src="//graph.facebook.com/<%=endorse.getFromFbId()%>/picture" border="0"/></a>

				<a href="<%=fuserend.getLink()%>" target="new"><%=fuserend.getFullName()%></a>
                    <%=(endorse.getEndorse()==null)?"":StringUtils.doSpecialCharacters(endorse.getEndorse())%>  <font size="1">Posted <%=DateUtil.convertDateToStringDate(endorse.getCreatedDate(),DateUtil.defaultdateformatforSQL)%></font>
					
				</div>
				
			
         <br>
		</li>

          <%}%>
		</ol>
</span>
</html>