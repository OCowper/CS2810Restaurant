package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



/**
 * this class will be used to confirm the orders.
 *
 * @author zjac311, zkac355
 *
 */
public class Confirmation {

  /**
   * sql statement to updates database based on the id that is supplied.
   *
   * @param id represents order that will be updated 
   * @param connection current database connection
   */
  public static void confirm(int id, Connection connection) {
    PreparedStatement stmt = null;
    String tempStmt = "UPDATE Orders SET order_status = 'confirmed' WHERE order_num = ?;";
    try {
      stmt = connection.prepareStatement(tempStmt);
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
