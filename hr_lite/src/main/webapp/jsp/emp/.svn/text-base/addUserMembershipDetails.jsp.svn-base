<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
<%@ include file="../common/include.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< addUserMembershipDetails.jsp >>");
%>

<bean:define id="cform" name="userMemberShipForm" type="com.form.employee.UserMemberShipForm" />


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
	var numbers=/^[0-9]+$/;
	var membership= document.userMemberShipForm.membershipTypeId.value;
	var amount= document.userMemberShipForm.amount.value;
    if(membership == "" || membership == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.membership.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(amount == "" || amount == null){
     	

	}else{
	    if(numbers.test(amount)==false){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.membership.Subscription_amount_in_number",user1.getLocale())%><br>";
			showalert = true;
			}
	}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userMemberShipForm.action = "usermembership.do?method=updateUserMembershipDetails";
	  document.userMemberShipForm.submit();



}


function savedata(){
	var alertstr="";
	var showalert=false;
	var numbers=/^[0-9]+$/;
	var membership= document.userMemberShipForm.membershipTypeId.value;
	var amount= document.userMemberShipForm.amount.value;
    if(membership == "" || membership == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.membership.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(amount == "" || amount == null){
     	

	}else{
	    if(numbers.test(amount)==false){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.membership.Subscription_amount_in_number",user1.getLocale())%><br>";
			showalert = true;
			}
	}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userMemberShipForm.action = "usermembership.do?method=saveUserMembershipDetails";
	  document.userMemberShipForm.submit();


}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
		document.userMemberShipForm.action = "usermembership.do?method=deleteUserMembershipDetails";
		document.userMemberShipForm.submit();
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
String membershipDetailsUpdated = (String)request.getAttribute("membershipDetailsUpdated");
	
if(membershipDetailsUpdated != null && membershipDetailsUpdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.membership.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String membershipDetailsSaved = (String)request.getAttribute("membershipDetailsSaved");
	
if(membershipDetailsSaved != null && membershipDetailsSaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.membership.saved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String membershipDetailsdeleted = (String)request.getAttribute("membershipDetailsdeleted");
	
if(membershipDetailsdeleted != null && membershipDetailsdeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.membership.deleted",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}else{
%>
<body >
<html:form action="/usermembership.do?method=saveUserMembershipDetails" >
<font color = red ><html:errors /> </font>

<table border="0" width="100%">

		<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.membership",user1.getLocale())%><font color="red">*</font></td>
			
			<td>
			<html:select  property="membershipTypeId">
			<option value=""><%=Constant.getResourceStringValue("hr.user.select",user1.getLocale()) %></option>
			<bean:define name="userMemberShipForm" property="membershipTypesList" id="membershipTypesList" />
            <html:options collection="membershipTypesList" property="membershipTypeId"  labelProperty="membershipTypeName"/>
			</html:select>
				
				<html:hidden property="userId" />
				<html:hidden property="userMemberShipId" />
			</td>
			
		
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.membership.Subscription_paid_by",user1.getLocale())%></td>
			<td>	

			<html:select  property="subscriptionPaidBy">
			<bean:define name="userMemberShipForm" property="subscriptionPaidByList" id="subscriptionPaidByList" />
            <html:options collection="subscriptionPaidByList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.membership.currency",user1.getLocale())%></td>
				<td>
			<html:select  property="currencyId">
			<option value=""><%=Constant.getResourceStringValue("hr.user.select",user1.getLocale()) %></option>
			<bean:define name="userMemberShipForm" property="currencyCodeList" id="currencyCodeList" />

            <html:options collection="currencyCodeList" property="currencyId"  labelProperty="currencyName"/>
			</html:select>
			</td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.membership.Subscription_amount",user1.getLocale())%></td>
			<td><html:text property="amount" size="25" maxlength="100"/></td>
		
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.membership.Subscription_commence_date",user1.getLocale())%></td>
		<td>
				
				<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
				var cal1xx = new CalendarPopup("testdiv1");
				cal1xx.showNavigationDropdowns();
		
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
			
				<html:text property="subscriptionCommenceDate" readonly="true" size="25" maxlength="200"/>
				
				<A HREF="#" onClick="cal1xx.select(document.userMemberShipForm.subscriptionCommenceDate,'anchor1xx','<%=datepattern%>');return false" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
				<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.membership.Subscription_renewal_date",user1.getLocale())%></td>
			<td>
				
				<SCRIPT LANGUAGE="JavaScript" ID="jscal2xx">
				var cal2xx = new CalendarPopup("testdiv2");
				cal2xx.showNavigationDropdowns();
		
				</SCRIPT>
		<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
		
			
				<html:text property="subscriptionRenewalDate" readonly="true" size="25" maxlength="200"/>
				
				<A HREF="#" onClick="cal2xx.select(document.userMemberShipForm.subscriptionRenewalDate,'anchor2xx','<%=datepattern%>');return false" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor2xx" ID="anchor2xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
		
				<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			</td>
		
		</tr>






		

</table>


<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getUserMemberShipId() != 0){
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
