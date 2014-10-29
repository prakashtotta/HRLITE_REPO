<%@ include file="../common/include.jsp" %>
<%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

 <HEAD>

  <%
String datepattern = Constant.getValue("email.defaultdateformatwithourday");
%>

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


 </HEAD>



<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   parent.parent.GB_hide();
	   } 
	}



      

 function canceloffer(){
	
     	
	  document.applicantForm.action = "applicant.do?method=canceloffersubmit&applicantId=<%=aform.getApplicantId()%>";
	  document.applicantForm.submit();
	  // window.top.hidePopWin();
	
	

	}

function closewindow(){
	self.parent.location.reload();
	 	  parent.parent.GB_hide();
	  
	}	


</script>
	
<%
String offercancelled = (String)request.getAttribute("offercancelled");
	
if(offercancelled != null && offercancelled.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_cancelled",user1.getLocale())%></font></td>
			<td><a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>
<br>
<%}%>


<body class="yui-skin-sam">
<html:form action="/applicant.do?method=canceloffersubmit">
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	

<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Cancellation_reason",user1.getLocale())%> </td>
			<td><html:select  property="offercancelledresonid">
			<bean:define name="applicantForm" property="offerdeclinedreasonslist" id="offerdeclinedreasonslist" />

            <html:options collection="offerdeclinedreasonslist" property="offerdeclinedreasonId"  labelProperty="offerdecliedreasonName"/>
			</html:select>
			</td>
		</tr>


<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
				<td><html:textarea property="offercancelledcomment" cols="50" rows="5" /></td>
			</tr>

	<tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td colspan="2">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant.Cancel_offer",user1.getLocale())%>" onClick="canceloffer()" class="button">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>


	</table>
</div>




</html:form>
</body>
