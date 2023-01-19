package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the controller class.
 *
 * @author zkac355
 */
public class RestControllerTest {

  private RestController testController;

  /**
   * Setup for test class.
   */
  @BeforeEach
  public void setup() {
    testController = new RestController();

  }

  @Test // test 1
  void testClass() {
    testController = new RestController();
  } // passed by creating a controller class

  @Test // test 2
  void testRetrieval() {
    testController.retrieveOrder();
    assertEquals(testController.returnOrder().items[0], "Cake",
        "first element should be set to cake");
  } // refactored to work with order class
}
