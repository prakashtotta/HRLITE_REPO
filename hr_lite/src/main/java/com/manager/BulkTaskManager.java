package com.manager;

import com.util.BulkUploadTaskUtil;
import com.util.ExportTaskUtil;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class BulkTaskManager
{
  protected static final Logger logger = Logger.getLogger(BulkTaskManager.class);
  static int nTasks = 2;
  static int tpSize = 5;
  private static ThreadPoolExecutor tpe = null;
  
  public static ThreadPoolExecutor getExcecuter()
  {
    if (tpe == null) {
      tpe = new ThreadPoolExecutor(tpSize, tpSize, 9223372036854775807L, TimeUnit.NANOSECONDS, new LinkedBlockingQueue());
    }
    return tpe;
  }
  
  public static void bulkupload(BulkUploadTaskUtil etask)
  {
    logger.info("start bulkupload");
    ThreadPoolExecutor tpe = getExcecuter();
    tpe.execute(etask);
    logger.info("end bulkupload");
  }
  
  public static void export(ExportTaskUtil etask)
  {
    logger.info("start export");
    ThreadPoolExecutor tpe = getExcecuter();
    tpe.execute(etask);
    logger.info("end export");
  }
}
