package com.action;

import com.bean.ExpenseTypes;
import com.bean.JobApplicant;
import com.bean.RequistionExpenses;
import com.bean.Timezone;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.RequistionExpenseForm;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RequistionExpenseAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(RequistionExpenseAction.class);
  
  public ActionForward requistionExpenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requistionExpenses method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RequistionExpenseForm reqexpForm = (RequistionExpenseForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String reqId = request.getParameter("jobreqId");
    logger.info("reqId" + reqId);
    reqexpForm.setJobReqId(new Long(reqId).longValue());
    
    BOFactory.getLovBO().setLovValuesForRequistionExpenses(user, reqexpForm);
    
    return mapping.findForward("requistionExpenses");
  }
  
  public ActionForward removeExpenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside removeExpenses method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RequistionExpenseForm reqexpForm = (RequistionExpenseForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String reqId = request.getParameter("jobreqId");
    
    List<Long> idlist = new ArrayList();
    String[] select = request.getParameterValues("expensechk");
    if ((select != null) && (select.length != 0)) {
      for (int i = 0; i < select.length; i++) {
        idlist.add(new Long(select[i]));
      }
    }
    if (idlist.size() > 0) {
      BOFactory.getLovBO().deleteExpenses(idlist);
    }
    reqexpForm.setJobReqId(new Long(reqId).longValue());
    
    BOFactory.getLovBO().setLovValuesForRequistionExpenses(user, reqexpForm);
    
    return mapping.findForward("requistionExpenses");
  }
  
  public ActionForward addExpense(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addExpense method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RequistionExpenseForm reqexpForm = (RequistionExpenseForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String reqId = request.getParameter("jobreqId");
    String voucherOwnerId = request.getParameter("voucherOwnerId");
    String expenseDate = request.getParameter("expenseDate");
    RequistionExpenses reqExp = new RequistionExpenses();
    if (!StringUtils.isNullOrEmpty(expenseDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(expenseDate, datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      reqExp.setExpenseDate(cal.getTime());
    }
    reqExp.setVoucherId(reqexpForm.getVoucherId());
    reqExp.setTotalamount(reqexpForm.getTotalamount());
    reqExp.setCreatedDate(new Date());
    reqExp.setCreatedBy(user.getUserName());
    
    logger.info("reqexpForm.getApplicantId()" + reqexpForm.getApplicantId());
    logger.info("reqexpForm.getExpenseTypesId()" + reqexpForm.getExpenseTypesId());
    logger.info("voucherOwnerId" + voucherOwnerId);
    if (reqexpForm.getApplicantId() != 0L)
    {
      JobApplicant applicant = new JobApplicant();
      applicant.setApplicantId(reqexpForm.getApplicantId());
      reqExp.setApplicant(applicant);
    }
    if (reqexpForm.getExpenseTypesId() != 0L)
    {
      ExpenseTypes type = new ExpenseTypes();
      type.setExpenseTypesId(reqexpForm.getExpenseTypesId());
      reqExp.setExpenseType(type);
    }
    if ((!StringUtils.isNullOrEmpty(voucherOwnerId)) && (!voucherOwnerId.equals("0")))
    {
      User vowner = new User();
      vowner.setUserId(new Long(voucherOwnerId.trim()).longValue());
      reqExp.setVoucherOwner(vowner);
    }
    reqExp.setNote(reqexpForm.getNote());
    reqexpForm.setJobReqId(new Long(reqId).longValue());
    
    BOFactory.getLovBO().saveValuesForRequistionExpenses(user, reqexpForm, reqExp);
    
    reqexpForm.setExpensesList(BOFactory.getLovBO().getAllRequistionExpenses(new Long(reqId).longValue()));
    
    reqexpForm.setVoucherId("");
    reqexpForm.setTotalamount(0.0D);
    reqexpForm.setNote("");
    
    return mapping.findForward("requistionExpenses");
  }
}
