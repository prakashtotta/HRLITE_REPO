package com.bo;

import com.bean.JobRequisition;
import com.bean.TaskData;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.dto.Recruiter;
import com.util.PermissionChecker;
import com.util.StringUtils;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;

public class PermissionBO
{
  protected static final Logger logger = Logger.getLogger(PermissionBO.class);
  
  public static boolean isInterviewScheduleAllowed(User user, JobRequisition jb)
  {
    boolean isSuccess = false;
    if (jb == null) {
      return false;
    }
    Recruiter recruiter = BOFactory.getJobRequistionBO().getRecruiterByReqId(jb.getJobreqId());
    
    long hiringmgrid = 0L;
    if (jb.getHiringmgr() != null) {
      hiringmgrid = jb.getHiringmgr().getUserId();
    }
    if (isHiringManagerOrRecruiter(user, hiringmgrid, recruiter)) {
      isSuccess = true;
    } else {
      isSuccess = PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user);
    }
    return isSuccess;
  }
  
  public static boolean isPermissionApplied(String permissionCode, User user)
  {
    return PermissionChecker.isPermissionApplied(permissionCode, user);
  }
  
  public static boolean isInterviewScheduleAllowed(User user, long recruiterId, long hiringMgrId)
  {
    boolean isSuccess = false;
    if ((user.getUserId() == recruiterId) || (user.getUserId() == hiringMgrId)) {
      isSuccess = true;
    } else {
      isSuccess = PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user);
    }
    return isSuccess;
  }
  
  public static boolean isApplicantEditAllowed(User user, long hiringMgrId, Recruiter recruiter)
  {
    boolean isSuccess = false;
    if ((isRecruiter(user, recruiter)) || (user.getUserId() == hiringMgrId)) {
      isSuccess = true;
    } else {
      isSuccess = PermissionChecker.isPermissionApplied("ALL_APPLICANTS", user);
    }
    return isSuccess;
  }
  
  public static boolean isApplicantEditAllowed(User user, long hiringMgrId, long recruiterId, String isrecruiterGroup)
  {
    boolean isSuccess = false;
    if (isHiringManagerOrRecruiter(user, hiringMgrId, recruiterId, isrecruiterGroup)) {
      isSuccess = true;
    } else {
      isSuccess = PermissionChecker.isPermissionApplied("ALL_APPLICANTS", user);
    }
    return isSuccess;
  }
  
  public static boolean isHiringManagerOrRecruiter(User user, long hiringMgrId, Recruiter recruiter)
  {
    boolean isSuccess = false;
    if ((isRecruiter(user, recruiter)) || (user.getUserId() == hiringMgrId)) {
      isSuccess = true;
    }
    return isSuccess;
  }
  
  public static boolean isHiringManagerOrRecruiter(User user, long hiringMgrId, long recruiterId, String isrecruiterGroup)
  {
    boolean isSuccess = false;
    Recruiter recruiter = new Recruiter();
    recruiter.setRecruiterId(recruiterId);
    recruiter.setIsgrouprecruiter(isrecruiterGroup);
    if ((isRecruiter(user, recruiter)) || (user.getUserId() == hiringMgrId)) {
      isSuccess = true;
    }
    return isSuccess;
  }
  
  public static boolean isRecruiter(User user, Recruiter recruiter)
  {
    boolean isSuccess = false;
    if (recruiter == null) {
      return false;
    }
    if ((!StringUtils.isNullOrEmpty(recruiter.getIsgrouprecruiter())) && (recruiter.getIsgrouprecruiter().equals("Y")))
    {
      UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(recruiter.getRecruiterId());
      if (usrgrp != null)
      {
        Set users = usrgrp.getUsers();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            if ((touser != null) && (touser.getUserId() == user.getUserId()))
            {
              isSuccess = true;
              break;
            }
          }
        }
      }
    }
    else if (user.getUserId() == recruiter.getRecruiterId())
    {
      isSuccess = true;
    }
    return isSuccess;
  }
  
  public static boolean isRequistionHeaderTreeAllowed(User user, JobRequisition jb)
  {
    logger.info("inside isRequistionHeaderTreeAllowed");
    boolean isSuccess = false;
    if (jb == null) {
      return false;
    }
    Recruiter recruiter = BOFactory.getJobRequistionBO().getRecruiterByReqId(jb.getJobreqId());
    

    long hiringmgrid = 0L;
    if (jb.getHiringmgr() != null) {
      hiringmgrid = jb.getHiringmgr().getUserId();
    }
    if (isHiringManagerOrRecruiter(user, hiringmgrid, recruiter)) {
      return true;
    }
    if (PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user)) {
      return true;
    }
    return isSuccess;
  }
  
  public static boolean isInterviewScheduleAllowed(User user, long jbreqId)
  {
    boolean isSuccess = false;
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(jbreqId));
    if (jb == null) {
      return false;
    }
    Recruiter recruiter = BOFactory.getJobRequistionBO().getRecruiterByReqId(jb.getJobreqId());
    if (isHiringManagerOrRecruiter(user, jb.getHiringmgr().getUserId(), recruiter)) {
      isSuccess = true;
    } else {
      isSuccess = PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user);
    }
    return isSuccess;
  }
  
  public static boolean isUserHasRequistionTask(User user, long jbreqId, String tasktype)
  {
    boolean isSuccess = false;
    TaskData task = TaskBO.getTaskAssignedToUser(user.getUserId(), jbreqId, tasktype);
    if (task != null) {
      isSuccess = true;
    }
    return isSuccess;
  }
  
  public static boolean isLoggedInUserIsOwner(long userid, long formownerid, String isGroup, UserGroup usrgrp)
  {
    boolean isowner = false;
    if ((!StringUtils.isNullOrEmpty(isGroup)) && (isGroup.equals("Y")))
    {
      if (usrgrp != null)
      {
        Set users = usrgrp.getUsers();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            if ((touser != null) && (touser.getUserId() == userid))
            {
              isowner = true;
              break;
            }
          }
        }
      }
    }
    else if (formownerid == userid) {
      isowner = true;
    }
    return isowner;
  }
  
  public static boolean isRequistionReadAllowed(User user1, long hiringMgrId, long recruiterId, String isGroupRecruiter)
  {
    boolean success = false;
    boolean isHiringMgrOrRrecruiter = isHiringManagerOrRecruiter(user1, hiringMgrId, recruiterId, isGroupRecruiter);
    if (isHiringMgrOrRrecruiter) {
      return true;
    }
    boolean isAllRequistionAction = isPermissionApplied("ALL_REQUISITIONS", user1);
    if (isAllRequistionAction) {
      return true;
    }
    boolean isAllRequistionRead = isPermissionApplied("ALL_REQUISITIONS_READ", user1);
    if (isAllRequistionRead) {
      return true;
    }
    return success;
  }
  
  public static boolean isRequistionReadAllowed(User user1)
  {
    boolean success = false;
    boolean isAllRequistionAction = isPermissionApplied("ALL_REQUISITIONS", user1);
    if (isAllRequistionAction) {
      return true;
    }
    boolean isAllRequistionRead = isPermissionApplied("ALL_REQUISITIONS_READ", user1);
    if (isAllRequistionRead) {
      return true;
    }
    return success;
  }
  
  public static boolean isRequistionHeaderTreeAllowed(User user1, long hiringMgrId, long recruiterId, String isGroupRecruiter)
  {
    boolean success = false;
    boolean isHiringMgrOrRrecruiter = isHiringManagerOrRecruiter(user1, hiringMgrId, recruiterId, isGroupRecruiter);
    if (isHiringMgrOrRrecruiter) {
      return true;
    }
    boolean isAllRequistionAction = isPermissionApplied("ALL_REQUISITIONS", user1);
    if (isAllRequistionAction) {
      return true;
    }
    return success;
  }
}
