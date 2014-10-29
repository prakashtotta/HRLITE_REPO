
	<STYLE type="text/css">
.button {
   border-top: 1px solid #96d1f8;
   background: #3B5999;
   padding: 5px 10px;
   border-radius: 8px;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: #ccc;
   font-size: 14px;
   font-family: Georgia, serif;
   text-decoration: none;
   vertical-align: middle;
   }
.button:hover {
   border-top-color: #3B5998;
   background: #3B5998;
   color: white;
   }
</style>
<logic:equal name="applicantForm" property="interviewState" scope="request" value="<%=Common.IN_SCREENING%>">
                 <%  
				 
				 if(BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup())){

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
    <button type="button" onClick="javascript:goodToGo('<%=goodtogo%>');return false;" class="button" >
          <%=Constant.getResourceStringValue("aquisition.applicant.add_feedback",user1.getLocale())%>
    </button>
</div>				 

	</td>			  
<td>
			
<div>
    <button type="button" onClick="javascript:reassignReview('<%=reassign%>');return false;" class="button" >
          <%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%>
    </button>
</div>

		</td>			  
<td>		

<div>
    <button type="button" onClick="javascript:declineReview('<%=decline%>');return false;" class="button" >
          <%=Constant.getResourceStringValue("aquisition.applicant.decline",user1.getLocale())%>
    </button>
</div>
</td>
			

			
				
				<%}%>
				</logic:equal>

				
                 <%  

				 if(form.getInterviewState()!=null && form.getInterviewState().startsWith(Common.INTERVIEW_ROUND)){
				 if(isApplicantEditAllowed){
				%>
<td>

<div>
    <button type="button"  onClick="javascript:rescheduleInterviewround('<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&reschedule=yes&eventype=<%=form.getInterviewState()%>&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
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
				 if(isApplicantEditAllowed){
			

String sendresumeforscrreningurl1 = request.getContextPath()+"/scheduleInterview.do?method=sendbulkresumeforscreening&reschedule=yes&eventype="+Common.IN_SCREENING+"&applicantids="+form.getApplicantId()+",";
				 // need to implement permission
				// if(user1.getUserId() == form.getOwnerid()){
				 %>
<td>
				  
<div>
    <button type="button" onClick="javascript:resendResumeForScrrening('<%=sendresumeforscrreningurl1%>');return false;" class="button" >
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
				 if(BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup())){

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
    <button type="button" onClick="javascript:goodToGo('<%=goodtogo%>');return false;" class="button" >
           <%=Constant.getResourceStringValue("aquisition.applicant.add_feedback",user1.getLocale())%>
    </button>
</div>

	</td>
	<td>			

			
<div>
    <button type="button" onClick="javascript:reassignReview('<%=reassign%>');return false;" class="button" >
           <%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%>
    </button>
</div>

</td>
	<td>				
			

<div>
    <button type="button" onClick="javascript:declineReview('<%=decline%>');return false;" class="button" >
           <%=Constant.getResourceStringValue("aquisition.applicant.decline",user1.getLocale())%>
    </button>
</div>

</td>
					
				<%}}%>
				



			
			<% if(form.getInterviewState().equals(Common.APPLICATION_SUBMITTED)){%>
			<%  
String sendresumeforscrreningurl = request.getContextPath()+"/scheduleInterview.do?method=sendbulkresumeforscreening&eventype="+Common.IN_SCREENING+"&applicantids="+form.getApplicantId()+",";
				 // need to implement permission
				 if(BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup()) || isApplicantEditAllowed){
				 %>
<td>


<div>
    <button type="button" onClick="javascript:sendResumeForScrrening('<%=sendresumeforscrreningurl%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%>
    </button>
</div>


	</td>
	<td>
<div>
    <button type="button" onClick="javascript:initiateoffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>
    </button>
</div>
	</td>
<td></td>
	
			<%
			}
			%>
			<%
			}// application submitted check
			%>
			 <br>

           
             <% if(form.getInterviewState().equals(Common.CLEARED_IN_SCREENING)){%>

			<%  
				 
				 if(BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup()) || isApplicantEditAllowed){
				 %>
<td>
              
<div>
    <button type="button" onClick="javascript:scheduleinterviewround('<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&eventype=<%=Common.INTERVIEW_ROUND%>-1&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.ScheduleInterviewRound_1",user1.getLocale())%>
    </button>
</div>

</td>
	<td>
<div>
    <button type="button" onClick="javascript:initiateoffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>
    </button>
</div>
</td>
	<td></td>
			<br>
			<%}%>
			<%
			}// CLEARED_IN_SCREENING check
			%>
			
			<br>
             <%
			         String stw = Common.CLEARED+"-"+Common.INTERVIEW_ROUND;
                     
					 
                     
                    if(form.getInterviewState()!=null && form.getInterviewState().startsWith(stw)){
						 String evt = form.getInterviewState().substring(form.getInterviewState().lastIndexOf("-")+1);
						int eventtype=new Integer(evt).intValue();


			 %>
			 <%  
				 
				 if((BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup()) || isApplicantEditAllowed) && (form.getInterviewState()!=null && !form.getInterviewState().equals(Common.OFFER_INITIATED))){
				 %>

<td>
          

<div>
    <button type="button" onClick="javascript:scheduleinterviewround('<%=request.getContextPath()%>/scheduleInterview.do?method=interviewschedulescr&eventype=<%=Common.INTERVIEW_ROUND%>-<%=eventtype+1%>&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.ScheduleInterviewRound",user1.getLocale())%> - <%=eventtype+1%>
    </button>
</div>

</td>
<td>
     
<%--	
<div>
    <button type="button" onClick="javascript:onholdapplicant('<%=request.getContextPath()%>/scheduleInterview.do?method=holdpage&eventype=<%=form.getInterviewState()%>&status=3&applicantId=<%=form.getApplicantId()%>');return false;" >
            <%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%>
    </button>
</div>
--%>
</td>
<td>		


 <div>
    <button type="button" onClick="javascript:initiateoffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>
    </button>
</div>

</td>


          
			<%}%>

				
           
    

			<%}%>

			 <%  
				  
				 if((BOFactory.getApplicantBO().isReInitiateOfferButton(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup(),form.getOfferownerId())) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.READY_TO_RELEASE_OFFER))){
				 %>

   
<td>              
 
 <div>
    <button type="button" onClick="javascript:releaseoffer('<%=request.getContextPath()%>/applicant.do?method=releaseofferscr&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>
    </button>
</div>
				 
</td>

				 <%}%>

				 <%  
				 
				 if((BOFactory.getApplicantBO().isReInitiateOfferButton(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup(),form.getOfferownerId())) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_DECLINED))){
				 %>
	<td>		 
<div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>

</td>

			

			<%}%>

			<%if((BOFactory.getApplicantBO().isReInitiateOfferButton(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup(),form.getOfferownerId())) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION))){
				 %>
			 
<td>
           			
 <div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>
</td>
<td></td><td></td>
			

			<%}%>


			

<%if((BOFactory.getApplicantBO().isReInitiateOfferButton(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup(),form.getOfferownerId())) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_APPROVAL_REJECTED))){
				 %>
			 
</td>
           			
   <div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>

	</td>
<td></td><td></td>	


			<%}%>



			<%if((BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup()))&& form.getInterviewState() != null && (form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION) ||  form.getInterviewState().equals(Common.OFFER) || form.getInterviewState().equals(Common.OFFER_ACCEPTED))){
				 %>
	<td>		 

   <div>
    <button type="button" onClick="javascript:canceloffer('<%=request.getContextPath()%>/applicant.do?method=canceloffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button">
        <img src="jsp/images/cross.png" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>"/> 
     <%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>			

			<%}%>

			<%if((BOFactory.getApplicantBO().isReInitiateOfferButton(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup(),form.getOfferownerId())) && form.getInterviewState() != null && (form.getInterviewState().equals(Common.OFFER_ACCEPTED))){
				 %>
			 

			
<td>


   <div>
    <button type="button" onClick="javascript:onboardjoined('<%=request.getContextPath()%>/applicant.do?method=onboard&applicantId=<%=form.getApplicantId()%>');return false;" class="button">
      <%=Constant.getResourceStringValue("aquisition.applicant.on_board_joined",user1.getLocale())%>
    </button>
</div>

</td>
<td>
   <div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>
</td>
<td></td>

<!--						 <div style="margin: 6px 6px 6px 0px;" class="buttonwrapper">
<a class="positive" href="#" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>')">
<span><%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%></span></a> </div>

	-->		

			<%}%>



<%  
				 
				 if((BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup())) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER))){

					 
					 String offeracceptonbehalf = request.getContextPath()+"/applicant.do?method=acceptofferOnBehalfsrc&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
					 String offerrejectonbehalf = request.getContextPath()+"/applicant.do?method=declineofferOnBehalfsrc&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
				 %>

                 
<td>

   <div>
    <button type="button" onClick="javascript:releaseoffer('<%=request.getContextPath()%>/applicant.do?method=releaseofferscr&applicantId=<%=form.getApplicantId()%>&resend=true');return false;" class="button">
      <%=Constant.getResourceStringValue("aquisition.applicant.Release_offer_resend",user1.getLocale())%>
    </button>
</div>

</td>
<% if(Constant.getValue("offer.accept.allow.onbehalf") != null && Constant.getValue("offer.accept.allow.onbehalf").equals("yes")){%>
<td>
   <div>
    <button type="button" onClick="javascript:acceptofferonbehalf('<%=offeracceptonbehalf%>');return false;" class="button">
      <%=Constant.getResourceStringValue("aquisition.applicant.accept_offer",user1.getLocale())%>
    </button>
</div>
</td>
<td>
<div>
    <button type="button" onClick="javascript:declineofferonbehalf('<%=offerrejectonbehalf%>');return false;" class="button">
      <%=Constant.getResourceStringValue("aquisition.applicant.decline_offer",user1.getLocale())%>
    </button>
</div>

</td>


<% if(Constant.getValue("offer.modification.allowed") != null && Constant.getValue("offer.modification.allowed").equals("yes")){%>
<td>
<div>
    <button type="button" onClick="javascript:modificationrequest();return false;" class="button">
      <%=Constant.getResourceStringValue("offer.modification.request",user1.getLocale())%>
    </button>
</div>
</td>
<%}%>
<%}else{%>
<td></td><td></td>
<%}%>


				 <%}%>


				 <%
					 if((user1.getUserId() == form.getOfferownerId() || PermissionBO.isHiringManagerOrRecruiter(user1,hiringmgr.getUserId(),recruiter) ) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.ON_BOARD))){
				 %>

                 
<td>

   <div>
    <button type="button" onClick="javascript:markresigned('<%=request.getContextPath()%>/applicant.do?method=markresignedscr&applicantId=<%=form.getApplicantId()%>');return false;" class="button">
       <%=Constant.getResourceStringValue("aquisition.applicant.Mark.Resigned",user1.getLocale())%>
    </button>
</div>

</td>
<td>
<%
User userCreated = BOFactory.getUserBO().getUserCreatedFromApplicant(form.getApplicantId());
if(userCreated != null){

%>
 <div>
    <button type="button" onClick="javascript:viewUserProfile('<%=request.getContextPath()%>/user.do?method=userprofile&userId=<%=EncryptDecrypt.encrypt(String.valueOf(userCreated.getUserId()))%>');return false;" class="button">
       <%=Constant.getResourceStringValue("aquisition.applicant.user.profile",user1.getLocale())%>
    </button>
</div>
<%}else{%>
   <div>
    <button type="button" onClick="javascript:convertToUser('<%=request.getContextPath()%>/user.do?method=convertToUser&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>');return false;" class="button">
       <%=Constant.getResourceStringValue("aquisition.applicant.convert.to.user",user1.getLocale())%>
    </button>
</div>
<%}%>
</td>
<td></td>
				 <%}%>


				 <%
					 if((user1.getUserId() == form.getOfferownerId() || user1.getUserId() == hiringmgr.getUserId()) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_ACCEPTED) && form.getInitiateJoiningProcess().equals("Not started"))){
				 %>

   <td>              

   <div>
    <button type="button" onClick="javascript:initiateOnboarding('<%=request.getContextPath()%>/onboarding.do?method=initiateOnBoarding&applicantids=<%=form.getApplicantId()%>,');return false;" class="button">
        <%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding",user1.getLocale())%>
    </button>
</div>
</td>
<td></td><td></td>
				 <%}%>


				<%
				    
					 if(isApplicantEditAllowed && (form.getInterviewState()!=null && form.getInterviewState().startsWith(Common.REJECTED))){
				 %>

     <td>            

                 

<div>
    <button type="button" onClick="javascript:recallapplicant('<%=request.getContextPath()%>/applicant.do?method=recallapplicantscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>');return false;" class="button">
         <%=Constant.getResourceStringValue("aquisition.applicant.Recall",user1.getLocale())%>
    </button>
</div>

</td>
<td></td><td></td>
				 <%}%>



<%if(form.getStatus()!=null && form.getStatus().equals("H")) {
		 
				 if(isApplicantEditAllowed ||(BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup()))){
			
	String relhold = request.getContextPath()+"/scheduleInterview.do?method=interviwercommmentpage&eventype="+form.getInterviewState()+"&status=1&applicantId="+
			form.getApplicantId()+"&st="+form.getStatus();
	%>
<td>
<div>
    <button type="button" onClick="javascript:releasehold('<%=relhold%>');return false;" class="button">
         <%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.release_hold",user1.getLocale())%>
    </button>
</div>
</td>
<%}}%>


<%
		String offerapproveurl = request.getContextPath()+"/applicant.do?method=offerapproverejectcomment&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid()+"&type=approve&offerapproverId=";
		String offerrejecturl = request.getContextPath()+"/applicant.do?method=offerapproverejectcomment&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid()+"&type=reject&offerapproverId=";
List approverList = BOFactory.getApplicantBO().getOfferApproverList(form.getApplicantId());
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
		 if(BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),offerapprover.getApproverId(),form.getIsGroup(),form.getOwnerGroup())){

		 offerapproveurl = offerapproveurl+offerapprover.getOfferApproverId();
		 offerrejecturl = offerrejecturl + offerapprover.getOfferApproverId();
	
%>
<td>

<div>
    <button type="button" onClick="javascript:approveOffer('<%=offerapproveurl%>');return false;" class="button">
        <img src="jsp/images/tick.png" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>"/> 
        <%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%>
    </button>
</div>
           
</td>
<td>
<div>
    <button type="button" onClick="javascript:rejectOffer('<%=offerrejecturl%>');return false;" class="button">
        <img src="jsp/images/cross.png" alt="<%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>"/> 
       <%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%>
    </button>
</div>
			
</td><td></td>



<%
// approver owner check end	
break;
}

}
}
}
%>

<%if((BOFactory.getApplicantBO().isReInitiateOfferButton(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup(),form.getOfferownerId()) ) && (form.getInterviewState()!=null && form.getInterviewState().equals(Common.OFFER_CANCELED))){
				 %>
 	<td>		
   <div>
    <button type="button" onClick="javascript:reInitiateOffer('<%=request.getContextPath()%>/applicant.do?method=initiateoffer&applicantId=<%=form.getApplicantId()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.Re_Initiate_offer",user1.getLocale())%>
    </button>
</div>
</td>
<%}%>

<%if((isApplicantEditAllowed || BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(),form.getOwnerid(),form.getIsGroup(),form.getOwnerGroup()) ) && (form.getStatus()!=null && form.getStatus().equals("D"))){
				 %>
 	<td>			
   <div>
    <button type="button" onClick="javascript:undodelete('<%=request.getContextPath()%>/applicant.do?method=undodeletescr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.undodelete",user1.getLocale())%>
    </button>
</div>
</td>
<%}%>

<%if(isApplicantEditAllowed || PermissionBO.isPermissionApplied(Common.APPLICANTS_MOVE_TO_REQUISTIONS_TALENTPOOL_BACK,user1)){
				 %>
 	<td>		
   <div>
    <button type="button" onClick="javascript:movetoreqtalentpool('<%=request.getContextPath()%>/applicant.do?method=movetoreqtalentpoolsrc&applicantId=<%=form.getApplicantId()%>&uuid=<%=form.getUuid()%>');return false;" class="button" >
            <%=Constant.getResourceStringValue("aquisition.applicant.moveto",user1.getLocale())%>
    </button>
</div>
</td>
<%}%>
<td>


</td>









