package com.bo;

import com.bean.AgencyRedemption;
import com.bean.Department;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.ReferralAgencyApplicants;
import com.bean.ReferralRedemption;
import com.bean.RefferalBudgetCode;
import com.bean.RefferalRedemptionRule;
import com.bean.RefferalScheme;
import com.bean.RefferalSchemeType;
import com.bean.User;
import com.dao.ApplicantTXDAO;
import com.dao.JobRequistionTXDAO;
import com.dao.ReferralTXDAO;
import com.dao.UserTXDAO;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import org.apache.log4j.Logger;

public class ReferralTXBO
{
  ReferralTXDAO referraltxdao;
  ApplicantTXDAO applicanttxdao;
  JobRequistionTXDAO jobreqtxdao;
  UserTXDAO usertxdao;
  protected static final Logger logger = Logger.getLogger(ReferralTXBO.class);
  
  public void doReferralRedemption(ReferralAgencyApplicants refagapp)
  {
    try
    {
      logger.info("start doReferralRedemption - applicant id:" + refagapp.getApplicantId());
      JobApplicant applicant = this.applicanttxdao.getJobApplicant(refagapp.getApplicantId());
      JobRequisition jobreq = this.jobreqtxdao.getJobRequision(String.valueOf(refagapp.getJobReqId()));
      if ((jobreq.getEmployeeReferralSchemeList() != null) && (jobreq.getEmployeeReferralSchemeList().size() > 0))
      {
        Iterator<RefferalScheme> itr = jobreq.getEmployeeReferralSchemeList().iterator();
        while (itr.hasNext())
        {
          RefferalScheme referralScheme = (RefferalScheme)itr.next();
          


          ReferralRedemption referralRedemption = new ReferralRedemption();
          
          referralRedemption.applicantId = applicant.getApplicantId();
          referralRedemption.setSuper_user_key(jobreq.getSuper_user_key());
          referralRedemption.setReqName(jobreq.getJobreqName());
          referralRedemption.setApplicant_number(applicant.getApplicant_number());
          referralRedemption.setRequisition_number(jobreq.getRequisition_number());
          referralRedemption.applicantName = applicant.getFullName();
          referralRedemption.jobReqId = jobreq.getJobreqId();
          referralRedemption.orgId = jobreq.getOrganization().getOrgId();
          if (jobreq.getDepartment() != null) {
            referralRedemption.departmentId = jobreq.getDepartment().getDepartmentId();
          }
          referralRedemption.JobTitle = jobreq.getJobTitle();
          
          referralRedemption.refferalSchemeId = referralScheme.getRefferalScheme_Id();
          referralRedemption.refferalSchemeName = referralScheme.getRefferalScheme_Name();
          referralRedemption.refferalSchemeAmount = referralScheme.getRefferalScheme_Amount();
          referralRedemption.uom = referralScheme.getUom();
          if (referralScheme.getRefferalBudgetCode() != null)
          {
            referralRedemption.refBudgetId = referralScheme.getRefferalBudgetCode().getRef_budgetId();
            referralRedemption.refBudgetCode = referralScheme.getRefferalBudgetCode().getRef_budgetCode();
          }
          if (referralScheme.getRefferalSchemeType() != null)
          {
            referralRedemption.refferalSchemeTypeId = referralScheme.getRefferalSchemeType().getRefferalSchemeTypeId();
            referralRedemption.refferalSchemeTypeName = referralScheme.getRefferalSchemeType().getRefferalSchemeName();
          }
          if (referralScheme.getRule() != null)
          {
            referralRedemption.ruleId = referralScheme.getRule().getRuleId();
            referralRedemption.ruleName = referralScheme.getRule().getRuleDesc();
          }
          referralRedemption.employeecode = applicant.getEmployeecode();
          referralRedemption.employeename = applicant.getReferrerName();
          referralRedemption.employeeemail = applicant.getReferrerEmail();
          referralRedemption.status = "A";
          referralRedemption.state = "Not Released";
          referralRedemption.applicantstate = refagapp.getState();
          referralRedemption.createdBy = "refredscheduler";
          referralRedemption.createdDate = new Date();
          referralRedemption.eventdate = refagapp.getEventdate();
          referralRedemption.setUuid(UUID.randomUUID().toString());
          referralRedemption.setIsPaid("N");
          
          this.referraltxdao.saveReferralRedemptin(referralRedemption);
          
          refagapp.setStatus("D");
          refagapp.setUpdatedDate(new Date());
          this.referraltxdao.updateReferralAgencyApplicants(refagapp);
        }
      }
      logger.info("end doReferralRedemption - applicant id:" + refagapp.getApplicantId());
    }
    catch (Exception e)
    {
      logger.info("Error on doReferralRedemption", e);
    }
  }
  
  public void doAgencyRedemption(ReferralAgencyApplicants refagapp)
  {
    try
    {
      logger.info("start doAgencyRedemption - applicant id:" + refagapp.getApplicantId());
      JobApplicant applicant = this.applicanttxdao.getJobApplicant(refagapp.getApplicantId());
      JobRequisition jobreq = this.jobreqtxdao.getJobRequision(String.valueOf(refagapp.getJobReqId()));
      if ((jobreq.getAgencyReferralSchemeList() != null) && (jobreq.getAgencyReferralSchemeList().size() > 0))
      {
        Iterator<RefferalScheme> itr = jobreq.getAgencyReferralSchemeList().iterator();
        while (itr.hasNext())
        {
          RefferalScheme referralScheme = (RefferalScheme)itr.next();
          User agency = this.usertxdao.getUser(applicant.getVendorId());
          



          AgencyRedemption referralRedemption = new AgencyRedemption();
          referralRedemption.applicantId = applicant.getApplicantId();
          referralRedemption.setSuper_user_key(jobreq.getSuper_user_key());
          referralRedemption.setReqName(jobreq.getJobreqName());
          referralRedemption.setApplicant_number(applicant.getApplicant_number());
          referralRedemption.setRequisition_number(jobreq.getRequisition_number());
          referralRedemption.applicantName = applicant.getFullName();
          referralRedemption.jobReqId = jobreq.getJobreqId();
          referralRedemption.orgId = jobreq.getOrganization().getOrgId();
          if (jobreq.getDepartment() != null) {
            referralRedemption.departmentId = jobreq.getDepartment().getDepartmentId();
          }
          referralRedemption.JobTitle = jobreq.getJobTitle();
          


          referralRedemption.refferalSchemeId = referralScheme.getRefferalScheme_Id();
          referralRedemption.refferalSchemeName = referralScheme.getRefferalScheme_Name();
          referralRedemption.refferalSchemeAmount = referralScheme.getRefferalScheme_Amount();
          referralRedemption.uom = referralScheme.getUom();
          if (referralScheme.getRefferalBudgetCode() != null)
          {
            referralRedemption.refBudgetId = referralScheme.getRefferalBudgetCode().getRef_budgetId();
            referralRedemption.refBudgetCode = referralScheme.getRefferalBudgetCode().getRef_budgetCode();
          }
          if (referralScheme.getRefferalSchemeType() != null)
          {
            referralRedemption.refferalSchemeTypeId = referralScheme.getRefferalSchemeType().getRefferalSchemeTypeId();
            referralRedemption.refferalSchemeTypeName = referralScheme.getRefferalSchemeType().getRefferalSchemeName();
          }
          if (referralScheme.getRule() != null)
          {
            referralRedemption.ruleId = referralScheme.getRule().getRuleId();
            referralRedemption.ruleName = referralScheme.getRule().getRuleDesc();
          }
          referralRedemption.agencyId = agency.getUserId();
          referralRedemption.agencyname = agency.getLastName();
          referralRedemption.agencyemail = agency.getEmailId();
          referralRedemption.status = "A";
          referralRedemption.state = "Not Released";
          referralRedemption.applicantstate = refagapp.getState();
          referralRedemption.createdBy = "refredscheduler";
          referralRedemption.createdDate = new Date();
          referralRedemption.eventdate = refagapp.getEventdate();
          referralRedemption.setUuid(UUID.randomUUID().toString());
          referralRedemption.setIsPaid("N");
          
          this.referraltxdao.saveAgencyRedemptin(referralRedemption);
          
          refagapp.setStatus("D");
          refagapp.setUpdatedDate(new Date());
          this.referraltxdao.updateReferralAgencyApplicants(refagapp);
        }
      }
      logger.info("end doAgencyRedemption - applicant id:" + refagapp.getApplicantId());
    }
    catch (Exception e)
    {
      logger.info("Error on doAgencyRedemption", e);
    }
  }
  
  public ReferralTXDAO getReferraltxdao()
  {
    return this.referraltxdao;
  }
  
  public void setReferraltxdao(ReferralTXDAO referraltxdao)
  {
    this.referraltxdao = referraltxdao;
  }
  
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
  
  public UserTXDAO getUsertxdao()
  {
    return this.usertxdao;
  }
  
  public void setUsertxdao(UserTXDAO usertxdao)
  {
    this.usertxdao = usertxdao;
  }
}
