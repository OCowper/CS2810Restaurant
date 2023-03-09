package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserts requests into the notification db.
 *
 * @author zkac355
 */
public class InsertRequest {

  /**
   * Inserts a request for help into the DB.
   *
   * @param request the request to be inserted.
   * @param connection the current connection DB.
   */
  public static void insert(HelpRequest request, Connection connection) {
    PreparedStatement stmt = null;
    String insertStmt =
        "INSERT INTO notifications(table_num, request_type, dealt_with) VALUES(?, ?, False);";
    try {
      stmt = connection.prepareStatement(insertStmt);
      stmt.setInt(1, request.getTableNum());
      stmt.setString(2, request.getRequestType().toString());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
