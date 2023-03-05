package restaurant;

import java.sql.ResultSet;

/**
 * Controller to facilitate communication between the view (gui) and the model (behaviour).
 *
 * @author zkac355
 */
public class RestController implements Observer {

  private RestModel model = new RestModel(this);

  // holds the current order
  private Order curOrder;
  // holds the current view
  private ViewInterface view;
  // holds the submitted userID
  private String userId;
  // holds the submitted password
  private String password;

  /**
   * Constructs an empty instance of the controller.
   */
  public RestController() {}

  /**
   * Constructs an instance of the controller containing the view.
   *
   * @param view the current view representing the current screen.
   */
  public RestController(ViewInterface view) {
    this.view = view;
    this.view.addObservers(this);
  }

  /**
   * Changes the currently observed view.
   */
  @Override
  public void setView(ViewInterface view) {
    this.view = view;
    this.view.addObservers(this);
  }


  /**
   * Sends the current stored order on.
   *
   * @return the current order.
   */
  public Order returnOrder() {
    return curOrder;
  }

  /**
   * Returns the instance of the current model.
   *
   * @return the current model.
   */
  public RestModel returnModel() {
    return model;
  }

  @Override
  public void update(String userId, String password) {
    this.userId = userId;
    this.password = password;
    login();
  }

  @Override
  public void update(Order curOrder) {
    this.curOrder = curOrder;
    storeOrder();

  }

  @Override
  public void update(Boolean confirmed) {
    if (view.getClass() == FoodMenuView.class) {
      curOrder.setConfirmed(confirmed);
    }
    view.acceptBoolean(confirmed);
  }

  @Override
  public void update(int orderId) {
    if (view.getClass() == NewOrdersView.class) {
      model.removeOrder(orderId, true);
    } else {
      model.removeOrder(orderId, false);
    }
  }

  @Override
  public void update(HelpRequest request) {
    model.submitRequest(request);
    
  }
  
  @Override 
  public void resolveNotif(int notifNum, String requestType) {
    model.removeNotification(notifNum, requestType);
  }


  private void storeOrder() {
    model.retrieveOrder(curOrder);
  }

  private void login() {
    model.acceptLogin(userId, password);
  }

  @Override
  public ResultSet returnOrders(Boolean confirm, Boolean finished) {
    return model.queryOrders(confirm, finished);
  }

  @Override
  public void orderStartup() {
    view.startup();
  }

  @Override
  public void confirmOrder(String orderId) {
    model.confirmOrder(orderId);

  }

  @Override
  public ResultSet getMenuItems() {
    return model.getMenu();

  }

  @Override
  public ResultSet returnNotifs() {
    return model.getNotifs();
  }

 
}
