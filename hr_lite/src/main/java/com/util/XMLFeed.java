package com.util;

import com.bean.CompFrequency;
import com.bean.Country;
import com.bean.FlsaStatus;
import com.bean.JobRequisition;
import com.bean.JobType;
import com.bean.Location;
import com.bean.Organization;
import com.bean.SalaryPlan;
import com.bean.State;
import com.bean.User;
import com.bean.UserRegData;
import com.bean.lov.JobCategory;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.resources.Constant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLFeed
{
  protected static final Logger logger = Logger.getLogger(XMLFeed.class);
  
  public void createInddedJobFeed(List jobsList)
  {
    logger.info("inside createInddedJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("source");
      doc.appendChild(rootElement);
      









      Date dt = new Date();
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        if (i == 0)
        {
          Element publisher = doc.createElement("publisher");
          publisher.appendChild(doc.createTextNode("<![CDATA[Hires360 Job Board]]>"));
          rootElement.appendChild(publisher);
          

          String urluniq2 = "http://www." + Constant.getValue("domain.name");
          
          Element publisherurl = doc.createElement("publisherurl");
          publisherurl.appendChild(doc.createTextNode("<![CDATA[" + urluniq2 + "]]>"));
          rootElement.appendChild(publisherurl);
          
          Element lastBuildDate = doc.createElement("lastBuildDate");
          lastBuildDate.appendChild(doc.createTextNode("<![CDATA[" + DateUtil.convertDateToStringDate(dt, DateUtil.dateformatindeedcal) + "]]>"));
          rootElement.appendChild(lastBuildDate);
        }
        Element job = doc.createElement("job");
        rootElement.appendChild(job);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode("<![CDATA[" + removeHtml(jb.getJobTitle()) + "]]>"));
        job.appendChild(title);
        
        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode("<![CDATA[" + DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.dateformatindeedcal) + "]]>"));
        job.appendChild(date);
        
        Element referencenumber = doc.createElement("referencenumber");
        referencenumber.appendChild(doc.createTextNode("<![CDATA[" + jb.getUuid() + "]]>"));
        job.appendChild(referencenumber);
        
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=INDEED";
        
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode("<![CDATA[" + urluniq + "]]>"));
        job.appendChild(url);
        
        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode("<![CDATA[" + jb.getOrganization().getOrgName() + "]]>"));
        job.appendChild(company);
        
        Location loc = jb.getLocation();
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
        {
          Element city = doc.createElement("city");
          city.appendChild(doc.createTextNode("<![CDATA[" + loc.getCity() + "]]>"));
          job.appendChild(city);
        }
        if ((loc != null) && (loc.getState() != null))
        {
          Element state = doc.createElement("state");
          state.appendChild(doc.createTextNode("<![CDATA[" + loc.getState().getStateName() + "]]>"));
          job.appendChild(state);
        }
        if ((loc != null) && (loc.getCountry() != null))
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode("<![CDATA[" + loc.getCountry().getCountryCode() + "]]>"));
          job.appendChild(country);
        }
        else
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode("<![CDATA[US]]>"));
          job.appendChild(country);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
        {
          Element zip = doc.createElement("postalcode");
          zip.appendChild(doc.createTextNode("<![CDATA[" + loc.getZip() + "]]>"));
          job.appendChild(zip);
        }
        String desc = removeHtml(jb.getJobDetails());
        

        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode("<![CDATA[" + desc + "]]>"));
        job.appendChild(description);
        if (jb.getSalaryplan() != null)
        {
          String sal = jb.getSalaryplan().getCurrencyCode() + jb.getSalaryplan().getFromRangeAmount() + "-" + jb.getSalaryplan().getToRangeAmount();
          Element salary = doc.createElement("salary");
          salary.appendChild(doc.createTextNode("<![CDATA[" + sal + "]]>"));
          job.appendChild(salary);
        }
        if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
        {
          Element education = doc.createElement("education");
          education.appendChild(doc.createTextNode("<![CDATA[" + jb.getMinimumLevelOfEducation() + "]]>"));
          job.appendChild(education);
        }
        if (jb.getJobtype() != null)
        {
          Element jobtype = doc.createElement("jobtype");
          jobtype.appendChild(doc.createTextNode("<![CDATA[" + jb.getJobtype().getJobTypeName() + "]]>"));
          job.appendChild(jobtype);
        }
        if (jb.getJobcategory() != null)
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode("<![CDATA[" + jb.getJobcategory().getJobCategoryName() + "]]>"));
          job.appendChild(category);
        }
        String exmp = "";
        if ((jb.getMinyearsofExpRequired() != 0) || (jb.getMaxyearsofExpRequired() != 0)) {
          if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() == 0)) {
            exmp = jb.getMinyearsofExpRequired() + "+ year(s)";
          } else if ((jb.getMinyearsofExpRequired() == 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMaxyearsofExpRequired() + " year(s)";
          } else if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMinyearsofExpRequired() + "-" + jb.getMaxyearsofExpRequired() + " year(s)";
          }
        }
        if (!StringUtils.isNullOrEmpty(exmp))
        {
          Element experience = doc.createElement("experience");
          experience.appendChild(doc.createTextNode("<![CDATA[" + exmp + "]]>"));
          job.appendChild(experience);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "indeed_job_feed.xml"));
      



      transformer.transform(source, result);
      












      replaceCharInFile(Constant.getValue("job.feed.path") + "indeed_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createInddedJobFeed", e);
    }
  }
  
  public void createJobDieselJobFeed(List jobsList)
  {
    logger.info("inside createJobDieselJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("source");
      doc.appendChild(rootElement);
      
      Date dt = new Date();
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        if (i == 0)
        {
          Element publisher = doc.createElement("publisher");
          publisher.appendChild(doc.createTextNode("Hires360 Job Board"));
          rootElement.appendChild(publisher);
          

          String urluniq2 = "http://www." + Constant.getValue("domain.name");
          
          Element publisherurl = doc.createElement("publisherurl");
          publisherurl.appendChild(doc.createTextNode(urluniq2));
          rootElement.appendChild(publisherurl);
          
          Element lastBuildDate = doc.createElement("lastBuildDate");
          lastBuildDate.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(dt, DateUtil.dateformatindeedcal)));
          rootElement.appendChild(lastBuildDate);
        }
        Element job = doc.createElement("job");
        rootElement.appendChild(job);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
        job.appendChild(title);
        
        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.dateformatindeedcal)));
        job.appendChild(date);
        
        Element referencenumber = doc.createElement("referencenumber");
        referencenumber.appendChild(doc.createTextNode(jb.getUuid()));
        job.appendChild(referencenumber);
        
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=JOBDIESEL";
        
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode(urluniq));
        job.appendChild(url);
        
        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
        job.appendChild(company);
        
        Location loc = jb.getLocation();
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
        {
          Element city = doc.createElement("city");
          city.appendChild(doc.createTextNode(loc.getCity()));
          job.appendChild(city);
        }
        if ((loc != null) && (loc.getState() != null))
        {
          Element state = doc.createElement("state");
          state.appendChild(doc.createTextNode(loc.getState().getStateName()));
          job.appendChild(state);
        }
        if ((loc != null) && (loc.getCountry() != null))
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode(loc.getCountry().getCountryName()));
          job.appendChild(country);
        }
        else
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode("US"));
          job.appendChild(country);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
        {
          Element zip = doc.createElement("postalcode");
          zip.appendChild(doc.createTextNode(loc.getZip()));
          job.appendChild(zip);
        }
        String desc = removeHtml(jb.getJobDetails());
        

        Element description = doc.createElement("description");
        if (!StringUtils.isNullOrEmpty(desc))
        {
          description.appendChild(doc.createTextNode(desc));
          job.appendChild(description);
        }
        if (jb.getSalaryplan() != null)
        {
          String sal = jb.getSalaryplan().getCurrencyCode() + jb.getSalaryplan().getFromRangeAmount() + "-" + jb.getSalaryplan().getToRangeAmount();
          Element salary = doc.createElement("salary");
          salary.appendChild(doc.createTextNode(sal));
          job.appendChild(salary);
        }
        if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
        {
          Element education = doc.createElement("education");
          education.appendChild(doc.createTextNode(jb.getMinimumLevelOfEducation()));
          job.appendChild(education);
        }
        if (jb.getJobtype() != null)
        {
          Element jobtype = doc.createElement("jobtype");
          jobtype.appendChild(doc.createTextNode(jb.getJobtype().getJobTypeName()));
          job.appendChild(jobtype);
        }
        if (jb.getJobcategory() != null)
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode(jb.getJobcategory().getJobCategoryName()));
          job.appendChild(category);
        }
        String exmp = "";
        if ((jb.getMinyearsofExpRequired() != 0) || (jb.getMaxyearsofExpRequired() != 0)) {
          if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() == 0)) {
            exmp = jb.getMinyearsofExpRequired() + "+ year(s)";
          } else if ((jb.getMinyearsofExpRequired() == 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMaxyearsofExpRequired() + " year(s)";
          } else if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMinyearsofExpRequired() + "-" + jb.getMaxyearsofExpRequired() + " year(s)";
          }
        }
        if (!StringUtils.isNullOrEmpty(exmp))
        {
          Element experience = doc.createElement("experience");
          experience.appendChild(doc.createTextNode(exmp));
          job.appendChild(experience);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "jobDiesel_job_feed.xml"));
      



      transformer.transform(source, result);
      












      replaceCharInFile(Constant.getValue("job.feed.path") + "jobDiesel_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createJobDieselJobFeed", e);
    }
  }
  
  public void createCareerVitalsJobFeed(List jobsList)
  {
    logger.info("inside createCareerVitalsJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("source");
      doc.appendChild(rootElement);
      

      Element publisher = doc.createElement("publisher");
      publisher.appendChild(doc.createTextNode("<![CDATA[Hires360 Job Board]]>"));
      rootElement.appendChild(publisher);
      

      String urluniq2 = "http://www." + Constant.getValue("domain.name");
      
      Element publisherurl = doc.createElement("publisherurl");
      publisherurl.appendChild(doc.createTextNode("<![CDATA[" + urluniq2 + "]]>"));
      rootElement.appendChild(publisherurl);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        

        Element job = doc.createElement("job");
        rootElement.appendChild(job);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode("<![CDATA[" + removeHtml(jb.getJobTitle()) + "]]>"));
        job.appendChild(title);
        
        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode("<![CDATA[" + DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.twFeedDate) + "]]>"));
        job.appendChild(date);
        
        Element referencenumber = doc.createElement("referencenumber");
        referencenumber.appendChild(doc.createTextNode("<![CDATA[" + jb.getJobreqId() + "]]>"));
        job.appendChild(referencenumber);
        
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=CAREERVITAL";
        
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode("<![CDATA[" + urluniq + "]]>"));
        job.appendChild(url);
        
        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode("<![CDATA[" + jb.getOrganization().getOrgName() + "]]>"));
        job.appendChild(company);
        
        Location loc = jb.getLocation();
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
        {
          Element city = doc.createElement("city");
          city.appendChild(doc.createTextNode("<![CDATA[" + loc.getCity() + "]]>"));
          job.appendChild(city);
        }
        else
        {
          Element city = doc.createElement("city");
          city.appendChild(doc.createTextNode("<![CDATA[" + loc.getCountry().getCountryName() + "]]>"));
          job.appendChild(city);
        }
        if ((loc != null) && (loc.getState() != null))
        {
          Element state = doc.createElement("state");
          state.appendChild(doc.createTextNode("<![CDATA[" + loc.getState().getStateName() + "]]>"));
          job.appendChild(state);
        }
        else
        {
          Element state = doc.createElement("state");
          state.appendChild(doc.createTextNode("<![CDATA[" + loc.getCountry().getCountryName() + "]]>"));
          job.appendChild(state);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
        {
          Element zip = doc.createElement("postalcode");
          zip.appendChild(doc.createTextNode("<![CDATA[" + loc.getZip() + "]]>"));
          job.appendChild(zip);
        }
        else
        {
          Element zip = doc.createElement("postalcode");
          zip.appendChild(doc.createTextNode("<![CDATA[000000]]>"));
          job.appendChild(zip);
        }
        String desc = removeHtml(jb.getJobDetails());
        

        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode("<![CDATA[" + desc + "]]>"));
        job.appendChild(description);
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "CareerVitals_job_feed.xml"));
      

      transformer.transform(source, result);
      


      replaceCharInFile(Constant.getValue("job.feed.path") + "CareerVitals_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createCareerVitalsJobFeed", e);
    }
  }
  
  public void createSimplyHiredJobFeed(List jobsList)
  {
    logger.info("inside createSimplyHiredJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("jobs");
      doc.appendChild(rootElement);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        

        Element job = doc.createElement("job");
        rootElement.appendChild(job);
        
        String title1 = removeHtml(jb.getJobTitle());
        

        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(title1));
        job.appendChild(title);
        









        Element jobboardname = doc.createElement("job-board-name");
        jobboardname.appendChild(doc.createTextNode(jb.getOrganization().getOrgName() + " Job Board"));
        job.appendChild(jobboardname);
        
        UserRegData userRegData = BOFactory.getUserBO().getUserRegDataById(jb.getSuper_user_key());
        String urluniq2 = "http://www." + Constant.getValue("domain.name");
        
        Element jobboardurl = doc.createElement("job-board-url");
        jobboardurl.appendChild(doc.createTextNode(urluniq2));
        job.appendChild(jobboardurl);
        
        Element jobcode = doc.createElement("job-code");
        jobcode.appendChild(doc.createTextNode(jb.getJobreqcode()));
        job.appendChild(jobcode);
        
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=SIMP";
        
        Element detailurl = doc.createElement("detail-url");
        detailurl.appendChild(doc.createTextNode(urluniq));
        job.appendChild(detailurl);
        
        Element applyurl = doc.createElement("apply-url");
        applyurl.appendChild(doc.createTextNode(urluniq));
        job.appendChild(applyurl);
        if (jb.getJobcategory() != null)
        {
          Element category = doc.createElement("job-category");
          category.appendChild(doc.createTextNode(jb.getJobcategory().getJobCategoryName()));
          job.appendChild(category);
        }
        else
        {
          Element category = doc.createElement("job-category");
          category.appendChild(doc.createTextNode("Other"));
          job.appendChild(category);
        }
        Element description = doc.createElement("description");
        job.appendChild(description);
        
        String desc1 = removeHtml(jb.getJobDetails());
        
        Element summary = doc.createElement("summary");
        summary.appendChild(doc.createTextNode("<![CDATA[" + desc1 + "]]>"));
        description.appendChild(summary);
        if (!StringUtils.isNullOrEmpty(jb.getPrimarySkill()))
        {
          Element requiredskills = doc.createElement("required-skills");
          requiredskills.appendChild(doc.createTextNode("<![CDATA[" + jb.getPrimarySkill() + "]]>"));
          description.appendChild(requiredskills);
        }
        if (jb.getMinimumLevelOfEducation() != null)
        {
          Element requirededucation = doc.createElement("required-education");
          requirededucation.appendChild(doc.createTextNode(jb.getMinimumLevelOfEducation()));
          description.appendChild(requirededucation);
        }
        String exmp = "";
        if ((jb.getMinyearsofExpRequired() != 0) || (jb.getMaxyearsofExpRequired() != 0)) {
          if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() == 0)) {
            exmp = jb.getMinyearsofExpRequired() + "+ year(s)";
          } else if ((jb.getMinyearsofExpRequired() == 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMaxyearsofExpRequired() + " year(s)";
          } else if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMinyearsofExpRequired() + "-" + jb.getMaxyearsofExpRequired() + " year(s)";
          }
        }
        if (!StringUtils.isNullOrEmpty(exmp))
        {
          Element experience = doc.createElement("required-experience");
          experience.appendChild(doc.createTextNode(exmp));
          description.appendChild(experience);
        }
        if (jb.getJobtype() != null)
        {
          Element fulltime = doc.createElement("full-time");
          if (jb.getJobtype().getJobTypeName().equals("Full Time")) {
            fulltime.appendChild(doc.createTextNode("1"));
          } else {
            fulltime.appendChild(doc.createTextNode(""));
          }
          description.appendChild(fulltime);
        }
        if (jb.getJobtype() != null)
        {
          Element parttime = doc.createElement("part-time");
          if (jb.getJobtype().getJobTypeName().equals("Part Time")) {
            parttime.appendChild(doc.createTextNode("1"));
          } else {
            parttime.appendChild(doc.createTextNode(""));
          }
          description.appendChild(parttime);
        }
        Element flextime = doc.createElement("flex-time");
        flextime.appendChild(doc.createTextNode(""));
        description.appendChild(flextime);
        
        Element internship = doc.createElement("internship");
        internship.appendChild(doc.createTextNode(""));
        description.appendChild(internship);
        
        Element volunteer = doc.createElement("volunteer");
        volunteer.appendChild(doc.createTextNode(""));
        description.appendChild(volunteer);
        if (jb.getFlsa() != null)
        {
          Element exempt = doc.createElement("exempt");
          if (jb.getFlsa().getFlsaName().equals("Exempt")) {
            exempt.appendChild(doc.createTextNode("1"));
          } else {
            exempt.appendChild(doc.createTextNode(""));
          }
          description.appendChild(exempt);
        }
        if (jb.getJobtype() != null)
        {
          Element contract = doc.createElement("contract");
          if (jb.getJobtype().getJobTypeName().equals("Contract")) {
            contract.appendChild(doc.createTextNode("1"));
          } else {
            contract.appendChild(doc.createTextNode(""));
          }
          description.appendChild(contract);
        }
        if (jb.getJobtype() != null)
        {
          Element permanent = doc.createElement("permanent");
          if (jb.getJobtype().getJobTypeName().equals("Regular")) {
            permanent.appendChild(doc.createTextNode("1"));
          } else {
            permanent.appendChild(doc.createTextNode(""));
          }
          description.appendChild(permanent);
        }
        if (jb.getJobtype() != null)
        {
          Element temporary = doc.createElement("temporary");
          if (jb.getJobtype().getJobTypeName().equals("Temporary")) {
            temporary.appendChild(doc.createTextNode("1"));
          } else {
            temporary.appendChild(doc.createTextNode(""));
          }
          description.appendChild(temporary);
        }
        Element telecommute = doc.createElement("telecommute");
        telecommute.appendChild(doc.createTextNode(""));
        description.appendChild(telecommute);
        

        Element compensation = doc.createElement("compensation");
        job.appendChild(compensation);
        if (jb.getSalaryplan() != null)
        {
          Element salaryrange = doc.createElement("salary-range");
          salaryrange.appendChild(doc.createTextNode(jb.getSalaryplan().getFromRangeAmount() + "~" + jb.getSalaryplan().getToRangeAmount()));
          compensation.appendChild(salaryrange);
        }
        if (jb.getSalaryplan() != null)
        {
          Element salaryamount = doc.createElement("salary-amount");
          salaryamount.appendChild(doc.createTextNode(""));
          compensation.appendChild(salaryamount);
        }
        if (jb.getSalaryplan() != null)
        {
          Element salarycurrency = doc.createElement("salary-currency");
          salarycurrency.appendChild(doc.createTextNode(jb.getSalaryplan().getCurrencyCode()));
          compensation.appendChild(salarycurrency);
        }
        Element benefits = doc.createElement("benefits");
        benefits.appendChild(doc.createTextNode(""));
        compensation.appendChild(benefits);
        

        Date dt = new Date();
        Element posteddate = doc.createElement("posted-date");
        
        posteddate.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.yyyyMMddSimpleHired)));
        job.appendChild(posteddate);
        














        Element location = doc.createElement("location");
        job.appendChild(location);
        





        Location loc = jb.getLocation();
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getAddress())))
        {
          Element address = doc.createElement("address");
          address.appendChild(doc.createTextNode(loc.getAddress()));
          location.appendChild(address);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
        {
          Element city = doc.createElement("city");
          city.appendChild(doc.createTextNode(loc.getCity()));
          location.appendChild(city);
        }
        if ((loc != null) && (loc.getState() != null))
        {
          Element state = doc.createElement("state");
          state.appendChild(doc.createTextNode(loc.getState().getStateName()));
          location.appendChild(state);
        }
        else
        {
          Element state = doc.createElement("state");
          state.appendChild(doc.createTextNode(loc.getCountry().getCountryName()));
          location.appendChild(state);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
        {
          Element zip = doc.createElement("zip");
          zip.appendChild(doc.createTextNode(loc.getZip()));
          location.appendChild(zip);
        }
        if ((loc != null) && (loc.getCountry() != null))
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode(loc.getCountry().getCountryCode()));
          location.appendChild(country);
        }
        else
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode("US"));
          location.appendChild(country);
        }
        Element contact = doc.createElement("contact");
        job.appendChild(contact);
        if (!StringUtils.isNullOrEmpty(jb.getRecruiterName()))
        {
          Element name = doc.createElement("name");
          name.appendChild(doc.createTextNode(jb.getRecruiterName()));
          contact.appendChild(name);
        }
        User recruiter = BOFactory.getUserBO().getUserByUserid(jb.getRecruiterId());
        if (!StringUtils.isNullOrEmpty(recruiter.getEmailId()))
        {
          Element email = doc.createElement("email");
          email.appendChild(doc.createTextNode(recruiter.getEmailId()));
          contact.appendChild(email);
        }
        if (jb.getHiringmgr() != null)
        {
          Element hiringmanagername = doc.createElement("hiring-manager-name");
          hiringmanagername.appendChild(doc.createTextNode(jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName()));
          contact.appendChild(hiringmanagername);
        }
        if (jb.getHiringmgr() != null)
        {
          Element hiringmanageremail = doc.createElement("hiring-manager-email");
          hiringmanageremail.appendChild(doc.createTextNode(jb.getHiringmgr().getEmailId()));
          contact.appendChild(hiringmanageremail);
        }
        if (!StringUtils.isNullOrEmpty(recruiter.getPhoneOffice()))
        {
          Element phone = doc.createElement("phone");
          phone.appendChild(doc.createTextNode(recruiter.getPhoneOffice()));
          contact.appendChild(phone);
        }
        Element fax = doc.createElement("fax");
        fax.appendChild(doc.createTextNode(""));
        contact.appendChild(fax);
        

        Element company = doc.createElement("company");
        job.appendChild(company);
        if (jb.getOrganization() != null)
        {
          Element name = doc.createElement("name");
          name.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
          company.appendChild(name);
          
          String orgnote = jb.getOrganization().getNotes() == null ? "NA" : jb.getOrganization().getNotes();
          Element desc = doc.createElement("description");
          desc.appendChild(doc.createTextNode("<![CDATA[" + orgnote + "]]>"));
          company.appendChild(desc);
          






          Element url = doc.createElement("url");
          url.appendChild(doc.createTextNode(urluniq2));
          company.appendChild(url);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "simply_hired_job_feed.xml"));
      



      transformer.transform(source, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "simply_hired_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createSimplyHiredJobFeed", e);
    }
  }
  
  public void createJujuJobFeed(List jobsList)
  {
    logger.info("inside createJujuJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      


      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("positionfeed");
      
      Attr attr1 = doc.createAttribute("xmlns");
      attr1.setValue("http://www.job-search-engine.com/employers/positionfeed-namespace/");
      rootElement.setAttributeNode(attr1);
      
      Attr attr2 = doc.createAttribute("xmlns:xsi");
      attr2.setValue("http://www.w3.org/2001/XMLSchema-instance");
      rootElement.setAttributeNode(attr2);
      
      Attr attr3 = doc.createAttribute("xsi:schemaLocation");
      attr3.setValue("http://www.job-search-engine.com/add-jobs/positionfeed-namespace/ http://www.job-search-engine.com/add-jobs/positionfeed.xsd");
      rootElement.setAttributeNode(attr3);
      
      Attr attr4 = doc.createAttribute("version");
      attr4.setValue("2006-04");
      rootElement.setAttributeNode(attr4);
      
      doc.appendChild(rootElement);
      
      Date dt = new Date();
      UserRegData userRegData = null;
      String urluniq2 = "";
      
      Element source = doc.createElement("source");
      source.appendChild(doc.createTextNode("Hires360 Job Board"));
      rootElement.appendChild(source);
      

      Element sourceurl = doc.createElement("sourceurl");
      urluniq2 = "http://www." + Constant.getValue("domain.name");
      sourceurl.appendChild(doc.createTextNode(urluniq2));
      rootElement.appendChild(sourceurl);
      
      Element feeddate = doc.createElement("feeddate");
      feeddate.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(dt, DateUtil.jujuFeedDate)));
      rootElement.appendChild(feeddate);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        if ((jb.getLocation() != null) && (jb.getLocation().getCountry() != null) && (jb.getLocation().getCountry().getCountryCode().equalsIgnoreCase("us")))
        {
          userRegData = BOFactory.getUserBO().getUserRegDataById(jb.getSuper_user_key());
          

          Element job = doc.createElement("job");
          rootElement.appendChild(job);
          

          Attr attr = doc.createAttribute("id");
          attr.setValue(String.valueOf(jb.getJobreqId()));
          job.setAttributeNode(attr);
          

          Element employer = doc.createElement("employer");
          employer.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
          job.appendChild(employer);
          
          Element title = doc.createElement("title");
          title.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
          job.appendChild(title);
          
          String desc = removeHtml(jb.getJobDetails());
          

          Element description = doc.createElement("description");
          description.appendChild(doc.createTextNode(desc));
          job.appendChild(description);
          

          Element postingdate = doc.createElement("postingdate");
          postingdate.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.yyyyMMdd)));
          job.appendChild(postingdate);
          


          String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=JUJU";
          
          Element joburl = doc.createElement("joburl");
          joburl.appendChild(doc.createTextNode(urluniq));
          job.appendChild(joburl);
          
          Element location = doc.createElement("location");
          job.appendChild(location);
          

          Location loc = jb.getLocation();
          if ((loc != null) && (loc.getCountry() != null))
          {
            Element nation = doc.createElement("nation");
            nation.appendChild(doc.createTextNode(loc.getCountry().getCountryCode()));
            location.appendChild(nation);
          }
          else
          {
            Element country = doc.createElement("nation");
            country.appendChild(doc.createTextNode("US"));
            location.appendChild(country);
          }
          if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
          {
            Element city = doc.createElement("city");
            city.appendChild(doc.createTextNode(loc.getCity()));
            location.appendChild(city);
          }
          if ((loc != null) && (loc.getCountry() != null))
          {
            Element county = doc.createElement("county");
            county.appendChild(doc.createTextNode(loc.getCountry().getCountryCode()));
            location.appendChild(county);
          }
          if ((loc != null) && (loc.getState() != null))
          {
            Element state = doc.createElement("state");
            state.appendChild(doc.createTextNode(loc.getState().getStateName()));
            location.appendChild(state);
          }
          if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
          {
            Element zip = doc.createElement("zip");
            zip.appendChild(doc.createTextNode(loc.getZip()));
            location.appendChild(zip);
          }
          if (jb.getSalaryplan() != null)
          {
            Element salary = doc.createElement("salary");
            job.appendChild(salary);
            

            Element period = doc.createElement("period");
            period.appendChild(doc.createTextNode(jb.getCompfrequency().getCompFrequencyName()));
            salary.appendChild(period);
            
            Element min = doc.createElement("min");
            min.appendChild(doc.createTextNode(jb.getOrganization().getCurrencyCode() + " " + jb.getSalaryplan().getFromRangeAmount()));
            salary.appendChild(min);
            
            Element max = doc.createElement("max");
            max.appendChild(doc.createTextNode(jb.getOrganization().getCurrencyCode() + " " + jb.getSalaryplan().getToRangeAmount()));
            salary.appendChild(max);
          }
          if (jb.getJobtype() != null)
          {
            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode(jb.getJobtype().getJobTypeName()));
            job.appendChild(type);
          }
          String exmp = "";
          if ((jb.getMinyearsofExpRequired() != 0) || (jb.getMaxyearsofExpRequired() != 0))
          {
            Element experience = doc.createElement("experience");
            experience.appendChild(doc.createTextNode(exmp));
            job.appendChild(experience);
            if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() == 0))
            {
              exmp = jb.getMinyearsofExpRequired() + "";
              
              Element min = doc.createElement("min");
              min.appendChild(doc.createTextNode(exmp));
              experience.appendChild(min);
            }
            if ((jb.getMinyearsofExpRequired() == 0) && (jb.getMaxyearsofExpRequired() != 0))
            {
              exmp = jb.getMaxyearsofExpRequired() + "";
              
              Element max = doc.createElement("max");
              max.appendChild(doc.createTextNode(exmp));
              experience.appendChild(max);
            }
            if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() != 0))
            {
              exmp = jb.getMinyearsofExpRequired() + "";
              Element min = doc.createElement("min");
              min.appendChild(doc.createTextNode(exmp));
              experience.appendChild(min);
              
              exmp = jb.getMaxyearsofExpRequired() + "";
              Element max = doc.createElement("max");
              max.appendChild(doc.createTextNode(exmp));
              experience.appendChild(max);
            }
          }
          if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
          {
            Element education = doc.createElement("education");
            education.appendChild(doc.createTextNode(jb.getMinimumLevelOfEducation()));
            job.appendChild(education);
          }
          Element jobsource = doc.createElement("jobsource");
          jobsource.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
          job.appendChild(jobsource);
          

          Element jobsourceurl = doc.createElement("jobsourceurl");
          urluniq2 = "http://www." + Constant.getValue("domain.name");
          jobsourceurl.appendChild(doc.createTextNode(urluniq2));
          job.appendChild(jobsourceurl);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source2 = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "juju_job_feed.xml"));
      



      transformer.transform(source2, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "juju_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createJujuJobFeed", e);
    }
  }
  
  public void createOlxJobFeed(List jobsList)
  {
    logger.info("inside createOlxJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Date dt = new Date();
      
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("ADS");
      doc.appendChild(rootElement);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        if ((jb.getLocation() != null) && (jb.getLocation().getState() != null))
        {
          Element ad = doc.createElement("AD");
          rootElement.appendChild(ad);
          
          Element jobid = doc.createElement("ID");
          jobid.appendChild(doc.createTextNode("<![CDATA[" + jb.getJobreqId() + "]]>"));
          ad.appendChild(jobid);
          
          Element status = doc.createElement("STATUS");
          status.appendChild(doc.createTextNode("N"));
          ad.appendChild(status);
          
          String jobtitle = jb.getJobTitle() + " for " + jb.getOrganization().getOrgName();
          Element title = doc.createElement("TITLE");
          title.appendChild(doc.createTextNode("<![CDATA[" + removeHtml(jobtitle) + "]]>"));
          ad.appendChild(title);
          
          String desc1 = removeHtml(jb.getJobDetails());
          

          Element desc = doc.createElement("DESCRIPTION");
          desc.appendChild(doc.createTextNode("<![CDATA[" + desc1 + "]]>"));
          ad.appendChild(desc);
          
          Element date = doc.createElement("DATE");
          date.appendChild(doc.createTextNode("<![CDATA[" + DateUtil.convertDateToStringDate(dt, DateUtil.olxFeedDate) + "]]>"));
          ad.appendChild(date);
          











          User recruiter = BOFactory.getUserBO().getUserByUserid(jb.getRecruiterId());
          if (!StringUtils.isNullOrEmpty(recruiter.getEmailId()))
          {
            Element email = doc.createElement("EMAIL");
            email.appendChild(doc.createTextNode("<![CDATA[" + recruiter.getEmailId() + "]]>"));
            ad.appendChild(email);
          }
          if (!StringUtils.isNullOrEmpty(recruiter.getPhoneOffice()))
          {
            Element phone = doc.createElement("PHONE");
            phone.appendChild(doc.createTextNode("<![CDATA[" + recruiter.getPhoneOffice() + "]]>"));
            ad.appendChild(phone);
          }
          Location loc = jb.getLocation();
          if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getAddress())))
          {
            Element address = doc.createElement("ADDRESS");
            address.appendChild(doc.createTextNode("<![CDATA[" + loc.getAddress() + "]]>"));
            ad.appendChild(address);
          }
          if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
          {
            Element zip = doc.createElement("ZIP_CODE");
            zip.appendChild(doc.createTextNode("<![CDATA[" + loc.getZip() + "]]>"));
            ad.appendChild(zip);
          }
          if ((loc != null) && (loc.getCountry() != null))
          {
            Element country = doc.createElement("LOCATION_COUNTRY");
            country.appendChild(doc.createTextNode("<![CDATA[" + loc.getCountry().getCountryCode() + "]]>"));
            ad.appendChild(country);
          }
          else
          {
            Element country = doc.createElement("LOCATION_COUNTRY");
            country.appendChild(doc.createTextNode("<![CDATA[US]]>"));
            ad.appendChild(country);
          }
          if ((loc != null) && (loc.getState() != null))
          {
            Element state = doc.createElement("LOCATION_STATE");
            state.appendChild(doc.createTextNode("<![CDATA[" + loc.getState().getOlxStateCode() + "]]>"));
            ad.appendChild(state);
          }
          if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
          {
            Element city = doc.createElement("LOCATION_CITY");
            city.appendChild(doc.createTextNode("<![CDATA[" + loc.getCity() + "]]>"));
            ad.appendChild(city);
          }
          if (jb.getJobcategory() != null)
          {
            String olxcode = jb.getJobcategory().getOlx_job_code() == null ? "329" : jb.getJobcategory().getOlx_job_code();
            Element category = doc.createElement("CATEGORY");
            category.appendChild(doc.createTextNode("<![CDATA[" + olxcode + "]]>"));
            ad.appendChild(category);
          }
          else
          {
            String olxcode = "329";
            Element category = doc.createElement("CATEGORY");
            category.appendChild(doc.createTextNode("<![CDATA[" + olxcode + "]]>"));
            ad.appendChild(category);
          }
          if (jb.getOrganization() != null)
          {
            Element price = doc.createElement("PRICE");
            price.appendChild(doc.createTextNode("<![CDATA[]]>"));
            ad.appendChild(price);
          }
          if (jb.getOrganization() != null)
          {
            Element currency = doc.createElement("CURRENCY");
            currency.appendChild(doc.createTextNode("<![CDATA[" + jb.getOrganization().getCurrencyCode() + "]]>"));
            ad.appendChild(currency);
          }
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source2 = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "olx_job_feed.xml"));
      



      transformer.transform(source2, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "olx_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createOlxJobFeed", e);
    }
  }
  
  public void createOodleJobFeed(List jobsList)
  {
    logger.info("inside createOodleJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      


      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("listings");
      doc.appendChild(rootElement);
      for (int i = 0; i < jobsList.size(); i++)
      {
        Element listing = doc.createElement("listing");
        rootElement.appendChild(listing);
        
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        if (jb.getJobcategory() != null)
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode(jb.getJobcategory().getJobCategoryName()));
          listing.appendChild(category);
        }
        else
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode(""));
          listing.appendChild(category);
        }
        String desc = removeHtml(jb.getJobDetails());
        if (!StringUtils.isNullOrEmpty(desc))
        {
          Element description = doc.createElement("description");
          description.appendChild(doc.createTextNode(desc));
          listing.appendChild(description);
        }
        else
        {
          Element description = doc.createElement("description");
          description.appendChild(doc.createTextNode(""));
          listing.appendChild(description);
        }
        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode("" + jb.getJobreqId()));
        listing.appendChild(id);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
        listing.appendChild(title);
        

        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=OODLE";
        
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode(urluniq));
        listing.appendChild(url);
        
        Location loc = jb.getLocation();
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getAddress())))
        {
          Element streetaddress = doc.createElement("address");
          streetaddress.appendChild(doc.createTextNode(loc.getAddress()));
          listing.appendChild(streetaddress);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
        {
          Element city = doc.createElement("city");
          city.appendChild(doc.createTextNode(loc.getCity()));
          listing.appendChild(city);
        }
        if ((loc != null) && (loc.getCountry() != null))
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode(loc.getCountry().getCountryCode()));
          listing.appendChild(country);
        }
        else
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode("US"));
          listing.appendChild(country);
        }
        if ((loc != null) && (loc.getState() != null))
        {
          Element state = doc.createElement("state");
          state.appendChild(doc.createTextNode(loc.getState().getStateName()));
          listing.appendChild(state);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
        {
          Element zip = doc.createElement("zip_code");
          zip.appendChild(doc.createTextNode(loc.getZip()));
          listing.appendChild(zip);
        }
        Element benefits = doc.createElement("benefits");
        benefits.appendChild(doc.createTextNode(""));
        listing.appendChild(benefits);
        if (jb.getOrganization() != null)
        {
          Element company = doc.createElement("company");
          company.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
          listing.appendChild(company);
        }
        if (jb.getOrganization() != null)
        {
          Element currency = doc.createElement("currency");
          currency.appendChild(doc.createTextNode(jb.getOrganization().getCurrencyCode()));
          listing.appendChild(currency);
        }
        Element create_time = doc.createElement("create_time");
        create_time.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.yyyyMMdd)));
        listing.appendChild(create_time);
        if (jb.getJobtype() != null)
        {
          Element employeetype = doc.createElement("employee_type");
          employeetype.appendChild(doc.createTextNode(jb.getJobtype().getJobTypeName()));
          listing.appendChild(employeetype);
        }
        if (jb.getJobcategory() != null)
        {
          String catcode = jb.getJobcategory().getOodle_job_code() == null ? "Other Jobs" : jb.getJobcategory().getOodle_job_code();
          Element industry = doc.createElement("industry");
          industry.appendChild(doc.createTextNode(catcode));
          listing.appendChild(industry);
        }
        else
        {
          Element industry = doc.createElement("industry");
          industry.appendChild(doc.createTextNode("Other Jobs"));
          listing.appendChild(industry);
        }
        Element registration = doc.createElement("registration");
        String allowed = "no";
        registration.appendChild(doc.createTextNode(allowed));
        listing.appendChild(registration);
        if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
        {
          Element requirededucation = doc.createElement("required_education");
          requirededucation.appendChild(doc.createTextNode(jb.getMinimumLevelOfEducation()));
          listing.appendChild(requirededucation);
        }
        String exmp = "";
        if ((jb.getMinyearsofExpRequired() != 0) || (jb.getMaxyearsofExpRequired() != 0)) {
          if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() == 0)) {
            exmp = jb.getMinyearsofExpRequired() + "+ year(s)";
          } else if ((jb.getMinyearsofExpRequired() == 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMaxyearsofExpRequired() + " year(s)";
          } else if ((jb.getMinyearsofExpRequired() != 0) && (jb.getMaxyearsofExpRequired() != 0)) {
            exmp = jb.getMinyearsofExpRequired() + "-" + jb.getMaxyearsofExpRequired() + " year(s)";
          }
        }
        if (!StringUtils.isNullOrEmpty(exmp))
        {
          Element experience = doc.createElement("required_experience");
          experience.appendChild(doc.createTextNode(exmp));
          listing.appendChild(experience);
        }
        if (jb.getSalaryplan() != null)
        {
          Element salaryrange = doc.createElement("salary");
          salaryrange.appendChild(doc.createTextNode(jb.getSalaryplan().getFromRangeAmount() + "~" + jb.getSalaryplan().getToRangeAmount()));
          listing.appendChild(salaryrange);
        }
        if (jb.getCompfrequency() != null)
        {
          Element salarytype = doc.createElement("salary_type");
          salarytype.appendChild(doc.createTextNode(jb.getCompfrequency().getCompFrequencyName()));
          listing.appendChild(salarytype);
        }
        Element secondarysource = doc.createElement("secondary_source");
        String urluniq2 = "http://www." + Constant.getValue("domain.name");
        secondarysource.appendChild(doc.createTextNode(urluniq2));
        listing.appendChild(secondarysource);
        
        User recruiter = BOFactory.getUserBO().getUserByUserid(jb.getRecruiterId());
        if (!StringUtils.isNullOrEmpty(recruiter.getEmailId()))
        {
          Element selleremail = doc.createElement("seller_email");
          selleremail.appendChild(doc.createTextNode(recruiter.getEmailId()));
          listing.appendChild(selleremail);
        }
        if (recruiter != null)
        {
          Element sellername = doc.createElement("seller_name");
          sellername.appendChild(doc.createTextNode(recruiter.getFirstName() + " " + recruiter.getLastName()));
          listing.appendChild(sellername);
        }
        if (!StringUtils.isNullOrEmpty(recruiter.getPhoneOffice()))
        {
          Element phone = doc.createElement("seller_phone");
          phone.appendChild(doc.createTextNode(recruiter.getPhoneOffice()));
          listing.appendChild(phone);
        }
        Element sellerurl = doc.createElement("seller_url");
        sellerurl.appendChild(doc.createTextNode(urluniq));
        listing.appendChild(sellerurl);
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source2 = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "oodle_job_feed.xml"));
      




      transformer.transform(source2, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "oodle_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createOodleJobFeed", e);
    }
  }
  
  public void createJoobleJobFeed(List jobsList)
  {
    logger.info("inside createJoobleJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      


      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("jobs");
      doc.appendChild(rootElement);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        

        Element job = doc.createElement("job");
        rootElement.appendChild(job);
        

        Attr attr = doc.createAttribute("id");
        attr.setValue(String.valueOf(jb.getJobreqId()));
        job.setAttributeNode(attr);
        


        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=JOOBLE";
        
        Element url = doc.createElement("link");
        url.appendChild(doc.createTextNode(urluniq));
        job.appendChild(url);
        


        Element title = doc.createElement("name");
        title.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
        job.appendChild(title);
        
        Location loc = jb.getLocation();
        if (loc != null)
        {
          Element region = doc.createElement("region");
          region.appendChild(doc.createTextNode(loc.getLocationName()));
          job.appendChild(region);
        }
        if (jb.getSalaryplan() != null)
        {
          Element salaryrange = doc.createElement("salary");
          salaryrange.appendChild(doc.createTextNode(jb.getSalaryplan().getFromRangeAmount() + " - " + jb.getSalaryplan().getToRangeAmount() + " " + jb.getSalaryplan().getCurrencyCode()));
          job.appendChild(salaryrange);
        }
        else
        {
          Element salaryrange = doc.createElement("salary");
          salaryrange.appendChild(doc.createTextNode("Not mentioned"));
          job.appendChild(salaryrange);
        }
        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
        job.appendChild(company);
        
        UserRegData userRegData = BOFactory.getUserBO().getUserRegDataById(jb.getSuper_user_key());
        String curl = "http://" + userRegData.getSubdomain() + "." + Constant.getValue("domain.name");
        Element company_url = doc.createElement("company_url");
        company_url.appendChild(doc.createTextNode(curl));
        job.appendChild(company_url);
        
        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(removeHtml(jb.getJobDetails())));
        job.appendChild(description);
        if (jb.getTargetfinishdate() != null)
        {
          Element expire_time = doc.createElement("expire");
          expire_time.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getTargetfinishdate(), DateUtil.mmDDyyyy)));
          job.appendChild(expire_time);
        }
        else
        {
          Date dt = new Date();
          dt.setMonth(dt.getMonth() + 2);
          Element expire_time = doc.createElement("expire");
          expire_time.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(dt, DateUtil.mmDDyyyy)));
          job.appendChild(expire_time);
        }
        if (jb.getPublishedDate() != null)
        {
          Element updated = doc.createElement("updated");
          updated.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.mmDDyyyy)));
          job.appendChild(updated);
        }
        else
        {
          Date dt = new Date();
          Element updated = doc.createElement("updated");
          updated.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(dt, DateUtil.mmDDyyyy)));
          job.appendChild(updated);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source2 = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "jooble_job_feed.xml"));
      




      transformer.transform(source2, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "jooble_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createJoobleJobFeed", e);
    }
  }
  
  public void createTrovitJobFeed(List jobsList)
  {
    logger.info("inside createTrovitJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("trovit");
      doc.appendChild(rootElement);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        
        Element ad = doc.createElement("ad");
        rootElement.appendChild(ad);
        
        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode("<![CDATA[" + jb.getJobreqId() + "]]>"));
        ad.appendChild(id);
        
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=TROVIT";
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode("<![CDATA[" + urluniq + "]]>"));
        ad.appendChild(url);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode("<![CDATA[" + removeHtml(jb.getJobTitle()) + "]]>"));
        ad.appendChild(title);
        
        String desc = removeHtml(jb.getJobDetails());
        

        Element content = doc.createElement("content");
        content.appendChild(doc.createTextNode("<![CDATA[" + desc + "]]>"));
        ad.appendChild(content);
        

        Location loc = jb.getLocation();
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
        {
          Element city = doc.createElement("city");
          city.appendChild(doc.createTextNode("<![CDATA[" + loc.getCity() + "]]>"));
          ad.appendChild(city);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getAddress())))
        {
          Element cityarea = doc.createElement("city_area");
          cityarea.appendChild(doc.createTextNode("<![CDATA[" + loc.getAddress() + "]]>"));
          ad.appendChild(cityarea);
        }
        if ((loc != null) && (loc.getCountry() != null))
        {
          Element region = doc.createElement("region");
          region.appendChild(doc.createTextNode("<![CDATA[" + loc.getCountry().getCountryName() + "]]>"));
          ad.appendChild(region);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
        {
          Element postcode = doc.createElement("postcode");
          postcode.appendChild(doc.createTextNode("<![CDATA[" + loc.getZip() + "]]>"));
          ad.appendChild(postcode);
        }
        if (jb.getSalaryplan() != null)
        {
          Element salary = doc.createElement("salary");
          salary.appendChild(doc.createTextNode("<![CDATA[" + jb.getSalaryplan().getFromRangeAmount() + "-" + jb.getSalaryplan().getToRangeAmount() + "]]>"));
          ad.appendChild(salary);
        }
        if (!StringUtils.isNullOrEmpty(jb.getStdworkinghoursunitName()))
        {
          Element workinghours = doc.createElement("working_hours");
          workinghours.appendChild(doc.createTextNode("<![CDATA[" + jb.getStdworkinghoursunitName() + "- " + jb.getDefaultStandardHours() + "]]>"));
          ad.appendChild(workinghours);
        }
        if (jb.getOrganization() != null)
        {
          Element company = doc.createElement("company");
          company.appendChild(doc.createTextNode("<![CDATA[" + jb.getOrganization().getOrgName() + "]]>"));
          ad.appendChild(company);
        }
        if (jb.getMinyearsofExpRequired() != 0)
        {
          String exmp = "at least " + jb.getMinyearsofExpRequired() + " years";
          
          Element experience = doc.createElement("experience");
          experience.appendChild(doc.createTextNode("<![CDATA[" + exmp + "]]>"));
          ad.appendChild(experience);
        }
        String req = jb.getJobRoles();
        if (!StringUtils.isNullOrEmpty(req)) {
          req = req.replace("\"", "");
        }
        Element requirements = doc.createElement("requirements");
        requirements.appendChild(doc.createTextNode("<![CDATA[" + req + "]]>"));
        ad.appendChild(requirements);
        if (jb.getJobtype() != null)
        {
          Element contract = doc.createElement("contract");
          contract.appendChild(doc.createTextNode("<![CDATA[" + jb.getJobtype().getJobTypeName() + "]]>"));
          ad.appendChild(contract);
        }
        if (jb.getJobcategory() != null)
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode("<![CDATA[" + jb.getJobcategory().getJobCategoryName() + "]]>"));
          ad.appendChild(category);
        }
        Date dt = new Date();
        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode("<![CDATA[" + DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.ddMMyyyyTrovit) + "]]>"));
        ad.appendChild(date);
        
        Element time = doc.createElement("time");
        time.appendChild(doc.createTextNode("<![CDATA[" + dt.getHours() + ":" + dt.getMinutes() + "]]>"));
        ad.appendChild(time);
        if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
        {
          Element studies = doc.createElement("studies");
          studies.appendChild(doc.createTextNode("<![CDATA[" + jb.getMinimumLevelOfEducation() + "]]>"));
          ad.appendChild(studies);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source2 = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "trovit_job_feed.xml"));
      



      transformer.transform(source2, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "trovit_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createTrovitJobFeed", e);
    }
  }
  
  private void replaceCharInFile(String pfile)
  {
    StringBuffer strbuffer = new StringBuffer();
    try
    {
      FileInputStream fstream = new FileInputStream(pfile);
      
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String strLine;
      while ((strLine = br.readLine()) != null)
      {
        strLine = strLine.replace("&lt;", "<");
        strLine = strLine.replace("&gt;", ">");
        

        strbuffer.append(strLine);
      }
      in.close();
    }
    catch (Exception e)
    {
      logger.info("Error: " + e);
    }
    try
    {
      FileWriter fstream = new FileWriter(pfile);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(strbuffer.toString());
      
      out.close();
    }
    catch (Exception e)
    {
      logger.info("Error: " + e);
    }
  }
  
  public void createTwitJobSearchJobFeed(List jobsList)
  {
    logger.info("inside createTwitJobSearchJobFeed" + jobsList.size());
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Date dt = new Date();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("jobs");
      doc.appendChild(rootElement);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        logger.info("inside job" + removeHtml(jb.getJobTitle()));
        if (i == 0)
        {
          Element publisher = doc.createElement("publisher-name");
          publisher.appendChild(doc.createTextNode("<![CDATA[Hires360 Job Board]]>"));
          rootElement.appendChild(publisher);
          

          String urluniq2 = "http://www." + Constant.getValue("domain.name");
          
          Element publisherurl = doc.createElement("publisher-url");
          publisherurl.appendChild(doc.createTextNode("<![CDATA[" + urluniq2 + "]]>"));
          rootElement.appendChild(publisherurl);
        }
        Element job = doc.createElement("job");
        rootElement.appendChild(job);
        
        Element jobid = doc.createElement("id");
        jobid.appendChild(doc.createTextNode(String.valueOf(jb.getJobreqId())));
        job.appendChild(jobid);
        
        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.twFeedDate)));
        job.appendChild(date);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
        job.appendChild(title);
        
        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
        job.appendChild(company);
        
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=TWITJOBSEARCH";
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode(urluniq));
        job.appendChild(url);
        if (jb.getSalaryplan() != null)
        {
          Element salary = doc.createElement("salary");
          salary.appendChild(doc.createTextNode(jb.getSalaryplan().getFromRangeAmount() + " - " + jb.getSalaryplan().getToRangeAmount()));
          job.appendChild(salary);
        }
        if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
        {
          Element education = doc.createElement("education");
          education.appendChild(doc.createTextNode(jb.getMinimumLevelOfEducation()));
          job.appendChild(education);
        }
        String exmp = "";
        if (jb.getMinyearsofExpRequired() != 0) {
          exmp = jb.getMinyearsofExpRequired() + "+ years";
        }
        if (!StringUtils.isNullOrEmpty(exmp))
        {
          Element experience = doc.createElement("experience");
          experience.appendChild(doc.createTextNode(exmp));
          job.appendChild(experience);
        }
        if ((jb.getLocation() != null) && (jb.getLocation().getCity() != null))
        {
          Element location = doc.createElement("location");
          location.appendChild(doc.createTextNode(jb.getLocation().getCity()));
          job.appendChild(location);
        }
        else
        {
          Element location = doc.createElement("location");
          location.appendChild(doc.createTextNode(jb.getLocation().getCountry().getCountryName()));
          job.appendChild(location);
        }
        if ((jb.getLocation() != null) && (!StringUtils.isNullOrEmpty(jb.getLocation().getZip())))
        {
          Element postcode = doc.createElement("postcode");
          postcode.appendChild(doc.createTextNode(jb.getLocation().getZip()));
          job.appendChild(postcode);
        }
        String desc = removeHtml(jb.getJobDetails());
        if (!StringUtils.isNullOrEmpty(desc))
        {
          Element description = doc.createElement("description");
          description.appendChild(doc.createTextNode(desc));
          job.appendChild(description);
        }
        if (jb.getJobcategory() != null)
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode(jb.getJobcategory().getJobCategoryName()));
          job.appendChild(category);
        }
      }
      logger.info(doc.toString());
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "twit_job_feed.xml"));
      


      transformer.transform(source, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "twit_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createTwitJobSearchJobFeed", e);
    }
  }
  
  private String removeHtml(String title1)
  {
    if (StringUtils.isNullOrEmpty(title1)) {
      return "";
    }
    title1 = title1.replace("\"", "");
    title1 = title1.replace("&", "");
    title1 = title1.replace("<", "");
    title1 = title1.replace(">", "");
    title1 = title1.replace("?", "");
    title1 = title1.replaceAll("\\<.*?>", "");
    return title1;
  }
  
  public void createWorkHoundJobFeed(List jobsList)
  {
    logger.info("inside createWorkHoundJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Date dt = new Date();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("jobs");
      doc.appendChild(rootElement);
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        if (i == 0)
        {
          Element publisher = doc.createElement("publisher-name");
          publisher.appendChild(doc.createTextNode("<![CDATA[Hires360 Job Board]]>"));
          rootElement.appendChild(publisher);
          

          String urluniq2 = "http://www." + Constant.getValue("domain.name");
          
          Element publisherurl = doc.createElement("publisher-url");
          publisherurl.appendChild(doc.createTextNode("<![CDATA[" + urluniq2 + "]]>"));
          rootElement.appendChild(publisherurl);
        }
        Element job = doc.createElement("job");
        rootElement.appendChild(job);
        
        Element jobid = doc.createElement("id");
        jobid.appendChild(doc.createTextNode(String.valueOf(jb.getJobreqId())));
        job.appendChild(jobid);
        
        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(DateUtil.convertDateToStringDate(jb.getPublishedDate(), DateUtil.twFeedDate)));
        job.appendChild(date);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
        job.appendChild(title);
        
        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
        job.appendChild(company);
        
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=WORKHOUND";
        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode(urluniq));
        job.appendChild(url);
        if (jb.getSalaryplan() != null)
        {
          Element salary = doc.createElement("salary");
          salary.appendChild(doc.createTextNode(jb.getSalaryplan().getFromRangeAmount() + " - " + jb.getSalaryplan().getToRangeAmount()));
          job.appendChild(salary);
        }
        if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
        {
          Element education = doc.createElement("education");
          education.appendChild(doc.createTextNode(jb.getMinimumLevelOfEducation()));
          job.appendChild(education);
        }
        String exmp = "";
        if (jb.getMinyearsofExpRequired() != 0) {
          exmp = jb.getMinyearsofExpRequired() + "+ years";
        }
        if (!StringUtils.isNullOrEmpty(exmp))
        {
          Element experience = doc.createElement("experience");
          experience.appendChild(doc.createTextNode(exmp));
          job.appendChild(experience);
        }
        if ((jb.getLocation() != null) && (jb.getLocation().getCity() != null))
        {
          Element location = doc.createElement("location");
          location.appendChild(doc.createTextNode(jb.getLocation().getCity()));
          job.appendChild(location);
        }
        else
        {
          Element location = doc.createElement("location");
          location.appendChild(doc.createTextNode(jb.getLocation().getCountry().getCountryName()));
          job.appendChild(location);
        }
        if ((jb.getLocation() != null) && (!StringUtils.isNullOrEmpty(jb.getLocation().getZip())))
        {
          Element postcode = doc.createElement("postcode");
          postcode.appendChild(doc.createTextNode(jb.getLocation().getZip()));
          job.appendChild(postcode);
        }
        String desc = removeHtml(jb.getJobDetails());
        if (!StringUtils.isNullOrEmpty(desc))
        {
          Element description = doc.createElement("description");
          description.appendChild(doc.createTextNode(desc));
          job.appendChild(description);
        }
        if (jb.getJobcategory() != null)
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode(jb.getJobcategory().getJobCategoryName()));
          job.appendChild(category);
        }
        else
        {
          Element category = doc.createElement("category");
          category.appendChild(doc.createTextNode("Other"));
          job.appendChild(category);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source2 = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "workHound_job_feed.xml"));
      



      transformer.transform(source2, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "workHound_job_feed.xml");
    }
    catch (Exception e)
    {
      logger.info("Exception on createWorkHoundJobFeed", e);
    }
  }
  
  public void createYakazJobFeed(List jobsList)
  {
    logger.info("inside createYakazJobFeed");
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Date dt = new Date();
      

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("yakaz");
      doc.appendChild(rootElement);
      

      rootElement.setAttribute("version", "1.0");
      for (int i = 0; i < jobsList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)jobsList.get(i);
        
        Element adjobs = doc.createElement("ad-jobs");
        rootElement.appendChild(adjobs);
        

        adjobs.setAttribute("category", "jobs");
        
        Element what = doc.createElement("what");
        adjobs.appendChild(what);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
        what.appendChild(title);
        
        String desc = removeHtml(jb.getJobDetails());
        if (!StringUtils.isNullOrEmpty(desc))
        {
          Element description = doc.createElement("description");
          description.appendChild(doc.createTextNode(desc));
          what.appendChild(description);
        }
        else
        {
          Element description = doc.createElement("description");
          description.appendChild(doc.createTextNode(""));
          what.appendChild(description);
        }
        String urluniq = Constant.getValue("external.url") + "jobs.do?method=jobdetailsext&reqid=" + jb.getJobreqId() + "&secureid=" + jb.getUuid() + "&source=YAKAZ";
        
        Element adurl = doc.createElement("ad-url");
        adurl.appendChild(doc.createTextNode(urluniq));
        what.appendChild(adurl);
        
        Element jobtitle = doc.createElement("jobtitle");
        jobtitle.appendChild(doc.createTextNode(removeHtml(jb.getJobTitle())));
        what.appendChild(jobtitle);
        

        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode(jb.getOrganization().getOrgName()));
        what.appendChild(company);
        if (jb.getSalaryplan() != null)
        {
          Element salary = doc.createElement("salary");
          salary.appendChild(doc.createTextNode(jb.getSalaryplan().getFromRangeAmount() + "-" + jb.getSalaryplan().getToRangeAmount()));
          what.appendChild(salary);
          
          salary.setAttribute("unit", jb.getOrganization().getCurrencyCode());
        }
        if (jb.getJobtype() != null)
        {
          Element contract = doc.createElement("contract");
          contract.appendChild(doc.createTextNode(jb.getJobtype().getJobTypeName()));
          what.appendChild(contract);
        }
        if (!StringUtils.isNullOrEmpty(jb.getMinimumLevelOfEducation()))
        {
          Element study = doc.createElement("study");
          study.appendChild(doc.createTextNode(jb.getMinimumLevelOfEducation()));
          what.appendChild(study);
        }
        Element where = doc.createElement("where");
        adjobs.appendChild(where);
        

        Location loc = jb.getLocation();
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getAddress())))
        {
          Element streetaddress = doc.createElement("street-address");
          
          streetaddress.appendChild(doc.createTextNode(loc.getAddress()));
          where.appendChild(streetaddress);
          
          streetaddress.setAttribute("display-address", "yes");
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getCity())))
        {
          Element city = doc.createElement("city-name");
          city.appendChild(doc.createTextNode(loc.getCity()));
          where.appendChild(city);
        }
        if ((loc != null) && (!StringUtils.isNullOrEmpty(loc.getZip())))
        {
          Element zip = doc.createElement("zip-code");
          zip.appendChild(doc.createTextNode(loc.getZip()));
          where.appendChild(zip);
        }
        if ((loc != null) && (loc.getCountry() != null))
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode(loc.getCountry().getCountryCode()));
          where.appendChild(country);
        }
        else
        {
          Element country = doc.createElement("country");
          country.appendChild(doc.createTextNode("US"));
          where.appendChild(country);
        }
      }
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source2 = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Constant.getValue("job.feed.path") + "yakaz.xml"));
      





      transformer.transform(source2, result);
      
      replaceCharInFile(Constant.getValue("job.feed.path") + "yakaz.xml");
      

      File file = new File(Constant.getValue("job.feed.path") + "yakaz.xml");
      if (file.exists())
      {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc1 = builder.parse(file);
        
        Transformer tFormer = TransformerFactory.newInstance().newTransformer();
        



        tFormer.setOutputProperty("doctype-public", "");
        tFormer.setOutputProperty("doctype-system", "http://www.yakaz.com/yakaz.dtd");
        


        Source source = new DOMSource(doc1);
        Result result1 = new StreamResult(new File(Constant.getValue("job.feed.path") + "yakaz_feed.xml"));
        tFormer.transform(source, result1);
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on createYakazJobFeed", e);
    }
  }
}
