package com.action;

import com.bean.Organization;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.pool.TalentPool;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.TalentPoolBO;
import com.dao.TalentPoolDAO;
import com.dao.UserDAO;
import com.form.TalentPoolForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TalentPoolAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(TalentPoolAction.class);
  
  public ActionForward talentpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside talentpool method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("talentpool");
  }
  
  public ActionForward talentpoolListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside talentpoolListpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    TalentPoolForm talentForm = (TalentPoolForm)form;
    talentForm.setIsgroupList(Constant.getIsgroupTalentpoolList(user1));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    talentForm.setOrganizationList(orgList);
    request.setAttribute("searchtalentpool", "no");
    return mapping.findForward("talentpoolListpage");
  }
  
  public ActionForward searchTalentpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchTalentpool method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    TalentPoolForm talentForm = (TalentPoolForm)form;
    talentForm.setTalentPoolName(talentForm.getTalentPoolName());
    if ((talentForm.getIsGroup() != null) && (talentForm.getIsGroup().equals("Yes"))) {
      talentForm.setIsGroup("Y");
    } else if ((talentForm.getIsGroup() != null) && (talentForm.getIsGroup().equals("No"))) {
      talentForm.setIsGroup("N");
    }
    talentForm.setIsGroup(talentForm.getIsGroup());
    talentForm.setIsgroupList(Constant.getIsgroupTalentpoolList(user1));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    talentForm.setOrganizationList(orgList);
    request.setAttribute("searchtalentpool", "no");
    request.setAttribute("searchtalentpool", "yes");
    return mapping.findForward("talentpoolListpage");
  }
  
  public ActionForward createTalentpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    TalentPoolForm talentForm = (TalentPoolForm)form;
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    talentForm.setOrganizationList(orgList);
    
    return mapping.findForward("createtalentpool");
  }
  
  public ActionForward saveTalentpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveTalentpool method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String isGroup = request.getParameter("isGroup");
    String assignedtoid = request.getParameter("assignedtoid");
    String reviewername = request.getParameter("reviewername");
    User user1 = (User)request.getSession().getAttribute("user_data");
    TalentPoolForm talentForm = (TalentPoolForm)form;
    TalentPool talent = new TalentPool();
    talent.setCreatedBy(user1.getUserName());
    talent.setCreatedDate(new Date());
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    talentForm.setOrganizationList(orgList);
    toValue(talent, talentForm);
    if (!StringUtils.isNullOrEmpty(assignedtoid)) {
      talent.setAssignedtoid(new Long(assignedtoid).longValue());
    }
    if ((!StringUtils.isNullOrEmpty(isGroup)) && (isGroup.equals("Y"))) {
      talent.setIsGroup("Y");
    } else {
      talent.setIsGroup("N");
    }
    talent.setAssigntouserName(reviewername);
    talent.setSuper_user_key(user1.getSuper_user_key());
    TalentPoolDAO.saveTalentpool(talent);
    toForm(talentForm, talent);
    
    TalentPoolBO.addSkillsToTalentPool(talent, user1);
    
    request.setAttribute("saveTalentpool", "yes");
    return mapping.findForward("createtalentpool");
  }
  
  public ActionForward talentpoolDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside talentpoolDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String talentpoolid = request.getParameter("talentpoolid");
    TalentPoolForm talentForm = (TalentPoolForm)form;
    TalentPool talent = TalentPoolDAO.getTalentpoolDetails(talentpoolid);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    talentForm.setOrganizationList(orgList);
    toForm(talentForm, talent);
    return mapping.findForward("createtalentpool");
  }
  
  public ActionForward updateTalentpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateTalentpool method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    TalentPoolForm talentForm = (TalentPoolForm)form;
    String talentpoolid = request.getParameter("talentpoolid");
    String reviewername = request.getParameter("reviewername");
    String isGroup = request.getParameter("isGroup");
    String assignedtoid = request.getParameter("assignedtoidnew");
    logger.info("Inside updateTalentpool method assignedtoidnew" + assignedtoid);
    TalentPool talent = TalentPoolDAO.getTalentpoolDetails(talentpoolid);
    Organization oldOrg = talent.getOrganization();
    talent.setUpdatedBy(user1.getUserName());
    talent.setUpdatedDate(new Date());
    toValue(talent, talentForm);
    if (!StringUtils.isNullOrEmpty(assignedtoid)) {
      talent.setAssignedtoid(new Long(assignedtoid).longValue());
    }
    if ((!StringUtils.isNullOrEmpty(isGroup)) && (isGroup.equals("Y"))) {
      talent.setIsGroup("Y");
    } else {
      talent.setIsGroup("N");
    }
    talent.setAssigntouserName(reviewername);
    if (talent.getTalentPoolId() == 9999999L) {
      talent.setOrganization(oldOrg);
    }
    talent = TalentPoolDAO.updateTalentpool(talent);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    talentForm.setOrganizationList(orgList);
    toForm(talentForm, talent);
    









    request.setAttribute("updateTalentpool", "yes");
    return mapping.findForward("createtalentpool");
  }
  
  public ActionForward deleteTalentpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteTalentpool234 method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String talentpoolid = request.getParameter("talentpoolid");
    TalentPoolForm talentForm = (TalentPoolForm)form;
    TalentPool talent = TalentPoolDAO.getTalentpoolDetails(talentpoolid);
    toDelete(talent, talentForm);
    talent = TalentPoolDAO.updateTalentpool(talent);
    logger.info("Inside deleteTalentpoo8765 method");
    request.setAttribute("deleteTalentpool", "yes");
    return mapping.findForward("createtalentpool");
  }
  
  public void toValue(TalentPool talent, TalentPoolForm talentForm)
  {
    talent.setTalentPoolName(talentForm.getTalentPoolName());
    talent.setTalentPoolDesc(talentForm.getTalentPoolDesc());
    talent.setTalentPoolemail(talentForm.getTalentPoolemail());
    
    talent.setIsGroup(talentForm.getIsGroup());
    talent.setSmtpserver(talentForm.getSmtpserver());
    talent.setSmptoport(talentForm.getSmptoport());
    talent.setSmtpuser(talentForm.getSmtpuser());
    talent.setSmtppassword(talentForm.getSmtppassword());
    talent.setStatus("A");
    Organization org = new Organization();
    org.setOrgId(talentForm.getOrgId());
    talent.setOrganization(org);
  }
  
  public void toForm(TalentPoolForm talentForm, TalentPool talent)
  {
    talentForm.setTalentPoolId(talent.getTalentPoolId());
    talentForm.setTalentPoolName(talent.getTalentPoolName());
    talentForm.setTalentPoolDesc(talent.getTalentPoolDesc());
    talentForm.setTalentPoolemail(talent.getTalentPoolemail());
    talentForm.setAssignedtoid(talent.getAssignedtoid());
    if ((talent.getIsGroup() != null) && (talent.getIsGroup().equals("N")))
    {
      talentForm.setAssigntoName(UserDAO.getUserFullName(talent.getAssignedtoid()));
      logger.info("setAssigntoName" + UserDAO.getUserFullName(talent.getAssignedtoid()));
    }
    else if ((talent.getIsGroup() != null) && (talent.getIsGroup().equals("Y")))
    {
      UserGroup usergroup = UserDAO.getUserGroupNameandId(talent.getAssignedtoid());
      talentForm.setAssigntoName(usergroup.getUsergrpName());
      logger.info("usergrpName" + usergroup.getUsergrpName());
    }
    talentForm.setIsGroup(talent.getIsGroup());
    talentForm.setSmtpserver(talent.getSmtpserver());
    talentForm.setSmptoport(talent.getSmptoport());
    talentForm.setSmtpuser(talent.getSmtpuser());
    talentForm.setSmtppassword(talent.getSmtppassword());
    talentForm.setOrgId(talent.getOrganization().getOrgId());
    talentForm.setOrgName(talent.getOrganization().getOrgName());
    talentForm.setAssigntouserName(talent.getAssigntouserName());
  }
  
  public void toDelete(TalentPool talent, TalentPoolForm talentForm)
  {
    talent.setStatus("D");
  }
}
