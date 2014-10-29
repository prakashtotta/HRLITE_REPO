package com.action;

import com.bean.AgencyRedemption;
import com.bean.JobApplicant;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.dao.RefferalDAO;
import com.form.AgencyRedemptionSummaryForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AgencyRemptionDetailsAction
  extends CommonAgencyAction
{
  public ActionForward summarydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside summarydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("agency_data");
    String agredid = request.getParameter("agredid");
    String uuid = request.getParameter("secureid");
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    AgencyRedemption agencyRed = RefferalDAO.getAgencyRedmptionDetails(agredid, uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(agencyRed.getApplicantId()));
    summaryform.setAgencyredmption(agencyRed);
    summaryform.setApplicantuuid(applicant.getUuid());
    return mapping.findForward("summarydetails");
  }
}
