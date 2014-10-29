package com.servlet;

import com.resources.Constant;
import com.util.HTMLInputFilter;
import com.util.StringUtils;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.log4j.Logger;

public final class RequestWrapper
  extends HttpServletRequestWrapper
{
  protected static final Logger logger = Logger.getLogger(RequestWrapper.class);
  HTMLInputFilter filter = new HTMLInputFilter();
  protected HttpServletRequest request = null;
  
  public RequestWrapper(HttpServletRequest servletRequest)
  {
    super(servletRequest);
  }
  
  public String[] getParameterValues(String parameter)
  {
    String[] values = super.getParameterValues(parameter);
    String uri = super.getRequestURI();
    if (uri != null) {
      uri = uri.substring(4);
    }
    String param = (String)Constant.xssMap.get(uri);
    logger.info("11uri:" + uri + "param:" + param + "parameter:" + parameter);
    if ((!StringUtils.isNullOrEmpty(param)) && (param.equals(parameter)))
    {
      logger.info("11Match found for parameter" + parameter);
      logger.info("values" + values);
      if (values == null) {
        return null;
      }
      int count = values.length;
      String[] encodedValues = new String[count];
      for (int i = 0; i < count; i++)
      {
        logger.info("11value" + values[i]);
        encodedValues[i] = this.filter.filter(values[i]);
        logger.info("11clean value" + encodedValues[i]);
      }
      return encodedValues;
    }
    return values;
  }
  
  public String getHeader(String name)
  {
    String value = super.getHeader(name);
    if (value == null) {
      return null;
    }
    String clean = this.filter.filter(value);
    
    return clean;
  }
  
  private String cleanXSS(String value)
  {
    value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
    value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
    value = value.replaceAll("'", "& #39;");
    value = value.replaceAll("eval\\((.*)\\)", "");
    value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
    value = value.replaceAll("script", "");
    value = value.replaceAll("(?i)script", "");
    return value;
  }
}
