<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = Constant.getValue("email.defaultdateformatwithourday");
if(user1 != null){
 datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>

 <bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
 <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>



<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
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

 </HEAD>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">

function discard(){
	  var doyou = confirm("Are you sure would you like to discard this changes ( OK = yes Cancel = No)");
	  if (doyou == true){
	    parent.parent.GB_hide();
	   } 
	}

function closewindow(){

//self.parent.location.reload();	

parent.parent.GB_hide();

	  
	}

      

 function updatedata(){
	
     	//alert(document.applicantForm.chagetargetjoiningdate.value);
	  document.applicantForm.action = "applicant.do?method=offermodifficationrequestOnBehalf&applicantId=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>";
	  document.applicantForm.submit();
	  

	}




</script>
	<body class="yui-skin-sam">
<html:form action="/applicant.do?method=offermodifficationrequestOnBehalf">
<%
String modificationrequestsaved = (String)request.getAttribute("modificationrequestsaved");
	
if(modificationrequestsaved != null && modificationrequestsaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("offer.modification.request.sendReview",user1.getLocale())%></font></td>
			<td><a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a></td>
		</tr>
		
	</table>
</div>

<%}else {%>



<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	 
	 	   <tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Target_joining_date",user1.getLocale())%></td>
			<td>
			
			<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

<INPUT TYPE="text" NAME="chagetargetjoiningdate" readonly="true" value="<%=(aform.getChagetargetjoiningdate()==null)?"":aform.getChagetargetjoiningdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.chagetargetjoiningdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.chagetargetjoiningdate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			
			</td>
		</tr>
<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant_portal.Offered_CTC",user1.getLocale())%></td>
			<td><html:text property="changeofferedctc" size="20"/>
&nbsp;&nbsp <bean:write name="applicantForm" property="offeredctccurrencycode"/>
			</td>
		</tr>


<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
				<td><html:textarea property="changeofferComment" cols="50" rows="4" /></td>
			</tr>


		<tr>
			<td>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("On.behalf.requests.sedforreview",user1.getLocale())%>" onClick="updatedata()" class="button">
			

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>

			<td></td>
		</tr>


	</table>
</div>



<%}%>
</html:form>
</body>
