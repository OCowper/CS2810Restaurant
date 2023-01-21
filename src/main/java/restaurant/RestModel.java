package restaurant;

/**
 * Behaviour handler for the system.
 *
 * @author zkac355
 */
public class RestModel {
  
  private RestController controller;
  private Order curOrder;
  
  /**
   * Constructs an empty instance of the model.
   */
  public RestModel() {}
  
  /**
   * Constructs an instance of the model containing the controller.
   *
   * @param controller the controller.
   */
  public RestModel(RestController controller) {
    this.controller = controller;
  }
  
  /**
   * Collects the current order from the controller.
   */
  public void retrieveOrder() {
    curOrder = controller.returnOrder();
  }
  
  /**
   * Returns the current order. 
   *
   * @return the order to be returned.
   */
  public Order getOrder() {
    return curOrder;
  }

}
