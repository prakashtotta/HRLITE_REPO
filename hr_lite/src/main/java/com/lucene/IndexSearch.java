package com.lucene;

import com.bean.ApplicantScoring;
import com.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class IndexSearch
{
  protected static final Logger logger = Logger.getLogger(IndexSearch.class);
  
  public List search(String queryString)
    throws Exception
  {
    List resultList = new ArrayList();
    logger.info("queryString" + queryString);
    




    searchIndex(queryString, resultList);
    


    logger.info("resultList size satya" + resultList.size());
    logger.info("resultList.toString()" + resultList.toString());
    
    return resultList;
  }
  
  public static ApplicantScoring searchApplicantScoreKeywords(ApplicantScoring appscore)
    throws Exception
  {
    logger.info("inside searchApplicantScoreKeyword");
    List resultList = new ArrayList();
    logger.info("queryString" + appscore.getKeyword());
    




    searchApplicantScoreKeyword(appscore);
    


    logger.info("score for applicant id:" + appscore.getApplicantId() + " keyword:" + appscore.getKeyword() + " score:" + appscore.getScore());
    
    return appscore;
  }
  
  public List<Long> searchRequistion(String queryString)
    throws Exception
  {
    List<Long> resultList = new ArrayList();
    logger.info("queryString" + queryString);
    




    searchIndexReq(queryString, resultList);
    


    logger.info("resultList size satya req" + resultList.size());
    

    return resultList;
  }
  
  public List<Long> searchFaceBookUsers(String queryString)
    throws Exception
  {
    List<Long> resultList = new ArrayList();
    logger.info("queryString" + queryString);
    
    searchIndexFbUsers(queryString, resultList);
    
    logger.info("resultList size satya fusers" + resultList.size());
    

    return resultList;
  }
  
  public List<Long> searchFaceBookJobs(String queryString)
    throws Exception
  {
    List<Long> resultList = new ArrayList();
    logger.info("queryString" + queryString);
    
    searchIndexFbJobs(queryString, resultList);
    
    logger.info("resultList size satya fbjobs" + resultList.size());
    

    return resultList;
  }
  
  private static void searchIndex(String searchString, List resultList)
    throws IOException, ParseException
  {
    File INDEX_DIRECTORY = new File(IndexCreator.indexfileDir_lu);
    
    SimpleFSDirectory directory = new SimpleFSDirectory(INDEX_DIRECTORY);
    

    IndexReader indexReader = IndexReader.open(directory);
    IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    

    QueryParser queryParser = new QueryParser(Version.LUCENE_34, "content", new StandardAnalyzer(Version.LUCENE_34));
    


    Query query = queryParser.parse(searchString);
    
    int hitsPerPage = 15;
    
    TopScoreDocCollector collector = TopScoreDocCollector.create(5 * hitsPerPage, false);
    indexSearcher.search(query, collector);
    
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    int hitCount = collector.getTotalHits();
    for (int i = 0; i < hits.length; i++)
    {
      ScoreDoc scoreDoc = hits[i];
      int docId = scoreDoc.doc;
      float docScore = scoreDoc.score;
      Document doc = indexSearcher.doc(docId);
      

      logger.info("applicantId" + doc.get("title") + "docScore: " + docScore);
      logger.info("Content: " + doc.get("content"));
      String appid = doc.get("title");
      if ((!StringUtils.isNullOrEmpty(appid)) && (!appid.equals("0")) && 
        (!resultList.contains(appid))) {
        resultList.add(appid);
      }
    }
  }
  
  private static void searchApplicantScoreKeyword(ApplicantScoring appscore)
    throws IOException, ParseException
  {
    File INDEX_DIRECTORY = new File(IndexCreator.indexfileDir_lu);
    
    SimpleFSDirectory directory = new SimpleFSDirectory(INDEX_DIRECTORY);
    

    IndexReader indexReader = IndexReader.open(directory);
    IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    

    QueryParser queryParser = new QueryParser(Version.LUCENE_34, "content", new StandardAnalyzer(Version.LUCENE_34));
    


    Query query = queryParser.parse(appscore.getKeyword());
    
    int hitsPerPage = 15;
    
    TopScoreDocCollector collector = TopScoreDocCollector.create(5 * hitsPerPage, false);
    indexSearcher.search(query, collector);
    
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    int hitCount = collector.getTotalHits();
    for (int i = 0; i < hits.length; i++)
    {
      ScoreDoc scoreDoc = hits[i];
      int docId = scoreDoc.doc;
      float docScore = scoreDoc.score;
      Document doc = indexSearcher.doc(docId);
      


      String appid = doc.get("title");
      if ((!StringUtils.isNullOrEmpty(appid)) && (!appid.equals("0")))
      {
        long appidv = new Long(appid).longValue();
        if (appidv == appscore.getApplicantId())
        {
          appscore.setScore(docScore);
          logger.info("applicantId" + doc.get("title") + "docScore: " + docScore);
          break;
        }
      }
    }
  }
  
  private static void searchIndexFbUsers(String searchString, List<Long> resultList)
    throws IOException, ParseException
  {
    File INDEX_DIRECTORY = new File(IndexCreator.indexfileDirFacebookUsers_lu);
    
    SimpleFSDirectory directory = new SimpleFSDirectory(INDEX_DIRECTORY);
    

    IndexReader indexReader = IndexReader.open(directory);
    IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    

    QueryParser queryParser = new QueryParser(Version.LUCENE_34, "content_fbuser", new StandardAnalyzer(Version.LUCENE_34));
    


    Query query = queryParser.parse(searchString);
    
    int hitsPerPage = 15;
    
    TopScoreDocCollector collector = TopScoreDocCollector.create(5 * hitsPerPage, false);
    indexSearcher.search(query, collector);
    
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    int hitCount = collector.getTotalHits();
    for (int i = 0; i < hits.length; i++)
    {
      ScoreDoc scoreDoc = hits[i];
      int docId = scoreDoc.doc;
      float docScore = scoreDoc.score;
      Document doc = indexSearcher.doc(docId);
      logger.info("docId: " + docId + "\t" + "docScore: " + docScore);
      logger.info("Fbuser id" + doc.get("title_fbuser") + "docScore: " + docScore);
      
      String appid = doc.get("title_fbuser");
      if ((!StringUtils.isNullOrEmpty(appid)) && (!appid.equals("0")))
      {
        long req_id = new Long(appid).longValue();
        if (!resultList.contains(Long.valueOf(req_id))) {
          resultList.add(Long.valueOf(req_id));
        }
      }
    }
  }
  
  private static void searchIndexFbJobs(String searchString, List<Long> resultList)
    throws IOException, ParseException
  {
    File INDEX_DIRECTORY = new File(IndexCreator.indexfileDirFacebookJobs_lu);
    
    SimpleFSDirectory directory = new SimpleFSDirectory(INDEX_DIRECTORY);
    

    IndexReader indexReader = IndexReader.open(directory);
    IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    

    QueryParser queryParser = new QueryParser(Version.LUCENE_34, "content_fbjob", new StandardAnalyzer(Version.LUCENE_34));
    


    Query query = queryParser.parse(searchString);
    
    int hitsPerPage = 15;
    
    TopScoreDocCollector collector = TopScoreDocCollector.create(5 * hitsPerPage, false);
    indexSearcher.search(query, collector);
    
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    int hitCount = collector.getTotalHits();
    for (int i = 0; i < hits.length; i++)
    {
      ScoreDoc scoreDoc = hits[i];
      int docId = scoreDoc.doc;
      float docScore = scoreDoc.score;
      Document doc = indexSearcher.doc(docId);
      logger.info("docId: " + docId + "\t" + "docScore: " + docScore);
      logger.info("Fbjob id" + doc.get("title_fbjob") + "docScore: " + docScore);
      
      String appid = doc.get("title_fbjob");
      if ((!StringUtils.isNullOrEmpty(appid)) && (!appid.equals("0")))
      {
        long req_id = new Long(appid).longValue();
        if (!resultList.contains(Long.valueOf(req_id))) {
          resultList.add(Long.valueOf(req_id));
        }
      }
    }
  }
  
  private static void searchIndexReq(String searchString, List<Long> resultList)
    throws IOException, ParseException
  {
    File INDEX_DIRECTORY = new File(IndexCreator.indexfileDirRequistion_lu);
    
    SimpleFSDirectory directory = new SimpleFSDirectory(INDEX_DIRECTORY);
    

    IndexReader indexReader = IndexReader.open(directory);
    IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    

    QueryParser queryParser = new QueryParser(Version.LUCENE_34, "content_req", new StandardAnalyzer(Version.LUCENE_34));
    


    Query query = queryParser.parse(searchString);
    
    int hitsPerPage = 15;
    
    TopScoreDocCollector collector = TopScoreDocCollector.create(5 * hitsPerPage, false);
    indexSearcher.search(query, collector);
    
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    int hitCount = collector.getTotalHits();
    for (int i = 0; i < hits.length; i++)
    {
      ScoreDoc scoreDoc = hits[i];
      int docId = scoreDoc.doc;
      float docScore = scoreDoc.score;
      Document doc = indexSearcher.doc(docId);
      logger.info("docId: " + docId + "\t" + "docScore: " + docScore);
      logger.info("Requistion uuid" + doc.get("title_req") + "docScore: " + docScore);
      
      String appid = doc.get("title_req");
      if ((!StringUtils.isNullOrEmpty(appid)) && (!appid.equals("0")))
      {
        long req_id = new Long(appid).longValue();
        if (!resultList.contains(Long.valueOf(req_id))) {
          resultList.add(Long.valueOf(req_id));
        }
      }
    }
  }
}
