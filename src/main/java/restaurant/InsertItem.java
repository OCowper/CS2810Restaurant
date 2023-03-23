package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Container class to run item insertion methods.
 *
 * @author zkac355, Tomor Voshki
 */
public class InsertItem {

  // finds the next available item number to assign to a new item
  private static int createId(Item item, Connection connection) {
    String findNewId = "SELECT MAX(item_id) from items;"; // creates new itemId
    int topCurId = -2;
    try {
      ResultSet rs = Operations.executeQuery(connection, findNewId);
      while (rs.next()) {
        topCurId = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return topCurId + 1;
  }

  /**
   * Inserts a new stock item into the database.
   *
   * @param item the item to be inserted.
   * @param connection the current database connection.
   */
  public static void insert(Item item, Connection connection) {
    PreparedStatement stmt = null;
    String query = "INSERT INTO Items(item_id, item_name, price, item_description, ingredients, "
        + "calories, item_category, available, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    try {
      stmt = connection.prepareStatement(query);
      stmt.setInt(1, createId(item, connection));
      stmt.setString(2, item.getItemName());
      stmt.setFloat(3, item.getPrice());
      stmt.setString(4, item.getItemDescription());
      stmt.setString(5, item.getIngredients());
      stmt.setInt(6, item.getCalories());
      stmt.setString(7, item.getItemCategory());
      stmt.setBoolean(8, item.isAvailable());
      stmt.setString(9, item.getImagePath());
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
