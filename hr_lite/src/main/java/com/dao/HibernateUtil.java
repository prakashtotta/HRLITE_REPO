package com.dao;

import com.bean.JobApplicant;
import com.common.AppContextUtil;
import com.resources.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class HibernateUtil
{
  protected static final Logger logger = Logger.getLogger(HibernateUtil.class);
  public static String appHome = "No";
  private static SessionFactory sessionFactory;
  private static final ThreadLocal threadSession = new ThreadLocal();
  private static final ThreadLocal threadTransaction = new ThreadLocal();
  
  public static void initMonitor()
  {
    logger.info("Hibernate configure");
    try
    {
      logger.info("appHome satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + appHome);
      String path_properties = appHome + File.separatorChar + "hibernate.properties";
      String path_mapping = appHome + File.separatorChar + "mapping_classes.mysql.hbm.xml";
      


      Properties propHibernate = new Properties();
      propHibernate.load(new FileInputStream(path_properties));
      
      Configuration configuration = new Configuration();
      configuration.addFile(path_mapping);
      configuration.setProperties(propHibernate);
      








      sessionFactory = configuration.buildSessionFactory();
      

      logger.info("Hibernate configure sessionFactory" + sessionFactory);
    }
    catch (Throwable ex)
    {
      logger.error("Exception in initMonitor", ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  
  public static SessionFactory getSessionFactory()
  {
    logger.info("Inside getSessionFactory method");
    try
    {
      if (sessionFactory == null)
      {
        ApplicationContext appContext = AppContextUtil.getAppcontext();
        BaseDAO basedao = (BaseDAO)appContext.getBean("baseDao");
        sessionFactory = basedao.getSessionFactory();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception in getSessionFactory", e);
    }
    return sessionFactory;
  }
  
  public static Session getSession()
  {
    Session s = (Session)threadSession.get();
    if (s == null)
    {
      s = getSessionFactory().openSession();
      threadSession.set(s);
      logger.debug("session 1 $" + s);
    }
    return s;
  }
  
  public static void closeSession()
  {
    Session s = (Session)threadSession.get();
    threadSession.set(null);
    if ((s != null) && (s.isOpen()))
    {
      s.flush();
      s.close();
    }
  }
  
  public static void beginTransaction()
  {
    Transaction tx = null;
    if (tx == null)
    {
      tx = getSession().beginTransaction();
      threadTransaction.set(tx);
    }
  }
  
  public static void commitTransaction()
  {
    Transaction tx = (Transaction)threadTransaction.get();
    try
    {
      if (tx != null) {
        tx.commit();
      }
      threadTransaction.set(null);
    }
    catch (HibernateException ex)
    {
      rollbackTransaction();
      
      throw ex;
    }
  }
  
  public static void rollbackTransaction()
  {
    Transaction tx = (Transaction)threadTransaction.get();
    try
    {
      threadTransaction.set(null);
      if ((tx != null) && (!tx.wasCommitted()) && (!tx.wasRolledBack())) {
        tx.rollback();
      }
    }
    finally
    {
      closeSession();
    }
  }
  
  public static Connection createSQLConnection()
    throws Exception
  {
    Connection con = null;
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    con = DriverManager.getConnection(Constant.getValue("report.db.url"));
    con.setAutoCommit(false);
    

    return con;
  }
  
  public static void closeConnectionProp(Connection conn, Statement st, ResultSet rs)
  {
    try
    {
      if (conn != null)
      {
        conn.commit();
        conn.close();
      }
      if (st != null) {
        st.close();
      }
      if (rs != null) {
        rs.close();
      }
    }
    catch (Exception exp)
    {
      exp.printStackTrace();
    }
  }
  
  public static void closeConnectionProp(Statement st, ResultSet rs)
  {
    try
    {
      if (st != null) {
        st.close();
      }
      if (rs != null) {
        rs.close();
      }
    }
    catch (Exception exp)
    {
      exp.printStackTrace();
    }
  }
  
  public static void configureApplicantColumnAttributeMappings()
  {
    Map m = new HashMap();
    Map mtable = new HashMap();
    logger.info("configureColumnAttributeMappings");
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    
    LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean)appContext.getBean("&sessionFactory");
    
    Configuration cfg = lsfb.getConfiguration();
    
    PersistentClass jobapp = cfg.getClassMapping(JobApplicant.class.getName());
    


    Field[] appf = JobApplicant.class.getFields();
    for (int i = 0; i < appf.length; i++)
    {
      Property prop = null;
      try
      {
        prop = jobapp.getProperty(appf[i].getName());
      }
      catch (Exception e)
      {
        logger.info(e.getMessage());
      }
      if (prop != null)
      {
        String attname = prop.getName();
        
        String attvalue = "";
        Iterator itr = prop.getColumnIterator();
        while (itr.hasNext())
        {
          Column cl = (Column)itr.next();
          attvalue = cl.getName();
        }
        m.put(attname, attvalue);
        mtable.put(attvalue, attname);
      }
    }
    m.put("applicantId", "application_id");
    mtable.put("applicantId", "application_id");
    
    Constant.setApplicantTableBeanColumnNameMap(m);
    Constant.setApplicantTableMap(mtable);
  }
}
