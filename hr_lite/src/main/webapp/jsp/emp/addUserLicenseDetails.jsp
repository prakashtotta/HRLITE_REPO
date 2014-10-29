<%@ include file="../common/include.jsp" %>

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< addUserLicenseDetails.jsp >>");
%>
<bean:define id="cform" name="userLicensesForm" type="com.form.employee.UserLicensesForm" />




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

function updatedata(){
	var alertstr="";
	var showalert=false;
	var licensetype=document.userLicensesForm.licenseTypeId.value.trim();

	
    if(licensetype == "" || licensetype == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Type.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userLicensesForm.action="userlicenses.do?method=updateUserLicenseDetails";
	  document.userLicensesForm.submit();


}

function savedata(){


	var alertstr="";
	var showalert=false;
	var licensetype=document.userLicensesForm.licenseTypeId.value.trim();

	
    if(licensetype == "" || licensetype == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Type.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userLicensesForm.action="userlicenses.do?method=saveUserLicenseDetails";
	  document.userLicensesForm.submit();



}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
	  document.userLicensesForm.action="userlicenses.do?method=deleteUserLicenseDetails";
	  document.userLicensesForm.submit();
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

	 
}
</script>
<%
String userLicenseDetailsUpdated = (String)request.getAttribute("userLicenseDetailsUpdated");
	
if(userLicenseDetailsUpdated != null && userLicenseDetailsUpdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String userLicenseDetailsSaved = (String)request.getAttribute("userLicenseDetailsSaved");
	
if(userLicenseDetailsSaved != null && userLicenseDetailsSaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Saved",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String userLicenseDetailsDeleted = (String)request.getAttribute("userLicenseDetailsDeleted");
	
if(userLicenseDetailsDeleted != null && userLicenseDetailsDeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
		<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Deleted",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		
		</tr>
		
	</table>
</div>
<%
}else{
%>

<body >

<html:form action="/userlicenses.do?method=saveUserLicenseDetails" >
<font color = red ><html:errors /> </font>
<br>
	<table border="0" width="100%">

	 	<tr>
			<td width="35%"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Type",user1.getLocale())%><font color="red">*</font></td>
			<td>
				<html:select  property="licenseTypeId">
			<option value=""><%=Constant.getResourceStringValue("hr.user.Imigration.select",user1.getLocale()) %></option>
			<bean:define name="userLicensesForm" property="licenseTypeList" id="licenseTypeList" />
            <html:options collection="licenseTypeList" property="licenseTypeId"  labelProperty="licenseTypeName"/>
			</html:select>
				

		
			<html:hidden property="userId" />
			<html:hidden property="userLicenseId" />
			</td>
		</tr>
		<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Number",user1.getLocale())%></td>
			<td><html:text property="licenseNumber"  size="38" maxlength="200"/></td>
		</tr>
		<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.License_Issuing_Authority",user1.getLocale())%></td>
			<td><html:text property="licenseIssueAuthority"  size="38" maxlength="200"/></td>
		</tr>
		<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Issued_Date",user1.getLocale())%></td>
		
			<td>
				
				<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
				var cal1xx = new CalendarPopup("testdiv1");
				cal1xx.showNavigationDropdowns();
		
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
			
				<html:text property="issueDate" readonly="true" size="25" maxlength="200"/>
				
				<A HREF="#" onClick="cal1xx.select(document.userLicensesForm.issueDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
				<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
		</tr>
		<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Expiry_Date",user1.getLocale())%></td>
		
			<td>
				
				<SCRIPT LANGUAGE="JavaScript" ID="jscal2xx">
				var cal2xx = new CalendarPopup("testdiv2");
				cal2xx.showNavigationDropdowns();
		
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
			
				<html:text property="expireDate" readonly="true" size="25" maxlength="200"/>
				
				<A HREF="#" onClick="cal2xx.select(document.userLicensesForm.expireDate,'anchor2xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor2xx" ID="anchor2xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
				<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
		</tr>
		<tr>
			<td width="25%"><%=Constant.getResourceStringValue("hr.user.Qualifications.License.Comment",user1.getLocale())%></td>
			<td><html:textarea property="comment" rows="5" cols="35"></html:textarea></td>
		</tr>
	</table>


<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getUserLicenseId()!= 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%
			}else{
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%
			}
			%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</td>
		</tr>
		</table>
</html:form>

</body>
<%
}
%>
