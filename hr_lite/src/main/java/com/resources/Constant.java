package com.resources;

import com.bean.InterviewState;
import com.bean.JobApplicant;
import com.bean.Locale;
import com.bean.ModeOfInterview;
import com.bean.ScreenCode;
import com.bean.User;
import com.bean.lov.CurrencyCodes;
import com.bean.lov.KeyValue;
import com.bean.lov.Months;
import com.bean.lov.Quarter;
import com.bean.lov.TimeLov;
import com.bean.lov.Years;
import com.common.Common;
import com.dao.UserDAO;
import com.util.StringUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;

public class Constant
{
  protected static final Logger logger = Logger.getLogger(Constant.class);
  public static List<String> screenCodeListAppScreenOtherPackage = new ArrayList();
  private static String userBufferString = "";
  public static List notforusergroupsearch = new ArrayList();
  public static Map keyValue = new HashMap();
  public static Map velocitymappings = new HashMap();
  public static List emailFunctionTypeList = new ArrayList();
  public static List skillsList = new ArrayList();
  public static List skillsYearsList = new ArrayList();
  public static List skillsRatingsList = new ArrayList();
  public static List uomList = new ArrayList();
  public static Map extra_resource_properties_key_value = new HashMap();
  public static Map resource_properties_english_us = new HashMap();
  public static Map resource_properties_french = new HashMap();
  public static List paginationRowsList = new ArrayList();
  public static Map applicantTableBeanColumnNameMap = new HashMap();
  public static Map applicantTableMap = new HashMap();
  public static Map applicantAttributeTypeMap = new HashMap();
  public static List userBufferList = new ArrayList();
  public static List dateTypeList = new ArrayList();
  public static Map xssMap = new HashMap();
  public static List notRemovedTokenMethodList = new ArrayList();
  public static List withoutTokenMethodList = new ArrayList();
  public static List trackingFrequencyList = new ArrayList();
  public static List hiringManagerInterviewStates = new ArrayList();
  public static Map packageMap = new HashMap();
  public static Map notApplicantTableBeanColumnNameMap = new HashMap();
  
  static
  {
    if ((getValue("user.cache.textarea.autosuggest") != null) && (getValue("user.cache.textarea.autosuggest").equals("yes"))) {
      setUserBuffer();
    }
    if ((paginationRowsList == null) || (paginationRowsList.size() < 1)) {
      paginationRowsList = getPginationRowsList();
    }
    if ((emailFunctionTypeList == null) || (emailFunctionTypeList.size() < 1)) {
      emailFunctionTypeList = getEmailFunctionsList();
    }
    if ((skillsList == null) || (skillsList.size() < 1)) {
      skillsList = getSkillsList();
    }
    if ((skillsYearsList == null) || (skillsYearsList.size() < 1)) {
      skillsYearsList = getSkillsYearsList();
    }
    if ((skillsRatingsList == null) || (skillsRatingsList.size() < 1)) {
      skillsRatingsList = getSkillsRatingsList();
    }
    if ((uomList == null) || (uomList.size() < 1)) {
      uomList = getRefferalUOMList();
    }
    if ((trackingFrequencyList == null) || (trackingFrequencyList.size() < 1)) {
      trackingFrequencyList = getTrackingFrequencyist();
    }
    if ((keyValue == null) || (keyValue.size() < 1)) {
      setkeyValue();
    }
    if ((velocitymappings == null) || (velocitymappings.size() < 1)) {
      setVelocityConstantMapping();
    }
    if ((resource_properties_english_us == null) || (resource_properties_english_us.size() < 1)) {
      setResourceLanguage();
    }
    if ((applicantAttributeTypeMap == null) || (applicantAttributeTypeMap.size() < 1)) {
      setApplicantAttributeTypeMap();
    }
    if ((xssMap == null) || (xssMap.size() < 1)) {
      setxssMap();
    }
    if ((packageMap == null) || (packageMap.size() < 1)) {
      setPackageSetting();
    }
    if ((dateTypeList == null) || (dateTypeList.size() < 1)) {
      setdateTypeList();
    }
    if ((screenCodeListAppScreenOtherPackage == null) || (screenCodeListAppScreenOtherPackage.size() < 1)) {
      setScreenCodeListAppScreenList();
    }
    if ((hiringManagerInterviewStates == null) || (hiringManagerInterviewStates.size() < 1)) {
      setHiringManagerInterviewStates();
    }
    if ((notforusergroupsearch == null) || (notforusergroupsearch.size() < 1)) {
      setnotforusergroupsearch();
    }
    if ((notRemovedTokenMethodList == null) || (notRemovedTokenMethodList.size() < 1)) {
      setNotRemovedTokenMethodList();
    }
    if ((withoutTokenMethodList == null) || (withoutTokenMethodList.size() < 1)) {
      setWithoutTokenMethodList();
    }
  }
  
  private static void setApplicantAttributeTypeMap()
  {
    JobApplicant jobapp = new JobApplicant();
    Class applicantClass = jobapp.getClass();
    Field[] f = applicantClass.getDeclaredFields();
    for (int k = 0; k < f.length; k++) {
      applicantAttributeTypeMap.put(f[k].getName(), f[k].getType().getName());
    }
  }
  
  private static void setUserBuffer()
  {
    List userList = UserDAO.getAllActiveUserNameEmail();
    for (int k = 0; k < userList.size(); k++)
    {
      User user = (User)userList.get(k);
      String fname = user.getFirstName();
      if (!StringUtils.isNullOrEmpty(fname)) {
        fname = fname.replace("'", "\\'");
      }
      String lname = user.getLastName();
      if (!StringUtils.isNullOrEmpty(lname)) {
        lname = lname.replace("'", "\\'");
      }
      String email = user.getEmailId();
      if (!StringUtils.isNullOrEmpty(email)) {
        email = email.replace("'", "\\'");
      }
      String temp = "'\"" + fname + " " + lname + "\"" + " " + "<" + email + ">" + "'";
      
      userBufferString = userBufferString + temp + ",";
    }
    userBufferString = userBufferString.substring(0, userBufferString.length() - 1);
  }
  
  public static String getUserBuffer()
  {
    return userBufferString;
  }
  
  public static void changeUserBufferNew(User user)
  {
    logger.info("changeUserBuffer" + user.getFirstName());
    
    String fname = user.getFirstName();
    if (!StringUtils.isNullOrEmpty(fname)) {
      fname = fname.replace("'", "\\'");
    }
    String lname = user.getLastName();
    if (!StringUtils.isNullOrEmpty(lname)) {
      lname = lname.replace("'", "\\'");
    }
    String email = user.getEmailId();
    if (!StringUtils.isNullOrEmpty(email)) {
      email = email.replace("'", "\\'");
    }
    String temp = "'\"" + fname + " " + lname + "\"" + " " + "<" + email + ">" + "'";
    userBufferString = userBufferString + "," + temp;
    

    logger.info("changeUserBuffer" + user.getFirstName());
  }
  
  public static void changeUserBufferUpdate(String olddata, User user)
  {
    String fname = user.getFirstName();
    if (!StringUtils.isNullOrEmpty(fname)) {
      fname = fname.replace("'", "\\'");
    }
    String lname = user.getLastName();
    if (!StringUtils.isNullOrEmpty(lname)) {
      lname = lname.replace("'", "\\'");
    }
    String email = user.getEmailId();
    if (!StringUtils.isNullOrEmpty(email)) {
      email = email.replace("'", "\\'");
    }
    String temp = "'\"" + fname + " " + lname + "\"" + " " + "<" + email + ">" + "'";
    if (!StringUtils.isNullOrEmpty(userBufferString))
    {
      String userBufferString1 = userBufferString;
      userBufferString = userBufferString.substring(0, userBufferString.indexOf(olddata)) + userBufferString1.substring(userBufferString1.indexOf(olddata) + olddata.length() + 1);
      userBufferString = userBufferString + "," + temp;
      userBufferString1 = null;
    }
    logger.info("changeUserBuffer" + user.getFirstName());
  }
  
  public static Map getVelocityMapping()
  {
    if (velocitymappings == null) {
      setVelocityConstantMapping();
    }
    return velocitymappings;
  }
  
  public static String getValue(String key)
  {
    String value = "";
    try
    {
      value = (String)keyValue.get(key);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return value;
  }
  
  public static String getResourceStringValue(String key, Locale locale)
  {
    String value = "";
    try
    {
      if ((locale != null) && (locale.getLocaleCode().equals("en_US")))
      {
        value = (String)resource_properties_english_us.get(key);
        if (value == null) {
          value = (String)resource_properties_english_us.get(extra_resource_properties_key_value.get(key));
        }
        if (value == null) {
          value = key;
        }
      }
      else
      {
        value = (String)resource_properties_english_us.get(key);
        if (value == null) {
          value = (String)resource_properties_english_us.get(extra_resource_properties_key_value.get(key));
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return value;
  }
  
  public static String getResourceStringValue(String key, Locale locale, String[] args)
  {
    String value = "";
    try
    {
      if ((locale != null) && (locale.getLocaleCode().equals("en_US")))
      {
        value = (String)resource_properties_english_us.get(key);
        if (value == null) {
          value = (String)resource_properties_english_us.get(extra_resource_properties_key_value.get(key));
        }
      }
      else
      {
        value = (String)resource_properties_english_us.get(key);
        if (value == null) {
          value = (String)resource_properties_english_us.get(extra_resource_properties_key_value.get(key));
        }
      }
      if ((args != null) && (args.length > 0)) {
        for (int i = 0; i < args.length; i++) {
          value = value.replace("{" + i + "}", args[i]);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return value;
  }
  
  public static String getResourceStringValueWithDefault(String key, Locale locale)
  {
    String value = "";
    try
    {
      if ((locale != null) && (locale.getLocaleCode().equals("en_US"))) {
        value = (String)resource_properties_english_us.get(key);
      } else {
        value = (String)resource_properties_english_us.get(key);
      }
      if (StringUtils.isNullOrEmpty(value)) {
        return key;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return value;
  }
  
  private static void setkeyValue()
  {
    logger.info("inside setkeyValue method");
    try
    {
      Properties propsEnv = new Properties();
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/job.properties");
      
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      String value = "";
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        keyValue.put(key, value);
      }
    }
    catch (Exception e)
    {
      logger.error(e);
      e.printStackTrace();
    }
    setkeyValueForConfigureComulns();
    setkeyValueForSchedulerProperties();
  }
  
  private static void setkeyValueForConfigureComulns()
  {
    logger.info("inside setkeyValueForConfigureComulns method");
    try
    {
      Properties propsEnv = new Properties();
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/configure_columns.properties");
      
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      String value = "";
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        keyValue.put(key, value);
      }
    }
    catch (Exception e)
    {
      logger.error(e);
      e.printStackTrace();
    }
  }
  
  private static void setkeyValueForSchedulerProperties()
  {
    logger.info("inside setkeyValueForSchedulerProperties method");
    try
    {
      Properties propsEnv = new Properties();
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/scheduler.properties");
      
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      String value = "";
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        keyValue.put(key, value);
      }
    }
    catch (Exception e)
    {
      logger.error(e);
      e.printStackTrace();
    }
  }
  
  private static void setapplicantTableBeanColumnNameMap()
  {
    logger.info("inside setapplicantTableBeanColumnNameMap method");
    
    applicantTableBeanColumnNameMap.put("applicantId", "application_id");
    applicantTableBeanColumnNameMap.put("fullName", "full_name");
    applicantTableBeanColumnNameMap.put("email", "email_id");
    applicantTableBeanColumnNameMap.put("city", "city");
    applicantTableBeanColumnNameMap.put("phone", "phone");
    applicantTableBeanColumnNameMap.put("heighestQualification", "heighest_qualification");
    applicantTableBeanColumnNameMap.put("interviewState", "interview_state");
    applicantTableBeanColumnNameMap.put("appliedDate", "applied_datetime");
    applicantTableBeanColumnNameMap.put("jobTitle", "req_name");
    applicantTableBeanColumnNameMap.put("referrerName", "referer_name");
    applicantTableBeanColumnNameMap.put("createdBy", "created_by");
    applicantTableBeanColumnNameMap.put("uuid", "uuid");
    applicantTableBeanColumnNameMap.put("joineddate", "joining_date");
    applicantTableBeanColumnNameMap.put("targerofferdate", "targerofferdate");
    applicantTableBeanColumnNameMap.put("offerreleasedate", "offerreleasedate");
    applicantTableBeanColumnNameMap.put("targetjoiningdate", "targetjoining_date");
    applicantTableBeanColumnNameMap.put("initiateJoiningProcess", "isInitiateJoiningProcess");
    applicantTableBeanColumnNameMap.put("referrerName", "referer_name");
    applicantTableBeanColumnNameMap.put("offerownerId", "offerownerId");
    applicantTableBeanColumnNameMap.put("offerownerName", "offerownerName");
    applicantTableBeanColumnNameMap.put("isofferownerGroup", "is_offer_owner_group");
    applicantTableBeanColumnNameMap.put("primarySkill", "primary_skill");
    applicantTableBeanColumnNameMap.put("scoreAve", "score_ave");
    applicantTableBeanColumnNameMap.put("dateofbirth", "dob");
    


    notApplicantTableBeanColumnNameMap.put("orgName", "org_name");
    notApplicantTableBeanColumnNameMap.put("hiringManager", "hiring_mgr_name");
    notApplicantTableBeanColumnNameMap.put("department", "dept_name");
    notApplicantTableBeanColumnNameMap.put("projectcode", "proj_code");
    notApplicantTableBeanColumnNameMap.put("percentage", "percentage");
    applicantTableBeanColumnNameMap.put("tagsName", "tag_name");
  }
  
  public static String getUIValue(String key)
  {
    String value = "";
    try
    {
      Properties propsEnv = new Properties();
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/job_resource.properties");
      
      value = (String)propsEnv.get(key);
      if (StringUtils.isNullOrEmpty(value)) {
        return key;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return value;
  }
  
  public static List getMonthList()
  {
    String value = "";
    List monthList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/months.properties");
      

      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      int i = 1;
      while (itr.hasNext())
      {
        itr.next();
        String key = "" + i;
        value = (String)propsEnv.get(key);
        Months m = new Months();
        m.setMonthkey(key);
        m.setMonthName(value);
        monthList.add(m);
        i++;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return monthList;
  }
  
  public static List getEducationsListOld()
  {
    String value = "";
    List eduList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/education.properties");
      


      Set<Object> keySet = propsEnv.keySet();
      TreeSet<Object> treeSet = new TreeSet(keySet);
      Iterator itr = treeSet.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        eduList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return eduList;
  }
  
  public static List getCriteriaList()
  {
    String value = "";
    List criteriaList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/criteria_ref_redumption_rule.properties");
      
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        criteriaList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return criteriaList;
  }
  
  public static List getStdworkinghoursunitList()
  {
    String value = "";
    List unitList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/stdworkinghoursunit.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        unitList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return unitList;
  }
  
  public static void setxssMap()
  {
    String value = "";
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/xsskeyvalue.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        

        xssMap.put(key, value);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private static List getEmailFunctionsList()
  {
    String value = "";
    List eduList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/email_function_type.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        eduList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return eduList;
  }
  
  private static List getSkillsList()
  {
    String value = "";
    List eduList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/skills.properties");
      
      Set<Object> keySet = propsEnv.keySet();
      TreeSet<Object> treeSet = new TreeSet(keySet);
      Iterator itr = treeSet.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        eduList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return eduList;
  }
  
  public static void setNotRemovedTokenMethodList()
  {
    notRemovedTokenMethodList.add("savequestionfilters");
    notRemovedTokenMethodList.add("savepreviousOrgdetails");
    notRemovedTokenMethodList.add("searchreqlistpage");
    notRemovedTokenMethodList.add("searchapplicantheader");
    notRemovedTokenMethodList.add("setuseridsinsession");
    notRemovedTokenMethodList.add("setuseridsinsessionallselect");
    notRemovedTokenMethodList.add("updateemailtemplate");
    notRemovedTokenMethodList.add("markpaid");
    notRemovedTokenMethodList.add("searchtemplatelistpage");
  }
  
  public static void setWithoutTokenMethodList()
  {
    withoutTokenMethodList.add("searchapplicantheader");
    
    withoutTokenMethodList.add("searchapplicantbyvendor");
    withoutTokenMethodList.add("searchtemplatelistpage");
    withoutTokenMethodList.add("searchreqlistpage");
    withoutTokenMethodList.add("emailidexistcheck");
    withoutTokenMethodList.add("jobreqListpage");
    withoutTokenMethodList.add("calendarView");
    withoutTokenMethodList.add("serachinapplicantpool");
    withoutTokenMethodList.add("saveUserdata");
    withoutTokenMethodList.add("myjobreqListRecruiter");
    withoutTokenMethodList.add("searchmenuheader");
    withoutTokenMethodList.add("addcommenttreeajax");
    withoutTokenMethodList.add("editcommenttree");
  }
  
  public static void setScreenCodeListAppScreenList()
  {
    screenCodeListAppScreenOtherPackage.add("APPLICANT_SCREEN_AGENCY");
    screenCodeListAppScreenOtherPackage.add("APPLICANT_SCREEN_REFERRAL");
    screenCodeListAppScreenOtherPackage.add("APPLICANT_SCREEN_TALENTPOOL");
    screenCodeListAppScreenOtherPackage.add("APPLICANT_SCREEN_EXTERNAL");
    screenCodeListAppScreenOtherPackage.add("APPLICANT_SCREEN_TALENTPOOL_EXTERNAL");
    screenCodeListAppScreenOtherPackage.add("APPLICANT_SCREEN_PROFILE");
  }
  
  private static void setdateTypeList()
  {
    String value = "";
    try
    {
      String datetypecolumns = getValue("datetype.columns");
      String[] columns = StringUtils.tokenize(datetypecolumns, ",");
      for (int i = 0; i < columns.length; i++) {
        dateTypeList.add(columns[i]);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private static void setnotforusergroupsearch()
  {
    notforusergroupsearch.add("addhighiringmanager");
    notforusergroupsearch.add("addhighiringmanagerreq");
  }
  
  public static List getSkillsRatingsList()
  {
    String value = "";
    List eduList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/skill_ratings.properties");
      
      Set<Object> keySet = propsEnv.keySet();
      TreeSet<Object> treeSet = new TreeSet(keySet);
      Iterator itr = treeSet.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        eduList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return eduList;
  }
  
  public static List getReasonTypeList()
  {
    String value = "";
    List reasonTypeList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/reasontype.properties");
      Set<Object> keySet = propsEnv.keySet();
      TreeSet<Object> treeSet = new TreeSet(keySet);
      Iterator itr = treeSet.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        reasonTypeList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return reasonTypeList;
  }
  
  private static List getSkillsYearsList()
  {
    String value = "";
    
    List eduList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/skill_years.properties");
      

      Set<Object> keySet = propsEnv.keySet();
      TreeSet<Object> treeSet = new TreeSet(keySet);
      Iterator itr = treeSet.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        eduList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return eduList;
  }
  
  private static List getCurrencyCodeList()
  {
    String value = "";
    List cuList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/currencycodes.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        CurrencyCodes m = new CurrencyCodes();
        m.setCuurecykey(key);
        m.setCurrencyName(value);
        cuList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return cuList;
  }
  
  private static List getRefferalUOMList()
  {
    String value = "";
    List aList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/refferal_uom.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        aList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return aList;
  }
  
  private static void setHiringManagerInterviewStates()
  {
    String value = "";
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/hiringmanagerInterviewStates.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        
        hiringManagerInterviewStates.add(value);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private static List getTrackingFrequencyist()
  {
    String value = "";
    List aList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/tracking_frequency.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        aList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return aList;
  }
  
  private static List getPginationRowsList()
  {
    String value = "";
    List aList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/paginationRows.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        aList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return aList;
  }
  
  public static List getApplicantUserActionsList(User user1)
  {
    String value = "";
    List aList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/applicant_user_actions.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(getResourceStringValue(value, user1.getLocale()));
        aList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return aList;
  }
  
  public static List getApplicantTypeList(User user1)
  {
    String value = "";
    List aList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/account_type.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        

        m.setKey(key);
        m.setValue(getResourceStringValue(value, user1.getLocale()));
        aList.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return aList;
  }
  
  private static void setVelocityConstantMapping()
  {
    String value = "";
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/velocitymapping.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        
        velocitymappings.put(key, value);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private static void setResourceLanguage()
  {
    String value = "";
    try
    {
      logger.info("Started English US resource properties");
      Properties propsEnvEnglish = new Properties();
      
      Constant con = new Constant();
      propsEnvEnglish = con.getPropertiesFromClasspath("conf/resource/resource.properties");
      
      Set set = propsEnvEnglish.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnvEnglish.get(key);
        
        resource_properties_english_us.put(key, value);
      }
      logger.info("End English US resource properties");
      
      logger.info("Started English US resource properties performance");
      propsEnvEnglish = new Properties();
      propsEnvEnglish = con.getPropertiesFromClasspath("conf/resource/performance_resource.properties");
      
      Set setres = propsEnvEnglish.keySet();
      Iterator itrRes = setres.iterator();
      while (itrRes.hasNext())
      {
        String key = (String)itrRes.next();
        value = (String)propsEnvEnglish.get(key);
        
        resource_properties_english_us.put(key, value);
      }
      logger.info("End English US resource properties performance");
      

      logger.info("Started English US resource properties leave");
      propsEnvEnglish = new Properties();
      propsEnvEnglish = con.getPropertiesFromClasspath("conf/resource/leave_resource.properties");
      
      Set setrel = propsEnvEnglish.keySet();
      Iterator itrRel = setrel.iterator();
      while (itrRel.hasNext())
      {
        String key = (String)itrRel.next();
        value = (String)propsEnvEnglish.get(key);
        
        resource_properties_english_us.put(key, value);
      }
      logger.info("End English US resource properties leave");
      




      extra_resource_properties_key_value.put("Applicant user created", "Applicant_user_created");
      extra_resource_properties_key_value.put("Applicant tagged", "Applicant_tagged");
      extra_resource_properties_key_value.put("Application Submitted", "Application_Submitted");
      extra_resource_properties_key_value.put("In Screening", "In_Screening");
      extra_resource_properties_key_value.put("On Board - Joined", "On_Board_Joined");
      extra_resource_properties_key_value.put("Offer Released", "Offer_Released");
      extra_resource_properties_key_value.put("Offer Initiated-In Approval", "Offer_Initiated_In_Approval");
      extra_resource_properties_key_value.put("Ready for Release Offer", "Ready_for_Release_Offer");
      extra_resource_properties_key_value.put("Offer rejected by approver", "Offer_rejected_by_approver");
      extra_resource_properties_key_value.put("In Approval-Rejected", "In_Approval_Rejected");
      extra_resource_properties_key_value.put("Cleared-In Screening", "Cleared_In_Screening");
      extra_resource_properties_key_value.put("Offer approved by approver", "Offer_approved_by_approver");
      extra_resource_properties_key_value.put("Rejected-In Screening", "Rejected_In_Screening");
      extra_resource_properties_key_value.put("In Screening-Recalled", "In_Screening_Recalled");
      extra_resource_properties_key_value.put("In Screening-Recalled-Recalled", "In_Screening_Recalled_Recalled");
      extra_resource_properties_key_value.put("Applicant action added", "Applicant_action_added");
      extra_resource_properties_key_value.put("Applicant action deleted", "Applicant_action_deleted");
      extra_resource_properties_key_value.put("Applicant action updated", "Applicant_action_updated");
      extra_resource_properties_key_value.put("In Review", "In_Review");
      extra_resource_properties_key_value.put("Cleared Review", "Cleared_Review");
      extra_resource_properties_key_value.put("On Hold", "On_Hold");
      extra_resource_properties_key_value.put("Resume Screening", "Resume_Screening");
      extra_resource_properties_key_value.put("Offer Accepted", "Offer_Accepted");
      extra_resource_properties_key_value.put("Offer Declined", "Offer_Declined");
      extra_resource_properties_key_value.put("Offer In Negotitaion", "Offer_In_Negotitaion");
      extra_resource_properties_key_value.put("OnHold-In Screening", "OnHold_In_Screening");
      extra_resource_properties_key_value.put("OnHold-Interview Round-1", "OnHold_Interview_Round_1");
      extra_resource_properties_key_value.put("OnHold-Interview Round-2", "OnHold_Interview_Round_2");
      extra_resource_properties_key_value.put("OnHold-Interview Round-3", "OnHold_Interview_Round_3");
      extra_resource_properties_key_value.put("OnHold-Interview Round-4", "OnHold_Interview_Round_4");
      extra_resource_properties_key_value.put("OnHold-Interview Round-5", "OnHold_Interview_Round_5");
      extra_resource_properties_key_value.put("OnHold-Interview Round-6", "OnHold_Interview_Round_6");
      extra_resource_properties_key_value.put("OnHold-Interview Round-7", "OnHold_Interview_Round_7");
      extra_resource_properties_key_value.put("OnHold-Interview Round-8", "OnHold_Interview_Round_8");
      extra_resource_properties_key_value.put("OnHold-Interview Round-9", "OnHold_Interview_Round_9");
      extra_resource_properties_key_value.put("Interview Round-1", "Interview_Round_1");
      extra_resource_properties_key_value.put("Interview Round-2", "Interview_Round_2");
      extra_resource_properties_key_value.put("Interview Round-3", "Interview_Round_3");
      extra_resource_properties_key_value.put("Interview Round-4", "Interview_Round_4");
      extra_resource_properties_key_value.put("Interview Round-5", "Interview_Round_5");
      extra_resource_properties_key_value.put("Interview Round-6", "Interview_Round_6");
      extra_resource_properties_key_value.put("Interview Round-7", "Interview_Round_7");
      extra_resource_properties_key_value.put("Interview Round-8", "Interview_Round_8");
      extra_resource_properties_key_value.put("Interview Round-9", "Interview_Round_9");
      extra_resource_properties_key_value.put("Cleared-Interview Round-1", "Cleared_Interview_Round_1");
      extra_resource_properties_key_value.put("Cleared-Interview Round-2", "Cleared_Interview_Round_2");
      extra_resource_properties_key_value.put("Cleared-Interview Round-3", "Cleared_Interview_Round_3");
      extra_resource_properties_key_value.put("Cleared-Interview Round-4", "Cleared_Interview_Round_4");
      extra_resource_properties_key_value.put("Cleared-Interview Round-5", "Cleared_Interview_Round_5");
      extra_resource_properties_key_value.put("Cleared-Interview Round-6", "Cleared_Interview_Round_6");
      extra_resource_properties_key_value.put("Cleared-Interview Round-7", "Cleared_Interview_Round_7");
      extra_resource_properties_key_value.put("Cleared-Interview Round-8", "Cleared_Interview_Round_8");
      extra_resource_properties_key_value.put("Cleared-Interview Round-9", "Cleared_Interview_Round_9");
      extra_resource_properties_key_value.put("Rejected-Interview Round-1", "Rejected_Interview_Round_1");
      extra_resource_properties_key_value.put("Rejected-Interview Round-2", "Rejected_Interview_Round_2");
      extra_resource_properties_key_value.put("Rejected-Interview Round-3", "Rejected_Interview_Round_3");
      extra_resource_properties_key_value.put("Rejected-Interview Round-4", "Rejected_Interview_Round_4");
      extra_resource_properties_key_value.put("Rejected-Interview Round-5", "Rejected_Interview_Round_5");
      extra_resource_properties_key_value.put("Rejected-Interview Round-6", "Rejected_Interview_Round_6");
      extra_resource_properties_key_value.put("Rejected-Interview Round-7", "Rejected_Interview_Round_7");
      extra_resource_properties_key_value.put("Rejected-Interview Round-8", "Rejected_Interview_Round_8");
      extra_resource_properties_key_value.put("Rejected-Interview Round-9", "Rejected_Interview_Round_9");
      extra_resource_properties_key_value.put("Interview Round-1-Recalled", "Interview_Round_1_Recalled");
      extra_resource_properties_key_value.put("Interview Round-2-Recalled", "Interview_Round_2_Recalled");
      extra_resource_properties_key_value.put("Interview Round-3-Recalled", "Interview_Round_3_Recalled");
      extra_resource_properties_key_value.put("Interview Round-4-Recalled", "Interview_Round_4_Recalled");
      extra_resource_properties_key_value.put("Interview Round-5-Recalled", "Interview_Round_5_Recalled");
      extra_resource_properties_key_value.put("Interview Round-6-Recalled", "Interview_Round_6_Recalled");
      extra_resource_properties_key_value.put("Interview Round-7-Recalled", "Interview_Round_7_Recalled");
      extra_resource_properties_key_value.put("Interview Round-8-Recalled", "Interview_Round_8_Recalled");
      extra_resource_properties_key_value.put("Interview Round-9-Recalled", "Interview_Round_9_Recalled");
      extra_resource_properties_key_value.put("Applicant marked for deletion", "Applicant_marked_for_deletion");
      extra_resource_properties_key_value.put("Applicant restored", "Applicant_restored");
      extra_resource_properties_key_value.put("Offer Canceled", "Offer_Canceled");
      extra_resource_properties_key_value.put("Mark deleted-Application Submitted", "Mark_deleted_Application_Submitted");
      extra_resource_properties_key_value.put("Undo-deleted-Application Submitted", "Undo_deleted_Application_Submitted");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-1", "Mark_deleted_Interview_Round_1");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-2", "Mark_deleted_Interview_Round_2");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-3", "Mark_deleted_Interview_Round_3");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-4", "Mark_deleted_Interview_Round_4");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-5", "Mark_deleted_Interview_Round_5");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-6", "Mark_deleted_Interview_Round_6");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-7", "Mark_deleted_Interview_Round_7");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-8", "Mark_deleted_Interview_Round_8");
      extra_resource_properties_key_value.put("Mark deleted-Interview Round-9", "Mark_deleted_Interview_Round_9");
      extra_resource_properties_key_value.put("Reassign review", "Reassign_review");
      extra_resource_properties_key_value.put("Requisition approved", "Requisition_approved");
      extra_resource_properties_key_value.put("Talent Pool", "Talent_Pool");
      extra_resource_properties_key_value.put("Offer Accepted - On behalf of", "Offer_Accepted_On_behalf_of");
      extra_resource_properties_key_value.put("Offer Declined - On behalf of", "Offer_Declined_On_behalf_of");
      extra_resource_properties_key_value.put("Offer In Negotitaion - On behalf of", "Offer_In_Negotitaion_On_behalf_of");
      extra_resource_properties_key_value.put("Requisition Approval", "Requisition_Approval");
      extra_resource_properties_key_value.put("Requistion Publish", "Requistion_Publish");
      extra_resource_properties_key_value.put("Applicant Review", "Applicant_Review");
      extra_resource_properties_key_value.put("Offer Release", "Offer_Release");
      extra_resource_properties_key_value.put("Offer Approval", "Offer_Approval");
      extra_resource_properties_key_value.put("OnBoarding", "OnBoarding");
      extra_resource_properties_key_value.put("Applicant In Queue", "Applicant_In_Queue");
      extra_resource_properties_key_value.put("Exam Screening", "Exam_Screening");
      extra_resource_properties_key_value.put("Exam Screening Fail", "Exam_Screening_Fail");
      extra_resource_properties_key_value.put("Exam Screening Passed", "Exam_Screening_Passed");
      extra_resource_properties_key_value.put("Exam Screening deleted", "Exam_Screening_deleted");
      extra_resource_properties_key_value.put("DECLINED_REVIEW", "DECLINED_REVIEW");
      extra_resource_properties_key_value.put("Initiate OnBoarding", "Initiate_OnBoarding");
      extra_resource_properties_key_value.put("OnBoarding Task Completed", "OnBoarding_Task_Completed");
      extra_resource_properties_key_value.put("ADD_OFFER_ATTACHMENT", "ADD_OFFER_ATTACHMENT");
      extra_resource_properties_key_value.put("DELETE_OFFER_ATTACHMENT", "DELETE_OFFER_ATTACHMENT");
      extra_resource_properties_key_value.put("REDEMPTION_PAID", "REDEMPTION_PAID");
    }
    catch (Exception e)
    {
      logger.error(e);
      e.printStackTrace();
    }
  }
  
  public static List getTimeList()
  {
    String value = "";
    List timeList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/time.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      int i = 1;
      while (itr.hasNext())
      {
        itr.next();
        String key = "" + i;
        value = (String)propsEnv.get(key);
        TimeLov m = new TimeLov();
        m.setTimekey(key);
        m.setTimeName(value);
        timeList.add(m);
        i++;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return timeList;
  }
  
  public static List getQuarterList()
  {
    String value = "";
    List qList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/quarter.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      int i = 1;
      while (itr.hasNext())
      {
        itr.next();
        String key = "" + i;
        value = (String)propsEnv.get(key);
        Quarter m = new Quarter();
        m.setQuarterkey(key);
        m.setQuarterName(value);
        qList.add(m);
        i++;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return qList;
  }
  
  public static List getyearsList()
  {
    String value = "";
    List qList = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/years.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      int i = 1;
      while (itr.hasNext())
      {
        itr.next();
        String key = "" + i;
        value = (String)propsEnv.get(key);
        Years m = new Years();
        m.setYearkey(key);
        m.setYearName(value);
        qList.add(m);
        i++;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return qList;
  }
  
  public static void setPackageSetting()
  {
    try
    {
      Properties propsEnv = new Properties();
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/packagesFunctions.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        String value = (String)propsEnv.get(key);
        packageMap.put(key, value);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static boolean isPackageContainFunction(String pkg, String fn)
  {
    boolean isPresent = false;
    if (pkg == null) {
      return false;
    }
    if (packageMap != null)
    {
      String value = (String)packageMap.get(pkg);
      
      List lst = StringUtils.tokenizeString(value, ",");
      if (lst != null) {
        if (lst.contains(fn)) {
          isPresent = true;
        }
      }
    }
    return isPresent;
  }
  
  public static void main(String[] args)
  {
    getMonthList();
  }
  
  public static List getModeOfInterviews()
  {
    List modeofinterviewList = new ArrayList();
    ModeOfInterview md1 = new ModeOfInterview();
    ModeOfInterview md2 = new ModeOfInterview();
    ModeOfInterview md3 = new ModeOfInterview();
    ModeOfInterview md4 = new ModeOfInterview();
    md1.setModofinterviewid(0);
    md1.setModofinterviewName("");
    md2.setModofinterviewid(1);
    md2.setModofinterviewName("Personal");
    md3.setModofinterviewid(2);
    md3.setModofinterviewName("Telephonic");
    md4.setModofinterviewid(3);
    md4.setModofinterviewName("Video Conference");
    modeofinterviewList.add(md1);
    modeofinterviewList.add(md2);
    modeofinterviewList.add(md3);
    modeofinterviewList.add(md4);
    
    return modeofinterviewList;
  }
  
  public static void setnterviewStates(List interviewstateList)
  {
    InterviewState int2 = new InterviewState();
    InterviewState int3 = new InterviewState();
    InterviewState int4 = new InterviewState();
    InterviewState int5 = new InterviewState();
    InterviewState int6 = new InterviewState();
    InterviewState int7 = new InterviewState();
    InterviewState int8 = new InterviewState();
    InterviewState int9 = new InterviewState();
    InterviewState int10 = new InterviewState();
    InterviewState int11 = new InterviewState();
    
    int2.setInterviewstateCode("Application Submitted");
    int2.setInterviewstateName("Application Submitted");
    int3.setInterviewstateCode("Screening");
    int3.setInterviewstateName("Screening");
    int4.setInterviewstateCode("Interview");
    int4.setInterviewstateName("In Interview Process");
    int5.setInterviewstateCode("Offer");
    int5.setInterviewstateName("In Offer Process");
    int6.setInterviewstateCode("Rejected");
    int6.setInterviewstateName("Rejected");
    int7.setInterviewstateCode("OnHold");
    int7.setInterviewstateName("OnHold");
    int8.setInterviewstateCode("On Board - Joined");
    int8.setInterviewstateName("On Board - Joined");
    int9.setInterviewstateCode("Offer Accepted");
    int9.setInterviewstateName("Offer Accepted");
    int10.setInterviewstateCode("Mark deleted");
    int10.setInterviewstateName("Mark deleted");
    int11.setInterviewstateCode("Talent Pool");
    int11.setInterviewstateName("Talent Pool");
    interviewstateList.add(int2);
    interviewstateList.add(int3);
    interviewstateList.add(int4);
    interviewstateList.add(int5);
    interviewstateList.add(int6);
    interviewstateList.add(int7);
    interviewstateList.add(int8);
    interviewstateList.add(int9);
    interviewstateList.add(int10);
  }
  
  public static List getInterviewStates()
  {
    List interviewstateList = new ArrayList();
    InterviewState int1 = new InterviewState();
    
    int1.setInterviewstateCode("NoValue");
    int1.setInterviewstateName("");
    
    interviewstateList.add(int1);
    
    setnterviewStates(interviewstateList);
    
    return interviewstateList;
  }
  
  public static List getInterviewStatesWithOutBlank()
  {
    List interviewstateList = new ArrayList();
    
    setnterviewStates(interviewstateList);
    
    return interviewstateList;
  }
  
  public static List getInterviewStatesForMassEmail()
  {
    List interviewstateList = new ArrayList();
    InterviewState int2 = new InterviewState();
    InterviewState int3 = new InterviewState();
    InterviewState int4 = new InterviewState();
    InterviewState int5 = new InterviewState();
    InterviewState int6 = new InterviewState();
    InterviewState int7 = new InterviewState();
    InterviewState int8 = new InterviewState();
    InterviewState int9 = new InterviewState();
    InterviewState int10 = new InterviewState();
    InterviewState int11 = new InterviewState();
    InterviewState int12 = new InterviewState();
    
    int2.setInterviewstateCode("Application Submitted");
    int2.setInterviewstateName("Application Submitted");
    int3.setInterviewstateCode("Screening");
    int3.setInterviewstateName("Screening");
    int4.setInterviewstateCode("Interview");
    int4.setInterviewstateName("In Interview Process");
    int5.setInterviewstateCode("Offer");
    int5.setInterviewstateName("In Offer Process");
    int6.setInterviewstateCode("Rejected");
    int6.setInterviewstateName("Rejected");
    int7.setInterviewstateCode("OnHold");
    int7.setInterviewstateName("OnHold");
    int8.setInterviewstateCode("On Board - Joined");
    int8.setInterviewstateName("On Board - Joined");
    int9.setInterviewstateCode("Offer Accepted");
    int9.setInterviewstateName("Offer Accepted");
    int10.setInterviewstateCode("Offer Canceled");
    int10.setInterviewstateName("Offer Canceled");
    int11.setInterviewstateCode("Offer Declined");
    int11.setInterviewstateName("Offer Declined");
    int12.setInterviewstateCode("Mark deleted");
    int12.setInterviewstateName("Mark deleted");
    
    interviewstateList.add(int2);
    interviewstateList.add(int3);
    interviewstateList.add(int4);
    interviewstateList.add(int5);
    interviewstateList.add(int6);
    interviewstateList.add(int7);
    interviewstateList.add(int8);
    interviewstateList.add(int9);
    interviewstateList.add(int10);
    interviewstateList.add(int11);
    interviewstateList.add(int12);
    
    return interviewstateList;
  }
  
  public static List getScreenCodes()
  {
    List screenCodesList = new ArrayList();
    ScreenCode int1 = new ScreenCode();
    ScreenCode int2 = new ScreenCode();
    ScreenCode int3 = new ScreenCode();
    
    int1.setScreenCode("NoValue");
    int1.setScreenName("");
    int2.setScreenCode("APPLICANT_SCREEN");
    int2.setScreenName("APPLICANT_SCREEN");
    int3.setScreenCode("APPLICANT_SCREEN_MYPROFILE");
    int3.setScreenName("APPLICANT_SCREEN_MYPROFILE");
    
    screenCodesList.add(int1);
    screenCodesList.add(int2);
    screenCodesList.add(int3);
    
    return screenCodesList;
  }
  
  public static List getCommentVisibleList()
  {
    List commentVisibleList = new ArrayList();
    KeyValue int1 = new KeyValue();
    KeyValue int2 = new KeyValue();
    KeyValue int3 = new KeyValue();
    
    int1.setKey("N");
    int1.setValue("Internal");
    int2.setKey("Y");
    int2.setValue("Applicant");
    int3.setKey("R");
    int3.setValue("Referrer");
    
    commentVisibleList.add(int1);
    commentVisibleList.add(int2);
    commentVisibleList.add(int3);
    
    return commentVisibleList;
  }
  
  public static List getFilterCriterias(String type)
  {
    List filterCriteriasList = new ArrayList();
    KeyValue int1 = new KeyValue();
    KeyValue int2 = new KeyValue();
    KeyValue int3 = new KeyValue();
    KeyValue int4 = new KeyValue();
    KeyValue int5 = new KeyValue();
    KeyValue int6 = new KeyValue();
    KeyValue int7 = new KeyValue();
    KeyValue int8 = new KeyValue();
    KeyValue int9 = new KeyValue();
    KeyValue int10 = new KeyValue();
    KeyValue int11 = new KeyValue();
    KeyValue int12 = new KeyValue();
    
    int1.setKey("NoValue");
    int1.setValue("");
    int2.setKey("EQUALS");
    int2.setValue("EQUALS");
    int3.setKey("NOT_EQUALS");
    int3.setValue("NOT_EQUALS");
    int4.setKey("NOT_EMPTY");
    int4.setValue("NOT_EMPTY");
    int5.setKey("BETWEEN");
    int5.setValue("BETWEEN");
    int6.setKey("GREATER_THAN");
    int6.setValue("GREATER_THAN");
    int7.setKey("LESS_THAN");
    int7.setValue("LESS_THAN");
    int8.setKey("STARTS_WITH");
    int8.setValue("STARTS_WITH");
    int9.setKey("ENDS_WITH");
    int9.setValue("ENDS_WITH");
    int10.setKey("CONTAINS");
    int10.setValue("CONTAINS");
    int11.setKey("EMPTY");
    int11.setValue("EMPTY");
    

    filterCriteriasList.add(int1);
    if (type != null) {
      if ((type.equals("text")) || (type.equals("textarea")))
      {
        filterCriteriasList.add(int2);
        filterCriteriasList.add(int3);
        filterCriteriasList.add(int4);
        filterCriteriasList.add(int11);
        filterCriteriasList.add(int8);
        filterCriteriasList.add(int9);
        filterCriteriasList.add(int10);
      }
      else if (type.equals("numeric"))
      {
        filterCriteriasList.add(int2);
        filterCriteriasList.add(int3);
        filterCriteriasList.add(int4);
        filterCriteriasList.add(int5);
        filterCriteriasList.add(int6);
        filterCriteriasList.add(int7);
      }
      else if (type.equals("date"))
      {
        filterCriteriasList.add(int2);
        filterCriteriasList.add(int3);
        filterCriteriasList.add(int4);
        filterCriteriasList.add(int5);
        filterCriteriasList.add(int6);
        filterCriteriasList.add(int7);
      }
      else if (type.equals("list"))
      {
        filterCriteriasList.add(int2);
        filterCriteriasList.add(int3);
      }
    }
    return filterCriteriasList;
  }
  
  public static List getInterviewStatesForOnBoarding()
  {
    List interviewstateList = new ArrayList();
    InterviewState int1 = new InterviewState();
    InterviewState int2 = new InterviewState();
    InterviewState int3 = new InterviewState();
    int1.setInterviewstateCode("NoValue");
    int1.setInterviewstateName("");
    int2.setInterviewstateCode("Offer Accepted");
    int2.setInterviewstateName("Offer Accepted");
    int3.setInterviewstateCode("On Board - Joined");
    int3.setInterviewstateName("On Board - Joined");
    interviewstateList.add(int1);
    interviewstateList.add(int2);
    interviewstateList.add(int3);
    
    return interviewstateList;
  }
  
  public static List getOnBoardingProcessStatus(User user1)
  {
    List onboardingProcessList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    
    key1.setKey("Not started");
    key1.setValue(getResourceStringValue("onboarding.process.not.started", user1.getLocale()));
    key2.setKey("Started");
    key2.setValue(getResourceStringValue("onboarding.process.started", user1.getLocale()));
    key3.setKey("Completed");
    key3.setValue(getResourceStringValue("onboarding.process.completed", user1.getLocale()));
    
    onboardingProcessList.add(key1);
    onboardingProcessList.add(key2);
    onboardingProcessList.add(key3);
    
    return onboardingProcessList;
  }
  
  public static List getLanguageFluencyList(User user1)
  {
    List languagesfluencyList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    
    key1.setKey("NoValue");
    key1.setValue("");
    key2.setKey("READING");
    key2.setValue(getResourceStringValue("hr.user.Qualifications.Language.Reading", user1.getLocale()));
    key3.setKey("SPEAKING");
    key3.setValue(getResourceStringValue("hr.user.Qualifications.Language.Speaking", user1.getLocale()));
    key4.setKey("WRITING");
    key4.setValue(getResourceStringValue("hr.user.Qualifications.Language.Writing", user1.getLocale()));
    
    languagesfluencyList.add(key1);
    languagesfluencyList.add(key2);
    languagesfluencyList.add(key3);
    languagesfluencyList.add(key4);
    
    return languagesfluencyList;
  }
  
  public static List getSearchCriterisString(User user1)
  {
    List criList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    KeyValue key5 = new KeyValue();
    KeyValue key6 = new KeyValue();
    
    key1.setKey("STARTS_WITH");
    key1.setValue(getResourceStringValue("STARTS_WITH", user1.getLocale()));
    key2.setKey("ENDS_WITH");
    key2.setValue(getResourceStringValue("ENDS_WITH", user1.getLocale()));
    key3.setKey("CONTAINS");
    key3.setValue(getResourceStringValue("CONTAINS", user1.getLocale()));
    key4.setKey("EQUALS");
    key4.setValue(getResourceStringValue("EQUALS", user1.getLocale()));
    key5.setKey("EMPTY");
    key5.setValue(getResourceStringValue("EMPTY", user1.getLocale()));
    key6.setKey("NOT_EMPTY");
    key6.setValue(getResourceStringValue("NOT_EMPTY", user1.getLocale()));
    
    criList.add(key1);
    criList.add(key2);
    criList.add(key3);
    criList.add(key4);
    criList.add(key5);
    criList.add(key6);
    
    return criList;
  }
  
  public static List getSearchCriterisNumeric(User user1)
  {
    List criList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    KeyValue key5 = new KeyValue();
    KeyValue key6 = new KeyValue();
    KeyValue key7 = new KeyValue();
    
    key1.setKey("EQUALS");
    key1.setValue(getResourceStringValue("EQUALS", user1.getLocale()));
    key2.setKey("GREATER_THAN");
    key2.setValue(getResourceStringValue("GREATER_THAN", user1.getLocale()));
    key3.setKey("GREATER_THAN_EQUAL");
    key3.setValue(getResourceStringValue("GREATER_THAN_EQUAL", user1.getLocale()));
    key4.setKey("LESS_THAN");
    key4.setValue(getResourceStringValue("LESS_THAN", user1.getLocale()));
    key5.setKey("LESS_THAN_EQUAL");
    key5.setValue(getResourceStringValue("LESS_THAN_EQUAL", user1.getLocale()));
    key6.setKey("NOT_EQUALS");
    key6.setValue(getResourceStringValue("NOT_EQUALS", user1.getLocale()));
    key7.setKey("BETWEEN");
    key7.setValue(getResourceStringValue("BETWEEN", user1.getLocale()));
    


    criList.add(key1);
    criList.add(key2);
    criList.add(key3);
    criList.add(key4);
    criList.add(key5);
    criList.add(key6);
    criList.add(key7);
    
    return criList;
  }
  
  public static List getSearchCriterisDate(User user1)
  {
    List criList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    KeyValue key5 = new KeyValue();
    KeyValue key6 = new KeyValue();
    KeyValue key7 = new KeyValue();
    KeyValue key8 = new KeyValue();
    KeyValue key9 = new KeyValue();
    
    key1.setKey("EQUALS");
    key1.setValue(getResourceStringValue("EQUALS", user1.getLocale()));
    key2.setKey("GREATER_THAN");
    key2.setValue(getResourceStringValue("GREATER_THAN", user1.getLocale()));
    key3.setKey("GREATER_THAN_EQUAL");
    key3.setValue(getResourceStringValue("GREATER_THAN_EQUAL", user1.getLocale()));
    key4.setKey("LESS_THAN");
    key4.setValue(getResourceStringValue("LESS_THAN", user1.getLocale()));
    key5.setKey("LESS_THAN_EQUAL");
    key5.setValue(getResourceStringValue("LESS_THAN_EQUAL", user1.getLocale()));
    key6.setKey("NOT_EQUALS");
    key6.setValue(getResourceStringValue("NOT_EQUALS", user1.getLocale()));
    key7.setKey("BETWEEN");
    key7.setValue(getResourceStringValue("BETWEEN", user1.getLocale()));
    key8.setKey("EMPTY");
    key8.setValue(getResourceStringValue("EMPTY", user1.getLocale()));
    key9.setKey("NOT_EMPTY");
    key9.setValue(getResourceStringValue("NOT_EMPTY", user1.getLocale()));
    

    criList.add(key1);
    criList.add(key2);
    criList.add(key3);
    criList.add(key4);
    criList.add(key5);
    criList.add(key6);
    criList.add(key7);
    criList.add(key8);
    criList.add(key9);
    
    return criList;
  }
  
  public static List getRequistionStates(User user1)
  {
    List reqstate = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    KeyValue key5 = new KeyValue();
    
    key1.setKey("Draft");
    key1.setValue(getResourceStringValue("Requisition.draft", user1.getLocale()));
    key2.setKey("In Approval");
    key2.setValue(getResourceStringValue("Requisition.inapproval", user1.getLocale()));
    key3.setKey("Approved");
    key3.setValue(getResourceStringValue("Requisition.approved", user1.getLocale()));
    key4.setKey("In Approval-Rejected");
    key4.setValue(getResourceStringValue("Requisition.inapprovalrejected", user1.getLocale()));
    key5.setKey("Active");
    key5.setValue(getResourceStringValue("Requisition.active", user1.getLocale()));
    
    reqstate.add(key1);
    reqstate.add(key2);
    reqstate.add(key3);
    reqstate.add(key4);
    reqstate.add(key5);
    
    return reqstate;
  }
  
  public static List getRequistionStatuses(User user1)
  {
    List reqstatus = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    KeyValue key5 = new KeyValue();
    
    key1.setKey("Open");
    key1.setValue(getResourceStringValue("Requisition.open", user1.getLocale()));
    key2.setKey("Closed");
    key2.setValue(getResourceStringValue("Requisition.closed", user1.getLocale()));
    key3.setKey("Deleted");
    key3.setValue(getResourceStringValue("Requisition.deleted", user1.getLocale()));
    

    reqstatus.add(key1);
    reqstatus.add(key2);
    reqstatus.add(key3);
    
    return reqstatus;
  }
  
  public static List getRequistionTemplateStatuses(User user1)
  {
    List reqtempstatus = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    KeyValue key5 = new KeyValue();
    
    key1.setKey("Draft");
    key1.setValue(getResourceStringValue("Requisition.draft", user1.getLocale()));
    key2.setKey("Active");
    key2.setValue(getResourceStringValue("Requisition.active", user1.getLocale()));
    key3.setKey("Closed");
    key3.setValue(getResourceStringValue("Requisition.closed", user1.getLocale()));
    key4.setKey("Deleted");
    key4.setValue(getResourceStringValue("Requisition.deleted", user1.getLocale()));
    

    reqtempstatus.add(key1);
    reqtempstatus.add(key2);
    reqtempstatus.add(key3);
    reqtempstatus.add(key4);
    
    return reqtempstatus;
  }
  
  public static List getSystemRuleTypeList(User user1)
  {
    List systemruletypeList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    

    key1.setKey(Common.SYSTEM_RULE_REQUISTION_APPROVER);
    key1.setValue(getResourceStringValue("systemrule.ruletype.requisition_approvers", user1.getLocale()));
    key2.setKey(Common.SYSTEM_RULE_OFFER_APPROVER);
    key2.setValue(getResourceStringValue("systemrule.ruletype.offer_approvers", user1.getLocale()));
    key3.setKey(Common.SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS);
    key3.setValue(getResourceStringValue("systemrule.ruletype.publish_requistions", user1.getLocale()));
    
    systemruletypeList.add(key1);
    systemruletypeList.add(key2);
    systemruletypeList.add(key3);
    

    return systemruletypeList;
  }
  
  public static List getTagTypeList(User user1)
  {
    List tagtypeList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    


    key1.setKey("APPLICANT");
    key1.setValue(getResourceStringValue("tags.tagtype.applicant", user1.getLocale()));
    key2.setKey("REQUISITION");
    key2.setValue(getResourceStringValue("tags.tagtype.requisition", user1.getLocale()));
    tagtypeList.add(key1);
    tagtypeList.add(key2);
    


    return tagtypeList;
  }
  
  public static List getIsgroupTalentpoolList(User user1)
  {
    List isgroupList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    


    key1.setKey("Yes");
    key1.setValue(getResourceStringValue("hr.text.yes", user1.getLocale()));
    key2.setKey("No");
    key2.setValue(getResourceStringValue("hr.text.no", user1.getLocale()));
    isgroupList.add(key1);
    isgroupList.add(key2);
    


    return isgroupList;
  }
  
  public static List getRelationshipList(User user1)
  {
    List relationsipList = new ArrayList();
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    key1.setKey("NoValue");
    key1.setValue("");
    key2.setKey(getResourceStringValue("hr.user.relationship.child", user1.getLocale()));
    key2.setValue(getResourceStringValue("hr.user.relationship.child", user1.getLocale()));
    key3.setKey(getResourceStringValue("hr.user.relationship.other", user1.getLocale()));
    key3.setValue(getResourceStringValue("hr.user.relationship.other", user1.getLocale()));
    relationsipList.add(key1);
    relationsipList.add(key2);
    relationsipList.add(key3);
    
    return relationsipList;
  }
  
  public static List getSubscriptionPaidByList(User user1)
  {
    List subscriptionPaidByList = new ArrayList();
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    key1.setKey("NoValue");
    key1.setValue("");
    key2.setKey(getResourceStringValue("hr.user.membership.Subscription_Company", user1.getLocale()));
    key2.setValue(getResourceStringValue("hr.user.membership.Subscription_Company", user1.getLocale()));
    key3.setKey(getResourceStringValue("hr.user.membership.Subscription_Individual", user1.getLocale()));
    key3.setValue(getResourceStringValue("hr.user.membership.Subscription_Individual", user1.getLocale()));
    subscriptionPaidByList.add(key1);
    subscriptionPaidByList.add(key2);
    subscriptionPaidByList.add(key3);
    
    return subscriptionPaidByList;
  }
  
  public static List getReportingMethodsList(User user1)
  {
    List reportingMethodsList = new ArrayList();
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    key1.setKey("NoValue");
    key1.setValue("");
    key2.setKey(getResourceStringValue("hr.user.reportto.Direct", user1.getLocale()));
    key2.setValue(getResourceStringValue("hr.user.reportto.Direct", user1.getLocale()));
    key3.setKey(getResourceStringValue("hr.user.reportto.Indirect", user1.getLocale()));
    key3.setValue(getResourceStringValue("hr.user.reportto.Indirect", user1.getLocale()));
    key4.setKey(getResourceStringValue("hr.user.reportto.Other", user1.getLocale()));
    key4.setValue(getResourceStringValue("hr.user.reportto.Other", user1.getLocale()));
    reportingMethodsList.add(key1);
    reportingMethodsList.add(key2);
    reportingMethodsList.add(key3);
    reportingMethodsList.add(key4);
    
    return reportingMethodsList;
  }
  
  public static List getVariableTypeList(User user1)
  {
    List variabletypeList = new ArrayList();
    
    KeyValue key1 = new KeyValue();
    KeyValue key2 = new KeyValue();
    KeyValue key3 = new KeyValue();
    KeyValue key4 = new KeyValue();
    KeyValue key5 = new KeyValue();
    


    key1.setKey("text");
    key1.setValue(getResourceStringValue("admin.variable.variabletype.text", user1.getLocale()));
    key2.setKey("numeric");
    key2.setValue(getResourceStringValue("admin.variable.variabletype.numeric", user1.getLocale()));
    key3.setKey("textarea");
    key3.setValue(getResourceStringValue("admin.variable.variabletype.textarea", user1.getLocale()));
    key4.setKey("date");
    key4.setValue(getResourceStringValue("admin.variable.variabletype.date", user1.getLocale()));
    key5.setKey("list");
    key5.setValue(getResourceStringValue("admin.variable.variabletype.list", user1.getLocale()));
    variabletypeList.add(key1);
    variabletypeList.add(key2);
    variabletypeList.add(key3);
    variabletypeList.add(key4);
    variabletypeList.add(key5);
    return variabletypeList;
  }
  
  public static List getAllInterviewStates()
  {
    List interviewstateList = new ArrayList();
    InterviewState int01 = new InterviewState();
    InterviewState int02 = new InterviewState();
    InterviewState int03 = new InterviewState();
    InterviewState int04 = new InterviewState();
    InterviewState int05 = new InterviewState();
    InterviewState int06 = new InterviewState();
    InterviewState int07 = new InterviewState();
    InterviewState int08 = new InterviewState();
    InterviewState int09 = new InterviewState();
    InterviewState int10 = new InterviewState();
    InterviewState int11 = new InterviewState();
    InterviewState int12 = new InterviewState();
    int01.setInterviewstateCode("Application Submitted");
    int01.setInterviewstateName("Application Submitted");
    int02.setInterviewstateCode("Screening");
    int02.setInterviewstateName("Screening");
    int03.setInterviewstateCode("Interview");
    int03.setInterviewstateName("In Interview Process");
    int04.setInterviewstateCode("Offer");
    int04.setInterviewstateName("In Offer Process");
    int05.setInterviewstateCode("Offer Released");
    int05.setInterviewstateName("Offer Released");
    int06.setInterviewstateCode("Offer Declined");
    int06.setInterviewstateName("Offer Declined");
    int07.setInterviewstateCode("Offer Accepted");
    int07.setInterviewstateName("Offer Accepted");
    int08.setInterviewstateCode("Offer In Negotiation");
    int08.setInterviewstateName("Offer In Negotiation");
    int09.setInterviewstateCode("Offer Canceled");
    int09.setInterviewstateName("Offer Canceled");
    int10.setInterviewstateCode("Rejected");
    int10.setInterviewstateName("Rejected");
    int11.setInterviewstateCode("OnHold");
    int11.setInterviewstateName("OnHold");
    int12.setInterviewstateCode("On Board - Joined");
    int12.setInterviewstateName("On Board - Joined");
    interviewstateList.add(int01);
    interviewstateList.add(int02);
    interviewstateList.add(int03);
    interviewstateList.add(int04);
    interviewstateList.add(int05);
    interviewstateList.add(int06);
    interviewstateList.add(int07);
    interviewstateList.add(int08);
    interviewstateList.add(int09);
    interviewstateList.add(int10);
    interviewstateList.add(int11);
    interviewstateList.add(int12);
    
    return interviewstateList;
  }
  
  public static List getTaskTypesList(User user)
  {
    List tasktypeList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    KeyValue t3 = new KeyValue();
    KeyValue t4 = new KeyValue();
    KeyValue t5 = new KeyValue();
    KeyValue t6 = new KeyValue();
    KeyValue t7 = new KeyValue();
    KeyValue t8 = new KeyValue();
    KeyValue t9 = new KeyValue();
    t1.setKey("NoValue");
    t1.setValue("");
    t2.setKey(Common.REQUISTION_APPROVAL_TASK);
    t2.setValue(getResourceStringValueWithDefault(Common.REQUISTION_APPROVAL_TASK, user.getLocale()));
    t3.setKey(Common.OFFER_APPROVAL_TASK);
    t3.setValue(getResourceStringValueWithDefault(Common.OFFER_APPROVAL_TASK, user.getLocale()));
    t4.setKey(Common.REFERENCE_CHECK_TASK);
    t4.setValue(getResourceStringValueWithDefault(Common.REFERENCE_CHECK_TASK, user.getLocale()));
    t5.setKey(Common.REQUISTION_PUBLISH_TASK);
    t5.setValue(getResourceStringValueWithDefault(Common.REQUISTION_PUBLISH_TASK, user.getLocale()));
    t6.setKey(Common.OFFER_RELEASE_TASK);
    t6.setValue(getResourceStringValueWithDefault(Common.OFFER_RELEASE_TASK, user.getLocale()));
    t7.setKey(Common.APPLICANT_REVIEW_TASK);
    t7.setValue(getResourceStringValueWithDefault(Common.APPLICANT_REVIEW_TASK, user.getLocale()));
    t8.setKey(Common.APPLICANT_IN_QUEUE);
    t8.setValue(getResourceStringValueWithDefault(Common.APPLICANT_IN_QUEUE, user.getLocale()));
    t9.setKey(Common.ONBOARDING_TASK);
    t9.setValue(getResourceStringValueWithDefault(Common.ONBOARDING_TASK, user.getLocale()));
    


    tasktypeList.add(t1);
    tasktypeList.add(t2);
    tasktypeList.add(t3);
    tasktypeList.add(t4);
    tasktypeList.add(t5);
    tasktypeList.add(t6);
    tasktypeList.add(t7);
    tasktypeList.add(t8);
    tasktypeList.add(t9);
    

    return tasktypeList;
  }
  
  public static List getBulUploadTaskTypesList()
  {
    List tasktypeList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    KeyValue t3 = new KeyValue();
    KeyValue t4 = new KeyValue();
    KeyValue t5 = new KeyValue();
    KeyValue t6 = new KeyValue();
    KeyValue t7 = new KeyValue();
    KeyValue t8 = new KeyValue();
    KeyValue t9 = new KeyValue();
    t1.setKey("NoValue");
    t1.setValue("");
    t2.setKey(Common.BULK_UPLOAD_RESUME);
    t2.setValue(Common.BULK_UPLOAD_RESUME);
    t3.setKey(Common.BULK_UPLOAD_USERS);
    t3.setValue(Common.BULK_UPLOAD_USERS);
    t4.setKey(Common.EXPORT_APPLICANTS);
    t4.setValue(Common.EXPORT_APPLICANTS);
    t5.setKey(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS);
    t5.setValue(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS);
    t6.setKey(Common.EXPORT_MYREQUISTIONS_SEARCH_RESULTS);
    t6.setValue(Common.EXPORT_MYREQUISTIONS_SEARCH_RESULTS);
    t7.setKey(Common.EXPORT_APPLICANTS_SEARCH);
    t7.setValue(Common.EXPORT_APPLICANTS_SEARCH);
    t8.setKey(Common.EXPORT_AGENCY_REDEMPTION_SEARCH_RESULTS);
    t8.setValue(Common.EXPORT_AGENCY_REDEMPTION_SEARCH_RESULTS);
    t9.setKey(Common.EXPORT_REFERAL_REDEMPTION_SEARCH_RESULTS);
    t9.setValue(Common.EXPORT_REFERAL_REDEMPTION_SEARCH_RESULTS);
    tasktypeList.add(t1);
    tasktypeList.add(t2);
    tasktypeList.add(t3);
    tasktypeList.add(t4);
    tasktypeList.add(t5);
    tasktypeList.add(t6);
    tasktypeList.add(t7);
    tasktypeList.add(t8);
    tasktypeList.add(t9);
    return tasktypeList;
  }
  
  public static List getSearchCriteraDateFields()
  {
    List searchCriDateList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    KeyValue t3 = new KeyValue();
    KeyValue t4 = new KeyValue();
    KeyValue t5 = new KeyValue();
    KeyValue t6 = new KeyValue();
    


    t1.setKey("NoValue");
    t1.setValue("");
    t2.setKey(Common.TARGET_ON_BOARD_DATE);
    t2.setValue(Common.TARGET_ON_BOARD_DATE);
    t3.setKey(Common.TARGET_OFFER_RELEASE_DATE);
    t3.setValue(Common.TARGET_OFFER_RELEASE_DATE);
    t4.setKey(Common.APPLIED_DATE);
    t4.setValue(Common.APPLIED_DATE);
    t5.setKey(Common.OFFER_RELEASED_DATE);
    t5.setValue(Common.OFFER_RELEASED_DATE);
    t6.setKey(Common.ON_BOARDED_DATE);
    t6.setValue(Common.ON_BOARDED_DATE);
    


    searchCriDateList.add(t1);
    searchCriDateList.add(t2);
    searchCriDateList.add(t3);
    searchCriDateList.add(t4);
    searchCriDateList.add(t5);
    searchCriDateList.add(t6);
    



    return searchCriDateList;
  }
  
  public static List getReleasedTypesList()
  {
    List rList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    

    t1.setKey("Released");
    t1.setValue("Released");
    t2.setKey("Not Released");
    t2.setValue("Not Released");
    
    rList.add(t1);
    rList.add(t2);
    



    return rList;
  }
  
  public static List getYesNoList()
  {
    List rList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    

    t1.setKey("Y");
    t1.setValue("Yes");
    t2.setKey("N");
    t2.setValue("No");
    
    rList.add(t1);
    rList.add(t2);
    



    return rList;
  }
  
  public static List getYesNoFullList()
  {
    List rList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    

    t1.setKey("Yes");
    t1.setValue("Yes");
    t2.setKey("No");
    t2.setValue("No");
    
    rList.add(t1);
    rList.add(t2);
    



    return rList;
  }
  
  public static List genderList()
  {
    List rList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    

    t1.setKey("M");
    t1.setValue("M");
    t2.setKey("F");
    t2.setValue("F");
    
    rList.add(t1);
    rList.add(t2);
    



    return rList;
  }
  
  public static List getBulUploadTaskStatusList()
  {
    List tasktypeList = new ArrayList();
    KeyValue t1 = new KeyValue();
    KeyValue t2 = new KeyValue();
    KeyValue t3 = new KeyValue();
    KeyValue t4 = new KeyValue();
    
    t1.setKey("NoValue");
    t1.setValue("");
    t2.setKey(Common.BULK_UPLOAD_STATUS_COMPLETED);
    t2.setValue(Common.BULK_UPLOAD_STATUS_COMPLETED);
    t3.setKey(Common.BULK_UPLOAD_STATUS_RUNNING);
    t3.setValue(Common.BULK_UPLOAD_STATUS_RUNNING);
    t4.setKey(Common.BULK_UPLOAD_STATUS_FAIL);
    t4.setValue(Common.BULK_UPLOAD_STATUS_FAIL);
    

    tasktypeList.add(t1);
    tasktypeList.add(t2);
    tasktypeList.add(t3);
    tasktypeList.add(t4);
    


    return tasktypeList;
  }
  
  public static List getAllApplicantScreenList()
  {
    List appScreenList = new ArrayList();
    appScreenList.add("APPLICANT_SCREEN");
    appScreenList.add("APPLICANT_SCREEN_AGENCY");
    appScreenList.add("APPLICANT_SCREEN_REFERRAL");
    appScreenList.add("APPLICANT_SCREEN_TALENTPOOL");
    appScreenList.add("APPLICANT_SCREEN_EXTERNAL");
    appScreenList.add("APPLICANT_SCREEN_TALENTPOOL_EXTERNAL");
    appScreenList.add("APPLICANT_SCREEN_PROFILE");
    return appScreenList;
  }
  
  public static List getYearsLovList()
  {
    List years = new ArrayList();
    

    Calendar can = Calendar.getInstance();
    int currentYear = can.get(1);
    for (int i = currentYear - 5; i < currentYear + 5; i++)
    {
      KeyValue t1 = new KeyValue();
      t1.setKey(String.valueOf(i));
      t1.setValue(String.valueOf(i));
      years.add(t1);
    }
    return years;
  }
  
  public static String getModeOfInterviewName(int id)
  {
    if (id == 1) {
      return "Personal";
    }
    if (id == 2) {
      return "Telephonic";
    }
    if (id == 3) {
      return "Video Chat";
    }
    return "Review";
  }
  
  public static void setApplicantTableBeanColumnNameMap(Map applicantTableBeanColumnNameMap)
  {
    applicantTableBeanColumnNameMap = applicantTableBeanColumnNameMap;
  }
  
  public static void setApplicantTableMap(Map applicantTableMap)
  {
    applicantTableMap = applicantTableMap;
  }
  
  public Properties getPropertiesFromClasspath(String propFileName)
    throws IOException
  {
    Properties props = new Properties();
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
    if (inputStream == null)
    {
      logger.info("property file '" + propFileName + "' not found in the classpath");
      
      throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
    }
    props.load(inputStream);
    
    return props;
  }
  
  public static List getFbJobExperienceList()
  {
    String value = "";
    List fbjobexplist = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/fbjobexperience.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        fbjobexplist.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return fbjobexplist;
  }
  
  public static List getFbJobTenureList()
  {
    String value = "";
    List fbjobtenureist = new ArrayList();
    try
    {
      Properties propsEnv = new Properties();
      
      Constant con = new Constant();
      propsEnv = con.getPropertiesFromClasspath("conf/lov/fbjobtenure.properties");
      Set set = propsEnv.keySet();
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String key = (String)itr.next();
        value = (String)propsEnv.get(key);
        KeyValue m = new KeyValue();
        m.setKey(key);
        m.setValue(value);
        fbjobtenureist.add(m);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return fbjobtenureist;
  }
}
