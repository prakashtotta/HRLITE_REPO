package com.servlet;

import com.dao.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperPrintServlet
  extends HttpServlet
{
  public void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    ServletContext context = getServletConfig().getServletContext();
    String reportfilename = request.getParameter("reportfilename");
    String orgId = request.getParameter("parentOrgId");
    String orgName = request.getParameter("parentOrgName");
    
    File reportFile = new File(context.getRealPath("/reportsoutput/dashboard/" + reportfilename));
    if (!reportFile.exists()) {
      throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
    }
    Map parameters = new HashMap();
    

    parameters.put("ORGANIZATION_ID", new Integer(orgId));
    parameters.put("ORGANIZATION_NAME", orgName);
    
    JasperPrint jasperPrint = null;
    Connection conn = null;
    try
    {
      conn = HibernateUtil.createSQLConnection();
      JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
      
      jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
    }
    catch (Exception e)
    {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<title>JasperReports - Web Application Sample</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
      out.println("</head>");
      
      out.println("<body bgcolor=\"white\">");
      
      out.println("<span class=\"bnew\">JasperReports encountered this error :</span>");
      out.println("<pre>");
      
      e.printStackTrace(out);
      
      out.println("</pre>");
      
      out.println("</body>");
      out.println("</html>"); return;
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
    if (jasperPrint != null)
    {
      response.setContentType("application/octet-stream");
      ServletOutputStream ouputStream = response.getOutputStream();
      
      ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
      oos.writeObject(jasperPrint);
      oos.flush();
      oos.close();
      
      ouputStream.flush();
      ouputStream.close();
    }
    else
    {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<title>JasperReports - Web Application Sample</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
      out.println("</head>");
      
      out.println("<body bgcolor=\"white\">");
      
      out.println("<span class=\"bold\">Empty response.</span>");
      
      out.println("</body>");
      out.println("</html>");
    }
  }
}
