package restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class supporting submission of staff logins to the system.
 *
 * @author zkac355
 */
public class LoginSubmit {

  /**
   * Takes a login and checks if it is valid or not.
   *
   * @param connection current database connection.
   * @param staffId submitted ID of the login person
   * @param password submitted password of the login person
   * @return true if there is a match, false if not.
   */
  public static Boolean submitLogin(Connection connection, String staffId, String password) {
    String query = "SELECT password FROM staff WHERE staff_id = '" + staffId + "';";
    String trialPswd = "null";
    try {
      ResultSet rs = Operations.executeQuery(connection, query);
      while (rs.next()) {
        trialPswd = rs.getString(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return password.equals(trialPswd);
  }

}
