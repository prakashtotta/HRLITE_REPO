package com.performance.bo;

import com.bean.User;
import com.bo.BOFactory;
import com.common.Common;
import com.performance.bean.GoalKra;
import com.performance.bean.GoalKraKpi;
import com.performance.bean.Kra;
import com.performance.bean.KraKPI;
import com.performance.dao.KRADAO;
import com.performance.form.KRAForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class KRABO
{
  public KRADAO kradao;
  
  public String searchKRAListPage(User user1, String results_str, String startIndex_str, String dir_str, String sort_str)
  {
    int results = -1;
    int startIndex = 0;
    String sort = "username";
    String dir = "asc";
    if ((startIndex_str != null) && (startIndex_str.length() > 0)) {
      startIndex = Integer.parseInt(startIndex_str);
    }
    if ((sort_str != null) && (sort_str.length() > 0)) {
      sort = sort_str;
    }
    if ((dir_str != null) && (dir_str.length() > 0)) {
      dir = dir_str;
    }
    if ((results_str != null) && (results_str.length() > 0)) {
      results = Integer.parseInt(results_str);
    }
    List dataList = new ArrayList();
    int totalSize = 0;
    





    Map m = this.kradao.searchKRAListPage(user1, results, startIndex, dir_str, sort_str);
    dataList = (List)m.get(Common.KRA_LIST);
    totalSize = ((Integer)m.get(Common.KRA_COUNT)).intValue();
    

    String[] fields = { "kraId", "parentKraId", "kraName", "kraWeight", "isTrack", "trackStartDate", "trackEndDate", "trackingFreqency", "parentKraName" };
    

    String data = "{\n\"recordsReturned\":" + dataList.size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + totalSize + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTable(dataList, fields, user1) + "}";
    








    return data;
  }
  
  public GoalKra saveKraWithGoal(String typekra, String goalId, KRAForm kForm, User user1, HttpServletRequest request)
  {
    GoalKra goalkra = new GoalKra();
    goalkra.setGoalId(new Long(goalId).longValue());
    
    Kra kra = new Kra();
    kForm.toValue(kra, request);
    if ((!StringUtils.isNullOrEmpty(typekra)) && (typekra.equalsIgnoreCase("true")))
    {
      goalkra.setCreatedBy(user1.getUserName());
      goalkra.setCreatedDate(new Date());
    }
    else
    {
      kra.setCreatedBy(user1.getUserName());
      kra.setCreatedDate(new Date());
      



      kra = saveKra(kra);
      
      saveKraKpi(kForm.getKpiList(), kra.getKraId());
    }
    transformGoalKra(goalkra, kra);
    
    goalkra = BOFactory.getGoalBO().saveGoalKra(goalkra);
    
    List kpiList = kForm.getKpiList();
    if ((kpiList != null) && (kpiList.size() > 0))
    {
      List<GoalKraKpi> gkpiList = new ArrayList();
      for (int i = 0; i < kpiList.size(); i++)
      {
        KraKPI krakpi = (KraKPI)kpiList.get(i);
        GoalKraKpi gkpri = new GoalKraKpi();
        gkpri.setGoalKraId(goalkra.getGoalKraId());
        gkpri.setKpiName(krakpi.getKpiName());
        gkpri.setKraMeasure(krakpi.getKraMeasure());
        
        gkpiList.add(gkpri);
      }
      BOFactory.getGoalBO().saveGoalKraKpi(gkpiList);
    }
    return goalkra;
  }
  
  private void transformGoalKra(GoalKra goalkra, Kra kra)
  {
    goalkra.setKraId(kra.getKraId());
    goalkra.setKraName(kra.getKraName());
    goalkra.setKradesc(kra.getKradesc());
    goalkra.setIsTrack(kra.getIsTrack());
    goalkra.setTrackStartDate(kra.getTrackStartDate());
    goalkra.setTrackEndDate(kra.getTrackEndDate());
    goalkra.setTrackingFreqency(kra.getTrackingFreqency());
    if (kra.getParentKra() != null) {
      goalkra.setParentKraId(kra.getParentKra().getKraId());
    }
    goalkra.setKraWeight(kra.getKraWeight());
    String ismod = (kra.getModifiable() == null) || (kra.getModifiable().equals("N")) ? "N" : "Y";
    goalkra.setModifiable(ismod);
  }
  
  public void saveKraKpi(List<KraKPI> kkpiList, long kraId)
  {
    this.kradao.saveKraKpi(kkpiList, kraId);
  }
  
  public Kra saveKra(Kra kra)
  {
    return this.kradao.saveKra(kra);
  }
  
  public List<KraKPI> getKPIListByKra(long kraId)
  {
    return this.kradao.getKPIListByKra(kraId);
  }
  
  public Kra getKRA(long id)
  {
    return this.kradao.getKRA(id);
  }
  
  public List getAllKRAs()
  {
    return this.kradao.getAllKRAs();
  }
  
  public KRADAO getKradao()
  {
    return this.kradao;
  }
  
  public void setKradao(KRADAO kradao)
  {
    this.kradao = kradao;
  }
}
