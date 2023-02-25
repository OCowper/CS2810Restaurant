package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class CancelOrderTest {

  @Test
  void testCancelOrder() {
    Connection connection = EstablishConnection.establishConnection();
    int orderId = 1; // replace with an actual order id from the database
    CancelOrder.finish(connection, orderId, false);

    // Check that the order was actually cancelled by querying the database for the order
    ResultSet resultSet =
        Operations.executeQuery(connection, "SELECT FROM orders WHERE id = " + orderId);
    int rows = 0;
    try {
      while (resultSet.next()) {
        rows++;
      }
    } catch (SQLException e) {
      fail("Failed to retrieve result from database: " + e.getMessage());
    }
    assertEquals(0, rows, "The order was not cancelled");
  }
}
