<logic:equal name="applicantForm" property="interviewState" scope="request" value="<%=Common.IN_SCREENING%>">
                 <%  
				 
				 if(user1.getUserId() == form.getOwnerid()){

					 String goodtogo = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=1&applicantId="+
			form.getApplicantId();
					  String rejected = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=2&applicantId="+
			form.getApplicantId();
					    String holdint = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=3&applicantId="+
			form.getApplicantId();
						String reassign = request.getContextPath()+"/scheduleInterview.do?method=interviwreassign&eventype="+form.getInterviewState()+"&applicantId="+
			form.getApplicantId();
						String decline = request.getContextPath()+"/scheduleInterview.do?method=declineinterview&applicantId="+form.getApplicantId()+"&eventype="+form.getInterviewState();
				 %>
	<td>
				 
<div class="div">
    <button type="button" onClick="javascript:goodToGo('<%=goodtogo%>');return false;" class="button">
          <%=Constant.getResourceStringValue("aquisition.applicant.add_feedback",user1.getLocale())%>
    </button>
</div>				 

	</td>			  
<td>
			
<div class="div">
    <button type="button" onClick="javascript:reassignReview('<%=reassign%>');return false;" class="button">
          <%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%>
    </button>
</div>

		</td>			  
<td>		

<div class="div">
    <button type="button" onClick="javascript:declineReview('<%=decline%>');return false;" class="button">
          <%=Constant.getResourceStringValue("aquisition.applicant.decline",user1.getLocale())%>
    </button>
</div>
</td>
			

			
				
				<%}%>
				</logic:equal>

				
                 <%  

				 if(form.getInterviewState()!=null && form.getInterviewState().startsWith(Common.INTERVIEW_ROUND)){
				 if(PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter)){
				%>
<td>

<div class="div">
    <button type="button" onClick="javascript:rescheduleInterviewround('<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&reschedule=yes&eventype=<%=form.getInterviewState()%>&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
          <%=Constant.getResourceStringValue("aquisition.applicant.Reschedule",user1.getLocale())%> <%=form.getInterviewState()%>
    </button>
</div>

</td><td></td><td></td>
				<%
				 }
				 }
				%>


				<%  

				 if(form.getInterviewState()!=null && form.getInterviewState().equals(Common.IN_SCREENING)){
				 if(PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter)){
			

String sendresumeforscrreningurl1 = request.getContextPath()+"/scheduleInterview.do?method=sendbulkresumeforscreening&reschedule=yes&eventype="+Common.IN_SCREENING+"&applicantids="+form.getApplicantId()+",";
				 // need to implement permission
				// if(user1.getUserId() == form.getOwnerid()){
				 %>
<td>
				  
<div>
    <button type="button" onClick="javascript:resendResumeForScrrening('<%=sendresumeforscrreningurl1%>');return false;" >
           <%=Constant.getResourceStringValue("aquisition.applicant.Re_Send_profile_for_screening",user1.getLocale())%>
    </button>
</div>

</td><td></td><td></td>
				<%
				 }
				 }
				%>

			


                 <%  

				 if(form.getInterviewState()!=null && form.getInterviewState().startsWith(Common.INTERVIEW_ROUND)){
				 if(user1.getUserId() == form.getOwnerid()){

					  String goodtogo = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=1&applicantId="+
			form.getApplicantId();
					  String rejected = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=2&applicantId="+
			form.getApplicantId();
					    String holdint = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=3&applicantId="+
			form.getApplicantId();
						String reassign = request.getContextPath()+"/scheduleInterview.do?method=interviwreassign&eventype="+form.getInterviewState()+"&applicantId="+
			form.getApplicantId();
						String decline = request.getContextPath()+"/scheduleInterview.do?method=declineinterview&applicantId="+form.getApplicantId()+"&eventype="+form.getInterviewState();
				 %>

				 <td>
               
<div>
    <button type="button" onClick="javascript:goodToGo('<%=goodtogo%>');return false;" >
           <%=Constant.getResourceStringValue("aquisition.applicant.add_feedback",user1.getLocale())%>
    </button>
</div>

	</td>
	<td>			

			
<div>
    <button type="button" onClick="javascript:reassignReview('<%=reassign%>');return false;" >
           <%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%>
    </button>
</div>

</td>
	<td>				
			

<div>
    <button type="button" onClick="javascript:declineReview('<%=decline%>');return false;" >
           <%=Constant.getResourceStringValue("aquisition.applicant.decline",user1.getLocale())%>
    </button>
</div>

</td>
					
				<%}}%>
				



			<logic:equal name="applicantForm" property="interviewState" scope="request" value="<%=Common.APPLICATION_SUBMITTED%>">
			<%  
String sendresumeforscrreningurl = request.getContextPath()+"/scheduleInterview.do?method=sendbulkresumeforscreening&eventype="+Common.IN_SCREENING+"&applicantids="+form.getApplicantId()+",";
				 // need to implement permission
				 if(user1.getUserId() == form.getOwnerid() || PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter)){
				 %>
<td>


<div>
    <button type="button" onClick="javascript:sendResumeForScrrening('<%=sendresumeforscrreningurl%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>
    </button>
</div>


	</td>
	<td>
<div>
    <button type="button" onClick="javascript:initiateoffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>
    </button>
</div>
	</td>
	<td></td>
	
			<%
			}
			%>
			</logic:equal> <br>

            <logic:equal name="applicantForm" property="interviewState" scope="request" value="<%=Common.CLEARED_IN_SCREENING%>">
			<%  
				 
				 if(user1.getUserId() == form.getOwnerid() || PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter)){
				 %>
<td>
              
<div>
    <button type="button" onClick="javascript:scheduleinterviewround('<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&eventype=<%=Common.INTERVIEW_ROUND%>-1&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.ScheduleInterviewRound_1",user1.getLocale())%>
    </button>
</div>

</td>
	<td>
<div>
    <button type="button" onClick="javascript:initiateoffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>
    </button>
</div>
</td>
	<td></td>
			<br>
			<%}%>
			</logic:equal> <br>
             <%
			         String stw = Common.CLEARED+"-"+Common.INTERVIEW_ROUND;
                     if(form.getInterviewState()!=null && form.getInterviewState().startsWith(stw)){
						 String evt = form.getInterviewState().substring(form.getInterviewState().lastIndexOf("-")+1);
						int eventtype=new Integer(evt).intValue();


			 %>
			 <%  
				 
				 if((user1.getUserId() == form.getOwnerid() || PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter)) && (form.getInterviewState()!=null && !form.getInterviewState().equals(Common.OFFER_INITIATED))){
				 %>

<td>
          

<div>
    <button type="button" onClick="return GB_showCenter('ScheduleInterview','<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&eventype=<%=Common.INTERVIEW_ROUND%>-<%=eventtype+1%>&applicantId=<%=form.getApplicantId()%>',500,900,messageret);return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.ScheduleInterviewRound",user1.getLocale())%> - <%=eventtype+1%>
    </button>
</div>

</td>
<td>
     
	
<div>
    <button type="button" onClick="javascript:onholdapplicant('<%=request.getContextPath()%>/scheduleInterview.do?method=holdpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%>
    </button>
</div>

</td>
<td>		


 <div>
    <button type="button" onClick="javascript:initiateoffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>
    </button>
</div>

</td>


          
			<%}%>

				
           
    

			<%}%>

			 <%  
				 
				 if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.READY_TO_RELEASE_OFFER))){
				 %>

   
<td>              
 
 <div>
    <button type="button" onClick="javascript:releaseoffer('<%=request.getContextPath()%>/applicant.do?method=releaseofferscr&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>
    </button>
</div>
				 
</td>
<td></td><td></td>
				 <%}%>

				 <%  
				 
				 if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_DECLINED))){
				 %>
	<td>		 
<div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>
			

			<%}%>

			<%if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId() ) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION))){
				 %>
			 
</td>
           			
 <div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>
</td>
<td></td><td></td>
			

			<%}%>


			<%if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId() ) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_APPROVAL_REJECTED))){
				 %>
			 
</td>
           			
   <div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>

	</td>
<td></td><td></td>		

			<%}%>


			<%if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId() )&& form.getInterviewState() != null && (form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION) ||  form.getInterviewState().equals(Common.OFFER) || form.getInterviewState().equals(Common.OFFER_ACCEPTED))){
				 %>
	<td>		 

   <div>
    <button type="button" onClick="javascript:canceloffer('<%=request.getContextPath()%>/applicant.do?method=canceloffer&applicantId=<%=form.getApplicantId()%>');return false;" class="negative">
        <img src="jsp/images/cross.png" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>"/> 
     <%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>			

			<%}%>

			<%if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && form.getInterviewState() != null && (form.getInterviewState().equals(Common.OFFER_ACCEPTED))){
				 %>
			 

			
<td>


   <div>
    <button type="button" onClick="javascript:onboardjoined('<%=request.getContextPath()%>/applicant.do?method=onboard&applicantId=<%=form.getApplicantId()%>');return false;">
      <%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>

<!--						 <div style="margin: 6px 6px 6px 0px;" class="buttonwrapper">
<a class="positive" href="#" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>')">
<span><%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%></span></a> </div>

	-->		

			<%}%>



<%  
				 
				 if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER))){
				 %>

                 
<td>

   <div>
    <button type="button" onClick="javascript:releaseoffer('<%=request.getContextPath()%>/applicant.do?method=releaseofferscr&applicantId=<%=form.getApplicantId()%>&resend=true');return false;">
      <%=Constant.getResourceStringValue("aquisition.applicant.Release_offer_resend",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>
				 <%}%>


				 <%
					 if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.ON_BOARD))){
				 %>

                 
<td>

   <div>
    <button type="button" onClick="javascript:markresigned('<%=request.getContextPath()%>/applicant.do?method=markresignedscr&applicantId=<%=form.getApplicantId()%>');return false;">
       <%=Constant.getResourceStringValue("aquisition.applicant.Mark.Resigned",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>
				 <%}%>


				 <%
					 if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_ACCEPTED) && form.getInitiateJoiningProcess().equals("Not started"))){
				 %>

   <td>              

   <div>
    <button type="button" onClick="javascript:initiateOnboarding('<%=request.getContextPath()%>/onboarding.do?method=initiateOnBoarding&applicantids=<%=form.getApplicantId()%>,');return false;">
        <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%>
    </button>
</div>
</td>
<td></td><td></td>
				 <%}%>


				<%
				    
					 if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && (form.getInterviewState()!=null && form.getInterviewState().startsWith(Common.REJECTED))){
				 %>

     <td>            

                 

<div>
    <button type="button" onClick="javascript:recallapplicant('<%=request.getContextPath()%>/applicant.do?method=recallapplicantscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>');return false;">
         <%=Constant.getResourceStringValue("aquisition.applicant.Recall",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>
				 <%}%>



<%if(form.getStatus()!=null && form.getStatus().equals("H")) {
			 
				 if(PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter) ||(user1.getUserId() == form.getOwnerid())){
			
	String relhold = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=1&applicantId="+
			form.getApplicantId()+"&st="+form.getStatus();
	%>

<div>
    <button type="button" onClick="javascript:releasehold('<%=relhold%>');return false;">
         <%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.release_hold",user1.getLocale())%>
    </button>
</div>

<%}}%>


<%
		String offerapproveurl = request.getContextPath()+"/applicant.do?method=offerapproverejectcomment&applicantId="+form.getApplicantId()+"&type=approve&offerapproverId=";
		String offerrejecturl = request.getContextPath()+"/applicant.do?method=offerapproverejectcomment&applicantId="+form.getApplicantId()+"&type=reject&offerapproverId=";
List approverList = form.getOfferApproverList();

if(approverList!=null && approverList.size() > 0){

boolean isOfferRejected=false;
for(int i=0;i<approverList.size();i++){
	OfferApprover offerapprover_temp = (OfferApprover)approverList.get(i);
    if(offerapprover_temp.getApproved().equals("R")){
		isOfferRejected = true;
		break;
	}
}

for(int i=0;i<approverList.size();i++){
	OfferApprover offerapprover = (OfferApprover)approverList.get(i);

	 if(offerapprover.getApproved().equals("N") && !isOfferRejected){
     if(user1.getUserId() == offerapprover.getApproverId()){
		 offerapproveurl = offerapproveurl+offerapprover.getOfferApproverId();
		 offerrejecturl = offerrejecturl + offerapprover.getOfferApproverId();
	
%>
<td>

<div>
    <button type="button" onClick="javascript:approveOffer('<%=offerapproveurl%>');return false;" class="positive">
        <img src="jsp/images/tick.png" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>"/> 
        <%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>
    </button>
</div>
           
</td>
<td>
<div>
    <button type="button" onClick="javascript:rejectOffer('<%=offerrejecturl%>');return false;" class="negative">
        <img src="jsp/images/cross.png" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>"/> 
       <%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>
    </button>
</div>
			
</td><td></td>



<%
// approver owner check end	
}
}
}
}
%>