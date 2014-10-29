package com.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.ConnectionProviderFactory;

public class DBCPConnectionProvider
  implements ConnectionProvider
{
  protected static final Logger log = Logger.getLogger(DBCPConnectionProvider.class);
  private static final String PREFIX = "hibernate.dbcp.";
  private BasicDataSource ds;
  private static final String DBCP_PS_MAXACTIVE = "hibernate.dbcp.ps.maxActive";
  private static final String AUTOCOMMIT = "hibernate.connection.autocommit";
  
  public void configure(Properties props)
    throws HibernateException
  {
    try
    {
      log.debug("Configure DBCPConnectionProvider");
      

      Properties dbcpProperties = new Properties();
      

      String jdbcDriverClass = props.getProperty("hibernate.connection.driver_class");
      String jdbcUrl = props.getProperty("hibernate.connection.url");
      dbcpProperties.put("driverClassName", jdbcDriverClass);
      dbcpProperties.put("url", jdbcUrl);
      

      String username = props.getProperty("hibernate.connection.username");
      String password = props.getProperty("hibernate.connection.password");
      dbcpProperties.put("username", username);
      dbcpProperties.put("password", password);
      

      String isolationLevel = props.getProperty("hibernate.connection.isolation");
      if ((isolationLevel != null) && (isolationLevel.trim().length() > 0)) {
        dbcpProperties.put("defaultTransactionIsolation", isolationLevel);
      }
      String autocommit = props.getProperty("hibernate.connection.autocommit");
      if ((autocommit != null) && (autocommit.trim().length() > 0)) {
        dbcpProperties.put("defaultAutoCommit", autocommit);
      } else {
        dbcpProperties.put("defaultAutoCommit", String.valueOf(Boolean.FALSE));
      }
      String poolSize = props.getProperty("hibernate.connection.pool_size");
      if ((poolSize != null) && (poolSize.trim().length() > 0) && (Integer.parseInt(poolSize) > 0)) {
        dbcpProperties.put("maxActive", poolSize);
      }
      Properties driverProps = ConnectionProviderFactory.getConnectionProperties(props);
      if (driverProps.size() > 0)
      {
        StringBuffer connectionProperties = new StringBuffer();
        for (Iterator iter = driverProps.entrySet().iterator(); iter.hasNext();)
        {
          Map.Entry entry = (Map.Entry)iter.next();
          String key = (String)entry.getKey();
          String value = (String)entry.getValue();
          connectionProperties.append(key).append('=').append(value);
          if (iter.hasNext()) {
            connectionProperties.append(';');
          }
        }
        dbcpProperties.put("connectionProperties", connectionProperties.toString());
      }
      for (Iterator iter = props.entrySet().iterator(); iter.hasNext();)
      {
        Map.Entry entry = (Map.Entry)iter.next();
        String key = (String)entry.getKey();
        if (key.startsWith("hibernate.dbcp."))
        {
          String property = key.substring("hibernate.dbcp.".length());
          String value = (String)entry.getValue();
          dbcpProperties.put(property, value);
        }
      }
      if (props.getProperty("hibernate.dbcp.ps.maxActive") != null)
      {
        dbcpProperties.put("poolPreparedStatements", String.valueOf(Boolean.TRUE));
        dbcpProperties.put("maxOpenPreparedStatements", props.getProperty("hibernate.dbcp.ps.maxActive"));
      }
      if (log.isDebugEnabled())
      {
        log.debug("Creating a DBCP BasicDataSource with the following DBCP factory properties:");
        StringWriter sw = new StringWriter();
        dbcpProperties.list(new PrintWriter(sw, true));
        log.debug(sw.toString());
      }
      this.ds = ((BasicDataSource)BasicDataSourceFactory.createDataSource(dbcpProperties));
      



      Connection conn = this.ds.getConnection();
      conn.close();
      

      logStatistics();
    }
    catch (Exception e)
    {
      String message = "Could not create a DBCP pool";
      log.error(message, e);
      if (this.ds != null)
      {
        try
        {
          this.ds.close();
        }
        catch (Exception e2) {}
        this.ds = null;
      }
      throw new HibernateException(message, e);
    }
    log.debug("Configure DBCPConnectionProvider complete");
  }
  
  public Connection getConnection()
    throws SQLException
  {
    Connection conn = null;
    try
    {
      conn = this.ds.getConnection();
    }
    finally
    {
      logStatistics();
    }
    return conn;
  }
  
  public void closeConnection(Connection conn)
    throws SQLException
  {
    try
    {
      conn.close();
    }
    finally
    {
      logStatistics();
    }
  }
  
  public void close()
    throws HibernateException
  {
    log.debug("Close DBCPConnectionProvider");
    logStatistics();
    try
    {
      if (this.ds != null)
      {
        this.ds.close();
        this.ds = null;
      }
      else
      {
        log.warn("Cannot close DBCP pool (not initialized)");
      }
    }
    catch (Exception e)
    {
      throw new HibernateException("Could not close DBCP pool", e);
    }
    log.debug("Close DBCPConnectionProvider complete");
  }
  
  protected void logStatistics()
  {
    if (log.isInfoEnabled()) {
      log.info("active: " + this.ds.getNumActive() + " (max: " + this.ds.getMaxActive() + ")   " + "idle: " + this.ds.getNumIdle() + "(max: " + this.ds.getMaxIdle() + ")");
    }
  }
  
  public boolean supportsAggressiveRelease()
  {
    return false;
  }
}
