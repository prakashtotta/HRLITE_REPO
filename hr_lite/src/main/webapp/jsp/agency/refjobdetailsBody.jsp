<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@include file="../common/tooltip.jsp" %>
<%
 String path = (String)request.getAttribute("filePath");
 String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

}
%>
<bean:define id="form" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- � Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<script language="javascript">


function createapplicant(){
	var url = "<%=request.getContextPath()%>/agencyops.do?method=createApplicantPage&requistionId=<%=form.getJobreqId()%>&frm=ag";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.applicant.details",user1.getLocale())%>',url,messageret);
}
function back(url){

	  document.jobRequisitionForm.action = url;
	  document.jobRequisitionForm.submit();
}
function createapplicantRef(url){

	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.applicant.details",user1.getLocale())%>',url,messageret);

	
}

	
	function messageret(){
	window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			}


function addbookmarksubmit(url){
	var comment = document.jobRequisitionForm.comment.value;
	if(comment == "" || comment == null){
     	alert("<%=Constant.getResourceStringValue("refferal.agencyreferralportal.jobdetails.Bookmark_name_required",user1.getLocale())%>");
		return false;
		}
		  document.jobRequisitionForm.action = url;
	  document.jobRequisitionForm.submit();
}

</script>

<style type="text/css">

table.ex2
{
border-collapse:separate;
border-spacing:15px 3px;
}
</style>
</head>

<%
String backurl = request.getContextPath()+"/agjob.do?method=jobsearch";
String addbookmarkdone = (String)request.getAttribute("addbookmarkdone");
String alreadyBookmarked = (String)request.getAttribute("alreadyBookmarked");
String backbutton = (String)request.getAttribute("backbutton");
String jobapply = (String)request.getAttribute("jobapply");
%>
<% if(backbutton != null && backbutton.equals("yes")){%>
<br><a class="button" href="#" onClick="back('<%=backurl%>')"> < <%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%> </a><br>
<%}%>
<%
if(addbookmarkdone != null && addbookmarkdone.equals("yes")){
%>
<br>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("Requisition.Bookmark_plz_click",user1.getLocale())%> </font> <a href="agjob.do?method=mybookmarks&requistionId=<%=form.getJobreqId()%>&secureid=<%=form.getUuid()%>"><font color="white"><%=Constant.getResourceStringValue("Requisition.My_bookmarks",user1.getLocale())%></a> </font></td>
			
		</tr>
		
	</table>
</div>
<%}%>
<br>
<div >

<br>  
<font size="5" face="verdana"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%> - <bean:write name="form" property="jobPosition"/>
</b></font>
  
<html:form action="/agjob.do?method=saveapplicantData">
<br>
 <table class="ex2" border="0" bordercolor="gray" RULES=NONE FRAME=BOX width="100%">

       	<tr>
          <td align="left" width="900px">   
          </td>
          
        </tr>
		
       <tr>
          <td align="left" width="900px">
		  <b><%=Constant.getResourceStringValue("admin.RefferalScheme.Referralscheme",user1.getLocale())%> : &nbsp;</b>
		  
		  <b>
		  <%
          if(form.getAgencyReferralSchemeList() != null && form.getAgencyReferralSchemeList().size()>0){
           Iterator itr1 = form.getAgencyReferralSchemeList().iterator();
			while(itr1.hasNext()){
			RefferalScheme japp = (RefferalScheme)itr1.next();

	
		  %>
		 
		  	<a href="#"  onMouseOver="ddrivetip('<%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%> :<%=(japp.getRefferalScheme_Name()==null)?"":japp.getRefferalScheme_Name()%><br><%=Constant.getResourceStringValue("admin.RefferalScheme.desc",user1.getLocale())%> :<%=(japp.getRefferalScheme_Desc()==null)?"":japp.getRefferalScheme_Desc()%><br><%=Constant.getResourceStringValue("admin.RefferalSchemeType.reward.type",user1.getLocale())%> : <%=(japp.getRefferalSchemeType()==null)?"":japp.getRefferalSchemeType().getRefferalSchemeName()%><br><%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",user1.getLocale())%> : <%=japp.getRefferalScheme_Amount()%>&nbsp;&nbsp;<%=(japp.getUom()==null)?"":japp.getUom()%><br><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Execution.criteria",user1.getLocale())%> :<%=(japp.getRule()==null)?"":japp.getRule().getRuleDesc()%>','#DFDFFF', 300)";
			onMouseout="hideddrivetip()"><%=japp.getRefferalScheme_Name()%></a>
		  <%}%>
		  <%}else{%>
			<%=Constant.getResourceStringValue("admin.RefferalScheme.Referralscheme.NA",user1.getLocale())%>
		  <%}%>
		  
		  </b>
		  </td>
        </tr>
        <tr></tr><tr>
					<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  

		</tr>

		<tr>
          <td align="left" > 
          <b><%=Constant.getResourceStringValue("Requisition.reqcode",user1.getLocale())%> :</b> <bean:write name="form" property="jobreqcode"/>
          </td>
         <td >
         <b><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%> :</b> <bean:write name="form" property="jobTitle"/>
         </td>
        </tr>
		<tr></tr><tr></tr>
		<tr>
          <td align="left"   width ="50%"> 
          <b><%=Constant.getResourceStringValue("Requisition.Noofopenpositions",user1.getLocale())%> :</b> <bean:write name="form" property="numberOfOpening"/></td>
          <td>
          <b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%> :</b> <bean:write name="form" property="locationName"/></td>
        </tr>
        <tr></tr><tr></tr>
		<tr>
          <td align="left" > 
          <b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> :</b> <bean:write name="form" property="parentOrgName"/></td>
          <td>

		  </td>
        </tr>
        <tr></tr><tr></tr>
		<tr>
          <td align="left" valign="top" width ="50%"> 
          <b><%=Constant.getResourceStringValue("Requisition.Publishdate",user1.getLocale())%> :</b> <%=DateUtil.convertDateToStringDate(form.getPublishedDate(),datepattern)%></td>

          <td>
          <b><%=Constant.getResourceStringValue("Requisition.Target.finish.date",user1.getLocale())%> :</b> <%=(form.getTargetfinishdate()=="")?"N/A":form.getTargetfinishdate()%></td>
        </tr>
      
        </tr>
        <tr></tr><tr></tr>
        <tr>
          <td align="left" valign="top"> 
          <b><%=Constant.getResourceStringValue("Requisition.qualifications",user1.getLocale())%> :</b> <bean:write name="form" property="minimumLevelOfEducation"/></td>
        
		<%if(form.getOtherExperience() != null && form.getOtherExperience().length()>0){%>
		
          <td align="left" valign="top" width ="50%">
          <b><%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%> :</b> <bean:write name="form" property="otherExperience"/></td>
       
		<%}%>
</tr>
<tr></tr><tr></tr>
<tr>
        
          <td align="left" valign="top">
          <b><%=Constant.getResourceStringValue("aquisition.applicant.Experience",user1.getLocale())%> :</b> <bean:write name="form" property="minyearsofExpRequired"/> 
		  <%if(form.getMaxyearsofExpRequired() > 0){%>
		  - <bean:write name="form" property="maxyearsofExpRequired"/>
		  	<%}%>
		  year(s)
		  </td>
        </tr>
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
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.additional.jobreqdetails",user1.getLocale())%></b></font>
          </td>
          </tr>
          <tr> <td align="left"> 
         <%@ include file="../talent/customVariablestoRequisitionDetails.jsp"%>
   		</td></tr>
<%	} %>  
               <tr>
		<td colspan="2"><font color="gray">_________________________________________________________________________________________________
		</font>
		</td>  
		</tr>
		
		<tr></tr><tr></tr>
		<%
			String appurl="";
			String bookmartkurl = "";
			if(user1 != null){
			appurl = request.getContextPath()+"/agencyops.do?method=createApplicantPage&requistionId="+form.getJobreqId()+"&empemail="+user1.getEmailId()+"&frm=ag";
			bookmartkurl = request.getContextPath()+"/agjob.do?method=addbookmark&requistionId="+form.getJobreqId()+"&empemail="+user1.getEmailId()+"&uuid="+form.getUuid()+"&backurl="+form.getBackurl();
			}
		%>
		<tr>
          
          <td align="left" > 
			  <% if(form.getStatus()!= null && form.getStatus().equals("Open") && form.getState().equals("Active") && jobapply != null && jobapply.equals("yes")){%>
				  <%if(user1 != null){%>
		           <!--  <a  href="#" onClick="createapplicantRef('<%=appurl%>')"><img SRC="jsp/images/Job-Ready-Apply-Now-Button.jpg" WIDTH="70" HEIGHT="20"></a> <br><br>-->
		            <a class="button" href="#" onClick="createapplicantRef('<%=appurl%>')"><%=Constant.getResourceStringValue("aquisition.applicant.upload.profile",user1.getLocale())%></a> <br><br>
				  <%}else{%>
				  <a class="button" href="#" onClick="createapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.ApplyJob",user1.getLocale())%></a> 
				  <%}%>
			  <%}%>
		  </td>
        </tr>
        
        
        <tr>
          <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.responsibilities",user1.getLocale())%></b></font>
          </td>
         
        </tr>
        <tr></tr><tr>
		
		</tr>
        <tr>
         <td align="left" colspan="2">  
         <bean:write name="form" property="jobRoles"/></td>
        </tr>
		
		<tr></tr><tr></tr><tr></tr><tr>
					<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  

		</tr>
        
        <tr>
			<td align="left"><font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.Instructions",user1.getLocale())%> </b> </font></td>
        </tr>
                <tr>
         <td align="left" colspan="2">  
         <bean:write name="form" property="jobInstructions"/></td>
        </tr>
         <tr></tr><tr>
		 			<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  

		 </tr>
        <tr>
          <td align="left" colspan="2"><font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></b></font>  <br>
		  </td>
        </tr>
        <tr>
         <td align="left" colspan="2">  
         <bean:write name="form" property="jobDetails"/></td>
        </tr>
         <tr></tr><tr></tr>

           <tr>
         
          <td align="left" colspan = "2">
		  

		  <table border="0" width="800" class="div">
			<tr>
			<td><b><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></b> </td>
			<td><b><%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%></b></td>
			</tr>
			<tr>
			<td><I><bean:write name="form" property="jobtype"/></I></td>
			<td><I><bean:write name="form" property="workshift"/></I></td>
	
			</tr>
		  </table>

		  </td>
        </tr>

 
		<tr></tr><tr></tr><tr></tr><tr>
					<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  

		</tr>
		
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
				if(competencyList !=null){
					for(int i=0;i<competencyList.size();i++){
						JobTemplateCompetency jcomp = (JobTemplateCompetency)competencyList.get(i);
						String checkedmancomp = (jcomp.getMandatory().equals("on"))?Constant.getResourceStringValue("Requisition.Mandatory",user1.getLocale()):Constant.getResourceStringValue("refferal.agencyreferralportal.jobdetails.goodtohave",user1.getLocale());
					%>
					<%=jcomp.getCharName()%> <i><%=checkedmancomp%></i> <br>
		
					<%
					}	
				}
		   %> 
</td>
        </tr>
<tr></tr><tr></tr><tr></tr><tr>
			<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  

</tr>
       	<tr>
          <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></b></font>

         
<%
	List attachmentList = form.getAttachmentList();
	if(attachmentList != null && attachmentList.size()>0){
%>
	
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

<%}%>
		  
		<tr></tr><tr>
					<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  

		</tr>
		 <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.Note",user1.getLocale())%></b></font>
          </td>
        </tr>
        <tr>
          <td align="left" colspan="2"><bean:write name="form" property="jobreqDesc"/>
		  </td>
        </tr>
         <tr>

		 <tr></tr><tr></tr><tr></tr><tr>
		 			<td colspan="2"><font color="gray">_________________________________________________________________________________________________
			</font>
			</td>  

		 </tr>
         <tr>
          <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.Contact.details",user1.getLocale())%></b></font>
          </td>
        </tr>
		<%if(form.getRecruiter()!=null){
			User recruiter = form.getRecruiter();
		%>
        <tr>
          <td align="left" valign="top"><font color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.ContactPerson",user1.getLocale())%> : </b></font>
          <bean:write name="form" property="recruiterName"/> (<font color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>: </b></font> <%=recruiter.getPhoneOffice()%>)</td>
        </tr>
         <tr>
          <td align="left" valign="top"><font color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.ContactEmail",user1.getLocale())%> &nbsp;&nbsp;: </b></font>
           <%=recruiter.getEmailId()%></td>
        </tr>
       
         <tr>
         <%}%>


        </font>
        
 
      
      </table>
      
  <tr>
 
  <td colspan="2"> </td>
  </tr>
	<% if( jobapply != null && jobapply.equals("yes")){%>
	
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;
            <input type="text"  name="comment" /> 
			<input type="button" name="save" value="<%=Constant.getResourceStringValue("hr.button.add_to_bookmark",user1.getLocale())%>" onClick="addbookmarksubmit('<%=bookmartkurl%>')" class="button">
			<% 
		  	if(alreadyBookmarked != null && alreadyBookmarked.equals("yes")){
			%>

			<div class="msg"><font color="white"><%=Constant.getResourceStringValue("Requisition.Bookmark.bookmark_already_added",user1.getLocale())%></font></div>
		
			<%}%>
			</td>
 		</tr>
		
  	</table>   
	<%}%>
<br><br><br><br>
		
</html:form>	
</div>
</html>
