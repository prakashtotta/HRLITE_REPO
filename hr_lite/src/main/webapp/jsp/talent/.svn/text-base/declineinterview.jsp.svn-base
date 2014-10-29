	<%@ include file="../common/include.jsp" %>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="sform" name="scheduleInterviewForm" type="com.form.ScheduleInterviewForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>

	  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
int eventtype = sform.getEventType();
System.out.println("eventtype :  "+eventtype);
%>
	
	<br>


	<style type="text/css">
	#myAutoComplete {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}
	#myAutoComplete1 {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}

  
	</style>
<style>
span1{color:#ff0000;}
</style>
    
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
	<script language="javascript">

	function closewindow(){
		//self.parent.location.reload();
	 	    parent.parent.GB_hide();
	  
	}

	function opensearchassignedto(){
  window.open("user.do?method=assignedtoselector&boxnumber=intschreassign", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchwatchlist(){
  window.open("user.do?method=watchlistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		    parent.parent.GB_hide();
		   } 
		}

	function savedata(){

		var alertstr = "";
		var commentsa = document.scheduleInterviewForm.notes.value;
		
		var showalert=false;

		 if(commentsa == "" || commentsa == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%><br>";
			showalert = true;
			}
		 if (showalert){
	     	alert(alertstr);
	        return false;
	          }

		
			
			  document.scheduleInterviewForm.action = "scheduleInterview.do?method=declineinterviewsubmit&applicantId=<bean:write name="scheduleInterviewForm" property="applicantId"/>&eventtype=<%=eventtype%>";
		   document.scheduleInterviewForm.submit();
			
		// parent.parent.GB_hide();
	   
}

		
function opensearch(){
  window.open("evtmpl.do?method=selectevtemplates", "SearchEvaluationTemplate","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}



	</script>

<%
String declineinterview = (String)request.getAttribute("declineinterview");
	
if(declineinterview != null && declineinterview.equals("yes")){
%>
<div align="center" class="msg">
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.ReviewDeclinedAssign",user1.getLocale())%> <b><%=sform.getCurrentowner()%></b></font>
<!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></font>
</div>

<%}else {%>
<br>
<font color="red"> <%=Constant.getResourceStringValue("aquisition.applicant.Decline_review.note",user1.getLocale())%> </font>
	<body class="yui-skin-sam">
	<html:form action="/scheduleInterview.do?method=interviwreassignsubmit"  enctype="multipart/form-data">

	<div align="center" class="div">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
		
				
			
			
			<br>



			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%><font color="red">*</font></td>
				<td><html:textarea property="notes" cols="60" rows="4"/></td>
			</tr>


			<tr></tr><tr></tr>
			<tr>
				<td colspan="2">
				
				
				
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant.decline",user1.getLocale())%>" onClick="savedata()" class="button">
			
				 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</tr>

		</table>
	</div>

	</html:form>


	<%}%>





	</body>