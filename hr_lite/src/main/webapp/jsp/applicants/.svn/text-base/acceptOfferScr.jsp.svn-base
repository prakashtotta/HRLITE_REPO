<%@ include file="../common/include.jsp" %>
<%
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
JobApplicant applicant = user1.getApplicant();


%>

 
 <HEAD>

<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   window.top.hidePopWin();
	   } 
	}


function closewindow(){
 window.top.hidePopWin();	  
	}
      

 function updatedata(){
	var alertstr="";
	var showalert=false;
	var comment = document.applicantForm.offeracceptedcomment.value;

    if(comment == "" || comment == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	//String offerrejecturl = "applicantuserops.do?method=declineoffer&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
	document.applicantForm.action = "applicantuserops.do?method=acceptoffer&applicantId=<%= applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>";
	document.applicantForm.submit();


	//   window.top.hidePopWin();
	
	

	}




</script>
	<body class="yui-skin-sam">
<html:form action="/applicantuserops.do?method=acceptoffer">
<%
String acceptoffer = (String)request.getAttribute("acceptoffer");
	
if(acceptoffer != null && acceptoffer.equals("yes")){
%>
<script language="javascript">
window.top.location.reload();
window.top.hidePopWin();
</script>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.offer_accepted",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}else {%>



<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	


		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
			<td><html:textarea property="offeracceptedcomment" cols="50" rows="4" /></td>
		</tr>




	</table>

</div>
	<table>
			<tr>
			<td>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant.send_for_review",user1.getLocale())%>" onClick="updatedata()">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"></td>
			<td></td>
		</tr>


	</table>


<%}%>
</html:form>
</body>
