package com.action.employee;

import com.action.CommonAction;
import com.bean.User;
import com.bean.employee.UserReportTo;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.form.employee.UserReportToForm;
import com.resources.Constant;
import com.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserReportToAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserReportToAction.class);
  
  public ActionForward userReportTodetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userReportTodetails method");
    UserReportToForm userReportToForm = (UserReportToForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userReportToForm.setUserId(new Long(userId).longValue());
    UserReportTo userReportTodetails = BOFactory.getUserBO().getUserReportTodetailsByUserId(new Long(userId).longValue());
    if (userReportTodetails != null)
    {
      String firstName = userReportTodetails.getUserReportTo().getFirstName();
      String lastName = userReportTodetails.getUserReportTo().getLastName();
      userReportToForm.setReportToUserName(firstName + " " + lastName);
      userReportToForm.setReportToUserId(userReportTodetails.getUserReportTo().getUserId());
      userReportToForm.fromValue(userReportTodetails, request);
    }
    return mapping.findForward("userReportTodetails");
  }
  
  public ActionForward editReportTodetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userReportTodetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserReportToForm userReportToForm = (UserReportToForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userReportToForm.setUserId(new Long(userId).longValue());
    UserReportTo userReportTodetails = BOFactory.getUserBO().getUserReportTodetailsByUserId(new Long(userId).longValue());
    if (userReportTodetails != null)
    {
      String firstName = userReportTodetails.getUserReportTo().getFirstName();
      String lastName = userReportTodetails.getUserReportTo().getLastName();
      userReportToForm.setReportToUserName(firstName + " " + lastName);
      userReportToForm.setReportToUserId(userReportTodetails.getUserReportTo().getUserId());
      userReportToForm.fromValue(userReportTodetails, request);
    }
    userReportToForm.setReportingMethodsList(Constant.getReportingMethodsList(user1));
    

    return mapping.findForward("editReportTodetails");
  }
  
  public ActionForward saveUserReportTodata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserReportTodata method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserReportToForm userReportToForm = (UserReportToForm)form;
    UserReportTo userReportTodetails = new UserReportTo();
    String reportToUserId = request.getParameter("reportToUserId");
    logger.info("reportToUserId >>>> " + reportToUserId.trim());
    if (reportToUserId != null)
    {
      User userReportToObj = new User();
      userReportToObj.setUserId(new Long(reportToUserId.trim()).longValue());
      userReportTodetails.setUserReportTo(userReportToObj);
    }
    userReportToForm.toValue(userReportTodetails, request);
    

    userReportTodetails = BOFactory.getUserBO().saveUserReportTodata(userReportTodetails);
    logger.info(Long.valueOf(userReportTodetails.getUserReportTo().getUserId()));
    userReportToForm.setReportingMethodsList(Constant.getReportingMethodsList(user1));
    userReportToForm.fromValue(userReportTodetails, request);
    request.setAttribute("userreporttodatasaved", "yes");
    return mapping.findForward("editReportTodetails");
  }
  
  public ActionForward updateUserReportTodata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserReportTodata method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserReportToForm userReportToForm = (UserReportToForm)form;
    String reportToId = request.getParameter("reportToId");
    String reportToMethod = request.getParameter("reportToMethod");
    String reportToMethodOther = request.getParameter("reportToMethodOther");
    
    logger.info("reportToId >> " + reportToId);
    UserReportTo userReportTodetails = BOFactory.getUserBO().getUserReportTodetailsById(new Long(reportToId).longValue());
    
    String reportToUserId = request.getParameter("reportToUserId");
    logger.info("reportToUserId >>>> " + reportToUserId.trim());
    if (reportToUserId != null)
    {
      User userReportToObj = new User();
      userReportToObj.setUserId(new Long(reportToUserId.trim()).longValue());
      userReportTodetails.setUserReportTo(userReportToObj);
    }
    userReportToForm.toValue(userReportTodetails, request);
    if ((!StringUtils.isNullOrEmpty(reportToMethod)) && (reportToMethod.equals("Other"))) {
      userReportTodetails.setReportToMethodOther(reportToMethodOther);
    } else {
      userReportTodetails.setReportToMethodOther("");
    }
    userReportTodetails = BOFactory.getUserBO().updateUserReportTodata(userReportTodetails);
    
    userReportToForm.setReportingMethodsList(Constant.getReportingMethodsList(user1));
    userReportToForm.fromValue(userReportTodetails, request);
    request.setAttribute("userreporttodatasaved", "yes");
    return mapping.findForward("editReportTodetails");
  }
  
  public ActionForward showOtherMethodReportToTextbox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside showOtherMethodReportToTextbox method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserReportToForm userReportToForm = (UserReportToForm)form;
    

    return mapping.findForward("showOtherMethodReportToTextbox");
  }
}
