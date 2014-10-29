package com.servlet;

import com.common.AppContextUtil;
import com.dao.HibernateUtil;
import com.manager.BulkTaskManager;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.scheduler.SchedulerService;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DBService
  extends HttpServlet
{
  protected static final Logger logger = Logger.getLogger(DBService.class);
  
  public void init(ServletConfig config)
    throws ServletException
  {
    System.out.println("\n**** Initializing Job Service Init Servlet ********** \n");
    
    super.init(config);
    












    String[] springcfgFile = { "conf/spring/applicationContext.xml", "conf/spring/applicationContext-performance.xml", "conf/spring/applicationContext-leave.xml", "conf/spring/applicationContext-network.xml" };
    


    logger.info("getServletContext().getRealPath()" + getServletContext().getRealPath("/"));
    



    logger.info("tomcat.webapps.path" + Constant.getValue("tomcat.webapps.path"));
    String path = Constant.getValue("tomcat.webapps.path") + "WEB-INF/classes/conf/";
    



    initLogPro(path);
    AppContextUtil.applicationContextfile = springcfgFile;
    AppContextUtil.getAppcontext();
    try
    {
      HibernateUtil.appHome = path;
      







      Constant.getValue("FILE_UPLOAD");
      EmailTaskManager.getExcecuter();
      BulkTaskManager.getExcecuter();
      
      new SchedulerService().createSchedule();
      
      HibernateUtil.configureApplicantColumnAttributeMappings();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {}
  
  private void initLogPro(String path)
  {
    try
    {
      Properties propsLog = new Properties();
      Constant con = new Constant();
      propsLog = con.getPropertiesFromClasspath("conf/log4j.properties");
      
      PropertyConfigurator.configure(propsLog);
    }
    catch (Exception ex)
    {
      System.out.println("Log4J can not be initialized");
      logger.info("Log4J can not be initialized");
    }
  }
}
