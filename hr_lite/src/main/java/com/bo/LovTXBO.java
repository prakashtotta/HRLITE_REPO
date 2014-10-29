package com.bo;

import com.bean.ApplicantReferencee;
import com.bean.ApplicantUser;
import com.bean.GroupCharacteristic;
import com.bean.JobApplicant;
import com.bean.RefferalScheme;
import com.bean.TaskData;
import com.bean.User;
import com.bean.lov.DemoInterest;
import com.common.Common;
import com.dao.ApplicantTXDAO;
import com.dao.LovOpsTXDAO;
import com.dao.LovTXDAO;
import com.dao.TaskTXDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class LovTXBO
{
  protected static final Logger logger = Logger.getLogger(LovTXBO.class);
  LovOpsTXDAO lovopstxdao;
  ApplicantTXDAO applicanttxdao;
  TaskTXDAO tasktxdao;
  LovTXDAO lovtxdao;
  
  public LovOpsTXDAO getLovopstxdao()
  {
    return this.lovopstxdao;
  }
  
  public void setLovopstxdao(LovOpsTXDAO lovopstxdao)
  {
    this.lovopstxdao = lovopstxdao;
  }
  
  public RefferalScheme getRefferalSchemeDetails(long id)
  {
    return this.lovtxdao.getRefferalSchemeDetails(id);
  }
  
  public List getAllJobGradeDetails(long superUserKey)
    throws Exception
  {
    return this.lovtxdao.getAllJobGradeDetails(superUserKey);
  }
  
  public void updateGroupChar(GroupCharacteristic grch, List chars)
  {
    logger.info("Inside updateGroupChar method");
    
    this.lovopstxdao.updateGroupChar(grch);
  }
  
  public List getTechnicalSkillList(long super_user_key)
  {
    List tList = new ArrayList();
    try
    {
      tList = this.lovtxdao.getTechnicalSkillList(super_user_key);
    }
    catch (Exception e)
    {
      logger.info("getTechnicalSkillList", e);
    }
    return tList;
  }
  
  public List getEducationListKeyValue(long super_user_key)
  {
    List tList = new ArrayList();
    try
    {
      tList = this.lovtxdao.getEducationListKeyValue(super_user_key);
    }
    catch (Exception e)
    {
      logger.info("getEducationListKeyValue", e);
    }
    return tList;
  }
  
  public List getTechnicalSkillListKeyValue(long super_user_key)
  {
    List tList = new ArrayList();
    try
    {
      tList = this.lovtxdao.getTechnicalSkillListKeyValue(super_user_key);
    }
    catch (Exception e)
    {
      logger.info("getTechnicalSkillListKeyValue", e);
    }
    return tList;
  }
  
  public ApplicantReferencee savereference(ApplicantReferencee applicantref, JobApplicant applicant, User user1)
  {
    this.lovopstxdao.saveApplicantReferencee(applicantref);
    
    TaskData task = new TaskData();
    task.setIdvalue(applicant.getApplicantId());
    task.setTaskname(applicant.getFullName());
    task.setTasktype(Common.REFERENCE_CHECK_TASK);
    task.setAssignedbyUserId(user1.getUserId());
    task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
    task.setAssignedtoUserId(applicantref.getAssignedTo());
    task.setAssignedtoUserName(applicantref.getAssignedToName());
    task.setCreatedBy(user1.getUserName());
    task.setCreatedDate(new Date());
    task.setStatus("A");
    task.setUuid(applicant.getUuid());
    
    this.tasktxdao.saveTask(task);
    
    applicant.setIsreferenceadded(1);
    this.applicanttxdao.updateApplicant(applicant);
    
    return applicantref;
  }
  
  public ApplicantReferencee savereferenceData(ApplicantReferencee applicantref, JobApplicant applicant, ApplicantUser user1)
  {
    this.lovopstxdao.saveApplicantReferencee(applicantref);
    

    applicant.setIsreferenceadded(1);
    this.applicanttxdao.updateApplicant(applicant);
    
    return applicantref;
  }
  
  public ApplicantReferencee saverefcheck(ApplicantReferencee applicantref, JobApplicant applicant, User user1)
  {
    this.lovopstxdao.updateApplicantReferencee(applicantref);
    TaskData task = this.tasktxdao.getTask(applicant.getApplicantId(), Common.REFERENCE_CHECK_TASK);
    this.tasktxdao.markCompleteTask(task, user1.getUserName());
    

    return applicantref;
  }
  
  public void deletereference(ApplicantReferencee applicantref, JobApplicant applicant, User user1)
  {
    this.lovopstxdao.deleteApplicantReference(applicantref.getApplicantReferenceId());
    TaskData task = this.tasktxdao.getTask(applicant.getApplicantId(), Common.REFERENCE_CHECK_TASK);
    this.tasktxdao.markCompleteTask(task, user1.getUserName());
  }
  
  public void SaveDemoInterest(String name, String email, String phone, String company)
  {
    DemoInterest dm = new DemoInterest();
    dm.setName(name);
    dm.setEmail(email);
    dm.setPhone(phone);
    dm.setCompany(company);
    dm.setCreatedDate(new Date());
    this.lovtxdao.saveDemoInterest(dm);
  }
  
  public List getProjectCodesByDept(String deptId)
    throws Exception
  {
    return this.lovtxdao.getProjectCodesByDept(deptId);
  }
  
  public ApplicantReferencee updatereference(ApplicantReferencee applicantref, JobApplicant applicant, User user1, String assignedtoid, String assignedtoname)
  {
    this.lovopstxdao.updateApplicantReferencee(applicantref);
    TaskData taskold = this.tasktxdao.getTask(applicant.getApplicantId(), Common.REFERENCE_CHECK_TASK);
    
    this.tasktxdao.deleteTask(taskold.getTaskId(), Common.REFERENCE_CHECK_TASK);
    
    TaskData task = new TaskData();
    task.setIdvalue(applicant.getApplicantId());
    task.setTaskname(applicant.getFullName());
    task.setTasktype(Common.REFERENCE_CHECK_TASK);
    task.setAssignedbyUserId(user1.getUserId());
    task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
    task.setAssignedtoUserId(applicantref.getAssignedTo());
    task.setAssignedtoUserName(applicantref.getAssignedToName());
    task.setCreatedBy(user1.getUserName());
    task.setCreatedDate(new Date());
    task.setStatus("A");
    task.setUuid(applicant.getUuid());
    
    this.tasktxdao.saveTask(task);
    
    return applicantref;
  }
  
  public ApplicantReferencee updatereferencescreen(ApplicantReferencee applicantref, JobApplicant applicant, ApplicantUser user1)
  {
    this.lovopstxdao.updateApplicantReferencee(applicantref);
    

    return applicantref;
  }
  
  public ApplicantTXDAO getApplicanttxdao()
  {
    return this.applicanttxdao;
  }
  
  public void setApplicanttxdao(ApplicantTXDAO applicanttxdao)
  {
    this.applicanttxdao = applicanttxdao;
  }
  
  public TaskTXDAO getTasktxdao()
  {
    return this.tasktxdao;
  }
  
  public void setTasktxdao(TaskTXDAO tasktxdao)
  {
    this.tasktxdao = tasktxdao;
  }
  
  public LovTXDAO getLovtxdao()
  {
    return this.lovtxdao;
  }
  
  public void setLovtxdao(LovTXDAO lovtxdao)
  {
    this.lovtxdao = lovtxdao;
  }
  
  public List getCompFrequencyList()
    throws Exception
  {
    return this.lovtxdao.getCompFrequencyList();
  }
  
  public List getAllSalaryDetails(long superUserKey)
    throws Exception
  {
    return this.lovtxdao.getAllSalaryDetails(superUserKey);
  }
}
