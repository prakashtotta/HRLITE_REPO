package webservice.admin;

import com.bean.User;
import com.common.WSException;
import com.dao.UserDAO;
import com.resources.Constant;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.PrintStream;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import webservice.common.Authontication;

@WebService
public class AdminServices
{
  protected static final Logger logger = Logger.getLogger(AdminServices.class);
  
  @WebMethod
  public boolean createBudgetCode(int a, int b)
  {
    return true;
  }
  
  @WebMethod
  public String getEncURL(@WebParam(name="auth") Authontication auth, @WebParam(name="userName") String userName, @WebParam(name="sharedSecret") String sharedSecret, @WebParam(name="page") String page)
    throws WSException
  {
    logger.info("inside getEncURL method");
    String url = null;
    
    User authUser = UserDAO.isLoginSuccessTypeEmployee(auth.getUsername(), EncryptDecrypt.encrypt(auth.getPassword()));
    if (authUser == null) {
      throw new WSException("1000", "Authontication fail.");
    }
    if ((authUser != null) && (authUser.getStatus().equals("A")))
    {
      System.out.println("Authonication successfull");
      try
      {
        url = getEncURL(userName, sharedSecret, page);
      }
      catch (WSException e)
      {
        throw new WSException(e.getErrorCode(), e.getErrorDescription());
      }
    }
    else
    {
      throw new WSException("1001", "User suspended or deleted.");
    }
    return url;
  }
  
  private static String getEncURL(String userName, String sharedSecret, String page)
    throws WSException
  {
    logger.info("inside private getEncURL method");
    logger.info("page" + page);
    String url = null;
    try
    {
      if (StringUtils.isNullOrEmpty(page)) {
        page = "homePage";
      }
      if (StringUtils.isNullOrEmpty(sharedSecret)) {
        throw new WSException("A100", "Shared secret is incorrect.");
      }
      if (StringUtils.isNullOrEmpty(userName)) {
        throw new WSException("U120", "User name is incorrect.");
      }
      String shsecret = Constant.getValue("_SHARED_SECRET_");
      if ((!StringUtils.isNullOrEmpty(sharedSecret)) && (!sharedSecret.equals(shsecret))) {
        throw new WSException("A100", "Shared secret is incorrect.");
      }
      String shEnc = EncryptDecrypt.encrypt(sharedSecret);
      
      url = Constant.getValue("external.url") + "login.do?method=ssologon&username=" + EncryptDecrypt.encrypt(userName) + "&securekey=" + shEnc + "&page=" + page;
    }
    catch (Exception e)
    {
      throw new WSException("99999999", "System error.");
    }
    return url;
  }
}
