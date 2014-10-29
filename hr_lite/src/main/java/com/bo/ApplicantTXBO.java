package com.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;

import com.bean.ApplicantActivity;
import com.bean.ApplicantAttachments;
import com.bean.ApplicantOfferAttachment;
import com.bean.ApplicantOtherDetails;
import com.bean.ApplicantUser;
import com.bean.ApplicantUserActions;
import com.bean.EmailTemplates;
import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.OfferApprover;
import com.bean.OfferWatchList;
import com.bean.OrganizationEmailTemplate;
import com.bean.ReferralAgencyApplicants;
import com.bean.RefferalEmployee;
import com.bean.ResumeSourceType;
import com.bean.TaskData;
import com.bean.Timezone;
import com.bean.UrlEncodeData;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.pool.TalentPool;
import com.bean.pool.TalentPoolElements;
import com.common.Common;
import com.common.EmailNotificationSettingFunction;
import com.common.PluginException;
import com.dao.ApplicantTXDAO;
import com.dao.JobRequistionTXDAO;
import com.dao.LovDAO;
import com.dao.LovOpsTXDAO;
import com.dao.TalentPoolDAO;
import com.dao.TalentPoolTXDAO;
import com.dao.TaskDAO;
import com.dao.TaskTXDAO;
import com.dao.UserTXDAO;
import com.manager.EmailTaskManager;
import com.plugin.IntercepterImpl;
import com.plugin.PluginScripts;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.DesEncrypter;
import com.util.EmailTask;
import com.util.EmailUtil;
import com.util.EncryptDecrypt;
import com.util.PasswordGenerator;
import com.util.StringUtils;

public class ApplicantTXBO
{
  protected static final Logger logger = Logger.getLogger(ApplicantTXBO.class);
  ApplicantTXDAO applicanttxdao;
  JobRequistionTXDAO jobreqtxdao;
  TaskTXDAO tasktxdao;
  UserTXDAO usertxdao;
  LovOpsTXDAO lovopstxdao;
  TalentPoolTXDAO talentpooltxdao;
  
  public ApplicantTXDAO getApplicanttxdao()
  {
    return this.applicanttxdao;
  }
  
  public void setApplicanttxdao(ApplicantTXDAO applicanttxdao)
  {
    this.applicanttxdao = applicanttxdao;
  }
  
  public JobRequistionTXDAO getJobreqtxdao()
  {
    return this.jobreqtxdao;
  }
  
  public void setJobreqtxdao(JobRequistionTXDAO jobreqtxdao)
  {
    this.jobreqtxdao = jobreqtxdao;
  }
  
  public void onBoardJoined(String applicantId, User user, String comment, Calendar eventdate)
    throws Exception
  {
    logger.info("Inside onBoardJoined method");
    try
    {
      JobApplicant applicant = this.applicanttxdao.getJobApplicant(new Long(applicantId).longValue());
      





      applicant.setJoinedcomment(comment);
      applicant.setUpdatedBy(user.getUserName());
      applicant.setUpdatedDate(new Date());
      applicant.setInterviewState("On Board - Joined");
      applicant.setStatus("A");
      if (eventdate != null) {
        applicant.setJoineddate(eventdate.getTime());
      }
      if ((applicant.getResumesourcetype() != null) && ((applicant.getResumesourcetype().getResumeSourceTypeId() == 1) || (applicant.getResumesourcetype().getResumeSourceTypeId() == 5)))
      {
        if ((BOFactory.getJobRequistionBO().isRedemptionRuleAppliedToAgency(applicant.getReqId(), "AFTER-JOINING")) || (BOFactory.getJobRequistionBO().isRedemptionRuleAppliedToEmployeeRef(applicant.getReqId(), "AFTER-JOINING")))
        {
          ReferralAgencyApplicants refagenctapplicant = new ReferralAgencyApplicants();
          refagenctapplicant.setApplicantId(applicant.getApplicantId());
          refagenctapplicant.setApplicantName(applicant.getFullName());
          refagenctapplicant.setResumeSourceId(applicant.getResumesourcetype().getResumeSourceTypeId());
          
          refagenctapplicant.setJobReqId(applicant.getReqId());
          refagenctapplicant.setJobTitle(applicant.getJobTitle());
          refagenctapplicant.setStatus("A");
          refagenctapplicant.setState(applicant.getInterviewState());
          refagenctapplicant.setCreatedDate(new Date());
          if (eventdate != null) {
            refagenctapplicant.setEventdate(eventdate.getTime());
          }
          addApplicantForReferralAndAgencyTracking(applicant, refagenctapplicant);
        }
      }
      else {
        this.applicanttxdao.updateApplicant(applicant);
      }
      ApplicantActivity activity = populateApplicantActivity(applicant, user, null);
      activity.setComment(comment);
      activity.setIsDisplayAppPage("Y");
      this.applicanttxdao.saveApplicantActivity(activity);
      



      postApplicantOnBoardSubmit(applicant, user);
    }
    catch (PluginException e)
    {
      logger.info("plugin exception1" + e.getMessage());
      throw new Exception(e.getMessage());
    }
    catch (Exception e)
    {
      logger.info("exeption saveapplicantData ...", e);
      throw e;
    }
  }
  
  private void rollBack()
  {
    if ((this.applicanttxdao.getSessionFactory().getCurrentSession() != null) && (this.applicanttxdao.getSessionFactory().getCurrentSession().getTransaction() != null)) {
      this.applicanttxdao.getSessionFactory().getCurrentSession().getTransaction().rollback();
    }
  }
  
  private void preApplicantOnBoardSubmit(JobApplicant applicant, User user)
    throws PluginException
  {
    if (PluginScripts.preAPPLICANT_ONBOARD_SUBMIT != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("ApplicantObject", applicant);
        ue.setObject("UserObject", user);
        ue.firePreApplicantOnBoardSubmit();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception" + ex.getMessage());
        logger.info("plugin exception", ex);
        throw new PluginException(ex.getMessage(), ex);
      }
    }
  }
  
  private void postApplicantOnBoardSubmit(JobApplicant applicant, User user)
    throws PluginException
  {
    if (PluginScripts.postAPPLICANT_ONBOARD_SUBMIT != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("ApplicantObject", applicant);
        ue.setObject("UserObject", user);
        ue.firePostApplicantOnBoardSubmit();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception", ex);
        throw new PluginException(ex.getMessage(), ex);
      }
    }
  }
  
  public void scheduleScreeningInterview(User fromUser, JobApplicant applicant, JobApplicationEvent event, String reschedule)
  {
    logger.info("Inside scheduleScreeningInterview method");
    if ((!StringUtils.isNullOrEmpty(reschedule)) && (reschedule.equals("yes")))
    {
      JobApplicant applicant1 = this.applicanttxdao.getJobApplicant(applicant.getApplicantId());
      



      this.tasktxdao.markTaskComplete(applicant1.getApplicantId(), applicant1.getUuid(), fromUser.getUserName());
      

      applicant1.setOwner(applicant.getOwner());
      
      applicant1.setInterview_organizer_id(applicant.getInterview_organizer_id());
      
      applicant1.setInterviewState(applicant.getInterviewState());
      
      JobApplicationEvent event1 = this.applicanttxdao.getJobApplicationEvent(applicant.getApplicantId(), event.getEventType());
      logger.info("event.getEventType()" + event.getEventType());
      long prejobappeventid = event1.getJobAppEventId();
      this.applicanttxdao.deleteApplicationEvent(event1);
      event1 = null;
      
      this.applicanttxdao.saveInterviewSchedule(event);
      if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
      {
        applicant1.setOwnerGroup(applicant.getOwnerGroup());
        applicant1.setIsGroup("Y");
      }
      else
      {
        applicant1.setOwnerGroup(null);
        applicant1.setIsGroup("N");
      }
      this.applicanttxdao.updateApplicant(applicant1);
      
      String tasktype = Common.APPLICANT_REVIEW_TASK;
      if (applicant1.getInterviewState().startsWith("Interview")) {
        tasktype = Common.APPLICANT_INTERVIEW_TASK;
      }
      if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
      {
        this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), tasktype, fromUser.getUserName());
        
        long groupId = 0L;
        if (applicant.getOwnerGroup() != null) {
          groupId = applicant.getOwnerGroup().getUsergrpId();
        }
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(groupId);
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData task = new TaskData();
            task.setTaskname(applicant.getFullName());
            task.setIdvalue(applicant.getApplicantId());
            task.setTasktype(tasktype);
            task.setAssignedbyUserId(fromUser.getUserId());
            task.setAssignedbyUserName(fromUser.getFirstName() + " " + fromUser.getLastName());
            task.setAssignedtoUserId(touser.getUserId());
            task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            task.setCreatedBy(fromUser.getUserName());
            task.setCreatedDate(new Date());
            task.setStatus("A");
            task.setUuid(applicant.getUuid());
            task.setEventdate(event.getInterviewDate());
            task.setSuper_user_key(fromUser.getSuper_user_key());
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
        }
      }
      else
      {
        this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), tasktype, fromUser.getUserName());
        

        TaskData task = new TaskData();
        task.setTaskname(applicant.getFullName());
        task.setIdvalue(applicant.getApplicantId());
        task.setTasktype(tasktype);
        task.setAssignedbyUserId(fromUser.getUserId());
        task.setAssignedbyUserName(fromUser.getFirstName() + " " + fromUser.getLastName());
        task.setAssignedtoUserId(applicant.getOwner().getUserId());
        User touser = this.usertxdao.getUser(applicant.getOwner().getUserId());
        task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
        task.setCreatedBy(fromUser.getUserName());
        task.setCreatedDate(new Date());
        task.setStatus("A");
        task.setUuid(applicant.getUuid());
        task.setEventdate(event.getInterviewDate());
        task.setSuper_user_key(fromUser.getSuper_user_key());
        this.tasktxdao.saveTask(task);
      }
    }
    else
    {
      this.applicanttxdao.saveInterviewSchedule(event);
      this.applicanttxdao.updateApplicant(applicant);
      




      this.tasktxdao.markTaskComplete(applicant.getApplicantId(), applicant.getUuid(), fromUser.getUserName());
      







      String tasktype = Common.APPLICANT_REVIEW_TASK;
      if (applicant.getInterviewState().startsWith("Interview")) {
        tasktype = Common.APPLICANT_INTERVIEW_TASK;
      }
      if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
      {
        long groupId = 0L;
        if (applicant.getOwnerGroup() != null) {
          groupId = applicant.getOwnerGroup().getUsergrpId();
        }
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(groupId);
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData task = new TaskData();
            task.setTaskname(applicant.getFullName());
            task.setIdvalue(applicant.getApplicantId());
            task.setTasktype(tasktype);
            task.setAssignedbyUserId(fromUser.getUserId());
            task.setAssignedbyUserName(fromUser.getFirstName() + " " + fromUser.getLastName());
            task.setAssignedtoUserId(touser.getUserId());
            task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            task.setCreatedBy(fromUser.getUserName());
            task.setCreatedDate(new Date());
            task.setStatus("A");
            task.setUuid(applicant.getUuid());
            task.setEventdate(event.getInterviewDate());
            task.setSuper_user_key(fromUser.getSuper_user_key());
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
        }
      }
      else
      {
        TaskData task = new TaskData();
        task.setTaskname(applicant.getFullName());
        task.setIdvalue(applicant.getApplicantId());
        task.setTasktype(tasktype);
        task.setAssignedbyUserId(fromUser.getUserId());
        task.setAssignedbyUserName(fromUser.getFirstName() + " " + fromUser.getLastName());
        task.setAssignedtoUserId(applicant.getOwner().getUserId());
        User touser = this.usertxdao.getUser(applicant.getOwner().getUserId());
        task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
        task.setCreatedBy(fromUser.getUserName());
        task.setCreatedDate(new Date());
        task.setStatus("A");
        task.setUuid(applicant.getUuid());
        task.setEventdate(event.getInterviewDate());
        task.setSuper_user_key(fromUser.getSuper_user_key());
        
        this.tasktxdao.saveTask(task);
      }
    }
    ApplicantActivity activity = populateApplicantActivity(applicant, fromUser, null);
    activity.setComment(event.getNotes());
    activity.setIsDisplayAppPage("Y");
    if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
    {
      UserGroup usrgrp = this.usertxdao.getUserGroupNameandId(applicant.getOwnerGroup().getUsergrpId());
      
      activity.setIsgroup("Y");
      activity.setAssignedTouserId(usrgrp.getUsergrpId());
      activity.setAssignedToUserName(usrgrp.getUsergrpName());
    }
    else
    {
      activity.setIsgroup("N");
      User touser = this.usertxdao.getUserFullNameEmailAndUserId(applicant.getOwner().getUserId());
      activity.setAssignedTouserId(touser.getUserId());
      activity.setAssignedToUserName(touser.getFirstName() + " " + touser.getLastName());
    }
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public void interviwercommment(User fromUser, JobApplicationEvent event, JobApplicant applicant, List applicantRatingList)
  {
    logger.info("Inside interviwercommment method");
    this.applicanttxdao.updateInterviewComments(event, applicant);
    this.applicanttxdao.addApplicantRatingList(applicantRatingList);
    if ((applicant.getStatus() != null) && (!applicant.getStatus().equals("H")))
    {
      String tasktype = Common.APPLICANT_REVIEW_TASK;
      if (applicant.getInterviewState().contains("Interview")) {
        tasktype = Common.APPLICANT_INTERVIEW_TASK;
      }
      this.tasktxdao.markTaskComplete(applicant.getApplicantId(), applicant.getUuid(), fromUser.getUserName());
      if (((StringUtils.isEmpty(applicant.getIsGroup())) || (!applicant.getIsGroup().equals("Y"))) || (
      





        (applicant.getStatus() != null) && (!applicant.getStatus().equals("R"))))
      {
        JobRequisition jb = this.jobreqtxdao.getJobRequision(applicant.getReqId());
        if (applicant.getInterview_organizer_id() != 0L)
        {
          TaskData task = new TaskData();
          task.setTaskname(applicant.getFullName());
          task.setIdvalue(applicant.getApplicantId());
          task.setTasktype(Common.APPLICANT_IN_QUEUE);
          task.setAssignedbyUserId(fromUser.getUserId());
          task.setAssignedbyUserName(fromUser.getFirstName() + " " + fromUser.getLastName());
          User intervieworganizer = this.usertxdao.getUser(applicant.getInterview_organizer_id());
          task.setAssignedtoUserId(intervieworganizer.getUserId());
          task.setAssignedtoUserName(intervieworganizer.getFirstName() + " " + intervieworganizer.getLastName());
          task.setCreatedBy(fromUser.getUserName());
          task.setCreatedDate(new Date());
          task.setStatus("A");
          task.setUuid(applicant.getUuid());
          task.setEventdate(event.getInterviewDate());
          task.setSuper_user_key(fromUser.getSuper_user_key());
          this.tasktxdao.saveTask(task);
          

          TaskBO.sendTaskAssignEmail(fromUser, task, event.getInterviewerComments());
        }
      }
    }
    ApplicantActivity activity = populateApplicantActivity(applicant, fromUser, null);
    activity.setComment(event.getInterviewerComments());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public void addApplicantForReferralAndAgencyTracking(JobApplicant applicant, ReferralAgencyApplicants refagenctapplicant)
  {
    logger.info("Inside addApplicantForReferralAndAgencyTracking method");
    this.applicanttxdao.updateApplicant(applicant);
    this.applicanttxdao.saveReferralAgenecyApplicant(refagenctapplicant);
  }
  
  public void addApplicantForReferralAndAgencyTracking(ReferralAgencyApplicants refagenctapplicant)
  {
    logger.info("Inside addApplicantForReferralAndAgencyTracking method");
    this.applicanttxdao.deleteReferralAgencyApplicants(refagenctapplicant.getApplicantId());
    this.applicanttxdao.saveReferralAgenecyApplicant(refagenctapplicant);
  }
  
  public void interviwercommmentonhold(JobApplicationEvent event, JobApplicant applicant, User user1)
  {
    logger.info("Inside interviwercommmentonhold method");
    this.applicanttxdao.updateInterviewComments(event, applicant);
    
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
    activity.setComment(applicant.getInterviewerComments());
    activity.setIsDisplayAppPage("Y");
    activity.setAssignedTouserId(applicant.getOwner().getUserId());
    activity.setAssignedToUserName(applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
    activity.setIsgroup("N");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public void declineInterview(JobApplicant applicant, JobApplicationEvent event, User user1, String comment)
  {
    logger.info("Inside declineInterview method");
    this.applicanttxdao.updateApplicant(applicant);
    
    this.applicanttxdao.updateApplicationEvent(event);
    
    this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), Common.APPLICANT_REVIEW_TASK, user1.getUserName());
    
    TaskData task = new TaskData();
    task.setTaskname(applicant.getFullName());
    task.setIdvalue(applicant.getApplicantId());
    task.setTasktype(Common.APPLICANT_IN_QUEUE);
    task.setAssignedbyUserId(user1.getUserId());
    task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
    task.setAssignedtoUserId(applicant.getOwner().getUserId());
    
    task.setAssignedtoUserName(applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
    task.setCreatedBy(user1.getUserName());
    task.setCreatedDate(new Date());
    task.setStatus("A");
    task.setUuid(applicant.getUuid());
    task.setEventdate(new Date());
    task.setSuper_user_key(user1.getSuper_user_key());
    this.tasktxdao.saveTask(task);
    

    TaskBO.sendTaskAssignEmail(user1, task, comment);
    
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
    activity.setComment(comment);
    activity.setIsDisplayAppPage("Y");
    activity.setAssignedTouserId(applicant.getOwner().getUserId());
    activity.setAssignedToUserName(applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
    activity.setActivityName(Common.DECLINED_REVIEW);
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public void reassignInterview(User user1, JobApplicant applicant, JobApplicationEvent event, String comment)
  {
    logger.info("Inside reassignInterview method");
    this.applicanttxdao.updateApplicant(applicant);
    this.applicanttxdao.updateApplicationEvent(event);
    

    TaskData taskold = this.tasktxdao.getTask(applicant.getApplicantId(), Common.APPLICANT_REVIEW_TASK);
    if (taskold != null) {
      this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), Common.APPLICANT_REVIEW_TASK, user1.getUserName());
    }
    if (taskold == null) {
      taskold = this.tasktxdao.getTask(applicant.getApplicantId(), Common.APPLICANT_INTERVIEW_TASK);
    }
    if (taskold != null) {
      this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), Common.APPLICANT_INTERVIEW_TASK, user1.getUserName());
    }
    if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
    {
      long groupId = 0L;
      if (applicant.getOwnerGroup() != null) {
        groupId = applicant.getOwnerGroup().getUsergrpId();
      }
      UserGroup usrgrp = this.lovopstxdao.getUserGroup(groupId);
      Set users = usrgrp.getUsers();
      List<TaskData> taskList = new ArrayList();
      if ((users != null) && (users.size() > 0))
      {
        Iterator itr = users.iterator();
        while (itr.hasNext())
        {
          User touser = (User)itr.next();
          
          TaskData task = new TaskData();
          task.setTaskname(applicant.getFullName());
          task.setIdvalue(applicant.getApplicantId());
          task.setTasktype(taskold.getTasktype());
          task.setAssignedbyUserId(user1.getUserId());
          task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
          task.setAssignedtoUserId(touser.getUserId());
          task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
          task.setCreatedBy(taskold.getCreatedBy());
          task.setCreatedDate(taskold.getCreatedDate());
          task.setStatus("A");
          task.setUuid(applicant.getUuid());
          task.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
          task.setUpdatedDate(new Date());
          task.setEventdate(event.getInterviewDate());
          task.setSuper_user_key(user1.getSuper_user_key());
          taskList.add(task);
        }
        this.tasktxdao.saveAllTask(taskList);
        
        TaskBO.sendTaskAssignEmail(user1, taskList, comment);
      }
    }
    else
    {
      TaskData task = new TaskData();
      task.setTaskname(applicant.getFullName());
      task.setIdvalue(applicant.getApplicantId());
      task.setTasktype(taskold.getTasktype());
      task.setAssignedbyUserId(user1.getUserId());
      task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
      User touser = this.usertxdao.getUserFullNameEmailAndUserId(applicant.getOwner().getUserId());
      task.setAssignedtoUserId(touser.getUserId());
      task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
      task.setCreatedBy(taskold.getCreatedBy());
      task.setCreatedDate(taskold.getCreatedDate());
      task.setStatus("A");
      task.setUuid(applicant.getUuid());
      task.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
      task.setUpdatedDate(new Date());
      task.setEventdate(event.getInterviewDate());
      task.setSuper_user_key(user1.getSuper_user_key());
      this.tasktxdao.saveTask(task);
      

      TaskBO.sendTaskAssignEmail(user1, task, comment);
    }
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
    activity.setComment(comment);
    activity.setActivityName("Reassign review");
    activity.setIsDisplayAppPage("Y");
    if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
    {
      UserGroup usrgrp = this.usertxdao.getUserGroupNameandId(applicant.getOwnerGroup().getUsergrpId());
      
      activity.setIsgroup("Y");
      activity.setAssignedTouserId(usrgrp.getUsergrpId());
      activity.setAssignedToUserName(usrgrp.getUsergrpName());
    }
    else
    {
      activity.setIsgroup("N");
      User touser = this.usertxdao.getUserFullNameEmailAndUserId(applicant.getOwner().getUserId());
      activity.setAssignedTouserId(touser.getUserId());
      activity.setAssignedToUserName(touser.getFirstName() + " " + touser.getLastName());
    }
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public void moveApplicantFromFolderToFolder(User user1, String uuid, String fromState, String toState)
  {
    try
    {
      logger.info("start moveApplicantFromFolderToFolder toState" + toState);
      JobApplicant applicant = this.applicanttxdao.getApplicantDetailsByUUID(uuid);
      applicant.setInterviewState(toState);
      applicant.setUpdatedBy(user1.getUserName());
      applicant.setUpdatedDate(new Date());
      JobRequisition jobreq = this.jobreqtxdao.getJobRequision(applicant.getReqId());
      String activityname = fromState + " -> " + toState;
      ApplicantActivity activity = new ApplicantActivity();
      String comment = Constant.getResourceStringValue("through.drag.drop.comment", user1.getLocale());
      



      this.tasktxdao.markTaskComplete(applicant.getApplicantId(), applicant.getUuid(), user1.getUserName());
      
      logger.info("all tasks related to applcant marked closed");
      if ((!StringUtils.isNullOrEmpty(toState)) && (toState.indexOf("Hold") > -1))
      {
        applicant.setStatus("H");
      }
      else if ((!StringUtils.isNullOrEmpty(toState)) && (toState.indexOf("Reject") > -1))
      {
        applicant.setStatus("R");
      }
      else if ((!StringUtils.isNullOrEmpty(toState)) && (Constant.hiringManagerInterviewStates.contains(toState)))
      {
        applicant.setStatus("A");
        




        logger.info("inside hiring mgr");
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("drag.drop.applicants.need.task.assign"))) && (Constant.getValue("drag.drop.applicants.need.task.assign").equals("yes")))
        {
          String tasktype = Common.APPLICANT_REVIEW_TASK;
          if ((toState != null) && (toState.contains("Interview"))) {
            tasktype = Common.APPLICANT_INTERVIEW_TASK;
          }
          TaskData task = new TaskData();
          task.setTaskname(applicant.getFullName());
          task.setIdvalue(applicant.getApplicantId());
          task.setTasktype(tasktype);
          task.setAssignedbyUserId(user1.getUserId());
          task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
          task.setAssignedtoUserId(jobreq.getHiringmgr().getUserId());
          task.setAssignedtoUserName(jobreq.getHiringmgr().getFirstName() + " " + jobreq.getHiringmgr().getLastName());
          task.setCreatedBy(user1.getCreatedBy());
          task.setCreatedDate(user1.getCreatedDate());
          task.setStatus("A");
          task.setUuid(applicant.getUuid());
          task.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
          task.setUpdatedDate(new Date());
          task.setSuper_user_key(user1.getSuper_user_key());
          
          this.tasktxdao.saveTask(task);
          
          TaskBO.sendTaskAssignEmail(user1, task, comment);
        }
        applicant.setIsGroup("N");
        applicant.setOwner(jobreq.getHiringmgr());
        logger.info("inside hiring mgr 1");
        



        CreateEventOnMoveFolderToFolder(user1, applicant, toState, comment);
      }
      else
      {
        applicant.setStatus("A");
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("drag.drop.applicants.need.task.assign"))) && (Constant.getValue("drag.drop.applicants.need.task.assign").equals("yes")))
        {
          if ((applicant.getInterviewState() == null) || (!applicant.getInterviewState().equals("Mark deleted")))
          {
            String tasktype = Common.APPLICANT_IN_QUEUE;
            if ((!StringUtils.isEmpty(jobreq.getIsgrouprecruiter())) && (jobreq.getIsgrouprecruiter().equals("Y")))
            {
              long groupId = 0L;
              
              UserGroup usrgrp = this.lovopstxdao.getUserGroup(jobreq.getRecruiterId());
              Set users = usrgrp.getUsers();
              List<TaskData> taskList = new ArrayList();
              if ((users != null) && (users.size() > 0))
              {
                Iterator itr = users.iterator();
                while (itr.hasNext())
                {
                  User touser = (User)itr.next();
                  
                  TaskData task = new TaskData();
                  task.setTaskname(applicant.getFullName());
                  task.setIdvalue(applicant.getApplicantId());
                  task.setTasktype(tasktype);
                  task.setAssignedbyUserId(user1.getUserId());
                  task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
                  task.setAssignedtoUserId(touser.getUserId());
                  task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
                  task.setCreatedBy(user1.getUserName());
                  task.setCreatedDate(new Date());
                  task.setStatus("A");
                  task.setUuid(applicant.getUuid());
                  task.setSuper_user_key(user1.getSuper_user_key());
                  taskList.add(task);
                }
                this.tasktxdao.saveAllTask(taskList);
                TaskBO.sendTaskAssignEmail(user1, taskList, comment);
              }
              applicant.setIsGroup("Y");
              applicant.setOwnerGroup(usrgrp);
            }
            else
            {
              TaskData task = new TaskData();
              task.setTaskname(applicant.getFullName());
              task.setIdvalue(applicant.getApplicantId());
              task.setTasktype(tasktype);
              task.setAssignedbyUserId(user1.getUserId());
              task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
              task.setAssignedtoUserId(applicant.getOwner().getUserId());
              User touser = this.usertxdao.getUser(jobreq.getRecruiterId());
              task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
              task.setCreatedBy(user1.getUserName());
              task.setCreatedDate(new Date());
              task.setStatus("A");
              task.setUuid(applicant.getUuid());
              task.setSuper_user_key(user1.getSuper_user_key());
              
              this.tasktxdao.saveTask(task);
              TaskBO.sendTaskAssignEmail(user1, task, comment);
              
              applicant.setIsGroup("N");
              applicant.setOwner(touser);
            }
          }
        }
        else if ((!StringUtils.isEmpty(jobreq.getIsgrouprecruiter())) && (jobreq.getIsgrouprecruiter().equals("Y")))
        {
          long groupId = 0L;
          
          UserGroup usrgrp = this.lovopstxdao.getUserGroup(jobreq.getRecruiterId());
          applicant.setIsGroup("Y");
          applicant.setOwnerGroup(usrgrp);
        }
        else
        {
          User touser = this.usertxdao.getUser(jobreq.getRecruiterId());
          applicant.setIsGroup("N");
          applicant.setOwner(touser);
        }
      }
      activity.setApplicantId(applicant.getApplicantId());
      activity.setUuid(applicant.getUuid());
      activity.setCreatedBy(user1.getUserName());
      activity.setUserId(user1.getUserId());
      activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
      activity.setCreatedDate(new Date());
      activity.setComment(comment);
      activity.setCreatedByType(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
      activity.setActivityName(activityname);
      activity.setIsDisplayAppPage("Y");
      if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
      {
        activity.setIsgroup("Y");
        activity.setAssignedTouserId(applicant.getOwnerGroup().getUsergrpId());
        activity.setAssignedToUserName(applicant.getOwnerGroup().getUsergrpName());
      }
      else
      {
        activity.setIsgroup("N");
        activity.setAssignedTouserId(applicant.getOwner().getUserId());
        activity.setAssignedToUserName(applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
      }
      this.applicanttxdao.saveApplicantActivity(activity);
      
      logger.info("to state" + toState);
      logger.info("to state" + applicant.getInterviewState());
      if ((!StringUtils.isNullOrEmpty(applicant.getInterviewState())) && (applicant.getInterviewState().indexOf("Offer") > -1))
      {
        logger.info("to state" + toState);
        applicant.setStatus("A");
        applicant.setOfferinitiatedby(user1.getUserName());
        applicant.setOfferinitiatedbyName(user1.getFirstName() + " " + user1.getLastName());
        if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
        {
          applicant.setOfferownerId(applicant.getOwnerGroup().getUsergrpId());
          applicant.setIsofferownerGroup("Y");
          applicant.setOfferownerName(applicant.getOwnerGroup().getUsergrpName());
        }
        else
        {
          applicant.setOfferownerId(applicant.getOwner().getUserId());
          applicant.setIsofferownerGroup("N");
          applicant.setOfferownerName(applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
        }
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getInterviewState())) && (applicant.getInterviewState().equals("Offer Declined"))) {
        applicant.setOfferdeclineddate(new Date());
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getInterviewState())) && (applicant.getInterviewState().equals("On Board - Joined"))) {
        applicant.setJoineddate(new Date());
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getInterviewState())) && (applicant.getInterviewState().equals("Offer Released")))
      {
        applicant.setOfferreleasedate(new Date());
        applicant.setOfferreleaseddby(user1.getUserName());
        BOFactory.getApplicantTXBO().sendOfferEmail(applicant);
      }
      this.applicanttxdao.updateApplicant(applicant);
      logger.info("end moveApplicantFromFolderToFolder");
    }
    catch (Exception e)
    {
      logger.info("error on moveApplicantFromFolderToFolder", e);
    }
  }
  
  public void CreateEventOnMoveFolderToFolder(User user1, JobApplicant applicant, String toState, String note)
    throws Exception
  {
    try
    {
      logger.info("start CreateEventOnMoveFolderToFolder");
      JobApplicationEvent event = new JobApplicationEvent();
      event.setCreatedBy(user1.getUserName());
      event.setCreatedByName(user1.getFirstName() + " " + user1.getLastName());
      event.setCreatedDate(new Date());
      event.setApplicant(applicant);
      event.setOwner(applicant.getOwner());
      event.setOwnerGroup(null);
      event.setIsGroup("N");
      event.setInterviewDate(new Date());
      event.setEventType(Common.getEventType(toState));
      logger.info("event type" + event.getEventType());
      event.setStatus(0);
      event.setModofinterviewid(0);
      event.setNotes(note);
      event.setInterviewState(applicant.getInterviewState());
      JobApplicationEvent event1 = this.applicanttxdao.getJobApplicationEvent(applicant.getApplicantId(), event.getEventType());
      if (event1 != null)
      {
        this.applicanttxdao.deleteApplicationEvent(event1);
        event1 = null;
      }
      this.applicanttxdao.saveInterviewSchedule(event);
      logger.info("end CreateEventOnMoveFolderToFolder");
    }
    catch (Exception e)
    {
      logger.info("error on CreateEventOnMoveFolderToFolder", e);
    }
  }
  
  public void cancelOffer(JobApplicant applicant, JobRequisition jobreq, User user1)
  {
    logger.info("Inside cancelOffer method");
    this.applicanttxdao.updateApplicant(applicant);
    this.jobreqtxdao.updateJobRequistion(jobreq);
    
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
    activity.setComment(applicant.getOffercancelledcomment());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
    

    TaskBO.sendEventEmail(applicant, user1, applicant.getOffercancelledcomment(), "Offer Cancelled", true, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString());
  }
  
  public void declineoffer(JobApplicant applicant, JobRequisition jobreq, String addedbytype)
  {
    logger.info("Inside declineoffer method");
    if ((!StringUtils.isEmpty(jobreq.getIsgrouprecruiter())) && (jobreq.getIsgrouprecruiter().equals("Y")))
    {
      long groupId = 0L;
      
      UserGroup usrgrp = this.lovopstxdao.getUserGroup(jobreq.getRecruiterId());
      Set users = usrgrp.getUsers();
      List<TaskData> taskList = new ArrayList();
      if ((users != null) && (users.size() > 0))
      {
        Iterator itr = users.iterator();
        while (itr.hasNext())
        {
          User touser = (User)itr.next();
          
          TaskData task = new TaskData();
          task.setTaskname(applicant.getFullName());
          task.setIdvalue(applicant.getApplicantId());
          task.setTasktype(Common.APPLICANT_IN_QUEUE);
          task.setAssignedbyUserId(applicant.getApplicantId());
          task.setAssignedbyUserName(applicant.getFullName());
          task.setAssignedtoUserId(touser.getUserId());
          task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
          task.setCreatedBy(applicant.getEmail());
          task.setCreatedDate(new Date());
          task.setStatus("A");
          task.setUuid(applicant.getUuid());
          task.setSuper_user_key(jobreq.getSuper_user_key());
          taskList.add(task);
        }
        this.tasktxdao.saveAllTask(taskList);
        TaskBO.sendTaskAssignEmail(applicant, taskList, applicant.getOfferdeclinedcomment());
      }
      applicant.setIsGroup("Y");
      applicant.setOwnerGroup(usrgrp);
    }
    else
    {
      TaskData task = new TaskData();
      task.setTaskname(applicant.getFullName());
      task.setIdvalue(applicant.getApplicantId());
      task.setTasktype(Common.APPLICANT_IN_QUEUE);
      task.setAssignedbyUserId(applicant.getApplicantId());
      task.setAssignedbyUserName(applicant.getFullName());
      task.setAssignedtoUserId(applicant.getOwner().getUserId());
      User touser = this.usertxdao.getUser(jobreq.getRecruiterId());
      task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
      task.setCreatedBy(applicant.getEmail());
      task.setCreatedDate(new Date());
      task.setStatus("A");
      task.setUuid(applicant.getUuid());
      task.setSuper_user_key(jobreq.getSuper_user_key());
      
      this.tasktxdao.saveTask(task);
      TaskBO.sendTaskAssignEmail(applicant, task, applicant.getOfferdeclinedcomment());
      
      applicant.setIsGroup("N");
      applicant.setOwner(touser);
    }
    this.applicanttxdao.updateApplicant(applicant);
    this.jobreqtxdao.updateJobRequistion(jobreq);
    
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(applicant.getEmail());
    activity.setUserId(applicant.getApplicantId());
    activity.setUserFullName(applicant.getFullName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment(applicant.getOfferdeclinedcomment());
    activity.setCreatedByType(addedbytype);
    activity.setActivityName(applicant.getInterviewState());
    this.applicanttxdao.saveApplicantActivity(activity);
    


    TaskBO.sendEventEmail(applicant, null, applicant.getOfferdeclinedcomment(), "Offer Declined", false, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString());
  }
  
  public void offermodifficationrequestOnBehalf(JobApplicant applicant, User user1)
  {
    logger.info("Inside offermodifficationrequestOnBehalf method");
    



    JobRequisition jobreq = this.jobreqtxdao.getJobRequision(applicant.getReqId());
    if (applicant.getOfferownerId() != 0L)
    {
      TaskData task = new TaskData();
      task.setTaskname(applicant.getFullName());
      task.setIdvalue(applicant.getApplicantId());
      task.setTasktype(Common.APPLICANT_IN_QUEUE);
      task.setAssignedbyUserId(applicant.getOfferownerId());
      task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
      task.setAssignedtoUserId(applicant.getOwner().getUserId());
      User touser = this.usertxdao.getUser(applicant.getOfferownerId());
      task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
      task.setCreatedBy(user1.getUserName());
      task.setCreatedDate(new Date());
      task.setStatus("A");
      task.setUuid(applicant.getUuid());
      task.setSuper_user_key(jobreq.getSuper_user_key());
      
      this.tasktxdao.saveTask(task);
      TaskBO.sendTaskAssignEmail(user1, task, applicant.getChangeofferComment());
      
      applicant.setIsGroup("N");
      applicant.setOwner(touser);
    }
    else
    {
      if ((!StringUtils.isEmpty(jobreq.getIsgrouprecruiter())) && (jobreq.getIsgrouprecruiter().equals("Y")))
      {
        long groupId = 0L;
        
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(jobreq.getRecruiterId());
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData task = new TaskData();
            task.setTaskname(applicant.getFullName());
            task.setIdvalue(applicant.getApplicantId());
            task.setTasktype(Common.APPLICANT_IN_QUEUE);
            task.setAssignedbyUserId(user1.getUserId());
            task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
            task.setAssignedtoUserId(touser.getUserId());
            task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            task.setCreatedBy(user1.getUserName());
            task.setCreatedDate(new Date());
            task.setStatus("A");
            task.setUuid(applicant.getUuid());
            task.setSuper_user_key(jobreq.getSuper_user_key());
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
          TaskBO.sendTaskAssignEmail(user1, taskList, applicant.getChangeofferComment());
        }
        applicant.setIsGroup("Y");
        applicant.setOwnerGroup(usrgrp);
      }
      else
      {
        TaskData task = new TaskData();
        task.setTaskname(applicant.getFullName());
        task.setIdvalue(applicant.getApplicantId());
        task.setTasktype(Common.APPLICANT_IN_QUEUE);
        task.setAssignedbyUserId(user1.getUserId());
        task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
        task.setAssignedtoUserId(applicant.getOwner().getUserId());
        User touser = this.usertxdao.getUser(jobreq.getRecruiterId());
        task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
        task.setCreatedBy(user1.getUserName());
        task.setCreatedDate(new Date());
        task.setStatus("A");
        task.setUuid(applicant.getUuid());
        task.setSuper_user_key(jobreq.getSuper_user_key());
        
        this.tasktxdao.saveTask(task);
        TaskBO.sendTaskAssignEmail(user1, task, applicant.getChangeofferComment());
        
        applicant.setIsGroup("N");
        applicant.setOwner(touser);
      }
      TaskBO.sendEventEmail(applicant, user1, applicant.getChangeofferComment(), "Offer Modification Request", true, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString());
    }
    this.applicanttxdao.updateApplicant(applicant);
    

    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment(applicant.getChangeofferComment());
    activity.setCreatedByType(null);
    activity.setIsDisplayAppPage("Y");
    activity.setActivityName(applicant.getInterviewState() + " - " + "On behalf of");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public JobApplicant offermodifficationrequest(JobApplicant applicant, String addedbytype)
  {
    logger.info("Inside offermodifficationrequest method");
    




    JobRequisition jobreq = this.jobreqtxdao.getJobRequision(applicant.getReqId());
    if (applicant.getOfferownerId() != 0L)
    {
      TaskData task = new TaskData();
      task.setTaskname(applicant.getFullName());
      task.setIdvalue(applicant.getApplicantId());
      task.setTasktype(Common.APPLICANT_IN_QUEUE);
      task.setAssignedbyUserId(applicant.getApplicantId());
      task.setAssignedbyUserName(applicant.getFullName());
      task.setAssignedtoUserId(applicant.getOwner().getUserId());
      User touser = this.usertxdao.getUser(applicant.getOfferownerId());
      task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
      task.setCreatedBy(applicant.getEmail());
      task.setCreatedDate(new Date());
      task.setStatus("A");
      task.setUuid(applicant.getUuid());
      task.setSuper_user_key(jobreq.getSuper_user_key());
      
      this.tasktxdao.saveTask(task);
      TaskBO.sendTaskAssignEmail(applicant, task, applicant.getChangeofferComment());
      
      applicant.setIsGroup("N");
      applicant.setOwner(touser);
    }
    else if ((!StringUtils.isEmpty(jobreq.getIsgrouprecruiter())) && (jobreq.getIsgrouprecruiter().equals("Y")))
    {
      long groupId = 0L;
      
      UserGroup usrgrp = this.lovopstxdao.getUserGroup(jobreq.getRecruiterId());
      Set users = usrgrp.getUsers();
      List<TaskData> taskList = new ArrayList();
      if ((users != null) && (users.size() > 0))
      {
        Iterator itr = users.iterator();
        while (itr.hasNext())
        {
          User touser = (User)itr.next();
          
          TaskData task = new TaskData();
          task.setTaskname(applicant.getFullName());
          task.setIdvalue(applicant.getApplicantId());
          task.setTasktype(Common.APPLICANT_IN_QUEUE);
          task.setAssignedbyUserId(applicant.getApplicantId());
          task.setAssignedbyUserName(applicant.getFullName());
          task.setAssignedtoUserId(touser.getUserId());
          task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
          task.setCreatedBy(applicant.getEmail());
          task.setCreatedDate(new Date());
          task.setStatus("A");
          task.setUuid(applicant.getUuid());
          task.setSuper_user_key(jobreq.getSuper_user_key());
          taskList.add(task);
        }
        this.tasktxdao.saveAllTask(taskList);
        TaskBO.sendTaskAssignEmail(applicant, taskList, applicant.getChangeofferComment());
      }
      applicant.setIsGroup("Y");
      applicant.setOwnerGroup(usrgrp);
    }
    else
    {
      TaskData task = new TaskData();
      task.setTaskname(applicant.getFullName());
      task.setIdvalue(applicant.getApplicantId());
      task.setTasktype(Common.APPLICANT_IN_QUEUE);
      task.setAssignedbyUserId(applicant.getApplicantId());
      task.setAssignedbyUserName(applicant.getFullName());
      task.setAssignedtoUserId(applicant.getOwner().getUserId());
      User touser = this.usertxdao.getUser(jobreq.getRecruiterId());
      task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
      task.setCreatedBy(applicant.getEmail());
      task.setCreatedDate(new Date());
      task.setStatus("A");
      task.setUuid(applicant.getUuid());
      task.setSuper_user_key(jobreq.getSuper_user_key());
      
      this.tasktxdao.saveTask(task);
      TaskBO.sendTaskAssignEmail(applicant, task, applicant.getChangeofferComment());
      
      applicant.setIsGroup("N");
      applicant.setOwner(touser);
    }
    this.applicanttxdao.updateApplicant(applicant);
    
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(applicant.getEmail());
    activity.setUserId(applicant.getApplicantId());
    activity.setUserFullName(applicant.getFullName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setCreatedByType(addedbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setComment(applicant.getChangeofferComment());
    this.applicanttxdao.saveApplicantActivity(activity);
    
    TaskBO.sendEventEmail(applicant, null, applicant.getChangeofferComment(), "Offer Modification Request", true, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString());
    

    return applicant;
  }
  
  public void declineofferOnBehalf(JobApplicant applicant, JobRequisition jobreq, User user1)
  {
    logger.info("Inside declineofferOnBehalf method");
    if ((!StringUtils.isEmpty(jobreq.getIsgrouprecruiter())) && (jobreq.getIsgrouprecruiter().equals("Y")))
    {
      long groupId = 0L;
      
      UserGroup usrgrp = this.lovopstxdao.getUserGroup(jobreq.getRecruiterId());
      Set users = usrgrp.getUsers();
      List<TaskData> taskList = new ArrayList();
      if ((users != null) && (users.size() > 0))
      {
        Iterator itr = users.iterator();
        while (itr.hasNext())
        {
          User touser = (User)itr.next();
          
          TaskData task = new TaskData();
          task.setTaskname(applicant.getFullName());
          task.setIdvalue(applicant.getApplicantId());
          task.setTasktype(Common.APPLICANT_IN_QUEUE);
          task.setAssignedbyUserId(user1.getUserId());
          task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
          task.setAssignedtoUserId(touser.getUserId());
          task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
          task.setCreatedBy(user1.getUserName());
          task.setCreatedDate(new Date());
          task.setStatus("A");
          task.setUuid(applicant.getUuid());
          task.setSuper_user_key(jobreq.getSuper_user_key());
          taskList.add(task);
        }
        this.tasktxdao.saveAllTask(taskList);
        TaskBO.sendTaskAssignEmail(user1, taskList, applicant.getOfferdeclinedcomment());
      }
      applicant.setIsGroup("Y");
      applicant.setOwnerGroup(usrgrp);
    }
    else
    {
      TaskData task = new TaskData();
      task.setTaskname(applicant.getFullName());
      task.setIdvalue(applicant.getApplicantId());
      task.setTasktype(Common.APPLICANT_IN_QUEUE);
      task.setAssignedbyUserId(applicant.getApplicantId());
      task.setAssignedbyUserId(user1.getUserId());
      task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
      User touser = this.usertxdao.getUser(jobreq.getRecruiterId());
      task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
      task.setCreatedBy(user1.getUserName());
      task.setCreatedDate(new Date());
      task.setStatus("A");
      task.setUuid(applicant.getUuid());
      task.setSuper_user_key(jobreq.getSuper_user_key());
      
      this.tasktxdao.saveTask(task);
      TaskBO.sendTaskAssignEmail(user1, task, applicant.getOfferdeclinedcomment());
      
      applicant.setIsGroup("N");
      applicant.setOwner(touser);
    }
    this.applicanttxdao.updateApplicant(applicant);
    this.jobreqtxdao.updateJobRequistion(jobreq);
    
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment(applicant.getOfferdeclinedcomment());
    activity.setCreatedByType(null);
    activity.setIsDisplayAppPage("Y");
    activity.setActivityName(applicant.getInterviewState() + " - " + "On behalf of");
    this.applicanttxdao.saveApplicantActivity(activity);
    

    TaskBO.sendEventEmail(applicant, user1, applicant.getOfferdeclinedcomment(), "Offer Declined", true, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString());
  }
  
  public JobApplicant saveApplicant(JobApplicant applicant, User user1, User agency, RefferalEmployee refEmployee, String refuserid, String createdbytype)
  {
    logger.info("Inside saveApplicant method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    
    this.applicanttxdao.saveApplicant(applicant);
    
    setCurrentOwner(applicant);
    
    String attachmentCreatedBy = "";
    if (attach != null)
    {
      attach.setApplicantId(applicant.getApplicantId());
      attach.setAppuuid(applicant.getUuid());
      attach.setCreatedBy(applicant.getCreatedBy());
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    if (applicant.getOtherdetails() != null)
    {
      ApplicantOtherDetails otherdetails = applicant.getOtherdetails();
      otherdetails.setApplicantId(applicant.getApplicantId());
      this.applicanttxdao.deleteApplicantOtherDetails(applicant.getApplicantId());
      this.applicanttxdao.saveApplicantOtherDetails(otherdetails);
    }
    addApplicantToRedemptionOnSubmit(applicant);
    if (user1 != null) {
      setApplicantActivity(applicant, user1, createdbytype);
    } else if (agency != null) {
      setApplicantActivityAgency(applicant, agency, createdbytype);
    } else if (refEmployee != null) {
      setApplicantActivityReferral(applicant, refEmployee, createdbytype);
    } else if (!refuserid.equals("external-jobpost")) {}
    return applicant;
  }
  
  private void addApplicantToRedemptionOnSubmit(JobApplicant applicant)
  {
    if ((applicant.getResumesourcetype() != null) && ((applicant.getResumesourcetype().getResumeSourceTypeId() == 1) || (applicant.getResumesourcetype().getResumeSourceTypeId() == 5))) {
      if ((BOFactory.getJobRequistionBO().isRedemptionRuleAppliedToAgency(applicant.getReqId(), "AFTER-UPLOAD")) || (BOFactory.getJobRequistionBO().isRedemptionRuleAppliedToEmployeeRef(applicant.getReqId(), "AFTER-UPLOAD")))
      {
        ReferralAgencyApplicants refagenctapplicant = new ReferralAgencyApplicants();
        refagenctapplicant.setApplicantId(applicant.getApplicantId());
        refagenctapplicant.setApplicantName(applicant.getFullName());
        refagenctapplicant.setResumeSourceId(applicant.getResumesourcetype().getResumeSourceTypeId());
        
        refagenctapplicant.setJobReqId(applicant.getReqId());
        refagenctapplicant.setJobTitle(applicant.getJobTitle());
        refagenctapplicant.setStatus("A");
        refagenctapplicant.setState(applicant.getInterviewState());
        refagenctapplicant.setCreatedDate(new Date());
        refagenctapplicant.setEventdate(new Date());
        



        addApplicantForReferralAndAgencyTracking(refagenctapplicant);
      }
    }
  }
  
  public void setConvertToUserApplicantActivity(long applicantId, User user1)
  {
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(applicantId));
    

    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
    activity.setActivityName("CONVERTED_TO_USER");
    
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  private void setApplicantActivity(JobApplicant applicant, User user1, String createdbytype)
  {
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  private void setApplicantActivityAgency(JobApplicant applicant, User agency, String createdbytype)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(agency.getUserName());
    activity.setUserId(agency.getUserId());
    activity.setUserFullName(agency.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  private void setApplicantActivityReferral(JobApplicant applicant, RefferalEmployee ref, String createdbytype)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(ref.getEmployeeemail());
    activity.setUserId(0L);
    activity.setUserFullName(ref.getEmployeename());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  private void setApplicantActivityExternalJobPost(JobApplicant applicant, String createdbytype)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(applicant.getEmail());
    activity.setUserId(0L);
    activity.setUserFullName(applicant.getFullName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public JobApplicant saveApplicantToTalentPool(JobApplicant applicant, User user1, String createdbytype, long talentpoolid)
  {
    logger.info("Inside saveApplicantToTalentPool method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    this.applicanttxdao.saveApplicant(applicant);
    if (attach != null)
    {
      attach.setApplicantId(applicant.getApplicantId());
      attach.setAppuuid(applicant.getUuid());
      attach.setCreatedBy(user1.getUserName());
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    if (applicant.getOtherdetails() != null)
    {
      ApplicantOtherDetails otherdetails = applicant.getOtherdetails();
      otherdetails.setApplicantId(applicant.getApplicantId());
      this.applicanttxdao.deleteApplicantOtherDetails(applicant.getApplicantId());
      this.applicanttxdao.saveApplicantOtherDetails(otherdetails);
    }
    logger.info("talentpoolid" + talentpoolid);
    if (applicant.getReqId() < 1L) {
      if (talentpoolid != 0L)
      {
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("talentpool.default.skills.folders"))) && (Constant.getValue("talentpool.default.skills.folders").equalsIgnoreCase("yes")))
        {
          TalentPoolElements te = this.talentpooltxdao.getTalentPoolElementBySkill(applicant.getPrimarySkill() == null ? "Others" : applicant.getPrimarySkill(), talentpoolid);
          if (te != null) {
            this.talentpooltxdao.insertTalentPoolElement(te.getId(), applicant.getFullName(), 1, applicant.getApplicantId(), talentpoolid);
          } else {
            this.talentpooltxdao.insertTalentPoolElement(talentpoolid, applicant.getFullName(), 1, applicant.getApplicantId(), talentpoolid);
          }
        }
        else
        {
          logger.info("inside else" + talentpoolid);
          this.talentpooltxdao.insertTalentPoolElement(talentpoolid, applicant.getFullName(), 1, applicant.getApplicantId(), talentpoolid);
        }
      }
      else
      {
        TalentPool tp = TalentPoolDAO.getDefaultTalentPools(applicant.getSuper_user_key());
        if (talentpoolid == 0L) {
          talentpoolid = tp.getTalentPoolId();
        }
        if (tp != null) {
          this.talentpooltxdao.insertTalentPoolElement(tp.getTalentPoolId(), applicant.getFullName(), 1, applicant.getApplicantId(), talentpoolid);
        }
      }
    }
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    activity.setIsDisplayAppPage("Y");
    if ((!StringUtils.isNullOrEmpty(applicant.getCreatedBy())) && (applicant.getCreatedBy().equals("emailreader")))
    {
      activity.setUserId(0L);
      activity.setUserFullName("emailreader");
      activity.setCreatedBy("emailreader");
    }
    this.applicanttxdao.saveApplicantActivity(activity);
    
    setCurrentOwner(applicant);
    
    return applicant;
  }
  
  public void setCurrentOwner(JobApplicant applicant)
  {
    JobRequisition jb = this.jobreqtxdao.getJobRequision(applicant.getReqId());
    if (jb != null)
    {
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("default.applicant.owner"))) && (Constant.getValue("default.applicant.owner").equalsIgnoreCase("recruiter")))
      {
        if (jb.getRecruiterId() > 0L)
        {
          if ((!StringUtils.isNullOrEmpty(jb.getIsgrouprecruiter())) && (jb.getIsgrouprecruiter().equals("Y")))
          {
            UserGroup ug = new UserGroup();
            ug.setUsergrpId(jb.getRecruiterId());
            applicant.setOwnerGroup(ug);
            applicant.setIsGroup("Y");
          }
          else
          {
            User owner = new User();
            owner.setUserId(jb.getRecruiterId());
            applicant.setOwner(owner);
            applicant.setIsGroup("N");
          }
        }
        else
        {
          applicant.setOwner(jb.getHiringmgr());
          applicant.setIsGroup("N");
        }
      }
      else
      {
        applicant.setOwner(jb.getHiringmgr());
        applicant.setIsGroup("N");
      }
      this.applicanttxdao.updateApplicant(applicant);
    }
  }
  
  public JobApplicant requistionChangedUpdate(JobApplicant applicant, User user1, boolean isReqChanged, long oldReqId, String oldJobTitle, JobRequisition jb)
  {
    if (isReqChanged)
    {
      setCurrentOwner(applicant);
      String comment = oldJobTitle + "-->" + jb.getJobTitle();
      ApplicantActivity activity = populateApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
      activity.setIsDisplayAppPage("Y");
      activity.setComment(comment);
      activity.setActivityName("REQ_TO_REQ_MOVE");
      this.applicanttxdao.saveApplicantActivity(activity);
    }
    return applicant;
  }
  
  public JobApplicant saveApplicantByEmaderailReader(JobApplicant applicant, String createdbytype)
  {
    logger.info("Inside saveApplicantByEmaderailReader method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    this.applicanttxdao.saveApplicant(applicant);
    
    ApplicantOtherDetails otherdetails = new ApplicantOtherDetails();
    otherdetails.setApplicantId(applicant.getApplicantId());
    this.applicanttxdao.deleteApplicantOtherDetails(applicant.getApplicantId());
    this.applicanttxdao.saveApplicantOtherDetails(otherdetails);
    if (attach != null)
    {
      attach.setApplicantId(applicant.getApplicantId());
      attach.setAppuuid(applicant.getUuid());
      attach.setCreatedBy("emailreader");
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy("emailreader");
    activity.setUserId(0L);
    activity.setUserFullName("emailreader");
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
    
    setCurrentOwner(applicant);
    return applicant;
  }
  
  public JobApplicant saveRefferalUserApplicant(JobApplicant applicant, String createdbytype, String refuserid)
  {
    logger.info("Inside saveRefferalUserApplicant method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    ResumeSourceType sourcetype = new ResumeSourceType();
    sourcetype.setResumeSourceTypeId(1);
    
    RefferalEmployee refEmp = this.lovopstxdao.getRefferalEmployee(refuserid);
    
    applicant.setReferrerEmail(refEmp.getEmployeeemail());
    applicant.setReferrerName(refEmp.getEmployeename());
    applicant.setEmployeecode(refEmp.getEmployeecode());
    
    applicant.setResumesourcetype(sourcetype);
    


    this.applicanttxdao.saveApplicant(applicant);
    logger.info("applicant : " + applicant);
    if (attach != null)
    {
      attach.setApplicantId(applicant.getApplicantId());
      attach.setAppuuid(applicant.getUuid());
      attach.setCreatedBy("external");
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      

      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    logger.info("Inside saveRefferalUserApplicant method ... 1");
    
    setCurrentOwner(applicant);
    
    return applicant;
  }
  
  public JobApplicant acceptandsubmitRefUserData(JobApplicant applicant, String createdbytype, String refuserid)
  {
    logger.info("Inside acceptandsubmitRefUserData method");
    if (applicant.getReqId() > 0L)
    {
      applicant.setStatus("A");
      applicant.setInterviewState("Application Submitted");
    }
    this.applicanttxdao.updateApplicant(applicant);
    

    logger.info("Inside saveRefferalUserApplicant method ... 1");
    
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    
    activity.setCreatedBy("external");
    activity.setUserId(0L);
    activity.setUserFullName("external");
    logger.info("Inside saveRefferalUserApplicant method ... 2");
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
    if (applicant.getReqId() < 1L)
    {
      TalentPool tpool = null;
      if ((refuserid != null) && (refuserid.equals("external-jobpost")))
      {
        tpool = null;
      }
      else
      {
        RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
        
        tpool = this.talentpooltxdao.getTalentpoolByOrganization(refEmp.getOrganization().getOrgId());
        



        logger.info("tpool name" + tpool);
        logger.info("applicant.getFullName()" + applicant.getFullName());
        logger.info("tpool name" + tpool);
        if (tpool != null)
        {
          if ((!StringUtils.isNullOrEmpty(Constant.getValue("talentpool.default.skills.folders"))) && (Constant.getValue("talentpool.default.skills.folders").equalsIgnoreCase("yes")))
          {
            logger.info("tpool name" + tpool.getTalentPoolId());
            logger.info("applicant.getPrimarySkill()" + applicant.getPrimarySkill());
            TalentPoolElements te = this.talentpooltxdao.getTalentPoolElementBySkill(applicant.getPrimarySkill() == null ? "Others" : applicant.getPrimarySkill(), tpool.getTalentPoolId());
            if (te != null) {
              this.talentpooltxdao.insertTalentPoolElement(te.getId(), applicant.getFullName(), 1, applicant.getApplicantId(), tpool.getTalentPoolId());
            } else {
              this.talentpooltxdao.insertTalentPoolElement(tpool.getTalentPoolId(), applicant.getFullName(), 1, applicant.getApplicantId(), tpool.getTalentPoolId());
            }
          }
          else
          {
            this.talentpooltxdao.insertTalentPoolElement(tpool.getTalentPoolId(), applicant.getFullName(), 1, applicant.getApplicantId(), tpool.getTalentPoolId());
          }
        }
        else {
          this.talentpooltxdao.insertTalentPoolElement(9999999L, applicant.getFullName(), 1, applicant.getApplicantId(), tpool.getTalentPoolId());
        }
      }
    }
    setCurrentOwner(applicant);
    return applicant;
  }
  
  public JobApplicant updateRefferalUserApplicant(JobApplicant applicant, String createdbytype, String refuserid)
  {
    logger.info("Inside updateRefferalUserApplicant method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    ResumeSourceType sourcetype = new ResumeSourceType();
    sourcetype.setResumeSourceTypeId(1);
    
    applicant.setResumesourcetype(sourcetype);
    this.applicanttxdao.updateApplicant(applicant);
    logger.info("applicant : " + applicant);
    if (attach != null)
    {
      attach.setApplicantId(applicant.getApplicantId());
      attach.setAppuuid(applicant.getUuid());
      attach.setCreatedBy("external");
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      

      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    return applicant;
  }
  
  public JobApplicant saveApplicantNoLogin(JobApplicant applicant, String createdbytype)
  {
    logger.info("Inside saveApplicantNoLogin method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    this.applicanttxdao.saveApplicant(applicant);
    
    attach.setApplicantId(applicant.getApplicantId());
    attach.setAppuuid(applicant.getUuid());
    if (attach != null)
    {
      attach.setCreatedBy("external");
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      

      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    
    activity.setCreatedBy("external");
    activity.setUserId(0L);
    activity.setUserFullName("external");
    
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
    
    setCurrentOwner(applicant);
    return applicant;
  }
  
  public JobApplicant updateApplicant(JobApplicant applicant, boolean isAttachmentupdated)
  {
    logger.info("Inside updateApplicant method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    

    applicant.setIsindexSearchApplied(0);
    if (applicant.getReqId() == 0L)
    {
      applicant.setStatus("T");
      if ((applicant.getInterviewState() != null) && (applicant.getInterviewState().equals("Talent Pool Temp"))) {
        applicant.setInterviewState("Talent Pool Temp");
      } else {
        applicant.setInterviewState("Talent Pool");
      }
    }
    this.applicanttxdao.updateApplicant(applicant);
    if ((isAttachmentupdated) && (attach != null))
    {
      this.applicanttxdao.deleteApplicantAttachment(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      attach.setApplicantId(applicant.getApplicantId());
      attach.setAppuuid(applicant.getUuid());
      attach.setCreatedBy(applicant.getUpdatedBy());
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    if (applicant.getOtherdetails() != null)
    {
      ApplicantOtherDetails otherdetails = applicant.getOtherdetails();
      otherdetails.setApplicantId(applicant.getApplicantId());
      this.applicanttxdao.deleteApplicantOtherDetails(applicant.getApplicantId());
      this.applicanttxdao.saveApplicantOtherDetails(otherdetails);
    }
    return applicant;
  }
  
  public JobApplicant moveToTalentpoolORrequistion(JobApplicant applicant, User user1, String comment, boolean isTalentPoolMove, String type, long oldReqId, String oldJobTitle)
  {
    String subject = "";
    if (isTalentPoolMove)
    {
      applicant.setJobTitle(null);
      applicant.setReqName(null);
      applicant.setReqId(0L);
      applicant.setStatus("T");
      applicant.setInterviewState("Talent Pool");
      applicant.setUpdatedBy(user1.getUserName());
      applicant.setUpdatedDate(new Date());
      
      applicant.setOwner(user1);
      applicant.setIsGroup("N");
      this.applicanttxdao.updateApplicant(applicant);
      
      TalentPoolElements te = this.talentpooltxdao.getTalentPoolElementByApplicantId(applicant.getApplicantId());
      
      String oldtalentpoolname = "";
      if ((type != null) && (type.equals("TALENT_POOL_TO_TALENT_POOL_MOVE")))
      {
        TalentPool tpold = this.talentpooltxdao.getTalentPoolByApplicantId(applicant.getApplicantId());
        oldtalentpoolname = tpold == null ? "" : tpold.getTalentPoolName();
      }
      if (te != null) {
        this.talentpooltxdao.deleteTalentPoolElements(String.valueOf(te.getId()), 0, String.valueOf(te.getOwnerId()));
      }
      this.talentpooltxdao.insertTalentPoolElement(applicant.getTalentpoolid(), applicant.getFullName(), 1, applicant.getApplicantId(), applicant.getTalentpoolid());
      


      setCurrentOwner(applicant);
      
      List<String> taskTypes = new ArrayList();
      taskTypes.add(Common.APPLICANT_REVIEW_TASK);
      taskTypes.add(Common.APPLICANT_INTERVIEW_TASK);
      taskTypes.add(Common.APPLICANT_IN_QUEUE);
      taskTypes.add(Common.APPLICANT_REVIEW_TASK);
      taskTypes.add(Common.OFFER_APPROVAL_TASK);
      



      this.tasktxdao.markTaskComplete(applicant.getApplicantId(), taskTypes, user1.getUserName());
      
      TalentPool tp = this.talentpooltxdao.getTalentPoolByApplicantId(applicant.getApplicantId());
      
      String newtalentpoolname = tp == null ? "" : tp.getTalentPoolName();
      if ((type != null) && (type.equals("REQ_TO_TALENT_POOL_MOVE")))
      {
        comment = oldJobTitle + " --> " + newtalentpoolname + "\n" + comment;
        subject = oldJobTitle + " --> " + newtalentpoolname;
      }
      else if ((type != null) && (type.equals("TALENT_POOL_TO_TALENT_POOL_MOVE")))
      {
        comment = oldtalentpoolname + " --> " + newtalentpoolname + "\n" + comment;
        subject = oldtalentpoolname + " --> " + newtalentpoolname;
      }
      ApplicantActivity activity = populateApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
      activity.setIsDisplayAppPage("Y");
      activity.setComment(comment);
      activity.setActivityName(type);
      this.applicanttxdao.saveApplicantActivity(activity);
    }
    else
    {
      if ((type != null) && ((type.equals("REQ_TO_REQ_MOVE")) || (type.equals("TALENT_POOL_TO_REQ_MOVE"))))
      {
        TalentPoolElements te = this.talentpooltxdao.getTalentPoolElementByApplicantId(applicant.getApplicantId());
        
        String oldtalentpoolname = "";
        if ((type != null) && (type.equals("TALENT_POOL_TO_REQ_MOVE")))
        {
          TalentPool tpold = this.talentpooltxdao.getTalentPoolByApplicantId(applicant.getApplicantId());
          oldtalentpoolname = tpold == null ? "" : tpold.getTalentPoolName();
        }
        if (te != null) {
          this.talentpooltxdao.deleteTalentPoolElements(String.valueOf(te.getId()), 0, String.valueOf(te.getOwnerId()));
        }
        JobRequisition jb = this.jobreqtxdao.getJobRequision(applicant.getReqId());
        applicant.setJobTitle(jb.getJobTitle());
        applicant.setReqName(jb.getJobreqName());
        applicant.setStatus("A");
        if ((type == null) || (!type.equals("REQ_TO_REQ_MOVE"))) {
          applicant.setInterviewState("Application Submitted");
        }
        applicant.setUpdatedBy(user1.getUserName());
        applicant.setUpdatedDate(new Date());
        this.applicanttxdao.updateApplicant(applicant);
        
        setCurrentOwner(applicant);
        
        List<String> taskTypes = new ArrayList();
        taskTypes.add(Common.APPLICANT_REVIEW_TASK);
        taskTypes.add(Common.APPLICANT_INTERVIEW_TASK);
        taskTypes.add(Common.APPLICANT_IN_QUEUE);
        taskTypes.add(Common.APPLICANT_REVIEW_TASK);
        taskTypes.add(Common.OFFER_APPROVAL_TASK);
        

        this.tasktxdao.markTaskComplete(applicant.getApplicantId(), taskTypes, user1.getUserName());
        if ((type != null) && (type.equals("REQ_TO_REQ_MOVE")))
        {
          comment = oldJobTitle + " --> " + jb.getJobreqName() + "\n" + comment;
          subject = oldJobTitle + " --> " + jb.getJobreqName();
        }
        else if ((type != null) && (type.equals("TALENT_POOL_TO_REQ_MOVE")))
        {
          comment = oldtalentpoolname + " --> " + jb.getJobreqName() + "\n" + comment;
          subject = oldtalentpoolname + " --> " + jb.getJobreqName();
        }
        ApplicantActivity activity = populateApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
        activity.setIsDisplayAppPage("Y");
        activity.setComment(comment);
        activity.setActivityName(type);
        this.applicanttxdao.saveApplicantActivity(activity);
      }
      addApplicantToRedemptionOnSubmit(applicant);
    }
    try
    {
      sendMoveToEmail(applicant, user1, comment, subject, oldReqId);
    }
    catch (Exception e)
    {
      logger.info("error on sendMoveToEmail", e);
    }
    return applicant;
  }
  
  public void sendMoveToEmail(JobApplicant applicant, User user, String comment, String subject, long oldreqId)
    throws Exception
  {
    String[] to = { user.getEmailId() };
    



    List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user, applicant, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_MOVE.toString(), false);
    
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    

    EmailTask emailtask = new EmailTask(user.getEmailId(), to, cc, null, null, subject, null, "dummybody", null, 0, null);
    emailtask.setFunctionType(Common.APPLICANT_MOVE);
    emailtask.setUser(user);
    emailtask.setApplicant(applicant);
    emailtask.setComment(comment);
    EmailTaskManager.sendEmail(emailtask);
  }
  
  public JobApplicant updateApplicantTalentPool(JobApplicant applicant, User user1, boolean isAttachmentupdata)
  {
    if (applicant.getReqId() > 0L)
    {
      JobRequisition jbr = this.jobreqtxdao.getJobRequision(String.valueOf(applicant.getReqId()));
      
      applicant.setJobTitle(jbr.getJobTitle());
      applicant.setStatus("A");
      applicant.setInterviewState("Application Submitted");
    }
    TalentPoolElements te = this.talentpooltxdao.getTalentPoolElementByApplicantId(applicant.getApplicantId());
    if (te != null) {
      applicant.setTalentpoolid(te.getApplicantpoolidInitial());
    }
    logger.info("Inside updateApplicantTalentPool method");
    ApplicantAttachments attach = applicant.getApplicantAttachment();
    this.applicanttxdao.updateApplicant(applicant);
    if ((isAttachmentupdata) && (attach != null))
    {
      this.applicanttxdao.deleteApplicantAttachment(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      attach.setApplicantId(applicant.getApplicantId());
      attach.setAppuuid(applicant.getUuid());
      attach.setCreatedBy(user1.getUserName());
      attach.setCreatedDate(new Date());
      attach.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      this.applicanttxdao.saveApplicantAttachment(attach);
    }
    if (applicant.getOtherdetails() != null)
    {
      ApplicantOtherDetails otherdetails = applicant.getOtherdetails();
      otherdetails.setApplicantId(applicant.getApplicantId());
      this.applicanttxdao.deleteApplicantOtherDetails(applicant.getApplicantId());
      this.applicanttxdao.saveApplicantOtherDetails(otherdetails);
    }
    if ((!StringUtils.isNullOrEmpty(te.getName())) && (!te.getName().equals(applicant.getFullName())))
    {
      te.setName(applicant.getFullName());
      this.talentpooltxdao.updateTalentPoolElement(te);
    }
    if (applicant.getReqId() > 0L)
    {
      String oldtalentpoolname = "";
      
      TalentPool tpold = this.talentpooltxdao.getTalentPoolByApplicantId(applicant.getApplicantId());
      oldtalentpoolname = tpold == null ? "" : tpold.getTalentPoolName();
      
      String comment = oldtalentpoolname + "-->" + applicant.getJobTitle();
      

      this.talentpooltxdao.deleteTalentPoolElements(String.valueOf(te.getId()), 0, String.valueOf(te.getOwnerId()));
      

      ApplicantActivity activity = populateApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
      activity.setIsDisplayAppPage("Y");
      activity.setComment(comment);
      activity.setActivityName("TALENT_POOL_TO_REQ_MOVE");
      this.applicanttxdao.saveApplicantActivity(activity);
    }
    setCurrentOwner(applicant);
    return applicant;
  }
  
  public JobApplicant updateApplicantforPassword(JobApplicant applicant, ApplicantUser user1)
  {
    this.applicanttxdao.updateApplicant(applicant);
    logger.info("Inside updateApplicantforPassword method");
    




    return applicant;
  }
  
  public JobApplicant addapplicanttag(JobApplicant applicant, User user1, String tagName, String createdbytype)
  {
    logger.info("Inside addapplicanttag method");
    this.applicanttxdao.updateApplicant(applicant);
    


    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    
    activity.setActivityName("Applicant tagged");
    activity.setComment(tagName);
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public void addTagActivity(JobApplicant applicant, User user1, String tagName, String activityname, String createdbytype)
  {
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    
    activity.setActivityName(activityname);
    activity.setComment(tagName);
    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public JobApplicant addapplicantactions(JobApplicant applicant, ApplicantUserActions action, User user1, String createdbytype)
  {
    logger.info("Inside addapplicantactions method");
    
    this.applicanttxdao.addapplicantaction(action);
    
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    
    activity.setActivityName("Applicant action added");
    activity.setIsDisplayAppPage("N");
    activity.setComment(action.getCreatorComment());
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public JobApplicant updateapplicantactions(JobApplicant applicant, ApplicantUserActions action, User user1, String createdbytype)
  {
    logger.info("Inside updateapplicantactions method");
    
    this.applicanttxdao.updateapplicantaction(action);
    
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    
    activity.setActivityName("Applicant action updated");
    activity.setIsDisplayAppPage("N");
    activity.setComment(action.getCreatorComment());
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public JobApplicant deleteapplicantactions(JobApplicant applicant, ApplicantUserActions action, User user1, String createdbytype)
  {
    logger.info("Inside deleteapplicantactions method");
    
    this.applicanttxdao.deleteapplicantaction(action);
    
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    
    activity.setActivityName("Applicant action deleted");
    activity.setIsDisplayAppPage("N");
    activity.setComment(action.getCreatorComment());
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public ApplicantUser saveApplicantUser(JobApplicant applicant, ApplicantUser appuser, User user1, String createdbytype)
  {
    logger.info("Inside saveApplicantUser method");
    this.applicanttxdao.saveApplicantUser(appuser);
    

    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    
    activity.setActivityName("Applicant user created");
    this.applicanttxdao.saveApplicantActivity(activity);
    return appuser;
  }
  
  public ApplicantUser updateApplicantUser(JobApplicant applicant, ApplicantUser appuser, User user1, String createdbytype)
  {
    logger.info("Inside updateApplicantUser method");
    this.applicanttxdao.updateApplicantUser(appuser);
    

    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    
    activity.setActivityName("Applicant user created");
    this.applicanttxdao.saveApplicantActivity(activity);
    return appuser;
  }
  
  public ApplicantUser updateApplicantUserForPassword(ApplicantUser appuser)
  {
    logger.info("Inside updateApplicantUserForPassword method");
    this.applicanttxdao.updateApplicantUser(appuser);
    





    return appuser;
  }
  
  public JobApplicant recallapplicant(JobApplicant applicant, JobApplicationEvent event, User user1, String comment, String createdbytype)
  {
    logger.info("Inside recallapplicant method");
    this.applicanttxdao.updateApplicant(applicant);
    this.applicanttxdao.updateInterviewComments(event, applicant);
    this.applicanttxdao.deleteApplicantRatingList(applicant.getApplicantId(), event.getJobAppEventId());
    

    TaskData taskold = this.tasktxdao.getLastCompleteTask(applicant.getApplicantId(), Common.APPLICANT_REVIEW_TASK);
    
    this.tasktxdao.markTaskActive(taskold);
    


    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    activity.setActivityName(applicant.getInterviewState() + "-" + "Recalled");
    activity.setComment(comment);
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public JobApplicant recallapplicantOnDragdrop(JobApplicant applicant, ApplicantActivity activitylog, User user1, String comment, String createdbytype)
  {
    logger.info("Inside recallapplicantOnDragdrop method");
    



    TaskData taskold = this.tasktxdao.getLastCompleteTask(applicant.getApplicantId(), Common.APPLICANT_REVIEW_TASK);
    if (taskold != null)
    {
      this.tasktxdao.markTaskActive(taskold);
      User owner = new User();
      owner.setUserId(taskold.getAssignedtoUserId());
      applicant.setOwner(owner);
      applicant.setIsGroup("N");
    }
    else
    {
      User owner = new User();
      owner.setUserId(activitylog.getUserId());
      applicant.setOwner(owner);
      applicant.setIsGroup("N");
    }
    this.applicanttxdao.updateApplicant(applicant);
    


    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    activity.setActivityName(applicant.getInterviewState() + "-" + "Recalled");
    activity.setComment(comment);
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public JobApplicant undodelete(JobApplicant applicant, User user1, String comment, String createdbytype)
  {
    logger.info("Inside undodelete method");
    this.applicanttxdao.updateApplicant(applicant);
    
    List<String> tasktypes = new ArrayList();
    tasktypes.add(Common.APPLICANT_REVIEW_TASK);
    tasktypes.add(Common.OFFER_APPROVAL_TASK);
    tasktypes.add(Common.OFFER_RELEASE_TASK);
    tasktypes.add(Common.APPLICANT_IN_QUEUE);
    tasktypes.add(Common.ONBOARDING_TASK);
    
    TaskData taskold = this.tasktxdao.getLastCompleteTask(applicant.getApplicantId(), tasktypes);
    if (taskold != null) {
      this.tasktxdao.markTaskActive(taskold);
    }
    taskold = this.tasktxdao.getLastCompleteTask(applicant.getApplicantId(), Common.REFERENCE_CHECK_TASK);
    if (taskold != null) {
      this.tasktxdao.markTaskActive(taskold);
    }
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    activity.setActivityName("Applicant restored");
    activity.setComment(comment);
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
    try
    {
      markForUndoDelete(applicant, user1, comment);
    }
    catch (Exception e)
    {
      logger.info("error on markForUndoDelete", e);
    }
    return applicant;
  }
  
  public void markForUndoDelete(JobApplicant applicant, User user, String comment)
    throws Exception
  {
    String[] to = { user.getEmailId() };
    




    List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user, applicant, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_MARK_UNDO_DELETE.toString(), false);
    
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    

    EmailTask emailtask = new EmailTask(user.getEmailId(), to, cc, null, null, "dummysubject", null, "dummybody", null, 0, null);
    emailtask.setFunctionType(Common.APPLICANT_MARK_UNDO_DELETE);
    emailtask.setUser(user);
    emailtask.setApplicant(applicant);
    emailtask.setComment(comment);
    EmailTaskManager.sendEmail(emailtask);
  }
  
  public JobApplicant markfordeletion(JobApplicant applicant, User user1, String comment, String createdbytype)
  {
    logger.info("Inside markfordeletion method");
    this.applicanttxdao.updateApplicant(applicant);
    
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.APPLICANT_REVIEW_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.APPLICANT_INTERVIEW_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.OFFER_APPROVAL_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.REFERENCE_CHECK_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.OFFER_RELEASE_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.APPLICANT_IN_QUEUE);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.ONBOARDING_TASK);
    



    ApplicantActivity activity = populateApplicantActivity(applicant, user1, createdbytype);
    activity.setActivityName("Applicant marked for deletion");
    activity.setComment(comment);
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public void markfordeletionbulk(List applicaantList, User user1, String comment, String createdbytype)
  {
    logger.info("Inside markfordeletionbulk method");
    logger.info("applicaantList size" + applicaantList.size());
    
    String applicantids = "";
    for (int i = 0; i < applicaantList.size(); i++)
    {
      String uuid = (String)applicaantList.get(i);
      applicantids = applicantids + "'" + uuid + "'" + ",";
    }
    if (!StringUtils.isNullOrEmpty(applicantids)) {
      applicantids = applicantids.substring(0, applicantids.length() - 1);
    }
    String sql = "update job_applications set status='D' , updated_by= '" + user1.getUserName() + "'" + " , updated_date=sysdate(), interview_state=(select CONCAT('Mark deleted-', interview_state) as interview_state)  where " + "uuid in ( " + applicantids + ") ";
    

    logger.info("sql" + sql);
    
    logger.info("sql" + sql);
  }
  
  public void deleteapplicant(JobApplicant applicant)
  {
    logger.info("Inside deleteapplicant method");
    

    this.applicanttxdao.deleteApplicant(applicant.getApplicantId());
    


    this.applicanttxdao.deleteOfferApprovers(applicant.getApplicantId());
    


    this.applicanttxdao.deleteOfferAttachments(applicant.getApplicantId());
    


    this.applicanttxdao.deleteOfferWatchList(applicant.getApplicantId());
    


    this.applicanttxdao.deleteInterviewWatchList(applicant.getApplicantId());
    


    this.applicanttxdao.deleteApplicantReferencee(applicant.getApplicantId());
    


    this.applicanttxdao.deleteQuestionAnswers(applicant.getApplicantId());
    


    this.applicanttxdao.deleteApplicantRating(applicant.getApplicantId());
    


    this.applicanttxdao.deleteEducationDetails(applicant.getApplicantId());
    


    this.applicanttxdao.deletePreviousOrgDetails(applicant.getApplicantId());
    


    this.applicanttxdao.deleteApplicantSkills(applicant.getApplicantId());
    


    this.applicanttxdao.deleteReferralAgencyApplicants(applicant.getApplicantId());
    


    this.applicanttxdao.deleteOnboarding(applicant.getApplicantId());
    


    this.applicanttxdao.deleteOnBoardingTask(applicant.getApplicantId());
    


    this.applicanttxdao.deleteApplicantUserActions(applicant.getApplicantId());
    


    this.applicanttxdao.deleteApplicantUser(applicant.getApplicantId());
    


    this.applicanttxdao.deleteActionsAttachment(applicant.getApplicantId());
    


    this.applicanttxdao.deleteApplicantAttachments(applicant.getApplicantId());
    



    this.applicanttxdao.deleteApplicantComment(applicant.getApplicantId());
    



    this.applicanttxdao.deleteJobApplicationEvent(applicant.getApplicantId());
    


    this.applicanttxdao.deleteApplicantActivity(applicant.getApplicantId());
    

    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.APPLICANT_REVIEW_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.APPLICANT_INTERVIEW_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.OFFER_APPROVAL_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.REFERENCE_CHECK_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.OFFER_RELEASE_TASK);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.APPLICANT_IN_QUEUE);
    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.ONBOARDING_TASK);
  }
  
  public JobApplicant acceptoffer(JobApplicant applicant, String createdbytype)
  {
    logger.info("Inside acceptoffer method");
    this.applicanttxdao.updateApplicant(applicant);
    


    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(applicant.getEmail());
    activity.setUserId(applicant.getApplicantId());
    activity.setUserFullName(applicant.getFullName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment(applicant.getOfferacceptedcomment());
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    activity.setIsDisplayAppPage("Y");
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public JobApplicant acceptofferOnBehalf(JobApplicant applicant, User user1)
  {
    logger.info("Inside acceptofferOnBehalf method");
    this.applicanttxdao.updateApplicant(applicant);
    


    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment(applicant.getOfferacceptedcomment());
    activity.setCreatedByType(null);
    activity.setIsDisplayAppPage("Y");
    activity.setActivityName(applicant.getInterviewState() + " - " + "On behalf of");
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  public JobApplicant markresigned(JobApplicant applicant, User user1)
  {
    logger.info("Inside markresigned method");
    this.applicanttxdao.updateApplicant(applicant);
    


    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment(applicant.getResignedcomment());
    activity.setCreatedByType(null);
    activity.setIsDisplayAppPage("Y");
    activity.setActivityName(applicant.getInterviewState());
    this.applicanttxdao.saveApplicantActivity(activity);
    return applicant;
  }
  
  private ApplicantActivity populateApplicantActivity(JobApplicant applicant, User user1, String createdbytype)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    
    return activity;
  }
  
  public void releaseoffer(JobApplicant applicant, JobRequisition jobreq, List watchList, ApplicantOfferAttachment appattach, User user1)
    throws Exception
  {
    logger.info("Inside releaseoffer transaction start method");
    try
    {
      this.applicanttxdao.updateApplicant(applicant);
      this.jobreqtxdao.updateJobRequistion(jobreq);
      this.applicanttxdao.deleteOfferWatchList(applicant.getApplicantId());
      this.applicanttxdao.addOfferWatchList(watchList);
      if (appattach != null) {
        BOFactory.getApplicantBO().saveOfferAttachment(appattach, applicant.getUuid(), user1.getUserId(), user1.getFirstName() + " " + user1.getLastName(), Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
      }
      TaskData task = this.tasktxdao.getTask(applicant.getApplicantId(), Common.OFFER_RELEASE_TASK);
      if (task != null) {
        this.tasktxdao.markCompleteTask(task, applicant.getOfferreleaseddby());
      }
      ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
      activity.setComment(applicant.getOfferReleaseComment());
      activity.setActivityName("Offer Released");
      this.applicanttxdao.saveApplicantActivity(activity);
      if (Constant.getValue("isemailsend.offer").equals("true")) {
        BOFactory.getApplicantTXBO().sendOfferEmail(applicant);
      }
      logger.info("Inside releaseoffer transaction end method");
    }
    catch (Exception e)
    {
      logger.info("Exception on releaseoffer", e);
      throw e;
    }
  }
  
  public void initiateoffer(JobApplicant applicant, List approverslist, List benefitList, long offerownerid, User user1, ApplicantOfferAttachment attachment)
  {
    logger.info("Inside initiateoffer transaction start method");
    boolean isofferIni = false;
    this.applicanttxdao.deleteOfferApprovers(applicant.getApplicantId());
    this.applicanttxdao.saveOfferApproversList(approverslist);
    
    this.applicanttxdao.deleteOtherBenefits(applicant.getApplicantId());
    this.applicanttxdao.saveOtherBenefits(benefitList);
    


    this.tasktxdao.deleteTask(applicant.getApplicantId(), Common.APPLICANT_IN_QUEUE);
    

    OfferApprover offerapprover = this.applicanttxdao.getNextOfferApprover(applicant.getApplicantId(), 0);
    if (offerapprover != null)
    {
      isofferIni = true;
      if ((!StringUtils.isNullOrEmpty(offerapprover.getIsGroup())) && (offerapprover.getIsGroup().equals("Y")))
      {
        UserGroup usergroup = new UserGroup();
        usergroup.setUsergrpId(offerapprover.getApproverId());
        applicant.setOwnerGroup(usergroup);
        applicant.setIsGroup("Y");
      }
      else
      {
        User user = new User();
        user.setUserId(offerapprover.getApproverId());
        applicant.setOwner(user);
        applicant.setOwnerGroup(null);
        applicant.setIsGroup("N");
      }
      applicant.setInterviewState("Offer Initiated-In Approval");
      if ((!StringUtils.isEmpty(offerapprover.getIsGroup())) && (offerapprover.getIsGroup().equals("Y")))
      {
        long groupId = offerapprover.getApproverId();
        
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(groupId);
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData task = new TaskData();
            task.setTaskname(applicant.getFullName());
            task.setIdvalue(applicant.getApplicantId());
            task.setTasktype(Common.OFFER_APPROVAL_TASK);
            task.setAssignedbyUserId(user1.getUserId());
            task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
            task.setAssignedtoUserId(touser.getUserId());
            task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            task.setCreatedBy(user1.getUserName());
            task.setCreatedDate(new Date());
            task.setStatus("A");
            task.setUuid(applicant.getUuid());
            task.setEventdate(new Date());
            task.setSuper_user_key(user1.getSuper_user_key());
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
        }
      }
      else
      {
        TaskData task = new TaskData();
        task.setTaskname(applicant.getFullName());
        task.setIdvalue(applicant.getApplicantId());
        task.setTasktype(Common.OFFER_APPROVAL_TASK);
        task.setAssignedbyUserId(user1.getUserId());
        task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
        task.setAssignedtoUserId(offerapprover.getApproverId());
        task.setAssignedtoUserName(offerapprover.getApproverName());
        task.setCreatedBy(user1.getUserName());
        task.setCreatedDate(new Date());
        task.setStatus("A");
        task.setUuid(applicant.getUuid());
        task.setEventdate(new Date());
        task.setSuper_user_key(user1.getSuper_user_key());
        
        this.tasktxdao.saveTask(task);
      }
    }
    else
    {
      isofferIni = false;
      
      applicant.setInterviewState("Ready for Release Offer");
      if ((!StringUtils.isEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
      {
        UserGroup usergroup = new UserGroup();
        usergroup.setUsergrpId(offerownerid);
        applicant.setOwnerGroup(usergroup);
        applicant.setIsGroup("Y");
        
        long groupId = offerownerid;
        
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(groupId);
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData task = new TaskData();
            task.setTaskname(applicant.getFullName());
            task.setIdvalue(applicant.getApplicantId());
            task.setTasktype(Common.OFFER_RELEASE_TASK);
            task.setAssignedbyUserId(user1.getUserId());
            task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
            task.setAssignedtoUserId(touser.getUserId());
            task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            task.setCreatedBy(user1.getUserName());
            task.setCreatedDate(new Date());
            task.setStatus("A");
            task.setUuid(applicant.getUuid());
            task.setEventdate(new Date());
            task.setSuper_user_key(user1.getSuper_user_key());
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
        }
      }
      else
      {
        User owner = new User();
        owner.setUserId(offerownerid);
        applicant.setOwner(owner);
        applicant.setIsGroup("N");
        applicant.setOwnerGroup(null);
        
        User offerowner = this.tasktxdao.getOfferOwnerDetails(offerownerid);
        
        TaskData task = new TaskData();
        task.setTaskname(applicant.getFullName());
        task.setIdvalue(applicant.getApplicantId());
        task.setTasktype(Common.OFFER_RELEASE_TASK);
        task.setAssignedbyUserId(user1.getUserId());
        task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
        task.setAssignedtoUserId(offerowner.getUserId());
        task.setAssignedtoUserName(offerowner.getFirstName() + " " + offerowner.getLastName());
        task.setCreatedBy(user1.getUserName());
        task.setCreatedDate(new Date());
        task.setStatus("A");
        task.setUuid(applicant.getUuid());
        task.setEventdate(new Date());
        task.setSuper_user_key(user1.getSuper_user_key());
        this.tasktxdao.saveTask(task);
      }
    }
    this.applicanttxdao.updateApplicant(applicant);
    if (attachment != null) {
      BOFactory.getApplicantBO().saveOfferAttachment(attachment, applicant.getUuid(), user1.getUserId(), user1.getFirstName() + " " + user1.getLastName(), Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
    }
    if (isofferIni)
    {
      ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
      activity.setComment(applicant.getOfferInitiationComment());
      activity.setActivityName("Offer Initiated-In Approval");
      activity.setIsDisplayAppPage("Y");
      if ((!StringUtils.isNullOrEmpty(offerapprover.getIsGroup())) && (offerapprover.getIsGroup().equals("Y"))) {
        activity.setIsgroup("Y");
      } else {
        activity.setIsgroup("N");
      }
      activity.setAssignedTouserId(offerapprover.getApproverId());
      activity.setAssignedToUserName(offerapprover.getApproverName());
      
      this.applicanttxdao.saveApplicantActivity(activity);
    }
    else
    {
      ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
      activity.setComment(applicant.getOfferInitiationComment());
      activity.setActivityName("Offer Initiated-In Approval");
      activity.setIsDisplayAppPage("Y");
      this.applicanttxdao.saveApplicantActivity(activity);
      




      ApplicantActivity activityfinal = populateApplicantActivity(applicant, user1, null);
      activityfinal.setComment(applicant.getOfferInitiationComment());
      activityfinal.setActivityName("Offer_approved_final");
      activityfinal.setIsDisplayAppPage("Y");
      this.applicanttxdao.saveApplicantActivity(activityfinal);
      
      ApplicantActivity activity1 = populateApplicantActivity(applicant, user1, null);
      
      activity1.setActivityName("Ready for Release Offer");
      activity1.setIsDisplayAppPage("Y");
      if ((!StringUtils.isNullOrEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
      {
        activity1.setIsgroup("Y");
        UserGroup usrgrp = this.usertxdao.getUserGroupNameandId(offerownerid);
        activity1.setAssignedTouserId(usrgrp.getUsergrpId());
        activity1.setAssignedToUserName(usrgrp.getUsergrpName());
      }
      else
      {
        activity1.setIsgroup("N");
        User offerowner = this.tasktxdao.getOfferOwnerDetails(offerownerid);
        activity1.setAssignedTouserId(offerowner.getUserId());
        activity1.setAssignedToUserName(offerowner.getFirstName() + " " + offerowner.getLastName());
      }
      this.applicanttxdao.saveApplicantActivity(activity1);
    }
    logger.info("Inside initiateoffer transaction end method");
  }
  
  public void rejectoffer(JobApplicant applicant, OfferApprover currentofferapprover, User user1)
  {
    logger.info("Inside rejectoffer transaction start method");
    
    this.applicanttxdao.updateOfferApprover(currentofferapprover);
    if ((!StringUtils.isEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
    {
      UserGroup usergroup = new UserGroup();
      usergroup.setUsergrpId(applicant.getOfferownerId());
      applicant.setOwnerGroup(usergroup);
      applicant.setIsGroup("Y");
    }
    else
    {
      User owner = new User();
      owner.setUserId(applicant.getOfferownerId());
      applicant.setOwner(owner);
      applicant.setIsGroup("N");
      applicant.setOwnerGroup(null);
    }
    applicant.setInterviewState("Offer rejected by approver");
    
    this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), Common.OFFER_APPROVAL_TASK, user1.getUserName());
    
    this.applicanttxdao.updateApplicant(applicant);
    

    ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
    activity.setComment(currentofferapprover.getComment());
    activity.setActivityName("Offer rejected by approver");
    activity.setIsDisplayAppPage("Y");
    if ((!StringUtils.isNullOrEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
    {
      activity.setIsgroup("Y");
      UserGroup usrgrp = this.usertxdao.getUserGroupNameandId(applicant.getOfferownerId());
      activity.setAssignedTouserId(usrgrp.getUsergrpId());
      activity.setAssignedToUserName(usrgrp.getUsergrpName());
    }
    else
    {
      activity.setIsgroup("N");
      User offerowner = this.tasktxdao.getOfferOwnerDetails(applicant.getOfferownerId());
      activity.setAssignedTouserId(offerowner.getUserId());
      activity.setAssignedToUserName(offerowner.getFirstName() + " " + offerowner.getLastName());
    }
    this.applicanttxdao.saveApplicantActivity(activity);
    
    logger.info("Inside rejectoffer transaction end method");
  }
  
  public void approveoffer(JobApplicant applicant, OfferApprover currentofferapprover, User user1)
  {
    logger.info("Inside approveoffer transaction start method");
    
    this.applicanttxdao.updateOfferApprover(currentofferapprover);
    

    TaskData task = this.tasktxdao.getTask(applicant.getApplicantId(), Common.OFFER_APPROVAL_TASK);
    boolean islastapprover = false;
    OfferApprover offerapprover = this.applicanttxdao.getNextOfferApprover(applicant.getApplicantId(), currentofferapprover.getLevelorder());
    if (offerapprover != null)
    {
      if ((!StringUtils.isNullOrEmpty(offerapprover.getIsGroup())) && (offerapprover.getIsGroup().equals("Y")))
      {
        UserGroup usergroup = new UserGroup();
        usergroup.setUsergrpId(offerapprover.getApproverId());
        applicant.setOwnerGroup(usergroup);
        applicant.setIsGroup("Y");
      }
      else
      {
        User user = new User();
        user.setUserId(offerapprover.getApproverId());
        applicant.setOwner(user);
        applicant.setOwnerGroup(null);
        applicant.setIsGroup("N");
      }
      islastapprover = false;
      

      ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
      activity.setComment(currentofferapprover.getComment());
      activity.setActivityName("Offer approved by approver");
      if ((!StringUtils.isNullOrEmpty(offerapprover.getIsGroup())) && (offerapprover.getIsGroup().equals("Y"))) {
        activity.setIsgroup("Y");
      } else {
        activity.setIsgroup("N");
      }
      activity.setAssignedTouserId(offerapprover.getApproverId());
      activity.setAssignedToUserName(offerapprover.getApproverName());
      this.applicanttxdao.saveApplicantActivity(activity);
    }
    else
    {
      islastapprover = true;
      

      ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
      activity.setComment(currentofferapprover.getComment());
      activity.setActivityName("Offer_approved_final");
      activity.setIsDisplayAppPage("Y");
      this.applicanttxdao.saveApplicantActivity(activity);
      

      ApplicantActivity activity1 = populateApplicantActivity(applicant, user1, null);
      
      activity1.setActivityName("Ready for Release Offer");
      activity1.setIsDisplayAppPage("Y");
      if ((!StringUtils.isNullOrEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
      {
        activity1.setIsgroup("Y");
        UserGroup usrgrp = this.usertxdao.getUserGroupNameandId(applicant.getOfferownerId());
        activity1.setAssignedTouserId(usrgrp.getUsergrpId());
        activity1.setAssignedToUserName(usrgrp.getUsergrpName());
      }
      else
      {
        activity1.setIsgroup("N");
        User offerowner = this.tasktxdao.getOfferOwnerDetails(applicant.getOfferownerId());
        activity1.setAssignedTouserId(offerowner.getUserId());
        activity1.setAssignedToUserName(offerowner.getFirstName() + " " + offerowner.getLastName());
      }
      this.applicanttxdao.saveApplicantActivity(activity1);
    }
    if (islastapprover)
    {
      applicant.setInterviewState("Ready for Release Offer");
      
      this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), Common.OFFER_APPROVAL_TASK, user1.getUserName());
      if ((!StringUtils.isEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
      {
        UserGroup usergroup = new UserGroup();
        usergroup.setUsergrpId(applicant.getOfferownerId());
        applicant.setOwnerGroup(usergroup);
        applicant.setIsGroup("Y");
        
        long groupId = applicant.getOfferownerId();
        
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(groupId);
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData tasknew = new TaskData();
            tasknew.setTaskname(applicant.getFullName());
            tasknew.setIdvalue(applicant.getApplicantId());
            tasknew.setTasktype(Common.OFFER_RELEASE_TASK);
            tasknew.setAssignedbyUserId(user1.getUserId());
            tasknew.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
            tasknew.setAssignedtoUserId(touser.getUserId());
            tasknew.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            tasknew.setCreatedBy(user1.getUserName());
            tasknew.setCreatedDate(new Date());
            tasknew.setStatus("A");
            tasknew.setUuid(applicant.getUuid());
            task.setEventdate(new Date());
            task.setSuper_user_key(user1.getSuper_user_key());
            taskList.add(tasknew);
          }
          this.tasktxdao.saveAllTask(taskList);
          
          TaskBO.sendTaskAssignEmail(user1, taskList, currentofferapprover.getComment());
        }
      }
      else
      {
        User owner = new User();
        owner.setUserId(applicant.getOfferownerId());
        applicant.setOwner(owner);
        applicant.setIsGroup("N");
        applicant.setOwnerGroup(null);
        
        User offerowner = this.tasktxdao.getOfferOwnerDetails(applicant.getOfferownerId());
        
        saveTask(user1, applicant.getFullName(), applicant.getApplicantId(), Common.OFFER_RELEASE_TASK, user1.getUserId(), user1.getFirstName() + " " + user1.getLastName(), offerowner.getUserId(), offerowner.getFirstName() + " " + offerowner.getLastName(), user1.getUserName(), applicant.getUuid(), currentofferapprover.getComment());
      }
    }
    else
    {
      this.tasktxdao.markcompleteforgroup(applicant.getApplicantId(), Common.OFFER_APPROVAL_TASK, user1.getUserName());
      if ((!StringUtils.isEmpty(offerapprover.getIsGroup())) && (offerapprover.getIsGroup().equals("Y")))
      {
        long groupId = offerapprover.getApproverId();
        
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(groupId);
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData tasknew = new TaskData();
            tasknew.setTaskname(applicant.getFullName());
            tasknew.setIdvalue(applicant.getApplicantId());
            tasknew.setTasktype(Common.OFFER_APPROVAL_TASK);
            tasknew.setAssignedbyUserId(task.getAssignedbyUserId());
            tasknew.setAssignedbyUserName(task.getAssignedbyUserName());
            tasknew.setAssignedtoUserId(touser.getUserId());
            tasknew.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            tasknew.setCreatedBy(user1.getUserName());
            tasknew.setCreatedDate(new Date());
            tasknew.setStatus("A");
            tasknew.setUuid(applicant.getUuid());
            task.setEventdate(new Date());
            task.setSuper_user_key(user1.getSuper_user_key());
            taskList.add(tasknew);
          }
          this.tasktxdao.saveAllTask(taskList);
          
          TaskBO.sendTaskAssignEmail(user1, taskList, currentofferapprover.getComment());
        }
      }
      else
      {
        saveTask(user1, applicant.getFullName(), applicant.getApplicantId(), Common.OFFER_APPROVAL_TASK, task.getAssignedbyUserId(), task.getAssignedbyUserName(), offerapprover.getApproverId(), offerapprover.getApproverName(), user1.getUserName(), applicant.getUuid(), currentofferapprover.getComment());
      }
    }
    this.applicanttxdao.updateApplicant(applicant);
    
    logger.info("Inside approveoffer transaction end method");
  }
  
  public void saveTask(User user1, String taskname, long idvalue, String tasktype, long assignedbyid, String assignedbyName, long assignedtoid, String assignedtoname, String createdby, String uuid, String comment)
  {
    TaskData tasknew = new TaskData();
    tasknew.setTaskname(taskname);
    tasknew.setIdvalue(idvalue);
    tasknew.setTasktype(tasktype);
    tasknew.setAssignedbyUserId(assignedbyid);
    tasknew.setAssignedbyUserName(assignedbyName);
    tasknew.setAssignedtoUserId(assignedtoid);
    tasknew.setAssignedtoUserName(assignedtoname);
    tasknew.setCreatedBy(createdby);
    tasknew.setCreatedDate(new Date());
    tasknew.setStatus("A");
    tasknew.setUuid(uuid);
    tasknew.setEventdate(new Date());
    tasknew.setSuper_user_key(user1.getSuper_user_key());
    this.tasktxdao.saveTask(tasknew);
    

    TaskBO.sendTaskAssignEmail(user1, tasknew, comment);
  }
  
  public void sendOfferEmail(JobApplicant applicant)
    throws Exception
  {
    try
    {
      JobRequisition jobreq = this.jobreqtxdao.getJobRequision(applicant.getReqId());
      



      OrganizationEmailTemplate orgemailtemplte = TaskDAO.getOrganizationEmailTemplate(jobreq.getOrganization().getOrgId(), Common.OFFER_EMAIL_COMPONENT);
      if (orgemailtemplte == null) {
        throw new Exception("Email template not found for function " + Common.OFFER_EMAIL_COMPONENT + " organization" + jobreq.getOrganization().getOrgName());
      }
      if ((orgemailtemplte.getStatus() != null) && (orgemailtemplte.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + Common.OFFER_EMAIL_COMPONENT + " organization" + jobreq.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemailtemplte.getEmailtemplate();
      

      User offerowner = this.usertxdao.getUser(applicant.getOfferownerId());
      

      String content = emptl.getEmailtemplateData();
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.user.createas.on.offer.release"))) && (Constant.getValue("applicant.user.createas.on.offer.release").equalsIgnoreCase("yes")) && (Constant.isPackageContainFunction(offerowner.getPackagetaken(), "applicantUser")))
      {
        ApplicantUser applicantuser = this.applicanttxdao.getApplicantUser(applicant.getApplicantId());
        if (applicantuser == null)
        {
          String email = this.applicanttxdao.isEmailIdExist(applicant.getEmail());
          if (!StringUtils.isNullOrEmpty(email))
          {
            logger.info("Email id already exist in Applicant user table" + email);
            applicant.setOfferEmailSentError("Email id is mapped with other applicant " + applicant.getEmail());
            logger.info(applicant.getOfferEmailSentError());
            throw new Exception(applicant.getOfferEmailSentError());
          }
          applicantuser = new ApplicantUser();
          applicantuser.setEmailId(applicant.getEmail());
          Locale locale = new Locale();
          locale.setLocaleId(offerowner.getLocale().getLocaleId());
          applicantuser.setLocale(locale);
          
          Timezone timezone = new Timezone();
          timezone.setTimezoneId(offerowner.getTimezone().getTimezoneId());
          applicantuser.setTimezone(timezone);
          
          applicantuser.setApplicant(applicant);
          applicantuser.setStatus("A");
          applicantuser.setPassword(EncryptDecrypt.encrypt(PasswordGenerator.getPassword(8)));
          this.applicanttxdao.saveApplicantUser(applicantuser);
        }
        else if ((!StringUtils.isNullOrEmpty(applicantuser.getEmailId())) && (!applicantuser.getEmailId().equalsIgnoreCase(applicant.getEmail())))
        {
          logger.info("Email id:" + applicant.getEmail() + " doesn't match with the existing applicant login email:" + applicantuser.getEmailId());
          
          applicant.setOfferEmailSentError("Email id:" + applicant.getEmail() + " doesn't match with the existing applicant login email:" + applicantuser.getEmailId());
          
          logger.info(applicant.getOfferEmailSentError());
          throw new Exception(applicant.getOfferEmailSentError());
        }
        String offer_acceptance_url = Constant.getValue("external.url") + "applicantlogin.do?method=login";
        String offer_acceptance_url_href = "<a href='" + offer_acceptance_url + "'" + "target='new'>" + offer_acceptance_url + "</a>";
        offer_acceptance_url_href = offer_acceptance_url_href + "<br>" + "Email id : " + applicantuser.getEmailId() + "<br>" + "Password : " + EncryptDecrypt.decrypt(applicantuser.getPassword()) + "<br>" + "Login code : " + EncryptDecrypt.decrypt(applicantuser.getApplicantCode()) + "<br>";
        
        content = content.replaceAll("##offer_acceptance_url##", offer_acceptance_url_href);
      }
      else
      {
        String url = "applicantofferdetails&applicantId=" + applicant.getApplicantId();
        String encrypted = "";
        UrlEncodeData enc = BOFactory.getLovBO().getEncodeUrlByUrl(url);
        if (enc == null)
        {
          SecretKey key = KeyGenerator.getInstance("DES").generateKey();
          
          DesEncrypter encrypter = new DesEncrypter(key);
          
          encrypted = encrypter.encrypt(url);
          
          UrlEncodeData urlen = new UrlEncodeData();
          urlen.setEncodedurl(encrypted);
          urlen.setUrl(url);
          LovDAO.saveeEncodeUrlNewSession(urlen);
        }
        else
        {
          encrypted = enc.getEncodedurl();
        }
        String offer_acceptance_url = Constant.getValue("external.url") + "applicantoffer.do?method=acceptordeclinedurl&key=" + encrypted;
        String offer_acceptance_url_href = "<a href='" + offer_acceptance_url + "'" + "target='new'>" + offer_acceptance_url + "</a>";
        content = content.replaceAll("##offer_acceptance_url##", offer_acceptance_url_href);
      }
      content = content.replaceAll("##applicant_no##", String.valueOf(applicant.getApplicant_number()));
      content = content.replaceAll("##applicant_name##", applicant.getFullName());
      

      content = content.replaceAll("##organization_name##", jobreq.getOrganization().getOrgName());
      content = content.replaceAll("##job_title##", applicant.getJobTitle());
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), Constant.getValue("email.defaultdateformat")));
      String address = "";
      if ((applicant.getStreet1() != null) && (applicant.getStreet2() != null)) {
        address = applicant.getStreet1() + " " + applicant.getStreet2();
      } else if ((applicant.getStreet1() != null) && (applicant.getStreet2() == null)) {
        address = applicant.getStreet1();
      } else if ((applicant.getStreet1() == null) && (applicant.getStreet2() != null)) {
        address = applicant.getStreet2();
      }
      if (!StringUtils.isNullOrEmpty(address)) {
        content = content.replaceAll("##applicant_address##", address);
      } else {
        content = content.replaceAll("##applicant_address##", "");
      }
      if (!StringUtils.isNullOrEmpty(applicant.getCity())) {
        content = content.replaceAll("##applicant_city##", applicant.getCity());
      } else {
        content = content.replaceAll("##applicant_city##", "");
      }
      if (applicant.getState() != null) {
        content = content.replaceAll("##applicant_state##", applicant.getState().getStateName());
      } else {
        content = content.replaceAll("##applicant_state##", "");
      }
      content = content.replaceAll("##hiring_manager##", jobreq.getHiringmgr().getFirstName() + " " + jobreq.getHiringmgr().getLastName());
      if (!StringUtils.isNullOrEmpty(applicant.getOfferedctc())) {
        content = content.replaceAll("##offered_ctc##", applicant.getOfferedctc());
      } else {
        content = content.replaceAll("##offered_ctc##", "");
      }
      if (!StringUtils.isNullOrEmpty(applicant.getOfferedctccurrencycode())) {
        content = content.replaceAll("##offered_ctc_currency_code##", applicant.getOfferedctccurrencycode());
      } else {
        content = content.replaceAll("##offered_ctc_currency_code##", "");
      }
      if (applicant.getOfferReleaseComment() != null) {
        content = content.replaceAll("##note##", applicant.getOfferReleaseComment());
      }
      String joburl = Constant.getValue("external.url") + "jobs.do?method=jobdetailsn&reqid=" + applicant.getReqId() + "&secureid=" + jobreq.getUuid() + "&source=OTHERJOB";
      joburl = "<a href='" + joburl + "'" + "target='new'>" + jobreq.getJobTitle() + "</a>";
      
      content = content.replaceAll("##job_url##", joburl);
      if (applicant.getTargetjoiningdate() != null) {
        content = content.replaceAll("##target_joining_date##", DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), Constant.getValue("email.defaultdateformat")));
      } else {
        content = content.replaceAll("##target_joining_date##", "");
      }
      if (jobreq.getLocation() != null)
      {
        String locationvalue = "<br>";
        if (jobreq.getLocation().getLocationName() != null) {
          locationvalue = locationvalue + jobreq.getLocation().getLocationName() + "<br>";
        }
        if (jobreq.getLocation().getAddress() != null) {
          locationvalue = locationvalue + jobreq.getLocation().getAddress() + "<br>";
        }
        if (jobreq.getLocation().getCity() != null) {
          locationvalue = locationvalue + jobreq.getLocation().getCity() + "<br>";
        }
        if (jobreq.getLocation().getState() != null) {
          locationvalue = locationvalue + jobreq.getLocation().getState().getStateName() + "<br>";
        }
        if (jobreq.getLocation().getCountry() != null) {
          locationvalue = locationvalue + jobreq.getLocation().getCountry().getCountryName() + "<br>";
        }
        if (jobreq.getLocation().getZip() != null) {
          locationvalue = locationvalue + jobreq.getLocation().getZip() + "<br>";
        }
        content = content.replaceAll("##organization_location##", locationvalue);
      }
      content = content.replaceAll("##offer_owner_contact_number##", offerowner.getPhoneOffice());
      content = content.replaceAll("##offer_owner_email_id##", offerowner.getEmailId());
      


      logger.info("email content" + content);
      
      EmailUtil emutil = new EmailUtil();
      String replyemail = "\"" + offerowner.getFirstName() + " " + offerowner.getLastName() + "\"" + " " + "<" + offerowner.getEmailId() + ">";
      String[] to = { applicant.getEmail() };
      String[] cc = null;
      
      String offercc = Constant.getValue("email.offer.cc");
      if (offercc != null) {
        cc = StringUtils.tokenize(offercc, ",");
      }
      List watchlist = this.applicanttxdao.getOfferwatchListByApplicantId(applicant.getApplicantId(), "release_offer");
      String[] bcc = new String[watchlist.size()];
      for (int j = 0; j < watchlist.size(); j++)
      {
        OfferWatchList ow = (OfferWatchList)watchlist.get(j);
        User wt = this.usertxdao.getUser(ow.getUserId());
        bcc[j] = wt.getEmailId();
      }
      List attachmentlist = this.applicanttxdao.getOfferAttachmentList(applicant.getApplicantId(), "reloffer");
      String[] attachemnt = new String[attachmentlist.size()];
      for (int k = 0; k < attachmentlist.size(); k++)
      {
        ApplicantOfferAttachment offerattch = (ApplicantOfferAttachment)attachmentlist.get(k);
        if ((offerattch != null) && (offerattch.getAttahmentname() != null) && (offerattch.getAttahmentname().length() > 0))
        {
          String filePath = Constant.getValue("ATTACHMENT_PATH");
          filePath = filePath + File.separator + "offerAttachment" + File.separator + offerattch.getUuid() + File.separator + offerattch.getAttahmentname();
          

          attachemnt[k] = filePath;
        }
      }
      String offersender = Constant.getValue("offer.email.sender");
      if (StringUtils.isNullOrEmpty(offersender)) {
        offersender = "\"" + offerowner.getFirstName() + " " + offerowner.getLastName() + "\"" + " " + "<" + offerowner.getEmailId() + ">";
      }
      String subject = emptl.getEmailSubject().replaceAll("##applicant_name##", applicant.getFullName());
      subject = subject.replaceAll("##organization_name##", jobreq.getOrganization().getOrgName());
      subject = subject.replaceAll("##job_title##", applicant.getJobTitle());
      subject = subject.replaceAll("##applicant_no##", String.valueOf(applicant.getApplicant_number()));
      

      emutil.sendMessage(offersender, to, cc, bcc, replyemail, subject, null, content, attachemnt, 0);
      for (int j = 0; j < watchlist.size(); j++)
      {
        OfferWatchList ow = (OfferWatchList)watchlist.get(j);
        ow.setIsemailsent("Y");
        ow.setUpdatedBy("offer-scheduler");
        ow.setUpdatedDate(new Date());
        this.applicanttxdao.updateOfferWaltcher(ow);
      }
      applicant.setIsOfferEmailSent("Y");
      applicant.setOfferEmailSentError("");
      applicant.setOfferemailsentdate(new Date());
      this.applicanttxdao.updateApplicant(applicant);
    }
    catch (Exception e)
    {
      throw new Exception(e.getMessage());
    }
  }
  
  public TaskTXDAO getTasktxdao()
  {
    return this.tasktxdao;
  }
  
  public void setTasktxdao(TaskTXDAO tasktxdao)
  {
    this.tasktxdao = tasktxdao;
  }
  
  public UserTXDAO getUsertxdao()
  {
    return this.usertxdao;
  }
  
  public void setUsertxdao(UserTXDAO usertxdao)
  {
    this.usertxdao = usertxdao;
  }
  
  public LovOpsTXDAO getLovopstxdao()
  {
    return this.lovopstxdao;
  }
  
  public void setLovopstxdao(LovOpsTXDAO lovopstxdao)
  {
    this.lovopstxdao = lovopstxdao;
  }
  
  public TalentPoolTXDAO getTalentpooltxdao()
  {
    return this.talentpooltxdao;
  }
  
  public void setTalentpooltxdao(TalentPoolTXDAO talentpooltxdao)
  {
    this.talentpooltxdao = talentpooltxdao;
  }
}
