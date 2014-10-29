package com.util;

import com.resources.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class XSSKeyUtil
{
  protected static final Logger logger = Logger.getLogger(XSSKeyUtil.class);
  static Map mp = new HashMap();
  
  public static List getListOfValuesByKey(String key)
  {
    if (mp.get(key) == null)
    {
      logger.info("mp.get(key)" + mp.get(key));
      List valueList = new ArrayList();
      String value = (String)Constant.xssMap.get(key);
      logger.info("value" + value);
      if (!StringUtils.isNullOrEmpty(value))
      {
        String[] columns = StringUtils.tokenize(value, ",");
        logger.info("columns" + columns);
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            valueList.add(columns[i]);
            logger.info("columns[i]" + columns[i]);
          }
        }
      }
      mp.put(key, valueList);
      return valueList;
    }
    return (List)mp.get(key);
  }
}
