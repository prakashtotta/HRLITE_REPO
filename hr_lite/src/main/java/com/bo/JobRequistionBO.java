package com.bo;

import com.bean.BudgetCode;
import com.bean.CompFrequency;
import com.bean.DataTableBean;
import com.bean.Department;
import com.bean.Designations;
import com.bean.EmailNotificationSetting;
import com.bean.FavoriteJob;
import com.bean.FlsaStatus;
import com.bean.JobApplicant;
import com.bean.JobGrade;
import com.bean.JobRequisionTemplate;
import com.bean.JobRequisition;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateApprovers;
import com.bean.JobTemplateCompetency;
import com.bean.JobType;
import com.bean.Location;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.PublishJobBoards;
import com.bean.RefferalEmployee;
import com.bean.RefferalScheme;
import com.bean.RequisitionComments;
import com.bean.RequistionAttachments;
import com.bean.RequistionExamQnsAssign;
import com.bean.SalaryPlan;
import com.bean.SocialUrlMapping;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.WorkShift;
import com.bean.criteria.RequisitionSearchCriteriaMultiple;
import com.bean.criteria.RequisitionTemplateSearchCriteriaMultiple;
import com.bean.dto.Recruiter;
import com.bean.filter.BusinessCriteria;
import com.bean.lov.FormVariablesMap;
import com.bean.lov.Variables;
import com.bean.lov.VariablesValues;
import com.bean.system.PublishToVendor;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.common.Common;
import com.common.PluginException;
import com.dao.ApplicantDAO;
import com.dao.JobRequistionDAO;
import com.dao.LovTXDAO;
import com.dao.OrganizationDAO;
import com.dao.RefferalDAO;
import com.dao.RuleTXDAO;
import com.dao.UserDAO;
import com.dao.VariablesTXDAO;
import com.form.AgencyRedemptionSummaryForm;
import com.form.JobRequisitionForm;
import com.form.JobRequisitionTemplateForm;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.manager.EmailTaskManager;
import com.plugin.IntercepterImpl;
import com.plugin.PluginScripts;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EmailTask;
import com.util.HTMLInputFilter;
import com.util.PermissionChecker;
import com.util.StringUtils;
import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class JobRequistionBO
{
  protected static final Logger logger = Logger.getLogger(JobRequistionBO.class);
  ApplicantDAO applicantdao;
  JobRequistionDAO jobrequisitiondao;
  OrganizationDAO organizationdao;
  UserDAO userdao;
  RefferalDAO refferaldao;
  LovTXDAO lovtxdao;
  RuleTXDAO ruletxdao;
  VariablesTXDAO variabletxdao;
  
  public boolean isLoggedInUserIsApprover(long userid, long approverId, String isGroup)
  {
    boolean isowner = false;
    if ((!StringUtils.isEmpty(isGroup)) && (isGroup.equals("Y")))
    {
      UserGroup usrgrp = this.userdao.getUserGroupTx(approverId);
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
    else if (approverId == userid)
    {
      isowner = true;
    }
    return isowner;
  }
  
  public boolean isRedemptionRuleAppliedToAgency(long jobReqId, String criteria)
  {
    return this.jobrequisitiondao.isRedemptionRuleAppliedToAgency(jobReqId, criteria);
  }
  
  public boolean isRedemptionRuleAppliedToEmployeeRef(long jobReqId, String criteria)
  {
    return this.jobrequisitiondao.isRedemptionRuleAppliedToEmployeeRef(jobReqId, criteria);
  }
  
  public JobRequisition updateJobRequistion(JobRequisition jb)
  {
    return this.jobrequisitiondao.updateJobRequistion(jb);
  }
  
  public JobRequisition jobdetails(JobRequisitionForm jbForm)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      JobApplicant applicant = this.applicantdao.getApplicantDetailsByUUID(jbForm.getUuid());
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(applicant.getReqId()));
      jbForm.setPublishedDate(jb.getPublishedDate());
      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
      

      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
      if (jb.getRecruiterId() > 0L)
      {
        User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
        jbForm.setRecruiter(recruiter);
      }
      jbForm.setComptetencyList(comptetencyList);
      jbForm.setAccomplishmentList(accomplishmentList);
      List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
      
      jbForm.setAttachmentList(attachmentList);
      jbForm.setJobreqId(jb.getJobreqId());
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisition jobdetailsNew(JobRequisitionForm jbForm)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
      jbForm.setPublishedDate(jb.getPublishedDate());
      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
      

      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
      if (jb.getRecruiterId() > 0L)
      {
        User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
        jbForm.setRecruiter(recruiter);
      }
      jbForm.setComptetencyList(comptetencyList);
      jbForm.setAccomplishmentList(accomplishmentList);
      List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
      
      jbForm.setAttachmentList(attachmentList);
      jbForm.setJobreqId(jb.getJobreqId());
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisition jobdetailsExternal(JobRequisitionForm jbForm)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()), jbForm.getUuid());
      jbForm.setPublishedDate(jb.getPublishedDate());
      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
      

      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
      if (jb.getRecruiterId() > 0L)
      {
        User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
        jbForm.setRecruiter(recruiter);
      }
      jbForm.setComptetencyList(comptetencyList);
      jbForm.setAccomplishmentList(accomplishmentList);
      List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
      
      jbForm.setAttachmentList(attachmentList);
      jbForm.setJobreqId(jb.getJobreqId());
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisition refjobdetails(JobRequisitionForm jbForm, String uuid)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionByUUid(uuid);
      jbForm.setPublishedDate(jb.getPublishedDate());
      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
      

      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
      if (jb.getRecruiterId() > 0L)
      {
        User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
        jbForm.setRecruiter(recruiter);
      }
      jbForm.setComptetencyList(comptetencyList);
      jbForm.setAccomplishmentList(accomplishmentList);
      List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
      
      jbForm.setAttachmentList(attachmentList);
      jbForm.setJobreqId(jb.getJobreqId());
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisition jobsummary(JobRequisitionForm jbForm, String approverstatus, String offerapplicant, User user)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
      

      CommonJobSummary(jb, jbForm, approverstatus, offerapplicant, user);
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisition jobsummarywithuuid(JobRequisitionForm jbForm, String approverstatus, String offerapplicant, User user)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()), jbForm.getUuid());
      
      CommonJobSummary(jb, jbForm, approverstatus, offerapplicant, user);
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  private void CommonJobSummary(JobRequisition jb, JobRequisitionForm jbForm, String approverstatus, String offerapplicant, User user)
    throws Exception
  {
    List jobtypeList = this.lovtxdao.getJobTypeList(user.getSuper_user_key());
    jbForm.setJobtypeList(jobtypeList);
    List workshiftList = this.lovtxdao.getWorkShiftList(user.getSuper_user_key());
    jbForm.setWorkshiftList(workshiftList);
    List flsastatusList = this.lovtxdao.getFlsaStatusList();
    jbForm.setFlsastatusList(flsastatusList);
    List compfrequencyList = this.lovtxdao.getCompFrequencyList();
    jbForm.setCompfrequencyList(compfrequencyList);
    
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
    

    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
    

    List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jb.getJobreqId(), "job");
    


    jbForm.setComptetencyList(comptetencyList);
    jbForm.setAccomplishmentList(accomplishmentList);
    jbForm.setApproversList(approversList);
    
    List applicantList = this.applicantdao.getOfferedApplicants(jb.getJobreqId());
    
    jbForm.setApplicantList(applicantList);
    
    List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
    
    jbForm.setAttachmentList(attachmentList);
    
    jbForm.setSkillRatingList(Constant.skillsRatingsList);
    

    jbForm.setApproverStatus(approverstatus);
    jbForm.setOfferapplicantStatus(offerapplicant);
  }
  
  public JobRequisition jobdetailsForAgency(JobRequisitionForm jbForm, User user)
  {
    JobRequisition jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
    

    jbForm.setPublishedDate(jb.getPublishedDate());
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
    
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
    if (jb.getRecruiterId() > 0L)
    {
      User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
      jbForm.setRecruiter(recruiter);
    }
    jbForm.setComptetencyList(comptetencyList);
    jbForm.setAccomplishmentList(accomplishmentList);
    List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
    
    jbForm.setAttachmentList(attachmentList);
    return jb;
  }
  
  public JobRequisition jobdetailsForAgencyWithUUID(JobRequisitionForm jbForm, User user)
  {
    JobRequisition jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()), jbForm.getUuid());
    

    jbForm.setPublishedDate(jb.getPublishedDate());
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
    
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
    if (jb.getRecruiterId() > 0L)
    {
      User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
      jbForm.setRecruiter(recruiter);
    }
    jbForm.setComptetencyList(comptetencyList);
    jbForm.setAccomplishmentList(accomplishmentList);
    List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
    
    jbForm.setAttachmentList(attachmentList);
    return jb;
  }
  
  public JobRequisition addbookmarkForEmployeeRef(JobRequisitionForm jbForm, String comment, RefferalEmployee empref, String uuid, HttpServletRequest request)
  {
    JobRequisition jb = this.jobrequisitiondao.getJobRequisionByUUid(uuid);
    List bookmarkList = this.refferaldao.getMybookmarksByReqIdandEmpemail(jbForm.getJobreqId(), empref.getEmployeeemail());
    
    logger.info("bookmark list size" + bookmarkList.size());
    if (bookmarkList.size() > 0)
    {
      request.setAttribute("addbookmarkdone", "no");
    }
    else
    {
      FavoriteJob fav = new FavoriteJob();
      fav.setReqId(new Long(jbForm.getJobreqId()).longValue());
      fav.setEmpemail(empref.getEmployeeemail());
      fav.setComment(comment);
      fav.setJobTitle(jb.getJobTitle());
      fav.setCreatedBy(empref.getEmployeeemail());
      fav.setCreatedDate(new Date());
      fav.setUuid(uuid);
      this.refferaldao.saveBookmark(fav);
      
      request.setAttribute("addbookmarkdone", "yes");
    }
    jbForm.setJobreqId(jb.getJobreqId());
    
    jbForm.setPublishedDate(jb.getPublishedDate());
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
    
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
    if (jb.getRecruiterId() > 0L)
    {
      User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
      jbForm.setRecruiter(recruiter);
    }
    jbForm.setComptetencyList(comptetencyList);
    jbForm.setAccomplishmentList(accomplishmentList);
    List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
    
    jbForm.setAttachmentList(attachmentList);
    
    return jb;
  }
  
  public JobRequisition addbookmark(JobRequisitionForm jbForm, FavoriteJob fav, HttpServletRequest request, User user1)
  {
    JobRequisition jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
    

    List bookmarkList = this.refferaldao.getMybookmarksByReqIdandAgencyId(jb.getJobreqId(), user1.getUserId());
    
    logger.info("bookmark list size : " + bookmarkList.size());
    if (bookmarkList.size() > 0)
    {
      request.setAttribute("alreadyBookmarked", "yes");
      request.setAttribute("jobapply", "yes");
    }
    else
    {
      fav.setJobTitle(jb.getJobTitle());
      
      this.refferaldao.saveBookmark(fav);
      request.setAttribute("addbookmarkdone", "yes");
      request.setAttribute("jobapply", "yes");
    }
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
    
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
    if (jb.getRecruiterId() > 0L)
    {
      User recruiter = this.userdao.getUserTx(jb.getRecruiterId());
      jbForm.setRecruiter(recruiter);
    }
    jbForm.setComptetencyList(comptetencyList);
    jbForm.setAccomplishmentList(accomplishmentList);
    List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
    
    jbForm.setAttachmentList(attachmentList);
    
    return jb;
  }
  
  public JobRequisition getSummaryDataForAgency(JobRequisitionForm jbForm, String approverstatus, String offerapplicant, User agency)
    throws Exception
  {
    JobRequisition jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
    
    jbForm.setJobreqId(jb.getJobreqId());
    
    List jobtypeList = this.lovtxdao.getJobTypeList(agency.getSuper_user_key());
    jbForm.setJobtypeList(jobtypeList);
    List workshiftList = this.lovtxdao.getWorkShiftList(agency.getSuper_user_key());
    jbForm.setWorkshiftList(workshiftList);
    List flsastatusList = this.lovtxdao.getFlsaStatusList();
    jbForm.setFlsastatusList(flsastatusList);
    List compfrequencyList = this.lovtxdao.getCompFrequencyList();
    jbForm.setCompfrequencyList(compfrequencyList);
    
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
    
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
    

    List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jb.getJobreqId(), "job");
    

    jbForm.setComptetencyList(comptetencyList);
    jbForm.setAccomplishmentList(accomplishmentList);
    jbForm.setApproversList(approversList);
    
    List applicantList = this.applicantdao.getOfferedApplicants(jb.getJobreqId());
    
    jbForm.setApplicantList(applicantList);
    
    List attachmentList = this.jobrequisitiondao.getAttachmentList(jb.getJobreqId(), "reqattachment");
    
    jbForm.setAttachmentList(attachmentList);
    
    jbForm.setSkillRatingList(Constant.skillsRatingsList);
    

    jbForm.setApproverStatus(approverstatus);
    jbForm.setOfferapplicantStatus(offerapplicant);
    
    return jb;
  }
  
  public List getRequisitionsActiveList()
  {
    return this.jobrequisitiondao.getRequisitionsActiveList();
  }
  
  public List getRequisitionsByRefferalPortal(long superuserkey)
  {
    return this.jobrequisitiondao.getRequisitionsByRefferalPortal(superuserkey);
  }
  
  public List getRequisitionsByAgency(long id)
  {
    return this.jobrequisitiondao.getRequisitionsByAgency(id);
  }
  
  public List getJobtitleforAgency(String query, long agencyid)
  {
    List jobList = this.jobrequisitiondao.getJobTitlesByAgency(query, agencyid);
    List<String> jobtitleList = new ArrayList();
    Iterator itr = jobList.iterator();
    while (itr.hasNext())
    {
      JobRequisition jbrequisition = (JobRequisition)itr.next();
      jobtitleList.add(jbrequisition.getJobTitle());
    }
    return jobtitleList;
  }
  
  public JobRequisition getJobRequision(String id)
  {
    return this.jobrequisitiondao.getJobRequision(id);
  }
  
  public String getRequisitionNamebyReqId(long reqId)
  {
    return this.jobrequisitiondao.getRequisitionNamebyReqId(reqId);
  }
  
  public JobRequisition getJobRequisionByJobCode(String jobreqcode, long super_user_key)
  {
    return this.jobrequisitiondao.getJobRequisionByJobCode(jobreqcode, super_user_key);
  }
  
  public List getJobRequisionByName(String name, long super_user_key)
  {
    return this.jobrequisitiondao.getJobRequisionByName(name, super_user_key);
  }
  
  public JobRequisition getJobRequision(String id, String uuid)
  {
    return this.jobrequisitiondao.getJobRequision(id, uuid);
  }
  
  public JobRequisition getJobRequisionActive(String id)
  {
    return this.jobrequisitiondao.getJobRequisionActive(id);
  }
  
  public JobRequisition getJobRequisionByNumber(long requisition_number, long super_user_key)
  {
    return this.jobrequisitiondao.getJobRequisionByNumber(requisition_number, super_user_key);
  }
  
  public SocialUrlMapping createSocialUrlMappingKey(String url, String uuid)
    throws Exception
  {
    SocialUrlMapping umapold = this.lovtxdao.getSocialUrlMapping(uuid);
    if (umapold == null)
    {
      umapold = new SocialUrlMapping();
      umapold.setUrlcode(uuid);
      umapold.setUrl(url);
      umapold = this.lovtxdao.saveSocialUrlMapping(umapold);
    }
    return umapold;
  }
  
  public SocialUrlMapping getSocialUrlMappingKey(String urlcode)
    throws Exception
  {
    SocialUrlMapping umapold = this.lovtxdao.getSocialUrlMapping(urlcode);
    
    return umapold;
  }
  
  public Organization getOrganizationByReqId(long reqId)
  {
    return this.jobrequisitiondao.getOrganizationByReqId(reqId);
  }
  
  public String getOrganizationNameByReqId(long reqId)
  {
    Organization org = this.jobrequisitiondao.getOrganizationByReqId(reqId);
    if (org != null) {
      return org.getOrgName();
    }
    return "";
  }
  
  public List getRequisitionsActiveListByOrgDeptProjCode(long orgId, long departmentId, long projectCodeId)
  {
    return this.jobrequisitiondao.getRequisitionsActiveListByOrgDeptProjCode(orgId, departmentId, projectCodeId);
  }
  
  public List getRequisitionsActiveListByOrgDeptProjCode(List<Long> orgidList, List<Long> deptidList, List<Long> projectcodeidList)
  {
    return this.jobrequisitiondao.getRequisitionsActiveListByOrgDeptProjCode(orgidList, deptidList, projectcodeidList);
  }
  
  public List getTotalAllRequistionsByBudgetCodeId(long budgetcodeId)
  {
    return this.jobrequisitiondao.getTotalAllRequistionsByBudgetCodeId(budgetcodeId);
  }
  
  public List getActiveNotDeletedRequisitionsList(long superUserKey)
  {
    return this.jobrequisitiondao.getActiveNotDeletedRequisitionsList(superUserKey);
  }
  
  public String getCurrencyCodeByReqId(long reqId)
  {
    return this.jobrequisitiondao.getCurrencyCodeByReqId(reqId);
  }
  
  public JobRequisition getActiveOpenJobRequision(String id)
  {
    return this.jobrequisitiondao.getActiveOpenJobRequision(id);
  }
  
  public JobRequisition getActiveOpenJobRequision(String id, String uuid)
  {
    return this.jobrequisitiondao.getActiveOpenJobRequision(id, uuid);
  }
  
  public List getRequisitionsActiveAndOpenList(long super_user_key)
  {
    return this.jobrequisitiondao.getRequisitionsActiveAndOpenList(super_user_key);
  }
  
  public List getLocationList(long super_user_key)
  {
    return this.lovtxdao.getLocationList(super_user_key);
  }
  
  public List getRequisitionsTemplateActiveList(long superUserKey)
  {
    return this.jobrequisitiondao.getRequisitionsTemplateActiveList(superUserKey);
  }
  
  public RequistionAttachments getAttachmentDetailsByUuid(String uuid)
  {
    return this.jobrequisitiondao.getAttachmentDetailsByUuid(uuid);
  }
  
  public JobRequisionTemplate getJobRequisionTemplate(long id)
  {
    return this.jobrequisitiondao.getJobRequisionTemplate(id);
  }
  
  public List getSearchJobRequisitionforExport(RequisitionSearchCriteriaMultiple searchCriteria)
  {
    return this.jobrequisitiondao.getSearchJobRequisitionforExport(searchCriteria);
  }
  
  public JobRequisition getActiveJobRequisionByJobCode(String jobcode)
  {
    return this.jobrequisitiondao.getActiveJobRequisionByJobCode(jobcode);
  }
  
  public List getSearchJobMyRequisitionHigiringManagerforExport(long currentuserid, String currentusername, RequisitionSearchCriteriaMultiple searchCriteria)
  {
    return this.jobrequisitiondao.getSearchJobMyRequisitionHigiringManagerforExport(currentuserid, currentusername, searchCriteria);
  }
  
  public List getSearchJobMyRequisitionRecruiterforExport(long currentuserid, RequisitionSearchCriteriaMultiple searchCriteria)
  {
    return this.jobrequisitiondao.getSearchJobMyRequisitionRecruiterforExport(currentuserid, searchCriteria);
  }
  
  public JobRequisionTemplate edittemplateselector(JobRequisitionTemplateForm tmplForm, User user)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      

      List jobtypeList = this.lovtxdao.getJobTypeList(user.getSuper_user_key());
      tmplForm.setJobtypeList(jobtypeList);
      List workshiftList = this.lovtxdao.getWorkShiftList(user.getSuper_user_key());
      tmplForm.setWorkshiftList(workshiftList);
      List flsastatusList = this.lovtxdao.getFlsaStatusList();
      tmplForm.setFlsastatusList(flsastatusList);
      List compfrequencyList = this.lovtxdao.getCompFrequencyList();
      tmplForm.setCompfrequencyList(compfrequencyList);
      tmplForm.setSkillRatingList(Constant.skillsRatingsList);
      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getTemplateId(), "tmpl");
      

      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getTemplateId(), "tmpl");
      

      List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jb.getTemplateId(), "tmpl");
      


      tmplForm.setComptetencyList(comptetencyList);
      tmplForm.setAccomplishmentList(accomplishmentList);
      tmplForm.setApproversList(approversList);
    }
    catch (Exception e)
    {
      throw e;
    }
    return jb;
  }
  
  public void searchreqlistpageLovs(JobRequisitionForm jbForm, long superUserKey, User user1)
    throws Exception
  {
    List jobtypeList = this.lovtxdao.getJobTypeList(superUserKey);
    jbForm.setJobtypeList(jobtypeList);
    List orgList = this.lovtxdao.getAllOrganization(superUserKey);
    jbForm.setOrganizationList(orgList);
    if ((jbForm.getOrgIds() != null) && (jbForm.getOrgIds().length > 0))
    {
      List<Long> orgIdsList = new ArrayList();
      for (long s : jbForm.getOrgIds()) {
        orgIdsList.add(Long.valueOf(s));
      }
      jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgIdsList));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List deptList = this.lovtxdao.getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      jbForm.setDepartmentList(deptList);
    }
    if (orgList.size() < 1)
    {
      List deptList = new ArrayList();
      jbForm.setDepartmentList(deptList);
    }
    List jobgradeList = this.lovtxdao.getAllJobGradeDetails(superUserKey);
    jbForm.setJobgradeList(jobgradeList);
    List budgetcodeList = this.lovtxdao.getAllBudgetCodeDetails(superUserKey);
    jbForm.setBudgetcodeList(budgetcodeList);
    
    List locationlist = this.lovtxdao.getLocationList(superUserKey);
    jbForm.setLocationList(locationlist);
    

    List categoryList = this.lovtxdao.getAllCategories(superUserKey);
    jbForm.setCategoryList(categoryList);
    
    List workshiftList = this.lovtxdao.getWorkShiftList(superUserKey);
    jbForm.setWorkshiftList(workshiftList);
    
    List skilllist = this.lovtxdao.getTechnicalSkillList(user1.getSuper_user_key());
    jbForm.setPrimarySkillList(skilllist);
    jbForm.setStatusList(Constant.getRequistionStatuses(user1));
    jbForm.setStateList(Constant.getRequistionStates(user1));
  }
  
  public void searchreqlistpageLovs(JobRequisitionForm jbForm, User user1)
    throws Exception
  {
    List jobtypeList = this.lovtxdao.getJobTypeList(user1.getSuper_user_key());
    jbForm.setJobtypeList(jobtypeList);
    List orgList = this.lovtxdao.getAllOrganization(user1.getSuper_user_key());
    jbForm.setOrganizationList(orgList);
    if ((jbForm.getOrgIds() != null) && (jbForm.getOrgIds().length > 0))
    {
      List<Long> orgIdsList = new ArrayList();
      for (long s : jbForm.getOrgIds()) {
        orgIdsList.add(Long.valueOf(s));
      }
      jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgIdsList));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List deptList = this.lovtxdao.getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      
      jbForm.setDepartmentList(deptList);
    }
    if (orgList.size() < 1)
    {
      List deptList = new ArrayList();
      jbForm.setDepartmentList(deptList);
    }
    List jobgradeList = this.lovtxdao.getAllJobGradeDetails(user1.getSuper_user_key());
    jbForm.setJobgradeList(jobgradeList);
    List budgetcodeList = this.lovtxdao.getAllBudgetCodeDetails(user1.getSuper_user_key());
    jbForm.setBudgetcodeList(budgetcodeList);
    jbForm.setStatusList(Constant.getRequistionStatuses(user1));
    jbForm.setStateList(Constant.getRequistionStates(user1));
    jbForm.setYesNoList(Constant.getYesNoList());
    
    List formVariablesList = BOFactory.getVariableBO().getAllFormVariablesListForRequistion(user1.getSuper_user_key());
    jbForm.setFormVariablesList(formVariablesList);
  }
  
  public JobRequisition publishJobReqscr(JobRequisitionForm jbForm, HttpServletRequest request, User user1)
    throws Exception
  {
    JobRequisition jbr = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
    if (jbr.getState().equals("Active")) {
      request.setAttribute("publishJobReq", "yes");
    }
    if ((!PermissionBO.isUserHasRequistionTask(user1, jbr.getJobreqId(), Common.REQUISTION_PUBLISH_TASK)) && (!PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user1))) {
      request.setAttribute("illegelaccess", "yes");
    }
    jbForm.fromValue(jbr, request);
    


    long orgId = jbr.getOrganization().getOrgId();
    long deptId = 0L;
    if (jbr.getDepartment() != null) {
      deptId = jbr.getDepartment().getDepartmentId();
    }
    SystemRule sysrule = this.ruletxdao.getOrganizationRuleByType(orgId, deptId, Common.SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS);
    
    logger.info("sysrule" + sysrule);
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
          jbapp.setJobreqId(jbr.getJobreqId());
          newvendorList.add(jbapp);
        }
      }
      jbForm.setPublishToExternal(sysrule.getPublishToExternal());
      jbForm.setPublishToEmpRef(sysrule.getPublishToEmpRef());
    }
    if (sysrule != null) {
      jbForm.setEeoInfoIncluded(sysrule.getEeoInfoIncluded());
    } else {
      jbForm.setEeoInfoIncluded("Y");
    }
    jbForm.setPublishToVendorList(newvendorList);
    jbForm.setParentOrgId(jbr.getOrganization().getOrgId());
    if (jbr.getDepartment() != null) {
      jbForm.setDepartmentId(jbr.getDepartment().getDepartmentId());
    }
    return jbr;
  }
  
  public JobRequisition publishJobReqscrErrorCondition(JobRequisitionForm jbForm, Set vendorList, HttpServletRequest request, User user1)
    throws Exception
  {
    JobRequisition jbr = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
    if (jbr.getState().equals("Active")) {
      request.setAttribute("publishJobReq", "yes");
    }
    if ((!PermissionBO.isUserHasRequistionTask(user1, jbr.getJobreqId(), Common.REQUISTION_PUBLISH_TASK)) && (!PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user1))) {
      request.setAttribute("illegelaccess", "yes");
    }
    long orgId = jbr.getOrganization().getOrgId();
    long deptId = 0L;
    if (jbr.getDepartment() != null) {
      deptId = jbr.getDepartment().getDepartmentId();
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
          jbapp.setJobreqId(jbr.getJobreqId());
          newvendorList.add(jbapp);
        }
      }
      jbForm.setPublishToExternal(sysrule.getPublishToExternal());
      jbForm.setPublishToEmpRef(sysrule.getPublishToEmpRef());
    }
    if ((vendorList != null) && (vendorList.size() > 0))
    {
      Iterator itr = vendorList.iterator();
      while ((itr != null) && (itr.hasNext()))
      {
        Long l = (Long)itr.next();
        User usr = this.userdao.getUserFullNameEmailAndUserIdById(l.longValue());
        PublishToVendor jbapp = new PublishToVendor();
        jbapp.setUserId(usr.getUserId());
        jbapp.setUserName(usr.getFirstName());
        jbapp.setIsFromSystemRule("N");
        jbapp.setJobreqId(jbr.getJobreqId());
        newvendorList.add(jbapp);
      }
    }
    jbForm.setPublishToVendorList(newvendorList);
    jbForm.setParentOrgId(jbr.getOrganization().getOrgId());
    if (jbr.getDepartment() != null) {
      jbForm.setDepartmentId(jbr.getDepartment().getDepartmentId());
    }
    Set<RefferalScheme> employeeReferralSchemeListnew = new HashSet();
    Set<RefferalScheme> agencyReferralSchemeListnew = new HashSet();
    Set<RefferalScheme> employeeReferralSchemeList = jbForm.getEmployeeReferralSchemeList();
    if (employeeReferralSchemeList != null)
    {
      Iterator<RefferalScheme> itr = employeeReferralSchemeList.iterator();
      while ((itr != null) && (itr.hasNext()))
      {
        RefferalScheme rs = (RefferalScheme)itr.next();
        RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
        rs.setRefferalScheme_Name(scheme.getRefferalScheme_Name());
        employeeReferralSchemeListnew.add(rs);
      }
    }
    jbForm.setEmployeeReferralSchemeList(employeeReferralSchemeListnew);
    Set<RefferalScheme> agencyReferralSchemeList = jbForm.getAgencyReferralSchemeList();
    if (agencyReferralSchemeList != null)
    {
      Iterator<RefferalScheme> itr = agencyReferralSchemeList.iterator();
      while ((itr != null) && (itr.hasNext()))
      {
        RefferalScheme rs = (RefferalScheme)itr.next();
        RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
        rs.setRefferalScheme_Name(scheme.getRefferalScheme_Name());
        agencyReferralSchemeListnew.add(rs);
      }
    }
    jbForm.setAgencyReferralSchemeList(agencyReferralSchemeListnew);
    
    return jbr;
  }
  
  public void setPublishSaveErrorCondition(JobRequisition jb, JobRequisitionForm jbForm, String userids, HttpServletRequest request, Set<RefferalScheme> employeeReferralSchemes, Set<RefferalScheme> agencyReferralScheme, User user1)
  {
    List newvendorList = new ArrayList();
    List publishToVendorList = this.jobrequisitiondao.getPublishToVendorList(jb.getJobreqId());
    if ((publishToVendorList != null) && (publishToVendorList.size() > 0)) {
      for (int i = 0; i < publishToVendorList.size(); i++)
      {
        PublishToVendor jbapp = (PublishToVendor)publishToVendorList.get(i);
        newvendorList.add(jbapp);
      }
    }
    String[] ids = StringUtils.tokenize(userids, ",");
    if (ids != null) {
      for (int i = 0; i < ids.length; i++)
      {
        User user = UserDAO.getUserFullNameEmailAndUserId(new Long(ids[i]).longValue());
        PublishToVendor jbapp = new PublishToVendor();
        jbapp.setUserId(user.getUserId());
        jbapp.setUserName(user.getFirstName());
        jbapp.setIsFromSystemRule("N");
        jbapp.setJobreqId(jb.getJobreqId());
        newvendorList.add(jbapp);
      }
    }
    jbForm.setPublishToVendorList(newvendorList);
    

    Set<RefferalScheme> employeeReferralSchemeListnew = new HashSet();
    Set<RefferalScheme> agencyReferralSchemeListnew = new HashSet();
    
    Set<RefferalScheme> employeeReferralSchemeList = jb.getEmployeeReferralSchemeList();
    if (employeeReferralSchemeList != null)
    {
      Iterator<RefferalScheme> itr = employeeReferralSchemeList.iterator();
      while ((itr != null) && (itr.hasNext()))
      {
        RefferalScheme rs = (RefferalScheme)itr.next();
        employeeReferralSchemeListnew.add(rs);
      }
    }
    employeeReferralSchemeList = employeeReferralSchemes;
    if (employeeReferralSchemeList != null)
    {
      Iterator<RefferalScheme> itr = employeeReferralSchemeList.iterator();
      while ((itr != null) && (itr.hasNext()))
      {
        RefferalScheme rs = (RefferalScheme)itr.next();
        RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
        rs.setRefferalScheme_Name(scheme.getRefferalScheme_Name());
        employeeReferralSchemeListnew.add(rs);
      }
    }
    jbForm.setEmployeeReferralSchemeList(employeeReferralSchemeListnew);
    
    Set<RefferalScheme> agencyReferralSchemeList = jb.getAgencyReferralSchemeList();
    if (agencyReferralSchemeList != null)
    {
      Iterator<RefferalScheme> itr = agencyReferralSchemeList.iterator();
      while ((itr != null) && (itr.hasNext()))
      {
        RefferalScheme rs = (RefferalScheme)itr.next();
        agencyReferralSchemeListnew.add(rs);
      }
    }
    agencyReferralSchemeList = agencyReferralScheme;
    if (agencyReferralSchemeList != null)
    {
      Iterator<RefferalScheme> itr = agencyReferralSchemeList.iterator();
      while ((itr != null) && (itr.hasNext()))
      {
        RefferalScheme rs = (RefferalScheme)itr.next();
        RefferalScheme scheme = this.refferaldao.getRefferalSchemeDetails(rs.getRefferalScheme_Id());
        rs.setRefferalScheme_Name(scheme.getRefferalScheme_Name());
        agencyReferralSchemeListnew.add(rs);
      }
    }
    jbForm.setAgencyReferralSchemeList(agencyReferralSchemeListnew);
  }
  
  public void initiateApprovalscr(JobRequisitionForm jbForm, HttpServletRequest request, User user1)
    throws Exception
  {
    JobRequisition jbr = this.jobrequisitiondao.getJobRequision(String.valueOf(jbForm.getJobreqId()));
    

    List errorList = BOFactory.getBusinessFilterBO().isRequisitionMandatoryValueSucceeed(jbr, user1, "REQUISITION_SCREEN");
    logger.info("errorList in initiateApprovalscr" + errorList.size());
    if ((errorList != null) && (errorList.size() > 0))
    {
      request.setAttribute("error_list", errorList);
      request.setAttribute("mandatoryValuesNotFilled", "yes");
    }
    if (jbr.getState().equals("In Approval")) {
      request.setAttribute("initiateApproval", "yes");
    }
    if ((!PermissionBO.isHiringManagerOrRecruiter(user1, jbr.getHiringmgr().getUserId(), jbr.getRecruiterId(), jbr.getIsgrouprecruiter())) && (!PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user1))) {
      request.setAttribute("illegelaccess", "yes");
    }
    List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jbr.getJobreqId(), "job");
    



    long orgId = jbr.getOrganization().getOrgId();
    long deptId = 0L;
    if (jbr.getDepartment() != null) {
      deptId = jbr.getDepartment().getDepartmentId();
    }
    SystemRule sysrule = this.ruletxdao.getOrganizationRuleByType(orgId, deptId, Common.SYSTEM_RULE_REQUISTION_APPROVER);
    

    int levelorder = 0;
    List newapproverList = new ArrayList();
    if ((approversList != null) && (approversList.size() > 0)) {
      for (int i = 0; i < approversList.size(); i++)
      {
        JobTemplateApprovers jbapp = (JobTemplateApprovers)approversList.get(i);
        
        jbapp.setIsFromSystemRule("N");
        levelorder += 1;
        jbapp.setLevelorder(levelorder);
        newapproverList.add(jbapp);
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
            jbapp.setJbTmplId(jbr.getJobreqId());
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
          jbapp.setJbTmplId(jbr.getJobreqId());
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
    jbForm.setApproversList(newapproverList);
  }
  
  public List getActiveJobTitleListForForm(long super_user_key)
  {
    List jobtitleList = this.jobrequisitiondao.getRequisitionsActiveList(super_user_key);
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      JobRequisition jreqnew = new JobRequisition();
      jreqnew.setJobreqId(jreq.getJobreqId());
      jreqnew.setJobTitle(jreq.getJobreqcode() + " # " + jreq.getJobreqName());
      
      newjobtitleList.add(jreqnew);
    }
    return newjobtitleList;
  }
  
  public List getJobTitlesActiveAndOpenListForForm(long super_user_key)
  {
    List jobtitleList = this.jobrequisitiondao.getRequisitionsActiveAndOpenList(super_user_key);
    
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      JobRequisition jreqnew = new JobRequisition();
      jreqnew.setJobreqId(jreq.getJobreqId());
      jreqnew.setJobTitle(jreq.getRequisition_number() + " # " + jreq.getJobreqName());
      
      newjobtitleList.add(jreqnew);
    }
    return newjobtitleList;
  }
  
  public List getJobTitlesActiveListForForm(long superUserKey)
  {
    List jobtitleList = this.jobrequisitiondao.getRequisitionsActiveList(superUserKey);
    
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      JobRequisition jreqnew = new JobRequisition();
      jreqnew.setJobreqId(jreq.getJobreqId());
      jreqnew.setJobTitle(jreq.getRequisition_number() + " # " + jreq.getJobreqName());
      
      newjobtitleList.add(jreqnew);
    }
    return newjobtitleList;
  }
  
  public List getJobTitlesActiveAndOpenListAssigedToUser(User user)
  {
    List newjobtitleList = new ArrayList();
    if (PermissionBO.isPermissionApplied("ALL_REQUISITIONS", user))
    {
      newjobtitleList = getJobTitlesActiveAndOpenListForForm(user.getSuper_user_key());
    }
    else
    {
      List jobtitleList = this.jobrequisitiondao.getJobTitlesActiveAndOpenListAssigedToUser(user.getUserId());
      for (int i = 0; i < jobtitleList.size(); i++)
      {
        JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
        JobRequisition jreqnew = new JobRequisition();
        jreqnew.setJobreqId(jreq.getJobreqId());
        jreqnew.setJobTitle(jreq.getRequisition_number() + " # " + jreq.getJobreqName());
        
        newjobtitleList.add(jreqnew);
      }
    }
    return newjobtitleList;
  }
  
  public List getJobTitlesActiveListAssigedToUser(User user)
  {
    List newjobtitleList = new ArrayList();
    if (PermissionBO.isPermissionApplied("ALL_REQUISITIONS", user))
    {
      newjobtitleList = getJobTitlesActiveListForForm(user.getSuper_user_key());
    }
    else
    {
      List jobtitleList = this.jobrequisitiondao.getJobTitlesActiveListAssigedToUser(user.getUserId());
      for (int i = 0; i < jobtitleList.size(); i++)
      {
        JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
        JobRequisition jreqnew = new JobRequisition();
        jreqnew.setJobreqId(jreq.getJobreqId());
        jreqnew.setJobTitle(jreq.getRequisition_number() + " # " + jreq.getJobreqName());
        
        newjobtitleList.add(jreqnew);
      }
    }
    return newjobtitleList;
  }
  
  public String getCurrentOwnerName(long ownerId, String isGroup)
  {
    String oname = "";
    try
    {
      if ((isGroup != null) && (isGroup.equals("Y")))
      {
        UserGroup usergrp = UserDAO.getUserGroupNameandIdById(ownerId);
        oname = usergrp.getUsergrpName();
      }
      else
      {
        User usr = UserDAO.getUserFullNameEmailAndUserId(ownerId);
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
  
  public void agencyredemptionlistLOVs(AgencyRedemptionSummaryForm summaryform, User user)
  {
    List agencyList = this.userdao.getAllUsersByTypeTx("Vendor", user.getSuper_user_key());
    summaryform.setAgencyList(agencyList);
    
    summaryform.setJobtitleList(getActiveJobTitleListForForm(user.getSuper_user_key()));
    List refferalBudgetCodeList = this.lovtxdao.getAllRefferalBudgetCode(user.getSuper_user_key());
    List refferalSchemeTypeList = this.lovtxdao.getAllRefferalSchemeType();
    List refferalSchemeList = this.lovtxdao.getAllRefferalSchemes("Vendor", user.getSuper_user_key());
    List ruleList = this.lovtxdao.getAllRefferalRules();
    summaryform.setReferralBudgetCodeList(refferalBudgetCodeList);
    summaryform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    summaryform.setRuleList(ruleList);
    summaryform.setReferralSchemeList(refferalSchemeList);
    summaryform.setReleaseTypeList(Constant.getReleasedTypesList());
    summaryform.setPaidTypeList(Constant.getYesNoList());
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    summaryform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    summaryform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      summaryform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
  }
  
  public List getJobRequistionTemplatesForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getJobRequistionTemplatesForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getJobRequistionTemplatesForPagination(String templatename, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getJobRequistionTemplatesForPagination(templatename, orgId, departmentId, jobgradeId, jobtypeId, statuscri, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getSearchJobRequisitionPagination(String jobreqname, String jobreqid, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri, String statecri, String jobreqcode, String budgetcodeid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getSearchJobRequisitionPagination(jobreqname, jobreqid, orgId, departmentId, jobgradeId, jobtypeId, statuscri, statecri, jobreqcode, budgetcodeid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchJobRequisitions(User user, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.searchJobRequisitions(user, searchCriteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchJobRequisitionsTemplate(User user1, RequisitionTemplateSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.searchJobRequisitionsTemplate(user1, searchCriteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchJobRequisitionsExternalUser(long superUserKey, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.searchJobRequisitionsExternalUser(superUserKey, searchCriteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchJobRequisitionsExternalUserSimple(long superUserKey, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.searchJobRequisitionsExternalUserSimple(superUserKey, searchCriteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getSearchJobRequisitionPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getSearchJobRequisitionPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public long getRequisitionIdByUuid(String uuid)
    throws Exception
  {
    return this.jobrequisitiondao.getRequisitionIdByUuid(uuid);
  }
  
  public long getSuperUserKeyByReqId(long jb_req_id)
    throws Exception
  {
    return this.jobrequisitiondao.getSuperUserKeyByReqId(jb_req_id);
  }
  
  public boolean isReqIdAndUuidMatch(long reqId, String uuid)
    throws Exception
  {
    long reqidfromdb = this.jobrequisitiondao.getRequisitionIdByUuid(uuid);
    if (reqidfromdb == reqId) {
      return true;
    }
    return false;
  }
  
  public List getAllJobRequistions()
  {
    return this.jobrequisitiondao.getAllJobRequistions();
  }
  
  public List getAllRequisitionComments(String uuid)
  {
    return this.jobrequisitiondao.getAllRequisitionComments(uuid);
  }
  
  public void saveRequisitionComment(String uuid, User user1, String commentType, String comment)
  {
    if (!StringUtils.isNullOrEmpty(comment))
    {
      RequisitionComments com = new RequisitionComments();
      com.setCommentType(commentType);
      com.setComment(comment);
      com.setCreatedById(user1.getUserId());
      com.setCreatedByName(user1.getFirstName() + " " + user1.getLastName());
      com.setCreatedDate(new Date());
      com.setUuid(uuid);
      BOFactory.getJobRequistionBO().saveRequisitionComment(com);
    }
  }
  
  public void saveRequisitionComment(RequisitionComments comment)
  {
    this.jobrequisitiondao.saveRequisitionComment(comment);
  }
  
  public List getNonIndexedJobRequistions()
  {
    return this.jobrequisitiondao.getNonIndexedJobRequistions();
  }
  
  public List getNonFilteredJobRequistions()
  {
    return this.jobrequisitiondao.getNonFilteredJobRequistions();
  }
  
  public int getCountOfSearchJobRequisitionPagination()
  {
    return this.jobrequisitiondao.getCountOfSearchJobRequisitionPagination();
  }
  
  public int getCountOfSearchJobRequisitionPagination(String jobreqname, String jobreqid, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri, String statecri, String jobreqcode, String budgetcodeId)
  {
    return this.jobrequisitiondao.getCountOfSearchJobRequisitionPagination(jobreqname, jobreqid, orgId, departmentId, jobgradeId, jobtypeId, statuscri, statecri, jobreqcode, budgetcodeId);
  }
  
  public List getMyJobRequistionsByOrgByDeptForPagination(String orgid, String hiringmgrid, String deptid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getMyJobRequistionsByOrgByDeptForPagination(orgid, hiringmgrid, deptid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllJobRequistionsByOrgByDeptForPagination(String orgid, String deptid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getAllJobRequistionsByOrgByDeptForPagination(orgid, deptid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfMyJobRequistionsByOrgByDept(String orgid, String hiringmgrid, String deptid)
  {
    return this.jobrequisitiondao.getCountOfMyJobRequistionsByOrgByDept(orgid, hiringmgrid, deptid);
  }
  
  public int getCountOfAllJobRequistionsByOrgByDept(String orgid, String deptid)
  {
    return this.jobrequisitiondao.getCountOfAllJobRequistionsByOrgByDept(orgid, deptid);
  }
  
  public List getJobRequistionsByOrgByRecruiterByDeptForPagination(String orgid, String recruiterId, String deptid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getJobRequistionsByOrgByRecruiterByDeptForPagination(orgid, recruiterId, deptid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public DataTableBean jobRequistionsByOrgByRecruiterByDeptForPagination(String orgid, String recruiterId, String deptid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    DataTableBean dbean = new DataTableBean();
    List rlist = this.jobrequisitiondao.getJobRequistionsByOrgByRecruiterByDeptForPagination(orgid, recruiterId, deptid, pageSize, startIndex, dir_str, sort_str);
    




    int total = this.jobrequisitiondao.getCountOfJobRequistionsByOrgByRecruiterIdByDept(orgid, recruiterId, deptid);
    
    dbean.setValueList(rlist);
    dbean.setTotalcount(total);
    return dbean;
  }
  
  public int getCountOfJobRequistionsByOrgByRecruiterIdByDept(String orgid, String recruiterId, String deptid)
  {
    return this.jobrequisitiondao.getCountOfJobRequistionsByOrgByRecruiterIdByDept(orgid, recruiterId, deptid);
  }
  
  public List searchJobsForPagination(User user, String jobTilte, String postdate, String cri, String locationId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.searchJobsForPagination(user, jobTilte, postdate, cri, locationId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getSearchJobsPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getSearchJobsPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountsearchJobs()
  {
    return this.jobrequisitiondao.getCountsearchJobs();
  }
  
  public List newJobsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.newJobsForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map newJobsForPaginationByVendor(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.newJobsForPaginationByVendor(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List searchJobsForPagination(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.searchJobsForPagination(user, jobTilte, postdate, cri, locationId, orgId, departmentId, jobgradeId, jobtypeId, jobreqcode, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchJobsForPaginationByVendor(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.searchJobsForPaginationByVendor(user, jobTilte, postdate, cri, locationId, orgId, departmentId, jobgradeId, jobtypeId, jobreqcode, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map myJobRequistionByVendor(User agency, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.myJobRequistionByVendor(agency, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountsearchJobs(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode)
  {
    return this.jobrequisitiondao.getCountsearchJobs(user, jobTilte, postdate, cri, locationId, orgId, departmentId, jobgradeId, jobtypeId, jobreqcode);
  }
  
  public int getCountsearchJobsByVendor(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode)
  {
    return this.jobrequisitiondao.getCountsearchJobsByVendor(user, jobTilte, postdate, cri, locationId, orgId, departmentId, jobgradeId, jobtypeId, jobreqcode);
  }
  
  public int getCountsearchJobs(User user, String jobTilte, String postdate, String cri, String locationId)
  {
    return this.jobrequisitiondao.getCountsearchJobs(user, jobTilte, postdate, cri, locationId);
  }
  
  public int getCountNewJobs()
  {
    return this.jobrequisitiondao.getCountNewJobs();
  }
  
  public List<String> getRequistionData(String query, String cri)
  {
    List requistions = this.jobrequisitiondao.getRequistionDataByQuery(query, cri);
    

    List<String> newReqNameList = new ArrayList();
    for (int i = 0; i < requistions.size(); i++)
    {
      JobRequisition jb = (JobRequisition)requistions.get(i);
      String fname = jb.getJobreqId() + " " + jb.getJobreqName() + " " + jb.getJobreqcode() + "|" + jb.getJobreqId();
      
      newReqNameList.add(fname);
      logger.info("fname" + fname);
    }
    return newReqNameList;
  }
  
  public String getRequistionDataXML(String query, String cri, User user)
  {
    logger.info("inside getRequistionDataXML");
    StringBuffer stbfr = new StringBuffer();
    

    stbfr.append("<results>");
    stbfr.append("\n");
    List requistions = this.jobrequisitiondao.getRequistionDataByQuery(query, cri);
    for (int i = 0; i < requistions.size(); i++)
    {
      JobRequisition jb = (JobRequisition)requistions.get(i);
      if (PermissionBO.isRequistionHeaderTreeAllowed(user, jb))
      {
        String temp = "<rs id=\"" + jb.getJobreqId() + "\" info=\"" + jb.getJobreqcode() + "\">" + jb.getJobreqName() + "</rs>";
        

        stbfr.append(temp);
        stbfr.append("\n");
      }
    }
    stbfr.append("</results>");
    return stbfr.toString();
  }
  
  public User getHiringManagerByReqId(long requisitionId)
  {
    return this.jobrequisitiondao.getHiringManagerByReqId(requisitionId);
  }
  
  public long getRecruiterIdByReqId(long requisitionId)
  {
    return this.jobrequisitiondao.getRecruiterIdByReqId(requisitionId);
  }
  
  public Recruiter getRecruiterByReqId(long requisitionId)
  {
    return this.jobrequisitiondao.getRecruiterByReqId(requisitionId);
  }
  
  public boolean isAllApproved(long jobreqid, String type)
  {
    boolean allapproved = false;
    JobRequisition jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jobreqid));
    if ((jb.getState() != null) && (jb.getState().equals("Approved")))
    {
      List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jobreqid, "job");
      
      allapproved = true;
      for (int j = 0; j < approversList.size(); j++)
      {
        JobTemplateApprovers japp1 = (JobTemplateApprovers)approversList.get(j);
        if (!japp1.getApproved().equals("Y"))
        {
          allapproved = false;
          break;
        }
      }
    }
    return allapproved;
  }
  
  public int getCountOfAllJobRequistionTemplates()
  {
    return this.jobrequisitiondao.getCountOfAllJobRequistionTemplates();
  }
  
  public int getCountOfAllJobRequistionTemplates(String templatename, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri)
  {
    return this.jobrequisitiondao.getCountOfAllJobRequistionTemplates(templatename, orgId, departmentId, jobgradeId, jobtypeId, statuscri);
  }
  
  public DataTableBean getMyJobRequistionsForPagination(String currentuserid, String currentusername, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.jobrequisitiondao.getMyJobRequistionsForPagination(currentuserid, currentusername, pageSize, startIndex, dir_str, sort_str);
    

    int totalcount = this.jobrequisitiondao.getCountOfMyJobRequistions(currentuserid, currentusername);
    
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public List getMyActiveAndFilledJobRequistionsHiringMgr(long currentuserid)
  {
    return this.jobrequisitiondao.getMyActiveAndFilledJobRequistionsHiringMgr(currentuserid);
  }
  
  public List getActiveAndFilledJobRequistions(long superUserKey, long currentuserid)
  {
    return this.jobrequisitiondao.getActiveAndFilledJobRequistions(superUserKey, currentuserid);
  }
  
  public List getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsHiringMgr(long currentuserid)
  {
    return this.jobrequisitiondao.getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsHiringMgr(currentuserid);
  }
  
  public List getMyActiveJobRequistionsForPagination(String currentuserid, String currentusername, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getMyActiveJobRequistionsForPagination(currentuserid, currentusername, pageSize, startIndex, dir_str, sort_str);
  }
  
  public DataTableBean myActiveJobRequistionsForPagination(String currentuserid, String currentusername, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.jobrequisitiondao.getMyActiveJobRequistionsForPagination(currentuserid, currentusername, pageSize, startIndex, dir_str, sort_str);
    


    valueList = BOFactory.getApplicantBO().isAllapplicantViewed(valueList);
    int totalcount = this.jobrequisitiondao.getCountOfAllMyActiveJobRequistions(currentuserid, currentusername);
    
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public DataTableBean activeJobRequistionsForPagination(long superUserKey, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.jobrequisitiondao.activeJobRequistionsForPagination(superUserKey, pageSize, startIndex, dir_str, sort_str);
    

    valueList = BOFactory.getApplicantBO().isAllapplicantViewed(valueList);
    int totalcount = this.jobrequisitiondao.getCountOfAllActiveJobRequistions(superUserKey);
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public DataTableBean getMyJobRequistionsForPagination(String currentuserid, String currentusername, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.jobrequisitiondao.getMyJobRequistionsForPagination(currentuserid, currentusername, searchCriteria, pageSize, startIndex, dir_str, sort_str);
    

    int totalcount = this.jobrequisitiondao.getCountOfMyJobRequistions(currentuserid, currentusername, searchCriteria);
    
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public DataTableBean getMyJobRequistionsForPaginationRecruiter(String currentuserid, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.jobrequisitiondao.getMyJobRequistionsForPaginationRecruiter(currentuserid, searchCriteria, pageSize, startIndex, dir_str, sort_str);
    

    int totalcount = this.jobrequisitiondao.getCountOfMyJobRequistionsRecruiter(currentuserid, searchCriteria);
    
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public int getCountOfAllMyActiveJobRequistions(String currentuserid, String currentusername)
  {
    return this.jobrequisitiondao.getCountOfAllMyActiveJobRequistions(currentuserid, currentusername);
  }
  
  public List getActiveJobRequistionsAssignedToMeAsRecruiterForPagination(String currentuserid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getActiveJobRequistionsAssignedToMeAsRecruiterForPagination(currentuserid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public DataTableBean activeJobRequistionsAssignedToMeAsRecruiterForPagination(String currentuserid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.jobrequisitiondao.getActiveJobRequistionsAssignedToMeAsRecruiterForPagination(currentuserid, pageSize, startIndex, dir_str, sort_str);
    


    valueList = BOFactory.getApplicantBO().isAllapplicantViewed(valueList);
    
    int totalcount = this.jobrequisitiondao.getCountOfActiveJobRequistionsAssignedToMeAsRecruiter(currentuserid);
    
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public DataTableBean jobRequistionsAssignedToMeAsRecruiterForPagination(String currentuserid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.jobrequisitiondao.getJobRequistionsAssignedToMeAsRecruiterForPagination(currentuserid, pageSize, startIndex, dir_str, sort_str);
    


    valueList = BOFactory.getApplicantBO().isAllapplicantViewed(valueList);
    
    int totalcount = this.jobrequisitiondao.getCountOfJobRequistionsAssignedToMeAsRecruiter(currentuserid);
    
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public List getMyActiveAndFilledJobRequistionsRecruiter(long currentuserid)
  {
    return this.jobrequisitiondao.getMyActiveAndFilledJobRequistionsRecruiter(currentuserid);
  }
  
  public List getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsRecruiter(long currentuserid)
  {
    return this.jobrequisitiondao.getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsRecruiter(currentuserid);
  }
  
  public List getMyActiveAndNotFilledWithTargetDateSlipingJobRequistions(long superUserKey, long currentuserid)
  {
    return this.jobrequisitiondao.getMyActiveAndNotFilledWithTargetDateSlipingJobRequistions(superUserKey, currentuserid);
  }
  
  public int getCountOfActiveJobRequistionsAssignedToMeAsRecruiter(String currentuserid)
  {
    return this.jobrequisitiondao.getCountOfActiveJobRequistionsAssignedToMeAsRecruiter(currentuserid);
  }
  
  public int getCountOfMyJobRequistions(String currentuserid, String currentusername)
  {
    return this.jobrequisitiondao.getCountOfMyJobRequistions(currentuserid, currentusername);
  }
  
  public List getMyApprovalJobRequistionsForPagination(String currentownerid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.jobrequisitiondao.getMyApprovalJobRequistionsForPagination(currentownerid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfAllMyApprovalJobRequistions(String currentownerid)
  {
    return this.jobrequisitiondao.getCountOfAllMyApprovalJobRequistions(currentownerid);
  }
  
  public JobRequisition copytemplatetojob(User user1, long templateId, String jobreqName, String withtml)
    throws Exception
  {
    HTMLInputFilter filter = new HTMLInputFilter();
    try
    {
      JobRequisition jbr = new JobRequisition();
      jbr.setSuper_user_key(user1.getSuper_user_key());
      jbr.setJobreqName(filter.filter(jobreqName));
      jbr.setJobTitle(filter.filter(jobreqName));
      jbr.setJobPosition(filter.filter(jobreqName));
      jbr.setUuid(UUID.randomUUID().toString());
      jbr.setState("Draft");
      jbr.setStatus("Open");
      jbr.setIsnewPositions("Y");
      jbr.setCreatedBy(user1.getUserName());
      jbr.setCreatedDate(new Date());
      
      jbr.setNumberOfOpening(1);
      
      long maxvalue = this.jobrequisitiondao.getMaxValueFromRequistion(user1.getSuper_user_key());
      jbr.setRequisition_number(maxvalue + 1L);
      if ((!StringUtils.isNullOrEmpty(withtml)) && (!withtml.equals("true")))
      {
        jbr.setOrganization(user1.getOrganization());
        jbr.setHiringmgr(user1);
        jbr.setCurrentOwnerId(user1.getUserId());
        jbr.setCurrentOwnerName(user1.getFirstName() + " " + user1.getLastName());
        
        jbr.setIsGroup("N");
        

        preJobRequitionCreate(jbr, user1);
        BOFactory.getJobRequistionTXBO().copytemplatetojobreq(jbr, null, null, null, null, null, user1);
        

        postJobRequitionCreate(jbr, user1);
      }
      else
      {
        JobRequisionTemplate jbtmpl = this.jobrequisitiondao.getJobRequisionTemplate(templateId);
        
        List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(templateId, "tmpl");
        
        List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(templateId, "tmpl");
        
        List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(templateId, "tmpl");
        


        copyTmpltoJobreq(jbr, jbtmpl);
        

        jbr.setApproversList(approversList);
        jbr.setComptetencyList(comptetencyList);
        jbr.setAccomplishmentList(accomplishmentList);
        


        jbr.setTemplateId(templateId);
        if (jbtmpl.getHiringmgr() == null)
        {
          jbr.setHiringmgr(user1);
          jbr.setCurrentOwnerId(user1.getUserId());
          jbr.setCurrentOwnerName(user1.getFirstName() + " " + user1.getLastName());
        }
        else
        {
          jbr.setCurrentOwnerId(jbr.getHiringmgr().getUserId());
          jbr.setCurrentOwnerName(user1.getFirstName() + " " + user1.getLastName());
        }
        jbr.setIsGroup("N");
        

        jbr.setRecruiterId(jbtmpl.getRecruiterId());
        jbr.setRecruiterName(jbtmpl.getRecruiterName());
        

        List formVariablesList = BOFactory.getVariableBO().getFormVariablesList("REQUISITION_TMPL_FORM", templateId, user1.getSuper_user_key());
        

        List variableValueList = BOFactory.getVariableBO().getVariablesValues(templateId, "REQUISITION_TMPL_FORM");
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("custom.variable.copy.template.to.requisition.preference"))) && (Constant.getValue("custom.variable.copy.template.to.requisition.preference").equalsIgnoreCase("Requisition")))
        {
          List reqVariableIdList = new ArrayList();
          List reqformVariablesList = BOFactory.getVariableBO().getFormVariablesList("REQUISITION_FORM", 0L, user1.getSuper_user_key());
          if ((reqformVariablesList != null) && (reqformVariablesList.size() > 0)) {
            for (int i = 0; i < reqformVariablesList.size(); i++)
            {
              FormVariablesMap vmap = (FormVariablesMap)reqformVariablesList.get(i);
              
              reqVariableIdList.add(String.valueOf(vmap.getVariable().getVariableId()));
            }
          }
          List formVariablesListnew = new ArrayList();
          List variablesValueListexist = new ArrayList();
          if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
            for (int i = 0; i < formVariablesList.size(); i++)
            {
              FormVariablesMap vmap = (FormVariablesMap)formVariablesList.get(i);
              
              String vid = String.valueOf(vmap.getVariable().getVariableId());
              if (!reqVariableIdList.contains(vid))
              {
                FormVariablesMap newvmap = new FormVariablesMap();
                
                newvmap.setFormVariablemapId(vmap.getFormVariablemapId());
                
                newvmap.setFormName(vmap.getFormName());
                newvmap.setFormCode(vmap.getFormCode());
                newvmap.setVariable(vmap.getVariable());
                newvmap.setSequence(vmap.getSequence());
                newvmap.setIsMandatory(vmap.getIsMandatory());
                newvmap.setCreatedBy(vmap.getCreatedBy());
                newvmap.setCreatedDate(vmap.getCreatedDate());
                newvmap.setUpdatedBy(vmap.getUpdatedBy());
                newvmap.setUpdatedDate(vmap.getUpdatedDate());
                newvmap.setIdValue(vmap.getIdValue());
                
                formVariablesListnew.add(newvmap);
              }
              else
              {
                variablesValueListexist.add(vid);
              }
            }
          }
          List variableValueListnew = new ArrayList();
          if ((variableValueList != null) && (variableValueList.size() > 0)) {
            for (int i = 0; i < variableValueList.size(); i++)
            {
              VariablesValues vval = (VariablesValues)variableValueList.get(i);
              
              String vid = String.valueOf(vval.getVariableId());
              if (!variablesValueListexist.contains(vid)) {
                variableValueListnew.add(vval);
              }
            }
          }
          preJobRequitionCreate(jbr, user1);
          BOFactory.getJobRequistionTXBO().copytemplatetojobreq(jbr, comptetencyList, accomplishmentList, approversList, formVariablesListnew, variableValueListnew, user1);
          

          postJobRequitionCreate(jbr, user1);
        }
        else
        {
          preJobRequitionCreate(jbr, user1);
          BOFactory.getJobRequistionTXBO().copytemplatetojobreq(jbr, comptetencyList, accomplishmentList, approversList, formVariablesList, variableValueList, user1);
          

          postJobRequitionCreate(jbr, user1);
        }
      }
      return jbr;
    }
    catch (PluginException e)
    {
      logger.info("plugin exception copytemplatetojob" + e.getMessage());
      throw new Exception(e.getMessage());
    }
    catch (Exception e)
    {
      logger.info("exeption copytemplatetojob ...", e);
      throw e;
    }
  }
  
  public void sendEmailForRequisition(User user1, JobRequisition jb, String comment, String functionType, String notificationFunction)
    throws Exception
  {
    boolean isHiringMgr = false;
    boolean isRecruiter = false;
    boolean isCurrentOwner = false;
    boolean isWatcher = false;
    

    EmailNotificationSetting ems = BOFactory.getLovBO().getEmailNotificationSetting(notificationFunction, user1.getSuper_user_key());
    if (ems != null)
    {
      if (ems.getIsHiringMgr().equals("A")) {
        isHiringMgr = true;
      }
      if (ems.getIsRecruiter().equals("A")) {
        isRecruiter = true;
      }
      if (ems.getIsWatcher().equals("A")) {
        isWatcher = true;
      }
      if (ems.getIsCurrentOwner().equals("A")) {
        isCurrentOwner = true;
      }
    }
    List cclist = new ArrayList();
    
    String[] to = { user1.getEmailId() };
    cclist = getEmailIdsForRequisition(jb, isWatcher, isHiringMgr, isRecruiter, isCurrentOwner);
    if (!StringUtils.isNullOrEmpty(Constant.getValue("all.requisition.activities.cc"))) {
      cclist.add(Constant.getValue("all.requisition.activities.cc"));
    }
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    
    String[] bcc = null;
    
    String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    


    EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
    


    emailtask.setUser(user1);
    emailtask.setFunctionType(functionType);
    emailtask.setJobreq(jb);
    emailtask.setComment(comment);
    EmailTaskManager.sendEmail(emailtask);
  }
  
  public List getEmailIdsForRequisition(JobRequisition jb, boolean isWatcher, boolean isHiringMgr, boolean isRecruiter, boolean isCurrentOwner)
    throws Exception
  {
    List cclist = new ArrayList();
    if (isWatcher) {
      cclist = BOFactory.getLovBO().getWatchersEmails(jb.getJobreqId(), 0L, "REQ");
    }
    if (isHiringMgr)
    {
      User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(jb.getHiringmgr().getUserId());
      cclist.add(usr.getEmailId());
    }
    if (isCurrentOwner) {
      if ((!StringUtils.isEmpty(jb.getIsGroup())) && (jb.getIsGroup().equals("Y")))
      {
        long groupId = 0L;
        
        UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(jb.getCurrentOwnerId());
        if (usrgrp != null)
        {
          Set users = usrgrp.getUsers();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User touser = (User)itr.next();
              cclist.add(touser.getEmailId());
            }
          }
        }
      }
      else
      {
        User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(jb.getCurrentOwnerId());
        if (usr != null) {
          cclist.add(usr.getEmailId());
        }
      }
    }
    if (isRecruiter) {
      if (jb.getRecruiterId() > 0L) {
        if ((!StringUtils.isEmpty(jb.getIsgrouprecruiter())) && (jb.getIsgrouprecruiter().equals("Y")))
        {
          long groupId = 0L;
          
          UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(jb.getRecruiterId());
          if (usrgrp != null)
          {
            Set users = usrgrp.getUsers();
            if ((users != null) && (users.size() > 0))
            {
              Iterator itr = users.iterator();
              while (itr.hasNext())
              {
                User touser = (User)itr.next();
                cclist.add(touser.getEmailId());
              }
            }
          }
        }
        else
        {
          User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(jb.getRecruiterId());
          if (usr != null) {
            cclist.add(usr.getEmailId());
          }
        }
      }
    }
    return cclist;
  }
  
  private void preJobRequitionCreate(JobRequisition requisition, User user)
    throws PluginException
  {
    if (PluginScripts.preREQCREATE != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("JobRequisitionObject", requisition);
        ue.setObject("UserObject", user);
        ue.firePreRequistionCreateScript();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception" + ex.getMessage());
        throw new PluginException(ex.getMessage(), ex);
      }
    }
  }
  
  private void postJobRequitionCreate(JobRequisition requisition, User user)
    throws PluginException
  {
    if (PluginScripts.postREQCREATE != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("JobRequisitionObject", requisition);
        ue.setObject("UserObject", user);
        ue.firePostRequistionCreateScript();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception" + ex.getMessage());
        throw new PluginException(ex.getMessage(), ex);
      }
    }
  }
  
  public void copyTmpltoJobreq(JobRequisition jb, JobRequisionTemplate jbtmpl)
  {
    jb.setTemplateName(jbtmpl.getTemplateName());
    jb.setJobreqDesc(jbtmpl.getTemplateDesc());
    jb.setJobRoles(jbtmpl.getJobRoles());
    
    jb.setJobDetails(jbtmpl.getJobDetails());
    jb.setJobInstructions(jbtmpl.getJobInstructions());
    jb.setInternal(jbtmpl.getInternal());
    jb.setDurationinmonths(jbtmpl.getDurationinmonths());
    jb.setMinyearsofExpRequired(jbtmpl.getMinyearsofExpRequired());
    jb.setMaxyearsofExpRequired(jbtmpl.getMaxyearsofExpRequired());
    jb.setMinimumLevelOfEducation(jbtmpl.getMinimumLevelOfEducation());
    jb.setOtherExperience(jbtmpl.getOtherExperience());
    jb.setDefaultStandardHours(jbtmpl.getDefaultStandardHours());
    jb.setNumberOfOpeningRemain(jb.getNumberOfOpening());
    if (jbtmpl.getDesignation() != null) {
      jb.setJobTitle(jbtmpl.getDesignation().getDesignationName());
    }
    jb.setDesignation(jbtmpl.getDesignation());
    jb.setJobcategory(jbtmpl.getJobcategory());
    
    Organization org = new Organization();
    org.setOrgId(jbtmpl.getOrganization().getOrgId());
    jb.setOrganization(org);
    if (jbtmpl.getLocation() != null)
    {
      Location location1 = new Location();
      location1.setLocationId(jbtmpl.getLocation().getLocationId());
      jb.setLocation(location1);
    }
    else
    {
      jb.setLocation(null);
    }
    if (jbtmpl.getCompfrequency() != null)
    {
      CompFrequency cmp = new CompFrequency();
      cmp.setCompFrequencyId(jbtmpl.getCompfrequency().getCompFrequencyId());
      jb.setCompfrequency(cmp);
    }
    else
    {
      jb.setCompfrequency(null);
    }
    if (jbtmpl.getJobtype() != null)
    {
      JobType jobtype = new JobType();
      jobtype.setJobTypeId(jbtmpl.getJobtype().getJobTypeId());
      jb.setJobtype(jobtype);
    }
    else
    {
      jb.setJobtype(null);
    }
    if (jbtmpl.getWorkshift() != null)
    {
      WorkShift ws = new WorkShift();
      ws.setShiftId(jbtmpl.getWorkshift().getShiftId());
      jb.setWorkshift(ws);
    }
    if (jbtmpl.getFlsa() != null)
    {
      FlsaStatus fl = new FlsaStatus();
      fl.setFlsaId(jbtmpl.getFlsa().getFlsaId());
      jb.setFlsa(fl);
    }
    if (jbtmpl.getDepartment() != null)
    {
      Department dept = new Department();
      dept.setDepartmentId(jbtmpl.getDepartment().getDepartmentId());
      jb.setDepartment(dept);
    }
    if (jbtmpl.getHiringmgr() != null)
    {
      User hiringmgr = new User();
      hiringmgr.setUserId(jbtmpl.getHiringmgr().getUserId());
      jb.setHiringmgr(hiringmgr);
    }
    if (jbtmpl.getProjectcode() != null)
    {
      ProjectCodes pcode = new ProjectCodes();
      pcode.setProjectId(jbtmpl.getProjectcode().getProjectId());
      jb.setProjectcode(pcode);
    }
    if (jbtmpl.getJobgrade() != null)
    {
      JobGrade jgrade = new JobGrade();
      jgrade.setJobgradeId(jbtmpl.getJobgrade().getJobgradeId());
      jb.setJobgrade(jgrade);
    }
    if (jbtmpl.getSalaryplan() != null)
    {
      SalaryPlan splan = new SalaryPlan();
      splan.setSalaryplanId(jbtmpl.getSalaryplan().getSalaryplanId());
      jb.setSalaryplan(splan);
    }
    if (jbtmpl.getBudgetcode() != null)
    {
      BudgetCode bcode = new BudgetCode();
      bcode.setBudgetId(jbtmpl.getBudgetcode().getBudgetId());
      jb.setBudgetcode(bcode);
    }
  }
  
  public void downloadpdf(JobRequisitionForm jbForm, HttpServletRequest request, User user1)
    throws Exception
  {
    JobRequisition jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionByUUID(jbForm.getUuid());
      jbForm.setJobreqId(jb.getJobreqId());
      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jb.getJobreqId(), "job");
      

      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jb.getJobreqId(), "job");
      

      List approverList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jb.getJobreqId(), "job");
      

      List applicantList = BOFactory.getApplicantBO().getOfferedApplicants(jb.getJobreqId());
      
      String filePath = "";
      String fileName = "";
      
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      

      logger.info("***** path ***** : " + request.getContextPath());
      Document document = new Document(PageSize.A4, 25.0F, 25.0F, 25.0F, 25.0F);
      

      fileName = jb.getJobreqcode() + ".pdf";
      filePath = Constant.getValue("ATTACHMENT_PATH") + fileName;
      PdfWriter.getInstance(document, new FileOutputStream(filePath));
      document.open();
      Paragraph title = new Paragraph("Job Requisition Details", FontFactory.getFont("Helvetica", 14.0F, 1, new Color(0, 0, 255)));
      

      title.setAlignment(1);
      document.add(title);
      

      PdfPTable table = new PdfPTable(4);
      
      table.setSpacingBefore(15.0F);
      PdfPCell cell = new PdfPCell(new Paragraph());
      table.addCell(new Paragraph("Requisition No.: ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table.addCell(new Paragraph("Requisition Code: ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table.addCell(new Paragraph("Status: ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table.addCell(new Paragraph("Remaining Positions: ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      

      table.addCell(new Paragraph(String.valueOf(jb.getJobreqId()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      
      table.addCell(new Paragraph(jb.getJobreqcode(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      
      table.addCell(new Paragraph(jb.getState() + " > " + jb.getStatus(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      






      table.addCell(new Paragraph(StringUtils.isNullOrEmpty(String.valueOf(jb.getNumberOfOpeningRemain())) ? "" : String.valueOf(jb.getNumberOfOpeningRemain()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      document.add(table);
      
      logger.info("test 11111111111111111");
      PdfPTable table1 = new PdfPTable(2);
      
      table1.setSpacingBefore(15.0F);
      
      PdfPCell cell1 = new PdfPCell(new Paragraph("Requisition Data", FontFactory.getFont("Helvetica", 10.0F, 3)));
      

      cell1.setColspan(2);
      cell1.setBorder(0);
      table1.addCell(cell1);
      
      table1.addCell(new Paragraph("Is internal job", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getInternal()) ? "" : jb.getInternal(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table1.addCell(new Paragraph("Requisition Name", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getJobreqName()) ? "" : jb.getJobreqName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table1.addCell(new Paragraph("Job Title", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getJobTitle()) ? "" : jb.getJobTitle(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table1.addCell(new Paragraph("Job Position Name", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getJobPosition()) ? "" : jb.getJobPosition(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table1.addCell(new Paragraph("No. of Openings", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(String.valueOf(jb.getNumberOfOpening())) ? "" : String.valueOf(jb.getNumberOfOpening()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table1.addCell(new Paragraph("Is New Position", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getIsnewPositions()) ? "" : jb.getIsnewPositions(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table1.addCell(new Paragraph("Target Finish Date", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph((StringUtils.isNullOrEmpty(String.valueOf(jb.getTargetfinishdate()))) || (String.valueOf(jb.getTargetfinishdate()).equals("null")) ? "" : String.valueOf(jb.getTargetfinishdate()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      if (jb.getLocation() != null)
      {
        table1.addCell(new Paragraph("Location", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getLocation().getLocationName()) ? "" : jb.getLocation().getLocationName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      else
      {
        table1.addCell(new Paragraph("Location", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph("", FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      table1.addCell(new Paragraph("Organization", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getOrganization().getOrgName()) ? "" : jb.getOrganization().getOrgName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      if (jb.getDepartment() != null)
      {
        table1.addCell(new Paragraph("Department", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getDepartment().getDepartmentName()) ? "" : jb.getDepartment().getDepartmentName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      else
      {
        table1.addCell(new Paragraph("Department", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph("", FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      if (jb.getProjectcode() != null)
      {
        table1.addCell(new Paragraph("Project Code", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getProjectcode().getProjCode()) ? "" : jb.getProjectcode().getProjCode(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      else
      {
        table1.addCell(new Paragraph("Project Code", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph("", FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      String name = jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName();
      
      table1.addCell(new Paragraph("Hirining Manager", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(name) ? "" : name, FontFactory.getFont("Helvetica", 8.0F, 1)));
      if (jb.getJobgrade() != null)
      {
        table1.addCell(new Paragraph("Job Grade", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getJobgrade().getJobGradeName()) ? "" : jb.getJobgrade().getJobGradeName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      else
      {
        table1.addCell(new Paragraph("Job Grade", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph("", FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      if (jb.getSalaryplan() != null)
      {
        table1.addCell(new Paragraph("Salary Plan", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getSalaryplan().getSalaryPlanName()) ? "" : jb.getSalaryplan().getSalaryPlanName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      else
      {
        table1.addCell(new Paragraph("Salary Plan", FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table1.addCell(new Paragraph("", FontFactory.getFont("Helvetica", 8.0F, 1)));
      }
      table1.addCell(new Paragraph("Budget Code", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(jb.getBudgetcode() == null ? "" : jb.getBudgetcode().getBudgetCode(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table1.addCell(new Paragraph("Recruiter", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getRecruiterName()) ? "" : jb.getRecruiterName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table1.addCell(new Paragraph("Notes", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table1.addCell(new Paragraph(StringUtils.isNullOrEmpty(jb.getJobreqDesc()) ? "" : jb.getJobreqDesc(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      document.add(table1);
      logger.info("***** table 2 ***** : ");
      PdfPTable table2 = new PdfPTable(2);
      
      table2.setSpacingBefore(15.0F);
      
      PdfPCell cell2 = new PdfPCell(new Paragraph("Job Details", FontFactory.getFont("Helvetica", 10.0F, 3)));
      

      cell2.setColspan(2);
      cell2.setBorder(0);
      table2.addCell(cell2);
      
      table2.addCell(new Paragraph("Introduction", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getJobInstructions() == null ? "" : jb.getJobInstructions(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table2.addCell(new Paragraph("Job Details", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getJobDetails() == null ? "" : jb.getJobDetails(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table2.addCell(new Paragraph("Job Roles", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getJobRoles() == null ? "" : jb.getJobRoles(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table2.addCell(new Paragraph("Default Standard Working Hours", FontFactory.getFont("Helvetica", 8.0F, 0)));
      


      table2.addCell(new Paragraph(String.valueOf(jb.getDefaultStandardHours()) == null ? "" : String.valueOf(jb.getDefaultStandardHours()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("Duration in months", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(String.valueOf(jb.getDurationinmonths()) == null ? "" : String.valueOf(jb.getDurationinmonths()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("Min years of exp required", FontFactory.getFont("Helvetica", 8.0F, 0)));
      


      table2.addCell(new Paragraph(String.valueOf(jb.getMinyearsofExpRequired()) == null ? "" : String.valueOf(jb.getMinyearsofExpRequired()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("Max years of exp required", FontFactory.getFont("Helvetica", 8.0F, 0)));
      


      table2.addCell(new Paragraph(String.valueOf(jb.getMaxyearsofExpRequired()) == null ? "" : String.valueOf(jb.getMaxyearsofExpRequired()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("Minimum Level of Education", FontFactory.getFont("Helvetica", 8.0F, 0)));
      


      table2.addCell(new Paragraph(String.valueOf(jb.getMinimumLevelOfEducation()) == null ? "" : String.valueOf(jb.getMinimumLevelOfEducation()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("Other Experience", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getOtherExperience() == null ? "" : jb.getOtherExperience(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      table2.addCell(new Paragraph("Job Type", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getJobtype() == null ? "" : jb.getJobtype().getJobTypeName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("Work Shift", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getWorkshift() == null ? "" : jb.getWorkshift().getShiftName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("FLSA Status", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getFlsa() == null ? "" : jb.getFlsa().getFlsaName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      



      table2.addCell(new Paragraph("Comp Frequency", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph(jb.getCompfrequency() == null ? "" : jb.getCompfrequency().getCompFrequencyName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      document.add(table2);
      logger.info("***** table 3 ***** : ");
      PdfPTable table3 = new PdfPTable(3);
      
      table3.setSpacingBefore(15.0F);
      
      PdfPCell cell3 = new PdfPCell(new Paragraph("Qualification Details", FontFactory.getFont("Helvetica", 10.0F, 3)));
      

      cell3.setColspan(3);
      cell3.setBorder(0);
      table3.addCell(cell3);
      
      PdfPCell cell4 = new PdfPCell(new Paragraph("Competencies Required", FontFactory.getFont("Helvetica", 9.0F, 1)));
      

      cell4.setColspan(3);
      
      table3.addCell(cell4);
      
      PdfPCell cell5 = new PdfPCell(new Paragraph("5-Excellent      4-Very good      3-Good      2-Average      1-Poor      0-Unaware", FontFactory.getFont("Helvetica", 8.0F, 0, new Color(0, 0, 255))));
      



      cell5.setColspan(3);
      
      table3.addCell(cell5);
      
      table3.addCell(new Paragraph("Competency Name", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table3.addCell(new Paragraph("Minimum score required", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table3.addCell(new Paragraph("Is mandatory?", FontFactory.getFont("Helvetica", 8.0F, 0)));
      if ((comptetencyList != null) && (comptetencyList.size() > 0))
      {
        for (int i = 0; i < comptetencyList.size(); i++)
        {
          JobTemplateCompetency comp = (JobTemplateCompetency)comptetencyList.get(i);
          

          table3.addCell(new Paragraph(comp.getCharName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          

          table3.addCell(new Paragraph(String.valueOf(comp.getImportance()), FontFactory.getFont("Helvetica", 8.0F, 0)));
          

          table3.addCell(new Paragraph((comp.getMandatory() != null) && (comp.getMandatory().equals("on")) ? "Yes" : "No", FontFactory.getFont("Helvetica", 8.0F, 0)));
        }
      }
      else
      {
        PdfPCell cell6 = new PdfPCell(new Paragraph("Competency are not defined.", FontFactory.getFont("Helvetica", 9.0F, 1)));
        

        cell6.setColspan(3);
        

        table3.addCell(cell6);
      }
      PdfPCell cell13 = new PdfPCell(new Paragraph("", FontFactory.getFont("Helvetica", 9.0F, 1)));
      
      cell13.setColspan(3);
      cell13.setBorder(0);
      table3.addCell(cell13);
      
      PdfPCell cell7 = new PdfPCell(new Paragraph("Accomplishment Required", FontFactory.getFont("Helvetica", 9.0F, 1)));
      

      cell7.setColspan(3);
      
      table3.addCell(cell7);
      
      table3.addCell(new Paragraph("Accomplishment Name", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table3.addCell(new Paragraph("Minimum score required", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table3.addCell(new Paragraph("Is mandatory?", FontFactory.getFont("Helvetica", 8.0F, 0)));
      if ((accomplishmentList != null) && (accomplishmentList.size() > 0))
      {
        for (int i = 0; i < accomplishmentList.size(); i++)
        {
          JobTemplateAccomplishment acc = (JobTemplateAccomplishment)accomplishmentList.get(i);
          

          table3.addCell(new Paragraph(acc.getAccName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          
          table3.addCell(new Paragraph(String.valueOf(acc.getImportance()), FontFactory.getFont("Helvetica", 8.0F, 0)));
          

          table3.addCell(new Paragraph((acc.getMandatory() != null) && (acc.getMandatory().equals("on")) ? "Yes" : "No", FontFactory.getFont("Helvetica", 8.0F, 0)));
        }
      }
      else
      {
        PdfPCell cell8 = new PdfPCell(new Paragraph("Accomplishments are not defined.", FontFactory.getFont("Helvetica", 9.0F, 1)));
        

        cell8.setColspan(3);
        
        table3.addCell(cell8);
      }
      document.add(table3);
      

      logger.info("***** table 4 ***** : ");
      
      PdfPTable table4 = new PdfPTable(9);
      
      table4.setSpacingBefore(15.0F);
      
      PdfPCell cell9 = new PdfPCell(new Paragraph("Approval Details", FontFactory.getFont("Helvetica", 10.0F, 3)));
      

      cell9.setColspan(9);
      cell9.setBorder(0);
      table4.addCell(cell9);
      
      PdfPCell cell10 = new PdfPCell(new Paragraph("Approval Statistics", FontFactory.getFont("Helvetica", 9.0F, 1)));
      
      cell10.setColspan(9);
      
      table4.addCell(cell10);
      
      table4.addCell(new Paragraph("Level", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("Approver Name", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("Status", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("On Date", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("Comment", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("Re-assigned Graph", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("Re-assigned by", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("Re-assigned on", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table4.addCell(new Paragraph("Re-assigned comment", FontFactory.getFont("Helvetica", 8.0F, 0)));
      for (int i = 0; i < approverList.size(); i++)
      {
        JobTemplateApprovers japp2 = (JobTemplateApprovers)approverList.get(i);
        
        String approvetext = "Approved";
        if (japp2.getApproved().equals("Y")) {
          approvetext = "Approved";
        }
        if (japp2.getApproved().equals("N")) {
          approvetext = "Not approved";
        }
        if (japp2.getApproved().equals("R")) {
          approvetext = "Rejected";
        }
        table4.addCell(new Paragraph("Level-" + (i + 1), FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table4.addCell(new Paragraph(japp2.getApproverName() == null ? "" : japp2.getApproverName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        


        table4.addCell(new Paragraph(approvetext, FontFactory.getFont("Helvetica", 8.0F, 0)));
        
        table4.addCell(new Paragraph(japp2.getApprovedDate() == null ? "" : DateUtil.convertDateToStringDate(japp2.getApprovedDate(), datepattern), FontFactory.getFont("Helvetica", 8.0F, 0)));
        




        table4.addCell(new Paragraph(japp2.getComment() == null ? "" : japp2.getComment(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        

        table4.addCell(new Paragraph(japp2.getReassignedgraphname() == null ? "" : japp2.getReassignedgraphname(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        


        table4.addCell(new Paragraph(japp2.getReassignedbyName() == null ? "" : japp2.getReassignedbyName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        


        table4.addCell(new Paragraph(japp2.getReassignedDate() == null ? "" : DateUtil.convertDateToStringDate(japp2.getReassignedDate(), datepattern), FontFactory.getFont("Helvetica", 8.0F, 0)));
        




        table4.addCell(new Paragraph(japp2.getReassignedcomment() == null ? "" : japp2.getReassignedcomment(), FontFactory.getFont("Helvetica", 8.0F, 0)));
      }
      document.add(table4);
      logger.info("***** table 5 ***** : ");
      PdfPTable table5 = new PdfPTable(8);
      
      table5.setSpacingBefore(15.0F);
      
      PdfPCell cell11 = new PdfPCell(new Paragraph("Offered Applicants Details", FontFactory.getFont("Helvetica", 10.0F, 3)));
      

      cell11.setColspan(8);
      cell11.setBorder(0);
      table5.addCell(cell11);
      
      table5.addCell(new Paragraph("Name", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table5.addCell(new Paragraph("Status", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table5.addCell(new Paragraph("Qualification", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table5.addCell(new Paragraph("Primary skill", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table5.addCell(new Paragraph("Email-ID", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table5.addCell(new Paragraph("Experience", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table5.addCell(new Paragraph("Current Organization", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table5.addCell(new Paragraph("Date of Birth", FontFactory.getFont("Helvetica", 8.0F, 0)));
      if (applicantList != null) {
        for (int i = 0; i < applicantList.size(); i++)
        {
          JobApplicant applicant = (JobApplicant)applicantList.get(i);
          

          table5.addCell(new Paragraph(applicant.getFullName() == null ? "" : applicant.getFullName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          


          table5.addCell(new Paragraph(applicant.getInterviewState() == null ? "" : applicant.getInterviewState(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          



          table5.addCell(new Paragraph(applicant.getHeighestQualification() == null ? "" : applicant.getHeighestQualification(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          


          table5.addCell(new Paragraph(applicant.getPrimarySkill() == null ? "" : applicant.getPrimarySkill(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          



          table5.addCell(new Paragraph(applicant.getEmail() == null ? "" : applicant.getEmail(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          


          table5.addCell(new Paragraph(String.valueOf(applicant.getNoofyearsexp()) == null ? "" : String.valueOf(applicant.getNoofyearsexp()), FontFactory.getFont("Helvetica", 8.0F, 0)));
          


          table5.addCell(new Paragraph(applicant.getPreviousOrganization() == null ? "" : applicant.getPreviousOrganization(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          


          table5.addCell(new Paragraph(applicant.getDateofbirth() == null ? "" : DateUtil.convertDateToStringDate(applicant.getDateofbirth(), datepattern), FontFactory.getFont("Helvetica", 8.0F, 0)));
        }
      }
      if ((applicantList != null) && (applicantList.size() < 1))
      {
        PdfPCell cell12 = new PdfPCell(new Paragraph("Applicants not yet offered.", FontFactory.getFont("Helvetica", 9.0F, 1)));
        

        cell12.setColspan(8);
        
        table5.addCell(cell12);
      }
      document.add(table5);
      
      document.close();
      
      logger.info("filepath : " + filePath);
      request.setAttribute("filePath", fileName);
    }
    catch (Exception e)
    {
      logger.info("error on download pdf" + e.getMessage());
      throw e;
    }
  }
  
  public void saveJobReqCompetency(JobTemplateCompetency jbc)
  {
    this.jobrequisitiondao.saveJobReqCompetency(jbc);
    if ((!StringUtils.isNullOrEmpty(jbc.getType())) && (jbc.getType().equals("job")))
    {
      JobRequisition jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbc.getJbTmplId()));
      if (jb != null)
      {
        jb.setIsindexSearchApplied(0);
        this.jobrequisitiondao.updateJobRequistion(jb);
      }
    }
  }
  
  public void saveJobReqAccomplieshment(JobTemplateAccomplishment jbc)
  {
    this.jobrequisitiondao.saveJobReqAccomplieshment(jbc);
    if ((!StringUtils.isNullOrEmpty(jbc.getType())) && (jbc.getType().equals("job")))
    {
      JobRequisition jb = this.jobrequisitiondao.getJobRequision(String.valueOf(jbc.getJbTmplId()));
      if (jb != null)
      {
        jb.setIsindexSearchApplied(0);
        this.jobrequisitiondao.updateJobRequistion(jb);
      }
    }
  }
  
  public void deleteCompetency(long id)
  {
    this.jobrequisitiondao.deleteCompetency(id);
  }
  
  public RequistionExamQnsAssign getRequistionExamQnsAssignWithExamName(long jb_req_id)
  {
    return this.jobrequisitiondao.getRequistionExamQnsAssignWithExamName(jb_req_id);
  }
  
  public void deleteAccomplishment(long id)
  {
    this.jobrequisitiondao.deleteAccomplishment(id);
  }
  
  public List getJobRequisionTemplateComptetencyList(long id, String type)
  {
    return this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(id, type);
  }
  
  public List getJobRequisionTemplateAccomplishmentList(long id, String type)
  {
    return this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(id, type);
  }
  
  public List getJobRequisionTemplateApproversList(long id, String type)
  {
    return this.jobrequisitiondao.getJobRequisionTemplateApproversList(id, type);
  }
  
  public void deleteApprovers(long id)
  {
    this.jobrequisitiondao.deleteApprovers(id);
  }
  
  public JobRequisionTemplate edittemplate(JobRequisitionTemplateForm tmplForm, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      

      commonLovPopulateTmpl(tmplForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate editjobtemplateselector(JobRequisitionTemplateForm tmplForm, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      

      commonLovPopulateTmpl(tmplForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate activatetemplate(JobRequisitionTemplateForm tmplForm, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      

      jb.setStatus("Active");
      jb.setState("Open");
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      jb = this.jobrequisitiondao.updateJobRequisitionTemplate(jb);
      
      commonLovPopulateTmpl(tmplForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public void saveTemplateApprover(JobTemplateApprovers jbtapprover, String templateid, String type, User user1)
    throws Exception
  {
    try
    {
      int level = this.jobrequisitiondao.getMaxLevelForApprover(new Long(templateid).longValue(), type);
      
      jbtapprover.setLevelorder(level + 1);
      
      this.jobrequisitiondao.saveJobReqApprover(jbtapprover);
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate closetemplate(JobRequisitionTemplateForm tmplForm, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      

      jb.setStatus("Closed");
      jb.setState("Open");
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      jb = this.jobrequisitiondao.updateJobRequisitionTemplate(jb);
      
      commonLovPopulateTmpl(tmplForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate updateTemplatestatus(JobRequisitionTemplateForm tmplForm, String status, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      

      jb.setStatus(status);
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      this.jobrequisitiondao.updateJobRequisitionTemplate(jb);
      tmplForm.setStatus(status);
      tmplForm.getStatus();
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate savejobtemplate(JobRequisitionTemplateForm tmplForm, HttpServletRequest request, String hiringManagerId, String recruiterId, String isInternal, User user1)
    throws Exception
  {
    try
    {
      JobRequisionTemplate jbt = new JobRequisionTemplate();
      tmplForm.setHiringMgrId(Long.parseLong(hiringManagerId));
      tmplForm.recruiterId = new Long(recruiterId).longValue();
      tmplForm.toValue(jbt, request);
      
      jbt.setCreatedBy(user1.getUserName());
      jbt.setCreatedDate(new Date());
      jbt.setStatus("A");
      jbt.setInternal(isInternal);
      jbt.setState("Open");
      jbt.setStatus("Draft");
      jbt.setSuper_user_key(user1.getSuper_user_key());
      boolean isError = false;
      if ((tmplForm.getErrorList() != null) && (tmplForm.getErrorList().size() > 0))
      {
        request.setAttribute("error_list", tmplForm.getErrorList());
        

        isError = true;
        tmplForm.setTabselected(5);
      }
      if (!isError)
      {
        this.jobrequisitiondao.saveJobRequisitionTemplate(jbt);
        if ((tmplForm.getFormVariableDataList() != null) && (tmplForm.getFormVariableDataList().size() > 0))
        {
          this.variabletxdao.deleteVariableValues(jbt.getTemplateId(), "REQUISITION_TMPL_FORM");
          
          this.variabletxdao.saveVariableValues(tmplForm.getFormVariableDataList());
        }
        request.setAttribute("savetemplate", "yes");
      }
      else
      {
        request.setAttribute("savetemplate", "no");
      }
      jbt = this.jobrequisitiondao.getJobRequisionTemplate(jbt.getTemplateId());
      
      tmplForm.setTemplateId(jbt.getTemplateId());
      
      commonLovPopulateTmpl(tmplForm, jbt, user1);
      
      return jbt;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate updatejobtemplate(JobRequisitionTemplateForm jbForm, HttpServletRequest request, String isInternal, String hiringMgrId, String recruiterId, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(jbForm.getTemplateId());
      

      jbForm.recruiterId = new Long(recruiterId).longValue();
      jbForm.setTemplateId(jb.getTemplateId());
      jbForm.toValue(jb, request);
      if ((!StringUtils.isNullOrEmpty(jbForm.getEducationName())) && (!jbForm.getEducationName().equals("Other"))) {
        jb.setOtherMinimumLevelOfEducation("");
      }
      jb.setInternal(isInternal);
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      
      boolean isError = false;
      if ((jbForm.getErrorList() != null) && (jbForm.getErrorList().size() > 0))
      {
        request.setAttribute("error_list", jbForm.getErrorList());
        isError = true;
        jbForm.setTabselected(5);
      }
      if (!isError)
      {
        jb = this.jobrequisitiondao.updateJobRequisitionTemplate(jb);
        if ((jbForm.getFormVariableDataList() != null) && (jbForm.getFormVariableDataList().size() > 0))
        {
          this.variabletxdao.deleteVariableValues(jb.getTemplateId(), "REQUISITION_TMPL_FORM");
          
          this.variabletxdao.saveVariableValues(jbForm.getFormVariableDataList());
        }
        request.setAttribute("savetemplate", "yes");
      }
      else
      {
        request.setAttribute("savetemplate", "no");
      }
      jb = this.jobrequisitiondao.getJobRequisionTemplate(jb.getTemplateId());
      commonLovPopulateTmpl(jbForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate deletetemplate(JobRequisitionTemplateForm tmplForm, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      

      jb.setStatus("Deleted");
      jb.setState("Open");
      jb.setUpdatedBy(user1.getUserName());
      jb.setUpdatedDate(new Date());
      jb = this.jobrequisitiondao.updateJobRequisitionTemplate(jb);
      
      commonLovPopulateTmpl(tmplForm, jb, user1);
      
      return jb;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public JobRequisionTemplate saveastemplate(JobRequisitionTemplateForm tmplForm, HttpServletRequest request, User user1)
    throws Exception
  {
    JobRequisionTemplate jb = null;
    try
    {
      jb = this.jobrequisitiondao.getJobRequisionTemplate(tmplForm.getTemplateId());
      
      JobRequisionTemplate jbnew = new JobRequisionTemplate();
      
      tmplForm.toValue(jbnew, request);
      

      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jbnew.getTemplateId(), "tmpl");
      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jbnew.getTemplateId(), "tmpl");
      List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jbnew.getTemplateId(), "tmpl");
      jbnew.setTemplateName(jb.getTemplateName() + "_" + "save as");
      jbnew.setStatus("Draft");
      jbnew.setState("Open");
      jbnew.setCreatedBy(user1.getUserName());
      jbnew.setCreatedDate(new Date());
      jbnew.setSuper_user_key(user1.getSuper_user_key());
      JobRequisionTemplate jbnew1 = this.jobrequisitiondao.saveJobRequisitionTemplate(jbnew);
      


      this.jobrequisitiondao.deleteCompetencies(jbnew1.getTemplateId(), "tmpl");
      this.jobrequisitiondao.saveJobReqCompetency(comptetencyList, "tmpl", jbnew1.getTemplateId());
      



      this.jobrequisitiondao.deleteAccomplishments(jbnew1.getTemplateId(), "tmpl");
      this.jobrequisitiondao.saveJobReqAccomplishments(accomplishmentList, "tmpl", jbnew1.getTemplateId());
      


      this.jobrequisitiondao.deleteApprovers(jbnew1.getTemplateId(), "tmpl");
      this.jobrequisitiondao.saveApproversList(approversList, "tmpl", jbnew1.getTemplateId());
      


      commonLovPopulateTmpl(tmplForm, jbnew1, user1);
      

      return jbnew1;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public void commonLovPopulateTmpl(JobRequisitionTemplateForm tmplForm, JobRequisionTemplate jbt, User user1)
    throws Exception
  {
    try
    {
      List jobtypeList = this.lovtxdao.getJobTypeList(user1.getSuper_user_key());
      tmplForm.setJobtypeList(jobtypeList);
      List workshiftList = this.lovtxdao.getWorkShiftList(user1.getSuper_user_key());
      tmplForm.setWorkshiftList(workshiftList);
      List flsastatusList = this.lovtxdao.getFlsaStatusList();
      tmplForm.setFlsastatusList(flsastatusList);
      List compfrequencyList = this.lovtxdao.getCompFrequencyList();
      tmplForm.setCompfrequencyList(compfrequencyList);
      tmplForm.setSkillRatingList(Constant.skillsRatingsList);
      
      List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(jbt.getTemplateId(), "tmpl");
      

      List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(jbt.getTemplateId(), "tmpl");
      

      List approversList = this.jobrequisitiondao.getJobRequisionTemplateApproversList(jbt.getTemplateId(), "tmpl");
      


      tmplForm.setComptetencyList(comptetencyList);
      tmplForm.setAccomplishmentList(accomplishmentList);
      
      List orgList = this.lovtxdao.getAllOrganization(user1.getSuper_user_key());
      tmplForm.setOrganizationList(orgList);
      List deptlist = new ArrayList();
      tmplForm.setDepartmentList(deptlist);
      if (orgList.size() > 0)
      {
        Organization org = (Organization)orgList.get(0);
        tmplForm.setDepartmentList(this.lovtxdao.getDepartmentListByOrg(String.valueOf(jbt.getOrganization().getOrgId())));
      }
      List budgetcodelist = new ArrayList();
      List projectcodeList = new ArrayList();
      tmplForm.setProjectcodeList(projectcodeList);
      tmplForm.setBudgetCodeList(budgetcodelist);
      if ((orgList.size() > 0) && (deptlist.size() > 0) && (jbt.getDepartment() != null))
      {
        budgetcodelist = this.lovtxdao.getAllBudgetCodeDetailsbyorganizationanddepartment(String.valueOf(jbt.getOrganization().getOrgId()), String.valueOf(jbt.getDepartment().getDepartmentId()));
        





        tmplForm.setBudgetCodeList(budgetcodelist);
      }
      else if (orgList.size() > 0)
      {
        budgetcodelist = this.lovtxdao.getAllBudgetCodeDetailsbyorganizationanddepartment(String.valueOf(jbt.getOrganization().getOrgId()), String.valueOf(0));
        



        tmplForm.setBudgetCodeList(budgetcodelist);
      }
      if (jbt.getDepartment() != null)
      {
        projectcodeList = this.lovtxdao.getProjectCodesByDept(String.valueOf(jbt.getDepartment().getDepartmentId()));
        

        tmplForm.setProjectcodeList(projectcodeList);
      }
      List jobgradeList = this.lovtxdao.getAllJobGradeDetails(user1.getSuper_user_key());
      tmplForm.setJobgradeList(jobgradeList);
      List salaryplanList = BOFactory.getLovBO().retrieveSalaryPlanByOrganization(String.valueOf(jbt.getOrganization().getOrgId()), user1.getSuper_user_key());
      tmplForm.setSalaryplanList(salaryplanList);
      


      List refferalSchemeList = this.lovtxdao.getAllRefferalSchemeDetails();
      tmplForm.setRefferalSchemeList(refferalSchemeList);
      tmplForm.setAgencyRefferalSchemeList(refferalSchemeList);
      
      tmplForm.setApproversList(approversList);
      tmplForm.setStdworkinghoursunitList(Constant.getStdworkinghoursunitList());
      
      tmplForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
      if (jbt.getHiringmgr().getUserId() > 0L)
      {
        User usr = this.userdao.getUserFullNameEmailAndUserIdById(jbt.getHiringmgr().getUserId());
        
        tmplForm.setHiringMgrId(jbt.getHiringmgr().getUserId());
        tmplForm.setHiringMgrName(usr.getFirstName() + " " + usr.getLastName());
        
        jbt.setHiringmgr(usr);
      }
      List jobcategoryList = this.lovtxdao.getAllJobCategories(user1.getSuper_user_key());
      tmplForm.setJobCategoryList(jobcategoryList);
      List designationList = this.lovtxdao.getAllDesignations(user1.getSuper_user_key());
      tmplForm.setDesignationList(designationList);
      




      List<BusinessCriteria> filters = BOFactory.getBusinessRuleBO().getBusinessCriteriasByTemplate(jbt.getTemplateId());
      
      tmplForm.setFilters(filters);
    }
    catch (Exception e)
    {
      logger.info("Error on commonLovPopulateTmpl" + e);
      throw e;
    }
  }
  
  public void commonLovPopulateForCreate(JobRequisitionTemplateForm tmplForm, User user1)
    throws Exception
  {
    try
    {
      List orgList = this.lovtxdao.getAllOrganization(user1.getSuper_user_key());
      tmplForm.setOrganizationList(orgList);
      List deptlist = new ArrayList();
      tmplForm.setDepartmentList(deptlist);
      List projectCodelist = new ArrayList();
      tmplForm.setProjectcodeList(projectCodelist);
      if (orgList.size() > 0)
      {
        Organization org = (Organization)orgList.get(0);
        deptlist = this.lovtxdao.getDepartmentListByOrg(String.valueOf(org.getOrgId()));
        tmplForm.setDepartmentList(deptlist);
        if (deptlist.size() > 0)
        {
          Department dept = (Department)deptlist.get(0);
          if (dept != null)
          {
            projectCodelist = this.lovtxdao.getProjectCodesByDept(String.valueOf(dept.getDepartmentId()));
            tmplForm.setProjectcodeList(projectCodelist);
          }
        }
        List salaryplanList = BOFactory.getLovBO().retrieveSalaryPlanByOrganization(String.valueOf(org.getOrgId()), user1.getSuper_user_key());
        tmplForm.setSalaryplanList(salaryplanList);
        
        List budgetcodelist = this.lovtxdao.getAllBudgetCodeDetailsbyorganizationanddepartment(String.valueOf(org.getOrgId()), String.valueOf(0));
        tmplForm.setBudgetCodeList(budgetcodelist);
      }
      List jobgradeList = this.lovtxdao.getAllJobGradeDetails(user1.getSuper_user_key());
      tmplForm.setJobgradeList(jobgradeList);
      



      List jobtypeList = this.lovtxdao.getJobTypeList(user1.getSuper_user_key());
      tmplForm.setJobtypeList(jobtypeList);
      List workshiftList = this.lovtxdao.getWorkShiftList(user1.getSuper_user_key());
      tmplForm.setWorkshiftList(workshiftList);
      List flsastatusList = this.lovtxdao.getFlsaStatusList();
      tmplForm.setFlsastatusList(flsastatusList);
      List compfrequencyList = this.lovtxdao.getCompFrequencyList();
      tmplForm.setCompfrequencyList(compfrequencyList);
      List refferalSchemeList = this.lovtxdao.getAllRefferalSchemeDetails();
      tmplForm.setRefferalSchemeList(refferalSchemeList);
      tmplForm.setAgencyRefferalSchemeList(refferalSchemeList);
      tmplForm.setStdworkinghoursunitList(Constant.getStdworkinghoursunitList());
      
      tmplForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
      
      List jobcategoryList = this.lovtxdao.getAllJobCategories(user1.getSuper_user_key());
      tmplForm.setJobCategoryList(jobcategoryList);
      List designationList = this.lovtxdao.getAllDesignations(user1.getSuper_user_key());
      tmplForm.setDesignationList(designationList);
      

      tmplForm.setSkillRatingList(Constant.skillsRatingsList);
    }
    catch (Exception e)
    {
      throw e;
    }
  }
  
  public boolean isAgencyHeaderTreeAllowed(User user, JobRequisition jb)
  {
    return this.jobrequisitiondao.isAgencyHeaderTreeAllowed(user, jb);
  }
  
  public PublishJobBoards savePublishJobBoards(PublishJobBoards publishJobBoards)
  {
    PublishJobBoards pjb = null;
    
    pjb = this.jobrequisitiondao.savePublishJobBoards(publishJobBoards);
    return pjb;
  }
  
  public PublishJobBoards updatePublishJobBoards(PublishJobBoards publishJobBoards)
  {
    PublishJobBoards pjb = null;
    
    pjb = this.jobrequisitiondao.updatePublishJobBoards(publishJobBoards);
    return pjb;
  }
  
  public PublishJobBoards deletePublishedJobBoards(PublishJobBoards publishJobBoards)
  {
    PublishJobBoards pjb = null;
    
    pjb = this.jobrequisitiondao.deletePublishedJobBoards(publishJobBoards);
    return pjb;
  }
  
  public boolean isjobBoardCodeExist(String jobreqId, String jobBoardCode)
  {
    return this.jobrequisitiondao.isjobBoardCodeExist(jobreqId, jobBoardCode);
  }
  
  public PublishJobBoards getPublishJobBoardsByReqIdandjobBoardCode(String jobreqId, String jobCode)
  {
    PublishJobBoards pjb = null;
    
    pjb = this.jobrequisitiondao.getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, jobCode);
    return pjb;
  }
  
  public List getAllPublishJobBoardsListByReqId(long jobreqId)
  {
    return this.jobrequisitiondao.getAllPublishJobBoardsListByReqId(jobreqId);
  }
  
  public List getJobBoardsListByJobBoardCode(String jobBoardCode)
  {
    return this.jobrequisitiondao.getJobBoardsListByJobBoardCode(jobBoardCode);
  }
  
  public void loadTemplateSearchLov(JobRequisitionTemplateForm tmplForm, User user1)
    throws Exception
  {
    List jobtypeList = this.lovtxdao.getJobTypeList(user1.getSuper_user_key());
    tmplForm.setJobtypeList(jobtypeList);
    List orgList = this.lovtxdao.getAllOrganization(user1.getSuper_user_key());
    
    tmplForm.setOrganizationList(orgList);
    if ((tmplForm.getOrgIds() != null) && (tmplForm.getOrgIds().length > 0))
    {
      List<Long> orgIdsList = new ArrayList();
      for (long s : tmplForm.getOrgIds()) {
        orgIdsList.add(Long.valueOf(s));
      }
      tmplForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgIdsList));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List deptList = this.lovtxdao.getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      
      tmplForm.setDepartmentList(deptList);
    }
    else
    {
      List deptList = new ArrayList();
      tmplForm.setDepartmentList(deptList);
    }
    List jobgradeList = this.lovtxdao.getAllJobGradeDetails(user1.getSuper_user_key());
    tmplForm.setJobgradeList(jobgradeList);
    List refferalSchemeListAgency = this.lovtxdao.getAllRefferalSchemeByType(Common.REFERRAL_SCHEME_TYPE_AGENCY);
    List refferalSchemeListEmployee = this.lovtxdao.getAllRefferalSchemeByType(Common.REFERRAL_SCHEME_TYPE_EMPLOYEE);
    tmplForm.setRefferalSchemeList(refferalSchemeListEmployee);
    tmplForm.setAgencyRefferalSchemeList(refferalSchemeListAgency);
    tmplForm.setStatusList(Constant.getRequistionTemplateStatuses(user1));
  }
  
  public List getRequisitionsActiveAndOpenListForSuperUser(long super_user_key)
  {
    return this.jobrequisitiondao.getRequisitionsActiveAndOpenListForSuperUser(super_user_key);
  }
  
  public Map<String, Integer> getCountMapOfRequitionOpenWithStatus(long userId, long super_user_key, String type)
  {
    return this.jobrequisitiondao.getCountMapOfRequitionOpenWithStatus(userId, super_user_key, type);
  }
  
  public boolean isEEOInfoIncluded(long jobReqId)
  {
    return this.jobrequisitiondao.isEEOInfoIncluded(jobReqId);
  }
  
  public ApplicantDAO getapplicantdao()
  {
    return this.applicantdao;
  }
  
  public void setapplicantdao(ApplicantDAO applicantdao)
  {
    this.applicantdao = applicantdao;
  }
  
  public ApplicantDAO getApplicantdao()
  {
    return this.applicantdao;
  }
  
  public void setApplicantdao(ApplicantDAO applicantdao)
  {
    this.applicantdao = applicantdao;
  }
  
  public JobRequistionDAO getJobrequisitiondao()
  {
    return this.jobrequisitiondao;
  }
  
  public void setJobrequisitiondao(JobRequistionDAO jobrequisitiondao)
  {
    this.jobrequisitiondao = jobrequisitiondao;
  }
  
  public OrganizationDAO getOrganizationdao()
  {
    return this.organizationdao;
  }
  
  public void setOrganizationdao(OrganizationDAO organizationdao)
  {
    this.organizationdao = organizationdao;
  }
  
  public UserDAO getUserdao()
  {
    return this.userdao;
  }
  
  public void setUserdao(UserDAO userdao)
  {
    this.userdao = userdao;
  }
  
  public RefferalDAO getRefferaldao()
  {
    return this.refferaldao;
  }
  
  public void setRefferaldao(RefferalDAO refferaldao)
  {
    this.refferaldao = refferaldao;
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
  
  public VariablesTXDAO getVariabletxdao()
  {
    return this.variabletxdao;
  }
  
  public void setVariabletxdao(VariablesTXDAO variabletxdao)
  {
    this.variabletxdao = variabletxdao;
  }
}
