package com.bean.criteria;

import com.form.JobRequisitionForm;
import com.form.JobRequisitionTemplateForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RequistionSearchUtil
{
  protected static final Logger logger = Logger.getLogger(RequistionSearchUtil.class);
  
  public static void setRequisitionSearchCriteriaMultiple(JobRequisitionForm jbForm, RequisitionSearchCriteriaMultiple serachCriteria)
  {
    logger.info("inside setRequisitionSearchCriteriaMultiple");
    logger.info("jbForm.getJobreqId()" + jbForm.getJobreqId());
    serachCriteria.setJobreqId(jbForm.getJobreqId());
    serachCriteria.setRequisition_number(jbForm.getRequisition_number());
    serachCriteria.setJobreqName(jbForm.getJobreqName());
    serachCriteria.setJobreqcode(jbForm.getJobreqcode());
    serachCriteria.setKeyword(jbForm.getKeyword());
    serachCriteria.setMinyearsofExpRequired(jbForm.getMaxyearsofExpRequired());
    
    serachCriteria.setIsinternal(jbForm.getIsinternal());
    serachCriteria.setIsnewposition(jbForm.getIsnewposition());
    serachCriteria.setIspublishredtoemployee(jbForm.getIspublishredtoemployee());
    serachCriteria.setIspublishredtoexternal(jbForm.getIspublishredtoexternal());
    
    long[] departmentIds = jbForm.getDepartmentIds();
    if ((departmentIds != null) && (departmentIds.length > 0))
    {
      String deptids = "";
      for (int i = 0; i < departmentIds.length; i++) {
        deptids = deptids + departmentIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(deptids))
      {
        deptids = deptids.substring(0, deptids.length() - 1);
        serachCriteria.setDepartmentIds(deptids);
      }
    }
    long[] orgIds = jbForm.getOrgIds();
    if ((orgIds != null) && (orgIds.length > 0))
    {
      String orgids = "";
      for (int i = 0; i < orgIds.length; i++) {
        orgids = orgids + orgIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(orgids))
      {
        orgids = orgids.substring(0, orgids.length() - 1);
        serachCriteria.setOrgId(orgids);
      }
    }
    String[] statuscri = jbForm.getStatuses();
    if ((statuscri != null) && (statuscri.length > 0))
    {
      String statuscris = "";
      for (int i = 0; i < statuscri.length; i++) {
        statuscris = statuscris + statuscri[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(statuscris))
      {
        statuscris = statuscris.substring(0, statuscris.length() - 1);
        serachCriteria.setStatus(statuscris);
      }
    }
    String[] statecri = jbForm.getStates();
    if ((statecri != null) && (statecri.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < statecri.length; i++) {
        tmp = tmp + statecri[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setState(tmp);
      }
    }
    long[] jobtypeids = jbForm.getJobtypeIds();
    if ((jobtypeids != null) && (jobtypeids.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < jobtypeids.length; i++) {
        tmp = tmp + jobtypeids[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setJobtypeId(tmp);
      }
    }
    long[] jobgradeids = jbForm.getJobgradeIds();
    if ((jobgradeids != null) && (jobgradeids.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < jobgradeids.length; i++) {
        tmp = tmp + jobgradeids[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setJobgradeId(tmp);
      }
    }
    long[] budgetcodeids = jbForm.getBudgetcodeIds();
    if ((budgetcodeids != null) && (budgetcodeids.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < budgetcodeids.length; i++) {
        tmp = tmp + budgetcodeids[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setBudgetcodeId(tmp);
      }
    }
    long[] locationids = jbForm.getLocationIds();
    if ((locationids != null) && (locationids.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < locationids.length; i++) {
        tmp = tmp + locationids[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setLocationId(tmp);
      }
    }
    long[] categoryIds = jbForm.getCategoryIds();
    if ((categoryIds != null) && (categoryIds.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < categoryIds.length; i++) {
        tmp = tmp + categoryIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setCategoryId(tmp);
      }
    }
    long[] workshiftIds = jbForm.getWorkshiftIds();
    if ((workshiftIds != null) && (workshiftIds.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < workshiftIds.length; i++) {
        tmp = tmp + workshiftIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setWorkshiftId(tmp);
      }
    }
    String[] primarySkils = jbForm.getPrimarySkills();
    if ((primarySkils != null) && (primarySkils.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < primarySkils.length; i++) {
        tmp = tmp + primarySkils[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setPrimarySkill(tmp);
      }
    }
    jbForm.setSerachCriteria(serachCriteria);
  }
  
  public static void setRequisitionTemplateSearchCriteriaMultiple(JobRequisitionTemplateForm jbForm, RequisitionTemplateSearchCriteriaMultiple serachCriteria)
  {
    serachCriteria.setTemplateId(jbForm.getTemplateId());
    serachCriteria.setTemplateName(jbForm.getTemplateName());
    
    serachCriteria.setKeyword(jbForm.getKeyword());
    

    long[] departmentIds = jbForm.getDepartmentIds();
    if ((departmentIds != null) && (departmentIds.length > 0))
    {
      String deptids = "";
      for (int i = 0; i < departmentIds.length; i++) {
        deptids = deptids + departmentIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(deptids))
      {
        deptids = deptids.substring(0, deptids.length() - 1);
        serachCriteria.setDepartmentIds(deptids);
      }
    }
    long[] orgIds = jbForm.getOrgIds();
    if ((orgIds != null) && (orgIds.length > 0))
    {
      String orgids = "";
      for (int i = 0; i < orgIds.length; i++) {
        orgids = orgids + orgIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(orgids))
      {
        orgids = orgids.substring(0, orgids.length() - 1);
        serachCriteria.setOrgId(orgids);
      }
    }
    String[] statuscri = jbForm.getStatuses();
    if ((statuscri != null) && (statuscri.length > 0))
    {
      String statuscris = "";
      for (int i = 0; i < statuscri.length; i++) {
        statuscris = statuscris + statuscri[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(statuscris))
      {
        statuscris = statuscris.substring(0, statuscris.length() - 1);
        serachCriteria.setStatus(statuscris);
      }
    }
    long[] jobtypeids = jbForm.getJobtypeIds();
    if ((jobtypeids != null) && (jobtypeids.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < jobtypeids.length; i++) {
        tmp = tmp + jobtypeids[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setJobtypeId(tmp);
      }
    }
    long[] jobgradeids = jbForm.getJobgradeIds();
    if ((jobgradeids != null) && (jobgradeids.length > 0))
    {
      String tmp = "";
      for (int i = 0; i < jobgradeids.length; i++) {
        tmp = tmp + jobgradeids[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(tmp))
      {
        tmp = tmp.substring(0, tmp.length() - 1);
        serachCriteria.setJobgradeId(tmp);
      }
    }
    jbForm.setSerachCriteria(serachCriteria);
  }
  
  public static void setRequisitionSearchCriteriaMultipleForExport(JobRequisitionForm jbForm, RequisitionSearchCriteriaMultiple serachCriteria)
  {
    serachCriteria.setJobreqId(jbForm.getJobreqId());
    serachCriteria.setJobreqName(jbForm.getJobreqName());
    serachCriteria.setJobreqcode(jbForm.getJobreqcode());
    
    serachCriteria.setIsinternal(jbForm.getIsinternal());
    serachCriteria.setIsnewposition(jbForm.getIsnewposition());
    serachCriteria.setIspublishredtoemployee(jbForm.getIspublishredtoemployee());
    serachCriteria.setIspublishredtoexternal(jbForm.getIspublishredtoexternal());
    
    long[] departmentIds = jbForm.getDepartmentIds();
    List<Long> departmentIdsList = new ArrayList();
    if ((departmentIds != null) && (departmentIds.length > 0)) {
      for (int i = 0; i < departmentIds.length; i++) {
        departmentIdsList.add(Long.valueOf(departmentIds[i]));
      }
    }
    serachCriteria.setDepartmentIdsList(departmentIdsList);
    
    long[] orgIds = jbForm.getOrgIds();
    List<Long> orgIdsList = new ArrayList();
    if ((orgIds != null) && (orgIds.length > 0)) {
      for (int i = 0; i < orgIds.length; i++) {
        orgIdsList.add(Long.valueOf(orgIds[i]));
      }
    }
    serachCriteria.setOrgIdList(orgIdsList);
    
    String[] statuscri = jbForm.getStatuses();
    List<String> statusList = new ArrayList();
    if ((statuscri != null) && (statuscri.length > 0)) {
      for (int i = 0; i < statuscri.length; i++) {
        statusList.add(statuscri[i]);
      }
    }
    serachCriteria.setStatusList(statusList);
    
    String[] statecri = jbForm.getStates();
    List<String> stateList = new ArrayList();
    if ((statecri != null) && (statecri.length > 0)) {
      for (int i = 0; i < statecri.length; i++) {
        stateList.add(statecri[i]);
      }
    }
    serachCriteria.setStateList(stateList);
    

    long[] jobtypeids = jbForm.getJobtypeIds();
    List<Long> jobtypeIdsList = new ArrayList();
    if ((jobtypeids != null) && (jobtypeids.length > 0)) {
      for (int i = 0; i < jobtypeids.length; i++) {
        jobtypeIdsList.add(Long.valueOf(jobtypeids[i]));
      }
    }
    serachCriteria.setJobtypeIdList(jobtypeIdsList);
    
    long[] jobgradeids = jbForm.getJobgradeIds();
    List<Long> jobgradeIdsList = new ArrayList();
    if ((jobgradeids != null) && (jobgradeids.length > 0)) {
      for (int i = 0; i < jobgradeids.length; i++) {
        jobgradeIdsList.add(Long.valueOf(jobgradeids[i]));
      }
    }
    serachCriteria.setJobgradeIdList(jobgradeIdsList);
    
    long[] budgetcodeids = jbForm.getBudgetcodeIds();
    List<Long> budgetcodeList = new ArrayList();
    if ((budgetcodeids != null) && (budgetcodeids.length > 0)) {
      for (int i = 0; i < budgetcodeids.length; i++) {
        budgetcodeList.add(Long.valueOf(budgetcodeids[i]));
      }
    }
    serachCriteria.setBudgetcodeIdList(budgetcodeList);
  }
  
  public static void setCriteriaFromRequest(HttpServletRequest request, RequisitionSearchCriteriaMultiple searchCriteria)
  {
    String jobreqname = request.getParameter("jobreqname");
    String jobreqid = request.getParameter("jobreqid");
    String reqno = request.getParameter("reqno");
    logger.info("reqno" + reqno);
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    String jobgradeId = request.getParameter("jobgradeId");
    String jobtypeId = request.getParameter("jobtypeId");
    String statuscri = request.getParameter("statuscri");
    String statecri = request.getParameter("statecri");
    String jobreqcode = request.getParameter("jobreqcode");
    String searchpagedisplay = request.getParameter("searchpagedisplay");
    String budgetcodeid = request.getParameter("budgetcodeid");
    
    String jobTitle = request.getParameter("jobTitle");
    String postdate = request.getParameter("postdate");
    String locationId = request.getParameter("locationId");
    String workshiftId = request.getParameter("workshiftId");
    String categoryId = request.getParameter("categoryId");
    String primarySkill = request.getParameter("primarySkill");
    String minyearsofExpRequired = request.getParameter("minyearsofExpRequired");
    String cri = request.getParameter("cri");
    String keyword = request.getParameter("keyword");
    
    searchCriteria.setIsinternal(request.getParameter("isinternal"));
    searchCriteria.setIsnewposition(request.getParameter("isnewposition"));
    searchCriteria.setIspublishredtoemployee(request.getParameter("ispublishredtoemployee"));
    searchCriteria.setIspublishredtoexternal(request.getParameter("ispublishredtoexternal"));
    if ((!StringUtils.isNullOrEmpty(jobreqid)) && (!jobreqid.equals("0"))) {
      searchCriteria.setJobreqId(new Long(jobreqid).longValue());
    }
    if ((!StringUtils.isNullOrEmpty(reqno)) && (!reqno.equals("0"))) {
      searchCriteria.setRequisition_number(new Long(reqno).longValue());
    }
    searchCriteria.setJobreqName(jobreqname);
    searchCriteria.setJobreqcode(jobreqcode);
    searchCriteria.setJobTitle(jobTitle);
    searchCriteria.setAppliedCri(cri);
    searchCriteria.setSearchposteddate(postdate);
    searchCriteria.setKeyword(keyword);
    if (!StringUtils.isNullOrEmpty(minyearsofExpRequired)) {
      searchCriteria.setMinyearsofExpRequired(new Integer(minyearsofExpRequired).intValue());
    }
    List<Long> departmentIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(departmentId, ",");
      while (token.hasMoreTokens()) {
        departmentIdsList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setDepartmentIdsList(departmentIdsList);
    


    List<String> statusList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(statuscri)) && (!statuscri.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(statuscri, ",");
      while (token.hasMoreTokens()) {
        statusList.add(token.nextToken());
      }
    }
    searchCriteria.setStatusList(statusList);
    

    List<String> stateList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(statecri)) && (!statecri.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(statecri, ",");
      while (token.hasMoreTokens()) {
        stateList.add(token.nextToken());
      }
    }
    searchCriteria.setStateList(stateList);
    

    List<Long> jobtypeIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(jobtypeId, ",");
      while (token.hasMoreTokens()) {
        jobtypeIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setJobtypeIdList(jobtypeIdList);
    

    List<Long> jobgradeIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(jobgradeId, ",");
      while (token.hasMoreTokens()) {
        jobgradeIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setJobgradeIdList(jobgradeIdList);
    

    List<Long> budgetcodeIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(budgetcodeid)) && (!budgetcodeid.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(budgetcodeid, ",");
      while (token.hasMoreTokens()) {
        budgetcodeIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setBudgetcodeIdList(budgetcodeIdList);
    

    List<Long> orgIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(orgId, ",");
      while (token.hasMoreTokens()) {
        orgIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setOrgIdList(orgIdList);
    

    List<Long> locationIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(locationId, ",");
      while (token.hasMoreTokens()) {
        locationIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setLocationIdList(locationIdList);
    


    List<Long> workshiftIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(workshiftId)) && (!workshiftId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(workshiftId, ",");
      while (token.hasMoreTokens()) {
        workshiftIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setWorkshiftIdList(workshiftIdList);
    


    List<Long> categoryIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(categoryId)) && (!categoryId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(categoryId, ",");
      while (token.hasMoreTokens()) {
        categoryIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setCategoryIdList(categoryIdList);
    

    List<String> primarySkillList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(primarySkill)) && (!primarySkill.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(primarySkill, ",");
      while (token.hasMoreTokens()) {
        primarySkillList.add(token.nextToken());
      }
    }
    searchCriteria.setPrimarySkillList(primarySkillList);
  }
  
  public static void setReqTemplateCriteriaFromRequest(HttpServletRequest request, RequisitionTemplateSearchCriteriaMultiple searchCriteria)
  {
    String templatename = request.getParameter("templatename");
    String templateid = request.getParameter("templateid");
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    String jobgradeId = request.getParameter("jobgradeId");
    String jobtypeId = request.getParameter("jobtypeId");
    String statuscri = request.getParameter("statuscri");
    String searchpagedisplay = request.getParameter("searchpagedisplay");
    String keyword = request.getParameter("keyword");
    if ((!StringUtils.isNullOrEmpty(templateid)) && (!templateid.equals("0"))) {
      searchCriteria.setTemplateId(new Long(templateid).longValue());
    }
    searchCriteria.setTemplateName(templatename);
    searchCriteria.setKeyword(keyword);
    
    List<Long> departmentIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(departmentId, ",");
      while (token.hasMoreTokens()) {
        departmentIdsList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setDepartmentIdsList(departmentIdsList);
    


    List<String> statusList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(statuscri)) && (!statuscri.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(statuscri, ",");
      while (token.hasMoreTokens()) {
        statusList.add(token.nextToken());
      }
    }
    searchCriteria.setStatusList(statusList);
    


    List<Long> jobtypeIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(jobtypeId, ",");
      while (token.hasMoreTokens()) {
        jobtypeIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setJobtypeIdList(jobtypeIdList);
    

    List<Long> jobgradeIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(jobgradeId, ",");
      while (token.hasMoreTokens()) {
        jobgradeIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setJobgradeIdList(jobgradeIdList);
    



    List<Long> orgIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(orgId, ",");
      while (token.hasMoreTokens()) {
        orgIdList.add(new Long(token.nextToken()));
      }
    }
    searchCriteria.setOrgIdList(orgIdList);
  }
}
