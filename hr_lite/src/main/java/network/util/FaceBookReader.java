package network.util;

import com.bo.BOFactory;
import com.util.StringUtils;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import network.bean.FaceBookUser;
import network.bean.FbConcentration;
import network.bean.FbEmployer;
import network.bean.FbLocation;
import network.bean.FbPositions;
import network.bean.FbSchool;
import network.bean.FbUserEmployer;
import network.bean.FbUserSchools;
import network.bo.FbUserBO;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FaceBookReader
{
  protected static final Logger logger = Logger.getLogger(FaceBookReader.class);
  
  public List<FaceBookUser> getFriendsData(String facebookid, String sesstionKey)
  {
    logger.info("inside getFriendsData");
    List<FaceBookUser> flist = new ArrayList();
    try
    {
      String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid,name,first_name,last_name,username,sex,profile_url,timezone,locale,work,education,current_location%20FROM%20user%20WHERE%20uid%20IN%20(SELECT%20uid2%20FROM%20friend%20WHERE%20uid1=" + facebookid + ")&access_token=" + sesstionKey;
      
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      
      System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
      

      NodeList listOfusers = doc.getElementsByTagName("user");
      int totalPersons = listOfusers.getLength();
      System.out.println("Total no of people : " + totalPersons);
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        FaceBookUser fuser = new FaceBookUser();
        
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          fuser.setFacebookid(nValue.getNodeValue());
          
          nodelist = userElement.getElementsByTagName("name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFullName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("first_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFirstName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("last_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLastName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("username").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFuserName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("sex").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setGender(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("profile_url").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLink(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("timezone").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setTimezone(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("locale").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLocale(nValue.getNodeValue());
          }
          Node currentLocation = userElement.getElementsByTagName("current_location").item(0);
          
          NodeList cllist = currentLocation.getChildNodes();
          for (int cl = 0; cl < cllist.getLength(); cl++)
          {
            Node node = cllist.item(cl);
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("city"))) {
              fuser.setCity(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("state"))) {
              fuser.setState(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("country"))) {
              fuser.setCountry(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("zip"))) {
              fuser.setZip(node.getTextContent());
            }
          }
          List<FbUserEmployer> fbEmploerList = new ArrayList();
          nodelist = userElement.getElementsByTagName("work").item(0).getChildNodes();
          if ((nodelist != null) && (nodelist.getLength() > 0))
          {
            int po = 0;
            for (int n = 0; n < nodelist.getLength(); n++)
            {
              Node workNode = nodelist.item(n);
              if (workNode.getNodeType() == 1)
              {
                Node workElt = userElement.getElementsByTagName("work_elt").item(po);
                if (workElt != null)
                {
                  FbUserEmployer fbuseremp = new FbUserEmployer();
                  
                  fbuseremp.setLevel(po);
                  Node description = userElement.getElementsByTagName("description").item(po);
                  if ((description != null) && 
                    (description.getParentNode() != null) && (description.getParentNode().getNodeName() != null) && (description.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setDescription(description.getTextContent());
                  }
                  Node start_date = userElement.getElementsByTagName("start_date").item(po);
                  if ((start_date != null) && 
                    (start_date.getParentNode() != null) && (start_date.getParentNode().getNodeName() != null) && (start_date.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setStartDate(start_date.getTextContent());
                  }
                  Node end_date = userElement.getElementsByTagName("end_date").item(po);
                  if ((end_date != null) && 
                    (end_date.getParentNode() != null) && (end_date.getParentNode().getNodeName() != null) && (end_date.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setEndDate(end_date.getTextContent());
                  }
                  Node employer = userElement.getElementsByTagName("employer").item(po);
                  if (employer != null)
                  {
                    FbEmployer fbemp = new FbEmployer();
                    
                    NodeList list = employer.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbemp.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbemp.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbemp.getFacebookid()))
                    {
                      FbEmployer fbemployer = BOFactory.getFbUserBO().isEmployeerExist(fbemp.getFacebookid());
                      if (fbemployer != null) {
                        fbemp.setEmId(fbemployer.getEmId());
                      } else {
                        fbemp = BOFactory.getFbUserBO().saveFbEmployer(fbemp);
                      }
                      fbuseremp.setEmployer(fbemp);
                    }
                  }
                  Node location = userElement.getElementsByTagName("location").item(po);
                  if (location != null)
                  {
                    FbLocation fbloc = new FbLocation();
                    
                    NodeList list = location.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbloc.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbloc.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbloc.getFacebookid()))
                    {
                      FbLocation fblocation = BOFactory.getFbUserBO().isLocationExist(fbloc.getFacebookid());
                      if (fblocation != null) {
                        fbloc.setLocationId(fblocation.getLocationId());
                      } else {
                        fbloc = BOFactory.getFbUserBO().saveFbLocation(fbloc);
                      }
                      fbuseremp.setLocation(fbloc);
                    }
                  }
                  Node position = userElement.getElementsByTagName("position").item(po);
                  if (position != null)
                  {
                    FbPositions fpos = new FbPositions();
                    
                    NodeList list = position.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fpos.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fpos.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fpos.getFacebookid()))
                    {
                      FbPositions fbposition = BOFactory.getFbUserBO().isPositionsExist(fpos.getFacebookid());
                      if (fbposition != null) {
                        fpos.setPositionId(fbposition.getPositionId());
                      } else {
                        fpos = BOFactory.getFbUserBO().saveFbPositions(fpos);
                      }
                      fbuseremp.setPosition(fpos);
                    }
                  }
                  fbEmploerList.add(fbuseremp);
                }
                po++;
              }
            }
          }
          List<FbUserSchools> fbschoolsList = new ArrayList();
          nodelist = userElement.getElementsByTagName("education").item(0).getChildNodes();
          if ((nodelist != null) && (nodelist.getLength() > 0))
          {
            int po = 0;
            for (int n = 0; n < nodelist.getLength(); n++)
            {
              Node schNode = nodelist.item(n);
              if (schNode.getNodeType() == 1)
              {
                Node workElt = userElement.getElementsByTagName("education_elt").item(po);
                if (workElt != null)
                {
                  FbUserSchools fbuserschool = new FbUserSchools();
                  
                  fbuserschool.setLevel(po);
                  Node type = userElement.getElementsByTagName("type").item(po);
                  if (type != null) {
                    fbuserschool.setType(type.getTextContent());
                  }
                  Node year = userElement.getElementsByTagName("year").item(po);
                  if (year != null)
                  {
                    NodeList yllist = year.getChildNodes();
                    for (int cl = 0; cl < yllist.getLength(); cl++)
                    {
                      Node node = yllist.item(cl);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbuserschool.setYear(node.getTextContent());
                      }
                    }
                  }
                  Node school = userElement.getElementsByTagName("school").item(po);
                  if (school != null)
                  {
                    FbSchool fbsch = new FbSchool();
                    
                    NodeList list = school.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbsch.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbsch.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbsch.getFacebookid()))
                    {
                      FbSchool fbschool = BOFactory.getFbUserBO().isSchoolExist(fbsch.getFacebookid());
                      if (fbschool != null) {
                        fbsch.setSchoolId(fbschool.getSchoolId());
                      } else {
                        fbsch = BOFactory.getFbUserBO().saveFbSchool(fbsch);
                      }
                      fbuserschool.setSchool(fbsch);
                    }
                  }
                  Node concentration = userElement.getElementsByTagName("concentration").item(po);
                  if (concentration != null)
                  {
                    FbConcentration fcon = new FbConcentration();
                    
                    NodeList list = concentration.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fcon.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fcon.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fcon.getFacebookid()))
                    {
                      FbConcentration fbconcentration = BOFactory.getFbUserBO().isConcentrationExist(fcon.getFacebookid());
                      if (fbconcentration != null) {
                        fcon.setConcentrationId(fbconcentration.getConcentrationId());
                      } else {
                        fcon = BOFactory.getFbUserBO().saveFbConcentration(fcon);
                      }
                      fbuserschool.setConcentration(fcon);
                    }
                  }
                  fbschoolsList.add(fbuserschool);
                }
                po++;
              }
            }
          }
          BOFactory.getFbUserBO().saveFaceBookUser(fuser, fbEmploerList, fbschoolsList);
        }
      }
      BOFactory.getFbUserBO().updateLastUpdatedDate(facebookid);
    }
    catch (Exception e)
    {
      logger.info("Exception on getFriendsData", e);
      e.printStackTrace();
    }
    return flist;
  }
  
  public FaceBookUser getOwnUserData(String facebookid, String sesstionKey)
  {
    logger.info("inside getOwnUserData");
    FaceBookUser fownuser = new FaceBookUser();
    try
    {
      String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid,name,first_name,last_name,username,email,sex,profile_url,timezone,locale,work,education,current_location%20FROM%20user%20WHERE%20uid%20IN%20(" + facebookid + ")&access_token=" + sesstionKey;
      
      System.out.println(urlString);
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      
      System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
      

      NodeList listOfusers = doc.getElementsByTagName("user");
      int totalPersons = listOfusers.getLength();
      System.out.println("Total no of people : " + totalPersons);
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        FaceBookUser fuser = new FaceBookUser();
        
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          fuser.setFacebookid(nValue.getNodeValue());
          
          nodelist = userElement.getElementsByTagName("name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFullName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("first_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFirstName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("last_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLastName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("username").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFuserName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("email").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setEmailId(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("sex").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setGender(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("profile_url").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLink(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("timezone").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setTimezone(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("locale").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLocale(nValue.getNodeValue());
          }
          Node currentLocation = userElement.getElementsByTagName("current_location").item(0);
          
          NodeList cllist = currentLocation.getChildNodes();
          for (int cl = 0; cl < cllist.getLength(); cl++)
          {
            Node node = cllist.item(cl);
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("city"))) {
              fuser.setCity(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("state"))) {
              fuser.setState(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("country"))) {
              fuser.setCountry(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("zip"))) {
              fuser.setZip(node.getTextContent());
            }
          }
          List<FbUserEmployer> fbEmploerList = new ArrayList();
          nodelist = userElement.getElementsByTagName("work").item(0).getChildNodes();
          if ((nodelist != null) && (nodelist.getLength() > 0))
          {
            int po = 0;
            for (int n = 0; n < nodelist.getLength(); n++)
            {
              Node workNode = nodelist.item(n);
              if (workNode.getNodeType() == 1)
              {
                Node workElt = userElement.getElementsByTagName("work_elt").item(po);
                if (workElt != null)
                {
                  NodeList workelt = workElt.getChildNodes();
                  



                  FbUserEmployer fbuseremp = new FbUserEmployer();
                  
                  fbuseremp.setLevel(po);
                  Node description = userElement.getElementsByTagName("description").item(po);
                  if ((description != null) && 
                    (description.getParentNode() != null) && (description.getParentNode().getNodeName() != null) && (description.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setDescription(description.getTextContent());
                  }
                  Node start_date = userElement.getElementsByTagName("start_date").item(po);
                  if ((start_date != null) && 
                    (start_date.getParentNode() != null) && (start_date.getParentNode().getNodeName() != null) && (start_date.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setStartDate(start_date.getTextContent());
                  }
                  Node end_date = userElement.getElementsByTagName("end_date").item(po);
                  if ((end_date != null) && 
                    (end_date.getParentNode() != null) && (end_date.getParentNode().getNodeName() != null) && (end_date.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setEndDate(end_date.getTextContent());
                  }
                  Node employer = userElement.getElementsByTagName("employer").item(po);
                  if (employer != null)
                  {
                    FbEmployer fbemp = new FbEmployer();
                    
                    NodeList list = employer.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbemp.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbemp.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbemp.getFacebookid()))
                    {
                      FbEmployer fbemployer = BOFactory.getFbUserBO().isEmployeerExist(fbemp.getFacebookid());
                      if (fbemployer != null) {
                        fbemp.setEmId(fbemployer.getEmId());
                      } else {
                        fbemp = BOFactory.getFbUserBO().saveFbEmployer(fbemp);
                      }
                      fbuseremp.setEmployer(fbemp);
                    }
                  }
                  Node location = userElement.getElementsByTagName("location").item(po);
                  if (location != null)
                  {
                    FbLocation fbloc = new FbLocation();
                    
                    NodeList list = location.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbloc.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbloc.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbloc.getFacebookid()))
                    {
                      FbLocation fblocation = BOFactory.getFbUserBO().isLocationExist(fbloc.getFacebookid());
                      if (fblocation != null) {
                        fbloc.setLocationId(fblocation.getLocationId());
                      } else {
                        fbloc = BOFactory.getFbUserBO().saveFbLocation(fbloc);
                      }
                      fbuseremp.setLocation(fbloc);
                    }
                  }
                  Node position = userElement.getElementsByTagName("position").item(po);
                  if (position != null)
                  {
                    FbPositions fpos = new FbPositions();
                    
                    NodeList list = position.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fpos.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fpos.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fpos.getFacebookid()))
                    {
                      FbPositions fbposition = BOFactory.getFbUserBO().isPositionsExist(fpos.getFacebookid());
                      if (fbposition != null) {
                        fpos.setPositionId(fbposition.getPositionId());
                      } else {
                        fpos = BOFactory.getFbUserBO().saveFbPositions(fpos);
                      }
                      fbuseremp.setPosition(fpos);
                    }
                  }
                  fbEmploerList.add(fbuseremp);
                }
                po++;
              }
            }
          }
          List<FbUserSchools> fbschoolsList = new ArrayList();
          nodelist = userElement.getElementsByTagName("education").item(0).getChildNodes();
          if ((nodelist != null) && (nodelist.getLength() > 0))
          {
            int po = 0;
            for (int n = 0; n < nodelist.getLength(); n++)
            {
              Node schNode = nodelist.item(n);
              if (schNode.getNodeType() == 1)
              {
                Node workElt = userElement.getElementsByTagName("education_elt").item(po);
                if (workElt != null)
                {
                  FbUserSchools fbuserschool = new FbUserSchools();
                  
                  fbuserschool.setLevel(po);
                  Node type = userElement.getElementsByTagName("type").item(po);
                  if (type != null) {
                    fbuserschool.setType(type.getTextContent());
                  }
                  Node year = userElement.getElementsByTagName("year").item(po);
                  if (year != null)
                  {
                    NodeList yllist = year.getChildNodes();
                    for (int cl = 0; cl < yllist.getLength(); cl++)
                    {
                      Node node = yllist.item(cl);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbuserschool.setYear(node.getTextContent());
                      }
                    }
                  }
                  Node school = userElement.getElementsByTagName("school").item(po);
                  if (school != null)
                  {
                    FbSchool fbsch = new FbSchool();
                    
                    NodeList list = school.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbsch.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbsch.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbsch.getFacebookid()))
                    {
                      FbSchool fbschool = BOFactory.getFbUserBO().isSchoolExist(fbsch.getFacebookid());
                      if (fbschool != null) {
                        fbsch.setSchoolId(fbschool.getSchoolId());
                      } else {
                        fbsch = BOFactory.getFbUserBO().saveFbSchool(fbsch);
                      }
                      fbuserschool.setSchool(fbsch);
                    }
                  }
                  Node concentration = userElement.getElementsByTagName("concentration").item(po);
                  if (concentration != null)
                  {
                    FbConcentration fcon = new FbConcentration();
                    
                    NodeList list = concentration.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fcon.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fcon.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fcon.getFacebookid()))
                    {
                      FbConcentration fbconcentration = BOFactory.getFbUserBO().isConcentrationExist(fcon.getFacebookid());
                      if (fbconcentration != null) {
                        fcon.setConcentrationId(fbconcentration.getConcentrationId());
                      } else {
                        fcon = BOFactory.getFbUserBO().saveFbConcentration(fcon);
                      }
                      fbuserschool.setConcentration(fcon);
                    }
                  }
                  fbschoolsList.add(fbuserschool);
                }
                po++;
              }
            }
          }
          BOFactory.getFbUserBO().saveFaceBookUser(fuser, fbEmploerList, fbschoolsList);
        }
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getOwnData", e);
      e.printStackTrace();
    }
    return fownuser;
  }
  
  public int getFriendsCount(FaceBookUser user)
  {
    int total_count = 0;
    
    String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20friend_count%20from%20user%20where%20uid=" + user.getFacebookid() + "&access_token=" + user.getSessionKey();
    try
    {
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      
      NodeList nodelist = doc.getElementsByTagName("friend_count").item(0).getChildNodes();
      Node nValue = nodelist.item(0);
      if ((nValue != null) && 
        (!StringUtils.isNullOrEmpty(nValue.getNodeValue()))) {
        total_count = new Integer(nValue.getNodeValue()).intValue();
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getFriendCount", e);
    }
    return total_count;
  }
  
  public String getFriendsUids(FaceBookUser user, int limit)
  {
    String users = "";
    


    String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid%20FROM%20user%20WHERE%20is_app_user%20=0%20AND%20uid%20IN%20(SELECT%20uid2%20FROM%20friend%20WHERE%20uid1=" + user.getFacebookid() + ")&access_token=" + user.getSessionKey();
    try
    {
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      


      NodeList listOfusers = doc.getElementsByTagName("user");
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          if (nValue != null) {
            users = users + nValue.getNodeValue() + ",";
          }
          if (i > limit) {
            break;
          }
        }
      }
      if (!StringUtils.isNullOrEmpty(users)) {
        users = users.substring(0, users.length() - 1);
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getFriendsUids", e);
    }
    return users;
  }
  
  public List getFriendsUsingThisApps(FaceBookUser user, int limit)
  {
    List userList = new ArrayList();
    


    String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid,name,username,profile_url%20FROM%20user%20WHERE%20is_app_user%20=1%20AND%20uid%20IN%20(SELECT%20uid2%20FROM%20friend%20WHERE%20uid1=" + user.getFacebookid() + ")&access_token=" + user.getSessionKey();
    try
    {
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      


      NodeList listOfusers = doc.getElementsByTagName("user");
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          FaceBookUser fuser = new FaceBookUser();
          
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFacebookid(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFullName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("username").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFuserName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("profile_url").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLink(nValue.getNodeValue());
          }
          userList.add(fuser);
          if (i > limit) {
            break;
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getFriendsUsingThisApps", e);
    }
    return userList;
  }
  
  public List getAskingForEndorse(FaceBookUser user, String ids)
  {
    List userList = new ArrayList();
    


    String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid,name,username,profile_url%20FROM%20user%20WHERE%20uid%20IN%20(" + ids + ")%20ORDER%20BY%20name&access_token=" + user.getSessionKey();
    logger.info("urlString" + urlString);
    try
    {
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      


      NodeList listOfusers = doc.getElementsByTagName("user");
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          FaceBookUser fuser = new FaceBookUser();
          
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFacebookid(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFullName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("username").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFuserName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("profile_url").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLink(nValue.getNodeValue());
          }
          userList.add(fuser);
        }
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getAskingForEndorse", e);
    }
    return userList;
  }
  
  public List getAllFriendsForEndorse(FaceBookUser user)
  {
    List userList = new ArrayList();
    


    String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid,name,username,profile_url%20FROM%20user%20WHERE%20uid%20IN%20(SELECT%20uid2%20FROM%20friend%20WHERE%20uid1=" + user.getFacebookid() + ")%20ORDER%20BY%20name&access_token=" + user.getSessionKey();
    logger.info("urlString" + urlString);
    try
    {
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      


      NodeList listOfusers = doc.getElementsByTagName("user");
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          FaceBookUser fuser = new FaceBookUser();
          
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFacebookid(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFullName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("username").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFuserName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("profile_url").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLink(nValue.getNodeValue());
          }
          userList.add(fuser);
        }
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getFriendsUsingThisApps", e);
    }
    return userList;
  }
  
  public FaceBookUser getUserDataFew(String facebookid, String sesstionKey)
  {
    logger.info("inside getUserDataFew");
    FaceBookUser fuser = new FaceBookUser();
    if (StringUtils.isNullOrEmpty(facebookid)) {
      return fuser;
    }
    try
    {
      String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid,name,first_name,last_name,username,email,sex,profile_url%20FROM%20user%20WHERE%20uid%20IN%20(" + facebookid + ")&access_token=" + sesstionKey;
      
      logger.info(urlString);
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      
      System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
      

      NodeList listOfusers = doc.getElementsByTagName("user");
      int totalPersons = listOfusers.getLength();
      System.out.println("Total no of people : " + totalPersons);
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        fuser = new FaceBookUser();
        
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          fuser.setFacebookid(nValue.getNodeValue());
          
          nodelist = userElement.getElementsByTagName("name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFullName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("first_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFirstName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("last_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLastName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("username").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFuserName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("email").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setEmailId(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("sex").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setGender(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("profile_url").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLink(nValue.getNodeValue());
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getUserDataFew", e);
      e.printStackTrace();
    }
    return fuser;
  }
  
  public FaceBookUser getOwnUserDataForUI(String facebookid, String sesstionKey)
  {
    logger.info("inside getOwnUserDataForUI");
    FaceBookUser fuser = new FaceBookUser();
    List<FbUserEmployer> fbEmploerList = new ArrayList();
    List<FbUserSchools> fbschoolsList = new ArrayList();
    try
    {
      String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20uid,name,first_name,last_name,username,email,sex,profile_url,timezone,locale,work,education,current_location%20FROM%20user%20WHERE%20uid%20IN%20(" + facebookid + ")&access_token=" + sesstionKey;
      
      logger.info(urlString);
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      
      System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
      

      NodeList listOfusers = doc.getElementsByTagName("user");
      int totalPersons = listOfusers.getLength();
      System.out.println("Total no of people : " + totalPersons);
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        fuser = new FaceBookUser();
        
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          Element userElement = (Element)userNode;
          
          NodeList nodelist = userElement.getElementsByTagName("uid").item(0).getChildNodes();
          Node nValue = nodelist.item(0);
          fuser.setFacebookid(nValue.getNodeValue());
          
          nodelist = userElement.getElementsByTagName("name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFullName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("first_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFirstName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("last_name").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLastName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("username").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setFuserName(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("email").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setEmailId(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("sex").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setGender(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("profile_url").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLink(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("timezone").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setTimezone(nValue.getNodeValue());
          }
          nodelist = userElement.getElementsByTagName("locale").item(0).getChildNodes();
          nValue = nodelist.item(0);
          if (nValue != null) {
            fuser.setLocale(nValue.getNodeValue());
          }
          Node currentLocation = userElement.getElementsByTagName("current_location").item(0);
          
          NodeList cllist = currentLocation.getChildNodes();
          for (int cl = 0; cl < cllist.getLength(); cl++)
          {
            Node node = cllist.item(cl);
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("city"))) {
              fuser.setCity(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("state"))) {
              fuser.setState(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("country"))) {
              fuser.setCountry(node.getTextContent());
            }
            if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("zip"))) {
              fuser.setZip(node.getTextContent());
            }
          }
          nodelist = userElement.getElementsByTagName("work").item(0).getChildNodes();
          if ((nodelist != null) && (nodelist.getLength() > 0))
          {
            int po = 0;
            for (int n = 0; n < nodelist.getLength(); n++)
            {
              Node workNode = nodelist.item(n);
              if (workNode.getNodeType() == 1)
              {
                Node workElt = userElement.getElementsByTagName("work_elt").item(po);
                if (workElt != null)
                {
                  FbUserEmployer fbuseremp = new FbUserEmployer();
                  
                  fbuseremp.setLevel(po);
                  Node description = userElement.getElementsByTagName("description").item(po);
                  if ((description != null) && 
                    (description.getParentNode() != null) && (description.getParentNode().getNodeName() != null) && (description.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setDescription(description.getTextContent());
                  }
                  Node start_date = userElement.getElementsByTagName("start_date").item(po);
                  if ((start_date != null) && 
                    (start_date.getParentNode() != null) && (start_date.getParentNode().getNodeName() != null) && (start_date.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setStartDate(start_date.getTextContent());
                  }
                  Node end_date = userElement.getElementsByTagName("end_date").item(po);
                  if ((end_date != null) && 
                    (end_date.getParentNode() != null) && (end_date.getParentNode().getNodeName() != null) && (end_date.getParentNode().getNodeName().equals("work_elt"))) {
                    fbuseremp.setEndDate(end_date.getTextContent());
                  }
                  Node employer = userElement.getElementsByTagName("employer").item(po);
                  if (employer != null)
                  {
                    FbEmployer fbemp = new FbEmployer();
                    
                    NodeList list = employer.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbemp.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbemp.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbemp.getFacebookid())) {
                      fbuseremp.setEmployer(fbemp);
                    }
                  }
                  Node location = userElement.getElementsByTagName("location").item(po);
                  if (location != null)
                  {
                    FbLocation fbloc = new FbLocation();
                    
                    NodeList list = location.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbloc.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbloc.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbloc.getFacebookid())) {
                      fbuseremp.setLocation(fbloc);
                    }
                  }
                  Node position = userElement.getElementsByTagName("position").item(po);
                  if (position != null)
                  {
                    FbPositions fpos = new FbPositions();
                    
                    NodeList list = position.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fpos.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fpos.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fpos.getFacebookid())) {
                      fbuseremp.setPosition(fpos);
                    }
                  }
                  fbEmploerList.add(fbuseremp);
                }
                po++;
              }
            }
          }
          nodelist = userElement.getElementsByTagName("education").item(0).getChildNodes();
          if ((nodelist != null) && (nodelist.getLength() > 0))
          {
            int po = 0;
            for (int n = 0; n < nodelist.getLength(); n++)
            {
              Node schNode = nodelist.item(n);
              if (schNode.getNodeType() == 1)
              {
                Node workElt = userElement.getElementsByTagName("education_elt").item(po);
                if (workElt != null)
                {
                  FbUserSchools fbuserschool = new FbUserSchools();
                  
                  fbuserschool.setLevel(po);
                  Node type = userElement.getElementsByTagName("type").item(po);
                  if (type != null) {
                    fbuserschool.setType(type.getTextContent());
                  }
                  Node year = userElement.getElementsByTagName("year").item(po);
                  if (year != null)
                  {
                    NodeList yllist = year.getChildNodes();
                    for (int cl = 0; cl < yllist.getLength(); cl++)
                    {
                      Node node = yllist.item(cl);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbuserschool.setYear(node.getTextContent());
                      }
                    }
                  }
                  Node school = userElement.getElementsByTagName("school").item(po);
                  if (school != null)
                  {
                    FbSchool fbsch = new FbSchool();
                    
                    NodeList list = school.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fbsch.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fbsch.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fbsch.getFacebookid())) {
                      fbuserschool.setSchool(fbsch);
                    }
                  }
                  Node concentration = userElement.getElementsByTagName("concentration").item(po);
                  if (concentration != null)
                  {
                    FbConcentration fcon = new FbConcentration();
                    
                    NodeList list = concentration.getChildNodes();
                    for (int e = 0; e < list.getLength(); e++)
                    {
                      Node node = list.item(e);
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                        fcon.setFacebookid(node.getTextContent());
                      }
                      if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                        fcon.setName(node.getTextContent());
                      }
                    }
                    if (!StringUtils.isNullOrEmpty(fcon.getFacebookid())) {
                      fbuserschool.setConcentration(fcon);
                    }
                  }
                  fbschoolsList.add(fbuserschool);
                }
                po++;
              }
            }
          }
        }
      }
      fuser.setEmployerList(fbEmploerList);
      fuser.setEducationList(fbschoolsList);
    }
    catch (Exception e)
    {
      logger.info("Exception on getOwnUserDataForUI", e);
      e.printStackTrace();
    }
    return fuser;
  }
  
  public List<FbEmployer> getFriendsCompanies(String facebookid, String sesstionKey, int limit, int offset)
  {
    logger.info("inside getFriendsCompanies");
    List<String> fidlist = new ArrayList();
    List<FbEmployer> emplist = new ArrayList();
    try
    {
      String urlString = "https://api.facebook.com/method/fql.query?query=SELECT%20work%20FROM%20user%20WHERE%20uid%20IN%20(SELECT%20uid2%20FROM%20friend%20WHERE%20uid1=" + facebookid + ")limit+" + limit + "+offset+" + offset + "&access_token=" + sesstionKey;
      
      logger.info(urlString);
      URL url = new URL(urlString);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      conn.connect();
      

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(conn.getInputStream());
      
      System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
      

      NodeList listOfusers = doc.getElementsByTagName("user");
      int totalPersons = listOfusers.getLength();
      System.out.println("Total no of people : " + totalPersons);
      for (int i = 0; i < listOfusers.getLength(); i++)
      {
        FaceBookUser fuser = new FaceBookUser();
        
        Node userNode = listOfusers.item(i);
        if (userNode.getNodeType() == 1)
        {
          Element userElement = (Element)userNode;
          
          NodeList nodelist = null;
          


          List<FbUserEmployer> fbEmploerList = new ArrayList();
          nodelist = userElement.getElementsByTagName("work").item(0).getChildNodes();
          if ((nodelist != null) && (nodelist.getLength() > 0))
          {
            int po = 0;
            boolean isdone = false;
            for (int n = 0; n < nodelist.getLength(); n++)
            {
              Node workNode = nodelist.item(n);
              if (workNode.getNodeType() == 1)
              {
                Node workElt = userElement.getElementsByTagName("work_elt").item(po);
                if (workElt == null) {
                  break;
                }
                Node employer = userElement.getElementsByTagName("employer").item(po);
                if (employer != null)
                {
                  FbEmployer fbemp = new FbEmployer();
                  
                  NodeList list = employer.getChildNodes();
                  for (int e = 0; e < list.getLength(); e++)
                  {
                    Node node = list.item(e);
                    if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("id"))) {
                      fbemp.setFacebookid(node.getTextContent());
                    }
                    if ((node != null) && (!StringUtils.isNullOrEmpty(node.getNodeName())) && (node.getNodeName().equalsIgnoreCase("name"))) {
                      fbemp.setName(node.getTextContent());
                    }
                  }
                  if (!fidlist.contains(fbemp.getFacebookid()))
                  {
                    fidlist.add(fbemp.getFacebookid());
                    emplist.add(fbemp);
                  }
                }
                break;
              }
            }
          }
        }
      }
      BOFactory.getFbUserBO().updateLastUpdatedDate(facebookid);
    }
    catch (Exception e)
    {
      logger.info("Exception on getFriendsCompanies", e);
      e.printStackTrace();
    }
    return emplist;
  }
  
  public static void main(String[] args)
  {
    FaceBookUser fu = new FaceBookUser();
    FaceBookReader r = new FaceBookReader();
    String sesstionKey = "AAAFoJrTxKRoBAPExSvXnizf8flIs2xzo9BOBZBQiJxYeeKk24GQRAibllv9ZAn75aA1D0qrbZAZCHa8NDEhwdSb79eA4HVB7pC3o5IbHHmQiioRDtqp9";
    String facebookid = "100001445034987";
    
    r.getOwnUserDataForUI("", "");
  }
}
