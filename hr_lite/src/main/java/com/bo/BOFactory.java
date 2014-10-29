package com.bo;

import com.common.AppContextUtil;
import com.performance.bo.GoalBO;
import com.performance.bo.KRABO;
import com.test.MockTestBO;
import network.bo.FbUserBO;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class BOFactory
{
  protected static final Logger logger = Logger.getLogger(BOFactory.class);
  private static RefBO refbo = null;
  private static OnBoardingTaskDefiBO onboardingtaskdefbo = null;
  private static ApplicantBO appbo = null;
  private static ApplicantTXBO apptxbo = null;
  private static JobRequistionBO jobbo = null;
  private static TreeBO treebo = null;
  private static OrganizationBO orgbo = null;
  private static LovBO lovbo = null;
  private static AccomplishmentBO accompbo = null;
  private static BudgetCodeBO budgetcodebo = null;
  private static CharBO charbo = null;
  private static DashBoardBO dashboardbo = null;
  private static DesignationBO designationbo = null;
  private static LocationBO locationbo = null;
  private static OrganizationEmailTemplateGroupBO orgemiltmplbo = null;
  private static ProjectCodeBO projbo = null;
  private static QuestionBO qnsbo = null;
  private static RefferalBudgetCodeBO refbudgetcodebo = null;
  private static RefferalRuleBO refrulebo = null;
  private static RefferalSchemeBO refschemebo = null;
  private static RoleBO rolebo = null;
  private static SalaryBO salarybo = null;
  private static TagsBO tagbo = null;
  private static BusinessFilterBO businessfilterbo = null;
  private static MockTestBO mocktestbo = null;
  private static BusinessRuleBO businessRuleBO = null;
  private static LovTXBO lovtxbo = null;
  private static VariableBO variablebo = null;
  private static JobRequistionTXBO jobreqtxbo = null;
  private static OnBoardingTXBO ontxbo = null;
  private static EmailCampaignBO emailCampaignbo = null;
  private static ReferralTXBO reftxbo = null;
  private static UserBO userbo = null;
  private static KRABO krabo = null;
  private static GoalBO goalbo = null;
  private static TaskBO taskbo = null;
  private static FbUserBO fbuserbo = null;
  
  public static FbUserBO getFbUserBO()
  {
    if (fbuserbo != null) {
      return fbuserbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    fbuserbo = (FbUserBO)appContext.getBean("fbuserboProxy");
    
    return fbuserbo;
  }
  
  public static TaskBO getTaskBO()
  {
    if (taskbo != null) {
      return taskbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    taskbo = (TaskBO)appContext.getBean("taskboProxy");
    
    return taskbo;
  }
  
  public static GoalBO getGoalBO()
  {
    if (goalbo != null) {
      return goalbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    goalbo = (GoalBO)appContext.getBean("goalboProxy");
    
    return goalbo;
  }
  
  public static KRABO getKRABO()
  {
    if (krabo != null) {
      return krabo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    krabo = (KRABO)appContext.getBean("kraboProxy");
    
    return krabo;
  }
  
  public static UserBO getUserBO()
  {
    if (userbo != null) {
      return userbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    userbo = (UserBO)appContext.getBean("userboProxy");
    
    return userbo;
  }
  
  public static ReferralTXBO getReferralTXBO()
  {
    if (reftxbo != null) {
      return reftxbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    reftxbo = (ReferralTXBO)appContext.getBean("referralTxBoProxy");
    
    return reftxbo;
  }
  
  public static EmailCampaignBO getEmailCampaignBO()
  {
    if (emailCampaignbo != null) {
      return emailCampaignbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    emailCampaignbo = (EmailCampaignBO)appContext.getBean("emailCampaignboProxy");
    
    return emailCampaignbo;
  }
  
  public static JobRequistionTXBO getJobRequistionTXBO()
  {
    if (jobreqtxbo != null) {
      return jobreqtxbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    jobreqtxbo = (JobRequistionTXBO)appContext.getBean("jobrequistiontxBoProxy");
    
    return jobreqtxbo;
  }
  
  public static OnBoardingTXBO getOnBoardingTXBO()
  {
    if (ontxbo != null) {
      return ontxbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    ontxbo = (OnBoardingTXBO)appContext.getBean("onboardingtxboProxy");
    
    return ontxbo;
  }
  
  public static MockTestBO getMockTestBO()
  {
    if (mocktestbo != null) {
      return mocktestbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    mocktestbo = (MockTestBO)appContext.getBean("mocktestboProxy");
    
    return mocktestbo;
  }
  
  public static VariableBO getVariableBO()
  {
    if (variablebo != null) {
      return variablebo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    variablebo = (VariableBO)appContext.getBean("variableboProxy");
    
    return variablebo;
  }
  
  public static LovTXBO getLovTXBO()
  {
    if (lovtxbo != null) {
      return lovtxbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    lovtxbo = (LovTXBO)appContext.getBean("lovtxBoProxy");
    
    return lovtxbo;
  }
  
  public static RefBO getRefBO()
  {
    if (refbo != null) {
      return refbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    refbo = (RefBO)appContext.getBean("agencyredemptionboProxy");
    
    return refbo;
  }
  
  public static OnBoardingTaskDefiBO getOnBoardingTaskDefiBO()
  {
    if (onboardingtaskdefbo != null) {
      return onboardingtaskdefbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    onboardingtaskdefbo = (OnBoardingTaskDefiBO)appContext.getBean("onboardingtaskdefboProxy");
    
    return onboardingtaskdefbo;
  }
  
  public static ApplicantBO getApplicantBO()
  {
    if (appbo != null) {
      return appbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    appbo = (ApplicantBO)appContext.getBean("applicantboProxy");
    
    return appbo;
  }
  
  public static BusinessRuleBO getBusinessRuleBO()
  {
    if (businessRuleBO != null) {
      return businessRuleBO;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    businessRuleBO = (BusinessRuleBO)appContext.getBean("businessruleboProxy");
    

    return businessRuleBO;
  }
  
  public static ApplicantTXBO getApplicantTXBO()
  {
    if (apptxbo != null) {
      return apptxbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    apptxbo = (ApplicantTXBO)appContext.getBean("apptxBoProxy");
    
    return apptxbo;
  }
  
  public static JobRequistionBO getJobRequistionBO()
  {
    if (jobbo != null) {
      return jobbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    jobbo = (JobRequistionBO)appContext.getBean("jobrequistionboProxy");
    
    return jobbo;
  }
  
  public static TreeBO getTreeBO()
  {
    if (treebo != null) {
      return treebo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    treebo = (TreeBO)appContext.getBean("treeboProxy");
    

    return treebo;
  }
  
  public static OrganizationBO getOrganizationBO()
  {
    if (orgbo != null) {
      return orgbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    orgbo = (OrganizationBO)appContext.getBean("organizationboProxy");
    

    return orgbo;
  }
  
  public static LovBO getLovBO()
  {
    if (lovbo != null) {
      return lovbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    lovbo = (LovBO)appContext.getBean("lovbontProxy");
    

    return lovbo;
  }
  
  public static AccomplishmentBO getAccomplishmentBO()
  {
    if (accompbo != null) {
      return accompbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    accompbo = (AccomplishmentBO)appContext.getBean("accomplishmentboProxy");
    

    return accompbo;
  }
  
  public static BudgetCodeBO getBudgetCodeBO()
  {
    if (budgetcodebo != null) {
      return budgetcodebo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    budgetcodebo = (BudgetCodeBO)appContext.getBean("budgetCodeBOProxy");
    

    return budgetcodebo;
  }
  
  public static CharBO getCharBO()
  {
    if (charbo != null) {
      return charbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    charbo = (CharBO)appContext.getBean("charboProxy");
    

    return charbo;
  }
  
  public static DashBoardBO getDashBoardBO()
  {
    if (dashboardbo != null) {
      return dashboardbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    dashboardbo = (DashBoardBO)appContext.getBean("dashboardboProxy");
    

    return dashboardbo;
  }
  
  public static DesignationBO getDesignationBO()
  {
    if (designationbo != null) {
      return designationbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    designationbo = (DesignationBO)appContext.getBean("designationboProxy");
    

    return designationbo;
  }
  
  public static LocationBO getLocationBO()
  {
    if (locationbo != null) {
      return locationbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    locationbo = (LocationBO)appContext.getBean("locationboProxy");
    

    return locationbo;
  }
  
  public static OrganizationEmailTemplateGroupBO getOrganizationEmailTemplateGroupBO()
  {
    if (orgemiltmplbo != null) {
      return orgemiltmplbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    orgemiltmplbo = (OrganizationEmailTemplateGroupBO)appContext.getBean("orgemailtmplboProxy");
    

    return orgemiltmplbo;
  }
  
  public static ProjectCodeBO getProjectCodeBO()
  {
    if (projbo != null) {
      return projbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    projbo = (ProjectCodeBO)appContext.getBean("projectcodeboProxy");
    

    return projbo;
  }
  
  public static QuestionBO getQuestionBO()
  {
    if (qnsbo != null) {
      return qnsbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    qnsbo = (QuestionBO)appContext.getBean("questionboProxy");
    

    return qnsbo;
  }
  
  public static RefferalBudgetCodeBO getRefferalBudgetCodeBO()
  {
    if (refbudgetcodebo != null) {
      return refbudgetcodebo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    refbudgetcodebo = (RefferalBudgetCodeBO)appContext.getBean("refbudgetcodeboProxy");
    

    return refbudgetcodebo;
  }
  
  public static RefferalRuleBO getRefferalRuleBO()
  {
    if (refrulebo != null) {
      return refrulebo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    refrulebo = (RefferalRuleBO)appContext.getBean("refruleboProxy");
    

    return refrulebo;
  }
  
  public static RefferalSchemeBO getRefferalSchemeBO()
  {
    if (refschemebo != null) {
      return refschemebo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    refschemebo = (RefferalSchemeBO)appContext.getBean("refschemeboProxy");
    

    return refschemebo;
  }
  
  public static RoleBO getRoleBO()
  {
    if (rolebo != null) {
      return rolebo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    rolebo = (RoleBO)appContext.getBean("roleboProxy");
    

    return rolebo;
  }
  
  public static SalaryBO getSalaryBO()
  {
    if (salarybo != null) {
      return salarybo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    salarybo = (SalaryBO)appContext.getBean("salaryboProxy");
    

    return salarybo;
  }
  
  public static TagsBO getTagsBO()
  {
    if (tagbo != null) {
      return tagbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    tagbo = (TagsBO)appContext.getBean("tagsboProxy");
    

    return tagbo;
  }
  
  public static BusinessFilterBO getBusinessFilterBO()
  {
    if (businessfilterbo != null) {
      return businessfilterbo;
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    businessfilterbo = (BusinessFilterBO)appContext.getBean("businessfilterboProxy");
    

    return businessfilterbo;
  }
}
