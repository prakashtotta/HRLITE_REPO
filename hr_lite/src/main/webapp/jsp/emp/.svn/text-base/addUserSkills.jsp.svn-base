<%@ include file="../common/include.jsp" %>

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< addUserSkills.jsp >>");
%>
<bean:define id="cform" name="userSkillsForm" type="com.form.employee.UserSkillsForm" />



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
span1{color:#ff0000;}
</style>



<script language="javascript">

function updatedata(){
	var alertstr="";
	var showalert=false;
	var skillname=document.userSkillsForm.skillname.value.trim();

	
    if(skillname == "" || skillname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userSkillsForm.action="userskills.do?method=updateUserSkillsDetails";
	  document.userSkillsForm.submit();


}

function savedata(){


	var alertstr="";
	var showalert=false;
	var skillname=document.userSkillsForm.skillname.value.trim();

	
    if(skillname == "" || skillname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userSkillsForm.action="userskills.do?method=saveUserSkillsDetails";
	  document.userSkillsForm.submit();



}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
	  document.userSkillsForm.action="userskills.do?method=deleteUserSkillsDetails";
	  document.userSkillsForm.submit();
 	}
	
}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	   parent.parent.GB_hide(); 
	   } 
	}
function closewindow2(){
	parent.parent.GB_hide();

	 
}
</script>
<%
String userSkillsDetailsupdated = (String)request.getAttribute("userSkillsDetailsupdated");
	
if(userSkillsDetailsupdated != null && userSkillsDetailsupdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.updated",user1.getLocale())%></font></td>
			<td> <!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String userSkillsDetailsSaved = (String)request.getAttribute("userSkillsDetailsSaved");
	
if(userSkillsDetailsSaved != null && userSkillsDetailsSaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.saved",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String userSkillsDetailsDeleted = (String)request.getAttribute("userSkillsDetailsDeleted");
	
if(userSkillsDetailsDeleted != null && userSkillsDetailsDeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
		<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.deleted",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		
		</tr>
		
	</table>
</div>
<%
}else{
%>

<body >

<html:form action="/userskills.do?method=saveUserSkillsDetails" >
<br>
<font color = red ><html:errors /> </font>
<br>
<table border="0" width="100%">

	 	<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill",user1.getLocale())%><font color="red">*</font></td>
			<td>
				
				<html:select  property="skillname">
					<bean:define name="userSkillsForm" property="userSkillList" id="userSkillList" />
	         		<html:options collection="userSkillList" property="key"  labelProperty="value"/>
				</html:select>
		
			<html:hidden property="userId" />
			<html:hidden property="skillId" />
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.YearsOfExperience",user1.getLocale())%></td>
			<td>
			<html:select  property="yearsofexp">
			<bean:define name="userSkillsForm" property="skillYearsList" id="skillYearsList" />
            <html:options collection="skillYearsList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.rating",user1.getLocale())%></td>
			<td>		
			<html:select  property="rating">
				<bean:define name="userSkillsForm" property="skillRatingList" id="skillRatingList" />
	            <html:options collection="skillRatingList" property="key"  labelProperty="value"/>
			</html:select>
			
			
			</td>
		</tr>


</table>
<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getSkillId() != 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%
			}else{
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%
			}
			%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</td>
		</tr>
		</table>
</html:form>

</body>
<%
}
%>
