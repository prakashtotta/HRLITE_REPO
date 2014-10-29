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
String ctcexeedssalaryplan=(String)request.getAttribute("ctcexeedssalaryplan");

%>
	
	<br>

 <style>
span1{color:#ff0000;}
</style>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
	<script language="javascript">
function closewindow(){
	  parent.parent.GB_hide();
}

	
	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   window.top.hidePopWin();
		   } 
		}

	function savedata(){

		var alertstr = "";
   var comment = document.applicantForm.comment.value;
	//var interviewer = document.scheduleInterviewForm.reviewerid.value;
		
	var showalert=false;

	if(comment == "" || comment == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.enter.comment.alert",user1.getLocale())%><br>";
		showalert = true;
		}

	
	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
		
		  document.applicantForm.action = "applicant.do?method=recallapplicant&applicantId=<%=sform.getApplicantId()%>&secureid=<%=sform.getUuid()%>";
	   document.applicantForm.submit();
	   //window.top.hidePopWin();
	   
		}




	</script>


	<body class="yui-skin-sam">
	
<%
String recalldone = (String)request.getAttribute("recalldone");
	
if(recalldone != null && recalldone.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.recall_successfully",user1.getLocale())%></font><!-- <a href="#" onClick="closewindow();return false;"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
			<td> </td>
		</tr>
		
	</table>
</div>

<%}else {%>


<html:form action="/applicant.do?method=recallapplicant">
	<div align="center" class="div">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%><span1>*</span1></td><br>
				<td><html:textarea property="comment" cols="60" rows="4" /></td>
			</tr>
			<tr>
				<td>
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
			
				
				 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</tr>
	</table>

	</html:form>

<%}%>
