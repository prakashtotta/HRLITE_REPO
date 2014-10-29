package com.bo;

import com.bean.User;
import com.bean.lov.TechnicalSkills;
import com.bean.pool.TalentPool;
import com.bean.pool.TalentPoolElements;
import com.dao.TalentPoolDAO;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.List;
import org.apache.log4j.Logger;

public class TalentPoolBO
{
  protected static final Logger logger = Logger.getLogger(TalentPoolBO.class);
  
  public static String getTalentPoolListWithTreeFormat(long userId)
  {
    String data = "";
    List tpool = TalentPoolDAO.getAllTalentPoolsAssignedToUser(userId);
    if ((tpool != null) && (tpool.size() > 0)) {
      for (int i = 0; i < tpool.size(); i++)
      {
        TalentPool tp = (TalentPool)tpool.get(i);
        

        String tmp = "<li class='text' id=id='" + tp.getTalentPoolId() + "'>" + "<span>" + tp.getTalentPoolName() + "</span>" + "<ul class='ajax'>" + "<li id='" + tp.getTalentPoolId() + "'>{url:manageTreeData.jsp?action=getElementList&ownerEl=" + tp.getTalentPoolId() + "}</li>" + "</ul>" + "</li>";
        





        data = data + tmp;
      }
    }
    return data;
  }
  
  public static boolean isApplicantFromAssignedTalentPool(long userId, long applicantId)
  {
    return TalentPoolDAO.isApplicantFromAssignedTalentPool(userId, applicantId);
  }
  
  public static String getTalentPoolElementsByOwnerId(long ownerId)
  {
    logger.info("getTalentPoolElementsByOwnerId");
    
    String data = "";
    List elements = TalentPoolDAO.getTalentPoolElementsByOwnerId(ownerId);
    if ((elements != null) && (elements.size() > 0)) {
      for (int i = 0; i < elements.size(); i++)
      {
        TalentPoolElements ele = (TalentPoolElements)elements.get(i);
        String tmptxt = "";
        String tmp = "";
        if (ele.getSlave() == 0) {
          tmptxt = "<ul class='ajax'><li id='" + ele.getId() + "'>{url:manageTreeData.jsp?action=getElementList&ownerEl=" + ele.getId() + "}</li>" + "</ul>";
        }
        tmp = "<li class='text' id='" + ele.getId() + "'>" + "<span>" + ele.getName() + "</span>" + tmptxt + "</li>";
        



        data = data + tmp;
      }
    } else {
      data = "<li><font color='grey'>No data.</font></li>";
    }
    logger.info(data);
    return data;
  }
  
  public static String insertTalentPoolElement(String ownerId, String name, String slave)
  {
    String data = "";
    TalentPoolElements ele = TalentPoolDAO.insertTalentPoolElement(ownerId, name, slave);
    data = "({ elementId:'" + ele.getId() + "', elementName:'" + ele.getName() + "', slave:'" + ele.getSlave() + "'})";
    return data;
  }
  
  public static String deleteTalentPoolElements(String elementId, String ownerEl)
  {
    String data = "true";
    TalentPoolDAO.deleteTalentPoolElements(elementId, 0, ownerEl);
    
    return data;
  }
  
  public static String changeOrderTalentPoolElement(String elementId, String oldOwnerEl, String destOwnerEl, String position)
  {
    String data = "";
    TalentPoolDAO.changeOrderTalentPoolElement(elementId, oldOwnerEl, destOwnerEl, position);
    data = "({oldElementId:'" + elementId + "', elementId:'" + elementId + "'})";
    return data;
  }
  
  public static String updateTalentPoolElement(String name, String elementId, String ownerId)
  {
    String data = "";
    TalentPoolDAO.updateTalentPoolElement(name, elementId, ownerId);
    data = "({elementName:'" + name + "', elementId:'" + elementId + "'})";
    return data;
  }
  
  public static List getAllTalentpoolForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TalentPoolDAO.getAllTalentpoolForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public static List getAllTalentpool()
  {
    return TalentPoolDAO.getAllTalentpool();
  }
  
  public static List getAllTalentpoolForPaginationBySearchCriteria(User user, String name, String emailid, String smtpserver, String smtpuser, String isgrouo, String description, String orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TalentPoolDAO.getAllTalentpoolForPaginationBySearchCriteria(user, name, emailid, smtpserver, smtpuser, isgrouo, description, orgId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfTalentpoolBySearchCriteria(User user, String name, String emailid, String description, String smtpserver, String smtpuser, String isgrouo, String orgId)
  {
    return TalentPoolDAO.getCountOfTalentpoolBySearchCriteria(user, name, emailid, smtpserver, smtpuser, isgrouo, description, orgId);
  }
  
  public static void addSkillsToTalentPool(TalentPool talent, User user)
  {
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("talentpool.default.skills.folders"))) && (Constant.getValue("talentpool.default.skills.folders").equalsIgnoreCase("yes")))
    {
      List skills = BOFactory.getLovTXBO().getTechnicalSkillList(user.getSuper_user_key());
      if (skills != null) {
        for (int i = 0; i < skills.size(); i++)
        {
          TechnicalSkills skill = (TechnicalSkills)skills.get(i);
          
          TalentPoolDAO.insertTalentPoolElement(talent.getTalentPoolId(), skill.getTechnialSkillName(), 0, 0L, talent.getTalentPoolId());
        }
      }
    }
  }
}
