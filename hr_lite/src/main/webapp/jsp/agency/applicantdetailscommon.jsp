


<script language="javascript">
</script>



<table border="0" width="100%">
<div class="div">

<tr>
<td valign="left" width="75%">
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

		<tr>
			<td width="35%" class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%> : </td>
			<td width="65%" class="bodytext"><b><bean:write name="applicantForm" property="interviewState"/></b></td>
		</tr>
			
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.gender",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="gender"/></td> 
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="dateofbirth"/></td>
		</tr>

		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="email"/></td>
		</tr>

		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Alternate_Email",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="alternateemail"/></td>
		</tr>
		<tr>

    		<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.PassportNo",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="passportno"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%> : </td>

			<td class="bodytext"><bean:write name="applicantForm" property="phone"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Alternate_phone",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="alternatephone"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.Location.address",user1.getLocale())%> : </td>
			<td class="bodytext">         
			<bean:write name="applicantForm" property="city"/> <br>
			<bean:write name="applicantForm" property="countryName"/> <br>
  			
			</td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Prefered_location",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="preferedlocation"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Current_designation",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="currentdesignation"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Profile_Header",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="resumeHeader"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="noofyearsexp"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Heighest_Qualification",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="heighestQualification"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="qualifications"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="referrerName"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Referrer_Email",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="referrerEmail"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="createdBy"/></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%> : </td>
			<td class="bodytext"><%=DateUtil.convertSourceToTargetTimezone(form.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
		<tr>
			<td class="bodytext"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%>: </td>
			<td class="bodytext">
			<% if(form.getResumename() != null){%>
			<a href="<%=request.getContextPath()%>/download/file?filename=<%=path%>"><bean:write name="applicantForm" property="resumename"/></a> 
			<%}%>
			</td>
		</tr>
	</table>

	


</td>
		<td  width="25%">



<br><br>
<!--button was there earlier -->
</td>

</tr>
</div>

</table>

