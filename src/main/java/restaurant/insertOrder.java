package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
* this class will be used to insert the orders.
*
* @author zhac324
*
*/

public static int insertOrder(Order order, Connection connection) {
    // find next available id and set it to nextId variable
    // ...

    // create a string containing all items in the order, separated by colons
    String itemsString = String.join(":", order.getItems());

    PreparedStatement stmt = null;
    String SQL = "INSERT INTO Orders(orderId, items, tableNum, total) VALUES (?,?,?,?)";
    try {
      stmt = connection.prepareStatement(SQL);
      stmt.setInt(1, nextId);
      stmt.setString(2, itemsString);
      stmt.setInt(3, order.getTableNum());
      stmt.setFloat(4, order.getTotal());

      stmt.executeUpdate();
      // set the order id
      order.setOrderId(nextId);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return nextId;
  }
