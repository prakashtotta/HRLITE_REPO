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
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
	
	<br>


	
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

	function opensearchwatchlist(){
  window.open("user.do?method=watchlistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   window.top.hidePopWin();
		   } 
		}

	function savedata(){

		var alertstr = "";
   var resigneddate = document.applicantForm.resigneddate.value;
	
		
	var showalert=false;

	if(resigneddate == "" || resigneddate == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("applicant.resigned.datealert",user1.getLocale())%><br>";
		showalert = true;
		}

	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	 //  var doyou = confirm("Are you confirm ? offer will be sent to applicant (OK = Yes   Cancel = No)");
	   
		  document.applicantForm.action = "applicant.do?method=markresignedsave&applicantId=<%=sform.getApplicantId()%>";
	   document.applicantForm.submit();
		    
	   
		}





		





	</script>

<%
String markresigned = (String)request.getAttribute("markresigned");
	
if(markresigned != null && markresigned.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><%=Constant.getResourceStringValue("applicant.Round.Data_saved",user1.getLocale())%></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<br>
<%}else {%>

	<body class="yui-skin-sam">
	<html:form action="/applicant.do?method=markresignedsave">

	<div align="center" class="div">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
		
	<%
       
  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreq&jobreqId="+sform.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+sform.getJobTitle()+"</a>";
		%>	
<tr>
<td>
<%=Constant.getResourceStringValue("applicant.Round.Applicant",user1.getLocale())%> # <%=sform.getFullName()%>
</td>
<td>
<%=Constant.getResourceStringValue("applicant.Round.Requistion",user1.getLocale())%> # <%=sform.getRequisitionName()%>
			
</td>
</tr>
           <tr>

				<td><%=Constant.getResourceStringValue("aquisition.applicant.Resigned_date",user1.getLocale())%><font color="red">*</font></td>
      <td>
	  <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv2");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	 <INPUT TYPE="text" NAME="resigneddate" readonly="true" value="" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.resigneddate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.resigneddate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx">
<img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

</td>
</tr>


<tr>
			<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td> 
			<td><html:textarea property="resignedcomment" cols="50" rows="4"/></td>
		</tr>


<tr>
<td>

<input type="button" name="on board" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onclick="savedata()" class="button"/>

</td>
<td>

</td>
</table>

			
</html:form>

	<%}%>




	</body>

