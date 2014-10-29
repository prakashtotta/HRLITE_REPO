<%@ include file="../common/include.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userMemberShipForm" type="com.form.employee.UserMemberShipForm" />




  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println("<<<<  userMembershipDetails.jsp >>>>>");
List userMembershipList = cform.getUsermembershipList();
%>





<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}
</style>
<html:form action="/userreportto.do?method=logon" >

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.membership.assigned",user1.getLocale())%></legend>
				<%
String deleteUserMembershipMultiple = (String)request.getAttribute("deleteUserMembershipMultiple");
	
if(deleteUserMembershipMultiple != null && deleteUserMembershipMultiple.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.usermembership.multiple.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%
}
%>
		
		<%
		 String usermembershipids="";
		if(userMembershipList.size() > 0){
			for(int i = 0; i< userMembershipList.size(); i++){
				UserMemberShip userMemberShip = (UserMemberShip)userMembershipList.get(i);
				usermembershipids = usermembershipids+userMemberShip.getUserMemberShipId()+","; 
		%>
		
		<%}
		}%>
			<div align="left">	
			<a  class="button" href="#" onClick="deleteUserDetails('<%=usermembershipids%>','usermembership');return false"><%=Constant.getResourceStringValue("hr.user.Delete",user1.getLocale())%></a>
			&nbsp;&nbsp;
			<%
				String addUsermembershipurl = request.getContextPath()+"/usermembership.do?method=addUserMembershipDetails&userId="+cform.getUserId();		
			%>
			<a  class="button" href="#" onClick="javascript:addUserMembership('<%=addUsermembershipurl%>');return false"><%=Constant.getResourceStringValue("hr.user.Add",user1.getLocale())%></a>
            &nbsp;&nbsp;
            <a  class="button" href="#" onClick="javascript:retrieveData('usermembership');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
           </div>
            <br>
            

<table border="0" width="100%">


		<%
	
		if(userMembershipList.size() > 0){
			%>
		 	<tr bgcolor="#bebabe">

				<td align="center"><input type="checkbox" id="masterCheck" onClick="checkAll(this.form,'usermembership')" name="masterCheck"></td>
				<td width="25%">&nbsp;<b><%=Constant.getResourceStringValue("hr.user.membership",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.membership.Subscription_paid_by",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.membership.Subscription_amount",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.membership.currency",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.membership.Subscription_commence_date",user1.getLocale())%></b></td>
				<td>&nbsp;<b><%=Constant.getResourceStringValue("hr.user.membership.Subscription_renewal_date",user1.getLocale())%></b></td>
			</tr>
		<%
			for(int i = 0; i< userMembershipList.size(); i++){
				UserMemberShip userMemberShip = (UserMemberShip)userMembershipList.get(i);
			    String bgcolor = "";
				if(i%2 == 1)bgcolor ="#B2D2FF";
				
				String editUserSalaryURl = request.getContextPath()+"/usermembership.do?method=editUserMembershipDetails&userMemberShipId="+userMemberShip.getUserMemberShipId()+"&userId="+cform.getUserId();		
				
		%>
		

			<tr bgcolor=<%=bgcolor%>>

				<td align="center"><input type="checkbox" id="usermembershipIsActive_<%=userMemberShip.getUserMemberShipId()%>"  name="usermembershipIsActive"></td>
				<td width="20%">&nbsp;<a href="#" onClick="editUserMembershipDetail('<%=editUserSalaryURl%>');return false"><%=userMemberShip.getMembershiptype()  == null ?"": userMemberShip.getMembershiptype().getMembershipTypeName()%></a></td>
				<td>&nbsp;<%=userMemberShip.getSubscriptionPaidBy().equals("NoValue")?"":userMemberShip.getSubscriptionPaidBy() %></td>
				<td>&nbsp;<%=userMemberShip.getAmount() == null ?"":userMemberShip.getAmount() %></td>
				<td>&nbsp;<%=userMemberShip.getCurrency()  == null ?"":userMemberShip.getCurrency().getCurrencyName()%></td>
				<td>&nbsp;<%=userMemberShip.getSubscriptionCommenceDate() == null ?"":userMemberShip.getSubscriptionCommenceDate()%></td>
				<td>&nbsp;<%=userMemberShip.getSubscriptionRenewalDate() == null ?"":userMemberShip.getSubscriptionRenewalDate()%></td>
				
				
			</tr>
			
		<%
			}
		}else{
		
		
		%>
		<tr><td align="left"><%=Constant.getResourceStringValue("hr.user.Imigration.NoRecordsFound",user1.getLocale())%></td></tr>
		<%
		}%>


</table>

            </fieldset>
            </span>
   </html:form>