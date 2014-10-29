package com.action;

import com.bean.Round;
import com.bean.User;
import com.dao.LovOpsDAO;
import com.form.RoundForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RoundAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(CharAction.class);
  
  public ActionForward roundlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside roundlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("roundlist");
  }
  
  public ActionForward createRound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createRound method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    






    return mapping.findForward("createRound");
  }
  
  public ActionForward saveRound(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveRound method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RoundForm roundForm = (RoundForm)form;
    Round rn = new Round();
    
    rn.setRoundDesc(roundForm.getRoundDesc());
    rn.setRoundName(roundForm.getRoundName());
    rn.setStatus("A");
    

    LovOpsDAO.saveRound(rn);
    
    return mapping.findForward("");
  }
}
