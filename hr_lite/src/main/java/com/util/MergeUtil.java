package com.util;

import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.User;
import com.bean.lov.KeyValue;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.resources.Constant;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;

public class MergeUtil
{
  protected static final Logger logger = Logger.getLogger(MergeUtil.class);
  
  public static KeyValue mergeApplicantData(JobApplicant applicant, User user, String content, String subjec, String functionName)
    throws Exception
  {
    logger.info("inside mergeApplicantData");
    KeyValue kv = new KeyValue();
    try
    {
      String keys = Constant.getValue(functionName);
      Map velocitymap = Constant.getVelocityMapping();
      
      Class applicantClass = applicant.getClass();
      Field[] appf = applicantClass.getFields();
      
      StringTokenizer apptokens = new StringTokenizer(keys, ",");
      List<String> keyList = new ArrayList();
      while (apptokens.hasMoreTokens())
      {
        String keyname = apptokens.nextToken();
        keyList.add(keyname);
        String filedannameproperty = (String)velocitymap.get(keyname);
        for (int i = 0; i < appf.length; i++) {
          if ((filedannameproperty != null) && (filedannameproperty.equalsIgnoreCase(appf[i].getName())))
          {
            content = content.replaceAll("##" + keyname + "##", String.valueOf(appf[i].get(applicant)));
            
            subjec = subjec.replaceAll("##" + keyname + "##", String.valueOf(appf[i].get(applicant)));
          }
        }
      }
      content = content.replaceAll("##applicant_no##", String.valueOf(applicant.getApplicantId()));
      String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant.getApplicantId() + "&secureid=" + applicant.getUuid();
      applicanturl = "<a href='" + applicanturl + "'" + "target='new'>" + Constant.getResourceStringValue("details", user.getLocale()) + "</a>";
      content = content.replaceAll("##applicant_url##", applicanturl);
      
      subjec = subjec.replaceAll("##applicant_no##", String.valueOf(applicant.getApplicantId()));
      subjec = subjec.replaceAll("##applicant_url##", applicanturl);
      if (keyList.contains("job_url"))
      {
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
        String joburl = Constant.getValue("external.url") + "jobs.do?method=jobdetailsn&reqid=" + applicant.getReqId() + "&secureid=" + jb.getUuid() + "&source=OTHERJOB";
        joburl = "<a href='" + joburl + "'" + "target='new'>" + jb.getJobTitle() + "</a>";
        
        content = content.replaceAll("##job_url##", joburl);
        subjec = subjec.replaceAll("##job_url##", joburl);
      }
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(user.getLocale())));
    }
    catch (Exception e)
    {
      logger.info("error on mergeApplicantData, applicant id" + applicant.getApplicantId());
      throw e;
    }
    kv.setKey(subjec);
    kv.setValue(content);
    
    return kv;
  }
  
  public static KeyValue mergeUserData(User user, String content, String subjec, String functionName)
    throws Exception
  {
    logger.info("inside mergeUserData");
    KeyValue kv = new KeyValue();
    try
    {
      functionName = functionName + "_user";
      String keys = Constant.getValue(functionName);
      Map velocitymap = Constant.getVelocityMapping();
      
      Class userClass = user.getClass();
      Field[] userf = userClass.getFields();
      
      StringTokenizer userstokens = new StringTokenizer(keys, ",");
      while (userstokens.hasMoreTokens())
      {
        String keyname = userstokens.nextToken();
        
        String filedannameproperty = (String)velocitymap.get(keyname);
        for (int i = 0; i < userf.length; i++) {
          if ((filedannameproperty != null) && (filedannameproperty.equalsIgnoreCase(userf[i].getName())))
          {
            content = content.replaceAll("##" + keyname + "##", String.valueOf(userf[i].get(user)));
            
            subjec = subjec.replaceAll("##" + keyname + "##", String.valueOf(userf[i].get(user)));
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("error on mergeUserData, user id" + user.getUserId());
      throw e;
    }
    kv.setKey(subjec);
    kv.setValue(content);
    
    return kv;
  }
  
  public static String getAvailableTags(String functionName)
  {
    String keys = Constant.getValue(functionName);
    if (Constant.getValue(functionName + "_user") != null) {
      keys = keys + "," + Constant.getValue(new StringBuilder().append(functionName).append("_user").toString());
    }
    String tags = "";
    if (keys != null)
    {
      StringTokenizer apptokens = new StringTokenizer(keys, ",");
      while ((apptokens != null) && (apptokens.hasMoreTokens()))
      {
        String keyname = apptokens.nextToken();
        tags = tags + "##" + keyname + "##" + "<br>";
      }
    }
    return tags;
  }
}
