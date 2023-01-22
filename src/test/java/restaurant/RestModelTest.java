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
  private String[] orderList = {"cake", "fanta", "crisps"};
  private Order testOrder;

  /**
   * setup for the test class.
   */
  @BeforeEach
  public void setup() {
    testController = new RestController();
    testModel = new RestModel(testController);
    testOrder = new Order(orderList, 54, 27.60f);

  }

  @Test // test 1
  void testClass() {
    testModel = new RestModel();
  }

  @Test // test 2
  void testConst() {
    testModel = new RestModel(testController);
  }

  @Test // test 3
  void testGetOrder() {
    testController.update(testOrder);
    assertEquals(testController.returnModel().getOrder().getItems()[0], "cake",
        "first item model should be cake");
  } // refactored to work with new order getters

}
