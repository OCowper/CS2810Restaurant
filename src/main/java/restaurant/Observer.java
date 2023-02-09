package restaurant;

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

}
