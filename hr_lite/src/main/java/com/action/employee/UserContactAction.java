package com.action.employee;

import com.action.CommonAction;
import com.bean.Country;
import com.bean.employee.UserContactInfo;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.employee.UserContactForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserContactAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserContactAction.class);
  
  public ActionForward contactdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    UserContactForm eform = (UserContactForm)form;
    String userId = request.getParameter("userId");
    UserContactInfo usercontact = BOFactory.getUserBO().getUserContactInfo(new Long(userId).longValue());
    if (usercontact != null) {
      eform.fromValue(usercontact, request);
    }
    return mapping.findForward("contactdetails");
  }
  
  public ActionForward editusercontact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    UserContactForm eform = (UserContactForm)form;
    String userId = request.getParameter("userId");
    
    UserContactInfo usercontact = BOFactory.getUserBO().getUserContactInfo(new Long(userId).longValue());
    if (usercontact != null) {
      eform.fromValue(usercontact, request);
    }
    List countryList = BOFactory.getLovBO().getAllCountries();
    eform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = null;
    if (eform.getCountryId() != 0L) {
      stateList = BOFactory.getLovBO().getStateListByCountry(eform.getCountryId());
    } else {
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    eform.setStateList(stateList);
    return mapping.findForward("editcontact");
  }
  
  public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside save method");
    UserContactForm eform = (UserContactForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >>> " + userId);
    UserContactInfo usercontact1 = new UserContactInfo();
    
    eform.toValue(usercontact1, request);
    usercontact1 = BOFactory.getUserBO().saveUserContactInfo(usercontact1);
    
    eform.fromValue(usercontact1, request);
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    eform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = null;
    if (eform.getCountryId() != 0L) {
      stateList = BOFactory.getLovBO().getStateListByCountry(eform.getCountryId());
    } else {
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    eform.setStateList(stateList);
    request.setAttribute("contactinfosaved", "yes");
    return mapping.findForward("editcontact");
  }
  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside update method");
    UserContactForm eform = (UserContactForm)form;
    String userContactId = request.getParameter("userContactId");
    logger.info("userContactId >>> " + userContactId);
    UserContactInfo usercontact = BOFactory.getUserBO().getUserContactInfobyUsrContactId(new Long(userContactId).longValue());
    logger.info("other email id >> " + usercontact.getOtherEmailId());
    
    eform.toValue(usercontact, request);
    usercontact = BOFactory.getUserBO().updateUserContactInfo(usercontact);
    
    eform.fromValue(usercontact, request);
    

    List countryList = BOFactory.getLovBO().getAllCountries();
    eform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = null;
    if (eform.getCountryId() != 0L) {
      stateList = BOFactory.getLovBO().getStateListByCountry(eform.getCountryId());
    } else {
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    eform.setStateList(stateList);
    request.setAttribute("contactinfoupdated", "yes");
    return mapping.findForward("editcontact");
  }
}
