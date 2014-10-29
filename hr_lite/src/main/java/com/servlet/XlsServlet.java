package com.servlet;

import com.bean.Timezone;
import com.bean.User;
import com.dao.HibernateUtil;
import com.util.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import org.apache.log4j.Logger;

public class XlsServlet
  extends HttpServlet
{
  protected static final Logger logger = Logger.getLogger(XlsServlet.class);
  
  public void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    String cmd = request.getParameter("cmd");
    if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("requisitionstat"))) {
      requisitionstat(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("applicantofferorg"))) {
      applicantofferorg(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("vendorperformancecomparision"))) {
      vendorperformancecomparision(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("sourcesOfResume"))) {
      sourcesOfResume(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("recruiterEfficiency"))) {
      recruiterEfficiency(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("offerVsAcceptanceVsOnboard"))) {
      offerVsAcceptanceVsOnboard(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("eeoreport"))) {
      eeoreport(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("interviewProcessEfficiency"))) {
      interviewProcessEfficiency(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("requisitionOpeningStatistics"))) {
      requisitionOpeningStatistics(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("declinedOffer"))) {
      declinedOffer(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("applicantOfferRatio"))) {
      applicantOfferRatio(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("budgetTracking"))) {
      budgetTracking(request, response);
    } else if ((!StringUtils.isNullOrEmpty(cmd)) && (cmd.equals("timeToFill"))) {
      timeToFill(request, response);
    }
  }
  
  public void requisitionstat(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgId = request.getParameter("parentOrgId");
      String orgName = request.getParameter("parentOrgName");
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      if ((reportfilename != null) && (reportfilename.equals("requistion_opening_stat.jasper")))
      {
        parameters.put("ORGANIZATION_ID", new Integer(orgId));
        parameters.put("ORGANIZATION_NAME", orgName);
        parameters.put("Company_Logo", "E:\\share satya on sdas\\Tomcat 5.0\\webapps\\hr\\jsp\\images\\logo.gif");
        parameters.put("TIMEZONE_OFFSET", timezoneoffset);
        parameters.put("SUBREPORT_1", filepath + "\\requistion_opening_stat_subreport1.jasper");
        parameters.put("SUBREPORT_3", filepath + "\\requistion_opening_stat_subreport3.jasper");
      }
      else
      {
        parameters.put("ReportTitle", "Basic JasperReport");
        parameters.put("MaxSalary", new Double(25000.0D));
      }
      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"file.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"file.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void applicantofferorg(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgId = request.getParameter("parentOrgId");
      String orgName = request.getParameter("parentOrgName");
      String reportFileName = context.getRealPath("/reportsoutput//applicant_offered_joined_by_org/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/") + "/applicant_offered_joined_by_org";
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      


      parameters.put("Org_id", new Integer(orgId));
      parameters.put("Organization_id", new Integer(orgId));
      parameters.put("ORGANIZATION_NAME", orgName);
      parameters.put("Company_Logo", "E:\\share satya on sdas\\Tomcat 5.0\\webapps\\hr\\jsp\\images\\logo.gif");
      parameters.put("TIMEZONE_OFFSET", timezoneoffset);
      parameters.put("REPORT3_SUBREPORT1", filepath + "\\report3_subreport1.jasper");
      parameters.put("REPORT3_SUBREPORT2", filepath + "\\report3_subreport2.jasper");
      parameters.put("Image_1", fileimagepath + "\\images\\images1.jpeg");
      parameters.put("Image_2", fileimagepath + "\\images\\untitled1.JPG");
      

      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"file.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"file.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void vendorperformancecomparision(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    System.out.println("vendor performance comparision ... ");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String vendorIds = request.getParameter("vendorIds");
      String year = request.getParameter("year");
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      List vidsList = null;
      if (!StringUtils.isNullOrEmpty(vendorIds))
      {
        String[] vids = vendorIds.split(",");
        vidsList = Arrays.asList(vids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      parameters.put("ReportTitle", "Vendorwise Performance Comparison");
      
      parameters.put("Vendor_id", vidsList);
      parameters.put("yearlist", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      



      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"vendor_performance_comparison.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"vendor_performance_comparison.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void sourcesOfResume(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      



      parameters.put("ReportTitle", "Source of Resume");
      
      parameters.put("Org_Id", orgidsList);
      parameters.put("Dept_Id", deptidsList);
      parameters.put("Hiring_mgr_Id", hiringmgrIdsList);
      parameters.put("yearlist", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      

      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Sources_of_Resume.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Sources_of_Resume.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void recruiterEfficiency(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String recruiterIds = request.getParameter("recruiterIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List recruiteridsList = null;
      if (!StringUtils.isNullOrEmpty(recruiterIds))
      {
        String[] recruiterids = recruiterIds.split(",");
        recruiteridsList = Arrays.asList(recruiterids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      
      parameters.put("ReportTitle", "Recruiter Efficiency");
      

      parameters.put("Org_id", orgidsList);
      parameters.put("Dept", deptidsList);
      parameters.put("year", yearsList);
      parameters.put("Recruiter", recruiteridsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      
      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Recruiter_Efficiency.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Recruiter_Efficiency.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void offerVsAcceptanceVsOnboard(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      



      parameters.put("ReportTitle", "Offer Vs Acceptance Vs Onboard");
      
      parameters.put("Org_id", orgidsList);
      parameters.put("Dept", deptidsList);
      parameters.put("Hiring_mgr", hiringmgrIdsList);
      parameters.put("year", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      

      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Offer_Acceptance_Onboard.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Offer_Acceptance_Onboard.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void eeoreport(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      



      parameters.put("ReportTitle", "EEO");
      
      parameters.put("Org_id", orgidsList);
      parameters.put("Dept", deptidsList);
      parameters.put("Hiring_mgr", hiringmgrIdsList);
      parameters.put("year", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      

      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"eeo.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"eeo.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void interviewProcessEfficiency(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      



      parameters.put("ReportTitle", "Interview Process Efficiency");
      
      parameters.put("Org_Id", orgidsList);
      parameters.put("Dept_Id", deptidsList);
      parameters.put("Hiring_mgr_Id", hiringmgrIdsList);
      parameters.put("yearlist", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      

      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Interview_Process_Efficiency.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Interview_Process_Efficiency.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void requisitionOpeningStatistics(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      



      parameters.put("ReportTitle", "Requisition Opening Statistics");
      
      parameters.put("Org_id", orgidsList);
      parameters.put("Dept", deptidsList);
      parameters.put("Hiring_mgr", hiringmgrIdsList);
      parameters.put("year", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      


      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Requisition_Opening_Statistics.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Requisition_Opening_Statistics.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void declinedOffer(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      



      parameters.put("ReportTitle", "Declined Offer");
      
      parameters.put("Org_id", orgidsList);
      parameters.put("Dept", deptidsList);
      parameters.put("Hiring_mgr", hiringmgrIdsList);
      parameters.put("year", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      


      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Declined_Offer.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Declined_Offer.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void applicantOfferRatio(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String year = request.getParameter("year");
      String requitionIds = request.getParameter("requitionIds");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List requitionidsList = null;
      if (!StringUtils.isNullOrEmpty(requitionIds))
      {
        String[] requitionids = requitionIds.split(",");
        requitionidsList = Arrays.asList(requitionids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      



      parameters.put("ReportTitle", "Applicant Offered Ratio");
      
      parameters.put("Org_id", orgidsList);
      parameters.put("Dept", deptidsList);
      parameters.put("Hiring_mgr", hiringmgrIdsList);
      parameters.put("req_id", requitionidsList);
      parameters.put("year", yearsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      


      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Applicant_Offer_Ratio.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Applicant_Offer_Ratio.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void budgetTracking(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    System.out.println("vendor performance comparision ... ");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String budgetIds = request.getParameter("budgetIds");
      
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      List budgetidsList = null;
      if (!StringUtils.isNullOrEmpty(budgetIds))
      {
        String[] budgetids = budgetIds.split(",");
        budgetidsList = Arrays.asList(budgetids);
      }
      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      parameters.put("ReportTitle", "Budget Tracking");
      
      parameters.put("Budget_id", budgetidsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      



      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Budget_Tracking.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Budget_Tracking.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
  
  public void timeToFill(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletContext context = getServletConfig().getServletContext();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String timezoneoffset = user1.getTimezone().getTimezoneoffset();
    

    Connection conn = null;
    try
    {
      String reportfilename = request.getParameter("reportfilename");
      String orgIds = request.getParameter("orgids");
      String deptIds = request.getParameter("deptids");
      String hiringmgrIds = request.getParameter("hiringMgrIds");
      String recruiterIds = request.getParameter("recruiterIds");
      String requitionIds = request.getParameter("requitionIds");
      String jobGradeIds = request.getParameter("jobGradeIds");
      String year = request.getParameter("year");
      
      List orgidsList = null;
      if (!StringUtils.isNullOrEmpty(orgIds))
      {
        String[] orgids = orgIds.split(",");
        orgidsList = Arrays.asList(orgids);
      }
      List deptidsList = null;
      if (!StringUtils.isNullOrEmpty(deptIds))
      {
        String[] deptids = deptIds.split(",");
        deptidsList = Arrays.asList(deptids);
      }
      List requitionidsList = null;
      if (!StringUtils.isNullOrEmpty(requitionIds))
      {
        String[] requitionids = requitionIds.split(",");
        requitionidsList = Arrays.asList(requitionids);
      }
      List hiringmgrIdsList = null;
      if (!StringUtils.isNullOrEmpty(hiringmgrIds))
      {
        String[] hiringmgrids = hiringmgrIds.split(",");
        hiringmgrIdsList = Arrays.asList(hiringmgrids);
      }
      List recruiterIdsList = null;
      if (!StringUtils.isNullOrEmpty(recruiterIds))
      {
        String[] recruiterids = recruiterIds.split(",");
        recruiterIdsList = Arrays.asList(recruiterids);
      }
      List jobGradeIdsList = null;
      if (!StringUtils.isNullOrEmpty(jobGradeIds))
      {
        String[] jobGradeids = jobGradeIds.split(",");
        jobGradeIdsList = Arrays.asList(jobGradeids);
      }
      List yearsList = null;
      if (!StringUtils.isNullOrEmpty(year))
      {
        String[] years = year.split(",");
        yearsList = Arrays.asList(years);
      }
      String reportFileName = context.getRealPath("/reportsoutput/" + reportfilename);
      String filepath = context.getRealPath("/reportsoutput/");
      String fileimagepath = context.getRealPath("/reportsoutput/");
      File reportFile = new File(reportFileName);
      

      FileInputStream io = new FileInputStream(reportFile);
      if (!reportFile.exists()) {
        throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
      }
      Map parameters = new HashMap();
      




      parameters.put("ReportTitle", "Time To fill");
      parameters.put("Org_id", orgidsList);
      parameters.put("dept_id", deptidsList);
      parameters.put("Hiring_mgr", hiringmgrIdsList);
      parameters.put("year", yearsList);
      parameters.put("job_grade_id", jobGradeIdsList);
      parameters.put("jb_req_id", requitionidsList);
      parameters.put("recruiter_id", recruiterIdsList);
      parameters.put("super_user_key", Long.valueOf(user1.getSuper_user_key()));
      

      conn = HibernateUtil.createSQLConnection();
      







      JasperPrint jasperPrint = JasperFillManager.fillReport(io, parameters, conn);
      




      response.setContentType("application/xls");
      response.setHeader("Content-Disposition", "inline; filename=\"Time_to_fill.xls\"");
      FileBufferedOutputStream fbos = new FileBufferedOutputStream();
      JExcelApiExporter exporter = new JExcelApiExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      try
      {
        exporter.exportReport();
        fbos.close();
        if (fbos.size() > 0)
        {
          response.setContentType("application/xls");
          response.setHeader("Content-Disposition", "inline; filename=\"Time_to_fill.xls\"");
          response.setContentLength(fbos.size());
          ServletOutputStream ouputStream = response.getOutputStream();
          try
          {
            fbos.writeData(ouputStream);
            fbos.dispose();
            ouputStream.flush();
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
            fbos.close();
          }
          finally
          {
            if (ouputStream != null) {
              try
              {
                ouputStream.close();
              }
              catch (IOException ex) {}
            }
          }
        }
        fbos.dispose();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
      finally
      {
        fbos.close();
        fbos.dispose();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      HibernateUtil.closeConnectionProp(conn, null, null);
    }
  }
}
