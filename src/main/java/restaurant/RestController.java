package restaurant;

/**
 * Controller to facilitate communication between the view (gui) and the model (behaviour).
 *
 * @author zkac355
 */
public class RestController implements Observer {

  // holds the current order
  private Order curOrder;


  /**
   * Retrieves the current order from the view.
   */
  public void retrieveOrder() {
    String[] itemList = {"Cake", "Fanta", "Steak"};
    curOrder = new Order(itemList, 54, 27.60f);
  }

  /**
   * Sends the current stored order on.
   *
   * @return the current order.
   */
  public Order returnOrder() {
    return curOrder;
  }

  @Override
  public void update(Order curOrder) {
    this.curOrder = curOrder;
    
  }

  @Override
  public void update(Boolean confirmed) {
    curOrder.setConfirmed(confirmed);
    
  }
}
