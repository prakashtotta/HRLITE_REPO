package com.tag;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.util.StringUtils;

public class PaginationTag
  extends TagSupport
{
  private String start;
  private String range;
  private String results;
  private String action;
  private String styleClass;
  private String title;
  public static final String SPACE = "&nbsp;";
  public static final String YES = "yes";
  public static final String START = "start";
  public static final String RANGE = "range";
  public static final String FIRST = "first";
  public static final String AND = "&";
  public static final String EQUALS = "=";
  public static final String ABEGIN = "<a href=\"";
  public static final String AEND = "</a>";
  public static final String QUESTION = "?";
  public static final String QUOT = "\"";
  public static final String GT = ">";
  public static final String LT = "<";
  public static final String PIPE = "|";
  public static final String LB = "[";
  public static final String RB = "]";
  public static final String BOLDSTART = "<b>";
  public static final String BOLDEND = "</b>";
  public static final String PAGES = "pages";
  
  public String getRange()
  {
    return this.range;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public int stringToInt(String str, int defaultValue)
  {
    if (str != null) {
      return Integer.parseInt(str);
    }
    return defaultValue;
  }
  
  public int doStartTag()
  {
    int start = stringToInt(this.start, 0);
    int range = stringToInt(this.range, 15);
    int results = stringToInt(this.results, 100);
    int pages = results / range;
    pages = results % range == 0 ? pages : pages + 1;
    if (pages > 1)
    {
      int ci = start / range;
      boolean firstPage = ci < 2;
      boolean hasPreviousPage = ci > 0;
      boolean hasNextPage = ci < pages - 1;
      start = 0;
      StringBuffer sb = null;
      try
      {
        JspWriter out = this.pageContext.getOut();
        sb = new StringBuffer();
        sb.append(getTableWithStyle()).append("<tr><td>");
        sb.append("[").append("&nbsp;");
        sb.append(pages).append("&nbsp;").append("pages").append("&nbsp;");
        if (hasPreviousPage) {
          sb.append(getAnchor((ci - 1) * range, range, "<")).append("|").append("&nbsp;");
        }
        if (!firstPage) {
          sb.append(getAnchor(0, range, "first")).append("|").append("&nbsp;");
        }
        int i = ci - 5 < 0 ? 0 : ci - 5;
        start = ci - 5 < 1 ? start : range * (ci - 5);
        pages = ci + 5 > pages ? pages : ci + 5;
        for (; i < pages; i++)
        {
          if (i == ci) {
            sb.append("<b>").append(i + 1).append("</b>").append("&nbsp;");
          } else {
            sb.append(getAnchor(start, range, i + 1));
          }
          start += range;
        }
        if (hasNextPage) {
          sb.append("|").append("&nbsp;").append(getAnchor((ci + 1) * range, range, ">"));
        }
        sb.append("]");
        sb.append("</td></tr></table>");
        out.println(sb);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return 0;
  }
  
  public int doEndTag()
  {
    return 6;
  }
  
  private String getAnchor(int start, int range, int page)
  {
    return getAnchor(start, range, String.valueOf(page));
  }
  
  private String getAnchor(int start, int range, String page)
  {
    HttpServletRequest r = (HttpServletRequest)this.pageContext.getRequest();
    String extension = ((String)this.pageContext.getServletContext().getAttribute("org.apache.struts.action.SERVLET_MAPPING")).substring(1);
    




    boolean hasQueryString = !StringUtils.isEmpty(getQueryString(r.getParameterMap()));
    


    StringBuffer url = new StringBuffer(r.getRequestURL().toString().substring(r.getRequestURL().toString().indexOf(r.getContextPath())));
    


    url = hasQueryString ? url.append("?").append(getQueryString(r.getParameterMap())).append("&") : url.append("?");
    





    return addTitle(new StringBuffer("<a href=\"").append(hasQueryString ? new StringBuffer(this.action).append(extension).append("?").append(getQueryString(r.getParameterMap())).append("&") : this.action == null ? url : new StringBuffer(this.action).append(extension).append("?")).append("start").append("=").append(start).append("&").append("range").append("=").append(range).append("\"").append(">").append(page).append("</a>").append("&nbsp;"), page).toString();
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public String getAction()
  {
    return this.action;
  }
  
  public void setAction(String action)
  {
    this.action = action;
  }
  
  public String getQueryString(Map map)
  {
    if (map == null) {
      return null;
    }
    StringBuffer sb = new StringBuffer();
    Set keys = map.keySet();
    Iterator iterator = keys.iterator();
    String obj = null;
    while (iterator.hasNext())
    {
      obj = (String)iterator.next();
      if ((!"start".equals(obj)) && (!"range".equals(obj))) {
        sb.append(obj).append("=").append(((String[])(String[])map.get(obj))[0]).append("&");
      }
    }
    return sb.toString().endsWith("&") ? sb.toString().substring(0, sb.toString().length() - 1) : sb.toString();
  }
  
  public String getStyleClass()
  {
    return this.styleClass;
  }
  
  public void setStyleClass(String styleClass)
  {
    this.styleClass = styleClass;
  }
  
  public String getTableWithStyle()
  {
    if (StringUtils.isNotEmpty(this.styleClass)) {
      return "<table" + " class=\"" + this.styleClass + "\"" + " width=\"100\"%>";
    }
    return "<table bgcolor=eeeeff border=1 style=\"border-collapse:collapse\" bordercolor=#666699 width=100%>";
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  private StringBuffer addTitle(StringBuffer sb, String page)
  {
    if ((sb == null) || (StringUtils.isEmpty(page))) {
      return sb;
    }
    if ((StringUtils.isNotEmpty(this.title)) && (StringUtils.trim(this.title).equals("yes")))
    {
      StringBuffer tt = new StringBuffer("<span title=\"");
      if (page.equals("<")) {
        tt.append("Previous page\">");
      } else if (page.equals(">")) {
        tt.append("Next page\">");
      } else if (page.equals("first")) {
        tt.append("First page\">");
      } else {
        tt.append("Go to page " + page + "\">");
      }
      tt.append(sb);
      tt.append("</span>");
      return tt;
    }
    return sb;
  }
}
