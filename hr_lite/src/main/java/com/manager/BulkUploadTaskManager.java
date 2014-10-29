package com.manager;

import com.util.BulkUploadTaskUtil;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class BulkUploadTaskManager
{
  protected static final Logger logger = Logger.getLogger(BulkUploadTaskManager.class);
  static int nTasks = 1;
  static int tpSize = 2;
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
}
