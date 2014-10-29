package com.action;

import com.bean.JobApplicant;
import com.bean.ReferralRedemption;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.dao.RefferalDAO;
import com.form.ReferralRedemptionSummaryForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ReferalRemptionDetailsAction
  extends CommonRefAction
{
  public ActionForward summarydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside summarydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("agency_data");
    String refredid = request.getParameter("refredid");
    String uuid = request.getParameter("secureid");
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    ReferralRedemption referralRed = RefferalDAO.getReferralRedmptionDetails(refredid, uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(referralRed.getApplicantId()));
    logger.info("Inside summarydetails method 1");
    summaryform.setReferralRedemption(referralRed);
    summaryform.setApplicantUuid(applicant.getUuid());
    logger.info("Inside summarydetails method 2");
    logger.info("Inside summarydetails method end");
    return mapping.findForward("summarydetails");
  }
}
