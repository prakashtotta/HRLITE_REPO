package com.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.cactus.ServletURL;
import org.apache.cactus.server.HttpServletRequestWrapper;
import org.apache.log4j.Logger;

public class StrutsRequestWrapper
  extends HttpServletRequestWrapper
{
  protected static final Logger logger = Logger.getLogger(StrutsRequestWrapper.class);
  private String pathInfo;
  private String servletPath;
  private Map parameters;
  
  public StrutsRequestWrapper(HttpServletRequestWrapper request)
  {
    super(request, new ServletURL(request.getServerName(), request.getContextPath(), request.getServletPath(), request.getPathInfo(), request.getQueryString()));
    

    this.parameters = new HashMap();
  }
  
  public void setPathInfo(String pathInfo)
  {
    this.pathInfo = pathInfo;
  }
  
  public String getPathInfo()
  {
    if (this.pathInfo == null) {
      return super.getPathInfo();
    }
    return this.pathInfo;
  }
  
  public void setServletPath(String servletPath)
  {
    this.servletPath = servletPath;
  }
  
  public String getServletPath()
  {
    if (this.servletPath == null) {
      return super.getServletPath();
    }
    return this.servletPath;
  }
  
  public String getParameter(String name)
  {
    logger.info("inside getParameter");
    String[] result = getParameterValues(name);
    if ((result != null) && (result.length > 0))
    {
      logger.info("inside getParameter" + result[0]);
      return result[0];
    }
    return null;
  }
  
  public String[] getParameterValues(String name)
  {
    logger.info("inside getParameterValues");
    Object result = super.getParameterValues(name);
    if ((result == null) && (this.parameters.containsKey(name)))
    {
      result = this.parameters.get(name);
      logger.info("inside getParameterValues" + result);
      if (!(result instanceof String[]))
      {
        String[] resultArray = { result.toString() };
        result = resultArray;
      }
    }
    return (String[])result;
  }
  
  public Enumeration getParameterNames()
  {
    Enumeration Names = super.getParameterNames();
    List nameList = new ArrayList(this.parameters.keySet());
    while (Names.hasMoreElements()) {
      nameList.add(Names.nextElement());
    }
    return Collections.enumeration(nameList);
  }
  
  public void addParameter(String name, String value)
  {
    if ((super.getParameter(name) == null) && (name != null) && (value != null)) {
      this.parameters.put(name, value);
    }
  }
  
  public void addParameter(String name, String[] values)
  {
    if ((super.getParameter(name) == null) && (name != null) && (values != null)) {
      this.parameters.put(name, values);
    }
  }
  
  public Map getParameterMap()
  {
    Map result = new HashMap();
    result.putAll(super.getParameterMap());
    result.putAll(this.parameters);
    return result;
  }
  
  public void clearRequestParameters()
  {
    this.parameters.clear();
  }
}
