package com.action;

import com.bo.BOFactory;
import com.bo.TreeBO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.jscontrolstags.server.treeview.BaseTreeViewAction;
import net.sourceforge.jscontrolstags.server.treeview.TreeNode;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AjaxAgencyTreeForReqAction
  extends BaseTreeViewAction
{
  protected static final Logger logger = Logger.getLogger(AjaxAgencyTreeForReqAction.class);
  
  public void onOpenAjax(String treeViewId, String treeNodeId, TreeNode treeNodeRoot, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    BOFactory.getTreeBO().ajaxAgencyTree(treeViewId, treeNodeId, treeNodeRoot, mapping, form, request, response);
  }
  
  public String onEditAjax(String treeViewId, String treeNodeId, String oldValue, String newValue, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    return newValue;
  }
  
  public String onDropAjax(String treeViewDragId, String treeViewDropId, String treeNodeDragId, String s, String s1, ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
  {
    return null;
  }
}
