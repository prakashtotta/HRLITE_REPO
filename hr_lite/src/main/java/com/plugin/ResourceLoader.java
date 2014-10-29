package com.plugin;

import com.common.ObjectNotFoundException;
import com.common.SystemException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;

public class ResourceLoader
{
  public static Map<String, String> contentCache = new TreeMap();
  protected static final Logger logger = Logger.getLogger(ResourceLoader.class);
  
  public static void load(String key, String uri)
  {
    if (key == null) {
      throw new SystemException("ResourceLoader.load() : Key cant be null");
    }
    if (contentCache.containsKey(key)) {
      throw new SystemException("Already loaded a resource with the same key " + key);
    }
    String body = null;
    try
    {
      body = readFile(uri);
    }
    catch (IOException e)
    {
      logger.error("Could not load file for URI : " + uri + ", Key passed : " + key, e);
    }
    contentCache.put(key, body);
  }
  
  public static void clearContentCache()
  {
    contentCache.clear();
  }
  
  public static String getContentByKey(String key)
    throws ObjectNotFoundException
  {
    String content = (String)contentCache.get(key);
    if (content == null) {
      throw new ObjectNotFoundException("Could not locate any content for the given key, Key: " + key);
    }
    return content;
  }
  
  public static String readFile(String fileName)
    throws IOException
  {
    BufferedReader fileReader = new BufferedReader(new InputStreamReader(ResourceLoader.class.getClassLoader().getResourceAsStream(fileName)));
    



    StringBuffer buf = new StringBuffer();
    String tmp = null;
    do
    {
      tmp = fileReader.readLine();
      if (tmp != null) {
        buf.append(tmp).append("\n");
      }
    } while (tmp != null);
    fileReader.close();
    return buf.toString();
  }
  
  public static boolean isAvailable(String dir, String resourceName)
  {
    String rsrcName = dir + "/" + resourceName;
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(rsrcName);
    boolean isAvail = is != null;
    try
    {
      is.close();
    }
    catch (Exception ex) {}
    return isAvail;
  }
}
