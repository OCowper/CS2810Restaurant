package restaurant;

/**
 * Controller to facilitate communication between the view (gui) and the model (behaviour).
 *
 * @author zkac355
 */
public class RestController implements Observer {
  
  private RestModel model = new RestModel(this);

  // holds the current order
  private Order curOrder;
  //holds the current view
  private MainView view;
  
  /**
   * Constructs an empty instance of the controller.
   */
  public RestController() {}
  
  public RestController(MainView view) {
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
  public void update(Order curOrder) {
    this.curOrder = curOrder;
    storeOrder();
    
  }

  @Override
  public void update(Boolean confirmed) {
    curOrder.setConfirmed(confirmed);
    
  }
  
  private void storeOrder() {
    model.retrieveOrder(curOrder);
    view.confirmRecieved();
  }
}
