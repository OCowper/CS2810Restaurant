package restaurant;

import java.sql.Connection;

/**
 * Class supporting submission of staff logins to the system.
 *
 * @author zkac355
 */
public class LoginSubmit {
  
  public static Boolean submitLogin(Connection connection, String staffId, String password) {
    return staffId == "12345" && password == "pass1";
  }
  
}
