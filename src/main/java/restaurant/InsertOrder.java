package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this class will be used to insert the orders.
 *
 * @author zhac324 zkac256
 *
 */



public class InsertOrder {

  /**
   * Takes in an order and inserts it into the database, along with the first and last name of the
   * waiter.
   *
   * @param order the order to be inserted
   * @param connection the current db connection
   * @param waiterFirstName first name of the submitting waiter
   * @param waiterLastName last name of the submitting waiter
   * @return returns the ID of the submitted order
   */
  public static int insert(Order order, Connection connection, String waiterFirstName,
      String waiterLastName) {
    // updated method takes in too more parameters, waiter first and last name. this is to find the
    // waiterID from the staff table
    // this could be changed later on if we would want the waiter to enter their own waiterID. for
    // this case they will have
    // to remember it however.
    // After finding the ID of the waiter it will add it to the order.
    String findWaiterId = "Select staff_ID from Staff WHERE first_name = '" + waiterFirstName
        + "' AND last_name = '" + waiterLastName + "';";

    // checks for the staff_ID for the waiter

    String waiterId = "";

    try {
      ResultSet ws = Operations.executeQuery(connection, findWaiterId);
      // executes query for the waiterID
      while (ws.next()) {
        waiterId = ws.getString(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    int newId = 0;
    String findNewId = "SELECT MAX(order_Num) from Orders;"; // creates new orderID
    int topCurId = 0;

    try {
      ResultSet rs = Operations.executeQuery(connection, findNewId);
      while (rs.next()) {
        topCurId = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    findNewId = "SELECT MAX(order_Num) from DoneOrders;";
    int topDoneId = 0;
    try {
      ResultSet rs = Operations.executeQuery(connection, findNewId);
      while (rs.next()) {
        topDoneId = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (topCurId > topDoneId) {
      newId = topCurId + 1;
    } else {
      newId = topDoneId + 1;
    }


    PreparedStatement stmt = null;
    String insertStatement = "INSERT INTO Orders(order_Num, order_Description, "
        + "table_Num, price, confirm, waiter_id) VALUES (?,?,?,?, False, ?);";
    try {
      // INSERTS new order with the new order number and the waitersID as primary key and foreign
      // key.
      stmt = connection.prepareStatement(insertStatement);
      stmt.setInt(1, newId);
      stmt.setString(2, order.getItems());
      stmt.setInt(3, order.getTableNum());
      stmt.setFloat(4, order.getTotal());
      stmt.setString(5, waiterId);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return topCurId; // returns the orderID

  }

  /**
   * edits an order to replace everything within it.
   *
   * @param connection current database connection
   * @param newOrder the new order to be stored
   * @param waiterId current waiter ID
   * @param orderId current order ID
   * @return the order ID
   */
  public static int editOrder(Connection connection, Order newOrder, String waiterId, int orderId) {
    // edits the order where it replaces it with everything within the order, so it is a completely
    // new order under same orderID,
    // another simple way we can do this is by adding the edits to the end of the order
    // description and just changing the new price.
    // not sure at the moment will discuss.
    String updateStatement =
        "UPDATE Orders" + " SET order_Num = ' " + orderId + "'," + " order_Description = ' "
            + newOrder.getItems() + "'," + " tableNum = ' " + newOrder.getTableNum() + "',"
            + " price = ' " + newOrder.getTotal() + "'," + " confirm = TRUE," + " waiter_id = '"
            + waiterId + "'" + "WHERE order_Num = '" + orderId + "';";
    try {
      Operations.executeQuery(connection, updateStatement);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return orderId;



  }
}
