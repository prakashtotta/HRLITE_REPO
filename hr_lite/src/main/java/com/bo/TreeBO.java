package com.bo;

import com.bean.Department;
import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.User;
import com.dao.ApplicantDAO;
import com.dao.JobRequistionDAO;
import com.dao.OrganizationDAO;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sourceforge.jscontrolstags.server.treeview.TreeNode;
import net.sourceforge.jscontrolstags.server.treeview.TreeView;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TreeBO
{
  protected static final Logger logger = Logger.getLogger(TreeBO.class);
  ApplicantDAO applicantdao;
  JobRequistionDAO jobrequisitiondao;
  OrganizationDAO organizationdao;
  
  public static String getInterviewStateGraphForJQPlot(Map<String, Integer> countMap)
  {
    String str = "";
    try
    {
      str = "['Application Submitted'," + getValue(countMap, "Application Submitted") + "]" + "," + "[" + "'" + "In Screening" + "'" + "," + getValue(countMap, "In Screening") + "]" + "," + "[" + "'" + "Cleared-In Screening" + "'" + "," + getValue(countMap, "Cleared-In Screening") + "]" + ",";
      


      int maximumround = new Integer(Constant.getValue("maximum.interview.rounds")).intValue();
      for (int i1 = 1; i1 <= maximumround; i1++)
      {
        String tmpround = "Interview Round-" + i1;
        String clearedtmpround = "Cleared-Interview Round-" + i1;
        str = str + "[" + "'" + tmpround + "'" + "," + getValue(countMap, tmpround) + "]" + ",";
        str = str + "[" + "'" + clearedtmpround + "'" + "," + getValue(countMap, clearedtmpround) + "]" + ",";
      }
      str = str + "[" + "'" + "Offer Initiated-In Approval" + "'" + "," + getValue(countMap, "Offer Initiated-In Approval") + "]" + "," + "[" + "'" + "Offer rejected by approver" + "'" + "," + getValue(countMap, "Offer rejected by approver") + "]" + "," + "[" + "'" + "Ready for Release Offer" + "'" + "," + getValue(countMap, "Ready for Release Offer") + "]" + "," + "[" + "'" + "Offer Released" + "'" + "," + getValue(countMap, "Offer Released") + "]" + "," + "[" + "'" + "Offer In Negotiation" + "'" + "," + getValue(countMap, "Offer In Negotiation") + "]" + "," + "[" + "'" + "Offer Accepted" + "'" + "," + getValue(countMap, "Offer Accepted") + "]" + "," + "[" + "'" + "Offer Canceled" + "'" + "," + getValue(countMap, "Offer Canceled") + "]" + "," + "[" + "'" + "Offer Declined" + "'" + "," + getValue(countMap, "Offer Declined") + "]" + "," + "[" + "'" + "Rejected" + "'" + "," + getValue(countMap, "Rejected") + "]," + "[" + "'" + "OnHold" + "'" + "," + getValue(countMap, "OnHold") + "]" + "," + "[" + "'" + "On Board - Joined" + "'" + "," + getValue(countMap, "On Board - Joined") + "]" + "," + "[" + "'" + "RESIGNED" + "'" + "," + getValue(countMap, "RESIGNED") + "]";
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return str;
  }
  
  private static String getValue(Map<String, Integer> countMap, String state)
  {
    String value = "0";
    if (countMap.get(state) != null)
    {
      Integer inn = (Integer)countMap.get(state);
      value = String.valueOf(inn);
    }
    return value;
  }
  
  public String getReqStatusPieChatData(long userId, long super_user_key, String type)
  {
    String str = "";
    Map<String, Integer> countMap = BOFactory.getJobRequistionBO().getCountMapOfRequitionOpenWithStatus(userId, super_user_key, type);
    Set set = countMap.keySet();
    Iterator itr = set.iterator();
    while (itr.hasNext())
    {
      String key = (String)itr.next();
      Integer intv = (Integer)countMap.get(key);
      String v = String.valueOf(intv);
      str = str + "[" + "'" + key + "'" + "," + v + "]" + ",";
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }
  
  public void setTreeview(HttpServletRequest request, String orgid)
  {
    Organization org = this.organizationdao.getOrganization(orgid);
    

    TreeView dynamicTreeview = new TreeView();
    TreeNode root1 = new TreeNode("orgname", org.getOrgName());
    root1.setImg("base.gif");
    root1.setOnclick("function(node){getrequisitionbyorg('" + org.getOrgId() + "'" + ");}");
    dynamicTreeview.addTreeNode(root1);
    

    List departmentList = org.getDeptlist();
    if ((departmentList != null) && (departmentList.size() > 0)) {
      for (int i = 0; i < departmentList.size(); i++)
      {
        Department dept = (Department)departmentList.get(i);
        
        int total = this.jobrequisitiondao.getCountOfAllJobRequistionsByOrgByDept(String.valueOf(org.getOrgId()), String.valueOf(dept.getDepartmentId()));
        TreeNode child1 = new TreeNode("110dept_" + dept.getDepartmentId() + "_" + org.getOrgId(), dept.getDepartmentName() + "(" + total + ")");
        child1.setImg("page.gif");
        root1.addTreeNode(child1);
        child1.setOpenAjax(Boolean.TRUE);
        child1.setOnclick("function(node){getrequisitionbyorgdept('" + org.getOrgId() + "'" + "," + "'" + dept.getDepartmentId() + "'" + ");}");
      }
    }
    request.setAttribute("dynamicTreeview", dynamicTreeview);
  }
  
  public void setTreeviewForMyApplicants(HttpServletRequest request, long currentuserid)
  {
    logger.info("Inside setTreeviewForMyApplicants");
    TreeView dynamicTreeview = new TreeView();
    List orgList = this.jobrequisitiondao.getMyJobRequistionsGroupByOrganization(currentuserid);
    for (int i = 0; i < orgList.size(); i++)
    {
      Object[] obj = (Object[])orgList.get(i);
      


      String orgname = (String)obj[0];
      long orgid = ((Long)obj[1]).longValue();
      
      TreeNode root1 = new TreeNode("orgname_" + orgid, orgname);
      root1.setImg("base.gif");
      root1.setOnclick("function(node){getmyrequisitions('" + orgid + "'" + "," + "'" + currentuserid + "'" + ");}");
      dynamicTreeview.addTreeNode(root1);
      
      Organization org = this.organizationdao.getOrganization(String.valueOf(orgid));
      

      List departmentList = org.getDeptlist();
      if ((departmentList != null) && (departmentList.size() > 0)) {
        for (int j = 0; j < departmentList.size(); j++)
        {
          Department dept = (Department)departmentList.get(j);
          
          int total = this.jobrequisitiondao.getCountOfMyJobRequistionsByOrgByDept(String.valueOf(org.getOrgId()), String.valueOf(currentuserid), String.valueOf(dept.getDepartmentId()));
          TreeNode child1 = new TreeNode("110dept_" + dept.getDepartmentId() + "_" + org.getOrgId() + "_" + currentuserid, dept.getDepartmentName() + "(" + total + ")");
          child1.setImg("page.gif");
          root1.addTreeNode(child1);
          child1.setOpenAjax(Boolean.TRUE);
          child1.setOnclick("function(node){getrequisitionbyorgdepthiringmgr('" + org.getOrgId() + "'" + "," + "'" + dept.getDepartmentId() + "'" + "," + "'" + currentuserid + "'" + ");}");
        }
      }
    }
    request.setAttribute("dynamicTreeview", dynamicTreeview);
  }
  
  public void setTreeviewForAgency(User user, HttpServletRequest request, long reqid)
  {
    setTreeviewForAgency(user, request, reqid, null);
  }
  
  public void setTreeviewForAgency(User user, HttpServletRequest request, long reqid, String uuid)
  {
    logger.info("Inside setTreeviewForAgency");
    TreeView dynamicTreeview = new TreeView();
    JobRequisition jb = null;
    if (uuid == null) {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(reqid));
    } else {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(reqid), uuid);
    }
    if (this.jobrequisitiondao.isAgencyHeaderTreeAllowed(user, jb))
    {
      int totalapplicant = this.applicantdao.getCountOfAllApplicantsByRequisitionIdandVendorId(reqid, user.getUserId());
      

      request.setAttribute("JOB_REQUISTION", jb);
      request.setAttribute("TOTAL_APPLICANT", String.valueOf(totalapplicant));
    }
    else
    {
      request.setAttribute("NO_PERMISSION", "yes");
    }
  }
  
  public void setTreeviewForRequistion(User user, HttpServletRequest request, long reqid)
  {
    setTreeviewForRequistion(user, request, reqid, null);
  }
  
  public void setTreeviewForRequistion(User user, HttpServletRequest request, long reqid, String uuid)
  {
    logger.info("Inside setTreeviewForRequistion" + uuid);
    TreeView dynamicTreeview = new TreeView();
    JobRequisition jb = null;
    if (uuid == null)
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(reqid));
      uuid = jb.getUuid();
    }
    else
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(reqid), uuid);
    }
    logger.info("jb.getRecruiterId()" + jb.getRecruiterId());
    logger.info("user()" + user.getUserId());
    if (PermissionBO.isRequistionHeaderTreeAllowed(user, jb))
    {
      String nodename = jb.getJobreqName();
      if (jb.getProjectcode() != null) {
        nodename = jb.getProjectcode().getProjCode() + "-" + jb.getJobreqName();
      }
      int totalapplicant = this.applicantdao.getCountOfAllApplicantsByRequisitionId(reqid);
      TreeNode root1 = new TreeNode("120req_" + reqid, nodename + "(" + totalapplicant + ")");
      root1.setImg("page.gif");
      root1.setOpenAjax(Boolean.TRUE);
      
      root1.setOnclick("function(node){getallapplicantsbyreq('" + reqid + "'" + "," + "'" + uuid + "'" + ");}");
      dynamicTreeview.addTreeNode(root1);
      


      request.setAttribute("JOB_REQUISTION", jb);
      request.setAttribute("TOTAL_APPLICANT", String.valueOf(totalapplicant));
      
      request.setAttribute("dynamicTreeview", dynamicTreeview);
    }
    else
    {
      request.setAttribute("NO_PERMISSION", "yes");
    }
  }
  
  public void setTreeviewForRequistionUIRevamp(User user, HttpServletRequest request, long reqid, String uuid)
  {
    logger.info("Inside setTreeviewForRequistionUIRevamp" + uuid);
    
    JobRequisition jb = null;
    if (uuid == null)
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(reqid));
      uuid = jb.getUuid();
    }
    else
    {
      jb = this.jobrequisitiondao.getJobRequision(String.valueOf(reqid), uuid);
    }
    if (PermissionBO.isRequistionHeaderTreeAllowed(user, jb))
    {
      int totalapplicant = this.applicantdao.getCountOfAllApplicantsByRequisitionId(reqid);
      

      request.setAttribute("JOB_REQUISTION", jb);
      request.setAttribute("TOTAL_APPLICANT", String.valueOf(totalapplicant));
    }
    else
    {
      request.setAttribute("NO_PERMISSION", "yes");
    }
  }
  
  public void setTreeviewForRecruiter(HttpServletRequest request, long recruiterId)
  {
    logger.info("Inside setTreeviewForRecruiter");
    TreeView dynamicTreeview = new TreeView();
    List orgList = this.jobrequisitiondao.getJobRequistionsGroupByOrganizationByRecruiterId(recruiterId);
    for (int i = 0; i < orgList.size(); i++)
    {
      Object[] obj = (Object[])orgList.get(i);
      


      String orgname = (String)obj[0];
      long orgid = ((Long)obj[1]).longValue();
      logger.info("orgname" + orgname);
      logger.info("orgid" + orgid);
      TreeNode root1 = new TreeNode("orgname_" + orgid, orgname);
      root1.setImg("base.gif");
      root1.setOnclick("function(node){getrequisitionbyorgrecruiterid('" + orgid + "'" + "," + "'" + recruiterId + "'" + ");}");
      dynamicTreeview.addTreeNode(root1);
      
      Organization org = this.organizationdao.getOrganization(String.valueOf(orgid));
      

      List departmentList = org.getDeptlist();
      logger.info("departmentList.size()" + departmentList.size());
      if ((departmentList != null) && (departmentList.size() > 0)) {
        for (int j = 0; j < departmentList.size(); j++)
        {
          Department dept = (Department)departmentList.get(j);
          
          int total = this.jobrequisitiondao.getCountOfJobRequistionsByOrgByRecruiterIdByDept(String.valueOf(org.getOrgId()), String.valueOf(recruiterId), String.valueOf(dept.getDepartmentId()));
          TreeNode child1 = new TreeNode("110dept_" + dept.getDepartmentId() + "_" + org.getOrgId() + "_" + recruiterId, dept.getDepartmentName() + "(" + total + ")");
          child1.setImg("page.gif");
          root1.addTreeNode(child1);
          child1.setOpenAjax(Boolean.TRUE);
          child1.setOnclick("function(node){getrequisitionbyorgdeptrecruiterid('" + org.getOrgId() + "'" + "," + "'" + dept.getDepartmentId() + "'" + "," + "'" + recruiterId + "'" + ");}");
        }
      }
    }
    request.setAttribute("dynamicTreeview", dynamicTreeview);
  }
  
  public void ajaxAgencyTree(String treeViewId, String treeNodeId, TreeNode treeNodeRoot, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.info("inside AjaxRecruiterTreeForReqAction onOpenAjax method");
    String app_id = (String)request.getSession().getAttribute("app_id");
    User user1 = (User)request.getSession().getAttribute("agency_data");
    if (treeNodeId.startsWith("120req_"))
    {
      String reqId = treeNodeId.substring(treeNodeId.indexOf("_") + 1, treeNodeId.length());
      logger.info("reqId" + reqId);
      long reqid = new Long(reqId).longValue();
      
      List applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Application Submitted", "desc", "createdDate");
      

      TreeNode reqappchild = new TreeNode("130reqappsub_" + reqId, "Application Submitted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappchild);
      reqappchild.setOpenAjax(Boolean.TRUE);
      reqappchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Application Submitted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj3 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj3[0]).longValue();
        String appname = (String)obj3[1];
        String uuid = (String)obj3[2];
        




        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "In Screening", "desc", "createdDate");
      
      TreeNode reqappinscreeningchild = new TreeNode("130reqappinscreening_" + reqId, "In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappinscreeningchild);
      reqappinscreeningchild.setOpenAjax(Boolean.TRUE);
      reqappinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Cleared-In Screening", "desc", "createdDate");
      
      TreeNode clearedinscreeningchild = new TreeNode("130reqappclinscreening_" + reqId, "Cleared-In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(clearedinscreeningchild);
      clearedinscreeningchild.setOpenAjax(Boolean.TRUE);
      clearedinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Cleared-In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        clearedinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      int k2 = 1;
      int k1 = 1;
      int minimumround = new Integer(Constant.getValue("minimum.interview.rounds")).intValue();
      int maximumround = new Integer(Constant.getValue("maximum.interview.rounds")).intValue();
      for (int i1 = 0; i1 < minimumround; i1++)
      {
        String tmpround = "Round-" + k1;
        

        applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Interview " + tmpround, "desc", "createdDate");
        
        TreeNode interviewroundchild = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild);
        interviewroundchild.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + tmpround;
        interviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          interviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Cleared-Interview " + tmpround, "desc", "createdDate");
        
        TreeNode clinterviewroundchild = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild);
        clinterviewroundchild.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + tmpround;
        clinterviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          clinterviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
        k2 += 2;
      }
      List intstateList = this.applicantdao.getDistictApplicantState(reqid);
      logger.info("k2" + k2);
      k1 = minimumround + 1;
      List intstateListnew = new ArrayList();
      Set set = new HashSet();
      for (int h1 = 0; h1 < intstateList.size(); h1++)
      {
        String sts = (String)intstateList.get(h1);
        if ((!StringUtils.isNullOrEmpty(sts)) && (sts.indexOf("Round-") > 0)) {
          set.add(sts.substring(sts.indexOf("Round-")));
        }
      }
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String tmp = (String)itr.next();
        String tmp1 = tmp.substring(tmp.indexOf("Round-") + 6);
        if (new Integer(tmp1).intValue() > minimumround) {
          intstateListnew.add(tmp);
        }
      }
      Collections.sort(intstateListnew);
      for (int i1 = 0; i1 < intstateListnew.size(); i1++)
      {
        String statename = (String)intstateListnew.get(i1);
        
        logger.info("statename" + statename);
        


        applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Interview " + statename, "desc", "createdDate");
        TreeNode interviewroundchild1 = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild1);
        interviewroundchild1.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + statename;
        interviewroundchild1.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild1 = new TreeNode("app_" + appid, appname);
          interviewroundchild1.addTreeNode(appchild1);
          setApplicantStyle(appchild1, appid, app_id, request);
          
          appchild1.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Cleared-Interview " + statename, "desc", "createdDate");
        
        TreeNode clinterviewroundchild2 = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild2);
        clinterviewroundchild2.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + statename;
        clinterviewroundchild2.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild2 = new TreeNode("app_" + appid, appname);
          clinterviewroundchild2.addTreeNode(appchild2);
          setApplicantStyle(appchild2, appid, app_id, request);
          
          appchild2.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Offer Initiated-In Approval", "desc", "createdDate");
      
      TreeNode offerinitiatedchild = new TreeNode("130reqofferinitiated_" + reqId, "Offer Initiated-In Approval(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinitiatedchild);
      offerinitiatedchild.setOpenAjax(Boolean.TRUE);
      offerinitiatedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Initiated-In Approval" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinitiatedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Offer rejected by approver", "desc", "createdDate");
      
      TreeNode offerapprovalrejectedchild = new TreeNode("130reqofferrejected_" + reqId, "Offer rejected by approver(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerapprovalrejectedchild);
      offerapprovalrejectedchild.setOpenAjax(Boolean.TRUE);
      offerapprovalrejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer rejected by approver" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerapprovalrejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Ready for Release Offer", "desc", "createdDate");
      
      TreeNode readyforofferreleasechild = new TreeNode("130reqofferready_" + reqId, "Ready for Release Offer(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(readyforofferreleasechild);
      readyforofferreleasechild.setOpenAjax(Boolean.TRUE);
      readyforofferreleasechild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Ready for Release Offer" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        readyforofferreleasechild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Offer Released", "desc", "createdDate");
      
      TreeNode offeredchild = new TreeNode("130reqoffed_" + reqId, "Offer Released(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeredchild);
      offeredchild.setOpenAjax(Boolean.TRUE);
      offeredchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Released" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeredchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Offer In Negotiation", "desc", "createdDate");
      
      TreeNode offerinnegochild = new TreeNode("130reqofferinnego_" + reqId, "Offer In Negotiation(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinnegochild);
      offerinnegochild.setOpenAjax(Boolean.TRUE);
      offerinnegochild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer In Negotiation" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinnegochild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Offer Accepted", "desc", "createdDate");
      
      TreeNode offeraccptedchild = new TreeNode("130reqofferaccepted_" + reqId, "Offer Accepted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeraccptedchild);
      offeraccptedchild.setOpenAjax(Boolean.TRUE);
      offeraccptedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Accepted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeraccptedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Offer Canceled", "desc", "createdDate");
      
      TreeNode offercancelledchild = new TreeNode("130reqoffercancelled_" + reqId, "Offer Canceled(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offercancelledchild);
      offercancelledchild.setOpenAjax(Boolean.TRUE);
      offercancelledchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Canceled" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offercancelledchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatusLike(reqid, user1.getUserId(), "Rejected", "desc", "createdDate");
      
      TreeNode rejectedchild = new TreeNode("130reqoffercalled_" + reqId, "Rejected(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(rejectedchild);
      rejectedchild.setOpenAjax(Boolean.TRUE);
      rejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Rejected" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        rejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "Offer Declined", "desc", "createdDate");
      
      TreeNode offerdeclinedchild = new TreeNode("130reqofferdeclined_" + reqId, "Offer Declined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerdeclinedchild);
      offerdeclinedchild.setOpenAjax(Boolean.TRUE);
      offerdeclinedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Declined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerdeclinedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "OnHold", "desc", "createdDate");
      
      TreeNode onholdchild = new TreeNode("130reqofferonhold_" + reqId, "OnHold(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onholdchild);
      onholdchild.setOpenAjax(Boolean.TRUE);
      onholdchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "OnHold" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onholdchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqid, user1.getUserId(), "On Board - Joined", "desc", "createdDate");
      
      TreeNode onboardchild = new TreeNode("130reqonboard_" + reqId, "On Board - Joined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onboardchild);
      onboardchild.setOpenAjax(Boolean.TRUE);
      onboardchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "On Board - Joined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onboardchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getReferenceCheckApplicantsByRequitionIdandVendorId(reqid, user1.getUserId(), "desc", "createdDate");
      
      TreeNode referencecheckchild = new TreeNode("130reqonboard_" + reqId, "Reference Check(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(referencecheckchild);
      referencecheckchild.setOpenAjax(Boolean.TRUE);
      referencecheckchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Reference Check" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        referencecheckchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
    }
  }
  
  public void ajaxHiringManagerTree(String treeViewId, String treeNodeId, TreeNode treeNodeRoot, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.info("inside ajaxHiringManagerTree method");
    String app_id = (String)request.getSession().getAttribute("app_id");
    if (treeNodeId.startsWith("110dept_"))
    {
      String deptId = "0";
      String orgId = "0";
      String userId = "0";
      StringTokenizer stoken = new StringTokenizer(treeNodeId, "_");
      if (stoken.hasMoreTokens())
      {
        stoken.nextToken();
        deptId = stoken.nextToken();
        orgId = stoken.nextToken();
        userId = stoken.nextToken();
      }
      logger.info("orgId" + orgId);
      logger.info("deptId" + deptId);
      List reqlst = this.jobrequisitiondao.getMyJobRequistionsByOrganizationByDepartmentTx(new Long(orgId).longValue(), new Long(deptId).longValue(), new Long(userId).longValue());
      for (int k = 0; k < reqlst.size(); k++)
      {
        JobRequisition jb = (JobRequisition)reqlst.get(k);
        long reqid = jb.getJobreqId();
        String reqname = jb.getJobreqName();
        String projectcode = "";
        ProjectCodes pj = jb.getProjectcode();
        if (pj != null) {
          projectcode = pj.getProjCode();
        }
        String projectcode1 = projectcode + "-";
        


        int totalapplicant = this.applicantdao.getCountOfAllApplicantsByRequisitionId(reqid);
        String reqtempvalue = projectcode1 + reqname;
        TreeNode reqchild = new TreeNode("120req_" + reqid, reqtempvalue + "(" + totalapplicant + ")");
        treeNodeRoot.addTreeNode(reqchild);
        reqchild.setOpenAjax(Boolean.TRUE);
        reqchild.setOnclick("function(node){getallapplicants('" + reqid + "');}");
      }
    }
    else if (treeNodeId.startsWith("120req_"))
    {
      String reqId = treeNodeId.substring(treeNodeId.indexOf("_") + 1, treeNodeId.length());
      logger.info("reqId" + reqId);
      long reqid = new Long(reqId).longValue();
      
      List applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Application Submitted", "desc", "createdDate");
      

      TreeNode reqappchild = new TreeNode("130reqappsub_" + reqId, "Application Submitted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappchild);
      reqappchild.setOpenAjax(Boolean.TRUE);
      reqappchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Application Submitted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj3 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj3[0]).longValue();
        String appname = (String)obj3[1];
        String uuid = (String)obj3[2];
        




        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "In Screening", "desc", "createdDate");
      
      TreeNode reqappinscreeningchild = new TreeNode("130reqappinscreening_" + reqId, "In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappinscreeningchild);
      reqappinscreeningchild.setOpenAjax(Boolean.TRUE);
      reqappinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-In Screening", "desc", "createdDate");
      
      TreeNode clearedinscreeningchild = new TreeNode("130reqappclinscreening_" + reqId, "Cleared-In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(clearedinscreeningchild);
      clearedinscreeningchild.setOpenAjax(Boolean.TRUE);
      clearedinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Cleared-In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        clearedinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      int k2 = 1;
      int k1 = 1;
      int minimumround = new Integer(Constant.getValue("minimum.interview.rounds")).intValue();
      int maximumround = new Integer(Constant.getValue("maximum.interview.rounds")).intValue();
      for (int i1 = 0; i1 < minimumround; i1++)
      {
        String tmpround = "Round-" + k1;
        

        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + tmpround, "desc", "createdDate");
        
        TreeNode interviewroundchild = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild);
        interviewroundchild.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + tmpround;
        interviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          interviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + tmpround, "desc", "createdDate");
        
        TreeNode clinterviewroundchild = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild);
        clinterviewroundchild.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + tmpround;
        clinterviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          clinterviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
        k2 += 2;
      }
      List intstateList = this.applicantdao.getDistictApplicantState(reqid);
      logger.info("k2" + k2);
      k1 = minimumround + 1;
      List intstateListnew = new ArrayList();
      Set set = new HashSet();
      for (int h1 = 0; h1 < intstateList.size(); h1++)
      {
        String sts = (String)intstateList.get(h1);
        if ((!StringUtils.isNullOrEmpty(sts)) && (sts.indexOf("Round-") > 0)) {
          set.add(sts.substring(sts.indexOf("Round-")));
        }
      }
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String tmp = (String)itr.next();
        String tmp1 = tmp.substring(tmp.indexOf("Round-") + 6);
        if (new Integer(tmp1).intValue() > minimumround) {
          intstateListnew.add(tmp);
        }
      }
      Collections.sort(intstateListnew);
      for (int i1 = 0; i1 < intstateListnew.size(); i1++)
      {
        String statename = (String)intstateListnew.get(i1);
        
        logger.info("statename" + statename);
        


        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + statename, "desc", "createdDate");
        TreeNode interviewroundchild1 = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild1);
        interviewroundchild1.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + statename;
        interviewroundchild1.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild1 = new TreeNode("app_" + appid, appname);
          interviewroundchild1.addTreeNode(appchild1);
          setApplicantStyle(appchild1, appid, app_id, request);
          
          appchild1.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + statename, "desc", "createdDate");
        
        TreeNode clinterviewroundchild2 = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild2);
        clinterviewroundchild2.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + statename;
        clinterviewroundchild2.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild2 = new TreeNode("app_" + appid, appname);
          clinterviewroundchild2.addTreeNode(appchild2);
          setApplicantStyle(appchild2, appid, app_id, request);
          
          appchild2.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Initiated-In Approval", "desc", "createdDate");
      
      TreeNode offerinitiatedchild = new TreeNode("130reqofferinitiated_" + reqId, "Offer Initiated-In Approval(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinitiatedchild);
      offerinitiatedchild.setOpenAjax(Boolean.TRUE);
      offerinitiatedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Initiated-In Approval" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinitiatedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer rejected by approver", "desc", "createdDate");
      
      TreeNode offerapprovalrejectedchild = new TreeNode("130reqofferrejected_" + reqId, "Offer rejected by approver(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerapprovalrejectedchild);
      offerapprovalrejectedchild.setOpenAjax(Boolean.TRUE);
      offerapprovalrejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer rejected by approver" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerapprovalrejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Ready for Release Offer", "desc", "createdDate");
      
      TreeNode readyforofferreleasechild = new TreeNode("130reqofferready_" + reqId, "Ready for Release Offer(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(readyforofferreleasechild);
      readyforofferreleasechild.setOpenAjax(Boolean.TRUE);
      readyforofferreleasechild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Ready for Release Offer" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        readyforofferreleasechild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Released", "desc", "createdDate");
      
      TreeNode offeredchild = new TreeNode("130reqoffed_" + reqId, "Offer Released(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeredchild);
      offeredchild.setOpenAjax(Boolean.TRUE);
      offeredchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Released" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeredchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer In Negotiation", "desc", "createdDate");
      
      TreeNode offerinnegochild = new TreeNode("130reqofferinnego_" + reqId, "Offer In Negotiation(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinnegochild);
      offerinnegochild.setOpenAjax(Boolean.TRUE);
      offerinnegochild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer In Negotiation" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinnegochild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Accepted", "desc", "createdDate");
      
      TreeNode offeraccptedchild = new TreeNode("130reqofferaccepted_" + reqId, "Offer Accepted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeraccptedchild);
      offeraccptedchild.setOpenAjax(Boolean.TRUE);
      offeraccptedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Accepted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeraccptedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Canceled", "desc", "createdDate");
      
      TreeNode offercancelledchild = new TreeNode("130reqoffercancelled_" + reqId, "Offer Canceled(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offercancelledchild);
      offercancelledchild.setOpenAjax(Boolean.TRUE);
      offercancelledchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Canceled" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offercancelledchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Rejected", "desc", "createdDate");
      
      TreeNode rejectedchild = new TreeNode("130reqoffercalled_" + reqId, "Rejected(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(rejectedchild);
      rejectedchild.setOpenAjax(Boolean.TRUE);
      rejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Rejected" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        rejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Declined", "desc", "createdDate");
      
      TreeNode offerdeclinedchild = new TreeNode("130reqofferdeclined_" + reqId, "Offer Declined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerdeclinedchild);
      offerdeclinedchild.setOpenAjax(Boolean.TRUE);
      offerdeclinedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Declined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerdeclinedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "OnHold", "desc", "createdDate");
      
      TreeNode onholdchild = new TreeNode("130reqofferonhold_" + reqId, "OnHold(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onholdchild);
      onholdchild.setOpenAjax(Boolean.TRUE);
      onholdchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "OnHold" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onholdchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "On Board - Joined", "desc", "createdDate");
      
      TreeNode onboardchild = new TreeNode("130reqonboard_" + reqId, "On Board - Joined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onboardchild);
      onboardchild.setOpenAjax(Boolean.TRUE);
      onboardchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "On Board - Joined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onboardchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Mark deleted", "desc", "createdDate");
      
      TreeNode markdeletedchild = new TreeNode("130markdelete_" + reqId, "Mark deleted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(markdeletedchild);
      markdeletedchild.setOpenAjax(Boolean.TRUE);
      markdeletedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Mark deleted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        markdeletedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getReferenceCheckApplicantsByRequitionId(reqid, "desc", "createdDate");
      
      TreeNode referencecheckchild = new TreeNode("130reqonboard_" + reqId, "Reference Check(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(referencecheckchild);
      referencecheckchild.setOpenAjax(Boolean.TRUE);
      referencecheckchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Reference Check" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        referencecheckchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
    }
  }
  
  public void ajaxRecruiterTree(String treeViewId, String treeNodeId, TreeNode treeNodeRoot, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.info("inside onOpenAjax method" + treeNodeId);
    String app_id = (String)request.getSession().getAttribute("app_id");
    if (treeNodeId.startsWith("110dept_"))
    {
      String deptId = "0";
      String orgId = "0";
      String userId = "0";
      StringTokenizer stoken = new StringTokenizer(treeNodeId, "_");
      if (stoken.hasMoreTokens())
      {
        stoken.nextToken();
        deptId = stoken.nextToken();
        orgId = stoken.nextToken();
        userId = stoken.nextToken();
      }
      logger.info("orgId" + orgId);
      logger.info("deptId" + deptId);
      List reqlst = this.jobrequisitiondao.getJobRequistionsByOrganizationByDepartmentByRecruiterTx(new Long(orgId).longValue(), new Long(deptId).longValue(), new Long(userId).longValue());
      for (int k = 0; k < reqlst.size(); k++)
      {
        JobRequisition jb = (JobRequisition)reqlst.get(k);
        
        long reqid = jb.getJobreqId();
        String reqname = jb.getJobreqName();
        String projectcode = null;
        if (jb.getProjectcode() != null) {
          projectcode = jb.getProjectcode().getProjCode();
        }
        String projectcode1 = projectcode + "-";
        


        int totalapplicant = this.applicantdao.getCountOfAllApplicantsByRequisitionId(reqid);
        String reqtempvalue = projectcode1 + reqname;
        TreeNode reqchild = new TreeNode("120req_" + reqid, reqtempvalue + "(" + totalapplicant + ")");
        treeNodeRoot.addTreeNode(reqchild);
        reqchild.setOpenAjax(Boolean.TRUE);
        reqchild.setOnclick("function(node){getallapplicants('" + reqid + "');}");
      }
    }
    else if (treeNodeId.startsWith("120req_"))
    {
      String reqId = treeNodeId.substring(treeNodeId.indexOf("_") + 1, treeNodeId.length());
      logger.info("reqId" + reqId);
      long reqid = new Long(reqId).longValue();
      
      List applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Application Submitted", "desc", "createdDate");
      

      TreeNode reqappchild = new TreeNode("130reqappsub_" + reqId, "Application Submitted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappchild);
      reqappchild.setOpenAjax(Boolean.TRUE);
      reqappchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Application Submitted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj3 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj3[0]).longValue();
        String appname = (String)obj3[1];
        String uuid = (String)obj3[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "In Screening", "desc", "createdDate");
      
      TreeNode reqappinscreeningchild = new TreeNode("130reqappinscreening_" + reqId, "In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappinscreeningchild);
      reqappinscreeningchild.setOpenAjax(Boolean.TRUE);
      reqappinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-In Screening", "desc", "createdDate");
      
      TreeNode clearedinscreeningchild = new TreeNode("130reqappclinscreening_" + reqId, "Cleared-In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(clearedinscreeningchild);
      clearedinscreeningchild.setOpenAjax(Boolean.TRUE);
      clearedinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Cleared-In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        clearedinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      int k2 = 1;
      int k1 = 1;
      int minimumround = new Integer(Constant.getValue("minimum.interview.rounds")).intValue();
      int maximumround = new Integer(Constant.getValue("maximum.interview.rounds")).intValue();
      for (int i1 = 0; i1 < minimumround; i1++)
      {
        String tmpround = "Round-" + k1;
        

        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + tmpround, "desc", "createdDate");
        
        TreeNode interviewroundchild = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild);
        interviewroundchild.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + tmpround;
        interviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          interviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + tmpround, "desc", "createdDate");
        
        TreeNode clinterviewroundchild = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild);
        clinterviewroundchild.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + tmpround;
        clinterviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          clinterviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
        k2 += 2;
      }
      List intstateList = this.applicantdao.getDistictApplicantState(reqid);
      logger.info("k2" + k2);
      k1 = minimumround + 1;
      List intstateListnew = new ArrayList();
      Set set = new HashSet();
      for (int h1 = 0; h1 < intstateList.size(); h1++)
      {
        String sts = (String)intstateList.get(h1);
        if ((!StringUtils.isNullOrEmpty(sts)) && (sts.indexOf("Round-") > 0)) {
          set.add(sts.substring(sts.indexOf("Round-")));
        }
      }
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String tmp = (String)itr.next();
        String tmp1 = tmp.substring(tmp.indexOf("Round-") + 6);
        if (new Integer(tmp1).intValue() > minimumround) {
          intstateListnew.add(tmp);
        }
      }
      Collections.sort(intstateListnew);
      for (int i1 = 0; i1 < intstateListnew.size(); i1++)
      {
        String statename = (String)intstateListnew.get(i1);
        
        logger.info("statename" + statename);
        


        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + statename, "desc", "createdDate");
        TreeNode interviewroundchild1 = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild1);
        interviewroundchild1.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + statename;
        interviewroundchild1.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild1 = new TreeNode("app_" + appid, appname);
          interviewroundchild1.addTreeNode(appchild1);
          setApplicantStyle(appchild1, appid, app_id, request);
          
          appchild1.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + statename, "desc", "createdDate");
        
        TreeNode clinterviewroundchild2 = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild2);
        clinterviewroundchild2.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + statename;
        clinterviewroundchild2.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild2 = new TreeNode("app_" + appid, appname);
          clinterviewroundchild2.addTreeNode(appchild2);
          setApplicantStyle(appchild2, appid, app_id, request);
          
          appchild2.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Initiated-In Approval", "desc", "createdDate");
      
      TreeNode offerinitiatedchild = new TreeNode("130reqofferinitiated_" + reqId, "Offer Initiated-In Approval(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinitiatedchild);
      offerinitiatedchild.setOpenAjax(Boolean.TRUE);
      offerinitiatedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Initiated-In Approval" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinitiatedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer rejected by approver", "desc", "createdDate");
      
      TreeNode offerapprovalrejectedchild = new TreeNode("130reqofferrejected_" + reqId, "Offer rejected by approver(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerapprovalrejectedchild);
      offerapprovalrejectedchild.setOpenAjax(Boolean.TRUE);
      offerapprovalrejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer rejected by approver" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerapprovalrejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Ready for Release Offer", "desc", "createdDate");
      
      TreeNode readyforofferreleasechild = new TreeNode("130reqofferready_" + reqId, "Ready for Release Offer(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(readyforofferreleasechild);
      readyforofferreleasechild.setOpenAjax(Boolean.TRUE);
      readyforofferreleasechild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Ready for Release Offer" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        readyforofferreleasechild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Released", "desc", "createdDate");
      
      TreeNode offeredchild = new TreeNode("130reqoffed_" + reqId, "Offer Released(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeredchild);
      offeredchild.setOpenAjax(Boolean.TRUE);
      offeredchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Released" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeredchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer In Negotiation", "desc", "createdDate");
      
      TreeNode offerinnegochild = new TreeNode("130reqofferinnego_" + reqId, "Offer In Negotiation(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinnegochild);
      offerinnegochild.setOpenAjax(Boolean.TRUE);
      offerinnegochild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer In Negotiation" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinnegochild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Accepted", "desc", "createdDate");
      
      TreeNode offeraccptedchild = new TreeNode("130reqofferaccepted_" + reqId, "Offer Accepted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeraccptedchild);
      offeraccptedchild.setOpenAjax(Boolean.TRUE);
      offeraccptedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Accepted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeraccptedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Canceled", "desc", "createdDate");
      
      TreeNode offercancelledchild = new TreeNode("130reqoffercancelled_" + reqId, "Offer Canceled(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offercancelledchild);
      offercancelledchild.setOpenAjax(Boolean.TRUE);
      offercancelledchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Canceled" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offercancelledchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Rejected", "desc", "createdDate");
      
      TreeNode rejectedchild = new TreeNode("130reqoffercalled_" + reqId, "Rejected(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(rejectedchild);
      rejectedchild.setOpenAjax(Boolean.TRUE);
      rejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Rejected" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        rejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Declined", "desc", "createdDate");
      
      TreeNode offerdeclinedchild = new TreeNode("130reqofferdeclined_" + reqId, "Offer Declined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerdeclinedchild);
      offerdeclinedchild.setOpenAjax(Boolean.TRUE);
      offerdeclinedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Declined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerdeclinedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "OnHold", "desc", "createdDate");
      
      TreeNode onholdchild = new TreeNode("130reqofferonhold_" + reqId, "OnHold(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onholdchild);
      onholdchild.setOpenAjax(Boolean.TRUE);
      onholdchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "OnHold" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onholdchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "On Board - Joined", "desc", "createdDate");
      
      TreeNode onboardchild = new TreeNode("130reqonboard_" + reqId, "On Board - Joined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onboardchild);
      onboardchild.setOpenAjax(Boolean.TRUE);
      onboardchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "On Board - Joined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onboardchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Mark deleted", "desc", "createdDate");
      
      TreeNode markdeletedchild = new TreeNode("130markdelete_" + reqId, "Mark deleted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(markdeletedchild);
      markdeletedchild.setOpenAjax(Boolean.TRUE);
      markdeletedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Mark deleted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        markdeletedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getReferenceCheckApplicantsByRequitionId(reqid, "desc", "createdDate");
      
      TreeNode referencecheckchild = new TreeNode("130reqonboard_" + reqId, "Reference Check(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(referencecheckchild);
      referencecheckchild.setOpenAjax(Boolean.TRUE);
      referencecheckchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Reference Check" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        referencecheckchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
    }
  }
  
  public void ajaxRecruiterTreeForReq(String treeViewId, String treeNodeId, TreeNode treeNodeRoot, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.info("inside AjaxRecruiterTreeForReqAction onOpenAjax method");
    String app_id = (String)request.getSession().getAttribute("app_id");
    logger.info("inside AjaxRecruiterTreeForReqAction onOpenAjax method" + app_id + treeNodeId);
    if (treeNodeId.startsWith("120req_"))
    {
      String reqId = treeNodeId.substring(treeNodeId.indexOf("_") + 1, treeNodeId.length());
      logger.info("reqId" + reqId);
      long reqid = new Long(reqId).longValue();
      String uuidreq = this.jobrequisitiondao.getUUIDbyReqId(reqid);
      
      List applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Application Submitted", "desc", "createdDate");
      

      TreeNode reqappchild = new TreeNode("130reqappsub_" + reqId, "Application Submitted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappchild);
      reqappchild.setOpenAjax(Boolean.TRUE);
      reqappchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Application Submitted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj3 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj3[0]).longValue();
        String appname = (String)obj3[1];
        String uuid = (String)obj3[2];
        




        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "In Screening", "desc", "createdDate");
      
      TreeNode reqappinscreeningchild = new TreeNode("130reqappinscreening_" + reqId, "In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappinscreeningchild);
      reqappinscreeningchild.setOpenAjax(Boolean.TRUE);
      reqappinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-In Screening", "desc", "createdDate");
      
      TreeNode clearedinscreeningchild = new TreeNode("130reqappclinscreening_" + reqId, "Cleared-In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(clearedinscreeningchild);
      clearedinscreeningchild.setOpenAjax(Boolean.TRUE);
      clearedinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Cleared-In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        clearedinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      int k2 = 1;
      int k1 = 1;
      int minimumround = new Integer(Constant.getValue("minimum.interview.rounds")).intValue();
      int maximumround = new Integer(Constant.getValue("maximum.interview.rounds")).intValue();
      for (int i1 = 0; i1 < minimumround; i1++)
      {
        String tmpround = "Round-" + k1;
        

        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + tmpround, "desc", "createdDate");
        
        TreeNode interviewroundchild = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild);
        interviewroundchild.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + tmpround;
        interviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          interviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + tmpround, "desc", "createdDate");
        
        TreeNode clinterviewroundchild = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild);
        clinterviewroundchild.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + tmpround;
        clinterviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          clinterviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
        k2 += 2;
      }
      List intstateList = this.applicantdao.getDistictApplicantState(reqid);
      logger.info("k2" + k2);
      k1 = minimumround + 1;
      List intstateListnew = new ArrayList();
      Set set = new HashSet();
      for (int h1 = 0; h1 < intstateList.size(); h1++)
      {
        String sts = (String)intstateList.get(h1);
        if ((!StringUtils.isNullOrEmpty(sts)) && (sts.indexOf("Round-") > 0)) {
          set.add(sts.substring(sts.indexOf("Round-")));
        }
      }
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String tmp = (String)itr.next();
        String tmp1 = tmp.substring(tmp.indexOf("Round-") + 6);
        if (new Integer(tmp1).intValue() > minimumround) {
          intstateListnew.add(tmp);
        }
      }
      Collections.sort(intstateListnew);
      for (int i1 = 0; i1 < intstateListnew.size(); i1++)
      {
        String statename = (String)intstateListnew.get(i1);
        
        logger.info("statename" + statename);
        


        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + statename, "desc", "createdDate");
        TreeNode interviewroundchild1 = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild1);
        interviewroundchild1.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + statename;
        interviewroundchild1.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild1 = new TreeNode("app_" + appid, appname);
          interviewroundchild1.addTreeNode(appchild1);
          setApplicantStyle(appchild1, appid, app_id, request);
          
          appchild1.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + statename, "desc", "createdDate");
        
        TreeNode clinterviewroundchild2 = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild2);
        clinterviewroundchild2.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + statename;
        clinterviewroundchild2.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild2 = new TreeNode("app_" + appid, appname);
          clinterviewroundchild2.addTreeNode(appchild2);
          setApplicantStyle(appchild2, appid, app_id, request);
          
          appchild2.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Initiated-In Approval", "desc", "createdDate");
      
      TreeNode offerinitiatedchild = new TreeNode("130reqofferinitiated_" + reqId, "Offer Initiated-In Approval(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinitiatedchild);
      offerinitiatedchild.setOpenAjax(Boolean.TRUE);
      offerinitiatedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Offer Initiated-In Approval" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinitiatedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer rejected by approver", "desc", "createdDate");
      
      TreeNode offerapprovalrejectedchild = new TreeNode("130reqofferrejected_" + reqId, "Offer rejected by approver(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerapprovalrejectedchild);
      offerapprovalrejectedchild.setOpenAjax(Boolean.TRUE);
      offerapprovalrejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Offer rejected by approver" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerapprovalrejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Ready for Release Offer", "desc", "createdDate");
      
      TreeNode readyforofferreleasechild = new TreeNode("130reqofferready_" + reqId, "Ready for Release Offer(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(readyforofferreleasechild);
      readyforofferreleasechild.setOpenAjax(Boolean.TRUE);
      readyforofferreleasechild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Ready for Release Offer" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        readyforofferreleasechild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Released", "desc", "createdDate");
      
      TreeNode offeredchild = new TreeNode("130reqoffed_" + reqId, "Offer Released(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeredchild);
      offeredchild.setOpenAjax(Boolean.TRUE);
      offeredchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Offer Released" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeredchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer In Negotiation", "desc", "createdDate");
      
      TreeNode offerinnegochild = new TreeNode("130reqofferinnego_" + reqId, "Offer In Negotiation(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinnegochild);
      offerinnegochild.setOpenAjax(Boolean.TRUE);
      offerinnegochild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Offer In Negotiation" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinnegochild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Accepted", "desc", "createdDate");
      
      TreeNode offeraccptedchild = new TreeNode("130reqofferaccepted_" + reqId, "Offer Accepted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeraccptedchild);
      offeraccptedchild.setOpenAjax(Boolean.TRUE);
      offeraccptedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Offer Accepted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeraccptedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Canceled", "desc", "createdDate");
      
      TreeNode offercancelledchild = new TreeNode("130reqoffercancelled_" + reqId, "Offer Canceled(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offercancelledchild);
      offercancelledchild.setOpenAjax(Boolean.TRUE);
      offercancelledchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Offer Canceled" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offercancelledchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Rejected", "desc", "createdDate");
      
      TreeNode rejectedchild = new TreeNode("130reqoffercalled_" + reqId, "Rejected(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(rejectedchild);
      rejectedchild.setOpenAjax(Boolean.TRUE);
      rejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Rejected" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        rejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Declined", "desc", "createdDate");
      
      TreeNode offerdeclinedchild = new TreeNode("130reqofferdeclined_" + reqId, "Offer Declined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerdeclinedchild);
      offerdeclinedchild.setOpenAjax(Boolean.TRUE);
      offerdeclinedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Offer Declined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerdeclinedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "OnHold", "desc", "createdDate");
      
      TreeNode onholdchild = new TreeNode("130reqofferonhold_" + reqId, "OnHold(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onholdchild);
      onholdchild.setOpenAjax(Boolean.TRUE);
      onholdchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "OnHold" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onholdchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "On Board - Joined", "desc", "createdDate");
      
      TreeNode onboardchild = new TreeNode("130reqonboard_" + reqId, "On Board - Joined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onboardchild);
      onboardchild.setOpenAjax(Boolean.TRUE);
      onboardchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "On Board - Joined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onboardchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Mark deleted", "desc", "createdDate");
      
      TreeNode markdeletedchild = new TreeNode("130markdelete_" + reqId, "Mark deleted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(markdeletedchild);
      markdeletedchild.setOpenAjax(Boolean.TRUE);
      markdeletedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Mark deleted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        markdeletedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getReferenceCheckApplicantsByRequitionId(reqid, "desc", "createdDate");
      
      TreeNode referencecheckchild = new TreeNode("130reqonboard_" + reqId, "Reference Check(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(referencecheckchild);
      referencecheckchild.setOpenAjax(Boolean.TRUE);
      referencecheckchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + uuidreq + "'" + "," + "'" + "Reference Check" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        referencecheckchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
    }
  }
  
  public void ajaxReqTree(String treeViewId, String treeNodeId, TreeNode treeNodeRoot, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.info("inside onOpenAjax method");
    String app_id = (String)request.getSession().getAttribute("app_id");
    if (treeNodeId.startsWith("110dept_"))
    {
      String deptId = "0";
      String orgId = "0";
      StringTokenizer stoken = new StringTokenizer(treeNodeId, "_");
      if (stoken.hasMoreTokens())
      {
        stoken.nextToken();
        deptId = stoken.nextToken();
        orgId = stoken.nextToken();
      }
      logger.info("orgId" + orgId);
      logger.info("deptId" + deptId);
      List reqlst = this.jobrequisitiondao.getJobRequistionsByOrganizationByDepartmentTx(new Long(orgId).longValue(), new Long(deptId).longValue());
      for (int k = 0; k < reqlst.size(); k++)
      {
        Object[] obj2 = (Object[])reqlst.get(k);
        long reqid = ((Long)obj2[0]).longValue();
        String reqname = (String)obj2[1];
        String projectcode = (String)obj2[2];
        String projectcode1 = projectcode + "-";
        


        int totalapplicant = this.applicantdao.getCountOfAllApplicantsByRequisitionId(reqid);
        String reqtempvalue = projectcode1 + reqname;
        TreeNode reqchild = new TreeNode("120req_" + reqid, reqtempvalue + "(" + totalapplicant + ")");
        
        treeNodeRoot.addTreeNode(reqchild);
        
        reqchild.setOpenAjax(Boolean.TRUE);
        reqchild.setOnclick("function(node){getallapplicants('" + reqid + "');}");
      }
    }
    else if (treeNodeId.startsWith("120req_"))
    {
      String reqId = treeNodeId.substring(treeNodeId.indexOf("_") + 1, treeNodeId.length());
      logger.info("reqId" + reqId);
      long reqid = new Long(reqId).longValue();
      
      List applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Application Submitted", "desc", "createdDate");
      

      TreeNode reqappchild = new TreeNode("130reqappsub_" + reqId, "Application Submitted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappchild);
      reqappchild.setOpenAjax(Boolean.TRUE);
      reqappchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Application Submitted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj3 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj3[0]).longValue();
        String appname = (String)obj3[1];
        String uuid = (String)obj3[2];
        




        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "In Screening", "desc", "createdDate");
      
      TreeNode reqappinscreeningchild = new TreeNode("130reqappinscreening_" + reqId, "In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(reqappinscreeningchild);
      reqappinscreeningchild.setOpenAjax(Boolean.TRUE);
      reqappinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        reqappinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-In Screening", "desc", "createdDate");
      
      TreeNode clearedinscreeningchild = new TreeNode("130reqappclinscreening_" + reqId, "Cleared-In Screening(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(clearedinscreeningchild);
      clearedinscreeningchild.setOpenAjax(Boolean.TRUE);
      clearedinscreeningchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Cleared-In Screening" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        clearedinscreeningchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      int k2 = 1;
      int k1 = 1;
      int minimumround = new Integer(Constant.getValue("minimum.interview.rounds")).intValue();
      int maximumround = new Integer(Constant.getValue("maximum.interview.rounds")).intValue();
      for (int i1 = 0; i1 < minimumround; i1++)
      {
        String tmpround = "Round-" + k1;
        

        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + tmpround, "desc", "createdDate");
        
        TreeNode interviewroundchild = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild);
        interviewroundchild.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + tmpround;
        interviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          interviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + tmpround, "desc", "createdDate");
        
        TreeNode clinterviewroundchild = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + tmpround + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild);
        clinterviewroundchild.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + tmpround;
        clinterviewroundchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild = new TreeNode("app_" + appid, appname);
          clinterviewroundchild.addTreeNode(appchild);
          setApplicantStyle(appchild, appid, app_id, request);
          
          appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
        k2 += 2;
      }
      List intstateList = this.applicantdao.getDistictApplicantState(reqid);
      logger.info("k2" + k2);
      k1 = minimumround + 1;
      List intstateListnew = new ArrayList();
      Set set = new HashSet();
      for (int h1 = 0; h1 < intstateList.size(); h1++)
      {
        String sts = (String)intstateList.get(h1);
        if ((!StringUtils.isNullOrEmpty(sts)) && (sts.indexOf("Round-") > 0)) {
          set.add(sts.substring(sts.indexOf("Round-")));
        }
      }
      Iterator itr = set.iterator();
      while (itr.hasNext())
      {
        String tmp = (String)itr.next();
        String tmp1 = tmp.substring(tmp.indexOf("Round-") + 6);
        if (new Integer(tmp1).intValue() > minimumround) {
          intstateListnew.add(tmp);
        }
      }
      Collections.sort(intstateListnew);
      for (int i1 = 0; i1 < intstateListnew.size(); i1++)
      {
        String statename = (String)intstateListnew.get(i1);
        
        logger.info("statename" + statename);
        


        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Interview " + statename, "desc", "createdDate");
        TreeNode interviewroundchild1 = new TreeNode("130reqappwaitinterview_" + reqId + "int_" + k1, "Waiting - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(interviewroundchild1);
        interviewroundchild1.setOpenAjax(Boolean.TRUE);
        String statevalue = "Interview " + statename;
        interviewroundchild1.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild1 = new TreeNode("app_" + appid, appname);
          interviewroundchild1.addTreeNode(appchild1);
          setApplicantStyle(appchild1, appid, app_id, request);
          
          appchild1.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Cleared-Interview " + statename, "desc", "createdDate");
        
        TreeNode clinterviewroundchild2 = new TreeNode("130reqappclinterview_" + reqId + "int_" + k1, "Cleared - Interview " + statename + "(" + applicantlist.size() + ")");
        treeNodeRoot.addTreeNode(clinterviewroundchild2);
        clinterviewroundchild2.setOpenAjax(Boolean.TRUE);
        statevalue = "Cleared-Interview " + statename;
        clinterviewroundchild2.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + statevalue + "'" + ");}");
        for (int l = 0; l < applicantlist.size(); l++)
        {
          Object[] obj4 = (Object[])applicantlist.get(l);
          long appid = ((Long)obj4[0]).longValue();
          String appname = (String)obj4[1];
          String uuid = (String)obj4[2];
          
          TreeNode appchild2 = new TreeNode("app_" + appid, appname);
          clinterviewroundchild2.addTreeNode(appchild2);
          setApplicantStyle(appchild2, appid, app_id, request);
          
          appchild2.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
        }
        k1++;
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Initiated-In Approval", "desc", "createdDate");
      
      TreeNode offerinitiatedchild = new TreeNode("130reqofferinitiated_" + reqId, "Offer Initiated-In Approval(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinitiatedchild);
      offerinitiatedchild.setOpenAjax(Boolean.TRUE);
      offerinitiatedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Initiated-In Approval" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinitiatedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer rejected by approver", "desc", "createdDate");
      
      TreeNode offerapprovalrejectedchild = new TreeNode("130reqofferrejected_" + reqId, "Offer rejected by approver(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerapprovalrejectedchild);
      offerapprovalrejectedchild.setOpenAjax(Boolean.TRUE);
      offerapprovalrejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer rejected by approver" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerapprovalrejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Ready for Release Offer", "desc", "createdDate");
      
      TreeNode readyforofferreleasechild = new TreeNode("130reqofferready_" + reqId, "Ready for Release Offer(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(readyforofferreleasechild);
      readyforofferreleasechild.setOpenAjax(Boolean.TRUE);
      readyforofferreleasechild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Ready for Release Offer" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        readyforofferreleasechild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Released", "desc", "createdDate");
      
      TreeNode offeredchild = new TreeNode("130reqoffed_" + reqId, "Offer Released(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeredchild);
      offeredchild.setOpenAjax(Boolean.TRUE);
      offeredchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Released" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeredchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer In Negotiation", "desc", "createdDate");
      
      TreeNode offerinnegochild = new TreeNode("130reqofferinnego_" + reqId, "Offer In Negotiation(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerinnegochild);
      offerinnegochild.setOpenAjax(Boolean.TRUE);
      offerinnegochild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer In Negotiation" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerinnegochild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Accepted", "desc", "createdDate");
      
      TreeNode offeraccptedchild = new TreeNode("130reqofferaccepted_" + reqId, "Offer Accepted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offeraccptedchild);
      offeraccptedchild.setOpenAjax(Boolean.TRUE);
      offeraccptedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Accepted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offeraccptedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Canceled", "desc", "createdDate");
      
      TreeNode offercancelledchild = new TreeNode("130reqoffercancelled_" + reqId, "Offer Canceled(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offercancelledchild);
      offercancelledchild.setOpenAjax(Boolean.TRUE);
      offercancelledchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Canceled" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offercancelledchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Rejected", "desc", "createdDate");
      
      TreeNode rejectedchild = new TreeNode("130reqoffercalled_" + reqId, "Rejected(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(rejectedchild);
      rejectedchild.setOpenAjax(Boolean.TRUE);
      rejectedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Rejected" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        rejectedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "Offer Declined", "desc", "createdDate");
      
      TreeNode offerdeclinedchild = new TreeNode("130reqofferdeclined_" + reqId, "Offer Declined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(offerdeclinedchild);
      offerdeclinedchild.setOpenAjax(Boolean.TRUE);
      offerdeclinedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Offer Declined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        offerdeclinedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "OnHold", "desc", "createdDate");
      
      TreeNode onholdchild = new TreeNode("130reqofferonhold_" + reqId, "OnHold(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onholdchild);
      onholdchild.setOpenAjax(Boolean.TRUE);
      onholdchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "OnHold" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onholdchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqid, "On Board - Joined", "desc", "createdDate");
      
      TreeNode onboardchild = new TreeNode("130reqonboard_" + reqId, "On Board - Joined(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(onboardchild);
      onboardchild.setOpenAjax(Boolean.TRUE);
      onboardchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "On Board - Joined" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        onboardchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqid, "Mark deleted", "desc", "createdDate");
      
      TreeNode markdeletedchild = new TreeNode("130markdelete_" + reqId, "Mark deleted(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(markdeletedchild);
      markdeletedchild.setOpenAjax(Boolean.TRUE);
      markdeletedchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Mark deleted" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        markdeletedchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
      applicantlist = this.applicantdao.getReferenceCheckApplicantsByRequitionId(reqid, "desc", "createdDate");
      
      TreeNode referencecheckchild = new TreeNode("130reqonboard_" + reqId, "Reference Check(" + applicantlist.size() + ")");
      treeNodeRoot.addTreeNode(referencecheckchild);
      referencecheckchild.setOpenAjax(Boolean.TRUE);
      referencecheckchild.setOnclick("function(node){getallapplicantswithstatus('" + reqId + "'" + "," + "'" + "Reference Check" + "'" + ");}");
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        TreeNode appchild = new TreeNode("app_" + appid, appname);
        referencecheckchild.addTreeNode(appchild);
        setApplicantStyle(appchild, appid, app_id, request);
        
        appchild.setOnclick("function(node){getapplicantdata('" + appid + "'" + "," + "'" + uuid + "'" + ");}");
      }
    }
  }
  
  public void setApplicantStyle(TreeNode appchild, long appid, String app_id, HttpServletRequest request)
  {
    if (app_id != null)
    {
      request.getSession().removeAttribute("app_id");
      long tempappid = new Long(app_id).longValue();
      if (appid == tempappid)
      {
        appchild.setImg("user_old.gif");
        appchild.setStyle("mover");
        appchild.setStyle("function(node){node.addClass('mover');}");
      }
      else
      {
        appchild.setImg("user.gif");
        appchild.setOnmouseover("function(node){node.addClass('mover');}");
        appchild.setOnmouseout("function(node){node.removeClass('mover');}");
      }
    }
    else
    {
      appchild.setImg("user.gif");
      appchild.setOnmouseover("function(node){node.addClass('mover');}");
      appchild.setOnmouseout("function(node){node.removeClass('mover');}");
    }
  }
  
  public Map<String, Integer> getCountMapOfApplicantsByRequitionIdAndStatus(long reqId)
  {
    return this.applicantdao.getCountMapOfApplicantsByRequitionIdAndStatus(reqId);
  }
  
  public Map<String, Integer> getCountMapOfApplicantsByRequitionIdAndStatus(long reqId, long vendorId)
  {
    return this.applicantdao.getCountMapOfApplicantsByRequitionIdAndStatus(reqId, vendorId);
  }
  
  public String getApplicantsTree(String enId)
  {
    String reqid = enId.substring(0, enId.indexOf("_"));
    String status = enId.substring(enId.indexOf("_") + 1);
    return getApplicantsByRequitionIdAndStatus(new Long(reqid).longValue(), status);
  }
  
  public String getApplicantsTreeByVendor(String enId, long vendorId)
  {
    String reqid = enId.substring(0, enId.indexOf("_"));
    String status = enId.substring(enId.indexOf("_") + 1);
    return getApplicantsByRequitionIdAndStatusByVendor(new Long(reqid).longValue(), status, vendorId);
  }
  
  public String getApplicantsByRequitionIdAndStatusByVendor(long reqId, String status, long vendorId)
  {
    List applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatusByVendor(reqId, vendorId, status, "desc", "createdDate");
    String data = "";
    if ((applicantlist != null) && (applicantlist.size() > 0)) {
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        
        String tmp = "<li class='text' id='" + uuid + "'>" + "<span>" + appname + "</span>" + "</li>";
        

        data = data + tmp;
      }
    } else {
      data = "<li></li>";
    }
    return data;
  }
  
  public String getApplicantsByRequitionIdAndStatus(long reqId, String status)
  {
    List applicantlist = this.applicantdao.getApplicantsByRequitionIdAndStatus(reqId, status, "desc", "createdDate");
    String data = "";
    if ((applicantlist != null) && (applicantlist.size() > 0)) {
      for (int l = 0; l < applicantlist.size(); l++)
      {
        Object[] obj4 = (Object[])applicantlist.get(l);
        long appid = ((Long)obj4[0]).longValue();
        String appname = (String)obj4[1];
        String uuid = (String)obj4[2];
        int isviewd = ((Integer)obj4[3]).intValue();
        
        String tmp = "<li class='text' id='" + uuid + "'>" + "<span>" + appname + "</span>" + "</li>";
        if (isviewd == 1) {
          tmp = "<li class='text' id='" + uuid + "'>" + "<span>" + appname + "</span>" + "<img src='jsp/images/icon_yellow_star_highlight.png' border='0' width='13' height='13'/>" + "</li>";
        }
        data = data + tmp;
      }
    } else {
      data = "<li></li>";
    }
    return data;
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
}
