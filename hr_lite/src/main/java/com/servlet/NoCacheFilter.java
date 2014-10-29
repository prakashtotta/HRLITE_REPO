package com.servlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter
  implements Filter
{
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;
    doFilterAction(response);
    chain.doFilter(request, response);
  }
  
  public void init(FilterConfig config)
    throws ServletException
  {}
  
  public void destroy() {}
  
  private void doFilterAction(HttpServletResponse resp)
    throws IOException, ServletException
  {
    resp.setHeader("Cache-Control", "no-cache");
    resp.setHeader("Cache-Control", "no-store");
    resp.setHeader("Cache-directive", "no-cache");
    resp.setHeader("Pragma", "no-cache");
    resp.setHeader("Pragma-Directive", "no-cache");
    resp.setDateHeader("Expires", -1L);
    
    resp.setDateHeader("Last-Modified", new Date().getTime());
  }
}
