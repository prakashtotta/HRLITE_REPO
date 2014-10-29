package com.action;

import com.bean.User;
import com.bean.lov.EthnicRace;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.EthnicRaceForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EthnicRaceAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(EthnicRaceAction.class);
  
  public ActionForward ethnicRaceList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside ethnicRaceList method");
    EthnicRaceForm ethnicRaceForm = (EthnicRaceForm)form;
    return mapping.findForward("ethnicracelist");
  }
  
  public ActionForward createEthnicRace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createEthnicRace method");
    EthnicRaceForm ethnicRaceForm = (EthnicRaceForm)form;
    return mapping.findForward("createEthnicRace");
  }
  
  public ActionForward editEthnicRace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createEthnicRace method");
    EthnicRaceForm ethnicRaceForm = (EthnicRaceForm)form;
    
    String ethnicRaceId = request.getParameter("ethnicRaceId");
    EthnicRace ethnicRace = BOFactory.getLovBO().getEthnicRaceDetails(new Long(ethnicRaceId).longValue());
    ethnicRaceForm.fromValue(ethnicRace, request);
    return mapping.findForward("createEthnicRace");
  }
  
  public ActionForward saveEthnicRace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveEthnicRace method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EthnicRaceForm ethnicRaceForm = (EthnicRaceForm)form;
    
    EthnicRace ethnicRace = new EthnicRace();
    ethnicRaceForm.toValue(ethnicRace, request);
    ethnicRace.setStatus("A");
    ethnicRace.setSuper_user_key(user1.getSuper_user_key());
    ethnicRace = BOFactory.getLovBO().saveEthnicRaceDetails(ethnicRace);
    
    ethnicRaceForm.fromValue(ethnicRace, request);
    ethnicRaceForm.setEthnicRaceId(ethnicRace.getEthnicRaceId());
    request.setAttribute("ethnicracesaved", "yes");
    return mapping.findForward("createEthnicRace");
  }
  
  public ActionForward updateEthnicRace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateEthnicRace method");
    EthnicRaceForm ethnicRaceForm = (EthnicRaceForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String ethnicRaceId = request.getParameter("ethnicRaceId");
    EthnicRace ethnicRace = BOFactory.getLovBO().getEthnicRaceDetails(new Long(ethnicRaceId).longValue());
    
    ethnicRaceForm.toValue(ethnicRace, request);
    
    ethnicRace = BOFactory.getLovBO().updateEthnicRaceDetails(ethnicRace);
    
    ethnicRaceForm.fromValue(ethnicRace, request);
    ethnicRaceForm.setEthnicRaceId(ethnicRace.getEthnicRaceId());
    request.setAttribute("ethnicraceupdated", "yes");
    return mapping.findForward("createEthnicRace");
  }
  
  public ActionForward deleteEthnicRace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteEthnicRace method");
    EthnicRaceForm ethnicRaceForm = (EthnicRaceForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String ethnicRaceId = request.getParameter("ethnicRaceId");
    try
    {
      BOFactory.getLovBO().deleteEthnicRace(new Long(ethnicRaceId).longValue());
      
      request.setAttribute("ethnicracedeleted", "yes");
      return mapping.findForward("createEthnicRace");
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
