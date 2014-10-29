	<%@ include file="../common/include.jsp" %>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="sform" name="applicantForm" type="com.form.ApplicantForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>
	
	<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


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
function closewindow(){
	  parent.parent.GB_hide();
}

function init(){
       
	  document.getElementById('progressbartable1').style.display = 'none';   
		}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   parent.parent.GB_hide();
		   } 
		}

	function exportdata(){

		var alertstr = "";
  // var targerofferdate = document.applicantForm.targerofferdate.value;
	//var interviewer = document.scheduleInterviewForm.reviewerid.value;
		document.getElementById('progressbartable1').style.display = 'inline'; 
			
		  document.applicantForm.action = "applicant.do?method=exportapplicants";
	   document.applicantForm.submit();
	   //window.top.hidePopWin();
	   
		}


function opensearchjobreq(){
  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=requisitionselectormuliple");
window.open(url,'SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');

}



</script>

<%
String exportdone = (String)request.getAttribute("exportdone");
	
if(exportdone != null && exportdone.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Export_app_task",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --> </td>
		</tr>
		
	</table>
</div>

<%}else {%>
	<body onLoad="init()" class="yui-skin-sam">
	<html:form action="/applicant.do?method=exportapplicants">

	<div align="center" class="div">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
	
	<span id='progressbartable1'>
<img src="jsp/images/indicator.gif" height="20"  width="40"/>
</span>
	<tr>
	<td width="30%"><%=Constant.getResourceStringValue("aquisition.applicant.Target_joining_date",user1.getLocale())%> </td>
	<td  width="70%">
	<select name="cri">
<option value="before"><%=Constant.getResourceStringValue("Requisition.brfore",user1.getLocale())%></option>
<option value="on"><%=Constant.getResourceStringValue("Requisition.on",user1.getLocale())%></option>
<option value="after"><%=Constant.getResourceStringValue("Requisition.after",user1.getLocale())%></option>
</select>
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv2");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	 <INPUT TYPE="text" NAME="targetjoiningdate" readonly="true" value="" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.targetjoiningdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.targetjoiningdate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx">
<img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
	</td>
	</tr>
	<tr>
	<td width="30%"> <%=Constant.getResourceStringValue("Requisition.jobreqs",user1.getLocale())%></td>
	<td  width="70%">
	<input type="hidden"  name="reqid1" value=""/>
	<input type="hidden" name="jobt1"  value=""/>
		<span id="requitionId"></span><a href="#" onClick="opensearchjobreq()"><img src="jsp/images/selector.gif" border="0"/></a>
	</td>
	</tr>
    <br>
	<tr>
	<td  width="30%"><%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%> </td>
	<td  width="70%">
	<html:select name="applicantForm"  property="interviewState">
			<bean:define name="applicantForm" property="interviewstateList" id="interviewstateList" />
             <option value="NoValue"><%=Constant.getResourceStringValue("aquisition.applicant.All",user1.getLocale())%></option>
            <html:options collection="interviewstateList" property="interviewstateCode"  labelProperty="interviewstateName"/>
			</html:select>

	</td>
	</tr>
	<tr>
	<td></td>
	<td>
<input type="checkbox" name="education"><%=Constant.getResourceStringValue("aquisition.applicant.Include_education_details",user1.getLocale())%><br>
<input type="checkbox" name="certification"><%=Constant.getResourceStringValue("aquisition.applicant.Include_certification_details",user1.getLocale())%><br>
<input type="checkbox" name="experience"><%=Constant.getResourceStringValue("aquisition.applicant.Include_experience_details",user1.getLocale())%><br>

	</td>
	</tr>

	<tr>
	<td> <input type="button" name="export" value="<%=Constant.getResourceStringValue("hr.button.export",user1.getLocale())%>" onclick="exportdata()"/><input type="button" name="cancel" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onclick="discard()" class="button"/></td>
	<td></td>
	</tr>
	
</table>
</div>
</html:form>
</body>
<%}%>