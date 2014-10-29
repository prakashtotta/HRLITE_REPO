package com.util;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;

public class FileExtractor
{
  protected static final Logger logger = Logger.getLogger(FileExtractor.class);
  
  public String getFileContent(String filePath)
  {
    logger.info("inside getFileContent");
    String content = "";
    if ((filePath != null) && (filePath.lastIndexOf(".docx") > 0)) {
      content = parsedocxfileReturnString(filePath);
    } else if ((filePath != null) && (filePath.lastIndexOf(".txt") > 0)) {
      content = readFile(filePath);
    } else if ((filePath != null) && (filePath.lastIndexOf(".pdf") > 0)) {
      content = readPdfFile(filePath);
    } else if ((filePath != null) && (filePath.lastIndexOf(".doc") > 0)) {
      content = readWordFile(filePath);
    }
    return content;
  }
  
  private String readWordFile(String filePath)
  {
    String content = "";
    StringBuffer stbfr = new StringBuffer("");
    
    POIFSFileSystem fs = null;
    try
    {
      fs = new POIFSFileSystem(new FileInputStream(filePath));
      HWPFDocument doc = new HWPFDocument(fs);
      
      WordExtractor we = new WordExtractor(doc);
      

      String[] paragraphs = we.getParagraphText();
      for (int i = 0; i < paragraphs.length; i++)
      {
        String pcontent = paragraphs[i].toString();
        pcontent = pcontent.replaceAll("\024\024", "");
        pcontent = pcontent.replaceAll("\024", "");
        stbfr.append(pcontent);
      }
      content = stbfr.toString();
    }
    catch (Exception e)
    {
      logger.info("Error in readWordFile", e);
      content = stbfr.toString();
    }
    return content;
  }
  
  private String readPdfFile(String filePath)
  {
    logger.info("inside readPdfFile");
    String content = "";
    StringBuffer stbfr = new StringBuffer("");
    try
    {
      PdfReader reader = new PdfReader(filePath);
      int n = reader.getNumberOfPages();
      
      PdfTextExtractor parser = new PdfTextExtractor(reader);
      for (int i = 1; i <= n; i++)
      {
        String str = parser.getTextFromPage(i);
        stbfr.append(str);
      }
      content = stbfr.toString();
    }
    catch (Exception e)
    {
      logger.info("Error in readPdfFile", e);
      content = stbfr.toString();
    }
    return content;
  }
  
  private String readFile(String filePath)
  {
    logger.info("inside readFile");
    String content = "";
    File file = new File(filePath);
    
    StringBuffer strContent = new StringBuffer("");
    FileInputStream fin = null;
    try
    {
      fin = new FileInputStream(file);
      int ch;
      while ((ch = fin.read()) != -1) {
        strContent.append((char)ch);
      }
      fin.close();
    }
    catch (Exception e)
    {
      logger.info("error in readFile" + e);
    }
    content = strContent.toString();
    
    return content;
  }
  
  private String parsedocxfileReturnString(String filepath)
  {
    logger.info("inside parsedocxfileReturnString");
    String content = "";
    try
    {
      File f = new File(filepath);
      if (parsedocxOnlycontent(filepath) != null)
      {
        POITextExtractor extractor = ExtractorFactory.createExtractor(f);
        content = extractor.getText();
      }
    }
    catch (Exception e)
    {
      logger.info("Exception reading docx file." + e);
    }
    return content;
  }
  
  private String parsedocxOnlycontent(String filepath)
  {
    ZipFile docxfile = null;
    try
    {
      docxfile = new ZipFile(filepath);
      
      InputStream in = null;
      
      ZipEntry ze = docxfile.getEntry("word/document.xml");
      
      in = docxfile.getInputStream(ze);
      

      Document document = null;
      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      
      DocumentBuilder builder = factory.newDocumentBuilder();
      
      document = builder.parse(in);
      














      return "";
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Error reading file." + e);
      return null;
    }
    finally
    {
      try
      {
        docxfile.close();
      }
      catch (IOException ioe)
      {
        logger.info("Exception closing file." + ioe);
      }
    }
  }
  
  public static void main(String[] args)
  {
    FileExtractor fx = new FileExtractor();
    fx.readPdfFile("c:\\HR_Create_Job_Requisition.pdf");
  }
}
