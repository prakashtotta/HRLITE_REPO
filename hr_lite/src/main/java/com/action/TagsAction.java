package com.action;

import com.bean.Tags;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.TagsForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TagsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(BudgetCodeAction.class);
  
  public ActionForward taglist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    TagsForm tagForm = (TagsForm)form;
    request.setAttribute("searchpage", "no");
    return mapping.findForward("tagListPage");
  }
  
  public ActionForward searchTagsListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchTagsListpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    TagsForm tagForm = (TagsForm)form;
    tagForm.setTagName(tagForm.getTagName());
    tagForm.setTagType(tagForm.getTagType());
    request.setAttribute("searchpage", "yes");
    return mapping.findForward("tagListPage");
  }
  
  public ActionForward createtags(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    TagsForm tagForm = (TagsForm)form;
    tagForm.setTagtypeList(Constant.getTagTypeList(user1));
    return mapping.findForward("createtags");
  }
  
  public ActionForward saveTags(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveTags method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    TagsForm tagForm = (TagsForm)form;
    boolean isTagExist = BOFactory.getLovBO().isTagExist(tagForm.getTagName(), user1.getSuper_user_key()).booleanValue();
    if (!isTagExist)
    {
      Tags tag = new Tags();
      toValue(tag, tagForm);
      tag.setSuper_user_key(user1.getSuper_user_key());
      BOFactory.getLovBO().saveTags(tag);
      tagForm.setTagId(tag.getTagId());
      tagForm.setTagtypeList(Constant.getTagTypeList(user1));
      request.setAttribute("saveTags", "yes");
    }
    else
    {
      request.setAttribute("tagExisted", "yes");
    }
    return mapping.findForward("createtags");
  }
  
  public ActionForward updateTags(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateTags method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    TagsForm tagForm = (TagsForm)form;
    String id = request.getParameter("id");
    boolean isTagExist = BOFactory.getLovBO().isTagExist(new Long(id).longValue(), tagForm.getTagName(), user1.getSuper_user_key()).booleanValue();
    if (!isTagExist)
    {
      Tags tag = BOFactory.getLovBO().getTagsDetails(id);
      toValue(tag, tagForm);
      tag = BOFactory.getLovBO().updateTags(tag);
      tagForm.setTagId(tag.getTagId());
      tagForm.setTagtypeList(Constant.getTagTypeList(user1));
      request.setAttribute("updateTags", "yes");
    }
    else
    {
      tagForm.setTagId(new Long(id).longValue());
      request.setAttribute("tagExisted", "yes");
    }
    return mapping.findForward("createtags");
  }
  
  public ActionForward tagsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside tagsDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String tagId = request.getParameter("tagid");
    TagsForm tagForm = (TagsForm)form;
    Tags tag = BOFactory.getLovBO().getTagsDetails(tagId);
    tagForm.setTagtypeList(Constant.getTagTypeList(user1));
    toForm(tagForm, tag);
    return mapping.findForward("createtags");
  }
  
  public ActionForward deleteTags(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteTags method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TagsForm tagForm = (TagsForm)form;
    String tagId = request.getParameter("tagid");
    Tags tag = BOFactory.getLovBO().getTagsDetails(tagId);
    toDelete(tag, tagForm);
    tag = BOFactory.getLovBO().updateTags(tag);
    request.setAttribute("deleteTags", "yes");
    return mapping.findForward("createtags");
  }
  
  public void toForm(TagsForm tagForm, Tags tag)
  {
    tagForm.setTagId(tag.getTagId());
    tagForm.setTagName(tag.getTagName());
    tagForm.setTagType(tag.getTagType());
  }
  
  public void toValue(Tags tag, TagsForm tagForm)
  {
    tag.setTagName(tagForm.getTagName());
    tag.setTagType(tagForm.getTagType());
    tag.setStatus("A");
  }
  
  public void toDelete(Tags tag, TagsForm tagForm)
  {
    tag.setStatus("D");
  }
}
