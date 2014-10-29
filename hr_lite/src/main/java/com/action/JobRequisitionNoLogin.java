package com.action;

import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.RequistionAttachments;
import com.bean.User;
import com.bean.UserRegData;
import com.bean.criteria.RequisitionSearchCriteriaMultiple;
import com.bean.criteria.RequistionSearchUtil;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.bo.UserBO;
import com.common.Common;
import com.form.JobRequisitionForm;
import com.resources.Constant;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JobRequisitionNoLogin
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(JobRequisitionNoLogin.class);
  
  public ActionForward deptlistmultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    
    List<Long> orgIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(orgId, ",");
      while (token.hasMoreTokens()) {
        orgIdsList.add(new Long(token.nextToken()));
      }
    }
    jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgIdsList));
    return mapping.findForward("deptlistmultiple");
  }
  
  public ActionForward jobsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobsearch method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      String l_code = request.getParameter("l_code");
      String subdomain = request.getParameter("subdomain");
      String wsint = request.getParameter("wsint");
      
      logger.info("subdomain" + subdomain);
      UserRegData userreg = BOFactory.getUserBO().getUserRegDataBySubdomain(subdomain);
      logger.info("userreg" + userreg);
      String ensuperUserKey = EncryptDecrypt.encrypt(String.valueOf(userreg.getUserRegId()));
      String superUserKey = String.valueOf(userreg.getUserRegId());
      request.getSession().setAttribute("superUserKey", ensuperUserKey);
      logger.info("ensuperUserKey" + ensuperUserKey);
      


      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      logger.info("Inside jobsearch method" + superUserKey);
      if (user1 == null)
      {
        Locale locale = new Locale();
        locale.setLocaleCode(l_code);
        user1 = new User();
        user1.setLocale(locale);
      }
      BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, new Long(superUserKey).longValue(), user1);
      RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
      jbForm.setSerachCriteria(serachCriteria);
      request.setAttribute("searchpagedisplay", "yes");
      request.setAttribute("l_code", l_code);
      request.setAttribute("ensuperUserKey", ensuperUserKey);
      request.setAttribute("search_type", "simple");
      request.getSession().setAttribute("orgname", userreg.getOrgName());
      request.getSession().setAttribute("subdomain", subdomain);
      if ((!StringUtils.isNullOrEmpty(wsint)) && (wsint.equals("yes"))) {
        request.getSession().setAttribute("wsint", "yes");
      } else {
        request.getSession().setAttribute("wsint", "no");
      }
      return mapping.findForward("jobsearch");
    }
    catch (Exception e)
    {
      logger.info("Exception on jobsearch", e);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward jobsearchsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobsearchsubmit method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    
    String l_code = request.getParameter("l_code");
    String search_type = request.getParameter("search_type");
    String searchposteddate = request.getParameter("searchposteddate");
    String cri = request.getParameter("cri");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    request.getSession().setAttribute("superUserKey", ensuperUserKey);
    


    jbForm.setAppliedcri(cri);
    jbForm.setSearchposteddate(searchposteddate);
    jbForm.setJobTitle(jbForm.getJobTitle());
    
    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    

    RequistionSearchUtil.setRequisitionSearchCriteriaMultiple(jbForm, serachCriteria);
    serachCriteria.setAppliedCri(cri);
    serachCriteria.setSearchposteddate(searchposteddate);
    serachCriteria.setJobTitle(jbForm.getJobTitle());
    if ((!StringUtils.isNullOrEmpty(search_type)) && (search_type.equals("simple"))) {
      serachCriteria.setKeyword(jbForm.getKeyword());
    } else {
      serachCriteria.setKeyword(jbForm.getKeyword1());
    }
    serachCriteria.setSearchType(search_type);
    if (user1 == null)
    {
      Locale locale = new Locale();
      locale.setLocaleCode(l_code);
      user1 = new User();
      user1.setLocale(locale);
    }
    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, new Long(superUserKey).longValue(), user1);
    request.setAttribute("searchpagedisplay", "yes");
    request.setAttribute("l_code", l_code);
    request.setAttribute("search_type", search_type);
    
    return mapping.findForward("jobsearch");
  }
  
  public ActionForward jobSearchListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    try
    {
      logger.info("Inside jobSearchListpage method");
      int results = -1;
      int startIndex = 0;
      String sort = "username";
      String dir = "asc";
      


      String results_str = request.getParameter("results");
      String startIndex_str = request.getParameter("startIndex");
      String sort_str = request.getParameter("sort");
      String dir_str = request.getParameter("dir");
      String search_type = request.getParameter("search_type");
      
      String ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
      String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
      logger.info("superUserKey" + superUserKey);
      if (StringUtils.isNullOrEmpty(ensuperUserKey))
      {
        ensuperUserKey = request.getParameter("ensuperUserKey");
        superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
        request.getSession().setAttribute("superUserKey", ensuperUserKey);
      }
      RequisitionSearchCriteriaMultiple searchCriteria = new RequisitionSearchCriteriaMultiple();
      RequistionSearchUtil.setCriteriaFromRequest(request, searchCriteria);
      searchCriteria.setSearchType(search_type);
      if ((startIndex_str != null) && (startIndex_str.length() > 0)) {
        startIndex = Integer.parseInt(startIndex_str);
      }
      if ((sort_str != null) && (sort_str.length() > 0)) {
        sort = sort_str;
      }
      if ((dir_str != null) && (dir_str.length() > 0)) {
        dir = dir_str;
      }
      if ((results_str != null) && (results_str.length() > 0)) {
        results = Integer.parseInt(results_str);
      }
      List dataList = new ArrayList();
      int totalSize = 0;
      
      Map m = new HashMap();
      if ((!StringUtils.isNullOrEmpty(search_type)) && (search_type.equals("simple"))) {
        m = BOFactory.getJobRequistionBO().searchJobRequisitionsExternalUserSimple(new Long(superUserKey).longValue(), searchCriteria, results, startIndex, dir_str, sort_str);
      } else {
        m = BOFactory.getJobRequistionBO().searchJobRequisitionsExternalUser(new Long(superUserKey).longValue(), searchCriteria, results, startIndex, dir_str, sort_str);
      }
      dataList = (List)m.get(Common.REQUISTION_LIST);
      totalSize = ((Integer)m.get(Common.REQUISTION_COUNT)).intValue();
      





      String[] fields = { "jobreqId", "uuid", "jobTitle", "organizationValue", "departmentValue", "locationValue", "projectcodeValue", "jobtypeValue", "workshiftValue", "hiringMgrValue", "templateName", "state", "templateId", "publishedDate", "targetfinishdate", "primarySkill", "categoryValue", "jobGradeValue" };
      



      String data = "{\n\"recordsReturned\":" + dataList.size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + totalSize + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTableWithDefaultDateFormat(dataList, fields) + "}";
      







      request.setAttribute("data", data);
    }
    catch (Exception e)
    {
      logger.info("Exception on jobSearchListpage", e);
    }
    return mapping.findForward("jobSearchListpage");
  }
  
  public ActionForward jobdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobdetails method");
    String reqid = request.getParameter("reqid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String backtolisturl = request.getParameter("backtolisturl");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    if (backtolisturl != null) {
      jbForm.setBackurl(backtolisturl);
    }
    jbForm.setJobreqId(new Long(reqid).longValue());
    JobRequisition jb = BOFactory.getJobRequistionBO().jobdetails(jbForm);
    jbForm.fromValue(jb, request);
    String ensuperUserKey = EncryptDecrypt.encrypt(String.valueOf(jb.getSuper_user_key()));
    request.getSession().setAttribute("superUserKey", ensuperUserKey);
    
    return mapping.findForward("jobdetails");
  }
  
  public ActionForward jobdetailsn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobdetailsn method");
    String reqid = request.getParameter("reqid");
    String secureid = request.getParameter("secureid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(reqid).longValue());
    jbForm.setUuid(secureid);
    JobRequisition jb = BOFactory.getJobRequistionBO().jobdetailsExternal(jbForm);
    jbForm.fromValue(jb, request);
    
    String ensuperUserKey = EncryptDecrypt.encrypt(String.valueOf(jb.getSuper_user_key()));
    request.getSession().setAttribute("superUserKey", ensuperUserKey);
    request.setAttribute("backbutton", "yes");
    request.setAttribute("jobapply", "yes");
    return mapping.findForward("jobdetailsn");
  }
  
  public ActionForward jobdetailsext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobdetailsext method");
    String reqid = request.getParameter("reqid");
    String secureid = request.getParameter("secureid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(reqid).longValue());
    jbForm.setUuid(secureid);
    JobRequisition jb = BOFactory.getJobRequistionBO().jobdetailsExternal(jbForm);
    jbForm.fromValue(jb, request);
    
    String ensuperUserKey = EncryptDecrypt.encrypt(String.valueOf(jb.getSuper_user_key()));
    request.getSession().setAttribute("superUserKey", ensuperUserKey);
    
    request.setAttribute("jobapply", "yes");
    return mapping.findForward("jobdetailsext");
  }
  
  public ActionForward attachementdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside attachementdetails method");
    String offerattachmentid = request.getParameter("attachmentid");
    
    RequistionAttachments offerattachment = BOFactory.getJobRequistionBO().getAttachmentDetailsByUuid(offerattachmentid);
    
    String filePath = Constant.getValue("ATTACHMENT_PATH");
    filePath = "reqattachment" + File.separator + offerattachment.getUuid() + File.separator + offerattachment.getAttahmentname();
    
    request.setAttribute("filePath", filePath);
    
    return mapping.findForward("attachementdetails");
  }
}
