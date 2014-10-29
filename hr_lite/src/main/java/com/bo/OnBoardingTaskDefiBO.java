package com.bo;

import com.dao.TaskDAO;
import java.util.List;
import org.apache.log4j.Logger;

public class OnBoardingTaskDefiBO
{
  protected static final Logger logger = Logger.getLogger(OnBoardingTaskDefiBO.class);
  TaskDAO taskdao;
  
  public static void main(String[] args) {}
  
  public List getAllOnBoardingTaskDefinationPaginationSearch(long super_user_key, String taskname, String primaryownerid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllOnBoardingTaskDefinationPaginationSearch method");
    return this.taskdao.getAllOnBoardingTaskDefinationForPaginationSearch(super_user_key, taskname, primaryownerid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getAllOnBoardingTaskDetailssearch(long super_user_key, String taskname, String primaryownerid)
  {
    logger.info("Inside getAllOnBoardingTaskDetailssearch method");
    return this.taskdao.getAllOnBoardingTaskDetailssearch(super_user_key, taskname, primaryownerid);
  }
  
  public List getAllOnBoardingTaskDefiForPagination(long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllOnBoardingTaskDetailssearch method");
    return this.taskdao.getAllOnBoardingTaskDefiDetailsForPagination(super_user_key, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllOnBoardingTaskDefiDetails(long super_user_key)
  {
    logger.info("Inside getAllOnBoardingTaskDefiDetails method");
    return this.taskdao.getAllOnBoardingTaskDefiDetails(super_user_key);
  }
  
  public static List<String> getOnBoardTaskDefData(String query)
  {
    List<String> users = TaskDAO.getOnBoardTaskDataByQuery(query);
    return users;
  }
  
  public TaskDAO getTaskdao()
  {
    return this.taskdao;
  }
  
  public void setTaskdao(TaskDAO taskdao)
  {
    this.taskdao = taskdao;
  }
}
