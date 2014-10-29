<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
 String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
%>
<bean:define id="form" name="agencyRedemptionSummaryForm" type="com.form.AgencyRedemptionSummaryForm" />
<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>




</head>




<body>

<%
AgencyRedemption agencyredmption = form.getAgencyredmption();
%>


 <table width="1000">
<tr>

    <td colSpan="2" vAlign="center">
      <p>
      <table border="0" cellPadding="1" cellSpacing="1" style="WIDTH: 100%" width="100%">
        
        <tr>
          <td bgColor="lightgoldenrodyellow" width="20%"><font face="Arial Narrow"><b> </b></font></td>
          <td align="middle" bgColor="lightgoldenrodyellow" width="80%"><b><%=Constant.getResourceStringValue("hr.Redemption.Redemption_details",user1.getLocale())%></b></td></tr>
        <tr>
        <td colspan="2"> <p> </td>
        </tr>
        
        <font face="Arial Narrow" color="brown">
        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%></td>
          <td aligh="left"  width="80%"> <a href="agencyops.do?method=applicantDetails&&applicantId=<%=agencyredmption.getApplicantId()%>&secureid=<%=form.getApplicantuuid()%>"><%=agencyredmption.getApplicantName()%></a></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>
          <td aligh="left"  width="80%">  <%=agencyredmption.getJobTitle()%></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%></td>
          <td aligh="left"  width="80%">  <%=agencyredmption.getRefferalSchemeName()%></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.Redemption.Redemption",user1.getLocale())%></td>
          <td aligh="left"  width="80%">   <%=agencyredmption.getRefferalSchemeAmount()%> <%=agencyredmption.getUom()%></td>
        </tr>
        <tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.RefferalSchemeType.reward.type",user1.getLocale())%></td>
          <td aligh="left"  width="80%">  <%=agencyredmption.getRefferalSchemeTypeName()%></td>
        </tr>

        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.RefferalScheme.rule",user1.getLocale())%></td>
          <td aligh="left"  width="80%"><%=agencyredmption.getRuleName()%></td>
        </tr>

       <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.Redemption.Event",user1.getLocale())%></td>
          <td aligh="left"  width="80%"><%=agencyredmption.getApplicantstate()%></td>
        </tr>

	     <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.Redemption.Event_Date",user1.getLocale())%></td>
          <td aligh="left"  width="80%"><%=DateUtil.convertDateToStringDate(agencyredmption.getEventdate(),datepattern)%></td>
        </tr>


	     <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%></td>
          <td aligh="left"  width="80%"><%=agencyredmption.getState()%></td>
        </tr>
<% if(agencyredmption.getState() != null && agencyredmption.getState().equals(Common.REFERRAL_REDEMPTION_RELEASED)){%>
	    <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.Redemption.Released_Date",user1.getLocale())%></td>
          <td aligh="left"  width="80%"><%=DateUtil.convertDateToStringDate(agencyredmption.getReleaseddate(),datepattern)%></td>
        </tr>
<%}%>

	     <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.Redemption.isPaid",user1.getLocale())%></td>
          <td aligh="left"  width="80%"><%=agencyredmption.getIsPaid()%></td>
        </tr>

 
  <td colspan="2"> </td>
  </tr>
   </table>
</td></tr>
</table>


		
	
</body>
</html>
