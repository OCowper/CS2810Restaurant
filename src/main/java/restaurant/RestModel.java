package restaurant;

/**
 * Behaviour handler for the system.
 *
 * @author zkac355
 */
public class RestModel {
  
  RestController controller;
  
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

}
