<script language="javascript">
function unhideotherskill(){
	var primaryskilvalue = document.applicantForm.primarySkill.value;
	if(primaryskilvalue == 'Others'){
		document.applicantForm.primarySkillOther.style.visibility="visible"; 
			 
	}else{
		document.applicantForm.primarySkillOther.style.visibility="hidden"; 
	}
}
</script>

<%
String width="100%";
List mlist =  new ArrayList();
mlist.add("backtodetails");
mlist.add("refferaluserdetails");
String mmm = (String)request.getParameter("method");

if(mlist.contains(mmm)){
	width="100%";
}
%>

<%@ include file="../talent/sceenFieldSorting.jsp" %>

<html:hidden property="screenCode"/>

<div class="container">

<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[personalinfo]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.personal.informations",locale)%> </div>
<div id="personalinfo" style="width  100%;">

<table border="0" width="<%=width%>">


<%

 for(int i=0;i<PERSONAL_INFO_List.size();i++){
	ScreenFields scf = (ScreenFields)PERSONAL_INFO_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.APPLICANT_NAME.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.name",locale)%><font color="red">*</font></td>
			<td><html:text property="fullName" styleClass="textdynamic" size="50" maxlength="600"/></td>
		</tr>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.DATE_OF_BIRTH.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.DATE_OF_BIRTH.toString())){%>
		
 	   <tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.DATE_OF_BIRTH.toString())){%>
			<font color="red">*</font>
			<%}%>			
			</td>
			<td>
			
			<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" class="textdynamic" NAME="dateofbirth" readonly="true" value="<%=aform.getDateofbirth()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.dateofbirth,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",locale)%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" border="0" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			
			</td>
		</tr>

<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EMAIL_ID.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.EMAIL_ID.toString())){%>
		<tr>

			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.email",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.EMAIL_ID.toString())){%>
			<font color="red">*</font>
			<%}%>	
			</td>
			<td><html:text property="email" styleClass="textdynamic" size="50" maxlength="500"/></td>


		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.ALTERNATE_EMAIL_ID.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.ALTERNATE_EMAIL_ID.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Alternate_Email",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.ALTERNATE_EMAIL_ID.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="alternateemail" styleClass="textdynamic" size="50" maxlength="500"/></td>	
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.PASSPORT_NO.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.PASSPORT_NO.toString())){%>
		<tr>
			 <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.PassportNo",locale)%>
			 <% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.PASSPORT_NO.toString())){%>
			<font color="red">*</font>
			<%}%>
			 </td>
			<td><html:text property="passportno" styleClass="textdynamic" size="50"/></td>
		<tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.SSN_NUMBER.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.SSN_NUMBER.toString())){%>
		<tr>
			 <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.SSNNo",locale)%>
			 <% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.SSN_NUMBER.toString())){%>
			<font color="red">*</font>
			<%}%>
			 </td>
			<td><html:text property="ssnno" styleClass="textdynamic" size="50"/></td>
		<tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.TAX_ID_NUMBER.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.TAX_ID_NUMBER.toString())){%>
		<tr>
			 <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.TaxIdNo",locale)%>
			 <% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.TAX_ID_NUMBER.toString())){%>
			<font color="red">*</font>
			<%}%>
			 </td>
			<td><html:text property="taxidno" styleClass="textdynamic" size="50"/></td>
		<tr>
<%}%>
<%}%>	
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.PHONE_NUMBER.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.PHONE_NUMBER.toString())){%>		
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.phone",locale)%>
			 <% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.PHONE_NUMBER.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="phone" styleClass="textdynamic" size="30" maxlength="50"/></td>
		</tr>
<%}%>
<%}%>	
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.ALTERNATE_PHONE_NUMBER.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.ALTERNATE_PHONE_NUMBER.toString())){%>	
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Alternate_phone",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.ALTERNATE_PHONE_NUMBER.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="alternatephone" styleClass="textdynamic" size="30" maxlength="50"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.COUNTRY.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.COUNTRY.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Country",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.COUNTRY.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="countryId" onchange="retrieveURL('user.do?method=loadState');" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("aquisition.applicant.Loading_States",locale)%>......</span>
			</td>
	    </tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.STATE.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.STATE.toString())){%>
		<tr>
			<td width="40%" id="state" ><%=Constant.getResourceStringValue("aquisition.applicant.State",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.STATE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<span id="states">
			<html:select  property="stateId" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="stateList" id="stateList" />

            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
			</html:select>&nbsp;
			</span>
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CITY.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.CITY.toString())){%>
       <tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.City",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.CITY.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="city" size="50" styleClass="textdynamic" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.ADDRESS.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.ADDRESS.toString())){%>
       <tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.ADDRESS",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.ADDRESS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:textarea property="address" styleClass="textdynamic" cols="60" rows="5"/></td>
		</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.ZIPCODE.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.ZIPCODE.toString())){%>
       <tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.ZIPCODE",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.ZIPCODE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="zipcode" size="20" styleClass="textdynamic" maxlength="20"/></td>
		</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.NATIONALITY.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.NATIONALITY.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Nationality",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.NATIONALITY.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="nationalityId" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="countryList" id="countryList" />
            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>
			</td>
	    </tr>
<%}%>
<%}%>


<%}// end of for loop%>
</table>
</div>


<% if(WORK_PREFERENCE_List.size()>0){%>
<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[workpreference]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.work.preference",locale)%> </div>
<div id="workpreference" style="width  100%;">


<table border="0" width="<%=width%>">
<%
 for(int i=0;i<WORK_PREFERENCE_List.size();i++){
	ScreenFields scf = (ScreenFields)WORK_PREFERENCE_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.PREFERRED_LOCATION.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.PREFERRED_LOCATION.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Prefered_location",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.PREFERRED_LOCATION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="preferedlocation" styleClass="textdynamic" size="50"/></td>
		</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CURRENT_CTC.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.CURRENT_CTC.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Current_CTC",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.CURRENT_CTC.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="currectctc" styleClass="textdynamic" size="20" maxlength="200"/>		
			<span id="currectctccurrencycodesp">
			<%=(aform.getCurrectctccurrencycode() == null)?"":aform.getCurrectctccurrencycode()%>
			</span>
			
			</td>
		</tr>
 <%}%> 
 <%}%>
 <% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EXPECTED_CTC.toString())){%>
 <% if(screenFields.contains(FieldCodes.ApplicantScreen.EXPECTED_CTC.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Expected_CTC",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.EXPECTED_CTC.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="expectedctc" styleClass="textdynamic" size="20" maxlength="200"/>
			<span id="expectedctccurrencycodesp"><%=(aform.getExpectedctccurrencycode() == null)?"":aform.getExpectedctccurrencycode()%></span>
			</td>
		</tr>
<%}%>
 <%}%>
 <% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.NOTICE_PERIOD.toString())){%>
 <% if(screenFields.contains(FieldCodes.ApplicantScreen.NOTICE_PERIOD.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Notice_period",locale)%> 
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.NOTICE_PERIOD.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="noticeperiod" styleClass="textdynamic" size="20"/><%=Constant.getResourceStringValue("aquisition.applicant.days",locale)%> </td>
		</tr>
<%}%>
<%}%>
 <% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CURRENT_DESIGNATION.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.CURRENT_DESIGNATION.toString())){%>
        <tr>

		    <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Current_designation",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.CURRENT_DESIGNATION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="currentdesignation" styleClass="textdynamic" size="50"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CURRENT_ORGANIZATION.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.CURRENT_ORGANIZATION.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Current_organization",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.CURRENT_ORGANIZATION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			
			<html:text property="previousOrganization" styleClass="textdynamic" size="50" maxlength="200"/>
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.YEARS_OF_EXPERINCE.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.YEARS_OF_EXPERINCE.toString())){%>
		<tr>			
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.YEARS_OF_EXPERINCE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<% 
			String noofexp = String.valueOf(aform.getNoofyearsexp());
						noofexp = noofexp.equals("0.0") ? "" :noofexp;
			//String noofexp1 = Double.toString(aform.getNoofyearsexp());
			
			%>
			<td><html:text property="noofyearsexp" styleClass="textdynamic" size="10" maxlength="5" value="<%=noofexp%>"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.RELEVANT_YEARS_OF_EXPERINCE.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.RELEVANT_YEARS_OF_EXPERINCE.toString())){%>
		<tr>			
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.RELEVANT_YEARS_OF_EXPERINCE",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.RELEVANT_YEARS_OF_EXPERINCE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<% 
			String noofexp = String.valueOf(aform.getRelyearsofexp());
						noofexp = noofexp.equals("0.0") ? "" :noofexp;
			//String noofexp1 = Double.toString(aform.getNoofyearsexp());
			
			%>
			<td><html:text property="relyearsofexp" styleClass="textdynamic" size="10" maxlength="5" value="<%=noofexp%>"/></td>
		</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.PROFILE_HEADER.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.PROFILE_HEADER.toString())){%>
		
		<tr>
		    <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Profile_Header",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.PROFILE_HEADER.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="resumeHeader" styleClass="textdynamic" size="50"/></td>
			
		</tr>
 <%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EARLIEST_START_DATE.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.EARLIEST_START_DATE.toString())){%>
		
 	   <tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.EARLIEST_START_DATE",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.EARLIEST_START_DATE.toString())){%>
			<font color="red">*</font>
			<%}%>			
			</td>
			<td>
			
			<SCRIPT LANGUAGE="JavaScript" ID="jsca2xx">

var cal2xx = new CalendarPopup("testdiv2");
cal1xx.showNavigationDropdowns();


</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" class="textdynamic" NAME="earliest_start_date" readonly="true" value="<%=aform.getEarliest_start_date()%>" SIZE=25>

<A HREF="#" onClick="cal2xx.select(document.applicantForm.earliest_start_date,'anchor2xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",locale)%>" NAME="anchor2xx" ID="anchor2xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>


<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			
			</td>
		</tr>

<%}%>
<%}%>


<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.WORK_ON_WEEKENDS.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.WORK_ON_WEEKENDS.toString())){%>
		
		<tr>
		    <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_WEEKENDS",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.WORK_ON_WEEKENDS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="work_on_weekends" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			
			</td>
			
		</tr>
 <%}%>
<%}%>


<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.WORK_ON_EVENINGS.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.WORK_ON_EVENINGS.toString())){%>
		
		<tr>
		    <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_EVENINGS",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.WORK_ON_EVENINGS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="work_on_evenings" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			
			</td>
			
		</tr>
 <%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.WORK_ON_OVERTIME.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.WORK_ON_OVERTIME.toString())){%>
		
		<tr>
		    <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.WORK_ON_OVERTIME",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.WORK_ON_OVERTIME.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="work_on_overtime" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			
			</td>
			
		</tr>
 <%}%>
<%}%>


<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.WANT_TO_RELOCATE.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.WANT_TO_RELOCATE.toString())){%>
		
		<tr>
		    <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.WANT_TO_RELOCATE",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.WANT_TO_RELOCATE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="want_to_relocate" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			
			</td>
			
		</tr>
 <%}%>
<%}%>


<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.LANGUAGES_SPOKEN.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.LANGUAGES_SPOKEN.toString())){%>
		
		<tr>
		    <td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.LANGUAGES_SPOKEN",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.LANGUAGES_SPOKEN.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:text property="languages_spoken" size="50" styleClass="textdynamic" maxlength="200"/>
			
			</td>
			
		</tr>
 <%}%>
<%}%>


<%}//end of work preference loop%>
</table>
</div>
<%}//end of work preference size%>



<% if(EDUCATION_List.size()>0){%>
<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[education]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("admin.applicant.education.details",locale)%> </div>
<div id="education" style="width  100%;">
<table border="0" width="<%=width%>">

<%
 for(int i=0;i<EDUCATION_List.size();i++){
	ScreenFields scf = (ScreenFields)EDUCATION_List.get(i);
     
%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.PRIMARY_SKILL.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.PRIMARY_SKILL.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.PRIMARY_SKILL.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="primarySkill" onchange="unhideotherskill()" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="lovList" id="lovList" />
            <html:options collection="lovList" property="technialSkillName"  labelProperty="technialSkillName"/>
			</html:select>
			<html:text property="primarySkillOther" size="50"/>
			</td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.HIGHEST_QUALIFICATION.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.HIGHEST_QUALIFICATION.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Heighest_Qualification",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.HIGHEST_QUALIFICATION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="heighestQualification" size="50" styleClass="textdynamic" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.OTHER_QUALIFICATIONS.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.OTHER_QUALIFICATIONS.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("Requisition.OtherQualifications",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.OTHER_QUALIFICATIONS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="qualifications" size="50" styleClass="textdynamic" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.COLLEGE_NAME.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.COLLEGE_NAME.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.COLLEGE_NAME",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.COLLEGE_NAME.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="college_name" styleClass="textdynamic" size="50" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.COLLEGE_GPA.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.COLLEGE_GPA.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.COLLEGE_GPA",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.COLLEGE_GPA.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="college_GPA" size="20" styleClass="textdynamic" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>
 

<%}//end of education%>

</table>
</div>
<%}//end of education size%>


<% if(SOCIAL_List.size()>0){%>
<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[social]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>Social </div>
<div id="social" style="width  100%;">
<table border="0" width="<%=width%>">

<%
 for(int i=0;i<SOCIAL_List.size();i++){
	ScreenFields scf = (ScreenFields)SOCIAL_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.WEBSITE_URL.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.WEBSITE_URL.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.WEBSITE_URL",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.WEBSITE_URL.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="website_url" styleClass="textdynamic" size="50" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.LINKEDIN_URL.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.LINKEDIN_URL.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.LINKEDIN_URL",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.LINKEDIN_URL.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="linkedin_url" styleClass="textdynamic" size="50" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.FACEBOOK_URL.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.FACEBOOK_URL.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.FACEBOOK_URL",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.FACEBOOK_URL.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="facebook_url" styleClass="textdynamic" size="50" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>
 

<%}//end of social%>

</table>
</div>
<%}//end of social size%>


<% if(BACKGROUND_List.size()>0){%>
<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[background]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>Background </div>
<div id="background" style="width  100%;">
<table border="0" width="<%=width%>">

<%
 for(int i=0;i<BACKGROUND_List.size();i++){
	ScreenFields scf = (ScreenFields)BACKGROUND_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.WORK_STATUS.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.WORK_STATUS.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.WORK_STATUS",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.WORK_STATUS.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:text property="work_status" styleClass="textdynamic" size="50" maxlength="200"/></td>
		</tr>
<%}%>
<%}%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.FELONY_CONVICTION.toString())){%>
<% if(screenFields.contains(FieldCodes.ApplicantScreen.FELONY_CONVICTION.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.FELONY_CONVICTION",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.FELONY_CONVICTION.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td>
			<html:select  property="felony_conviction" styleClass="list">
			<option value=""></option>
			<bean:define name="applicantForm" property="yesnofulllist" id="yesnofulllist" />

            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
<%}%>

			<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.FELONY_CONVICTION_DESC",locale)%></td> 
			<td><html:textarea property="felony_conviction_desc" styleClass="textdynamic" cols="60" rows="5"/></td>
			</tr>

<%}%>
 

<%}//end of background%>

</table>
</div>
<%}//end of background size%>



<!-- custom variable start -->
<%@ include file="customvariableApplicant.jsp" %>
<!-- custom variable end -->	


<% if(PROFILE_List.size()>0){%>
<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[profile]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>Profile </div>
<div id="profile" style="width  100%;">
<table border="0" width="<%=width%>">

<%
 for(int i=0;i<PROFILE_List.size();i++){
	ScreenFields scf = (ScreenFields)PROFILE_List.get(i);
     
%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.RESUME_UPLOAD.toString())){%>
 <% if(screenFields.contains(FieldCodes.ApplicantScreen.RESUME_UPLOAD.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Resume_upload",locale)%>
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.RESUME_UPLOAD.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td>
			<td><html:file property="resumeData"/> 
			           
	<% if(BOFactory.getApplicantBO().getApplicantAttachment(aform.getApplicantId(),aform.getUuid(),Common.APPLICANT_ATTACHMENT_TYPE_RESUME) != null){%>
			<a href="applicantoffer.do?method=attachmentdetails&applicantId=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>&attachmentuuid=<%=aform.getAttachmentuuid()%>&fname=<%=aform.getResumename()%>" ><bean:write name="applicantForm" property="resumename"/></a>
			&nbsp;&nbsp;<a href="#" onClick="deleteresume();return false"><img src="jsp/images/delete.gif" border="0" alt="delete resume" title="<%=Constant.getResourceStringValue("aquisition.applicant.Resume_delete",locale)%>" height="20"  width="19"/></a>
         
			<%}%> 
			</td>
		</tr>
<%}%>



		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.resumeCopy",locale)%></td> 
			<td><html:textarea property="resumedetails" styleClass="textdynamic" cols="60" rows="5"/></td>
		</tr>

<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.NOTE.toString())){%>
 <% if(screenFields.contains(FieldCodes.ApplicantScreen.NOTE.toString())){%>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Note",locale)%> 
			<% if(mandatoryFields.contains(FieldCodes.ApplicantScreen.NOTE.toString())){%>
			<font color="red">*</font>
			<%}%>
			</td> 
			<td><html:textarea property="note" styleClass="textdynamic" cols="60" rows="5"/></td>
		</tr>
<%}%>
<%}%>
<%}//end of profile%>
</table>
</div>
<%}//end of profile size%>

</div>

<% 

if(BOFactory.getJobRequistionBO().isEEOInfoIncluded(aform.getRequitionId())){%>

<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[eeoinfo]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>The following three questions are entirely optional. </div>
<div id="eeoinfo" class="container" style="width  100%;">
<table border="0" width="<%=width%>">
<font size="1">
We would like to request you to enter this personal data.This data will help to comply with government Equal Employment Opportunity / Affirmative Action reporting regulations.
we assure you that this information will only be used for regulatory reporting purposes - hence your co-operation is requested.
</font>
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.gender",locale)%></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.gender.male",locale)%>
			<input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("M"))? "Checked=true" : "" %> value="M">&nbsp;&nbsp;
			<%=Constant.getResourceStringValue("aquisition.applicant.gender.female",locale)%><input type="radio" name="gender" <%=(aform.getGender()!= null && aform.getGender().equals("F"))? "Checked=true" : "" %> value="F">
			</td>
		</tr>


		
		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Ethnicity.race",locale)%>
			
			</td>
			<td>
			<html:select  property="ethnicRaceId" styleClass="list">
			<option value="">Decline to answer</option>
			<bean:define name="applicantForm" property="ethnicRaceList" id="ethnicRaceList" />
            <html:options collection="ethnicRaceList" property="ethnicRaceId"  labelProperty="ethnicRaceName"/>
			</html:select>
			</td>
	    </tr>


		<tr>
			<td width="40%">Veteran/Disability status</td>
			<td>
			<html:select  property="veteranDisabilityId" styleClass="list">
			<option value="">Decline to answer</option>
			<bean:define name="applicantForm" property="veteranDisabilityList" id="veteranDisabilityList" />
            <html:options collection="veteranDisabilityList" property="vdId"  labelProperty="name"/>
			</html:select>
			</td>
	    </tr>



</table>
</div>
<%}%>