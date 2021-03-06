<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%

String datepattern = Constant.getValue("defaultdateformat");
User userAg = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
User user = (User)request.getSession().getAttribute(Common.USER_DATA);
RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

com.bean.Locale locale= null;
if(userAg != null){

datepattern = DateUtil.getDatePatternFormat(userAg.getLocale());
locale = userAg.getLocale();
}else if(user != null){
	datepattern = DateUtil.getDatePatternFormat(user.getLocale());
    locale = user.getLocale();
}else if(userEmp != null){
	datepattern = DateUtil.getDatePatternFormat(userEmp.getLocale());
	locale = userEmp.getLocale();

}else if(appuser != null){
	datepattern = DateUtil.getDatePatternFormat(appuser.getLocale());
	locale = appuser.getLocale();

}else{
String refuserid = (String)session.getAttribute("refuserid");
	if(refuserid != null){
		RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
		locale= refEmp.getLocale();
	}else{
		locale = new com.bean.Locale();
		locale.setLocaleCode("en_US");
	}
}
String jobapply = (String)request.getAttribute("jobapply");
String backurl = request.getContextPath()+"/refjob.do?method=jobsearch";
String backbutton = (String)request.getAttribute("backbutton");
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
	var url = "<%=request.getContextPath()%>/applicantoffer.do?method=createPage&requistionId=<%=form.getJobreqId()%>&frm=ref";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('Applicant details',url,messageret);
}

function createapplicantRef(url){
	//var url = "<%=request.getContextPath()%>/applicantoffer.do?method=createPage&requistionId=<%=form.getJobreqId()%>&frm=ref";
	//parent.setPopTitle1("Create applicant");
	
	GB_showFullScreen('Applicant details',url,messageret);
}

	
	function messageret(){
	window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			}


function addbookmarksubmit(url){
		  document.jobRequisitionForm.action = url;
	  document.jobRequisitionForm.submit();
}
function back(url){

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
String addbookmarkdone = (String)request.getAttribute("addbookmarkdone");

	
if(addbookmarkdone != null && addbookmarkdone.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white">bookmark is saved. Please click <a href="refjob.do?method=mybookmarks">My bookmarks</a> </font></td>
			
		</tr>
		
	</table>
</div>
<%}%>
<br>
<body>

<% if(backbutton != null && backbutton.equals("yes")){%>
<a  class="button" href="#" onClick="back('<%=backurl%>')"><%=Constant.getResourceStringValue("hr.button.back",locale)%></a>
<%}%>
<br>   
<div class="button"><font size="4" face="sans-serif"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",locale)%> - <font size="4" color="white"><b> <bean:write name="form" property="jobPosition"/></b></font>
</b></font></div>
<html:form action="/refjob.do?method=saveapplicantData">

 <table border="0" bordercolor="white" RULES=NONE FRAME=BOX width="700">

       	<tr>
          <td align="left" width ="50%">   
          </td>
          
        </tr>
		<tr></tr>
        

        <tr></tr><tr></tr>
		<tr>
          <td align="left" > 
          <b><%=Constant.getResourceStringValue("Requisition.reqcode",locale)%> :</b> <bean:write name="form" property="jobreqcode"/>
          </td>
         <td >
         <b><%=Constant.getResourceStringValue("Requisition.jobtitle",locale)%> :</b> <bean:write name="form" property="jobTitle"/>
         </td>
        </tr>
		<tr></tr><tr></tr>
		<tr>
          <td align="left"   width ="50%"> 
          <b><%=Constant.getResourceStringValue("Requisition.Noofopenpositions",locale)%> :</b> <bean:write name="form" property="numberOfOpening"/></td>
          <td>
          <b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",locale)%> :</b> <bean:write name="form" property="locationName"/></td>
        </tr>
        <tr></tr><tr></tr>
		<tr>
          <td align="left" > 
          <b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",locale)%> :</b> <bean:write name="form" property="parentOrgName"/></td>
          <td>
         </td>
        </tr>
        <tr></tr><tr></tr>
		<tr>
          <td align="left" valign="top" width ="50%"> 
          <b><%=Constant.getResourceStringValue("Requisition.Publishdate",locale)%> :</b> <%=DateUtil.convertDateToStringDate(form.getPublishedDate(),datepattern)%></td>

          <td>
          <b><%=Constant.getResourceStringValue("Requisition.Target.finish.date",locale)%> :</b> <%=(form.getTargetfinishdate()=="")?"N/A":form.getTargetfinishdate()%></td>
        </tr>
      
        </tr>
        <tr></tr><tr></tr>
        <tr>
          <td align="left" valign="top"> 
          <b><%=Constant.getResourceStringValue("Requisition.qualifications",locale)%> :</b> <bean:write name="form" property="minimumLevelOfEducation"/></td>
        
		<%if(form.getOtherExperience() != null && form.getOtherExperience().length()>0){%>
		
          <td align="left" valign="top" width ="50%">
          <b><%=Constant.getResourceStringValue("Requisition.OtherQualifications",locale)%> :</b> <bean:write name="form" property="otherExperience"/></td>
       
		<%}%>
</tr>
<tr></tr><tr></tr>
<tr>
        
          <td align="left" valign="top">
          <b><%=Constant.getResourceStringValue("aquisition.applicant.Experience",locale)%> :</b> <bean:write name="form" property="minyearsofExpRequired"/> 
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
			<td colspan="2"><font color="white">_________________________________________________________________________________________________
			</font>
			</td>  
		</tr>
		        <tr>
          <td align="left"> 
          	<font size="3" ><b><%=Constant.getResourceStringValue("Requisition.additional.jobreqdetails",locale)%></b></font>
          </td>
          </tr>
          <tr> <td align="left"> 
         <%@ include file="../talent/customVariablestoRequisitionDetails.jsp"%>
   		</td></tr>
<%	} %>  
               <tr>
		<td colspan="2"><font >_________________________________________________________________________________________________
		</font>
		</td>  
		</tr>
		
		<tr></tr><tr></tr>

        
        
        <tr>
          <td align="left"> 
          	<font size="3" ><b><%=Constant.getResourceStringValue("Requisition.responsibilities",locale)%></b></font>
          </td>
         
        </tr>
        <tr></tr><tr></tr>
        <tr>
         <td align="left" colspan="2">  
		 <%=StringUtils.doSpecialCharacters(form.getJobRoles())%>
         </td>
        </tr>
		
		<tr></tr><tr></tr><tr></tr><tr>
		
		</tr>
        
        <tr>
			<td align="left"><font size="3" ><b><%=Constant.getResourceStringValue("Requisition.Instructions",locale)%> </b> </font></td>
        </tr>
                <tr>
         <td align="left" colspan="2">  
		  <%=StringUtils.doSpecialCharacters(form.getJobInstructions())%>
        </td>
        </tr>
         <tr></tr><tr>
		 			<td colspan="2"><font >_________________________________________________________________________________________________
			</font>
			</td>  

		 </tr>
        <tr>
          <td align="left" colspan="2"><font size="3" ><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",locale)%></b></font>  <br>
		  </td>
        </tr>
        <tr>
         <td align="left" colspan="2">  
		 <%=StringUtils.doSpecialCharacters(form.getJobDetails())%>
        </td>
        </tr>
         <tr></tr><tr></tr>

           <tr>
         
          <td align="left" colspan = "2">
		  

		  <table border="0" width="600" >
			<tr>
			<td><font ><%=Constant.getResourceStringValue("Requisition.jobtype",locale)%></font> </td>
			<td><font ><%=Constant.getResourceStringValue("Requisition.workshift",locale)%></font></td>


			<td><I><font ><bean:write name="form" property="jobtype"/></font></I></td>
			<td><I><font ><bean:write name="form" property="workshift"/></font></I></td>

			</tr>
		  </table>

		  </td>
        </tr>

 
		<tr></tr><tr></tr><tr></tr><tr>
					<td colspan="2"><font >_________________________________________________________________________________________________
			</font>
			</td>  

		</tr>
		
				<tr>
          <td align="left"> 
          	<font size="3" ><b><%=Constant.getResourceStringValue("Requisition.Competency",locale)%></b></font>
          </td>
        </tr>
		<tr></tr><tr></tr>
		<tr>
          <!--  <td align="left" valign="top"><%=Constant.getResourceStringValue("Requisition.Competency",locale)%></td>-->
          <td align="left">
		  <%
				List competencyList = form.getComptetencyList();
				if(competencyList !=null){
					for(int i=0;i<competencyList.size();i++){
						JobTemplateCompetency jcomp = (JobTemplateCompetency)competencyList.get(i);
						String checkedmancomp = (jcomp.getMandatory().equals("on"))?Constant.getResourceStringValue("Requisition.Mandatory",locale):Constant.getResourceStringValue("refferal.agencyreferralportal.jobdetails.goodtohave",locale);
					%>
					<%=jcomp.getCharName()%> &nbsp;&nbsp;<i><%=checkedmancomp%></i> <br>
		
					<%
					}	
				}
		   %> 
</td>
        </tr>
<tr></tr><tr></tr><tr></tr><tr>
			<td colspan="2"><font >_________________________________________________________________________________________________
			</font>
			</td>  

</tr>
       	<tr>
          <td align="left"> 
          	<font size="3" ><b><%=Constant.getResourceStringValue("Requisition.attachments",locale)%></b></font>

         
<%
	List attachmentList = form.getAttachmentList();
	if(attachmentList != null && attachmentList.size()>0){
%>
	
	<tr>

	<td width ="50%"><b><%=Constant.getResourceStringValue("Requisition.Attachement.details",locale)%></b></td>
	<td width ="50%"><b><%=Constant.getResourceStringValue("Requisition.Attachement.file.name",locale)%></b></td>
	
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
					<td colspan="2"><font >_________________________________________________________________________________________________
			</font>
			</td>  

		</tr>
		 <td align="left"> 
          	<font size="3" ><b><%=Constant.getResourceStringValue("aquisition.applicant.Note",locale)%></b></font>
          </td>
        </tr>
        <tr>
          <td align="left" colspan="2"><bean:write name="form" property="jobreqDesc"/>
		  </td>
        </tr>
         <tr>

		 <tr></tr><tr></tr><tr></tr><tr>
		 			<td colspan="2"><font >_________________________________________________________________________________________________
			</font>
			</td>  

		 </tr>
         <tr>
          <td align="left"> 
          	<font size="3" ><b><%=Constant.getResourceStringValue("Requisition.Contact.details",locale)%></b></font>
          </td>
        </tr>
		<%if(form.getRecruiter()!=null){
			User recruiter = form.getRecruiter();
		%>
        <tr>
          <td align="left" valign="top"><font ><b><%=Constant.getResourceStringValue("aquisition.applicant.ContactPerson",locale)%> : </b></font>
          <bean:write name="form" property="recruiterName"/> (<font ><b><%=Constant.getResourceStringValue("aquisition.applicant.phone",locale)%>: </b></font> <%=recruiter.getPhoneOffice()%>)</td>
        </tr>
         <tr>
          <td align="left" valign="top"><font ><b><%=Constant.getResourceStringValue("aquisition.applicant.ContactEmail",locale)%> &nbsp;&nbsp;: </b></font>
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
			<input type="button" name="save" value="<%=Constant.getResourceStringValue("hr.button.add_to_bookmark",locale)%>" class="button" onClick="addbookmarksubmit('')">
			<% 
		  	if(addbookmarkdone != null && addbookmarkdone.equals("no")){
			%>

			<font color="red"><%=Constant.getResourceStringValue("Requisition.Bookmark.bookmark_already_added",locale)%></font>
		
			<%}%>
			</td>
 		</tr>
  	</table>   
	<%}%>
<br><br><br><br>
		
</html:form>	
</body>
</html>
