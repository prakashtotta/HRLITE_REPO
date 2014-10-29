package com.action;

import com.bean.Feedbacks;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.FeedbacksForm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class FeedbacksAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(FeedbacksAction.class);
  
  public ActionForward addFeedbackScr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addFeedbackScr method");
    FeedbacksForm feedbacksForm = (FeedbacksForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (user1 != null) {
      feedbacksForm.setEmailId(user1.getEmailId());
    }
    return mapping.findForward("addFeedbackScr");
  }
  
  public ActionForward saveFeedbacks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveFeedbacks method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    FeedbacksForm feedbacksForm = (FeedbacksForm)form;
    Feedbacks feedbacks = new Feedbacks();
    if (user1 != null) {
      feedbacks.setEmailId(user1.getEmailId());
    } else {
      feedbacks.setEmailId(feedbacksForm.getEmailId());
    }
    feedbacks.setComment(feedbacksForm.getComment());
    feedbacks.setCreatedDate(new Date());
    
    BOFactory.getLovBO().saveFeedbacks(feedbacks);
    
    request.setAttribute("isfeedbacksaved", "yes");
    
    return mapping.findForward("addFeedbackScr");
  }
}
