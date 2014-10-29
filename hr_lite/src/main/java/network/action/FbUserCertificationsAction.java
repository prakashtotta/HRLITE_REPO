package network.action;

import com.bo.BOFactory;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import network.bean.FaceBookUser;
import network.bean.FbUserCertifications;
import network.bo.FbUserBO;
import network.form.FbUserCertificationsForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class FbUserCertificationsAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(FbUserCertificationsAction.class);
  
  public ActionForward updateFBUserCertificationsScr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateFBUserCertifications method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    


    return mapping.findForward("updateFBUserCertifications");
  }
  
  public ActionForward updateFBUserCertifications(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateFBUserCertifications method ..");
    FaceBookUser user = (FaceBookUser)request.getSession().getAttribute("fb_user_data");
    FbUserCertificationsForm uform = (FbUserCertificationsForm)form;
    String certification1 = request.getParameter("certification1");
    String certification2 = request.getParameter("certification2");
    String certification3 = request.getParameter("certification3");
    String certification4 = request.getParameter("certification4");
    String certification5 = request.getParameter("certification5");
    

    String org1 = request.getParameter("org1");
    String org2 = request.getParameter("org2");
    String org3 = request.getParameter("org3");
    String org4 = request.getParameter("org4");
    String org5 = request.getParameter("org5");
    
    String year1 = request.getParameter("year1");
    String year2 = request.getParameter("year2");
    String year3 = request.getParameter("year3");
    String year4 = request.getParameter("year4");
    String year5 = request.getParameter("year5");
    
    List certificationsList = new ArrayList();
    certificationsList.add(certification1);
    certificationsList.add(certification2);
    certificationsList.add(certification3);
    certificationsList.add(certification4);
    certificationsList.add(certification5);
    
    List orgList = new ArrayList();
    orgList.add(org1);
    orgList.add(org2);
    orgList.add(org3);
    orgList.add(org4);
    orgList.add(org5);
    
    List yearList = new ArrayList();
    yearList.add(year1);
    yearList.add(year2);
    yearList.add(year3);
    yearList.add(year4);
    yearList.add(year5);
    

    List certList = BOFactory.getFbUserBO().getFBUserCertificationsListByUserId(user.getUserId());
    if (certList.size() > 0) {
      for (int i = 0; i < certificationsList.size(); i++)
      {
        FbUserCertifications fbUserCertifications = (FbUserCertifications)certList.get(i);
        fbUserCertifications.setUserId(user.getUserId());
        fbUserCertifications.setCertName((String)certificationsList.get(i));
        fbUserCertifications.setCertOrg((String)orgList.get(i));
        fbUserCertifications.setYear((String)yearList.get(i));
        

        BOFactory.getFbUserBO().updateFBUserCertifications(fbUserCertifications);
      }
    } else {
      for (int i = 0; i < certificationsList.size(); i++)
      {
        FbUserCertifications fbUserCertifications = new FbUserCertifications();
        fbUserCertifications.setUserId(user.getUserId());
        fbUserCertifications.setCertName((String)certificationsList.get(i));
        fbUserCertifications.setCertOrg((String)orgList.get(i));
        fbUserCertifications.setYear((String)yearList.get(i));
        
        BOFactory.getFbUserBO().saveFBUserCertifications(fbUserCertifications);
      }
    }
    request.setAttribute("savecertifications", "yes");
    return mapping.findForward("updateFBUserCertifications");
  }
}
