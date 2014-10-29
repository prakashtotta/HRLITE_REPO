package network.bo;

import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import network.bean.FaceBookUser;
import network.bean.FbConcentration;
import network.bean.FbDTO;
import network.bean.FbEmployer;
import network.bean.FbEndorsementAsk;
import network.bean.FbJobApplicants;
import network.bean.FbJobs;
import network.bean.FbLocation;
import network.bean.FbPositions;
import network.bean.FbSavedCompanies;
import network.bean.FbSavedJobs;
import network.bean.FbSchool;
import network.bean.FbUserAchivements;
import network.bean.FbUserCertifications;
import network.bean.FbUserEmployer;
import network.bean.FbUserSchools;
import network.bean.FbUserSkills;
import network.bean.FbUserSpecialities;
import network.dao.FbUserDAO;

public class FbUserBO
{
  FbUserDAO fbuserdao;
  
  public FbEmployer isEmployeerExist(String facebookid)
  {
    return this.fbuserdao.isEmployeerExist(facebookid);
  }
  
  public FbLocation isLocationExist(String facebookid)
  {
    return this.fbuserdao.isLocationExist(facebookid);
  }
  
  public FbPositions isPositionsExist(String facebookid)
  {
    return this.fbuserdao.isPositionsExist(facebookid);
  }
  
  public FbSchool isSchoolExist(String facebookid)
  {
    return this.fbuserdao.isSchoolExist(facebookid);
  }
  
  public FbConcentration isConcentrationExist(String facebookid)
  {
    return this.fbuserdao.isConcentrationExist(facebookid);
  }
  
  public FaceBookUser isFaceBookUserExist(String facebookid)
  {
    return this.fbuserdao.isFaceBookUserExist(facebookid);
  }
  
  public void updateLastUpdatedDate(String facebookid)
  {
    FaceBookUser user = this.fbuserdao.isFaceBookUserExist(facebookid);
    user.setLastFriendupdatedDate(new Date());
    this.fbuserdao.updateFbUser(user);
  }
  
  public FaceBookUser updateFbUser(FaceBookUser user)
  {
    return this.fbuserdao.updateFbUser(user);
  }
  
  public FbJobs updateFbJob(FbJobs job)
  {
    return this.fbuserdao.updateFbJob(job);
  }
  
  public FbDTO getSavedJobInfo(long jobId, long userId)
  {
    return this.fbuserdao.getSavedJobInfo(jobId, userId);
  }
  
  public List searchFbUsers(String queryString, int pageSize, int startIndex)
  {
    return this.fbuserdao.searchFbUsers(queryString, pageSize, startIndex);
  }
  
  public List searchCompanies(String queryString, int pageSize, int startIndex)
  {
    return this.fbuserdao.searchCompanies(queryString, pageSize, startIndex);
  }
  
  public List searchFbJobs(String queryString, int pageSize, int startIndex)
  {
    return this.fbuserdao.searchFbJobs(queryString, pageSize, startIndex);
  }
  
  public FbDTO getEmployeeCountAndSavedInfo(String facebookid, long userId)
  {
    return this.fbuserdao.getEmployeeCountAndSavedInfo(facebookid, userId);
  }
  
  public FbSavedCompanies saveFbSavedCompanies(FbSavedCompanies fbc)
  {
    return this.fbuserdao.saveFbSavedCompanies(fbc);
  }
  
  public List getSavedJobs(long userId, int startIndex, int pagesize)
  {
    return this.fbuserdao.getSavedJobs(userId, startIndex, pagesize);
  }
  
  public FbSavedJobs saveFbSavedJob(FbSavedJobs fsj)
  {
    return this.fbuserdao.saveFbSavedJob(fsj);
  }
  
  public void deleteSavedCompany(long userId, String comFacebookid)
  {
    this.fbuserdao.deleteSavedCompany(userId, comFacebookid);
  }
  
  public List getSavedCompanies(long userId, int startIndex, int pagesize)
  {
    return this.fbuserdao.getSavedCompanies(userId, startIndex, pagesize);
  }
  
  public FaceBookUser saveFaceBookUser(FaceBookUser user, List<FbUserEmployer> ulist, List<FbUserSchools> slist)
  {
    FaceBookUser userold = this.fbuserdao.isFaceBookUserExist(user.getFacebookid());
    if (userold == null)
    {
      user = this.fbuserdao.saveFbUser(user);
    }
    else
    {
      user.setUserId(userold.getUserId());
      
      user = this.fbuserdao.updateFbUser(user);
    }
    this.fbuserdao.deleteFbUserEmployers(user.getUserId());
    this.fbuserdao.saveFbEmployerList(ulist, user.getUserId());
    



    this.fbuserdao.deleteFbUserSchools(user.getUserId());
    this.fbuserdao.saveFbUserSchoolsList(slist, user.getUserId());
    return user;
  }
  
  public void saveAskForEndorsement(List uids, String fromid)
  {
    this.fbuserdao.saveAskForEndorsement(uids, fromid);
  }
  
  public void saveEndorsements(List uids, String fromid, String comment)
  {
    this.fbuserdao.saveEndorsements(uids, fromid, comment);
  }
  
  public List getAllEndorsements(String toFbId)
  {
    return this.fbuserdao.getAllEndorsements(toFbId);
  }
  
  public List getAllEndorsementsGiven(String fromFbId)
  {
    return this.fbuserdao.getAllEndorsementsGiven(fromFbId);
  }
  
  public List getAllAskEndorsesTo(String toFbId)
  {
    return this.fbuserdao.getAllAskEndorsesTo(toFbId);
  }
  
  public List getAllFbUsers(int indexdone)
  {
    return this.fbuserdao.getAllFbUsers(indexdone);
  }
  
  public List getAllFbJobs(int indexdone)
  {
    return this.fbuserdao.getAllFbJobs(indexdone);
  }
  
  public String getAllAskEndorsesToIds(String toFbId)
  {
    String ids = "";
    List askList = this.fbuserdao.getAllAskEndorsesTo(toFbId);
    for (int i = 0; i < askList.size(); i++)
    {
      FbEndorsementAsk ask = (FbEndorsementAsk)askList.get(i);
      ids = ids + ask.getFromFbId() + ",";
    }
    if (!StringUtils.isNullOrEmpty(ids)) {
      ids = ids.substring(0, ids.length() - 1);
    }
    return ids;
  }
  
  public FbEmployer saveFbEmployer(FbEmployer emp)
  {
    return this.fbuserdao.saveFbEmployer(emp);
  }
  
  public FbPositions saveFbPositions(FbPositions emp)
  {
    return this.fbuserdao.saveFbPositions(emp);
  }
  
  public FbLocation saveFbLocation(FbLocation emp)
  {
    return this.fbuserdao.saveFbLocation(emp);
  }
  
  public FbSchool saveFbSchool(FbSchool emp)
  {
    return this.fbuserdao.saveFbSchool(emp);
  }
  
  public FbConcentration saveFbConcentration(FbConcentration con)
  {
    return this.fbuserdao.saveFbConcentration(con);
  }
  
  public List getFbUsersskillListByUserId(long userId)
  {
    return this.fbuserdao.getFbUsersskillListByUserId(userId);
  }
  
  public FbUserSkills updateFbUserSkills(FbUserSkills fbUserSkills)
  {
    return this.fbuserdao.updateFbUserSkills(fbUserSkills);
  }
  
  public FbUserSkills saveFbUserSkills(FbUserSkills fbUserSkills)
  {
    return this.fbuserdao.saveFbUserSkills(fbUserSkills);
  }
  
  public List getFbUsersSpecialitiesListByUserId(long userId)
  {
    return this.fbuserdao.getFbUsersSpecialitiesListByUserId(userId);
  }
  
  public FbUserSpecialities saveFbUserSpecialiteis(FbUserSpecialities fbUserSpecialities)
  {
    return this.fbuserdao.saveFbUserSpecialiteis(fbUserSpecialities);
  }
  
  public FbUserSpecialities updateFbUserSpecialiteis(FbUserSpecialities fbUserSpecialities)
  {
    return this.fbuserdao.updateFbUserSpecialiteis(fbUserSpecialities);
  }
  
  public List getFbUsersAchievementListByUserId(long userId)
  {
    return this.fbuserdao.getFbUsersAchievementListByUserId(userId);
  }
  
  public FbUserAchivements saveFbUserAchievement(FbUserAchivements fbUserAchivements)
  {
    return this.fbuserdao.saveFbUserAchievement(fbUserAchivements);
  }
  
  public FbUserAchivements updateFbUserAchievement(FbUserAchivements fbUserAchivements)
  {
    return this.fbuserdao.updateFbUserAchievement(fbUserAchivements);
  }
  
  public FbJobs savePostJob(FbJobs fbJobs)
  {
    return this.fbuserdao.savePostJob(fbJobs);
  }
  
  public FbUserDAO getFbuserdao()
  {
    return this.fbuserdao;
  }
  
  public void setFbuserdao(FbUserDAO fbuserdao)
  {
    this.fbuserdao = fbuserdao;
  }
  
  public FbUserCertifications updateFBUserCertifications(FbUserCertifications fbUserCertifications)
  {
    return this.fbuserdao.updateFBUserCertifications(fbUserCertifications);
  }
  
  public FbUserCertifications saveFBUserCertifications(FbUserCertifications fbUserCertifications)
  {
    return this.fbuserdao.saveFBUserCertifications(fbUserCertifications);
  }
  
  public List getFBUserCertificationsListByUserId(long userId)
  {
    return this.fbuserdao.getFBUserCertificationsListByUserId(userId);
  }
  
  public FbJobs getFBJobsByJobId(long jobId)
  {
    return this.fbuserdao.getFBJobsByJobId(jobId);
  }
  
  public List allFbJobsList()
  {
    return this.fbuserdao.allFbJobsList();
  }
  
  public FbJobApplicants applyJob(FbJobApplicants fsj)
  {
    return this.fbuserdao.applyJob(fsj);
  }
  
  public List getApplicantListByJobId(long jobId)
  {
    return this.fbuserdao.getApplicantListByJobId(jobId);
  }
  
  public FaceBookUser getFbUser(long userId)
  {
    return this.fbuserdao.getFbUser(userId);
  }
  
  public boolean isAppliedtoSameJob(long userId, long jobId)
  {
    return this.fbuserdao.isAppliedtoSameJob(userId, jobId);
  }
}
