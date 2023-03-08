package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderTracking{

public static String GetOrderStatus(Connection connection, int orderId) {
  String status = "";
  String query = "SELECT status FROM Orders WHERE order_Num = ?";
  try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    preparedStatement.setInt(1, orderId);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      status = resultSet.getString("status");
    }
  } catch (SQLException e) {
    System.err.println("Error searching for order with ID " + orderId + ": " + e.getMessage());
  }
  return status;
}
}


