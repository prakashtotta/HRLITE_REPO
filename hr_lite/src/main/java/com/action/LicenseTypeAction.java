package com.action;

import com.bean.User;
import com.bean.lov.LicenseType;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.LicenseTypeForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LicenseTypeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(LicenseTypeAction.class);
  
  public ActionForward licensetypelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside licensetypelist method");
    LicenseTypeForm licenseTypeForm = (LicenseTypeForm)form;
    return mapping.findForward("licensetypelist");
  }
  
  public ActionForward addLicenseType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside licensetypelist method");
    LicenseTypeForm licenseTypeForm = (LicenseTypeForm)form;
    return mapping.findForward("addLicenseType");
  }
  
  public ActionForward editLicenseType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editLicenseType method");
    LicenseTypeForm licenseTypeForm = (LicenseTypeForm)form;
    
    String licenseTypeId = request.getParameter("licenseTypeId");
    LicenseType licenseType = BOFactory.getLovBO().getLicenseTypeDetails(new Long(licenseTypeId).longValue());
    licenseTypeForm.fromValue(licenseType, request);
    return mapping.findForward("addLicenseType");
  }
  
  public ActionForward saveLicenseType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveLicenseType method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    LicenseTypeForm licenseTypeForm = (LicenseTypeForm)form;
    LicenseType licenseType = new LicenseType();
    licenseTypeForm.toValue(licenseType, request);
    licenseType.setStatus("A");
    licenseType.setSuper_user_key(user1.getSuper_user_key());
    licenseType = BOFactory.getLovBO().saveLicenseType(licenseType);
    licenseTypeForm.setLicenseTypeId(licenseType.getLicenseTypeId());
    request.setAttribute("licensetypesaved", "yes");
    return mapping.findForward("addLicenseType");
  }
  
  public ActionForward updateLicenseType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateLicenseType method");
    LicenseTypeForm licenseTypeForm = (LicenseTypeForm)form;
    
    String licenseTypeId = request.getParameter("licenseTypeId");
    LicenseType licenseType = BOFactory.getLovBO().getLicenseTypeDetails(new Long(licenseTypeId).longValue());
    licenseTypeForm.toValue(licenseType, request);
    

    licenseType = BOFactory.getLovBO().updateLicenseType(licenseType);
    
    licenseTypeForm.fromValue(licenseType, request);
    licenseTypeForm.setLicenseTypeId(licenseType.getLicenseTypeId());
    
    request.setAttribute("licensetypeupdated", "yes");
    return mapping.findForward("addLicenseType");
  }
  
  public ActionForward deleteLicenseType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteLicenseType method");
    LicenseTypeForm licenseTypeForm = (LicenseTypeForm)form;
    
    String licenseTypeId = request.getParameter("licenseTypeId");
    String fromwhere = request.getParameter("fromwhere");
    User user = (User)request.getSession().getAttribute("user_data");
    try
    {
      BOFactory.getLovBO().deleteLicenseType(new Long(licenseTypeId).longValue());
      
      request.setAttribute("licensetypedeleted", "yes");
      return mapping.findForward("addLicenseType");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
}
