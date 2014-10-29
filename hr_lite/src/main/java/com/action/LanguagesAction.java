package com.action;

import com.bean.User;
import com.bean.lov.Languages;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.LanguagesForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LanguagesAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(LanguagesAction.class);
  
  public ActionForward languageslist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside languageslist method");
    LanguagesForm languagesForm = (LanguagesForm)form;
    
    return mapping.findForward("languageslist");
  }
  
  public ActionForward languagelistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside languagelistsearch method");
    LanguagesForm languagesForm = (LanguagesForm)form;
    String languageName = request.getParameter("languageName");
    languagesForm.setLanguageName(languageName);
    

    return mapping.findForward("languageslist");
  }
  
  public ActionForward addLanguage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside languageslist method");
    LanguagesForm languagesForm = (LanguagesForm)form;
    
    return mapping.findForward("addLanguage");
  }
  
  public ActionForward editLanguage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editLanguage method");
    LanguagesForm languagesForm = (LanguagesForm)form;
    String languageId = request.getParameter("languageId");
    Languages languages = BOFactory.getLovBO().getLanguagesDetails(new Long(languageId).longValue());
    
    languagesForm.fromValue(languages, request);
    
    return mapping.findForward("addLanguage");
  }
  
  public ActionForward saveLanguage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveLanguage method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    LanguagesForm languagesForm = (LanguagesForm)form;
    Languages languages = new Languages();
    languagesForm.toValue(languages, request);
    languages.setStatus("A");
    languages.setSuper_user_key(user1.getSuper_user_key());
    languages = BOFactory.getLovBO().saveLanguage(languages);
    
    languagesForm.fromValue(languages, request);
    languagesForm.setLanguageId(languages.getLanguageId());
    request.setAttribute("languagesave", "yes");
    return mapping.findForward("addLanguage");
  }
  
  public ActionForward updateLanguage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateLanguage method");
    LanguagesForm languagesForm = (LanguagesForm)form;
    String languageId = request.getParameter("languageId");
    Languages languages = BOFactory.getLovBO().getLanguagesDetails(new Long(languageId).longValue());
    languagesForm.toValue(languages, request);
    languages = BOFactory.getLovBO().updateLanguage(languages);
    
    languagesForm.fromValue(languages, request);
    languagesForm.setLanguageId(languages.getLanguageId());
    request.setAttribute("languageupdated", "yes");
    return mapping.findForward("addLanguage");
  }
  
  public ActionForward deleteLanguage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteLanguage method");
    LanguagesForm languagesForm = (LanguagesForm)form;
    String languageId = request.getParameter("languageId");
    String fromwhere = request.getParameter("fromwhere");
    User user = (User)request.getSession().getAttribute("user_data");
    try
    {
      BOFactory.getLovBO().deleteLanguage(new Long(languageId).longValue());
      
      request.setAttribute("languagedeleted", "yes");
      return mapping.findForward("addLanguage");
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
