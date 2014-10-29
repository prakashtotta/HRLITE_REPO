package com.test;

import java.util.List;
import org.apache.log4j.Logger;

public class MockUtil
{
  protected static final Logger logger = Logger.getLogger(MockUtil.class);
  
  public List getMockQuestionList(int catid, String filename)
    throws Exception
  {
    logger.info("inside addMockQuestions");
    try
    {
      List mockqlist = XMLReader.parsePMPXML(filename);
      if ((mockqlist == null) || (mockqlist.size() == 0)) {
        throw new Exception("XML File format is incorrect.");
      }
      logger.info("mockqlist size" + mockqlist.size());
      return mockqlist;
    }
    catch (Exception e)
    {
      throw e;
    }
  }
}
