package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.jscontrolstags.server.treeview.TreeNode;
import net.sourceforge.jscontrolstags.server.treeview.TreeView;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

public class DynamicTreeviewAction
  extends DispatchAction
{
  public ActionForward load(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    TreeView dynamicTreeview = new TreeView();
    TreeNode root1 = new TreeNode("root1", getResources(request).getMessage("treeview.treeview-view.root.title"));
    root1.setImg("base.gif");
    dynamicTreeview.addTreeNode(root1);
    TreeNode child1 = new TreeNode("child1", "The Object");
    child1.setImg("page.gif");
    root1.addTreeNode(child1);
    TreeNode child1_1 = new TreeNode("child1_1", "Methods object");
    child1.addTreeNode(child1_1);
    TreeNode child1_2 = new TreeNode("child1_2", "Properties object");
    child1.addTreeNode(child1_2);
    TreeNode child1_3 = new TreeNode("child1_3", "Custom functions (eg : onclick)");
    child1_3.setOnclick("function(node){alert('Here text of this node : \\n' + node.struct.txt);}");
    child1.addTreeNode(child1_3);
    String anyHTMLContent = "<div><h3>On peut même mettre de l'HTML</h3><p>Y'a rien besoin de spécial, juste avoir :</p><ol><li>des notions du HTML</li><li>de l'imagination</li><li>Echapper les simples quotes (\\' au lieu de ')</li></ol></div>";
    


    TreeNode child1_4 = new TreeNode("child1_4", anyHTMLContent);
    child1.addTreeNode(child1_4);
    TreeNode child1_5 = new TreeNode("child1_5", "Here, there is a custom CSS class.");
    child1_5.setStyle("special");
    child1.addTreeNode(child1_5);
    TreeNode child1_6 = new TreeNode("child1_6", "Drag me!!! This node is draggable.");
    child1_6.setDraggable(Boolean.TRUE);
    child1.addTreeNode(child1_6);
    TreeNode child1_7 = new TreeNode("child1_7", "Drag me with AJAX!!! This node is draggable.");
    child1_7.setDropAjax(Boolean.TRUE);
    child1.addTreeNode(child1_7);
    TreeNode child2 = new TreeNode("child2", "Events");
    child2.setImg("page.gif");
    root1.addTreeNode(child2);
    TreeNode child2_1 = new TreeNode("child2_1", "This node manage mouseover and mouseout event.");
    child2_1.setOnmouseover("function(branch){branch.addClass('mover');}");
    child2_1.setOnmouseout("function(branch){branch.removeClass('mover');}");
    child2.addTreeNode(child2_1);
    TreeNode child2_2 = new TreeNode("child2_1", "Double click me !!! This node manage double click.");
    child2_2.setOndblclick("function(branch){alert('Event double click on branch ' + branch.getId());}");
    child2.addTreeNode(child2_2);
    TreeNode ajaxNode = new TreeNode("ajaxNode", "Dynamic node opened with AJAX.");
    ajaxNode.setOpenAjax(Boolean.TRUE);
    child2.addTreeNode(ajaxNode);
    TreeNode child2_3 = new TreeNode("child2_3", "Edit me!!! This node can be editable and changed.");
    child2_3.setEditable(Boolean.TRUE);
    child2.addTreeNode(child2_3);
    TreeNode child2_4 = new TreeNode("child2_4", "Edit me with AJAX!!! This node can be editable and changed.");
    child2_4.setEditAjax(Boolean.TRUE);
    child2.addTreeNode(child2_4);
    TreeNode child3 = new TreeNode("child2_4", "Trashcan");
    child3.setImg("trash.gif");
    child3.setImgopen("trashfull.gif");
    child3.setImgclose("trashfull.gif");
    child3.setDraggable(Boolean.FALSE);
    child3.setLast(Boolean.TRUE);
    root1.addTreeNode(child3);
    request.setAttribute("dynamicTreeview", dynamicTreeview);
    return mapping.findForward("display");
  }
}
