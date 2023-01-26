package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this class will be used to insert the orders.
 *
 * @author zhac324
 *
 */

public class insertOrder {
  public static int insert(Order order, Connection connection) {
    
    String findNewId = "SELECT MAX(order_Num) from Orders";
    int Id = 0;
    try {
      ResultSet rs = Operations.executeQuery(connection, findNewId);
      Id = rs.getInt(1) + 1;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    
    String itemsString = String.join(":", order.getItems());

    PreparedStatement stmt = null;
    String SQL = "INSERT INTO Orders(orderId, items, tableNum, total) VALUES (?,?,?,?, False)";
    try {
      stmt = connection.prepareStatement(SQL);
      stmt.setInt(1, Id);
      stmt.setString(2, itemsString);
      stmt.setInt(3, order.getTableNum());
      stmt.setFloat(4, order.getTotal());
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Id;
    
  }
}
