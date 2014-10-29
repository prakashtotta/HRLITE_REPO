package com.servlet;

import com.resources.Constant;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CacheFilter
  implements Filter
{
  private static final String DEFAULT_CACHE_DURATION = "2592000";
  protected static final Logger logger = Logger.getLogger(CacheFilter.class);
  private boolean cache = true;
  private String cacheDurationStr = "2592000";
  
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;
    doFilterAction(request, response);
    chain.doFilter(request, response);
  }
  
  public void init(FilterConfig config)
    throws ServletException
  {
    String val = Constant.getValue("cache.enable");
    if ((val != null) && 
      (val.equalsIgnoreCase("no"))) {
      this.cache = false;
    }
    val = Constant.getValue("cache.duration");
    if (val != null) {
      try
      {
        int intVal = Integer.parseInt(val);
        if (intVal > 0) {
          this.cacheDurationStr = val;
        }
      }
      catch (NumberFormatException e)
      {
        logger.info("cache duration" + e);
      }
    }
    this.cacheDurationStr = ("max-age=" + this.cacheDurationStr);
  }
  
  public void destroy() {}
  
  private void doFilterAction(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException
  {
    if (this.cache) {
      resp.addHeader("Cache-Control", this.cacheDurationStr);
    }
  }
}
