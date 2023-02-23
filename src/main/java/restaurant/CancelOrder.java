package restaurant;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles SQL statements cancelling orders.
 *
 * @author zjac311, zkac355
 */
public class CancelOrder {

  /**
   * cancel method deletes an order with the given order ID from the database.
   *
   * @param connection database connection
   * @param orderId order ID
   */
  public static void finish(Connection connection, int orderId, boolean confirmed) {
    String deleteStatement = "DELETE FROM Orders WHERE order_Num = ? ;";
    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement)) {
      preparedStatement.setInt(1, orderId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error deleting order with ID " + orderId + ": " + e.getMessage());
    }
    String moveStatement = "INSERT INTO DoneOrders values (?, ?);";
    try (PreparedStatement preparedStatement = connection.prepareStatement(moveStatement)) {
      preparedStatement.setInt(1, orderId);
      preparedStatement.setBoolean(2, confirmed);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error deleting order with ID " + orderId + ": " + e.getMessage());
    }
  }
}
