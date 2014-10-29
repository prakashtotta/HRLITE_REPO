<%@ include file="../common/include.jsp"%>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>
<%@ include file="screenFieldSortingReq.jsp" %>
<%
int jobdetailsothersize = JOB_DETAILS_OTHERS_List.size();
%>
<html>
<title><%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%> </title>
<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<head>

<style>
.today {
	COLOR: black;
	FONT-FAMILY: sans-serif;
	FONT-SIZE: 10pt;
	FONT-WEIGHT: bold
}

.days {
	COLOR: navy;
	FONT-FAMILY: sans-serif;
	FONT-SIZE: 10pt;
	FONT-WEIGHT: bold;
	TEXT-ALIGN: center
}

.dates {
	COLOR: black;
	FONT-FAMILY: sans-serif;
	FONT-SIZE: 10pt;
}
</style>
<STYLE>
.TESTcpYearNavigation,.TESTcpMonthNavigation {
	background-color: #6677DD;
	text-align: center;
	vertical-align: center;
	text-decoration: none;
	color: #FFFFFF;
	font-weight: bold;
}

.TESTcpDayColumnHeader,.TESTcpYearNavigation,.TESTcpMonthNavigation,.TESTcpCurrentMonthDate,.TESTcpCurrentMonthDateDisabled,.TESTcpOtherMonthDate,.TESTcpOtherMonthDateDisabled,.TESTcpCurrentDate,.TESTcpCurrentDateDisabled,.TESTcpTodayText,.TESTcpTodayTextDisabled,.TESTcpText
	{
	font-family: arial;
	font-size: 8pt;
}

TD.TESTcpDayColumnHeader {
	text-align: right;
	border: solid thin #6677DD;
	border-width: 0 0 1 0;
}

.TESTcpCurrentMonthDate,.TESTcpOtherMonthDate,.TESTcpCurrentDate {
	text-align: right;
	text-decoration: none;
}

.TESTcpCurrentMonthDateDisabled,.TESTcpOtherMonthDateDisabled,.TESTcpCurrentDateDisabled
	{
	color: #D0D0D0;
	text-align: right;
	text-decoration: line-through;
}

.TESTcpCurrentMonthDate {
	color: #6677DD;
	font-weight: bold;
}

.TESTcpCurrentDate {
	color: #FFFFFF;
	font-weight: bold;
}

.TESTcpOtherMonthDate {
	color: #808080;
}

TD.TESTcpCurrentDate {
	color: #FFFFFF;
	background-color: #6677DD;
	border-width: 1;
	border: solid thin #000000;
}

TD.TESTcpCurrentDateDisabled {
	border-width: 1;
	border: solid thin #FFAAAA;
}

TD.TESTcpTodayText,TD.TESTcpTodayTextDisabled {
	border: solid thin #6677DD;
	border-width: 1 0 0 0;
}

A.TESTcpTodayText,SPAN.TESTcpTodayTextDisabled {
	height: 20px;
}

A.TESTcpTodayText {
	color: #6677DD;
	font-weight: bold;
}

SPAN.TESTcpTodayTextDisabled {
	color: #D0D0D0;
}

.TESTcpBorder {
	border: solid thin #6677DD;
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</STYLE>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin: 0;
	padding: 0;
}
</style>

<style type="text/css">
.yui-navset button {
	position: absolute;
	top: 0;
	right: 0;
}
</style>
<!--begin custom header content for this example-->
<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>

<style type="text/css">
#example {
	height: 30em;
}

label {
	display: block;
	float: left;
	width: 45%;
	clear: left;
}

.clear {
	clear: both;
}

#resp {
	margin: 10px;
	padding: 5px;
	border: 1px solid #ccc;
	background: #fff;
}

#resp li {
	font-family: monospace
}

.yui-pe .yui-pe-content {
	display: none;
}
</style>

<body onload="init()" class="yui-skin-sam">
<%@include file="/jsp/common/tooltip.jsp"%>





<%
	String tabselected1 = "";
	String tabselected4 = "";
	String tabselected6 = "";
	if (jobreqform.getTabselected() == 4) {
		tabselected4 = "class=\"selected\"";
	}
	if (jobreqform.getTabselected() == 1) {
		tabselected1 = "class=\"selected\"";
	}
	if (jobreqform.getTabselected() == 6) {
		tabselected6 = "class=\"selected\"";
	}
%>

<html:form action="/jobreq.do?method=editjobreq"
	enctype="multipart/form-data">
	<div id="demo" class="yui-navset">
	<%
		String viewforselector = (String) request
					.getAttribute("viewforselector");
	%>

	<table width="105%">
		<tr bgcolor="#edf5ff">
			<td class="yui-dt"><%=Constant.getResourceStringValue("Requisition.number",user1.getLocale())%> :&nbsp;<b><%=jobreqform.getJobreqId()%>
			</b>&nbsp;<%=Constant.getResourceStringValue("Requisition.reqcode",user1.getLocale())%> :&nbsp;<b><%=jobreqform.getJobreqcode()%></b>&nbsp;<%=Constant.getResourceStringValue("Requisition.state",user1.getLocale())%>:&nbsp;<b><%=jobreqform.getState()%></b>
			&nbsp; <%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%>:&nbsp;<b><%=jobreqform.getStatus()%></b>&nbsp;
			<%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%>:&nbsp;<b><%=jobreqform.getTemplateName()== null?"":jobreqform.getTemplateName()%></b>&nbsp;
			<%=Constant.getResourceStringValue("Requisition.remainig.possition",user1.getLocale())%>:&nbsp;<b><%=jobreqform.getNumberOfOpeningRemain()%></b></td>
		</tr>
	</table>


	<fieldset><legend><%=Constant.getResourceStringValue("Requisition.ReqDetails",user1.getLocale())%></legend>
	<tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>
	<fieldset><legend><%=Constant.getResourceStringValue("Requisition.reqdata",user1.getLocale())%></legend>
	<table border="0" width="100%">
		
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>

	<% if(screenFields.contains(FieldCodes.RequisitionScreen.IS_INTERNAL_JOB.toString())){%>



			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.isinternaljaob",user1.getLocale())%></td>
				<%
					if (jobreqform.getInternal() != null
								&& jobreqform.getInternal().equals("Y")) {
				%>
				<td><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%></td>
				<%
					} else {
				%>
				<td><%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%></td>
				<%
					}
				%>
			</tr>
	<%}%>
	
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.REQUISITION_NAME.toString())){%>	
			<tr>
				<td>
				 <!-- <a href="#" onMouseover="ddrivetip('The name of the requisition','yellow', 300)" ; onMouseout="hideddrivetip()">
				<img src="jsp/images/iconQuestionMark.gif" border="0" height="17" width="17" /></a> -->
				<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%></td>
				<td><%=jobreqform.getJobreqName()%></td>
			</tr>
	<%}%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_TITLE.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
				<td><%=jobreqform.getJobTitle()%></td>
			</tr>
	<%}%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_POSITION_NAME.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.jobpossitionname",user1.getLocale())%></td>
				<td><%=jobreqform.getJobPosition() == null ?"N/A":jobreqform.getJobPosition()%></td>
			</tr>
	<%}%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.NO_OF_OPENING.toString())){%>
	
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.Noofopenings",user1.getLocale())%></td>
				<td><%=jobreqform.getNumberOfOpening()%></td>
			</tr>
	<%}%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.IS_NEW_POSITION.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.IsNewPosition",user1.getLocale())%></td>
				<%
					if (jobreqform.getIsnewPositions() != null
								&& jobreqform.getIsnewPositions().equals("Y")) {
				%>
				<td><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%></td>
				<%
					} else {
				%>
				<td><%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%></td>
				<%
					}
				%>
			</tr>
	<%}%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.TARGET_FINISH_DATE.toString())){%>

			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Target.finish.date",user1.getLocale())%></td>
				<td><%=jobreqform.getTargetfinishdate()%></td>
			</tr>
	<%}%>

			<%
				String orgurl = null;
					System.out.println("jobreqform.getParentOrgId()"
							+ jobreqform.getParentOrgId());
					if (jobreqform.getParentOrgId() != 0) {

						orgurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "org.do?method=editorg&readPreview=2&orgid="
								+ jobreqform.getParentOrgId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqform.getParentOrgName()
								+ "</a>";

					}
			%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.ORGANIZATION.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> :</td>

				<td><%=(orgurl == null) ? "" : orgurl%></td>
			</tr>
	<%}%>
			<%
				String locationurl = null;
					System.out.println("jobreqform.getLocationId()"
							+ jobreqform.getLocationId());
					if (jobreqform.getLocationId() != 0) {

						locationurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "location.do?method=locationdetails&readPreview=2&id="
								+ jobreqform.getLocationId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqform.getLocationName()
								+ "</a>";

					}
			%>
<!-- 
			<tr>
				<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%> :</td>

				<td><%=(locationurl == null) ? "" : locationurl%></td>
			</tr>
 -->
			<%
				String depturl = null;
					System.out.println("jobreqform.getDepartmentId()"
							+ jobreqform.getDepartmentId());
					if (jobreqform.getDepartmentId() != 0) {

						depturl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "dept.do?method=editDepartmentDetails&readPreview=2&id="
								+ jobreqform.getDepartmentId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqform.getDepartmentName()
								+ "</a>";

					}
			%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.DEPARTMENT.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> :</td>

				<td><%=(depturl == null) ? "" : depturl%></td>
			</tr>
	<%}%>
			<%
				String projcturl = null;
					System.out.println("jobreqform.getProjectcodeId()"
							+ jobreqform.getProjectcodeId());
					if (jobreqform.getProjectcodeId() != 0) {

						projcturl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "projectcodes.do?method=editProjectCodes&readPreview=2&projectCodeId="
								+ jobreqform.getProjectcodeId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqform.getProjectcodeName()
								+ "</a>";

					}
			%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.PROJECT_CODE.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%> :</td>

				<td><%=(projcturl == null) ? "" : projcturl%></td>
			</tr>
	<%}%>
			<%
				String hiringvalueurl = null;
					System.out.println("jobreqform.getHiringMgrId()"
							+ jobreqform.getHiringMgrId());
					if (jobreqform.getHiringMgrId() != 0) {

hiringvalueurl = "<a href='#'  onClick=window.open(" + "'" + "user.do?method=edituser&readPreview=2&userId=" + jobreqform.getHiringMgrId() + "'" + "," + "'" + "_blank" + "'" + "," + "'" + "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500" + "'" + ")>" + jobreqform.getHiringMgrName() + "</a>";

					}
			%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.PRIMARY_SKILL_REQUIRED.toString())){%>
		
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.primary.skill.required",user1.getLocale())%> :</td>

				<td><%=(jobreqform.getPrimarySkill() == null) ? "" : jobreqform.getPrimarySkill()%></td>
			</tr>
		
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.HIRING_MANAGER.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%> :</td>

				<td><%=(hiringvalueurl == null) ? "" : hiringvalueurl%></td>
			</tr>
		<%}%>	


			<%
				String jobgradeurl = null;
					System.out.println("jobreqform.getJobgradeId()"
							+ jobreqform.getJobgradeId());
					if (jobreqform.getJobgradeId() != 0) {

						jobgradeurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "jobgrade.do?method=jobGradeDetails&readPreview=2&id="
								+ jobreqform.getJobgradeId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqform.getJobgradeName()
								+ "</a>";

					}
			%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_GRADE.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.jobgrade",user1.getLocale())%> :</td>

				<td><%=(jobgradeurl == null) ? "" : jobgradeurl%></td>
			</tr>
		<%}%>
			<%
				String salaryplanurl = null;
					System.out.println("jobreqform.getSalaryplanId()"
							+ jobreqform.getSalaryplanId());
					if (jobreqform.getSalaryplanId() != 0) {

						salaryplanurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "salaryplan.do?method=editsaaryplan&readPreview=2&planId="
								+ jobreqform.getSalaryplanId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqform.getSalaryplanName()
								+ "</a>";

					}
			%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.SALARY_PLAN.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%> :</td>

				<td><%=(salaryplanurl == null) ? "" : salaryplanurl%></td>
			</tr>
	<%}%>
			<%
				String budgetcodeurl = null;
					System.out.println("jobreqform.getBudgetcodeId()"+ jobreqform.getBudgetcodeId());
					if (jobreqform.getBudgetcodeId() != 0) {

budgetcodeurl = "<a href='#'  onClick=window.open(" + "'" + "budgetcode.do?method=editbudgetCode&readPreview=2&id=" + jobreqform.getBudgetcodeId() + "'" + "," + "'" + "_blank" + "'" + "," + "'" + "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500" + "'" + ")>" + jobreqform.getBudgetcodeName() + "</a>";

					}
			%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.BUDGET_CODE.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.budgetcode",user1.getLocale())%> :</td>

				<td><%=(budgetcodeurl == null) ? "" : budgetcodeurl%></td>
			</tr>
	<%}%>
			<%
				String reqcruiterurl = null;
					
					if (jobreqform.getRecruiterId() != 0) {

						if(!StringUtils.isNullOrEmpty(jobreqform.getIsgrouprecruiter()) && jobreqform.getIsgrouprecruiter().equals("Y")){

							reqcruiterurl = "<img src='jsp/images/User-Group-icon.png'><a href='#'  onClick=window.open(" + "'" + "usergroup.do?method=editusergroup&readPreview=2&usergroupid=" + jobreqform.getRecruiterId() + "'" + "," + "'" + "_blank" + "'" + "," + "'" + "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500" + "'" + ")>" + jobreqform.getRecruiterName() + "</a>";
						}else{

							reqcruiterurl = "<img src='jsp/images/user.gif'><a href='#'  onClick=window.open(" + "'" + "user.do?method=edituser&readPreview=2&userId=" + jobreqform.getRecruiterId() + "'" + "," + "'" + "_blank" + "'" + "," + "'" + "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500" + "'" + ")>" + jobreqform.getRecruiterName() + "</a>";
						}



					}
			%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.RECRUITER.toString())){%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Recruiter",user1.getLocale())%> :</td>

				<td><%=(reqcruiterurl == null) ? "" : reqcruiterurl%></td>
			</tr>
	<%}%>
	<% if(screenFields.contains(FieldCodes.RequisitionScreen.NOTES.toString())){%>		
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.notes",user1.getLocale())%></td>
				<td><%=jobreqform.getJobreqDesc()%></td>
			</tr>
	<%}%>
	</table>
	</fieldset>

            <tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>

	
<%if(jobdetailsothersize > 0){ %>
		<fieldset><legend><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></legend>
		<table border="0" width="65%">

			<tr>
				<td></td>
				<td></td>
			</tr>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.INTRODUCTION.toString())){%>	
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.Introduction",user1.getLocale())%></td>
				<td><%=jobreqform.getJobInstructions()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_DETAILS.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></td>
				<td><%=jobreqform.getJobDetails()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_ROLES.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.JobRoles",user1.getLocale())%></td>
				<td><%=jobreqform.getJobRoles()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.DEFAULT_STANDARD_WORKING_HOURS.toString())){%>	
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DefaultStandardWorkingHour",user1.getLocale())%>*</td>
				<td><%=jobreqform.getDefaultStandardHours()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.DURATION_IN_MONTHS.toString())){%>	
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DurationinMonths",user1.getLocale())%></td>
				<td><%=jobreqform.getDurationinmonths()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_EXPERIENCE_REQUIRED.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minexp",user1.getLocale())%></td>
				<td><%=jobreqform.getMinyearsofExpRequired()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.MAX_EXPERIENCE_REQUIRED.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.maxexp",user1.getLocale())%></td>
				<td><%=jobreqform.getMaxyearsofExpRequired()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.MIN_LEVEL_OF_EDUCATION.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minedu",user1.getLocale())%></td>
				<td><%=jobreqform.getMinimumLevelOfEducation()== null?"":jobreqform.getMinimumLevelOfEducation()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.OTHER_EXP_REQUIRED.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.otherExp",user1.getLocale())%></td>
				<td><%=jobreqform.getOtherExperience()== null?"":jobreqform.getOtherExperience()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.JOB_TYPE.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></td>
				<td><%=jobreqform.getJobtype()== null?"":jobreqform.getJobtype()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.WORK_SHIFT.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%></td>
				<td><%=jobreqform.getWorkshift()== null?"":jobreqform.getWorkshift()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.FLSA_STATUS.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.FLSA.Status",user1.getLocale())%></td>
				<td><%=jobreqform.getFlsastatus()== null?"":jobreqform.getFlsastatus()%></td>
			</tr>
		<%}%>
		<% if(screenFields.contains(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString())){%>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.compfreq",user1.getLocale())%></td>
				<td><%=jobreqform.getCompfrequency()== null?"":jobreqform.getCompfrequency()%></td>
			</tr>
		<%}%>

		</table>
   </fieldset>
<%}%>
   <br>
<% 
	List formvariableList = jobreqform.getFormVariablesList();
	if(formvariableList != null && formvariableList.size()>0){
			
%>
<fieldset><legend><%=Constant.getResourceStringValue("Requisition.additional.jobreqdetails",user1.getLocale())%></legend>

<%@ include file="customVariablestoRequisitionDetails.jsp" %>
</fieldset>
<%	} %>

	<tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>	
<fieldset><legend><%=Constant.getResourceStringValue("Requisition.Qualification_Details",user1.getLocale())%></legend>


			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>
		<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_required",user1.getLocale())%></legend>

		<table>
		<%=Constant.getResourceStringValue("aquisition.applicant.grades",user1.getLocale())%>
			<tr>
				<td width="33%"><%=Constant.getResourceStringValue("Requisition.Competency_name",user1.getLocale())%></td>
				<td width="43%"><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></td>
				<td width="23%"><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></td>
			</tr>
			<%
				List comptetencyList = jobreqform.getComptetencyList();
					if (comptetencyList != null && comptetencyList.size() > 0) {
						for (int i = 0; i < comptetencyList.size(); i++) {
							JobTemplateCompetency comp = (JobTemplateCompetency) comptetencyList
									.get(i);
			%>

			<tr>
				<td><%=comp.getCharName()%></td>
				<td><%=comp.getImportance()%></td>
				<td><%=(comp.getMandatory() != null && comp
								.getMandatory().equals("On")) ? "Yes" : "No"%></td>
			</tr>

			<%
				}
					} else {
			%>
			<%=Constant.getResourceStringValue("aquisition.applicant.Competencies_are_not_defind",user1.getLocale())%>
			<%
				}
			%>
		</table>
		</fieldset>






		<tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>

		<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments_required",user1.getLocale())%></legend>

		<table>
			<tr>
				<td width="33%"><%=Constant.getResourceStringValue("Requisition.Competency_name",user1.getLocale())%></td>
				<td width="43%"><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></td>
				<td width="23%"><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></td>
			</tr>
			<%
				List accList = jobreqform.getAccomplishmentList();
					if (accList != null && accList.size() > 0) {
						for (int i = 0; i < accList.size(); i++) {
							JobTemplateAccomplishment acc = (JobTemplateAccomplishment) accList
									.get(i);
			%>

			<tr>
				<td><%=acc.getAccName()%></td>
				<td><%=acc.getImportance()%></td>
				<td><%=(acc.getMandatory() != null && acc
								.getMandatory().equals("On")) ? "Yes" : "No"%></td>
			</tr>

			<%
				}
					} else {
			%>
						<%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments_are_not_defind",user1.getLocale())%>
			<%
				}
			%>
		</table>

		</fieldset>
	
</fieldset>

<% if (jobreqform.getApproverStatus().equals("yes")){%>	

 <tr>
            <br>
			<tr>
				<td></td>
				<td></td>
			</tr>

				<%
					List approverList = jobreqform.getApproversList();
				%>
				<fieldset><legend><%=Constant.getResourceStringValue("Requisition.Approval_details",user1.getLocale())%></legend>
				<table>
					<b><%=Constant.getResourceStringValue("Requisition.ApprovalStatistics",user1.getLocale())%> </b>
					<table width="100%">
						<tr>
<td><%=Constant.getResourceStringValue("Requisition.Level",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.ApproverName",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.OnDate",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.Re.assigned.Graph",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.Re.assigned.by",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.Re.assigned.on",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("Requisition.Re.assigned.comment",user1.getLocale())%></td>

						</tr>

						<%
							for (int i = 0; i < approverList.size(); i++) {
									JobTemplateApprovers japp2 = (JobTemplateApprovers) approverList
											.get(i);
									String approvetext = "Approved";
									if (japp2.getApproved().equals("Y")) {
										approvetext = "Approved";
									}
									if (japp2.getApproved().equals("N")) {
										approvetext = "Not approved";
									}
									if (japp2.getApproved().equals("R")) {
										approvetext = "Rejected";
									}
									String bgcolor = "";
									if (i % 2 == 1)
										bgcolor = "#edf5ff";
						%>
						<tr>
							<td><%=Constant.getResourceStringValue("Requisition.Level",user1.getLocale())%>-<%=i + 1%></td>

                            
							<td><a href="#"
								onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=japp2.getUserId()%>','EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=400')"><%=japp2.getApproverName()%></a>
							</td>





							<td><%=approvetext%></td>
							<td><%=(japp2.getApprovedDate() == null) ? "" : japp2
							.getApprovedDate()%></td>
							<td><%=(japp2.getComment() == null) ? "" : japp2
							.getComment()%></td>
							<td><%=(japp2.getReassignedgraphname() == null)
							? ""
							: japp2.getReassignedgraphname()%></td>
							<td><%=(japp2.getReassignedbyName() == null)
							? ""
							: japp2.getReassignedbyName()%></td>
							<td><%=(japp2.getReassignedDate() == null) ? "" : japp2
							.getReassignedDate()%></td>
							<td><%=(japp2.getReassignedcomment() == null)
							? ""
							: japp2.getReassignedcomment()%></td>

						</tr>
						<%
							}
						%>
					</table>




				</table>
				</fieldset>


<%}%>





<% if (jobreqform.getOfferapplicantStatus().equals("yes")){%>	

<tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>


	
		<fieldset><legend><%=Constant.getResourceStringValue("Requisition.Offered_applicants_details",user1.getLocale())%></legend>
		<table>

			<%
				List applicantList = jobreqform.getApplicantList();
			%>


			<table border="0" width="100%">
				<tr>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.Experience",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.CurrentOrganization",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",user1.getLocale())%></td>
				</tr>
				<%
					if (applicantList != null) {

							for (int i = 0; i < applicantList.size(); i++) {

								JobApplicant applicant = (JobApplicant) applicantList
										.get(i);
								String bgcolor = "";
								if (i % 2 == 0)
									bgcolor = "#B2D2FF";

								//if(applicant.getInterviewState()!=null && applicant.getInterviewState().equals(Common.OFFER_ACCEPTED)){
								//	bgcolor ="green";
								//}
				%>


				<tr bgcolor=<%=bgcolor%>>
					<td><a
						href="applicant.do?method=applicantDetails&applicantId=<%=applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>"
						target="new"><%=applicant.getFullName() == null ?"" : applicant.getFullName()%></a></td>
					<td><%=applicant.getInterviewState() == null ?"" : applicant.getInterviewState() %></td>
					<td><%=(applicant.getHeighestQualification() == null)
										? ""
										: applicant.getHeighestQualification()%></td>
					<td><%=(applicant.getPrimarySkill() == null)
								? ""
								: applicant.getPrimarySkill()%></td>
					<td><%=applicant.getEmail() == null ?"" : applicant.getEmail()%></td>
					<td><%=applicant.getNoofyearsexp()  == 0 ?"":applicant.getNoofyearsexp()%></td>
					<td><%=(applicant.getPreviousOrganization() == null)
								? ""
								: applicant.getPreviousOrganization()%></td>
					<td><%=applicant.getDateofbirth() ==null ?"":applicant.getDateofbirth()%></td>
				</tr>


				<%
					}
						}
				%>
				<%
					if (applicantList != null && applicantList.size() < 1) {
				%>
				<tr>
					<td><font color="red"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Not.yet.offered",user1.getLocale())%> </b></font></td>

				</tr>
				<%
					}
				%>

			</table>
			</fieldset>
			
<%}%>

			
</fieldset>
		
</html:form>
</html>
