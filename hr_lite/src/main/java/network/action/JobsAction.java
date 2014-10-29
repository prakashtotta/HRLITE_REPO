package network.action;

import com.bo.BOFactory;
import com.util.StringUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import network.bean.FaceBookUser;
import network.bean.FbJobApplicants;
import network.bean.FbSavedJobs;
import network.bo.FbUserBO;
import network.form.FaceBookUserForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class JobsAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(JobsAction.class);
  
  public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String searchquery = request.getParameter("search");
    List joblist = BOFactory.getFbUserBO().searchFbJobs(searchquery, 10, 0);
    FaceBookUserForm uform = (FaceBookUserForm)form;
    uform.setJobsList(joblist);
    if ((StringUtils.isNullOrEmpty(searchquery)) || (searchquery.equals("null"))) {
      uform.setSearchQuery("");
    }
    uform.setSearchQuery(searchquery);
    return mapping.findForward("search");
  }
  
  public ActionForward savedjobs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    List joblist = BOFactory.getFbUserBO().getSavedJobs(user.getUserId(), 0, 10);
    FaceBookUserForm uform = (FaceBookUserForm)form;
    uform.setJobsList(joblist);
    
    return mapping.findForward("savedjobs");
  }
  
  public ActionForward jobdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    String id = request.getParameter("id");
    String fromWhere = request.getParameter("fromWhere");
    boolean isapplied = BOFactory.getFbUserBO().isAppliedtoSameJob(user.getUserId(), new Long(id).longValue());
    request.setAttribute("jobDetailsId", id);
    request.setAttribute("fromWhere", fromWhere);
    request.setAttribute("isApplied", Boolean.valueOf(isapplied));
    
    return mapping.findForward("jobdetails");
  }
  
  public ActionForward savejob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside savejob");
    String id = request.getParameter("id");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbSavedJobs fbc = new FbSavedJobs();
    fbc.setUserId(user.getUserId());
    fbc.setJobId(new Long(id).longValue());
    BOFactory.getFbUserBO().saveFbSavedJob(fbc);
    
    return null;
  }
  
  public ActionForward applyJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside applyjob method");
    String jobdetailsId = request.getParameter("jobdetailsId");
    
    String fromWhere = request.getParameter("fromWhere");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbJobApplicants fbc = new FbJobApplicants();
    fbc.setUserId(user.getUserId());
    fbc.setJobId(new Long(jobdetailsId).longValue());
    
    BOFactory.getFbUserBO().applyJob(fbc);
    
    boolean isapplied = BOFactory.getFbUserBO().isAppliedtoSameJob(user.getUserId(), new Long(jobdetailsId).longValue());
    


    request.setAttribute("jobDetailsId", jobdetailsId);
    request.setAttribute("fromWhere", fromWhere);
    request.setAttribute("jobApplied", "yes");
    request.setAttribute("isApplied", Boolean.valueOf(isapplied));
    


    return mapping.findForward("jobdetails");
  }
}
