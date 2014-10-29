package com.action;

import com.bean.User;
import com.bean.WatchList;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.WatchListForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WatchListAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(WatchListAction.class);
  
  public ActionForward watchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside watchList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    WatchListForm wform = (WatchListForm)form;
    
    String type = request.getParameter("type");
    String reqid = request.getParameter("reqid");
    String applicantId = request.getParameter("applicantId");
    List wlist = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE")))
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), 0L, type);
      wform.setReqId(new Long(reqid).longValue());
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQ")))
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), 0L, type);
      wform.setReqId(new Long(reqid).longValue());
    }
    else
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), new Long(applicantId).longValue(), type);
      wform.setReqId(new Long(reqid).longValue());
      wform.setApplicantId(new Long(applicantId).longValue());
    }
    wform.setWatchList(wlist);
    wform.setType(type);
    
    return mapping.findForward("watchList");
  }
  
  public ActionForward addwatchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addwatchList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    WatchListForm wform = (WatchListForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String type = request.getParameter("type");
    String reqid = request.getParameter("reqid");
    String applicantId = request.getParameter("applicantId");
    String userUserGrpId = request.getParameter("userUserGrpId");
    String isGroup = request.getParameter("isGroup");
    String userUserGrpName = request.getParameter("userUserGrpName");
    List wlist = new ArrayList();
    WatchList watch = new WatchList();
    watch.setIsGroup(isGroup);
    watch.setType(type);
    watch.setUserUserGrpId(new Long(userUserGrpId.trim()).longValue());
    watch.setUserUserGrpName(userUserGrpName);
    watch.setCreatedBy(user1.getUserName());
    watch.setCreatedDate(new Date());
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE"))) {
      watch.setIdvalue(new Long(reqid).longValue());
    } else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQ"))) {
      watch.setIdvalue(new Long(reqid).longValue());
    } else {
      watch.setIdvalue(new Long(applicantId).longValue());
    }
    if (!BOFactory.getLovBO().isWatcherExist(new Long(reqid).longValue(), new Long(applicantId).longValue(), type, new Long(userUserGrpId.trim()).longValue(), isGroup)) {
      BOFactory.getLovBO().saveWatchList(watch);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE")))
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), 0L, type);
      wform.setReqId(new Long(reqid).longValue());
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQ")))
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), 0L, type);
      wform.setReqId(new Long(reqid).longValue());
    }
    else
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), new Long(applicantId).longValue(), type);
      wform.setReqId(new Long(reqid).longValue());
      wform.setApplicantId(new Long(applicantId).longValue());
    }
    wform.setWatchList(wlist);
    wform.setType(type);
    
    return mapping.findForward("watchList");
  }
  
  public ActionForward removewatchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside removewatchList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    WatchListForm wform = (WatchListForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String type = request.getParameter("type");
    String reqid = request.getParameter("reqid");
    String applicantId = request.getParameter("applicantId");
    List<Long> idlist = new ArrayList();
    String[] select = request.getParameterValues("watchlistchk");
    if ((select != null) && (select.length != 0)) {
      for (int i = 0; i < select.length; i++) {
        idlist.add(new Long(select[i]));
      }
    }
    if (idlist.size() > 0) {
      BOFactory.getLovBO().deleteWatcher(idlist);
    }
    List wlist = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE")))
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), 0L, type);
      wform.setReqId(new Long(reqid).longValue());
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQ")))
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), 0L, type);
      wform.setReqId(new Long(reqid).longValue());
    }
    else
    {
      wlist = BOFactory.getLovBO().getWatchList(new Long(reqid).longValue(), new Long(applicantId).longValue(), type);
      wform.setReqId(new Long(reqid).longValue());
      wform.setApplicantId(new Long(applicantId).longValue());
    }
    wform.setWatchList(wlist);
    wform.setType(type);
    
    return mapping.findForward("watchList");
  }
}
