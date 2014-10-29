package network.bo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class FbBulkUserUploadManager
{
  protected static final Logger logger = Logger.getLogger(FbBulkUserUploadManager.class);
  static int nTasks = 5;
  static int tpSize = 10;
  private static ThreadPoolExecutor tpe = null;
  
  public static ThreadPoolExecutor getExcecuter()
  {
    if (tpe == null) {
      tpe = new ThreadPoolExecutor(tpSize, tpSize, 9223372036854775807L, TimeUnit.NANOSECONDS, new LinkedBlockingQueue());
    }
    return tpe;
  }
  
  public static void uploadUser(FbUserTask etask)
  {
    logger.info("start upload users");
    ThreadPoolExecutor tpe = getExcecuter();
    if (tpe != null) {
      tpe.execute(etask);
    }
    logger.info("end upload users");
  }
}
