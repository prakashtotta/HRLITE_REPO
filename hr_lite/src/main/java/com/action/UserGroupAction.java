package com.action;

import com.bean.User;
import com.bean.UserGroup;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.dao.UserDAO;
import com.form.UserGroupForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserGroupAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserGroupAction.class);
  
  public ActionForward usergrouplist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside usergrouplist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    request.setAttribute("userHome", "userGroup");
    return mapping.findForward("usergrouplist");
  }
  
  public ActionForward searchusergrouplistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside usergrouplist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    UserGroupForm usergroupform = (UserGroupForm)form;
    usergroupform.setUsergrpName(usergroupform.getUsergrpName());
    if ((userId == null) || (userId == "0")) {
      usergroupform.setUserId(new Long(0L).longValue());
    } else {
      usergroupform.setUserId(new Long(userId).longValue());
    }
    request.setAttribute("userHome", "userGroup");
    return mapping.findForward("usergrouplist");
  }
  
  public ActionForward createusergroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createusergroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    UserGroupForm usergroupform = (UserGroupForm)form;
    
    return mapping.findForward("createusergroup");
  }
  
  public ActionForward editusergroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editusergroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    UserGroupForm usergroupform = (UserGroupForm)form;
    String usergroupid = request.getParameter("usergroupid");
    UserGroup usergroup = BOFactory.getLovBO().getUserGroup(new Long(usergroupid).longValue());
    usergroupform.setUsergrpId(usergroup.getUsergrpId());
    usergroupform.setUsersset(usergroup.getUsers());
    usergroupform.setStatus(usergroup.getStatus());
    usergroupform.setUsergrpName(usergroup.getUsergrpName());
    usergroupform.setUsergrpDesc(usergroup.getUsergrpDesc());
    usergroupform.setCreatedBy(usergroup.getCreatedBy());
    usergroupform.setCreatedDate(usergroup.getCreatedDate());
    
    List useridList = new ArrayList();
    Set set1 = usergroup.getUsers();
    Iterator itr = set1.iterator();
    while (itr.hasNext())
    {
      User user1 = (User)itr.next();
      useridList.add(Long.valueOf(user1.getUserId()));
    }
    usergroupform.setUseridListVal(useridList);
    String readpreview = request.getParameter("readPreview");
    usergroupform.setReadPreview(readpreview);
    return mapping.findForward("createusergroup");
  }
  
  public ActionForward saveusergroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveusergroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    UserGroupForm usergroupform = (UserGroupForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userids = request.getParameter("userids");
    List userList = new ArrayList();
    if (!StringUtils.isNullOrEmpty(userids)) {
      userList = StringUtils.tokenizeString(userids, ",");
    }
    UserGroup usergroup = new UserGroup();
    usergroup.setUsergrpName(usergroupform.getUsergrpName());
    usergroup.setUsergrpDesc(usergroupform.getUsergrpDesc());
    usergroup.setCreatedBy(user1.getUserName());
    usergroup.setCreatedDate(new Date());
    usergroup.setStatus("A");
    usergroup.setSuper_user_key(user1.getSuper_user_key());
    Set users = new HashSet();
    for (int i = 0; i < userList.size(); i++)
    {
      String userid = (String)userList.get(i);
      if (userid != null)
      {
        User usrobj = new User();
        usrobj.setUserId(new Long(userid).longValue());
        users.add(usrobj);
      }
    }
    usergroup.setUsers(users);
    UserDAO.saveUserGroup(usergroup);
    
    usergroup = BOFactory.getLovBO().getUserGroup(usergroup.getUsergrpId());
    usergroupform.setUsergrpId(usergroup.getUsergrpId());
    usergroupform.setUsersset(usergroup.getUsers());
    usergroupform.setStatus(usergroup.getStatus());
    
    request.setAttribute("isusergrpadded", "yes");
    return mapping.findForward("createusergroup");
  }
  
  public ActionForward suspendUserGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("Inside suspendUserGroup  method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String usergroupId = request.getParameter("usergroupId");
    UserGroupForm usergroupform = (UserGroupForm)form;
    logger.info("Inside suspendUserGroup user method1234" + usergroupId);
    UserGroup usergr = UserDAO.getUserGroup(new Long(usergroupId).longValue());
    logger.info("Inside suspendUserGroup user method5555" + usergr.getStatus());
    usergr.setStatus("I");
    usergr.setUpdatedBy(user1.getUserName());
    usergr.setUpdatedDate(new Date());
    UserDAO.updateUserGroup(usergr);
    usergroupform.setStatus(usergr.getStatus());
    request.setAttribute("isusergrsuspended", "yes");
    return mapping.findForward("createusergroup");
  }
  
  public ActionForward deleteUserGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteUserGroup user method");
    UserGroupForm usergroupform = (UserGroupForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String usergroupId = request.getParameter("usergroupId");
    UserGroup usergr = UserDAO.getUserGroup(new Long(usergroupId).longValue());
    usergr.setStatus("D");
    usergr.setUpdatedBy(user1.getUserName());
    usergr.setUpdatedDate(new Date());
    UserDAO.updateUserGroup(usergr);
    usergroupform.setStatus(usergr.getStatus());
    return mapping.findForward("createusergroup");
  }
  
  public ActionForward updateusergroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateusergroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    UserGroupForm usergroupform = (UserGroupForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userids = request.getParameter("userids");
    String usergroupid = request.getParameter("usergroupid");
    UserGroup usergroup = BOFactory.getLovBO().getUserGroup(new Long(usergroupid).longValue());
    List userList = new ArrayList();
    if (!StringUtils.isNullOrEmpty(userids)) {
      userList = StringUtils.tokenizeString(userids, ",");
    }
    usergroup.setUsergrpName(usergroupform.getUsergrpName());
    usergroup.setUsergrpDesc(usergroupform.getUsergrpDesc());
    usergroup.setUpdatedBy(user1.getUserName());
    usergroup.setUpdatedDate(new Date());
    
    Set users = new HashSet();
    for (int i = 0; i < userList.size(); i++)
    {
      String userid = (String)userList.get(i);
      if (userid != null)
      {
        User usrobj = new User();
        usrobj.setUserId(new Long(userid).longValue());
        users.add(usrobj);
      }
    }
    usergroup.setUsers(users);
    UserDAO.updateUserGroup(usergroup);
    
    usergroup = BOFactory.getLovBO().getUserGroup(usergroup.getUsergrpId());
    usergroupform.setUsergrpId(usergroup.getUsergrpId());
    usergroupform.setUsersset(usergroup.getUsers());
    
    usergroupform.setStatus(usergroup.getStatus());
    usergroupform.setUsergrpName(usergroup.getUsergrpName());
    usergroupform.setUsergrpDesc(usergroup.getUsergrpDesc());
    usergroupform.setUpdatedBy(usergroup.getUpdatedBy());
    usergroupform.setUpdatedDate(usergroup.getUpdatedDate());
    usergroupform.setCreatedBy(usergroup.getCreatedBy());
    usergroupform.setCreatedDate(usergroup.getCreatedDate());
    request.setAttribute("isusergrpupdated", "yes");
    return mapping.findForward("createusergroup");
  }
  
  public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside activate usergroup method");
    UserGroupForm usergroupform = (UserGroupForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String usergroupid = request.getParameter("usergroupid");
    String userids = request.getParameter("userids");
    
    UserGroup usergroup = BOFactory.getLovBO().getUserGroup(new Long(usergroupid).longValue());
    List userList = new ArrayList();
    if (!StringUtils.isNullOrEmpty(userids))
    {
      userids = userids.substring(0, userids.length() - 1);
      
      userList = StringUtils.tokenizeString(userids, ",");
    }
    Set users = new HashSet();
    for (int i = 0; i < userList.size(); i++)
    {
      String userid = (String)userList.get(i);
      if (userid != null)
      {
        User usrobj = new User();
        usrobj.setUserId(new Long(userid).longValue());
        users.add(usrobj);
      }
    }
    usergroup.setStatus("A");
    usergroup.setUpdatedBy(user1.getUserName());
    usergroup.setUpdatedDate(new Date());
    UserDAO.updateUserGroup(usergroup);
    UserGroup usergroup1 = BOFactory.getLovBO().getUserGroup(new Long(usergroupid).longValue());
    usergroupform.setUsergrpId(usergroup1.getUsergrpId());
    usergroupform.setStatus(usergroup1.getStatus());
    usergroupform.setUsersset(usergroup1.getUsers());
    request.setAttribute("isusergrpactivated", "yes");
    return mapping.findForward("createusergroup");
  }
}
