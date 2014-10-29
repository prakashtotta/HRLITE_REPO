package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.jscontrolstags.server.treeview.BaseTreeViewAction;
import net.sourceforge.jscontrolstags.server.treeview.TreeNode;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AjaxTreeviewAction
  extends BaseTreeViewAction
{
  public void onOpenAjax(String treeViewId, String treeNodeId, TreeNode treeNodeRoot, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (treeNodeId.startsWith("ajaxNode"))
    {
      TreeNode treeNodeDraggable = new TreeNode("treeNodeDraggable", "Tree Node Draggable");
      treeNodeDraggable.setDraggable(Boolean.TRUE);
      treeNodeRoot.addTreeNode(treeNodeDraggable);
      TreeNode treeNodeEditable = new TreeNode("treeNodeDraggable", "Tree Node Editable");
      treeNodeEditable.setEditable(Boolean.TRUE);
      treeNodeRoot.addTreeNode(treeNodeEditable);
    }
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
