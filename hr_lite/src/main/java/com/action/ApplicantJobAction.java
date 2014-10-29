package com.action;

import com.bean.JobRequisition;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.common.Common;
import com.form.JobRequisitionForm;
import com.util.EncryptDecrypt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ApplicantJobAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(ApplicantJobAction.class);
  
  public ActionForward jobdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("ApplicantJobAction ..Inside jobdetails method .....");
    String reqid = request.getParameter("reqid");
    reqid = EncryptDecrypt.decrypt(reqid);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(reqid).longValue());
    try
    {
      JobRequisition jb = BOFactory.getJobRequistionBO().jobdetailsNew(jbForm);
      
      jbForm.fromValue(jb, request);
      request.setAttribute("jobapply", "no");
      return mapping.findForward("jobdetails");
    }
    catch (Exception e)
    {
      String msg = "Server Error";
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
}
