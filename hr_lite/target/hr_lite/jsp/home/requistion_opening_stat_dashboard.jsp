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
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String timezoneoffset = user1.getTimezone().getTimezoneoffset();
%>

<%
	JasperPrint jasperPrint = null;
	String reportfilename = (String)request.getParameter("reportfilename");
		String orgId = (String)request.getParameter("parentOrgId");
		String orgName = (String)request.getParameter("parentOrgName");
		String urllink = (String)request.getParameter("url");

      List orgListnew = new ArrayList();		
		orgListnew.add(user1.getOrganization().getOrgId());
		
	
		
		List yearsList = new ArrayList();
		Calendar cal=Calendar.getInstance();
		    int year=cal.get(Calendar.YEAR);
			yearsList.add(year-1);
		    yearsList.add(year);
			yearsList.add(year+1);
			
	
		
		String reportFileName = application.getRealPath("/reportsoutput/dashboard/"+reportfilename);
		String filepath = application.getRealPath("/reportsoutput/dashboard/");
		File reportFile = new File(reportFileName);
		//File reportFile = new File(reportFileName);
		FileInputStream io = new  FileInputStream(reportFile);

		//JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());

		Map parameters = new HashMap();
		//parameters.put("ReportTitle", "Address Report");
		//parameters.put("BaseDir", reportFile.getParentFile());
		parameters.put("Org_id", orgListnew);
		parameters.put("year", yearsList);
		parameters.put("super_user_key", user1.getSuper_user_key());	








Connection conn = HibernateUtil.createSQLConnection(); 
		jasperPrint = 
			JasperFillManager.fillReport(
				io, 
				parameters, 
				conn
				);
				
		session.setAttribute("reqopeningstat", jasperPrint);
	
	
	//JRHtmlExporter exporter = new JRHtmlExporter();
	JRXhtmlExporter exporter = new JRXhtmlExporter();
	

	
	
	
	StringBuffer sbuffer = new StringBuffer();

   String imageuri = request.getContextPath()+"/reports/dashboardimage?dashboard=reqopeningstat&ver="+new java.util.Date().getTime() +"&image=";
  
	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageuri);
	//exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.valueOf(pageIndex));
	exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
	exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
	exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
	//exporter.setParameter(JRHtmlExporterParameter.OUTPUT_FILE_NAME, "req-op");


	exporter.exportReport();
%>



<% if(sbuffer.toString() != null && sbuffer.toString().length()>0){%>
<%=sbuffer.toString()%>

<%}else{%>
No data found.
<%}%>





