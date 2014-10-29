package com.action;

import com.bean.Characteristic;
import com.bean.GroupCharacteristic;
import com.bean.User;
import com.bo.LovTXBO;
import com.common.AppContextUtil;
import com.dao.LovOpsDAO;
import com.form.GroupCharForm;
import com.util.StringUtils;
import java.util.ArrayList;
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
import org.springframework.context.ApplicationContext;

public class GroupCharAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(GroupCharAction.class);
  
  public ActionForward groupCharlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside groupCharlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("groupCharlist");
  }
  
  public ActionForward createGroupChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createGroupChar method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("createGroupChar");
  }
  
  public ActionForward saveGroupChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveGroupChar method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String charids = request.getParameter("charids");
    GroupCharForm grform = (GroupCharForm)form;
    GroupCharacteristic grch = new GroupCharacteristic();
    logger.info("Characterictics ids" + charids);
    if (!StringUtils.isNullOrEmpty(charids))
    {
      charids = charids.substring(0, charids.length() - 1);
      logger.info("Characterictics ids next" + charids);
      String[] ids = StringUtils.tokenize(charids, ",");
      List charList = new ArrayList();
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          Characteristic ch = new Characteristic();
          ch.setCharId(new Long(ids[i]).longValue());
          charList.add(ch);
        }
      }
      grch.setCharList(charList);
    }
    grch.setGroupCharName(grform.getGroupCharName());
    grch.setGroupCharDesc(grform.getGroupCharDesc());
    grch.setCreatedBy(user1.getUserName());
    grch.setCreatedDate(new Date());
    grch.setStatus("A");
    LovOpsDAO.saveGroupChar(grch);
    grch = LovOpsDAO.getGroupChar(String.valueOf(grch.getGroupCharId()));
    grform.setCharList(grch.getCharList());
    request.setAttribute("savedgroupchars", "yes");
    
    return mapping.findForward("createGroupChar");
  }
  
  public ActionForward updateGroupChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateGroupChar method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String charids = request.getParameter("charids");
    String groupCharId = request.getParameter("groupCharId");
    
    GroupCharacteristic grch = LovOpsDAO.getGroupChar(groupCharId);
    
    List charoldList = grch.getCharList();
    
    GroupCharForm grform = (GroupCharForm)form;
    
    logger.info("Characterictics ids" + charids);
    if (!StringUtils.isNullOrEmpty(charids))
    {
      charids = charids.substring(0, charids.length() - 1);
      logger.info("Characterictics ids next" + charids);
      String[] ids = StringUtils.tokenize(charids, ",");
      List charList = new ArrayList();
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          Characteristic ch = new Characteristic();
          ch.setCharId(new Long(ids[i]).longValue());
          charList.add(ch);
        }
      }
      grch.setCharList(charList);
    }
    grch.setGroupCharName(grform.getGroupCharName());
    grch.setGroupCharDesc(grform.getGroupCharDesc());
    grch.setUpdatedBy(user1.getUserName());
    grch.setUpdatedDate(new Date());
    

    ApplicationContext appContext = AppContextUtil.getAppcontext();
    LovTXBO lovtxbo = (LovTXBO)appContext.getBean("lovtxBoProxy");
    lovtxbo.updateGroupChar(grch, charoldList);
    
    grch = LovOpsDAO.getGroupChar(groupCharId);
    grform.setCharList(grch.getCharList());
    
    request.setAttribute("updatedgroupchars", "yes");
    
    return mapping.findForward("createGroupChar");
  }
  
  public ActionForward editGroupChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editGroupChar method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String groupCharId = request.getParameter("groupCharId");
    GroupCharForm grform = (GroupCharForm)form;
    GroupCharacteristic grch = LovOpsDAO.getGroupChar(groupCharId);
    grform.setGroupCharId(grch.getGroupCharId());
    
    grform.setGroupCharName(grch.getGroupCharName());
    grform.setGroupCharDesc(grch.getGroupCharDesc());
    grform.setCharList(grch.getCharList());
    
    request.setAttribute("savedgroupchars", "no");
    
    return mapping.findForward("createGroupChar");
  }
  
  public ActionForward deleteGroupChar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteGroupChar method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String groupCharId = request.getParameter("groupCharId");
    GroupCharForm grform = (GroupCharForm)form;
    GroupCharacteristic grch = LovOpsDAO.getGroupChar(groupCharId);
    grch.setStatus("D");
    LovOpsDAO.updateGroupChar(grch);
    request.setAttribute("deletegroupchars", "yes");
    
    return mapping.findForward("createGroupChar");
  }
}
