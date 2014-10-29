package com.util;

import com.resources.Constant;
import java.io.PrintStream;
import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LDAPAuthenticator
{
  private static final String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
  
  public boolean authenticate(String idUser, String password)
  {
    Hashtable<String, String> environment = new Hashtable();
    
    environment.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
    environment.put("java.naming.provider.url", Constant.getValue("ldap.server"));
    environment.put("java.naming.security.principal", idUser + Constant.getValue("ldap.domain.name"));
    
    environment.put("java.naming.security.credentials", password);
    DirContext dir = null;
    try
    {
      dir = new InitialDirContext(environment);
      
      System.out.println("Successfull");
      boolean bool;
      return true;
    }
    catch (AuthenticationException e)
    {
      e.printStackTrace();
      return false;
    }
    catch (NamingException e)
    {
      e.printStackTrace();
      return false;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return false;
    }
    finally
    {
      if (dir != null) {
        try
        {
          dir.close();
        }
        catch (NamingException e) {}
      }
    }
  }
  
  public static void main(String[] args)
  {
    LDAPAuthenticator l = new LDAPAuthenticator();
    l.authenticate("sdas", "shanu)))99rr");
  }
}
