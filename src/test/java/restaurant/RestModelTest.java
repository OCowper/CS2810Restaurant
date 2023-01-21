package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing for the model class.
 *
 * @author zkac355
 */
public class RestModelTest {

  private RestModel testModel;
  private RestController testController;
  
  /**
   * setup for the test class.
   */
  @BeforeEach
  public void setup() {
    testController = new RestController();
    testModel = new RestModel(testController);
    
  }
  
  @Test // test 1
  void testClass() {
    testModel = new RestModel();
  }
  
  @Test // test 2
  void testConst() { 
    testModel = new RestModel(testController); 
  }
  
  @Test //test 3
  void testGetOrder() {
    testController.retrieveOrder();
    testModel.retrieveOrder();
    assertEquals(testModel.getOrder().items[0], "Cake", "order first item should be cake");
  }

}
