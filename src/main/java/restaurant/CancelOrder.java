package restaurant;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles SQL statements cancelling orders.
 *
 * @author zjac311
 */
public class CancelOrder {

  /**
   * cancel method deletes an order with the given order ID from the database.
   *
   * @param connection database connection
   * @param orderId order ID
   */
  public static void cancel(Connection connection, int orderId) {
    System.out.println("cancel order method");
    String deleteStatement = "DELETE FROM Orders WHERE order_Num = ? ;";
    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement)) {
      preparedStatement.setInt(1, orderId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error deleting order with ID " + orderId + ": " + e.getMessage());
    }
  }
}
