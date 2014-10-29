package com.action;

import com.bean.Menu;
import com.bean.User;
import com.bean.UserRegData;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.dao.UserDAO;
import com.form.UserRegForm;
import com.resources.Constant;
import com.util.EmailValidator;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserRegAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(UserRegAction.class);
  
  public ActionForward reg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside reg method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String pkg = request.getParameter("pkg");
    UserRegForm regform = (UserRegForm)form;
    regform.setPackagetaken(pkg);
    BOFactory.getLovBO().regUserLovs(regform);
    regform.setNationalityId(185L);
    return mapping.findForward("reg");
  }
  
  public ActionForward saveUserdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveUserdata method");
    
    UserRegForm regform = (UserRegForm)form;
    
    UserRegData userreg = new UserRegData();
    regform.setIpaddress(request.getRemoteAddr());
    userreg = regform.toValue(userreg, request);
    
    UserRegData userold = BOFactory.getUserBO().getUserRegDataByipaddress(request.getRemoteAddr());
    if ((regform.getIpaddress() == null) || (userold != null))
    {
      BOFactory.getLovBO().regUserLovs(regform);
      request.setAttribute("alreadyexist", "yes");
    }
    else
    {
      User user = BOFactory.getUserBO().saveUserReg(userreg, regform);
      request.setAttribute("savesucces", "yes");
      
      Menu menu = new Menu();
      menu.setupMenuAndPackage(user);
      user.setMenu(menu);
      request.getSession().setAttribute("user_data", user);
      String url = "/dashboard.do?method=dashboardlist";
      ActionForward forward = new ActionForward(url);
      forward.setRedirect(true);
      return forward;
    }
    return mapping.findForward("reg");
  }
  
  public ActionForward emailidexistcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside emailidexistcheck method");
    String email = request.getParameter("email");
    UserRegForm regform = (UserRegForm)form;
    logger.info("Inside emailidexistcheck method" + email);
    
    boolean isvalid = new EmailValidator().validate(email);
    logger.info("Inside emailidexistcheck method isvalid" + isvalid);
    if (!isvalid)
    {
      request.setAttribute("emailidexistcheckfail", "notvalid");
    }
    else
    {
      String emailid = UserDAO.isEmailIdExist(email);
      logger.info("emailid" + emailid);
      if (emailid != null) {
        request.setAttribute("emailidexistcheckfail", "yes");
      } else {
        request.setAttribute("emailidexistcheckfail", "no");
      }
    }
    return mapping.findForward("emailidexistcheck");
  }
  
  public ActionForward subdomainexistcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside subdomainexistcheck method");
    String subdomain = request.getParameter("subdomain");
    UserRegForm regform = (UserRegForm)form;
    logger.info("Inside subdomainexistcheck method" + subdomain);
    if ((StringUtils.isNullOrEmpty(subdomain)) || (subdomain.equals("mycompany")))
    {
      request.setAttribute("subdomainexistcheckfail", "yes");
      return mapping.findForward("subdomainexistcheck");
    }
    UserRegData userreg = BOFactory.getUserBO().getUserRegDataBySubdomain(subdomain);
    if (userreg != null) {
      request.setAttribute("subdomainexistcheckfail", "yes");
    } else {
      request.setAttribute("subdomainexistcheckfail", "no");
    }
    return mapping.findForward("subdomainexistcheck");
  }
  
  public ActionForward registerUserListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside registerUserListpage method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String subdomain = request.getParameter("subdomain");
    UserRegForm regform = (UserRegForm)form;
    List userRegDataList = new ArrayList();
    userRegDataList = BOFactory.getUserBO().getUserRegDataList();
    logger.info("userRegDataList >> " + userRegDataList.size());
    regform.setUserRegDataList(userRegDataList);
    if (!user1.getUserName().equals(Constant.getValue("default.username"))) {
      return null;
    }
    return mapping.findForward("registerUserList");
  }
  
  public ActionForward updateRegUserData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateRegUserData method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userRegdataid = request.getParameter("userRegdataid");
    String months = request.getParameter("months");
    String packagetaken = request.getParameter("packagetaken");
    String ipaddress = request.getParameter("ipaddress");
    logger.info("packagetaken >> " + packagetaken);
    if (!user1.getUserName().equals(Constant.getValue("default.username"))) {
      return null;
    }
    UserRegForm regform = (UserRegForm)form;
    Date dt = new Date();
    if (!StringUtils.isNullOrEmpty(months)) {
      dt.setMonth(dt.getMonth() + new Integer(months).intValue());
    }
    UserRegData userreg = BOFactory.getUserBO().getUserRegDataByid(userRegdataid);
    userreg.setIpaddress(ipaddress);
    userreg.setUpdatedBy(user1.getEmailId());
    userreg.setUpdatedDate(new Date());
    if (!StringUtils.isNullOrEmpty(months)) {
      userreg.setExpireDate(dt);
    }
    String oldpackage = userreg.getPackagetaken();
    
    userreg.setPackagetaken(packagetaken.trim());
    
    userreg = BOFactory.getUserBO().updateRegUserData(userreg, oldpackage);
    regform.setUserRegId(userreg.getUserRegId());
    

    List userRegDataList = new ArrayList();
    userRegDataList = BOFactory.getUserBO().getUserRegDataList();
    regform.setUserRegDataList(userRegDataList);
    
    regform.setPackagetaken(userreg.getPackagetaken());
    request.setAttribute("updated", "yes");
    

    return mapping.findForward("registerUserList");
  }
}
