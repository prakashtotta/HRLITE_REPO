<%--
/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
--%>


<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.dao.*" %>
<%@ page import="com.bean.*" %>
<%@ page import="com.common.*" %>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String timezoneoffset = user1.getTimezone().getTimezoneoffset();
%>
<%
	JasperPrint jasperPrint = null;
	String reportfilename = (String)request.getParameter("reportfilename");
		String requisitionId = (String)request.getParameter("reqid1");
		String requisitionName = (String)request.getParameter("jobt1");
	if (request.getParameter("reload") != null || jasperPrint == null)
	{
		
		String reportFileName = application.getRealPath("/reportsoutput/"+"/applicant_offered_joined_by_req/"+reportfilename);
		String filepath = application.getRealPath("/reportsoutput/") + "/applicant_offered_joined_by_req";
		String fileimagepath = application.getRealPath("/reportsoutput/");
		File reportFile = new File(reportFileName);
		//File reportFile = new File(reportFileName);
		FileInputStream io = new  FileInputStream(reportFile);

		//JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());

		Map parameters = new HashMap();
		//parameters.put("ReportTitle", "Address Report");
		//parameters.put("BaseDir", reportFile.getParentFile());
		parameters.put("Req_id", new Integer(requisitionId));
		parameters.put("Requisition_id", new Integer(requisitionId));
parameters.put("REQ_NAME", requisitionName);	
parameters.put("Company_Logo", "E:\\share satya on sdas\\Tomcat 5.0\\webapps\\hr\\jsp\\images\\logo.gif");
parameters.put("TIMEZONE_OFFSET", timezoneoffset);	
parameters.put("REPORT4_SUBREPORT1", filepath+"\\report4_subreport1.jasper");	
parameters.put("REPORT4_SUBREPORT2", filepath+"\\report4_subreport2.jasper");	
parameters.put("Image_1", fileimagepath+"\\images\\images1.jpeg");	
parameters.put("Image_2", fileimagepath+"\\images\\untitled1.JPG");	








Connection conn = HibernateUtil.createSQLConnection(); 
		jasperPrint = 
			JasperFillManager.fillReport(
				io, 
				parameters, 
				conn
				);
				
		session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
	}
	
	//JRHtmlExporter exporter = new JRHtmlExporter();
	JRXhtmlExporter exporter = new JRXhtmlExporter();
	
	int pageIndex = 0;
	int lastPageIndex = 0;
	if (jasperPrint.getPages() != null)
	{
		lastPageIndex = jasperPrint.getPages().size() - 1;
	}

	String pageStr = request.getParameter("page");
	try
	{
		pageIndex = Integer.parseInt(pageStr);
	}
	catch(Exception e)
	{
	}
	
	if (pageIndex < 0)
	{
		pageIndex = 0;
	}

	if (pageIndex > lastPageIndex)
	{
		pageIndex = lastPageIndex;
	}
	
	StringBuffer sbuffer = new StringBuffer();

	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../../reports/image?image=");
	exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.valueOf(pageIndex));
	exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
	exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
	exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");


	exporter.exportReport();
%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
  <style type="text/css">
    a {text-decoration: none}
  </style>
  <title>Total applicants vs offered</title>
</head>
<body text="#000000" link="#000000" alink="#000000" vlink="#000000">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td width="10%">&nbsp;</td>
  <td align="left">
    <hr size="1" color="#000000">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td><a href="applicant_offered_joined_by_org.jsp?reportfilename=<%=reportfilename%>&parentOrgId=<%=requisitionId%>&parentOrgName=<%=requisitionName%>&reload=true"><img src="../images/reload.GIF" border="0"></a></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
<%
	if (pageIndex > 0)
	{
%>
        <td><a href="applicant_offered_joined_by_org.jsp?reportfilename=<%=reportfilename%>&parentrequisitionId=<%=requisitionId%>&parentrequisitionName=<%=requisitionName%>&page=0"><img src="../images/first.GIF" border="0"></a></td>
        <td><a href="applicant_offered_joined_by_org.jsp?reportfilename=<%=reportfilename%>&parentrequisitionId=<%=requisitionId%>&parentrequisitionName=<%=requisitionName%>&page=<%=pageIndex - 1%>"><img src="../images/previous.GIF" border="0"></a></td>
<%
	}
	else
	{
%>
        <td><img src="../images/first_grey.GIF" border="0"></td>
        <td><img src="../images/previous_grey.GIF" border="0"></td>
<%
	}

	if (pageIndex < lastPageIndex)
	{
%>
        <td><a href="applicant_offered_joined_by_org.jsp?reportfilename=<%=reportfilename%>&parentrequisitionId=<%=requisitionId%>&parentrequisitionName=<%=requisitionName%>&page=<%=pageIndex + 1%>"><img src="../images/next.GIF" border="0"></a></td>
        <td><a href="applicant_offered_joined_by_org.jsp?reportfilename=<%=reportfilename%>&parentrequisitionId=<%=requisitionId%>&parentrequisitionName=<%=requisitionName%>&page=<%=lastPageIndex%>"><img src="../images/last.GIF" border="0"></a></td>
<%
	}
	else
	{
%>
        <td><img src="../images/next_grey.GIF" border="0"></td>
        <td><img src="../images/last_grey.GIF" border="0"></td>
<%
	}
%>
        <td width="100%">&nbsp;</td>
		
		<td width="100%">&nbsp;<a href="<%=request.getContextPath()%>/reports.do?method=firstpage"><img src="../images/home.gif" border="0" width="18" height="18"></a></td>
		<td width="100%">&nbsp;<a href="<%=request.getContextPath()%>/reports/xls?cmd=applicantofferorg&reportfilename=<%=reportfilename%>&parentrequisitionId=<%=requisitionId%>&parentrequisitionName=<%=requisitionName%>" target="new"><img src="../images/xls_icon.jpg" border="0" width="18" height="18"></a></td>
		<td width="100%">&nbsp;<a href="<%=request.getContextPath()%>/reports/pdf?cmd=applicantofferorg&reportfilename=<%=reportfilename%>&parentrequisitionId=<%=requisitionId%>&parentrequisitionName=<%=requisitionName%>" target="new"><img src="../images/PDF_icon.jpg" border="0" width="18" height="18"></a></td>
<td width="100%">&nbsp;<a href="javascript:window.print()"><img src="../images/print.gif" border="0"></a></td>
		

      </tr>
    </table>
    <hr size="1" color="#000000">
  </td>
  <td width="10%">&nbsp;</td>
</tr>
<tr>
  <td width="10%">&nbsp;</td>
  <td  align="center">

<%=sbuffer.toString()%>

  </td>
  <td width="10%">&nbsp;</td>
</tr>
</table>
</body>
</html>
