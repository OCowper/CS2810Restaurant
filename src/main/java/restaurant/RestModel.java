package restaurant;

/**
 * Behaviour handler for the system.
 *
 * @author zkac355
 */
public class RestModel implements Subject {
  
  private Order curOrder;
  private Observer obs;
  
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

}
