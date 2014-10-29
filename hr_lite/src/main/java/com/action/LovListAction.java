package com.action;

import com.bean.User;
import com.bean.lov.LovList;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.dao.LovOpsDAO;
import com.form.LovListForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LovListAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(LovListAction.class);
  
  public ActionForward lovList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside lovList method");
    LovListForm lovListForm = (LovListForm)form;
    
    return mapping.findForward("lovList");
  }
  
  public ActionForward createLov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createLov method");
    LovListForm lovListForm = (LovListForm)form;
    
    BOFactory.getLovBO().createLovs(lovListForm);
    
    return mapping.findForward("createLov");
  }
  
  public ActionForward saveLov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createLov method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    LovListForm lovListForm = (LovListForm)form;
    LovList lovList = new LovList();
    lovListForm.toValue(lovList, request);
    lovList.setLocaleId(0L);
    lovList.setSuper_user_key(user1.getSuper_user_key());
    lovList = LovOpsDAO.saveLovs(lovList);
    
    BOFactory.getLovBO().editLovs(lovListForm);
    lovListForm.fromValue(lovList, request);
    List lovlistcodeList = BOFactory.getLovBO().getLovlistvaluecodeList(lovList.getLovListCode());
    lovListForm.setLovlistvaluecodeList(lovlistcodeList);
    
    request.setAttribute("lovsaved", "yes");
    return mapping.findForward("createLov");
  }
  
  public ActionForward editLov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editLov method");
    LovListForm lovListForm = (LovListForm)form;
    LovList lovList = new LovList();
    String readPreview = request.getParameter("readPreview");
    String lovid = request.getParameter("lovid");
    lovList = BOFactory.getLovBO().getLovDetails(lovListForm, new Long(lovid).longValue());
    BOFactory.getLovBO().editLovs(lovListForm);
    lovListForm.fromValue(lovList, request);
    List lovlistcodeList = BOFactory.getLovBO().getLovlistvaluecodeList(lovList.getLovListCode());
    lovListForm.setLovlistvaluecodeList(lovlistcodeList);
    
    request.setAttribute("readPreview", readPreview);
    request.setAttribute("editmode", "yes");
    return mapping.findForward("createLov");
  }
  
  public ActionForward updateLov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateLov method");
    LovListForm lovListForm = (LovListForm)form;
    LovList lovList = new LovList();
    
    String lovid = request.getParameter("lovid");
    lovList = BOFactory.getLovBO().getLovDetails(lovListForm, new Long(lovid).longValue());
    lovListForm.toValue(lovList, request);
    lovList.setLovListValueCode(null);
    lovList.setLovListValueName(null);
    lovList = LovOpsDAO.updateLovs(lovList);
    BOFactory.getLovBO().editLovs(lovListForm);
    lovListForm.fromValue(lovList, request);
    List lovlistcodeList = BOFactory.getLovBO().getLovlistvaluecodeList(lovList.getLovListCode());
    lovListForm.setLovlistvaluecodeList(lovlistcodeList);
    request.setAttribute("lovupdated", "yes");
    request.setAttribute("editmode", "yes");
    return mapping.findForward("createLov");
  }
  
  public ActionForward addLoveListValue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addLoveListValue method");
    LovListForm lovListForm = (LovListForm)form;
    LovList lovList = new LovList();
    List lovlistcodeList = null;
    
    String lovid = request.getParameter("lovid");
    String lovListCode = request.getParameter("lovListCode");
    lovList = BOFactory.getLovBO().getLovDetails(lovListForm, new Long(lovid).longValue());
    








    lovListForm.toValue(lovList, request);
    lovList = LovOpsDAO.saveLovs(lovList);
    

    BOFactory.getLovBO().editLovs(lovListForm);
    lovListForm.fromValue(lovList, request);
    lovListForm.setLocaleId(0L);
    lovlistcodeList = BOFactory.getLovBO().getLovlistvaluecodeList(lovListCode);
    lovListForm.setLovlistvaluecodeList(lovlistcodeList);
    logger.info("lovlistcodeList : " + lovlistcodeList.size());
    
    request.setAttribute("editmode", "yes");
    return mapping.findForward("createLov");
  }
  
  public ActionForward deleteLovlistcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteLovlistcode method");
    LovListForm lovListForm = (LovListForm)form;
    LovList lovList = new LovList();
    
    String lovid = request.getParameter("lovid");
    lovList = BOFactory.getLovBO().getLovDetails(lovListForm, new Long(lovid).longValue());
    lovListForm.toValue(lovList, request);
    lovList = LovOpsDAO.deleteLovlistcode(lovList);
    BOFactory.getLovBO().editLovs(lovListForm);
    lovListForm.fromValue(lovList, request);
    List lovlistcodeList = BOFactory.getLovBO().getLovlistvaluecodeList(lovList.getLovListCode());
    lovListForm.setLovlistvaluecodeList(lovlistcodeList);
    
    request.setAttribute("editmode", "yes");
    return mapping.findForward("createLov");
  }
  
  public ActionForward inActiveLovlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside inActiveLovlist method");
    LovListForm lovListForm = (LovListForm)form;
    LovList lovList = new LovList();
    
    String lovid = request.getParameter("lovid");
    lovList = BOFactory.getLovBO().getLovDetails(lovListForm, new Long(lovid).longValue());
    
    lovList.setStatus("I");
    lovList = LovOpsDAO.updateLovs(lovList);
    BOFactory.getLovBO().editLovs(lovListForm);
    lovListForm.fromValue(lovList, request);
    List lovlistcodeList = BOFactory.getLovBO().getLovlistvaluecodeList(lovList.getLovListCode());
    lovListForm.setLovlistvaluecodeList(lovlistcodeList);
    request.setAttribute("lovlistdeactivated", "yes");
    request.setAttribute("editmode", "yes");
    return mapping.findForward("createLov");
  }
  
  public ActionForward activeLovlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside activeLovlist method");
    LovListForm lovListForm = (LovListForm)form;
    LovList lovList = new LovList();
    
    String lovid = request.getParameter("lovid");
    lovList = BOFactory.getLovBO().getLovDetails(lovListForm, new Long(lovid).longValue());
    
    lovList.setStatus("A");
    lovList = LovOpsDAO.updateLovs(lovList);
    BOFactory.getLovBO().editLovs(lovListForm);
    lovListForm.fromValue(lovList, request);
    List lovlistcodeList = BOFactory.getLovBO().getLovlistvaluecodeList(lovList.getLovListCode());
    lovListForm.setLovlistvaluecodeList(lovlistcodeList);
    request.setAttribute("lovlistactivated", "yes");
    request.setAttribute("editmode", "yes");
    return mapping.findForward("createLov");
  }
}
