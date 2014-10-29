<%@ include file="../header.jsp" %>

<bean:define id="aform" name="faceBookUserForm" type="network.form.FaceBookUserForm" />

<%

FaceBookUser ownuser = freader.getOwnUserDataForUI(fuser.getFacebookid(),fuser.getSessionKey());

List fbuserSkillList=BOFactory.getFbUserBO().getFbUsersskillListByUserId(fuser.getUserId());
System.out.println(" userId >>"+ownuser.getUserId());
System.out.println(" fbuserSkillList.size() >>"+fbuserSkillList.size());

List fbuserSpecialitiesList=BOFactory.getFbUserBO().getFbUsersSpecialitiesListByUserId(fuser.getUserId());

System.out.println("hello ... ");
List fbuserAchievementList=BOFactory.getFbUserBO().getFbUsersAchievementListByUserId(fuser.getUserId());
System.out.println(" fbuserAchievementList.size() >>"+fbuserAchievementList.size());

FbUserSkills fbUserSkills1 = null;
FbUserSkills fbUserSkills2 = null;
FbUserSkills fbUserSkills3 = null;
FbUserSkills fbUserSkills4 = null;
FbUserSkills fbUserSkills5 = null;

FbUserSpecialities fbUserSpecialities1=null;
FbUserSpecialities fbUserSpecialities2=null;

FbUserAchivements fbUserAchivements1=null;
FbUserAchivements fbUserAchivements2=null;
FbUserAchivements fbUserAchivements3=null;


if(fbuserSkillList.size() > 0){
	fbUserSkills1 = (FbUserSkills)fbuserSkillList.get(0);
	fbUserSkills2 = (FbUserSkills)fbuserSkillList.get(1);
	fbUserSkills3 = (FbUserSkills)fbuserSkillList.get(2);
	fbUserSkills4 = (FbUserSkills)fbuserSkillList.get(3);
	fbUserSkills5 = (FbUserSkills)fbuserSkillList.get(4);
}
if(fbuserSpecialitiesList.size() > 0){
	fbUserSpecialities1=(FbUserSpecialities)fbuserSpecialitiesList.get(0);
	fbUserSpecialities2=(FbUserSpecialities)fbuserSpecialitiesList.get(1);
	
}
if(fbuserAchievementList.size() > 0){
	
	fbUserAchivements1=(FbUserAchivements)fbuserAchievementList.get(0);
	fbUserAchivements2=(FbUserAchivements)fbuserAchievementList.get(1);
	fbUserAchivements3=(FbUserAchivements)fbuserAchievementList.get(2);
	
}
List<FbUserEmployer> employerList = ownuser.getEmployerList();
List<FbUserSchools> educationList = ownuser.getEducationList();
System.out.println("employerList.size()"+employerList.size());
System.out.println("employerList.size()"+employerList.size());
String saveprofile=(String)request.getAttribute("saveprofile");

%>
<html>
<head>
	<title>Edit Profile</title>

<script language="javascript">
function savedata(){
	//alert("savedata method");
	var skill1 = document.faceBookUserForm.skill1.value.trim();
	var skill2 = document.faceBookUserForm.skill2.value.trim();
	var skill3 = document.faceBookUserForm.skill3.value.trim();
	var skill4 = document.faceBookUserForm.skill4.value.trim();
	var skill5 = document.faceBookUserForm.skill5.value.trim();

	var skill1Exp = document.faceBookUserForm.skill1Exp.value.trim();
	var skill2Exp = document.faceBookUserForm.skill2Exp.value.trim();
	var skill3Exp = document.faceBookUserForm.skill3Exp.value.trim();
	var skill4Exp = document.faceBookUserForm.skill4Exp.value.trim();
	var skill5Exp = document.faceBookUserForm.skill5Exp.value.trim();

	var specility1=document.faceBookUserForm.specility1.value.trim();
	var specility2=document.faceBookUserForm.specility2.value.trim();

	var achivement1=document.faceBookUserForm.achivement1.value.trim();
	var achivement2=document.faceBookUserForm.achivement2.value.trim();
	var achivement3=document.faceBookUserForm.achivement3.value.trim();
	var content = nicEditors.findEditor('bio').getContent();
	
	document.faceBookUserForm.summary.value=content;
	
	
//	alert("skill1 >>"+skill1+" skill2 >> "+skill2+" skill3 >> "+skill3+" skill4 >> "+skill4+" skill5 >> "+skill5);
	  //var url = "<%=request.getContextPath()%>/networkhome.do?method=saveProfile";
	  var url = "networkhome.do?method=saveProfile&skill1="+skill1+"&skill2="+skill2+"&skill3="+skill3+"&skill4="+skill4+"&skill5="+skill5+"&skill1Exp="+skill1Exp+"&skill2Exp="+skill2Exp+"&skill3Exp="+skill3Exp+"&skill4Exp="+skill4Exp+"&skill5Exp="+skill5Exp+"&specility1="+specility1+"&specility2="+specility2+"&achivement1="+achivement1+"&achivement2="+achivement2+"&achivement3="+achivement3;
	  document.faceBookUserForm.action = url;
	  document.faceBookUserForm.submit();

}
function closewindow(){
	parent.parent.GB_hide(); 
	
}
function discard(){
	  var doyou = confirm("Are you sure you would like to discard these changes ( OK = yes Cancel = No)");
	  if (doyou == true){
		//self.parent.location.reload();
	    //window.top.hidePopWin();
	   parent.parent.GB_hide(); 
	   } 
	}

</script>
 <script type="text/javascript" src="jsp/js/nicEdit.js"></script>
 <script type="text/javascript">
 //<![CDATA[
 bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
 //]]>  
 </script>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width:95%;
	border: 1px solid #999;
	padding: 10px;
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
<link rel="stylesheet" type="text/css" href="jsp/css/button.css" />

</head>

<body class="yui-skin-sam" >

<%
	
if(saveprofile != null && saveprofile.equals("yes")){
%>
<fieldset>
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/approve2.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="green">Profile saved successfully</font></td>
			<td> <a href="#" onclick="closewindow()">Close window</a></td>
		</tr>
		
	</table>
</fieldset>

<%}%>
<html:form action="/networkhome.do?method=saveProfile">
<fieldset>
	<table border="0" width="100%" cellspacing="2" cellpadding="2">
			<tr>
				<td width="20%">Name:</td>
				<td ><%=ownuser.getFullName()%></td>
			</tr>
			<tr></tr><tr></tr>
			<tr>
				<td>Profile heading:</td><td><html:text property="topLine" maxlength="100" size="50" value="<%=fuser.getTopLine()==null?"":fuser.getTopLine()%>"/></td>
			</tr>
			<tr></tr><tr></tr>
			<tr>
				<td>Gender:</td>
				<td><%=(ownuser.getGender() == null)?"":ownuser.getGender()%></td>
			</tr>
			<tr></tr><tr></tr>
			<tr>
				<td>Current city:</td>
				<td><html:text property="city" maxlength="100" size="50" value="<%=ownuser.getCity()%>"/></td>
			</tr>
			<tr></tr><tr></tr>
			<tr>
				<td>Skills:</td>
				<td>&nbsp;&nbsp;&nbsp;
					Skills
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					
					Exp.
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td>1: <input type="text" name="skill1" size="37" value="<%=(fbUserSkills1 == null)?"":fbUserSkills1.getSkill() %>"></input>&nbsp;&nbsp;<input type="text" name="skill1Exp" size="5" value="<%=(fbUserSkills1 == null)?"":fbUserSkills1.getYearsExp() %>"></td>
			</tr>

			<tr>
				<td></td>
				<td>2: <input type="text" name="skill2" size="37" value="<%=(fbUserSkills2 == null)?"":fbUserSkills2.getSkill() %>"></input>&nbsp;&nbsp;<input type="text" name="skill2Exp" size="5" value="<%=(fbUserSkills2 == null)?"":fbUserSkills2.getYearsExp() %>"></td>
			</tr>
			<tr>
				<td></td>
				<td>3: <input type="text" name="skill3" size="37" value="<%=(fbUserSkills3 == null)?"":fbUserSkills3.getSkill() %>"></input>&nbsp;&nbsp;<input type="text" name="skill3Exp" size="5" value="<%=(fbUserSkills3 == null)?"":fbUserSkills3.getYearsExp() %>"></td>
			</tr>
			<tr>
				<td></td>
				<td>4: <input type="text" name="skill4" size="37" value="<%=(fbUserSkills4 == null)?"":fbUserSkills4.getSkill() %>"></input>&nbsp;&nbsp;<input type="text" name="skill4Exp" size="5" value="<%=(fbUserSkills4 == null)?"":fbUserSkills4.getYearsExp() %>"></td>
			</tr>
			<tr>
				<td></td>
				<td>5: <input type="text" name="skill5" size="37" value="<%=(fbUserSkills5 == null)?"":fbUserSkills5.getSkill() %>"></input>&nbsp;&nbsp;<input type="text" name="skill5Exp" size="5" value="<%=(fbUserSkills5 == null)?"":fbUserSkills5.getYearsExp() %>"></td>
			</tr>
			<tr></tr><tr></tr>
			<tr>
				<td>Profile url:</td>
				<td><a href="p/<%=ownuser.getFuserName()%>" target="new">http://www.talentnetwork.com/<%=ownuser.getFuserName()%></a></td>
			</tr>
			<tr></tr><tr></tr>
			<tr>
				<td>Summary:</td>
				<td>
				<html:textarea property="bio" styleId="bio" cols="48" rows="10" value="<%=fuser.getBio()== null?"": fuser.getBio()%>"/>
				<input type="hidden" name="summary"/>
				</td>
				
			</tr>
			<tr></tr><tr></tr><tr></tr><tr></tr>
			<tr>
				<td>Specialties:</td>
				<td>1: <input type="text" name="specility1" size="50" value="<%=(fbUserSpecialities1 == null)?"":fbUserSpecialities1.getSpecialities()%>"></input></td>
			</tr>
			<tr>
				<td></td>
				<td>2: <input type="text" name="specility2" size="50" value="<%=(fbUserSpecialities2 == null)?"":fbUserSpecialities2.getSpecialities()%>"></input></td>
			</tr>
			<tr></tr><tr></tr>
			<tr>
				<td>Achievements:</td>
				<td>1: <input type="text" name="achivement1" size="50" value="<%=(fbUserAchivements1 == null)?"":fbUserAchivements1.getAchivement()%>"></input></td>
			</tr>
			<tr>
				<td></td>
				<td>2: <input type="text" name="achivement2" size="50" value="<%=(fbUserAchivements2 == null)?"":fbUserAchivements2.getAchivement()%>"></input></td>
			</tr>
			<tr>
				<td></td>
				<td>3: <input type="text" name="achivement3" size="50" value="<%=(fbUserAchivements3 == null)?"":fbUserAchivements3.getAchivement()%>"></input></td>
			</tr>

					
	</table>
	<br>
	<table>
		<tr>
			<td><input type="button" class="button" name="save" value="Submit" onClick="savedata()"></td>
			<td><input type="button" class="button" name="cancel" value="Cancel" onClick="discard()"></td>
		</tr>
	</table>
	</fieldset>
</html:form>
</body>
</html>