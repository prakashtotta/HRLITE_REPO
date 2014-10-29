package com.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class CrossScriptingFilter
  implements Filter
{
  protected static final Logger logger = Logger.getLogger(CrossScriptingFilter.class);
  FilterConfig filterConfig = null;
  private Pattern scriptPattern = Pattern.compile("(<|%3C|&lt(;)?)(/)?(((S|s)(C|c)(R|r)(I|i)(P|p)(T|t))|%73%63%72%69%70%74)(\\s)*(>|%3E|&gt(;)?)");
  
  public void init(FilterConfig filterConfig)
    throws ServletException
  {
    this.filterConfig = filterConfig;
  }
  
  public void destroy()
  {
    this.filterConfig = null;
  }
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    if (isUrlContainsValidParameters((HttpServletRequest)request))
    {
      logger.info("Request parameters failed validation");
      request.getRequestDispatcher("/jsp/common/requestDataError.jsp").forward(request, response);
      return;
    }
    String contentType = request.getContentType();
    logger.info("contentType" + contentType);
    














    chain.doFilter(new RequestWrapper((HttpServletRequest)request), response);
  }
  
  private boolean isUrlContainsValidParameters(HttpServletRequest req)
  {
    String url = req.getRequestURI();
    if (!url.endsWith("/jsp/common/requestDataError.jsp"))
    {
      Map paramMap = req.getParameterMap();
      Set paramKeySet = paramMap.keySet();
      Iterator itr = paramKeySet.iterator();
      while (itr.hasNext())
      {
        String paramKey = (String)itr.next();
        String[] paramValue = (String[])paramMap.get(paramKey);
        if (this.scriptPattern.matcher(paramValue[0].toString()).find()) {
          return true;
        }
      }
    }
    return false;
  }
}
