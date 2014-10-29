<%@ page import="java.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.bo.*"%>
<%@ page import="com.dao.*"%>
<%@ page import="com.test.*"%>
<%@ page import="com.util.*"%>

<%
String catNo  = (String)session.getAttribute("catId");
int catnoid = Integer.parseInt(catNo);

MockQuestionSet mockset = BOFactory.getMockTestBO().getMockQuestionSet(catnoid);

int timeinsecond = mockset.getTimeLimit()*60;
String timeinmt = String.valueOf(mockset.getTimeLimit());
String examname = "";

String applicantName = (String)session.getAttribute("app_name");

Long testid  = (Long)session.getAttribute("testid");



%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=mockset.getDisplayName()%></title>
</head><body onload="display_c(<%=timeinsecond%>);">




<script type="text/javascript">
function display_c(start){
   window.start = parseFloat(start);
   var end = 0 // change this to stop the counter at a higher value
   var refresh=1000; // Refresh rate in milli seconds
   if(window.start >= end ){
     mytime=setTimeout('display_ct()',refresh)
   }
}

function display_ct() {
   // Calculate the number of days left
   var days=Math.floor(window.start / 86400);
   // After deducting the days calculate the number of hours left
   var hours = Math.floor((window.start - (days * 86400 ))/3600)
   // After days and hours , how many minutes are left
   var minutes = Math.floor((window.start - (days * 86400 ) - (hours * 3600 ))/60)
   // Finally how many seconds left after removing days, hours and minutes.
   var secs = Math.floor((window.start - (days * 86400 ) - (hours * 3600) - (minutes * 60)))

	
   if(window.start == 0) {
   	var x =  "    " + "Time Out"
	end();
   }
   else {
   	var x =  "    " + "Time left : " + hours + ":" + minutes + ":" + secs;
   }
	 
   document.getElementById('ct').innerHTML = x;
   window.start= window.start- 1;

   tt=display_c(window.start);

}


function end(){
		alert('Time out, Mentioned time for exam is over');
window.parent.location.href="<%=request.getContextPath()%>/jsp/testengine/pmpresult.jsp?testid=<%=testid.longValue()%>&cmd=result";				

}

</script>


<%
//PMPUser user = (PMPUser)session.getAttribute("user");
%>
<div style="width: 95%; height: 27px;">
  <div>
  <table width="100%" border="0" cellpadding="3" cellspacing="0" height="40px">
  <tr><td width="10%"></td>
  <td>
    <table width="90%" border="0" cellpadding="3" cellspacing="0" height="40px">
      <tbody><tr>
	  
        <td width="183" bgcolor="#000066"><font color="#ffffff" face="Arial, Helvetica, sans-serif" size="3">
           <%=applicantName%>
          </font></td>
        <td colspan="2" bgcolor="#000066"><div class="" align="center">

            <font color="#ffffff" face="Arial, Helvetica, sans-serif" size="3"><strong><%=mockset.getDisplayName()%></strong></font></div></td>
        <td style="font-weight: bold;" width="218" bgcolor="#000066"><font color="#ffffff" face="Arial, Helvetica, sans-serif" size="3"><span class="style3" id="ct">    Time left : <%=timeinmt%></span></font></td>
      </tr>
    </tbody></table>
	</td>
	</tr>
	</table>
  </div>

</div>
</body></html>
