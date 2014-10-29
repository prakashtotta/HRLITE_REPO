package com.action;

import com.bean.Permissions;
import com.bean.Role;
import com.bean.User;
import com.bean.lov.KeyValue;
import com.bean.lov.KeyValueComparator;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.RoleBO;
import com.dao.LovOpsDAO;
import com.form.RoleForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RoleAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(RoleAction.class);
  
  public ActionForward createRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    RoleForm roleForm = (RoleForm)form;
    List permissionList = BOFactory.getLovBO().getAllPermissions();
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnListCreate(permissionList, fromColumnsList, toColumnsList, user1, "ALL_ROLE_PERMISSIONS");
    Collections.sort(fromColumnsList, new KeyValueComparator());
    roleForm.setFromColumnsList(fromColumnsList);
    roleForm.setToColumnsList(toColumnsList);
    return mapping.findForward("createRole");
  }
  
  public ActionForward saveRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveRole method");
    
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    List permissionList = BOFactory.getLovBO().getAllPermissions();
    RoleForm roleForm = (RoleForm)form;
    String[] selectedpermission = null;
    String totaldata = request.getParameter("todata");
    if ((totaldata != null) && (totaldata.length() > 0))
    {
      String[] column = StringUtils.tokenize(totaldata, ",");
      selectedpermission = new String[column.length];
      for (int i = 0; i < column.length; i++) {
        selectedpermission[i] = column[i];
      }
    }
    roleForm.setPermission(selectedpermission);
    
    Role role = new Role();
    toValue(role, roleForm, permissionList);
    role.setCreatedBy(user.getUserName());
    role.setCreatedDate(new Date());
    role.setUpdatedBy(user.getUserName());
    role.setUpdatedDate(new Date());
    role.setStatus("A");
    role.setSuper_user_key(user.getSuper_user_key());
    boolean isError = false;
    String rolecode = BOFactory.getRoleBO().isRoleCodeExist(roleForm.getRoleCode(), user.getSuper_user_key());
    if (rolecode != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.rolecode.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      LovOpsDAO.saveUpdateRole(role);
      String readpreview = request.getParameter("readPreview");
      roleForm.setReadPreview(readpreview);
      roleForm.setRoleId(role.getRoleId());
      request.setAttribute("updateRole", "yes");
    }
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    if (totaldata.length() > 0) {
      setFromToCoulmnList(permissionList, fromColumnsList, toColumnsList, user, "ALL_ROLE_PERMISSIONS", selectedpermission);
    } else {
      setFromToCoulmnListCreate(permissionList, fromColumnsList, toColumnsList, user, "ALL_ROLE_PERMISSIONS");
    }
    Collections.sort(fromColumnsList, new KeyValueComparator());
    Collections.sort(toColumnsList, new KeyValueComparator());
    roleForm.setFromColumnsList(fromColumnsList);
    roleForm.setToColumnsList(toColumnsList);
    
    return mapping.findForward("createRole");
  }
  
  public ActionForward updateRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateRole method");
    
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    List permissionList = BOFactory.getLovBO().getAllPermissions();
    RoleForm roleForm = (RoleForm)form;
    String[] selectedpermission = null;
    String totaldata = request.getParameter("todata");
    String roleId = request.getParameter("roleid");
    if (totaldata.length() > 0)
    {
      String[] column = StringUtils.tokenize(totaldata, ",");
      selectedpermission = new String[column.length];
      for (int i = 0; i < column.length; i++) {
        selectedpermission[i] = column[i];
      }
    }
    roleForm.setPermission(selectedpermission);
    Role role = LovOpsDAO.getRole(roleId);
    role.setUpdatedBy(user.getUserName());
    role.setUpdatedDate(new Date());
    role.setStatus("A");
    toValue(role, roleForm, permissionList);
    boolean isError = false;
    String rolecode = BOFactory.getRoleBO().isRoleCodeExist(roleForm.getRoleCode(), user.getSuper_user_key());
    if ((rolecode != null) && (!rolecode.equals(role.getRoleCode())))
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.rolecode.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      role = LovOpsDAO.uodateRole(role);
      String readpreview = request.getParameter("readPreview");
      roleForm.setReadPreview(readpreview);
      
      request.setAttribute("updateRole", "yes");
    }
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    if (totaldata.length() > 0) {
      setFromToCoulmnList(permissionList, fromColumnsList, toColumnsList, user, "ALL_ROLE_PERMISSIONS", selectedpermission);
    } else {
      setFromToCoulmnListCreate(permissionList, fromColumnsList, toColumnsList, user, "ALL_ROLE_PERMISSIONS");
    }
    Collections.sort(fromColumnsList, new KeyValueComparator());
    Collections.sort(toColumnsList, new KeyValueComparator());
    roleForm.setFromColumnsList(fromColumnsList);
    roleForm.setToColumnsList(toColumnsList);
    roleForm.setRoleId(role.getRoleId());
    return mapping.findForward("createRole");
  }
  
  public ActionForward editrole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editrole method");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String roleId = request.getParameter("roleid");
    RoleForm roleForm = (RoleForm)form;
    Role role = LovOpsDAO.getRole(roleId);
    Set permissions = role.getPermissions();
    
    String[] selectedpermission = null;
    if (permissions.size() > 0)
    {
      selectedpermission = new String[permissions.size()];
      Iterator itr = permissions.iterator();
      int i = 0;
      while (itr.hasNext())
      {
        Permissions permission = (Permissions)itr.next();
        selectedpermission[i] = permission.getPerCode();
        i++;
      }
    }
    List permissionList = BOFactory.getLovBO().getAllPermissions();
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    if (permissions.size() > 0) {
      setFromToCoulmnList(permissionList, fromColumnsList, toColumnsList, user, "ALL_ROLE_PERMISSIONS", selectedpermission);
    } else {
      setFromToCoulmnListCreate(permissionList, fromColumnsList, toColumnsList, user, "ALL_ROLE_PERMISSIONS");
    }
    Collections.sort(fromColumnsList, new KeyValueComparator());
    Collections.sort(toColumnsList, new KeyValueComparator());
    roleForm.setFromColumnsList(fromColumnsList);
    roleForm.setToColumnsList(toColumnsList);
    toFormEdit(roleForm, role);
    
    return mapping.findForward("editrole");
  }
  
  public ActionForward DeleteRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside DeleteRole method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RoleForm RoleForm = (RoleForm)form;
    
    String id = request.getParameter("id");
    Role Role = LovOpsDAO.getRole(id);
    toDelete(Role, RoleForm);
    
    Role = LovOpsDAO.uodateRole(Role);
    request.setAttribute("deleteRole", "yes");
    return mapping.findForward("");
  }
  
  public void toDelete(Role Role, RoleForm RoleForm)
  {
    Role.setStatus("D");
  }
  
  public ActionForward roledelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleterole method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String roleId = request.getParameter("id");
    Role role = new Role();
    role.setRoleId(new Long(roleId).longValue());
    Role roleold = LovOpsDAO.getRole(roleId);
    
    role.setUsers(roleold.getUsers());
    LovOpsDAO.deleteRole(role);
    return mapping.findForward("roleList");
  }
  
  public Role toValue(Role role, RoleForm roleForm, List permissionList)
  {
    role.setRoleCode(roleForm.getRoleCode());
    role.setRoleName(roleForm.getRoleName());
    role.setRoleDesc(roleForm.getRoleDesc());
    
    Map m = new HashMap();
    for (int i = 0; i < permissionList.size(); i++)
    {
      Permissions per = (Permissions)permissionList.get(i);
      m.put(per.getPerCode(), per);
    }
    logger.info("roleForm.getPermission()" + roleForm.getPermission());
    if ((roleForm.getPermission() != null) && (roleForm.getPermission().length > 0))
    {
      Set permissionset = new HashSet();
      for (int i = 0; i < roleForm.getPermission().length; i++)
      {
        Permissions per = new Permissions();
        
        Permissions perm = (Permissions)m.get(roleForm.getPermission()[i]);
        if (perm != null)
        {
          per.setPerId(perm.getPerId());
          permissionset.add(per);
        }
      }
      role.setPermissions(permissionset);
    }
    else
    {
      Set emptypermissionset = new HashSet();
      role.setPermissions(emptypermissionset);
    }
    return role;
  }
  
  public ActionForward rolelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside rolelist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("roleList");
  }
  
  public ActionForward roledetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside roledetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String roleId = request.getParameter("roleid");
    
    RoleForm roleForm = (RoleForm)form;
    
    Role role = LovOpsDAO.getRole(roleId);
    
    toForm(roleForm, role);
    
    return mapping.findForward("roledetails");
  }
  
  public void toForm(RoleForm roleForm, Role role)
  {
    roleForm.setRoleId(role.getRoleId());
    roleForm.setRoleCode(role.getRoleCode());
    roleForm.setRoleName(role.getRoleName());
    roleForm.setRoleDesc(role.getRoleDesc());
    
    Iterator itr = role.getPermissions().iterator();
    List lst = new ArrayList();
    while (itr.hasNext())
    {
      Permissions per = (Permissions)itr.next();
      lst.add(per);
    }
    roleForm.setPermissionsList(lst);
    
    roleForm.setCreatedBy(role.getCreatedBy());
    roleForm.setCreatedDate(role.getCreatedDate());
    roleForm.setUpdatedBy(role.getUpdatedBy());
    roleForm.setUpdatedDate(role.getUpdatedDate());
  }
  
  public void toFormEdit(RoleForm roleForm, Role role)
  {
    roleForm.setRoleId(role.getRoleId());
    roleForm.setRoleCode(role.getRoleCode());
    roleForm.setRoleName(role.getRoleName());
    roleForm.setRoleDesc(role.getRoleDesc());
    
    Iterator itr = role.getPermissions().iterator();
    List lst = new ArrayList();
    while (itr.hasNext())
    {
      Permissions per = (Permissions)itr.next();
      lst.add(Long.valueOf(per.getPerId()));
    }
    String[] per = new String[lst.size()];
    for (int i = 0; i < lst.size(); i++)
    {
      System.out.println("per[i]" + per[i]);
      per[i] = String.valueOf(lst.get(i));
    }
    roleForm.setPermission(per);
    roleForm.setPermissionsList(BOFactory.getLovBO().getAllPermissions());
    
    roleForm.setCreatedBy(role.getCreatedBy());
    roleForm.setCreatedDate(role.getCreatedDate());
    roleForm.setUpdatedBy(role.getUpdatedBy());
    roleForm.setUpdatedDate(role.getUpdatedDate());
  }
  
  private void setFromToCoulmnList(List permission, List fromColumnsList, List toColumnsList, User user1, String screenName, String[] selectedpermission)
  {
    String[] selectedid = null;
    if (selectedpermission.length > 0)
    {
      selectedid = new String[selectedpermission.length];
      for (int j = 0; j < selectedpermission.length; j++)
      {
        String resoucekeyidselected = "admin.security.roles.createrole." + selectedpermission[j];
        KeyValue kid = new KeyValue();
        selectedid[j] = selectedpermission[j];
        
        kid.setKey(selectedpermission[j]);
        kid.setValue(Constant.getResourceStringValue(resoucekeyidselected, user1.getLocale()));
        toColumnsList.add(kid);
      }
    }
    for (int i = 0; i < permission.size(); i++)
    {
      Permissions per = (Permissions)permission.get(i);
      String resoucekey = "admin.security.roles.createrole." + per.getPerCode();
      String yes = "no";
      KeyValue kid1 = new KeyValue();
      if (selectedid.length > 0) {
        for (int m = 0; m < selectedid.length; m++)
        {
          String jjjj = selectedid[m];
          if (StringUtils.compare(per.getPerCode(), String.valueOf(jjjj))) {
            yes = "true";
          }
        }
      }
      if (!StringUtils.compare(yes, "true"))
      {
        kid1.setKey(per.getPerCode());
        kid1.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        fromColumnsList.add(kid1);
      }
    }
  }
  
  private void setFromToCoulmnListCreate(List permission, List fromColumnsList, List toColumnsList, User user1, String screenName)
  {
    for (int i = 0; i < permission.size(); i++)
    {
      Permissions per = (Permissions)permission.get(i);
      String resoucekey = "admin.security.roles.createrole." + per.getPerCode();
      
      KeyValue k = new KeyValue();
      k.setKey(per.getPerCode());
      k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
      fromColumnsList.add(k);
    }
  }
}
