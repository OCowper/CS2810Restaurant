package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles the insertion of notifications that orders are complete.
 *
 * @author zkac355
 */
public class InsertOrderNotif {

  /**
   * Method for inserting the notifs.
   *
   * @param orderNum the number of the complete order.
   * @param connection the current db connection.
   */
  public static void insert(int orderNum, Connection connection) {
    int tableNum = findTableNum(orderNum, connection);
    PreparedStatement stmt = null;
    String insertStmt = "INSERT INTO NOTIFICATIONS values(?, 'Order Complete', ?);";
    try {
      stmt = connection.prepareStatement(insertStmt);
      stmt.setInt(1, tableNum);
      stmt.setBoolean(2, false);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  //finds the table number relating to a certain order.
  private static int findTableNum(int orderNum, Connection connection) {
    int tableNum = -1;
    String query = "SELECT table_num FROM orders WHERE order_num = " + orderNum + ";";
    ResultSet rs = Operations.executeQuery(connection, query);
    try {
      while (rs.next()) {
        tableNum = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tableNum;
  }

}
