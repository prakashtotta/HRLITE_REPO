<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/include.jsp" %>

<bean:define id="cform" name="userJobForm" type="com.form.employee.UserJobForm" />
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< user job details >>>");
%>



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
function savedata(){
	document.userJobForm.action="userjob.do?method=saveUserJobdata";
	document.userJobForm.submit();

	
}
function updatedata(){
	document.userJobForm.action="userjob.do?method=updateUserJobdata";
	document.userJobForm.submit();

	
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
String saveUserJobdataSaved = (String)request.getAttribute("saveUserJobdataSaved");
	
if(saveUserJobdataSaved != null && saveUserJobdataSaved.equals("Yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.jobdetails.saved",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<body>  
 <html:form action="/userjob.do?method=logon" enctype="multipart/form-data">      
            <table border="0" width="100%">
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.job_title",user1.getLocale())%></td>
            	 <td>
					<html:select  property="designationId">
					<option value=""></option>
					<bean:define name="userJobForm" property="designationList" id="designationList" />
		
		            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
					</html:select>
					<html:hidden property="userId" />
					<html:hidden property="jobdetailsId" />
				</td>
            </tr>
            <tr>
            <td><%=Constant.getResourceStringValue("hr.user.job_grade",user1.getLocale())%></td>
            <td>
            
            	<html:select  property="jobgradeId">
            	<option value=""></option>
			      <bean:define name="userJobForm" property="jobgradeList" id="jobgradeList" />
                  <html:options collection="jobgradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
			    </html:select>
            </td>
            </tr>
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.Employment_status",user1.getLocale())%></td>
            	   <td>
					<html:select  property="jobTypeId" >
					<option value=""></option>
					<bean:define name="userJobForm" property="jobtypeList" id="jobtypeList" />
		
		            <html:options collection="jobtypeList" property="jobTypeId"  labelProperty="jobTypeName"/>
					</html:select>
				</td>
            </tr>
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.Job_Category",user1.getLocale())%></td>
            	 <td>
					<html:select  property="jobCategoryId">
					<option value=""></option>
					<bean:define name="userJobForm" property="jobCategoryList" id="jobCategoryList" />
		
		            <html:options collection="jobCategoryList" property="jobCategoryId"  labelProperty="jobCategoryName"/>
					</html:select>
				</td>
            </tr>
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.Joined_date",user1.getLocale())%></td>
            	<td>
			
					<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
					var cal1xx = new CalendarPopup("testdiv1");
					cal1xx.showNavigationDropdowns();
			
					</SCRIPT>
			<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
			
				
					<html:text property="joinedDate" readonly="true" size="25" maxlength="200"/>
					
					<A HREF="#" onClick="cal1xx.select(document.userJobForm.joinedDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			
					<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
				</td>
            </tr>
			<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
         	
            <tr>
            	<td><font size="2"><b><%=Constant.getResourceStringValue("hr.user.Employment_Contract",user1.getLocale())%></b></font></td>
            </tr>
           
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.start_date",user1.getLocale())%></td>
            	
            	<td>
			
					<SCRIPT LANGUAGE="JavaScript" ID="jscal2xx">
					var cal2xx = new CalendarPopup("testdiv2");
					cal2xx.showNavigationDropdowns();
			
					</SCRIPT>
			<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
			
				
					<html:text property="contractStartDate" readonly="true" size="25" maxlength="200"/>
					
					<A HREF="#" onClick="cal2xx.select(document.userJobForm.contractStartDate,'anchor2xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor2xx" ID="anchor2xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			
					<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
				</td>
            </tr>
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.end_date",user1.getLocale())%></td>
            	<td>
			
					<SCRIPT LANGUAGE="JavaScript" ID="jscal3xx">
					var cal3xx = new CalendarPopup("testdiv3");
					cal3xx.showNavigationDropdowns();
			
					</SCRIPT>
			<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
			
				
					<html:text property="contractEndDate" readonly="true" size="25" maxlength="200"/>
					
					<A HREF="#" onClick="cal3xx.select(document.userJobForm.contractEndDate,'anchor3xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor3xx" ID="anchor3xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			
					<DIV ID="testdiv3" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
				</td>
            </tr>
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.contract_details",user1.getLocale())%></td>
            	<td><html:file property="contractdetailsData"/> </td>
            </tr>
            </table>
             <br>
            <table border="0" width="80%">
			<tr>
				<td>
				<%if(cform.getJobdetailsId() != 0){ %>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
				<%}else{ %>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
				<%}%>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
				</td>
			</tr>
		</table>
		</html:form>
</body>