package webservice.user;

import com.bean.User;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.common.WSException;
import com.dao.UserDAO;
import com.util.EncryptDecrypt;
import java.io.PrintStream;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import webservice.common.Authontication;
import webservice.common.Transform;

@WebService
public class UserServices
{
  @WebMethod
  public long addUser(@WebParam(name="auth") Authontication auth, @WebParam(name="userdata") UserData user)
    throws WSException
  {
    long userId = 0L;
    
    User authUser = UserDAO.isLoginSuccessTypeEmployee(auth.getUsername(), EncryptDecrypt.encrypt(auth.getPassword()));
    if (authUser == null) {
      throw new WSException("1000", "Authontication fail.");
    }
    if ((authUser != null) && (authUser.getStatus().equals("A")))
    {
      System.out.println("Authonication successfull");
      try
      {
        User usr = new User();
        usr = Transform.convertWsUserDataToUser(user, usr);
        
        userId = BOFactory.getUserBO().addUser(authUser, usr);
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
    return userId;
  }
}
