package com.action;

import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.LovTXBO;
import com.form.LovForm;
import com.util.EmailUtil;
import com.util.StringUtils;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LovNoLoginAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(LovNoLoginAction.class);
  
  public ActionForward loadDeptlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    
    System.out.println("orgId" + orgId);
    LovForm tmplForm = (LovForm)form;
    
    tmplForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    
    return mapping.findForward("loadDeptlist");
  }
  
  public ActionForward saveDemo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String company = request.getParameter("company");
    if (!StringUtils.isNullOrEmpty(email))
    {
      BOFactory.getLovTXBO().SaveDemoInterest(name, email, phone, company);
      String[] to = { "sales@hires360.com", "www.hires360.com@gmail.com" };
      
      String body = phone + " " + company;
      EmailUtil em = new EmailUtil();
      em.sendMessage(email, to, null, null, email, "Request for Demo", body, body, null, 0);
    }
    return null;
  }
  
  public ActionForward loadProjectCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String departmentId = request.getParameter("departmentId");
    
    LovForm tmplForm = (LovForm)form;
    tmplForm.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(departmentId));
    
    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward loadState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside loadState method .... lovnologin ...");
    String cid = request.getParameter("cid");
    System.out.println("cid" + cid);
    LovForm tmplForm = (LovForm)form;
    
    tmplForm.setStateList(BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue()));
    
    return mapping.findForward("stateList");
  }
}
