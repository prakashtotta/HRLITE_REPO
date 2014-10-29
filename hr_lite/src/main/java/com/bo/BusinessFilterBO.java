package com.bo;

import com.bean.ApplicantOtherDetails;
import com.bean.EducationDetails;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.PreviousOrgDetails;
import com.bean.Timezone;
import com.bean.User;
import com.bean.UserRegData;
import com.bean.dto.ScreenFieldData;
import com.bean.filter.BusinessCriteria;
import com.bean.filter.BusinessFilterConditions;
import com.bean.filter.ScreenFields;
import com.bean.lov.VariablesValues;
import com.common.Common;
import com.dao.BusinessFilterDAO;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

public class BusinessFilterBO
{
  protected static final Logger logger = Logger.getLogger(BusinessFilterBO.class);
  public static Map<String, String> screenfieldmap = new HashMap();
  public static Map<String, String> applicantOtherscreenfieldmap = new HashMap();
  public static Map<String, String> workExpScreenfieldmap = new HashMap();
  public static Map<String, String> educationScreenfieldmap = new HashMap();
  public static Map<String, String> screenfieldmapReq = new HashMap();
  BusinessFilterDAO businessFilterDao;
  
  public List isApplicantValidationSuccessed(JobApplicant applicant, long jobReqId, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside isApplicantValidationSuccessed");
    
    List errorList = new ArrayList();
    


    String screencode = checkScreenCodeBasedOnPackage(applicant.getSuper_user_key(), applicant.getScreenCode());
    applicant.setScreenCode(screencode);
    



    List<BusinessCriteria> bunessCriterias = this.businessFilterDao.getBusinessCriteriasForApplicantValidationWithoutSilent(jobReqId);
    if (bunessCriterias == null) {
      return null;
    }
    logger.info("inside bunessCriterias" + bunessCriterias.size());
    for (int i = 0; i < bunessCriterias.size(); i++)
    {
      BusinessCriteria bcriteria = (BusinessCriteria)bunessCriterias.get(i);
      logger.info("bcriteria.getVariableId()" + bcriteria.getVariableId());
      logger.info("bcriteria.getVariableOrigin()" + bcriteria.getVariableOrigin());
      logger.info("bcriteria.getName()" + bcriteria.getName());
      logger.info("applicant.getScreenCode()" + applicant.getScreenCode());
      if (bcriteria.getVariableOrigin() == null)
      {
        Set<BusinessFilterConditions> filterconditions = bcriteria.getBusinessConditions();
        logger.info("filterconditions()" + filterconditions);
        
        fireTheFilterConditions(applicant, filterconditions, jobReqId, errorList, customVariableDataList, user, applicant.getSuper_user_key());
      }
      else if ((bcriteria.getVariableOrigin() != null) && (bcriteria.getVariableOrigin().equals("SCREEN")))
      {
        if (preConditionCheck(applicant, bcriteria, jobReqId, customVariableDataList, user, applicant.getSuper_user_key()))
        {
          Set<BusinessFilterConditions> filterconditions = bcriteria.getBusinessConditions();
          

          fireTheFilterConditions(applicant, filterconditions, jobReqId, errorList, customVariableDataList, user, applicant.getSuper_user_key());
        }
      }
      else if ((bcriteria.getVariableOrigin() != null) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        if (preConditionCheck(applicant, bcriteria, jobReqId, customVariableDataList, user, applicant.getSuper_user_key()))
        {
          Set<BusinessFilterConditions> filterconditions = bcriteria.getBusinessConditions();
          
          fireTheFilterConditions(applicant, filterconditions, jobReqId, errorList, customVariableDataList, user, applicant.getSuper_user_key());
        }
      }
    }
    return errorList;
  }
  
  private VariablesValues getVariablesValuesFromCustomData(long varibleId, List customVariableDataList)
  {
    logger.info("varibleId" + varibleId);
    VariablesValues vvalue = null;
    logger.info("this.customVariableDataList" + customVariableDataList);
    if ((customVariableDataList != null) && (customVariableDataList.size() > 0)) {
      for (int i = 0; i < customVariableDataList.size(); i++)
      {
        VariablesValues vv = (VariablesValues)customVariableDataList.get(i);
        logger.info("vv" + vv);
        if (vv != null)
        {
          logger.info("vv.getVariableId()" + vv.getVariableId());
          if (vv.getVariableId() == varibleId) {
            vvalue = vv;
          }
        }
      }
    }
    return vvalue;
  }
  
  public List isApplicantValidationSuccessedWithSilentFilters(JobApplicant applicant, long jobReqId, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside isApplicantValidationSuccessedWithSilentFilters");
    
    List errorList = new ArrayList();
    




    List<BusinessCriteria> bunessCriterias = this.businessFilterDao.getBusinessCriteriasForApplicantValidationWithSilent(jobReqId);
    if (bunessCriterias == null) {
      return null;
    }
    logger.info("inside bunessCriterias" + bunessCriterias.size());
    for (int i = 0; i < bunessCriterias.size(); i++)
    {
      BusinessCriteria bcriteria = (BusinessCriteria)bunessCriterias.get(i);
      logger.info("bcriteria.getVariableId()" + bcriteria.getVariableId());
      logger.info("bcriteria.getVariableOrigin()" + bcriteria.getVariableOrigin());
      if (bcriteria.getVariableOrigin() == null)
      {
        Set<BusinessFilterConditions> filterconditions = bcriteria.getBusinessConditions();
        logger.info("filterconditions()" + filterconditions);
        
        fireTheFilterConditions(applicant, filterconditions, jobReqId, errorList, customVariableDataList, user, applicant.getSuper_user_key());
      }
      else if ((bcriteria.getVariableOrigin() != null) && (bcriteria.getVariableOrigin().equals("SCREEN")))
      {
        if (preConditionCheck(applicant, bcriteria, jobReqId, customVariableDataList, user, applicant.getSuper_user_key()))
        {
          Set<BusinessFilterConditions> filterconditionsPre = bcriteria.getBusinessConditions();
          
          fireTheFilterConditions(applicant, filterconditionsPre, jobReqId, errorList, customVariableDataList, user, applicant.getSuper_user_key());
        }
      }
      else if ((bcriteria.getVariableOrigin() != null) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        if (preConditionCheck(applicant, bcriteria, jobReqId, customVariableDataList, user, applicant.getSuper_user_key()))
        {
          Set<BusinessFilterConditions> filterconditions = bcriteria.getBusinessConditions();
          
          fireTheFilterConditions(applicant, filterconditions, jobReqId, errorList, customVariableDataList, user, applicant.getSuper_user_key());
        }
      }
    }
    return errorList;
  }
  
  private boolean preConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, long jobReqId, List customVariableDataList, User user, long super_user_key)
    throws Exception
  {
    logger.info("inside preConditionCheck");
    boolean success = false;
    if ((bcriteria.getVariableOrigin() != null) && (bcriteria.getVariableOrigin().equals("SCREEN")))
    {
      if (!BOFactory.getApplicantBO().isFieldAttachedToScreenCode(applicant.getScreenCode(), bcriteria.getVariableName(), super_user_key)) {
        return true;
      }
    }
    else if ((bcriteria.getVariableOrigin() != null) && (bcriteria.getVariableOrigin().equals("CUSTOM")) && 
      (!BOFactory.getVariableBO().isVariableIdAttachedToScreenCode(applicant.getScreenCode(), bcriteria.getVariableId(), super_user_key))) {
      return true;
    }
    if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("GREATER_THAN")))
    {
      success = originScreenGreaterThanPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      
      logger.info("originScreenGreaterThanPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("LESS_THAN")))
    {
      success = originScreenLessThanPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      
      logger.info("originScreenLessThanPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("EQUALS")))
    {
      success = originScreenEqualsPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      
      logger.info("originScreenEqualsPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("NOT_EQUALS")))
    {
      success = originScreenNotEqualsPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      logger.info("originScreenNotEqualsPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("NOT_EMPTY")))
    {
      success = originScreenNotNullPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      logger.info("originScreenNotNullPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("EMPTY")))
    {
      success = originScreenNullPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      logger.info("originScreenNullPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("STARTS_WITH")))
    {
      success = originScreenStartWithPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      logger.info("originScreenStartWithPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("ENDS_WITH")))
    {
      success = originScreenEndWithPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      logger.info("originScreenEndWithPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("CONTAINS")))
    {
      success = originScreenContainsPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      logger.info("originScreenContainsPreConditionCheck return value" + success);
    }
    else if ((bcriteria.getVariableCriteria() != null) && (bcriteria.getVariableCriteria().equals("BETWEEN")))
    {
      success = originScreenBetweenPreConditionCheck(applicant, bcriteria, customVariableDataList, user);
      logger.info("originScreenBetweenPreConditionCheck return value" + success);
    }
    return success;
  }
  
  private void fireTheFilterConditions(JobApplicant applicant, Set<BusinessFilterConditions> filterconditions, long jobReqId, List errorList, List customVariableDataList, User user, long super_user_key)
    throws Exception
  {
    logger.info("inside fireTheFilterConditions");
    if ((filterconditions != null) && (filterconditions.size() > 0))
    {
      Iterator<BusinessFilterConditions> itr = filterconditions.iterator();
      while (itr.hasNext())
      {
        BusinessFilterConditions businessfilter = (BusinessFilterConditions)itr.next();
        boolean isContinue = false;
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          boolean isTrue = BOFactory.getVariableBO().isVariableIdAttachedToScreenCode(applicant.getScreenCode(), businessfilter.getVariableId(), super_user_key);
          logger.info("isTrue screen" + businessfilter.getVariableId() + isTrue);
          if (isTrue) {
            isContinue = true;
          }
        }
        else if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("SCREEN")))
        {
          boolean isTrue = BOFactory.getApplicantBO().isFieldAttachedToScreenCode(applicant.getScreenCode(), businessfilter.getVariableName(), super_user_key);
          logger.info("isTrue screen" + businessfilter.getVariableName() + isTrue);
          if (isTrue) {
            isContinue = true;
          }
        }
        logger.info("Filter name" + businessfilter.getName());
        logger.info("Filter name" + businessfilter.getName());
        logger.info("Variable name" + businessfilter.getVariableName());
        logger.info("Variable origin" + businessfilter.getVariableOrigin());
        if (isContinue) {
          if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("GREATER_THAN"))) {
            originScreenGreaterThan(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("LESS_THAN"))) {
            originScreenLessThan(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("EQUALS"))) {
            originScreenEquals(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("NOT_EQUALS"))) {
            originScreenNotEquals(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("NOT_EMPTY"))) {
            originScreenNotNull(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("EMPTY"))) {
            originScreenNull(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("STARTS_WITH"))) {
            originScreenStartsWith(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("ENDS_WITH"))) {
            originScreenEndsWith(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("CONTAINS"))) {
            originScreenContains(applicant, businessfilter, errorList, customVariableDataList, user);
          } else if ((businessfilter.getFilterCriteria() != null) && (businessfilter.getFilterCriteria().equals("BETWEEN"))) {
            originScreenBetween(applicant, businessfilter, errorList, customVariableDataList, user);
          }
        }
      }
    }
  }
  
  private void originScreenGreaterThan(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenGreaterThan");
    
    Class applicantClass = applicant.getClass();
    if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        if (vv != null) {
          value = vv.getValueText();
        }
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(businessfilter.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue > criteriavalue)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.greater.than.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(businessfilter.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = businessfilter.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue != null) && (enteredvalue.after(criteriavalue)))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String enterv = "";
          if (enteredvalue != null) {
            enterv = DateUtil.convertDateToStringDate(enteredvalue, datepattern);
          }
          String criteriav = "";
          if (criteriavalue != null) {
            criteriav = DateUtil.convertDateToStringDate(criteriavalue, datepattern);
          }
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(enterv), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(criteriav) };
          String msg = Constant.getResourceStringValue("entered.value.should.greater.than.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(enteredvalue), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private boolean originScreenGreaterThanPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenGreaterThanPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("numeric")))
    {
      Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
      String value = String.valueOf(objvalue);
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(bcriteria.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue > criteriavalue)
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(bcriteria.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = bcriteria.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue != null) && (enteredvalue.after(criteriavalue)))
        {
          logger.info("correct behabiour");
          
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private void originScreenBetween(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenBetween");
    
    Class applicantClass = applicant.getClass();
    if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue1 = new Double(businessfilter.getFilterValue1()).doubleValue();
        double criteriavalue2 = new Double(businessfilter.getFilterValue2()).doubleValue();
        logger.info("criteriavalue1" + criteriavalue1);
        logger.info("criteriavalue2" + criteriavalue2);
        if (((enteredvalue >= criteriavalue1) && (enteredvalue <= criteriavalue2)) || ((enteredvalue <= criteriavalue1) && (enteredvalue >= criteriavalue2)))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()), String.valueOf(businessfilter.getFilterValue2()) };
          String msg = Constant.getResourceStringValue("entered.value.should.between.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue1 = null;
      Date criteriavalue2 = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(businessfilter.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue1 = businessfilter.getFilterValueDate1();
        criteriavalue2 = businessfilter.getFilterValueDate2();
        logger.info("criteriavalue1" + criteriavalue1);
        logger.info("criteriavalue2" + criteriavalue2);
        if ((enteredvalue != null) && (criteriavalue1 != null) && (criteriavalue2 != null) && (((enteredvalue.after(criteriavalue1)) && (enteredvalue.before(criteriavalue2))) || ((enteredvalue.before(criteriavalue1)) && (enteredvalue.after(criteriavalue2)))))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String enterv = "";
          if (enteredvalue != null) {
            enterv = DateUtil.convertDateToStringDate(enteredvalue, datepattern);
          }
          String criteriav1 = "";
          if (criteriavalue1 != null) {
            criteriav1 = DateUtil.convertDateToStringDate(criteriavalue1, datepattern);
          }
          String criteriav2 = "";
          if (criteriavalue2 != null) {
            criteriav2 = DateUtil.convertDateToStringDate(criteriavalue2, datepattern);
          }
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(enterv), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(criteriav1), String.valueOf(criteriav2) };
          String msg = Constant.getResourceStringValue("entered.value.should.between.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(enteredvalue), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private boolean originScreenBetweenPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenBetweenPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("numeric")))
    {
      Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
      String value = String.valueOf(objvalue);
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue1 = new Double(bcriteria.getFilterValue1()).doubleValue();
        double criteriavalue2 = new Double(bcriteria.getFilterValue2()).doubleValue();
        logger.info("criteriavalue1" + criteriavalue1);
        logger.info("criteriavalue2" + criteriavalue2);
        if (((enteredvalue >= criteriavalue1) && (enteredvalue <= criteriavalue2)) || ((enteredvalue <= criteriavalue1) && (enteredvalue >= criteriavalue2)))
        {
          logger.info("correct behabiour");
          
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue1 = null;
      Date criteriavalue2 = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(bcriteria.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue1 = bcriteria.getFilterValueDate1();
        criteriavalue2 = bcriteria.getFilterValueDate2();
        logger.info("criteriavalue1" + criteriavalue1);
        logger.info("criteriavalue2" + criteriavalue2);
        if ((enteredvalue != null) && (criteriavalue1 != null) && (criteriavalue2 != null) && (((enteredvalue.after(criteriavalue1)) && (enteredvalue.before(criteriavalue2))) || ((enteredvalue.before(criteriavalue1)) && (enteredvalue.after(criteriavalue2)))))
        {
          logger.info("correct behabiour");
          
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private void originScreenLessThan(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenLessThan");
    
    Class applicantClass = applicant.getClass();
    if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        logger.info("vv" + vv);
        if (vv != null) {
          value = vv.getValueText();
        }
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(businessfilter.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue < criteriavalue)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.less.than.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(businessfilter.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = businessfilter.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue != null) && (enteredvalue.before(criteriavalue)))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          
          String enterv = "";
          if (enteredvalue != null) {
            enterv = DateUtil.convertDateToStringDate(enteredvalue, datepattern);
          }
          String criteriav = "";
          if (criteriavalue != null) {
            criteriav = DateUtil.convertDateToStringDate(criteriavalue, datepattern);
          }
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(enterv), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(criteriav) };
          
          String msg = Constant.getResourceStringValue("entered.value.should.less.than.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(enteredvalue), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private boolean originScreenLessThanPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenLessThanPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("numeric")))
    {
      Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
      String value = String.valueOf(objvalue);
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(bcriteria.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue < criteriavalue)
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(bcriteria.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = bcriteria.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue != null) && (enteredvalue.before(criteriavalue)))
        {
          logger.info("correct behabiour");
          
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private void originScreenStartsWith(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenStartsWith");
    
    Class applicantClass = applicant.getClass();
    logger.info("inside originScreenEquals businessfilter.getVariableOrigin()" + businessfilter.getVariableOrigin());
    if ((businessfilter.getVariableType() != null) && ((businessfilter.getVariableType().equals("text")) || (businessfilter.getVariableType().equals("textarea")) || (businessfilter.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        if (vv != null) {
          value = vv.getValueText();
        }
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = businessfilter.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (enteredvalue.startsWith(criteriavalue)))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.startswith.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private void originScreenEndsWith(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenEndsWith");
    
    Class applicantClass = applicant.getClass();
    logger.info("inside originScreenEquals businessfilter.getVariableOrigin()" + businessfilter.getVariableOrigin());
    if ((businessfilter.getVariableType() != null) && ((businessfilter.getVariableType().equals("text")) || (businessfilter.getVariableType().equals("textarea")) || (businessfilter.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        if (vv != null) {
          value = vv.getValueText();
        }
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = businessfilter.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (enteredvalue.endsWith(criteriavalue)))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.endswith.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private void originScreenContains(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenContains");
    
    Class applicantClass = applicant.getClass();
    logger.info("inside originScreenEquals businessfilter.getVariableOrigin()" + businessfilter.getVariableOrigin());
    if ((businessfilter.getVariableType() != null) && ((businessfilter.getVariableType().equals("text")) || (businessfilter.getVariableType().equals("textarea")) || (businessfilter.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        if (vv != null) {
          value = vv.getValueText();
        }
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = businessfilter.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (enteredvalue.indexOf(criteriavalue) > -1))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.contains.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private void originScreenEquals(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenEquals");
    
    Class applicantClass = applicant.getClass();
    logger.info("inside originScreenEquals businessfilter.getVariableOrigin()" + businessfilter.getVariableOrigin());
    if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(businessfilter.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue == criteriavalue)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.equals.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && ((businessfilter.getVariableType().equals("text")) || (businessfilter.getVariableType().equals("textarea")) || (businessfilter.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        if (vv != null) {
          value = vv.getValueText();
        }
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = businessfilter.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((StringUtils.isNullOrEmpty(enteredvalue)) && (StringUtils.isNullOrEmpty(criteriavalue)))
        {
          logger.info("correct behabiour");
        }
        else if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (!enteredvalue.equalsIgnoreCase(criteriavalue)))
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.equals.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(businessfilter.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = businessfilter.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue != null) && (enteredvalue.compareTo(criteriavalue) == 0))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String enterv = "";
          if (enteredvalue != null) {
            enterv = DateUtil.convertDateToStringDate(enteredvalue, datepattern);
          }
          String criteriav = "";
          if (criteriavalue != null) {
            criteriav = DateUtil.convertDateToStringDate(criteriavalue, datepattern);
          }
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(enterv), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(criteriav) };
          String msg = Constant.getResourceStringValue("entered.value.should.equals.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(enteredvalue), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private void originScreenNotEquals(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenNotEquals");
    
    Class applicantClass = applicant.getClass();
    if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(businessfilter.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue != criteriavalue)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.not.equals.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && ((businessfilter.getVariableType().equals("text")) || (businessfilter.getVariableType().equals("textarea")) || (businessfilter.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + businessfilter.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = businessfilter.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((StringUtils.isNullOrEmpty(enteredvalue)) && (!StringUtils.isNullOrEmpty(criteriavalue)))
        {
          logger.info("correct behabiour");
        }
        else if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (!enteredvalue.equalsIgnoreCase(criteriavalue)))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(businessfilter.getFilterValue1()) };
          String msg = Constant.getResourceStringValue("entered.value.should.not.equals.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(businessfilter.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = businessfilter.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue == null) && (criteriavalue != null))
        {
          logger.info("correct behabiour");
        }
        else if ((enteredvalue != null) && (enteredvalue.compareTo(criteriavalue) != 0))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String enterv = "";
          if (enteredvalue != null) {
            enterv = DateUtil.convertDateToStringDate(enteredvalue, datepattern);
          }
          String criteriav = "";
          if (criteriavalue != null) {
            criteriav = DateUtil.convertDateToStringDate(criteriavalue, datepattern);
          }
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { String.valueOf(enterv), Constant.getResourceStringValue(resoucekey, user.getLocale()), String.valueOf(criteriav) };
          String msg = Constant.getResourceStringValue("entered.value.should.not.equals.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(enteredvalue), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private void originScreenNull(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenNull");
    
    Class applicantClass = applicant.getClass();
    if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("enteredvalue" + value);
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        if (enteredvalue == 0.0D)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
          String msg = Constant.getResourceStringValue("entered.value.should.null.criteria.value.numeric", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && ((businessfilter.getVariableType().equals("text")) || (businessfilter.getVariableType().equals("textarea")) || (businessfilter.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        if (StringUtils.isNullOrEmpty(enteredvalue))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
          String msg = Constant.getResourceStringValue("entered.value.should.null.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(businessfilter.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        if (enteredvalue == null)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String enterv = "";
          if (enteredvalue != null) {
            enterv = DateUtil.convertToTimezoneDate(enteredvalue, datepattern, user.getTimezone().getTimezoneCode());
          }
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
          String msg = Constant.getResourceStringValue("entered.value.should.null.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(enteredvalue), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private void originScreenNotNull(JobApplicant applicant, BusinessFilterConditions businessfilter, List errorList, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenNotNull");
    
    Class applicantClass = applicant.getClass();
    if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("enteredvalue" + value);
      try
      {
        if (StringUtils.isNullOrEmpty(value)) {
          value = "0";
        }
        double enteredvalue = new Double(value).doubleValue();
        if (enteredvalue != 0.0D)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
          String msg = Constant.getResourceStringValue("entered.value.should.not.null.criteria.value.numeric", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && ((businessfilter.getVariableType().equals("text")) || (businessfilter.getVariableType().equals("textarea")) || (businessfilter.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(businessfilter.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        if (!StringUtils.isNullOrEmpty(enteredvalue))
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
          String msg = Constant.getResourceStringValue("entered.value.should.not.null.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(value), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
    else if ((businessfilter.getVariableType() != null) && (businessfilter.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(businessfilter.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(businessfilter.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        if (enteredvalue != null)
        {
          logger.info("correct behabiour");
        }
        else
        {
          logger.info("not correct behabiour");
          String enterv = "";
          if (enteredvalue != null) {
            enterv = DateUtil.convertToTimezoneDate(enteredvalue, datepattern, user.getTimezone().getTimezoneCode());
          }
          String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
          if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
          {
            resoucekey = businessfilter.getVariableName();
            if (!StringUtils.isNullOrEmpty(resoucekey)) {
              resoucekey = resoucekey.replaceAll("_", " ");
            }
          }
          String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
          String msg = Constant.getResourceStringValue("entered.value.should.not.null.criteria.value", user.getLocale(), args);
          logger.info(msg);
          errorList.add(msg);
        }
      }
      catch (NumberFormatException n)
      {
        String resoucekey = "aquisition.applicant." + businessfilter.getVariableName();
        if ((!StringUtils.isNullOrEmpty(businessfilter.getVariableOrigin())) && (businessfilter.getVariableOrigin().equals("CUSTOM")))
        {
          resoucekey = businessfilter.getVariableName();
          if (!StringUtils.isNullOrEmpty(resoucekey)) {
            resoucekey = resoucekey.replaceAll("_", " ");
          }
        }
        String[] args = { String.valueOf(enteredvalue), Constant.getResourceStringValue(resoucekey, user.getLocale()) };
        String msg = Constant.getResourceStringValue("entered.value.not.correct.format", user.getLocale(), args);
        errorList.add(msg);
      }
    }
  }
  
  private boolean originScreenNullPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenNullPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("enteredvalue" + value);
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        if (enteredvalue == 0.0D)
        {
          logger.info("correct behabiour");
          
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && ((bcriteria.getVariableType().equals("text")) || (bcriteria.getVariableType().equals("textarea")) || (bcriteria.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        if (StringUtils.isNullOrEmpty(enteredvalue))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(bcriteria.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        if (enteredvalue == null)
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private boolean originScreenNotNullPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenNotNullPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("enteredvalue" + value);
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        if (enteredvalue != 0.0D)
        {
          logger.info("correct behabiour");
          
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && ((bcriteria.getVariableType().equals("text")) || (bcriteria.getVariableType().equals("textarea")) || (bcriteria.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        if (!StringUtils.isNullOrEmpty(enteredvalue))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(bcriteria.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        if (enteredvalue != null)
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private boolean originScreenNotEqualsPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenNotEqualsPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(bcriteria.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue != criteriavalue)
        {
          logger.info("correct behabiour");
          success = true;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && ((bcriteria.getVariableType().equals("text")) || (bcriteria.getVariableType().equals("textarea")) || (bcriteria.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = bcriteria.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((StringUtils.isNullOrEmpty(enteredvalue)) && (!StringUtils.isNullOrEmpty(criteriavalue)))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (!enteredvalue.equalsIgnoreCase(criteriavalue)))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(bcriteria.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = bcriteria.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue == null) && (criteriavalue != null))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else if ((enteredvalue != null) && (enteredvalue.compareTo(criteriavalue) != 0))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private boolean originScreenStartWithPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenStartWithPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && ((bcriteria.getVariableType().equals("text")) || (bcriteria.getVariableType().equals("textarea")) || (bcriteria.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = bcriteria.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (enteredvalue.startsWith(criteriavalue)))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private boolean originScreenEndWithPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenEndWithPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && ((bcriteria.getVariableType().equals("text")) || (bcriteria.getVariableType().equals("textarea")) || (bcriteria.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = bcriteria.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (enteredvalue.endsWith(criteriavalue)))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private boolean originScreenContainsPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenContainsPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && ((bcriteria.getVariableType().equals("text")) || (bcriteria.getVariableType().equals("textarea")) || (bcriteria.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = bcriteria.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (enteredvalue.indexOf(criteriavalue) > -1))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private boolean originScreenEqualsPreConditionCheck(JobApplicant applicant, BusinessCriteria bcriteria, List customVariableDataList, User user)
    throws Exception
  {
    logger.info("inside originScreenEqualsPreConditionCheck");
    boolean success = false;
    Class applicantClass = applicant.getClass();
    if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("numeric")))
    {
      String value = "0";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("variable name" + bcriteria.getVariableName());
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        double enteredvalue = new Double(value).doubleValue();
        logger.info("enteredvalue" + enteredvalue);
        double criteriavalue = new Double(bcriteria.getFilterValue1()).doubleValue();
        logger.info("criteriavalue" + criteriavalue);
        if (enteredvalue == criteriavalue)
        {
          logger.info("correct behabiour");
          success = true;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && ((bcriteria.getVariableType().equals("text")) || (bcriteria.getVariableType().equals("textarea")) || (bcriteria.getVariableType().equals("list"))))
    {
      String value = "";
      if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
      {
        VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
        value = vv.getValueText();
      }
      else
      {
        Object objvalue = getValueFromApplicant(bcriteria.getVariableName(), applicant);
        value = String.valueOf(objvalue);
      }
      logger.info("value" + value);
      logger.info("businessfilter.getFilterValue1()" + bcriteria.getFilterValue1());
      try
      {
        String enteredvalue = value;
        logger.info("enteredvalue" + enteredvalue);
        String criteriavalue = bcriteria.getFilterValue1();
        logger.info("criteriavalue" + criteriavalue);
        if ((StringUtils.isNullOrEmpty(enteredvalue)) && (StringUtils.isNullOrEmpty(criteriavalue)))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else if ((!StringUtils.isNullOrEmpty(enteredvalue)) && (enteredvalue.equalsIgnoreCase(criteriavalue)))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    else if ((bcriteria.getVariableType() != null) && (bcriteria.getVariableType().equals("date")))
    {
      Date enteredvalue = null;
      Date criteriavalue = null;
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      try
      {
        if ((!StringUtils.isNullOrEmpty(bcriteria.getVariableOrigin())) && (bcriteria.getVariableOrigin().equals("CUSTOM")))
        {
          VariablesValues vv = getVariablesValuesFromCustomData(bcriteria.getVariableId(), customVariableDataList);
          enteredvalue = vv.getValueDate();
        }
        else
        {
          enteredvalue = (Date)getValueFromApplicant(bcriteria.getVariableName(), applicant);
        }
        logger.info("enteredvalue" + enteredvalue);
        criteriavalue = bcriteria.getFilterValueDate1();
        logger.info("criteriavalue" + criteriavalue);
        if ((enteredvalue == null) && (criteriavalue != null))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else if ((enteredvalue != null) && (enteredvalue.compareTo(criteriavalue) == 0))
        {
          logger.info("correct behabiour");
          success = true;
        }
        else
        {
          success = false;
        }
      }
      catch (NumberFormatException n)
      {
        success = true;
      }
    }
    return success;
  }
  
  private Map<String, String> getApplicantVariableMap()
  {
    if (screenfieldmap.size() == 0)
    {
      screenfieldmap.put("YEARS_OF_EXPERINCE", "noofyearsexp");
      screenfieldmap.put("GENDER", "gender");
      screenfieldmap.put("NOTICE_PERIOD", "noticeperiod");
      screenfieldmap.put("DATE_OF_BIRTH", "dateofbirth");
      screenfieldmap.put("APPLICANT_NAME", "fullName");
      screenfieldmap.put("SOURCE_SUBSOURCE", "validationOnlyresumeSourceId");
      screenfieldmap.put("EMAIL_ID", "email");
      screenfieldmap.put("PASSPORT_NO", "passportno");
      screenfieldmap.put("ALTERNATE_EMAIL_ID", "alternateemail");
      screenfieldmap.put("PHONE_NUMBER", "phone");
      screenfieldmap.put("ALTERNATE_PHONE_NUMBER", "alternatephone");
      screenfieldmap.put("PRIMARY_SKILL", "primarySkill");
      screenfieldmap.put("COUNTRY", "validationOnlyCountryId");
      screenfieldmap.put("STATE", "validationOnlyStateId");
      screenfieldmap.put("CITY", "city");
      screenfieldmap.put("PREFERRED_LOCATION", "preferedlocation");
      screenfieldmap.put("CURRENT_DESIGNATION", "currentdesignation");
      screenfieldmap.put("CURRENT_ORGANIZATION", "previousOrganization");
      screenfieldmap.put("PROFILE_HEADER", "resumeHeader");
      screenfieldmap.put("HIGHEST_QUALIFICATION", "heighestQualification");
      screenfieldmap.put("OTHER_QUALIFICATIONS", "qualifications");
      screenfieldmap.put("CURRENT_CTC", "currectctc");
      screenfieldmap.put("EXPECTED_CTC", "expectedctc");
      screenfieldmap.put("NOTE", "note");
      screenfieldmap.put("SSN_NUMBER", "ssnno");
      screenfieldmap.put("TAX_ID_NUMBER", "taxidno");
      screenfieldmap.put("RESUME_UPLOAD", "resumename");
      screenfieldmap.put("RELEVANT_YEARS_OF_EXPERINCE", "relyearsofexp");
    }
    return screenfieldmap;
  }
  
  private Map<String, String> getRequisitionVariableMap()
  {
    if (screenfieldmapReq.size() == 0)
    {
      screenfieldmapReq.put("IS_INTERNAL_JOB", "internal");
      screenfieldmapReq.put("REQUISITION_NAME", "jobreqName");
      screenfieldmapReq.put("JOB_TITLE", "designationIdValue");
      screenfieldmapReq.put("JOB_POSITION_NAME", "jobPosition");
      screenfieldmapReq.put("NO_OF_OPENING", "numberOfOpening");
      screenfieldmapReq.put("IS_NEW_POSITION", "isnewPositions");
      screenfieldmapReq.put("TARGET_FINISH_DATE", "targetfinishdate");
      screenfieldmapReq.put("CATEGORY", "catIdValue");
      screenfieldmapReq.put("ORGANIZATION", "orgIdValue");
      screenfieldmapReq.put("DEPARTMENT", "deptIdValue");
      screenfieldmapReq.put("PROJECT_CODE", "projIdValue");
      screenfieldmapReq.put("PRIMARY_SKILL_REQUIRED", "primarySkill");
      screenfieldmapReq.put("HIRING_MANAGER", "hiringMgrIdValue");
      screenfieldmapReq.put("RECRUITER", "recruiterId");
      screenfieldmapReq.put("JOB_GRADE", "jobgradeIdValue");
      screenfieldmapReq.put("SALARY_PLAN", "salaryPlanIdValue");
      screenfieldmapReq.put("BUDGET_CODE", "budgetCodeIdValue");
      screenfieldmapReq.put("NOTES", "jobreqDesc");
      screenfieldmapReq.put("INTRODUCTION", "jobInstructions");
      screenfieldmapReq.put("JOB_DETAILS", "jobDetails");
      screenfieldmapReq.put("JOB_ROLES", "jobRoles");
      screenfieldmapReq.put("DEFAULT_STANDARD_WORKING_HOURS", "defaultStandardHours");
      screenfieldmapReq.put("DURATION_IN_MONTHS", "durationinmonths");
      screenfieldmapReq.put("MIN_EXPERIENCE_REQUIRED", "minyearsofExpRequired");
      screenfieldmapReq.put("MAX_EXPERIENCE_REQUIRED", "maxyearsofExpRequired");
      screenfieldmapReq.put("MIN_LEVEL_OF_EDUCATION", "minimumLevelOfEducation");
      screenfieldmapReq.put("OTHER_EXP_REQUIRED", "otherExperience");
      screenfieldmapReq.put("JOB_TYPE", "jobtypeIdValue");
      screenfieldmapReq.put("WORK_SHIFT", "workshiftIdValue");
      screenfieldmapReq.put("FLSA_STATUS", "flsaStatusIdValue");
      screenfieldmapReq.put("COMP_FREQUENCY", "compFreqIdValue");
      screenfieldmapReq.put("JOB_CATEGORY", "jobcatIdValue");
      screenfieldmapReq.put("JOB_LOCATION", "locationIdValue");
    }
    return screenfieldmapReq;
  }
  
  private Map<String, String> getApplicantOtherDetilsVariableMap()
  {
    if (applicantOtherscreenfieldmap.size() == 0)
    {
      applicantOtherscreenfieldmap.put("EARLIEST_START_DATE", "earliest_start_date");
      applicantOtherscreenfieldmap.put("WORK_ON_WEEKENDS", "work_on_weekends");
      applicantOtherscreenfieldmap.put("WORK_ON_EVENINGS", "work_on_evenings");
      applicantOtherscreenfieldmap.put("WORK_ON_OVERTIME", "work_on_overtime");
      applicantOtherscreenfieldmap.put("WANT_TO_RELOCATE", "want_to_relocate");
      applicantOtherscreenfieldmap.put("LANGUAGES_SPOKEN", "languages_spoken");
      applicantOtherscreenfieldmap.put("WORK_STATUS", "work_status");
      applicantOtherscreenfieldmap.put("FELONY_CONVICTION", "felony_conviction");
      applicantOtherscreenfieldmap.put("WEBSITE_URL", "website_url");
      applicantOtherscreenfieldmap.put("LINKEDIN_URL", "linkedin_url");
      applicantOtherscreenfieldmap.put("FACEBOOK_URL", "facebook_url");
      applicantOtherscreenfieldmap.put("COLLEGE_NAME", "college_name");
      applicantOtherscreenfieldmap.put("COLLEGE_GPA", "college_GPA");
      applicantOtherscreenfieldmap.put("ZIPCODE", "zipcode");
      applicantOtherscreenfieldmap.put("ADDRESS", "address");
      applicantOtherscreenfieldmap.put("NATIONALITY", "validationOnlyNationalityId");
      applicantOtherscreenfieldmap.put("ETHNICITY", "validationOnlyEthnicRaceId");
    }
    return applicantOtherscreenfieldmap;
  }
  
  private Map<String, String> getEducationVariableMap()
  {
    if (educationScreenfieldmap.size() == 0)
    {
      educationScreenfieldmap.put("EDUCATION_NAME", "educationName");
      educationScreenfieldmap.put("SPECIALIZATION", "specialization");
      educationScreenfieldmap.put("EDUCATION_COLL_UNIVERSITY", "instituteName");
      educationScreenfieldmap.put("EDUCATION_PERCENT_GRADE", "percentile");
      educationScreenfieldmap.put("EDUCATION_START_MONTH_YEAR", "startingYear");
      educationScreenfieldmap.put("EDUCATION_PASSING_MONTH_YEAR", "passingYear");
      educationScreenfieldmap.put("CERTIFICATION_NAME", "educationName");
      educationScreenfieldmap.put("CERTIFIED_ORGANIZATION", "instituteName");
      educationScreenfieldmap.put("CERTIFICATION_PERCENT_GRADE", "percentile");
      educationScreenfieldmap.put("CERIFICATION_PASS_MONTH_YEAR", "passingYear");
    }
    return educationScreenfieldmap;
  }
  
  private Map<String, String> getWorkExperienceVariableMap()
  {
    if (workExpScreenfieldmap.size() == 0)
    {
      workExpScreenfieldmap.put("EMPLOYER", "prevOrgName");
      workExpScreenfieldmap.put("EMPLOYER_JOB_TITLE", "role");
      workExpScreenfieldmap.put("EMPLOYER_CITY", "city");
      workExpScreenfieldmap.put("EMPLOYER_COUNTRY", "countryIdScreenField");
      workExpScreenfieldmap.put("EMPLOYER_STATE", "stateIdScreenField");
      workExpScreenfieldmap.put("EMPLOYER_START_DATE", "startdate");
      workExpScreenfieldmap.put("EMPLOYER_END_DATE", "enddate");
      workExpScreenfieldmap.put("LAST_SALARY_DRAWN", "lastSalary");
      workExpScreenfieldmap.put("BONUS", "bonus");
      workExpScreenfieldmap.put("EMPLOYER_CURRENCY", "currencyIdScreenField");
      workExpScreenfieldmap.put("RESPONSIBILITIES", "responsibilities");
      workExpScreenfieldmap.put("EMPLOYER_CONTACT_NAME", "employercontactName");
      workExpScreenfieldmap.put("EMPLOYER_CONTACT_PHONE", "employercontactPhone");
      workExpScreenfieldmap.put("REPORTING_MANAGER", "reportingToName");
      workExpScreenfieldmap.put("REASON_FOR_LEAVING", "reasonforleave");
    }
    return workExpScreenfieldmap;
  }
  
  public List isRequisitionMandatoryValueSucceeed(JobRequisition jb, User user, String screenCode)
    throws Exception
  {
    logger.info("inside isRequisitionMandatoryValueSucceeed , screenCode" + screenCode);
    Class requitionClass = jb.getClass();
    List errorList = new ArrayList();
    


    List<ScreenFields> fieldlist = this.businessFilterDao.getVisibleMandatoryScreenFiledsByScreenCode(screenCode, user.getSuper_user_key());
    try
    {
      if ((fieldlist != null) && (fieldlist.size() > 0)) {
        for (int i = 0; i < fieldlist.size(); i++)
        {
          ScreenFields s = (ScreenFields)fieldlist.get(i);
          Object objvalue = getValueFromJobRequisition(s.getFieldcode(), jb);
          logger.info(s.getFieldcode() + objvalue);
          if ((objvalue == null) || (objvalue.equals(new Long(0L))) || (objvalue.equals(new Integer(0))))
          {
            logger.info("error for : " + s.getFieldcode());
            
            String resoucekey = "Requisition.screen." + s.getFieldcode();
            
            String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
            String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
            errorList.add(msg);
          }
          else
          {
            String value = String.valueOf(objvalue);
            if (StringUtils.isNullOrEmpty(value))
            {
              logger.info("error for" + s.getFieldcode());
              String resoucekey = "Requisition.screen." + s.getFieldcode();
              String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
              String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
              errorList.add(msg);
            }
          }
          if ((s.getFieldtype() == null) || (!s.getFieldtype().equals("numeric"))) {}
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Exception on isRequisitionMandatoryValueSucceeed" + e.getStackTrace());
      throw e;
    }
    return errorList;
  }
  
  public List isApplicantMandatoryValueSucceeed(JobApplicant applicant, long jobReqId, User user, String screenCode)
    throws Exception
  {
    logger.info("inside isMandatoryValueSucceeed , screenCode" + screenCode + user.getSuper_user_key());
    Class applicantClass = applicant.getClass();
    List errorList = new ArrayList();
    
    screenCode = checkScreenCodeBasedOnPackage(user.getSuper_user_key(), screenCode);
    

    List<ScreenFields> fieldlist = this.businessFilterDao.getVisibleMandatoryScreenFiledsByScreenCode(screenCode, user.getSuper_user_key());
    try
    {
      if ((fieldlist != null) && (fieldlist.size() > 0)) {
        for (int i = 0; i < fieldlist.size(); i++)
        {
          ScreenFields s = (ScreenFields)fieldlist.get(i);
          Object objvalue = getValueFromApplicant(s.getFieldcode(), applicant);
          logger.info(s.getFieldcode() + objvalue);
          if ((objvalue == null) || (objvalue.equals(new Long(0L))) || (objvalue.equals(new Double(0.0D))) || (objvalue.equals(new Integer(0))))
          {
            logger.info("error for : " + s.getFieldcode());
            
            String resoucekey = "aquisition.applicant." + s.getFieldcode();
            
            String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
            String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
            errorList.add(msg);
          }
          else
          {
            String value = String.valueOf(objvalue);
            if (StringUtils.isNullOrEmpty(value))
            {
              logger.info("error for" + s.getFieldcode());
              String resoucekey = "aquisition.applicant." + s.getFieldcode();
              String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
              String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
              errorList.add(msg);
            }
          }
          if ((s.getFieldtype() == null) || (!s.getFieldtype().equals("numeric"))) {}
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Exception on isMandatoryValueSucceeed" + e.getStackTrace());
      throw e;
    }
    return errorList;
  }
  
  private Object getValueFromApplicant(String fieldcode, JobApplicant applicant)
    throws Exception
  {
    Class applicantClass = applicant.getClass();
    ApplicantOtherDetails otherdetails = applicant.getOtherdetails();
    logger.info("otherdetails" + otherdetails);
    Class applicantOtherDetailsClass = otherdetails.getClass();
    Object objvalue = null;
    String filedname = (String)getApplicantVariableMap().get(fieldcode);
    logger.info("filedname >> " + filedname);
    if (filedname != null)
    {
      objvalue = applicantClass.getField(filedname).get(applicant);
    }
    else
    {
      logger.info("fieldcode" + fieldcode);
      filedname = (String)getApplicantOtherDetilsVariableMap().get(fieldcode);
      logger.info("filedname" + filedname);
      objvalue = applicantOtherDetailsClass.getField(filedname).get(otherdetails);
    }
    return objvalue;
  }
  
  private Object getValueFromJobRequisition(String fieldcode, JobRequisition jb)
    throws Exception
  {
    Class reqClass = jb.getClass();
    

    Object objvalue = null;
    String filedname = (String)getRequisitionVariableMap().get(fieldcode);
    if (filedname != null) {
      objvalue = reqClass.getField(filedname).get(jb);
    }
    return objvalue;
  }
  
  public List isApplicantProfileMandatoryValueSucceeed(JobApplicant applicant, long jobReqId, User user)
    throws Exception
  {
    logger.info("inside isApplicantProfileMandatoryValueSucceeed");
    Class applicantClass = applicant.getClass();
    List errorList = new ArrayList();
    


    List<ScreenFields> fieldlist = this.businessFilterDao.getVisibleMandatoryScreenFiledsByScreenCode("APPLICANT_SCREEN_PROFILE", user.getSuper_user_key());
    try
    {
      if ((fieldlist != null) && (fieldlist.size() > 0)) {
        for (int i = 0; i < fieldlist.size(); i++)
        {
          ScreenFields s = (ScreenFields)fieldlist.get(i);
          
          Object objvalue = getValueFromApplicant(s.getFieldcode(), applicant);
          logger.info(s.getFieldcode() + objvalue);
          if ((objvalue == null) || (objvalue.equals(new Long(0L))))
          {
            logger.info("error for" + s.getFieldcode());
            String resoucekey = "aquisition.applicant." + s.getFieldcode();
            String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
            String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
            errorList.add(msg);
          }
          else
          {
            String value = String.valueOf(objvalue);
            if (StringUtils.isNullOrEmpty(value))
            {
              logger.info("error for" + s.getFieldcode());
              String resoucekey = "aquisition.applicant." + s.getFieldcode();
              String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
              String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
              errorList.add(msg);
            }
          }
          if ((s.getFieldtype() == null) || (!s.getFieldtype().equals("numeric"))) {}
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Exception on isMandatoryValueSucceeed" + e.getStackTrace());
      throw e;
    }
    return errorList;
  }
  
  public List isWorkExpepenceMandatoryValueSucceeed(PreviousOrgDetails workexp, User user, String screenCode)
    throws Exception
  {
    logger.info("inside isWorkExpepenceMandatoryValueSucceeed");
    Class workexpClass = workexp.getClass();
    List errorList = new ArrayList();
    


    List<ScreenFields> fieldlist = this.businessFilterDao.getVisibleMandatoryScreenFiledsByScreenCode(screenCode, "WORK_EXPERIENCE");
    try
    {
      if ((fieldlist != null) && (fieldlist.size() > 0)) {
        for (int i = 0; i < fieldlist.size(); i++)
        {
          ScreenFields s = (ScreenFields)fieldlist.get(i);
          
          Object objvalue = null;
          String filedname = (String)getWorkExperienceVariableMap().get(s.getFieldcode());
          if (filedname != null) {
            objvalue = workexpClass.getField(filedname).get(workexp);
          }
          logger.info(s.getFieldcode() + objvalue);
          if ((objvalue == null) || (objvalue.equals(new Long(0L))))
          {
            logger.info("error for" + s.getFieldcode());
            String resoucekey = "aquisition.applicant." + s.getFieldcode();
            String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
            String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
            errorList.add(msg);
          }
          else
          {
            String value = String.valueOf(objvalue);
            if (StringUtils.isNullOrEmpty(value))
            {
              logger.info("error for" + s.getFieldcode());
              String resoucekey = "aquisition.applicant." + s.getFieldcode();
              String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
              String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
              errorList.add(msg);
            }
          }
          if ((s.getFieldtype() == null) || (!s.getFieldtype().equals("numeric"))) {}
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Exception on isWorkExpepenceMandatoryValueSucceeed" + e.getStackTrace());
      throw e;
    }
    return errorList;
  }
  
  public List isEducationMandatoryValueSucceeed(EducationDetails edu, User user, String type, String screenCode)
    throws Exception
  {
    logger.info("inside isEducationMandatoryValueSucceeed");
    Class eduClass = edu.getClass();
    List errorList = new ArrayList();
    
    List<ScreenFields> fieldlist = null;
    if ((type != null) && (type.equals(Common.EDUCATION_COLLEGE))) {
      fieldlist = this.businessFilterDao.getVisibleMandatoryScreenFiledsByScreenCode(screenCode, "EDUCATION_DETAILS");
    } else {
      fieldlist = this.businessFilterDao.getVisibleMandatoryScreenFiledsByScreenCode(screenCode, "CERTIFICATION_DETAILS");
    }
    try
    {
      if ((fieldlist != null) && (fieldlist.size() > 0)) {
        for (int i = 0; i < fieldlist.size(); i++)
        {
          ScreenFields s = (ScreenFields)fieldlist.get(i);
          
          Object objvalue = null;
          String filedname = (String)getEducationVariableMap().get(s.getFieldcode());
          if (filedname != null) {
            objvalue = eduClass.getField(filedname).get(edu);
          }
          logger.info(s.getFieldcode() + objvalue);
          if ((objvalue == null) || (objvalue.equals(new Long(0L))))
          {
            logger.info("error for" + s.getFieldcode());
            String resoucekey = "aquisition.applicant." + s.getFieldcode();
            String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
            String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
            errorList.add(msg);
          }
          else
          {
            String value = String.valueOf(objvalue);
            if (StringUtils.isNullOrEmpty(value))
            {
              logger.info("error for" + s.getFieldcode());
              String resoucekey = "aquisition.applicant." + s.getFieldcode();
              String[] args = { Constant.getResourceStringValue(resoucekey, user.getLocale()) };
              String msg = Constant.getResourceStringValue("value.is.mandatory", user.getLocale(), args);
              errorList.add(msg);
            }
          }
          if ((s.getFieldtype() == null) || (!s.getFieldtype().equals("numeric"))) {}
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Exception on isWorkExpepenceMandatoryValueSucceeed" + e.getStackTrace());
      throw e;
    }
    return errorList;
  }
  
  public ScreenFieldData screenFieldConfigurationsByScreenCode(String screencode, long superUserKey)
  {
    ScreenFieldData scr = new ScreenFieldData();
    
    String screencodeWorkExp = "";
    String screencodeEducation = "";
    
    screencode = checkScreenCodeBasedOnPackage(superUserKey, screencode);
    if ((!StringUtils.isNullOrEmpty(screencode)) && (screencode.equals("APPLICANT_SCREEN")))
    {
      screencodeWorkExp = "APPLICANT_SCREEN_WORK_EXP";
      screencodeEducation = "APPLICANT_SCREEN_EDUCATION";
    }
    else if ((!StringUtils.isNullOrEmpty(screencode)) && (screencode.equals("APPLICANT_SCREEN_AGENCY")))
    {
      screencodeWorkExp = "APPLICANT_SCREEN_WORK_EXP_AGENCY";
      screencodeEducation = "APPLICANT_SCREEN_EDUCATION_AGENCY";
    }
    else if ((!StringUtils.isNullOrEmpty(screencode)) && (screencode.equals("APPLICANT_SCREEN_REFERRAL")))
    {
      screencodeWorkExp = "APPLICANT_SCREEN_WORK_EXP_REFERRAL";
      screencodeEducation = "APPLICANT_SCREEN_EDUCATION_REFERRAL";
    }
    else if ((!StringUtils.isNullOrEmpty(screencode)) && (screencode.equals("APPLICANT_SCREEN_TALENTPOOL")))
    {
      screencodeWorkExp = "APPLICANT_SCREEN_WORK_EXP_TALENTPOOL";
      screencodeEducation = "APPLICANT_SCREEN_EDUCATION_TALENTPOOL";
    }
    else if ((!StringUtils.isNullOrEmpty(screencode)) && (screencode.equals("APPLICANT_SCREEN_TALENTPOOL_EXTERNAL")))
    {
      screencodeWorkExp = "APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL";
      screencodeEducation = "APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL";
    }
    else if ((!StringUtils.isNullOrEmpty(screencode)) && (screencode.equals("APPLICANT_SCREEN_EXTERNAL")))
    {
      screencodeWorkExp = "APPLICANT_SCREEN_WORK_EXP_EXTERNAL";
      screencodeEducation = "APPLICANT_SCREEN_EDUCATION_EXTERNAL";
    }
    else if ((!StringUtils.isNullOrEmpty(screencode)) && (screencode.equals("APPLICANT_SCREEN_PROFILE")))
    {
      screencodeWorkExp = "APPLICANT_SCREEN_WORK_EXP_PROFILE";
      screencodeEducation = "APPLICANT_SCREEN_EDUCATION_PROFILE";
    }
    Map<String, List<String>> screenMap = getVisibleAndMandatoryScreenFiledsByScreenCode(screencode, superUserKey);
    Map<String, List<String>> screenMapWorkExp = getVisibleAndMandatoryScreenFiledsByScreenCode(screencodeWorkExp, superUserKey);
    Map<String, List<String>> screenMapEducationDetials = getVisibleAndMandatoryScreenFiledsByScreenCode(screencodeEducation, superUserKey);
    
    List screenfieldList = getVisibleScreenFieldList(screencode, superUserKey);
    List screenfieldListWorkTab = getVisibleScreenFieldList(screencodeWorkExp, superUserKey);
    List screenfieldListEduTab = getVisibleScreenFieldList(screencodeEducation, superUserKey);
    


    scr.setScreenMap(screenMap);
    scr.setScreenMapEducationDetials(screenMapEducationDetials);
    scr.setScreenMapWorkExp(screenMapWorkExp);
    scr.setScreenfieldList(screenfieldList);
    scr.setScreenfieldListEduTab(screenfieldListEduTab);
    scr.setScreenfieldListWorkTab(screenfieldListWorkTab);
    return scr;
  }
  
  public String checkScreenCodeBasedOnPackage(long superUserKey, String screenCode)
  {
    UserRegData userreg = BOFactory.getUserBO().getUserRegDataById(superUserKey);
    if ((!StringUtils.isNullOrEmpty(userreg.getPackagetaken())) && (Constant.isPackageContainFunction(userreg.getPackagetaken(), "screenSetting"))) {
      screenCode = screenCode;
    } else {
      screenCode = "APPLICANT_SCREEN";
    }
    return screenCode;
  }
  
  public Map<String, List<String>> getVisibleAndMandatoryScreenFiledsByScreenCode(String screencode, long superUserKey)
  {
    if ((!StringUtils.isNullOrEmpty(screencode)) && (Constant.screenCodeListAppScreenOtherPackage.contains(screencode))) {
      screencode = checkScreenCodeBasedOnPackage(superUserKey, screencode);
    }
    return this.businessFilterDao.getVisibleAndMandatoryScreenFiledsByScreenCode(screencode, superUserKey);
  }
  
  public List getVisibleScreenFieldList(String screencode, long superUserKey)
  {
    if ((!StringUtils.isNullOrEmpty(screencode)) && (Constant.screenCodeListAppScreenOtherPackage.contains(screencode))) {
      screencode = checkScreenCodeBasedOnPackage(superUserKey, screencode);
    }
    return this.businessFilterDao.getVisibleScreenFieldList(screencode, superUserKey);
  }
  
  public BusinessFilterDAO getBusinessFilterDao()
  {
    return this.businessFilterDao;
  }
  
  public void setBusinessFilterDao(BusinessFilterDAO businessFilterDao)
  {
    this.businessFilterDao = businessFilterDao;
  }
}
