package network.action;

import com.bo.BOFactory;
import com.bo.LovBO;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import network.bean.FaceBookUser;
import network.bean.FbJobApplicants;
import network.bean.FbJobs;
import network.bo.FbUserBO;
import network.form.FbJobsForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class FbJobsAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(FbJobsAction.class);
  
  public ActionForward postJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside postJob method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbJobsForm uform = (FbJobsForm)form;
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    uform.setCountryList(countryList);
    

    List stateList = new ArrayList();
    uform.setStateList(stateList);
    
    List jobCategoryList = BOFactory.getLovBO().getJobCategoryList(new Long(0L).longValue());
    uform.setJobCategoryList(jobCategoryList);
    
    List fbexpList = Constant.getFbJobExperienceList();
    uform.setFbexpList(fbexpList);
    
    List fbtenureList = Constant.getFbJobTenureList();
    uform.setFbtenureList(fbtenureList);
    

    return mapping.findForward("postJob");
  }
  
  public ActionForward savePostJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside savePostJob method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbJobsForm uform = (FbJobsForm)form;
    String vetarans = request.getParameter("veterans");
    String refcode = request.getParameter("refcode");
    String applyauto = request.getParameter("applyauto");
    String applyext = request.getParameter("applyext");
    String desc = request.getParameter("desc");
    logger.info("desc >> " + desc);
    



    logger.info("" + uform.getJobTitle());
    logger.info("" + uform.getHeadline());
    logger.info("" + uform.getCompanyName());
    logger.info("" + uform.getCity());
    logger.info("" + uform.getCountryId());
    logger.info("" + uform.getStateId());
    logger.info("" + uform.getJobCategoryId());
    logger.info("" + uform.getExperience());
    logger.info("" + uform.getTenure());
    logger.info("" + uform.getPerks());
    


    FbJobs fbJobs = new FbJobs();
    fbJobs.setJobTitle(uform.getJobTitle());
    fbJobs.setHeadline(uform.getHeadline());
    fbJobs.setCompanyName(uform.getCompanyName());
    fbJobs.setCity(uform.getCity());
    fbJobs.setPostalcode(uform.getPostalcode());
    fbJobs.setCreatedBy(user.getUserId());
    fbJobs.setCreatedDate(new Date());
    fbJobs.setCountry(uform.getCountry());
    fbJobs.setState(uform.getState());
    fbJobs.setJobcategory(uform.getJobcategory());
    
    fbJobs.setExperience(uform.getExperience());
    fbJobs.setTenure(uform.getTenure());
    fbJobs.setPerks(uform.getPerks());
    fbJobs.setVeterans(vetarans);
    if ((!StringUtils.isNullOrEmpty(refcode)) && (refcode.equals("Y"))) {
      fbJobs.setReferencecode(uform.getReferencecode());
    }
    fbJobs.setApplyauto(applyauto);
    if ((!StringUtils.isNullOrEmpty(applyext)) && (applyext.equals("Y")))
    {
      fbJobs.setAppyurl(uform.getAppyurl());
      fbJobs.setAppyemail(uform.getAppyemail());
    }
    fbJobs.setDescription(desc);
    
    BOFactory.getFbUserBO().savePostJob(fbJobs);
    


    List countryList = BOFactory.getLovBO().getAllCountries();
    uform.setCountryList(countryList);
    



    List jobCategoryList = BOFactory.getLovBO().getJobCategoryList(new Long(0L).longValue());
    uform.setJobCategoryList(jobCategoryList);
    
    List fbexpList = Constant.getFbJobExperienceList();
    uform.setFbexpList(fbexpList);
    
    List fbtenureList = Constant.getFbJobTenureList();
    uform.setFbtenureList(fbtenureList);
    request.setAttribute("jobposted", "yes");
    return mapping.findForward("postJob");
  }
  
  public ActionForward previewPostJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside previewPostJob method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbJobsForm uform = (FbJobsForm)form;
    String vetarans = request.getParameter("veterans");
    String refcode = request.getParameter("refcode");
    String applyauto = request.getParameter("applyauto");
    String applyext = request.getParameter("applyext");
    String desc = request.getParameter("desc");
    logger.info("desc >> " + desc);
    



    logger.info("" + uform.getJobTitle());
    logger.info("" + uform.getHeadline());
    logger.info("" + uform.getCompanyName());
    logger.info("" + uform.getCity());
    logger.info("" + uform.getCountryId());
    logger.info("" + uform.getStateId());
    logger.info("" + uform.getJobCategoryId());
    logger.info("" + uform.getExperience());
    logger.info("" + uform.getTenure());
    logger.info("" + uform.getPerks());
    

    uform.setJobTitle(uform.getJobTitle());
    uform.setHeadline(uform.getHeadline());
    uform.setCompanyName(uform.getCompanyName());
    uform.setCity(uform.getCity());
    uform.setPostalcode(uform.getPostalcode());
    uform.setCreatedBy(user.getUserId());
    uform.setCreatedDate(new Date());
    uform.setCountry(uform.getCountry());
    uform.setState(uform.getState());
    uform.setJobcategory(uform.getJobcategory());
    
    uform.setExperience(uform.getExperience());
    uform.setTenure(uform.getTenure());
    uform.setPerks(uform.getPerks());
    uform.setVeterans(vetarans);
    if ((!StringUtils.isNullOrEmpty(refcode)) && (refcode.equals("Y"))) {
      uform.setReferencecode(uform.getReferencecode());
    }
    uform.setApplyauto(applyauto);
    if ((!StringUtils.isNullOrEmpty(applyext)) && (applyext.equals("Y")))
    {
      uform.setAppyurl(uform.getAppyurl());
      uform.setAppyemail(uform.getAppyemail());
    }
    uform.setDescription(desc);
    
    request.setAttribute("readPreview", "2");
    
    return mapping.findForward("postJob");
  }
  
  public ActionForward editPostJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editPostJob method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbJobsForm uform = (FbJobsForm)form;
    String vetarans = request.getParameter("veterans");
    String refcode = request.getParameter("refcode");
    String applyauto = request.getParameter("applyauto");
    String applyext = request.getParameter("applyext");
    String desc = request.getParameter("desc");
    logger.info("desc >> " + desc);
    



    logger.info("uform.getJobTitle() > " + uform.getJobTitle());
    logger.info("uform.getHeadline() > " + uform.getHeadline());
    logger.info("uform.getCompanyName() > " + uform.getCompanyName());
    logger.info("uform.getCity() > " + uform.getCity());
    logger.info("uform.getCountry() > " + uform.getCountry());
    logger.info("uform.getState() > " + uform.getState());
    logger.info("uform.getJobcategory() > " + uform.getJobcategory());
    logger.info("uform.getExperience() >" + uform.getExperience());
    logger.info("uform.getTenure() >" + uform.getTenure());
    logger.info("uform.getPerks() >" + uform.getPerks());
    

    uform.setJobTitle(uform.getJobTitle());
    uform.setHeadline(uform.getHeadline());
    uform.setCompanyName(uform.getCompanyName());
    uform.setCity(uform.getCity());
    uform.setPostalcode(uform.getPostalcode());
    uform.setCreatedBy(user.getUserId());
    uform.setCreatedDate(new Date());
    uform.setCountry(uform.getCountry());
    uform.setState(uform.getState());
    uform.setJobcategory(uform.getJobcategory());
    
    uform.setExperience(uform.getExperience());
    uform.setTenure(uform.getTenure());
    uform.setPerks(uform.getPerks());
    uform.setVeterans(vetarans);
    if ((!StringUtils.isNullOrEmpty(refcode)) && (refcode.equals("Y"))) {
      uform.setReferencecode(uform.getReferencecode());
    }
    uform.setApplyauto(applyauto);
    if ((!StringUtils.isNullOrEmpty(applyext)) && (applyext.equals("Y")))
    {
      uform.setAppyurl(uform.getAppyurl());
      uform.setAppyemail(uform.getAppyemail());
    }
    uform.setDescription(desc);
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    uform.setCountryList(countryList);
    

    List jobCategoryList = BOFactory.getLovBO().getJobCategoryList(new Long(0L).longValue());
    uform.setJobCategoryList(jobCategoryList);
    
    List fbexpList = Constant.getFbJobExperienceList();
    uform.setFbexpList(fbexpList);
    
    List fbtenureList = Constant.getFbJobTenureList();
    uform.setFbtenureList(fbtenureList);
    logger.info("end of method");
    
    return mapping.findForward("postJob");
  }
  
  public ActionForward manageJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside manageJob method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbJobsForm uform = (FbJobsForm)form;
    
    List joblist = BOFactory.getFbUserBO().allFbJobsList();
    uform.setJobsList(joblist);
    

    return mapping.findForward("manageJob");
  }
  
  public ActionForward getApplicantsByJobId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside getApplicantsByJobId method");
    FbJobsForm uform = (FbJobsForm)form;
    String jobId = request.getParameter("jobId");
    List applicantlist = BOFactory.getFbUserBO().getApplicantListByJobId(new Long(jobId).longValue());
    logger.info("applicantlist size >>" + applicantlist.size());
    List facebookUserList = new ArrayList();
    for (int i = 0; i < applicantlist.size(); i++)
    {
      FbJobApplicants applicant = (FbJobApplicants)applicantlist.get(i);
      FaceBookUser faceBookUser = BOFactory.getFbUserBO().getFbUser(applicant.getUserId());
      facebookUserList.add(faceBookUser);
    }
    uform.setApplicantList(facebookUserList);
    logger.info("again applicantlist size >>" + facebookUserList.size());
    request.setAttribute("jobId", jobId);
    

    return mapping.findForward("applicantlist");
  }
}
