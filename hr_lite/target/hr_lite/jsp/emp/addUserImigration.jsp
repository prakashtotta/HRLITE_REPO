<%@ include file="../common/yahooincludes.jsp" %>
 <%@ include file="../common/include.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< add Immigration >>");
%>

<bean:define id="cform" name="userImigrationsForm" type="com.form.employee.UserImigrationsForm" />


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}
span1{color:#ff0000;}
</style>

<STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
</STYLE>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<script language="javascript">

function updatedata(doctype){
	var alertstr="";
	var showalert=false;
	
	var passportno=document.userImigrationsForm.passportNo.value.trim();
	
    if(( doctype[0].checked == false ) && ( doctype[1].checked == false ) ){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Imigration.passportVisaType.Required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(passportno == "" || passportno == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Imigration.passportNo.Required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userImigrationsForm.action = "userimmigration.do?method=updateUserImmigration";
	  document.userImigrationsForm.submit();



}


function savedata(doctype){
	var alertstr="";
	var showalert=false;
	
	var passportno=document.userImigrationsForm.passportNo.value.trim();
	
    if(( doctype[0].checked == false ) && ( doctype[1].checked == false ) ){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Imigration.passportVisaType.Required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(passportno == "" || passportno == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Imigration.passportNo.Required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userImigrationsForm.action = "userimmigration.do?method=saveUserImmigration";
	  document.userImigrationsForm.submit();


}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
	  document.userImigrationsForm.action = "userimmigration.do?method=deleteUserImmigration";
	  document.userImigrationsForm.submit();
 	}
	
}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	   parent.parent.GB_hide(); 
	   } 
	}
function closewindow2(){
	parent.parent.GB_hide();
	 // document.userEmergencyContactForm.action = "useremergencycontact.do?method=emergencyContactdetails";
	 // document.userEmergencyContactForm.submit();
	 
}
</script>
<%
String userImmigrationUpdated = (String)request.getAttribute("userImmigrationUpdated");
	
if(userImmigrationUpdated != null && userImmigrationUpdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Imigration.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String userImmigrationSaved = (String)request.getAttribute("userImmigrationSaved");
	
if(userImmigrationSaved != null && userImmigrationSaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Imigration.saved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String userImmigrationDeleted = (String)request.getAttribute("userImmigrationDeleted");
	
if(userImmigrationDeleted != null && userImmigrationDeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Imigration.deleted",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}else{
%>

<html:form action="/userimmigration.do?method=saveUserimmigration" enctype="multipart/form-data">
<table border="0" width="100%">


	 	<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Imigration.Document",user1.getLocale())%><span1>*</span1></td>
			<td>
			<input type="radio" name="passportVisaType" id="1" <%=(cform.getPassportVisaType()!= null && cform.getPassportVisaType().equals("Passport"))? "Checked=true" : "" %> value="Passport">&nbsp;<%=Constant.getResourceStringValue("hr.user.Imigration.Document.Passport",user1.getLocale())%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="passportVisaType" id="0" <%=(cform.getPassportVisaType()!= null &&cform.getPassportVisaType().equals("Visa"))? "Checked=true" : "" %> value="Visa">&nbsp;<%=Constant.getResourceStringValue("hr.user.Imigration.Document.Visa",user1.getLocale())%>
			<html:hidden property="userId" />
			<html:hidden property="imigrationId" />
			</td>
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Imigration.Document.Number",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="passportNo" size="35" maxlength="200"/></td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Imigration.Document.IssuedDate",user1.getLocale())%></td>
			
			<td>
				
				<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
				var cal1xx = new CalendarPopup("testdiv1");
				cal1xx.showNavigationDropdowns();
		
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
			
				<html:text property="passportIssueDate" readonly="true" size="25" maxlength="200"/>
				
				<A HREF="#" onClick="cal1xx.select(document.userImigrationsForm.passportIssueDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
				<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Imigration.Document.ExpiryDate",user1.getLocale())%></td>
			
			<td>
				
				<SCRIPT LANGUAGE="JavaScript" ID="jscal2xx">
				var cal2xx = new CalendarPopup("testdiv2");
				cal2xx.showNavigationDropdowns();
		
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
			
				<html:text property="passportExpiryDate" readonly="true" size="25" maxlength="200"/>
				
				<A HREF="#" onClick="cal2xx.select(document.userImigrationsForm.passportExpiryDate,'anchor2xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor2xx" ID="anchor2xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
				<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Imigration.Document.EligibleStutus",user1.getLocale())%></td>
			<td><html:text property="eligibleStatus" size="35" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Imigration.Document.IssuedBy",user1.getLocale())%></td>
						
			<td>
			<html:select  property="countryId">
			<option value=""><%=Constant.getResourceStringValue("hr.user.Imigration.select",user1.getLocale()) %></option>
			<bean:define name="userImigrationsForm" property="countryList" id="countryList" />
            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>

			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Imigration.Document.EligibleReviewDate",user1.getLocale())%></td>
			<td>
				
				<SCRIPT LANGUAGE="JavaScript" ID="jscal3xx">
				var cal3xx = new CalendarPopup("testdiv3");
				cal3xx.showNavigationDropdowns();
		
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
			
				<html:text property="eligibleReviewDate" readonly="true" size="25" maxlength="200"/>
				
				<A HREF="#" onClick="cal3xx.select(document.userImigrationsForm.eligibleReviewDate,'anchor3xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor3xx" ID="anchor3xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
				<DIV ID="testdiv3" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Imigration.Document.Comments",user1.getLocale())%></td>
			
			<td><html:textarea property="comment" cols="37" rows="5"/></td>
		</tr>
</table>
<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getImigrationId() != 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata(document.userImigrationsForm.passportVisaType)" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%
			}else{
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata(document.userImigrationsForm.passportVisaType)" class="button">
			<%
			}
			%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</td>
		</tr>
		</table>
</html:form>


<%
}
%>