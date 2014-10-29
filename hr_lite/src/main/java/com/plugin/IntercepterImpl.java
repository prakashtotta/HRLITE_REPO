package com.plugin;

import bsh.BshClassManager;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import com.common.PluginException;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class IntercepterImpl
{
  protected static final Logger logger = Logger.getLogger(IntercepterImpl.class);
  private static BshClassManager _classPath = null;
  private Interpreter interpreter = null;
  
  static
  {
    Interpreter _i = new Interpreter();
    addJarsToScriptClassPath(_i);
    _classPath = _i.getNameSpace().getClassManager();
  }
  
  public static long _lastLoad = System.currentTimeMillis();
  private static boolean useSingleClassLoader;
  
  private static void addJarsToScriptClassPath(Interpreter interpreter)
  {
    try
    {
      if (PluginScripts.jarList != null) {
        for (int i = 0; i < PluginScripts.jarList.size(); i++) {
          interpreter.getClassManager().addClassPath((URL)PluginScripts.jarList.get(i));
        }
      }
    }
    catch (Exception ex)
    {
      logger.info("An exception has occurred while adding the jar to the scripting class path", ex);
      throw new InternalError("Error while adding jar files to the scripting class path.");
    }
  }
  
  public static long getLastLoadTime()
  {
    return _lastLoad;
  }
  
  public static void forceReload()
  {
    Interpreter _i = new Interpreter();
    addJarsToScriptClassPath(_i);
    _classPath = _i.getNameSpace().getClassManager();
    System.out.println("Reloaded jars and classloader");
    _lastLoad = System.currentTimeMillis();
  }
  
  public IntercepterImpl()
  {
    if (useSingleClassLoader)
    {
      NameSpace ns = new NameSpace(_classPath, "ecm" + System.currentTimeMillis());
      this.interpreter = new Interpreter(new StringReader(""), System.out, System.err, false, ns);
    }
    else
    {
      this.interpreter = new Interpreter();
      addJarsToScriptClassPath(this.interpreter);
    }
  }
  
  public void setObject(String name, Object value)
    throws PluginException
  {
    try
    {
      this.interpreter.set(name, value);
    }
    catch (EvalError evalError)
    {
      throw new PluginException("Error setting object on interpreter: " + name, evalError);
    }
  }
  
  public String[] getObjectNames()
  {
    return this.interpreter.getNameSpace().getVariableNames();
  }
  
  public Object getObject(String name)
    throws PluginException
  {
    try
    {
      return this.interpreter.get(name);
    }
    catch (EvalError evalError)
    {
      throw new PluginException("Error getting object from interpreter: " + name, evalError);
    }
  }
  
  public Object eval(String scriptContent)
    throws EvalError
  {
    return this.interpreter.eval(scriptContent);
  }
  
  public Object firePreRequistionCreateScript()
    throws PluginException
  {
    return runUE(PluginScripts.preREQCREATE);
  }
  
  public Object firePostRequistionCreateScript()
    throws PluginException
  {
    return runUE(PluginScripts.postREQCREATE);
  }
  
  public Object firePreRequistionPublishScript()
    throws PluginException
  {
    return runUE(PluginScripts.preREQPUBLISH);
  }
  
  public Object firePostRequistionPublishScript()
    throws PluginException
  {
    return runUE(PluginScripts.postREQPUBLISH);
  }
  
  public Object firePreApplicantSubmit()
    throws PluginException
  {
    return runUE(PluginScripts.preAPPLICANTSUBMIT);
  }
  
  public Object firePostApplicantSubmit()
    throws PluginException
  {
    return runUE(PluginScripts.postAPPLICANTSUBMIT);
  }
  
  public Object firePreApplicantOnBoardSubmit()
    throws PluginException
  {
    return runUE(PluginScripts.preAPPLICANT_ONBOARD_SUBMIT);
  }
  
  public Object firePostApplicantOnBoardSubmit()
    throws PluginException
  {
    return runUE(PluginScripts.postAPPLICANT_ONBOARD_SUBMIT);
  }
  
  /* Error */
  private Object runUE(String code)
    throws PluginException
  {
	  return null;
    // Byte code:
    //   0: new 29	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   7: ldc 50
    //   9: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_1
    //   13: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   19: astore_2
    //   20: aconst_null
    //   21: astore_3
    //   22: aload_0
    //   23: aload_1
    //   24: invokestatic 51	com/plugin/PluginScripts:getScriptContent	(Ljava/lang/String;)Ljava/lang/String;
    //   27: invokevirtual 52	com/plugin/IntercepterImpl:eval	(Ljava/lang/String;)Ljava/lang/Object;
    //   30: astore_3
    //   31: aload_3
    //   32: astore 4
    //   34: aload 4
    //   36: areturn
    //   37: astore 4
    //   39: aload 4
    //   41: invokevirtual 54	bsh/TargetError:getTarget	()Ljava/lang/Throwable;
    //   44: instanceof 43
    //   47: ifeq +21 -> 68
    //   50: new 43	com/common/PluginException
    //   53: dup
    //   54: aload 4
    //   56: invokevirtual 54	bsh/TargetError:getTarget	()Ljava/lang/Throwable;
    //   59: invokevirtual 55	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   62: aload 4
    //   64: invokespecial 45	com/common/PluginException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   67: athrow
    //   68: new 56	com/common/SystemException
    //   71: dup
    //   72: new 29	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   79: ldc 57
    //   81: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: aload_1
    //   85: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   91: invokespecial 58	com/common/SystemException:<init>	(Ljava/lang/String;)V
    //   94: athrow
    //   95: astore 4
    //   97: getstatic 8	com/plugin/IntercepterImpl:logger	Lorg/apache/log4j/Logger;
    //   100: new 29	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   107: ldc 60
    //   109: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: aload_1
    //   113: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: aload 4
    //   121: invokevirtual 10	org/apache/log4j/Logger:info	(Ljava/lang/Object;Ljava/lang/Throwable;)V
    //   124: new 56	com/common/SystemException
    //   127: dup
    //   128: new 29	java/lang/StringBuilder
    //   131: dup
    //   132: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   135: ldc 61
    //   137: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload_1
    //   141: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokespecial 58	com/common/SystemException:<init>	(Ljava/lang/String;)V
    //   150: athrow
    //   151: astore 4
    //   153: getstatic 8	com/plugin/IntercepterImpl:logger	Lorg/apache/log4j/Logger;
    //   156: ldc 62
    //   158: aload 4
    //   160: invokevirtual 10	org/apache/log4j/Logger:info	(Ljava/lang/Object;Ljava/lang/Throwable;)V
    //   163: new 56	com/common/SystemException
    //   166: dup
    //   167: new 29	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   174: ldc 57
    //   176: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload_1
    //   180: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokespecial 58	com/common/SystemException:<init>	(Ljava/lang/String;)V
    //   189: athrow
    //   190: astore 4
    //   192: new 56	com/common/SystemException
    //   195: dup
    //   196: new 29	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   203: ldc 63
    //   205: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload_1
    //   209: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: invokespecial 58	com/common/SystemException:<init>	(Ljava/lang/String;)V
    //   218: athrow
    //   219: astore 5
    //   221: aload 5
    //   223: athrow
    // Line number table:
    //   Java source line #115	-> byte code offset #0
    //   Java source line #118	-> byte code offset #20
    //   Java source line #120	-> byte code offset #22
    //   Java source line #121	-> byte code offset #31
    //   Java source line #122	-> byte code offset #37
    //   Java source line #123	-> byte code offset #39
    //   Java source line #125	-> byte code offset #50
    //   Java source line #128	-> byte code offset #68
    //   Java source line #130	-> byte code offset #95
    //   Java source line #131	-> byte code offset #97
    //   Java source line #132	-> byte code offset #124
    //   Java source line #133	-> byte code offset #151
    //   Java source line #134	-> byte code offset #153
    //   Java source line #136	-> byte code offset #163
    //   Java source line #137	-> byte code offset #190
    //   Java source line #138	-> byte code offset #192
    //   Java source line #140	-> byte code offset #219
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	224	0	this	IntercepterImpl
    //   0	224	1	code	String
    //   19	2	2	evName	String
    //   21	11	3	Obj	Object
    //   37	26	4	ex	bsh.TargetError
    //   95	25	4	e	bsh.ParseException
    //   151	8	4	e	EvalError
    //   190	3	4	e	Exception
    //   219	3	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   22	34	37	bsh/TargetError
    //   22	34	95	bsh/ParseException
    //   22	34	151	bsh/EvalError
    //   22	34	190	java/lang/Exception
    //   20	34	219	finally
    //   37	221	219	finally
  }
}
