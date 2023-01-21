package restaurant;

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
    testModel = new RestModel();
    testController = new RestController();
  }
  
  @Test // test 1
  void testClass() {
    testModel = new RestModel();
  }
  
  @Test // test 2
  void testConst() { 
    testModel = new RestModel(testController); 
  }

}
