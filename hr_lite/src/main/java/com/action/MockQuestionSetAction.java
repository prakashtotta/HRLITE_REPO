package com.action;

import com.bean.BulkUploadTask;
import com.bean.User;
import com.bean.testengine.MockQuestionSet;
import com.bo.BOFactory;
import com.common.Common;
import com.form.MockQuestionSetForm;
import com.manager.BulkTaskManager;
import com.resources.Constant;
import com.test.MockTestBO;
import com.util.BulkUploadTaskUtil;
import java.io.File;
import java.io.RandomAccessFile;
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

public class MockQuestionSetAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(MockQuestionSetAction.class);
  
  public ActionForward mockquestionsetListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mockquestionsetListpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    MockQuestionSetForm appuserform = (MockQuestionSetForm)form;
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("mockquestionsetList");
  }
  
  public ActionForward createMockQuestionSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    MockQuestionSetForm appuserform = (MockQuestionSetForm)form;
    return mapping.findForward("createmockquestionset");
  }
  
  public ActionForward saveMockQuestionSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveMockQuestionSet method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    MockQuestionSetForm mockquestionsetForm = (MockQuestionSetForm)form;
    MockQuestionSet mockquestionSet = new MockQuestionSet();
    mockquestionSet.setCreatedBy(user1.getUserName());
    mockquestionSet.setCreatedDate(new Date());
    mockquestionSet.setSuper_user_key(user1.getSuper_user_key());
    toValue(mockquestionSet, mockquestionsetForm);
    try
    {
      logger.info("saveMockQuestionSet before util call");
      

      mockquestionSet = BOFactory.getMockTestBO().saveMockQuestionSet(mockquestionSet);
      

      request.setAttribute("saveMockQuestionSet", "yes");
    }
    catch (Exception e)
    {
      request.setAttribute("saveMockQuestionSet", "error");
    }
    String filePath = Constant.getValue("ATTACHMENT_PATH") + user1.getUserName() + File.separator;
    

    FormFile myFile = mockquestionsetForm.getAttachmentData();
    
    String fileName = "";
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      logger.info(Integer.valueOf(fileSize));
      if (fileSize > 10240000)
      {
        request.setAttribute("filesizeexceed", "yes");
        request.setAttribute("uploadsucceed", "no");
      }
      else
      {
        byte[] fileData = myFile.getFileData();
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        

        BulkUploadTask task = new BulkUploadTask();
        task.setUploadedFileName(fileName);
        BulkUploadTaskUtil bulkupload = new BulkUploadTaskUtil();
        bulkupload.setTasktype(Common.BULK_UPLOAD_EXAM);
        bulkupload.setUser(user1);
        bulkupload.setTask(task);
        bulkupload.setExamId(mockquestionSet.getCatId());
        BulkTaskManager.bulkupload(bulkupload);
        
        request.setAttribute("uploadsucceed", "yes");
      }
    }
    toForm(mockquestionsetForm, mockquestionSet);
    


    return mapping.findForward("createmockquestionset");
  }
  
  public ActionForward MockQuestionSetDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside MockQuestionSetDetails method");
    
    String readonlymode = request.getParameter("readonlymode");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String catid = request.getParameter("catid");
    MockQuestionSetForm mockquestionsetForm = (MockQuestionSetForm)form;
    MockQuestionSet mockquestionSet = BOFactory.getMockTestBO().getMockQuestionSeDetails(catid);
    toForm(mockquestionsetForm, mockquestionSet);
    request.setAttribute("readonlymode", readonlymode);
    return mapping.findForward("createmockquestionset");
  }
  
  public ActionForward updateMockQuestionSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateMockQuestionSet method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    MockQuestionSetForm mockquestionsetForm = (MockQuestionSetForm)form;
    String catid = request.getParameter("catid");
    MockQuestionSet mockquestionSet = BOFactory.getMockTestBO().getMockQuestionSeDetails(catid);
    mockquestionSet.setUpdatedBy(user1.getUserName());
    mockquestionSet.setUpdatedDate(new Date());
    
    toValue(mockquestionSet, mockquestionsetForm);
    try
    {
      logger.info("saveMockQuestionSet before util call");
      

      mockquestionSet = BOFactory.getMockTestBO().updateMockQuestionSet(mockquestionSet);
      

      request.setAttribute("updateMockQuestionSet", "yes");
    }
    catch (Exception e)
    {
      request.setAttribute("saveMockQuestionSet", "error");
    }
    logger.info("after update ..1");
    String filePath = Constant.getValue("ATTACHMENT_PATH") + user1.getUserName() + File.separator;
    

    FormFile myFile = mockquestionsetForm.getAttachmentData();
    logger.info("after update ..2 >> myfile " + myFile.getFileName());
    String fileName = "";
    if (myFile.getFileSize() != 0)
    {
      logger.info("inside >> ");
      String contentType = myFile.getContentType();
      fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      logger.info("file size >> " + fileSize);
      if (fileSize > 10240000)
      {
        request.setAttribute("filesizeexceed", "yes");
        request.setAttribute("uploadsucceed", "no");
      }
      else
      {
        byte[] fileData = myFile.getFileData();
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        

        BulkUploadTask task = new BulkUploadTask();
        task.setUploadedFileName(fileName);
        BulkUploadTaskUtil bulkupload = new BulkUploadTaskUtil();
        bulkupload.setTasktype(Common.BULK_UPLOAD_EXAM);
        bulkupload.setUser(user1);
        bulkupload.setTask(task);
        bulkupload.setExamId(mockquestionSet.getCatId());
        BulkTaskManager.bulkupload(bulkupload);
        
        request.setAttribute("uploadsucceed", "yes");
      }
    }
    mockquestionSet = BOFactory.getMockTestBO().getMockQuestionSet(mockquestionSet.getCatId());
    
    toForm(mockquestionsetForm, mockquestionSet);
    

    return mapping.findForward("createmockquestionset");
  }
  
  public ActionForward deleteMockQuestionSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteMockQuestionSet method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String catid = request.getParameter("catid");
    MockQuestionSetForm mockquestionsetForm = (MockQuestionSetForm)form;
    MockQuestionSet mockquestionSet = BOFactory.getMockTestBO().getMockQuestionSeDetails(catid);
    toDelete(mockquestionSet, mockquestionsetForm);
    BOFactory.getMockTestBO().updateMockQuestionSet(mockquestionSet);
    request.setAttribute("deleteMockQuestionSet", "yes");
    return mapping.findForward("createmockquestionset");
  }
  
  public ActionForward searchMockQuestionSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchMockQuestionSet method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    MockQuestionSetForm mockquestionsetForm = (MockQuestionSetForm)form;
    mockquestionsetForm.setName(mockquestionsetForm.getName());
    mockquestionsetForm.setDisplayName(mockquestionsetForm.getDisplayName());
    mockquestionsetForm.setDescription(mockquestionsetForm.getDescription());
    request.setAttribute("searchpagedisplay", "no");
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("mockquestionsetList");
  }
  
  public ActionForward deleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteAttachment method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    MockQuestionSetForm mockquestionsetForm = (MockQuestionSetForm)form;
    
    String catid = request.getParameter("catid");
    MockQuestionSet mockquestionSet = BOFactory.getMockTestBO().getMockQuestionSeDetails(catid);
    mockquestionSet.setAttachmentName(null);
    mockquestionSet.setAttachmentdata(null);
    
    BOFactory.getMockTestBO().updateMockQuestionSet(mockquestionSet);
    toForm(mockquestionsetForm, mockquestionSet);
    
    return mapping.findForward("createmockquestionset");
  }
  
  public void toValue(MockQuestionSet mockquestionSet, MockQuestionSetForm mockquestionsetForm)
    throws Exception
  {
    mockquestionSet.setName(mockquestionsetForm.getName());
    mockquestionSet.setDisplayName(mockquestionsetForm.getDisplayName());
    mockquestionSet.setDescription(mockquestionsetForm.getDescription());
    mockquestionSet.setTimeLimit(mockquestionsetForm.getTimeLimit());
    FormFile myFile = mockquestionsetForm.getAttachmentData();
    if ((myFile != null) && (myFile.getFileSize() != 0))
    {
      logger.info("inside my if of to value method  .... ");
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] filedata = myFile.getFileData();
      mockquestionSet.setAttachmentName(fileName);
      Blob blob = null;
      blob = new SerialBlob(filedata);
      mockquestionSet.setAttachmentdata(blob);
    }
    mockquestionSet.setStatus("A");
  }
  
  public void toForm(MockQuestionSetForm mockquestionsetForm, MockQuestionSet mockquestionSet)
  {
    mockquestionsetForm.setCatId(mockquestionSet.getCatId());
    mockquestionsetForm.setName(mockquestionSet.getName());
    mockquestionsetForm.setDisplayName(mockquestionSet.getDisplayName());
    mockquestionsetForm.setTimeLimit(mockquestionSet.getTimeLimit());
    mockquestionsetForm.setAttachmentName(mockquestionSet.getAttachmentName());
    mockquestionsetForm.setDescription(mockquestionSet.getDescription());
    mockquestionsetForm.setCreatedBy(mockquestionSet.getCreatedBy());
  }
  
  public void toDelete(MockQuestionSet mockquestionSet, MockQuestionSetForm mockquestionsetForm)
  {
    mockquestionSet.setStatus("D");
  }
}
