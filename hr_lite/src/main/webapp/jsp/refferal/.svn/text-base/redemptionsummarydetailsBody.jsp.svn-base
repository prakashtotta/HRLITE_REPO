<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
 String datepattern = Constant.getValue("defaultdateformat");

%>
<bean:define id="form" name="referralRedemptionSummaryForm" type="com.form.ReferralRedemptionSummaryForm" />
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
ReferralRedemption referralredmption = form.getReferralRedemption();

%>
<div class="div">

 <table width="1000">
<tr>

    <td colSpan="2" vAlign="center">
      <p>
      <table border="0" cellPadding="1" cellSpacing="1" style="WIDTH: 100%" width="100%">
        
        <tr>
          <td bgColor="lightgoldenrodyellow" width="20%"><font face="Arial Narrow"><b> </b></font></td>
          <td align="middle" bgColor="lightgoldenrodyellow" width="80%"><b>Redemption details</b></td></tr>
        <tr>
        <td colspan="2"> <p> </td>
        </tr>
        
        <font face="Arial Narrow" color="brown">
        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Applicant name</td>
          <td aligh="left"  width="80%"> <a href="refops.do?method=applicantDetails&applicantId=<%=referralredmption.getApplicantId()%>&secureid=<%=form.getApplicantUuid()%>"><%=referralredmption.getApplicantName()%></a></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Job title</td>
          <td aligh="left"  width="80%">  <%=referralredmption.getJobTitle()%></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Referral scheme name</td>
          <td aligh="left"  width="80%">  <%=referralredmption.getRefferalSchemeName()%></td>
        </tr>
		<tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Redemption</td>
          <td aligh="left"  width="80%">   <%=referralredmption.getRefferalSchemeAmount()%> <%=referralredmption.getUom()%></td>
        </tr>
        <tr>
          <td align="left"  valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Reward type</td>
          <td aligh="left"  width="80%">  <%=referralredmption.getRefferalSchemeTypeName()%></td>
        </tr>

        <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Rule</td>
          <td aligh="left"  width="80%"><%=referralredmption.getRuleName()%></td>
        </tr>

       <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Event</td>
          <td aligh="left"  width="80%"><%=referralredmption.getApplicantstate()%></td>
        </tr>

	     <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Event date</td>
          <td aligh="left"  width="80%"><%=DateUtil.convertDateToStringDate(referralredmption.getEventdate(),datepattern)%></td>
        </tr>


	     <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Status</td>
          <td aligh="left"  width="80%"><%=referralredmption.getState()%></td>
        </tr>
<% if(referralredmption.getState() != null && referralredmption.getState().equals(Common.REFERRAL_REDEMPTION_RELEASED)){%>
	    <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Released date</td>
          <td aligh="left"  width="80%"><%=DateUtil.convertDateToStringDate(referralredmption.getReleaseddate(),datepattern)%></td>
        </tr>
<%}%>

	     <tr>
          <td align="left" valign="top" width="20%"><img SRC="jsp/images/square.gif" WIDTH="9" HEIGHT="9">&nbsp;&nbsp;Is paid</td>
          <td aligh="left"  width="80%"><%=referralredmption.getIsPaid()%></td>
        </tr>

 
  <td colspan="2"> </td>
  </tr>
   </table>
</td></tr>
</table>

</div>
		
	
</body>
</html>
