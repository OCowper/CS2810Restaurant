package restaurant;

/**
 * Controller to facilitate communication between the view (gui) and the model (behaviour).
 *
 * @author zkac355
 */
public class RestController {
  
  String[] curOrder = {""};
  
  
  /**
   * Retrieves the current order from the view.
   */
  public void retrieveOrder() {
    curOrder[0] = "cake";
  }
  
  /**
   * Sends the current stored order on.
   *
   * @return the current order.
   */
  public String[] returnOrder() {
    return curOrder;
  }
}
