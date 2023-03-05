package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CancelRequest {
  
  public static void delete(int notifNum, String requestType, Connection connection) {
    PreparedStatement stmt = null;
    String insertStmt = "UPDATE notifications SET dealt_with = true WHERE table_num = ? AND request_type = ?;";
    try {
      stmt = connection.prepareStatement(insertStmt);
      stmt.setInt(1, notifNum);
      stmt.setString(2, requestType);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
