package com.action.employee;

import com.action.CommonAction;
import com.bean.employee.EmergencyContact;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.form.employee.UserEmergencyContactForm;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserEmergencyContactAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserEmergencyContactAction.class);
  
  public ActionForward emergencyContactdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside emergencyContactdetails method");
    UserEmergencyContactForm eform = (UserEmergencyContactForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    List emergencyCotactlist = new ArrayList();
    emergencyCotactlist = BOFactory.getUserBO().getUserEmergencyContactlist(new Long(userId).longValue());
    logger.info("emergencyCotactlist.size() >> " + emergencyCotactlist.size());
    eform.setUserEmergencyContactList(emergencyCotactlist);
    return mapping.findForward("emergencyContactdetails");
  }
  
  public ActionForward addEmergencyContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addEmergencyContact method");
    UserEmergencyContactForm eform = (UserEmergencyContactForm)form;
    


    return mapping.findForward("addEmergencyContact");
  }
  
  public ActionForward saveEmergencyContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveEmergencyContact method >>>>> ");
    UserEmergencyContactForm eform = (UserEmergencyContactForm)form;
    
    EmergencyContact emergencyContact = new EmergencyContact();
    
    eform.toValue(emergencyContact, request);
    emergencyContact = BOFactory.getUserBO().saveUserEmergencyContactInfo(emergencyContact);
    
    eform.fromValue(emergencyContact, request);
    

    request.setAttribute("emergencycontactsaved", "yes");
    return mapping.findForward("addEmergencyContact");
  }
  
  public ActionForward updateEmergencyContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateEmergencyContact method >>>>> ");
    UserEmergencyContactForm eform = (UserEmergencyContactForm)form;
    
    String emergencyContactId = request.getParameter("emergencyContactId");
    logger.info("emergencyContactId >>> " + emergencyContactId);
    EmergencyContact useremergencycontact = BOFactory.getUserBO().getUserEmergencyContactInfo(new Long(emergencyContactId).longValue());
    

    eform.toValue(useremergencycontact, request);
    useremergencycontact = BOFactory.getUserBO().updateUserEmergencyContactInfo(useremergencycontact);
    
    eform.fromValue(useremergencycontact, request);
    

    request.setAttribute("emergencycontactupdated", "yes");
    return mapping.findForward("addEmergencyContact");
  }
  
  public ActionForward editEmergencyContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editEmergencyContact method >>>>> ");
    UserEmergencyContactForm eform = (UserEmergencyContactForm)form;
    
    String emergencyContactId = request.getParameter("emergencyContactId");
    logger.info("emergencyContactId >>> " + emergencyContactId);
    EmergencyContact useremergencycontact = BOFactory.getUserBO().getUserEmergencyContactInfo(new Long(emergencyContactId).longValue());
    

    eform.fromValue(useremergencycontact, request);
    
    return mapping.findForward("addEmergencyContact");
  }
  
  public ActionForward deleteEmergencyContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteEmergencyContact method >>>>> ");
    UserEmergencyContactForm eform = (UserEmergencyContactForm)form;
    
    String emergencyContactId = request.getParameter("emergencyContactId");
    logger.info("emergencyContactId >>> " + emergencyContactId);
    EmergencyContact useremergencycontact = BOFactory.getUserBO().getUserEmergencyContactInfo(new Long(emergencyContactId).longValue());
    

    useremergencycontact = BOFactory.getUserBO().deleteEmergencyContact(useremergencycontact);
    


    request.setAttribute("emergencycontactdeleted", "yes");
    return mapping.findForward("addEmergencyContact");
  }
  
  public ActionForward deleteEmergencyContactMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteEmergencyContactMultiple method");
    UserEmergencyContactForm eform = (UserEmergencyContactForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    String checkedItems = request.getParameter("checkedItems");
    

    BOFactory.getUserBO().deleteEmergencyContactMultiple(checkedItems);
    
    List emergencyCotactlist = new ArrayList();
    emergencyCotactlist = BOFactory.getUserBO().getUserEmergencyContactlist(new Long(userId).longValue());
    logger.info("emergencyCotactlist.size() >> " + emergencyCotactlist.size());
    eform.setUserEmergencyContactList(emergencyCotactlist);
    request.setAttribute("emergencyContactsdeleted", "yes");
    return mapping.findForward("emergencyContactdetails");
  }
}
