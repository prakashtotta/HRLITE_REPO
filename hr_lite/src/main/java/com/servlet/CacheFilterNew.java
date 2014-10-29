package com.servlet;

import com.resources.Constant;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CacheFilterNew
  implements Filter
{
  protected static final Logger logger = Logger.getLogger(CacheFilterNew.class);
  ServletContext sc;
  FilterConfig fc;
  long cacheTimeout = 9223372036854775807L;
  private static final String DEFAULT_CACHE_DURATION = "2592000";
  
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)res;
    

    String r = this.sc.getRealPath("");
    String path = this.fc.getInitParameter(request.getRequestURI());
    if ((path != null) && (path.equals("nocache")))
    {
      chain.doFilter(request, response);
      return;
    }
    path = r + path;
    

    String id = request.getRequestURI() + request.getQueryString();
    
    String localeSensitive = this.fc.getInitParameter("locale-sensitive");
    if (localeSensitive != null)
    {
      StringWriter ldata = new StringWriter();
      Enumeration locales = request.getLocales();
      while (locales.hasMoreElements())
      {
        Locale locale = (Locale)locales.nextElement();
        ldata.write(locale.getISO3Language());
      }
      id = id + ldata.toString();
    }
    String temp = Constant.getValue("ATTACHMENT_PATH") + "cache" + File.separator;
    File file = new File(temp + id);
    if (path == null) {
      path = this.sc.getRealPath(request.getRequestURI());
    }
    File current = new File(path);
    try
    {
      long now = Calendar.getInstance().getTimeInMillis();
      if ((!file.exists()) || ((file.exists()) && (current.lastModified() > file.lastModified())) || (this.cacheTimeout < now - file.lastModified()))
      {
        String name = file.getAbsolutePath();
        name = name.substring(0, name.lastIndexOf("/") == -1 ? 0 : name.lastIndexOf("/"));
        new File(name).mkdirs();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CacheResponseWrapper wrappedResponse = new CacheResponseWrapper(response, baos);
        
        chain.doFilter(req, wrappedResponse);
        
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
      }
    }
    catch (ServletException e)
    {
      logger.info(e);
      if (!file.exists()) {
        throw new ServletException(e);
      }
    }
    catch (IOException e)
    {
      logger.info(e);
      if (!file.exists()) {
        throw e;
      }
    }
    FileInputStream fis = new FileInputStream(file);
    String mt = this.sc.getMimeType(request.getRequestURI());
    response.setContentType(mt);
    ServletOutputStream sos = res.getOutputStream();
    for (int i = fis.read(); i != -1; i = fis.read()) {
      sos.write((byte)i);
    }
  }
  
  public void init(FilterConfig filterConfig)
  {
    this.fc = filterConfig;
    String ct = this.fc.getInitParameter("cacheTimeout");
    if (ct != null) {
      this.cacheTimeout = (60000L * Long.parseLong(ct));
    }
    this.sc = filterConfig.getServletContext();
  }
  
  public void destroy()
  {
    this.sc = null;
    this.fc = null;
  }
}
