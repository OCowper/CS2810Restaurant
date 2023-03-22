package restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;


/**
 * Behaviour handler for the system.
 *
 * @author zkac355
 */
public class RestModel implements Subject {

  private Order curOrder;
  private Observer obs;
  private Scanner scanner = new Scanner(System.in);
  private Connection connection = EstablishConnection.establishConnection(scanner);

  /**
   * Constructs an empty instance of the model.
   */
  public RestModel() {}

  /**
   * Constructs an instance of the model containing the controller.
   *
   * @param controller the controller.
   */
  public RestModel(Observer controller) {
    addObservers(controller);
    RestStartup.startup(connection, scanner);
    scanner.close();
  }

  /**
   * Collects the current order from the controller.
   */
  public void retrieveOrder(Order curOrder) {
    this.curOrder = curOrder;
    curOrder.setId(InsertOrder.insert(curOrder, connection, "admin", "admin"));
  }

  /**
   * Submits the login and returns back to the controller the result.
   *
   * @param userId the submitted userID
   * @param password the submitted password
   */
  public void acceptLogin(String userId, String password) {
    obs.update(LoginSubmit.submitLogin(connection, userId, password));

  }

  /**
   * Returns the current order.
   *
   * @return the order to be returned.
   */
  public Order getOrder() {
    return curOrder;
  }

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }

  @Override
  public void notifyObservers(Observer obs) {
    obs.update(true);

  }

  /**
   * Collects a subset of all orders from the database.
   *
   * @param finished whether or not finished orders should be collected
   * @return the result set of orders to be returned.
   */
  public ResultSet queryOrders(Boolean confirm, Boolean finished) {
    String query = "";
    if (confirm && finished) {
      query =
          "SELECT * FROM doneOrders WHERE cancelled = false AND order_num > 0 order by order_num;";
    } else if (!confirm && finished) {
      query =
          "SELECT * FROM doneOrders WHERE cancelled = true and order_num > 0 order by order_num;";
    } else if (confirm && !finished) {
      query = "SELECT * FROM orders WHERE order_status = "
          + "'confirmed' AND order_num > 0 order by order_num;";
    } else {
      query = "SELECT * FROM orders WHERE "
          + "order_status = 'recieved' and order_num > 0 order by order_num;";
    }
    return Operations.executeQuery(connection, query);
  }

  /**
   * Edits the database to change an order id from unconfirmed to confirmed.
   *
   * @param orderId the ID of the order to be confirmed.
   */
  public void confirmOrder(String orderId) {
    Confirmation.confirm(Integer.parseInt(orderId), connection);

  }

  /**
   * Returns menu items stored in the database.
   *
   * @return result set containing all menu items.
   */
  public ResultSet getMenu() {
    String query = "SELECT * FROM items where available = True;";
    return Operations.executeQuery(connection, query);
  }

  /**
   * Returns menu items of a certain type.
   *
   * @param type the type of item to be returned
   * @return a result set containing the items.
   */
  public ResultSet getMenu(String type) {
    return MenuQueries.filterMenuType(connection, type);
  }

  /**
   * Removes an order from the database and moves it to the done orders database.
   *
   * @param orderId the ID of the order
   * @param confirmed whether the order was completed or cancelled.
   */
  public void removeOrder(int orderId, boolean confirmed) {
    CancelOrder.finish(connection, orderId, confirmed);
  }

  /**
   * Returns currently stored uncompleted requests from the database.
   *
   * @return result set containing all requests not dealt with.
   */
  public ResultSet getNotifs() {
    String query = "SELECT table_num, request_type FROM notifications WHERE dealt_with = False;";
    return Operations.executeQuery(connection, query);
  }

  /**
   * Submits a help request to the database.
   *
   * @param request the request to be submitted
   */
  public void submitRequest(HelpRequest request) {
    InsertRequest.insert(request, connection);
  }

  /**
   * Removes a request from the notification db.
   *
   * @param notifNum the notification number.
   * @param requestType the type of request
   */
  public void removeNotification(int notifNum, String requestType) {
    CancelRequest.delete(notifNum, requestType, connection);
  }

  /**
   * Adds a notification that an order is ready to be delivered.
   *
   * @param orderNum the number of the order.
   */
  public void orderCompleteNotify(int orderNum) {
    InsertOrderNotif.insert(orderNum, connection);

  }

  /**
   * Returns the status of an order.
   *
   * @param orderId the id of the order to be returned
   * @return returns the status as a string.
   */
  public String getStatus(int orderId) {
    return OrderTracking.getOrderStatus(connection, orderId);
  }

  /**
   * Returns menu items that are in stock by type.
   *
   * @param type the type of menu item specificed
   * @return result set of in stock items.
   */
  public ResultSet getMenuType(ItemType type) {
    return MenuQueries.inStockItems(type, connection);
  }

  /**
   * Takes an in stock item and sets it to out of stock.
   *
   * @param selectedItem the item to be toggled.
   */
  public void toggleItemStock(String selectedItem) {
    if (selectedItem.charAt(selectedItem.length() - 1) == '!') {
      MenuQueries.setInStock(connection, selectedItem);
    } else {
      MenuQueries.setOutStock(connection, selectedItem);
    }
  }


}
