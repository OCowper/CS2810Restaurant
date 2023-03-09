package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Returns the current status of an order via it's number.
 *
 * @author Tomor, zkac355
 */
public class OrderTracking {

  /**
   * Method to return order status by ID.
   *
   * @param connection the current db connection
   * @param orderId the ID of the desired order
   * @return return the status as a string.
   */
  public static String getOrderStatus(Connection connection, int orderId) {
    String status = "";
    String query = "SELECT order_status FROM Orders WHERE order_Num = ?;";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      // Add input validation for the orderId parameter
            if (orderId <= 0) {
                throw new IllegalArgumentException("Order ID must be a positive integer");
            }
      preparedStatement.setInt(1, orderId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        status = resultSet.getString("order_status");
      }
    } catch (SQLException e) {
      System.err.println("Error searching for order with ID " + orderId + ": " + e.getMessage());
    }
    return status;
  }
}


