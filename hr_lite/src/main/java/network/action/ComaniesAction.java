package network.action;

import com.bo.BOFactory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import network.bean.FaceBookUser;
import network.bean.FbSavedCompanies;
import network.bo.FbUserBO;
import network.form.FaceBookUserForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ComaniesAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(ComaniesAction.class);
  
  public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside home");
    return mapping.findForward("home");
  }
  
  public ActionForward savecompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside savecompany");
    String fid = request.getParameter("fid");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbSavedCompanies fbc = new FbSavedCompanies();
    fbc.setUserId(user.getUserId());
    fbc.setComFacebookid(fid);
    BOFactory.getFbUserBO().saveFbSavedCompanies(fbc);
    
    return null;
  }
  
  public ActionForward deletecompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deletecompany");
    String fid = request.getParameter("fid");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    BOFactory.getFbUserBO().deleteSavedCompany(user.getUserId(), fid);
    
    return null;
  }
  
  public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String searchquery = request.getParameter("search");
    List ulist = BOFactory.getFbUserBO().searchCompanies(searchquery, 15, 0);
    FaceBookUserForm uform = (FaceBookUserForm)form;
    uform.setCompaniesList(ulist);
    uform.setSearchQuery(searchquery);
    return mapping.findForward("search");
  }
}
