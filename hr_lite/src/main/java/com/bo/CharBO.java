package com.bo;

import com.bean.Accomplishments;
import com.bean.Characteristic;
import com.bean.User;
import com.dao.LovDAO;
import java.util.ArrayList;
import java.util.List;

public class CharBO
{
  LovDAO lovdao;
  
  public List getCharListForPagination(User user1, int pageSize, int startIndex, String dir_str, String sort_str, String charname)
  {
    return this.lovdao.getCharListForPagination(user1, pageSize, startIndex, dir_str, sort_str, charname);
  }
  
  public int getCountOfAllChar(User user1, String charname)
  {
    return this.lovdao.getCountOfAllChar(user1, charname);
  }
  
  public List getGroupCharListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getGroupCharListForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfAllGroupsChar()
  {
    return this.lovdao.getCountOfAllGroupsChar();
  }
  
  public List<String> getCompetencies(String query)
  {
    List complist = this.lovdao.getCompetencies(query);
    List<String> compstrlist = new ArrayList();
    for (int i = 0; i < complist.size(); i++)
    {
      Characteristic chartar = (Characteristic)complist.get(i);
      String cname = chartar.getCharName();
      compstrlist.add(cname);
    }
    return compstrlist;
  }
  
  public List<String> getAccomplishments(String query)
  {
    List accomplist = this.lovdao.getAccomplishments(query);
    List<String> accomptrlist = new ArrayList();
    for (int i = 0; i < accomplist.size(); i++)
    {
      Accomplishments chartar = (Accomplishments)accomplist.get(i);
      String cname = chartar.getAccName();
      accomptrlist.add(cname);
    }
    return accomptrlist;
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
