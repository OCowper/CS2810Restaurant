package restaurant;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zjac311
 * 
 *CancelOrder class provides the method to cancel an order.
 */
public class CancelOrder {

  /**
   * cancel method deletes an order with the given order ID from the database.
   * @param connection  database connection
   * @param orderId order ID
   */
  public static void cancel(Connection connection, int orderId) {
    String deleteStatement = "DELETE FROM Orders WHERE order_Num = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement)) {
      preparedStatement.setInt(1, orderId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error deleting order with ID " + orderId + ": " + e.getMessage());
    }
  }
}