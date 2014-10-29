package com.servlet;

import com.util.HTMLInputFilter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.upload.MultipartRequestWrapper;

public final class MultipartRequestWrapperImpl
  extends MultipartRequestWrapper
{
  protected static final Logger logger = Logger.getLogger(MultipartRequestWrapperImpl.class);
  HTMLInputFilter filter = new HTMLInputFilter();
  
  public MultipartRequestWrapperImpl(HttpServletRequest servletRequest)
  {
    super(servletRequest);
    
    logger.info("MultipartRequestWrapperImpl");
  }
  
  public Map getParameterMap()
  {
    logger.info("getParameterMap");
    Map map = this.parameters;
    logger.info("map" + map);
    Iterator iter = map.keySet() != null ? map.keySet().iterator() : null;
    String key = null;
    String[] values = null;
    if (iter != null) {
      while (iter.hasNext())
      {
        key = (String)iter.next();
        if (key != null)
        {
          values = (String[])map.get(key);
          for (int i = 0; i < values.length; i++)
          {
            logger.info("values[i]" + values[i]);
            values[i] = this.filter.filter(values[i]);
          }
        }
      }
    }
    return map;
  }
}
