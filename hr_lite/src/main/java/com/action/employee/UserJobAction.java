package com.action.employee;

import com.action.CommonAction;
import com.bean.Designations;
import com.bean.Timezone;
import com.bean.User;
import com.bean.employee.UserJobDetails;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.employee.UserJobForm;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserJobAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserJobAction.class);
  
  public ActionForward userJobdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userJobdetails method");
    UserJobForm userJobForm = (UserJobForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userJobForm.setUserId(new Long(userId).longValue());
    UserJobDetails userJobDetails = BOFactory.getUserBO().getUserJobdetailsByUserId(new Long(userId).longValue());
    if (userJobDetails != null) {
      userJobForm.fromValue(userJobDetails, request);
    }
    User user = BOFactory.getUserBO().getUserByUserid(new Long(userId).longValue());
    if (user.getDesignation() != null)
    {
      userJobForm.setDesignationId(user.getDesignationId());
      userJobForm.setDesignationName(user.getDesignation().getDesignationName());
    }
    return mapping.findForward("userJobdetails");
  }
  
  public ActionForward editUserJobdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserJobdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserJobForm userJobForm = (UserJobForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userJobForm.setUserId(new Long(userId).longValue());
    
    UserJobDetails userJobDetails = BOFactory.getUserBO().getUserJobdetailsByUserId(new Long(userId).longValue());
    if (userJobDetails != null) {
      userJobForm.fromValue(userJobDetails, request);
    }
    User user = BOFactory.getUserBO().getUserByUserid(new Long(userId).longValue());
    if (user.getDesignation() != null)
    {
      userJobForm.setDesignationId(user.getDesignation().getDesignationId());
      userJobForm.setDesignationName(user.getDesignation().getDesignationName());
    }
    userJobForm.setJobtypeList(BOFactory.getLovBO().getJobTypeList(user1.getSuper_user_key()));
    userJobForm.setDesignationList(BOFactory.getLovBO().getAllDesignations(user1.getSuper_user_key()));
    userJobForm.setJobCategoryList(BOFactory.getLovBO().getJobCategoryList(user1.getSuper_user_key()));
    userJobForm.setJobgradeList(BOFactory.getLovBO().getAllJobGradeDetails(user1.getSuper_user_key()));
    


    return mapping.findForward("editUserJobdetails");
  }
  
  public ActionForward saveUserJobdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserJobdata method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserJobForm userJobForm = (UserJobForm)form;
    UserJobDetails userJobDetails = new UserJobDetails();
    String userId = request.getParameter("userId");
    
    logger.info("userId >> " + userId);
    userJobForm.setUserId(new Long(userId).longValue());
    String designationId = request.getParameter("designationId");
    

    String joinedDate = request.getParameter("joinedDate");
    String contractStartDate = request.getParameter("contractStartDate");
    String contractEndDate = request.getParameter("contractEndDate");
    if (!StringUtils.isNullOrEmpty(joinedDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(joinedDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userJobDetails.setJoinedDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(contractStartDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(contractStartDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userJobDetails.setContractStartDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(contractEndDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(contractEndDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userJobDetails.setContractEndDate(cal.getTime());
    }
    userJobForm.toValue(userJobDetails, request);
    
    userJobDetails = BOFactory.getUserBO().saveUserJobdata(userJobDetails);
    
    BOFactory.getUserBO().updateDesignationByUserId(new Long(userId).longValue(), new Long(designationId).longValue());
    
    userJobForm.setJobtypeList(BOFactory.getLovBO().getJobTypeList(user1.getSuper_user_key()));
    userJobForm.setDesignationList(BOFactory.getLovBO().getAllDesignations(user1.getSuper_user_key()));
    userJobForm.setJobCategoryList(BOFactory.getLovBO().getJobCategoryList(user1.getSuper_user_key()));
    userJobForm.setJobgradeList(BOFactory.getLovBO().getAllJobGradeDetails(user1.getSuper_user_key()));
    request.setAttribute("saveUserJobdataSaved", "Yes");
    
    return mapping.findForward("editUserJobdetails");
  }
  
  public ActionForward updateUserJobdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserJobdata method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserJobForm userJobForm = (UserJobForm)form;
    
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    String jobdetailsId = request.getParameter("jobdetailsId");
    logger.info("jobdetailsId >> " + jobdetailsId);
    userJobForm.setJobdetailsId(new Long(jobdetailsId).longValue());
    String designationId = request.getParameter("designationId");
    
    String joinedDate = request.getParameter("joinedDate");
    String contractStartDate = request.getParameter("contractStartDate");
    String contractEndDate = request.getParameter("contractEndDate");
    

    UserJobDetails userJobDetails = BOFactory.getUserBO().getUserJobdetails(new Long(jobdetailsId).longValue());
    if (!StringUtils.isNullOrEmpty(joinedDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(joinedDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userJobDetails.setJoinedDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(contractStartDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(contractStartDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userJobDetails.setContractStartDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(contractEndDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(contractEndDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userJobDetails.setContractEndDate(cal.getTime());
    }
    userJobForm.toValue(userJobDetails, request);
    
    userJobDetails = BOFactory.getUserBO().updateUserJobdata(userJobDetails);
    if (!StringUtils.isNullOrEmpty(designationId)) {
      BOFactory.getUserBO().updateDesignationByUserId(new Long(userId).longValue(), new Long(designationId).longValue());
    }
    userJobForm.setJobtypeList(BOFactory.getLovBO().getJobTypeList(user1.getSuper_user_key()));
    userJobForm.setDesignationList(BOFactory.getLovBO().getAllDesignations(user1.getSuper_user_key()));
    userJobForm.setJobCategoryList(BOFactory.getLovBO().getJobCategoryList(user1.getSuper_user_key()));
    userJobForm.setJobgradeList(BOFactory.getLovBO().getAllJobGradeDetails(user1.getSuper_user_key()));
    request.setAttribute("saveUserJobdataSaved", "Yes");
    
    return mapping.findForward("editUserJobdetails");
  }
}
