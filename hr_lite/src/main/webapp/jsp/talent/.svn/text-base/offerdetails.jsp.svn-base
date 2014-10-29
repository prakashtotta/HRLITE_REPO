<%@ include file="../common/includejava.jsp" %>


<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>
	
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

<style type="text/css">
.yui-navset button {
    position:absolute;
    top:0;
    right:0;
}
</style>
<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>

<style type="text/css">
#example {
    height:30em;
}

label { 
    display:block;
    float:left;
    width:45%;
    clear:left;
}

.clear {
    clear:both;
}

#resp {
    margin:10px;
    padding:5px;
    border:1px solid #ccc;
    background:#fff;
}

#resp li {
    font-family:monospace
}

.yui-pe .yui-pe-content {
    display:none;
}
</style>



<body class="yui-skin-sam">

<script language="javascript">


</script>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>




<%
User offerInitiator = new User();
if(form.getOfferinitiatedby() != null){
 offerInitiator = UserBO.getUserFullNameEmailAndUserId(form.getOfferinitiatedby());
}
User offerReleaser = new User();
if(form.getOfferreleaseddby() != null){
offerReleaser = UserBO.getUserFullNameEmailAndUserId(form.getOfferreleaseddby());
}

%>

<%
if(form.getInterviewState() != null && (form.getInterviewState().indexOf("Offer") > -1 || form.getInterviewState().equals(Common.ON_BOARD)) ){
%>

<form name ="applicantForm" action="/applicant.do">
		<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Offerdetails",user1.getLocale())%>  </div>
    
	 <div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
	<table border="0" width="100%" >
	<font color = red ><html:errors /> </font>
		
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%> </td>
			<td class="bodytext"><b><bean:write name="applicantForm" property="interviewState"/></b></td>
		</tr>
			
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_target_date",user1.getLocale())%> </td>
			<td class="bodytext">
			<%
		
		if(!StringUtils.isNullOrEmpty(form.getTargerofferdate())){%> 
			<bean:write name="applicantForm" property="targerofferdate"/>
			<%}else{%>
			<%=Constant.getResourceStringValue("admin.NA",user1.getLocale())%>
			<%}%>
			</td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Target_joining_date",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="targetjoiningdate"/>
			
			<%if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>
<%if(form.getChagetargetjoiningdate() != null){
		Date chnagedate = DateUtil.convertStringDateToDate(form.getChagetargetjoiningdate(), DateUtil.getDatePatternFormat(user1.getLocale()));
		Date earliertargetjoingdate = DateUtil.convertStringDateToDate(form.getTargetjoiningdate(), DateUtil.getDatePatternFormat(user1.getLocale()));
			if(chnagedate.compareTo(earliertargetjoingdate)!=0){		
%>
<font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.request_target_joining_date",user1.getLocale())%> : <bean:write name="applicantForm" property="chagetargetjoiningdate"/> </font>
<%}}}%>
			
			</td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Current_CTC",user1.getLocale())%> </td>
			<td class="bodytext"><!-- <bean:write name="applicantForm" property="currectctc"/>-->
			<%=StringUtils.isNullOrEmpty(form.getCurrectctc()) ?"-":form.getCurrectctc()%>&nbsp;<bean:write name="applicantForm" property="currectctccurrencycode"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Expected_CTC",user1.getLocale())%></td>
			<td class="bodytext">
			<!--  <bean:write name="applicantForm" property="expectedctc"/> -->
			<%=StringUtils.isNullOrEmpty(form.getExpectedctc()) ?"-":form.getExpectedctc()%>&nbsp;<bean:write name="applicantForm" property="expectedctccurrencycode"/>		
			</td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offered_CTC",user1.getLocale())%> </td>
			<td class="bodytext"><!--  <bean:write name="applicantForm" property="offeredctc"/> -->
			<%=StringUtils.isNullOrEmpty(form.getOfferedctc()) ?"-":form.getOfferedctc()%>&nbsp;<bean:write name="applicantForm" property="offeredctccurrencycode"/>

			<% if(form.getSalaryPlan() != null && !StringUtils.isNullOrEmpty(form.getOfferedctc()) && form.getSalaryPlan().getToRangeAmount() < new Long(form.getOfferedctc()).longValue()){%>
			<font color="red">
			<%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%> #
			<%=form.getSalaryPlan().getFromRangeAmount()%> -- <%=form.getSalaryPlan().getToRangeAmount()%>  <%=form.getSalaryPlan().getCurrencyCode()%>
			<%}%>
			
			<%if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>
<%if(!StringUtils.isNullOrEmpty(form.getChangeofferedctc())){
	if(!form.getChangeofferedctc().equals(form.getOfferedctc())){			
%>
<font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.request_CTC",user1.getLocale())%> : <bean:write name="applicantForm" property="changeofferedctc"/>&nbsp;<bean:write name="applicantForm" property="changeofferedctccurrencycode"/> </font>
<%}}}%>

			</td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.other_benefits",user1.getLocale())%></a></td>
			<td class="bodytext">
			<%
				List benefitList = form.getOtherBenefitsList();
			    if(benefitList != null && benefitList.size()>0){
					for(int i=0;i<benefitList.size();i++){
                      ApplicantOtherBenefits otherbenefit = (ApplicantOtherBenefits)benefitList.get(i);
			%>
			  <%=otherbenefit.getName()%>&nbsp;&nbsp;<%=otherbenefit.getAmount()%>&nbsp;<bean:write name="applicantForm" property="expectedctccurrencycode"/> <br>
			
			<%
					}// end for loop	
			}else{%>
			
			<%=Constant.getResourceStringValue("admin.NA",user1.getLocale())%> 
			
			<%}%>
			</td>
		</tr>

	

		<tr>
		<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user1.getLocale())%> </td>
		<%if(!StringUtils.isNullOrEmpty(form.getIsofferownerGroup()) && form.getIsofferownerGroup().equals("Y")){%>
		<td class="bodytext"><img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=form.getOfferownerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=form.getOfferownerName()%></a> </td> 
		<%}else{%>
		<td class="bodytext"><img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=form.getOfferownerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=form.getOfferownerName()%></a> </td>
		<%}%>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_initiated_by",user1.getLocale())%> </td>
			<td class="bodytext">			
			<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=offerInitiator.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=offerInitiator.getFirstName()+ " "+offerInitiator.getLastName()%></a>
 on <%=DateUtil.convertSourceToTargetTimezone(form.getOfferinitiationdate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
		
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_initiation_comment",user1.getLocale())%>  </td>
			<td class="bodytext"><span id="modelDescription">
			<%=(form.getOfferInitiationComment() == null)?"":StringUtils.doSpecialCharacters(form.getOfferInitiationComment())%>
			</span></td>
		</tr>
		<tr><td></td><td></td></tr>



<%
String iniwatchlistvalue ="N/A";
String intwatchlisttempurl = "";

List iniwatchlist = form.getInitiateOfferwatchList();

if(iniwatchlist != null && iniwatchlist.size()>0){

for(int i=0;i<iniwatchlist.size();i++){
OfferWatchList ch = (OfferWatchList)iniwatchlist.get(i);

  intwatchlisttempurl   = intwatchlisttempurl + "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+ch.getUserId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+ch.getUsername()+"</a>&nbsp;&nbsp;";


}

iniwatchlistvalue = "<span id=\"watchlist\">"+intwatchlisttempurl+"</span>";

} // end null check


	%>
<tr>			
<td><%=Constant.getResourceStringValue("aquisition.applicant.Watch_list",user1.getLocale())%></td>
				<td><%=Constant.getResourceStringValue("admin.NA",user1.getLocale())%> 
				
				</td>
			</tr>


		<tr>
<td><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></td>
</tr>
<tr>
<td colspan="2">
<%
List attachmentList = form.getOfferAttachmentList();

if(attachmentList.size()>0){
	%>
	<table border="0" width="100%" >
			<tr bgcolor="#B2D2FF">
				<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
				<td><%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%></td>
				<td>&nbsp;&nbsp <%=Constant.getResourceStringValue("aquisition.applicant.added_date",user1.getLocale())%></td>
				<td></td>
			</tr>
<%
		for(int i=0;i<attachmentList.size();i++){
		ApplicantOfferAttachment offerattach = (ApplicantOfferAttachment)attachmentList.get(i);
		
%>
			<tr>
			
			<td>
			<a href="<%=request.getContextPath()%>/applicant.do?method=offerattachmentdetails&uuid=<%=offerattach.getUuid()%>"  ><%=offerattach.getAttahmentname()%></a></td><td><%=offerattach.getCreatedBy()%></td><td>
			<%=DateUtil.convertSourceToTargetTimezone(offerattach.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>
			</td>
			<td>
			<a href="#" onClick="deleteattachment('<%=offerattach.getUuid()%>');return false;">
			<img src="jsp/images/delete.gif" border="0" alt="Delete" title="delete"/>
			</td>
			</tr>
		<%}%>
</table>

<%}%>
</td>
</tr>

</table>
</DIV>






		<%
		String offerapproveurl = request.getContextPath()+"/applicant.do?method=offerapproverejectcomment&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid()+"&type=approve&offerapproverId=";
		String offerrejecturl = request.getContextPath()+"/applicant.do?method=offerapproverejectcomment&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid()+"&type=reject&offerapproverId=";
List approverList = form.getOfferApproverList();

%>
<br><br>
<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("Requisition.ApprovalStatistics",user1.getLocale())%>  </div>

<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
<table border="0" width="100%" >
<%
		if(approverList.size() > 0){

%>

<tr bgcolor="#B2D2FF">
<td><%=Constant.getResourceStringValue("Requisition.Level",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.ApproverName",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
<td></td>

</tr>
<%
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

String approverurltemp   =  "";

	if(!StringUtils.isNullOrEmpty(offerapprover.getIsGroup()) && offerapprover.getIsGroup().equals("Y")){

		approverurltemp = "<img src='jsp/images/User-Group-icon.png'><a href='#' onClick=window.open("+"'"+"usergroup.do?method=edituser&readPreview=2&userId=="+offerapprover.getApproverId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+offerapprover.getApproverName()+"</a>";
        if(!StringUtils.isNullOrEmpty(offerapprover.getOnbehalfApproverName())){
			approverurltemp = approverurltemp + " ("+
             "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+offerapprover.getOnbehalfApproverId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+offerapprover.getOnbehalfApproverName()+"</a>"
			+ " )";

		}

	}else{
		approverurltemp = "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+offerapprover.getApproverId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+offerapprover.getApproverName()+"</a>";
	}



	String approvetext = "Approved";
	if(offerapprover.getApproved().equals("Y")){
		approvetext = "Approved";
	}
	if(offerapprover.getApproved().equals("N")){
		approvetext = "Not Approved";
	}
	if(offerapprover.getApproved().equals("R")){
		approvetext = "Rejected";
	}
		String bgcolor = "";
	if(i%2 == 1)bgcolor ="#edf5ff";
%>
<tr bgcolor=<%=bgcolor%>>
<td><%=Constant.getResourceStringValue("Requisition.Level",user1.getLocale())%>-<%=i+1%></td>
<td><%=approverurltemp%></td>
<td><%=approvetext%></td>
<td><%=(offerapprover.getApprovedDate()==null)?"":DateUtil.convertSourceToTargetTimezone(offerapprover.getApprovedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
<td><%=(offerapprover.getComment() == null)? "":StringUtils.doSpecialCharacters(offerapprover.getComment())%></td>
<td>
<!--
<% if(offerapprover.getApproved().equals("N") && !isOfferRejected){
     if(user1.getUserId() == offerapprover.getApproverId()){
		 offerapproveurl = offerapproveurl+offerapprover.getOfferApproverId();
		 offerrejecturl = offerrejecturl + offerapprover.getOfferApproverId();
			%>

             <div style="margin: 6px 6px 6px 0px;"  class="buttonwrapper">
<a class="squarebutton" href="#" onClick="javascript:approveOffer('<%=offerapproveurl%>');return false;"><span><%=Constant.getResourceStringValue("aquisition.applicant.Approve_offer",user1.getLocale())%></span></a> </div>
<div style="margin: 6px 6px 6px 0px; " class="buttonwrapper">
<a class="squarebutton" href="#" onClick="javascript:rejectOffer('<%=offerrejecturl%>');return false;"><span><%=Constant.getResourceStringValue("aquisition.applicant.Reject_offer",user1.getLocale())%></span></a> </div>
			



<%
// approver owner check end	
}}%>
-->
</td>
</tr>
<%}
} else {%>
<%=Constant.getResourceStringValue("aquisition.applicant.approver_not_added",user1.getLocale())%>
<%}%>
</table>
</DIV>

<br><br>

<% if(form.getInterviewState().equals(Common.OFFER) || form.getInterviewState().equals(Common.OFFER_ACCEPTED) || form.getInterviewState().equals(Common.OFFER_DECLINED) || form.getInterviewState().equals(Common.OFFER_CANCELED) || form.getInterviewState().equals(Common.ON_BOARD)){%>

<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Offer_release_details",user1.getLocale())%>  </div>

	<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
	<table border="0" width="100%">
	
		
		<tr>
			<td class="bodytext" width="38%"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_released_date",user1.getLocale())%> </td>
			<td class="bodytext"><%=DateUtil.convertSourceToTargetTimezone(form.getOfferreleasedate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
			
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_released_by",user1.getLocale())%> </td>
			<td class="bodytext">
			<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=offerReleaser.getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=offerReleaser.getFirstName()+ " "+offerReleaser.getLastName()%></a>
			</td>
		</tr>

		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Note",user1.getLocale())%> </td>
			<td class="bodytext"><%=(form.getOfferReleaseComment() == null)? "":StringUtils.doSpecialCharacters(form.getOfferReleaseComment())%></td>
		</tr>

		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.IsOfferEmailSentMsg",user1.getLocale())%> </td>
			<td class="bodytext"><bean:write name="applicantForm" property="isOfferEmailSent"/></td>
		</tr>
         <% if(!StringUtils.isNullOrEmpty(form.getOfferEmailSentError())){%>
			<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.offer.email.sent.error.Msg",user1.getLocale())%> </td>
			  <td class="bodytext"><%=form.getOfferEmailSentError()%></td>
			<!--<td class="bodytext"><%=Constant.getResourceStringValue("server.error",user1.getLocale())%></td>-->
		</tr>
        <%}%>
   
		<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.Offer_Attachments",user1.getLocale())%></td>
</tr>
<tr>
<td colspan="2">
<%
List offerattachmentList = form.getOfferReleaseAttachmentList();

if(offerattachmentList != null && offerattachmentList.size()>0){
	%>
	<table border="0" width="100%" >
	<tr bgcolor="#B2D2FF">
		<td width="20%"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></td>
		<td width="20%"><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
		<td ><%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%></td>
		<td width="25%">&nbsp;&nbsp <%=Constant.getResourceStringValue("aquisition.applicant.added_date",user1.getLocale())%></td>
		<td></td>
	</tr>
<%
		for(int i=0;i<offerattachmentList.size();i++){
		ApplicantOfferAttachment offerattach = (ApplicantOfferAttachment)offerattachmentList.get(i);
		String deleteurl = "applicant.do?method=deleteattachmentrelatedtooffer&attachuuid="+offerattach.getUuid()+"&applicantId="+form.getApplicantId();

%>
	<tr>
		<td><%=(offerattach.getAttahmentdetails()==null)?"":offerattach.getAttahmentdetails()%></td>
		<td>
		<a href="<%=request.getContextPath()%>/applicant.do?method=offerattachmentdetails&uuid=<%=offerattach.getUuid()%>"><%=offerattach.getAttahmentname()%></a></td><td><%=offerattach.getCreatedBy()%></td><td>
		<%=DateUtil.convertSourceToTargetTimezone(offerattach.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>
		</td>
		<td align="left">
		<a href="#" onClick="deleteattachment('<%=offerattach.getUuid()%>');return false;">
		<img src="jsp/images/delete.gif" border="0" alt="Delete" title="delete"/>
		</a>
		</td>
	</tr>
		<%}%>
	</table>
<%}%>
</td>
</tr>

</table>
</DIV>

<br><br>
<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Offer_release_watch_list",user1.getLocale())%>  </div>

<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
<table border="0" width="100%" >
<%
	List offerwatchlist = form.getOfferwatchList();

String watchlisttempurl = "";

	if(offerwatchlist.size() > 0){
	%>
<tr bgcolor="#B2D2FF">
<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("aquisition.applicant.is_email_sent",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("aquisition.applicant.sent_date",user1.getLocale())%></td>
<tr>
<%

for(int i=0;i<offerwatchlist.size();i++){
	OfferWatchList owatch = (OfferWatchList)offerwatchlist.get(i);
	 watchlisttempurl   =  "<img src='jsp/images/user.gif'><a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+owatch.getUserId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+owatch.getUsername()+"</a>";
	 String bgcolor = "";
	if(i%2 == 1)bgcolor ="#edf5ff";
%>



<tr bgcolor=<%=bgcolor%>>
<td><%=watchlisttempurl%></td>
<td><%=owatch.getIsemailsent()%></td>
<td><%=(owatch.getUpdatedDate()==null)?"":DateUtil.convertSourceToTargetTimezone(owatch.getUpdatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
</tr>



<%}} else {%>
<%=Constant.getResourceStringValue("aquisition.applicant.watch_list_not_added",user1.getLocale())%>
<%}%>
</table>
<%}%>
</DIV>

</form>


<br><br>
<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Offer_status",user1.getLocale())%> </div>

<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
<table border="0" width="100%" >

<%if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER_ACCEPTED)){%>
		<tr>
			<td class="bodytext" width="38%"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_acceptance_on",user1.getLocale())%> : </td>
			<td class="bodytext"><%=(form.getOfferaccepteddate()==null)?"":DateUtil.convertSourceToTargetTimezone(form.getOfferaccepteddate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_acceptance_comment",user1.getLocale())%> : </td>
			<td class="bodytext">
			<%=(form.getOfferacceptedcomment() == null)? "":StringUtils.doSpecialCharacters(form.getOfferacceptedcomment())%>
			</td>
		</tr>

 		<%}%>

<%if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER_DECLINED)){%>
		<tr>
			<td class="bodytext" width="38%"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_declined_on",user1.getLocale())%> : </td>
			<td class="bodytext"><%=(form.getOfferdeclineddate()==null)?"":DateUtil.convertSourceToTargetTimezone(form.getOfferdeclineddate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_declined_comment",user1.getLocale())%> : </td>
			<td class="bodytext">
			<%=(form.getOfferdeclinedcomment() == null)? "":StringUtils.doSpecialCharacters(form.getOfferdeclinedcomment())%>
		
			</td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_declined_reason",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="offerdeclinedreason"/></td>
		</tr>
		<%}%>

<%if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>
<tr>
			<td class="bodytext" width="38%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Appl_req_for_offer_modification",user1.getLocale())%></b></td>
			<td class="bodytext"></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Target_joining_date",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="chagetargetjoiningdate"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Expected_CTC_(requested)",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="changeofferedctc"/>&nbsp;<bean:write name="applicantForm" property="changeofferedctccurrencycode"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_comment",user1.getLocale())%> : </td>
			<td class="bodytext">
			
			<%=(form.getChangeofferComment() == null)? "":StringUtils.doSpecialCharacters(form.getChangeofferComment())%>
			</td>
		</tr>
		<%}%>

<%if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER_CANCELED)){%>
<tr>
			<td class="bodytext" width="38%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Offercanceled",user1.getLocale())%></b></td>
			<td class="bodytext"></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_cancelled_by",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="offercancelledby"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_cancelled_on",user1.getLocale())%> : </td>
			<td class="bodytext"><%=(form.getOffercancelleddate()==null)?"":DateUtil.convertSourceToTargetTimezone(form.getOffercancelleddate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%> : </td>
			<td class="bodytext">
			<%=(form.getOffercancelledcomment() == null)? "":StringUtils.doSpecialCharacters(form.getOffercancelledcomment())%>
			
			</td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_cancelled_reason",user1.getLocale())%>: </td>
			<td class="bodytext"><bean:write name="applicantForm" property="offercancelledreason"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Isoffercancellationemailsentapp",user1.getLocale())%>: </td>
			<td class="bodytext"><bean:write name="applicantForm" property="isoffercancelemailsent"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_cancellation_email_sent_on",user1.getLocale())%>: </td>
			<td class="bodytext"><%=(form.getOffercancelemailsentdate()==null)?"":DateUtil.convertSourceToTargetTimezone(form.getOffercancelemailsentdate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
		</tr>
		<%}%>

		<%if(form.getInterviewState() != null && form.getInterviewState().equals(Common.ON_BOARD)){%>
		<tr>
			<td class="bodytext" width="38%"><%=Constant.getResourceStringValue("aquisition.applicant.Joined_date",user1.getLocale())%> : </td>
			<td class="bodytext"><bean:write name="applicantForm" property="joineddate"/></td>
		</tr>
		<tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%> : </td>
			<td class="bodytext">
			<%=(form.getJoinedcomment() == null)? "":StringUtils.doSpecialCharacters(form.getJoinedcomment())%>
			
			</td>
			
		</tr>
		<%}%>

</table>
</DIV>

<BR>

<%
	//if(form.getInterviewState() != null && form.getInterviewState().equals(Common.OFFER_ACCEPTED)){
%>
<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.Attachments_added_by_applicant",user1.getLocale())%> </div>

<div onmouseover="boxOnHover(this);" onmouseout="boxOffHover(this);">
<%
List appattachmentList = form.getApplicantAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	
	%>
	<table border="0" width="100%" >
	<tr bgcolor="#B2D2FF">
		<td width="20%"><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></td>
		<td width="20%"><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%></td>
		<td width="25%">&nbsp;&nbsp <%=Constant.getResourceStringValue("aquisition.applicant.added_date",user1.getLocale())%></td>
		<td></td>
</tr>
<%
		for(int i=0;i<appattachmentList.size();i++){
		ApplicantOfferAttachment offerattach = (ApplicantOfferAttachment)appattachmentList.get(i);
		String deleteurl = "applicant.do?method=deleteattachmentrelatedtooffer&attachuuid="+offerattach.getUuid()+"&applicantId="+form.getApplicantId();

%>
<tr>
<td><%=(offerattach.getAttahmentdetails()==null)?"":offerattach.getAttahmentdetails()%></td>
<td>
<a href="<%=request.getContextPath()%>/applicant.do?method=offerattachmentdetails&uuid=<%=offerattach.getUuid()%>"><%=offerattach.getAttahmentname()%></a></td><td><%=offerattach.getCreatedBy()%></td><td>
<%=DateUtil.convertSourceToTargetTimezone(offerattach.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>
</td>
<td>
<a href="#" onClick="deleteattachment('<%=offerattach.getUuid()%>');return false;">
<img src="jsp/images/delete.gif" border="0" alt="Delete" title="delete"/>
</a>
</td>
</tr>
<%}%>
</table>
<%}else{%>
<%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>
<%}%>
</DIV>

<%//}%>

		</div>
	</div>
	



<%} else {%>
<%=Constant.getResourceStringValue("aquisition.applicant.Offer_is_not_yet_initiated",user1.getLocale())%>
<%}%>






</body>
</html>