package com.form;

import com.bean.User;
import com.bean.filter.BusinessCriteria;
import com.bean.filter.BusinessFilterConditions;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class BusinessRuleForm
  extends ActionForm
{
  public long businessCriteraId;
  public String name;
  public String desc;
  public String type;
  public long idvalue;
  public long variableId;
  public String variableName;
  public String variableType;
  public String variableOrigin;
  public String variableCriteria;
  public String filterValue1;
  public String filterValue2;
  public Date filterValueDate1;
  public Date filterValueDate2;
  public String status;
  public String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String from_type;
  public List screenfieldsList;
  public List customVariablesList;
  public List filterCriteriasList;
  public long filterId;
  public List filtersList;
  public String start;
  public String range;
  public String results;
  public List typeList;
  private List sourceTypeList;
  private int sourceTypeId;
  private List countryList;
  private long countryId;
  private List stateList;
  private long stateId;
  public String work_on_weekends;
  public String work_on_evenings;
  public String work_on_overtime;
  public String want_to_relocate;
  public List yesnofulllist = Constant.getYesNoFullList();
  public String primarySkill;
  private List lovList;
  public String felony_conviction;
  private List genderlist = Constant.genderList();
  public String gender;
  private String filterdate1;
  private String filterdate2;
  public List varibalesListdata;
  public long variableListDataId;
  
  public long getVariableListDataId()
  {
    return this.variableListDataId;
  }
  
  public void setVariableListDataId(long variableListDataId)
  {
    this.variableListDataId = variableListDataId;
  }
  
  public List getVaribalesListdata()
  {
    return this.varibalesListdata;
  }
  
  public void setVaribalesListdata(List varibalesListdata)
  {
    this.varibalesListdata = varibalesListdata;
  }
  
  public String getFilterdate1()
  {
    return this.filterdate1;
  }
  
  public void setFilterdate1(String filterdate1)
  {
    this.filterdate1 = filterdate1;
  }
  
  public String getFilterdate2()
  {
    return this.filterdate2;
  }
  
  public void setFilterdate2(String filterdate2)
  {
    this.filterdate2 = filterdate2;
  }
  
  public List getGenderlist()
  {
    return this.genderlist;
  }
  
  public void setGenderlist(List genderlist)
  {
    this.genderlist = genderlist;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getFelony_conviction()
  {
    return this.felony_conviction;
  }
  
  public void setFelony_conviction(String felonyConviction)
  {
    this.felony_conviction = felonyConviction;
  }
  
  public String getPrimarySkill()
  {
    return this.primarySkill;
  }
  
  public void setPrimarySkill(String primarySkill)
  {
    this.primarySkill = primarySkill;
  }
  
  public List getLovList()
  {
    return this.lovList;
  }
  
  public void setLovList(List lovList)
  {
    this.lovList = lovList;
  }
  
  public String getWork_on_weekends()
  {
    return this.work_on_weekends;
  }
  
  public void setWork_on_weekends(String workOnWeekends)
  {
    this.work_on_weekends = workOnWeekends;
  }
  
  public String getWork_on_evenings()
  {
    return this.work_on_evenings;
  }
  
  public void setWork_on_evenings(String workOnEvenings)
  {
    this.work_on_evenings = workOnEvenings;
  }
  
  public String getWork_on_overtime()
  {
    return this.work_on_overtime;
  }
  
  public void setWork_on_overtime(String workOnOvertime)
  {
    this.work_on_overtime = workOnOvertime;
  }
  
  public String getWant_to_relocate()
  {
    return this.want_to_relocate;
  }
  
  public void setWant_to_relocate(String wantToRelocate)
  {
    this.want_to_relocate = wantToRelocate;
  }
  
  public List getYesnofulllist()
  {
    return this.yesnofulllist;
  }
  
  public void setYesnofulllist(List yesnofulllist)
  {
    this.yesnofulllist = yesnofulllist;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
  }
  
  public long getStateId()
  {
    return this.stateId;
  }
  
  public void setStateId(long stateId)
  {
    this.stateId = stateId;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public int getSourceTypeId()
  {
    return this.sourceTypeId;
  }
  
  public void setSourceTypeId(int sourceTypeId)
  {
    this.sourceTypeId = sourceTypeId;
  }
  
  public List getSourceTypeList()
  {
    return this.sourceTypeList;
  }
  
  public void setSourceTypeList(List sourceTypeList)
  {
    this.sourceTypeList = sourceTypeList;
  }
  
  public List getFilterCriteriasList()
  {
    return this.filterCriteriasList;
  }
  
  public void setFilterCriteriasList(List filterCriteriasList)
  {
    this.filterCriteriasList = filterCriteriasList;
  }
  
  public List getScreenfieldsList()
  {
    return this.screenfieldsList;
  }
  
  public void setScreenfieldsList(List screenfieldsList)
  {
    this.screenfieldsList = screenfieldsList;
  }
  
  public long getBusinessCriteraId()
  {
    return this.businessCriteraId;
  }
  
  public void setBusinessCriteraId(long businessCriteraId)
  {
    this.businessCriteraId = businessCriteraId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
  
  public void setDesc(String desc)
  {
    this.desc = desc;
  }
  
  public long getIdvalue()
  {
    return this.idvalue;
  }
  
  public void setIdvalue(long idvalue)
  {
    this.idvalue = idvalue;
  }
  
  public long getVariableId()
  {
    return this.variableId;
  }
  
  public void setVariableId(long variableId)
  {
    this.variableId = variableId;
  }
  
  public String getVariableName()
  {
    return this.variableName;
  }
  
  public void setVariableName(String variableName)
  {
    this.variableName = variableName;
  }
  
  public String getVariableType()
  {
    return this.variableType;
  }
  
  public void setVariableType(String variableType)
  {
    this.variableType = variableType;
  }
  
  public String getVariableCriteria()
  {
    return this.variableCriteria;
  }
  
  public void setVariableCriteria(String variableCriteria)
  {
    this.variableCriteria = variableCriteria;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getVariableOrigin()
  {
    return this.variableOrigin;
  }
  
  public void setVariableOrigin(String variableOrigin)
  {
    this.variableOrigin = variableOrigin;
  }
  
  public String getFilterValue1()
  {
    return this.filterValue1;
  }
  
  public void setFilterValue1(String filterValue1)
  {
    this.filterValue1 = filterValue1;
  }
  
  public String getFilterValue2()
  {
    return this.filterValue2;
  }
  
  public void setFilterValue2(String filterValue2)
  {
    this.filterValue2 = filterValue2;
  }
  
  public Date getFilterValueDate1()
  {
    return this.filterValueDate1;
  }
  
  public void setFilterValueDate1(Date filterValueDate1)
  {
    this.filterValueDate1 = filterValueDate1;
  }
  
  public Date getFilterValueDate2()
  {
    return this.filterValueDate2;
  }
  
  public void setFilterValueDate2(Date filterValueDate2)
  {
    this.filterValueDate2 = filterValueDate2;
  }
  
  public List getFiltersList()
  {
    return this.filtersList;
  }
  
  public void setFiltersList(List filtersList)
  {
    this.filtersList = filtersList;
  }
  
  public long getFilterId()
  {
    return this.filterId;
  }
  
  public void setFilterId(long filterId)
  {
    this.filterId = filterId;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public String getRange()
  {
    return this.range;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public List getTypeList()
  {
    return this.typeList;
  }
  
  public void setTypeList(List typeList)
  {
    this.typeList = typeList;
  }
  
  public List getCustomVariablesList()
  {
    return this.customVariablesList;
  }
  
  public void setCustomVariablesList(List customVariablesList)
  {
    this.customVariablesList = customVariablesList;
  }
  
  public String getFrom_type()
  {
    return this.from_type;
  }
  
  public void setFrom_type(String fromType)
  {
    this.from_type = fromType;
  }
  
  public void fromValue(BusinessFilterConditions filtercondition1, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (filtercondition1.getFilterValueDate1() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.filterdate1 = DateUtil.convertDateToStringDate(filtercondition1.getFilterValueDate1(), datepattern);
    }
    if (filtercondition1.getFilterValueDate2() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.filterdate2 = DateUtil.convertDateToStringDate(filtercondition1.getFilterValueDate2(), datepattern);
    }
    if ((!StringUtils.isNullOrEmpty(filtercondition1.getVariableType())) && (filtercondition1.getVariableType().equals("list"))) {
      if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("GENDER")))
      {
        this.gender = filtercondition1.getFilterValue1();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("SOURCE_SUBSOURCE")))
      {
        this.sourceTypeId = new Integer(filtercondition1.getFilterValue1()).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("PRIMARY_SKILL")))
      {
        this.primarySkill = filtercondition1.getFilterValue1();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("COUNTRY")))
      {
        this.countryId = new Integer(filtercondition1.getFilterValue1()).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("STATE")))
      {
        this.stateId = new Integer(filtercondition1.getFilterValue1()).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_WEEKENDS")))
      {
        this.work_on_weekends = filtercondition1.getFilterValue1();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_EVENINGS")))
      {
        this.work_on_evenings = filtercondition1.getFilterValue1();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_OVERTIME")))
      {
        this.work_on_overtime = filtercondition1.getFilterValue1();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WANT_TO_RELOCATE")))
      {
        this.want_to_relocate = filtercondition1.getFilterValue1();
      }
      else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("FELONY_CONVICTION")))
      {
        this.felony_conviction = filtercondition1.getFilterValue1();
      }
      else
      {
        this.variableId = filtercondition1.getVariableId();
        this.variableListDataId = new Long(filtercondition1.getFilterValue1()).longValue();
      }
    }
  }
  
  public BusinessFilterConditions toValue(BusinessFilterConditions filtercondition1, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (!StringUtils.isNullOrEmpty(getFilterdate1()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      


      Calendar cal = DateUtil.convertStringDateToCalendar(getFilterdate1(), datepattern);
      

      filtercondition1.setFilterValueDate1(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(getFilterdate2()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      




      Calendar cal = DateUtil.convertStringDateToCalendar(getFilterdate2(), datepattern);
      

      filtercondition1.setFilterValueDate2(cal.getTime());
    }
    if ((!StringUtils.isNullOrEmpty(filtercondition1.getVariableType())) && (filtercondition1.getVariableType().equals("list"))) {
      if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("GENDER"))) {
        filtercondition1.setFilterValue1(this.gender);
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("SOURCE_SUBSOURCE"))) {
        filtercondition1.setFilterValue1(String.valueOf(this.sourceTypeId));
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("PRIMARY_SKILL"))) {
        filtercondition1.setFilterValue1(this.primarySkill);
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("COUNTRY"))) {
        filtercondition1.setFilterValue1(String.valueOf(this.countryId));
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("STATE"))) {
        filtercondition1.setFilterValue1(String.valueOf(this.stateId));
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_WEEKENDS"))) {
        filtercondition1.setFilterValue1(this.work_on_weekends);
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_EVENINGS"))) {
        filtercondition1.setFilterValue1(this.work_on_evenings);
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_OVERTIME"))) {
        filtercondition1.setFilterValue1(this.work_on_overtime);
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WANT_TO_RELOCATE"))) {
        filtercondition1.setFilterValue1(this.want_to_relocate);
      } else if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("FELONY_CONVICTION"))) {
        filtercondition1.setFilterValue1(this.felony_conviction);
      } else {
        filtercondition1.setFilterValue1(String.valueOf(this.variableListDataId));
      }
    }
    return filtercondition1;
  }
  
  public void fromValueBusinessCriteria(BusinessCriteria filtercondition1, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (filtercondition1.getFilterValueDate1() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.filterdate1 = DateUtil.convertDateToStringDate(filtercondition1.getFilterValueDate1(), datepattern);
    }
    if (filtercondition1.getFilterValueDate2() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.filterdate2 = DateUtil.convertDateToStringDate(filtercondition1.getFilterValueDate2(), datepattern);
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("GENDER"))) {
      this.gender = filtercondition1.getFilterValue1();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("SOURCE_SUBSOURCE"))) {
      this.sourceTypeId = new Integer(filtercondition1.getFilterValue1()).intValue();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("PRIMARY_SKILL"))) {
      this.primarySkill = filtercondition1.getFilterValue1();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("COUNTRY"))) {
      this.countryId = new Integer(filtercondition1.getFilterValue1()).intValue();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("STATE"))) {
      this.stateId = new Integer(filtercondition1.getFilterValue1()).intValue();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_WEEKENDS"))) {
      this.work_on_weekends = filtercondition1.getFilterValue1();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_EVENINGS"))) {
      this.work_on_evenings = filtercondition1.getFilterValue1();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_OVERTIME"))) {
      this.work_on_overtime = filtercondition1.getFilterValue1();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WANT_TO_RELOCATE"))) {
      this.want_to_relocate = filtercondition1.getFilterValue1();
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("FELONY_CONVICTION"))) {
      this.felony_conviction = filtercondition1.getFilterValue1();
    }
  }
  
  public BusinessCriteria toValueBusinessCriteria(BusinessCriteria filtercondition1, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (!StringUtils.isNullOrEmpty(getFilterdate1()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      




      Calendar cal = DateUtil.convertStringDateToCalendar(getFilterdate1(), datepattern);
      

      filtercondition1.setFilterValueDate1(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(getFilterdate2()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      




      Calendar cal = DateUtil.convertStringDateToCalendar(getFilterdate2(), datepattern);
      

      filtercondition1.setFilterValueDate2(cal.getTime());
    }
    filtercondition1.setVariableName(getVariableName());
    filtercondition1.setVariableType(getVariableType());
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("GENDER"))) {
      filtercondition1.setFilterValue1(this.gender);
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("SOURCE_SUBSOURCE"))) {
      filtercondition1.setFilterValue1(String.valueOf(this.sourceTypeId));
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("PRIMARY_SKILL"))) {
      filtercondition1.setFilterValue1(this.primarySkill);
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("COUNTRY"))) {
      filtercondition1.setFilterValue1(String.valueOf(this.countryId));
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("STATE"))) {
      filtercondition1.setFilterValue1(String.valueOf(this.stateId));
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_WEEKENDS"))) {
      filtercondition1.setFilterValue1(this.work_on_weekends);
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_EVENINGS"))) {
      filtercondition1.setFilterValue1(this.work_on_evenings);
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WORK_ON_OVERTIME"))) {
      filtercondition1.setFilterValue1(this.work_on_overtime);
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("WANT_TO_RELOCATE"))) {
      filtercondition1.setFilterValue1(this.want_to_relocate);
    }
    if ((!StringUtils.isNullOrEmpty(this.variableName)) && (this.variableName.equals("FELONY_CONVICTION"))) {
      filtercondition1.setFilterValue1(this.felony_conviction);
    }
    return filtercondition1;
  }
}
