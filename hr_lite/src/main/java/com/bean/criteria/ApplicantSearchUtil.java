package com.bean.criteria;

import com.bean.SearchApplicant;
import com.bean.User;
import com.form.ApplicantForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;

public class ApplicantSearchUtil
{
  protected static final Logger logger = Logger.getLogger(ApplicantSearchUtil.class);
  
  public static void setApplicantSearchParameter(ApplicantForm applicantform, String fromdate, String todate, User user1, SearchApplicant searchsave)
  {
    if (!StringUtils.isNullOrEmpty(applicantform.getApplicantNo())) {
      try
      {
        searchsave.setApplicantId(new Long(applicantform.getApplicantNo()).longValue());
      }
      catch (Exception e) {}
    }
    searchsave.setApplicantId1(applicantform.getApplicantId1());
    searchsave.setAppliedcri(applicantform.getAppliedcri());
    searchsave.setFromdate(fromdate);
    searchsave.setTodate(todate);
    searchsave.setSaveduserid(user1.getUserId());
    searchsave.setCreatedBy(user1.getCreatedBy());
    searchsave.setCreatedDate(new Date());
    searchsave.setSaveshare("recent");
    searchsave.setType("applicantsearch");
    searchsave.setEmail(applicantform.getEmail());
    searchsave.setPreviousOrganization(applicantform.getPreviousOrganization());
    
    searchsave.setFullName(applicantform.getFullName());
    searchsave.setKeyword(applicantform.getKeywords());
    searchsave.setSourceTypeId(applicantform.getSourceTypeId());
    searchsave.setSourceId(applicantform.getSourceId());
    searchsave.setEmployeecode(applicantform.getEmployeecode());
    searchsave.setReferrerName(applicantform.getReferrerName());
    searchsave.setReferrerEmail(applicantform.getReferrerEmail());
    searchsave.setPassportno(applicantform.getPassportno());
    searchsave.setSsnno(applicantform.getSsnno());
    searchsave.setTaxidno(applicantform.getTaxidno());
    searchsave.setCountryId(applicantform.getCountryId());
    searchsave.setStateId(applicantform.getStateId());
    searchsave.setCity(applicantform.getCity());
    searchsave.setFelony_conviction(applicantform.getFelony_conviction());
    searchsave.setHeighestQualification(applicantform.getHeighestQualification());
    searchsave.setQualifications(applicantform.getQualifications());
    searchsave.setCollege_name(applicantform.getCollege_name());
    searchsave.setEarliest_start_date(applicantform.getEarliest_start_date());
    searchsave.setEarliest_start_date1(applicantform.getEarliest_start_date1());
    if (!StringUtils.isNullOrEmpty(applicantform.getZipcode())) {
      try
      {
        searchsave.setZipCode(new Long(applicantform.getZipcode()).longValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getMilesWithin())) {
      try
      {
        searchsave.setMilesWithin(new Long(applicantform.getMilesWithin()).doubleValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getCollege_GPA())) {
      try
      {
        searchsave.setCollege_GPA(new Double(applicantform.getCollege_GPA()).doubleValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getCollege_GPA1())) {
      try
      {
        searchsave.setCollege_GPA1(new Double(applicantform.getCollege_GPA1()).doubleValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getCurrectctc())) {
      try
      {
        searchsave.setCurrectctc(new Long(applicantform.getCurrectctc()).doubleValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getCurrectctc1())) {
      try
      {
        searchsave.setCurrectctc1(new Long(applicantform.getCurrectctc1()).doubleValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getExpectedctc())) {
      try
      {
        searchsave.setExpectedctc(new Long(applicantform.getExpectedctc()).doubleValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getExpectedctc1())) {
      try
      {
        searchsave.setExpectedctc1(new Long(applicantform.getExpectedctc1()).doubleValue());
      }
      catch (Exception e) {}
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getNoticeperiod())) {
      try
      {
        searchsave.setNoticeperiod(new Long(applicantform.getNoticeperiod()).doubleValue());
      }
      catch (Exception e)
      {
        logger.info("exception in notice period : " + e.getMessage());
      }
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getNoticeperiod1())) {
      try
      {
        searchsave.setNoticeperiod1(new Long(applicantform.getNoticeperiod1()).doubleValue());
      }
      catch (Exception e)
      {
        logger.info("exception in notice period 1: " + e.getMessage());
      }
    }
    if (applicantform.getNoofyearsexp() == 0.0D) {
      searchsave.setNoofyearsexp(2147483647.0D);
    } else {
      searchsave.setNoofyearsexp(applicantform.getNoofyearsexp());
    }
    if (applicantform.getNoofyearsexp1() == 0.0D) {
      searchsave.setNoofyearsexp1(2147483647.0D);
    } else {
      searchsave.setNoofyearsexp1(applicantform.getNoofyearsexp1());
    }
    searchsave.setCurrentdesignation(applicantform.getCurrentdesignation());
    searchsave.setResumeHeader(applicantform.getResumeHeader());
    searchsave.setWork_on_weekends(applicantform.getWork_on_weekends());
    searchsave.setWork_on_evenings(applicantform.getWork_on_evenings());
    searchsave.setWork_on_overtime(applicantform.getWork_on_overtime());
    searchsave.setWant_to_relocate(applicantform.getWant_to_relocate());
    searchsave.setLanguages_spoken(applicantform.getLanguages_spoken());
    searchsave.setPreferedlocation(applicantform.getPreferedlocation());
    
    searchsave.setApplicantNo_criteria(applicantform.getApplicantNo_criteria());
    searchsave.setApplicantName_criteria(applicantform.getApplicantName_criteria());
    searchsave.setDob_criteria(applicantform.getDob_criteria());
    searchsave.setPassport_criteria(applicantform.getPassport_criteria());
    searchsave.setSsn_criteria(applicantform.getSsn_criteria());
    searchsave.setTaxno_criteria(applicantform.getTaxno_criteria());
    searchsave.setEmail_criteria(applicantform.getEmail_criteria());
    searchsave.setCity_criteria(applicantform.getCity_criteria());
    searchsave.setOrg_criteria(applicantform.getOrg_criteria());
    
    searchsave.setCurrectctc_criteria(applicantform.getCurrectctc_criteria());
    searchsave.setExpectedctc_criteria(applicantform.getExpectedctc_criteria());
    searchsave.setNoofyearsexp_criteria(applicantform.getNoofyearsexp_criteria());
    searchsave.setNoticeperiod_criteria(applicantform.getNoticeperiod_criteria());
    searchsave.setCurrentdesignation_criteria(applicantform.getCurrentdesignation_criteria());
    searchsave.setResumeHeader_criteria(applicantform.getResumeHeader_criteria());
    searchsave.setEarliest_start_date_criteria(applicantform.getEarliest_start_date_criteria());
    searchsave.setLanguages_spoken_criteria(applicantform.getLanguages_spoken_criteria());
    searchsave.setPreferedlocation_criteria(applicantform.getPreferedlocation_criteria());
    searchsave.setHeighestQualification_criteria(applicantform.getHeighestQualification_criteria());
    searchsave.setQualifications_criteria(applicantform.getQualifications_criteria());
    searchsave.setCollege_name_criteria(applicantform.getCollege_name_criteria());
    searchsave.setCollege_GPA_criteria(applicantform.getCollege_GPA_criteria());
    
    searchsave.setMockquestionset_criteria(applicantform.getMockquestionset_criteria());
    searchsave.setMockQuestionSetId(applicantform.getMockQuestionSetId());
    if (!StringUtils.isNullOrEmpty(applicantform.getMockquestionsetValue1()))
    {
      String cri1 = applicantform.getMockquestionsetValue1();
      try
      {
        cri1 = cri1.replace("%", "");
        searchsave.setMockquestionsetValue1(new Long(cri1).doubleValue());
      }
      catch (Exception e)
      {
        logger.info("exception in MockquestionsetValue1 : " + e.getMessage());
      }
    }
    if (!StringUtils.isNullOrEmpty(applicantform.getMockquestionsetValue2()))
    {
      String cri2 = applicantform.getMockquestionsetValue2();
      try
      {
        cri2 = cri2.replace("%", "");
        searchsave.setMockquestionsetValue2(new Long(cri2).doubleValue());
      }
      catch (Exception e)
      {
        logger.info("exception in MockquestionsetValue2 : " + e.getMessage());
      }
    }
    long[] orgIds = applicantform.getOrgIds();
    if ((orgIds != null) && (orgIds.length > 0))
    {
      String orgids = "";
      for (int i = 0; i < orgIds.length; i++) {
        orgids = orgids + orgIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(orgids))
      {
        orgids = orgids.substring(0, orgids.length() - 1);
        searchsave.setOrgId(orgids);
      }
    }
    long[] departmentIds = applicantform.getDepartmentIds();
    if ((departmentIds != null) && (departmentIds.length > 0))
    {
      String deptids = "";
      for (int i = 0; i < departmentIds.length; i++) {
        deptids = deptids + departmentIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(deptids))
      {
        deptids = deptids.substring(0, deptids.length() - 1);
        searchsave.setDepartmentId(deptids);
      }
    }
    long[] projectcodeIds = applicantform.getProjectcodeIds();
    if ((projectcodeIds != null) && (projectcodeIds.length > 0))
    {
      String projids = "";
      for (int i = 0; i < projectcodeIds.length; i++) {
        projids = projids + projectcodeIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(projids))
      {
        projids = projids.substring(0, projids.length() - 1);
        searchsave.setProjectcodeId(projids);
      }
    }
    long[] vendorIds = applicantform.getVendorIds();
    if ((vendorIds != null) && (vendorIds.length > 0))
    {
      String temp = "";
      for (int i = 0; i < vendorIds.length; i++) {
        temp = temp + vendorIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(temp))
      {
        temp = temp.substring(0, temp.length() - 1);
        searchsave.setVendorId(temp);
      }
    }
    long[] reqIds = applicantform.getRequitionIds();
    if ((reqIds != null) && (reqIds.length > 0))
    {
      String temp = "";
      for (int i = 0; i < reqIds.length; i++) {
        temp = temp + reqIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(temp))
      {
        temp = temp.substring(0, temp.length() - 1);
        searchsave.setReqId(temp);
      }
    }
    long[] tagIds = applicantform.getTagIds();
    if ((tagIds != null) && (tagIds.length > 0))
    {
      String temp = "";
      for (int i = 0; i < tagIds.length; i++) {
        temp = temp + tagIds[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(temp))
      {
        temp = temp.substring(0, temp.length() - 1);
        searchsave.setTagId(temp);
      }
    }
    String[] interviewstates = applicantform.getInterviewStates();
    if ((interviewstates != null) && (interviewstates.length > 0))
    {
      String temp = "";
      for (int i = 0; i < interviewstates.length; i++) {
        temp = temp + interviewstates[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(temp))
      {
        temp = temp.substring(0, temp.length() - 1);
        searchsave.setInterviewState(temp);
      }
    }
    String[] primaryskills = applicantform.getPrimarySkills();
    if ((primaryskills != null) && (primaryskills.length > 0))
    {
      String temp = "";
      for (int i = 0; i < primaryskills.length; i++) {
        temp = temp + primaryskills[i] + ",";
      }
      if (!StringUtils.isNullOrEmpty(temp))
      {
        temp = temp.substring(0, temp.length() - 1);
        searchsave.setPrimarySkill(temp);
      }
    }
  }
  
  public static void setSearchCriteria(ApplicantSearchCriteria criteria, SearchApplicant searchsave)
  {
    criteria.setApplicantId(searchsave.getApplicantId());
    criteria.setApplicantId1(searchsave.getApplicantId1());
    criteria.setAppliedcri(searchsave.getAppliedcri());
    criteria.setFromdate(searchsave.getFromdate());
    criteria.setTodate(searchsave.getTodate());
    
    criteria.setEmail(searchsave.getEmail());
    criteria.setPrevorg(searchsave.getPreviousOrganization());
    criteria.setFullname(searchsave.getFullName());
    criteria.setKeywords(searchsave.getKeyword());
    
    criteria.setSourceTypeId(searchsave.getSourceTypeId());
    criteria.setSourceId(searchsave.getSourceId());
    criteria.setEmployeecode(searchsave.getEmployeecode());
    criteria.setReferrerName(searchsave.getReferrerName());
    criteria.setReferrerEmail(searchsave.getReferrerEmail());
    criteria.setPassportno(searchsave.getPassportno());
    criteria.setSsnno(searchsave.getSsnno());
    criteria.setTaxidno(searchsave.getTaxidno());
    criteria.setCountryId(searchsave.getCountryId());
    criteria.setStateId(searchsave.getStateId());
    criteria.setCity(searchsave.getCity());
    criteria.setFelony_conviction(searchsave.getFelony_conviction());
    criteria.setGender(searchsave.getGender());
    criteria.setDateofbirth(searchsave.getDateofbirth());
    criteria.setDateofbirth1(searchsave.getDateofbirth1());
    

    criteria.setCurrectctc(searchsave.getCurrectctc());
    criteria.setExpectedctc(searchsave.getExpectedctc());
    criteria.setCurrectctc1(searchsave.getCurrectctc1());
    criteria.setExpectedctc1(searchsave.getExpectedctc1());
    criteria.setNoticeperiod(searchsave.getNoticeperiod());
    criteria.setNoticeperiod1(searchsave.getNoticeperiod1());
    criteria.setNoofyearsexp(searchsave.getNoofyearsexp());
    criteria.setNoofyearsexp1(searchsave.getNoofyearsexp1());
    criteria.setCurrentdesignation(searchsave.getCurrentdesignation());
    criteria.setResumeHeader(searchsave.getResumeHeader());
    criteria.setWork_on_weekends(searchsave.getWork_on_weekends());
    criteria.setWork_on_evenings(searchsave.getWork_on_evenings());
    criteria.setWork_on_overtime(searchsave.getWork_on_overtime());
    criteria.setWant_to_relocate(searchsave.getWant_to_relocate());
    criteria.setLanguages_spoken(searchsave.getLanguages_spoken());
    criteria.setEarliest_start_date(searchsave.getEarliest_start_date());
    criteria.setEarliest_start_date1(searchsave.getEarliest_start_date1());
    criteria.setPreferedlocation(searchsave.getPreferedlocation());
    
    criteria.setHeighestQualification(searchsave.getHeighestQualification());
    criteria.setQualifications(searchsave.getQualifications());
    criteria.setCollege_name(searchsave.getCollege_name());
    criteria.setCollege_GPA(searchsave.getCollege_GPA());
    criteria.setCollege_GPA1(searchsave.getCollege_GPA1());
    
    criteria.setZipCode(searchsave.getZipCode());
    criteria.setMilesWithin(searchsave.getMilesWithin());
    
    criteria.setApplicantNo_criteria(searchsave.getApplicantNo_criteria());
    criteria.setApplicantName_criteria(searchsave.getApplicantName_criteria());
    criteria.setDob_criteria(searchsave.getDob_criteria());
    criteria.setPassport_criteria(searchsave.getPassport_criteria());
    criteria.setSsn_criteria(searchsave.getSsn_criteria());
    criteria.setTaxno_criteria(searchsave.getTaxno_criteria());
    criteria.setEmail_criteria(searchsave.getEmail_criteria());
    criteria.setCity_criteria(searchsave.getCity_criteria());
    criteria.setOrg_criteria(searchsave.getOrg_criteria());
    criteria.setCurrectctc_criteria(searchsave.getCurrectctc_criteria());
    criteria.setExpectedctc_criteria(searchsave.getExpectedctc_criteria());
    criteria.setNoofyearsexp_criteria(searchsave.getNoofyearsexp_criteria());
    criteria.setNoticeperiod_criteria(searchsave.getNoticeperiod_criteria());
    criteria.setCurrentdesignation_criteria(searchsave.getCurrentdesignation_criteria());
    criteria.setResumeHeader_criteria(searchsave.getResumeHeader_criteria());
    criteria.setEarliest_start_date_criteria(searchsave.getEarliest_start_date_criteria());
    criteria.setLanguages_spoken_criteria(searchsave.getLanguages_spoken_criteria());
    criteria.setPreferedlocation_criteria(searchsave.getPreferedlocation_criteria());
    
    criteria.setHeighestQualification_criteria(searchsave.getHeighestQualification_criteria());
    criteria.setQualifications_criteria(searchsave.getQualifications_criteria());
    criteria.setCollege_name_criteria(searchsave.getCollege_name_criteria());
    criteria.setCollege_GPA_criteria(searchsave.getCollege_GPA_criteria());
    
    criteria.setQuestionCriList(searchsave.getQuestionCriList());
    criteria.setCustomFieldCriList(searchsave.getCustomFieldCriList());
    
    criteria.setMockquestionset_criteria(searchsave.getMockquestionset_criteria());
    criteria.setMockQuestionSetId(searchsave.getMockQuestionSetId());
    criteria.setMockquestionsetValue1(searchsave.getMockquestionsetValue1());
    criteria.setMockquestionsetValue2(searchsave.getMockquestionsetValue2());
    
    List<Long> orgIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getOrgId())) && (!searchsave.getOrgId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getOrgId(), ",");
      while (token.hasMoreTokens()) {
        orgIdList.add(new Long(token.nextToken()));
      }
    }
    criteria.setOrgIdList(orgIdList);
    
    List<Long> departmentIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getDepartmentId())) && (!searchsave.getDepartmentId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getDepartmentId(), ",");
      while (token.hasMoreTokens()) {
        departmentIdsList.add(new Long(token.nextToken()));
      }
    }
    criteria.setDepartmentIdsList(departmentIdsList);
    

    List<Long> projectcodeIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getProjectcodeId())) && (!searchsave.getProjectcodeId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getProjectcodeId(), ",");
      while (token.hasMoreTokens()) {
        projectcodeIdsList.add(new Long(token.nextToken()));
      }
    }
    criteria.setProjectIdList(projectcodeIdsList);
    

    List<Long> vendorIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getVendorId())) && (!searchsave.getVendorId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getVendorId(), ",");
      while (token.hasMoreTokens()) {
        vendorIdsList.add(new Long(token.nextToken()));
      }
    }
    criteria.setVendorList(vendorIdsList);
    

    List<Long> requistionList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getReqId())) && (!searchsave.getReqId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getReqId(), ",");
      while (token.hasMoreTokens()) {
        requistionList.add(new Long(token.nextToken()));
      }
    }
    criteria.setRequitionList(requistionList);
    

    List<Long> tagList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getTagId())) && (!searchsave.getTagId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getTagId(), ",");
      while (token.hasMoreTokens()) {
        tagList.add(new Long(token.nextToken()));
      }
    }
    criteria.setTagIdList(tagList);
    

    List<String> interviewstateList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getInterviewState())) && (!searchsave.getInterviewState().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getInterviewState(), ",");
      while (token.hasMoreTokens()) {
        interviewstateList.add(String.valueOf(token.nextToken()));
      }
    }
    criteria.setInterviewStateList(interviewstateList);
    
    List<String> primarySkillList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getPrimarySkill())) && (!searchsave.getPrimarySkill().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getPrimarySkill(), ",");
      while (token.hasMoreTokens()) {
        primarySkillList.add(String.valueOf(token.nextToken()));
      }
    }
    criteria.setPrimarySkillList(primarySkillList);
  }
  
  public static void setFormData(ApplicantForm aform, SearchApplicant searchsave)
  {
    aform.setQuestionCriList(searchsave.getQuestionCriList());
    aform.setApplicantId(searchsave.getApplicantId());
    aform.setApplicantNo(String.valueOf(searchsave.getApplicantId()));
    aform.setApplicantId1(searchsave.getApplicantId1());
    aform.setAppliedcri(searchsave.getAppliedcri());
    aform.setSearchfromdate(searchsave.getFromdate());
    aform.setSearchtodate(searchsave.getTodate());
    
    aform.setEmail(searchsave.getEmail());
    aform.setPreviousOrganization(searchsave.getPreviousOrganization());
    aform.setFullName(searchsave.getFullName());
    aform.setKeywords(searchsave.getKeyword());
    
    aform.setNoofrows(String.valueOf(searchsave.getNoofrows()));
    aform.setSourceTypeId(searchsave.getSourceTypeId());
    aform.setSourceId(searchsave.getSourceId());
    aform.setEmployeecode(searchsave.getEmployeecode());
    aform.setReferrerName(searchsave.getReferrerName());
    aform.setReferrerEmail(searchsave.getReferrerEmail());
    aform.setPassportno(searchsave.getPassportno());
    aform.setSsnno(searchsave.getSsnno());
    aform.setTaxidno(searchsave.getTaxidno());
    aform.setCountryId(searchsave.getCountryId());
    aform.setStateId(searchsave.getStateId());
    aform.setCity(searchsave.getCity());
    aform.setFelony_conviction(searchsave.getFelony_conviction());
    aform.setGender(searchsave.getGender());
    aform.setDateofbirth(searchsave.getDateofbirth());
    aform.setDateofbirth1(searchsave.getDateofbirth1());
    
    aform.setCurrectctc(String.valueOf(searchsave.getCurrectctc()));
    aform.setExpectedctc(String.valueOf(searchsave.getExpectedctc()));
    aform.setCurrectctc1(String.valueOf(searchsave.getCurrectctc1()));
    aform.setExpectedctc1(String.valueOf(searchsave.getExpectedctc1()));
    aform.setNoticeperiod(String.valueOf(searchsave.getNoticeperiod()));
    aform.setNoticeperiod1(String.valueOf(searchsave.getNoticeperiod1()));
    aform.setNoofyearsexp(searchsave.getNoofyearsexp());
    aform.setNoofyearsexp1(searchsave.getNoofyearsexp1());
    aform.setCurrentdesignation(searchsave.getCurrentdesignation());
    aform.setResumeHeader(searchsave.getResumeHeader());
    aform.setWork_on_weekends(searchsave.getWork_on_weekends());
    aform.setWork_on_evenings(searchsave.getWork_on_evenings());
    aform.setWork_on_overtime(searchsave.getWork_on_overtime());
    aform.setWant_to_relocate(searchsave.getWant_to_relocate());
    aform.setLanguages_spoken(searchsave.getLanguages_spoken());
    aform.setEarliest_start_date(searchsave.getEarliest_start_date());
    aform.setEarliest_start_date1(searchsave.getEarliest_start_date1());
    aform.setPreferedlocation(searchsave.getPreferedlocation());
    
    aform.setHeighestQualification(searchsave.getHeighestQualification());
    aform.setQualifications(searchsave.getQualifications());
    aform.setCollege_name(searchsave.getCollege_name());
    aform.setCollege_GPA(String.valueOf(searchsave.getCollege_GPA()));
    aform.setCollege_GPA1(String.valueOf(searchsave.getCollege_GPA1()));
    
    aform.setZipcode(String.valueOf(searchsave.getZipCode()));
    aform.setMilesWithin(String.valueOf(searchsave.getMilesWithin()));
    
    aform.setApplicantNo_criteria(searchsave.getApplicantNo_criteria());
    aform.setApplicantName_criteria(searchsave.getApplicantName_criteria());
    aform.setDob_criteria(searchsave.getDob_criteria());
    aform.setPassport_criteria(searchsave.getPassport_criteria());
    aform.setSsn_criteria(searchsave.getSsn_criteria());
    aform.setTaxno_criteria(searchsave.getTaxno_criteria());
    aform.setEmail_criteria(searchsave.getEmail_criteria());
    aform.setCity_criteria(searchsave.getCity_criteria());
    aform.setOrg_criteria(searchsave.getOrg_criteria());
    
    aform.setCurrectctc_criteria(searchsave.getCurrectctc_criteria());
    aform.setExpectedctc_criteria(searchsave.getExpectedctc_criteria());
    aform.setNoofyearsexp_criteria(searchsave.getNoofyearsexp_criteria());
    aform.setNoticeperiod_criteria(searchsave.getNoticeperiod_criteria());
    aform.setCurrentdesignation_criteria(searchsave.getCurrentdesignation_criteria());
    aform.setResumeHeader_criteria(searchsave.getResumeHeader_criteria());
    aform.setEarliest_start_date_criteria(searchsave.getEarliest_start_date_criteria());
    aform.setLanguages_spoken_criteria(searchsave.getLanguages_spoken_criteria());
    aform.setPreferedlocation_criteria(searchsave.getPreferedlocation_criteria());
    
    aform.setHeighestQualification_criteria(searchsave.getHeighestQualification_criteria());
    aform.setQualifications_criteria(searchsave.getQualifications_criteria());
    aform.setCollege_name_criteria(searchsave.getCollege_name_criteria());
    aform.setCollege_GPA_criteria(searchsave.getCollege_GPA_criteria());
    
    aform.setMockquestionset_criteria(searchsave.getMockquestionset_criteria());
    aform.setMockQuestionSetId(searchsave.getMockQuestionSetId());
    aform.setMockquestionsetValue1(String.valueOf(searchsave.getMockquestionsetValue1()));
    aform.setMockquestionsetValue2(String.valueOf(searchsave.getMockquestionsetValue2()));
    
    List<Long> orgIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getOrgId())) && (!searchsave.getOrgId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getOrgId(), ",");
      while (token.hasMoreTokens()) {
        orgIdList.add(new Long(token.nextToken()));
      }
    }
    long[] oarray = new long[orgIdList.size()];
    for (int i = 0; i < orgIdList.size(); i++) {
      oarray[i] = ((Long)orgIdList.get(i)).longValue();
    }
    aform.setOrgIds(oarray);
    
    List<Long> departmentIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getDepartmentId())) && (!searchsave.getDepartmentId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getDepartmentId(), ",");
      while (token.hasMoreTokens()) {
        departmentIdsList.add(new Long(token.nextToken()));
      }
    }
    long[] darray = new long[departmentIdsList.size()];
    for (int i = 0; i < departmentIdsList.size(); i++) {
      darray[i] = ((Long)departmentIdsList.get(i)).longValue();
    }
    aform.setDepartmentIds(darray);
    
    List<Long> projectcodeIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getProjectcodeId())) && (!searchsave.getProjectcodeId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getProjectcodeId(), ",");
      while (token.hasMoreTokens()) {
        projectcodeIdsList.add(new Long(token.nextToken()));
      }
    }
    long[] parray = new long[projectcodeIdsList.size()];
    for (int i = 0; i < projectcodeIdsList.size(); i++) {
      parray[i] = ((Long)projectcodeIdsList.get(i)).longValue();
    }
    aform.setProjectcodeIds(parray);
    

    List<Long> vendorIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getVendorId())) && (!searchsave.getVendorId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getVendorId(), ",");
      while (token.hasMoreTokens()) {
        vendorIdsList.add(new Long(token.nextToken()));
      }
    }
    long[] varray = new long[vendorIdsList.size()];
    for (int i = 0; i < vendorIdsList.size(); i++) {
      varray[i] = ((Long)vendorIdsList.get(i)).longValue();
    }
    aform.setVendorIds(varray);
    
    List<Long> requistionList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getReqId())) && (!searchsave.getReqId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getReqId(), ",");
      while (token.hasMoreTokens()) {
        requistionList.add(new Long(token.nextToken()));
      }
    }
    long[] rarray = new long[requistionList.size()];
    for (int i = 0; i < requistionList.size(); i++) {
      rarray[i] = ((Long)requistionList.get(i)).longValue();
    }
    aform.setRequitionIds(rarray);
    
    List<Long> tagList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getTagId())) && (!searchsave.getTagId().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getTagId(), ",");
      while (token.hasMoreTokens()) {
        tagList.add(new Long(token.nextToken()));
      }
    }
    long[] tarray = new long[tagList.size()];
    for (int i = 0; i < tagList.size(); i++) {
      tarray[i] = ((Long)tagList.get(i)).longValue();
    }
    aform.setTagIds(tarray);
    
    List<String> interviewstateList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getInterviewState())) && (!searchsave.getInterviewState().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getInterviewState(), ",");
      while (token.hasMoreTokens()) {
        interviewstateList.add(String.valueOf(token.nextToken()));
      }
    }
    String[] iarray = (String[])interviewstateList.toArray(new String[interviewstateList.size()]);
    aform.setInterviewStates(iarray);
    

    List<String> primarySkillList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(searchsave.getPrimarySkill())) && (!searchsave.getPrimarySkill().equals("null")))
    {
      StringTokenizer token = new StringTokenizer(searchsave.getPrimarySkill(), ",");
      while (token.hasMoreTokens()) {
        primarySkillList.add(String.valueOf(token.nextToken()));
      }
    }
    String[] primariskillsrray = (String[])primarySkillList.toArray(new String[primarySkillList.size()]);
    aform.setPrimarySkills(primariskillsrray);
  }
}
