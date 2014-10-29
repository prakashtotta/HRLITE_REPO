<%@ include file="../common/include.jsp"%>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>

<html>
<title><%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%> </title>

<bean:define id="jobreqtmplform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />
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
<html:form action="/jobreq.do?method=editjobreq"
	enctype="multipart/form-data">
	<div id="demo" class="yui-navset">
	<%
		String viewforselector = (String) request
					.getAttribute("viewforselector");
	%>

		<table width="105%">
		<tr bgcolor="#edf5ff">
			<td class="yui-dt">Job Template Id :&nbsp;<b><%=jobreqtmplform.getTemplateId()%></b>
			&nbsp;Job Template Name :&nbsp;<b><%=jobreqtmplform. getTemplateName()%></b>
			&nbsp;State :&nbsp;<b><%=jobreqtmplform.getState()%></b>
			&nbsp;Status :&nbsp;<b><%=jobreqtmplform.getStatus()%></b>
			</td>
		</tr>
	</table>
	
		<fieldset><legend>Requisition Template Details</legend>
	<tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>
	<fieldset><legend><%=Constant.getResourceStringValue("Requisition.reqdata",user1.getLocale())%></legend>
		<table border="0" width="100%">
		<tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>



			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.isinternaljaob",user1.getLocale())%></td>
							<%
					if (jobreqtmplform.getInternal() != null
								&& jobreqtmplform.getInternal().equals("Y")) {
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
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%>*</td>
			<td><%=jobreqtmplform.getTemplateName()%></td>
		</tr>
					<%
				String orgurl = null;
					System.out.println("jobreqtmplform.getParentOrgId()"
							+ jobreqtmplform.getParentOrgId());
					if (jobreqtmplform.getParentOrgId() != 0) {

						orgurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "org.do?method=editorg&readPreview=2&orgid="
								+ jobreqtmplform.getParentOrgId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getParentOrgName()
								+ "</a>";

					}
			%>
		<tr>
		<td width="40%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>*</td>
		<td><%=(orgurl == null) ? "" : orgurl%></td>
		</tr>
			<%
				String locationurl = null;
					System.out.println("jobreqtmplform.getLocationId()"
							+ jobreqtmplform.getLocationId());
					if (jobreqtmplform.getLocationId() != 0) {

						locationurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "location.do?method=locationdetails&readPreview=2&id="
								+ jobreqtmplform.getLocationId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getLocationName()
								+ "</a>";

					}
			%>

			<tr>
				<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%> :</td>

				<td><%=(locationurl == null) ? "" : locationurl%></td>
			</tr>

			<%
				String depturl = null;
					System.out.println("jobreqtmplform.getDepartmentId()"
							+ jobreqtmplform.getDepartmentId());
					if (jobreqtmplform.getDepartmentId() != 0) {

						depturl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "dept.do?method=editDepartmentDetails&readPreview=2&id="
								+ jobreqtmplform.getDepartmentId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getDepartmentName()
								+ "</a>";

					}
			%>

			<tr>
				<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> :</td>

				<td><%=(depturl == null) ? "" : depturl%></td>
			</tr>
						<%
				String projcturl = null;
					System.out.println("jobreqtmplform.getProjectcodeId()"
							+ jobreqtmplform.getProjectcodeId());
					if (jobreqtmplform.getProjectcodeId() != 0) {

						projcturl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "projectcodes.do?method=editProjectCodes&readPreview=2&projectCodeId="
								+ jobreqtmplform.getProjectcodeId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getProjectcodeName()
								+ "</a>";

					}
			%>

			<tr>
				<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%> :</td>

				<td><%=(projcturl == null) ? "" : projcturl%></td>
			</tr>
						<%
				String hiringvalueurl = null;
					System.out.println("jobreqtmplform.getHiringMgrId()"
							+ jobreqtmplform.getHiringMgrId());
					if (jobreqtmplform.getHiringMgrId() != 0) {

hiringvalueurl = "<a href='#'  onClick=window.open(" + "'" + "user.do?method=edituser&readPreview=2&userId=" + jobreqtmplform.getHiringMgrId() + "'" + "," + "'" + "_blank" + "'" + "," + "'" + "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500" + "'" + ")>" + jobreqtmplform.getHiringMgrName() + "</a>";

					}
			%>

			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.hiringmgr",user1.getLocale())%> :</td>

				<td><%=(hiringvalueurl == null) ? "" : hiringvalueurl%></td>
			</tr>
			
						<%
				String jobgradeurl = null;
					System.out.println("jobreqtmplform.getJobgradeId()"
							+ jobreqtmplform.getJobgradeId());
					if (jobreqtmplform.getJobgradeId() != 0) {

						jobgradeurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "jobgrade.do?method=jobGradeDetails&readPreview=2&id="
								+ jobreqtmplform.getJobgradeId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getJobgradeName()
								+ "</a>";

					}
			%>

			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.jobgrade",user1.getLocale())%> :</td>

				<td><%=(jobgradeurl == null) ? "" : jobgradeurl%></td>
			</tr>
			
						<%
				String salaryplanurl = null;
					System.out.println("jobreqtmplform.getSalaryplanId()"
							+ jobreqtmplform.getSalaryplanId());
					if (jobreqtmplform.getSalaryplanId() != 0) {

						salaryplanurl = "<a href='#'  onClick=window.open("
								+ "'"
								+ "salaryplan.do?method=editsaaryplan&readPreview=2&planId="
								+ jobreqtmplform.getSalaryplanId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getSalaryplanName()
								+ "</a>";

					}
			%>

			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%> :</td>

				<td><%=(salaryplanurl == null) ? "" : salaryplanurl%></td>
			</tr>
			
						<%
				String budgetcodeurl = null;
					System.out.println("jobreqform.getBudgetcodeId()"+ jobreqtmplform.getBudgetcodeId());
					if (jobreqtmplform.getBudgetcodeId() != 0) {

budgetcodeurl = "<a href='#'  onClick=window.open(" + "'" + "budgetcode.do?method=editbudgetCode&readPreview=2&id=" + jobreqtmplform.getBudgetcodeId() + "'" + "," + "'" + "_blank" + "'" + "," + "'" + "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500" + "'" + ")>" + jobreqtmplform.getBudgetcodeName() + "</a>";

					}
			%>

			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.budgetcode",user1.getLocale())%> :</td>

				<td><%=(budgetcodeurl == null) ? "" : budgetcodeurl%></td>
			</tr>
			
			<%
				String referraltempurl = null;
					System.out.println("jobreqtmplform.getRefferalSchemeId()"
							+ jobreqtmplform.getRefferalSchemeId());
					if (jobreqtmplform.getRefferalSchemeId() != 0) {
						System.out.println("in if ............");
						referraltempurl = "<a href='#' onClick=window.open("
								+"'"
								+"refferalscheme.do?method=editRefferalScheme&readPreview=2&id="
								+jobreqtmplform.getRefferalSchemeId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getRefferalSchemeName()
								+ "</a>";

					}
					System.out.println("referraltempurl : "+referraltempurl);
			%>
			<tr>
			  
			<td><%=Constant.getResourceStringValue("Requisition.Employee.Referral.Scheme",user1.getLocale())%>*</td>
			<td><%=(referraltempurl == null) ? "" : referraltempurl%></td>
			</tr>
			<%
				String agreferralvalue = null;
					System.out.println("jobreqtmplform.getAgencyRefferalSchemeId()"
							+ jobreqtmplform.getAgencyRefferalSchemeId());
					if (jobreqtmplform.getAgencyRefferalSchemeId() != 0) {
						System.out.println("in if ............");
						agreferralvalue = "<a href='#' onClick=window.open("
								+"'"
								+"refferalscheme.do?method=editRefferalScheme&readPreview=2&id="
								+jobreqtmplform.getAgencyRefferalSchemeId()
								+ "'"
								+ ","
								+ "'"
								+ "_blank"
								+ "'"
								+ ","
								+ "'"
								+ "location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=500"
								+ "'" + ")>" + jobreqtmplform.getAgencyRefferalSchemeName()
								+ "</a>";

					}
					System.out.println("agreferralvalue : "+agreferralvalue);
			%>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Agency.Referral.Scheme",user1.getLocale())%>*</td>
				<td><%=(agreferralvalue == null) ? "" : agreferralvalue%></td>	
			</tr>
			
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.notes",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getTemplateDesc()%></td>
			</tr>
	</table>
	</fieldset>
	
	<fieldset><legend><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></legend>
		<table border="0" width="65%">

			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Introduction",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getJobInstructions()%></td>
			</t>
			
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getJobDetails()%></td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.JobRoles",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getJobRoles()%></td>
			</tr>
			
						<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DefaultStandardWorkingHour",user1.getLocale())%>*</td>
				<td><%=jobreqtmplform.getDefaultStandardHours()%></td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.DurationinMonths",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getDurationinmonths()%></td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minexp",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getMinyearsofExpRequired()%></td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.maxexp",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getMaxyearsofExpRequired()%></td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.minedu",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getMinimumLevelOfEducation()%></td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.otherExp",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getOtherExperience()%></td>
			</tr>
			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobtype",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getJobTypeName()%></td>
			</tr>

			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.workshift",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getShiftName()%></td>
			</tr>

			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.FLSA.Status",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getFlsaName()%></td>
			</tr>

			<tr>
			<td><%=Constant.getResourceStringValue("Requisition.compfreq",user1.getLocale())%></td>
				<td><%=jobreqtmplform.getCompfrequency()%></td>
			</tr>


		</table>
   </fieldset>
   
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
					List comptetencyList = jobreqtmplform.getComptetencyList();
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
		<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments_required",user1.getLocale())%></legend>

		<table>
			<tr>
				<td width="33%"><%=Constant.getResourceStringValue("Requisition.Competency_name",user1.getLocale())%></td>
				<td width="43%"><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></td>
				<td width="23%"><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></td>
			</tr>
			<%
				List accList = jobreqtmplform.getAccomplishmentList();
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
</fieldset>
	

				<%
					List approverList = jobreqtmplform.getApproversList();
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





</html:form>
</html>