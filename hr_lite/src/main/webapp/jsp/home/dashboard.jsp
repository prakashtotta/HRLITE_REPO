<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>

	

<%
User user2 = (User)request.getSession().getAttribute(Common.USER_DATA);
List dashboardReportList = ReportBO.getAllDashBoardReportByUserId(user2.getUserId());
int totalreportsize = dashboardReportList.size();

System.out.println("totalreportsize"+totalreportsize);

%>
<span id="reportdata">
<table>
<tr>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 0){%>

<%

DashBoardReport dash = (DashBoardReport)dashboardReportList.get(0);
ReportNames reportname = dash.getReport();

%>
<%if(reportname.getReportKey()!= null && reportname.getReportKey().equals("REQ_OPENING_STAT")){
%>


<fieldset><legend><%=reportname.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/requistion_opening_stat_dashboard.jsp?reportfilename=requistion_opening_stat_subreport3.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="requistion_opening_stat_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>
<%}%>

<%if(reportname.getReportKey()!= null && reportname.getReportKey().equals("DECLINED_OFFER")){
	
%>


<fieldset><legend><%=reportname.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/declined_offer_dashboard.jsp?reportfilename=Decline_Offer_dashboard.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="declined_offer_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>


<%}%>

<%if(reportname.getReportKey()!= null && reportname.getReportKey().equals("OFFER_APP_RATIO")){
%>


<fieldset><legend><%=reportname.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/applicant_offer_ratio_dashboard.jsp?reportfilename=Decline_Offer_dashboard.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="applicant_offer_ratio_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>


<%}%>


<%}//end of size check%>
</td>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 1){%>


<%

DashBoardReport dash1 = (DashBoardReport)dashboardReportList.get(1);
ReportNames reportname1 = dash1.getReport();
%>

<%if(reportname1.getReportKey()!= null && reportname1.getReportKey().equals("REQ_OPENING_STAT")){
%>


<fieldset><legend><%=reportname1.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/requistion_opening_stat_dashboard.jsp?reportfilename=requistion_opening_stat_subreport3.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="requistion_opening_stat_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname1.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>
<%}%>


<%if(reportname1.getReportKey()!= null && reportname1.getReportKey().equals("DECLINED_OFFER")){
%>


<fieldset><legend><%=reportname1.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/declined_offer_dashboard.jsp?reportfilename=Decline_Offer_dashboard.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="declined_offer_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname1.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>


<%}%>


<%if(reportname1.getReportKey()!= null && reportname1.getReportKey().equals("OFFER_APP_RATIO")){
%>


<fieldset><legend><%=reportname1.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/applicant_offer_ratio_dashboard.jsp?reportfilename=Decline_Offer_dashboard.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="applicant_offer_ratio_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname1.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>


<%}%>



<%}//end of size check%>

</td>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 2){%>



<%

DashBoardReport dash2 = (DashBoardReport)dashboardReportList.get(2);
ReportNames reportname2 = dash2.getReport();
%>
<%if(reportname2.getReportKey()!= null && reportname2.getReportKey().equals("REQ_OPENING_STAT")){
%>


<fieldset><legend><%=reportname2.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/requistion_opening_stat_dashboard.jsp?reportfilename=requistion_opening_stat_subreport3.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="requistion_opening_stat_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname2.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>
<%}%>

<%if(reportname2.getReportKey()!= null && reportname2.getReportKey().equals("DECLINED_OFFER")){
	
%>


<fieldset><legend><%=reportname2.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/declined_offer_dashboard.jsp?reportfilename=Decline_Offer_dashboard.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="declined_offer_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname2.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>


<%}%>

<%if(reportname2.getReportKey()!= null && reportname2.getReportKey().equals("OFFER_APP_RATIO")){
%>


<fieldset><legend><%=reportname2.getReportName()%></legend>
<%
String reqopenstsurl = request.getContextPath()+"/jsp/home/applicant_offer_ratio_dashboard.jsp?reportfilename=AppOfferRatio_OrgWise_Dashboard.jasper&parentOrgId="+user2.getOrganization().getOrgId()+"&parentOrgName="+user2.getOrganization().getOrgName()+"&reload=true";
%>


<jsp:include page="applicant_offer_ratio_dashboard.jsp">
    <jsp:param name="reportfilename" value="<%=reportname2.getReportFileName()%>" />
    <jsp:param name="parentOrgId" value="<%=user2.getOrganization().getOrgId()%>" />
	 <jsp:param name="parentOrgName" value="<%=user2.getOrganization().getOrgName()%>" />
	  <jsp:param name="url" value="<%=reqopenstsurl%>" />
</jsp:include>

</fieldset>


<%}%>

<%}//end of size check%>

</td>
</tr>
<tr>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 3){%>

<%}%>
</td>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 4){%>

<%}%>

</td>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 5){%>

<%}%>

</td>
</tr>
<tr>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 6){%>

<%}%>
</td>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 7){%>

<%}%>

</td>
<td valign="top" width="33%" height="300">
<% if(totalreportsize > 8){%>

<%}%>

</td>
</tr>

</table>
</span>
