package com.util;

import com.bean.ApplicantAttachments;
import com.google.code.javascribd.connection.ScribdClient;
import com.google.code.javascribd.connection.StreamableData;
import com.google.code.javascribd.docs.Upload;
import com.google.code.javascribd.docs.UploadResponse;
import com.google.code.javascribd.type.Access;
import com.google.code.javascribd.type.ApiKey;
import com.google.code.javascribd.type.DocumentId;
import com.google.code.javascribd.type.FileData;
import com.resources.Constant;
import java.io.File;
import org.apache.log4j.Logger;

public class ScribdUtil
{
  protected static final Logger logger = Logger.getLogger(ScribdUtil.class);
  
  public static void uploadFile(ApplicantAttachments attach)
  {
    logger.info("inside uploadFile scribd");
    try
    {
      ScribdClient client = new ScribdClient();
      ApiKey apiKey = new ApiKey(Constant.getValue("scribd.api.key"));
      String filePath1 = Constant.getValue("ATTACHMENT_PATH");
      String filePath = filePath1 + File.separator + "applicantAttachment" + File.separator + attach.getUuid() + File.separator;
      File file = new File(filePath + attach.getAttahmentname());
      StreamableData uploadData = new FileData(file);
      
      Upload upload = new Upload(apiKey, uploadData);
      
      String doctype = attach.getAttahmentname().substring(attach.getAttahmentname().lastIndexOf(".") + 1);
      
      upload.setDocType(doctype);
      upload.setAccess(Access.PRIVATE);
      
      UploadResponse response1 = (UploadResponse)client.execute(upload);
      
      attach.setScribddocumentid(response1.getDocId().intValue());
      attach.setScribddocumentkey(response1.getAccessKey());
      logger.info("document_id: %d\n" + response1.getDocId().intValue());
      logger.info("document_id: %s\n" + response1.getAccessKey());
    }
    catch (Exception e)
    {
      logger.info("error on uploadFile", e);
    }
  }
}
