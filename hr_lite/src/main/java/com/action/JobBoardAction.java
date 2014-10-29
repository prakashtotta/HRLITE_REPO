package com.action;

import com.bean.ProfilePhoto;
import com.bean.User;
import com.bean.UserRegData;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.dao.UserDAO;
import com.form.UserRegForm;
import com.util.StringUtils;
import java.sql.Blob;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class JobBoardAction
  extends CommonAction
{
  public ActionForward editjobboard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editjobboard method");
    
    UserRegForm regform = (UserRegForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    
    UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(user.getSuper_user_key());
    regform.setUserRegId(userregdata.getUserRegId());
    regform.setSubdomain(userregdata.getSubdomain());
    regform.setCompanyInfo(userregdata.getCompanyInfo());
    regform.setLogoPhotoId(userregdata.getLogoPhotoId());
    return mapping.findForward("editjobboard");
  }
  
  public ActionForward updatejobboard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatejobboard method");
    String cinfo = request.getParameter("cinfo");
    logger.info("cinfo" + cinfo);
    UserRegForm regform = (UserRegForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    
    UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(user.getSuper_user_key());
    String oldsubdomain = userregdata.getSubdomain();
    logger.info("Inside updatejobboard method" + regform.getSubdomain());
    String subd = "";
    if (!StringUtils.isNullOrEmpty(regform.getSubdomain()))
    {
      subd = regform.getSubdomain();
      subd = subd.replace(".", "");
      subd = subd.replace(" ", "");
    }
    userregdata.setSubdomain(subd);
    userregdata.setCompanyInfo(cinfo);
    if ((!StringUtils.isNullOrEmpty(oldsubdomain)) && (!StringUtils.isNullOrEmpty(subd)) && (oldsubdomain.equals(subd)))
    {
      BOFactory.getUserBO().updateUserRegData(userregdata);
      request.getSession().setAttribute("updatejobboard", "yes");
    }
    else
    {
      UserRegData userreg = BOFactory.getUserBO().getUserRegDataBySubdomain(subd);
      if (userreg != null)
      {
        request.getSession().setAttribute("updatejobboard", "subdomaintaken");
      }
      else
      {
        BOFactory.getUserBO().updateUserRegData(userregdata);
        request.getSession().setAttribute("updatejobboard", "yes");
      }
    }
    regform.setUserRegId(userregdata.getUserRegId());
    regform.setSubdomain(userregdata.getSubdomain());
    regform.setCompanyInfo(userregdata.getCompanyInfo());
    regform.setLogoPhotoId(userregdata.getLogoPhotoId());
    



    String url = "/jobboard.do?method=editjobboard";
    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    return forward;
  }
  
  public ActionForward uploadLogoPhotoscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadUserPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(user.getSuper_user_key());
    UserRegForm regform = (UserRegForm)form;
    regform.setUserRegId(userregdata.getUserRegId());
    regform.setSubdomain(userregdata.getSubdomain());
    regform.setCompanyInfo(userregdata.getCompanyInfo());
    regform.setLogoPhotoId(userregdata.getLogoPhotoId());
    request.setAttribute("uploadUserPhoto", "no");
    return mapping.findForward("uploadLogoPhoto");
  }
  
  public ActionForward uploadLogoPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadLogoPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(user.getSuper_user_key());
    
    UserRegForm regform = (UserRegForm)form;
    
    FormFile myFile = regform.getLogoPhoto();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      
      ProfilePhoto pp = new ProfilePhoto();
      Blob blob = null;
      
      blob = new SerialBlob(fileData);
      pp.setProfilePhoto(blob);
      
      pp.setUpdatedBy(user.getUserName());
      pp.setUpdatedDate(new Date());
      if (userregdata.getLogoPhotoId() > 0L) {
        pp.setProfilePhotoId(userregdata.getLogoPhotoId());
      }
      pp = UserDAO.saveUserProfilePhoto(pp);
      
      userregdata.setLogoPhotoId(pp.getProfilePhotoId());
      
      BOFactory.getUserBO().updateUserRegData(userregdata);
    }
    regform.setUserRegId(userregdata.getUserRegId());
    regform.setSubdomain(userregdata.getSubdomain());
    regform.setCompanyInfo(userregdata.getCompanyInfo());
    regform.setLogoPhotoId(userregdata.getLogoPhotoId());
    

    request.setAttribute("uploadUserPhoto", "yes");
    return mapping.findForward("uploadLogoPhoto");
  }
}
