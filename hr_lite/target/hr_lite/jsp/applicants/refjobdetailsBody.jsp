<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
String datepattern = Constant.getValue("defaultdateformat");
String jobapply = (String)request.getAttribute("jobapply");
String backurl = request.getContextPath()+"/refjob.do?method=jobsearch";
String backbutton = (String)request.getAttribute("backbutton");
%>
<bean:define id="form" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
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
<a class="button" href="#" onClick="back('<%=backurl%>')"><%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%></a>
<%}%>
<br><br>    
<div class="button"><font size="4" face="verdana"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></b></font></div>
<html:form action="/refjob.do?method=saveapplicantData">
<br>
 <table class="ex2" border="2" bordercolor="gray" RULES=NONE FRAME=BOX width="700">

       	<tr>
          <td align="left" width ="50%">   
          	<font size="4" color="gray"><b> <bean:write name="form" property="jobPosition"/></b></font>
          </td>
          
        </tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
        
	    
        <tr></tr><tr></tr>
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
          <b><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> :</b>  <bean:write name="form" property="departmentName"/></td>
        </tr>
        <tr></tr><tr></tr>
		<tr>
          <td align="left" valign="top" width ="50%"> 
          <b><%=Constant.getResourceStringValue("Requisition.Publishdate",user1.getLocale())%> :</b> <%=DateUtil.convertDateToStringDate(form.getPublishedDate(),datepattern)%></td>

          <td>
          <b><%=Constant.getResourceStringValue("Requisition.Lastdate",user1.getLocale())%> :</b> <%=(form.getTargetfinishdate()=="")?"N/A":form.getTargetfinishdate()%></td>
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
               <tr>
		<td colspan="2"><font color="gray">_________________________________________________________________________________________________
		</font>
		</td>  
		</tr>
		
		<tr></tr><tr></tr>
		 <%
 
RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
String appurl="";
String bookmartkurl = "";
if(empref != null){
appurl = request.getContextPath()+"/applicantoffer.do?method=createPage&requistionId="+form.getJobreqId()+"&empemail="+empref.getEmployeeemail()+"&frm=ref";
bookmartkurl = request.getContextPath()+"/refjob.do?method=addbookmark&requistionId="+form.getJobreqId()+"&empemail="+empref.getEmployeeemail();
}
%>
		<tr>
          
          <td align="left" > 
			  <% if(form.getStatus()!= null && form.getStatus().equals("Open") && form.getState().equals("Active") && jobapply != null && jobapply.equals("yes")){%>
				  <%if(user1 != null){%>
		           <a class="button" href="#" onClick="createapplicantRef('<%=appurl%>')">Refer a friend</a> <br><br>
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
        <tr></tr><tr></tr>
        <tr>
         <td align="left" colspan="2">  
         <bean:write name="form" property="jobRoles"/></td>
        </tr>
		
		<tr></tr><tr></tr><tr></tr><tr></tr>
        
        <tr>
			<td align="left"><font size="3" color="gray"><b><%=Constant.getResourceStringValue("Requisition.Instructions",user1.getLocale())%> </b> </font></td>
        </tr>
                <tr>
         <td align="left" colspan="2">  
         <bean:write name="form" property="jobInstructions"/></td>
        </tr>
         <tr></tr><tr></tr>
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
		  
		  <table border="0" width="400" >
			<tr bgcolor="DDDDDD">
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%> </td>
			<td><%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%></td>
			<td><%=Constant.getResourceStringValue("Requisition.FLSA.Status",user1.getLocale())%></td>
			<td><%=Constant.getResourceStringValue("Requisition.compfreq",user1.getLocale())%></td>
			</tr>
			<tr>
			<td><I><bean:write name="form" property="jobtype"/></I></td>
			<td><I><bean:write name="form" property="workshift"/></I></td>
			<td><I><bean:write name="form" property="flsastatus"/></I></td>
			<td><I><bean:write name="form" property="compfrequency"/></I></td>
			</tr>
		  </table>

		  </td>
        </tr>

 
		<tr></tr><tr></tr><tr></tr><tr></tr>
		
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
					<%=jcomp.getCharName()%> &nbsp;&nbsp;<i><%=checkedmancomp%></i> <br>
		
					<%
					}	
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
		  
		<tr></tr><tr></tr>
		 <td align="left"> 
          	<font size="3" color="gray"><b><%=Constant.getResourceStringValue("aquisition.applicant.Note",user1.getLocale())%></b></font>
          </td>
        </tr>
        <tr>
          <td align="left" colspan="2"><bean:write name="form" property="jobreqDesc"/>
		  </td>
        </tr>
         <tr>

		 <tr></tr><tr></tr><tr></tr><tr></tr>
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
		  	if(addbookmarkdone != null && addbookmarkdone.equals("no")){
			%>

			<font color="red"><%=Constant.getResourceStringValue("Requisition.Bookmark.bookmark_already_added",user1.getLocale())%></font>
		
			<%}%>
			</td>
 		</tr>
  	</table>   
	<%}%>
<br><br><br><br>
		
</html:form>	
</body>
</html>
