package restaurant;

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

  // test retrieval deleted after refactoring to work with observers.
}
