package com.action;

import com.bean.Country;
import com.bean.Location;
import com.bean.Region;
import com.bean.State;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.LocationForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LocationAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(LocationAction.class);
  
  public ActionForward loadState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String cid = request.getParameter("cid");
    System.out.println("cid" + cid);
    String rid = request.getParameter("rid");
    System.out.println("rid" + rid);
    LocationForm locForm = (LocationForm)form;
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    locForm.setCountryList(countryList);
    

    List stateList = BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue());
    System.out.println("<<<<<<<<<<< what is the size of stateList : " + stateList.size());
    locForm.setStateList(stateList);
    


    String readPreview = request.getParameter("readPreview");
    locForm.setReadPreview(readPreview);
    request.setAttribute("locationsaved", "no");
    request.setAttribute("selectregion", "yes");
    request.setAttribute("selectcountry", "yes");
    
    return mapping.findForward("statesList");
  }
  
  public ActionForward loadCountry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String rid = request.getParameter("rid");
    System.out.println("rid" + rid);
    
    LocationForm locForm = (LocationForm)form;
    List regionList = BOFactory.getLovBO().getRegions();
    locForm.setRegionList(regionList);
    List countryList = BOFactory.getLovBO().getAllCountriesByRegion(new Long(rid).longValue());
    locForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    locForm.setStateList(stateList);
    


    String readPreview = request.getParameter("readPreview");
    locForm.setReadPreview(readPreview);
    request.setAttribute("locationsaved", "no");
    request.setAttribute("selectregion", "yes");
    
    return mapping.findForward("createLocation");
  }
  
  public ActionForward setStateNull(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    LocationForm locForm = (LocationForm)form;
    List stateList = new ArrayList();
    locForm.setStateList(stateList);
    return mapping.findForward("setStateNull");
  }
  
  public ActionForward selectlocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selectlocation method");
    LocationForm locForm = (LocationForm)form;
    String readpreview = request.getParameter("readPreview");
    List countryList = BOFactory.getLovBO().getAllCountries();
    locForm.setCountryList(countryList);
    List regionList = BOFactory.getLovBO().getRegions();
    

    locForm.setRegionList(regionList);
    locForm.setReadPreview(readpreview);
    Country ct = (Country)countryList.get(0);
    List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    locForm.setStateList(stateList);
    

    return mapping.findForward("selectlocation");
  }
  
  public ActionForward saveLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveLocation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    LocationForm locationForm = (LocationForm)form;
    Location location = new Location();
    locationForm.toValue(location, request);
    location.setSuper_user_key(user.getSuper_user_key());
    location = LovOpsDAO.saveLocation(location);
    

    List countryList = BOFactory.getLovBO().getAllCountries();
    locationForm.setCountryList(countryList);
    List stateList = new ArrayList();
    if (location.getCountry() != null)
    {
      stateList = BOFactory.getLovBO().getStateListByCountry(location.getCountry().getCountryId());
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    locationForm.setStateList(stateList);
    List regionList = BOFactory.getLovBO().getRegions();
    locationForm.setRegionList(regionList);
    
    locationForm.fromValue(location, request);
    request.setAttribute("locationsaved", "yes");
    return mapping.findForward("createLocation");
  }
  
  public ActionForward updateLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateLocation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    LocationForm locationForm = (LocationForm)form;
    
    String id = request.getParameter("id");
    Location location = LovOpsDAO.getLocationDetails(id);
    locationForm.toValue(location, request);
    
    location = LovOpsDAO.updateLocation(location);
    List countryList = BOFactory.getLovBO().getAllCountries();
    locationForm.setCountryList(countryList);
    List stateList = new ArrayList();
    if (location.getCountry() != null)
    {
      stateList = BOFactory.getLovBO().getStateListByCountry(location.getCountry().getCountryId());
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    locationForm.setStateList(stateList);
    List regionList = BOFactory.getLovBO().getRegions();
    locationForm.setRegionList(regionList);
    
    locationForm.fromValue(location, request);
    request.setAttribute("locationupdated", "yes");
    
    return mapping.findForward("createLocation");
  }
  
  public ActionForward deleteLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteLocation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    LocationForm locationForm = (LocationForm)form;
    
    String id = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteLocation(new Long(id).longValue());
      
      List countryList = BOFactory.getLovBO().getAllCountries();
      locationForm.setCountryList(countryList);
      
      List stateList = new ArrayList();
      locationForm.setStateList(stateList);
      
      request.setAttribute("locationdeleted", "yes");
      return mapping.findForward("createLocation");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward locationlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside locationlist method");
    LocationForm locationForm = (LocationForm)form;
    List countryList = BOFactory.getLovBO().getAllCountries();
    locationForm.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    locationForm.setStateList(stateList);
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("locationlist");
  }
  
  public ActionForward searchlocationslist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchlocationslist method");
    LocationForm locationForm = (LocationForm)form;
    List countryList = BOFactory.getLovBO().getAllCountries();
    locationForm.setCountryList(countryList);
    List stateList = new ArrayList();
    if (locationForm.getCountryId() != 0L)
    {
      stateList = BOFactory.getLovBO().getStateListByCountry(locationForm.getCountryId());
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    locationForm.setStateList(stateList);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("locationlist");
  }
  
  public ActionForward createLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createLocation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    LocationForm locationForm = (LocationForm)form;
    

    List regionList = BOFactory.getLovBO().getRegions();
    locationForm.setRegionList(regionList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    locationForm.setCountryList(countryList);
    

    List stateList = new ArrayList();
    locationForm.setStateList(stateList);
    

    String readPreview = request.getParameter("readPreview");
    locationForm.setReadPreview(readPreview);
    request.setAttribute("locationsaved", "no");
    return mapping.findForward("createLocation");
  }
  
  public ActionForward locationinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside locationdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    LocationForm locationForm = (LocationForm)form;
    
    String locId = request.getParameter("id");
    Location location = LovOpsDAO.getLocationDetails(locId);
    


    List countryList = BOFactory.getLovBO().getAllCountries();
    locationForm.setCountryList(countryList);
    Country ct = location.getCountry();
    if (ct != null)
    {
      List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
      locationForm.setStateList(stateList);
    }
    List regionList = BOFactory.getLovBO().getRegions();
    locationForm.setRegionList(regionList);
    


    locationForm.fromValue(location, request);
    
    request.setAttribute("applicantsaved", "no");
    String readpreview = request.getParameter("readPreview");
    locationForm.setReadPreview(readpreview);
    request.setAttribute("foredit", "yes");
    return mapping.findForward("locationinfo");
  }
  
  public ActionForward locationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside locationdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    LocationForm locationForm = (LocationForm)form;
    
    String locId = request.getParameter("id");
    Location location = LovOpsDAO.getLocationDetails(locId);
    


    List countryList = BOFactory.getLovBO().getAllCountries();
    locationForm.setCountryList(countryList);
    Country ct = location.getCountry();
    if (ct != null)
    {
      List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
      locationForm.setStateList(stateList);
    }
    List regionList = BOFactory.getLovBO().getRegions();
    locationForm.setRegionList(regionList);
    


    locationForm.fromValue(location, request);
    
    request.setAttribute("applicantsaved", "no");
    String readpreview = request.getParameter("readPreview");
    locationForm.setReadPreview(readpreview);
    request.setAttribute("foredit", "yes");
    return mapping.findForward("createLocation");
  }
  
  public ActionForward locationdetails1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside locationdetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String locationId = request.getParameter("id");
    
    LocationForm locationForm = (LocationForm)form;
    
    Location location = LovOpsDAO.getLocationDetails(locationId);
    
    toForm(locationForm, location);
    String redpreview = request.getParameter("readPreview");
    locationForm.setReadPreview(redpreview);
    
    return mapping.findForward("createLocation");
  }
  
  public ActionForward searchlocations(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchgroupchars method");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String readpreview = request.getParameter("readPreview");
    
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    LocationForm locform = (LocationForm)form;
    

    List countryList = BOFactory.getLovBO().getAllCountries();
    locform.setCountryList(countryList);
    List regionList = BOFactory.getLovBO().getRegions();
    locform.setRegionList(regionList);
    locform.setReadPreview(readpreview);
    

    List locationList = LovOpsDAO.getLocationsByCritera(user1, locform.getLocationName(), locform.getLocationCode(), locform.getRegionId(), locform.getCountryId(), locform.getStateId(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getCountOfLocationByCritera(user1, locform.getLocationName(), locform.getLocationCode(), locform.getRegionId(), locform.getCountryId(), locform.getStateId());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    locform.setStart(String.valueOf(start1));
    locform.setRange(String.valueOf(range1));
    locform.setResults(String.valueOf(totaluser));
    locform.setLocationCode(locform.getLocationCode());
    List stateList = new ArrayList();
    if (locform.getCountryId() != 0L)
    {
      stateList = BOFactory.getLovBO().getStateListByCountry(locform.getCountryId());
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    locform.setStateList(stateList);
    locform.setLocationsList(locationList);
    
    locform.setReadPreview(readpreview);
    return mapping.findForward("selectlocation");
  }
  
  public ActionForward locationpreview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside locationpreview method");
    String locationId = request.getParameter("id");
    
    Location loc = LovOpsDAO.getLocationDetails(locationId);
    LocationForm locationform = (LocationForm)form;
    
    return mapping.findForward("createLocation");
  }
  
  public void toForm(LocationForm locationForm, Location location)
    throws Exception
  {
    locationForm.setLocationId(location.getLocationId());
    locationForm.setLocationCode(location.getLocationCode());
    locationForm.setLocationName(location.getLocationName());
    locationForm.setAddress(location.getAddress());
    locationForm.setZip(location.getZip());
    locationForm.setPhone(location.getPhone());
    locationForm.setFax(location.getFax());
    locationForm.setCity(location.getCity());
    locationForm.setCreatedBy(location.getCreatedBy());
    locationForm.setCreatedDate(location.getCreatedDate());
    locationForm.setStatus(location.getStatus());
    locationForm.setNotes(location.getNotes());
    




    locationForm.setCountryName(location.getCountry().getCountryName());
  }
  
  public void toValue(Location location, LocationForm locationForm)
  {
    location.setLocationCode(locationForm.getLocationCode());
    location.setLocationName(locationForm.getLocationName());
    location.setAddress(locationForm.getAddress());
    location.setZip(locationForm.getZip());
    location.setPhone(locationForm.getPhone());
    location.setFax(locationForm.getFax());
    location.setCity(locationForm.getCity());
    location.setCreatedBy(locationForm.getCreatedBy());
    location.setCreatedDate(locationForm.getCreatedDate());
    location.setStatus("A");
    location.setNotes(locationForm.getNotes());
    





    Country country = new Country();
    country.setCountryId(locationForm.getCountryId());
    location.setCountry(country);
    
    State state = new State();
    state.setStateId(locationForm.getStateId());
    location.setState(state);
    



    Region region = new Region();
    region.setRegionId(locationForm.getRegionId());
    location.setRegion(region);
  }
}
