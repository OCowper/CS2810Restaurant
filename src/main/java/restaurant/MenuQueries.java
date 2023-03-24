package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class containing some queries on the menu table.
 *
 * @author zkac355, James Wood
 */
public class MenuQueries {
  /**
   * Returns the menu as an ArrayList.
   *
   * @param connection current database connection
   * @return Menu items as ArrayList.
   *
   */
  public static ArrayList<String> viewMenu(Connection connection) {

    ArrayList<String> itemList = new ArrayList<String>();
    String getItems = "SELECT * FROM menu;";

    try {
      ResultSet rs = Operations.executeQuery(connection, getItems);
      while (rs.next()) {
        itemList.add(rs.getString("name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return itemList;

  }

  /**
   * Returns items that are less then a certain price.
   *
   * @param connection current database connection
   * @param priceCap value of the users input
   * @return all dishes which are priced under the priceCap
   *
   */
  public static ArrayList<String> filterMenu(Connection connection, int priceCap) {
    ArrayList<String> items = new ArrayList<String>();

    String statement = "SELECT menu_items.dish FROM menu_items WHERE menu_items.price < "
        + Integer.toString(priceCap) + ";";

    try {
      ResultSet rs = Operations.executeQuery(connection, statement);

      while (rs.next()) {
        items.add(rs.getString("dish"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return items;
  }


  /**
   * Returns an array list of the items given the chosen type.
   *
   * @param connection current database connection
   * @param menuType the type of item the customer wants to filter through.
   * @return all items of the chosen type.
   *
   */
  public static ResultSet filterMenuType(Connection connection, String menuType) {

    String statement =
        "SELECT * from items where item_category = '" + menuType + "' and available = true;";
    ResultSet rs = Operations.executeQuery(connection, statement);
    return rs;
  }


  /**
   * Returns an array list of the items given the chosen type.
   *
   * @param connection current database connection
   * @return all items in stock.
   *
   */
  public static ResultSet inStockItems(ItemType type, Connection connection) {
    String query = "SELECT item_name FROM items WHERE item_category = '" + type.toString() + "';";
    return Operations.executeQuery(connection, query);
  }

  /**
   * Removes the '!' from item name making it in stock.
   *
   * @param connection current database connection
   * @param item that is in stock
   * @throws SQLException if connection failed
   * 
   */
  public static void setInStock(Connection connection, String item) {
    String statement = "UPDATE items SET item_name = ?, available = true WHERE item_name = ?";
    StringBuffer buffer = new StringBuffer(item);
    buffer.deleteCharAt(item.length() - 1);
    try {
      PreparedStatement prepStatement = connection.prepareStatement(statement);
      prepStatement.setString(1, buffer.toString());
      prepStatement.setString(2, item);
      prepStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets item_name to have '!' at end if out of stock.
   *
   * @param connection current database connection
   * @param item that is out of stock
   * @throws SQLException if connection failed
   * 
   */
  public static void setOutStock(Connection connection, String item) {
    String statement = "UPDATE items SET item_name = ?, available = false WHERE item_name = ?";
    try {
      PreparedStatement prepStatement = connection.prepareStatement(statement);
      prepStatement.setString(1, item + "!");
      prepStatement.setString(2, item);
      prepStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}


