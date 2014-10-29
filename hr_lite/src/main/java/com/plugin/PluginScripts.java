package com.plugin;

import com.common.ObjectNotFoundException;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;

public class PluginScripts
  extends ResourceLoader
{
  protected static final Logger logger = Logger.getLogger(PluginScripts.class);
  private static final String FILE_SEPARATOR = File.separator;
  public static String preREQCREATE = null;
  public static String postREQCREATE = null;
  public static String preREQPUBLISH = null;
  public static String postREQPUBLISH = null;
  public static String preAPPLICANTSUBMIT = null;
  public static String postAPPLICANTSUBMIT = null;
  public static String preAPPLICANT_ONBOARD_SUBMIT = null;
  public static String postAPPLICANT_ONBOARD_SUBMIT = null;
  static ArrayList jarList = null;
  
  static
  {
    init();
  }
  
  public static void reset()
  {
    preREQCREATE = null;
    postREQCREATE = null;
    preREQPUBLISH = null;
    postREQPUBLISH = null;
    preAPPLICANTSUBMIT = null;
    postAPPLICANTSUBMIT = null;
    preAPPLICANT_ONBOARD_SUBMIT = null;
    postAPPLICANT_ONBOARD_SUBMIT = null;
    clearContentCache();
  }
  
  public static void clearContentCache()
  {
    contentCache.clear();
  }
  
  protected static synchronized void init()
  {
    try
    {
      if ((preREQCREATE == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preRequistionCreate.java") != null))
      {
        preREQCREATE = "preREQCREATE";
        load(preREQCREATE, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preRequistionCreate.java");
      }
      if ((postREQCREATE == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postRequistionCreate.java") != null))
      {
        postREQCREATE = "postREQCREATE";
        load(postREQCREATE, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postRequistionCreate.java");
      }
      if ((preREQPUBLISH == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preRequistionPublish.java") != null))
      {
        preREQPUBLISH = "preREQPUBLISH";
        load(preREQPUBLISH, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preRequistionPublish.java");
      }
      if ((postREQPUBLISH == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postRequistionPublish.java") != null))
      {
        postREQPUBLISH = "postREQPUBLISH";
        load(postREQPUBLISH, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postRequistionPublish.java");
      }
      if ((preAPPLICANTSUBMIT == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preApplicantSubmit.java") != null))
      {
        preAPPLICANTSUBMIT = "preAPPLICANTSUBMIT";
        load(preAPPLICANTSUBMIT, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preApplicantSubmit.java");
      }
      if ((postAPPLICANTSUBMIT == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postApplicantSubmit.java") != null))
      {
        postAPPLICANTSUBMIT = "postAPPLICANTSUBMIT";
        load(postAPPLICANTSUBMIT, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postApplicantSubmit.java");
      }
      if ((preAPPLICANT_ONBOARD_SUBMIT == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preApplicantOnBoardSubmit.java") != null))
      {
        preAPPLICANT_ONBOARD_SUBMIT = "preAPPLICANT_ONBOARD_SUBMIT";
        load(preAPPLICANT_ONBOARD_SUBMIT, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "preApplicantOnBoardSubmit.java");
      }
      if ((postAPPLICANT_ONBOARD_SUBMIT == null) && (PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postApplicantOnBoardSubmit.java") != null))
      {
        postAPPLICANT_ONBOARD_SUBMIT = "postAPPLICANT_ONBOARD_SUBMIT";
        load(postAPPLICANT_ONBOARD_SUBMIT, "conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR + "postApplicantOnBoardSubmit.java");
      }
      getAllJarsForScrips();
    }
    catch (Exception ex)
    {
      logger.info("Error while reading plugin scripts.Please check the configuration scripts are available.", ex);
    }
  }
  
  public static String getScriptContent(String key)
    throws ObjectNotFoundException
  {
    return getContentByKey(key);
  }
  
  private static void getAllJarsForScrips()
  {
    try
    {
      File file = new File(PluginScripts.class.getClassLoader().getResource("conf" + FILE_SEPARATOR + "plugin" + FILE_SEPARATOR + "jars").getFile());
      if ((file != null) && (file.exists()) && 
        (!file.isFile()))
      {
        File[] files = file.listFiles();
        if (files.length > 0)
        {
          jarList = new ArrayList(files.length);
          for (int i = 0; i < files.length; i++) {
            jarList.add(files[i].toURI().toURL());
          }
        }
      }
    }
    catch (Exception ex)
    {
      logger.info("Error while reading plugin jars.Please check the configuration jars are available.", ex);
    }
  }
}
