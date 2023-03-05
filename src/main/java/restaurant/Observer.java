package restaurant;

import java.sql.ResultSet;

/**
 * Interface defining Observers (objects that watch other classes).
 *
 * @author zkac355
 */
public interface Observer {
  
  /**
   * Updates the observer with information. This update is for orders coming from the order view.
   *
   * @param curOrder the new order information.
   */
  public void update(Order curOrder);
  
  /**
   * Updates the observer with information. This update is for details coming from the model.
   *
   * @param confirmed Whether or not the order is confirmed.
   */
  public void update(Boolean confirmed);

  /**
   * Updates the observer with information. This update is for details coming from the login view.
   *
   * @param userId the submitted userId
   * @param password the submitted password
   */
  public void update(String userId, String password);
  
  /**
   * Cancels or completes a specific order by ID.
   *
   * @param orderId the ID of the order to be cancelled or completed
   */
  public void update(int orderId);
  
  /**
   * Updates the observer with a help request.
   *
   * @param curRequest current request for help.
   */
  public void update(HelpRequest request);
  
  /**
   * Changes the view being observed.
   *
   * @param view the new view.
   */
  public void setView(ViewInterface view);

  /**.
   * Returns a result set containing all current orders in the database
   *
   * @param confirm whether or not the orders to be returned are listed as confirmed
   * @param finished whether or not the orders should be from the done tables
   * @return the result set to be returned
   */
  public ResultSet returnOrders(Boolean confirm, Boolean finished);

  /**
   * Collects orders so they can be displayed.
   */
  public void orderStartup();

  /**
   * Confirms an order.
   *
   * @param topId the ID of the earlier order.
   */
  public void confirmOrder(String topId);

  /**
   * Retrives menu items stored in the database.
   *
   * @return result set containing menu items.
   */
  public ResultSet getMenuItems();

  /**
   * Retrieves notifications from the database.
   *
   * @return result set containing notifications
   */
  public ResultSet returnNotifs();

  /**
   * Removes a notification from the database.
   *
   * @param notifNum the number of the notification to remove.
   * @param selectedRequest the type of request to remove.
   */
  public void resolveNotif(int notifNum, String selectedRequest);




}
