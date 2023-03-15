package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
   * Returns items that are less then a certain price
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
    String query = "SELECT item_name FROM items WHERE available = true and item_category = '"
        + type.toString() + "';";
    return Operations.executeQuery(connection, query);
  }

  /**
   * Sets inStock to true for an item of choice.
   *
   * @param connection current database connection
   * @param item that is in stock
   * @throws SQLException if connection failed
   * 
   */
  public static void setInStock(Connection connection, String item) throws SQLException {
    String statement = "UPDATE menu_items SET inStock = true WHERE dish = ?";
    PreparedStatement pStatement = connection.prepareStatement(statement);
    pStatement.setString(1, item);
    pStatement.executeUpdate();
  }

  /**
   * Sets inStock to false for an item of choice.
   *
   * @param connection current database connection
   * @param item that is out of stock
   * @throws SQLException if connection failed
   * 
   */
  public static void setOutStock(Connection connection, String item) {
    String statement = "UPDATE items SET available = false WHERE item_name = ?";
    try {
      PreparedStatement pStatement = connection.prepareStatement(statement);
      pStatement.setString(1, item);
      pStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}


