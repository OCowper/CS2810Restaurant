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
   * Changes the view being observed.
   *
   * @param view the new view.
   */
  public void setView(ViewInterface view);

  /**.
   * Returns a result set containing all current orders in the database
   *
   * @return the result set to be returned
   */
  public ResultSet returnOrders(Boolean confirm);

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
   * Cancels a specific order by ID.
   *
   * @param orderID the ID of the order to be cancelled
   */
  public void update(int orderID);

}
