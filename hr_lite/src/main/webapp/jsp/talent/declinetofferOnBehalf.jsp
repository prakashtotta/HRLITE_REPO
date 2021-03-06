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

		var alertstr="";
		var showalert=false;
		var comm = document.applicantForm.comment.value;
		var reason = document.applicantForm.offerdeclinedresonid.value;
		if (comm == "" ||comm == null ) {
			
			alertstr = alertstr +"<%=Constant.getResourceStringValue("aquisition.applicant_portal.comment_mandatory",user1.getLocale())%><BR>";
			showalert = true;
		}
	
			
			//alert("Please select reason for decline");
		if(reason == 0){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant_portal.reason_mandatory",user1.getLocale())%><BR>";
			showalert = true;
		}
		
		if (showalert){
	     	alert(alertstr);
	        return false;
	          }
	
	  document.applicantForm.action = "applicant.do?method=declineofferOnBehalf&applicantId=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>";
	  document.applicantForm.submit();
	  

	}




</script>


<%
List offerdeclinedresonlist = form.getOfferdeclinedreasonslist();
%>
	<body class="yui-skin-sam">
<html:form action="/applicant.do?method=declineofferOnBehalf">
<%
String declineoffer = (String)request.getAttribute("declineoffer");
	
if(declineoffer != null && declineoffer.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color ="white"><%=Constant.getResourceStringValue("Offer_Declined",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>

		</tr>
		
	</table>
</div>

<%}else {%>
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	

<tr>
				<td>
<%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_decline",user1.getLocale())%> <font color="red">*</font>:
</td>
<td>
<select name="offerdeclinedresonid">
<option value="0"><%=Constant.getResourceStringValue("aquisition.applicant.Select_One",user1.getLocale())%></option>
<% 
	for(int aa=0;aa<offerdeclinedresonlist.size();aa++)
	{
	DeclinedResons offd = (DeclinedResons)offerdeclinedresonlist.get(aa);

%>
  <option value="<%=offd.getOfferdeclinedreasonId()%>"><%=offd.getOfferdecliedreasonName()%></option>
  
 <%}%>

</select>
</td>
</tr>
<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%><font color="red">*</font></td>
				<td><TEXTAREA NAME="comment" COLS=40 ROWS=4></TEXTAREA></td>
			</tr>



	</table>
	


</div>
	<table>
		<tr>
			<td>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant.decline_offer",user1.getLocale())%>" onClick="updatedata()" class="button">
			

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>

			<td></td>
		</tr>


	</table>
<%}%>

</html:form>
</body>
