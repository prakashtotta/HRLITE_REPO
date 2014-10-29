package com.util;

import com.resources.Constant;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.Logger;

public class DateUtil
{
  protected static final Logger logger = Logger.getLogger(DateUtil.class);
  public static String dateformatyahoocal = "EEE, d MMM yyyy HH:mm aaa";
  public static String dateformatstandard = Constant.getValue("defaultdateformat");
  public static String defaultdateformatforSQL = "MMMM d, yyyy";
  public static String dateformatindeedcal = "EEE, d MMM yyyy HH:mm:ss z";
  public static String yyyyMMdd = "yyyy-MM-dd";
  public static String jujuFeedDate = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  public static String olxFeedDate = "dd-MM-yyyy HH:mm:ss";
  public static String yyyyMMddSimpleHired = "yyyy/MM/dd";
  public static String ddMMyyyyTrovit = "dd/MM/yyyy";
  public static String twFeedDate = "yyyy-MM-dd HH:mm:ss";
  public static String mmDDyyyy = "MM/dd/yyyy";
  
  public static Calendar convertStringDateToCalendar(String strdate, String format)
  {
    Calendar cal = Calendar.getInstance();
    try
    {
      DateFormat formatter = new SimpleDateFormat(format);
      Date date = formatter.parse(strdate);
      
      cal.setTime(date);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return cal;
  }
  
  public static Calendar convertStringDateToCalendarWithException(String strdate, String format)
    throws Exception
  {
    Calendar cal = Calendar.getInstance();
    try
    {
      DateFormat formatter = new SimpleDateFormat(format);
      Date date = formatter.parse(strdate);
      
      cal.setTime(date);
    }
    catch (Exception e)
    {
      throw e;
    }
    return cal;
  }
  
  public static Date convertStringDateToDate(String strdate, String format)
  {
    Calendar cal = Calendar.getInstance();
    Date dt = null;
    try
    {
      DateFormat formatter = new SimpleDateFormat(format);
      Date date = formatter.parse(strdate);
      
      cal.setTime(date);
      dt = cal.getTime();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return dt;
  }
  
  public static String convertDateToStringDate(Date date, String format)
  {
    if (date == null) {
      return "";
    }
    String dt = null;
    try
    {
      DateFormat formatter = new SimpleDateFormat(format);
      
      dt = formatter.format(date);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return dt;
  }
  
  public static String convertDateDisplayFormat(Date date, com.bean.Locale locale)
  {
    if (date == null) {
      return "";
    }
    if (locale == null) {
      return "";
    }
    String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
    String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
    System.out.println(tx1);
    System.out.println(tx2);
    java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
    
    return SimpleDateFormat.getDateTimeInstance(1, 2, javaloc).format(date);
  }
  
  public static String convertFromTimezoneToTimezoneDate(String date, String format, String fromtimezone, String toTimezone)
  {
    String dt = null;
    try
    {
      DateFormat df1 = new SimpleDateFormat(format);
      df1.setTimeZone(TimeZone.getTimeZone(fromtimezone));
      

      Date d = df1.parse(date);
      
      DateFormat df2 = new SimpleDateFormat(format);
      if (toTimezone == null) {
        toTimezone = TimeZone.getDefault().getID();
      }
      df2.setTimeZone(TimeZone.getTimeZone(toTimezone));
      

      dt = df2.format(d);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return dt;
  }
  
  public static String convertFromTimezoneToTimezoneDateWithException(String date, String format, String fromtimezone, String toTimezone)
    throws Exception
  {
    String dt = null;
    try
    {
      DateFormat df1 = new SimpleDateFormat(format);
      df1.setTimeZone(TimeZone.getTimeZone(fromtimezone));
      

      Date d = df1.parse(date);
      
      DateFormat df2 = new SimpleDateFormat(format);
      if (toTimezone == null) {
        toTimezone = TimeZone.getDefault().getID();
      }
      df2.setTimeZone(TimeZone.getTimeZone(toTimezone));
      

      dt = df2.format(d);
    }
    catch (Exception e)
    {
      throw e;
    }
    return dt;
  }
  
  public static String convertToTimezoneDate(Date date, String format, String toTimezone)
  {
    String dt = null;
    try
    {
      DateFormat df1 = new SimpleDateFormat(format);
      df1.setTimeZone(TimeZone.getDefault());
      

      Date d = df1.parse(convertDateToStringDate(date, format));
      
      DateFormat df2 = new SimpleDateFormat(format);
      df2.setTimeZone(TimeZone.getTimeZone(toTimezone));
      

      dt = df2.format(d);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return dt;
  }
  
  public static String convertFromTimeToTimezone(Date date, String format, String fromTimezone, String toTimezone)
  {
    String dt = null;
    try
    {
      DateFormat df1 = new SimpleDateFormat(format);
      df1.setTimeZone(TimeZone.getTimeZone(fromTimezone));
      

      Date d = df1.parse(date.toString());
      
      DateFormat df2 = new SimpleDateFormat(format);
      df2.setTimeZone(TimeZone.getTimeZone(toTimezone));
      

      dt = df2.format(d);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return dt;
  }
  
  public static String convertSourceToTargetTimezone(Date date, String toTimezone, com.bean.Locale locale)
  {
    String datelast = null;
    if (date == null) {
      return "";
    }
    try
    {
      DateFormat secondFormat = new SimpleDateFormat();
      
      TimeZone secondTime = TimeZone.getTimeZone(toTimezone);
      
      secondFormat.setTimeZone(secondTime);
      
      String dt1 = secondFormat.format(date);
      


      DateFormat formatter = new SimpleDateFormat();
      Date date1 = formatter.parse(dt1);
      String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
      String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
      
      java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
      

      datelast = SimpleDateFormat.getDateTimeInstance(2, 3, javaloc).format(date1);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return datelast;
  }
  
  public static String convertSourceToTargetTimezoneReturnTime(Date date, String toTimezone, com.bean.Locale locale)
  {
    String datelast = null;
    if (date == null) {
      return "";
    }
    try
    {
      DateFormat secondFormat = new SimpleDateFormat();
      
      TimeZone secondTime = TimeZone.getTimeZone(toTimezone);
      
      secondFormat.setTimeZone(secondTime);
      
      String dt1 = secondFormat.format(date);
      


      DateFormat formatter = new SimpleDateFormat();
      Date date1 = formatter.parse(dt1);
      String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
      String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
      
      java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
      

      datelast = SimpleDateFormat.getTimeInstance(3, javaloc).format(date1);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return datelast;
  }
  
  public static String convertSourceToTargetTimezoneWithoutTime(Date date, String toTimezone, com.bean.Locale locale)
  {
    String datelast = null;
    try
    {
      if (date == null) {
        return "";
      }
      DateFormat secondFormat = new SimpleDateFormat();
      
      TimeZone secondTime = TimeZone.getTimeZone(toTimezone);
      
      secondFormat.setTimeZone(secondTime);
      
      String dt1 = secondFormat.format(date);
      


      DateFormat formatter = new SimpleDateFormat();
      Date date1 = formatter.parse(dt1);
      String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
      String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
      
      java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
      

      datelast = SimpleDateFormat.getDateInstance(2, javaloc).format(date1);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return datelast;
  }
  
  public static String convertSourceToTargetTimezoneDate(Date date, String toTimezone, com.bean.Locale locale)
  {
    String datelast = null;
    if (date == null) {
      return "";
    }
    try
    {
      DateFormat secondFormat = new SimpleDateFormat();
      
      TimeZone secondTime = TimeZone.getTimeZone(toTimezone);
      
      secondFormat.setTimeZone(secondTime);
      
      String dt1 = secondFormat.format(date);
      


      DateFormat formatter = new SimpleDateFormat();
      Date date1 = formatter.parse(dt1);
      String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
      String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
      
      java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
      

      datelast = SimpleDateFormat.getDateInstance(2, javaloc).format(date1);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return datelast;
  }
  
  public static String getDatePatternFormat(com.bean.Locale locale)
  {
    String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
    String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
    
    java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
    
    String datePattern = ((SimpleDateFormat)DateFormat.getDateInstance(2, javaloc)).toPattern();
    
    return datePattern;
  }
  
  public static String getDatePatternFormatLong(com.bean.Locale locale)
  {
    String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
    String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
    
    java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
    
    String datePattern = ((SimpleDateFormat)DateFormat.getDateInstance(1, javaloc)).toPattern();
    
    return datePattern;
  }
  
  public static String getLongDatePatternFormat(com.bean.Locale locale)
  {
    String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
    String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
    
    java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
    
    String datePattern = ((SimpleDateFormat)DateFormat.getDateInstance(1, javaloc)).toPattern();
    
    return datePattern;
  }
  
  public static String getShortDatePatternFormat(com.bean.Locale locale)
  {
    String tx1 = locale.getLocaleCode().substring(0, locale.getLocaleCode().lastIndexOf("_"));
    String tx2 = locale.getLocaleCode().substring(locale.getLocaleCode().lastIndexOf("_") + 1);
    
    java.util.Locale javaloc = new java.util.Locale(tx1, tx2);
    
    String datePattern = ((SimpleDateFormat)DateFormat.getDateInstance(3, javaloc)).toPattern();
    
    return datePattern;
  }
  
  public static String convertFaceBookDate(String fdate)
  {
    String fbdate = "";
    try
    {
      if (StringUtils.isNullOrEmpty(fdate)) {
        return "";
      }
      String year = fdate.substring(0, fdate.indexOf("-"));
      if ((StringUtils.isNullOrEmpty(year)) || (year.startsWith("0"))) {
        return "";
      }
      String month = fdate.substring(fdate.indexOf("-") + 1);
      int monthv = new Integer(month).intValue();
      if (monthv == 1) {
        month = "Jan ";
      } else if (monthv == 2) {
        month = "Feb ";
      } else if (monthv == 3) {
        month = "Mar ";
      } else if (monthv == 4) {
        month = "Apr ";
      } else if (monthv == 5) {
        month = "May ";
      } else if (monthv == 6) {
        month = "Jun ";
      } else if (monthv == 7) {
        month = "Jul ";
      } else if (monthv == 8) {
        month = "Aug ";
      } else if (monthv == 9) {
        month = "Sep ";
      } else if (monthv == 10) {
        month = "Oct ";
      } else if (monthv == 11) {
        month = "Nov ";
      } else if (monthv == 12) {
        month = "Dec ";
      }
      return month + year;
    }
    catch (Exception e)
    {
      logger.info("exception on facebookdateconvert", e);
    }
    return "";
  }
  
  public static String getInVcalformat(Calendar calGMT, String hr, String minute)
  {
    int hour_of = calGMT.get(11);
    String hour_of1 = "" + hour_of;
    

    int min_of = calGMT.get(12);
    String min_of1 = "" + min_of;
    
    int day_of = calGMT.get(5);
    String day_of1 = "" + day_of;
    

    int month_of = calGMT.get(2) + 1;
    String month_of1 = "" + month_of;
    
    int year = calGMT.get(1);
    
    Calendar rightNow1 = Calendar.getInstance();
    
    rightNow1.set(new Integer(year).intValue(), new Integer(month_of1).intValue() - 1, new Integer(day_of1).intValue(), hour_of + new Integer(hr).intValue(), min_of + new Integer(minute).intValue(), 0);
    



    hour_of = rightNow1.get(11);
    hour_of1 = "" + hour_of;
    

    min_of = rightNow1.get(12);
    min_of1 = "" + min_of;
    
    day_of = rightNow1.get(5);
    day_of1 = "" + day_of;
    

    month_of = rightNow1.get(2) + 1;
    month_of1 = "" + month_of;
    
    year = rightNow1.get(1);
    
    String vcal = year + month_of1 + day_of1 + "T" + hour_of1 + min_of1 + "00" + "Z";
    return vcal;
  }
  
  public static String getInVcalformat(Calendar calGMT)
  {
    int hour_of = calGMT.get(11);
    String hour_of1 = "" + hour_of;
    

    int min_of = calGMT.get(12);
    String min_of1 = "" + min_of;
    
    int day_of = calGMT.get(5);
    String day_of1 = "" + day_of;
    

    int month_of = calGMT.get(2) + 1;
    String month_of1 = "" + month_of;
    
    int year = calGMT.get(1);
    
    String vcal = year + month_of1 + day_of1 + "T" + hour_of1 + min_of1 + "00" + "Z";
    return vcal;
  }
  
  public static String convertFromTimezoneToTimezoneDate(Date date, String format, String fromtimezone, String toTimezone)
  {
    String dt = null;
    try
    {
      DateFormat df1 = new SimpleDateFormat(format);
      df1.setTimeZone(TimeZone.getTimeZone(fromtimezone));
      

      Date d = df1.parse(convertDateToStringDate(date, format));
      
      DateFormat df2 = new SimpleDateFormat(format);
      if (toTimezone == null) {
        toTimezone = TimeZone.getDefault().getID();
      }
      df2.setTimeZone(TimeZone.getTimeZone(toTimezone));
      

      dt = df2.format(d);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return dt;
  }
  
  public static void main(String[] args)
  {
    com.bean.Locale locale = new com.bean.Locale();
    locale.setLocaleCode("en_US");
    System.out.println(getLongDatePatternFormat(locale));
    
    Date dt = new Date();
    System.out.println(convertDateToStringDate(dt, mmDDyyyy));
  }
}
