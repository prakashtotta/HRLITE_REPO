<%@ include file="../common/include.jsp" %>

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< addUserLanguages.jsp >>");
%>
<bean:define id="cform" name="userLanguagesForm" type="com.form.employee.UserLanguagesForm" />




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
	var language=document.userLanguagesForm.languageId.value.trim();

	
    if(language == "" || language == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.Language.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userLanguagesForm.action="userlanguages.do?method=updateUserLanguageDetails";
	  document.userLanguagesForm.submit();


}

function savedata(){


	var alertstr="";
	var showalert=false;
	var language=document.userLanguagesForm.languageId.value.trim();

	
    if(language == "" || language == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.Language.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userLanguagesForm.action="userlanguages.do?method=saveUserLanguageDetails";
	  document.userLanguagesForm.submit();



}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
	  document.userLanguagesForm.action="userlanguages.do?method=deleteUserLanguageDetails";
	  document.userLanguagesForm.submit();
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
String userLanguageDetailsUpdated = (String)request.getAttribute("userLanguageDetailsUpdated");
	
if(userLanguageDetailsUpdated != null && userLanguageDetailsUpdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String userLanguageDetailsSaved = (String)request.getAttribute("userLanguageDetailsSaved");
	
if(userLanguageDetailsSaved != null && userLanguageDetailsSaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.saved",user1.getLocale())%></font></td>
			<td><!--   <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String userLanguageDetailsDeleted = (String)request.getAttribute("userLanguageDetailsDeleted");
	
if(userLanguageDetailsDeleted != null && userLanguageDetailsDeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
		<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.deleted",user1.getLocale())%></font></td>
			<td><!--   <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		
		</tr>
		
	</table>
</div>
<%
}else{
%>

<body >

<html:form action="/userlanguages.do?method=saveUserLanguageDetails" >
<font color = red ><html:errors /> </font>
<br>

<table border="0" width="100%">





	 	<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Qualifications.Language",user1.getLocale())%><font color="red">*</font></td>
			<td>
				
			<html:select  property="languageId">
			<option value=""></option>
			<bean:define name="userLanguagesForm" property="languagesList" id="languagesList" />
            <html:options collection="languagesList" property="languageId"  labelProperty="languageName"/>
			</html:select>
		
			<html:hidden property="userId" />
			<html:hidden property="userLangId" />
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.Fluency",user1.getLocale())%></td>
			<td>
			<html:select  property="fluency">
			
				<bean:define name="userLanguagesForm" property="languageFluencyList" id="languageFluencyList" />	    
	            <html:options collection="languageFluencyList" property="key"  labelProperty="value"/>
			</html:select>
			</td>

		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.Competency",user1.getLocale())%></td>
			<td>
			<html:select  property="rating">
				<bean:define name="userLanguagesForm" property="competencyList" id="competencyList" />
	            <html:options collection="competencyList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Language.Comments",user1.getLocale())%></td>
			<td><html:textarea property="comment" rows="5" cols="35"></html:textarea></td>
		</tr>


</table>
<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getUserLangId() != 0){
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
