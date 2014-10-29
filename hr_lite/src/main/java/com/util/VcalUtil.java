package com.util;

import java.util.Calendar;
import org.apache.log4j.Logger;

public class VcalUtil
{
  protected static final Logger logger = Logger.getLogger(VcalUtil.class);
  
  public static String getVcalformatForInterview(Calendar fromdattime, String hr, String minute, String url, String subject, String from, String fromemail)
  {
    logger.info("getVcalformatForInterview start");
    logger.info(fromdattime);
    logger.info(hr);
    logger.info(minute);
    StringBuffer buffer = new StringBuffer();
    
    String vcalfrom = DateUtil.getInVcalformat(fromdattime);
    
    String vcalto = DateUtil.getInVcalformat(fromdattime, hr, minute);
    
    String body = "=0D=0AApplicant profile=0D=0A" + url + "=0D=0A=0D=0A" + "Regards," + "=0D=0A" + from + " - " + fromemail + "=0D=0A";
    
    buffer.append("BEGIN:VCALENDAR\n");
    buffer.append("PRODID:-//Microsoft Corporation//Outlook 12.0 MIMEDIR//EN\n");
    buffer.append("VERSION:1.0\n");
    buffer.append("BEGIN:VEVENT\n");
    buffer.append("DTSTART:" + vcalfrom + "\n");
    buffer.append("DTEND:" + vcalto + "\n");
    buffer.append("UID:040000008200E00074C5B7101A82E008000000001047A5107682CB01000000000000000\n");
    buffer.append("0100000006689CCAC7A15BB4D8AD835400800E16A\n");
    buffer.append("DESCRIPTION;ENCODING=QUOTED-PRINTABLE:=\n");
    buffer.append(body);
    buffer.append("\n");
    buffer.append("SUMMARY:" + subject + "\n");
    buffer.append("PRIORITY:3\n");
    buffer.append("END:VEVENT\n");
    buffer.append("END:VCALENDAR\n");
    logger.info(buffer.toString());
    logger.info("getVcalformatForInterview end");
    return buffer.toString();
  }
}
