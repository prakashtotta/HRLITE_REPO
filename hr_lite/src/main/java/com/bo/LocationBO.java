package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class LocationBO
{
  LovDAO lovdao;
  
  public List getAllLocationNameList()
  {
    return this.lovdao.getLocationList();
  }
  
  public List getAllLocationDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllLocationDetailsForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllLocationDetailsForPagination(User user, String locationname, String locationcode, String countryid, String stateid, String city, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllLocationDetailsForPagination(user, locationname, locationcode, countryid, stateid, city, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllLocationDetails()
  {
    return this.lovdao.getAllLocationDetails();
  }
  
  public int getCountOfAllLocations()
  {
    return this.lovdao.getCountOfAllLocations();
  }
  
  public int getCountOfAllLocations(User user, String locationname, String locationcode, String countryid, String stateid, String city)
  {
    return this.lovdao.getCountOfAllLocations(user, locationname, locationcode, countryid, stateid, city);
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
}
