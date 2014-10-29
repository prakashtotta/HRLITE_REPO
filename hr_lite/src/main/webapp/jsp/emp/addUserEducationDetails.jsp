<%@ include file="../common/yahooincludes.jsp" %>
 <%@ include file="../common/include.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< add dependents >>");
%>
<bean:define id="cform" name="userEducationDetailsForm" type="com.form.employee.UserEducationDetailsForm" />


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
	var name=document.userEducationDetailsForm.educationName.value.trim();

	
    if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.Education.name.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userEducationDetailsForm.action="usereducationdetails.do?method=updateUserEducationDetails";
	  document.userEducationDetailsForm.submit();


}

function savedata(){


	var alertstr="";
	var showalert=false;
	var name=document.userEducationDetailsForm.educationName.value.trim();

	
    if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.Qualifications.Education.name.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userEducationDetailsForm.action="usereducationdetails.do?method=saveUserEducationDetails";
	  document.userEducationDetailsForm.submit();



}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
	  document.userEducationDetailsForm.action="usereducationdetails.do?method=deleteUserEducationDetails";
	  document.userEducationDetailsForm.submit();
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
String userEducationDetailsUpdated = (String)request.getAttribute("userEducationDetailsUpdated");
	
if(userEducationDetailsUpdated != null && userEducationDetailsUpdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String userEducationDetailsSaved = (String)request.getAttribute("userEducationDetailsSaved");
	
if(userEducationDetailsSaved != null && userEducationDetailsSaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.saved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String userEducationDetailsDeleted = (String)request.getAttribute("userEducationDetailsDeleted");
	
if(userEducationDetailsDeleted != null && userEducationDetailsDeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.deleted",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		</tr>
		
	</table>
</div>
<%
}else{
%>

<body >
<html:form action="/usereducationdetails.do?method=saveUserEducationDetails" enctype="multipart/form-data">
<font color = red ><html:errors /> </font>
<table border="0" width="100%">

	 	<tr>
			<td width="40%"><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:select  property="educationName">
				<option value=""></option>
				<bean:define name="userEducationDetailsForm" property="educationNamesList" id="educationNamesList" />
	
	            <html:options collection="educationNamesList" property="key"  labelProperty="value"/>
				</html:select>
		
			<html:hidden property="userId" />
			<html:hidden property="educationId" />
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.Specialization",user1.getLocale())%></td>
			<td><html:text property="specialization" size="35" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.College_University",user1.getLocale())%></td>
			<td><html:text property="instituteName" size="35" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.Percentage_Grade",user1.getLocale())%></td>
			<td><html:text property="percentile" size="35" maxlength="200"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.start_month_year",user1.getLocale())%></td>
			
			<td><html:text property="startingYear" size="35" maxlength="200"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Qualifications.Education.passing_month_year",user1.getLocale())%></td>
			<td><html:text property="passingYear" size="35" maxlength="200"/></td>

		</tr>






</table>
<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getEducationId() != 0){
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
