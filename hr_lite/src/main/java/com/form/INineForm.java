package com.form;

import com.bean.INineFormBean;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class INineForm
  extends ActionForm
{
  protected static final Logger logger = Logger.getLogger(INineForm.class);
  public long i9formid;
  public String firstName;
  public String middleName;
  public String lastName;
  public String maidenName;
  public String address;
  public String inUS;
  public String apartmentno;
  public String dateofbirth;
  public String city;
  public String state;
  public String zip;
  public String socialsecurityno;
  public String employeeattest;
  public String alienno;
  public String i94no;
  public String employeetype;
  public String translatorname;
  public String translatoraddress1;
  public String translatoraddress2;
  public String translatorcountry;
  public String translatorcity;
  public String translatorstate;
  public String translatorzip;
  public String createdBy;
  public Date createdDate;
  public String updatedBy;
  public Date updatedDate;
  public String dateofexpiration;
  public String alienno2;
  
  public String getDateofexpiration()
  {
    return this.dateofexpiration;
  }
  
  public void setDateofexpiration(String dateofexpiration)
  {
    this.dateofexpiration = dateofexpiration;
  }
  
  public long getI9formid()
  {
    return this.i9formid;
  }
  
  public void setI9formid(long i9formid)
  {
    this.i9formid = i9formid;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getMiddleName()
  {
    return this.middleName;
  }
  
  public void setMiddleName(String middleName)
  {
    this.middleName = middleName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public String getMaidenName()
  {
    return this.maidenName;
  }
  
  public void setMaidenName(String maidenName)
  {
    this.maidenName = maidenName;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getInUS()
  {
    return this.inUS;
  }
  
  public void setInUS(String inUS)
  {
    this.inUS = inUS;
  }
  
  public String getApartmentno()
  {
    return this.apartmentno;
  }
  
  public void setApartmentno(String apartmentno)
  {
    this.apartmentno = apartmentno;
  }
  
  public String getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(String dateofbirth)
  {
    this.dateofbirth = dateofbirth;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public String getZip()
  {
    return this.zip;
  }
  
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  
  public String getSocialsecurityno()
  {
    return this.socialsecurityno;
  }
  
  public void setSocialsecurityno(String socialsecurityno)
  {
    this.socialsecurityno = socialsecurityno;
  }
  
  public String getEmployeeattest()
  {
    return this.employeeattest;
  }
  
  public void setEmployeeattest(String employeeattest)
  {
    this.employeeattest = employeeattest;
  }
  
  public String getAlienno()
  {
    return this.alienno;
  }
  
  public void setAlienno(String alienno)
  {
    this.alienno = alienno;
  }
  
  public String getI94no()
  {
    return this.i94no;
  }
  
  public void setI94no(String i94no)
  {
    this.i94no = i94no;
  }
  
  public String getEmployeetype()
  {
    return this.employeetype;
  }
  
  public void setEmployeetype(String employeetype)
  {
    this.employeetype = employeetype;
  }
  
  public String getTranslatorname()
  {
    return this.translatorname;
  }
  
  public void setTranslatorname(String translatorname)
  {
    this.translatorname = translatorname;
  }
  
  public String getTranslatorcountry()
  {
    return this.translatorcountry;
  }
  
  public void setTranslatorcountry(String translatorcountry)
  {
    this.translatorcountry = translatorcountry;
  }
  
  public String getTranslatorcity()
  {
    return this.translatorcity;
  }
  
  public void setTranslatorcity(String translatorcity)
  {
    this.translatorcity = translatorcity;
  }
  
  public String getTranslatorstate()
  {
    return this.translatorstate;
  }
  
  public void setTranslatorstate(String translatorstate)
  {
    this.translatorstate = translatorstate;
  }
  
  public String getTranslatorzip()
  {
    return this.translatorzip;
  }
  
  public void setTranslatorzip(String translatorzip)
  {
    this.translatorzip = translatorzip;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getTranslatoraddress1()
  {
    return this.translatoraddress1;
  }
  
  public void setTranslatoraddress1(String translatoraddress1)
  {
    this.translatoraddress1 = translatoraddress1;
  }
  
  public String getTranslatoraddress2()
  {
    return this.translatoraddress2;
  }
  
  public void setTranslatoraddress2(String translatoraddress2)
  {
    this.translatoraddress2 = translatoraddress2;
  }
  
  public String getAlienno2()
  {
    return this.alienno2;
  }
  
  public void setAlienno2(String alienno2)
  {
    this.alienno2 = alienno2;
  }
  
  public void fromValue(INineFormBean iNineFormBean, HttpServletRequest request)
    throws Exception
  {
    this.i9formid = iNineFormBean.getI9formid();
    this.firstName = iNineFormBean.getFirstName();
    this.lastName = iNineFormBean.getLastName();
    this.middleName = iNineFormBean.getMiddleName();
    this.maidenName = iNineFormBean.getMaidenName();
    this.address = iNineFormBean.getAddress();
    this.inUS = iNineFormBean.getInUS();
    setApartmentno(iNineFormBean.getApartmentno());
    this.dateofbirth = iNineFormBean.getDateofbirth();
    this.city = iNineFormBean.getCity();
    this.state = iNineFormBean.getState();
    this.zip = iNineFormBean.getZip();
    setSocialsecurityno(iNineFormBean.getSocialsecurityno());
    this.employeeattest = iNineFormBean.getEmployeeattest();
    this.dateofexpiration = iNineFormBean.getDateofexpiration();
    setAlienno(iNineFormBean.getAlienno());
    setAlienno2(iNineFormBean.getAlienno2());
    setI94no(iNineFormBean.getI94no());
    this.employeetype = iNineFormBean.getEmployeetype();
    this.translatorname = iNineFormBean.getTranslatorname();
    this.translatoraddress1 = iNineFormBean.getTranslatoraddress1();
    this.translatoraddress2 = iNineFormBean.getTranslatoraddress2();
    this.translatorcountry = iNineFormBean.getTranslatorcountry();
    this.translatorcity = iNineFormBean.getTranslatorcity();
    this.translatorstate = iNineFormBean.getTranslatorstate();
    this.translatorzip = iNineFormBean.getTranslatorzip();
  }
  
  public INineFormBean toValue(INineFormBean iNineFormBean, HttpServletRequest request)
    throws Exception
  {
    iNineFormBean.setI9formid(this.i9formid);
    iNineFormBean.setFirstName(this.firstName);
    iNineFormBean.setMiddleName(this.middleName);
    iNineFormBean.setLastName(this.lastName);
    iNineFormBean.setMaidenName(this.maidenName);
    iNineFormBean.setAddress(this.address);
    iNineFormBean.setInUS(this.inUS);
    iNineFormBean.setApartmentno(this.apartmentno);
    iNineFormBean.setDateofbirth(this.dateofbirth);
    iNineFormBean.setCity(this.city);
    iNineFormBean.setState(this.state);
    iNineFormBean.setZip(this.zip);
    iNineFormBean.setSocialsecurityno(this.socialsecurityno);
    iNineFormBean.setEmployeeattest(this.employeeattest);
    iNineFormBean.setAlienno(this.alienno);
    iNineFormBean.setAlienno2(this.alienno2);
    iNineFormBean.setI94no(this.i94no);
    iNineFormBean.setEmployeetype(this.employeetype);
    iNineFormBean.setDateofexpiration(this.dateofexpiration);
    iNineFormBean.setTranslatorname(this.translatorname);
    iNineFormBean.setTranslatoraddress1(this.translatoraddress1);
    iNineFormBean.setTranslatoraddress2(this.translatoraddress2);
    iNineFormBean.setTranslatorcountry(this.translatorcountry);
    iNineFormBean.setTranslatorcity(this.translatorcity);
    iNineFormBean.setTranslatorstate(this.translatorstate);
    iNineFormBean.setTranslatorzip(this.translatorzip);
    

    return iNineFormBean;
  }
}
