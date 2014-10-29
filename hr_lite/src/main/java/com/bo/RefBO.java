package com.bo;

import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.ReferAFriend;
import com.bean.RefferalEmployee;
import com.bean.User;
import com.bean.criteria.AgencyRedumptionSearchCriteria;
import com.bean.criteria.ReferalRedumptionSearchCriteria;
import com.bean.lov.KeyValue;
import com.common.Common;
import com.dao.JobRequistionDAO;
import com.dao.LovTXDAO;
import com.dao.RefferalDAO;
import com.dao.UserDAO;
import com.form.ReferralRedemptionSummaryForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.ConvertBeanUtil;
import com.util.EmailTask;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

public class RefBO
{
  protected static final Logger logger = Logger.getLogger(RefBO.class);
  RefferalDAO refferaldao;
  JobRequistionDAO jobrequisitiondao;
  LovTXDAO lovtxdao;
  
  public static void main(String[] args) {}
  
  public List exportAgencyRedumptionSearch(User user, AgencyRedumptionSearchCriteria agencyRedumptionSearchCriteria)
  {
    logger.info("Inside exportOnBoardApplicantsSearch method");
    return this.refferaldao.exportAgencyRedumptionSearch(user, agencyRedumptionSearchCriteria);
  }
  
  public List exportReferalRedumptionSearch(User user, ReferalRedumptionSearchCriteria referalRedumptionSearchCriteria)
  {
    logger.info("Inside exportOnBoardApplicantsSearch method");
    return this.refferaldao.exportReferalRedumptionSearch(user, referalRedumptionSearchCriteria);
  }
  
  public static void sendReferAFriendEmail(RefferalEmployee emp, ReferAFriend reffriend)
  {
    logger.info("sendReferAFriendEmail start");
    try
    {
      logger.info("email need to send to" + reffriend.getEmailId());
      
      String toemailid = "\"" + reffriend.getName() + "\"" + " " + "<" + reffriend.getEmailId() + ">";
      
      String[] tonew = { toemailid };
      String alltaskcc = Constant.getValue("all.refer.friend.cc");
      List ccList = new ArrayList();
      ccList.add(emp.getEmployeeemail());
      if (!StringUtils.isNullOrEmpty(alltaskcc)) {
        ccList.add(alltaskcc);
      }
      String[] ccnew = new String[ccList.size()];
      ccList.toArray(ccnew);
      
      String from = "\"" + emp.getEmployeename() + "\"" + " " + "<" + emp.getEmployeeemail() + ">";
      
      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.REFER_FRIEND);
      emailtasknew.setEmployeeReferral(emp);
      emailtasknew.setReferafriend(reffriend);
      EmailTaskManager.sendEmail(emailtasknew);
    }
    catch (Exception e) {}
    logger.info("sendReferAFriendEmail end");
  }
  
  public void referalredemptionlistLOVs(ReferralRedemptionSummaryForm summaryform, User user)
  {
    List referalList = this.refferaldao.getAllReferalByTypeTx();
    summaryform.setReferalRedemptionList(referalList);
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    summaryform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    summaryform.setDepartmentList(deptlist);
    String usertype = "Employee";
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      summaryform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListForForm(user.getSuper_user_key());
    
    summaryform.setJobtitleList(jobtitleList);
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    List refferalSchemeList = BOFactory.getLovBO().getAllRefferalSchemes(usertype, user.getSuper_user_key());
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    summaryform.setReferralBudgetCodeList(refferalBudgetCodeList);
    summaryform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    summaryform.setRuleList(ruleList);
    summaryform.setReferralSchemeList(refferalSchemeList);
    summaryform.setReleaseTypeList(Constant.getReleasedTypesList());
    summaryform.setPaidTypeList(Constant.getYesNoList());
  }
  
  public List getActiveJobTitleListForForm()
  {
    List jobtitleList = this.jobrequisitiondao.getRequisitionsActiveList();
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      JobRequisition jreqnew = new JobRequisition();
      jreqnew.setJobreqId(jreq.getJobreqId());
      jreqnew.setJobTitle(jreq.getJobreqcode() + " # " + jreq.getJobTitle());
      newjobtitleList.add(jreqnew);
    }
    return newjobtitleList;
  }
  
  public static RefferalEmployee getRefferalEmployee(String id)
  {
    User user = UserDAO.getUser(new Long(id).longValue());
    RefferalEmployee refemp = new RefferalEmployee();
    refemp = ConvertBeanUtil.convertUserToEmployeeReferral(user, refemp);
    return refemp;
  }
  
  public static List getMybookmarks(String email, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.getMybookmarks(email, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfMybookmarks(String email)
  {
    return RefferalDAO.getCountOfMybookmarks(email);
  }
  
  public static List getMybookmarks(long agencyId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.getMybookmarks(agencyId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfMybookmarks(long agencyId)
  {
    return RefferalDAO.getCountOfMybookmarks(agencyId);
  }
  
  public static List searchfriendsForPagination(RefferalEmployee user1, String friendsName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.searchfriendsForPagination(user1, friendsName, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfsearchfriends(RefferalEmployee user1, String friendsName)
  {
    return RefferalDAO.getCountOfsearchfriends(user1, friendsName);
  }
  
  public static List searchfriendsForPagination(RefferalEmployee user1, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.searchfriendsForPagination(user1, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfsearchfriends(RefferalEmployee user1)
  {
    return RefferalDAO.getCountOfsearchfriends(user1);
  }
  
  public static List searchOwnRefferalRedemptionsForPagination(RefferalEmployee user, String applicantName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.searchOwnRefferalRedemptionsForPagination(user, applicantName, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfsearchOwnRefferalRedemptions(RefferalEmployee user, String applicantName)
  {
    return RefferalDAO.getCountOfsearchOwnRefferalRedemptions(user, applicantName);
  }
  
  public static List searchOwnAgencyRedemptionsForPagination(User agency, String applicantName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.searchOwnAgencyRedemptionsForPagination(agency, applicantName, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfsearchOwnAgencyRedemptions(User agency, String applicantName)
  {
    return RefferalDAO.getCountOfsearchOwnAgencyRedemptions(agency, applicantName);
  }
  
  public static Set<String> getDistinctRedemptionUOMByReferral(RefferalEmployee user, String state)
  {
    return RefferalDAO.getDistinctRedemptionUOMByReferral(user, state);
  }
  
  public static List getRedemptionsByUOMBandState(RefferalEmployee user, String uom, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.getRedemptionsByUOMBandState(user, uom, state, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfRedemptionsByUOMBandState(RefferalEmployee user, String uom, String state)
  {
    return RefferalDAO.getCountOfRedemptionsByUOMBandState(user, uom, state);
  }
  
  public static long getTotalRedemptionsByUOMBandState(RefferalEmployee user, String uom, String state)
  {
    return RefferalDAO.getTotalRedemptionsByUOMBandState(user, uom, state);
  }
  
  public static long getTotalRedemptionsNotPiadByUOMBandState(RefferalEmployee user, String uom, String state)
  {
    return RefferalDAO.getTotalRedemptionsNotPiadByUOMBandState(user, uom, state);
  }
  
  public static Set<String> getDistinctRedemptionUOMByAgency(User user, String state)
  {
    return RefferalDAO.getDistinctRedemptionUOMByAgency(user, state);
  }
  
  public static long getTotalRedemptionsByUOMBandStateForAgency(User user, String uom, String state)
  {
    return RefferalDAO.getTotalRedemptionsByUOMBandStateForAgency(user, uom, state);
  }
  
  public static long getTotalRedemptionsNotPiadByUOMBandStateForAgency(User user, String uom, String state)
  {
    return RefferalDAO.getTotalRedemptionsNotPiadByUOMBandStateForAgency(user, uom, state);
  }
  
  public static List getRedemptionsByUOMBandStateForAgency(User user, String uom, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.getRedemptionsByUOMBandStateForAgency(user, uom, state, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfRedemptionsByUOMBandStateForAgency(User user, String uom, String state)
  {
    return RefferalDAO.getCountOfRedemptionsByUOMBandStateForAgency(user, uom, state);
  }
  
  public static List getAllAgencyRedemptions(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.getAllAgencyRedemptions(pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfAllAgencyRedemptions()
  {
    return RefferalDAO.getCountOfAllAgencyRedemptions();
  }
  
  public static List searchAgencyRedemptions(User user, String agencyId, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid, String orgId, String departmentId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.searchAgencyRedemptions(user, agencyId, applicantNo, applicantName, requitionId, refBudgetId, refferalSchemeId, refferalSchemeTypeId, ruleId, releasedate, cri, uom, state, isPaid, orgId, departmentId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static List searchReferralRedemptions(User user, String employeecode, String employeename, String employeeemail, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.searchReferralRedemptions(user, employeecode, employeename, employeeemail, applicantNo, applicantName, requitionId, refBudgetId, refferalSchemeId, refferalSchemeTypeId, ruleId, releasedate, cri, uom, state, isPaid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfsearchReferralRedemptions(User user, String employeecode, String employeename, String employeeemail, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid)
  {
    return RefferalDAO.getCountOfsearchReferralRedemptions(user, employeecode, employeename, employeeemail, applicantNo, applicantName, requitionId, refBudgetId, refferalSchemeId, refferalSchemeTypeId, ruleId, releasedate, cri, uom, state, isPaid);
  }
  
  public static int getCountOfsearchAgencyRedemptions(User user, String agencyId, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid, String orgId, String departmentId)
  {
    return RefferalDAO.getCountOfsearchAgencyRedemptions(user, agencyId, applicantNo, applicantName, requitionId, refBudgetId, refferalSchemeId, refferalSchemeTypeId, ruleId, releasedate, cri, uom, state, isPaid, orgId, departmentId);
  }
  
  public static List getAllEmployeeReferralRedemptions(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RefferalDAO.getAllEmployeeReferralRedemptions(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfAllEmployeeReferralRedemptions(User user)
  {
    return RefferalDAO.getCountOfAllEmployeeReferralRedemptions(user);
  }
  
  public static List<String> getMyReferralRedumptionAgencyApplicantNames(long agencyId, String query)
  {
    List<String> applicantList = RefferalDAO.getMyReferralRedumptionAgencyApplicantNames(agencyId, query);
    

    return applicantList;
  }
  
  public static int searchRefferalEmpCount()
  {
    return RefferalDAO.searchRefferalEmpCount();
  }
  
  public static List searchRefferalEmpForPagination(int pageSize, int startIndex, String dir, String sort_str)
  {
    return RefferalDAO.searchRefferalEmpForPagination(pageSize, startIndex, dir, sort_str);
  }
  
  public static List searchRefferalEmpForPagination(String employeecode, String employeename, String employeeemail, String orgId, String departmentId, String projectId, String status, String dir, String sort, int pageSize, int startIndex)
  {
    return RefferalDAO.searchRefferalEmpForPagination(employeecode, employeename, employeeemail, orgId, departmentId, projectId, status, dir, sort, pageSize, startIndex);
  }
  
  public static int searchRefferalEmpCount(String employeecode, String employeename, String employeeemail, String orgId, String departmentId, String projectId, String status)
  {
    return RefferalDAO.searchRefferalEmpCount(employeecode, employeename, employeeemail, orgId, departmentId, projectId, status);
  }
  
  public static List<String> getMyReferralRedumptionApplicantNames(RefferalEmployee emp, String query)
  {
    List<String> applicantList = RefferalDAO.getMyReferralRedumptionApplicantNames(emp, query);
    

    return applicantList;
  }
  
  public static List<String> getMyReferfriendNames(RefferalEmployee emp, String query)
  {
    List<String> friendList = RefferalDAO.getMyReferfriendNames(emp, query);
    

    return friendList;
  }
  
  public static Map createJSONGraphForAgencySummary(User agency, String uom, User user)
  {
    logger.info("inside createJSONGraphForAgencySummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"agencysummary\"";
    String seriesdef = "";
    
    Set years = RefferalDAO.getDistinctYearsName(agency.getUserId(), uom);
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      logger.info("Year" + yearstr);
      long totalamount = RefferalDAO.getTotalAgencyRedemptionByYear(agency.getUserId(), uom, year.intValue());
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ agencysummary: \"" + yearstr + "\"";
      

      String intstate = agency.getLastName();
      if (!StringUtils.isNullOrEmpty(intstate))
      {
        intstate = intstate.replace("-", "_");
        intstate = intstate.replace(" ", "_");
      }
      tempstr = tempstr + ", " + intstate + ":" + totalamount;
      if (k == 0)
      {
        fields = fields + ", " + "\"" + intstate + "\"";
        seriesdef = seriesdef + "{ displayName: " + "\"" + intstate + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public static Map createJSONGraphAgencyApplicantSummary(User agency, User user)
  {
    logger.info("inside createJSONGraphAgencyApplicantSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"agencyapplicantsummary\"";
    String seriesdef = "";
    
    Set years = RefferalDAO.getApplicantsAddedDistinctYearsName(agency.getUserId());
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List statewithcountlist = RefferalDAO.getApplicantsStateAndCountByAgencyIdAndYear(agency.getUserId(), year.intValue());
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ agencyapplicantsummary: \"" + yearstr + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public static Map createJSONGraphAgencyPerformance(User agency, User user)
  {
    logger.info("inside createJSONGraphAgencyPerformance");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"agencyperformance\"";
    String seriesdef = "";
    
    Set years = RefferalDAO.getApplicantsAddedDistinctYearsName(agency.getUserId());
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List perflist = RefferalDAO.getAgencyPerformanceByYear(agency.getUserId(), year.intValue());
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ agencyperformance: \"" + yearstr + "\"";
      for (int j = 0; j < perflist.size(); j++)
      {
        KeyValue keyv = (KeyValue)perflist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public static Map createJSONGraphAgencyPerformanceOverall(User agency, User user)
  {
    logger.info("inside createJSONGraphAgencyPerformanceOverall");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"agencyperformanceoverall\"";
    String seriesdef = "";
    
    Set overall = new HashSet();
    overall.add(Constant.getResourceStringValueWithDefault("Overall Performance", user.getLocale()));
    

    int k = 0;
    Iterator itr = overall.iterator();
    while (itr.hasNext())
    {
      String overallperf = (String)itr.next();
      List perflist = RefferalDAO.getAgencyOverallPerformance(agency.getUserId());
      if (!StringUtils.isNullOrEmpty(overallperf)) {
        overallperf = overallperf.replace("\"", "");
      }
      String tempstr = "{ agencyperformanceoverall: \"" + overallperf + "\"";
      for (int j = 0; j < perflist.size(); j++)
      {
        KeyValue keyv = (KeyValue)perflist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
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
