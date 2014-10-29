<%@ include file="../common/include.jsp" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%> </td>
			<td class="bodytext"><b><bean:write name="applicantForm" property="interviewState"/></b></td>
		</tr>
			
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.gender",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="gender"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="dateofbirth"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.resumeheader",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="resumeHeader"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="heighestQualification"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Requisition.OtherQualifications",user1.getLocale())%></td>
			<td class="bodytext"><bean:write name="applicantForm" property="qualifications"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="email"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="phone"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("admin.Location.address",user1.getLocale())%> </td>
			<td class="bodytext">         
			<bean:write name="applicantForm" property="city"/> <br>
			<bean:write name="applicantForm" property="countryName"/> <br>
  			
			</td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="createdBy"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%> </td>
			<td class="bodytext"><%=DateUtil.convertSourceToTargetTimezone(form.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%> </td>
			<td class="bodytext"><a href="<%=path%>" target="new"><bean:write name="applicantForm" property="resumename"/></a></td>
		</tr>
		
       

		</table>
</form>


<br><br>
<logic:equal name="applicantForm" property="interviewState" scope="request" value="In Screening">
                 <%  
				 
				 if(user1.getUserId() == form.getOwnerid()){
				 %>
                 <a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=1&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>">
			<%=Constant.getResourceStringValue("aquisition.applicant.good_to_go",user1.getLocale())%></a> &nbsp;&nbsp;&nbsp;&nbsp;
			 <a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=2&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("Requisition.reject",user1.getLocale())%></a> &nbsp;&nbsp;&nbsp;&nbsp;

			<a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%></a>

			
				
				<%}%>
				</logic:equal>

				
                 <%  

				 if(form.getInterviewState()!=null && form.getInterviewState().startsWith(Common.INTERVIEW_ROUND)){
				 if(user1.getUserId() == form.getOwnerid()){
				 %>
                 <a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=1&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.good_to_go",user1.getLocale())%></a> &nbsp;&nbsp;&nbsp;&nbsp;
			 <a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=2&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("Requisition.reject",user1.getLocale())%></a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%></a>


			<a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%></a>

			<a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.decline",user1.getLocale())%></a>

			<a class="submodal-700-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.propose_new_time",user1.getLocale())%></a>
				
				<%}}%>
				



			<logic:equal name="applicantForm" property="interviewState" scope="request" value="Application Submitted">
			<%  
				 
				 if(user1.getUserId() == form.getOwnerid()){
				 %>
<a class="submodal-850-500" href="<%=request.getContextPath()%>/scheduleInterview.do?eventype=In Screening&method=interviewschedulescr&applicantId=<%=form.getApplicantId()%>">
<%=Constant.getResourceStringValue("aquisition.applicant.Send_Resume_for_Screening",user1.getLocale())%></a> <BR>
			<%}%>
			</logic:equal> <br>

            <logic:equal name="applicantForm" property="interviewState" scope="request" value="Cleared-In Screening">
			<%  
				 
				 if(user1.getUserId() == form.getOwnerid()){
				 %>
			<a class="submodal-850-400" href="<%=request.getContextPath()%>/scheduleInterview.do?eventype=Interview Round-1&method=interviewschedulescr&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.ScheduleInterviewRound_1",user1.getLocale())%></a> <BR>
			<%}%>
			</logic:equal> <br>
             <%
			         String stw = Common.CLEARED+"-"+Common.INTERVIEW_ROUND;
                     if(form.getInterviewState()!=null && form.getInterviewState().startsWith(stw)){
						 String evt = form.getInterviewState().substring(form.getInterviewState().lastIndexOf("-")+1);
						int eventtype=new Integer(evt).intValue();


			 %>
			 <%  
				 
				 if(user1.getUserId() == form.getOwnerid() && (form.getInterviewState()!=null && !form.getInterviewState().equals(Common.OFFER_INITIATED))){
				 %>
			 <a class="submodal-850-400" href="<%=request.getContextPath()%>/scheduleInterview.do?eventype=Interview Round-<%=eventtype+1%>&method=interviewschedulescr&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.ScheduleInterviewRound",user1.getLocale())%> - <%=eventtype+1%></a> <BR>
			<a class="submodal-850-400" href="<%=request.getContextPath()%>/scheduleInterview.do?method=interviwercommmentpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=
			<bean:write name="applicantForm" property="applicantId"/>"><%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%></a>

			<a class="submodal-700-600" href="<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>">
			<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%></a>

			<%}%>

				
           
    

			<%}%>

			 <%  
				 
				 if(user1.getUserId() == form.getOfferownerId() && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.READY_TO_RELEASE_OFFER))){
				 %>

                 <a class="submodal-700-600" href="<%=request.getContextPath()%>/applicant.do?method=releaseofferscr&applicantId=<%=form.getApplicantId()%>">
				 <%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%></a>

				 <%}%>

				 <%  
				 
				 if(user1.getUserId() == form.getOfferownerId() && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_DECLINED))){
				 %>
			 

			<a class="submodal-700-600" href="<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>">
			<%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%></a>

			<%}%>

			<%if(user1.getUserId() == form.getOfferownerId() && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION))){
				 %>
			 

			<a class="submodal-700-600" href="<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>">
			<%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%></a>

			<%}%>


			<%if(user1.getUserId() == form.getOfferownerId() && form.getInterviewState() != null && (form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION) ||  form.getInterviewState().equals(Common.OFFER) || form.getInterviewState().equals(Common.OFFER_ACCEPTED))){
				 %>
			 

			<a class="submodal-600-400" href="<%=request.getContextPath()%>/applicant.do?method=canceloffer&applicantId=<%=form.getApplicantId()%>">
			<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%></a>

			<%}%>