<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
 String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
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
	var url = "<%=request.getContextPath()%>/applicant.do?method=createPage&requistionId=<%=form.getJobreqId()%>";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%>',url,messageret);
}

function createapplicantRef(url){
	//var url = "<%=request.getContextPath()%>/applicantoffer.do?method=createPage&requistionId=<%=form.getJobreqId()%>";
	//parent.setPopTitle1("Create applicant");
	
	GB_showFullScreen('<%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%>',url,messageret);
}

	
	function messageret(){
	window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			}

</script>


</head>




<body>




 <table width="1000">
<tr>

</tr>
<tr>

    <td colSpan="2" vAlign="center">
      <p>
      <table border="0" cellPadding="1" cellSpacing="1" style="WIDTH: 100%" width="100%">
        
        <tr>
          <td bgColor="lightgoldenrodyellow" width="20%"><font face="Arial Narrow"><b> </b></font>
		  <% if(form.getBackurl()!=null){%>
		 <%if((form.getBackurl()).equals("jobs.do?method=jobsearchsubmit")){%>
<a href="<%=form.getBackurl()%>"><%=Constant.getResourceStringValue("Agency.back_tojob_search",user1.getLocale())%></a>
		<%}else{%>

<a class="button" href="<%=form.getBackurl()%>"><%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%></a>

<%}%>

<%}%>
		  </td>


          <td align="middle" bgColor="lightgoldenrodyellow" width="80%"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></b></td></tr>
        <tr>
        <td colspan="2"> <p> </td>
        </tr>
        
        <font face="Arial Narrow" color="brown">
        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="jobTitle"/></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.reqcode",user1.getLocale())%></td>
          <td aligh="left"  width="80%">  <bean:write name="form" property="jobreqcode"/></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.JobPosition",user1.getLocale())%> </td>
          <td aligh="left"  width="80%">  <bean:write name="form" property="jobPosition"/></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.Noofopenpositions",user1.getLocale())%> </td>
          <td aligh="left"  width="80%">  <bean:write name="form" property="numberOfOpening"/></td>
        </tr>
		<tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="parentOrgName"/></td>
        </tr>
        <tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
          <td aligh="left"  width="80%">  <bean:write name="form" property="departmentName"/></td>
        </tr>

        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.qualifications",user1.getLocale())%> </td>
          <td aligh="left"  width="80%"><bean:write name="form" property="minimumLevelOfEducation"/></td>
        </tr>
		<%if(form.getOtherExperience() != null && form.getOtherExperience().length()>0){%>
		<tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.OtherQualifications",user1.getLocale())%> </td>
          <td aligh="left"  width="80%"><bean:write name="form" property="otherExperience"/></td>
        </tr>
		<%}%>

        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Experience",user1.getLocale())%> </td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="minyearsofExpRequired"/> 
		  <%if(form.getMaxyearsofExpRequired() > 0){%>
		  - <bean:write name="form" property="maxyearsofExpRequired"/>
		  	<%}%>
		  year(s)
		  </td>
        </tr>
		        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.Comptetency",user1.getLocale())%> </td>
          <td aligh="left"  width="80%">
		  <%
List competencyList = form.getComptetencyList();
for(int i=0;i<competencyList.size();i++){
	JobTemplateCompetency jcomp = (JobTemplateCompetency)competencyList.get(i);
	String checkedmancomp = (jcomp.getMandatory().equals("on"))?"Mandatory":"Good to have";
%>
<%=jcomp.getCharName()%> <i><%=checkedmancomp%></i> <br>

<%}%> </td>
        </tr>
        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;</td>
          <td aligh="left"  width="80%">
		  
		  <table border="0" width="500" >
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
        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="jobDetails"/> <br>
		  <bean:write name="form" property="jobreqDesc"/>
		  </td>
        </tr>
	     <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.Duties",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="jobRoles"/></td>
        </tr>
		 <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.Instructions",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="jobInstructions"/></td>
        </tr>

        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="locationName"/></td>
        </tr>
		 <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.Publishdate",user1.getLocale())%> </td>
          <td aligh="left"  width="80%"> <%=DateUtil.convertDateToStringDate(form.getPublishedDate(),datepattern)%></td>
        </tr>
		<tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.Lastdate",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <%=(form.getTargetfinishdate()=="")?"N/A":form.getTargetfinishdate()%></td>
        </tr>

		<tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%> </td>
          <td aligh="left"  width="80%"> 
		  <%
List attachmentList = form.getAttachmentList();

if(attachmentList != null && attachmentList.size()>0){
	%>
	<table CELLPADDING=3>
	<tr>

	<td><b><%=Constant.getResourceStringValue("Requisition.Attachement.details",user1.getLocale())%></b></td><td><b>
	<%=Constant.getResourceStringValue("Requisition.Attachement.file.name",user1.getLocale())%></b></td>
	</tr>

<%
		for(int i=0;i<attachmentList.size();i++){
		RequistionAttachments attach = (RequistionAttachments)attachmentList.get(i);
		
if(attach.getVisibleInJobDetails() != null && attach.getVisibleInJobDetails().equals("Y")){
%>
<tr>
<td><%=(attach.getAttahmentdetails()==null)?"":attach.getAttahmentdetails()%></td>
<td>
<a href="jobs.do?method=attachementdetails&attachmentid=<%=attach.getUuid()%>"><%=attach.getAttahmentname()%></a>
</td>

</tr>
<%}%>
<%}%>
</table>
<%}%>
		  
		  
		  </td>
        </tr>

		<%if(form.getRecruiter()!=null){
			User recruiter = form.getRecruiter();
		%>
        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;
		  <%=Constant.getResourceStringValue("aquisition.applicant.ContactPerson",user1.getLocale())%> </td>
          <td aligh="left"  width="80%"> <bean:write name="form" property="recruiterName"/> (<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>: <%=recruiter.getPhoneOffice()%>)</td>
        </tr>
         <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.ContactEmail",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <%=recruiter.getEmailId()%></td>
        </tr>
         <tr>
         <%}%>
		 <%
 
RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
String appurl="";
if(empref != null){
appurl = request.getContextPath()+"/applicantoffer.do?method=createPage&requistionId="+form.getJobreqId()+"&empemail="+empref.getEmployeeemail();
}
%>
		 <tr>
          <td align="left" valign="top" width="20%">&nbsp;&nbsp;</td>
          <td aligh="left"  width="80%"> 
		  <%if(empref != null){%>
           <a  href="#" onClick="createapplicantRef('<%=appurl%>')"><%=Constant.getResourceStringValue("aquisition.applicant.ApplyJob",user1.getLocale())%></a>
		  <%}else{%>
		  <a  href="#" onClick="createapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.ApplyJob",user1.getLocale())%></a>
		  <%}%>
		  </td>
        </tr>
        </font>
        
 
      
      </table></p></td></tr>
  <tr>
 
  <td colspan="2"> </td>
  </tr>
   </table>




		
	
</body>
</html>
