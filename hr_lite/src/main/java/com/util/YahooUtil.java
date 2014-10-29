package com.util;

import com.bean.Timezone;
import com.bean.User;
import com.resources.Constant;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class YahooUtil
{
  public static String createJasonDataTableWithOnlyDate(List list, String[] fields, User user1)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                System.out.println(f[k].getType().getName());
                

                String temp = "";
                if ((f[k].getType().getName().equals("java.util.Date")) && (f[k].get(obj) != null))
                {
                  String condate = "";
                  if (Constant.dateTypeList.contains(fields[j])) {
                    condate = DateUtil.convertDateToStringDate((Date)f[k].get(obj), datepattern);
                  } else {
                    condate = DateUtil.convertSourceToTargetTimezoneWithoutTime((Date)f[k].get(obj), user1.getTimezone().getTimezoneCode(), user1.getLocale());
                  }
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + condate + "\"";
                }
                else
                {
                  Object tempobj = f[k].get(obj);
                  if ((f[k].getType().getName().equals("java.lang.String")) && (tempobj != null))
                  {
                    String tempstring = (String)f[k].get(obj);
                    tempstring = tempstring.replace("\"", "&#34;");
                    tempstring = tempstring.replace("\r\n", "<br>").replace("\n", "<br>");
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + tempstring + "\"";
                  }
                  else
                  {
                    Object nval = f[k].get(obj) == null ? "" : f[k].get(obj);
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + nval + "\"";
                  }
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
  
  public static String createJasonDataTableWithTime(List list, String[] fields, User user1)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                System.out.println(f[k].getType().getName());
                

                String temp = "";
                if ((f[k].getType().getName().equals("java.util.Date")) && (f[k].get(obj) != null))
                {
                  String condate = "";
                  if (Constant.dateTypeList.contains(fields[j])) {
                    condate = DateUtil.convertDateToStringDate((Date)f[k].get(obj), datepattern);
                  } else {
                    condate = DateUtil.convertSourceToTargetTimezoneWithoutTime((Date)f[k].get(obj), user1.getTimezone().getTimezoneCode(), user1.getLocale());
                  }
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + condate + "\"";
                }
                else
                {
                  Object tempobj = f[k].get(obj);
                  if ((f[k].getType().getName().equals("java.lang.String")) && (tempobj != null))
                  {
                    String tempstring = (String)f[k].get(obj);
                    tempstring = tempstring.replace("\"", "&#34;");
                    tempstring = tempstring.replace("\r\n", "<br>").replace("\n", "<br>");
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + tempstring + "\"";
                  }
                  else
                  {
                    Object nval = f[k].get(obj) == null ? "" : f[k].get(obj);
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + nval + "\"";
                  }
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
  
  public static String createJasonDataTableWithDefaultDateFormat(List list, String[] fields)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                System.out.println(f[k].getType().getName());
                

                String temp = "";
                if ((f[k].getType().getName().equals("java.util.Date")) && (f[k].get(obj) != null))
                {
                  String condate = DateUtil.convertDateToStringDate((Date)f[k].get(obj), datepattern);
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + condate + "\"";
                }
                else
                {
                  Object tempobj = f[k].get(obj);
                  if ((f[k].getType().getName().equals("java.lang.String")) && (tempobj != null))
                  {
                    String tempstring = (String)f[k].get(obj);
                    tempstring = tempstring.replace("\"", "&#34;");
                    tempstring = tempstring.replace("\r\n", "<br>").replace("\n", "<br>");
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + tempstring + "\"";
                  }
                  else
                  {
                    Object nval = f[k].get(obj) == null ? "" : f[k].get(obj);
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + nval + "\"";
                  }
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
}
