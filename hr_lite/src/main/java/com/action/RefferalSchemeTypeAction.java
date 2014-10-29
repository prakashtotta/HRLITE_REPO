package com.action;

import com.bean.RefferalSchemeType;
import com.bean.User;
import com.dao.LovOpsDAO;
import com.form.RefferalSchemeTypeForm;
import com.resources.Constant;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RefferalSchemeTypeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(RefferalSchemeTypeAction.class);
  
  public ActionForward createRefferalSchemeType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    RefferalSchemeTypeForm refferalScheme = (RefferalSchemeTypeForm)form;
    refferalScheme.setUomList(Constant.uomList);
    
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward refferalSchemelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    RefferalSchemeTypeForm refferalSchemeform = (RefferalSchemeTypeForm)form;
    request.setAttribute("refferalHome", "refferalschemetype");
    return mapping.findForward("refferalSchemelist");
  }
  
  public ActionForward editRefferalSchemeType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferalSchemeType method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String Id = request.getParameter("id");
    RefferalSchemeTypeForm refferalSchemeform = (RefferalSchemeTypeForm)form;
    
    RefferalSchemeType refferalscheme = LovOpsDAO.getRefferalSchemeTypeDetails(Id);
    toForm(refferalSchemeform, refferalscheme);
    
    refferalSchemeform.setUomList(Constant.uomList);
    
    request.setAttribute("saveRefferalScheme", "edit");
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward updateRefferalSchemeType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateRefferalSchemeType method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RefferalSchemeTypeForm refferalSchemeform = (RefferalSchemeTypeForm)form;
    
    String Id = request.getParameter("id");
    RefferalSchemeType refferalscheme = LovOpsDAO.getRefferalSchemeTypeDetails(Id);
    
    toValue(refferalscheme, refferalSchemeform);
    

    refferalscheme = LovOpsDAO.updateRefferalSchemeType(refferalscheme);
    
    refferalSchemeform.setUomList(Constant.uomList);
    toForm(refferalSchemeform, refferalscheme);
    request.setAttribute("saveRefferalScheme", "update");
    
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward saveRefferalSchemeType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveRefferalSchemeType method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalSchemeTypeForm refferalSchemeform = (RefferalSchemeTypeForm)form;
    RefferalSchemeType refferalscheme = new RefferalSchemeType();
    toValue(refferalscheme, refferalSchemeform);
    refferalscheme.setCreatedBy(user.getUserName());
    refferalscheme.setSuper_user_key(user.getSuper_user_key());
    LovOpsDAO.saveReffaralSchemeType(refferalscheme);
    request.setAttribute("saveRefferalScheme", "save");
    refferalSchemeform.setUomList(Constant.uomList);
    toForm(refferalSchemeform, refferalscheme);
    
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward deleteRefferalSchemeType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteRefferalSchemeType method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RefferalSchemeTypeForm refferalSchemeform = (RefferalSchemeTypeForm)form;
    
    String id = request.getParameter("id");
    RefferalSchemeType refferalscheme = LovOpsDAO.getRefferalSchemeTypeDetails(id);
    toDelete(refferalscheme, refferalSchemeform);
    
    refferalscheme = LovOpsDAO.updateRefferalSchemeType(refferalscheme);
    request.setAttribute("saveRefferalScheme", "delete");
    return mapping.findForward("createrefferalscheme");
  }
  
  public void toDelete(RefferalSchemeType refferalscheme, RefferalSchemeTypeForm refferalSchemeform)
  {
    refferalscheme.setStatus("D");
  }
  
  public void toValue(RefferalSchemeType refferalscheme, RefferalSchemeTypeForm refferalSchemeform)
  {
    refferalscheme.setRefferalSchemeName(refferalSchemeform.getRefferalSchemeName());
    refferalscheme.setRefferalSchemeDesc(refferalSchemeform.getRefferalSchemeDesc());
    refferalscheme.setCreatedDate(new Date());
    refferalscheme.setStatus("A");
    refferalscheme.setUom(refferalSchemeform.getUom());
  }
  
  public void toForm(RefferalSchemeTypeForm refferalSchemeform, RefferalSchemeType refferalscheme)
  {
    refferalSchemeform.setRefferalSchemeTypeId(refferalscheme.getRefferalSchemeTypeId());
    refferalSchemeform.setRefferalSchemeName(refferalscheme.getRefferalSchemeName());
    refferalSchemeform.setRefferalSchemeDesc(refferalscheme.getRefferalSchemeDesc());
    refferalSchemeform.setCreatedBy(refferalscheme.getCreatedBy());
    refferalSchemeform.setCreatedDate(refferalscheme.getCreatedDate());
    refferalSchemeform.setStatus(refferalscheme.getStatus());
    refferalSchemeform.setUom(refferalscheme.getUom());
  }
}
