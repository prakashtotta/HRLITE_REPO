package com.action.employee;

import com.action.CommonAction;
import com.bean.User;
import com.bean.employee.UserSalary;
import com.bo.BOFactory;
import com.bo.LovTXBO;
import com.bo.UserBO;
import com.form.employee.UserSalaryForm;
import com.resources.Constant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserSalaryAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserImmigrationAction.class);
  
  public ActionForward userSalarydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userSalarydetails method");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userSalaryForm.setUserId(new Long(userId).longValue());
    userSalaryForm.setUserId(new Long(userId).longValue());
    
    List userSalaryComponentList = new ArrayList();
    userSalaryComponentList = BOFactory.getUserBO().getUserSalaryComponentListByUserId(new Long(userId).longValue());
    logger.info("userSalaryComponentList.size() >> " + userSalaryComponentList.size());
    userSalaryForm.setUserSalaryComponentList(userSalaryComponentList);
    

    return mapping.findForward("userSalarydetails");
  }
  
  public ActionForward addUserSalaryComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserSalaryComponent method");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List salaryplanList = BOFactory.getLovTXBO().getAllSalaryDetails(user1.getSuper_user_key());
    userSalaryForm.setSalaryplanList(salaryplanList);
    
    List compfrequencyList = BOFactory.getLovTXBO().getCompFrequencyList();
    userSalaryForm.setCompfrequencyList(compfrequencyList);
    
    List accounttypelist = Constant.getApplicantTypeList(user1);
    userSalaryForm.setAccountTypeList(accounttypelist);
    return mapping.findForward("addUserSalaryComponent");
  }
  
  public ActionForward saveUserSalaryComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserSalaryComponent method");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    UserSalary userSalary = new UserSalary();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String isdirectdeposite = request.getParameter("isdirectdeposite");
    logger.info("isdirectdeposite >> " + isdirectdeposite);
    userSalaryForm.setIsBankAcountDetails(isdirectdeposite);
    userSalaryForm.toValue(userSalary, request);
    userSalary = BOFactory.getUserBO().saveUserSalaryComponent(userSalary);
    
    userSalaryForm.fromValue(userSalary, request);
    List salaryplanList = BOFactory.getLovTXBO().getAllSalaryDetails(user1.getSuper_user_key());
    userSalaryForm.setSalaryplanList(salaryplanList);
    
    List compfrequencyList = BOFactory.getLovTXBO().getCompFrequencyList();
    userSalaryForm.setCompfrequencyList(compfrequencyList);
    

    List accounttypelist = Constant.getApplicantTypeList(user1);
    userSalaryForm.setAccountTypeList(accounttypelist);
    request.setAttribute("usersalarycomponentsaved", "yes");
    return mapping.findForward("addUserSalaryComponent");
  }
  
  public ActionForward editUserSalaryComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserSalaryComponent method");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    userSalaryForm.setUserId(new Long(userId).longValue());
    String userSalaryId = request.getParameter("userSalaryId");
    logger.info("userSalaryId >> " + userSalaryId);
    UserSalary userSalary = BOFactory.getUserBO().getUserSalaryInfo(new Long(userSalaryId).longValue());
    
    userSalaryForm.fromValue(userSalary, request);
    List salaryplanList = BOFactory.getLovTXBO().getAllSalaryDetails(user1.getSuper_user_key());
    userSalaryForm.setSalaryplanList(salaryplanList);
    
    List compfrequencyList = BOFactory.getLovTXBO().getCompFrequencyList();
    userSalaryForm.setCompfrequencyList(compfrequencyList);
    
    List accounttypelist = Constant.getApplicantTypeList(user1);
    userSalaryForm.setAccountTypeList(accounttypelist);
    

    return mapping.findForward("addUserSalaryComponent");
  }
  
  public ActionForward updateUserSalaryComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserSalaryComponent method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    String userSalaryId = request.getParameter("userSalaryId");
    logger.info("userSalaryId >> " + userSalaryId);
    String isdirectdeposite = request.getParameter("isdirectdeposite");
    logger.info("isdirectdeposite >> " + isdirectdeposite);
    
    UserSalary userSalary = BOFactory.getUserBO().getUserSalaryInfo(new Long(userSalaryId).longValue());
    userSalaryForm.setIsBankAcountDetails(isdirectdeposite);
    
    userSalaryForm.toValue(userSalary, request);
    


    userSalary = BOFactory.getUserBO().updateUserSalaryComponent(userSalary);
    

    List salaryplanList = BOFactory.getLovTXBO().getAllSalaryDetails(user1.getSuper_user_key());
    userSalaryForm.setSalaryplanList(salaryplanList);
    
    List compfrequencyList = BOFactory.getLovTXBO().getCompFrequencyList();
    userSalaryForm.setCompfrequencyList(compfrequencyList);
    
    List accounttypelist = Constant.getApplicantTypeList(user1);
    userSalaryForm.setAccountTypeList(accounttypelist);
    userSalaryForm.fromValue(userSalary, request);
    
    request.setAttribute("usersalarycomponentupdated", "yes");
    return mapping.findForward("addUserSalaryComponent");
  }
  
  public ActionForward deleteUserSalaryComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserSalaryComponent method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    String userSalaryId = request.getParameter("userSalaryId");
    logger.info("userSalaryId >> " + userSalaryId);
    UserSalary userSalary = BOFactory.getUserBO().getUserSalaryInfo(new Long(userSalaryId).longValue());
    

    userSalaryForm.toValue(userSalary, request);
    userSalary = BOFactory.getUserBO().deleteUserSalaryComponent(userSalary);
    
    userSalaryForm.fromValue(userSalary, request);
    List salaryplanList = BOFactory.getLovTXBO().getAllSalaryDetails(user1.getSuper_user_key());
    userSalaryForm.setSalaryplanList(salaryplanList);
    
    List compfrequencyList = BOFactory.getLovTXBO().getCompFrequencyList();
    userSalaryForm.setCompfrequencyList(compfrequencyList);
    
    List accounttypelist = Constant.getApplicantTypeList(user1);
    userSalaryForm.setAccountTypeList(accounttypelist);
    
    request.setAttribute("usersalarycomponentdeleted", "yes");
    return mapping.findForward("addUserSalaryComponent");
  }
  
  public ActionForward deleteUserSalarydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserSalarydetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userSalaryForm.setUserId(new Long(userId).longValue());
    String checkedItems = request.getParameter("checkedItems");
    

    BOFactory.getUserBO().deleteUserSalarydetailsMultiple(checkedItems);
    

    List userSalaryComponentList = new ArrayList();
    userSalaryComponentList = BOFactory.getUserBO().getUserSalaryComponentListByUserId(new Long(userId).longValue());
    logger.info("userSalaryComponentList.size() >> " + userSalaryComponentList.size());
    userSalaryForm.setUserSalaryComponentList(userSalaryComponentList);
    
    request.setAttribute("deleteUserSalarydetails", "yes");
    return mapping.findForward("userSalarydetails");
  }
  
  public ActionForward showBankDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside showBankDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserSalaryForm userSalaryForm = (UserSalaryForm)form;
    String showbankdetails = request.getParameter("showbankdetails");
    logger.info("showbankdetails >> " + showbankdetails);
    if (showbankdetails.equals("YES"))
    {
      userSalaryForm.setIsBankAcountDetails("Y");
      return mapping.findForward("showBankDetails");
    }
    userSalaryForm.setIsBankAcountDetails("N");
    

    List accounttypelist = Constant.getApplicantTypeList(user1);
    userSalaryForm.setAccountTypeList(accounttypelist);
    return mapping.findForward("hideBankDetails");
  }
  
  public ActionForward showOtherAccountTypeTextbox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside showOtherAccountTypeTextbox method");
    
    String showtextbox = request.getParameter("showtextbox");
    if (showtextbox.equals("YES")) {
      return mapping.findForward("showOtherAccountTypeTextbox");
    }
    return mapping.findForward("hideOtherAccountTypeTextbox");
  }
}
