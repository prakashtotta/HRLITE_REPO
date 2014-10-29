package com.servlet;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

public class ContextCleanup
  implements ServletContextListener
{
  protected static final Logger logger = Logger.getLogger(ContextCleanup.class);
  
  public void contextInitialized(ServletContextEvent event)
  {
    logger.info("contextInitialized");
  }
  
  public void contextDestroyed(ServletContextEvent event)
  {
    logger.info("contextDestroyed");
    
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements())
    {
      Driver driver = (Driver)drivers.nextElement();
      if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
        try
        {
          DriverManager.deregisterDriver(driver);
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}
