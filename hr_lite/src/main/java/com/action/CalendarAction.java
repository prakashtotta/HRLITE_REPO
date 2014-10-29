package com.action;

import com.bean.User;
import com.resources.Constant;
import com.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CalendarAction
  extends CommonAction
{
  public ActionForward calendarView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside calendarView method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    if ((!StringUtils.isNullOrEmpty(user1.getPackagetaken())) && (Constant.isPackageContainFunction(user1.getPackagetaken(), "calendar"))) {
      return mapping.findForward("calendarView");
    }
    return mapping.findForward("upgradeplan");
  }
}
