package com.action;

import com.bean.Characteristic;
import com.bean.User;
import com.dao.LovOpsDAO;
import com.form.CharForm;
import com.util.StringUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CharAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(CharAction.class);
  
  public ActionForward charlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside charlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CharForm charForm = (CharForm)form;
    request.setAttribute("competenciesHome", "competencies");
    return mapping.findForward("charlist");
  }
  
  public ActionForward saveChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createChar method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    CharForm charForm = (CharForm)form;
    Characteristic ch = new Characteristic();
    if (StringUtils.isNullOrEmpty(charForm.getCharName())) {
      return null;
    }
    ch.setCharDesc(charForm.getCharDesc());
    ch.setCharName(charForm.getCharName());
    ch.setStatus("A");
    ch.setWeight(charForm.getWeight());
    ch.setCreatedBy(user.getUserName());
    ch.setCreatedDate(new Date());
    ch.setSuper_user_key(user.getSuper_user_key());
    LovOpsDAO.saveChar(ch);
    charForm.setCharId(ch.getCharId());
    request.setAttribute("charsaved", "yes");
    
    return mapping.findForward("createChar");
  }
  
  public ActionForward deleteChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteChar method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String charId = request.getParameter("charId");
    Characteristic chardata = LovOpsDAO.getChar(charId);
    chardata.setStatus("D");
    LovOpsDAO.updateChar(chardata);
    
    request.setAttribute("chardeleted", "yes");
    
    return mapping.findForward("createChar");
  }
  
  public ActionForward updateChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateChar method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CharForm charForm = (CharForm)form;
    logger.info("Inside updateChar method" + charForm.getCharName());
    String charId = request.getParameter("charId");
    Characteristic chardata = LovOpsDAO.getChar(charId);
    if (StringUtils.isNullOrEmpty(charForm.getCharName())) {
      return null;
    }
    chardata.setCharDesc(charForm.getCharDesc());
    chardata.setCharName(charForm.getCharName());
    chardata.setWeight(charForm.getWeight());
    LovOpsDAO.updateChar(chardata);
    
    request.setAttribute("updatechar", "yes");
    
    return mapping.findForward("createChar");
  }
  
  public ActionForward createChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createChar method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    






    return mapping.findForward("createChar");
  }
  
  public ActionForward editChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editChar method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String charId = request.getParameter("charId");
    CharForm charForm = (CharForm)form;
    Characteristic chardata = LovOpsDAO.getChar(charId);
    
    charForm.setCharDesc(chardata.getCharDesc());
    charForm.setCharName(chardata.getCharName());
    charForm.setWeight(chardata.getWeight());
    charForm.setCharId(chardata.getCharId());
    
    return mapping.findForward("createChar");
  }
  
  public ActionForward charlistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside charlistsearch method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CharForm charForm = (CharForm)form;
    String charName = request.getParameter("charName");
    logger.info("charName : " + charName);
    charForm.setCharName(charName);
    request.setAttribute("competenciesHome", "competencies");
    return mapping.findForward("charlist");
  }
}
