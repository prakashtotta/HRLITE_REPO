package com.action;

import com.bean.User;
import com.bean.lov.Category;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovDAO;
import com.form.CatagoryForm;
import com.resources.Constant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CatagoryAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(BudgetCodeAction.class);
  
  public ActionForward catagorylist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    CatagoryForm catgForm = (CatagoryForm)form;
    request.setAttribute("searchpageCatagory", "no");
    return mapping.findForward("catagoryPage");
  }
  
  public ActionForward searchCatagoryListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchCatagoryListpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CatagoryForm catgForm = (CatagoryForm)form;
    catgForm.setCatName(catgForm.getCatName());
    request.setAttribute("searchpageCatagory", "yes");
    return mapping.findForward("catagoryPage");
  }
  
  public ActionForward createCatagory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    CatagoryForm catgForm = (CatagoryForm)form;
    
    return mapping.findForward("createcatagory");
  }
  
  public ActionForward saveCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveCategory method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    CatagoryForm catgForm = (CatagoryForm)form;
    Category catgory = new Category();
    toValue(catgory, catgForm);
    catgory.setCreatedBy(user1.getUserName());
    catgory.setCreatedDate(new Date());
    catgory.setSuper_user_key(user1.getSuper_user_key());
    List allcatagoryList = new ArrayList();
    Set catAllName = new HashSet();
    allcatagoryList = LovDAO.getAllCatagory();
    for (int i = 0; i < allcatagoryList.size(); i++)
    {
      Category cat = (Category)allcatagoryList.get(i);
      catAllName.add(cat.getCatName());
    }
    if (catAllName.contains(catgForm.getCatName()))
    {
      request.setAttribute("duplicateCatName", "yes");
    }
    else
    {
      LovDAO.saveCategory(catgory);
      catgForm.setCatId(catgory.getCatId());
      request.setAttribute("saveCategory", "yes");
    }
    return mapping.findForward("createcatagory");
  }
  
  public ActionForward updateCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateCategory method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    CatagoryForm catgForm = (CatagoryForm)form;
    String id = request.getParameter("catid");
    Category catgory = LovDAO.getCategoryDetails(id);
    String name2 = catgory.getCatName();
    toValue(catgory, catgForm);
    
    List allcatagoryList = new ArrayList();
    Set catAllName = new HashSet();
    allcatagoryList = LovDAO.getAllCatagory();
    for (int i = 0; i < allcatagoryList.size(); i++)
    {
      Category cat = (Category)allcatagoryList.get(i);
      String name1 = cat.getCatName();
      if (!name1.equals(name2)) {
        catAllName.add(cat.getCatName());
      }
    }
    if (catAllName.contains(catgForm.getCatName()))
    {
      request.setAttribute("duplicateCatName", "yes");
      catgForm.setCatId(catgory.getCatId());
    }
    else
    {
      catgory = LovDAO.updateCategory(catgory);
      catgForm.setCatId(catgory.getCatId());
      
      request.setAttribute("updateCategory", "yes");
    }
    return mapping.findForward("createcatagory");
  }
  
  public ActionForward categoryDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside categoryDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String catId = request.getParameter("catagoryid");
    CatagoryForm catgForm = (CatagoryForm)form;
    Category catgory = LovDAO.getCategoryDetails(catId);
    
    toForm(catgForm, catgory);
    return mapping.findForward("createcatagory");
  }
  
  public ActionForward deleteCatagory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteCatagory method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CatagoryForm catgForm = (CatagoryForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String catId = request.getParameter("catid");
    try
    {
      BOFactory.getLovBO().deleteCategory(new Long(catId).longValue());
      

      request.setAttribute("deleteCategory", "yes");
      return mapping.findForward("createcatagory");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public void toValue(Category catgory, CatagoryForm catgForm)
  {
    catgory.setCatName(catgForm.getCatName());
    catgory.setCatDesc(catgForm.getCatDesc());
    catgory.setStatus("A");
  }
  
  public void toForm(CatagoryForm catgForm, Category catgory)
  {
    catgForm.setCatId(catgory.getCatId());
    catgForm.setCatName(catgory.getCatName());
    catgForm.setCatDesc(catgory.getCatDesc());
  }
  
  public void toDelete(Category catgory, CatagoryForm catgForm)
  {
    catgory.setStatus("D");
  }
}
