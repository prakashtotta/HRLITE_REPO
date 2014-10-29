
<table class="ex2" border="0" bordercolor="gray" RULES=NONE FRAME=BOX width="100%">

       	<tr>
       	
          <td align="left" width="25%">   
          	<font size="4" color="gray"><b> <bean:write name="form" property="jobPosition"/></b></font>
          </td>
          
        </tr>
		<tr></tr><tr></tr>
  

		<tr>
          <td align="left" width="25%"><b><%=Constant.getResourceStringValue("Requisition.reqcode",user1.getLocale())%> :</b></td>  <td><bean:write name="form" property="jobreqcode"/></td>

         <td width="20%">
         <b><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%> :</b></td>  <td> <bean:write name="form" property="jobTitle"/> </td>        
	
        </tr>
		<tr></tr><tr></tr>
		
		<tr>
          <td align="left" ><b><%=Constant.getResourceStringValue("Requisition.Noofopenpositions",user1.getLocale())%> :</b></td>  <td> <%=form.getNumberOfOpening()== 0?"N/A": form.getNumberOfOpening()%></td>
          <td>
          <b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%> :</b></td>  <td><%=StringUtils.isNullOrEmpty(form.getLocationName())?"N/A":form.getLocationName()%></td>
        </tr>
        <tr></tr><tr></tr>
		<tr>
          <td align="left" valign="top" > 
          <b><%=Constant.getResourceStringValue("Requisition.Publishdate",user1.getLocale())%> :</b></td>  <td> <%=DateUtil.convertDateToStringDate(form.getPublishedDate(),datepattern)%></td>

          <td>
          <b><%=Constant.getResourceStringValue("Requisition.Target.finish.date",user1.getLocale())%> :</b></td>  <td> <%=(form.getTargetfinishdate()=="")?"N/A":form.getTargetfinishdate()%></td>
        </tr>
      
       
        <tr></tr><tr></tr>
        		<tr>
          <td align="left" > 
          <b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> :</b></td>  <td><%=StringUtils.isNullOrEmpty(form.getParentOrgName())?"N/A":form.getParentOrgName()%> </td>
         <% if(screenFields.contains(FieldCodes.RequisitionScreen.DEPARTMENT.toString())){%>
          <td>
          <b><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> :</b></td>  <td>  <%=StringUtils.isNullOrEmpty(form.getDepartmentName())?"N/A":form.getDepartmentName()%>
          </td>
       	 <%} %>
        </tr>
         <tr></tr><tr></tr>
      
	       
	        <tr>
	          <% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_LEVEL_OF_EDUCATION.toString())){%>
	          <td align="left" valign="top"> 
	          <b><%=Constant.getResourceStringValue("Requisition.qualifications",user1.getLocale())%> :</b></td>  <td> <%=StringUtils.isNullOrEmpty(form.getMinimumLevelOfEducation())?"N/A":form.getMinimumLevelOfEducation()%></td>
	          <%} %>
				<%if(form.getOtherExperience() != null && form.getOtherExperience().length()>0){%>
				<% if(screenFields.contains(FieldCodes.RequisitionScreen.OTHER_EXP_REQUIRED.toString())){%>
	          <td align="left" valign="top" >
	          <b><%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%> :</b></td>  <td><%=StringUtils.isNullOrEmpty(form.getOtherExperience())?"N/A":form.getOtherExperience()%> </td>
	       
				<%}%>
			</tr>
		
		<tr></tr><tr></tr>
		 <%} %>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_EXPERIENCE_REQUIRED.toString()) && screenFields.contains(FieldCodes.RequisitionScreen.MAX_EXPERIENCE_REQUIRED.toString())){%>
			<tr>
	        
	          <td align="left" valign="top">
	          <b><%=Constant.getResourceStringValue("aquisition.applicant.Experience",user1.getLocale())%> :</b></td>  
	          <td> 
	          	<% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_EXPERIENCE_REQUIRED.toString())){%>
	          		<bean:write name="form" property="minyearsofExpRequired"/> 
	          		
	          	<%}%>
	          	&nbsp;<%=Constant.getResourceStringValue("hr.text.to",user1.getLocale())%>&nbsp;
	          	
	          	<% if(screenFields.contains(FieldCodes.RequisitionScreen.MAX_EXPERIENCE_REQUIRED.toString())){%>
				  	<%if(form.getMaxyearsofExpRequired() > 0){%>
				 		  <bean:write name="form" property="maxyearsofExpRequired"/>&nbsp;
				  	<%}%>
				<%}%>
			  		<%=Constant.getResourceStringValue("Requisition.screen.years",user1.getLocale())%>
			  </td>
	   		</tr>
   		<%}else{%>
   		
	   		<% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_EXPERIENCE_REQUIRED.toString())){%>
	   		<tr>
	   			<td>
	   			 <b><%=Constant.getResourceStringValue("aquisition.applicant.Min_Experience",user1.getLocale())%> :</b> 
	   				<bean:write name="form" property="minyearsofExpRequired"/>&nbsp;<%=Constant.getResourceStringValue("Requisition.screen.years",user1.getLocale())%>
	   			</td>
	   		</tr>
	   		<%}%>
	   		<% if(screenFields.contains(FieldCodes.RequisitionScreen.MAX_EXPERIENCE_REQUIRED.toString())){%>
	   		<tr>
	   			<td>
	   			 <b><%=Constant.getResourceStringValue("aquisition.applicant.Max_Experience",user1.getLocale())%> :</b> 
	   				<%if(form.getMaxyearsofExpRequired() > 0){%>
				 		  <bean:write name="form" property="maxyearsofExpRequired"/>&nbsp;<%=Constant.getResourceStringValue("Requisition.screen.years",user1.getLocale())%>
				  	<%}%>
	   			</td>
	   		</tr>
	   		<%}%>
   		<%}%>
   		</table>
		<table class="div" border="0" bordercolor="gray" width="100%">
		 <% 
		
	List formvariableList = form.getFormVariablesList();
	if(formvariableList != null && formvariableList.size()>0){
		
	%>


        <tr>
			<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  
		</tr>
		        <tr>
          <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.additional.jobreqdetails",locale)%></b></font>
          </td>
          </tr>
          <tr> <td align="left"> 
         <%@ include file="../talent/customVariablestoRequisitionDetails.jsp"%>
   		</td></tr>
<%	} 
		
		
		%>  
               <tr>
		<td colspan="2"><!--<font color="gray">_________________________________________________________________________________________________-->
		</font>
		</td>  
		</tr>
		
		<tr></tr><tr></tr>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.INTRODUCTION.toString())){%>	
			 <tr>
				<td align="left"><font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.Introduction",user1.getLocale())%> </b> </font></td>
	        </tr>
	                <tr>
	         <td align="left" colspan="2">  
	
			 <%=StringUtils.isNullOrEmpty(form.getJobInstructions())?"N/A":StringUtils.textAreaValuesDisplay(form.getJobInstructions())%>
	         </td>
	        </tr>
	         <tr></tr><tr>
			 
			 
			 </tr>
        <%}%>
        <% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_ROLES.toString())){%>
	        <tr>
	          <td align="left"> 
	          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.responsibilities",user1.getLocale())%></b></font>
	          </td>
	         
	        </tr>
	        <tr></tr><tr></tr>
	        <tr>
	         <td align="left" colspan="2">  
	                 <%=StringUtils.isNullOrEmpty(form.getJobRoles())?"N/A":StringUtils.textAreaValuesDisplay(form.getJobRoles())%>
	         
	         </td>
	        </tr>
			
			<tr></tr><tr></tr>
        <%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_DETAILS.toString())){%>
	        <tr>
	          <td align="left" colspan="2"><font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></b></font>  <br>
			  </td>
	        </tr>
	        <tr>
	         <td align="left" colspan="2">  
	
			 <%=StringUtils.isNullOrEmpty(form.getJobDetails())?"N/A":StringUtils.textAreaValuesDisplay(form.getJobDetails())%>
	        </td>
	        </tr>
	         <tr></tr><tr></tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_TYPE.toString()) || screenFields.contains(FieldCodes.RequisitionScreen.WORK_SHIFT.toString()) || screenFields.contains(FieldCodes.RequisitionScreen.FLSA_STATUS.toString()) || screenFields.contains(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString())){%>
		<tr>
	          <td align="left" colspan="2"><font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails.other",user1.getLocale())%></b></font>  <br>
			  </td>
	    </tr>
	    
		 <tr></tr><tr></tr>
		 <%}%>
           <tr>
         
          <td align="left" colspan = "4">
		  
		  <table class="div" border="0" width="800" >
			<tr>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_TYPE.toString())){%>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></b></td>
			<%}%>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.WORK_SHIFT.toString())){%>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%></b></td>
			<%}%>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.FLSA_STATUS.toString())){%>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("Requisition.FLSA.Status",user1.getLocale())%></b></td>
			<%}%>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString())){%>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("Requisition.compfreq",user1.getLocale())%></b></td>
			<%}%>
			</tr>
			<tr>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_TYPE.toString())){%>
				<td>&nbsp;<%=StringUtils.isNullOrEmpty(form.getJobtype())?"<center>-</center>":form.getJobtype()%></td>
			<%}%>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.WORK_SHIFT.toString())){%>
				<td>&nbsp;<%=StringUtils.isNullOrEmpty(form.getWorkshift())?"<center>-</center>":form.getWorkshift()%></td>
			<%}%>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.FLSA_STATUS.toString())){%>
				<td>&nbsp;<%=StringUtils.isNullOrEmpty(form.getFlsastatus())?"<center>-</center>":form.getFlsastatus()%></td>
			<%}%>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString())){%>
				<td>&nbsp;<%=StringUtils.isNullOrEmpty(form.getCompfrequency())?"<center>-</center>":form.getCompfrequency()%></td>
			<%}%>
			</tr>
		  </table>

		  </td>
        </tr>
		<tr></tr><tr></tr>
			<% if(screenFields.contains(FieldCodes.RequisitionScreen.NOTES.toString())){%>	
	 		<tr>
			 <td align="left"> 
	          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.Note",user1.getLocale())%></b></font>
	          </td>
	        </tr>
	        <tr>
	          <td align="left" colspan="2">
	
			 <%=StringUtils.isNullOrEmpty(form.getJobreqDesc())?"N/A":StringUtils.textAreaValuesDisplay(form.getJobreqDesc())%>
			  </td>
	        </tr>
			<tr></tr><tr></tr><tr></tr><tr></tr>
		<%}%>
		
		<tr>
          <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></b></font>
          </td>
        </tr>
		<tr></tr><tr></tr>
		<tr>
          <!--  <td align="left" valign="top"><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></td>-->
          <td align="left">
		  <%
				List competencyList = form.getComptetencyList();

				if(competencyList !=null && competencyList.size() > 0){
					for(int i=0;i<competencyList.size();i++){
						JobTemplateCompetency jcomp = (JobTemplateCompetency)competencyList.get(i);
						String checkedmancomp = (jcomp.getMandatory().equals("on"))?Constant.getResourceStringValue("Requisition.Mandatory",user1.getLocale()):Constant.getResourceStringValue("refferal.agencyreferralportal.jobdetails.goodtohave",user1.getLocale());
					%>
					<%=jcomp.getCharName()%> &nbsp;&nbsp;<i>(<%=checkedmancomp%>)</i> <br>
		
					<%
					}	
				}else{
				
		   %> 
		    		<%=Constant.getResourceStringValue("admin.NA",user1.getLocale())%>
			   <%
			   }
			   %> 
		  
		</td>
        </tr>
		<tr></tr><tr></tr><tr></tr><tr></tr>
       	<tr>
          <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></b></font>

         
<%

	List attachmentList = form.getAttachmentList();
	if(attachmentList != null && attachmentList.size()>0){
%>
	</td>
	</tr>
	<tr>

	<td width ="50%"><b><%=Constant.getResourceStringValue("Requisition.Attachement.details",user1.getLocale())%></b></td>
	<td width ="50%"><b><%=Constant.getResourceStringValue("Requisition.Attachement.file.name",user1.getLocale())%></b></td>
	
	</tr>

<%
		for(int i=0;i<attachmentList.size();i++){
		RequistionAttachments attach = (RequistionAttachments)attachmentList.get(i);
		
			if(attach.getVisibleInJobDetails() != null && attach.getVisibleInJobDetails().equals("Y")){
%>
			<tr>
			<td><%=(attach.getAttahmentdetails()==null)?"":attach.getAttahmentdetails()%></td>
			<td><a href="jobs.do?method=attachementdetails&attachmentid=<%=attach.getUuid()%>"><%=attach.getAttahmentname()%></a></td>
			</tr>
			<%}%>
		<%}%>

<%}else{
				
%> 
		<tr>
			<td>
			 <%=Constant.getResourceStringValue("admin.NA",user1.getLocale())%>
			</td>
		</tr>
		
<%}%> 
		  
		<tr></tr><tr>
		
		</tr>

         

		 

		<%if(form.getRecruiter()!=null){
			User recruiter = form.getRecruiter();
		%>
        <tr>
          <td align="left" valign="top"><font color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.ContactPerson",user1.getLocale())%> : </b></font>

           <%=StringUtils.isNullOrEmpty(form.getRecruiterName())?"N/A":form.getRecruiterName()%>
           (<font color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>: </b></font>  <%=StringUtils.isNullOrEmpty(recruiter.getPhoneOffice())?"N/A":recruiter.getPhoneOffice()%>)</td>
        </tr>
         <tr>
          <td align="left" valign="top"><font color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.ContactEmail",user1.getLocale())%> &nbsp;&nbsp;: </b></font>
           <%=StringUtils.isNullOrEmpty(recruiter.getEmailId())?"N/A":recruiter.getEmailId()%></td>
        </tr>
       
         <tr>
         <%}%>


        </font>
        
        <tr>
          
          <td align="left" > 
			 <br> <a  class="button" href="<%=request.getContextPath()%>/refferaluser.do?method=refferaluserdetails&from=applicantexternal&source=<%=source%>&secureid=<%=secureid%>&jobreqid=<%=reqid%>&locale_code=<%=user1.getLocale().getLocaleCode()%>" target="new"><%=Constant.getResourceStringValue("Requisition.apply.for.this.job",user1.getLocale())%></a> <br><br>
		  </td>
        </tr>
      
      </table>
