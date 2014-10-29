package com.bo;

import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;

import com.bean.AgencyRedemption;
import com.bean.BudgetCode;
import com.bean.JobRequisition;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateApprovers;
import com.bean.JobTemplateCompetency;
import com.bean.Location;
import com.bean.Organization;
import com.bean.QuestionGroups;
import com.bean.ReferralRedemption;
import com.bean.RefferalBudgetCode;
import com.bean.RefferalScheme;
import com.bean.RequisitionActivity;
import com.bean.RequistionAttachments;
import com.bean.RequistionExamQnsAssign;
import com.bean.SalaryPlan;
import com.bean.TaskData;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.WatchList;
import com.bean.filter.BusinessCriteria;
import com.bean.system.PublishToVendor;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.bean.testengine.MockQuestionSet;
import com.common.Common;
import com.common.FieldCodes;
import com.common.ObjectNotFoundException;
import com.common.PluginException;
import com.common.ValidationException;
import com.dao.JobRequistionTXDAO;
import com.dao.LovOpsTXDAO;
import com.dao.LovTXDAO;
import com.dao.RefferalDAO;
import com.dao.RuleTXDAO;
import com.dao.TaskTXDAO;
import com.dao.UserDAO;
import com.dao.UserTXDAO;
import com.dao.VariablesTXDAO;
import com.form.JobRequisitionForm;
import com.plugin.IntercepterImpl;
import com.plugin.PluginScripts;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;

public class JobRequistionTXBO
{
  protected static final Logger logger = Logger.getLogger(JobRequistionTXBO.class);
  JobRequistionTXDAO jobreqtxdao;
  TaskTXDAO tasktxdao;
  VariablesTXDAO variabletxdao;
  LovOpsTXDAO lovopstxdao;
  UserTXDAO usertxdao;
  LovTXDAO lovtxdao;
  RuleTXDAO ruletxdao;
  RefferalDAO refferaldao;
  
  public JobRequistionTXDAO getJobreqtxdao()
  {
    return this.jobreqtxdao;
  }
  
  public void setJobreqtxdao(JobRequistionTXDAO jobreqtxdao)
  {
    this.jobreqtxdao = jobreqtxdao;
  }
  
  public TaskTXDAO getTasktxdao()
  {
    return this.tasktxdao;
  }
  
  public void setTasktxdao(TaskTXDAO tasktxdao)
  {
    this.tasktxdao = tasktxdao;
  }
  
  public JobRequisition deletepublishtovendor(JobRequisitionForm jbForm, String publishtovendorId, User user)
    throws Exception
  {
    try
    {
      JobRequisition jb = null;
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      this.jobreqtxdao.deletePublishToVendor(jb.getJobreqId(), new Long(publishtovendorId).longValue());
      commonLovPopulate(jbForm, jb, user);
      return jb;
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition initiateapproval(JobRequisitionForm jbForm, User user, String comment)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      jb.setState("In Approval");
      jb.setIsapprovalInitiated(1);
      jb.setApprovalInitiationComment(comment);
      jb.setApprovalIniByName(user.getFirstName() + " " + user.getLastName());
      jb.setApprovalIniDate(new Date());
      jb.setApprovalIniById(user.getUserId());
      

      List approversList = this.jobreqtxdao.getJobRequisionTmplApproversList(jb.getJobreqId(), "job");
      logger.info("Inside initiateoffer method approversList" + approversList.size());
      


      long orgId = jb.getOrganization().getOrgId();
      long deptId = 0L;
      if (jb.getDepartment() != null) {
        deptId = jb.getDepartment().getDepartmentId();
      }
      SystemRule sysrule = this.ruletxdao.getOrganizationRuleByType(orgId, deptId, Common.SYSTEM_RULE_REQUISTION_APPROVER);
      

      int levelorder = 0;
      List newapproverList = new ArrayList();
      if ((approversList != null) && (approversList.size() > 0)) {
        for (int i = 0; i < approversList.size(); i++)
        {
          levelorder += 1;
          JobTemplateApprovers jbapp = (JobTemplateApprovers)approversList.get(i);
          JobTemplateApprovers jbappnew = new JobTemplateApprovers();
          jbappnew.setUserId(jbapp.getUserId());
          jbappnew.setJbTmplId(jb.getJobreqId());
          jbappnew.setApproverName(jbapp.getApproverName());
          jbappnew.setIsFromSystemRule("N");
          jbappnew.setIsapprover("Y");
          jbappnew.setIsGroup(jbapp.getIsGroup());
          levelorder += 1;
          jbappnew.setLevelorder(levelorder);
          
          newapproverList.add(jbappnew);
        }
      }
      if (sysrule != null)
      {
        List approvers = sysrule.getRuleUsers();
        logger.info("Inside initiateoffer method approvers.size" + approvers.size());
        for (int i = 0; i < approvers.size(); i++)
        {
          SystemRuleUser sysuser = (SystemRuleUser)approvers.get(i);
          logger.info("Inside initiateoffer method" + sysuser.getIsGroup());
          if ((sysuser != null) && (sysuser.getIsGroup().equals("Y")))
          {
            if ((sysuser.getUserGroup() != null) && (sysuser.getUserGroup().getStatus().equals("A")))
            {
              JobTemplateApprovers jbapp = new JobTemplateApprovers();
              jbapp.setUserId(sysuser.getUserGroup().getUsergrpId());
              jbapp.setJbTmplId(jb.getJobreqId());
              jbapp.setApproverName(sysuser.getUserGroup().getUsergrpName());
              jbapp.setIsFromSystemRule("Y");
              jbapp.setIsapprover("Y");
              jbapp.setIsGroup("Y");
              levelorder += 1;
              jbapp.setLevelorder(levelorder);
              newapproverList.add(jbapp);
            }
          }
          else if ((sysuser.getUser() != null) && (sysuser.getUser().getStatus().equals("A")))
          {
            JobTemplateApprovers jbapp = new JobTemplateApprovers();
            jbapp.setUserId(sysuser.getUser().getUserId());
            jbapp.setJbTmplId(jb.getJobreqId());
            jbapp.setApproverName(sysuser.getUser().getFirstName() + " " + sysuser.getUser().getLastName());
            jbapp.setIsFromSystemRule("Y");
            jbapp.setIsapprover("Y");
            jbapp.setIsGroup("N");
            levelorder += 1;
            jbapp.setLevelorder(levelorder);
            newapproverList.add(jbapp);
          }
        }
      }
      this.jobreqtxdao.deleteApprovers(jb.getJobreqId(), "job");
      
      logger.info("Inside initiateoffer method newapproverList" + newapproverList.size());
      this.jobreqtxdao.saveApproversList(newapproverList, "job", jb.getJobreqId());
      



      JobTemplateApprovers approver = this.jobreqtxdao.getNextRequistionApproverTx(jb.getJobreqId(), "job", 0);
      if (approver != null)
      {
        if ((!StringUtils.isNullOrEmpty(approver.getIsGroup())) && (approver.getIsGroup().equals("Y")))
        {
          jb.setCurrentOwnerId(approver.getUserId());
          jb.setIsGroup("Y");
          jb.setCurrentOwnerName(getCurrentOwnerName(approver.getUserId(), jb.getIsGroup()));
          this.jobreqtxdao.updateJobRequistion(jb);
          


          long groupId = approver.getUserId();
          
          UserGroup usrgrp = this.lovopstxdao.getUserGroupTx(groupId);
          Set users = usrgrp.getUsers();
          List<TaskData> taskList = new ArrayList();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User touser = (User)itr.next();
              
              TaskData task = new TaskData();
              setTaskData(jb, Common.REQUISTION_APPROVAL_TASK, user, touser, task);
              taskList.add(task);
            }
            this.tasktxdao.saveAllTask(taskList);
            
            TaskBO.sendTaskAssignEmail(user, taskList, comment);
          }
        }
        else
        {
          jb.setCurrentOwnerId(approver.getUserId());
          jb.setIsGroup("N");
          jb.setCurrentOwnerName(getCurrentOwnerName(approver.getUserId(), jb.getIsGroup()));
          this.jobreqtxdao.updateJobRequistion(jb);
          


          TaskData task = new TaskData();
          setTaskData(jb, Common.REQUISTION_APPROVAL_TASK, user, approver.getUserId(), approver.getApproverName(), task);
          this.tasktxdao.saveTask(task);
          
          TaskBO.sendTaskAssignEmail(user, task, comment);
        }
        saveActivity(user, jb, "REQUISITION_APPROVAL_INITIATED", jb.getApprovalInitiationComment(), true);
      }
      else
      {
        saveActivity(user, jb, "REQUISITION_APPROVAL_INITIATED", jb.getApprovalInitiationComment(), false);
        

        setTaskIfAllApproved(jb, jbForm, user, jb.getApprovalInitiationComment(), true);
      }
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  private void rollBack() {}
  
  private void saveActivity(User user, JobRequisition jb, String activityType, String comment, boolean isassigntoapply)
  {
    RequisitionActivity activity = populateActivityData(user, jb, activityType, comment);
    if (isassigntoapply) {
      if ((!StringUtils.isEmpty(jb.getIsGroup())) && (jb.getIsGroup().equals("Y")))
      {
        UserGroup usrgrp = this.usertxdao.getUserGroupNameandId(jb.getCurrentOwnerId());
        
        activity.setIsgroup("Y");
        activity.setAssignedTouserId(usrgrp.getUsergrpId());
        activity.setAssignedToUserName(usrgrp.getUsergrpName());
      }
      else
      {
        activity.setIsgroup("N");
        User touser = this.usertxdao.getUserFullNameEmailAndUserId(jb.getCurrentOwnerId());
        activity.setAssignedTouserId(touser.getUserId());
        activity.setAssignedToUserName(touser.getFirstName() + " " + touser.getLastName());
      }
    }
    this.jobreqtxdao.saveActivity(activity);
  }
  
  public JobRequisition approvereq(JobRequisitionForm jbForm, User user1, String jobreqId, String jbTmplApproverId, String comment)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      JobTemplateApprovers jobapprover = this.jobreqtxdao.getApproverByTmplApproverId(jbTmplApproverId);
      jobapprover.setApproved("Y");
      jobapprover.setApprovedDate(new Date());
      jobapprover.setComment(comment);
      if ((!StringUtils.isNullOrEmpty(jobapprover.getIsGroup())) && (jobapprover.getIsGroup().equals("Y")))
      {
        jobapprover.setOnbehalfApproverId(user1.getUserId());
        jobapprover.setOnbehalfApproverName(user1.getFirstName() + " " + user1.getLastName());
      }
      this.jobreqtxdao.updateJobRequisionApprover(jobapprover);
      



      this.tasktxdao.markCompleteForGroup(jb.getJobreqId(), Common.REQUISTION_APPROVAL_TASK, user1.getUserName());
      boolean isalltaskdone = true;
      
      JobTemplateApprovers approver = this.jobreqtxdao.getNextRequistionApproverTx(jb.getJobreqId(), "job", jobapprover.getLevelorder());
      if (approver != null)
      {
        if ((!StringUtils.isNullOrEmpty(approver.getIsGroup())) && (approver.getIsGroup().equals("Y")))
        {
          jb.setCurrentOwnerId(approver.getUserId());
          jb.setIsGroup("Y");
          jb.setCurrentOwnerName(getCurrentOwnerName(approver.getUserId(), jb.getIsGroup()));
          this.jobreqtxdao.updateJobRequistion(jb);
          


          long groupId = approver.getUserId();
          
          UserGroup usrgrp = this.lovopstxdao.getUserGroupTx(groupId);
          Set users = usrgrp.getUsers();
          List<TaskData> taskList = new ArrayList();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User touser = (User)itr.next();
              
              TaskData task = new TaskData();
              setTaskData(jb, Common.REQUISTION_APPROVAL_TASK, user1, touser, task);
              taskList.add(task);
            }
            this.tasktxdao.saveAllTask(taskList);
            
            TaskBO.sendTaskAssignEmail(user1, taskList, comment);
          }
        }
        else
        {
          jb.setCurrentOwnerId(approver.getUserId());
          jb.setIsGroup("N");
          jb.setCurrentOwnerName(getCurrentOwnerName(approver.getUserId(), jb.getIsGroup()));
          this.jobreqtxdao.updateJobRequistion(jb);
          


          TaskData task = new TaskData();
          setTaskData(jb, Common.REQUISTION_APPROVAL_TASK, user1, approver.getUserId(), approver.getApproverName(), task);
          this.tasktxdao.saveTask(task);
          
          TaskBO.sendTaskAssignEmail(user1, task, comment);
        }
        saveActivity(user1, jb, Common.REQUISTION_APPROVED, jobapprover.getComment(), true);
        
        isalltaskdone = false;
      }
      else
      {
        List approversList = this.jobreqtxdao.getJobRequisionTmplApproversList(jb.getJobreqId(), "job");
        
        boolean allapproved = true;
        for (int j = 0; j < approversList.size(); j++)
        {
          JobTemplateApprovers japp1 = (JobTemplateApprovers)approversList.get(j);
          if (!japp1.getApproved().equals("Y"))
          {
            allapproved = false;
            break;
          }
        }
        setTaskIfAllApproved(jb, jbForm, user1, comment, allapproved);
      }
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  private void setTaskIfAllApproved(JobRequisition jb, JobRequisitionForm jbForm, User user1, String comment, boolean allapproved)
    throws Exception
  {
    try
    {
      if (allapproved)
      {
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("publish.requistion.default.owner"))) && (Constant.getValue("publish.requistion.default.owner").equalsIgnoreCase("recruiter")))
        {
          if (jb.getRecruiterId() > 0L)
          {
            jb.setCurrentOwnerId(jb.getRecruiterId());
            logger.info("jb.getIsgrouprecruiter()jb.getIsgrouprecruiter()+jb.getIsgrouprecruiter()" + jb.getIsgrouprecruiter());
            jb.setIsGroup(jb.getIsgrouprecruiter());
            jb.setCurrentOwnerName(getCurrentOwnerName(jb.getRecruiterId(), jb.getIsgrouprecruiter()));
          }
          else
          {
            jb.setCurrentOwnerId(jb.getHiringmgr().getUserId());
            jb.setIsGroup("N");
            jb.setCurrentOwnerName(getCurrentOwnerName(jb.getHiringmgr().getUserId(), jb.getIsGroup()));
          }
        }
        else
        {
          jb.setCurrentOwnerId(jb.getHiringmgr().getUserId());
          jb.setIsGroup("N");
          jb.setCurrentOwnerName(getCurrentOwnerName(jb.getHiringmgr().getUserId(), jb.getIsGroup()));
        }
        jb.setState("Approved");
        jb.setUpdatedBy(user1.getUserName());
        jb.setUpdatedDate(new Date());
        this.jobreqtxdao.updateJobRequistion(jb);
      }
      saveActivity(user1, jb, Common.REQUISTION_APPROVED_FINAL, comment, true);
      if (allapproved) {
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("publish.requistion.step.skip"))) && (Constant.getValue("publish.requistion.step.skip").equalsIgnoreCase("yes")))
        {
          publishrequisition(jbForm, user1, comment);
        }
        else if ((!StringUtils.isNullOrEmpty(Constant.getValue("publish.requistion.default.owner"))) && (Constant.getValue("publish.requistion.default.owner").equalsIgnoreCase("recruiter")))
        {
          if (jb.getRecruiterId() > 0L)
          {
            jb.setCurrentOwnerId(jb.getRecruiterId());
            jb.setIsGroup(jb.getIsgrouprecruiter());
            jb.setCurrentOwnerName(getCurrentOwnerName(jb.getRecruiterId(), jb.getIsgrouprecruiter()));
          }
          setTaskForRecruiter(jb, user1, Common.REQUISTION_PUBLISH_TASK, comment, true);
        }
        else
        {
          TaskData tasknew = new TaskData();
          setTaskData(jb, Common.REQUISTION_PUBLISH_TASK, user1, jb.getHiringmgr().getUserId(), jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName(), tasknew);
          this.tasktxdao.saveTask(tasknew);
          
          TaskBO.sendTaskAssignEmail(user1, tasknew, comment);
        }
      }
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition publishrequisition(JobRequisitionForm jbForm, User user1, String comment)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      jb.setState("Active");
      jb.setPublishedComment(comment);
      jb.setPublishedByName(user1.getFirstName() + " " + user1.getLastName());
      jb.setPublishedDate(new Date());
      jb.setPublishedById(user1.getUserId());
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      









      long orgId = jb.getOrganization().getOrgId();
      long deptId = 0L;
      if (jb.getDepartment() != null) {
        deptId = jb.getDepartment().getDepartmentId();
      }
      SystemRule sysrule = this.ruletxdao.getOrganizationRuleByType(orgId, deptId, Common.SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS);
      
      List newvendorList = new ArrayList();
      if (sysrule != null)
      {
        List vendors = sysrule.getRuleUsers();
        for (int i = 0; i < vendors.size(); i++)
        {
          SystemRuleUser sysuser = (SystemRuleUser)vendors.get(i);
          if ((sysuser.getUser() != null) && (sysuser.getUser().getStatus().equals("A")))
          {
            PublishToVendor jbapp = new PublishToVendor();
            jbapp.setUserId(sysuser.getUser().getUserId());
            jbapp.setUserName(sysuser.getUser().getFirstName());
            jbapp.setIsFromSystemRule("Y");
            jbapp.setJobreqId(jb.getJobreqId());
            jbapp.setCreatedBy(user1.getUserName());
            jbapp.setCreatedDate(new Date());
            jbapp.setSystemRuleId(sysrule.getSystemRuleId());
            newvendorList.add(jbapp);
          }
        }
        jb.setPublishToExternal(sysrule.getPublishToExternal());
        jb.setPublishToEmpRef(sysrule.getPublishToEmpRef());
      }
      if (jb.getRecruiterId() > 0L)
      {
        jb.setCurrentOwnerId(jb.getRecruiterId());
        jb.setIsGroup(jb.getIsgrouprecruiter());
        jb.setCurrentOwnerName(getCurrentOwnerName(jb.getRecruiterId(), jb.getIsgrouprecruiter()));
      }
      else
      {
        jb.setCurrentOwnerId(jb.getHiringmgr().getUserId());
        jb.setIsGroup("N");
        jb.setCurrentOwnerName(getCurrentOwnerName(jb.getHiringmgr().getUserId(), jb.getIsGroup()));
      }
      preJobRequitionPublish(jb, user1);
      
      this.jobreqtxdao.updateJobRequistion(jb);
      this.jobreqtxdao.savePublishToVendorList(newvendorList);
      
      TaskData task = this.tasktxdao.getTask(jb.getJobreqId(), Common.REQUISTION_PUBLISH_TASK);
      if (task != null) {
        this.tasktxdao.markCompleteTask(task, user1.getUserName());
      }
      saveActivity(user1, jb, "REQUISITION_PUBLISHED", comment, true);
      





      postJobRequitionPublish(jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (PluginException e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public String getCurrentOwnerName(long ownerId, String isGroup)
  {
    String oname = "";
    try
    {
      if ((isGroup != null) && (isGroup.equals("Y")))
      {
        UserGroup usergrp = this.usertxdao.getUserGroupNameandIdById(ownerId);
        oname = usergrp.getUsergrpName();
      }
      else
      {
        User usr = this.usertxdao.getUserFullNameEmailAndUserId(ownerId);
        oname = usr.getFirstName() + " " + usr.getLastName();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    logger.info("owner name" + oname);
    return oname;
  }
  
  public boolean isReferralBudgetExceed(long referralSchemeId)
  {
    boolean success = false;
    RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(referralSchemeId);
    if (scheme == null) {
      return false;
    }
    if ((scheme.getRefferalSchemeType() != null) && (
      (scheme.getRefferalSchemeType().getUom() == null) || (!scheme.getRefferalSchemeType().getUom().equals("monetary")))) {
      if (scheme == null) {
        return false;
      }
    }
    if ((scheme.getSchemeType() != null) && (scheme.getSchemeType().equals(Common.REFERRAL_SCHEME_TYPE_AGENCY)))
    {
      RefferalBudgetCode bcode = scheme.getRefferalBudgetCode();
      if (bcode != null)
      {
        List agnecyRedList = this.refferaldao.getAllAgencyRedemptionByBudget(bcode.getRef_budgetId());
        long refferalSchemeAmount = scheme.getRefferalScheme_Amount();
        if (agnecyRedList != null) {
          for (int i = 0; i < agnecyRedList.size(); i++)
          {
            AgencyRedemption ag = (AgencyRedemption)agnecyRedList.get(i);
            refferalSchemeAmount += ag.getRefferalSchemeAmount();
          }
        }
        if (refferalSchemeAmount > bcode.getRef_budgetamount()) {
          success = true;
        }
      }
    }
    else
    {
      RefferalBudgetCode bcode = scheme.getRefferalBudgetCode();
      if (bcode != null)
      {
        List empRedList = this.refferaldao.getAllEmployeeRedemptionByBudget(bcode.getRef_budgetId());
        long refferalSchemeAmount = scheme.getRefferalScheme_Amount();
        if (empRedList != null) {
          for (int i = 0; i < empRedList.size(); i++)
          {
            ReferralRedemption ref = (ReferralRedemption)empRedList.get(i);
            refferalSchemeAmount += ref.getRefferalSchemeAmount();
          }
        }
        if (refferalSchemeAmount > bcode.getRef_budgetamount()) {
          success = true;
        }
      }
    }
    return success;
  }
  
  public JobRequisition getJobRequisionTx(long reqId)
    throws ObjectNotFoundException
  {
    return this.jobreqtxdao.getJobRequisionTx(reqId);
  }
  
  public JobRequisition publishrequisitionwindow(JobRequisitionForm jbForm, User user1, Set uservendoridList, String comment, String publishToExternal, String publishToEmpRef, String eeoInfoIncluded, String userids, List errorList)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("budget.validation.referral.redemption"))) && (Constant.getValue("budget.validation.referral.redemption").equals("yes")))
      {
        if (jbForm.getEmployeeReferralSchemeList() != null)
        {
          Iterator itr = jbForm.getEmployeeReferralSchemeList().iterator();
          while (itr.hasNext())
          {
            RefferalScheme rs = (RefferalScheme)itr.next();
            if (isReferralBudgetExceed(rs.getRefferalScheme_Id()))
            {
              RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
              String[] args = { scheme.getRefferalScheme_Name() };
              String msg = Constant.getResourceStringValue("referral.budget.amount.exceed", user1.getLocale(), args);
              errorList.add(msg);
            }
          }
        }
        if (jbForm.getAgencyReferralSchemeList() != null)
        {
          Iterator itr = jbForm.getAgencyReferralSchemeList().iterator();
          while (itr.hasNext())
          {
            RefferalScheme rs = (RefferalScheme)itr.next();
            if (isReferralBudgetExceed(rs.getRefferalScheme_Id()))
            {
              RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
              String[] args = { scheme.getRefferalScheme_Name() };
              String msg = Constant.getResourceStringValue("referral.budget.amount.exceed", user1.getLocale(), args);
              errorList.add(msg);
            }
          }
        }
        if (errorList.size() > 0) {
          throw new ValidationException("", "Referral scheme validation failed");
        }
      }
      jb.setState("Active");
      jb.setPublishedComment(comment);
      jb.setPublishedByName(user1.getFirstName() + " " + user1.getLastName());
      jb.setPublishedDate(new Date());
      jb.setPublishedById(user1.getUserId());
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      if ((!StringUtils.isNullOrEmpty(publishToExternal)) && (publishToExternal.equals("Y"))) {
        jb.setPublishToExternal("Y");
      } else {
        jb.setPublishToExternal("N");
      }
      if ((!StringUtils.isNullOrEmpty(publishToEmpRef)) && (publishToEmpRef.equals("Y"))) {
        jb.setPublishToEmpRef("Y");
      } else {
        jb.setPublishToEmpRef("N");
      }
      if ((!StringUtils.isNullOrEmpty(eeoInfoIncluded)) && (eeoInfoIncluded.equals("Y"))) {
        jb.setEeoInfoIncluded("Y");
      } else {
        jb.setEeoInfoIncluded("N");
      }
      jb.setEmployeeReferralSchemeList(jbForm.getEmployeeReferralSchemeList());
      jb.setAgencyReferralSchemeList(jbForm.getAgencyReferralSchemeList());
      if (jb.getRecruiterId() > 0L)
      {
        jb.setIsGroup(jb.getIsgrouprecruiter());
        jb.setCurrentOwnerId(jb.getRecruiterId());
        jb.setCurrentOwnerName(getCurrentOwnerName(jb.getRecruiterId(), jb.getIsgrouprecruiter()));
      }
      else
      {
        jb.setIsGroup("N");
        jb.setCurrentOwnerId(jb.getHiringmgr().getUserId());
        jb.setCurrentOwnerName(getCurrentOwnerName(jb.getHiringmgr().getUserId(), jb.getIsGroup()));
      }
      preJobRequitionPublish(jb, user1);
      
      this.jobreqtxdao.updateJobRequistion(jb);
      







      long orgId = jb.getOrganization().getOrgId();
      long deptId = 0L;
      if (jb.getDepartment() != null) {
        deptId = jb.getDepartment().getDepartmentId();
      }
      SystemRule sysrule = RuleTXDAO.getOrganizationRuleByTypeByNewSession(orgId, deptId, Common.SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS);
      
      List newvendorList = new ArrayList();
      if (sysrule != null)
      {
        List vendors = sysrule.getRuleUsers();
        for (int i = 0; i < vendors.size(); i++)
        {
          SystemRuleUser sysuser = (SystemRuleUser)vendors.get(i);
          if (uservendoridList.contains(Long.valueOf(sysuser.getUser().getUserId()))) {
            if ((sysuser.getUser() != null) && (sysuser.getUser().getStatus().equals("A")))
            {
              PublishToVendor jbapp = new PublishToVendor();
              jbapp.setUserId(sysuser.getUser().getUserId());
              jbapp.setUserName(sysuser.getUser().getFirstName());
              jbapp.setIsFromSystemRule("Y");
              jbapp.setJobreqId(jb.getJobreqId());
              jbapp.setCreatedBy(user1.getUserName());
              jbapp.setCreatedDate(new Date());
              jbapp.setSystemRuleId(sysrule.getSystemRuleId());
              newvendorList.add(jbapp);
            }
          }
        }
      }
      List newvendorListIds = new ArrayList();
      if (newvendorList != null) {
        for (int i = 0; i < newvendorList.size(); i++)
        {
          PublishToVendor sysuser = (PublishToVendor)newvendorList.get(i);
          newvendorListIds.add(String.valueOf(sysuser.getUserId()));
        }
      }
      String[] ids = StringUtils.tokenize(userids, ",");
      if (ids != null) {
        for (int i = 0; i < ids.length; i++) {
          if (!newvendorListIds.contains(ids[i]))
          {
            User user = UserDAO.getUserFullNameEmailAndUserId(new Long(ids[i]).longValue());
            PublishToVendor jbapp = new PublishToVendor();
            jbapp.setUserId(user.getUserId());
            jbapp.setUserName(user.getFirstName());
            jbapp.setIsFromSystemRule("N");
            jbapp.setJobreqId(jb.getJobreqId());
            jbapp.setCreatedBy(user1.getUserName());
            jbapp.setCreatedDate(new Date());
            newvendorList.add(jbapp);
          }
        }
      }
      this.jobreqtxdao.deletePublishToVendorList(jb.getJobreqId());
      this.jobreqtxdao.savePublishToVendorList(newvendorList);
      
      jbForm.setPublishToVendorList(newvendorList);
      
      this.tasktxdao.markCompleteForGroup(jb.getJobreqId(), Common.REQUISTION_PUBLISH_TASK, user1.getUserName());
      



      saveActivity(user1, jb, "REQUISITION_PUBLISHED", comment, true);
      













      postJobRequitionPublish(jb, user1);
      
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (PluginException e)
    {
      throw new Exception(e.getMessage());
    }
    catch (Exception e)
    {
      throw new Exception(e.getMessage());
    }
  }
  
  private void preJobRequitionPublish(JobRequisition requisition, User user)
    throws PluginException
  {
    if (PluginScripts.preREQPUBLISH != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("JobRequisitionObject", requisition);
        ue.setObject("UserObject", user);
        ue.firePreRequistionPublishScript();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception", ex);
        throw new PluginException(ex.getMessage(), ex);
      }
    }
  }
  
  private void postJobRequitionPublish(JobRequisition requisition, User user)
    throws PluginException
  {
    if (PluginScripts.postREQPUBLISH != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("JobRequisitionObject", requisition);
        ue.setObject("UserObject", user);
        ue.firePostRequistionPublishScript();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception", ex);
        throw new PluginException(ex.getMessage(), ex);
      }
    }
  }
  
  public JobRequisition savepublishinfo(JobRequisitionForm jbForm, User user1, String publishToExternal, String publishToEmpRef, String eeoInfoIncluded, String userids, List errorList)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("budget.validation.referral.redemption"))) && (Constant.getValue("budget.validation.referral.redemption").equals("yes")))
      {
        if (jbForm.getEmployeeReferralSchemeList() != null)
        {
          Iterator itr = jbForm.getEmployeeReferralSchemeList().iterator();
          while (itr.hasNext())
          {
            RefferalScheme rs = (RefferalScheme)itr.next();
            if (isReferralBudgetExceed(rs.getRefferalScheme_Id()))
            {
              RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
              String[] args = { scheme.getRefferalScheme_Name() };
              String msg = Constant.getResourceStringValue("referral.budget.amount.exceed", user1.getLocale(), args);
              errorList.add(msg);
            }
          }
        }
        if (jbForm.getAgencyReferralSchemeList() != null)
        {
          Iterator itr = jbForm.getAgencyReferralSchemeList().iterator();
          while (itr.hasNext())
          {
            RefferalScheme rs = (RefferalScheme)itr.next();
            if (isReferralBudgetExceed(rs.getRefferalScheme_Id()))
            {
              RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
              String[] args = { scheme.getRefferalScheme_Name() };
              String msg = Constant.getResourceStringValue("referral.budget.amount.exceed", user1.getLocale(), args);
              errorList.add(msg);
            }
          }
        }
        if (errorList.size() > 0) {
          throw new ValidationException("", "Referral scheme validation failed");
        }
      }
      if ((!StringUtils.isNullOrEmpty(publishToExternal)) && (publishToExternal.equals("Y"))) {
        jb.setPublishToExternal("Y");
      } else {
        jb.setPublishToExternal("N");
      }
      if ((!StringUtils.isNullOrEmpty(publishToEmpRef)) && (publishToEmpRef.equals("Y"))) {
        jb.setPublishToEmpRef("Y");
      } else {
        jb.setPublishToEmpRef("N");
      }
      if ((!StringUtils.isNullOrEmpty(eeoInfoIncluded)) && (eeoInfoIncluded.equals("Y"))) {
        jb.setEeoInfoIncluded("Y");
      } else {
        jb.setEeoInfoIncluded("N");
      }
      jb.setEmployeeReferralSchemeList(jbForm.getEmployeeReferralSchemeList());
      jb.setAgencyReferralSchemeList(jbForm.getAgencyReferralSchemeList());
      

      List systemvendorlist = this.jobreqtxdao.getPublishToVendorList(jb.getJobreqId());
      

      List newvendorListIds = new ArrayList();
      if (systemvendorlist != null) {
        for (int i = 0; i < systemvendorlist.size(); i++)
        {
          PublishToVendor sysuser = (PublishToVendor)systemvendorlist.get(i);
          

          newvendorListIds.add(String.valueOf(sysuser.getUserId()));
        }
      }
      List newvendorList = new ArrayList();
      String[] ids = StringUtils.tokenize(userids, ",");
      if (ids != null) {
        for (int i = 0; i < ids.length; i++) {
          if (!newvendorListIds.contains(ids[i]))
          {
            User user = this.usertxdao.getUserFullNameEmailAndUserIdById(new Long(ids[i]).longValue());
            PublishToVendor jbapp = new PublishToVendor();
            jbapp.setUserId(user.getUserId());
            jbapp.setUserName(user.getFirstName());
            jbapp.setIsFromSystemRule("N");
            jbapp.setJobreqId(jb.getJobreqId());
            jbapp.setCreatedBy(user1.getUserName());
            jbapp.setCreatedDate(new Date());
            newvendorList.add(jbapp);
          }
        }
      }
      this.jobreqtxdao.updateJobRequistion(jb);
      
      this.jobreqtxdao.savePublishToVendorList(newvendorList);
      

      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_UPDATE_PUBLISH_INFO", "");
      this.jobreqtxdao.saveActivity(activity);
      
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition closerequisition(JobRequisitionForm jbForm, User user1, String comment)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      jb.setStatus("Closed");
      jb.setClosedComment(comment);
      jb.setClosedByName(user1.getFirstName() + " " + user1.getLastName());
      jb.setClosedDate(new Date());
      jb.setClosedById(user1.getUserId());
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      
      this.jobreqtxdao.updateJobRequistion(jb);
      
      this.tasktxdao.deleteTaskTx(jb.getJobreqId(), Common.REQUISTION_APPROVAL_TASK);
      this.tasktxdao.deleteTaskTx(jb.getJobreqId(), Common.REQUISTION_PUBLISH_TASK);
      

      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_CLOSED", jb.getClosedComment());
      this.jobreqtxdao.saveActivity(activity);
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition deleterequisition(JobRequisitionForm jbForm, User user1, String comment)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      jb.setStatus("Deleted");
      jb.setDeleteComment(comment);
      jb.setDeleteByName(user1.getFirstName() + " " + user1.getLastName());
      jb.setDeleteDate(new Date());
      jb.setDeleteById(user1.getUserId());
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      
      this.jobreqtxdao.updateJobRequistion(jb);
      
      this.tasktxdao.deleteTaskTx(jb.getJobreqId(), Common.REQUISTION_APPROVAL_TASK);
      this.tasktxdao.deleteTaskTx(jb.getJobreqId(), Common.REQUISTION_PUBLISH_TASK);
      

      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_DELETED", jb.getDeleteComment());
      this.jobreqtxdao.saveActivity(activity);
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition rejectrequisition(JobRequisitionForm jbForm, User user1, String jbTmplApproverId, String comment)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      JobTemplateApprovers jobapprover = this.jobreqtxdao.getApproverByTmplApproverId(jbTmplApproverId);
      
      jobapprover.setApproved("R");
      jobapprover.setApprovedDate(new Date());
      jobapprover.setComment(comment);
      if ((!StringUtils.isNullOrEmpty(jobapprover.getIsGroup())) && (jobapprover.getIsGroup().equals("Y")))
      {
        jobapprover.setOnbehalfApproverId(user1.getUserId());
        jobapprover.setOnbehalfApproverName(user1.getFirstName() + " " + user1.getLastName());
      }
      jb.setCurrentOwnerId(jb.getHiringmgr().getUserId());
      jb.setIsGroup("N");
      jb.setCurrentOwnerName(jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName());
      
      this.jobreqtxdao.updateJobRequisionApprover(jobapprover);
      
      this.tasktxdao.markCompleteForGroup(jb.getJobreqId(), Common.REQUISTION_APPROVAL_TASK, user1.getUserName());
      
      jb.setState("In Approval-Rejected");
      jb.setIsrejected(1);
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      
      jb.setCurrentOwnerId(jb.getHiringmgr().getUserId());
      jb.setCurrentOwnerName(jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName());
      this.jobreqtxdao.updateJobRequistion(jb);
      

      saveActivity(user1, jb, "REQUISITION_APPROVAL_REJECTED", jobapprover.getComment(), true);
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition resetjobrequistion(JobRequisitionForm jbForm, User user1)
    throws Exception
  {
    logger.info("inside resetjobrequistion");
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      





      jb.setState("Draft");
      jb.setIsrejected(0);
      jb.setIsapprovalInitiated(0);
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      
      List approversList = this.jobreqtxdao.getJobRequisionTemplateApproversList(jb.getJobreqId(), "job");
      logger.info("approver list size : " + approversList.size());
      List newapproverlist = new ArrayList();
      int level = 1;
      for (int i = 0; i < approversList.size(); i++)
      {
        JobTemplateApprovers jbtmplapprover = (JobTemplateApprovers)approversList.get(i);
        if ((!StringUtils.isNullOrEmpty(jbtmplapprover.getIsFromSystemRule())) && (!jbtmplapprover.getIsFromSystemRule().equals("Y")))
        {
          JobTemplateApprovers jtemp = new JobTemplateApprovers();
          jtemp.setComment(null);
          jtemp.setApprovedDate(null);
          jtemp.setApproved("N");
          jtemp.setReassignedgraphname(jbtmplapprover.getReassignedgraphname());
          jtemp.setReassignedbyName(jbtmplapprover.getReassignedbyName());
          jtemp.setReassignedDate(jbtmplapprover.getReassignedDate());
          jtemp.setReassignedcomment(jbtmplapprover.getReassignedcomment());
          jtemp.setIsreassigned(jbtmplapprover.getIsreassigned());
          jtemp.setIsapprover("Y");
          jtemp.setIsGroup(jbtmplapprover.getIsGroup());
          jtemp.setApproverName(jbtmplapprover.getApproverName());
          jtemp.setJbTmplId(jbtmplapprover.getJbTmplId());
          jtemp.setType(jbtmplapprover.getType());
          jtemp.setUserId(jbtmplapprover.getUserId());
          jtemp.setLevelorder(level);
          jtemp.setIsFromSystemRule("N");
          level += 1;
          newapproverlist.add(jtemp);
        }
      }
      this.jobreqtxdao.deleteApprovers(jb.getJobreqId(), "job");
      this.jobreqtxdao.saveApproversList(newapproverlist, "job", jb.getJobreqId());
      
      jb.setCurrentOwnerId(user1.getUserId());
      jb.setCurrentOwnerName(user1.getFirstName() + " " + user1.getLastName());
      jb.setIsGroup("N");
      jb = this.jobreqtxdao.updateJobRequistionWithReturn(jb);
      

      saveActivity(user1, jb, "REQUISITION_RESET", "", false);
      
      commonLovPopulate(jbForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public List getRequistionAttachmentList(long reqId, String type)
    throws Exception
  {
    return this.jobreqtxdao.getAttachmentList(reqId, type);
  }
  
  public void saveRequistionAttachments(List newattachmentList, String type, long reqId)
  {
    this.jobreqtxdao.saveAttachments(newattachmentList, "reqattachment", reqId);
  }
  
  public JobRequisition recalljobrequistion(JobRequisitionForm jbForm, String comment, User user1)
    throws Exception
  {
    logger.info("inside recalljobrequistion");
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      





      jb.setState("Draft");
      jb.setIsrejected(0);
      jb.setIsapprovalInitiated(0);
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      
      List approversList = this.jobreqtxdao.getJobRequisionTemplateApproversList(jb.getJobreqId(), "job");
      logger.info("approver list size : " + approversList.size());
      List newapproverlist = new ArrayList();
      int level = 1;
      for (int i = 0; i < approversList.size(); i++)
      {
        JobTemplateApprovers jbtmplapprover = (JobTemplateApprovers)approversList.get(i);
        if ((!StringUtils.isNullOrEmpty(jbtmplapprover.getIsFromSystemRule())) && (!jbtmplapprover.getIsFromSystemRule().equals("Y")))
        {
          JobTemplateApprovers jtemp = new JobTemplateApprovers();
          jtemp.setComment(null);
          jtemp.setApprovedDate(null);
          jtemp.setApproved("N");
          jtemp.setReassignedgraphname(jbtmplapprover.getReassignedgraphname());
          jtemp.setReassignedbyName(jbtmplapprover.getReassignedbyName());
          jtemp.setReassignedDate(jbtmplapprover.getReassignedDate());
          jtemp.setReassignedcomment(jbtmplapprover.getReassignedcomment());
          jtemp.setIsreassigned(jbtmplapprover.getIsreassigned());
          jtemp.setIsapprover("Y");
          jtemp.setIsGroup(jbtmplapprover.getIsGroup());
          jtemp.setApproverName(jbtmplapprover.getApproverName());
          jtemp.setJbTmplId(jbtmplapprover.getJbTmplId());
          jtemp.setType(jbtmplapprover.getType());
          jtemp.setUserId(jbtmplapprover.getUserId());
          jtemp.setLevelorder(level);
          jtemp.setIsFromSystemRule("N");
          level += 1;
          newapproverlist.add(jtemp);
        }
      }
      this.jobreqtxdao.deleteApprovers(jb.getJobreqId(), "job");
      this.jobreqtxdao.saveApproversList(newapproverlist, "job", jb.getJobreqId());
      
      jb.setCurrentOwnerId(user1.getUserId());
      jb.setCurrentOwnerName(user1.getFirstName() + " " + user1.getLastName());
      jb.setIsGroup("N");
      
      jb = this.jobreqtxdao.updateJobRequistionWithReturn(jb);
      
      this.tasktxdao.deleteTaskTx(jb.getJobreqId(), Common.REQUISTION_APPROVAL_TASK);
      this.tasktxdao.deleteTaskTx(jb.getJobreqId(), Common.REQUISTION_PUBLISH_TASK);
      

      saveActivity(user1, jb, "REQUISITION_RECALLED", comment, false);
      
      commonLovPopulate(jbForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition saveasjobrequisition(JobRequisitionForm jbForm, HttpServletRequest request, String isCompetencyChecked, String isAccomplishmentChecked, String isApproversChecked, String isAttachmentsChecked, String jobreqName, String jobTitle, User user1)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTxByUUID(jbForm.getUuid());
      
      JobRequisition jbnew = new JobRequisition();
      jbnew.setSuper_user_key(user1.getSuper_user_key());
      jbnew.setStatus("Open");
      jbnew.setState("Draft");
      jbnew.setDeleteComment(null);
      jbnew.setDeleteByName(null);
      jbnew.setDeleteDate(null);
      jbnew.setDeleteById(0L);
      jbnew.setUpdatedBy(null);
      jbnew.setUpdatedDate(null);
      jbnew.setClosedComment(null);
      jbnew.setClosedByName(null);
      jbnew.setClosedDate(null);
      jbnew.setClosedById(0L);
      jbnew.setPublishedComment(null);
      jbnew.setPublishedByName(null);
      jbnew.setPublishedDate(null);
      jbnew.setPublishedById(0L);
      jbnew.setIsapprovalInitiated(0);
      jbnew.setApprovalInitiationComment(null);
      jbnew.setApprovalIniByName(null);
      jbnew.setApprovalIniDate(null);
      jbnew.setApprovalIniById(0L);
      jbnew.setCreatedBy(user1.getUserName());
      jbnew.setCreatedDate(new Date());
      jbnew.setIsrejected(0);
      
      long maxvalue = this.jobreqtxdao.getMaxValueFromRequistion(user1.getSuper_user_key());
      jbnew.setRequisition_number(maxvalue + 1L);
      
      List comptetencyList = this.jobreqtxdao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
      List accomplishmentList = this.jobreqtxdao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
      List approversList = this.jobreqtxdao.getJobRequisionTemplateApproversList(jb.getJobreqId(), "job");
      List attachmentList = this.jobreqtxdao.getAttachmentList(jb.getJobreqId(), "reqattachment");
      logger.info("1 ... attachmentList.size() : " + attachmentList.size());
      
      List newcomptetencyList = new ArrayList();
      if (comptetencyList != null) {
        for (int i = 0; i < comptetencyList.size(); i++)
        {
          JobTemplateCompetency jbtCompetencyold = (JobTemplateCompetency)comptetencyList.get(i);
          JobTemplateCompetency jobTemplateCompetency = new JobTemplateCompetency();
          jobTemplateCompetency.setJbTmplId(jbtCompetencyold.getJbTmplId());
          jobTemplateCompetency.setImportance(jbtCompetencyold.getImportance());
          jobTemplateCompetency.setCharName(jbtCompetencyold.getCharName());
          jobTemplateCompetency.setMandatory(jbtCompetencyold.getMandatory());
          jobTemplateCompetency.setType(jbtCompetencyold.getType());
          jobTemplateCompetency.setIsVisible(jbtCompetencyold.getIsVisible());
          
          newcomptetencyList.add(jobTemplateCompetency);
        }
      }
      List newaccomplishmentList = new ArrayList();
      if (accomplishmentList != null) {
        for (int i = 0; i < accomplishmentList.size(); i++)
        {
          JobTemplateAccomplishment jbtAccomplishmentold = (JobTemplateAccomplishment)accomplishmentList.get(i);
          JobTemplateAccomplishment jobTemplateAccomplishment = new JobTemplateAccomplishment();
          jobTemplateAccomplishment.setJbTmplId(jbtAccomplishmentold.getJbTmplId());
          jobTemplateAccomplishment.setImportance(jbtAccomplishmentold.getImportance());
          jobTemplateAccomplishment.setAccName(jbtAccomplishmentold.getAccName());
          jobTemplateAccomplishment.setMandatory(jbtAccomplishmentold.getMandatory());
          jobTemplateAccomplishment.setType(jbtAccomplishmentold.getType());
          newaccomplishmentList.add(jobTemplateAccomplishment);
        }
      }
      List newapproverslist = new ArrayList();
      if (approversList != null) {
        for (int i = 0; i < approversList.size(); i++)
        {
          JobTemplateApprovers jbtapproverold = (JobTemplateApprovers)approversList.get(i);
          if ((jbtapproverold.getIsFromSystemRule() != null) && (!jbtapproverold.getIsFromSystemRule().equals("Y")))
          {
            JobTemplateApprovers jbtapprover = new JobTemplateApprovers();
            jbtapprover.setJbTmplId(jbtapproverold.getJbTmplId());
            jbtapprover.setApproverName(jbtapproverold.getApproverName());
            jbtapprover.setLevelorder(jbtapproverold.getLevelorder());
            jbtapprover.setUserId(jbtapproverold.getUserId());
            jbtapprover.setType(jbtapproverold.getType());
            newapproverslist.add(jbtapprover);
          }
        }
      }
      List newattachmentList = new ArrayList();
      if (attachmentList != null) {
        for (int i = 0; i < attachmentList.size(); i++)
        {
          RequistionAttachments reqAttachmentsold = (RequistionAttachments)attachmentList.get(i);
          RequistionAttachments requistionAttachments = new RequistionAttachments();
          
          requistionAttachments.setReqId(reqAttachmentsold.getReqId());
          requistionAttachments.setAttahmentname(reqAttachmentsold.getAttahmentname());
          requistionAttachments.setCreatedBy(reqAttachmentsold.getCreatedBy());
          requistionAttachments.setCreatedDate(reqAttachmentsold.getCreatedDate());
          requistionAttachments.setType(reqAttachmentsold.getType());
          requistionAttachments.setAttahmentdetails(reqAttachmentsold.getAttahmentdetails());
          requistionAttachments.setUuid(reqAttachmentsold.getUuid());
          requistionAttachments.setVisibleInJobDetails(reqAttachmentsold.getVisibleInJobDetails());
          newattachmentList.add(requistionAttachments);
        }
      }
      jbnew.setCurrentOwnerId(jbForm.getCurrentOwnerId());
      jbnew.setCurrentOwnerName(jbForm.getCurrentOwnerName());
      jbnew.setTemplateId(jb.getTemplateId());
      jbnew.setTemplateName(jb.getTemplateName());
      jbnew.setIsnewPositions(jb.getIsnewPositions());
      jbnew.setIsnewpositionno(jb.getIsnewpositionno());
      jbnew.setJobreqName(jobreqName);
      jbnew.setJobTitle(jobTitle);
      jbnew.setJobreqDesc(jb.getJobreqDesc());
      jbnew.setJobRoles(jb.getJobRoles());
      jbnew.setJobPosition(jb.getJobPosition());
      jbnew.setJobDetails(jb.getJobDetails());
      jbnew.setJobInstructions(jb.getJobInstructions());
      jbnew.setInternal(jb.getInternal());
      jbnew.setDurationinmonths(jb.getDurationinmonths());
      jbnew.setMinyearsofExpRequired(jb.getMinyearsofExpRequired());
      jbnew.setMaxyearsofExpRequired(jb.getMaxyearsofExpRequired());
      jbnew.setMinimumLevelOfEducation(jb.getMinimumLevelOfEducation());
      jbnew.setOtherMinimumLevelOfEducation(jb.getOtherMinimumLevelOfEducation());
      jbnew.setMinimumLevelOfEducation(jb.getMinimumLevelOfEducation());
      jbnew.setOtherExperience(jb.getOtherExperience());
      jbnew.setDefaultStandardHours(jb.getDefaultStandardHours());
      jbnew.setStdworkinghoursunitName(jb.getStdworkinghoursunitName());
      jbnew.setNumberOfOpening(jb.getNumberOfOpening());
      jbnew.setNumberOfOpeningRemain(jb.getNumberOfOpeningRemain());
      jbnew.setIsnewPositions(jbForm.getIsnewPositions());
      jbnew.setTargetfinishdate(jb.getTargetfinishdate());
      jbnew.setRecruiterId(jb.getRecruiterId());
      jbnew.setRecruiterName(jb.getRecruiterName());
      jbnew.setIsgrouprecruiter(jb.getIsgrouprecruiter());
      jbnew.setIsnewpositionno(jb.getIsnewpositionno());
      jbnew.setUuid(UUID.randomUUID().toString());
      jbnew.setOrganization(jb.getOrganization());
      



      jbnew.setCurrentOwnerId(user1.getUserId());
      jbnew.setCurrentOwnerName(user1.getFirstName() + " " + user1.getLastName());
      jbnew.setIsGroup("N");
      
      Location location1 = jb.getLocation();
      
      jbnew.setDepartment(jb.getDepartment());
      

      jbnew.setProjectcode(jb.getProjectcode());
      

      jbnew.setLocation(location1);
      
      jbnew.setCompfrequency(jb.getCompfrequency());
      
      jbnew.setJobtype(jb.getJobtype());
      
      jbnew.setWorkshift(jb.getWorkshift());
      
      jbnew.setFlsa(jb.getFlsa());
      
      jbnew.setHiringmgr(jb.getHiringmgr());
      

      jbnew.setJobgrade(jb.getJobgrade());
      
      jbnew.setSalaryplan(jb.getSalaryplan());
      
      jbnew.setBudgetcode(jb.getBudgetcode());
      
      jbnew.setCatagory(jb.getCatagory());
      


      this.jobreqtxdao.saveJobRequistion(jbnew);
      

      String jobreqcode = "";
      Organization org = BOFactory.getOrganizationBO().getParentOrganizationInfo(user1.getSuper_user_key());
      if ((!StringUtils.isNullOrEmpty(org.getOrgName())) && (org.getOrgName().length() > 2)) {
        jobreqcode = jobreqcode + org.getOrgName().substring(0, 3);
      } else if (!StringUtils.isNullOrEmpty(org.getOrgName())) {
        jobreqcode = jobreqcode + org.getOrgName();
      }
      jobreqcode = jobreqcode + "-" + jbnew.getSuper_user_key();
      
      jobreqcode = jobreqcode + "-" + jbnew.getRequisition_number();
      
      jbnew.setJobreqcode(jobreqcode);
      
      this.jobreqtxdao.updateJobRequistion(jbnew);
      
      logger.info("jbnew.getJobreqId() : " + jbnew.getJobreqId());
      if ((!StringUtils.isNullOrEmpty(isCompetencyChecked)) && (isCompetencyChecked.equals("true"))) {
        this.jobreqtxdao.saveJobReqCompetency(newcomptetencyList, "job", jbnew.getJobreqId());
      }
      if ((!StringUtils.isNullOrEmpty(isAccomplishmentChecked)) && (isAccomplishmentChecked.equals("true"))) {
        this.jobreqtxdao.saveJobReqAccomplishments(newaccomplishmentList, "job", jbnew.getJobreqId());
      }
      if ((!StringUtils.isNullOrEmpty(isApproversChecked)) && (isApproversChecked.equals("true"))) {
        this.jobreqtxdao.saveApproversList(newapproverslist, "job", jbnew.getJobreqId());
      }
      if ((!StringUtils.isNullOrEmpty(isAttachmentsChecked)) && (isAttachmentsChecked.equals("true"))) {
        this.jobreqtxdao.saveAttachments(newattachmentList, "reqattachment", jbnew.getJobreqId());
      }
      commonLovPopulate(jbForm, jbnew, user1);
      
      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_CREATED", "");
      this.jobreqtxdao.saveActivity(activity);
      
      return jbnew;
    }
    catch (Exception e)
    {
      logger.info("Exception on saveasrequistion", e);
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition reassignreq(JobRequisitionForm jbForm, User user1, String jbTmplApproverId, String reassignedto, String reassignedtoname, String isgroup, String comment)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      JobTemplateApprovers jobapprover = this.jobreqtxdao.getApproverByTmplApproverId(jbTmplApproverId);
      

      jobapprover.setIsapprover("Y");
      jobapprover.setIsreassigned("Y");
      jobapprover.setReassignedBy(user1.getUserId());
      jobapprover.setReassignedbyName(user1.getUserName());
      jobapprover.setReassignedDate(new Date());
      String reassignedgraph = "";
      String reassignedgraphname = "";
      if (StringUtils.isNullOrEmpty(jobapprover.getReassignedgraph())) {
        reassignedgraph = user1.getUserId() + "->" + reassignedto;
      } else {
        reassignedgraph = jobapprover.getReassignedgraph() + "->" + reassignedto;
      }
      jobapprover.setReassignedgraph(reassignedgraph);
      if (StringUtils.isNullOrEmpty(jobapprover.getReassignedgraphname())) {
        reassignedgraphname = user1.getFirstName() + " " + user1.getLastName() + "->" + reassignedtoname;
      } else {
        reassignedgraphname = jobapprover.getReassignedgraphname() + "->" + reassignedtoname;
      }
      jobapprover.setReassignedgraphname(reassignedgraphname);
      
      jobapprover.setApprovedDate(null);
      jobapprover.setReassignedcomment(comment);
      jobapprover.setUserId(new Long(reassignedto).longValue());
      jobapprover.setApproverName(reassignedtoname);
      if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y"))) {
        jobapprover.setIsGroup("Y");
      } else {
        jobapprover.setIsGroup("N");
      }
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      jobapprover = this.jobreqtxdao.updateJobRequisionApproverWithReturn(jobapprover);
      


      this.tasktxdao.markCompleteForGroup(jb.getJobreqId(), Common.REQUISTION_APPROVAL_TASK, user1.getUserName());
      if ((!StringUtils.isEmpty(jobapprover.getIsGroup())) && (jobapprover.getIsGroup().equals("Y")))
      {
        logger.info("is group true" + jobapprover.getUserId());
        
        UserGroup usrgrp = this.lovopstxdao.getUserGroupTx(jobapprover.getUserId());
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            TaskData task = new TaskData();
            setTaskDataReAssignReq(jb, Common.REQUISTION_APPROVAL_TASK, user1, touser, task);
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
          
          TaskBO.sendTaskAssignEmail(user1, taskList, comment);
        }
      }
      else
      {
        TaskData task = new TaskData();
        setTaskDataReAssignReq(jb, Common.REQUISTION_APPROVAL_TASK, user1, jobapprover.getUserId(), jobapprover.getApproverName(), task);
        
        this.tasktxdao.saveTask(task);
        
        TaskBO.sendTaskAssignEmail(user1, task, comment);
      }
      jb.setCurrentOwnerId(jobapprover.getUserId());
      if ((!StringUtils.isEmpty(jobapprover.getIsGroup())) && (jobapprover.getIsGroup().equals("Y"))) {
        jb.setIsGroup("Y");
      } else {
        jb.setIsGroup("N");
      }
      jb.setCurrentOwnerName(getCurrentOwnerName(jobapprover.getUserId(), jb.getIsGroup()));
      this.jobreqtxdao.updateJobRequistion(jb);
      


      saveActivity(user1, jb, "REQUISITION_APPROVER_REASSIGNED", jobapprover.getReassignedcomment(), true);
      
      commonLovPopulate(jbForm, jb, user1);
      
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  private void setTaskData(JobRequisition jb, String tasktype, User user1, User touser, TaskData task)
  {
    task.setTaskname(jb.getJobreqName());
    task.setIdvalue(jb.getJobreqId());
    task.setTasktype(tasktype);
    task.setAssignedbyUserId(user1.getUserId());
    task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
    task.setAssignedtoUserId(touser.getUserId());
    task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
    task.setCreatedBy(user1.getCreatedBy());
    task.setCreatedDate(new Date());
    task.setStatus("A");
    task.setUuid(jb.getUuid());
  }
  
  private void setTaskData(JobRequisition jb, String tasktype, User user1, long toId, String toName, TaskData task)
  {
    task.setTaskname(jb.getJobreqName());
    task.setIdvalue(jb.getJobreqId());
    task.setTasktype(tasktype);
    task.setAssignedbyUserId(user1.getUserId());
    task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
    task.setAssignedtoUserId(toId);
    task.setAssignedtoUserName(toName);
    task.setCreatedBy(user1.getCreatedBy());
    task.setCreatedDate(new Date());
    task.setStatus("A");
    task.setUuid(jb.getUuid());
  }
  
  private void setTaskDataReAssignReq(JobRequisition jb, String tasktype, User user1, User touser, TaskData task)
  {
    task.setTaskname(jb.getJobreqName());
    task.setIdvalue(jb.getJobreqId());
    task.setTasktype(tasktype);
    task.setAssignedbyUserId(jb.getApprovalIniById());
    task.setAssignedbyUserName(jb.getApprovalIniByName());
    task.setAssignedtoUserId(touser.getUserId());
    task.setAssignedtoUserName(touser.getFirstName() + " " + touser.getLastName());
    
    task.setCreatedDate(jb.getApprovalIniDate());
    task.setStatus("A");
    task.setUuid(jb.getUuid());
    task.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
    task.setUpdatedDate(new Date());
  }
  
  private void setTaskDataReAssignReq(JobRequisition jb, String tasktype, User user1, long toId, String toName, TaskData task)
  {
    task.setTaskname(jb.getJobreqName());
    task.setIdvalue(jb.getJobreqId());
    task.setTasktype(tasktype);
    task.setAssignedbyUserId(jb.getApprovalIniById());
    task.setAssignedbyUserName(jb.getApprovalIniByName());
    task.setAssignedtoUserId(toId);
    task.setAssignedtoUserName(toName);
    
    task.setCreatedDate(jb.getApprovalIniDate());
    task.setStatus("A");
    task.setUuid(jb.getUuid());
    task.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
    task.setUpdatedDate(new Date());
  }
  
  public JobRequisition updatejobreq(HttpServletRequest request, ActionErrors errors, JobRequisitionForm jbForm, JobRequisition jb, User user1, String isInternal, String isNewPosition, String targetfinishdate, String recruiterId, String isgrouprecruiter, String hiringMgrId, int noofpositionFilled)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    try
    {
      String hrmgrchange = "";
      String recruiterchange = "";
      

      long olfhrringmgrid = 0L;
      String oldhrmgrname = "";
      if (jb.getHiringmgr() != null)
      {
        olfhrringmgrid = jb.getHiringmgr().getUserId();
        oldhrmgrname = jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName();
      }
      long oldrecruiterid = jb.getRecruiterId();
      String oldrecruitername = jb.getRecruiterName();
      String oldisrecruitergroup = jb.getIsgrouprecruiter();
      logger.info("Recruiter Id : " + recruiterId);
      logger.info("hiringMgrId Id : " + hiringMgrId);
      jbForm.recruiterId = new Long(recruiterId).longValue();
      if (StringUtils.isNullOrEmpty(isgrouprecruiter)) {
        isgrouprecruiter = "N";
      }
      jbForm.isgrouprecruiter = isgrouprecruiter;
      logger.info("isgrouprecruiter : " + isgrouprecruiter);
      jbForm.toValue(jb, request);
      if (!StringUtils.isNullOrEmpty(hiringMgrId))
      {
        User hiringmgr = new User();
        hiringmgr.setUserId(new Long(hiringMgrId).longValue());
        jb.setHiringmgr(hiringmgr);
      }
      if ((jb.getHiringmgr() != null) && 
        (jb.getHiringmgr().getUserId() != olfhrringmgrid))
      {
        User us = this.usertxdao.getUserFullNameEmailAndUserIdById(jb.getHiringmgr().getUserId());
        hrmgrchange = oldhrmgrname + "-->" + us.getFirstName() + " " + us.getLastName();
      }
      if ((isRecruiterChanged(oldrecruiterid, oldisrecruitergroup, jb.getRecruiterId(), isgrouprecruiter)) && (oldrecruiterid != 0L)) {
        recruiterchange = oldrecruitername + "-->" + jb.getRecruiterName();
      }
      jb.setIsnewPositions(isNewPosition);
      if ((!StringUtils.isNullOrEmpty(jbForm.getEducationName())) && (!jbForm.getEducationName().equals("Other"))) {
        jb.setOtherMinimumLevelOfEducation("");
      }
      if ((!StringUtils.isNullOrEmpty(isNewPosition)) && (isNewPosition.equals("Y"))) {
        jb.setIsnewpositionno("");
      }
      jb.setInternal(isInternal);
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      
      jb.setNumberOfOpeningRemain(jb.getNumberOfOpening());
      
      boolean isError = false;
      

      List errorList = BOFactory.getBusinessFilterBO().isRequisitionMandatoryValueSucceeed(jb, user1, "REQUISITION_SCREEN");
      if ((errorList != null) && (errorList.size() > 0))
      {
        request.setAttribute("error_list", errorList);
        isError = true;
        jbForm.setTabselected(1);
      }
      if ((jbForm.getErrorList() != null) && (jbForm.getErrorList().size() > 0))
      {
        errorList.addAll(jbForm.getErrorList());
        if (!isError) {
          jbForm.setTabselected(7);
        }
        isError = true;
        request.setAttribute("error_list", errorList);
      }
      if (!isError)
      {
        String isBudgetValidation = Constant.getValue("budget.validation.requisition");
        if ((!StringUtils.isNullOrEmpty(isBudgetValidation)) && (isBudgetValidation.equalsIgnoreCase("yes")))
        {
          isError = validateBudget(jbForm.getBudgetcodeId(), jb, request, errors);
          if (isError)
          {
            String msg = Constant.getResourceStringValue("Requisition.budgetrangemsg", user1.getLocale());
            errorList.add(msg);
            isError = true;
            jbForm.setTabselected(1);
            request.setAttribute("error_list", errorList);
          }
        }
      }
      if (isError) {
        request.setAttribute("saverequisition", "no");
      }
      if (!isError)
      {
        if (!StringUtils.isNullOrEmpty(targetfinishdate))
        {
          String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
          String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(targetfinishdate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
          
          logger.info("converteddate" + converteddate);
          
          Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
          
          jb.setTargetfinishdate(cal.getTime());
        }
        if ((jb.getState() != null) && (jb.getState().equals("Active")))
        {
          if (!StringUtils.isNullOrEmpty(recruiterchange))
          {
            jb.setCurrentOwnerId(jb.getRecruiterId());
            jb.setCurrentOwnerName(getCurrentOwnerName(new Long(jb.getRecruiterId()).longValue(), isgrouprecruiter));
            if ((isgrouprecruiter != null) && (isgrouprecruiter.equals("Y"))) {
              jb.setIsGroup("Y");
            } else {
              jb.setIsGroup("N");
            }
          }
          else if ((jb.getRecruiterId() < 1L) && 
            (!StringUtils.isNullOrEmpty(hrmgrchange)))
          {
            jb.setCurrentOwnerId(new Long(hiringMgrId).longValue());
            jb.setCurrentOwnerName(getCurrentOwnerName(new Long(hiringMgrId).longValue(), "N"));
            jb.setIsGroup("N");
          }
        }
        else if (!StringUtils.isNullOrEmpty(hrmgrchange))
        {
          jb.setCurrentOwnerId(new Long(hiringMgrId).longValue());
          jb.setCurrentOwnerName(getCurrentOwnerName(new Long(hiringMgrId).longValue(), "N"));
          jb.setIsGroup("N");
        }
        jb.setIsindexSearchApplied(0);
        



        jb.setNumberOfOpeningRemain(jb.getNumberOfOpening() - noofpositionFilled);
        
        this.jobreqtxdao.updateJobRequistionWithClear(jb);
        
        request.setAttribute("saverequisition", "yes");
        if ((jbForm.getFormVariableDataList() != null) && (jbForm.getFormVariableDataList().size() > 0))
        {
          this.variabletxdao.deleteVariableValues(jb.getJobreqId(), "REQUISITION_FORM");
          this.variabletxdao.saveVariableValues(jbForm.getFormVariableDataList());
        }
        RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_UPDATED", "");
        if (!StringUtils.isNullOrEmpty(hrmgrchange))
        {
          activity.setComment(hrmgrchange);
          activity.setActivityName("REQUISITION_HIRING_MGR_UPDATED");
        }
        if (!StringUtils.isNullOrEmpty(recruiterchange))
        {
          activity.setComment(recruiterchange);
          activity.setActivityName("REQUISITION_RECRUITER_UPDATED");
        }
        if ((!StringUtils.isNullOrEmpty(hrmgrchange)) && (!StringUtils.isNullOrEmpty(recruiterchange)))
        {
          activity.setComment(hrmgrchange);
          activity.setActivityName("REQUISITION_HIRING_MGR_UPDATED");
          this.jobreqtxdao.saveActivity(activity);
          activity = populateActivityData(user1, jb, "REQUISITION_UPDATED", "");
          activity.setComment(recruiterchange);
          activity.setActivityName("REQUISITION_RECRUITER_UPDATED");
        }
        this.jobreqtxdao.saveActivity(activity);
      }
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  private boolean validateBudget(long budgetCodeid, JobRequisition jb, HttpServletRequest request, ActionErrors errors)
    throws Exception
  {
    logger.info("inside validateBudget, budgetCodeid" + budgetCodeid);
    boolean isErrror = false;
    try
    {
      if (budgetCodeid == 0L) {
        return false;
      }
      BudgetCode budgetCode = this.lovopstxdao.getBudgetCode(String.valueOf(budgetCodeid));
      List jobreqList = this.lovopstxdao.getTotalAllRequistionsByBudgetCodeId(budgetCodeid);
      long totalamountused = 0L;
      for (int i = 0; i < jobreqList.size(); i++)
      {
        JobRequisition jobreq = (JobRequisition)jobreqList.get(i);
        if (jobreq.getJobreqId() != jb.getJobreqId()) {
          if ((!StringUtils.isNullOrEmpty(jobreq.getIsnewPositions())) && (jobreq.getIsnewPositions().equals("Y")))
          {
            int noofpositions = jobreq.getNumberOfOpening();
            long highsalary = jobreq.getSalaryplan() == null ? 0L : jobreq.getSalaryplan().getToRangeAmount();
            totalamountused += noofpositions * highsalary;
          }
        }
      }
      if (jb.getSalaryplan() != null)
      {
        SalaryPlan sl = this.lovopstxdao.getSalaryPlan(jb.getSalaryplan().getSalaryplanId());
        if ((!StringUtils.isNullOrEmpty(jb.getIsnewPositions())) && (jb.getIsnewPositions().equals("Y")))
        {
          int noofpositions = jb.getNumberOfOpening();
          long highsalary = sl.getToRangeAmount();
          totalamountused += noofpositions * highsalary;
        }
      }
      if (totalamountused > budgetCode.getBudgetamount()) {
        isErrror = true;
      }
    }
    catch (Exception e)
    {
      throw e;
    }
    return isErrror;
  }
  
  public JobRequisition saverecruiterajax(JobRequisitionForm jbForm, User user1, String recruiterId, String recruiterName, String isgrouprecruiter)
    throws ObjectNotFoundException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      String recruiterchange = "";
      long oldrecruiterid = jb.getRecruiterId();
      String oldrecruitername = jb.getRecruiterName();
      String oldisrecruitergroup = jb.getIsgrouprecruiter();
      jb.setRecruiterId(new Long(recruiterId).longValue());
      logger.info(" Recruiter Name : " + jbForm.getRecruiterName());
      jb.setRecruiterName(recruiterName);
      if ((!StringUtils.isNullOrEmpty(isgrouprecruiter)) && (isgrouprecruiter.equals("Y"))) {
        jb.setIsgrouprecruiter("Y");
      } else {
        jb.setIsgrouprecruiter("N");
      }
      if ((isRecruiterChanged(oldrecruiterid, oldisrecruitergroup, jb.getRecruiterId(), jb.getIsgrouprecruiter())) && (oldrecruiterid != 0L)) {
        recruiterchange = oldrecruitername + "-->" + jb.getRecruiterName();
      }
      logger.info(" jb.getRecruiterId() : " + jb.getRecruiterId());
      logger.info(" jb.getIsgrouprecruiter() satya : " + jb.getIsgrouprecruiter());
      logger.info(" oldrecruiterid : " + oldrecruiterid);
      if (isRecruiterChanged(oldrecruiterid, oldisrecruitergroup, jb.getRecruiterId(), jb.getIsgrouprecruiter())) {
        if ((!StringUtils.isNullOrEmpty(jb.getStatus())) && (jb.getStatus().equalsIgnoreCase("Open")) && (!StringUtils.isNullOrEmpty(jb.getState())) && (jb.getState().equalsIgnoreCase("Active")))
        {
          jb.setCurrentOwnerId(jb.getRecruiterId());
          jb.setIsGroup(jb.getIsgrouprecruiter());
          jb.setCurrentOwnerName(getCurrentOwnerName(jb.getRecruiterId(), jb.getIsgrouprecruiter()));
        }
        else if ((!StringUtils.isNullOrEmpty(jb.getStatus())) && (jb.getStatus().equalsIgnoreCase("Open")) && (!StringUtils.isNullOrEmpty(jb.getState())) && (jb.getState().equalsIgnoreCase("Approved")))
        {
          this.tasktxdao.markCompleteForGroup(jb.getJobreqId(), Common.REQUISTION_PUBLISH_TASK, user1.getUserName());
          
          setTaskForRecruiter(jb, user1, Common.REQUISTION_PUBLISH_TASK, "", true);
          

          jb.setCurrentOwnerId(jb.getRecruiterId());
          jb.setIsGroup(jb.getIsgrouprecruiter());
          jb.setCurrentOwnerName(getCurrentOwnerName(jb.getRecruiterId(), jb.getIsgrouprecruiter()));
        }
      }
      this.jobreqtxdao.updateJobRequistion(jb);
      

      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_RECRUITER_ADDED", "");
      activity.setComment(recruiterchange);
      activity.setAssignedTouserId(new Long(recruiterId).longValue());
      activity.setAssignedToUserName(recruiterName);
      activity.setIsgroup(jb.getIsgrouprecruiter());
      if (!StringUtils.isNullOrEmpty(recruiterchange)) {
        activity.setActivityName("REQUISITION_RECRUITER_UPDATED");
      }
      this.jobreqtxdao.saveActivity(activity);
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
    return jb;
  }
  
  private boolean isRecruiterChanged(long oldrecruiterid, String oldisrecruitergroup, long recruiterid, String isrecruitergroup)
  {
    boolean success = false;
    if (oldisrecruitergroup == null) {
      oldisrecruitergroup = "";
    }
    if (isrecruitergroup == null) {
      isrecruitergroup = "";
    }
    if (oldrecruiterid != recruiterid) {
      success = true;
    } else if ((oldrecruiterid == recruiterid) && (!oldisrecruitergroup.equals(isrecruitergroup))) {
      success = true;
    }
    return success;
  }
  
  private void setTaskForRecruiter(JobRequisition jb, User user1, String taskType, String comment, boolean isTaskToHringMgrIfRecruterNotExist)
    throws Exception
  {
    if (jb.getRecruiterId() > 0L)
    {
      if ((!StringUtils.isNullOrEmpty(jb.getIsgrouprecruiter())) && (jb.getIsgrouprecruiter().equals("Y")))
      {
        UserGroup usrgrp = this.lovopstxdao.getUserGroupTx(jb.getRecruiterId());
        Set users = usrgrp.getUsers();
        List<TaskData> taskList = new ArrayList();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            
            TaskData task = new TaskData();
            setTaskData(jb, taskType, user1, touser, task);
            taskList.add(task);
          }
          this.tasktxdao.saveAllTask(taskList);
          
          TaskBO.sendTaskAssignEmail(user1, taskList, comment);
        }
      }
      else
      {
        TaskData tasknew = new TaskData();
        setTaskData(jb, taskType, user1, jb.getRecruiterId(), jb.getRecruiterName(), tasknew);
        this.tasktxdao.saveTask(tasknew);
        
        TaskBO.sendTaskAssignEmail(user1, tasknew, comment);
      }
    }
    else if (isTaskToHringMgrIfRecruterNotExist)
    {
      TaskData tasknew = new TaskData();
      setTaskData(jb, taskType, user1, jb.getHiringmgr().getUserId(), jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName(), tasknew);
      this.tasktxdao.saveTask(tasknew);
      
      TaskBO.sendTaskAssignEmail(user1, tasknew, comment);
    }
  }
  
  public JobRequisition deleteattachment(JobRequisitionForm jbForm, User user1, String uuid)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      RequistionAttachments attach = this.jobreqtxdao.getRequistionAttachment(uuid);
      String attachmentname = "";
      if (attach != null) {
        attachmentname = attach.getAttahmentname();
      }
      this.jobreqtxdao.deleteAttachment(uuid);
      try
      {
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "reqattachment" + File.separator + attach.getUuid() + File.separator;
        File file = new File(filePath);
        if (file.isDirectory())
        {
          String[] files = file.list();
          if ((files != null) && (files.length > 0)) {
            for (int i = 0; i < files.length; i++)
            {
              String f1name = files[i];
              File f1 = new File(filePath + f1name);
              if (f1.exists()) {
                f1.delete();
              }
            }
          } else if (file.exists()) {
            file.delete();
          }
        }
        if (file.exists()) {
          file.delete();
        }
      }
      catch (Exception e)
      {
        logger.info("Exception on delete attachment file" + e.getMessage());
      }
      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_ATTACHMENT_DELETED", attachmentname);
      
      this.jobreqtxdao.saveActivity(activity);
      
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (ObjectNotFoundException e)
    {
      rollBack();
      throw new ObjectNotFoundException(e.getMessage());
    }
    catch (ValidationException e)
    {
      rollBack();
      throw new ValidationException(e.getMessage());
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition addattachment(JobRequisitionForm jbForm, JobRequisition jb, String attahmentdetails, String visibleInJobDetails, User user1)
    throws Exception
  {
    try
    {
      jb = this.jobreqtxdao.updateJobRequistionWithReturn(jb);
      
      FormFile myFile = jbForm.getAttachmentdata();
      if (myFile != null)
      {
        String contentType = myFile.getContentType();
        String fileName = myFile.getFileName();
        int fileSize = myFile.getFileSize();
        byte[] fileData = myFile.getFileData();
        if (!StringUtils.isNullOrEmpty(fileName))
        {
          RequistionAttachments appattach = new RequistionAttachments();
          appattach.setAttahmentname(fileName);
          Blob blob = null;
          
          blob = new SerialBlob(fileData);
          


          appattach.setAttahmentdetails(attahmentdetails);
          appattach.setReqId(jbForm.getJobreqId());
          appattach.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
          appattach.setCreatedDate(new Date());
          appattach.setType("reqattachment");
          appattach.setUuid(UUID.randomUUID().toString());
          appattach.setVisibleInJobDetails(visibleInJobDetails);
          this.jobreqtxdao.saveAttachment(appattach);
          



          String filePath = Constant.getValue("ATTACHMENT_PATH");
          filePath = filePath + File.separator + "reqattachment" + File.separator + appattach.getUuid() + File.separator;
          File file = new File(filePath);
          if (!file.exists()) {
            file.mkdirs();
          }
          RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
          

          int length = (int)blob.length();
          byte[] _blob = blob.getBytes(1L, length);
          raf.write(_blob);
          raf.close();
          



          RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_ATTACHMENT_ADDED", appattach.getAttahmentname());
          
          this.jobreqtxdao.saveActivity(activity);
        }
      }
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition addapprover(JobRequisitionForm jbForm, JobRequisition jb, User user1, JobTemplateApprovers jbtapprover, String isgroup)
    throws Exception
  {
    try
    {
      jb = this.jobreqtxdao.updateJobRequistionWithReturn(jb);
      
      int level = this.jobreqtxdao.getMaxLevelForApprover(jbForm.getJobreqId(), jbtapprover.getType());
      jbtapprover.setLevelorder(level + 1);
      if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y"))) {
        jbtapprover.setIsGroup("Y");
      } else {
        jbtapprover.setIsGroup("N");
      }
      this.jobreqtxdao.saveJobApprover(jbtapprover);
      

      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_APPROVAER_ADDED", jbtapprover.getApproverName());
      
      this.jobreqtxdao.saveActivity(activity);
      
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public JobRequisition deleteApprover(JobRequisitionForm jbForm, User user1, String id)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      
      JobTemplateApprovers app = this.jobreqtxdao.getApprover(id);
      

      this.jobreqtxdao.deleteApprover(jb.getJobreqId(), app.getJbTmplApproverId());
      

      RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_APPROVAER_DELETED", app.getApproverName());
      
      this.jobreqtxdao.saveActivity(activity);
      
      commonLovPopulate(jbForm, jb, user1);
      return jb;
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
  }
  
  public void copytemplatetojobreq(JobRequisition jb, List comptetencyList, List accomplishmentList, List approverslist, List formVariablesList, List variableValueList, User user1)
    throws Exception
  {
    logger.info("copytemplatetojobreq 0" + this.jobreqtxdao);
    
    logger.info(jb.getJobreqName());
    this.jobreqtxdao.saveJobRequistion(jb);
    
    logger.info("copytemplatetojobreq 1" + jb.getOrganization());
    String jobreqcode = "";
    

    Organization org = BOFactory.getOrganizationBO().getParentOrganizationInfo(user1.getSuper_user_key());
    if ((!StringUtils.isNullOrEmpty(org.getOrgName())) && (org.getOrgName().length() > 2)) {
      jobreqcode = jobreqcode + org.getOrgName().substring(0, 3);
    } else if (!StringUtils.isNullOrEmpty(org.getOrgName())) {
      jobreqcode = jobreqcode + org.getOrgName();
    }
    jobreqcode = jobreqcode + "-" + jb.getSuper_user_key();
    
    jobreqcode = jobreqcode + "-" + jb.getRequisition_number();
    
    jb.setJobreqcode(jobreqcode);
    
    this.jobreqtxdao.updateJobRequistion(jb);
    if ((formVariablesList != null) && (formVariablesList.size() > 0))
    {
      this.variabletxdao.deleteFormVariableMap(jb.getJobreqId(), "REQUISITION_FORM");
      this.variabletxdao.saveFormVariableMap(formVariablesList, "REQUISITION_FORM", jb.getJobreqId());
    }
    if ((variableValueList != null) && (variableValueList.size() > 0))
    {
      this.variabletxdao.deleteVariableValues(jb.getJobreqId(), "REQUISITION_FORM");
      this.variabletxdao.saveVariableValues(variableValueList, "REQUISITION_FORM", jb.getJobreqId());
    }
    if ((comptetencyList != null) && (comptetencyList.size() > 0))
    {
      this.jobreqtxdao.deleteCompetencies(jb.getJobreqId(), "job");
      this.jobreqtxdao.saveJobReqCompetency(comptetencyList, "job", jb.getJobreqId());
    }
    if ((accomplishmentList != null) && (accomplishmentList.size() > 0))
    {
      this.jobreqtxdao.deleteAccomplishments(jb.getJobreqId(), "job");
      this.jobreqtxdao.saveJobReqAccomplishments(accomplishmentList, "job", jb.getJobreqId());
    }
    if ((approverslist != null) && (approverslist.size() > 0))
    {
      this.jobreqtxdao.deleteApprovers(jb.getJobreqId(), "job");
      this.jobreqtxdao.saveApproversList(approverslist, "job", jb.getJobreqId());
    }
    if (jb.getTemplateId() != 0L) {
      BOFactory.getBusinessRuleBO().copyApplicantFiltersFromTemplateToRequisition(jb.getTemplateId(), jb.getJobreqId(), user1);
    }
    if (jb.getTemplateId() != 0L) {
      copyWatchListTemplateToReq(jb.getTemplateId(), jb.getJobreqId(), user1);
    }
    logger.info("copytemplatetojobreq 3 end");
    
    RequisitionActivity activity = populateActivityData(user1, jb, "REQUISITION_CREATED", "");
    this.jobreqtxdao.saveActivity(activity);
  }
  
  private void copyWatchListTemplateToReq(long templateId, long reqId, User user)
  {
    List watchlist = BOFactory.getLovBO().getWatchList(templateId, 0L, "TEMPLATE");
    List watchlistnew = new ArrayList();
    for (int i = 0; i < watchlist.size(); i++)
    {
      WatchList watch = (WatchList)watchlist.get(i);
      WatchList watch1 = new WatchList();
      watch1.setIdvalue(reqId);
      watch1.setType("REQ");
      watch1.setUserUserGrpId(watch.getUserUserGrpId());
      watch1.setUserUserGrpName(watch.getUserUserGrpName());
      watch1.setIsGroup(watch.getIsGroup());
      watch1.setCreatedBy(user.getUserName());
      watch1.setCreatedDate(new Date());
      watchlistnew.add(watch1);
    }
    BOFactory.getLovBO().copyWatchListTemplateToReq(watchlistnew, reqId);
  }
  
  private RequisitionActivity populateActivityData(User user1, JobRequisition jb, String activityName, String comment)
  {
    RequisitionActivity activity = new RequisitionActivity();
    activity.setReqId(jb.getJobreqId());
    activity.setReqName(jb.getJobreqName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(jb.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment(comment);
    activity.setCreatedByType(user1.getRole().getRoleName());
    activity.setActivityName(activityName);
    activity.setIsDisplayAppPage("Y");
    return activity;
  }
  
  public JobRequisition editJobRequistion(User user1, JobRequisitionForm jbForm)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequisionTx(jbForm.getJobreqId());
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      logger.info("recruiter name in edit : " + jb.getRecruiterName());
      
      commonLovPopulate(jbForm, jb, user1);
    }
    catch (ObjectNotFoundException e)
    {
      throw e;
    }
    catch (ValidationException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw e;
    }
    return jb;
  }
  
  public RequistionExamQnsAssign assignExam(String jobreqid, int examsetid, double passpercentage, String comment, User user1, JobRequisitionForm jbForm)
    throws Exception
  {
    JobRequisition jb = null;
    RequistionExamQnsAssign jbrqnse = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequision(jobreqid);
      
      jbrqnse = this.jobreqtxdao.getRequistionExamQnsAssign(jb.getJobreqId());
      if (jbrqnse == null)
      {
        jbrqnse = new RequistionExamQnsAssign();
        jbrqnse.setJobreqid(jb.getJobreqId());
        jbrqnse.setExamId(examsetid);
        jbrqnse.setPassPercentage(passpercentage);
        jbrqnse.setMcomckexamsetcomment(comment);
        jbrqnse.setUpdatedBy(user1.getUserName());
        jbrqnse.setUpdatedDate(new Date());
        this.jobreqtxdao.saveRequistionExamQnsAssign(jbrqnse);
      }
      else
      {
        jbrqnse.setExamId(examsetid);
        jbrqnse.setPassPercentage(passpercentage);
        jbrqnse.setMcomckexamsetcomment(comment);
        jbrqnse.setUpdatedBy(user1.getUserName());
        jbrqnse.setUpdatedDate(new Date());
        this.jobreqtxdao.updateRequistionExamQnsAssign(jbrqnse);
      }
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
    return jbrqnse;
  }
  
  public RequistionExamQnsAssign assignQuestionnaire(String jobreqid, long qnsgrpid, String comment, User user1, JobRequisitionForm jbForm)
    throws Exception
  {
    JobRequisition jb = null;
    RequistionExamQnsAssign jbrqnse = null;
    try
    {
      jb = this.jobreqtxdao.getJobRequision(jobreqid);
      
      jbrqnse = this.jobreqtxdao.getRequistionExamQnsAssign(jb.getJobreqId());
      if (jbrqnse == null)
      {
        jbrqnse = new RequistionExamQnsAssign();
        jbrqnse.setJobreqid(jb.getJobreqId());
        jbrqnse.setQnsgrpId(qnsgrpid);
        jbrqnse.setQuestiongroupcomment(comment);
        jbrqnse.setUpdatedBy(user1.getUserName());
        jbrqnse.setUpdatedDate(new Date());
        this.jobreqtxdao.saveRequistionExamQnsAssign(jbrqnse);
      }
      else
      {
        jbrqnse.setQnsgrpId(qnsgrpid);
        jbrqnse.setQuestiongroupcomment(comment);
        jbrqnse.setUpdatedBy(user1.getUserName());
        jbrqnse.setUpdatedDate(new Date());
        this.jobreqtxdao.updateRequistionExamQnsAssign(jbrqnse);
      }
      commonLovPopulate(jbForm, jb, user1);
    }
    catch (Exception e)
    {
      rollBack();
      throw new Exception(e.getMessage());
    }
    return jbrqnse;
  }
  
  public RequistionExamQnsAssign getRequistionExamQnsAssign(long reqid)
  {
    return this.jobreqtxdao.getRequistionExamQnsAssign(reqid);
  }
  
  public JobRequisition jobRequistionDetails(User user1, JobRequisitionForm jbForm, JobRequisition jb)
    throws ObjectNotFoundException, ValidationException, Exception
  {
    try
    {
      jb = this.jobreqtxdao.updateJobRequistionWithReturn(jb);
      if (jb == null) {
        throw new ObjectNotFoundException("requistion id: " + jbForm.getJobreqId() + " not found");
      }
      logger.info("recruiter name in edit : " + jb.getRecruiterName());
      
      commonLovPopulate(jbForm, jb, user1);
    }
    catch (ObjectNotFoundException e)
    {
      throw e;
    }
    catch (ValidationException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw e;
    }
    return jb;
  }
  
  public void commonLovPopulate(JobRequisitionForm jbForm, JobRequisition jb, User user)
    throws ValidationException, Exception
  {
    try
    {
      List orgList = this.lovtxdao.getAllOrganization(user.getSuper_user_key());
      jbForm.setOrganizationList(orgList);
      
      Map<String, List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode("REQUISITION_SCREEN", user.getSuper_user_key());
      List<String> screenFields = (List)screenMap.get("VISIBLE_FIELDS_LIST");
      

      List deptlist = new ArrayList();
      jbForm.setDepartmentList(deptlist);
      if (orgList.size() > 0) {
        if (screenFields.contains(FieldCodes.RequisitionScreen.DEPARTMENT.toString())) {
          jbForm.setDepartmentList(this.lovtxdao.getDepartmentListByOrg(String.valueOf(jb.getOrganization().getOrgId())));
        }
      }
      List budgetcodelist = new ArrayList();
      List projectcodeList = new ArrayList();
      jbForm.setBudgetcodeList(budgetcodelist);
      jbForm.setProjectcodeList(projectcodeList);
      if ((orgList.size() > 0) && (deptlist.size() > 0) && (jb.getDepartment() != null))
      {
        Organization org = (Organization)orgList.get(0);
        if (screenFields.contains(FieldCodes.RequisitionScreen.BUDGET_CODE.toString()))
        {
          budgetcodelist = this.lovtxdao.getAllBudgetCodeDetailsbyorganizationanddepartment(String.valueOf(jb.getOrganization().getOrgId()), String.valueOf(jb.getDepartment().getDepartmentId()));
          jbForm.setBudgetcodeList(budgetcodelist);
        }
      }
      else if (orgList.size() > 0)
      {
        Organization org = (Organization)orgList.get(0);
        if (screenFields.contains(FieldCodes.RequisitionScreen.BUDGET_CODE.toString()))
        {
          budgetcodelist = this.lovtxdao.getAllBudgetCodeDetailsbyorganizationanddepartment(String.valueOf(jb.getOrganization().getOrgId()), String.valueOf(0));
          jbForm.setBudgetcodeList(budgetcodelist);
        }
      }
      if ((jb.getDepartment() != null) && 
        (screenFields.contains(FieldCodes.RequisitionScreen.PROJECT_CODE.toString())))
      {
        projectcodeList = this.lovtxdao.getProjectCodesByDept(String.valueOf(jb.getDepartment().getDepartmentId()));
        jbForm.setProjectcodeList(projectcodeList);
      }
      List locationList = this.lovtxdao.getAllLocationDetails(user.getSuper_user_key());
      jbForm.setLocationList(locationList);
      if (screenFields.contains(FieldCodes.RequisitionScreen.JOB_GRADE.toString()))
      {
        List jobgradeList = this.lovtxdao.getAllJobGradeDetails(user.getSuper_user_key());
        jbForm.setJobgradeList(jobgradeList);
      }
      if (screenFields.contains(FieldCodes.RequisitionScreen.SALARY_PLAN.toString()))
      {
        List salaryplanList = BOFactory.getLovBO().retrieveSalaryPlanByOrganization(String.valueOf(jb.getOrganization().getOrgId()), user.getSuper_user_key());
        jbForm.setSalaryplanList(salaryplanList);
      }
      if (screenFields.contains(FieldCodes.RequisitionScreen.CATEGORY.toString()))
      {
        List categoryList = this.lovtxdao.getAllCategories(user.getSuper_user_key());
        jbForm.setCategoryList(categoryList);
      }
      if (screenFields.contains(FieldCodes.RequisitionScreen.JOB_CATEGORY.toString()))
      {
        List jobcategoryList = this.lovtxdao.getAllJobCategories(user.getSuper_user_key());
        jbForm.setJobCategoryList(jobcategoryList);
      }
      List designationList = this.lovtxdao.getAllDesignations(user.getSuper_user_key());
      jbForm.setDesignationList(designationList);
      if (screenFields.contains(FieldCodes.RequisitionScreen.JOB_TITLE.toString()))
      {
        List jobtypeList = this.lovtxdao.getJobTypeList(user.getSuper_user_key());
        jbForm.setJobtypeList(jobtypeList);
      }
      if (screenFields.contains(FieldCodes.RequisitionScreen.WORK_SHIFT.toString()))
      {
        List workshiftList = this.lovtxdao.getWorkShiftList(user.getSuper_user_key());
        jbForm.setWorkshiftList(workshiftList);
      }
      if (screenFields.contains(FieldCodes.RequisitionScreen.FLSA_STATUS.toString()))
      {
        List flsastatusList = this.lovtxdao.getFlsaStatusList();
        jbForm.setFlsastatusList(flsastatusList);
      }
      if (screenFields.contains(FieldCodes.RequisitionScreen.COMP_FREQUENCY.toString()))
      {
        List compfrequencyList = this.lovtxdao.getCompFrequencyList();
        jbForm.setCompfrequencyList(compfrequencyList);
      }
      if (screenFields.contains(FieldCodes.RequisitionScreen.PRIMARY_SKILL_REQUIRED.toString()))
      {
        List skilllist = this.lovtxdao.getTechnicalSkillList(user.getSuper_user_key());
        jbForm.setPrimarySkillList(skilllist);
      }
      List approversList = this.jobreqtxdao.getJobRequisionTmplApproversList(jb.getJobreqId(), "job");
      
      jbForm.setApproversList(approversList);
      


      List attachmentList = this.jobreqtxdao.getAttachmentList(jb.getJobreqId(), "reqattachment");
      jbForm.setAttachmentList(attachmentList);
      

      jbForm.setSkillRatingList(Constant.skillsRatingsList);
      jbForm.setStdworkinghoursunitList(Constant.getStdworkinghoursunitList());
      

      jbForm.setEducationNamesList(this.lovtxdao.getEducationListKeyValue(user.getSuper_user_key()));
      if ((jb.getStatus() != null) && (jb.getStatus().equals("Open")) && (jb.getState() != null) && (jb.getState().equals("Active")))
      {
        List publishToVendorList = this.jobreqtxdao.getPublishToVendorList(jb.getJobreqId());
        jbForm.setPublishToVendorList(publishToVendorList);
      }
      if (jb.getCurrentOwnerId() > 0L) {
        if ((!StringUtils.isNullOrEmpty(jb.getIsGroup())) && (jb.getIsGroup().equals("Y")))
        {
          UserGroup usrgrp = this.usertxdao.getUserGroupNameandIdById(jb.getCurrentOwnerId());
          jbForm.setCurrentOwnerId(usrgrp.getUsergrpId());
          jbForm.setCurrentOwnerName(usrgrp.getUsergrpName());
          jbForm.setIsGroup("Y");
        }
        else
        {
          User usr = this.usertxdao.getUserFullNameEmailAndUserIdById(jb.getCurrentOwnerId());
          jbForm.setCurrentOwnerId(usr.getUserId());
          jbForm.setCurrentOwnerName(usr.getFirstName() + " " + usr.getLastName());
          jbForm.setIsGroup("N");
        }
      }
      if (jb.getHiringmgr().getUserId() > 0L)
      {
        User usr = this.usertxdao.getUserFullNameEmailAndUserIdById(jb.getHiringmgr().getUserId());
        jbForm.setHiringMgrId(jb.getHiringmgr().getUserId());
        jbForm.setHiringMgrName(usr.getFirstName() + " " + usr.getLastName());
        jb.setHiringmgr(usr);
      }
      List resumeSources = this.lovtxdao.getResumeSourcesList();
      jbForm.setResumeSourceList(resumeSources);
      




      List<BusinessCriteria> filters = BOFactory.getBusinessRuleBO().getBusinessCriteriasByRequistion(jb.getJobreqId());
      jbForm.setFilters(filters);
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisitionForm setEvaluationTabData(User user1, JobRequisitionForm jbform)
  {
    logger.info("inside setEvaluationTabData");
    try
    {
      List comptetencyList = this.jobreqtxdao.getJobRequisionTemplateComptetencyList(jbform.getJobreqId(), "job");
      List accomplishmentList = this.jobreqtxdao.getJobRequisionTemplateAccomplishmentList(jbform.getJobreqId(), "job");
      
      jbform.setComptetencyList(comptetencyList);
      jbform.setAccomplishmentList(accomplishmentList);
      









      RequistionExamQnsAssign jbrqnse = this.jobreqtxdao.getRequistionExamQnsAssign(jbform.getJobreqId());
      if (jbrqnse != null)
      {
        jbform.setReqexqnid(jbrqnse.getReqexqnid());
        jbform.setMockexamsetId(jbrqnse.getExamId());
        jbform.setPassPercentage(jbrqnse.getPassPercentage());
        jbform.setMcomckexamsetcomment(jbrqnse.getMcomckexamsetcomment());
        MockQuestionSet mockQuestionSet = this.lovtxdao.getMockExamset(jbrqnse.getExamId(), user1.getSuper_user_key());
        if (mockQuestionSet != null) {
          jbform.setExamName(mockQuestionSet.getName());
        }
        jbform.setQuestiongroupId(jbrqnse.getQnsgrpId());
        jbform.setQuestiongroupcomment(jbrqnse.getQuestiongroupcomment());
        QuestionGroups questionGroups = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(jbrqnse.getQnsgrpId());
        if (questionGroups != null) {
          jbform.setQuestiongroupName(questionGroups.getQuestiongroupName());
        }
      }
    }
    catch (Exception e)
    {
      logger.info("exception on setEvaluationTabData" + e.getMessage());
    }
    return jbform;
  }
  
  public List getMockExamListForForm(long superuserkey)
  {
    return this.lovtxdao.getMockExamListForForm(superuserkey);
  }
  
  public List getQuestionGroupListForForm(long superuserkey)
  {
    return this.lovtxdao.getQuestionGroupListForForm(superuserkey);
  }
  
  public List getActivity(String reqId)
  {
    logger.info("inside getActivity");
    List activityList = new ArrayList();
    try
    {
      activityList = this.jobreqtxdao.getActivityList(new Long(reqId).longValue());
    }
    catch (Exception e)
    {
      logger.info("exception on getActivity" + e.getMessage());
    }
    return activityList;
  }
  
  public List getReqActivityByUuid(String uuid)
    throws Exception
  {
    logger.info("inside getReqActivityByUuid");
    List activityList = new ArrayList();
    try
    {
      activityList = this.jobreqtxdao.getActivityListByUuid(uuid);
    }
    catch (Exception e)
    {
      logger.info("exception on getReqActivityByUuid" + e.getMessage());
      throw e;
    }
    return activityList;
  }
  
  public List getApptoversListForRequistion(long id)
    throws Exception
  {
    return this.jobreqtxdao.getJobRequisionTmplApproversList(id, "job");
  }
  
  public List getApptoversListForTemplate(long id)
    throws Exception
  {
    return this.jobreqtxdao.getJobRequisionTmplApproversList(id, "tmpl");
  }
  
  public VariablesTXDAO getVariabletxdao()
  {
    return this.variabletxdao;
  }
  
  public void setVariabletxdao(VariablesTXDAO variabletxdao)
  {
    this.variabletxdao = variabletxdao;
  }
  
  public LovOpsTXDAO getLovopstxdao()
  {
    return this.lovopstxdao;
  }
  
  public void setLovopstxdao(LovOpsTXDAO lovopstxdao)
  {
    this.lovopstxdao = lovopstxdao;
  }
  
  public UserTXDAO getUsertxdao()
  {
    return this.usertxdao;
  }
  
  public void setUsertxdao(UserTXDAO usertxdao)
  {
    this.usertxdao = usertxdao;
  }
  
  public LovTXDAO getLovtxdao()
  {
    return this.lovtxdao;
  }
  
  public void setLovtxdao(LovTXDAO lovtxdao)
  {
    this.lovtxdao = lovtxdao;
  }
  
  public RuleTXDAO getRuletxdao()
  {
    return this.ruletxdao;
  }
  
  public void setRuletxdao(RuleTXDAO ruletxdao)
  {
    this.ruletxdao = ruletxdao;
  }
  
  public static void main(String[] args)
  {
    JobRequistionTXBO t = new JobRequistionTXBO();
    System.out.println(t.isRecruiterChanged(1L, "N", 3L, "Y"));
  }
  
  public RefferalDAO getRefferaldao()
  {
    return this.refferaldao;
  }
  
  public void setRefferaldao(RefferalDAO refferaldao)
  {
    this.refferaldao = refferaldao;
  }
}
