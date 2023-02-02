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




public class insertOrder {
  
  public static int insert(Order order, Connection connection, String waiterFirstName, String waiterLastName) {
    // updated method takes in too more parameters, waiter first and last name. this is to find the waiterID from the staff table
    // this could be changed later on if we would want the waiter to enter their own waiterID. for this case they will have 
    // to remember it however.
    // After finding the ID of the waiter it will add it to the order.
    String findWaiterID = "Select staff_ID from Staff WHERE first_name = '" 
    + waiterFirstName + "' AND last_name = '" + waiterLastName + "';";
    
    // checks for the staff_ID for the waiter
    
    String waiterID = "";
    
    try {
      ResultSet ws = Operations.executeQuery(connection, findWaiterID); // executes query for the waiterID
      waiterID = ws.toString(); 
    }
    catch(Exception e) {
     e.printStackTrace(); 
    }
    
    String findNewId = "SELECT MAX(order_Num) from Orders"; // creates new orderID
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
    String SQL = "INSERT INTO Orders(orderId, items, tableNum, total) VALUES (?,?,?,?,?, False)";
    try { // INSERTS new order with the new order number and the waitersID as primary key and foreign key. 
      stmt = connection.prepareStatement(SQL);
      stmt.setInt(1, Id);
      stmt.setString(2, itemsString);
      stmt.setInt(3, order.getTableNum());
      stmt.setFloat(4, order.getTotal());
      stmt.setString(5, waiterID);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Id; // returns the orderID 
    
  }

  public static int editOrder(Connection connection, Order newOrder, String waiterID, int orderID)
  { 
    // edits the order where it replaces it with everything within the order, so it is a completely new order under same orderID, 
    // another simple way we can do this is by adding the edits to the end of the order 
    // description and just changing the new price.
    // not sure at the moment will discuss.
    String updateStatement = "UPDATE Orders"
            + " SET order_Num = ' " + orderID + "',"
            + " order_Description = ' " + newOrder.getItems() + "',"
            + " tableNum = ' " + newOrder.getTableNum() + "',"
            + " price = ' " + newOrder.getTotal() + "',"
            + " confirm = TRUE,"
            + " waiter_id = '" + waiterID + "'"
            + "WHERE order_Num = '" + orderID + "';";
    try {
      ResultSet rs = Operations.executeQuery(connection, updateStatement);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return orderID;
    
    
    
  }
}
