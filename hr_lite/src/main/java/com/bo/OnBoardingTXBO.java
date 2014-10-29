package com.bo;

import com.bean.ApplicantActivity;
import com.bean.JobApplicant;
import com.bean.TaskData;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.onboard.OnBoardingTask;
import com.bean.onboard.OnBoardingTaskAttributeValues;
import com.bean.onboard.OnBoardingTaskAttributes;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.bean.onboard.Onboarding;
import com.common.Common;
import com.dao.ApplicantTXDAO;
import com.dao.LovOpsTXDAO;
import com.dao.OnBoardingTXDAO;
import com.dao.TaskTXDAO;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.apache.log4j.Logger;

public class OnBoardingTXBO
{
  protected static final Logger logger = Logger.getLogger(OnBoardingTXBO.class);
  TaskTXDAO tasktxdao;
  OnBoardingTXDAO onboardingtxdao;
  ApplicantTXDAO applicanttxdao;
  LovOpsTXDAO lovopstxdao;
  
  public Onboarding saveOnBoarding(JobApplicant applicant, List onboardtaskList, Onboarding onboard, User user1)
  {
    onboard.setOnBoardingDate(applicant.getTargetjoiningdate());
    this.onboardingtxdao.saveOnBoarding(onboard);
    for (int i = 0; i < onboardtaskList.size(); i++)
    {
      OnBoardingTask onboardtask = (OnBoardingTask)onboardtaskList.get(i);
      onboardtask.setOnboardingid(onboard.getOnboardingid());
      onboardtask.setEventDate(null);
      logger.info("onboardtask.getTaskdefid()" + onboardtask.getTaskdefid());
      OnBoardingTaskDefinitions taskdef = this.onboardingtxdao.getOnBoardingTaskDefinitions(onboardtask.getTaskdefid());
      logger.info("taskdef" + taskdef);
      
      onboardtask.setEventType(taskdef.getEventType());
      onboardtask.setTaskdefid(taskdef.getTaskdefid());
      onboardtask.setTaskName(taskdef.getTaskName());
      onboardtask.setTaskDesc(taskdef.getTaskDesc());
      
      List attributes = taskdef.getAtrributes();
      logger.info("taskdef.getTaskdefid()" + taskdef.getTaskdefid());
      logger.info("attributes.size()" + attributes.size());
      logger.info("onboardtask.getNoofdays()" + onboardtask.getNoofdays());
      
      Date targetobboarddingdate = new Date();
      

      logger.info("targetobboarddingdate" + targetobboarddingdate);
      if (applicant.getTargetjoiningdate() != null)
      {
        if (taskdef.getEventType().equals("BEFORE"))
        {
          targetobboarddingdate.setMonth(applicant.getTargetjoiningdate().getMonth());
          targetobboarddingdate.setYear(applicant.getTargetjoiningdate().getYear());
          targetobboarddingdate.setDate(applicant.getTargetjoiningdate().getDate() - onboardtask.getNoofdays());
          logger.info("targetobboarddingdate add" + targetobboarddingdate);
          onboardtask.setEventDate(targetobboarddingdate);
        }
        if (taskdef.getEventType().equals("AFTER"))
        {
          targetobboarddingdate.setMonth(applicant.getTargetjoiningdate().getMonth());
          targetobboarddingdate.setYear(applicant.getTargetjoiningdate().getYear());
          targetobboarddingdate.setDate(applicant.getTargetjoiningdate().getDate() + onboardtask.getNoofdays());
          onboardtask.setEventDate(targetobboarddingdate);
        }
      }
      String taskuuid = UUID.randomUUID().toString();
      onboardtask.setTaskuuid(taskuuid);
      onboardtask.setApplicantuuid(applicant.getUuid());
      this.onboardingtxdao.saveOnBoardingTask(onboardtask);
      for (int o = 0; o < attributes.size(); o++)
      {
        OnBoardingTaskAttributes taskattr = (OnBoardingTaskAttributes)attributes.get(o);
        OnBoardingTaskAttributeValues taskattrvalue = new OnBoardingTaskAttributeValues();
        taskattrvalue.setAttribute(taskattr.getAttribute());
        taskattrvalue.setIsMandatory(taskattr.getIsMandatory());
        taskattrvalue.setOnBoardingTaskId(onboardtask.getOnboardingTaskId());
        this.onboardingtxdao.saveOnBoardingTaskAttributeValues(taskattrvalue);
      }
      if ((!StringUtils.isNullOrEmpty(onboardtask.getIsGroup())) && (onboardtask.getIsGroup().equals("Y")))
      {
        UserGroup usrgrp = this.lovopstxdao.getUserGroup(onboardtask.getAssignedtoUserId());
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData task = new TaskData();
            task.setTaskname(onboardtask.getTaskName());
            task.setIdvalue(onboardtask.getOnboardingTaskId());
            task.setTasktype(Common.ONBOARDING_TASK);
            task.setAssignedbyUserId(onboardtask.getAssignedbyUserId());
            task.setAssignedbyUserName(onboardtask.getAssignedbyUserName());
            task.setAssignedtoUserId(touser.getUserId());
            task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
            task.setCreatedBy(onboardtask.getCreatedBy());
            task.setCreatedDate(new Date());
            task.setStatus("A");
            task.setUuid(taskuuid);
            task.setEventdate(onboardtask.getEventDate());
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
        }
      }
      else
      {
        TaskData task = new TaskData();
        task.setTaskname(onboardtask.getTaskName());
        task.setIdvalue(onboardtask.getOnboardingTaskId());
        task.setTasktype(Common.ONBOARDING_TASK);
        task.setAssignedbyUserId(onboardtask.getAssignedbyUserId());
        task.setAssignedbyUserName(onboardtask.getAssignedbyUserName());
        task.setAssignedtoUserId(onboardtask.getAssignedtoUserId());
        task.setAssignedtoUserName(onboardtask.getAssignedtoUserName());
        task.setCreatedBy(onboardtask.getCreatedBy());
        task.setCreatedDate(new Date());
        task.setStatus("A");
        task.setUuid(taskuuid);
        task.setEventdate(onboardtask.getEventDate());
        
        this.tasktxdao.saveTask(task);
      }
      ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
      activity.setComment(onboardtask.getTaskName());
      activity.setActivityName(Common.INITIATE_ONBOARDING_TASK);
      activity.setIsDisplayAppPage("Y");
      if ((!StringUtils.isNullOrEmpty(onboardtask.getIsGroup())) && (onboardtask.getIsGroup().equals("Y"))) {
        activity.setIsgroup("Y");
      } else {
        activity.setIsgroup("N");
      }
      activity.setAssignedTouserId(onboardtask.getAssignedtoUserId());
      activity.setAssignedToUserName(onboardtask.getAssignedtoUserName());
      
      this.applicanttxdao.saveApplicantActivity(activity);
    }
    applicant.setInitiateJoiningProcess("Started");
    this.applicanttxdao.updateApplicant(applicant);
    return onboard;
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
  
  public void submitOnboardingTask(OnBoardingTask taskonboard, JobApplicant applicant, User user1)
  {
    logger.info("inside submitOnboardingTask");
    this.onboardingtxdao.updateOnBoardingTask(taskonboard);
    List attributeValueList = taskonboard.getAttributeValues();
    for (int i = 0; i < attributeValueList.size(); i++)
    {
      OnBoardingTaskAttributeValues taskattrvalue = (OnBoardingTaskAttributeValues)attributeValueList.get(i);
      logger.info(Long.valueOf(taskattrvalue.getAttid()));
      this.onboardingtxdao.updateOnBoardingTaskAttributeValues(taskattrvalue);
    }
    this.tasktxdao.markcompleteforgroup(taskonboard.getOnboardingTaskId(), Common.ONBOARDING_TASK, taskonboard.getUpdatedBy());
    


    List onboardTaskList = this.onboardingtxdao.getOnBoardingTaskList(taskonboard.getOnboardingid());
    boolean isCompleted = true;
    for (int i = 0; i < onboardTaskList.size(); i++)
    {
      OnBoardingTask ontasktemp = (OnBoardingTask)onboardTaskList.get(i);
      if ((ontasktemp.getStatus() != null) && (!ontasktemp.getStatus().equals("C")))
      {
        isCompleted = false;
        break;
      }
    }
    if (isCompleted)
    {
      Onboarding onboard = this.onboardingtxdao.getOnBoarding(taskonboard.getOnboardingid());
      onboard.setStatus("C");
      this.onboardingtxdao.updateOnBoarding(onboard);
      
      applicant.setInitiateJoiningProcess("Completed");
      this.applicanttxdao.updateApplicant(applicant);
    }
    ApplicantActivity activity = populateApplicantActivity(applicant, user1, null);
    activity.setComment(taskonboard.getTaskName());
    activity.setActivityName(Common.ONBOARDING_TASK_COMPLETED);
    activity.setIsDisplayAppPage("Y");
    


    this.applicanttxdao.saveApplicantActivity(activity);
  }
  
  public void deleteOnBoardingTask(OnBoardingTask onboradtask)
  {
    this.tasktxdao.deleteTask(onboradtask.getOnboardingTaskId(), Common.ONBOARDING_TASK);
    

    this.onboardingtxdao.deleteOnboardingTask(onboradtask);
  }
  
  public Onboarding getOnBoarding(long applicantId, String uuid)
  {
    return this.onboardingtxdao.getOnBoarding(applicantId, uuid);
  }
  
  public List getOnBoardingTaskList(long onboardingId)
  {
    return this.onboardingtxdao.getOnBoardingTaskList(onboardingId);
  }
  
  public OnBoardingTask getOnBoardingTask(long onboardTaskId, String taskuuid)
  {
    return this.onboardingtxdao.getOnBoardingTask(onboardTaskId, taskuuid);
  }
  
  public TaskTXDAO getTasktxdao()
  {
    return this.tasktxdao;
  }
  
  public void setTasktxdao(TaskTXDAO tasktxdao)
  {
    this.tasktxdao = tasktxdao;
  }
  
  public OnBoardingTXDAO getOnboardingtxdao()
  {
    return this.onboardingtxdao;
  }
  
  public void setOnboardingtxdao(OnBoardingTXDAO onboardingtxdao)
  {
    this.onboardingtxdao = onboardingtxdao;
  }
  
  public ApplicantTXDAO getApplicanttxdao()
  {
    return this.applicanttxdao;
  }
  
  public void setApplicanttxdao(ApplicantTXDAO applicanttxdao)
  {
    this.applicanttxdao = applicanttxdao;
  }
  
  public LovOpsTXDAO getLovopstxdao()
  {
    return this.lovopstxdao;
  }
  
  public void setLovopstxdao(LovOpsTXDAO lovopstxdao)
  {
    this.lovopstxdao = lovopstxdao;
  }
}
