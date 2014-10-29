package com.action;

import com.bean.DeclinedResons;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.DeclinedResonsForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeclinedResonsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(DeclinedResonsAction.class);
  
  public ActionForward declinedResonslist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside declinedResonslist method");
    DeclinedResonsForm declineform = new DeclinedResonsForm();
    
    return mapping.findForward("declinedResonslist");
  }
  
  public ActionForward createDeclineReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside createDeclineReasons method");
    DeclinedResonsForm declineform = new DeclinedResonsForm();
    List reasonlist = new ArrayList();
    reasonlist = Constant.getReasonTypeList();
    declineform.setReasonTypeList(reasonlist);
    logger.info("Constant.getReasonTypeList() >> " + Constant.getReasonTypeList().size());
    logger.info("Constant.getReasonTypeList() form >> " + declineform.getReasonTypeList().size());
    return mapping.findForward("createDeclineReasons");
  }
  
  public ActionForward saveDeclineReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveDeclineReasons method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    DeclinedResonsForm declineform = new DeclinedResonsForm();
    DeclinedResons declinedResons = new DeclinedResons();
    declinedResons.setOfferdecliedreasonName(request.getParameter("offerdecliedreasonName"));
    declinedResons.setOfferdecliedreasonDesc(request.getParameter("offerdecliedreasonDesc"));
    

    declinedResons.setStatus("A");
    declinedResons.setSuper_user_key(user1.getSuper_user_key());
    
    declinedResons = BOFactory.getLovBO().saveDeclinedReasons(declinedResons);
    

    declineform.setOfferdeclinedreasonId(declinedResons.getOfferdeclinedreasonId());
    declineform.setOfferdecliedreasonName(declinedResons.getOfferdecliedreasonName());
    declineform.setOfferdecliedreasonDesc(declinedResons.getOfferdecliedreasonDesc());
    
    declineform.setReasonTypeList(Constant.getReasonTypeList());
    
    request.setAttribute("savedeclinedreason", "yes");
    
    return mapping.findForward("createDeclineReasons");
  }
  
  public ActionForward editDeclineReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editDeclineReasons method");
    
    DeclinedResonsForm declineform = new DeclinedResonsForm();
    DeclinedResons declinedResons = null;
    
    String declinedreasonid = request.getParameter("declinedreasonid");
    logger.info("declinedreasonid >> " + declinedreasonid);
    
    declinedResons = BOFactory.getLovBO().getDeclinedReasons(new Integer(declinedreasonid).intValue());
    logger.info("id >> " + declinedResons.getOfferdeclinedreasonId());
    logger.info("name >> " + declinedResons.getOfferdecliedreasonName());
    logger.info("desc >> " + declinedResons.getOfferdecliedreasonDesc());
    logger.info("reason type >> " + declinedResons.getResonType());
    logger.info("status >> " + declinedResons.getStatus());
    

    declineform.setOfferdeclinedreasonId(declinedResons.getOfferdeclinedreasonId());
    declineform.setOfferdecliedreasonName(declinedResons.getOfferdecliedreasonName());
    declineform.setOfferdecliedreasonDesc(declinedResons.getOfferdecliedreasonDesc());
    
    declineform.setReasonTypeList(Constant.getReasonTypeList());
    
    return mapping.findForward("createDeclineReasons");
  }
  
  public ActionForward updateDeclineReasons(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateDeclineReasons method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    DeclinedResonsForm declineform = new DeclinedResonsForm();
    DeclinedResons declinedResons = new DeclinedResons();
    String declinedreasonid = request.getParameter("declinedreasonid");
    String delete = request.getParameter("delete");
    

    declinedResons = BOFactory.getLovBO().getDeclinedReasons(new Integer(declinedreasonid).intValue());
    if (!StringUtils.isNullOrEmpty(delete)) {
      declineform.setStatus("I");
    } else {
      declineform.toValue(declinedResons, request);
    }
    declinedResons = BOFactory.getLovBO().updateDeclinedReasons(declinedResons);
    
    declineform.fromValue(declinedResons, request);
    declineform.setReasonTypeList(Constant.getReasonTypeList());
    if (!StringUtils.isNullOrEmpty(delete)) {
      request.setAttribute("deletedeclinedreason", "yes");
    } else {
      request.setAttribute("updateddeclinedreason", "yes");
    }
    return mapping.findForward("createDeclineReasons");
  }
}
