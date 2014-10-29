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
<%
  
 
 
  


  %>

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
<%@ page import="java.util.Calendar" %>
<%@ page import="com.util.*" %>
<%@ page import="com.resources.*" %>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String timezoneoffset = user1.getTimezone().getTimezoneoffset();
%>
<html>
<head><title><%=Constant.getResourceStringValue("home.Reports.report",user1.getLocale())%> <%=Constant.getResourceStringValue("home.Reports.Budget_tracking",user1.getLocale())%></title>
</head>
<%
	JasperPrint jasperPrint = null;
	String reportfilename = (String)request.getParameter("reportfilename");

	String budgetIds = (String)request.getParameter("budgetIds");
		String urllink = (String)request.getParameter("url");
		
		List budgetidsList = null;
		if(! StringUtils.isNullOrEmpty(budgetIds)){
			String [] budgetids=budgetIds.split(",");
			budgetidsList = Arrays.asList(budgetids);
		}
	
		
		String reportFileName = application.getRealPath("/reportsoutput/"+reportfilename);
		String filepath = application.getRealPath("/reportsoutput/");
		File reportFile = new File(reportFileName);
		//File reportFile = new File(reportFileName);
		FileInputStream io = new  FileInputStream(reportFile);

		//JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());

		Map parameters = new HashMap();
		parameters.put("ReportTitle", "Budget Tracking");

		parameters.put("Budget_id", budgetidsList);
		parameters.put("super_user_key", user1.getSuper_user_key());
		
		








Connection conn = HibernateUtil.createSQLConnection(); 
		jasperPrint = 
			JasperFillManager.fillReport(
				io, 
				parameters, 
				conn
				);
		
				
		session.setAttribute("applicantofferratio", jasperPrint);
	
	
	//JRHtmlExporter exporter = new JRHtmlExporter();
	JRXhtmlExporter exporter = new JRXhtmlExporter();
	
	
	
	StringBuffer sbuffer = new StringBuffer();



    String imageuri = request.getContextPath()+"/reports/dashboardimage?dashboard=applicantofferratio&ver="+new java.util.Date().getTime() +"&image=";
  
	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageuri);
	//exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.valueOf(pageIndex));
	exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
	exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
	exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
	


	exporter.exportReport();
	
%>


<% if(sbuffer.toString() != null && sbuffer.toString().length()>0){%>
<table>
<tr>
<td align="right">&nbsp;<a href="<%=request.getContextPath()%>/reports/xls?cmd=budgetTracking&reportfilename=<%=reportfilename%>&budgetIds=<%=budgetIds%>" target="new"><img src="../images/xls_icon.jpg" border="0" width="18" height="18"></a></td>
<td align="right">&nbsp;<a href="<%=request.getContextPath()%>/reports/pdf?cmd=budgetTracking&reportfilename=<%=reportfilename%>&budgetIds=<%=budgetIds%>" target="new"><img src="../images/PDF_icon.jpg" border="0" width="18" height="18"></a></td>
<td align="right">&nbsp;<a href="javascript:window.print()"><img src="../images/print.gif" border="0"></a></td>
</tr>
</table>
<%=sbuffer.toString()%>

<%}else{%>
No data found.
<%}%>
</html>
 