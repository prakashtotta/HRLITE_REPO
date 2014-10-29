package network.action;

import com.bo.BOFactory;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import network.bean.FaceBookUser;
import network.bean.FbUserAchivements;
import network.bean.FbUserSkills;
import network.bean.FbUserSpecialities;
import network.bo.FbBulkUserUploadManager;
import network.bo.FbUserBO;
import network.bo.FbUserTask;
import network.form.FaceBookUserForm;
import network.util.FaceBookReader;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NetworkHomeAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(NetworkHomeAction.class);
  
  public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String fid = request.getParameter("fid");
    
    String key = request.getParameter("key");
    

    FaceBookUser user = BOFactory.getFbUserBO().isFaceBookUserExist(fid);
    if (user != null)
    {
      user.setSessionKey(key);
      request.getSession().setAttribute("fb_user_data", user);
      FbUserTask fbtask = new FbUserTask();
      fbtask.setFacebookid(fid);
      fbtask.setSessionkey(key);
      fbtask.setType("OWN");
      FbBulkUserUploadManager.uploadUser(fbtask);
    }
    else
    {
      FaceBookReader reader = new FaceBookReader();
      reader.getOwnUserData(fid, key);
      user = BOFactory.getFbUserBO().isFaceBookUserExist(fid);
      if (user != null)
      {
        user.setSessionKey(key);
        request.getSession().setAttribute("fb_user_data", user);
      }
      else
      {
        return mapping.findForward("error");
      }
    }
    Date dt = new Date();
    dt.setDate(dt.getDate() - 3);
    if ((user.getLastFriendupdatedDate() == null) || (user.getLastFriendupdatedDate().compareTo(dt) < 0))
    {
      FbUserTask fbtask = new FbUserTask();
      fbtask.setFacebookid(fid);
      fbtask.setSessionkey(key);
      fbtask.setType("FRIENDS");
      FbBulkUserUploadManager.uploadUser(fbtask);
    }
    return mapping.findForward("home");
  }
  
  public ActionForward updateuserinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return null;
  }
  
  public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String searchquery = request.getParameter("search");
    List ulist = BOFactory.getFbUserBO().searchFbUsers(searchquery, 25, 0);
    FaceBookUserForm uform = (FaceBookUserForm)form;
    uform.setUsersList(ulist);
    uform.setSearchQuery(searchquery);
    return mapping.findForward("search");
  }
  
  public ActionForward endorseReceived(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return mapping.findForward("endorseReceived");
  }
  
  public ActionForward endorseGiven(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return mapping.findForward("endorseGiven");
  }
  
  public ActionForward askForEndorsement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return mapping.findForward("askForEndorsement");
  }
  
  public ActionForward endorsementScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String uids = request.getParameter("uids");
    request.setAttribute("uids", uids);
    return mapping.findForward("endorsementScreen");
  }
  
  public ActionForward saveendorsement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    String uids = request.getParameter("uids");
    String comment = request.getParameter("comment");
    List ulist = StringUtils.tokenizeString(uids, ",");
    if ((!StringUtils.isNullOrEmpty(comment)) && (ulist.size() > 0)) {
      BOFactory.getFbUserBO().saveEndorsements(ulist, user.getFacebookid(), comment);
    }
    request.setAttribute("saveendorsement", "yes");
    return mapping.findForward("endorsementScreen");
  }
  
  public ActionForward askForEndorsementsave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String uids = request.getParameter("uids");
    List ulist = StringUtils.tokenizeString(uids, ",");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    
    BOFactory.getFbUserBO().saveAskForEndorsement(ulist, user.getFacebookid());
    

    return null;
  }
  
  public ActionForward editFBUserProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editFBUserProfile method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FaceBookUserForm uform = (FaceBookUserForm)form;
    
    return mapping.findForward("editFBUserProfile");
  }
  
  public ActionForward saveProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveProfile method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FaceBookUserForm uform = (FaceBookUserForm)form;
    
    String skill1 = request.getParameter("skill1");
    String skill2 = request.getParameter("skill2");
    String skill3 = request.getParameter("skill3");
    String skill4 = request.getParameter("skill4");
    String skill5 = request.getParameter("skill5");
    
    String skill1Exp = request.getParameter("skill1Exp");
    String skill2Exp = request.getParameter("skill2Exp");
    String skill3Exp = request.getParameter("skill3Exp");
    String skill4Exp = request.getParameter("skill4Exp");
    String skill5Exp = request.getParameter("skill5Exp");
    

    String specility1 = request.getParameter("specility1");
    String specility2 = request.getParameter("specility2");
    
    String achivement1 = request.getParameter("achivement1");
    String achivement2 = request.getParameter("achivement2");
    String achivement3 = request.getParameter("achivement3");
    String summary = request.getParameter("summary");
    logger.info("summary >> " + summary);
    
    List skillList = new ArrayList();
    skillList.add(skill1);
    skillList.add(skill2);
    skillList.add(skill3);
    skillList.add(skill4);
    skillList.add(skill5);
    
    List skillexpList = new ArrayList();
    skillexpList.add(skill1Exp);
    skillexpList.add(skill2Exp);
    skillexpList.add(skill3Exp);
    skillexpList.add(skill4Exp);
    skillexpList.add(skill5Exp);
    
    List spcialitiesList = new ArrayList();
    spcialitiesList.add(specility1);
    spcialitiesList.add(specility2);
    
    List achievementsList = new ArrayList();
    achievementsList.add(achivement1);
    achievementsList.add(achivement2);
    achievementsList.add(achivement3);
    
    logger.info("skill1 >> " + skill1 + " skill1Exp >> " + skill1Exp);
    logger.info("skill2 >> " + skill2 + " skill2Exp >> " + skill2Exp);
    logger.info("skill3 >> " + skill3 + " skill3Exp >> " + skill3Exp);
    logger.info("skill4 >> " + skill4 + " skill4Exp >> " + skill4Exp);
    logger.info("skill5 >> " + skill5 + " skill5Exp >> " + skill5Exp);
    
    logger.info("achivement1 >> " + achivement1);
    logger.info("achivement2 >> " + achivement2);
    logger.info("achivement3 >> " + achivement3);
    
    logger.info("city >> " + uform.getCity());
    logger.info("summary >> " + uform.getBio());
    



    user.setCity(uform.getCity());
    user.setBio(summary);
    user.setTopLine(uform.getTopLine());
    
    BOFactory.getFbUserBO().updateFbUser(user);
    

    List fbuserSkillList = BOFactory.getFbUserBO().getFbUsersskillListByUserId(user.getUserId());
    logger.info("fbuserSkillList.size() >> " + fbuserSkillList.size());
    if (fbuserSkillList.size() > 0)
    {
      logger.info(" in if ...");
      for (int i = 0; i < skillList.size(); i++)
      {
        FbUserSkills fbUserSkills = (FbUserSkills)fbuserSkillList.get(i);
        fbUserSkills.setUserId(user.getUserId());
        fbUserSkills.setSkill((String)skillList.get(i));
        fbUserSkills.setYearsExp((String)skillexpList.get(i));
        BOFactory.getFbUserBO().updateFbUserSkills(fbUserSkills);
      }
    }
    else
    {
      logger.info(" in else ...");
      for (int i = 0; i < skillList.size(); i++)
      {
        FbUserSkills fbUserSkills = new FbUserSkills();
        fbUserSkills.setUserId(user.getUserId());
        fbUserSkills.setSkill((String)skillList.get(i));
        fbUserSkills.setYearsExp((String)skillexpList.get(i));
        BOFactory.getFbUserBO().saveFbUserSkills(fbUserSkills);
      }
    }
    List fbuserSpecialitiesList = BOFactory.getFbUserBO().getFbUsersSpecialitiesListByUserId(user.getUserId());
    logger.info("fbuserSpecialitiesList.size() >> " + fbuserSpecialitiesList.size());
    if (fbuserSpecialitiesList.size() > 0)
    {
      logger.info(" in if ...");
      for (int i = 0; i < spcialitiesList.size(); i++)
      {
        FbUserSpecialities fbUserSpecialities = (FbUserSpecialities)fbuserSpecialitiesList.get(i);
        fbUserSpecialities.setUserId(user.getUserId());
        fbUserSpecialities.setSpecialities((String)spcialitiesList.get(i));
        BOFactory.getFbUserBO().updateFbUserSpecialiteis(fbUserSpecialities);
      }
    }
    else
    {
      logger.info(" in else ...");
      for (int i = 0; i < spcialitiesList.size(); i++)
      {
        FbUserSpecialities fbUserSpecialities = new FbUserSpecialities();
        fbUserSpecialities.setUserId(user.getUserId());
        fbUserSpecialities.setSpecialities((String)spcialitiesList.get(i));
        BOFactory.getFbUserBO().saveFbUserSpecialiteis(fbUserSpecialities);
      }
    }
    List fbuserAchievementList = BOFactory.getFbUserBO().getFbUsersAchievementListByUserId(user.getUserId());
    logger.info("fbuserAchievementList.size() >> " + fbuserSpecialitiesList.size());
    if (fbuserAchievementList.size() > 0)
    {
      logger.info(" in if ...");
      for (int i = 0; i < achievementsList.size(); i++)
      {
        FbUserAchivements fbUserAchivements = (FbUserAchivements)fbuserAchievementList.get(i);
        fbUserAchivements.setUserId(user.getUserId());
        fbUserAchivements.setAchivement((String)achievementsList.get(i));
        BOFactory.getFbUserBO().updateFbUserAchievement(fbUserAchivements);
      }
    }
    else
    {
      logger.info(" in else ...");
      for (int i = 0; i < achievementsList.size(); i++)
      {
        FbUserAchivements fbUserAchivements = new FbUserAchivements();
        fbUserAchivements.setUserId(user.getUserId());
        fbUserAchivements.setAchivement((String)achievementsList.get(i));
        BOFactory.getFbUserBO().saveFbUserAchievement(fbUserAchivements);
      }
    }
    request.setAttribute("saveprofile", "yes");
    
    return mapping.findForward("editFBUserProfile");
  }
}
