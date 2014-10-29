package com.action;

import com.bean.User;
import com.bean.filter.ScreenFields;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.form.ScreenFieldForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ScreenFieldAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ScreenFieldAction.class);
  static List appScreenList = new ArrayList();
  static List appScreenWorkExpList = new ArrayList();
  static List appScreeneducationList = new ArrayList();
  
  public ScreenFieldAction()
  {
    appScreenList.add("APPLICANT_SCREEN");
    appScreenList.add("APPLICANT_SCREEN_AGENCY");
    appScreenList.add("APPLICANT_SCREEN_REFERRAL");
    appScreenList.add("APPLICANT_SCREEN_TALENTPOOL");
    appScreenList.add("APPLICANT_SCREEN_EXTERNAL");
    appScreenList.add("APPLICANT_SCREEN_TALENTPOOL_EXTERNAL");
    appScreenList.add("APPLICANT_SCREEN_PROFILE");
    

    appScreenWorkExpList.add("APPLICANT_SCREEN_WORK_EXP");
    appScreenWorkExpList.add("APPLICANT_SCREEN_WORK_EXP_AGENCY");
    appScreenWorkExpList.add("APPLICANT_SCREEN_WORK_EXP_REFERRAL");
    appScreenWorkExpList.add("APPLICANT_SCREEN_WORK_EXP_TALENTPOOL");
    appScreenWorkExpList.add("APPLICANT_SCREEN_WORK_EXP_EXTERNAL");
    appScreenWorkExpList.add("APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL");
    appScreenWorkExpList.add("APPLICANT_SCREEN_WORK_EXP_PROFILE");
    
    appScreeneducationList.add("APPLICANT_SCREEN_EDUCATION");
    appScreeneducationList.add("APPLICANT_SCREEN_EDUCATION_REFERRAL");
    appScreeneducationList.add("APPLICANT_SCREEN_EDUCATION_AGENCY");
    appScreeneducationList.add("APPLICANT_SCREEN_EDUCATION_TALENTPOOL");
    appScreeneducationList.add("APPLICANT_SCREEN_EDUCATION_EXTERNAL");
    appScreeneducationList.add("APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL");
    appScreeneducationList.add("APPLICANT_SCREEN_EDUCATION_PROFILE");
  }
  
  public ActionForward screenFieldScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ScreenFieldForm screenFieldForm = (ScreenFieldForm)form;
    
    return mapping.findForward("screenFieldScreen");
  }
  
  public ActionForward editscreenfield(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ScreenFieldForm screenFieldForm = (ScreenFieldForm)form;
    String screencode = request.getParameter("screencode");
    List screenfieldList = new ArrayList();
    logger.info("screenName : " + screencode);
    User user1 = (User)request.getSession().getAttribute("user_data");
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails(screencode, user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    screenFieldForm.setScreenfieldsList(screenfieldList);
    screenFieldForm.setScreenCode(screencode);
    if ((screencode != null) && (appScreenWorkExpList.contains(screencode))) {
      return mapping.findForward("editscreenfieldworkexptab");
    }
    if ((screencode != null) && (appScreeneducationList.contains(screencode))) {
      return mapping.findForward("editscreenfieldeducationtab");
    }
    return mapping.findForward("editscreenfield");
  }
  
  public ActionForward editscreenfieldReq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editscreenfieldReq");
    ScreenFieldForm screenFieldForm = (ScreenFieldForm)form;
    String screencode = request.getParameter("screencode");
    List screenfieldList = new ArrayList();
    logger.info("screenName : " + screencode);
    User user1 = (User)request.getSession().getAttribute("user_data");
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails(screencode, user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    screenFieldForm.setScreenfieldsList(screenfieldList);
    screenFieldForm.setScreenCode(screencode);
    

    return mapping.findForward("editscreenfieldReq");
  }
  
  public ActionForward loadFields(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside loadFields method ");
    ScreenFieldForm screenFieldForm = (ScreenFieldForm)form;
    ScreenFields screenFields = null;
    List screenfieldList = new ArrayList();
    String screencode = request.getParameter("screencode");
    logger.info("screenName : " + screencode);
    User user1 = (User)request.getSession().getAttribute("user_data");
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails(screencode, user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    screenFieldForm.setScreenfieldsList(screenfieldList);
    return mapping.findForward("loadFields");
  }
  
  public ActionForward savescreenfields(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String screencode = request.getParameter("screencode");
    try
    {
      logger.info("inside savescreenfields method ");
      ScreenFieldForm screenFieldForm = (ScreenFieldForm)form;
      User user1 = (User)request.getSession().getAttribute("user_data");
      

      List screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails(screencode, user1.getSuper_user_key());
      



      List fieldList = screenfieldList;
      if (screenfieldList.size() != 0)
      {
        Iterator itr = fieldList.iterator();
        for (int i = 0; i < screenfieldList.size(); i++)
        {
          ScreenFields screenFields = (ScreenFields)fieldList.get(i);
          if ((screenFields.getIssystem() != null) && (!screenFields.getIssystem().equals("Y")))
          {
            String isvisible = request.getParameter("isvisible_" + screenFields.getScreenfieldId());
            String ismandatory = request.getParameter("isMandatory_" + screenFields.getScreenfieldId());
            if ((!StringUtils.isNullOrEmpty(isvisible)) && (isvisible.equals("on"))) {
              screenFields.setIsvisible("Y");
            } else {
              screenFields.setIsvisible("N");
            }
            if ((!StringUtils.isNullOrEmpty(ismandatory)) && (isvisible.equals("on"))) {
              screenFields.setIsMandatory("Y");
            } else {
              screenFields.setIsMandatory("N");
            }
            BOFactory.getApplicantBO().updateScreenFields(screenFields);
          }
        }
      }
      screenFieldForm.setScreenCode(screencode);
      screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails(screencode, user1.getSuper_user_key());
      
      screenFieldForm.setScreenfieldsList(screenfieldList);
      
      request.setAttribute("screenfieldsupdated", "yes");
    }
    catch (Exception e)
    {
      logger.info("exception on savescreenfileds", e);
    }
    if ((screencode != null) && (appScreenWorkExpList.contains(screencode))) {
      return mapping.findForward("editscreenfieldworkexptab");
    }
    if ((screencode != null) && (appScreeneducationList.contains(screencode))) {
      return mapping.findForward("editscreenfieldeducationtab");
    }
    return mapping.findForward("editscreenfield");
  }
  
  public ActionForward savescreenfieldsReq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside savescreenfieldsReq method ");
    ScreenFieldForm screenFieldForm = (ScreenFieldForm)form;
    ScreenFields screenFields = new ScreenFields();
    String screencode = request.getParameter("screencode");
    User user1 = (User)request.getSession().getAttribute("user_data");
    List screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails(screencode, user1.getSuper_user_key());
    



    List fieldList = screenfieldList;
    if (screenfieldList.size() != 0)
    {
      Iterator itr = fieldList.iterator();
      for (int i = 0; i < screenfieldList.size(); i++)
      {
        screenFields = (ScreenFields)fieldList.get(i);
        if ((screenFields.getIssystem() != null) && (!screenFields.getIssystem().equals("Y")))
        {
          String isvisible = request.getParameter("isvisible_" + screenFields.getScreenfieldId());
          String ismandatory = request.getParameter("isMandatory_" + screenFields.getScreenfieldId());
          


          screenFields = (ScreenFields)fieldList.get(i);
          
          screenFieldForm.setIsvisible(isvisible);
          screenFieldForm.setIsMandatory(ismandatory);
          if ((!StringUtils.isNullOrEmpty(isvisible)) && (isvisible.equals("on"))) {
            screenFields.setIsvisible("Y");
          } else {
            screenFields.setIsvisible("N");
          }
          if ((!StringUtils.isNullOrEmpty(ismandatory)) && (isvisible.equals("on"))) {
            screenFields.setIsMandatory("Y");
          } else {
            screenFields.setIsMandatory("N");
          }
          BOFactory.getApplicantBO().updateScreenFields(screenFields);
        }
      }
    }
    screenFieldForm.setScreenCode(screencode);
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails(screencode, user1.getSuper_user_key());
    
    screenFieldForm.setScreenfieldsList(screenfieldList);
    
    request.setAttribute("screenfieldsupdated", "yes");
    
    return mapping.findForward("editscreenfieldReq");
  }
}
