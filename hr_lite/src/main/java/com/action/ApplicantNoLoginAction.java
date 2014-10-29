package com.action;

import com.bean.ActionsAttachment;
import com.bean.ApplicantAttachments;
import com.bean.ApplicantComment;
import com.bean.ApplicantOfferAttachment;
import com.bean.ApplicantSkills;
import com.bean.ApplicantUser;
import com.bean.Country;
import com.bean.Currency;
import com.bean.EducationDetails;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.PreviousOrgDetails;
import com.bean.RefferalEmployee;
import com.bean.ResumeSourceType;
import com.bean.Role;
import com.bean.State;
import com.bean.UrlEncodeData;
import com.bean.User;
import com.bean.dto.Recruiter;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.bo.BusinessFilterBO;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.bo.LovTXBO;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.dao.RefferalDAO;
import com.dao.UserDAO;
import com.form.ApplicantForm;
import com.form.VariableDataCaptureUtil;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EmailTask;
import com.util.StringUtils;
import java.io.File;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
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

public class ApplicantNoLoginAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(ApplicantNoLoginAction.class);
  
  public ActionForward deleteApplicantattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteApplicantattachment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantUserDAO.deleteActionAttachment(attachmentuuid);
    
    request.setAttribute("attachmentdeleted", "yes");
    
    return mapping.findForward("deleteApplicantattachment");
  }
  
  public ActionForward forgotpasswordsapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpasswordsapplicant method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    return mapping.findForward("forgotpasswordsapplicant");
  }
  
  public ActionForward forgotpassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info(".... Inside forgotpassword method ... 1");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String email = request.getParameter("emailId");
    String applicantCode = request.getParameter("applicantCode");
    logger.info("email : " + email);
    ApplicantUser applicant = BOFactory.getApplicantBO().getApplicantByEmail(email, applicantCode);
    if (applicant == null)
    {
      request.setAttribute("wrongemailid", "yes");
    }
    else
    {
      request.setAttribute("forgotpasswordsent", "yes");
      


      String[] tonew = { applicant.getEmailId() };
      logger.info("refemp.getEmployeeemail()" + applicant.getEmailId());
      String[] ccnew = null;
      String from = Constant.getValue("email.fromemail");
      String replyto = Constant.getValue("email.replytoemail");
      
      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, replyto, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.FORGOT_PASSWORD);
      emailtasknew.setSubFunctionType(Common.FORGOT_PASSWORD_APPLICANT);
      emailtasknew.setAppuser(applicant);
      EmailTaskManager.sendEmail(emailtasknew);
    }
    return mapping.findForward("forgotpasswordsapplicant");
  }
  
  public ActionForward acceptordeclinedurl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String decrypted = "";
    logger.info("Inside acceptordeclinedurl method");
    String keyvalue = request.getParameter("key");
    logger.info("keyvalue" + keyvalue);
    keyvalue = keyvalue.replace(" ", "+");
    UrlEncodeData enc = BOFactory.getLovBO().getEncodeUrl(keyvalue);
    logger.info("enc" + enc);
    String tt = enc.getUrl();
    logger.info("url" + tt);
    String applicantId = tt.substring(tt.lastIndexOf("applicantId") + 12, tt.length());
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    


    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    
    List appliocantattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
    applicantform.setApplicantAttachmentList(appliocantattachmentList);
    
    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    logger.info("finish applicantofferdetails");
    
    request.getSession().setAttribute("keyvalue", keyvalue);
    request.getSession().setAttribute("jobreqid", String.valueOf(applicant.getReqId()));
    request.getSession().setAttribute("wsint", "no");
    

    return mapping.findForward("applicantofferdetails");
  }
  
  public ActionForward applicantpfferattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String decrypted = "";
    logger.info("Inside applicantpfferattachment method");
    String applicantId = request.getParameter("applicantId");
    String filedetails = request.getParameter("filedetails");
    
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    FormFile myFile = applicantform.getAttachmentdata();
    int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (fileSize > limitfileSize)
      {
        request.getSession().setAttribute("attachmentsizeexceed", "yes");
      }
      else if (!StringUtils.isNullOrEmpty(fileName))
      {
        ApplicantOfferAttachment appattach = new ApplicantOfferAttachment();
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        

        appattach.setAttahmentdetails(filedetails);
        appattach.setApplicantId(new Long(applicantId).longValue());
        appattach.setCreatedBy(applicant.getFullName());
        appattach.setCreatedDate(new Date());
        appattach.setType("applicantdoc");
        appattach.setUuid(UUID.randomUUID().toString());
        BOFactory.getApplicantBO().saveOfferAttachment(appattach, applicant.getUuid(), applicant.getApplicantId(), applicant.getFullName(), null);
        



        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "offerAttachment" + File.separator + appattach.getUuid() + File.separator;
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
      }
    }
    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    
    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    


    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    
    List appliocantattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
    applicantform.setApplicantAttachmentList(appliocantattachmentList);
    
    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    logger.info("finish applicantofferdetails");
    
    String keyval = (String)request.getSession().getAttribute("keyvalue");
    String url = "applicantoffer.do?method=acceptordeclinedurl&key=" + keyval;
    ActionForward forward = new ActionForward(url, true);
    return forward;
  }
  
  public ActionForward offerattachmentdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offerattachmentdetails method");
    String offerattachmentid = request.getParameter("offerattachmentid");
    
    ApplicantOfferAttachment offerattachment = BOFactory.getApplicantBO().getOfferAttachmentDetailsByUuid(offerattachmentid);
    if ((offerattachment != null) && (offerattachment.getAttahmentname() != null) && (offerattachment.getAttahmentname().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = "offerAttachment" + File.separator + offerattachment.getUuid() + File.separator + offerattachment.getAttahmentname();
      
      request.setAttribute("filePath", filePath);
      logger.info("filepath >> " + filePath);
    }
    return mapping.findForward("offerattachmentdetails");
  }
  
  public ActionForward acceptoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside acceptoffer method");
    String applicantId = request.getParameter("applicantId");
    String comment = request.getParameter("comment");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    applicant.setInterviewState("Offer Accepted");
    applicant.setOfferacceptedcomment(comment);
    applicant.setOfferaccepteddate(new Date());
    






    applicant = BOFactory.getApplicantTXBO().acceptoffer(applicant, Common.OFFER_ACCEPTED_DECLINED_BY_TYPE_APPLICANT);
    



    ApplicantForm applicantform = (ApplicantForm)form;
    
    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    


    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    
    applicantform.setOfferacceptedcomment(applicant.getOfferacceptedcomment());
    applicantform.setOfferaccepteddate(applicant.getOfferaccepteddate());
    
    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    String keyval = (String)request.getSession().getAttribute("keyvalue");
    String url = "applicantoffer.do?method=acceptordeclinedurl&key=" + keyval;
    ActionForward forward = new ActionForward(url, true);
    return forward;
  }
  
  public ActionForward stateListNologin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside stateListNologin method");
    String cid = request.getParameter("cid");
    System.out.println(" **** cid :" + cid);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setStateList(BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue()));
    


    System.out.println(" **** test :");
    return mapping.findForward("stateListNologin");
  }
  
  public ActionForward declineoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside declineoffer method");
    String applicantId = request.getParameter("applicantId");
    String comment = request.getParameter("comment");
    String offerdeclinedresonid = request.getParameter("offerdeclinedresonid");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    applicant.setInterviewState("Offer Declined");
    applicant.setOfferdeclinedcomment(comment);
    applicant.setOfferdeclineddate(new Date());
    applicant.setOfferdeclinedresonid(new Integer(offerdeclinedresonid).intValue());
    


    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    jobreq.setNumberOfOpeningRemain(jobreq.getNumberOfOpeningRemain() + 1);
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("auto.close.requisition.on.position.filled"))) && (Constant.getValue("auto.close.requisition.on.position.filled").equalsIgnoreCase("yes")) && 
      (jobreq.getStatus().equals("Closed")) && (jobreq.getNumberOfOpeningRemain() > 0)) {
      jobreq.setStatus("Open");
    }
    BOFactory.getApplicantTXBO().declineoffer(applicant, jobreq, Common.OFFER_ACCEPTED_DECLINED_BY_TYPE_APPLICANT);
    

    ApplicantForm applicantform = (ApplicantForm)form;
    
    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    


    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    


    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    String keyval = (String)request.getSession().getAttribute("keyvalue");
    String url = "applicantoffer.do?method=acceptordeclinedurl&key=" + keyval;
    ActionForward forward = new ActionForward(url, true);
    return forward;
  }
  
  public ActionForward sendoffermodifficationrequestscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sendoffermodifficationrequestscr method");
    String applicantId = request.getParameter("applicantId");
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    if (applicant.getTargetjoiningdate() != null) {
      applicantform.setChagetargetjoiningdate(DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), Constant.getValue("email.defaultdateformatwithourday")));
    }
    applicantform.setChangeofferedctc(applicant.getOfferedctc());
    applicantform.setChangeofferedctccurrencycode(applicant.getOfferedctccurrencycode());
    applicantform.setApplicantId(applicant.getApplicantId());
    

    request.setAttribute("modificationrequestsaved", "no");
    return mapping.findForward("sendoffermodifficationrequestscr");
  }
  
  public ActionForward sendoffermodifficationrequest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sendoffermodifficationrequest method");
    String applicantId = request.getParameter("applicantId");
    String chagetargetjoiningdate = request.getParameter("chagetargetjoiningdate");
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    if (chagetargetjoiningdate != null) {
      applicant.setChagetargetjoiningdate(DateUtil.convertStringDateToDate(chagetargetjoiningdate, Constant.getValue("email.defaultdateformatwithourday")));
    }
    applicant.setChangeofferComment(applicantform.getChangeofferComment());
    applicant.setChangeofferedctc(applicantform.getChangeofferedctc());
    applicant.setChangeofferedctccurrencycode(applicantform.getChangeofferedctccurrencycode());
    applicant.setInterviewState("Offer In Negotiation");
    applicant = BOFactory.getApplicantTXBO().offermodifficationrequest(applicant, Common.OFFER_ACCEPTED_DECLINED_BY_TYPE_APPLICANT);
    
    request.setAttribute("modificationrequestsaved", "yes");
    return mapping.findForward("sendoffermodifficationrequestscr");
  }
  
  public ActionForward createPageforRefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createPageforRefferal method .. ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String requitionId = request.getParameter("requistionId");
    String empemail = request.getParameter("empemail");
    

    logger.info("empemail" + empemail);
    if (!StringUtils.isNullOrEmpty(requitionId))
    {
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(requitionId);
      if (jobreq != null)
      {
        applicantform.setRequitionId(jobreq.getJobreqId());
        applicantform.setJobTitle(jobreq.getJobTitle());
        String currencycode = jobreq.getOrganization().getCurrencyCode();
        applicantform.setCurrectctccurrencycode(currencycode);
        applicantform.setExpectedctccurrencycode(currencycode);
      }
    }
    BOFactory.getApplicantBO().commonLovPopulateOnCreate(applicantform, request, user1.getSuper_user_key());
    if (!StringUtils.isNullOrEmpty(empemail))
    {
      applicantform.setSourceTypeId(1);
      RefferalEmployee refemp = RefferalDAO.getRefferalEmployeeByEmail(empemail);
      if (refemp != null)
      {
        applicantform.setReferrerEmail(refemp.getEmployeeemail());
        applicantform.setReferrerName(refemp.getEmployeename());
        applicantform.setEmployeecode(refemp.getEmployeecode());
        applicantform.setHidesource("refferal");
      }
    }
    String fromWhere = request.getParameter("frm");
    logger.info("fromWhere : " + fromWhere);
    request.setAttribute("frm", fromWhere);
    logger.info("applicantform.getSourceTypeId()" + applicantform.getSourceTypeId());
    request.setAttribute("applicantsaved", "no");
    return mapping.findForward("createPageforRefferal");
  }
  
  public ActionForward saveapplicantDataforRefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantDataforRefferal method");
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    JobApplicant applicant = new JobApplicant();
    String requitionId = request.getParameter("reqid1");
    String jobTitle = request.getParameter("jobt1");
    String dateofbirth = request.getParameter("dateofbirth");
    String previousOrganization = request.getParameter("previousOrganization");
    

    applicantform.toValue(applicant, request);
    String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
    logger.info("request.getParameter request.getParameter");
    logger.info("applicantform.getSourceTypeId()" + applicantform.getSourceTypeId());
    
    String gender = request.getParameter("gender");
    logger.info(gender);
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(empref.getLocale());
      
      Calendar cal = DateUtil.convertStringDateToCalendar(dateofbirth, datepattern);
      
      applicant.setDateofbirth(cal.getTime());
    }
    logger.info(requitionId);
    logger.info(jobTitle);
    applicant.setReqId(new Long(requitionId).longValue());
    

    applicant.setGender(gender);
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(requitionId);
    applicant.setJobTitle(jb.getJobreqName());
    
    applicant.setPreviousOrganization(previousOrganization);
    applicant.setUuid(UUID.randomUUID().toString());
    

    applicant.setCurrectctc(applicantform.getCurrectctc());
    applicant.setExpectedctc(applicantform.getExpectedctc());
    String currencycode = jb.getOrganization().getCurrencyCode();
    

    applicant.setCurrectctccurrencycode(currencycode);
    applicant.setExpectedctccurrencycode(currencycode);
    
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    
    boolean isSuccess = true;
    List errorList = new ArrayList();
    JobApplicant oldapplicant = BOFactory.getApplicantBO().isApplicantDuplicate(applicant, empref.getSuper_user_key());
    if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
    {
      request.setAttribute("attachmentsizeexceed", "yes");
      isSuccess = false;
    }
    if (oldapplicant != null)
    {
      request.setAttribute("oldapplicant", oldapplicant);
    }
    else
    {
      applicant = BOFactory.getApplicantBO().saveApplicant(applicant, null, null, empref, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList());
      
      errorList = applicant.getErrorList();
      if ((errorList != null) && (errorList.size() > 0)) {
        isSuccess = false;
      }
      if (isSuccess)
      {
        request.setAttribute("applicantsaved", "yes");
        request.setAttribute("applicantdatacustom", "yes");
      }
      else
      {
        logger.info("in error condition");
        request.setAttribute("error_list", errorList);
      }
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, empref.getSuper_user_key());
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
    }
    request.setAttribute("applicantId", Long.valueOf(applicant.getApplicantId()));
    request.setAttribute("uuid", applicant.getUuid());
    
    return mapping.findForward("createPageforRefferal");
  }
  
  public ActionForward attachmentdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside attachmentdetails method");
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String attachmentuuid = request.getParameter("attachmentuuid");
    String fname = request.getParameter("fname");
    

    ApplicantAttachments appattach = BOFactory.getApplicantBO().getApplicantAttachments(new Long(applicantId).longValue(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if (appattach != null)
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = "applicantAttachment" + File.separator + appattach.getUuid() + File.separator + appattach.getAttahmentname();
      
      request.setAttribute("filePath", filePath);
    }
    else
    {
      String filePath = "applicantAttachment" + File.separator + attachmentuuid + File.separator + fname;
      request.setAttribute("filePath", filePath);
    }
    return mapping.findForward("attachmentdetails");
  }
  
  public ActionForward updateapplicantDataforRefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantDataforRefferal method");
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String requitionId = request.getParameter("reqid1");
    String jobTitle = request.getParameter("jobt1");
    String dateofbirth = request.getParameter("dateofbirth");
    String previousOrganization = request.getParameter("previousOrganization");
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    String rname = applicant.getResumename();
    
    applicantform.toValue(applicant, request);
    
    logger.info("request.getParameter request.getParameter");
    
    String gender = request.getParameter("gender");
    logger.info(gender);
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = Constant.getValue("defaultdateformat");
      Calendar cal = DateUtil.convertStringDateToCalendar(dateofbirth, datepattern);
      
      applicant.setDateofbirth(cal.getTime());
    }
    logger.info(requitionId);
    logger.info(jobTitle);
    applicant.setReqId(new Long(requitionId).longValue());
    applicant.setJobTitle(jobTitle);
    
    applicant.setGender(gender);
    
    applicant.setPreviousOrganization(previousOrganization);
    logger.info("rname" + rname);
    logger.info("applicantform.getResumename()" + applicantform.getResumename());
    boolean isAttachmentupdata = true;
    if ((rname != null) && (StringUtils.isNullOrEmpty(applicantform.getResumename())))
    {
      applicant.setResumename(rname);
      isAttachmentupdata = false;
    }
    applicant.setCurrectctc(applicantform.getCurrectctc());
    applicant.setExpectedctc(applicantform.getExpectedctc());
    String currencycode = BOFactory.getJobRequistionBO().getCurrencyCodeByReqId(new Long(requitionId).longValue());
    applicant.setCurrectctccurrencycode(currencycode);
    applicant.setExpectedctccurrencycode(currencycode);
    if (!StringUtils.isNullOrEmpty(applicantform.getReferrerEmail())) {
      applicant.setCreatedBy(applicantform.getReferrerEmail());
    }
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    
    boolean isSuccess = true;
    List errorList = new ArrayList();
    applicant = BOFactory.getApplicantBO().updateApplicant(applicant, null, null, empref, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), isAttachmentupdata);
    


    errorList = applicant.getErrorList();
    if ((errorList != null) && (errorList.size() > 0)) {
      isSuccess = false;
    }
    if (isSuccess)
    {
      request.setAttribute("applicantupdated", "yes");
      request.setAttribute("applicantdatacustom", "yes");
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, empref.getSuper_user_key());
    
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    applicantform.setSourceTypeList(soucetypeList);
    if (applicant.getResumesourcetype().getResumeSourceTypeId() == 5)
    {
      List vendorList = UserDAO.getAllActiveVendors();
      applicantform.setVendorsList(vendorList);
    }
    else if (applicant.getResumesourcetype().getResumeSourceTypeId() == 1)
    {
      applicantform.setReferrerEmail(applicant.getReferrerEmail());
      applicantform.setReferrerName(applicant.getReferrerName());
    }
    else if (applicant.getResumesourcetype().getResumeSourceTypeId() > 0)
    {
      List sourceList = BOFactory.getLovBO().getAllResumeSource(applicant.getResumesourcetype().getResumeSourceTypeId());
      applicantform.setSourceList(sourceList);
    }
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
    }
    return mapping.findForward("createPageforRefferal");
  }
  
  public ActionForward updateapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantData method");
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String requitionId = request.getParameter("reqid1");
    String jobTitle = request.getParameter("jobt1");
    String dateofbirth = request.getParameter("dateofbirth");
    String previousOrganization = request.getParameter("previousOrganization");
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    String rname = applicant.getResumename();
    applicantform.toValue(applicant, request);
    String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
    logger.info("request.getParameter request.getParameter");
    
    String gender = request.getParameter("gender");
    logger.info(gender);
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = Constant.getValue("defaultdateformat");
      Calendar cal = DateUtil.convertStringDateToCalendar(dateofbirth, datepattern);
      
      applicant.setDateofbirth(cal.getTime());
    }
    logger.info(requitionId);
    logger.info(jobTitle);
    applicant.setReqId(new Long(requitionId).longValue());
    applicant.setJobTitle(jobTitle);
    
    applicant.setGender(gender);
    

    applicant.setPreviousOrganization(previousOrganization);
    logger.info("rname" + rname);
    logger.info("applicantform.getResumename()" + applicantform.getResumename());
    boolean isAttachmentupdata = true;
    if ((rname != null) && (StringUtils.isNullOrEmpty(applicantform.getResumename())))
    {
      applicant.setResumename(rname);
      isAttachmentupdata = false;
    }
    applicant.setCurrectctc(applicantform.getCurrectctc());
    applicant.setExpectedctc(applicantform.getExpectedctc());
    String currencycode = BOFactory.getJobRequistionBO().getCurrencyCodeByReqId(new Long(requitionId).longValue());
    applicant.setCurrectctccurrencycode(currencycode);
    applicant.setExpectedctccurrencycode(currencycode);
    if (!StringUtils.isNullOrEmpty(applicantform.getReferrerEmail())) {
      applicant.setCreatedBy(applicantform.getReferrerEmail());
    }
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    
    boolean isSuccess = true;
    List errorList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
    {
      request.setAttribute("attachmentsizeexceed", "yes");
      isSuccess = false;
    }
    else
    {
      applicant = BOFactory.getApplicantBO().updateApplicant(applicant, null, null, empref, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), isAttachmentupdata);
      

      errorList = applicant.getErrorList();
      if ((errorList != null) && (errorList.size() > 0)) {
        isSuccess = false;
      }
    }
    if (isSuccess)
    {
      request.setAttribute("applicantupdated", "yes");
      request.setAttribute("applicantdatacustom", "yes");
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
    }
    applicantform.fromValue(applicant, request);
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, empref.getSuper_user_key());
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
    }
    return mapping.findForward("createPageforRefferal");
  }
  
  public ActionForward deleteResumeforref(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteResume method");
    RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant == null) {
      return null;
    }
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getResumename() != null) && (applicant.getResumename().length() > 0))
    {
      BOFactory.getApplicantBO().deleteResume(new Long(applicantId).longValue(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      
      applicant.setResumename(null);
      applicantform.setResumedetails("");
      BOFactory.getApplicantBO().updateApplicant(applicant);
      
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = filePath + File.separator + "applicantAttachment" + File.separator + applicant.getApplicantAttachment().getUuid() + File.separator;
      BOFactory.getApplicantBO().deleteAttachmentDirFromFileServer(filePath, applicant.getApplicantAttachment().getUuid());
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, userEmp.getSuper_user_key());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    

    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("createPageforRefferal");
  }
  
  public ActionForward saveeducationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveeducationdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    String type = request.getParameter("type");
    String educationName = request.getParameter("educationName");
    String specialization = request.getParameter("specialization");
    String institute = request.getParameter("institute");
    String percentile = request.getParameter("percentile");
    String passingyear = request.getParameter("passingyear");
    String startingYear = request.getParameter("startingYear");
    

    EducationDetails edu = new EducationDetails();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setEducationName(educationName);
    edu.setSpecialization(specialization);
    edu.setInstituteName(institute);
    edu.setPercentile(percentile);
    edu.setPassingYear(passingyear);
    edu.setEduType(type);
    edu.setStartingYear(startingYear);
    if (user1 != null) {
      edu.setCreatedBy(user1.getUserName());
    }
    edu.setUuid(UUID.randomUUID().toString());
    edu.setCreatedDate(new Date());
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantid, uuid);
    String screenCode = VariableDataCaptureUtil.getScreenCodeEducation(request, applicant);
    List errorList = BOFactory.getBusinessFilterBO().isEducationMandatoryValueSucceeed(edu, VariableDataCaptureUtil.getUser(request), type, screenCode);
    if ((errorList != null) && (errorList.size() > 0))
    {
      logger.info("in error condition");
      request.setAttribute("error_list_education", errorList);
    }
    else
    {
      BOFactory.getApplicantBO().saveEducationDetails(edu);
    }
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE))) {
      applicantform.setEducationsList(eduList);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_CERTIFICATION))) {
      applicantform.setCertificationsList(eduList);
    }
    applicantform.setEduType(type);
    
    applicantform.setApplicantId(new Long(applicantid).longValue());
    applicantform.setUuid(uuid);
    

    return mapping.findForward("geteducationdetails");
  }
  
  public ActionForward saveeducationdetailsforReferral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveeducationdetailsforReferral method");
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String type = request.getParameter("type");
    String educationName = request.getParameter("educationName");
    String specialization = request.getParameter("specialization");
    String institute = request.getParameter("institute");
    String percentile = request.getParameter("percentile");
    String passingyear = request.getParameter("passingyear");
    String startingYear = request.getParameter("startingYear");
    

    EducationDetails edu = null;
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE)))
    {
      List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
      
      Iterator itr = eduList.iterator();
      logger.info("Applicant Skills list : " + eduList);
      while (itr.hasNext())
      {
        edu = (EducationDetails)itr.next();
        System.out.println("skill name : " + edu.getEducationName());
        if (educationName.equals(edu.getEducationName()))
        {
          request.setAttribute("educationalreadyadded", "yes");
          return mapping.findForward("createPageforRefferal");
        }
      }
    }
    edu = new EducationDetails();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setEducationName(educationName);
    edu.setSpecialization(specialization);
    edu.setInstituteName(institute);
    edu.setPercentile(percentile);
    edu.setPassingYear(passingyear);
    edu.setStartingYear(startingYear);
    edu.setEduType(type);
    if (user1 != null) {
      edu.setCreatedBy(user1.getUserName());
    }
    edu.setCreatedDate(new Date());
    BOFactory.getApplicantBO().saveEducationDetails(edu);
    


    return null;
  }
  
  public ActionForward deleteEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteEducation method");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String eduid = request.getParameter("eduid");
    BOFactory.getApplicantBO().deleteEducationByUUID(eduid);
    return null;
  }
  
  public ActionForward deleteSkill(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteSkill method");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String skillid = request.getParameter("skillid");
    BOFactory.getApplicantBO().deleteSkillByUUID(skillid);
    return null;
  }
  
  public ActionForward saveapplicantSkillsforrefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantSkills method");
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String skillname = request.getParameter("skillname");
    String yearsofexpskill = request.getParameter("yearsofexpskill");
    String ratingskill = request.getParameter("ratingskill");
    
    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    logger.info("Applicant Skills list : " + skillsList);
    Iterator itr = skillsList.iterator();
    ApplicantSkills edu = null;
    while (itr.hasNext())
    {
      edu = (ApplicantSkills)itr.next();
      System.out.println("skill name : " + edu.getSkillname());
      if (skillname.equals(edu.getSkillname()))
      {
        request.setAttribute("skillalreadyadded", "yes");
        return mapping.findForward("createPageforRefferal");
      }
    }
    edu = new ApplicantSkills();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setSkillname(skillname);
    edu.setYearsofexp(yearsofexpskill);
    edu.setRating(ratingskill);
    if (user1 != null) {
      edu.setCreatedBy(user1.getUserName());
    }
    edu.setCreatedDate(new Date());
    BOFactory.getApplicantBO().saveSkillsDetails(edu);
    


    return null;
  }
  
  public ActionForward saveapplicantSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantSkills method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String skillname = request.getParameter("skillname");
    String yearsofexpskill = request.getParameter("yearsofexpskill");
    
    String ratingskill = request.getParameter("ratingskill");
    
    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    
    logger.info("Applicant Skills list : " + skillsList);
    Iterator itr = skillsList.iterator();
    ApplicantSkills edu = null;
    while (itr.hasNext())
    {
      edu = (ApplicantSkills)itr.next();
      System.out.println("skill name : " + edu.getSkillname());
      if (skillname.equals(edu.getSkillname()))
      {
        request.setAttribute("skillalreadyadded", "yes");
        return mapping.findForward("createPage");
      }
    }
    edu = new ApplicantSkills();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setSkillname(skillname);
    edu.setYearsofexp(yearsofexpskill);
    edu.setRating(ratingskill);
    if (user1 != null) {
      edu.setCreatedBy(user1.getUserName());
    }
    edu.setCreatedDate(new Date());
    edu.setUuid(UUID.randomUUID().toString());
    BOFactory.getApplicantBO().saveSkillsDetails(edu);
    
    return null;
  }
  
  public ActionForward geteducationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside geteducationdetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String type = request.getParameter("type");
    logger.info("Inside geteducationdetails method type" + type);
    String uuid = request.getParameter("uuid");
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE))) {
      applicantform.setEducationsList(eduList);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_CERTIFICATION))) {
      applicantform.setCertificationsList(eduList);
    }
    applicantform.setEduType(type);
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantid).longValue());
    
    return mapping.findForward("geteducationdetails");
  }
  
  public ActionForward geteducationdetailsforReferral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside geteducationdetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String type = request.getParameter("type");
    




    String uuid = request.getParameter("uuid");
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE))) {
      applicantform.setEducationsList(eduList);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_CERTIFICATION))) {
      applicantform.setCertificationsList(eduList);
    }
    applicantform.setEduType(type);
    applicantform.setUuid(uuid);
    
    return mapping.findForward("geteducationdetailsforReferral");
  }
  
  public ActionForward deleteattachmentpopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachmentpopup method");
    String applicantId = request.getParameter("applicantId");
    String idvalue = request.getParameter("idvalue");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantUserDAO.deleteActionAttachment(attachmentuuid);
    
    List attachmentList = ApplicantUserDAO.getActionAttachmentList(new Long(idvalue).longValue(), actioname);
    applicantform.setActionAttachmentList(attachmentList);
    request.setAttribute("action_name", actioname);
    request.setAttribute("idvalue", idvalue);
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward deleteattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantUserDAO.deleteActionAttachment(attachmentuuid);
    
    request.setAttribute("attachmentdeleted", "yes");
    
    return mapping.findForward("deleteattachment");
  }
  
  public ActionForward deletePrevOg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletePrevOg method");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String prevorgid = request.getParameter("prevorgid");
    BOFactory.getApplicantBO().deletePreviousOrg(new Long(prevorgid).longValue());
    return null;
  }
  
  public ActionForward getpreviousOrgdetailsforrefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getpreviousOrgdetailsforrefferal method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    
    List orgDetailsList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantid).longValue());
    applicantform.setPreviousOrgList(orgDetailsList);
    
    return mapping.findForward("getpreviousOrgdetailsforrefferal");
  }
  
  public ActionForward getpreviousOrgdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getpreviousOrgdetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    
    List orgDetailsList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantid).longValue());
    applicantform.setPreviousOrgList(orgDetailsList);
    
    return mapping.findForward("getpreviousOrgdetails");
  }
  
  public ActionForward getApplicantSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getApplicantSkills method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    
    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    applicantform.setApplicantSkillList(skillsList);
    
    return mapping.findForward("getApplicantSkills");
  }
  
  public ActionForward getApplicantSkillsforrefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getApplicantSkills method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    
    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    applicantform.setApplicantSkillList(skillsList);
    
    return mapping.findForward("getApplicantSkillsforrefferal");
  }
  
  public ActionForward savepreviousOrgdetailsforrefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savepreviousOrgdetailsforrefferal method");
    
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    logger.info("user1 .." + user1);
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String prevOrgName = request.getParameter("prevOrgName");
    String role = request.getParameter("role");
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    String reasonforleave = request.getParameter("reasonforleave");
    String reportingto = request.getParameter("reportingto");
    
    PreviousOrgDetails prevorg = new PreviousOrgDetails();
    prevorg.setApplicantId(new Long(applicantid).longValue());
    prevorg.setPrevOrgName(prevOrgName);
    prevorg.setRole(role);
    prevorg.setStartdate(startDate);
    prevorg.setEnddate(endDate);
    prevorg.setReasonforleave(reasonforleave);
    prevorg.setReportingToName(reportingto);
    if (user1 != null) {
      prevorg.setCreatedBy(user1.getUserName());
    }
    prevorg.setCreatedDate(new Date());
    
    BOFactory.getApplicantBO().savePreviousOrgDetails(prevorg);
    


    return null;
  }
  
  public ActionForward savepreviousOrgdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savepreviousOrgdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    String prevOrgName = request.getParameter("prevOrgName");
    String role = request.getParameter("role");
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    String reasonforleave = request.getParameter("reasonforleave");
    String reportingto = request.getParameter("reportingto");
    String city_Previousorg = request.getParameter("city_Previousorg");
    String country_id_prevorg = request.getParameter("country_id_prevorg");
    String state_id_prevorg = request.getParameter("state_id_prevorg");
    String lastSalary = request.getParameter("lastSalary");
    String bonus = request.getParameter("bonus");
    String currency_id_prevorg = request.getParameter("currency_id_prevorg");
    String responsibilities = request.getParameter("responsibilities");
    String employercontactName = request.getParameter("employercontactName");
    String employercontactPhone = request.getParameter("employercontactPhone");
    
    logger.info("city_Previousorg" + city_Previousorg);
    logger.info("country_id_prevorg" + country_id_prevorg);
    logger.info("state_id_prevorg" + state_id_prevorg);
    logger.info("lastSalary" + lastSalary);
    logger.info("bonus" + bonus);
    logger.info("currency_id_prevorg" + currency_id_prevorg);
    logger.info("responsibilities" + responsibilities);
    logger.info("employercontactName" + employercontactName);
    logger.info("employercontactPhone" + employercontactPhone);
    

    PreviousOrgDetails prevorg = new PreviousOrgDetails();
    prevorg.setApplicantId(new Long(applicantid).longValue());
    prevorg.setPrevOrgName(prevOrgName);
    prevorg.setRole(role);
    prevorg.setStartdate(startDate);
    prevorg.setEnddate(endDate);
    prevorg.setReasonforleave(reasonforleave);
    prevorg.setReportingToName(reportingto);
    prevorg.setCity(city_Previousorg);
    if ((!StringUtils.isNullOrEmpty(country_id_prevorg)) && (!country_id_prevorg.equals("0")) && (!country_id_prevorg.equals("null")))
    {
      Country cn = new Country();
      cn.setCountryId(new Long(country_id_prevorg).longValue());
      prevorg.setCountry(cn);
    }
    if ((!StringUtils.isNullOrEmpty(state_id_prevorg)) && (!state_id_prevorg.equals("0")) && (!state_id_prevorg.equals("null")))
    {
      State st = new State();
      st.setStateId(new Long(state_id_prevorg).longValue());
      prevorg.setState(st);
    }
    prevorg.setLastSalary(lastSalary);
    prevorg.setBonus(bonus);
    if ((!StringUtils.isNullOrEmpty(currency_id_prevorg)) && (!currency_id_prevorg.equals("0")) && (!currency_id_prevorg.equals("null")))
    {
      Currency cr = new Currency();
      cr.setCurrencyId(new Integer(currency_id_prevorg).intValue());
      prevorg.setCurrency(cr);
    }
    prevorg.setResponsibilities(responsibilities);
    prevorg.setEmployercontactName(employercontactName);
    prevorg.setEmployercontactPhone(employercontactPhone);
    if (user1 != null) {
      prevorg.setCreatedBy(user1.getUserName());
    }
    prevorg.setCreatedDate(new Date());
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantid, uuid);
    String screenCode = VariableDataCaptureUtil.getScreenCodeWorkExp(request, applicant);
    List errorList = BOFactory.getBusinessFilterBO().isWorkExpepenceMandatoryValueSucceeed(prevorg, VariableDataCaptureUtil.getUser(request), screenCode);
    if ((errorList != null) && (errorList.size() > 0))
    {
      logger.info("in error condition");
      request.setAttribute("error_list_work_exp", errorList);
    }
    else
    {
      BOFactory.getApplicantBO().savePreviousOrgDetails(prevorg);
    }
    List orgDetailsList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantid).longValue());
    applicantform.setPreviousOrgList(orgDetailsList);
    applicantform.setApplicantId(new Long(applicantid).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("getpreviousOrgdetails");
  }
  
  public ActionForward loadResumeSources(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadResumeSources method");
    String typeid = request.getParameter("typeid");
    logger.info("typeid" + typeid);
    String applicantId = request.getParameter("applicantId");
    ApplicantForm applicantform = (ApplicantForm)form;
    if ((typeid != null) && (new Integer(typeid).intValue() == 5))
    {
      List vendorList = UserDAO.getAllActiveVendors();
      applicantform.setVendorsList(vendorList);
    }
    else if ((typeid == null) || (new Integer(typeid).intValue() != 1))
    {
      List sourceList = BOFactory.getLovBO().getAllResumeSource(new Integer(typeid).intValue());
      applicantform.setSourceList(sourceList);
    }
    request.setAttribute("soucetypeid", typeid);
    if ((applicantId != null) && (applicantId.length() > 0))
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
      
      applicantform.setEmployeecode(applicant.getEmployeecode());
      applicantform.setReferrerName(applicant.getReferrerName());
      applicantform.setReferrerEmail(applicant.getReferrerEmail());
    }
    return mapping.findForward("resumeSources");
  }
  
  public ActionForward editapplicantforRefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editapplicantforRefferal method");
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuId = request.getParameter("uuId");
    logger.info("user1  : " + user1);
    logger.info("uuId  : " + uuId);
    logger.info("applicantId  : " + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    logger.info("applicant : " + applicant);
    

    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
      File file = new File(filePath);
      if (!file.exists()) {
        file.mkdirs();
      }
      RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
      
      int length = (int)blob.length();
      byte[] _blob = blob.getBytes(1L, length);
      raf.write(_blob);
      raf.close();
      
      filePath = applicant.getResumename();
      request.setAttribute("filePath", filePath);
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    

    request.setAttribute("applicantsaved", "no");
    request.setAttribute("applicantId", applicantId);
    
    request.setAttribute("uuid", uuId);
    return mapping.findForward("createPageforRefferal");
  }
  
  public ActionForward editapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editapplicant method");
    User user1 = (User)request.getSession().getAttribute("agency_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
      File file = new File(filePath);
      if (!file.exists()) {
        file.mkdirs();
      }
      RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
      
      int length = (int)blob.length();
      byte[] _blob = blob.getBytes(1L, length);
      raf.write(_blob);
      raf.close();
      
      filePath = applicant.getResumename();
      request.setAttribute("filePath", filePath);
    }
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListForForm(user1.getSuper_user_key());
    
    applicantform.setJobtitleList(jobtitleList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    applicantform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    applicantform.setStateList(stateList);
    
    applicantform.fromValue(applicant, request);
    
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(applicant.getApplicantId());
    applicantform.setPreviousOrgList(orgList);
    applicantform.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
    applicantform.setSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(user1.getSuper_user_key()));
    applicantform.setSkillYearsList(Constant.skillsYearsList);
    applicantform.setSkillRatingList(Constant.skillsRatingsList);
    List appskillList = BOFactory.getApplicantBO().getSkillsByApplicant(applicant.getApplicantId());
    applicantform.setApplicantSkillList(appskillList);
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    applicantform.setSourceTypeList(soucetypeList);
    if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 5))
    {
      List vendorList = UserDAO.getAllActiveVendors();
      applicantform.setVendorsList(vendorList);
    }
    else if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 1))
    {
      applicantform.setReferrerEmail(applicant.getReferrerEmail());
      applicantform.setReferrerName(applicant.getReferrerName());
    }
    else if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() > 0))
    {
      List sourceList = BOFactory.getLovBO().getAllResumeSource(applicant.getResumesourcetype().getResumeSourceTypeId());
      applicantform.setSourceList(sourceList);
    }
    else
    {
      applicantform.setSourceList(new ArrayList());
    }
    if ((user1 != null) && 
      (user1.getType() != null) && (user1.getType().equals("Vendor")))
    {
      applicantform.setSourceTypeId(5);
      applicantform.setVendorId(user1.getUserId());
      applicantform.setHidesource("vendor");
    }
    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("createPage");
  }
  
  public ActionForward actionattachmentaddscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside actionattachmentaddscr method");
    String applicantId = request.getParameter("applicantId");
    
    String appuuid = request.getParameter("secureid");
    String idvalue = request.getParameter("idvalue");
    String actionname = request.getParameter("action");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    List attachmentList = ApplicantUserDAO.getActionAttachmentList(new Long(idvalue).longValue(), actionname);
    applicantform.setActionAttachmentList(attachmentList);
    request.setAttribute("action_name", actionname);
    request.setAttribute("idvalue", idvalue);
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward actionattachmentadd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside actionattachmentadd method");
    String applicantId = request.getParameter("applicantId");
    String filedetails = request.getParameter("filedetails");
    String appuuid = request.getParameter("secureid");
    String idvalue = request.getParameter("idvalue");
    String actionname = request.getParameter("action");
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    

    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, appuuid);
    
    FormFile myFile = applicantform.getAttachmentdata();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (!StringUtils.isNullOrEmpty(fileName))
      {
        ActionsAttachment appattach = new ActionsAttachment();
        
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        

        appattach.setIdvalue(new Long(idvalue).longValue());
        appattach.setAction(actionname);
        appattach.setAttahmentdetails(filedetails);
        appattach.setApplicantId(new Long(applicantId).longValue());
        if (user1 != null) {
          appattach.setCreatedBy(user1.getEmployeeemail());
        }
        appattach.setCreatedDate(new Date());
        appattach.setUuid(UUID.randomUUID().toString());
        appattach.setAppuuid(appuuid);
        ApplicantUserDAO.saveActionAttachment(appattach);
        

        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "actionAttachment" + File.separator + appattach.getUuid() + File.separator;
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
      }
    }
    List attachmentList = ApplicantUserDAO.getActionAttachmentList(new Long(idvalue).longValue(), actionname);
    applicantform.setActionAttachmentList(attachmentList);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(appuuid);
    request.setAttribute("action_name", actionname);
    request.setAttribute("idvalue", idvalue);
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward requistionApplicantDetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requistionApplicantDetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if ((attachlist != null) && (attachlist.size() > 0)) {
        applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
      }
      if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
      {
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
        
        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        
        filePath = applicant.getResumename();
        request.setAttribute("filePath", filePath);
      }
    }
    if (applicant != null)
    {
      List eventList = BOFactory.getApplicantBO().getApplicationEventList(applicantId);
      logger.info("interview events size" + eventList.size());
      applicantform.fromValue(applicant, request);
      applicantform.setApplicantuser(ApplicantUserDAO.getApplicantUser(applicant.getApplicantId()));
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
      applicantform.setEventList(eventList);
    }
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
    applicantform.setPreviousOrgList(orgList);
    List appskillList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantId).longValue());
    applicantform.setApplicantSkillList(appskillList);
    
    User hiringmgr = BOFactory.getJobRequistionBO().getHiringManagerByReqId(applicantform.getRequitionId());
    Recruiter recruiter = BOFactory.getJobRequistionBO().getRecruiterByReqId(applicantform.getRequitionId());
    
    applicantform.setHiringmgr(hiringmgr);
    applicantform.setRecruiter(recruiter);
    request.getSession().setAttribute("app_id", applicantId);
    return mapping.findForward("jobrequistiontreelist");
  }
  
  public ActionForward resumeDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resumeDetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null) {
      applicantform.fromValue(applicant, request);
    }
    return mapping.findForward("resumeDetailsajax");
  }
  
  public ActionForward addcommenttreeajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addcommenttreeajax method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String isappvisible = request.getParameter("isappvisible");
    String rurl = request.getParameter("rurl");
    
    User user1 = (User)request.getSession().getAttribute("agency_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = new ApplicantComment();
    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    comment.setComment(applicantform.getComment());
    comment.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    comment.setByUserId(user1.getUserId());
    comment.setCreatedDate(new Date());
    comment.setBytype(user1.getRole().getRoleName());
    comment.setIsVisibleToApplicant(isappvisible);
    
    ApplicantUserDAO.saveApplicantComment(comment);
    


    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    
    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    if (StringUtils.isNullOrEmpty(rurl)) {
      rurl = rurl.substring(rurl.indexOf(request.getContextPath()));
    }
    String url = rurl;
    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    return forward;
  }
  
  public ActionForward compareapplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside compareapplicants method");
    String applicantids = request.getParameter("applicantids");
    ApplicantForm applicantform = (ApplicantForm)form;
    List applicantList = new ArrayList();
    List applicantListdetails = new ArrayList();
    if (!StringUtils.isNullOrEmpty(applicantids))
    {
      applicantids = applicantids.substring(0, applicantids.length() - 1);
      
      applicantList = StringUtils.tokenizeString(applicantids, ",");
    }
    logger.info("applicantids" + applicantids);
    for (int i = 0; i < applicantList.size(); i++)
    {
      String appliantId = (String)applicantList.get(i);
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(appliantId);
      applicantListdetails.add(applicant);
    }
    applicantform.setApplicantList(applicantListdetails);
    return mapping.findForward("compareapplicants");
  }
  
  public ActionForward applicantDetailsForCompare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailsForCompare method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if ((attachlist != null) && (attachlist.size() > 0)) {
        applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
      }
      if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
      {
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
        
        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        
        filePath = applicant.getResumename();
        request.setAttribute("filePath", filePath);
      }
    }
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
    }
    User hiringmgr = BOFactory.getJobRequistionBO().getHiringManagerByReqId(applicantform.getRequitionId());
    Recruiter recruiter = BOFactory.getJobRequistionBO().getRecruiterByReqId(applicantform.getRequitionId());
    
    applicantform.setHiringmgr(hiringmgr);
    applicantform.setRecruiter(recruiter);
    
    return mapping.findForward("applicantDetailsForCompare");
  }
  
  public ActionForward applicantlogdetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlogdetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      List eventList = BOFactory.getApplicantBO().getApplicationEventListWithRatings(applicantId);
      logger.info("interview events size" + eventList.size());
      
      applicantform.setEventList(eventList);
    }
    return mapping.findForward("applicantlogdetailsajax");
  }
}
