<%@ include file="../common/include.jsp" %>
<%
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
JobApplicant applicant = user1.getApplicant();


%>

 
 <HEAD>

<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%
System.out.println("reason list size >> "+aform.getOfferdeclinedreasonslist().size());
List offerdeclinedresonlist = aform.getOfferdeclinedreasonslist();
%>
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
	var decresid = document.applicantForm.offerdeclinedresonid.value;

    if(decresid == "0" || decresid == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant_portal.reason_mandatory",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	//String offerrejecturl = "applicantuserops.do?method=declineoffer&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
	document.applicantForm.action = "applicantuserops.do?method=declineoffer&applicantId=<%= applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>";
	document.applicantForm.submit();
	//   window.top.hidePopWin();
	
	

	}




</script>
	<body class="yui-skin-sam">
<html:form action="/applicantuserops.do?method=declineoffer">
<%
String declineoffer = (String)request.getAttribute("declineoffer");
	
if(declineoffer != null && declineoffer.equals("yes")){
%>
<script language="javascript">
window.top.location.reload();
window.top.hidePopWin();

</script>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("home.offer_declined",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}else {%>



<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_decline",user1.getLocale())%><font color="red">*</font></td>
			<td>

			<select name="offerdeclinedresonid">
			<option value="0"><%=Constant.getResourceStringValue("aquisition.applicant_portal.Select_One",user1.getLocale())%></option>
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
			<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
			<td><html:textarea property="offerdeclinedcomment" cols="50" rows="4" /></td>
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
