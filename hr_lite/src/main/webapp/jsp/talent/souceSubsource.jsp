

<% if(screenFields.contains(FieldCodes.ApplicantScreen.SOURCE_SUBSOURCE.toString())){%>


      <div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[source]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.resume.source",user1.getLocale())%> </div>
<div id="source" style="width  100%;">
<div >
<table border="0" width="100%">
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Source",user1.getLocale())%>
<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.SOURCE_SUBSOURCE.toString())){%>
			<font color="red">*</font>
<%}%>
			</td>
			
			<td>
			
			<html:select  styleClass="list" property="sourceTypeId" onchange="retrieveURLresumesource(document.applicantForm.sourceTypeId.value);">
			<option value=""></option>
			<bean:define name="applicantForm" property="sourceTypeList" id="sourceTypeList" />

            <html:options collection="sourceTypeList" property="resumeSourceTypeId"  labelProperty="resumeSourceTypeName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingsource" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_sub_source",user1.getLocale())%>......</span>
			</td>
		</tr>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.SubSource",user1.getLocale())%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.SOURCE_SUBSOURCE.toString())){%>
			<!--  <span1>*</span1>-->
			<%}%>
			</td>
			
			<td>
            <span id="subsource">
			
			<%if(aform.getSourceTypeId()==Common.RESUME_SOURCE_AGENCY_ID){%>
	<html:select  property="vendorId" styleClass="list">
	<option value=""></option>
			<bean:define name="applicantForm" property="vendorsList" id="vendorsList" />

            <html:options collection="vendorsList" property="userId"  labelProperty="firstName"/>
			</html:select>
<%} else if(aform.getSourceTypeId()==Common.RESUME_SOURCE_FERERRAL_ID){%>
<table width="100%">

 <tr><td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%></td> 
 <td><html:text styleClass="textdynamic" property="employeecode" size="30"/> </td>
 </tr>
  <tr><td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%></td> 
  <td><html:text styleClass="textdynamic" property="referrerName" size="30"/> </td>
  </tr>
  <tr><td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Referrer_Email",user1.getLocale())%></td> 
  <td> <html:text styleClass="textdynamic" property="referrerEmail" size="30"/> </td>
  </tr>
  </table>
<%
//}else if(aform.getSourceTypeId()>0){
}else{
%>


	<html:select  property="sourceId" styleClass="list">
	<option value=""></option>
			<bean:define name="applicantForm" property="sourceList" id="sourceList" />

            <html:options collection="sourceList" property="resumeSourcesId"  labelProperty="resumeSourcesName"/>
			</html:select>
<%}%>
			</span>
			</td>
		</tr>
</table>
</div>
</div>

<%}//source fields visibility check%>

