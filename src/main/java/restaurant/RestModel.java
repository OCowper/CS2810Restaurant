package restaurant;

import java.sql.Connection;
import java.sql.ResultSet;


/**
 * Behaviour handler for the system.
 *
 * @author zkac355
 */
public class RestModel implements Subject {

  private Order curOrder;
  private Observer obs;
  private Connection connection = EstablishConnection.establishConnection();

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
  }

  /**
   * Collects the current order from the controller.
   */
  public void retrieveOrder(Order curOrder) {
    this.curOrder = curOrder;
    curOrder.setId(InsertOrder.insert(curOrder, connection, "Oscar", "Cowper"));
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
   * @return the result set of orders to be returned.
   */
  public ResultSet queryOrders(Boolean confirm) {
    String query = "";
    if (confirm) {
      query = "SELECT * FROM orders WHERE confirm = true AND order_num > 0 order by order_num;";
    } else {
      query = "SELECT * FROM orders WHERE confirm = false order by order_num;";
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



}
