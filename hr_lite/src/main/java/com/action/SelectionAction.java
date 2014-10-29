package com.action;

import com.bean.Round;
import com.bean.SelectionCycle;
import com.bean.User;
import com.dao.LovOpsDAO;
import com.dao.SelectionDAO;
import com.form.SelectionForm;
import java.io.PrintStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SelectionAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(SelectionAction.class);
  
  public ActionForward selectionslist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selectionslist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("selectionslist");
  }
  
  public ActionForward createSelPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createSelPage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    






    return mapping.findForward("createSelPage");
  }
  
  public ActionForward saveSelection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveSelection method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    SelectionForm selForm = (SelectionForm)form;
    SelectionCycle sc = new SelectionCycle();
    
    sc.setCreatedBy(user.userName);
    sc.setCreatedDate(new Date());
    sc.setSelName(selForm.getSelName());
    sc.setSelDesc(selForm.getSelDesc());
    sc.setStatus("A");
    sc.setUpdatedBy(user.userName);
    sc.setUpdatedDate(new Date());
    
    SelectionDAO.saveSelection(sc);
    
    return mapping.findForward("");
  }
  
  public ActionForward selcylesdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selcylesdetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String selId = request.getParameter("selId");
    
    SelectionForm selectionForm = (SelectionForm)form;
    
    SelectionCycle selection = SelectionDAO.getSeletion(selId);
    
    toForm(selectionForm, selection);
    
    return mapping.findForward("selcylesdetails");
  }
  
  public void toForm(SelectionForm selectionForm, SelectionCycle selection)
  {
    selectionForm.setSelDesc(selection.getSelDesc());
    selectionForm.setSelId(selection.getSelId());
    selectionForm.setSelName(selection.getSelName());
  }
  
  public ActionForward addselround(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addselround method");
    String selId = request.getParameter("selId");
    String roundName = request.getParameter("roundname");
    System.out.println("selId" + selId);
    
    Round rn = LovOpsDAO.getRoundByRoundName(roundName);
    System.out.println("rn" + rn);
    if (rn == null)
    {
      rn = new Round();
      rn.setRoundDesc(roundName);
      rn.setRoundName(roundName);
      rn.setStatus("A");
      rn = LovOpsDAO.saveRound(rn);
    }
    SelectionCycle selection = SelectionDAO.getSeletion(selId);
    
    SelectionDAO.saveRoundToSelection(selection.getSelId(), rn.getRoundId());
    SelectionForm selectionForm = (SelectionForm)form;
    toForm(selectionForm, selection);
    
    return mapping.findForward("selcylesdetails");
  }
}
