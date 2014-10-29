package com.test;

import com.bean.testengine.MockTestQuestion;
import com.resources.Constant;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader
{
  public static DocumentBuilderFactory dbf;
  public static DocumentBuilder db;
  static HashMap mapQuestions;
  
  public static HashMap getQuestions()
  {
    return mapQuestions;
  }
  
  public static Document getQuestionsXML(String filename)
  {
    Document xmlDoc = null;
    try
    {
      File f = new File(Constant.getValue("ATTACHMENT_PATH") + filename);
      
      xmlDoc = db.parse(f);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return xmlDoc;
  }
  
  public static List parseXML(String filename)
  {
    List mockqlist = new ArrayList();
    int numberQuestions = 0;
    mapQuestions = new HashMap();
    Node questionsNode = null;
    Document questionDoc = getQuestionsXML(filename);
    Element objElm = questionDoc.getDocumentElement();
    NodeList questions = objElm.getElementsByTagName("fq");
    for (int i = 0; i < questions.getLength(); i++)
    {
      MockTestQuestion objQues = new MockTestQuestion();
      questionsNode = questions.item(i);
      Element elmFQuestion = (Element)questionsNode;
      NodeList nlQuestion = elmFQuestion.getElementsByTagName("q");
      Node qNode = nlQuestion.item(0);
      String strTemp = qNode.getFirstChild().getNodeValue().trim();
      objQues.setQuestion(strTemp);
      NodeList nlAnswers = elmFQuestion.getElementsByTagName("a");
      for (int i1 = 0; i1 < nlAnswers.getLength(); i1++)
      {
        Node qAnswer = nlAnswers.item(i1);
        strTemp = qAnswer.getFirstChild().getNodeValue().trim();
        if (i1 == 0)
        {
          System.out.println("1:" + strTemp);
          objQues.setAns1(strTemp);
        }
        if (i1 == 1)
        {
          System.out.println("2:" + strTemp);
          objQues.setAns2(strTemp);
        }
        if (i1 == 2)
        {
          System.out.println("3:" + strTemp);
          objQues.setAns3(strTemp);
        }
        if (i1 == 3)
        {
          System.out.println("4:" + strTemp);
          objQues.setAns4(strTemp);
        }
      }
      NodeList nlCorrect = elmFQuestion.getElementsByTagName("c");
      Node cNode = nlCorrect.item(0);
      strTemp = cNode.getFirstChild().getNodeValue().trim();
      objQues.setCorrect(Integer.parseInt(strTemp));
      NodeList nlExplanation = elmFQuestion.getElementsByTagName("e");
      Node eNode = nlExplanation.item(0);
      strTemp = eNode.getFirstChild().getNodeValue().trim();
      objQues.setDesc(strTemp);
      mapQuestions.put(Integer.toString(i), objQues);
      objQues.setCatId(1);
      mockqlist.add(objQues);
    }
    return mockqlist;
  }
  
  public static List parsePMPXML(String filename)
  {
    List mockqlist = new ArrayList();
    int numberQuestions = 0;
    mapQuestions = new HashMap();
    Node questionsNode = null;
    Document questionDoc = getQuestionsXML(filename);
    Element objElm = questionDoc.getDocumentElement();
    NodeList questions = objElm.getElementsByTagName("fq");
    for (int i = 0; i < questions.getLength(); i++)
    {
      MockTestQuestion objQues = new MockTestQuestion();
      objQues.setQuestionno(i + 1);
      questionsNode = questions.item(i);
      Element elmFQuestion = (Element)questionsNode;
      NodeList nlQuestion = elmFQuestion.getElementsByTagName("q");
      Node qNode = nlQuestion.item(0);
      String strTemp = qNode.getFirstChild().getNodeValue().trim();
      objQues.setQuestion(strTemp);
      NodeList nlAnswers = elmFQuestion.getElementsByTagName("a");
      for (int i1 = 0; i1 < nlAnswers.getLength(); i1++)
      {
        Node qAnswer = nlAnswers.item(i1);
        strTemp = qAnswer.getFirstChild().getNodeValue().trim();
        if (i1 == 0)
        {
          System.out.println("1:" + strTemp);
          objQues.setAns1(strTemp);
        }
        if (i1 == 1)
        {
          System.out.println("2:" + strTemp);
          objQues.setAns2(strTemp);
        }
        if (i1 == 2)
        {
          System.out.println("3:" + strTemp);
          objQues.setAns3(strTemp);
        }
        if (i1 == 3)
        {
          System.out.println("4:" + strTemp);
          objQues.setAns4(strTemp);
        }
      }
      NodeList nlCorrect = elmFQuestion.getElementsByTagName("c");
      Node cNode = nlCorrect.item(0);
      strTemp = cNode.getFirstChild().getNodeValue().trim();
      objQues.setCorrect(Integer.parseInt(strTemp));
      NodeList nlExplanation = elmFQuestion.getElementsByTagName("e");
      Node eNode = nlExplanation.item(0);
      strTemp = eNode.getFirstChild().getNodeValue().trim();
      objQues.setDesc(strTemp);
      mapQuestions.put(Integer.toString(i), objQues);
      objQues.setCatId(1);
      mockqlist.add(objQues);
    }
    return mockqlist;
  }
  
  static
  {
    try
    {
      dbf = DocumentBuilderFactory.newInstance();
      db = dbf.newDocumentBuilder();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
