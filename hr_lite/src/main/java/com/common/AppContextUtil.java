package com.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextUtil
{
  public static String[] applicationContextfile = null;
  private static ApplicationContext appContext = null;
  
  public static ApplicationContext getAppcontext()
  {
    if (appContext == null) {
      appContext = new ClassPathXmlApplicationContext(applicationContextfile);
    }
    return appContext;
  }
}
