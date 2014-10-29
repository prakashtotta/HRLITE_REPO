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


	
		
		  document.applicantForm.action = "applicant.do?method=undodelete&applicantId=<%=sform.getApplicantId()%>&secureid=<%=sform.getUuid()%>";
	   document.applicantForm.submit();
	   //window.top.hidePopWin();
	   
		}




	</script>


	<body class="yui-skin-sam">
	
<%
String undodelete = (String)request.getAttribute("undodelete");
	
if(undodelete != null && undodelete.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">


			<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.undodelete_successfully",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}else {%>


<html:form action="/applicant.do?method=undodelete">
	<div align="center">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%><span1>*</span1></td>
				<td><html:textarea property="comment" cols="60" rows="8" /></td>
			</tr>
			<tr>
				<td>
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">
			
				
				 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"></td>
			</tr>
	</table>

	</html:form>

<%}%>
