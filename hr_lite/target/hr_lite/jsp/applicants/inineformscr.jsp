<%@ include file="../common/include.jsp" %>

<bean:define id="iform" name="iNineForm" type="com.form.INineForm" />
<%
String datepattern = Constant.getValue("defaultdateformat");
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

String iNineDatasave =(String)request.getAttribute("iNineDatasave");
String applicantId =(String)request.getAttribute("applicantId");
String showSaveBtn =(String)request.getAttribute("showSaveBtn");
%>
<html>
<head>
<script language="javascript">
function discard(){
	  var doyou = confirm('<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>');
	  if (doyou == true){

	 parent.parent.GB_hide();
	 
	   } 
	}

function savedata(){
	var alertstr = "";
	var showalert=false;
	var employeetype = document.iNineForm.employeetype.checked;
	var applicantid= document.iNineForm.applicantid.value.trim();
	var alienno = document.iNineForm.alienno.value.trim();
	var alienno2 = document.iNineForm.alienno2.value.trim();
	var i94no= document.iNineForm.i94no.value.trim();
	var dateofexpiration = document.iNineForm.dateofexpiration.value.trim();

	myOption = -1;
	for (i=document.iNineForm.employeeattest.length-1; i > -1; i--) {
	if (document.iNineForm.employeeattest[i].checked) {
	myOption = i; i = -1;
	}
	}

	var employeeattest=document.iNineForm.employeeattest[myOption].value;
	if(employeeattest == "3"){
		//alert(employeeattest);
		if(alienno == "" || alienno == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.alienno_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
	}
	if(employeeattest == "4"){
		//alert(employeeattest);
		if(alienno2 == "" || alienno2 == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.alienno_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
		if(i94no == "" || i94no == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.i94_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
		if(dateofexpiration == "" || dateofexpiration == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.expirationdate_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
	}
	

	if (showalert){
		alert(alertstr);
		return false;
	}
	
	document.iNineForm.action="inineform.do?method=inineformSubmit&employeetype="+employeetype+"&employeeattest="+employeeattest+"&applicantId="+applicantid;
	document.iNineForm.submit();
	
}
function updatedata(){
	var alertstr = "";
	var showalert=false;
	var employeetype = document.iNineForm.employeetype.checked;
	var iNineFormId= document.iNineForm.i9formid.value.trim();
	var applicantid= document.iNineForm.applicantid.value.trim();
	var alienno = document.iNineForm.alienno.value.trim();
	var alienno2 = document.iNineForm.alienno2.value.trim();
	var i94no= document.iNineForm.i94no.value.trim();
	var dateofexpiration = document.iNineForm.dateofexpiration.value.trim();

	myOption = -1;
	for (i=document.iNineForm.employeeattest.length-1; i > -1; i--) {
		if (document.iNineForm.employeeattest[i].checked) {
		myOption = i; i = -1;
		}
	}

	var employeeattest=document.iNineForm.employeeattest[myOption].value;
	if(employeeattest == "3"){
		//alert(employeeattest);
		if(alienno == "" || alienno == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.alienno_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
	}
	if(employeeattest == "4"){
		//alert(employeeattest);
		if(alienno2 == "" || alienno2 == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.alienno_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
		if(i94no == "" || i94no == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.i94_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
		if(dateofexpiration == "" || dateofexpiration == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("i9form.expirationdate_mandatory",user1.getLocale())%><BR>";
			showalert = true;
			}
	}
	

	if (showalert){
		alert(alertstr);
		return false;
	}
	document.iNineForm.action="inineform.do?method=updateinineform&employeetype="+employeetype+"&employeeattest="+employeeattest+"&iNineFormId="+iNineFormId;
	document.iNineForm.submit();
	
}
function hideShowtextBox(){

	myOption = -1;
	for (i=document.iNineForm.employeeattest.length-1; i > -1; i--) {
		if (document.iNineForm.employeeattest[i].checked) {
		myOption = i; i = -1;
		
		}
	}
	var employeeattest=document.iNineForm.employeeattest[myOption].value;
	//alert(employeeattest);
	if(employeeattest == "1" || employeeattest == "2"){
		document.iNineForm.alienno.value = "";
		document.iNineForm.alienno2.value = "";
		document.iNineForm.i94no.value = "";
		document.iNineForm.dateofexpiration.value = "";
		
		document.iNineForm.alienno.readOnly = 1;
		document.iNineForm.alienno2.readOnly = 1;
		document.iNineForm.i94no.readOnly = 1;
		document.iNineForm.dateofexpiration.readOnly = 1;
	}
	if(employeeattest == "3"){
		document.iNineForm.alienno.readOnly = 0;
		
		document.iNineForm.alienno2.value = "";
		document.iNineForm.i94no.value = "";
		document.iNineForm.dateofexpiration.value = "";
		
		document.iNineForm.alienno2.readOnly = 1;
		document.iNineForm.i94no.readOnly = 1;
		document.iNineForm.dateofexpiration.readOnly = 1;
	}
	if(employeeattest == "4"){
		document.iNineForm.alienno.value = "";
		document.iNineForm.alienno.readOnly = 1;
		
		document.iNineForm.alienno2.readOnly = 0;
		document.iNineForm.i94no.readOnly = 0;
		document.iNineForm.dateofexpiration.readOnly = 0;

	}

}
</script>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width:800px;
	border: none;
	padding: 10px;
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}


</style>
</head>
<body>
<html:form action="/inineform.do?method=inineformSubmit" >
<br>

<center>
	<fieldset>
		<%
		if(! StringUtils.isNullOrEmpty(iNineDatasave) && iNineDatasave.equals("yes") ){
		%>
		<table width="100%">
			<tr>
				
				<td align="left" >
				<font color="green"><%=Constant.getResourceStringValue("i9form.data.savemsg",user1.getLocale())%></font>
				</td>
			</tr>
		</table>
		<%}%>
		<table width="100%">
			<tr>
				<td align="left" >
				Department of Homeland Security<br>
				U.S. Citizenship and Immigration Services	
				</td>
				<td align="right">
				OMB No. 1615-0047; Expires 08/31/12<br>
				Form I-9, Employment<br>
				Eligibility Verification
				</td>
			
			</tr>
		</table>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 5px; width: 100%; text-align: center;">
		<p align="justify">
		Please read instructions carefully before completing this form. 
		The instructions must be available during completion of this form. 
		ANTI-DISCRIMINATION NOTICE: It is illegal to discriminate against 
		work-authorized individuals. Employers CANNOT specify which document(s) 
		they will accept from an employee. The refusal to hire an individual 
		because the documents have a future expiration date may also constitute 
		illegal discrimination.
		</p>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 5px; width: 100%; text-align: center;">
		<p align="left">
		Section 1. Employee Information and Verification.<br/>
		To be completed and signed by employee at the time employment begins.
		</p>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 2px; width: 100%; text-align: center;">
		
				<table width="100%">
					<tr>
						<td width="40%"><%=Constant.getResourceStringValue("i9form.data.lastname",user1.getLocale())%></td>
						<td width="25%"><%=Constant.getResourceStringValue("i9form.data.firstname",user1.getLocale())%></td>
						<td width="15%"><%=Constant.getResourceStringValue("i9form.data.middlename",user1.getLocale())%></td>
						<td><%=Constant.getResourceStringValue("i9form.data.maidenname",user1.getLocale())%></td>
					</tr>
					<tr>
						<td><html:text property="lastName" size="35" maxlength="200"/></td>
						<td><html:text property="firstName" size="20" maxlength="200"/></td>
						<td><html:text property="middleName" size="10" maxlength="200"/></td>
						<td><html:text property="maidenName" size="20" maxlength="200"/></td>
						<td><input type="hidden" name="applicantid" value="<%=applicantId%>"></input>
						<input type="hidden" name="i9formid" value="<%=iform.getI9formid()%>"></input>
						</td>
					</tr>
		
				</table>

		<hr style="border: 1; color: #000000; background-color: #000000;height: 2px; width: 100%; text-align: center;">
			
				<table width="100%">
					<tr>
						<td width="78%"><%=Constant.getResourceStringValue("i9form.data.address",user1.getLocale())%> <i><%=Constant.getResourceStringValue("i9form.data.address2",user1.getLocale())%> </i>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="inUS" <%=(! StringUtils.isNullOrEmpty( iform.getInUS()) && iform.getInUS().equals("Y"))?"Checked":"" %> value="Y"><%=Constant.getResourceStringValue("i9form.data.US",user1.getLocale())%>&nbsp;<input type="radio" <%=(! StringUtils.isNullOrEmpty( iform.getInUS()) && iform.getInUS().equals("N"))?"Checked":"" %> name="inUS"  value="N"><%=Constant.getResourceStringValue("i9form.data.international",user1.getLocale())%>
						</td>
						<td width="25%">Apt.#</td>
						<td><%=Constant.getResourceStringValue("i9form.data.dob",user1.getLocale())%> <i><%=Constant.getResourceStringValue("i9form.data.dtformat",user1.getLocale())%></i></td>
					</tr>
					<tr>
						<td><html:text property="address" size="73" maxlength="200"/></td>
						<td><html:text property="apartmentno" size="10" maxlength="200" /></td>
						<td><html:text property="dateofbirth" size="20" maxlength="200"/></td>
					</tr>
				</table>
	
		<hr style="border: 1; color: #000000; background-color: #000000;height: 2px; width: 100%; text-align: center;">
			<table width="100%">
				<tr>
					<td width="40%"><%=Constant.getResourceStringValue("i9form.data.city",user1.getLocale())%></td>
					<td width="25%"><%=Constant.getResourceStringValue("i9form.data.state",user1.getLocale())%></td>
					<td width="15%"><%=Constant.getResourceStringValue("i9form.data.zip",user1.getLocale())%></td>
					<td><%=Constant.getResourceStringValue("i9form.data.socialsecNo",user1.getLocale())%></td>
				</tr>
				<tr>
					<td><html:text property="city" size="35" maxlength="200"/></td>
					<td><html:text property="state" size="20" maxlength="200"/></td>
					<td><html:text property="zip" size="10" maxlength="200"/></td>
					<td><html:text property="socialsecurityno" size="20" maxlength="200" /></td>
				</tr>
	
			</table>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 2px; width: 100%; text-align: center;">
			<table width="100%">
				<tr>
					<td align="left" width="30%" valign="top" >
					<p align="justify">
					I am aware that federal law provides 
					for imprisonment and/or fines for false statements or use of 
					false documents in connection with the completion of this form.
					</p>
					</td>	
					<td>
					<p align="justify">&nbsp;&nbsp;&nbsp;&nbsp;
						<%=Constant.getResourceStringValue("i9form.data.attest",user1.getLocale())%>
						<br /><br />&nbsp;&nbsp;
						<input type="radio" name="employeeattest" onClick="hideShowtextBox()" <%=(! StringUtils.isNullOrEmpty( iform.getEmployeeattest()) && iform.getEmployeeattest().equals("1"))?"Checked":"" %> value="1">&nbsp;<%=Constant.getResourceStringValue("i9form.data.citizeofUS",user1.getLocale())%>
						
						<br /><br />&nbsp;&nbsp;
						<input type="radio" name="employeeattest" onClick="hideShowtextBox()" <%=(! StringUtils.isNullOrEmpty( iform.getEmployeeattest()) && iform.getEmployeeattest().equals("2"))?"Checked":"" %> value="2">&nbsp;<%=Constant.getResourceStringValue("i9form.data.noncitizeofUS",user1.getLocale())%>
						
						<br /><br />&nbsp;&nbsp;
						<input type="radio" name="employeeattest" onClick="hideShowtextBox()" <%=(! StringUtils.isNullOrEmpty( iform.getEmployeeattest()) && iform.getEmployeeattest().equals("3"))?"Checked":"" %> value="3">&nbsp;<%=Constant.getResourceStringValue("i9form.data.lawful",user1.getLocale())%> 	   &nbsp;&nbsp;<%=Constant.getResourceStringValue("i9form.data.alienA",user1.getLocale())%> &nbsp;&nbsp;<html:text property="alienno" size="10" maxlength="200" />
						
						<br /><br />&nbsp;&nbsp;
						<input type="radio" name="employeeattest" onClick="hideShowtextBox()" <%=(! StringUtils.isNullOrEmpty( iform.getEmployeeattest()) && iform.getEmployeeattest().equals("4"))?"Checked":"" %> value="4">&nbsp;<%=Constant.getResourceStringValue("i9form.data.alienauthorizedtowork",user1.getLocale())%> &nbsp;&nbsp;
						
						<%=Constant.getResourceStringValue("i9form.data.alienA",user1.getLocale())%> &nbsp;&nbsp;<html:text property="alienno2" size="10" maxlength="200" />&nbsp;&nbsp;
						<%=Constant.getResourceStringValue("i9form.data.i94",user1.getLocale())%>&nbsp;&nbsp;<html:text property="i94no" size="10" maxlength="200" />						
						<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <%=Constant.getResourceStringValue("i9form.data.expiration",user1.getLocale())%>&nbsp;&nbsp;<html:text property="dateofexpiration" size="20" maxlength="200"/>
						<br />&nbsp;&nbsp;
						<input type="checkbox" name="employeetype"  <%=(iform.getEmployeetype() != null && iform.getEmployeetype().equals("Y")) ? "Checked":""%>/>&nbsp;<%=Constant.getResourceStringValue("i9form.data.refugee",user1.getLocale())%>
					</p>
					
					</td>			
				
				</tr>
			</table>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 5px; width: 100%; text-align: center;">
		
		<table width="70%">
				<tr>
					<td align="justify">
						<p>
						Preparer and/or Translator Certification.
						To be completed and signed if Section 1 is prepared by a person other than the employee 
						</p>	
					</td>
					
				</tr>
		</table>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 2px; width: 70%; text-align: center;">
		
		<table width="70%">
				<tr>
					<td align="justify"><%=Constant.getResourceStringValue("i9form.data.preparator",user1.getLocale())%></td>
				</tr>
				<tr>
					<td><html:text property="translatorname" size="73" maxlength="200"/></td>
				</tr>
		</table>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 2px; width: 70%; text-align: center;">
		
			<table width="70%">
				<tr>
					<td width="78%"><%=Constant.getResourceStringValue("i9form.data.address",user1.getLocale())%> <i><%=Constant.getResourceStringValue("i9form.data.address2",user1.getLocale())%></i>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="translatorcountry" Checked="checked" value="Y">U.S.&nbsp;<input type="radio" name="translatorcountry"  value="N">International
						</td>
				</tr>
				<tr>
					<td><html:text property="translatoraddress1" size="73" maxlength="200"/></td>
				</tr>
				<tr>
					<td><html:text property="translatoraddress2" size="73" maxlength="200"/></td>
				</tr>
		</table>
		<hr style="border: 1; color: #000000; background-color: #000000;height: 2px; width: 70%; text-align: center;">
			<table width="70%">
				<tr>
					<td width="40%"><%=Constant.getResourceStringValue("i9form.data.city",user1.getLocale())%></td>
					<td width="25%"><%=Constant.getResourceStringValue("i9form.data.state",user1.getLocale())%></td>
					<td width="15%"><%=Constant.getResourceStringValue("i9form.data.zip",user1.getLocale())%></td>
				</tr>
				<tr>
					<td><html:text property="translatorcity" size="35" maxlength="200"/></td>
					<td><html:text property="translatorstate" size="20" maxlength="200"/></td>
					<td><html:text property="translatorzip" size="10" maxlength="200"/></td>
				
				</tr>
	
			</table>
		
		
		<hr style="border: 1; color: #000000; background-color: #000000;height: 5px; width: 100%; text-align: center;">
		<table>
		<tr>
			
			<td>
				<%if(! StringUtils.isNullOrEmpty(showSaveBtn) && showSaveBtn.equals("yes")){ %>
				<input class="button" type="button" name="login" value="Save" onClick="savedata()">
				<%}else if(! StringUtils.isNullOrEmpty(showSaveBtn) && showSaveBtn.equals("no")){ %>	
				<input class="button" type="button" name="login" value="Update" onClick="updatedata()">
				<%} %>
			</td>
			<td><input class="button" type="button" name="login" value="Cancel" onClick="discard()"></td>
		</tr>
		</table>
	</fieldset>
</center>
</html:form>


</body>


</html>