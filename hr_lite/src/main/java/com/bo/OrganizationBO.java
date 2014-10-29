package com.bo;

import com.bean.Location;
import com.bean.Organization;
import com.bean.User;
import com.common.ValidationException;
import com.dao.OrganizationDAO;
import com.form.OrganizationForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class OrganizationBO
{
  OrganizationDAO organizationdao;
  protected static final Logger logger = Logger.getLogger(OrganizationBO.class);
  
  public List getAllOrganization()
  {
    return this.organizationdao.getAllOrganization();
  }
  
  public Organization getRootOrganization(long superUserKey)
  {
    return this.organizationdao.getRootOrganization(superUserKey);
  }
  
  public Organization updateOrg(Organization org)
  {
    return this.organizationdao.updateOrganization(org);
  }
  
  public Organization saveOrganization(Organization org)
  {
    return this.organizationdao.saveOrganization(org);
  }
  
  public Location saveLocation(Location location)
  {
    return this.organizationdao.saveLocation(location);
  }
  
  public Organization saveOrg(OrganizationForm organizationForm, Organization org, User user1)
  {
    org.setCreatedBy(user1.getUserName());
    org.setCreatedDate(new Date());
    org = this.organizationdao.saveOrganization(org);
    organizationForm.setOrganization(org);
    if (org.getLocation() != null)
    {
      Location loc = this.organizationdao.getLocation("" + org.getLocation().getLocationId());
      organizationForm.setLocationId(loc.getLocationId());
      organizationForm.setLocationName(loc.getLocationName());
    }
    return org;
  }
  
  public Organization updateOrganization(OrganizationForm organizationForm, User user1)
  {
    Organization organization = this.organizationdao.getOrganization(String.valueOf(organizationForm.getOrgId()));
    organizationForm.toValue(organization, organizationForm);
    if ((organization.getIsRoot() != null) && (organization.getIsRoot().equals("Y"))) {
      organization.setParentOrg(null);
    }
    organization.setUpdatedBy(user1.getUserName());
    organization.setUpdatedDate(new Date());
    
    this.organizationdao.updateOrganization(organization);
    
    Organization org = this.organizationdao.getOrganization(String.valueOf(organization.getOrgId()));
    
    organizationForm.setOrganization(org);
    if (org.getLocation() != null)
    {
      Location loc = this.organizationdao.getLocation("" + org.getLocation().getLocationId());
      organizationForm.setLocationId(loc.getLocationId());
      organizationForm.setLocationName(loc.getLocationName());
    }
    return org;
  }
  
  public void deleteOrganization(OrganizationForm organizationForm, User user1)
    throws ValidationException, Exception
  {
    this.organizationdao.deleteOrganization(organizationForm.getOrgId());
  }
  
  public void deleteDepartment(long departmentId)
    throws ValidationException, Exception
  {
    this.organizationdao.deleteDepartment(departmentId);
  }
  
  public void deleteProjectCode(long projectId)
    throws ValidationException, Exception
  {
    this.organizationdao.deleteProjectCode(projectId);
  }
  
  public void searchorganization(User user1, OrganizationForm orgForm, int start, int range, String result, String isLegal)
  {
    orgForm.setOrgnizationTypeList(this.organizationdao.getOrganizationTypeList());
    orgForm.setLocationList(this.organizationdao.getLocationList(user1.getSuper_user_key()));
    
    List organizationList = this.organizationdao.getOrganizationsByCritera(user1, orgForm.getOrgName(), orgForm.getOrgCode(), isLegal, orgForm.getLocationId(), orgForm.getOrganizationTypeId(), start, range);
    
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(result)) {
      totaluser = this.organizationdao.getCountOfOrganizationByCritera(user1, orgForm.getOrgName(), orgForm.getOrgCode(), isLegal, orgForm.getLocationId(), orgForm.getOrganizationTypeId());
    } else {
      totaluser = new Integer(result).intValue();
    }
    logger.info("results" + totaluser);
    orgForm.setStart(String.valueOf(start));
    orgForm.setRange(String.valueOf(range));
    orgForm.setResults(String.valueOf(totaluser));
    orgForm.setOrgName(orgForm.getOrgName());
    orgForm.setOrgCode(orgForm.getOrgCode());
    orgForm.setLocationId(orgForm.getLocationId());
    orgForm.setOrganizationTypeId(orgForm.getOrganizationTypeId());
    orgForm.setIsLegal(isLegal);
    orgForm.setOrgList(organizationList);
  }
  
  public void orgselector(OrganizationForm orgForm, User user)
  {
    orgForm.setOrgnizationTypeList(this.organizationdao.getOrganizationTypeList());
    orgForm.setLocationList(this.organizationdao.getLocationList(user.getSuper_user_key()));
  }
  
  public Organization getOrganization(String id)
  {
    return this.organizationdao.getOrganization(id);
  }
  
  public Organization getOrganizationByCode(String code)
  {
    return this.organizationdao.getOrganizationByCode(code);
  }
  
  public Organization getParentOrganizationInfo(long superUserKey)
  {
    return this.organizationdao.getParentOrganizationInfo(superUserKey);
  }
  
  public List getChildOrganizations(long parentOrgId)
  {
    return this.organizationdao.getChildOrganizations(parentOrgId);
  }
  
  public List getChildOrganizationsForPagination(long superUserKey, long parentOrgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.organizationdao.getChildOrganizationsForPagination(superUserKey, parentOrgId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfChildOrganizations(long superUserKey, long parentOrgId)
  {
    return this.organizationdao.getChildOrganizationsCount(superUserKey, parentOrgId);
  }
  
  public List getAllOrganizationsWithoutParent(long parentOrgId, long superUserKey)
  {
    return this.organizationdao.getAllOrganizationsWithoutParent(parentOrgId, superUserKey);
  }
  
  public List getDeptsByOrgForPagination(long orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.organizationdao.getDeptsByOrgForPagination(orgId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfDeptsByOrg(long orgId)
  {
    return this.organizationdao.getCountOfDeptsByOrg(orgId);
  }
  
  public List<String> getJavaScriptTree(String orgId, User user)
  {
    List<String> lst = new ArrayList();
    int nodeid = 0;
    Organization parentOrg = getParentOrganizationInfo(user.getSuper_user_key());
    List orgList = getAllOrganizationsWithoutParent(parentOrg.getOrgId(), user.getSuper_user_key());
    StringBuffer strbfr = new StringBuffer();
    StringBuffer strbfrblockjs = new StringBuffer();
    strbfr.append("<script type=\"text/javascript\">");
    strbfr.append("\n");
    strbfr.append("d = new dTree('d');");
    strbfr.append("\n");
    
    String parentOrgname = parentOrg.getOrgName();
    if (!StringUtils.isNullOrEmpty(parentOrgname)) {
      parentOrgname = parentOrgname.replace("'", "\\'");
    }
    String root = "d.add(0,-1,'" + Constant.getResourceStringValue("organizations.tree", user.getLocale()) + "'" + "," + "'" + "#" + "'" + ")";
    strbfr.append(root);
    strbfr.append("\n");
    String urlroot = "org.do?method=orglistajax";
    String blcstrroot = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + urlroot + "') });" + "\n";
    strbfrblockjs.append(blcstrroot);
    String temp = "d.add(" + parentOrg.getOrgId() + ",0," + "'" + parentOrgname + "'" + "," + "'" + "#" + "'" + ");";
    strbfr.append(temp);
    nodeid++;
    String url = "org.do?method=orglistajax&orgId=" + parentOrg.getOrgId();
    String blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
    strbfrblockjs.append(blcstr);
    for (int i = 0; i < orgList.size(); i++)
    {
      Organization org = (Organization)orgList.get(i);
      
      String orgname = org.getOrgName();
      if (!StringUtils.isNullOrEmpty(orgname)) {
        orgname = orgname.replace("'", "\\'");
      }
      temp = "d.add(" + org.getOrgId() + "," + org.getParent_org_id() + "," + "'" + orgname + "'" + "," + "'" + "#" + "'" + ");";
      strbfr.append(temp);
      strbfr.append("\n");
      

      nodeid++;
      url = "org.do?method=orglistajax&orgId=" + org.getOrgId();
      blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
      strbfrblockjs.append(blcstr);
    }
    strbfr.append("document.write(d)");
    strbfr.append("</script>");
    

    lst.add(strbfr.toString());
    lst.add(strbfrblockjs.toString());
    
    return lst;
  }
  
  public List<String> getJavaScriptTreeForSystemRule(String orgId, User user)
  {
    List<String> lst = new ArrayList();
    int nodeid = 0;
    Organization parentOrg = getParentOrganizationInfo(user.getSuper_user_key());
    List orgList = getAllOrganizationsWithoutParent(parentOrg.getOrgId(), user.getSuper_user_key());
    StringBuffer strbfr = new StringBuffer();
    StringBuffer strbfrblockjs = new StringBuffer();
    strbfr.append("<script type=\"text/javascript\">");
    strbfr.append("\n");
    strbfr.append("d = new dTree('d');");
    strbfr.append("\n");
    
    String parentOrgname = parentOrg.getOrgName();
    if (!StringUtils.isNullOrEmpty(parentOrgname)) {
      parentOrgname = parentOrgname.replace("'", "\\'");
    }
    String root = "d.add(0,-1,'" + Constant.getResourceStringValue("organizations.tree", user.getLocale()) + "')";
    strbfr.append(root);
    strbfr.append("\n");
    String temp = "d.add(" + parentOrg.getOrgId() + ",0," + "'" + parentOrgname + "'" + "," + "'" + "#" + "'" + ");";
    strbfr.append(temp);
    nodeid++;
    String url = "sysrule.do?method=sysruleorglistajax&orgId=" + parentOrg.getOrgId();
    String blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
    strbfrblockjs.append(blcstr);
    for (int i = 0; i < orgList.size(); i++)
    {
      Organization org = (Organization)orgList.get(i);
      
      String orgname = org.getOrgName();
      if (!StringUtils.isNullOrEmpty(orgname)) {
        orgname = orgname.replace("'", "\\'");
      }
      temp = "d.add(" + org.getOrgId() + "," + org.getParent_org_id() + "," + "'" + orgname + "'" + "," + "'" + "#" + "'" + ");";
      strbfr.append(temp);
      strbfr.append("\n");
      

      nodeid++;
      url = "sysrule.do?method=sysruleorglistajax&orgId=" + org.getOrgId();
      blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
      strbfrblockjs.append(blcstr);
    }
    strbfr.append("document.write(d)");
    strbfr.append("</script>");
    

    lst.add(strbfr.toString());
    lst.add(strbfrblockjs.toString());
    return lst;
  }
  
  public List<String> getJavaScriptTreeForOrganizationGoals(String orgId, User user)
  {
    List<String> lst = new ArrayList();
    int nodeid = 0;
    Organization parentOrg = getParentOrganizationInfo(user.getSuper_user_key());
    List orgList = getAllOrganizationsWithoutParent(parentOrg.getOrgId(), user.getSuper_user_key());
    StringBuffer strbfr = new StringBuffer();
    StringBuffer strbfrblockjs = new StringBuffer();
    strbfr.append("<script type=\"text/javascript\">");
    strbfr.append("\n");
    strbfr.append("d = new dTree('d');");
    strbfr.append("\n");
    
    String parentOrgname = parentOrg.getOrgName();
    if (!StringUtils.isNullOrEmpty(parentOrgname)) {
      parentOrgname = parentOrgname.replace("'", "\\'");
    }
    String root = "d.add(0,-1,'" + Constant.getResourceStringValue("organizations.tree", user.getLocale()) + "')";
    strbfr.append(root);
    strbfr.append("\n");
    String temp = "d.add(" + parentOrg.getOrgId() + ",0," + "'" + parentOrgname + "'" + "," + "'" + "#" + "'" + ");";
    strbfr.append(temp);
    nodeid++;
    String url = "goal.do?method=goalorglistajax&orgId=" + parentOrg.getOrgId();
    String blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
    strbfrblockjs.append(blcstr);
    for (int i = 0; i < orgList.size(); i++)
    {
      Organization org = (Organization)orgList.get(i);
      
      String orgname = org.getOrgName();
      if (!StringUtils.isNullOrEmpty(orgname)) {
        orgname = orgname.replace("'", "\\'");
      }
      temp = "d.add(" + org.getOrgId() + "," + org.getParent_org_id() + "," + "'" + orgname + "'" + "," + "'" + "#" + "'" + ");";
      strbfr.append(temp);
      strbfr.append("\n");
      
      nodeid++;
      url = "goal.do?method=goalorglistajax&orgId=" + org.getOrgId();
      blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
      strbfrblockjs.append(blcstr);
    }
    strbfr.append("document.write(d)");
    strbfr.append("</script>");
    
    lst.add(strbfr.toString());
    lst.add(strbfrblockjs.toString());
    return lst;
  }
  
  public String getJavaScriptTreeForSelectNode(String orgId)
  {
    StringBuffer strbfr = new StringBuffer();
    if (!StringUtils.isNullOrEmpty(orgId))
    {
      strbfr.append("<script type=\"text/javascript\">");
      strbfr.append("\n");
      strbfr.append("d.openTo(" + orgId + ", true);");
      strbfr.append("\n");
      strbfr.append("</script>");
    }
    else
    {
      strbfr.append("<script type=\"text/javascript\">");
      strbfr.append("\n");
      strbfr.append("d.openTo(99999999, true);");
      strbfr.append("\n");
      strbfr.append("</script>");
    }
    return strbfr.toString();
  }
  
  public OrganizationDAO getorganizationdao()
  {
    return this.organizationdao;
  }
  
  public void setorganizationdao(OrganizationDAO organizationdao)
  {
    this.organizationdao = organizationdao;
  }
  
  public String isOrgCodeExist(String orgcode, long super_user_key)
  {
    return this.organizationdao.isOrgCodeExist(orgcode, super_user_key);
  }
  
  public String isDepartmentCodeExist(String departmentCode, long orgid)
  {
    return this.organizationdao.isDepartmentCodeExist(departmentCode, orgid);
  }
}
