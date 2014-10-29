
<%@ include file="../common/include.jsp" %>
<%@ page import="com.dao.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%

String markforpaid = (String)request.getAttribute("markforpaid");
String refredids = (String)request.getAttribute("refredids");
//String delurl = "applicant.do?method=markfordeletionbulksubmit&applicantids="+applicantids;	
String delurl = "referralredemptionsummary.do?method=markpaidforapplicantsSubmit&refredids="+refredids;

%>

<script language="javascript">

function closewindow(){
	//self.parent.location.reload();
	 	  //  window.top.hidePopWin();
	   parent.parent.GB_hide();
	}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   //window.top.hidePopWin();
		    parent.parent.GB_hide();
		   } 
		}

	function savedata(){


		  document.applicantForm.action = "<%=delurl%>";
	   	  document.applicantForm.submit();
	
	   
		}
</script>
<%	
if(markforpaid != null && markforpaid.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.mark.paid.msg",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>


<form name="applicantForm" method="POST" action="applicant.do">
<br>
<%
List redList = new ArrayList();
System.out.println("refferal redumption list : "+redList.size());
%>
<font color="red"><b><%=Constant.getResourceStringValue("hr.Redemption.Refferal_Redemption.Note",user1.getLocale())%></b></font>
<br><br>
<div class="div">
<table>



	
		<%	
		if(!StringUtils.isNullOrEmpty(refredids)){
			refredids = refredids.substring(0,refredids.length()-1);
			
			redList = StringUtils.tokenizeString(refredids, ",");
			
		}
		
			int count=0;
			for(int i=0;i<redList.size();i++){
				String refId = (String)redList.get(i);
				ReferralRedemption refredumption = RefferalDAO.getReferralRedmptionDetails(refId);
			String str="";
			count++;
			String bgcolor = "";
			if(i%2 == 0)bgcolor ="#B2D2FF";
		%>
			<%if(i == 0){ 
				if(refredumption.getState().equals(Common.REFERRAL_REDEMPTION_RELEASED)){
			%>
				<tr>
		
					<td width="174px"><b><%=Constant.getResourceStringValue("aquisition.applicant.APPLICANT_NAME",user1.getLocale())%></b></td>
					<td width="140px"><b><%=Constant.getResourceStringValue("hr.Redemption.Bonus_state",user1.getLocale())%></b></td>
				</tr>
			<%} 
			}%>
			<tr bgcolor=<%=bgcolor%>>
			<%
			if(refredumption.getState().equals(Common.REFERRAL_REDEMPTION_RELEASED)){%>
			<td width="174px" wrap><%=refredumption.getApplicantName() %></td>
			<td width="140px"><%=refredumption.getState()%></td>
			<%
				
			}else{ 
				count=0;
			}%>
			
			</tr>
		<%}%>
	
	<tr>
	<td>
	<textarea rows="4" cols="50" name="comment"></textarea> 
	</td>
	</tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>
		<td>
			<%if(count != 0 ){ %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
			<%} %>
			<!--<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">-->
		</td>
	</tr>

</table>
	</div>
</form>
<%}%>